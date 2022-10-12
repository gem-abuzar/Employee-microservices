package com.employee.project;

import com.employee.project.controller.AddressController;
import com.employee.project.controller.DepartmentController;
import com.employee.project.exception.ResourceNotFoundException;
import com.employee.project.model.Address;
import com.employee.project.model.Department;
import com.employee.project.model.Employee;
import com.employee.project.service.addressservice;
import com.employee.project.service.departmentservice;
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

@SpringBootTest(classes={AddressControllerTest.class})
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages="com.employee.project")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddressControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Mock
    addressservice addressService;

    @InjectMocks
    AddressController addressController;
    List<Address> adds;

    @Test
    public void testGetAddress() throws Exception {
        List<Address> adds = new ArrayList<Address>();
        adds.add(new Address(30, "ClockTower", "mzn", "251201", true, false));
        when(addressService.findAllAddress()).thenReturn(adds);
        this.mockMvc.perform(get("/address/find"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getAddressResourceNotFound() throws Exception {
        Integer Id = 1;
        when(this.addressService.findAllAddress()).thenThrow(ResourceNotFoundException.class);
        this.mockMvc.perform(get("/address/find/{id}", Id))
                .andExpect(status().isNotFound()).andDo(print());


    }

    @Test
    public void testGetAddressById() throws Exception {
        Integer did = 30;
        Address adds = new Address(30, "ghantaghar", "mzn", "251201", true, false);
        when(this.addressService.findAddressById(did)).thenReturn(adds);
        this.mockMvc.perform(get("/address/{Id}", did))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".addressId").value(30))
                .andExpect(MockMvcResultMatchers.jsonPath(".address").value("ghantaghar"))
                .andExpect(MockMvcResultMatchers.jsonPath(".city").value("mzn"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void getAddressByIdResourceNotFound() throws Exception {
        Integer eid = 1;

        when(this.addressService.findAddressById(eid)).thenThrow(ResourceNotFoundException.class);

        this.mockMvc.perform(get("/address/{Id}", eid))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testCreateAddress() throws Exception {
        Address adds = new Address(30, "ghantaghar", "mzn", "251201", true, false);
        when(addressService.saveAddress(adds)).thenReturn(adds);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(adds);
        this.mockMvc.perform(post("/address/create")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());

    }
    @Test
    public void testCreateAddressBadRequest() throws Exception {
        Address adds = new Address(30, "ghantaghar", "mzn", "251201", true, false);
        Integer Id = 100;
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(adds);

        this.mockMvc.perform(post("/addresses/{id}",Id)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    public void testUpdateAddress() throws Exception {
        Address adds = new Address(30, "ghantaghar", "mzn", "251201", true, false);
        Integer id = 1;
        when(addressService.updateAddress(adds)).thenReturn(adds);
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(adds);

        this.mockMvc.perform(put("/address/{id}", id)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void testDeleteAddress() throws Exception {
        Integer id = 1;
        addressService.deleteAddress(id);
        this.mockMvc.perform(delete("/address/{id}",1))
                .andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    public void testDeleteAddressNotFound() throws Exception {
        Integer Id = 32;
        when(addressService.deleteAddress(Id))
                .thenReturn(true);

        this.mockMvc.perform(delete("/addresses/{Id}",Id))
                .andExpect(status().isNotFound())
                .andDo(print());

    }

}


