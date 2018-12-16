package vn.edu.hcmute.aloha.fragment;

import android.content.ContentResolver;
import android.database.Cursor;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;



import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmute.aloha.R;
import vn.edu.hcmute.aloha.adapter.ContactAdapter;
import vn.edu.hcmute.aloha.model.Contact;
// fragment cho danh bạ
public class FragmentContacts extends Fragment {

    // ArrayList
     private ArrayList<Contact> contacts;
     private List<Contact> temp;
    // Contact List
     private ListView lvContact;
    // Cursor to load contacts list
     private Cursor phones;//, email;

    // Pop up
     private ContentResolver resolver;
     private ContactAdapter adapter;

    public static FragmentContacts newInstance() {
        return new FragmentContacts();
    }

    //Khởi tại các đối tượng
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);




        contacts = new ArrayList<Contact>();
        resolver = this.getActivity().getContentResolver();
        lvContact = rootView.<ListView>findViewById(R.id.lvContact);

        // kết nối tới csdl danh bạ trong máy
        phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " DESC");
        LoadContact loadContact = new LoadContact();
        loadContact.execute();

        return rootView;


    }

    // Load thông tin danh bạ từ máy
    class LoadContact extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            if (phones != null) {
                Log.e("count", "" + phones.getCount());
                if (phones.getCount() == 0) {
                    //Toast.makeText(getActivity(), "No contacts in your contact list.", Toast.LENGTH_LONG).show();
                }

                while (phones.moveToNext()) {
                    // Load dữ liệu danh bạ từ máy sau đó add vào List Contact
                    String id = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                    String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    Contact contact = new Contact();
                    contact.setName(name);
                    contact.setPhone(phoneNumber);
                    contacts.add(contact);
                }
            } else {
                Log.e("Cursor close 1", "----------------");
            }
            phones.close();
            return null;
        }
        //Hiển thị thông tin lên giao diện
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter = new ContactAdapter(contacts, getActivity());
            lvContact.setAdapter(adapter);

        }
    }
}
