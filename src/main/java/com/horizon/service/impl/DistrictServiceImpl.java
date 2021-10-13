package com.horizon.service.impl;

import com.horizon.entity.District;
import com.horizon.mapper.DistrictMapper;
import com.horizon.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        return list;
    }

    @Override
    public String findNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
