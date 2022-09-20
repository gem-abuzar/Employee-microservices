package com.employee.project.repository;

import com.employee.project.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Modifying
    @Transactional
    @Query(value = "update department set Deleted = true,Active = false where deptid = :deptid",nativeQuery = true)
    public void DeleteDepartment(@Param("deptid") Long dept_id);
}
