package com.redmath.lec_3.authentication.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
@Slf4j
@Component
public class EmployeeApiClient {
    private final RestTemplate restTemplate;
    //private static final Logger logger = LoggerFactory.getLogger(EmployeeApiClient.class);

    public EmployeeApiClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
    public List<Employee> findEmployee(String search)
    {
        String result=restTemplate.getForEntity("http://localhost:8080/api/v1/employee/all",String.class).getBody();
        Employee employees=new Employee();
        employees.setName(result);
        log.info("Received response from NewsAPI");
        log.warn("invalid user: {}",result);
        return List.of(employees);
    }
}
