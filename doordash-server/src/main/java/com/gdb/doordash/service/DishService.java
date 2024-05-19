package com.gdb.doordash.service;

import com.gdb.doordash.dto.DishDTO;
import com.gdb.doordash.dto.DishPageQueryDTO;
import com.gdb.doordash.entity.Dish;
import com.gdb.doordash.result.PageResult;
import com.gdb.doordash.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
     * 新增菜品和对应口味
     */
    int saveWithFlavor(DishDTO dishDTO);

    /**
     * 菜品分页查询
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 菜品批量删除
     */
    Integer deleteBatch(List<Long> ids);

    /**
     * 根据id查询菜品和对应的口味数据
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * 根据id修改菜品基本信息和对应的口味信息
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * 根据id设置菜品的状态
     */
    void startOrStop (Integer type, Long id);

    /**
     * 根据分类id查询相关的菜品
     */
    List<Dish> dishList(Long categoryId);
}
