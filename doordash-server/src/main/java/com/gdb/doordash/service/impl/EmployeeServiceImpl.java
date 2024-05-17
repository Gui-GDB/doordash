package com.gdb.doordash.service.impl;

import com.gdb.doordash.constant.MessageConstant;
import com.gdb.doordash.constant.PasswordConstant;
import com.gdb.doordash.constant.StatusConstant;
import com.gdb.doordash.dto.EmployeeDTO;
import com.gdb.doordash.dto.EmployeeLoginDTO;
import com.gdb.doordash.dto.EmployeePageQueryDTO;
import com.gdb.doordash.entity.Employee;
import com.gdb.doordash.exception.AccountLockedException;
import com.gdb.doordash.exception.AccountNotFoundException;
import com.gdb.doordash.exception.PasswordErrorException;
import com.gdb.doordash.mapper.EmployeeMapper;
import com.gdb.doordash.result.PageResult;
import com.gdb.doordash.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //将前端传过来的密码使用MD5进行加密，然后与数据库中的数据进行对比
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (Objects.equals(employee.getStatus(), StatusConstant.DISABLE)) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 添加员工
     */
    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        //对象属性拷贝
        BeanUtils.copyProperties(employeeDTO, employee);
        //设置账号的状态，默认正常状态，1表示正常，0表示锁定
        employee.setStatus(StatusConstant.ENABLE);
        //设置密码，默认密码是123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        //设置创建时间、修改时间、创建人、修改人，这里通过AOP自动填充了
        employeeMapper.insert(employee);
    }

    /**
     * 分页查询
     */
    @Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        //开启分页
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        //执行sql查询语句
        List<Employee> employees = employeeMapper.pageQuery(employeePageQueryDTO);
        //获取总的返回记录条数
        PageInfo<Employee> pageInfo = new PageInfo<>(employees);
        long total = pageInfo.getTotal();
        return new PageResult(total, employees);
    }

    /**
     * 根据员工状态启用或禁用员工状态
     */
    @Override
    public void startOrStop(Integer status, Long id) {

        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();
        log.info(String.valueOf(employee));
        employeeMapper.update(employee);
    }

    @Override
    public Employee getById(Integer id) {
        Employee employee = employeeMapper.getById(id);
        employee.setPassword("*******");
        return employee;
    }

    @Override
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        //设置修改时间、修改人，这里通过AOP自动填充了
        employeeMapper.update(employee);
    }
}
