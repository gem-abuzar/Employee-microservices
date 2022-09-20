package com.employee.project.service;

import com.employee.project.model.Department;
import com.employee.project.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class departmentserviceimpl implements departmentservice  {


    @Autowired
    private DepartmentRepository repository;

    @Override
    public List<Department> findAllDepartment() {
        return repository.findAll();
    }

    @Override
    public Department findDepartmentById(long id) {
        Optional<Department> department = repository.findById(id);
        if(department.isPresent()) {
            return department.get();
        }
        return null;
    }

    @Override
    public Department saveDepartment(Department department) {
        return repository.save(department);

    }

    @Override
    public Department updateDepartment(Department department) {
        Optional<Department> DeptDb = repository.findById(department.getDeptId());
        if(DeptDb.isPresent()) {
            Department dep = DeptDb.get();
            dep.setDeptId(department.getDeptId());
            dep.setDeptName(department.getDeptName());
            dep.setDeptDesc(department.getDeptDesc());
            dep.setCreateDate(department.getCreateDate());
            dep.setCreatedBy(department.getCreatedBy());
            dep.setUpdateDate(department.getUpdateDate());
            dep.setUpdatedBy(department.getUpdatedBy());
            return repository.save(dep);
        }
        return null;    }

    @Override
    public boolean deleteDepartment(long id) {
        Optional<Department> department = repository.findById(id);
        if(department.isPresent()) {
            repository.delete(department.get());
            return true;
        }
        return false;    }
}
