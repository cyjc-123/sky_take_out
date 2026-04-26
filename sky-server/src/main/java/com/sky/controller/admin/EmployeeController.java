package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
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
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody EmployeeDTO employee){
          log.info("新增员工：{}",employee);
            employeeService.insert(employee);
            return Result.success();
    }

    @GetMapping("/page")
    public Result PageSelect( EmployeePageQueryDTO epqt){
        log.info("分页查询：");
        PageResult employeeList=employeeService.PageSelect(epqt);
        return Result.success(employeeList);

    }
   @PostMapping("/status/{status}")
    public Result JudgeStatus( @PathVariable Integer status,Long id){
           log.info("员工状态：{},员工id:{}",status,id);
           employeeService.JudgeStatus(status,id);
           return Result.success();
    }
    @GetMapping("/{id}")
    public Result selectInfo(@PathVariable Long id){
        log.info("根据id查询回显：{}",id);
        Employee e=employeeService.selectInfo(id);
        return Result.success(e);
    }
    @PutMapping
    public Result update(@RequestBody EmployeeDTO employee){
         log.info("修改员工：{}",employee);
         employeeService.update(employee);
         return Result.success();
    }

}
