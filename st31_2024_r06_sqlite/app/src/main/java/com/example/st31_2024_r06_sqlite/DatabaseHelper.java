package com.example.st31_2024_r06_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "mySQLite.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
//    public DatabaseHelper(
//            @Nullable Context context,
//            @Nullable SQLiteDatabase.CursorFactory factory
//            ) {
//        super(context, DB_NAME, factory, DB_VERSION);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSQL = "";
        strSQL = "CREATE TABLE if not exists user (id TEXT PRIMARY KEY, name TEXT, age INTEGER, pass TEXT);";
        db.execSQL(strSQL);

        strSQL = "insert into user values ('00001', '片岡', 43, 'ktok')";
        db.execSQL(strSQL);
        strSQL = "insert into user values ('00002', '長屋', 45, 'nagaya')";
        db.execSQL(strSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }
}
