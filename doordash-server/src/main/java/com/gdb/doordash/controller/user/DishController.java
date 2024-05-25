package com.gdb.doordash.controller.user;


import com.gdb.doordash.constant.StatusConstant;
import com.gdb.doordash.entity.Dish;
import com.gdb.doordash.result.Result;
import com.gdb.doordash.service.DishService;
import com.gdb.doordash.vo.DishVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Slf4j
@Tag(name = "C端-菜品浏览接口")
public class DishController {
    @Resource
    private DishService dishService;
    @Resource
    private RedisTemplate<String, List<DishVO>> redisTemplate;

    /**
     * 根据分类id查询菜品
     */
    @GetMapping("/list")
    @Operation(summary = "根据分类id查询菜品")
    public Result<List<DishVO>> list(Long categoryId) {
        //拼接redis中使用的key
        String key = "category_" + categoryId;
        //查询redis中是否存在菜品数据
        List<DishVO> list = redisTemplate.opsForValue().get(key);
        //如果存在，直接返回，无需查询数据库
        if (list != null && list.size() > 0)
            return Result.success(list);

        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);//查询起售中的菜品

        //如果不存在，查询数据库，将查询到的数据放入到redis中
        list = dishService.listWithFlavor(dish);
        redisTemplate.opsForValue().set(key, list);

        return Result.success(list);
    }
}
