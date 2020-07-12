package com.example.helpeachother;

public class LoseEntity {
    private int state;//失物或拾物
    private String information;//具体信息
    private String Position;
    private String score;//悬赏积分或钱
    private String PhoneNum;//联系方式
    private int LoseImageId;

    public int getLoseImageId() {
        return LoseImageId;
    }

    public void setLoseImageId(int loseImageId) {
        this.LoseImageId = loseImageId;
    }



    public LoseEntity(int state, String information, String position, String  score, String phoneNum,int loseImageId) {
        this.state = state;
        this.information = information;
        Position = position;
        this.score = score;
        PhoneNum = phoneNum;
        this.LoseImageId = loseImageId;
    }
    public LoseEntity(String position,int loseImageId){
        this.Position = position;
        this.LoseImageId = loseImageId;
    }
    public LoseEntity(){}

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String  getScore() {
        return score;
    }

    public void setScore(String  score) {
        this.score = score;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }
}
