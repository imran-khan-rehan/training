package com.redmath.lec_3.authentication.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/api/v1/employee/all")
    public ResponseEntity<List<Employee>> get(){
        return ResponseEntity.ok(employeeService.findAll());
    }
    @GetMapping("/api/v1/employee/{id}")
    public ResponseEntity<Employee> getone(@PathVariable("id") Long empId){
        Optional<Employee> id=employeeService.findById(empId);
        if(id.isEmpty())
        {
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(id.get());
    }
    @GetMapping("/api/v1/admin")
    public ResponseEntity<Employee> getadmin() {
        Long empId = 1L;
        Optional<Employee> id = employeeService.findById(empId);
        if (id.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(id.get());
    }
    @PostMapping("/api/v1/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.create(employee);
        return ResponseEntity.ok(createdEmployee);
    }
}
