package com.example.violationsystem.pojo;

import org.springframework.stereotype.Repository;

@Repository
public class Student {
    private String sname;
    private String snum;
    private String sex;
    private String sinstitute;
    private String phone;
    private String idCard;
    private String password;

    public Student(String sname, String snum, String sex, String sinstitute, String phone, String idCard, String password) {
        this.sname = sname;
        this.snum = snum;
        this.sex = sex;
        this.sinstitute = sinstitute;
        this.phone = phone;
        this.idCard = idCard;
        this.password = password;
    }

    public Student() {
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSnum() {
        return snum;
    }

    public void setSnum(String snum) {
        this.snum = snum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSinstitute() {
        return sinstitute;
    }

    public void setSinstitute(String sinstitute) {
        this.sinstitute = sinstitute;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sname='" + sname + '\'' +
                ", snum='" + snum + '\'' +
                ", sex='" + sex + '\'' +
                ", sinstitute='" + sinstitute + '\'' +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
