package com.employee.project.service;

import com.employee.project.model.Employee;

import java.util.List;

public interface employeeservice {

        List<Employee> findAllEmployees();

        Employee findEmployeeById(long id);

        Employee createEmployee(Employee employee);

        Employee updateEmployee(Employee employee);

        boolean deleteEmployee(long id);



    }


