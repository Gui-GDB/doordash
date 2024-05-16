package com.gdb.doordash.service;

import com.gdb.doordash.dto.DishDTO;
import com.gdb.doordash.dto.DishPageQueryDTO;
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
}
