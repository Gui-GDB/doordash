package com.gdb.doordash.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Schema(description = "分页查询封装请求数据")
@Data
public class EmployeePageQueryDTO implements Serializable {

    //员工姓名
    @Schema(description = "员工姓名")
    private String name;

    //页码
    @Schema(description = "页码", requiredMode = Schema.RequiredMode.REQUIRED)
    private int page;

    //每页显示记录数
    @Schema(description = "每页显示记录数", requiredMode = Schema.RequiredMode.REQUIRED)
    private int pageSize;

}
