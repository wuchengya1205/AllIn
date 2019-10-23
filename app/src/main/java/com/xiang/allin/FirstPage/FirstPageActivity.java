package com.xiang.allin.FirstPage;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.xiang.allin.FirstPage.adapter.RecyclerListAdapter;
import com.xiang.allin.R;
import com.xiang.allin.base.BaseMvpActivity;
import com.xiang.allin.common.CommonBean;
import com.xiang.allin.contract.FirstPageContract;
import com.xiang.allin.presenter.FirstPagePresenter;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirstPageActivity extends BaseMvpActivity<FirstPageContract.IPresenter> implements FirstPageContract.IView, NavigationView.OnNavigationItemSelectedListener {

    private List<String> imageList;
    private RecyclerView recycler_list;
    private XBanner xbanner;
    private NavigationView navigation_view;
    private DrawerLayout drawer_layout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_firstpage;
    }

    @Override
    public void getDataSuccess(CommonBean commonBean) {
        String reason = commonBean.getReason();
        showToast(reason);
        String stat = commonBean.getResult().getStat();
        if ("1".equals(stat)){
            List<CommonBean.ResultBean.DataBean> data = commonBean.getResult().getData();
            recycler_list.setLayoutManager(new LinearLayoutManager(this));
            RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(this, data);
            recycler_list.setAdapter(recyclerListAdapter);
        }
    }

    @Override
    public void getDataError(String msg) {
        showToast(msg);
        Log.d("wwwwwwwwwwww",msg);
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

    @NotNull
    @Override
    public Class<? extends FirstPageContract.IPresenter> registerPresenter() {
        return FirstPagePresenter.class;
    }

    @Override
    public void initView() {
        super.initView();
        xbanner = findViewById(R.id.xbanner);
        recycler_list = findViewById(R.id.recycler_list);
        navigation_view = findViewById(R.id.navigation_view);
        drawer_layout = findViewById(R.id.drawer_layout);
        navigation_view.setNavigationItemSelectedListener(this);
    }

    @Override
    public void initData() {
        imageList = new ArrayList<>();
        imageList.add("http://www.pptok.com/wp-content/uploads/2012/08/xunguang-7.jpg");
        imageList.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imageList.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imageList.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        xbanner.setData(imageList,null);
        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(FirstPageActivity.this).load(imageList.get(position)).into((ImageView) view);
            }
        });
        // 设置XBanner的页面切换特效，有多个，其他的可以到网上去查
        xbanner.setPageTransformer(Transformer.Default);//横向移动
        xbanner.setPageTransformer(Transformer.Alpha); //渐变，效果不明显
        //设置轮播图点击监听
        xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(FirstPageActivity.this, "点击了"+position, Toast.LENGTH_SHORT).show();
            }
        });
        getPresenter().getData();
    }



    @Override
    protected void onResume() {
        super.onResume();
        xbanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        xbanner.stopAutoPlay();
    }

    //侧滑菜单子条目点击事件
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        return false;
    }
}
