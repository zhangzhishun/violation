package com.example.violationsystem.service;

import com.example.violationsystem.mapper.AdmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmService {

    @Autowired
    private AdmMapper admMapper;
    public String getPassword(String admName){
        return admMapper.getPassword(admName);
    }
}
