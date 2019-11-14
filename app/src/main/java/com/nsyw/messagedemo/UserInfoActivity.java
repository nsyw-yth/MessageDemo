package com.nsyw.messagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nsyw.messagedemo.message.MessageManager;

public class UserInfoActivity extends AppCompatActivity {

    private EditText mNicknameEt;
    private Button mSureBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mNicknameEt=findViewById(R.id.et_nickname);
        mSureBtn=findViewById(R.id.btn_sure);
        mSureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message=new Message();
                Bundle bundle=new Bundle();
                bundle.putString("nickname",mNicknameEt.getText().toString());
                message.setData(bundle);
                MessageManager.getInstance().sendMessage("user_info_change",message);
                UserInfoActivity.this.finish();
            }
        });

    }
}
