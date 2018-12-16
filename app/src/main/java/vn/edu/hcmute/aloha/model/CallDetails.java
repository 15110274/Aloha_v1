package vn.edu.hcmute.aloha.model;

import java.io.Serializable;
//Nguyễn Thị Yến Nhi 12/11-18/11(tuần 13)
// Tạo đối tượng CallDetails để lưu chi tiết cuộc gọi

public class CallDetails implements Serializable {
    private String name;
    private String phone;
    private String duration;
    private int type;
    private String dayTime;


    // các constructor
    public CallDetails(String name, String phone, String duration, int type, String dayTime) {
        this.name = name;
        this.phone = phone;
        this.duration = duration;
        this.type = type;
        this.dayTime = dayTime;
    }


    public CallDetails() {
    }

    // các getter setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }
}
