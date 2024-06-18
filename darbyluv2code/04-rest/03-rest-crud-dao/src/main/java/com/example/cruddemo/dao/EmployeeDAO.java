package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.service.EmployeeService;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    void deleteById(int id);
}
