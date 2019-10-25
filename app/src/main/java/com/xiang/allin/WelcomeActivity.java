package com.xiang.allin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xiang.allin.login.LoginActivity;
import com.xiang.allin.login.LoginPresenter;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    int time = 4;
    private TextView texttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        texttime.setText("倒计时:"+time);
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (time > 1) {
                            time--;
                            texttime.setText("倒计时:"+time);
                        } else {
                            timer.cancel();
                            texttime.setVisibility(View.GONE);
                            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                });

            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    private void initView() {
        texttime = (TextView) findViewById(R.id.texttime);
    }
}
