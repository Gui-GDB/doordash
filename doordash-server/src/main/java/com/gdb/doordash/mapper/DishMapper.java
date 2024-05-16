package com.gdb.doordash.mapper;

import com.gdb.doordash.annotation.AutoFill;
import com.gdb.doordash.dto.DishPageQueryDTO;
import com.gdb.doordash.entity.Dish;
import com.gdb.doordash.enumeration.OperationType;
import com.gdb.doordash.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: Mr.Gui
 * @program: doordash
 * @create: 2024-05-16 21:42
 * @description:
 **/

@Mapper
public interface DishMapper {
    @AutoFill(OperationType.INSERT)
    int insert(Dish dish);

    /**
     * 菜品分页查询
     */
    List<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据主键查询菜品
     */
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 根据主键删除菜品数据
     */
    @Delete("delete from dish where id = #{id}")
    Integer deleteById(Long id);
}
