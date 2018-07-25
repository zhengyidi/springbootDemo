package com.transcend.demo.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.transcend.demo.dao.datasource1.UserMapper;
import com.transcend.demo.dao.datasource2.EmpMapper;
import com.transcend.demo.entity.datasource1.User;
import com.transcend.demo.entity.datasource2.Emp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private  UserMapper userMapper;

    @Resource
    private  EmpMapper empMapper;

    @RequestMapping("/user")
    public Object user() {
        PageHelper.startPage(1,1);
        List<User> users = userMapper.selectAll();
        PageInfo<User> info = new PageInfo<>(users);
        return info.getList();
//        return userMapper.selectAll();
    }


    @RequestMapping("/emp")
    public Object emp() {
//        System.out.println(empMapper);
//        return empMapper.selectAll();
        PageHelper.startPage(2,1);
        List<Emp> emps = empMapper.selectAll();
        PageInfo<Emp> info = new PageInfo<>(emps);

        return info.getList();
    }


//    @RequestMapping("/addUser")
//    @Transactional/*("datasource1TransactionManager")*/
//    static void addUser(){
//        User user = new User();
//        user.setUsername("4");
//        user.setPassword("2");
//        user.setStatus(1);
//        user.setType(1);
//        userMapper.insert(user);
//        if(1==1){
//            throw new RuntimeException("异常");
//        }
//        user.setUsername("234");
//        userMapper.insert(user);
//        userMapper.selectAll();
//    }

}
