package vn.edu.hcmute.aloha.model;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.Serializable;
//Nguyễn Thị Yến Nhi: 12/11-18/11(tuần 13)

// đối tượng các liên hệ trong danh bạ
public class Contact implements Serializable {

    private Bitmap thumb;
    private String name;
    private String phone;

    public Contact(Bitmap thumb, String name, String phone) {
        this.thumb = thumb;
        this.name = name;
        this.phone = phone;
    }

    public Contact() {
    }

    public Bitmap getThumb() {
        return thumb;
    }

    public void setThumb(Bitmap thumb) {
        this.thumb = thumb;
    }

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

}
