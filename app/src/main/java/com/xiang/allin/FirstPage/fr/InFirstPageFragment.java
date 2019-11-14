package com.xiang.allin.FirstPage.fr;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.xiang.allin.FirstPage.adapter.RecyclerListAdapter;
import com.xiang.allin.FirstPage.contract.InFirstPageContract;
import com.xiang.allin.FirstPage.presenter.InFirstPagePresenter;
import com.xiang.allin.R;
import com.xiang.allin.base.fr.BaseMvpFragment;
import com.xiang.allin.common.CommonBean;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/30
 * time   :15:53
 * desc   :ohuo
 * version: 1.0
 */
public class InFirstPageFragment extends BaseMvpFragment<InFirstPageContract.IPresenter> implements InFirstPageContract.IView {


    boolean mIsPrepare = false;		//视图还没准备好
    boolean mIsVisible= false;		//不可见
    boolean mIsFirstLoad = true;	//第一次加载
    private RecyclerView recycler_list;
    private RecyclerListAdapter recyclerListAdapter;
    private String type;
    private String key;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_infirstpage;
    }

    @Override
    public void getDataSuccess(CommonBean commonBean) {

        showToast(commonBean.getStat());
        if ("1".equals(commonBean.getStat())){
            if (recyclerListAdapter != null){
                recyclerListAdapter.setData(commonBean.getData());
            }
        }
    }

    @Override
    public void getDataError(String msg) {
        showToast(msg);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void initView() {
//        recycler_list = view.findViewById(R.id.recycler_list);
        mIsPrepare = true;
        type = getArguments().getString("type");
        key = getArguments().getString("key");
        recycler_list.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerListAdapter = new RecyclerListAdapter(getContext(), new ArrayList<CommonBean.DataBean>());
        recycler_list.setAdapter(recyclerListAdapter);
    }

    @Override
    public void initData() {
        super.initData();
        recycler_list.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerListAdapter = new RecyclerListAdapter(getContext(), dataList);
        recycler_list.setAdapter(recyclerListAdapter);
        lazyLoad();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @NotNull
    @Override
    public Class<? extends InFirstPageContract.IPresenter> registerPresenter() {
        return InFirstPagePresenter.class;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            mIsVisible = true;
            if (getView() != null){
                lazyLoad();
            }
        } else {
            mIsVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行三个条件的判断，如果有一个不满足，都将不进行加载
        if (!mIsPrepare || !mIsVisible||!mIsFirstLoad) {
            return;
        }
        loadData();
        //数据加载完毕,恢复标记,防止重复加载
        mIsFirstLoad = false;
    }

    //请求网络数据
    private void loadData() {
        getPresenter().getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsFirstLoad=true;
        mIsPrepare=false;
        mIsVisible = false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String msg) {
        if ("refresh".equals(msg)){
            getPresenter().getData();
        }else if ("loadmore".equals(msg)){
            getPresenter().getData();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(InFirstPageFragment.this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(InFirstPageFragment.this);
    }
}
