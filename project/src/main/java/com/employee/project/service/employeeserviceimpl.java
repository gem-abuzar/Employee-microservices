package com.employee.project.service;

import com.employee.project.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employee.project.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class employeeserviceimpl implements employeeservice{
    @Autowired
    private EmployeeRepository repository;
    @Override
    public Employee createEmployee( Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee findEmployeeById(long id) {
        Optional<Employee> employee = repository.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        }
        return null;
    }



    @Override
    public Employee updateEmployee(Employee employee) {
        Optional<Employee> employeeDb = repository.findById(employee.getId());
        if(employeeDb.isPresent()) {
            Employee emp = employeeDb.get();
            emp.setId(employee.getId());
            emp.setName(employee.getName());
            emp.setDesignation(employee.getDesignation());
            emp.setAddress(employee.getAddress());
//            emp.setDept_id(employee.getDept_id());
            emp.setPhno(employee.getPhno());
            return repository.save(emp);
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(long id) {
        Optional<Employee> employee = repository.findById(id);
        if(employee.isPresent()) {
            repository.delete(employee.get());
            return true;
        }
        return false;
    }


}
