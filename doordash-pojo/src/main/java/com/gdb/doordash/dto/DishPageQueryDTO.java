package com.gdb.doordash.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DishPageQueryDTO implements Serializable {

    //显示第几页
    private int page;

    //每页显示的记录条数
    private int pageSize;

    //菜品名称
    private String name;

    //分类id
    private Integer categoryId;

    //状态 0表示禁用 1表示启用
    private Integer status;

}
