package com.hasanalizada.phonebook.controller;

import com.hasanalizada.phonebook.model.Employee;
import com.hasanalizada.phonebook.repository.EmployeeRepository;
import com.hasanalizada.phonebook.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Controller
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public String showEmployeeList(Model model) {
        model.addAttribute("employeeList", employeeRepository.findByStatus(Employee.Status.ACTIVE));
        return "index"; // Return the name of the Thymeleaf template (index.html)
    }

    @GetMapping("/admin")
    public String showAddEmployeeForm(Model model) {
        // Add any necessary attributes to the model
        model.addAttribute("employee", new Employee());
        model.addAttribute("employeeList", employeeRepository.findByStatus(Employee.Status.ACTIVE));
        return "admin"; // Return the name of the Thymeleaf template
    }

    @PostMapping("/employees")
    public String addEmployee(@RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String patronymic,
                              @RequestParam String department,
                              @RequestParam String position,
                              @RequestParam String ipNumber,
                              @RequestParam String structure1,
                              @RequestParam String structure2,
                              @RequestParam String structure3,
                              @RequestParam String structure4,
                              @RequestParam String structure5) {
        // Create an Employee object and save it to the database
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setPatronymic(patronymic);
        employee.setDepartment(department);
        employee.setPosition(position);
        employee.setIpNumber(ipNumber);
        employee.setStructure1(structure1);
        employee.setStructure2(structure2);
        employee.setStructure3(structure3);
        employee.setStructure4(structure4);
        employee.setStructure5(structure5);
        employee.setStatus(Employee.Status.ACTIVE);

        try {
            employeeRepository.save(employee); // Save the employee to the database using the repository
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/admin?error=" + e.getMessage(); // Redirect with error message as a query parameter
        }
    }

    @PatchMapping("/employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id,
                                                 @RequestParam String name,
                                                 @RequestParam String surname,
                                                 @RequestParam String patronymic,
                                                 @RequestParam String department,
                                                 @RequestParam String position,
                                                 @RequestParam String ipNumber,
                                                 @RequestParam String structure1,
                                                 @RequestParam String structure2,
                                                 @RequestParam String structure3,
                                                 @RequestParam String structure4,
                                                 @RequestParam String structure5) {
        // Retrieve the employee by ID
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));

        // Update the employee's attributes
        employee.setName(name);
        employee.setSurname(surname);
        employee.setPatronymic(patronymic);
        employee.setDepartment(department);
        employee.setPosition(position);
        employee.setIpNumber(ipNumber);
        employee.setStructure1(structure1);
        employee.setStructure2(structure2);
        employee.setStructure3(structure3);
        employee.setStructure4(structure4);
        employee.setStructure5(structure5);

        // Save the updated employee to the database
        employeeRepository.save(employee);

        // Return a success response with a message
        return ResponseEntity.ok("Employee updated successfully");
    }

    @PatchMapping("employees/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        boolean updated = employeeService.updateEmployeeStatus(id, Employee.Status.DELETED);
        if (updated) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
