package com.gdb.doordash.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {
    /**
     * 根据菜品id查询对应的套餐id
     */
    //select setMeal_id from setMeal_dish where dish_id in (1,2,3,4)
    List<Long> getSetMealIdsByDishIds(List<Long> dishIds);
}
