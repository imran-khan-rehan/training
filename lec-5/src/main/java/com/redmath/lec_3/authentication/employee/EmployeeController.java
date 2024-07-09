package com.redmath.lec_3.authentication.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
private final EmployeeApiClient employeeApiClient;
private final EmployeeDataService employeeDataService;
    public EmployeeController(EmployeeService employeeService, EmployeeApiClient employeeApiClient, EmployeeDataService employeeDataService) {
        this.employeeService = employeeService;
        this.employeeApiClient = employeeApiClient;
        this.employeeDataService = employeeDataService;
    }
    @GetMapping("/news")
    public List<Employee> getEmployees(@RequestParam String search) {
        log.info("hey iam in /news");
        return employeeApiClient.findEmployee(search);
    }
    @GetMapping("/employees")
    public Employee getNews(@RequestParam Long id) {
        log.info("hey iam in /news");
        return employeeDataService.findByUserName(id);
    }
    @GetMapping("/api/v1/employee/all")
    public ResponseEntity<List<Employee>> get(){
        return ResponseEntity.ok(employeeService.findAll());
    }
    @GetMapping("/api/v1/employee/id")
    public Employee getone(){
        Optional<Employee> id=employeeService.findById(1L);
//        if(id.isEmpty())
//        {
//            return  ResponseEntity.notFound().build();
//        }
//        return  ResponseEntity.ok(id.get());
        return employeeDataService.findByUserName(1L);
        //return new Employee();
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
    @PostMapping("/api/v1")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        //Employee createdEmployee = employeeService.create(employee);
        return ResponseEntity.ok(employee);
    }
    @PreAuthorize("hasAnyAuthority('admin','reporter')")
    @PostMapping("/api/v1/employee")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        employee = employeeService.create(employee);
        return ResponseEntity.created(URI.create("/api/v1/employee/" + employee.getEmpId())).body(employee);
    }
    @PreAuthorize("hasAnyAuthority('admin', 'editor')")
    @PutMapping("/api/v1/news/{newsId}")
    public ResponseEntity<Employee> update(@PathVariable("newsId") Long newsId, @RequestBody Employee news) {
        Optional<Employee> saved = employeeService.update(newsId, news);
        if (saved.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(saved.get());
    }
}
