package com.gdb.doordash.controller.admin;

import com.gdb.doordash.constant.JwtClaimsConstant;
import com.gdb.doordash.dto.EmployeeDTO;
import com.gdb.doordash.dto.EmployeeLoginDTO;
import com.gdb.doordash.dto.EmployeePageQueryDTO;
import com.gdb.doordash.entity.Employee;
import com.gdb.doordash.properties.JwtProperties;
import com.gdb.doordash.result.PageResult;
import com.gdb.doordash.result.Result;
import com.gdb.doordash.service.EmployeeService;
import com.gdb.doordash.utils.JwtUtil;
import com.gdb.doordash.vo.EmployeeLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */

@Tag(name="员工相关接口")
@Slf4j
@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private JwtProperties jwtProperties;

    /**
     * 登录
     */
    @Operation(description = "登录")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     */
    @Operation(summary = "登出")
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 添加员工
     */
    @Operation(summary = "添加员工")
    @PostMapping()
    public Result<String> addUser(@RequestBody EmployeeDTO employeeDTO) {
        log.info("添加员工的信息{}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * 分页查询接口
     * @param employeePageQueryDTO 查询条件
     * @return 返回总记录条数和当前查询页的数据
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page")
    public Result<PageResult> page(@ParameterObject EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("分页查询信息{}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    @Operation (summary = "启用禁用员工账号")
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        log.info(status + "====> " + id);
        employeeService.startOrStop(status, id);
        return Result.success();
    }
}



















