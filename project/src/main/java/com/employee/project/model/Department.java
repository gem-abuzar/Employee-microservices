package com.employee.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Where(clause = "is_deleted=false")
@Table(name="department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long deptid;
    @NotBlank(message = "department name should be there")
    private String dept_name;
    @NotBlank(message="Description should be there")
    private String dept_desc;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate create_date;
    private int created_by;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate update_date;
    private int updated_by;
    private boolean is_active=Boolean.TRUE;
    private boolean is_deleted=Boolean.FALSE;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Employee> employees;
    }



