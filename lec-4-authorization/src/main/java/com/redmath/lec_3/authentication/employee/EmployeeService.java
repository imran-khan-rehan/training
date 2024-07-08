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
        employee.setEmpId(employee.getEmpId());
       // String username = SecurityContextHolder.getContext().getAuthentication().getName();
        employee.setName(employee.getName());
        employee.setJoinAt(employee.getJoinAt());
        return employeeRepository.save(employee);
    }
    public Optional<Employee> update(Long empId,Employee employee)
    {
        Optional<Employee> exist=employeeRepository.findById(empId);
        if(exist.isPresent())
        {
            exist.get().setEmpId(employee.getEmpId());
            exist.get().setName(employee.getName());
            exist.get().setJoinAt(employee.getJoinAt());
            exist.get().setSalary(employee.getSalary());
            exist=Optional.of(employeeRepository.save(exist.get()));
        }
        return  exist;
    }
}
