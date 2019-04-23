package com.example.cipherlab;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {

    private Button bt1;
    private Button bt2;
    private Button bt3;
    private EditText et1;
    private EditText et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e1 = et1.getText().toString();
                String e2 = et2.getText().toString();
                if (e1 == "" && e1 == null) {
                    Toast.makeText(MainActivity.this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
                }
                if (e2 == "" && e2 == null) {
                    Toast.makeText(MainActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                }
                if (e1 != "" && e1 != null && e2 != "" && e2 != null) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);

                    startActivity(intent);
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建打电话的意图
                Intent intent = new Intent();
                //设置拨打电话的动作
                intent.setAction(Intent.ACTION_CALL);
                //设置拨打电话的号码
                intent.setData(Uri.parse("tel:18856152017"));
                //开启打电话的意图
                startActivity(intent);
            }
        });
    }
}

