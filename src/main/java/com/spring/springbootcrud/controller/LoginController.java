package com.spring.springbootcrud.controller;

import com.spring.springbootcrud.Mapper.UserMapper;
import com.spring.springbootcrud.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserMapper userMapper;
    @PostMapping(value="/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpSession session){

        User user=userMapper.getUserByName(username);
        if(user==null)
        {
            map.put("msg","用户名不存在");
            return "login";
        }
        else if(user.getUser_password().equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }
        else{
           map.put("msg","用户名或密码错误");
           return "login";
        }
    }
}
