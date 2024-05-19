package com.gdb.doordash.controller.web;

import com.gdb.doordash.dto.SetmealDTO;
import com.gdb.doordash.dto.SetmealPageQueryDTO;
import com.gdb.doordash.result.PageResult;
import com.gdb.doordash.result.Result;
import com.gdb.doordash.service.SetmealService;
import com.gdb.doordash.vo.SetmealVO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Mr.Gui
 * @program: doordash
 * @create: 2024-05-18 15:16
 * @description: 套餐管理相关的接口
 **/

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetmealController {
    @Resource
    private SetmealService setmealService;

    /**
     * 新增套餐
     */
    @PostMapping
    @Operation(summary = "新增套餐")
    public Result<String> save(@RequestBody SetmealDTO setmealDTO) {
        setmealService.addSetmeal(setmealDTO);
        return Result.success();
    }

    /**
     * 套餐分页查询
     */
    @GetMapping("/page")
    @Operation(summary = "套餐分页查询")
    public Result<PageResult<SetmealVO>> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResult<SetmealVO> setmealVOPageResult = setmealService.querySetmealPage(setmealPageQueryDTO);
        return Result.success(setmealVOPageResult);
    }

    /**
     * 删除套餐
     */
    @DeleteMapping
    @Operation(summary = "删除套餐")
    public Result<String> dropSetmeal(Long[] ids) {
        setmealService.deleteBatchSetmeal(ids);
        return Result.success();
    }

    /**
     * 启售或停售套餐
     */
    @PostMapping("/status/{status}")
    @Operation(summary = "启售或停售套餐")
    public Result<String> startOrStopSetmeal(@PathVariable("status") Integer status, Long id) {
        setmealService.startOrStopSetmeal(status, id);
        return Result.success();
    }

    /**
     * 根据id查询套餐，用于修改页面回显数据
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据id查询套餐")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        SetmealVO setmealVO = setmealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }

    /**
     * 修改套餐
     */
    @PutMapping
    @Operation(summary = "修改套餐")
    public Result<String> update(@RequestBody SetmealDTO setmealDTO) {
        setmealService.update(setmealDTO);
        return Result.success();
    }
}
