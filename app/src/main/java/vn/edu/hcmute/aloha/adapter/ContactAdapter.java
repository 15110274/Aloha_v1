package vn.edu.hcmute.aloha.adapter;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.hcmute.aloha.R;
import vn.edu.hcmute.aloha.model.Contact;
//adapter để đổ dữ liệu danh bạ lên listView ở FragmentContacts
public class ContactAdapter extends BaseAdapter {
    public List<Contact> _contact;
    private ArrayList<Contact> arraylistContact;
    Context _contextContact;
    ViewHolder v;




        public ContactAdapter(List<Contact> contacts, Context context) {
        _contact = contacts;
        _contextContact = context;
        this.arraylistContact = new ArrayList<Contact>();
        this.arraylistContact.addAll(_contact);

    }

    @Override
    public int getCount() {
        return _contact.size();
    }

    @Override
    public Object getItem(int i) {
        return _contact.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            LayoutInflater li = (LayoutInflater) _contextContact.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.contact_item, null);
            Log.e("Inside", "here--------------------------- In view1");
        } else {
            view = convertView;
            Log.e("Inside", "here--------------------------- In view2");
        }

        //khai báo ViewHolder
        v = new ViewHolder();

        v.avatar = view.<CircleImageView>findViewById(R.id.imvAvatar);
        v.name = view.<TextView>findViewById(R.id.txtNameItContact);
        v.phone = view.<TextView>findViewById(R.id.txtPhoneItContact);
        v.btnChat= view.<ImageButton>findViewById(R.id.btnChatItContact);
        v.btnCall= view.<ImageButton>findViewById(R.id.btnCallItContact);

        final Contact data = (Contact) _contact.get(i);

        v.name.setText(data.getName());
        v.phone.setText(data.getPhone());


        /*// Set check box listener android
        v.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked()) {
                    data.setCheckedBox(true);
                  } else {
                    data.setCheckedBox(false);
                }
            }
        });*/

        //sự kiện bấm nút gọi
        v.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final Animation buttonclick= AnimationUtils.loadAnimation(_context,R.anim.anim_alpha);
                //v.startAnimation(buttonclick);
                xuLyCall(data.getPhone());

            }
        });
        v.avatar.setImageResource(R.drawable.default_avata);
        v.btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChat();
            }
        });


        view.setTag(data);
        return view;
    }

    private void xuLyChat() {
//        Intent intent=new Intent(this._contextContact,ChatActivity.class);
//        this._contextContact.startActivity(intent);
    }

    //hàm xử lý gọi
    private void xuLyCall(String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:" + phone);
        intent.setData(uri);
        if (ActivityCompat.checkSelfPermission(this._contextContact, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this._contextContact.startActivity(intent);
    }

    //ViewHolder cho custom layout danh bạ
    static class ViewHolder {
        CircleImageView avatar;
        TextView name, phone;
        ImageButton btnChat,btnCall;
    }
}
