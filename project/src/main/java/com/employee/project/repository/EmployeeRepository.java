package com.employee.project.repository;

import com.employee.project.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Modifying
    @Transactional
    @Query(value = "update employee set Deleted = true,Active = false where id = :id",nativeQuery = true)
    public void DeleteEmployee(@Param("id") Long id);
}
