package com.redmath.lec_3.authentication.employee;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDataService {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Employee findByUserName(Long id)
    {
        return jdbcTemplate.queryForObject("select * from EMPLOYEES where empId = ?",Employee.class,id);
    }
}
