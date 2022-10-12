package com.employee.project.controller;

import com.employee.project.exception.ResourceNotFoundException;
import com.employee.project.model.Address;
import com.employee.project.service.addressservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
public class AddressController {
    @Autowired
    private addressservice addressService;

    /**
     *
     * Get all addresses
     */
    @GetMapping("/address/find")
    public ResponseEntity<List<Address>> getAllAddress() {
        log.info("get address");
        List<Address> addressList= addressService.findAllAddress();
        return new ResponseEntity<>(addressList, HttpStatus.OK);

    }

    /**
     *
     * @param id referenced variable used to get data of that employee of id
     * Get address data by id
     */
    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable int id) {
        log.info("get address by id");
        Address address=addressService.findAddressById(id);
        if(address==null)
            throw new ResourceNotFoundException("address id does not exist");
        return new ResponseEntity<Address>(address,HttpStatus.OK);
    }


    /**
     *
     * @param address for creation of address model
     * Create a new address data
     */
    @PostMapping("/address/create")
    public ResponseEntity< Address> createAddress(@RequestBody Address address) {
        try {
            log.info("created");
            Address add = addressService.saveAddress(address);
            return new ResponseEntity<>(add, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            log.error("data not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
    }


    /**
     *
     * @param id referenced variable used to update employee which belongs to that id
     * @param address for update in address model
     * Update address data by id
     */
    @PutMapping("/address/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable int id, @RequestBody Address address) {
        try {
            log.info("updated");
            address.setAddressId(id);
            addressService.updateAddress(address);
            return new ResponseEntity<>(addressService.findAddressById(id),HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            log.error("address id not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    /**
     *
     * @param id referenced variable used to delete employee data by that id whivh it belongs
     * Delete address data by id
     */
    @DeleteMapping("/address/{id}")
    public HttpStatus deleteAddress(@PathVariable int id) {
        log.info("address deletion");
        boolean isDeleted = addressService.deleteAddress(id);
        return isDeleted ? HttpStatus.OK : HttpStatus.NO_CONTENT;
    }
}
