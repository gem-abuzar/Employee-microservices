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
    private long deptId;
    @NotBlank(message = "department name should be there")
    private String deptName;
    @NotBlank(message="Description should be there")
    private String deptDesc;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createDate;
    private int createdBy;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate updateDate;
    private int updatedBy;
    private boolean isActive=Boolean.TRUE;
    private boolean isDeleted=Boolean.FALSE;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Employee> employees;
    }



