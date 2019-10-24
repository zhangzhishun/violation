package com.example.violationsystem.controller;

import com.example.violationsystem.pojo.Message;
import com.example.violationsystem.pojo.Student;
import com.example.violationsystem.service.MsgService;
import com.example.violationsystem.service.StuService;
import com.example.violationsystem.service.TeaService;
import com.google.gson.Gson;
import com.sun.org.apache.regexp.internal.RE;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.json.JSONObject;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


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
        } else if(role.equals("stuM")) {
            return "stuMManage";
        } else{
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

        System.out.println(list);


//        System.out.println();
        String dataJson = new Gson().toJson(list);
        System.out.println(dataJson);
        return "{\"code\":" + 0 + ",\"msg\":\"" + msg + "\",\"count\":" + list.size() + ",\"data\":" + dataJson + "}";
    }


    //返回违纪列表
    @RequestMapping(value = "/stumGetVioList")
    @ResponseBody
    public String stumGetVioList(HttpServletResponse response, @CookieValue("userName") String userName, @CookieValue("role") String role) {

        response.setStatus(200);
        String msg = "";
        List<HashMap<String, String>> list = null;
        if (role.equals("tea")) {
            String institute = teaService.getTeaByTnum(userName).getTinstitute();
            list = msgService.getMsgBySinstitute(institute);
        } else if (role.equals("stu")) {
            list = msgService.getMsgBySnum(userName);
        } else if (role.equals("stuM")) {
            list = msgService.getMsgAll();
        } else if (role.equals("adm")) {
            list = msgService.getMsgAll();
        }

        System.out.println(userName);


//        System.out.println();
        String dataJson = new Gson().toJson(list);
        System.out.println(dataJson);
        return "{\"code\":" + 0 + ",\"msg\":\"" + msg + "\",\"count\":" + list.size() + ",\"data\":" + dataJson + "}";
    }

    //申诉信息修改
    @RequestMapping(value = "/upAppeal", method = {RequestMethod.POST})
    @ResponseBody
    public String upAppeal(@RequestBody Message message, HttpServletResponse response) {
        System.out.println(message);
        response.setStatus(200);
        try {
            msgService.upAppeal(message);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
        String detail = "";
        return "{\"code\":\"" + response.getStatus() + "\",\"detail\":\"" + detail + "\"}";
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


    /** 填写申诉表 */
    @GetMapping("/stuAppeal")
    public String stuAppeal(){
        return "stuAppeal";
    }
    /** 填写申诉表 */
    @GetMapping("/test")
    public String test(){

        return "test";
    }

    /** 填写申诉表 */
    @GetMapping("/vioAddAppealPage")
    public String vioAddAppealPage(){

        return "vioAddAppealPage";
    }
    /** 上传文件 */
    @GetMapping("/uploadNewWindow")
    public String uploadNewWindow(){
        return "uploadNewWindow";
    }

    /** 上传文件 */
    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    @ResponseBody
    public String upload(@RequestParam MultipartFile file, HttpServletRequest request)throws IllegalStateException, IOException {
        if (null != file) {
            String myFileName = file.getOriginalFilename();// 文件原名称
            System.out.println(myFileName);
            String fileName = "123.docx";
            String path="E:\\upload\\";//设置文件保存路径
            File fileDir=new File(path);
            if (!fileDir.exists()) { //如果不存在 则创建
                fileDir.mkdirs();
            }
            File localFile = new File(path+fileName);
            try {
                file.transferTo(localFile);
                System.out.println(path+fileName);
                //return path+fileName;
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            System.out.println("文件为空");
        }
        JSONObject jsonObject = new JSONObject("{status:200}");
        System.out.println(jsonObject);
        return jsonObject.toString();
        //return jsonObject.toString();
    }


    @RequestMapping("/download")
    private String download(HttpServletResponse response){
        String downloadFilePath = "E:\\upload\\123.docx";//被下载的文件在服务器中的路径,
        File file = new File(downloadFilePath);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + "123.docx");
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "下载失败";
    }


    @GetMapping("/getCookie")
    public String checkCookie(HttpServletRequest request) {
        String role="";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("role")) role = cookie.getValue();
        }
        if (role.equals("")){
            return "null";
        }else{
            return role;
        }
    }
}
