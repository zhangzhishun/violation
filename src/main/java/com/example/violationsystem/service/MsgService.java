package com.example.violationsystem.service;

import com.example.violationsystem.mapper.MsgMapper;
import com.example.violationsystem.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MsgService {

    @Autowired
    private MsgMapper msgMapper;

    public int addMsg(Message msg) {
        return msgMapper.addMsg(msg);
    }

    public List<HashMap<String, String>> getMsgBySnum(String snum) {
        return msgMapper.getMsgBySnum(snum);
    }

    public List<HashMap<String, String>> getMsgBySinstitute(String sinstitute) {
        return msgMapper.getMsgBySinstitute(sinstitute);
    }

    public List<HashMap<String, String>> getMsgAll() {
        return msgMapper.getMsgAll();
    }

    public int delMsg(String msgNum) {
        return msgMapper.delMsg(msgNum);
    }

    public int upMsg(Message msg){
        return msgMapper.upMsg(msg);
    }

    public int upAppeal(Message msg){
        return msgMapper.upAppeal(msg);
    }

}
