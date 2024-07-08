//package com.redmath.lec_3.authentication.employee;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//@Getter
//@Setter
//@Entity(name = "EMPLOYEES")
//public class Employee {
//    @Id
//    private Long empId;
//    private String name;
//    private LocalDateTime joinAt;
//    private Long salary;
//}
package com.redmath.lec_3.authentication.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "EMPLOYEES")
public class Employee {
    @Id
    private Long empId;
    private String name;
    private LocalDateTime joinAt;
    private Long salary;

    // Getter for empId
    public Long getEmpId() {
        return empId;
    }

    // Setter for empId
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for joinAt
    public LocalDateTime getJoinAt() {
        return joinAt;
    }

    // Setter for joinAt
    public void setJoinAt(LocalDateTime joinAt) {
        this.joinAt = joinAt;
    }

    // Getter for salary
    public Long getSalary() {
        return salary;
    }

    // Setter for salary
    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
