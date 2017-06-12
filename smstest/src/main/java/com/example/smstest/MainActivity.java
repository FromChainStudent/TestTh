package com.example.smstest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

import static android.R.attr.data;

public class MainActivity extends Activity {

    @InjectView(R.id.et_name)
    EditText etName;
    @InjectView(R.id.btn_sms)
    Button btnSms;
    @InjectView(R.id.et_sms)
    EditText etSms;
    @InjectView(R.id.btn_sma)
    Button btnSma;

    private String phone;
    private String code;
    private int i = 60;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(MainActivity.this, "短信已发送", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(runnable, 1000);
                    break;
                case 1:
                    Toast.makeText(MainActivity.this, "短信验证成功", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    btnSma.setText(i + "秒后重新发送");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SMSSDK.initSDK(this, "1e509a2364c12", "b00a212ebc9ff95ff33079111f2aa0bb");
        setContentView(R.layout.login_main);
        ButterKnife.inject(this);

        SMSSDK.registerEventHandler(eventHandler);
        SMSSDK.getSupportedCountries();
    }

    @OnClick({R.id.btn_sms, R.id.btn_sma})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sms:
                phone = etName.getText().toString().trim();
                code = etSms.getText().toString().trim();
                if (phone.isEmpty()) {
                    Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show();
                } else {
                    if (etSms.getText().toString().trim().isEmpty()) {
                        Toast.makeText(this, "请填写验证码", Toast.LENGTH_SHORT).show();
                    } else {
                        SMSSDK.submitVerificationCode("+86", phone, code);
                    }
                }
                break;
            case R.id.btn_sma:
                phone = etName.getText().toString().trim();
                if (etName.getText().toString().trim().isEmpty()) {
                    Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show();
                } else {
                    if (etSms.getText().toString().trim().isEmpty()) {
                        SMSSDK.getVerificationCode("+86", phone, new OnSendMessageHandler() {
                            @Override
                            public boolean onSendMessage(String s, String s1) {
                                return false;
                            }
                        });
//                        handler.sendEmptyMessageDelayed(0, 3000);17600919537
                    } else {
                        etSms.setText("");
                    }
                }
                break;
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (i > 0) {
                i--;
                handler.sendEmptyMessage(2);
                handler.postDelayed(runnable, 1000);
                btnSma.setClickable(false);
            } else if (i == 0) {
                i = 60;
                btnSma.setClickable(true);
                btnSma.setText("重新发送");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    private EventHandler eventHandler = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    HashMap<String, Object> map = (HashMap<String, Object>) data;
                    String phone1 = (String) map.get("phone");
                    if (phone1.equals(phone)) {
                        handler.sendEmptyMessage(1);
                    }
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    handler.sendEmptyMessage(0);
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };
}
