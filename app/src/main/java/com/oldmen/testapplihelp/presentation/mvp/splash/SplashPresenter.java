package com.oldmen.testapplihelp.presentation.mvp.splash;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.oldmen.testapplihelp.CustomApplication;
import com.oldmen.testapplihelp.data.database.BoardDao;
import com.oldmen.testapplihelp.data.network.CallbackWrapper;
import com.oldmen.testapplihelp.data.network.RetrofitClient;
import com.oldmen.testapplihelp.domain.models.Board;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class SplashPresenter extends MvpPresenter<SplashView> {

    @SuppressLint("CheckResult")
    public void downloadBoards() {
        RetrofitClient.getApiService().getBoards()
                .doOnNext(this::saveBoards)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CallbackWrapper<List<Board>>(getViewState()) {
                    @Override
                    protected void onSuccess(List<Board> boards) {
                        getViewState().startMainActivity();
                    }
                });
    }

    private void saveBoards(List<Board> boards) {
        BoardDao dao = CustomApplication.getDbInstance().getBoardDao();
        dao.drop();
        dao.insertAll(boards);
    }
}
