package com.oldmen.testapplihelp.presentation.mvp.splash;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.oldmen.testapplihelp.presentation.mvp.base.BaseView;

@StateStrategyType(SkipStrategy.class)
public interface SplashView extends BaseView {

    void startMainActivity();

}
