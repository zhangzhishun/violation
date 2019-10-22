package com.example.violationsystem.mapper;

import com.example.violationsystem.pojo.Student;
import com.example.violationsystem.pojo.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeaMapper {
    /**
     * 新增辅导员
     */
    int addTea(Teacher teacher);

    /**
     * 通过工号获取辅导员信息
     */
    Teacher getTeaByTnum(String tnum);
}
