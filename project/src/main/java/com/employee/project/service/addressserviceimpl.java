package com.employee.project.service;

import com.employee.project.model.Address;
import com.employee.project.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class addressserviceimpl implements addressservice{

    @Autowired
    private AddressRepository repository;
    @Override
    public List<Address> findAllAddress() {
        return repository.findAll();
    }

    @Override
    public Address findAddressById(int id) {
        Optional<Address> address = repository.findById(id);
        if(address.isPresent()) {
            return address.get();
        }
        return null;
    }

    @Override
    public Address saveAddress(Address address) {
        return repository.save(address);
    }

    @Override
    public Address updateAddress(Address address) {
        Optional<Address> addDb = repository.findById(address.getAddressId());
        if(addDb.isPresent()) {
            Address add = addDb.get();
            add.setAddressId(address.getAddressId());
            add.setAddress(address.getAddress());
            add.setCity(address.getCity());
            add.setPinCode(address.getPinCode());
            return repository.save(add);
        }
        return null;
    }

    @Override
    public boolean deleteAddress(int id) {
        Optional<Address> address = repository.findById(id);
        if(address.isPresent()) {
            repository.delete(address.get());
            return true;
        }
        return false;    }
}
