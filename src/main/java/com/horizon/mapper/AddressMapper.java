package com.horizon.mapper;

import com.horizon.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AddressMapper {

    Integer insert(Address address);

    Integer countByUid(Integer uid);

    List<Address> findByUid(Integer uid);

    Integer updateNonDefaultByUid(@Param("uid") Integer uid);

    Integer updateDefaultByAid(@Param("aid") Integer aid,
                               @Param("modifiedUser") String modifiedUser,
                               @Param("modifiedTime") Date modifiedTime);

    Address findByAid(Integer aid);

    Integer deleteByAid(Integer uid);

    Integer findLastModified(Integer uid);
}
