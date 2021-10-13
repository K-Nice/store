package com.horizon.mapper;

import com.horizon.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTest {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(6);
        address.setPhone("1256625");
        address.setName("张三");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid() {
        System.out.println(addressMapper.countByUid(6));
    }

    @Test
    public void findBuUid() {
        List<Address> list = addressMapper.findByUid(6);
        for(Address address : list) {
            System.out.println(address);
        }
    }

    @Test
    public void updateNonDefaultByUid() {
        Integer rows = addressMapper.updateNonDefaultByUid(6);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateDefaultByAid() {
        Integer aid = 8;
        String modifiedUser = "214";
        Date modifiedTime = new Date();
        Integer rows = addressMapper.updateDefaultByAid(aid, modifiedUser, modifiedTime);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findByAid() {
        Integer aid = 8;
        Address result = addressMapper.findByAid(aid);
        System.out.println(result);
    }

    @Test
    public void findByUid() {
        addressMapper.deleteByAid(2);
    }

    @Test
    public void findLastModified() {
        System.out.println(addressMapper.findLastModified(6));
    }
}
