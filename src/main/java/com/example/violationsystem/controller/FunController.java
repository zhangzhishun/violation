package com.example.violationsystem.controller;

import com.example.violationsystem.pojo.Message;
import com.example.violationsystem.pojo.Student;
import com.example.violationsystem.service.MsgService;
import com.example.violationsystem.service.StuService;
import com.example.violationsystem.service.TeaService;
import com.google.gson.Gson;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;


@Controller
public class FunController {
    @Autowired
    MsgService msgService;
    @Autowired
    TeaService teaService;
    @Autowired
    StuService stuService;

    //管理页
    @RequestMapping(value = "/teaManage")
    public String teaManage(@CookieValue("role") String role) {
        if (role.equals("stu")) {
            return "stuManage";
        } else {
            return "teaManage";
        }
    }

    //违纪信息增加页
    @RequestMapping(value = "/vioAddPage")
    public String vioAddPage() {

        return "vioAddPage";
    }

    //违纪信息增加
    @RequestMapping(value = "/vioAdd", method = {RequestMethod.POST})
    @ResponseBody
    public String vioAdd(@RequestBody Message msg, HttpServletResponse response) {

        System.out.println(msg.toString());
        response.setStatus(200);
        try {
            msgService.addMsg(msg);
        } catch (Exception e) {
            response.setStatus(500);
        }
        String detail = "";
        return "{\"code\":\"" + response.getStatus() + "\",\"detail\":\"" + detail + "\"}";
    }

    //违纪信息修改
    @RequestMapping(value = "/vioEdit", method = {RequestMethod.POST})
    @ResponseBody
    public String vioEdit(@RequestBody Message msg, HttpServletResponse response) {

        System.out.println(msg.toString());
        response.setStatus(200);
        try {
            msgService.upMsg(msg);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
        String detail = "";
        return "{\"code\":\"" + response.getStatus() + "\",\"detail\":\"" + detail + "\"}";
    }


    //返回违纪列表
    @RequestMapping(value = "/getVioList")
    @ResponseBody
    public String getVioList(HttpServletResponse response, @CookieValue("userName") String userName, @CookieValue("role") String role) {

        response.setStatus(200);
        String msg = "";
        List<HashMap<String, String>> list = null;
        if (role.equals("tea")) {
            String institute = teaService.getTeaByTnum(userName).getTinstitute();
            list = msgService.getMsgBySinstitute(institute);
        } else if (role.equals("stu")) {
            list = msgService.getMsgBySnum(userName);
        } else if (role.equals("adm")) {
            list = msgService.getMsgAll();
        }

        System.out.println(userName);


//        System.out.println();
        String dataJson = new Gson().toJson(list);

        return "{\"code\":" + 0 + ",\"msg\":\"" + msg + "\",\"count\":" + list.size() + ",\"data\":" + dataJson + "}";
    }

    //删除违纪
    @RequestMapping(value = "/delMsg")
    @ResponseBody
    public String delMsg(HttpServletResponse response, @RequestParam("msgNum") String msgNum) {

        System.out.println("msgNum:" + msgNum);
        msgService.delMsg(msgNum);
        String detail = "";
        response.setStatus(200);
        return "{\"code\":\"" + response.getStatus() + "\",\"detail\":\"" + detail + "\"}";

    }

    //删除学生信息
    @RequestMapping(value = "/delStu")
    @ResponseBody
    public String delStu(HttpServletResponse response, @RequestParam("snum") String snum) {

        stuService.delStu(snum);
        String detail = "";
        response.setStatus(200);
        return "{\"code\":\"" + response.getStatus() + "\",\"detail\":\"" + detail + "\"}";

    }
    //学生信息修改
    @RequestMapping(value = "/editStu", method = {RequestMethod.POST})
    @ResponseBody
    public String editStu(@RequestBody Student student, HttpServletResponse response) {

        System.out.println(student);
        response.setStatus(200);
        try {
            stuService.upStu(student);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
        String detail = "";
        return "{\"code\":\"" + response.getStatus() + "\",\"detail\":\"" + detail + "\"}";
    }


    //返回学生列表
    @RequestMapping(value = "/getStuList")
    @ResponseBody
    public String getStudent(HttpServletResponse response, @CookieValue("userName") String userName, @CookieValue("role") String role) {

        response.setStatus(200);
        String msg = "";
        List<Student> list = null;
        if (role.equals("tea")) {
            String institute = teaService.getTeaByTnum(userName).getTinstitute();
            list = stuService.getStuBySinstitute(institute);
        } else if (role.equals("adm")) {
            list = stuService.getStuAll();
        }

        String dataJson = new Gson().toJson(list);

        return "{\"code\":" + 0 + ",\"msg\":\"" + msg + "\",\"count\":" + list.size() + ",\"data\":" + dataJson + "}";
    }

    //学生信息修改页
    @RequestMapping(value = "stuAddPage")
    public String stuAddPage() {
        return "stuAddPage";
    }


}
