package com.urovo.core.mvp

import android.content.Context
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * create by 大白菜
 * description
 */
open abstract class BasePresenter<V : IBaseView, M : IBaseModel> constructor(
    val context: Context,
    val model: M,
    val view: V
) {

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * 添加消息订阅
     */
    fun subscribe(disposable: Disposable) {
        mCompositeDisposable?.let { it.add(disposable)}
    }

    /**
     * 取消消息订阅
     */
    fun unSubcribe(){
        mCompositeDisposable ?.let { it.clear() }
    }

}