package com.employee.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Entity
@Where(clause = "is_deleted=false")
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int addressId;
    @NotBlank(message = "Address should be there")
    private String address;
    @NotBlank(message = "City should be specified")
    private String city;
    @NotBlank(message = "Pin code must be there")
    private String pinCode;
    private boolean isActive=Boolean.TRUE;
    private boolean isDeleted=Boolean.FALSE;


    }

