package vn.edu.hcmute.aloha.model;

import java.util.ArrayList;

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
