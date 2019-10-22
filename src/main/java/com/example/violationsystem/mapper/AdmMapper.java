package com.example.violationsystem.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface AdmMapper {
    String getPassword(String admName);
}
