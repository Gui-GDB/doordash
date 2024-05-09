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

    /**
     * 根据id查询员工信息
     */
    Employee getById(Integer id);

    /**
     * 编辑员工信息
     * @param employeeDTO 员工编辑的信息内容
     */
    void update(EmployeeDTO employeeDTO);

}
