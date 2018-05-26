package com.oldmen.testapplihelp.presentation.mvp.base;


import com.arellomobile.mvp.MvpView;

public interface BaseView extends MvpView{

    void showNoInternetMsg();

    void showMsg(String msg);

}
