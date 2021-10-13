package com.horizon.controller;

import com.horizon.entity.Address;
import com.horizon.service.IAddressService;
import com.horizon.service.IDistrictService;
import com.horizon.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController extends BaseController {

    @Autowired
    public IAddressService addressService;
    @Autowired
    public IDistrictService districtService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        address.setProvinceName(districtService.findNameByCode(address.getProvinceCode()));
        address.setCityName(districtService.findNameByCode(address.getCityCode()));
        address.setAreaName(districtService.findNameByCode(address.getAreaCode()));
        addressService.addNewAddress(uid, username, address);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<Address>> findByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> list = addressService.findByUid(uid);
        System.out.println(list);
        return new JsonResult<>(OK, list);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.setDefault(aid, uid, username);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.delete(aid, uid, username);
        return new JsonResult<>(OK);
    }
}
