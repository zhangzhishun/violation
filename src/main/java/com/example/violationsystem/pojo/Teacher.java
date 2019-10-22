package com.example.violationsystem.pojo;

public class Teacher {
    private String tname;
    private String tnum;
    private String tinstitute;
    private String sex;
    private String password;

    public Teacher() {
    }

    public Teacher(String tname, String tnum, String tinstitute, String sex, String password) {
        this.tname = tname;
        this.tnum = tnum;
        this.tinstitute = tinstitute;
        this.sex = sex;
        this.password = password;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTnum() {
        return tnum;
    }

    public void setTnum(String tnum) {
        this.tnum = tnum;
    }

    public String getTinstitute() {
        return tinstitute;
    }

    public void setTinstitute(String tinstitute) {
        this.tinstitute = tinstitute;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tname='" + tname + '\'' +
                ", tnum='" + tnum + '\'' +
                ", tinstitute='" + tinstitute + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
