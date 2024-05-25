package com.gdb.doordash.mapper;

import com.gdb.doordash.annotation.AutoFill;
import com.gdb.doordash.dto.SetmealPageQueryDTO;
import com.gdb.doordash.entity.Setmeal;
import com.gdb.doordash.enumeration.OperationType;
import com.gdb.doordash.vo.DishItemVO;
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

    /**
     * 动态条件查询套餐
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据套餐id查询菜品选项
     */
    @Select("select sd.name, sd.copies, d.image, d.description " +
            "from setmeal_dish sd left join dish d on sd.dish_id = d.id " +
            "where sd.setmeal_id = #{setmealId}")
    List<DishItemVO> getDishItemBySetmealId(Long setmealId);

    /**
     * 通过id查询当前分类下是否存在套餐
     */
    @Select("select count(*) from setmeal where category_id = #{id}")
    Integer countByCategoryId(Long id);
}
