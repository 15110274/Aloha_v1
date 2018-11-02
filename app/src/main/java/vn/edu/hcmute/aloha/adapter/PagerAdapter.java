package vn.edu.hcmute.aloha.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vn.edu.hcmute.aloha.fragment.FragmentCall;
import vn.edu.hcmute.aloha.fragment.FragmentChat;
import vn.edu.hcmute.aloha.fragment.FragmentContacts;

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        Fragment frag=null;
        switch (i){
            case 0:
                frag = new FragmentChat();
                break;
            case 1:
                frag = new FragmentCall();
                break;
            case 2:
                frag = new FragmentContacts();
                break;
        }
        return frag;
    }


    @Override
    public int getCount() {

        return 3;
    }

    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "CHAT";
                break;
            case 1:
                title = "CALLS";
                break;
            case 2:
                title = "CONTACTS";
                break;
        }
        return title;
    }

}
