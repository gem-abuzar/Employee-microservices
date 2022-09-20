package com.employee.project.service;

import com.employee.project.model.Department;

import java.util.List;

public interface departmentservice {
    List<Department> findAllDepartment();

    Department findDepartmentById(long id);

    Department saveDepartment(Department department);

    Department updateDepartment(Department department);

    boolean deleteDepartment(long id);
}
