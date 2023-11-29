package com.ace.registrationloginsystem.security;

import com.ace.registrationloginsystem.entity.Employee;
import com.ace.registrationloginsystem.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomEmployeeDetailsService implements UserDetailsService {

    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(usernameOrEmail);
        if (employee != null) {
            return new org.springframework.security.core.userdetails.User(employee.getEmail(),
                    employee.getPassword(),
                    employee.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()));
        } else
            throw new UsernameNotFoundException("Invalid email or password");
    }
}
