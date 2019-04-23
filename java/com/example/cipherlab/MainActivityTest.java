package com.example.cipherlab;

import com.cipherlab.barcode.*;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivityTest extends Activity {
//    //初始化阅读器
    private IntentFilter filter;    //定义过滤器
    private ImageButton b1 = null;          //初始化扫码图片按钮
    ReaderManager m_RM = null;      //初始化阅读器管理对象
    @Override                       //重写方法加此注解，便于检查是否重写成功，不存在复写私有方法！
    protected void onCreate(Bundle savedInstanceState) {    //复写Activity父类中的方法
        super.onCreate(savedInstanceState);     //super关键字引用父类对象，this引用当前对象
        setContentView(R.layout.activity_unit_details); //编辑布局界面
        b1 = findViewById(R.id.image_btn_scan);    //引用图片按钮
        b1.setOnClickListener(new View.OnClickListener(){       //设置点击事件的监听器
            @Override public void onClick(View v){
                Log.i("onClick","被点击了");
                if(m_RM != null){
                    m_RM.SoftScanTrigger();    //软件扫描触发器
                }
            }
        });
        m_RM = ReaderManager.InitInstance(this);    //创立程序，程序初始化，创建窗口
        filter = new IntentFilter();
        filter.addAction(GeneralString.Intent_PASS_TO_APP);
        registerReceiver(myDataReceiver,filter);    //注册广播接收器
    }
    @Override
    protected void onDestroy(){
        //TODO AUTO-generated method stub
        super.onDestroy();
        unregisterReceiver(myDataReceiver);
    }
    private final BroadcastReceiver myDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(GeneralString.Intent_PASS_TO_APP)){
                // 从 intent 获取数据
                String sDataStr = intent.getStringExtra(GeneralString.BcReaderData);
                //Toast是安卓的消息提示框
                Toast.makeText(MainActivityTest.this,"Decode data is" + sDataStr,
                        Toast.LENGTH_SHORT).show();
            }
        }
    };
//    private void checkCameraPermission(){  //检查相机是否授权
//        if(ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
//            //如果没有授权,请求授权
//            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST_CALL_CAMERA);
//        }else{
//            callPDAScan("UTF-8");
//        }
//    }
    private void callPDAScan(String characterSet){
        //截取屏幕宽度

    }
}
