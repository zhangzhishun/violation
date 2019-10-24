package com.example.violationsystem.mapper;

import com.example.violationsystem.pojo.Message;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface MsgMapper {

    /**
     * 新增违纪
     */
    int addMsg(Message msg);

    /**
     * 查询违纪
     */
    List<HashMap<String,String>> getMsgBySnum(String snum);
    List<HashMap<String,String>> getMsgBySinstitute(String sinstitute);
    List<HashMap<String,String>> getMsgAll();

    /**
     * 删除违纪
     */
    int delMsg(String msgNum);

    /**
     * 修改违纪
     */
    int upMsg(Message msg);

    /**
     * 修改违纪
     */
    int upAppeal(Message msg);

}
