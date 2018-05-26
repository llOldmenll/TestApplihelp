package com.oldmen.testapplihelp.data.network;

import com.oldmen.testapplihelp.presentation.mvp.base.BaseView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.HttpException;

public abstract class CallbackWrapper<T> extends DisposableObserver<T> {

    //BaseView is just a reference of a View in MVP
    private WeakReference<BaseView> weakReference;

    public CallbackWrapper(BaseView view) {
        this.weakReference = new WeakReference<>(view);
    }

    protected abstract void onSuccess(T t);

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        BaseView view = weakReference.get();
        if (e instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) e).response().errorBody();
            String errorMsg = "ERROR!";
            try {
                if (responseBody != null)
                    errorMsg = new JSONObject(responseBody.string()).getString("detail_message");
            } catch (IOException | JSONException e1) {
                e1.printStackTrace();
            }
            view.showMsg(errorMsg);
        } else if (e instanceof IOException) {
            view.showNoInternetMsg();
        } else {
            view.showMsg(e.getMessage());
        }
    }

    @Override
    public void onComplete() {
    }
}
