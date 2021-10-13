package com.horizon.controller;

import com.horizon.entity.District;
import com.horizon.service.IDistrictService;
import com.horizon.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("district")
public class DistrictController extends BaseController {

    @Autowired
    public IDistrictService districtService;

    @GetMapping({"", "/"})
    public JsonResult<List<District>> getByParent(String parent) {
        return new JsonResult<List<District>>(OK, districtService.getByParent(parent));
    }

}
