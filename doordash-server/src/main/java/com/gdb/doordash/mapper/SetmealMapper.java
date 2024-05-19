package com.gdb.doordash.mapper;

import com.gdb.doordash.annotation.AutoFill;
import com.gdb.doordash.dto.SetmealPageQueryDTO;
import com.gdb.doordash.entity.Setmeal;
import com.gdb.doordash.enumeration.OperationType;
import com.gdb.doordash.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: Mr.Gui
 * @program: doordash
 * @create: 2024-05-18 15:20
 * @description: 套餐相关的mapper忌口
 **/
@Mapper
public interface SetmealMapper {
    /**
     * 新增套餐
     */
    @AutoFill(OperationType.INSERT)
    Integer insertBatch(Setmeal setmeals);

    /**
     * 分页查询
     */
    List<SetmealVO> pageSelect(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 批量删除套餐
     */
    @Delete("delete from setmeal where id = #{id}")
    void deleteSetmealById(Long id);

    /**
     * 根据id查询套餐
     */
    @Select("select * from setmeal where id = #{id}")
    Setmeal getById(Long id);

    /**
     * 修改套餐的信息
     */
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);
}
