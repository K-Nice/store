package com.horizon.mapper;

import com.horizon.entity.District;

import java.util.List;

public interface DistrictMapper {

    List<District> findByParent(String parent);

    String findNameByCode(String code);
}
