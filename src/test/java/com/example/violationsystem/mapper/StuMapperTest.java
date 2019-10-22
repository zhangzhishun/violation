package com.example.violationsystem.mapper;

import com.example.violationsystem.pojo.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class StuMapperTest {

    @Autowired
    private StuMapper stuMapper;

    @Test
    public void addStu() {
        Student student=new Student("1","1","1","1","1","1","1");
        stuMapper.addStu(student);
    }
}