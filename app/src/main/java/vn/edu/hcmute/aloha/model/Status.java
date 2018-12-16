package vn.edu.hcmute.aloha.model;

// đối tượng Status để xem bạn bè có đang online

public class Status{
    public boolean isOnline;
    public long timestamp;

    public Status(){
        isOnline = false;
        timestamp = 0;
    }
}
