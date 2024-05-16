package com.gdb.doordash.controller.web;

import com.gdb.doordash.dto.CategoryDTO;
import com.gdb.doordash.dto.CategoryPageQueryDTO;
import com.gdb.doordash.entity.Category;
import com.gdb.doordash.result.PageResult;
import com.gdb.doordash.result.Result;
import com.gdb.doordash.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Mr.Gui
 * @program: doordash
 * @create: 2024-05-09 17:15
 * @description: 分类管理
 **/

@RestController
@RequestMapping("/admin/category")
@Tag(name = "分类相关接口")
@Slf4j
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    /**
     * 分类分页查询
     */
    @GetMapping("/page")
    @Operation(summary = "分类分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分页查询：{}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 添加分类
     */
    @PostMapping
    @Operation(summary = "添加分类")
    public Result<Integer> addCategory(@RequestBody CategoryDTO categoryDTO) {
        return Result.success(categoryService.addCategory(categoryDTO));
    }

    /**
     * 修改分类
     */
    @PutMapping
    @Operation(summary = "修改分类")
    public Result<Integer> modifyCategory(@RequestBody CategoryDTO categoryDTO) {
        return Result.success(categoryService.modifyCategory(categoryDTO));
    }

    /**
     * 设置分类状态关闭、开启
     */
    @PostMapping("/status/{status}")
    @Operation(summary = "启用禁用分类")
    public Result<Integer> startOrStop(@PathVariable("status") Integer status, Long id) {
        return Result.success(categoryService.startOrStop(status, id));
    }

    /**
     * 根据 id 删除分类
     */
    @DeleteMapping
    @Operation(summary = "根据id删除分类")
    public Result<Integer> dropCategory(Long id) {
        return Result.success(categoryService.dropCategory(id));
    }

    /**
     * 根据类型查询分类
     */
    @GetMapping("/list")
    @Operation(summary = "根据类型查询分类")
    public Result<List<Category>> list(Integer type) {
        return Result.success(categoryService.list(type));
    }
}
