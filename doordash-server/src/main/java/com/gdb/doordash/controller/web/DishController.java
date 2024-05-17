package com.gdb.doordash.controller.web;

import com.gdb.doordash.dto.DishDTO;
import com.gdb.doordash.dto.DishPageQueryDTO;
import com.gdb.doordash.result.PageResult;
import com.gdb.doordash.result.Result;
import com.gdb.doordash.service.DishService;
import com.gdb.doordash.vo.DishVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Mr.Gui
 * @program: doordash
 * @create: 2024-05-16 20:58
 * @description: 菜品管理相关的接口
 **/

@RestController
@RequestMapping("/admin/dish")
@Tag(name = "菜品相关接口")
@Slf4j
public class DishController {

    @Resource
    private DishService dishService;

    /**
     * 新增菜品
     */
    @PostMapping
    @Operation(summary = "新增菜品")
    public Result<Integer> save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品：{}", dishDTO);
        return Result.success(dishService.saveWithFlavor(dishDTO));
    }

    /**
     * 菜品分页查询
     */
    @GetMapping("/page")
    @Operation(summary = "菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询:{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 菜品批量删除
     */
    @DeleteMapping
    @Operation(summary = "菜品批量删除")
    public Result<Integer> delete(@RequestParam List<Long> ids) {
        log.info("菜品批量删除：{}", ids);
        return Result.success(dishService.deleteBatch(ids));
    }

    /**
     * 根据id查询菜品
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据id查询菜品")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("根据id查询菜品：{}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * 修改菜品
     */
    @PutMapping
    @Operation(summary = "修改菜品")
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品：{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * 根据 id 启用禁用菜品
     */
    @Operation (summary = "启用禁用员菜品")
    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        log.info(status + "====> " + id);
        dishService.startOrStop(status, id);
        return Result.success();
    }
}
