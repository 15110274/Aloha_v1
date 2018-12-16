package vn.edu.hcmute.aloha.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import vn.edu.hcmute.aloha.model.Friend;
import vn.edu.hcmute.aloha.model.ListFriend;

// lớp FriendDB
public final class FriendDB {
    private static FriendDBHelper mDbHelper = null;

    private FriendDB() {
    }

    private static FriendDB instance = null;

    public static FriendDB getInstance(Context context) {
        if (instance == null) {
            instance = new FriendDB();
            mDbHelper = new FriendDBHelper(context);
        }
        return instance;
    }

// hàm chèn thêm bạn
    public long addFriend(Friend friend) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_ID, friend.id);
        values.put(FeedEntry.COLUMN_NAME_NAME, friend.name);
        values.put(FeedEntry.COLUMN_NAME_EMAIL, friend.email);
        values.put(FeedEntry.COLUMN_NAME_ID_ROOM, friend.idRoom);
        values.put(FeedEntry.COLUMN_NAME_AVATA, friend.avata);

        return db.insert(FeedEntry.TABLE_NAME, null, values);
    }

// hàm add danh sách bạn
    public void addListFriend(ListFriend listFriend){
        for(Friend friend: listFriend.getListFriend()){// gọi lại hàm lấy danh sách bạn
            addFriend(friend);// gọi lại hàm thêm bạn
        }
    }
// hàm lấy danh sách bạn
    public ListFriend getListFriend() {
        ListFriend listFriend = new ListFriend();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery("select * from " + FeedEntry.TABLE_NAME, null);
            while (cursor.moveToNext()) {
                Friend friend = new Friend();
                friend.id = cursor.getString(0);
                friend.name = cursor.getString(1);
                friend.email = cursor.getString(2);
                friend.idRoom = cursor.getString(3);
                friend.avata = cursor.getString(4);
                listFriend.getListFriend().add(friend);
            }
            cursor.close();
        }catch (Exception e){
            return new ListFriend();
        }
        return listFriend;// trả về danh sách bạn
    }

    public void dropDB(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
    }

// gán các base column cho chuỗi
    public static class FeedEntry implements BaseColumns {
        static final String TABLE_NAME = "friend";
        static final String COLUMN_NAME_ID = "friendID";
        static final String COLUMN_NAME_NAME = "name";
        static final String COLUMN_NAME_EMAIL = "email";
        static final String COLUMN_NAME_ID_ROOM = "idRoom";
        static final String COLUMN_NAME_AVATA = "avata";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    // chuỗi tạo bảng
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry.COLUMN_NAME_ID + " TEXT PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_ID_ROOM + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_AVATA + TEXT_TYPE + " )";
    // chuỗi xóa bảng
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


    private static class FriendDBHelper extends SQLiteOpenHelper {
        static final int DATABASE_VERSION = 1;
        static final String DATABASE_NAME = "FriendChat.db";

        FriendDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        // hàm tạo
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        // hàm upgrade
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // cơ sở dữ liệu chỉ là bộ đệm cho csdl trực tuyến, nên muốn upgarde thì xóa cái cũ và tạo cái mới
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}
