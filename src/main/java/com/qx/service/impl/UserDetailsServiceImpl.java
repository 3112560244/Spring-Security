package com.qx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qx.domain.LoginUser;
import com.qx.domain.User;
import com.qx.exception.MyCustomException;
import com.qx.mapper.MenuMapper;
import com.qx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * TODO
 *
 * @Description 它在整个框架中用作用户 DAO，用于从数据库中加载用户信息和权限信息
 * @Author ZedQ
 * @Date 2023/3/19 16:55
 * @Version 1.0
 **/

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);

        User user = userMapper.selectOne(wrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new MyCustomException(401,"用户名或密码错误");
        }


        //查询用户权限 将权限信息封装到List中
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
//        ArrayList list = new ArrayList<>(Arrays.asList("admin", "user"));
        //封装成UserDetails对象返回
        return new LoginUser(user,list);

    }






}
