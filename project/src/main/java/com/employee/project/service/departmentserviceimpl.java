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
        Optional<Department> DeptDb = repository.findById(department.getDeptid());
        if(DeptDb.isPresent()) {
            Department dep = DeptDb.get();
            dep.setDeptid(department.getDeptid());
            dep.setDept_name(department.getDept_name());
            dep.setDept_desc(department.getDept_desc());
            dep.setCreate_date(department.getCreate_date());
            dep.setCreated_by(department.getCreated_by());
            dep.setUpdate_date(department.getUpdate_date());
            dep.setUpdated_by(department.getUpdated_by());
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
