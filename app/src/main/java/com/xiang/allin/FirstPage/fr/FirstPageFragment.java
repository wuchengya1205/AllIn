package com.xiang.allin.FirstPage.fr;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.xiang.allin.FirstPage.adapter.RecyclerListAdapter;
import com.xiang.allin.R;
import com.xiang.allin.WebViewActivity;
import com.xiang.allin.base.fr.BaseMvpFragment;
import com.xiang.allin.FirstPage.presenter.FirstPagePresenter;
import com.xiang.allin.common.CommonBean;
import com.xiang.allin.FirstPage.FirstPageContract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/24
 * time   :14:10
 * desc   :ohuo
 * version: 1.0
 */
public class FirstPageFragment extends BaseMvpFragment<FirstPageContract.IPresenter> implements FirstPageContract.IView, XBanner.OnItemClickListener, OnRefreshListener, RecyclerListAdapter.setOnItemClickListener, NestedScrollView.OnScrollChangeListener {

    private List<String> imageList;
    private RecyclerView recycler_list;
    private XBanner xbanner;
    private Transformer[] transformers = {Transformer.Rotate, Transformer.Cube,
            Transformer.Flip, Transformer.Accordion, Transformer.ZoomFade, Transformer.ZoomStack,
            Transformer.Stack, Transformer.Depth, Transformer.Zoom};
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerListAdapter recyclerListAdapter;
    private List<CommonBean.ResultBean.DataBean> list = new ArrayList<>();
    private NestedScrollView nestedScrollView;
    private int bannerHeight;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_firstpage;
    }

    @Override
    public void getDataSuccess(CommonBean commonBean) {
        String reason = commonBean.getReason();
        smartRefreshLayout.finishRefresh();
//        //设置轮播图数据
//        setXBanner();
        // 设置XBanner的页面切换特效，有多个，其他的可以到网上去查
//        xbanner.setPageTransformer(randomTransformer());
        if (commonBean.getResult() != null){
            String stat = commonBean.getResult().getStat();
            if ("1".equals(stat)) {
                recyclerListAdapter.getData().clear();
                List<CommonBean.ResultBean.DataBean> data = commonBean.getResult().getData();
                recyclerListAdapter.setData(data);
                recyclerListAdapter.notifyDataSetChanged();
            }
        }else {
            showToast(reason);
        }


    }

    @Override
    public void getDataError(String msg) {
        showToast(msg);
        Log.d("wwwwwwwwwwww", msg);
    }

    @Override
    public Map<String, String> getParams() {
        return null;
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
        xbanner = getActivity().findViewById(R.id.xbanner);
        recycler_list = getActivity().findViewById(R.id.recycler_list);
        smartRefreshLayout = getActivity().findViewById(R.id.smartRefreshLayout);
        nestedScrollView = getActivity().findViewById(R.id.nestedScrollView);
    }

    @Override
    public void initData() {
        super.initData();
        setXBanner();
        bannerHeight = xbanner.getLayoutParams().height;
        recycler_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerListAdapter = new RecyclerListAdapter(getActivity(), list);
        recycler_list.setAdapter(recyclerListAdapter);
        recycler_list.setHasFixedSize(true);
        recycler_list.setNestedScrollingEnabled(false);
        xbanner.setPageChangeDuration(1000);
        //设置轮播图点击监听
        xbanner.setOnItemClickListener(this);
        smartRefreshLayout.setOnRefreshListener(this);
        recyclerListAdapter.setOnItem(this);
        nestedScrollView.setOnScrollChangeListener(this);
        //获取新闻列表的数据
        getPresenter().getData();
    }

    private void setXBanner() {
        //设置轮播图数据
        imageList = new ArrayList<>();
        imageList.clear();
//        imageList.add("https://p3.pstatp.com/large/c0b300014a9ef7257e51.jpg");
//        imageList.add("https://p3.pstatp.com/large/b8410004ef57355b50cf.jpg");
//        imageList.add("https://p1.pstatp.com/large/bd8f000561db440796dd.jpg");
        imageList.add("https://p98.pstatp.com/large/c0a10007f2fc822bc278.jpg");
        imageList.add("http://www.pptok.com/wp-content/uploads/2012/08/xunguang-7.jpg");
        imageList.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imageList.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imageList.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        xbanner.setData(imageList, null);
        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(imageList.get(position)).into((ImageView) view);
            }
        });
//        xbanner.setPageTransformer(Transformer.Scale);
        //设置轮播图点击监听
        xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(getActivity(), "点击了"+position, Toast.LENGTH_SHORT).show();
            }
        });
        //获取新闻列表的数据
        getPresenter().getData();
    }

    private Transformer randomTransformer() {
        int a = (int) (Math.random() * transformers.length);
        Log.d("TAG", "----样式------" + a);
        return transformers[a];
    }

    @NotNull
    @Override
    public Class<? extends FirstPageContract.IPresenter> registerPresenter() {
        return FirstPagePresenter.class;
    }

    @Override
    public void onResume() {
        super.onResume();
        xbanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xbanner.stopAutoPlay();
    }

    @Override
    public void onItemClick(XBanner banner, Object model, View view, int position) {
        Toast.makeText(getActivity(), "点击了" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        //获取新闻列表的数据
        getPresenter().getData();
    }

    @Override
    public void onItemClickListener(int position, RecyclerListAdapter.RecyclerListHolder holder, String url) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra("web", url);
        startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY > oldScrollY) {
            if (scrollY >= (bannerHeight - 115) && scrollY <= bannerHeight) {
                ImmersionBar.with(getActivity())
                        .statusBarColor(R.color.colorWhite)
                        .statusBarDarkFont(true)
                        .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR) // 隐藏导航栏或者状态栏
                        .init();
            }
        }
        if (scrollY < oldScrollY) {
            if (scrollY <= (bannerHeight - 115) && scrollY >= (bannerHeight / 2) + 100) {
                ImmersionBar.with(getActivity())
                        .transparentBar()
                        .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR) // 隐藏导航栏或者状态栏
                        .init();
            }
        }
    }
}
