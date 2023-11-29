package com.ace.registrationloginsystem.service.Impl;

import com.ace.registrationloginsystem.dto.EmployeeDto;
import com.ace.registrationloginsystem.entity.Employee;
import com.ace.registrationloginsystem.entity.Role;
import com.ace.registrationloginsystem.repository.EmployeeRepository;
import com.ace.registrationloginsystem.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public void saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        // encrypt the password using spring security
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null) {
            role = checkRoleExist();
        }
        employee.setRoles(Arrays.asList(role));
        employeeRepository.save(employee);
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public List<EmployeeDto> findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map((employee) -> mapToEmployeeDto(employee))
                        .collect(Collectors.toList());

    }

    private EmployeeDto mapToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPassword(employee.getPassword());
        return employeeDto;

    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
