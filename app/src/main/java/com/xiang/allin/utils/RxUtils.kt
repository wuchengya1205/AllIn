package com.test.project.utils

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.trello.rxlifecycle2.RxLifecycle
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.Disposable

/**
 * Author:Ljb
 * Time:2018/12/28
 * There is a lot of misery in life
 **/
object RxUtils {

    fun dispose(disposable: Disposable?) {
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }

    /**
     * Java使用该方法bind Rx生命周期
     * Kotlin可直接调用bindRxLife()绑定我们自己实现的生命周期管理
     * */
    /*fun <T> bindToLifecycle(obj: Any): ObservableTransformer<T, T> {
        return if (obj is AppCompatActivity) {
            RxLifecycleCompact.bind(obj).disposeObservableWhen(LifecycleEvent.DESTROY_VIEW)
        } else if (obj is FragmentActivity) {
            RxLifecycle.bind(obj).disposeObservableWhen(LifecycleEvent.DESTROY_VIEW)
        } else if (obj is Activity) {
            RxLifecycle.bind(obj).disposeObservableWhen(LifecycleEvent.DESTROY_VIEW)
        } else if (obj is Fragment) {
            RxLifecycleCompact.bind(obj).disposeObservableWhen(LifecycleEvent.DESTROY_VIEW)
        } else {
            throw IllegalArgumentException("view isn't activity or fragment")
        }
    }*/

}