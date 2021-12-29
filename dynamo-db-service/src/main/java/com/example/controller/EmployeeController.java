package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	private EmployeeRepository employeeRepository;
	
	@Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
	
	@PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/employee/{userId}")
    public Employee getEmployee(@PathVariable("userId") String userId) {
        return employeeRepository.getEmployeeById(userId);
    }

    @DeleteMapping("/employee/{userId}")
    public String deleteEmployee(@PathVariable("userId") String userId) {
        return  employeeRepository.delete(userId);
    }

    @PutMapping("/employee/{userId}")
    public String updateEmployee(@PathVariable("userId") String userId, @RequestBody Employee employee) {
        return employeeRepository.update(userId,employee);
    }
    
}
