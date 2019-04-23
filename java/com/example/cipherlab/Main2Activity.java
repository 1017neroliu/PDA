package com.example.cipherlab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Main2Activity extends Activity {
    private Button btn1;
    private EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.bt1);
        et1 = (EditText) findViewById(R.id.et1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content ="忘记密码";//短信内容
                String phone = et1.getText().toString();;//电话号码
                if (phone.length() != 11){
                    Toast.makeText(Main2Activity.this,"请输入正确的手机号码！",Toast.LENGTH_SHORT).show();
                }else {
                    boolean flag = false;
                    for (int i = 0;i< phone.length();i++){
                        char num = phone.charAt(i);
                        if (num >= '0' && num <= '9') {
                            flag = true;
                        }else {
                            flag = false;
                            break;
                        }
                    }
                    if (flag){
                        SmsManager sm = SmsManager.getDefault();
                        sm.sendTextMessage("18856152017",null,content+":"+phone,null,null);
                        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(Main2Activity.this,"请输入正确的手机号码！",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}

