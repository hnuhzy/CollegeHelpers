package com.example.helpeachother;

import java.io.Serializable;

public class GoodsEntity implements Serializable {
    private int state;//是否接单
    private String infomation;//基本信息
    private String PositionFrom,PositionTo;//传送位置
    private int score;//悬赏积分
    private String RequestTime;//时间要求

    public GoodsEntity(){

    }

    public GoodsEntity(int state, String infomation, String positionFrom, String positionTo, int score, String requestTime) {
        this.state = state;
        this.infomation = infomation;
        PositionFrom = positionFrom;
        PositionTo = positionTo;
        this.score = score;
        RequestTime = requestTime;
    }

    public String toString(){
        return "GoodsEntity{"+
                "information"+infomation+'\''+
                '}';
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInfomation() {
        return infomation;
    }

    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }

    public String getPositionFrom() {
        return PositionFrom;
    }

    public void setPositionFrom(String positionFrom) {
        PositionFrom = positionFrom;
    }

    public String getPositionTo() {
        return PositionTo;
    }

    public void setPositionTo(String positionTo) {
        PositionTo = positionTo;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRequestTime() {
        return RequestTime;
    }

    public void setRequestTime(String requestTime) {
        RequestTime = requestTime;
    }
}
