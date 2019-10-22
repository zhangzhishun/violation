package com.example.violationsystem.service;

import com.example.violationsystem.mapper.StuMapper;
import com.example.violationsystem.mapper.TeaMapper;
import com.example.violationsystem.pojo.Student;
import com.example.violationsystem.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeaService {
    @Autowired
    private TeaMapper teaMapper;

    public int addTea(Teacher teacher) {
        return teaMapper.addTea(teacher);
    }

    public Teacher getTeaByTnum(String tnum) {
        return teaMapper.getTeaByTnum(tnum);
    }
}
