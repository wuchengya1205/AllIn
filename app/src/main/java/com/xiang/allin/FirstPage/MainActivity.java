package com.xiang.allin.FirstPage;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.gyf.barlibrary.ImmersionBar;
import com.xiang.allin.FirstPage.fr.FirstPageFragment;
import com.xiang.allin.R;
import com.xiang.allin.base.ac.BaseActivity;
import com.xiang.allin.chatpage.ChatPageFragment;
import com.xiang.allin.onlinepage.OnlinePageFragment;
import com.xiang.allin.videopage.VideoPageFragment;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private FrameLayout main_frame;
    private BottomNavigationBar bottom_nav_bar;
    private TextBadgeItem badgeItem;
    private FragmentTransaction transaction;
    private Fragment mFragment;
    private FirstPageFragment firstPageFragment;
    private VideoPageFragment videoPageFragment;
    private ChatPageFragment chatPageFragment;
    private OnlinePageFragment onlinePageFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
    }

    @Override
    public void initView() {
        super.initView();
        initBar();
        main_frame = findViewById(R.id.main_frame);
        bottom_nav_bar = findViewById(R.id.bottom_nav_bar);
    }

    private void initBar() {
        ImmersionBar.with(this)
                .transparentStatusBar()  //透明状态栏，不写默认透明色
                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为 true)
                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为 true）
                .statusBarAlpha(0.3f)  //状态栏透明度，不写默认 0.0f
                .navigationBarAlpha(0.2f)  //导航栏透明度，不写默认 0.0F
                .barAlpha(0.3f)  //状态栏和导航栏透明度，不写默认 0.0f
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init();
    }

    @Override
    public void initData() {
        super.initData();
        initBottomNavigationBar();
        initFragment();
    }

    private void initFragment() {
        firstPageFragment = new FirstPageFragment();
        videoPageFragment = new VideoPageFragment();
        chatPageFragment = new ChatPageFragment();
        onlinePageFragment = new OnlinePageFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_frame, firstPageFragment).commit();
        mFragment = firstPageFragment;
    }

    private void initBottomNavigationBar() {
        bottom_nav_bar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor("#ffffff")//选中颜色
                .setInActiveColor("#2B2B2B")//未选中颜色
                .setBarBackgroundColor("#EDC18E");//导航栏背景色
        badgeItem = new TextBadgeItem()
                .setBorderWidth(3)
                .setTextColor(Color.WHITE)
                .setBackgroundColor(Color.RED)
                .setText("66");

        /**
         *添加导航按钮
         */
        bottom_nav_bar.addItem(
                new BottomNavigationItem(R.mipmap.xx3,"首页"))
                .addItem(new BottomNavigationItem(R.mipmap.xx3,"视频").setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.xx3,"聊天").setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.xx3,"直播").setBadgeItem(badgeItem))//添加小红点数据
                .initialise();//initialise 一定要放在 所有设置的最后一项
    }

    @Override
    public void onTabSelected(int position) {
        switch (position){
            case 0:
                switchFragment(firstPageFragment);
                break;
            case 1:
                switchFragment(videoPageFragment);
                break;
            case 2:
                switchFragment(chatPageFragment);
                break;
            case 3:
                switchFragment(onlinePageFragment);
                break;
        }
    }

    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if(mFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mFragment)
                        .add(R.id.main_frame,fragment)
                        .commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mFragment)
                        .show(fragment)
                        .commit();
            }
            mFragment = fragment;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
