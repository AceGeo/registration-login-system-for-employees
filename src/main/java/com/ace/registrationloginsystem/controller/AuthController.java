package com.ace.registrationloginsystem.controller;

import com.ace.registrationloginsystem.dto.EmployeeDto;
import com.ace.registrationloginsystem.entity.Employee;
import com.ace.registrationloginsystem.service.Impl.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AuthController {

    private EmployeeService employeeService;

    // handler method to handle
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        //Create model object to store form data
        EmployeeDto employee = new EmployeeDto();
        model.addAttribute("employee",employee);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("employee")
                                   EmployeeDto employeeDto,
                               BindingResult result,
                               Model model) {
        Employee existingEmployee = employeeService.findEmployeeByEmail(employeeDto.getEmail());

        if(existingEmployee !=null && existingEmployee.getEmail()!=null && !existingEmployee.getEmail().isEmpty() ) {
            result.rejectValue("email",null,
                    "There is already an account registered with the same email!");
        }

        if(result.hasErrors()) {
            model.addAttribute("employee",employeeDto);
            return "/register";
        }
        employeeService.saveEmployee(employeeDto);
        return "redirect:/register?success";
    }

    //
    @GetMapping("/employees")
    public String employees(Model model){
        List<EmployeeDto> employees = employeeService.findAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";

    }

}
