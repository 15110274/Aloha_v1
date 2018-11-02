package vn.edu.hcmute.aloha.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmute.aloha.R;
import vn.edu.hcmute.aloha.model.CallDetails;

public class CallLogAdapter extends BaseAdapter {
    public List<CallDetails> _callDetails;
    private ArrayList<CallDetails> arraylistCallDetails;
    Context _context;
    ViewHolder vCallLog;

    public CallLogAdapter(List<CallDetails> callDetails, Context context) {
        _callDetails = callDetails;
        _context = context;
        this.arraylistCallDetails = new ArrayList<CallDetails>();
        this.arraylistCallDetails.addAll(_callDetails);

    }
    @Override
    public int getCount() {
        return _callDetails.size();
    }

    @Override
    public Object getItem(int i) {
        return _callDetails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater li = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.call_item, null);
            Log.e("Inside", "here--------------------------- In view1");
        } else {
            view = convertView;
            Log.e("Inside", "here--------------------------- In view2");
        }

        vCallLog = new CallLogAdapter.ViewHolder();

        //v.avatar = view.<ImageView>findViewById(R.id.imvAvatar);
        vCallLog.name = view.<TextView>findViewById(R.id.txtNameItCall);
        //vCallLog.type = view.<TextView>findViewById(R.id.txtTypeItCall);
        vCallLog.duraion= view.<TextView>findViewById(R.id.txtDurationItCall);
        vCallLog.dayTime= view.<TextView>findViewById(R.id.txtDayTimeItCall);
//        vCallLog.btnChat= view.<ImageButton>findViewById(R.id.btnChatItContact);

        final CallDetails data = (CallDetails) _callDetails.get(i);

        //người gọi không có trong danh bạ
        if(data.getName()!=null)
            vCallLog.name.setText(data.getName());
        else vCallLog.name.setText(data.getPhone());
        vCallLog.duraion.setText(data.getDuration()+"s");
        switch (data.getType())
        {
            case 1:{
                vCallLog.name.setText(vCallLog.name.getText().toString()+ "  ↙");
                break;
            }
            case 2:{
                vCallLog.name.setText(vCallLog.name.getText().toString()+ "  ↗");
                break;
            }
            case 3:{
                vCallLog.name.setTextColor(Color.parseColor("#f44336"));
                break;
            }
            default:{

            }

        }

        vCallLog.dayTime.setText(data.getDayTime());

        // Set image if exists
//        try {
//
//            if (data.getThumb() != null) {
//                v.avatar.setImageBitmap(data.getThumb());
//            } else {
//                v.avatar.setImageResource(R.drawable.avatar);
//            }
//            // Seting round image
//            Bitmap bm = BitmapFactory.decodeResource(view.getResources(), R.drawable.avatar); // Load default image
//            roundedImage = new RoundImage(bm);
//            v.avatar.setImageDrawable(roundedImage);
//        } catch (OutOfMemoryError e) {
//            // Add default picture
//            v.avatar.setImageDrawable(this._c.getDrawable(R.drawable.avatar));
//            e.printStackTrace();
//        }
//
//        Log.e("Image Thumb", "--------------" + data.getThumb());

        view.setTag(data);
        return view;
    }

    public class ViewHolder {
        TextView name,duraion,dayTime;
        //ImageButton btnChat;
    }
}
