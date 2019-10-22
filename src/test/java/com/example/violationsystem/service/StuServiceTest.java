package com.example.violationsystem.service;

import com.example.violationsystem.pojo.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class StuServiceTest {
    @Autowired
    private StuService stuService;
    @Test
    public void addStu() {
        stuService.addStu(new Student("1","2","1","1","1","1","1"));
    }
}