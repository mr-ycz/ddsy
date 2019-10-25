package com.ycz.controller;

import com.ycz.pojo.User;
import com.ycz.pojo.UserVo;
import com.ycz.service.UserService;
import com.ycz.utils.Captcha2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("login")
    @ResponseBody
    public String loginLogic(UserVo userVo, HttpServletRequest request){
        UsernamePasswordToken token = new UsernamePasswordToken(userVo.getUsername(), userVo.getPassword());
        if ("on".equals(userVo.getRemember())){
            token.setRememberMe(true);
        }
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        User user = userService.queryUserByUsername(userVo.getUsername());
        request.getSession().setAttribute("userid", user.getId());

        return "1";
    }

    @RequestMapping("/checkUserName")
    @ResponseBody
    public String checkUserName(String username) throws IOException {
        User user = userService.queryUserByUsername(username);
        if (user!=null){
            return "1";
        }
        return "0";
    }

    @RequestMapping("/checkEmail")
    @ResponseBody
    public String checkEmail(String email) throws IOException {
        User user = userService.queryUserByEmail(email);
        if (user!=null){
            return "1";
        }
        return "0";
    }

    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "index";
    }

    @GetMapping("/regist")
    public String registPage(){
        return "register";
    }

    @RequestMapping("/regist")
    @ResponseBody
    public String regist(User user) {
        userService.insertUser(user);
        return "1";
    }

    @RequestMapping("/getCode")
    public String getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Captcha2.generateCaptcha(request, response);
        return "redirect:/user/login";
    }

    @RequestMapping("/checkCode")
    @ResponseBody
    public String checkCode(String code, HttpServletRequest request) throws IOException {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (captcha.equalsIgnoreCase(code)){
            return "0";
        }
        return "1";
    }
}
