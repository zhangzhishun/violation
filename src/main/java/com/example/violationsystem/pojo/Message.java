package com.example.violationsystem.pojo;

import org.springframework.boot.autoconfigure.domain.EntityScan;


public class Message {
    private String msgType;
    private String msgRemarks;//备注
    private String msgDate;
    private String msgNum;//处分编号
    private String snum;//学生学号
    private String tnum;//老师工号
    private String suggestion;//意见
    private String approvalStatus;//审批
    private String appealTime;//审批
    private String appealConntent;//审批

    public String getAppealTime() {
        return appealTime;
    }

    public void setAppealTime(String appealTime) {
        this.appealTime = appealTime;
    }

    public String getAppealConntent() {
        return appealConntent;
    }

    public void setAppealConntent(String appealConntent) {
        this.appealConntent = appealConntent;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    //无此空构造函数，将报错
    public Message() {
    }

    public Message(String msgType, String msgRemarks, String msgDate, String msgNum, String snum, String tnum) {
        this.msgType = msgType;
        this.msgRemarks = msgRemarks;
        this.msgDate = msgDate;
        this.msgNum = msgNum;
        this.snum = snum;
        this.tnum = tnum;
    }

    @Override
    public String  toString() {
        return "Message{" +
                "msgType='" + msgType + '\'' +
                ", msgRemarks='" + msgRemarks + '\'' +
                ", msgDate='" + msgDate + '\'' +
                ", msgNum='" + msgNum + '\'' +
                ", snum='" + snum + '\'' +
                ", tnum='" + tnum + '\'' +
                '}';
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgRemarks() {
        return msgRemarks;
    }

    public void setMsgRemarks(String msgRemarks) {
        this.msgRemarks = msgRemarks;
    }

    public String getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }

    public String getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(String msgNum) {
        this.msgNum = msgNum;
    }

    public String getSnum() {
        return snum;
    }

    public void setSnum(String snum) {
        this.snum = snum;
    }

    public String getTnum() {
        return tnum;
    }

    public void setTnum(String tnum) {
        this.tnum = tnum;
    }
}
