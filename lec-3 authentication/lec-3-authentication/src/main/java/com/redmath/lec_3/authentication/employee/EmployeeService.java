package com.redmath.lec_3.authentication.employee;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public List<Employee> findAll()
    {
        return employeeRepository.findAll();
    }
    public Optional<Employee> findById(Long EmpId)
    {
        return employeeRepository.findById(EmpId);
    }
    public Employee create(Employee employee) {
        employee.setEmpId(System.currentTimeMillis());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        employee.setName(username);
        employee.setJoinAt(LocalDateTime.now());
        return employeeRepository.save(employee);
    }
}
