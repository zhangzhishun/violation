package com.example.violationsystem.controller;

import com.example.violationsystem.pojo.Student;
import com.example.violationsystem.pojo.Teacher;
import com.example.violationsystem.service.StuService;
import com.example.violationsystem.service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class RegController {
    @Autowired
    private StuService stuService;
    @Autowired
    private TeaService teaService;

    //学生注册页
    @RequestMapping(value = "/stuRegPage")
    public String stuRegPage() {

        return "stuRegPage";
    }

    //学生注册：姓名，学号，性别，院系，电话，身份证号
    @RequestMapping(value = "/stuReg", method = {RequestMethod.POST})
    @ResponseBody
    public String stuReg(@RequestBody Student student, HttpServletResponse response) {

        System.out.println(student.toString());
        response.setStatus(200);
        try {
            stuService.addStu(student);
        }catch (Exception e){
            response.setStatus(500);
        }

        String detail="";

        return "{\"code\":\"" + response.getStatus() + "\",\"detail\":\"" + detail + "\"}";
    }

    //辅导员注册页
    @RequestMapping(value = "/teaRegPage")
    public String teaRegPage() {


        return "teaRegPage";
    }

    //辅导员注册：姓名，工号，性别，院系
    @RequestMapping(value = "/teaReg")
    @ResponseBody
    public String stReg(@RequestBody Teacher teacher,HttpServletResponse response) {

        System.out.println(teacher.toString());
        response.setStatus(200);
        try {
            teaService.addTea(teacher);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(500);
        }

        String detail="";

        return "{\"code\":\"" + response.getStatus() + "\",\"detail\":\"" + detail + "\"}";
    }


}
