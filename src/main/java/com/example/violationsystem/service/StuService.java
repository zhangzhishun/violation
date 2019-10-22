package com.example.violationsystem.service;

import com.example.violationsystem.mapper.StuMapper;
import com.example.violationsystem.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuService {
    @Autowired
    private StuMapper stuMapper;

    public int addStu(Student student) {
        return stuMapper.addStu(student);
    }

    public Student getStuBySnum(String snum) {
        return stuMapper.getStuBySnum(snum);
    }

    public List<Student> getStuBySinstitute(String sinstitute) {
        return stuMapper.getStuBySinstitute(sinstitute);
    }

    public List<Student> getStuAll() {
        return stuMapper.getStuAll();
    }

    public int delStu(String snum) {
        return stuMapper.delStu(snum);
    }

    public int upStu(Student student) {
        return stuMapper.upStu(student);
    }


}
