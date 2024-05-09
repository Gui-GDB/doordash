package com.gdb.doordash.service;

import com.gdb.doordash.dto.EmployeeDTO;
import com.gdb.doordash.dto.EmployeeLoginDTO;
import com.gdb.doordash.dto.EmployeePageQueryDTO;
import com.gdb.doordash.entity.Employee;
import com.gdb.doordash.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用禁用员工账号
     * @param status 员工状态码
     */
    void startOrStop(Integer status, Long id);

}
