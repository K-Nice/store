package com.horizon.service;

import com.horizon.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTest {

    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setPhone("256325");
        address.setAddress("西安");
        address.setName("horizon");
        try {
            addressService.addNewAddress(6, "horizon", address);
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findByUid() {
        List<Address> list = addressService.findByUid(6);
        for(Address item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void setDefault() {
        try {
            addressService.setDefault(5, 6, "horizon");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void delete() {
        try {
            addressService.delete(1, 6, "214");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getByAid() {
        try {
            Address address = addressService.getByAid(6, 6);
            System.out.println(address);
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
