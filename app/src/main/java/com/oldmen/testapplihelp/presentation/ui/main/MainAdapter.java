package com.oldmen.testapplihelp.presentation.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oldmen.testapplihelp.R;
import com.oldmen.testapplihelp.domain.models.Board;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {
    private List<Board> mBoards;

    public MainAdapter(List<Board> boards) {
        mBoards = boards;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.board_item, parent, false);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        holder.bindView(mBoards.get(position));
    }

    @Override
    public int getItemCount() {
        return mBoards.size();
    }

    class MainHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_board_item)
        TextView mName;

        MainHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(Board board) {
            mName.setText(board.getName());
        }
    }
}
