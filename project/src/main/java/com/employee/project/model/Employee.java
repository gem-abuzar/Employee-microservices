package com.employee.project.model;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
    private String phno;
    private boolean is_active=Boolean.TRUE;
    private boolean is_deleted=Boolean.FALSE;

    @OneToOne
    @JoinColumn(name="address_id")
    private Address address;
    @ManyToOne(
    )
    @JoinColumn(name = "dept_id")
    private Department department;


}
