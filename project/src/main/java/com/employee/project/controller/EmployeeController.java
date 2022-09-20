package com.employee.project.controller;

import com.employee.project.exception.ResourceNotFoundException;
import com.employee.project.model.Employee;
import com.employee.project.service.employeeservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
public class EmployeeController {
    @Autowired
    private employeeservice employeeService;

    /**
     *Get all employees
     */
    @GetMapping("/employee/find")
    public ResponseEntity <List<Employee>> getAllEmployee() {
        log.info("get employee");
        List<Employee> employeeList= employeeService.findAllEmployees();
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }

    /**
     *
     * @param id referenced variable used to find
     * Get an employee by id
     */

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        log.info("get employee by id");
        Employee employee=employeeService.findEmployeeById(id);
        if(employee == null)
            throw new ResourceNotFoundException("Employee id CANNOT BE FOUND");
        return new ResponseEntity<Employee>(employee,HttpStatus.OK);


    }

    /**
     *
     * @param employee for creation of model data
     * Create a new employee data
     */


    @PostMapping("/employee/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        log.info("created");
        Employee emp = employeeService.createEmployee(employee);
        return new ResponseEntity<>(emp,HttpStatus.CREATED);
    }

    /**
     *
     * @param id referenced variable used here to update
     * @param employee
     * Modify employee's data by id
     */
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
        try {
            log.info("Update Employee");
            employee.setId(id);
            employeeService.updateEmployee(employee);
            return new ResponseEntity<>(employeeService.findEmployeeById(id),HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            log.error("element id not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     *
     * @param id the employee id which will help in deletion
     * Delete employee data by id
     */

    @DeleteMapping("/employee/{id}")
    public HttpStatus deleteEmployee(@PathVariable long id) {
        log.info("employee deletion");
        boolean isDeleted = employeeService.deleteEmployee(id);
        return isDeleted ? HttpStatus.OK : HttpStatus.NO_CONTENT;
    }
}
