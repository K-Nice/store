package com.horizon.service;

import com.horizon.entity.District;

import java.util.List;

public interface IDistrictService {

    List<District> getByParent(String parent);

    String findNameByCode(String code);
}
