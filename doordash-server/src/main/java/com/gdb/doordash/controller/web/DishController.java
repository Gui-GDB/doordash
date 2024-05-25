package com.gdb.doordash.controller.web;

import com.gdb.doordash.dto.DishDTO;
import com.gdb.doordash.dto.DishPageQueryDTO;
import com.gdb.doordash.entity.Dish;
import com.gdb.doordash.result.PageResult;
import com.gdb.doordash.result.Result;
import com.gdb.doordash.service.DishService;
import com.gdb.doordash.vo.DishVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author: Mr.Gui
 * @program: doordash
 * @create: 2024-05-16 20:58
 * @description: 菜品管理相关的接口
 **/

@RestController
@RequestMapping("/web/dish")
@Tag(name = "菜品相关接口")
@Slf4j
public class DishController {

    @Resource
    private DishService dishService;
    @Resource
    private RedisTemplate<String, List<DishVO>> redisTemplate;

    /**
     * 清理缓存数据
     */
    private void cleanCache() {
        String REDIS_DATA = "category_*";
        Set<String> keys = redisTemplate.keys(REDIS_DATA);
        if (keys != null && keys.size() > 0)
            redisTemplate.delete(keys);
    }

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
    public Result<PageResult<DishVO>> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询:{}", dishPageQueryDTO);
        PageResult<DishVO> pageResult = dishService.pageQuery(dishPageQueryDTO);
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
     * 根据菜品id查询菜品
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
    public Result<String> update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品：{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        //将所有的菜品缓存数据清理掉，所有以dish_开头的key
        cleanCache();
        return Result.success();
    }

    /**
     * 根据菜品 id 启用禁用菜品
     */
    @Operation(summary = "启用禁用员菜品")
    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        log.info(status + "====> " + id);
        dishService.startOrStop(status, id);
        //将所有的菜品缓存数据清理掉，所有以dish_开头的key
        cleanCache();
        return Result.success();
    }

    /**
     * 根据分类 id 查询对应的菜品
     */
    @Operation(summary = "根据分类id查询对应的菜品")
    @GetMapping("/list")
    public Result<List<Dish>> dishList(Long categoryId) {
        List<Dish> dishes = dishService.dishList(categoryId);
        return Result.success(dishes);
    }
}
