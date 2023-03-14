package com.qx.test;

import com.qx.domain.User;
import com.qx.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/3/13 19:58
 * @Version 1.0
 **/
@SpringBootTest
public class MapperTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }




    @Test
    public void TestBCryp(){
//        $2a$10$UGU/R4rmdI.MrMRDCz.8juNfUXfsyOn2SEP6Nu9ZnVnm9E4bLUPI2
//        $2a$10$On1lmKEY4X0pPD/yKGNwTeUWxV9TaSDwXIl4wdml70QeZx64ziCUK




        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


        //数据库中的密码必须要进行加密
        String encode = bCryptPasswordEncoder.encode("test");
        System.out.println(encode);



        boolean b = bCryptPasswordEncoder.matches("123456", "$2a$10$UGU/R4rmdI.MrMRDCz.8juNfUXfsyOn2SEP6Nu9ZnVnm9E4bLUPI2");
        System.out.println(b);
//        String encode = bCryptPasswordEncoder.encode("123456");
//        String encode1 = bCryptPasswordEncoder.encode("123456");
//        System.out.println(encode);
//        System.out.println(encode1);
    }






















}
