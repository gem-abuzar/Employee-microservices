package com.employee.project;

import com.employee.project.controller.DepartmentController;
import com.employee.project.controller.EmployeeController;
import com.employee.project.exception.ResourceNotFoundException;
import com.employee.project.model.Department;
import com.employee.project.model.Employee;
import com.employee.project.service.departmentservice;
import com.employee.project.service.employeeservice;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes={DepartmentControllerTest.class})
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages="com.employee.project")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Mock
    departmentservice departmentService;

    @InjectMocks
    DepartmentController departmentController;
    List<Department> departments;

    @Test
    public void testGetDepartment() throws Exception {
        List<Department> departments = new ArrayList<Department>();
        departments.add(new Department(10, "java", "app", null, 2, null, 3, true, false, null));
        when(departmentService.findAllDepartment()).thenReturn(departments);
        this.mockMvc.perform(get("/department/find"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void getDepartmentResourceNotFound() throws Exception {
        long Id = 1L;

        when(this.departmentService.findAllDepartment()).thenThrow(ResourceNotFoundException.class);

        this.mockMvc.perform(get("/Department/find", Id))
                .andExpect(status().isNotFound());


    }
}
