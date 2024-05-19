package com.gdb.doordash.service.impl;

import com.gdb.doordash.constant.MessageConstant;
import com.gdb.doordash.constant.StatusConstant;
import com.gdb.doordash.dto.SetmealDTO;
import com.gdb.doordash.dto.SetmealPageQueryDTO;
import com.gdb.doordash.entity.Dish;
import com.gdb.doordash.entity.Setmeal;
import com.gdb.doordash.entity.SetmealDish;
import com.gdb.doordash.exception.DeletionNotAllowedException;
import com.gdb.doordash.exception.SetmealEnableFailedException;
import com.gdb.doordash.mapper.DishMapper;
import com.gdb.doordash.mapper.SetMealDishMapper;
import com.gdb.doordash.mapper.SetmealMapper;
import com.gdb.doordash.result.PageResult;
import com.gdb.doordash.service.SetmealService;
import com.gdb.doordash.vo.SetmealVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author: Mr.Gui
 * @program: doordash
 * @create: 2024-05-18 15:32
 * @description: 套餐管理模块
 **/

@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {
    @Resource
    private SetmealMapper setmealMapper;
    @Resource
    private SetMealDishMapper setMealDishMapper;
    @Resource
    private DishMapper dishMapper;

    /**
     * 添加套餐
     */
    @Override
    @Transactional
    public void addSetmeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        //向套餐表插入数据
        setmealMapper.insertBatch(setmeal);
        //获取套餐生成的id
        Long setmealId = setmeal.getId();
        log.info("获取到生成套餐的id值 ---->  {}", setmealId);
        //将套餐id保存到套餐关系表中
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes.forEach(setmealDish -> setmealDish.setSetmealId(setmealId));
        //保存套餐和菜品的关联关系
        setMealDishMapper.insertBatch(setmealDishes);
    }

    /**
     * 分页查询套餐
     */
    @Override
    public PageResult<SetmealVO> querySetmealPage(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        List<SetmealVO> setmealVOS = setmealMapper.pageSelect(setmealPageQueryDTO);
        PageInfo<SetmealVO> pageInfo = new PageInfo<>(setmealVOS);
        return new PageResult<>(pageInfo.getPageSize(), setmealVOS);
    }

    /**
     * 批量删除套餐
     */
    @Override
    @Transactional
    public void deleteBatchSetmeal(Long[] ids) {
        //第一步，判断需要删除的列表中是否存在售卖中的套餐
        for (Long id : ids) {
            Setmeal setmeal = setmealMapper.getById(id);
            //售卖中的套餐不能被删除
            if (Objects.equals(setmeal.getStatus(), StatusConstant.ENABLE)) {
                log.info(MessageConstant.DISH_ON_SALE);
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        //第二步，删除套餐和与套餐菜品关系的数据
        for (Long id : ids) {
            //注意，这里要删除子表然后才删除父表
            //删除套餐菜品关系表中的数据
            setMealDishMapper.deleteBySetmealId(id);
            //删除套餐表中的数据
            setmealMapper.deleteSetmealById(id);
        }
    }

    /**
     * 起售或停售套餐
     */
    @Override
    public void startOrStopSetmeal(Integer status, Long id) {
        //起售套餐时，判断套餐内是否有停售菜品，有停售菜品提示"套餐内包含未启售菜品，无法启售"
        if (Objects.equals(status, StatusConstant.ENABLE)) {
            List<Dish> dishList = dishMapper.getBySetmealId(id);
            if (dishList != null && dishList.size() > 0) {
                dishList.forEach(dish -> {
                    if (StatusConstant.DISABLE.equals(dish.getStatus())) {
                        throw new SetmealEnableFailedException(MessageConstant.SETMEAL_ENABLE_FAILED);
                    }
                });
            }
        }
        //修改套餐的状态
        Setmeal setmeal = Setmeal.builder().id(id).status(status).build();
        setmealMapper.update(setmeal);
    }

    /**
     * 根据id查询套餐和套餐菜品关系
     */
    public SetmealVO getByIdWithDish(Long id) {
        Setmeal setmeal = setmealMapper.getById(id);
        List<SetmealDish> setmealDishes = setMealDishMapper.getBySetmealId(id);

        SetmealVO setmealVO = new SetmealVO();
        BeanUtils.copyProperties(setmeal, setmealVO);
        setmealVO.setSetmealDishes(setmealDishes);

        return setmealVO;
    }

    /**
     * 修改套餐
     */
    @Transactional
    public void update(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        //1、修改套餐表，执行update
        setmealMapper.update(setmeal);

        //套餐id
        Long setmealId = setmealDTO.getId();

        //2、删除套餐和菜品的关联关系，操作setmeal_dish表，执行delete
        setMealDishMapper.deleteBySetmealId(setmealId);

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes.forEach(setmealDish ->
                setmealDish.setSetmealId(setmealId));
        //3、重新插入套餐和菜品的关联关系，操作setmeal_dish表，执行insert
        setMealDishMapper.insertBatch(setmealDishes);
    }
}
