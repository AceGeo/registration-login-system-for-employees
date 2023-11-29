package com.ace.registrationloginsystem.repository;

import com.ace.registrationloginsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee findByEmail(String email);
}
