package com.nsyw.messagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nsyw.messagedemo.message.MessageLooper;
import com.nsyw.messagedemo.message.MessageManager;

public class MainActivity extends AppCompatActivity {

    private TextView mNicknameTv;
    private Button mGoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNicknameTv = findViewById(R.id.tv_nickname);
        mGoBtn = findViewById(R.id.btn_go);
        mGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserInfoActivity.class));
            }
        });
        MessageManager.getInstance().registerMessageReceiver(this, "user_info_change", new MessageLooper.OnMessageListener() {
            @Override
            public void onMessage(Message msg) {
                if (msg != null) {
                    String nickname = msg.getData().getString("nickname", "");
                    mNicknameTv.setText(nickname);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MessageManager.getInstance().unRegisterMessageReceiver(this);
    }
}
