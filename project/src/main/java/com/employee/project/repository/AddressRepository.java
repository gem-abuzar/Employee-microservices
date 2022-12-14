package com.employee.project.repository;

import com.employee.project.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AddressRepository extends JpaRepository <Address, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update address set Deleted = true,Active = false where addressId = :addressId",nativeQuery = true)
    public void DeleteAddress(@Param("addressId") Integer address_id);
}
