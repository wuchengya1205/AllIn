package com.xiang.allin.FirstPage.fr;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
public class FirstPageFragment extends BaseMvpFragment<FirstPageContract.IPresenter> implements FirstPageContract.IView {

    private List<String> imageList;
    private RecyclerView recycler_list;
    private XBanner xbanner;
    private DrawerLayout drawer_layout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_firstpage;
    }

    @Override
    public void getDataSuccess(CommonBean commonBean) {
        String reason = commonBean.getReason();
        showToast(reason);
        String stat = commonBean.getResult().getStat();
        if ("1".equals(stat)){
            List<CommonBean.ResultBean.DataBean> data = commonBean.getResult().getData();
            recycler_list.setLayoutManager(new LinearLayoutManager(getActivity()));
            RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(getActivity(), data);
            recycler_list.setAdapter(recyclerListAdapter);
            recyclerListAdapter.setOnItem(new RecyclerListAdapter.setOnItemClickListener() {
                @Override
                public void onItemClickListener(int position, RecyclerListAdapter.RecyclerListHolder holder, String url) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("web",url);
                    startActivity(intent);
                }
            });
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

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void initView() {
        super.initView();
        xbanner = getActivity().findViewById(R.id.xbanner);
        recycler_list = getActivity().findViewById(R.id.recycler_list);
        drawer_layout = getActivity().findViewById(R.id.drawer_layout);
    }

    @Override
    public void initData() {
        super.initData();
        //设置轮播图数据
        imageList = new ArrayList<>();
        imageList.add("http://www.pptok.com/wp-content/uploads/2012/08/xunguang-7.jpg");
        imageList.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imageList.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imageList.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        xbanner.setData(imageList,null);
        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(imageList.get(position)).into((ImageView) view);
            }
        });
        // 设置XBanner的页面切换特效，有多个，其他的可以到网上去查
        xbanner.setPageTransformer(Transformer.Default);//横向移动
        xbanner.setPageTransformer(Transformer.Alpha); //渐变，效果不明显
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
}
