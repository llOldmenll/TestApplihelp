package com.oldmen.testapplihelp.presentation.ui.splash;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.oldmen.testapplihelp.R;
import com.oldmen.testapplihelp.presentation.mvp.splash.SplashPresenter;
import com.oldmen.testapplihelp.presentation.mvp.splash.SplashView;
import com.oldmen.testapplihelp.presentation.ui.main.MainActivity;

public class SplashActivity extends MvpAppCompatActivity implements SplashView {
    @InjectPresenter
    SplashPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mPresenter.downloadBoards();
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showNoInternetMsg() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.no_internet_title))
                .setMessage(getString(R.string.no_internet_msg))
                .setPositiveButton(getString(android.R.string.ok), null)
                .setCancelable(false)
                .show();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
