package com.oldmen.testapplihelp.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.oldmen.testapplihelp.domain.models.Board;

@Database(entities = {Board.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract BoardDao getBoardDao();

}
