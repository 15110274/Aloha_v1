package vn.edu.hcmute.aloha.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.hcmute.aloha.R;
import vn.edu.hcmute.aloha.model.CallDetails;
//adapter để đổ dữ liệu thông tin lịch sử cuộc gọi lên listView ở FragmentCall
public class CallLogAdapter extends BaseAdapter {
    public List<CallDetails> _callDetails;
    private ArrayList<CallDetails> arraylistCallDetails;
    Context _context;
    ViewHolder vCallLog;

    public CallLogAdapter(List<CallDetails> callDetails, Context context) {
        _callDetails = callDetails;
        _context = context;
        this.arraylistCallDetails = new ArrayList<>();
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

        vCallLog = new ViewHolder();

        vCallLog.avatar = view.<CircleImageView>findViewById(R.id.imvCallItAvar);
        vCallLog.name = view.<TextView>findViewById(R.id.txtNameItCall);
        vCallLog.duraion= view.<TextView>findViewById(R.id.txtDurationItCall);
        vCallLog.dayTime= view.<TextView>findViewById(R.id.txtDayTimeItCall);
        vCallLog.btnChat= view.<ImageButton>findViewById(R.id.btnChatItCall);

        CallDetails data = _callDetails.get(i);

        //người gọi không có trong danh bạ
        if(data.getName()!=null)
            vCallLog.name.setText(data.getName());
        else vCallLog.name.setText(data.getPhone());
        //set time
        vCallLog.duraion.setText(data.getDuration()+"s");
        //set type
        switch (data.getType())
        {
            case 1:{
                vCallLog.name.setText(vCallLog.name.getText().toString()+ "  ↙");
                vCallLog.name.setTextColor(Color.parseColor("#000000"));
                break;
            }
            case 2:{
                vCallLog.name.setText(vCallLog.name.getText().toString()+ "  ↗");
                vCallLog.name.setTextColor(Color.parseColor("#000000"));
                break;
            }
            case 3:{
                vCallLog.name.setTextColor(Color.parseColor("#f44336"));
                vCallLog.duraion.setText("0s");
                break;
            }
        }

        //set date
        vCallLog.dayTime.setText(data.getDayTime());

        vCallLog.avatar.setImageResource(R.drawable.default_avata);

        view.setTag(data);
        return view;
    }

    // Viewholder cho custem layout thông tin lịch sử cuộc gọi
    static class ViewHolder {
        TextView name,duraion,dayTime;
        CircleImageView avatar;
        ImageButton btnChat;
    }
}
