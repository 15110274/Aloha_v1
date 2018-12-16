package vn.edu.hcmute.aloha.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.edu.hcmute.aloha.R;
import vn.edu.hcmute.aloha.adapter.CallLogAdapter;
import vn.edu.hcmute.aloha.model.CallDetails;
// Fragment hiển thị lịch sử cuộc gọi

public class FragmentCall extends Fragment {

    // ArrayList lưu danh sách chi tiết cuộc gọi
    private ArrayList<CallDetails> callDetails;
    //private List<CallDetails> temp;
    // CallDetail ListView hiển thị danh sách lịch sử cuộc gọi
    private ListView lvCallDetail;
    // Cursor to load calllog list
    private Cursor cursor;

    // Pop up
    private ContentResolver resolver;
    private CallLogAdapter adapter;


    public static FragmentCall newInstance() {
        return new FragmentCall();
    }

    //Khởi tại Fragment
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call, container, false);
        callDetails = new ArrayList<CallDetails>();
        resolver = this.getActivity().getContentResolver();
        lvCallDetail = view.<ListView>findViewById(R.id.lvCall);
        cursor = getActivity().getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                null, null,CallLog.Calls.DATE+ " DESC");
        FragmentCall.LoadCallLog loadCallLog = new FragmentCall.LoadCallLog();
        loadCallLog.execute();


        return view;

    }

    //Load dữ liệu thông tin cuộc gọi tử điện thoại đổ lên list callDetails
    public class LoadCallLog extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            if (cursor != null) {
                Log.e("count", "" + cursor.getCount());
                if (cursor.getCount() == 0) {
                    //Toast.makeText(getActivity(), "No call log list.", Toast.LENGTH_LONG).show();
                }

                // đưa dữ liệu đã lấy được từ máy vào List CallDetail
                while (cursor.moveToNext()){
                    try{
                        String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                        String phone = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                        String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));
                        int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));

                        // Convert to DateFormat
                        int secondindex = cursor.getColumnIndex(CallLog.Calls.DATE);
                        long seconds=cursor.getLong(secondindex);
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        String dateString = formatter.format(new Date(seconds));




                        CallDetails callDetail = new CallDetails();
                        callDetail.setName(name);
                        callDetail.setPhone(phone);
                        callDetail.setType(type);
                        callDetail.setDuration(duration);
                        callDetail.setDayTime(dateString);

                        callDetails.add(callDetail);
                    }
                    catch (Exception e){

                    }
                }
            } else {
                Log.e("Cursor close 1", "----------------");
            }
            cursor.close();
            return null;
        }
        // set adapter cho LvCalldetail
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter = new CallLogAdapter(callDetails, getActivity());
            lvCallDetail.setAdapter(adapter);

            // Select item on listclick
//            lvCallDetailt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                    Log.e("search", "here---------------- listener");
//
//                    CallDetails data = callDetails.get(i);
//                }
//            });
        }
    }


}
