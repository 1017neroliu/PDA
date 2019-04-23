package com.example.cipherlab;

import com.cipherlab.barcode.*;
import com.cipherlab.barcode.decoder.KeyboardEmulationType;
import com.cipherlab.barcode.decoder.OutputEnterChar;
import com.cipherlab.barcode.decoder.OutputEnterWay;
import com.cipherlab.barcode.decoderparams.Codabar;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MyFragment extends Activity {
    //创建ReaderManager实例
//    private ReaderManager mReaderManager;
//    mReaderManager = ReaderManager.InitInstance(this);
    /**
     * 1.2
     * ReaderManager的方法
     *
     * GetActive()获取阅读器的状态，返回的是Boolean值
     * 示例：
     * boolean bRet = mReaderManager.GetActive();
     *     if(bRet == false){
     *          ClResult clRet = mReaderManager.SetActive();
     *          如果成功返回 ClResult.S_OK，否则返回ClResult.S_ERR
     *      }
     * SetActive()
     * GetReaderType()获取阅读器的类型
     * 示例：
     * BcReaderType myReaderType = mReaderManager.GetReaderType();
     *      返回阅读器的类型
     *
     * ResetReaderToDefault()
     *
     * mReaderManager实例的方法有     Release()释放资源
     *                                InitInstance()初始化
     *                                ResetReaderToDefault()重置阅读器默认值
     * 1.3
     * 获取数据
     * 设置附加到解码条形码上的值
     * 参数：  showCodeType，条码类型
     *         szPrefixCode，前缀代码，如果值为0，就没有前缀代码
     *         DecodeData，条码解码数据
     *         szSuffixCode，后缀代码，同前缀代码
     *         showCodeLen，解码的条码长度，不包括前后缀代码
     *
     * Get_ReaderOutputConfiguration()获取当前输出格式，设置默认值用“*”表示
     *
     * 示例：
     * ReaderOutputConfiguration settings = new ReaderOutputConfiguration();
     * mReaderManager.Get_ReaderOutputConfiguration(settings);
     *
     * Set_ReaderOutputConfiguration()
     * KeyboardEmulationType enableKeyboardEmulation 是否将数据模拟化输入文本
     * 示例：
     * ReaderOutputConfiguration settings = new ReaderOutputConfiguration();
     * settings.enableKeyboardEmulation = KeyboardEmulationType.InputMethod;
     * settings.autoEnterWay = OutputEnterWay.SuffixData;解码后是否增加字符
     * settings.autoEnterChar = OutputEnterChar.Return;增加的字符的值
     * settings.showCodeLen = Enable_State.TRUE;
     * settings.showCodeType = Enable_State.TRUE;
     * settings.szPrefixCode = "PreStr";
     * settings.szSuffixCode = "SufStr";
     * settings.useDelim = ':';
     * settings.szCharsetName = 'shift_JIS';
     * settings.clearPreviousData = Enable_State.TRUE;
     * mReaderManager.Set_ReaderOutputConfiguration(settings);
     *
     * SoftScanTrigger()模拟物理按键
     * 1.注册字符串
     * 2.接收字符串
     * 3.从intent中获取数据
     *
     * Get_BarcodeServiceVer
     * String ver = mReaderManager.Get_BarcodeServiceVer();获得阅读器服务版本
     *
     * GetBarcodeAPIVersion
     * String ver = mReaderManager.GetBarcodeAPIVersion();获得阅读器API版本
     *
     * 1.4  操作状态指示
     * 通过发出声音或者震动来反馈接收数据的指示
     *
     * Get_NotificationParams
     * NotificationParams();
     * 参数有震动时间，声音的类型，或者LED灯光闪烁
     *
     * 示例：
     * NotificationParams settings = new NotificationParams();
     * mReaderManager.Get_NotificationParams(settings);
     *
     * Set_NotificationParams
     * NotificationParams settings = new NotificationParams();
     * settings.enableReaderBeep = Enable_state.TRUE;设置声音
     * settings.enableVibrator = Enable_state.TRUE;设置震动
     * settings.ledDuration = 500;单位ms           设置LED指示灯
     * settings.vibrationCounter = 1;
     * mReaderManager.Get_NotificationParams(settings);
     *
     * 1.5 配置扫描引擎
     * Get_Decoders_Status 获取阅读器是否能够解码，返回的状态
     *
     * Decoders settings = new Decoders();
     * mReaderManager.Get_Decoders_Status(settings);
     * if(Enable_State.NotSupport == settings.enableAustralianPostal){
     *
     * }
     *
     * Set_Decoders_Status
     *
     * Decoders settings = new Decoders();
     * settings.enableAustralianPostal = Enable_State.FALSE;
     * settings.enableAztec = Enable_State.FALSE;
     * settings.enableChinese2Of5 = Enable_State.FALSE;
     * settings.enableCodabar = Enable_State.FALSE;
     * settings.enableCode11 = Enable_State.FALSE;
     * settings.enableCode128 = Enable_State.FALSE;
     * settings.enableCode39 = Enable_State.FALSE;
     * settings.enableCode93 = Enable_State.FALSE;
     *
     * if(ClResult.S_ERR == mReaderManager.Set_Decoders_Status(settings))
     * Toast.makeText(this,"Set_Decoders_Status was failed",Toast.LENGTH_SHORT).show();
     * else
     * Toast.makeText(this,"Set_Decoders_Status was successful",Toast.LENGTH_SHORT).show();
     *
     * Get_UserPreferences  获取用户偏好设置
     *
     * UserPreference settings = new UserPreference();
     * mReaderManager.Get_UserPreferences(settings);
     * if(Enable_State.NotSupport == settings.displayMode){
     *
     * }
     *
     * Set_UserPreferences  设置用户偏好设置
     * UserPreference settings = new UserPreference();
     * settings.addonSecurityLevel = 2;设置安全等级
     * settings.negativeBarcodes = InverseType.AutoDetect;
     * settings.scanAngle = ScanAngleType.Wide;
     *
     * if(ClResult.S_ERR == mReaderManager.Set_UserPreferences(settings))
     * Toast.makeText(this,"Set_UserPreferences was failed",Toast.LENGTH_SHORT).show();
     * else
     * Toast.makeText(this,"Set_UserPreferences was successful",Toast.LENGTH_SHORT).show();
     *
     * 1.5.2    符号设置
     * Get_Symbology    通过实现Symbology接口实例，获取Symbology
     *
     * 共有38种Symbology
     *
     * Codabar settings = new Codabar();
     * if(ClResult.ERR_NotSupport == mReaderManager.Get_Symbology(settings)){
     *      //验证是否支持该符号
     * }
     * //如果禁用就启用他
     * if(Codabar.enable == Enable_State.FALSE){
     *      Codabar.enable == Enable_State.TRUE;
     * }
     *
     * Set_Symbology
     * Codabar settings = new Codabar();
     * Codabar.enable == Enable_State.TRUE;
     * if(ClResult.S_ERR == mReaderManager.Set_Symbology(settings))
     * Toast.makeText(this,"Set_Symbology was failed",Toast.LENGTH_SHORT).show();
     * else
     * Toast.makeText(this,"Set_Symbology was successful",Toast.LENGTH_SHORT).show();
     *
     * 1.6  重置阅读器
     * ResetReaderToDefault
     * if(ClResult.S_ERR == mReaderManager.ResetReaderToDefault()){
     *      Toast.makeText(this,"ResetReaderToDefault was failed",Toast.LENGTH_SHORT).show();
     * }else{
     *     Toast.makeText(this,"ResetReaderToDefault was done!",Toast.LENGTH_SHORT).show();
     * }
     *
     * 1.7  intent
     *  阅读器服务连接
     *
     *  软件触发器
     *
     *  硬件扫描key
     *
     *  回调函数，设置阅读器回调函数
     *  interface ReaderCallback{
     *      void onDecodeComplete(in String strDecodeData);
     *  }
     *
     */






















    //初始化阅读器
    private IntentFilter filter;    //定义过滤器
    ImageButton b1 = null;          //初始化扫码图片按钮
    ReaderManager m_RM = null;      //初始化阅读器管理对象
    @Override                       //重写方法加此注解，便于检查是否重写成功，不存在复写私有方法！
    protected void onCreate(Bundle savedInstanceState) {    //复写Activity父类中的方法
        super.onCreate(savedInstanceState);     //super关键字引用父类对象，this引用当前对象
        setContentView(R.layout.activity_unit_details); //编辑布局界面
        b1 = findViewById(R.id.image_btn_scan);    //引用图片按钮
        b1.setOnClickListener(new View.OnClickListener(){       //设置点击事件的监听器

            @Override public void onClick(View v){
//                Log.v("onClick","onClick()");相当于System.out.println(）
                if(m_RM != null){
                    m_RM.SoftScanTrigger();    //软件扫描触发器
                }

            }
        });
        m_RM = ReaderManager.InitInstance(this);    //创立程序，程序初始化，创建窗口
        filter = new IntentFilter();
        filter.addAction(GeneralString.Intent_SOFTTRIGGER_DATA);
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
            if(intent.getAction().equals(GeneralString.Intent_SOFTTRIGGER_DATA)){
                // 从 intent 获取数据
                String sDataStr = intent.getStringExtra(GeneralString.BcReaderData);
                //Toast是安卓的消息提示框
                Toast.makeText(MyFragment.this,"Decode data is" + sDataStr,
                        Toast.LENGTH_SHORT).show();
            }
        }
    };
}
