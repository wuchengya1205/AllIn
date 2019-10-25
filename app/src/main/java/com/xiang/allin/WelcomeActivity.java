package com.xiang.allin;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xiang.allin.login.LoginActivity;
import com.xiang.allin.login.LoginPresenter;
import com.xiang.allin.view.CountdownDrawable;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView countdown;
    private CountdownDrawable mCdDrawable;
    private Animator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        if (animator != null){
            animator.cancel();
        }
        mCdDrawable = new CountdownDrawable(getResources().getDimensionPixelSize(R.dimen.dp_4), getResources().getColor(R.color.dark_grey), getResources().getColor(R.color.cheng), getResources().getColor(R.color.holo_green_light), 3, getResources().getColor(R.color.colorWhite));
        countdown.setImageDrawable(mCdDrawable);
        animator = prepareAnimator();
        animator.start();
    }

    private void goActivity() {
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        finish();
    }

    private void initView() {
        countdown = (ImageView) findViewById(R.id.countdown);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.texttime){
            goActivity();
        }
    }

    private Animator prepareAnimator() {
        AnimatorSet animation = new AnimatorSet();

        // 进度条动画
        ObjectAnimator progressAnimator = ObjectAnimator.ofFloat(mCdDrawable, "progress", 1f, 0f);
        progressAnimator.setDuration(10000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                countdown.setVisibility(View.GONE);
                goActivity();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                countdown.setVisibility(View.GONE);
            }
        });

        // 居中的倒计时数字
        ObjectAnimator showNumberAnimator = ObjectAnimator.ofInt(mCdDrawable, "showNumber", 5, 0);
        showNumberAnimator.setDuration(10000);
        showNumberAnimator.setInterpolator(new LinearInterpolator());
        animation.playTogether(progressAnimator, showNumberAnimator);
        return animation;
    }
}
