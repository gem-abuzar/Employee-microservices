package com.employee.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@Getter
@Setter
@ToString

@Entity
@Where(clause = "is_deleted=false")
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "name should be there")
    private String name;
    @NotBlank(message = "designation should be there")
    private String designation;
    @NotBlank(message = "phone number should be there")
    private String phNo;
    private boolean isActive=Boolean.TRUE;
    private boolean isDeleted=Boolean.FALSE;

    @OneToOne
    @JoinColumn(name="address_id")
    private Address address;
    @ManyToOne(
    )
    @JoinColumn(name = "dept_id")
    private Department department;


}
