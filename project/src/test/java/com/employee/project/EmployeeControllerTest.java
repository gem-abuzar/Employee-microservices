package com.employee.project;


import com.employee.project.controller.EmployeeController;
import com.employee.project.exception.ResourceNotFoundException;

import com.employee.project.model.Employee;
import com.employee.project.service.employeeservice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes={EmployeeControllerTest.class})
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages="com.employee.project")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Mock
    employeeservice employeeService;

    @InjectMocks
    EmployeeController employeeController;
    List<Employee> employees;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }


    @Test
    public void testGetEmployeesList() throws Exception {
        employees = new ArrayList<Employee>();
        employees.add(new Employee(1, "ali", "backend", "9999", true, false, null, null));
        when(employeeService.findAllEmployees()).thenReturn(employees);
        this.mockMvc.perform(get("/employee/find"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getEmployeeResourceNotFound() throws Exception {
        Integer Id = 1;

        when(this.employeeService.findAllEmployees()).thenThrow(ResourceNotFoundException.class);

        this.mockMvc.perform(get("/employee/find", Id))
                .andExpect(status().isNotFound());


    }


    @Test
    public void testGetEmployeeById() throws Exception {
        Integer eid = 1;
        Employee emp = new Employee(1, "ali", "tester", "9999", true, false, null, null);
        when(this.employeeService.findEmployeeById(eid)).thenReturn(emp);
        this.mockMvc.perform(get("/employee/{Id}", eid))
                .andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".name").value("ali"))
                .andExpect(MockMvcResultMatchers.jsonPath(".designation").value("tester"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getEmployeeByIdResourceNotFound() throws Exception {
        Integer eid = 1;

        when(this.employeeService.findEmployeeById(eid)).thenThrow(ResourceNotFoundException.class);

        this.mockMvc.perform(get("/employee/{Id}", eid))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateEmployee() throws Exception {
        Employee employee = new Employee(1, "ali", "tester", "9999", true, false, null, null);
        when(employeeService.createEmployee(employee)).thenReturn(employee);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(employee);
        this.mockMvc.perform(post("/employee/create")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Employee employee = new Employee(1L, "ali", "tester", "9999", true, false, null, null);
        Long id = 1L;
        when(employeeService.updateEmployee(employee)).thenReturn(employee);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(employee);

        this.mockMvc.perform(put("/employee/{id}", id)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        long id = 1;
        employeeService.deleteEmployee(id);
        this.mockMvc.perform(delete("/employee/{id}",1))
                .andExpect(status().isOk())
                .andDo(print());

    }

}

