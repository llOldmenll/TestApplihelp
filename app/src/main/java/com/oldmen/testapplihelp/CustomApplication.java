package com.oldmen.testapplihelp;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.oldmen.testapplihelp.data.database.AppDataBase;

import static com.oldmen.testapplihelp.utils.Constants.DATA_BASE_NAME;

public class CustomApplication extends Application {
    private static AppDataBase mDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        mDataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, DATA_BASE_NAME)
                .build();
    }

    public static AppDataBase getDbInstance() {
        return mDataBase;
    }
}
