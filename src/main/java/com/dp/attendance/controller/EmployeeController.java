package com.dp.attendance.controller;

import com.dp.attendance.model.Employee;
import com.dp.attendance.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping(value = "/login")
    public Employee employee_login(@RequestBody Employee employee){
        return service.login(employee);
    }

    @PutMapping(value = "/logout/{id}")
    public String employee_logOut(@PathVariable int id){
       return service.logout(id);

    }
}
