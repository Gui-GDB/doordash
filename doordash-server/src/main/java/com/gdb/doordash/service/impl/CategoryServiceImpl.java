package com.gdb.doordash.service.impl;

import com.gdb.doordash.constant.StatusConstant;
import com.gdb.doordash.context.BaseContext;
import com.gdb.doordash.dto.CategoryDTO;
import com.gdb.doordash.dto.CategoryPageQueryDTO;
import com.gdb.doordash.entity.Category;
import com.gdb.doordash.entity.Employee;
import com.gdb.doordash.mapper.CategoryMapper;
import com.gdb.doordash.result.PageResult;
import com.gdb.doordash.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: Mr.Gui
 * @program: doordash
 * @create: 2024-05-09 17:11
 * @description: 分类管理业务
 **/

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        //开启分页
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        //执行sql查询语句
        List<Category> categories = categoryMapper.pageQuery(categoryPageQueryDTO);
        //获取总的返回记录条数
        PageInfo<Category> pageInfo = new PageInfo<>(categories);
        long total = pageInfo.getTotal();
        return new PageResult(total, categories);
    }

    @Override
    public int addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        //属性拷贝
        BeanUtils.copyProperties(categoryDTO, category);

        //分类状态默认为禁用状态0
        category.setStatus(StatusConstant.DISABLE);

        //设置创建时间、修改时间、创建人、修改人
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());
        return categoryMapper.insertCategory(category);
    }

    @Override
    public int dropCategory(Long id) {
        //todo 这里还没有使用其他的表，后面补
/*        //查询当前分类是否关联了菜品，如果关联了就抛出业务异常
        Integer count = dishMapper.countByCategoryId(id);
        if(count > 0){
            //当前分类下有菜品，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }

        //查询当前分类是否关联了套餐，如果关联了就抛出业务异常
        count = setmealMapper.countByCategoryId(id);
        if(count > 0){
            //当前分类下有菜品，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }*/
        return categoryMapper.deleteCategory(id);
    }

    @Override
    public int modifyCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);

        //设置修改时间、修改人
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());
        return categoryMapper.updateCategory(category);
    }

    /**
     * 启用、禁用分类
     */
    public int startOrStop(Integer status, Long id) {
        Category category = Category.builder()
                .id(id)
                .status(status)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();
        return categoryMapper.updateCategory(category);
    }
}
