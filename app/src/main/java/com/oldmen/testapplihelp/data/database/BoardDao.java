package com.oldmen.testapplihelp.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.oldmen.testapplihelp.domain.models.Board;

import java.util.List;

import io.reactivex.Flowable;

import static com.oldmen.testapplihelp.utils.Constants.DB_BOARD_TABLE;

@Dao
public interface BoardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Board> boards);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Board... boards);

    @Query("SELECT * FROM " + DB_BOARD_TABLE)
    Flowable<List<Board>> getAll();

    @Query("DELETE FROM " + DB_BOARD_TABLE)
    void drop();

}
