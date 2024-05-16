package com.gdb.doordash.service;

import com.gdb.doordash.dto.CategoryDTO;
import com.gdb.doordash.dto.CategoryPageQueryDTO;
import com.gdb.doordash.entity.Category;
import com.gdb.doordash.result.PageResult;

import java.util.List;

public interface CategoryService {
    /**
     * 分页查询
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 添加分类
     */
    int addCategory(CategoryDTO categoryDTO);

    /**
     * 删除分类
     */
    int dropCategory(Long id);

    /**
     * 修改分类
     */
    int modifyCategory(CategoryDTO categoryDTO);

    /**
     * 设置分类状态
     */
    int startOrStop(Integer status, Long id);

    /**
     * 根据类型查询分类
     */
    List<Category> list(Integer type);
}
