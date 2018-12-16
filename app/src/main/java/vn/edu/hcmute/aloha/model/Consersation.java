package vn.edu.hcmute.aloha.model;

import java.util.ArrayList;
// Nguyễn Trần Tấn Phát 19/11-25-11(tuần 14)
// danh sách các tin nhắn

public class Consersation {
    private ArrayList<Message> listMessageData;
    public Consersation(){
        listMessageData = new ArrayList<>();
    }

    public ArrayList<Message> getListMessageData() {
        return listMessageData;
    }
}
