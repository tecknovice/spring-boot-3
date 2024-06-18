package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    //define field for entitymanager
    private EntityManager entityManager;

    //set up constructor injection

    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create a query
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);
        //execute query and get result list
        List<Employee> employees = query.getResultList();
        //return data
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = entityManager.find(Employee.class,id);
        return employee;
    }

    @Override
    //create or update employee
    public Employee save(Employee employee) {
        Employee dbEmployee = entityManager.merge(employee);//if id =0 then insert   else update
        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {
        //find employee
        Employee employee = entityManager.find(Employee.class,id);
        //delete
        entityManager.remove(employee);
    }
}
