package com.employee.project.controller;

import com.employee.project.exception.ResourceNotFoundException;
import com.employee.project.model.Department;
import com.employee.project.service.departmentservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
public class DepartmentController {
    @Autowired
    private departmentservice departmentService;

    /**
     *
     * Get all departments
     */
    @GetMapping("/department/find")
    public ResponseEntity<List<Department>> getAllDepartment() {
        log.info("get department");
        List<Department> departmentList=departmentService.findAllDepartment();
        return new ResponseEntity<>(departmentList, HttpStatus.OK);
    }


    /**
     *
     * @param id referenced variable to get to employee's data
     * Get department data by id
     */
    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable long id) {
        log.info("get employee by id");
        Department department=departmentService.findDepartmentById(id);
        if(department==null)
            throw new ResourceNotFoundException("department id does not exist");
        return new ResponseEntity<Department>(department,HttpStatus.OK);

    }


    /**
     *
     * @param department for creating a department model
     * Create a new department
     */
    @PostMapping("/department/create")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        log.info("created");
        Department dep= departmentService.saveDepartment(department);
        return new ResponseEntity<>(dep,HttpStatus.CREATED);

    }


    /**
     *
     * @param id
     * @param department for update in model
     * Modify department data by id
     */
    @PutMapping("/department/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable long id, @RequestBody Department department) {
        try {
            log.info("updated");
            department.setDeptId(id);
            departmentService.updateDepartment(department);
            return new ResponseEntity<>(departmentService.findDepartmentById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            log.error("department id not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     *
     * @param id referenced variable used for deletion
     * Delete department by id
     */

    @DeleteMapping("/department/{id}")
    public HttpStatus deleteDepartment(@PathVariable long id) {
        log.info("department deletion");
        boolean isDeleted = departmentService.deleteDepartment(id);
        return isDeleted ? HttpStatus.OK : HttpStatus.NO_CONTENT;
    }
}
