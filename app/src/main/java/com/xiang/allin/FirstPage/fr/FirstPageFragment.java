package com.xiang.allin.FirstPage.fr;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xiang.allin.FirstPage.contract.FirstPageContract;
import com.xiang.allin.FirstPage.presenter.FirstPagePresenter;
import com.xiang.allin.FirstPage.view.BannerViewPager;
import com.xiang.allin.R;
import com.xiang.allin.base.fr.BaseMvpFragment;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/24
 * time   :14:10
 * desc   :ohuo
 * version: 1.0
 */
public class FirstPageFragment extends BaseMvpFragment<FirstPageContract.IPresenter> implements FirstPageContract.IView, OnRefreshListener, OnLoadMoreListener {

    private List<String> imageList;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String>titles = new ArrayList<>();
    String key = "61005cfc63a8075c88d5d408ba90aff9";
    String type = "";
    private BannerViewPager banner;
    private SmartRefreshLayout refreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_firstpage;
    }

    @Override
    public void getDataSuccess() {
        }

    @Override
    public void getDataError(String msg) {
    }

    @Override
    public String getType() {
        return "top";
    }

    @Override
    public String getKey() {
        return "61005cfc63a8075c88d5d408ba90aff9";
    }

    @Override
    public void initView() {
        super.initView();
        tabLayout = getActivity().findViewById(R.id.tabLayout);
        viewPager = getActivity().findViewById(R.id.viewPager);
        banner = getActivity().findViewById(R.id.banner);
        refreshLayout = getActivity().findViewById(R.id.refreshLayout);
    }

    @Override
    public void initData() {
        super.initData();
        initTitles();
        initViewPagers();
        setXBanner();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
    }

    private void initViewPagers() {
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                InFirstPageFragment inFirstPageFragment = new InFirstPageFragment();
//                InFirstPageFragment inFirstPageFragment = InFirstPageFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putString("type", gettype(position));
                bundle.putString("key",key);
                inFirstPageFragment.setArguments(bundle);
                return  inFirstPageFragment;
            }

            @Override
            public int getCount() {
                return titles.size();
            }

        });
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(titles.size());
    }


    private void setXBanner() {
        //设置轮播图数据
        imageList = new ArrayList<>();
        imageList.add("http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg");
        imageList.add("https://ww1.sinaimg.cn/large/0065oQSqly1g2hekfwnd7j30sg0x4djy.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/0065oQSqly1g0ajj4h6ndj30sg11xdmj.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/0065oQSqly1fytdr77urlj30sg10najf.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/0065oQSqly1fymj13tnjmj30r60zf79k.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fy58bi1wlgj30sg10hguu.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fxno2dvxusj30sf10nqcm.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fwyf0wr8hhj30ie0nhq6p.jpg");
        imageList.add("https://ws1.sinaimg.cn/large/0065oQSqgy1fwgzx8n1syj30sg15h7ew.jpg");
        banner.initBanner(imageList, false)//关闭3D画廊效果
                .addPageMargin(20, 50)//参数1page之间的间距,参数2中间item距离边界的间距
                .addPoint(imageList.size()+2)//添加指示器
                .addPointBottom(7)
                .addStartTimer(2)
                .addRoundCorners(5)//圆角
                .finishConfig()//这句必须加
                .addBannerListener(new BannerViewPager.OnClickBannerListener() {
                    @Override
                    public void onBannerClick(int position) {
                        Log.i("test","--------------00x2");
                    }
                });
    }

    @NotNull
    @Override
    public Class<? extends FirstPageContract.IPresenter> registerPresenter() {
        return FirstPagePresenter.class;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    private void initTitles() {
        titles.add("头条");
        titles.add("社会");
        titles.add("国内");
        titles.add("国际");
        titles.add("娱乐");
        titles.add("体育");
        titles.add("军事");
        titles.add("科技");
        titles.add("财经");
        titles.add("时尚");
    }

    private String gettype(int position) {
        if ("头条".equals(titles.get(position))){
            type = "top";
        }else if ("社会".equals(titles.get(position))){
            type = "shehui";
        }else if ("国内".equals(titles.get(position))){
            type = "guonei";
        }else if ("国际".equals(titles.get(position))){
            type = "guoji";
        }else if ("娱乐".equals(titles.get(position))){
            type = "yule";
        }else if ("体育".equals(titles.get(position))){
            type = "tiyu";
        }else if ("军事".equals(titles.get(position))){
            type = "junshi";
        }else if ("科技".equals(titles.get(position))){
            type = "keji";
        }else if ("财经".equals(titles.get(position))){
            type = "caijing";
        }else if ("时尚".equals(titles.get(position))){
            type = "shishang";
        }
        return type;
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        EventBus.getDefault().post("refresh");
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        EventBus.getDefault().post("loadmore");
    }
}
