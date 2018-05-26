package com.oldmen.testapplihelp.presentation.mvp.main;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.oldmen.testapplihelp.domain.models.Board;
import com.oldmen.testapplihelp.presentation.mvp.base.BaseView;

import java.util.List;

@StateStrategyType(SkipStrategy.class)
public interface MainView extends BaseView {

    void updateBoards(List<Board> boards);

}
