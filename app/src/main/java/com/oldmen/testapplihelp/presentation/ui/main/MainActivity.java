package com.oldmen.testapplihelp.presentation.ui.main;

import android.app.AlertDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.oldmen.testapplihelp.R;
import com.oldmen.testapplihelp.domain.models.Board;
import com.oldmen.testapplihelp.presentation.mvp.main.MainPresenter;
import com.oldmen.testapplihelp.presentation.mvp.main.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.oldmen.testapplihelp.utils.Constants.BUNDLE_RECYCLER_POSITION;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    @InjectPresenter
    MainPresenter mPresenter;
    @BindView(R.id.recycler_main)
    RecyclerView mRecycler;
    @BindView(R.id.fab_main)
    FloatingActionButton mFab;

    private List<Board> mBoards = new ArrayList<>();
    private MainAdapter mAdapter;
    private boolean isOrientationChanged;
    private Bundle savedState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initRecycler();
        initFab();
        mPresenter.getBoardsFromDB();
        savedState = savedInstanceState;
    }

    private void initRecycler() {
        mAdapter = new MainAdapter(mBoards);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mAdapter);

        //Hide floating action button when recycler scrolling
        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) mFab.hide();
                else if (dy < 0) mFab.show();
            }
        });
    }

    private void initFab() {
        mFab.setOnClickListener(v -> createBoardEditingDialog());
    }

    private void createBoardEditingDialog() {
        float dpi = getResources().getDisplayMetrics().density;
        EditText name = new EditText(this);
        name.setHint(R.string.board_name);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.title_board_editing))
                .setPositiveButton(android.R.string.ok, (dialog1, which) -> {
                    String boardName = name.getText().toString().trim();
                    if (boardName.isEmpty()) showMsg(getString(R.string.empty_board_name_msg));
                    else mPresenter.postBoard(new Board.Builder().mName(boardName).build());
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        //Add margin to EditText name
        dialog.setView(name, (int) (19 * dpi), (int) (5 * dpi), (int) (19 * dpi), (int) (5 * dpi));
        dialog.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(BUNDLE_RECYCLER_POSITION, mRecycler.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        isOrientationChanged = true;
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void updateBoards(List<Board> boards) {
        mBoards.clear();
        mBoards.addAll(boards);
        mAdapter.notifyDataSetChanged();

        // scroll RecyclerView to position which was before rotation
        if (isOrientationChanged && savedState != null && savedState.containsKey(BUNDLE_RECYCLER_POSITION)) {
            mRecycler.getLayoutManager()
                    .onRestoreInstanceState(savedState.getParcelable(BUNDLE_RECYCLER_POSITION));
            isOrientationChanged = false;
            savedState = null;
        }
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
