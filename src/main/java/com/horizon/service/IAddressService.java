package com.horizon.service;

import com.horizon.entity.Address;

import java.util.List;

public interface IAddressService {

    void addNewAddress(Integer uid, String username, Address address);

    List<Address> findByUid(Integer uid);

    void setDefault(Integer aid, Integer uid, String username);

    void delete(Integer aid, Integer uid, String username);

    Address getByAid(Integer aid, Integer uid);
}
