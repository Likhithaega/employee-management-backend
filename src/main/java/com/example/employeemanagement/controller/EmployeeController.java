package com.example.employeemanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return repository.save(employee);
    }
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee empDetails) {
        Employee employee = repository.findById(id).orElseThrow();
        employee.setName(empDetails.getName());
        employee.setEmail(empDetails.getEmail());
        employee.setDepartment(empDetails.getDepartment());
        return repository.save(employee);
    }
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
