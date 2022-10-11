package com.employee.project;

import com.employee.project.controller.DepartmentController;
import com.employee.project.controller.EmployeeController;
import com.employee.project.exception.ResourceNotFoundException;
import com.employee.project.model.Department;
import com.employee.project.model.Employee;
import com.employee.project.service.departmentservice;
import com.employee.project.service.employeeservice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    public void testGetDepartmentById() throws Exception {
        long did = 2;
        Department dept = new Department(2, "java", "app", null, 2, null, 3, true, false, null);
        when(this.departmentService.findDepartmentById(did)).thenReturn(dept);
        this.mockMvc.perform(get("/department/{Id}", did))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".deptId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath(".deptName").value("java"))
                .andExpect(MockMvcResultMatchers.jsonPath(".deptDesc").value("app"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getDepartmentByIdResourceNotFound() throws Exception {
        Integer did = 1;

        when(this.departmentService.findDepartmentById(did)).thenThrow(ResourceNotFoundException.class);

        this.mockMvc.perform(get("/department/{Id}", did))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateDepartment() throws Exception {
        Department dept = new Department(1, "java", "app", null, 2, null, 3, true, false, null);
        when(departmentService.saveDepartment(dept)).thenReturn(dept);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(dept);
        this.mockMvc.perform(post("/department/create")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    public void testCreateDepartmentBadRequest() throws Exception {
        long deptId = 2;
        Department dept = new Department(1, "java", "app", null, 2, null, 3, true, false, null);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(dept);

        this.mockMvc.perform(post("/departments/{id}", deptId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testUpdateDepartment() throws Exception {
        Department dept = new Department(1, "java", "app", null, 2, null, 3, true, false, null);
        Long id = 1L;
        when(departmentService.updateDepartment(dept)).thenReturn(dept);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(dept);

        this.mockMvc.perform(put("/department/{id}", id)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testUpdateDepartmentBadRequest() throws Exception {
        long deptId = 1;
        Department dept = new Department(1, "java", "app", null, 2, null, 3, true, false, null);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(dept);

        this.mockMvc.perform(put("/departments/{Id}", deptId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testDeleteDepartment() throws Exception {
        long id = 1;
        departmentService.deleteDepartment(id);
        this.mockMvc.perform(delete("/department/{id}", 1))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void testDeleteDepartmentNotFound() throws Exception {
        long deptId = 1;

        when(departmentService.deleteDepartment(deptId))
                .thenReturn(true);

        this.mockMvc.perform(delete("/departments/{Id}", deptId))
                .andExpect(status().isNotFound())
                .andDo(print());

    }
}


