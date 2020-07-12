package com.example.helpeachother;

import android.widget.ImageView;

public class SkillEntity {
    private String Skilltype;
    private String Skillinfo;
    private String Skiller;
    private int SkillPic;
    private String SkillPhone;
    private String SkillTime;
    private String SkillPosition;
    private int SkillWorth;

    public SkillEntity(String skillinfo, int skillPic) {
        Skillinfo = skillinfo;
        SkillPic = skillPic;
    }

    public SkillEntity(){}

    public SkillEntity(String skilltype, String skillinfo, String skiller, int skillPic, String skillPhone, String skillTime, String skillPosition, int skillWorth) {
        Skilltype = skilltype;
        Skillinfo = skillinfo;
        Skiller = skiller;
        SkillPic = skillPic;
        SkillPhone = skillPhone;
        SkillTime = skillTime;
        SkillPosition = skillPosition;
        SkillWorth = skillWorth;
    }


    public String getSkilltype() {
        return Skilltype;
    }

    public void setSkilltype(String skilltype) {
        Skilltype = skilltype;
    }

    public String getSkillinfo() {
        return Skillinfo;
    }

    public void setSkillinfo(String skillinfo) {
        Skillinfo = skillinfo;
    }

    public String getSkiller() {
        return Skiller;
    }

    public void setSkiller(String skiller) {
        Skiller = skiller;
    }

    public int getSkillPic() {
        return SkillPic;
    }

    public void setSkillPic(int skillPic) {
        SkillPic = skillPic;
    }

    public String getSkillPhone() {
        return SkillPhone;
    }

    public void setSkillPhone(String skillPhone) {
        SkillPhone = skillPhone;
    }

    public String getSkillTime() {
        return SkillTime;
    }

    public void setSkillTime(String skillTime) {
        SkillTime = skillTime;
    }

    public String getSkillPosition() {
        return SkillPosition;
    }

    public void setSkillPosition(String skillPosition) {
        SkillPosition = skillPosition;
    }

    public int getSkillWorth() {
        return SkillWorth;
    }

    public void setSkillWorth(int skillWorth) {
        SkillWorth = skillWorth;
    }
}
