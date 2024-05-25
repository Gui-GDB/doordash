package com.gdb.doordash.service.impl;

import com.gdb.doordash.constant.MessageConstant;
import com.gdb.doordash.constant.StatusConstant;
import com.gdb.doordash.dto.DishDTO;
import com.gdb.doordash.dto.DishPageQueryDTO;
import com.gdb.doordash.entity.Dish;
import com.gdb.doordash.entity.DishFlavor;
import com.gdb.doordash.exception.DeletionNotAllowedException;
import com.gdb.doordash.mapper.DishFlavorMapper;
import com.gdb.doordash.mapper.DishMapper;
import com.gdb.doordash.mapper.SetMealDishMapper;
import com.gdb.doordash.result.PageResult;
import com.gdb.doordash.service.DishService;
import com.gdb.doordash.vo.DishVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: Mr.Gui
 * @program: doordash
 * @create: 2024-05-16 21:02
 * @description: 菜品管理业务逻辑
 **/
@Service
public class DishServiceImpl implements DishService {
    @Resource
    private DishMapper dishMapper;
    @Resource
    DishFlavorMapper dishFlavorMapper;
    @Resource
    private SetMealDishMapper setmealDishMapper;

    /**
     * 新增菜品和对应口味
     */
    @Override
    @Transactional
    public int saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        int res = 0;
        //向菜品表插入一条数据
        res += dishMapper.insert(dish);
        //向菜品口味表中插入一条数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            //获取外键值
            flavors.forEach(dishFlavor -> dishFlavor.setDishId(dish.getId()));
            //向口味表中插入N条数据
            res += dishFlavorMapper.insertBatch(flavors);
        }
        return res;
    }

    /**
     * 菜品分页查询
     */
    @Override
    public PageResult<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        List<DishVO> dishVOS = dishMapper.pageQuery(dishPageQueryDTO);
        //获取总的返回记录条数
        PageInfo<DishVO> pageInfo = new PageInfo<>(dishVOS);
        long total = pageInfo.getTotal();
        return new PageResult<>(total, dishVOS);
    }

    /**
     * 菜品批量删除
     */
    @Override
    @Transactional//事务
    public Integer deleteBatch(List<Long> ids) {
        Integer res = 0;
        //判断当前菜品是否能够删除---是否存在起售中的菜品？？
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            if (Objects.equals(dish.getStatus(), StatusConstant.ENABLE)) {
                //当前菜品处于起售中，不能删除
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }

        //判断当前菜品是否能够删除---是否被套餐关联了？？
        List<Long> setMealIds = setmealDishMapper.getSetMealIdsByDishIds(ids);
        if (setMealIds != null && setMealIds.size() > 0) {
            //当前菜品被套餐关联了，不能删除
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        //删除菜品表中的菜品数据
        for (Long id : ids) {
            res += dishMapper.deleteById(id);
            //删除菜品关联的口味数据
            res += dishFlavorMapper.deleteByDishId(id);
        }
        return res;
    }

    /**
     * 根据id查询菜品和对应的口味数据
     */
    public DishVO getByIdWithFlavor(Long id) {
        //根据id查询菜品数据
        Dish dish = dishMapper.getById(id);
        //根据菜品id查询口味数据
        List<DishFlavor> dishFlavors = dishFlavorMapper.getByDishId(id);
        //将查询到的数据封装到VO
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(dishFlavors);
        return dishVO;
    }

    /**
     * 根据id修改菜品基本信息和对应的口味信息
     */
    public void updateWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        //修改菜品表基本信息
        dishMapper.update(dish);

        //删除原有的口味数据
        dishFlavorMapper.deleteByDishId(dishDTO.getId());

        //重新插入口味数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(dishFlavor -> dishFlavor.setDishId(dishDTO.getId()));
            //向口味表插入n条数据
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * 根据id设置菜品的状态
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        Dish dish = Dish.builder().id(id).status(status).build();
        dishMapper.update(dish);
    }

    /**
     * 根据分类id查询相关的菜品
     */
    @Override
    public List<Dish> dishList(Long categoryId) {
        Dish dish = Dish.builder().categoryId(categoryId).status(StatusConstant.ENABLE).build();
        return dishMapper.selectDish(dish);
    }

    /**
     * 条件查询菜品和口味
     */
    public List<DishVO> listWithFlavor(Dish dish) {
        List<Dish> dishList = dishMapper.selectDish(dish);

        List<DishVO> dishVOList = new ArrayList<>();

        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d, dishVO);

            //根据菜品id查询对应的口味
            List<DishFlavor> flavors = dishFlavorMapper.getByDishId(d.getId());

            dishVO.setFlavors(flavors);
            dishVOList.add(dishVO);
        }

        return dishVOList;
    }
}
