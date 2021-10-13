package com.horizon.mapper;

import com.horizon.entity.Cart;
import com.horizon.vo.CartVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CartMapper {

    Integer insert(Cart cart);

    Integer updateNumByCid(
            @Param("cid") Integer cid,
            @Param("num") Integer num,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    Cart findByUidAndPid(
            @Param("uid") Integer uid,
            @Param("pid") Integer pid);

    List<CartVo> findVoByUid(Integer uid);

    Cart findByCid(Integer cid);

    List<CartVo> findVoByCids(Integer[] cids);
}
