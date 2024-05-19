package com.gdb.doordash.service;

import com.gdb.doordash.dto.SetmealDTO;
import com.gdb.doordash.dto.SetmealPageQueryDTO;
import com.gdb.doordash.result.PageResult;
import com.gdb.doordash.vo.SetmealVO;
import org.springframework.stereotype.Service;

@Service
public interface SetmealService {
    /**
     * 添加套餐
     */
    void addSetmeal(SetmealDTO setmealDTO);

    /**
     * 分页查询套餐
     */
    PageResult<SetmealVO> querySetmealPage(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 批量删除套餐
     */
    void deleteBatchSetmeal(Long[] ids);

    /**
     * 起售或停售套餐
     */
    void startOrStopSetmeal(Integer status, Long id);

    /**
     * 根据id查询套餐和关联的菜品数据
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * 修改套餐
     */
    void update(SetmealDTO setmealDTO);
}
