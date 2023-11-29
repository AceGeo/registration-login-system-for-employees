package com.ace.registrationloginsystem.service.Impl;

import com.ace.registrationloginsystem.dto.EmployeeDto;
import com.ace.registrationloginsystem.entity.Employee;

import java.util.List;

public interface EmployeeService {

    void  saveEmployee(EmployeeDto employeeDto);

    Employee findEmployeeByEmail(String email);

    List<EmployeeDto> findAllEmployees();
}
