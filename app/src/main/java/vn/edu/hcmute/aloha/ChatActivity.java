package vn.edu.hcmute.aloha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class ChatActivity extends AppCompatActivity {

    ImageButton btnBack,btnCall;
    //Button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        //btnBack= this.<ImageButton>findViewById(R.id.btnBackAcChat);
        //btnCall= this.<ImageButton>findViewById(R.id.btnCallAcChat);

    }
}
