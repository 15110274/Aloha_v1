package vn.edu.hcmute.aloha.model;
// Nguyễn Thị Yến Nhi 19/11-25-11(tuần 14)
// đối tượng Status để xem bạn bè có đang online

public class Status{
    public boolean isOnline;
    public long timestamp;

    public Status(){
        isOnline = false;
        timestamp = 0;
    }
}
