package com.oldmen.testapplihelp.domain.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.oldmen.testapplihelp.utils.Constants.DB_BOARD_TABLE;

@Entity(tableName = DB_BOARD_TABLE)
public class Board {

    @PrimaryKey
    @SerializedName("id")
    @Expose(serialize = false)
    private int mId;
    @SerializedName("name")
    private String mName;

    public Board() {
    }

    private Board(Builder builder) {
        setId(builder.mId);
        setName(builder.mName);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public static final class Builder {
        @Expose(serialize = false)
        private int mId;
        private String mName;

        public Builder() {
        }

        public Builder mId(int val) {
            mId = val;
            return this;
        }

        public Builder mName(String val) {
            mName = val;
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }
}
