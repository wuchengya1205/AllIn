package com.xiang.allin.listener;

/**
 * author : fengzhangwei
 * date : 2019/11/15
 */
public interface ProgressRequestListener {
    void onRequestProgress(int pro, long contentLength, boolean done);
}
