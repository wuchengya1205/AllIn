package com.xiang.allin.base;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import com.xiang.allin.R;

/**
 * author : fengzhangwei
 * date : 2019/9/12
 */
public class LoadingDialog extends Dialog {


    private  Boolean mIsBack;

    public LoadingDialog(@NonNull Context context, Boolean isBack) {
        super(context);
        this.mIsBack = isBack;
        setContentView(View.inflate(getContext(), R.layout.dialog_loading, null));
        setCancelable(mIsBack);
        setCanceledOnTouchOutside(isBack);
    }
}
