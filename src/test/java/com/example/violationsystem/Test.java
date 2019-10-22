package com.example.violationsystem;

import com.example.violationsystem.mapper.StuMapper;
import com.example.violationsystem.pojo.Message;
import com.example.violationsystem.pojo.Student;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

public class Test {
    @Autowired
    private StuMapper stuMapper;

    public void add(){
        stuMapper.addStu(new Student("1","1","1","1","1","1","1"));
    }

    public static void main(String[] args) {
        new Test().add();
    }
}
