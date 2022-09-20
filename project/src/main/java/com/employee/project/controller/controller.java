package com.employee.project.controller;

import com.employee.project.exception.ResourceNotFoundException;
import com.employee.project.model.Address;
import com.employee.project.model.Department;
import com.employee.project.model.Employee;
import com.employee.project.repository.EmployeeRepository;
import com.employee.project.service.departmentservice;
import com.employee.project.service.addressservice;
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
public class controller {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private employeeservice employeeService;
    @Autowired
    private departmentservice departmentService;
    @Autowired
    private addressservice addressService;
    @GetMapping ("/home")
    public ResponseEntity<?> HomePage() {
        return new ResponseEntity<>("Home Page", HttpStatus.OK);
    }
    @GetMapping("/employee/find")
    public ResponseEntity <List<Employee>> getAllEmployee() {
        log.info("get employee");
        List<Employee> employeeList= employeeService.findAllEmployees();
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        log.info("get employee by id");
        Employee employee=employeeService.findEmployeeById(id);
        if(employee == null)
            throw new ResourceNotFoundException("Employee id CANNOT BE FOUND");
        return new ResponseEntity<Employee>(employee,HttpStatus.FOUND);


    }


    @PostMapping("/employee/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        log.info("created");
        Employee emp = employeeService.createEmployee(employee);
        return new ResponseEntity<>(emp,HttpStatus.CREATED);
    }

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

    @DeleteMapping("/employee/{id}")
    public HttpStatus deleteEmployee(@PathVariable long id) {
        log.info("employee deletion");
        boolean isDeleted = employeeService.deleteEmployee(id);
        return isDeleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }
    @GetMapping("/department/find")
    public ResponseEntity<List<Department>> getAllDepartment() {
        log.info("get department");
        List<Department> departmentList=departmentService.findAllDepartment();
        return new ResponseEntity<>(departmentList, HttpStatus.OK);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable long id) {
        log.info("get employee by id");
        Department department=departmentService.findDepartmentById(id);
        if(department==null)
            throw new ResourceNotFoundException("department id does not exist");
        return new ResponseEntity<Department>(department,HttpStatus.OK);

    }


    @PostMapping("/department/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        log.info("created");
        Department dep= departmentService.saveDepartment(department);
        return new ResponseEntity<>(dep,HttpStatus.CREATED);

    }

    @PutMapping("/department/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable long id, @RequestBody Department department) {
        try {
            log.info("updated");
            department.setDeptid(id);
            departmentService.updateDepartment(department);
            return new ResponseEntity<>(departmentService.findDepartmentById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            log.error("department id not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/department/{id}")
    public HttpStatus deleteDepartment(@PathVariable long id) {
        log.info("department deletion");
        boolean isDeleted = departmentService.deleteDepartment(id);
        return isDeleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }
    @GetMapping("/address/find")
    public ResponseEntity<List<Address>> getAllAddress() {
        log.info("get address");
        List<Address> addressList= addressService.findAllAddress();
        return new ResponseEntity<>(addressList, HttpStatus.OK);

    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable int id) {
        log.info("get address by id");
        Address address=addressService.findAddressById(id);
        if(address==null)
            throw new ResourceNotFoundException("address id does not exist");
        return new ResponseEntity<Address>(address,HttpStatus.OK);
    }


    @PostMapping("/address/create")
    public ResponseEntity< Address> createAddress(@RequestBody Address address) {
        log.info("created");
        Address add= addressService.saveAddress(address);
        return new ResponseEntity<>(add,HttpStatus.CREATED);
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable int id, @RequestBody Address address) {
        try {
            log.info("updated");
            address.setAddress_id(id);
            addressService.updateAddress(address);
            return new ResponseEntity<>(addressService.findAddressById(id),HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            log.error("address id not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/address/{id}")
    public HttpStatus deleteAddress(@PathVariable int id) {
        log.info("address deletion");
        boolean isDeleted = addressService.deleteAddress(id);
        return isDeleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }}
