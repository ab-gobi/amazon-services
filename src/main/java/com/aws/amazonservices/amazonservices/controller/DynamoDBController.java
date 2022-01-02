package com.aws.amazonservices.amazonservices.controller;

import com.aws.amazonservices.entity.Employee;
import com.aws.amazonservices.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DynamoDBController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/get/{employeeId}")
    public Employee getEmployee(@PathVariable String employeeId){
        return employeeRepository.getEmployeeById(employeeId);
    }

    @DeleteMapping("/remove/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId){
        return employeeRepository.delete(employeeId);
    }

    @PostMapping("/save")
    public Employee  saveEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @PutMapping("/update/{employeeId}")
    public String updateEmployee(@PathVariable String employeeId,@RequestBody Employee employee){
        return employeeRepository.update(employeeId,employee);
    }
}
