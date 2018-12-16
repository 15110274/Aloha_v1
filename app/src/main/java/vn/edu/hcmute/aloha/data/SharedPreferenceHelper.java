package vn.edu.hcmute.aloha.data;

import android.content.Context;
import android.content.SharedPreferences;

import vn.edu.hcmute.aloha.model.User;
//Nguyễn Dương Văn Khoa 26/11-2-12(tuần 15)
// dùng SharedPreference để lưu trữ dữ liệu

public class SharedPreferenceHelper {
    private static SharedPreferenceHelper instance = null;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    private static String SHARE_USER_INFO = "userinfo";
    private static String SHARE_KEY_NAME = "name";
    private static String SHARE_KEY_EMAIL = "email";
    private static String SHARE_KEY_AVATA = "avata";
    private static String SHARE_KEY_UID = "uid";


    private SharedPreferenceHelper() {}
// lấy đối tượng, thông tin của user
    public static SharedPreferenceHelper getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferenceHelper();
            preferences = context.getSharedPreferences(SHARE_USER_INFO, Context.MODE_PRIVATE);
            editor = preferences.edit();
        }
        return instance;
    }
//hàm lưu thông tin user
    public void saveUserInfo(User user) {
        editor.putString(SHARE_KEY_NAME, user.name);
        editor.putString(SHARE_KEY_EMAIL, user.email);
        editor.putString(SHARE_KEY_AVATA, user.avata);
        editor.putString(SHARE_KEY_UID, StaticConfig.UID);
        editor.apply();
    }
// hàm lấy thông tin user
    public User getUserInfo(){
        String userName = preferences.getString(SHARE_KEY_NAME, "");
        String email = preferences.getString(SHARE_KEY_EMAIL, "");
        String avatar = preferences.getString(SHARE_KEY_AVATA, "default");

        User user = new User();
        user.name = userName;
        user.email = email;
        user.avata = avatar;

        return user;
    }

    public String getUID(){
        return preferences.getString(SHARE_KEY_UID, "");
    }

}
