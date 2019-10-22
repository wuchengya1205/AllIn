package com.xiang.allin.base;

import mvp.ljb.kt.contract.IPresenterContract;
import mvp.ljb.kt.contract.IViewContract;
import mvp.ljb.kt.presenter.IBasePresenter;


public abstract class BaseMvpPresenter<V extends IViewContract> implements IBasePresenter<V>, IPresenterContract {
    private V mMvpView = null;

    @Override
    public void registerMvpView(IViewContract mvpView) {
            mMvpView = (V)mvpView;
        }

    @Override
    public V getMvpView() {
        return mMvpView;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
