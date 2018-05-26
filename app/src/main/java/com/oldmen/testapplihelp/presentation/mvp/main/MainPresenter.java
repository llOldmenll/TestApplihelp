package com.oldmen.testapplihelp.presentation.mvp.main;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.oldmen.testapplihelp.CustomApplication;
import com.oldmen.testapplihelp.data.database.BoardDao;
import com.oldmen.testapplihelp.data.network.CallbackWrapper;
import com.oldmen.testapplihelp.data.network.RetrofitClient;
import com.oldmen.testapplihelp.domain.models.Board;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public void getBoardsFromDB() {
        CustomApplication.getDbInstance().getBoardDao().getAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(boards -> getViewState().updateBoards(boards))
                .subscribe();
    }

    @SuppressLint("CheckResult")
    public void postBoard(Board postBoard) {
        RetrofitClient.getApiService().postBoard(postBoard)
                .doOnNext(this::saveBoards)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CallbackWrapper<Board>(getViewState()) {
                    @Override
                    protected void onSuccess(Board board) {
                        getViewState().showMsg("Board " + board.getName() + " was added!");
                    }
                });
    }

    private void saveBoards(Board board) {
        BoardDao dao = CustomApplication.getDbInstance().getBoardDao();
        dao.insertAll(board);
    }

}
