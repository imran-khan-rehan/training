package com.redmath.lec_3.authentication.manager;

import com.redmath.lec_3.authentication.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {
    Optional<Manager> findByUsername(String username);

}
