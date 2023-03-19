package com.qx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qx.domain.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/3/19 14:58
 * @Version 1.0
 **/
public interface MenuMapper extends BaseMapper<Menu> {

    //根据用户id查询菜单
    List<String> selectPermsByUserId(@Param("userId") Long userId);

}
