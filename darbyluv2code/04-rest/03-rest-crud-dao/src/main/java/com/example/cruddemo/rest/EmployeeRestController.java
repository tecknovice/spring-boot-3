package com.example.cruddemo.rest;

import com.example.cruddemo.dao.EmployeeDAO;
import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.service.EmployeeService;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    //setup constructor injection

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable int id) throws BadRequestException {
        Employee employee = employeeService.findById(id);
        if(employee==null) throw new BadRequestException("Employee id not found - "  +id);
        return employee;
    }


    @PostMapping("/employees")
    public Employee create(@RequestBody Employee employee){
        //in case they pass an id in JSON, set id to 0
        //this is  to force an insert of new item, instead of an update
        employee.setId(0);
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }
    @PutMapping("/employees")
    public Employee update(@RequestBody Employee employee){
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    @DeleteMapping("/employees/{id}")
    public String delete(@PathVariable int id) throws BadRequestException {
        //find employee
        Employee employee = employeeService.findById(id);
        //check if not exists
        if(employee==null) throw new BadRequestException("Employee id not found = " + id);
        //delete
        employeeService.deleteById(id);
        //return
        return "deleted - " + id;
    }
}
