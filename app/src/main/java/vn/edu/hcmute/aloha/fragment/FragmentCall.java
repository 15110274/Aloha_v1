package vn.edu.hcmute.aloha.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmute.aloha.R;
import vn.edu.hcmute.aloha.adapter.CallLogAdapter;
import vn.edu.hcmute.aloha.model.CallDetails;

public class FragmentCall extends Fragment {

    // ArrayList
    private ArrayList<CallDetails> callDetails;
    private List<CallDetails> temp;
    // Contact List
    private ListView lvCallDetail;
    // Cursor to load contacts list
    private Cursor cursor;//, email;

    // Pop up
    private ContentResolver resolver;
    private CallLogAdapter adapter;


    public static FragmentCall newInstance() {
        return new FragmentCall();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call, container, false);
        callDetails = new ArrayList<CallDetails>();
        resolver = this.getActivity().getContentResolver();
        lvCallDetail = view.<ListView>findViewById(R.id.lvCall);

        cursor = getActivity().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        FragmentCall.LoadCallLog loadCallLog = new FragmentCall.LoadCallLog();
        loadCallLog.execute();

        return view;
    }

    public class LoadCallLog extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            if (cursor != null) {
                Log.e("count", "" + cursor.getCount());
                if (cursor.getCount() == 0) {
                    //Toast.makeText(getActivity(), "No call log list.", Toast.LENGTH_LONG).show();
                }
                //cursor.moveToLast();
//
                while (cursor.moveToNext()) {
                    //Bitmap bit_thumb = null;
                    //String id = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                    String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                    String phone = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                    String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));
                    String dayTime = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
                    int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
//                    String image_thumb = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI));
//                    try {
//                        if (image_thumb != null) {
//                            bit_thumb = MediaStore.Images.Media.getBitmap(resolver, Uri.parse(image_thumb));
//                        } else {
//                            Log.e("No Image Thumb", "--------------");
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                    CallDetails callDetail = new CallDetails();
//                    //contact.setThumb(bit_thumb);
                    callDetail.setName(name);
                    callDetail.setPhone(phone);
                    callDetail.setType(type);
                    callDetail.setDuration(duration);
                    callDetail.setDayTime(dayTime);
//
                    callDetails.add(callDetail);
                }
            } else {
                Log.e("Cursor close 1", "----------------");
            }
            cursor.close();
            return null;
        }
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
