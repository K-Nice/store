package com.horizon.service.impl;

import com.horizon.entity.Address;
import com.horizon.mapper.AddressMapper;
import com.horizon.service.IAddressService;
import com.horizon.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private Integer MAX_SIZE;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        int count = addressMapper.countByUid(uid);
        if(count >= MAX_SIZE) {
            throw new AddressCountLimitException("收货地址数目超过上限:" + MAX_SIZE);
        }

        Date time = new Date();
        address.setUid(uid);
        address.setIsDefault(count == 0 ? 1 : 0);
        address.setCreatedUser(username);
        address.setCreatedTime(time);
        address.setModifiedUser(username);
        address.setModifiedTime(time);

        int rows = addressMapper.insert(address);
        if(rows != 1) {
            throw new InsertException("插入数据时产生未知异常");
        }

        return ;
    }

    @Override
    public List<Address> findByUid(Integer uid) {
        return addressMapper.findByUid(uid);
    }

    @Override
    @Transactional
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);

        if(result == null) {
            throw new AddressNotFoundException("收货地址数据不存在");
        } else if(!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }

        int rows = addressMapper.updateNonDefaultByUid(uid);
        if(rows < 1) {
            throw new InsertException("设置默认收获地址时发生异常[1]");
        }

        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if(rows != 1) {
            throw new InsertException("设置默认收获地址时发生异常[2]");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);

        if(result == null) {
            throw new AddressNotFoundException("地址不存在");
        } else if(!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }

        int rows = addressMapper.deleteByAid(aid);
        if(rows != 1) {
            throw new DeleteException("删除收获地址时产生未知错误");
        }

        int num = addressMapper.countByUid(uid);
        if(num == 0) {
            return ;
        }

        int lastModifiedId = addressMapper.findLastModified(uid);
        rows = addressMapper.updateDefaultByAid(lastModifiedId, username, new Date());
        if(rows != 1) {
            throw new UpdateException("更新默认收获地址时产生未知异常");
        }
    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        Address address = addressMapper.findByAid(aid);

        if(address == null) {
            throw new AddressNotFoundException("收货地址不存在");
        } else if(!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }

        return address;
    }

}
