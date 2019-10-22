package com.example.violationsystem.mapper;

import com.example.violationsystem.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StuMapper {
    /**
     * 新增学生
     */
    int addStu(Student student);

    /**
     * 通过学号获取学生信息
     */
    Student getStuBySnum(String snum);

    List<Student> getStuBySinstitute(String sinstitute);

    List<Student> getStuAll();

    int delStu(String snum);

    int upStu(Student student);
}
