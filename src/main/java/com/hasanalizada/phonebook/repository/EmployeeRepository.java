package com.hasanalizada.phonebook.repository;

import com.hasanalizada.phonebook.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByStatus(Employee.Status status);

}
