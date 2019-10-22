package com.example.violationsystem.Utils;

import com.example.violationsystem.pojo.Student;
import com.example.violationsystem.service.StuService;
import com.example.violationsystem.service.TeaService;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class PreCheckUtil {
    private StuService stuService=new StuService();
    private TeaService teaService=new TeaService();



}
