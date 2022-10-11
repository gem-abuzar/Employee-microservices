package com.employee.project;

import com.employee.project.exception.ResourceNotFoundException;
import com.employee.project.model.Address;
import com.employee.project.model.Department;
import com.employee.project.model.Employee;
import com.employee.project.repository.AddressRepository;
import com.employee.project.repository.DepartmentRepository;
import com.employee.project.repository.EmployeeRepository;
import com.employee.project.service.addressserviceimpl;
import com.employee.project.service.departmentserviceimpl;
import com.employee.project.service.employeeserviceimpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ServicesTests {

    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    AddressRepository addressRepository;
    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    employeeserviceimpl employeeService;
    @InjectMocks
    addressserviceimpl addressService;
    @InjectMocks
    departmentserviceimpl departmentService;

    public List<Employee> myemployees;
    public List<Department> mydepartment;
    public List<Address> myaddress;

    @Test
    public void testGetAllEmployees() throws ResourceNotFoundException
    {
        List<Employee> myemployees= new ArrayList<Employee>();
        myemployees.add(new Employee(1,"ali","backend","9999",true,false,null,null));
        myemployees.add(new Employee(2,"rohan","tester","8888",true,false,null,null));

        when(employeeRepository.findAll()).thenReturn(myemployees);
        assertEquals(2,employeeService.findAllEmployees().size());
    }
    @Test
    public void testGetAllDepartment() throws ResourceNotFoundException
    {
        List<Department> mydepartment= new ArrayList<Department>();
        mydepartment.add(new Department(10,"java","app",null,2,null,3,true,false,null));
        mydepartment.add(new Department(20,"python","app",null,3,null,3,true,false,null));
        when(departmentRepository.findAll()).thenReturn(mydepartment);
        assertEquals(2,departmentService.findAllDepartment().size());
    }
    @Test
    public void testGetAllAddress() throws ResourceNotFoundException
    {
        List<Address> myaddress=new ArrayList<Address>();
        myaddress.add(new Address(30,"ghantaghar","mzn","251201",true,false));
        myaddress.add(new Address(31,"budhana","mzn","251201",true,false));
        myaddress.add(new Address(32,"jansath","mzn","251201",true,false));
        when(addressRepository.findAll()).thenReturn(myaddress);
        assertEquals(3,addressService.findAllAddress().size());


    }
    @Test
    public void testGetEmployeeById()
    {
        List<Employee> myemployees= new ArrayList<Employee>();
        myemployees.add(new Employee(1,"ali","tester","9999",true,false,null,null));
        myemployees.add(new Employee(2,"rohan","tester","8888",true,false,null,null));
        when(employeeRepository.findAll()).thenReturn(myemployees);
        assertThat(myemployees).isNotNull();

    }
    @Test
    public void testGetDepartmentById()
    {
        List<Department> mydepartment=new ArrayList<Department>();
        mydepartment.add(new Department(10,"java","app",null,2,null,3,true,false,null));
        when(departmentRepository.findAll()).thenReturn(mydepartment);
        assertThat(mydepartment).isNotNull();

    }

    @Test
    public void testGetAddressById()
    {
        List<Address> myaddress=new ArrayList<Address>();
        myaddress.add(new Address(30,"ghantaghar","mzn","251201",true,false));
        when(addressRepository.findAll()).thenReturn(myaddress);
        assertThat(myaddress).isNotNull();
    }

    @Test
    public void testCreateEmployee() throws ResourceNotFoundException
    {
        Employee employee= new Employee(1,"ali","tester","9999",true,false,null,null);
        when(employeeRepository.save(employee)).thenReturn(employee);
        employeeService.createEmployee(employee);
        assertEquals(employee,employeeService.createEmployee(employee));

    }
    @Test
    public void testCreateDepartment() throws ResourceNotFoundException
    {
        Department department=new Department(10,"java","app",null,2,null,3,true,false,null);
        when(departmentRepository.save(department)).thenReturn(department);
        departmentService.saveDepartment(department);
        assertEquals(department,departmentService.saveDepartment(department));
    }

    @Test
    public void testCreateAddress() throws ResourceNotFoundException
    {
        Address address=new Address(30,"ghantaghar","mzn","251201",true,false);
        when(addressRepository.save(address)).thenReturn(address);
        addressService.saveAddress(address);
        assertEquals(address,addressService.saveAddress(address));
    }

    @Test
    public void testUpdateEmployee() throws ResourceNotFoundException
    {
        Employee employee= new Employee(1,"ali","tester","9999",true,false,null,null);
        employee.setName("Example");
        when(employeeRepository.save(employee)).thenReturn(employee);
        employeeService.createEmployee(employee);
        assertThat(employee.getName()).isNotNull();
    }
    @Test
    public void testUpdateDepartment() throws ResourceNotFoundException
    {
        Department department=new Department(10,"java","app",null,2,null,3,true,false,null);
        department.setDeptName("EX");
        when(departmentRepository.save(department)).thenReturn(department);
        departmentService.saveDepartment(department);
        assertThat(department.getDeptName()).isNotNull();

    }

    @Test
    public void testUpdateAddress() throws ResourceNotFoundException
    {
        Address address=new Address(30,"ghantaghar","mzn","251201",true,false);
        address.setCity("mzn");
        when(addressRepository.save(address)).thenReturn(address);
        addressService.saveAddress(address);
        assertThat(address.getCity()).isNotNull();

    }

    @Test
    public void testDeleteEmployee()
    {
        List<Employee> myemployees= new ArrayList<Employee>();
        myemployees.add(new Employee(1,"ali","tester","9999",true,false,null,null));
        long id=1;
        employeeRepository.DeleteEmployee(id);
        when(employeeRepository.findAll()).thenReturn(myemployees);
        assertThat(myemployees).isNotNull();

    }

    @Test
    public void testDeleteDepartment()
    {
        List<Department> mydepartment=new ArrayList<Department>();
        mydepartment.add(new Department(10,"java","app",null,2,null,3,true,false,null));
        long id=10;
        departmentRepository.DeleteDepartment(id);
        when(departmentRepository.findAll()).thenReturn(mydepartment);
        assertThat(mydepartment).isNotNull();

    }

    @Test
    public void testDeleteAddress()
    {
        List<Address> myaddress=new ArrayList<Address>();
        myaddress.add(new Address(30,"ghantaghar","mzn","251201",true,false));
        int id=30;
        addressRepository.DeleteAddress(id);
        when(addressRepository.findAll()).thenReturn(myaddress);
        assertThat(myaddress).isNotNull();

    }

}
