package com.hasanalizada.phonebook.service;

import com.hasanalizada.phonebook.model.Employee;
import com.hasanalizada.phonebook.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean updateEmployeeStatus(Long id, Employee.Status newStatus) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setStatus(newStatus);
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }
}
