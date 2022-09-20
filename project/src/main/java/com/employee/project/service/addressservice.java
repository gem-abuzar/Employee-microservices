package com.employee.project.service;

import com.employee.project.model.Address;

import java.util.List;

public interface addressservice {
    List<Address> findAllAddress();

    Address findAddressById(int id);

    Address saveAddress(Address address);

    Address updateAddress(Address address);

    boolean deleteAddress(int id);
}
