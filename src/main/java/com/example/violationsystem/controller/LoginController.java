package com.example.violationsystem.controller;

import com.example.violationsystem.mapper.AdmMapper;
import com.example.violationsystem.pojo.Student;
import com.example.violationsystem.pojo.Teacher;
import com.example.violationsystem.service.AdmService;
import com.example.violationsystem.service.StuService;
import com.example.violationsystem.service.TeaService;
import com.google.gson.Gson;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private StuService stuService;
    @Autowired
    private TeaService teaService;
    @Autowired
    private AdmService admService;

    private final String salt = "a！09312s.@#;df";

    //主页
    @RequestMapping(value = "/index")
    public String indexTea(HttpServletRequest request, @CookieValue("role") String role) {

        if (!checkCookie(salt, request)) return "redirect:/login";
        if (role.equals("tea")) return "indexTea";
        else if (role.equals("stu")) return "indexStu";
        else if (role.equals("adm")) return "indexAdm";
        else if (role.equals("stuM")){
            System.out.println("委员会角色");
            return "indexStuM";
        }
        else return "redirect:/login";
    }


    //登陆页面
    @RequestMapping(value = "/login")
    public String login() {

        return "login";
    }

    //登陆验证
    @RequestMapping(value = "/check")
    @ResponseBody
    public String check(HttpServletResponse response, @RequestParam("userName") String userName,
                        @RequestParam("password") String password, @RequestParam("role") String role) {
        System.out.println(userName);
        System.out.println(password);
        String detail = "";
        response.setStatus(401);

        boolean flag = false;
        if (role.equals("stu")) {
            Student student = stuService.getStuBySnum(userName);
            if (student == null || !student.getPassword().equals(password)) {
                return "{\"code\":\"" + response.getStatus() + "\",\"detail\":\"" + detail + "\"}";
            }
        } else if (role.equals("tea")) {
            Teacher teacher = teaService.getTeaByTnum(userName);
            if (teacher == null || !teacher.getPassword().equals(password)) {
                return "{\"code\":\"" + response.getStatus() + "\",\"detail\":\"" + detail + "\"}";
            }
        } else if (role.equals("stuM")) {
            System.out.println("角色：" +role);
            Teacher teacher = teaService.getTeaByTnum(userName);
            System.out.println("教师密码"+teacher.getPassword());
            if (teacher == null || !teacher.getPassword().equals(password)) {
                System.out.println("结果："+detail);
                return "{\"code\":\"" + response.getStatus() + "\",\"detail\":\"" + detail + "\"}";
            }
        } else if (role.equals("adm")) {
            if (!admService.getPassword(userName).equals(password)) {
                return "{\"code\":\"" + response.getStatus() + "\",\"detail\":\"" + detail + "\"}";
            }
        }

        String cookieValue = SecureUtil.md5(userName + password + salt);
        Cookie cookie = new Cookie("Auth", cookieValue);
        cookie.setPath("/");

        Cookie cookie1 = new Cookie("role", role);
        cookie1.setPath("/");

        Cookie cookie2 = new Cookie("userName", userName);
        cookie1.setPath("/");

        response.setStatus(200);
        response.addCookie(cookie);
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        return "{\"code\":\"" + response.getStatus() + "\",\"detail\":\"" + detail + "\"}";
    }


    //退出登陆
    @RequestMapping(value = "/exit")
    public String exit(HttpServletResponse response) {
        Cookie cookie = new Cookie("Auth", "");
        cookie.setPath("/");
        response.addCookie(cookie);
        return "login";
    }

    @RequestMapping(value = "/getUser")
    @ResponseBody
    public String getUser(@CookieValue("userName") String userName, @CookieValue("role") String role) {
        String institute="";
        String name="";
        if(role.equals("tea")){
            Teacher teacher=teaService.getTeaByTnum(userName);
            institute=teacher.getTinstitute();
            name=teacher.getTname();

        }else if(role.equals("stu")){
            Student student=stuService.getStuBySnum(userName);
            institute=student.getSinstitute();
            name=student.getSname();
        }
        String[] strings = {userName, role,institute,name};
        return new Gson().toJson(strings);
    }

    public boolean checkCookie(String salt, HttpServletRequest request) {
        String role = "";
        String auth = "";
        String userName = "";

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("role")) role = cookie.getValue();
            else if (cookie.getName().equals("Auth")) auth = cookie.getValue();
            else if (cookie.getName().equals("userName")) userName = cookie.getValue();
        }

        if (role.equals("")) return false;
        if (auth.equals("")) return false;
        if (userName.equals("")) return false;

        String password = "";
        if (role.equals("stu")) {
            Student student = stuService.getStuBySnum(userName);
            password = student.getPassword();
        } else if (role.equals("tea")) {
            password = teaService.getTeaByTnum(userName).getPassword();
        }  else if (role.equals("stuM")) {
            password = teaService.getTeaByTnum(userName).getPassword();
        } else if (role.equals("adm")) {
            password =admService.getPassword(userName);
        } else {
            return false;
        }

        String key = SecureUtil.md5(userName + password + salt);
        System.out.println("key:" + key);

        if (key.equals(auth)) {
            return true;
        } else {
            return false;
        }

    }

    @RequestMapping(value = "/stuListPage")
    public String stuListPage(){

        return "stuListPage";
    }

    @RequestMapping(value = "/userInfo")
    public String userInfo(){
        return "userInfo";
    }


}
