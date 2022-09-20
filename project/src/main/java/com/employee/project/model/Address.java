package com.employee.project.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Where(clause = "is_deleted=false")
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int address_id;
    @NotBlank(message = "Address should be there")
    private String address;
    @NotBlank(message = "City should be specified")
    private String city;
    @NotBlank(message = "Pin code must be there")
    private String pincode;
    private boolean is_active=Boolean.TRUE;
    private boolean is_deleted=Boolean.FALSE;


    }

