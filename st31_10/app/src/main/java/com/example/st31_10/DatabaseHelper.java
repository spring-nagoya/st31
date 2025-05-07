package com.example.st31_10;

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
        strSQL = "CREATE TABLE if not exists mod (id TEXT PRIMARY KEY, name TEXT, url TEXT);";
        db.execSQL(strSQL);

        strSQL = "insert into mod values ('1', 'Tinkers Construct', 'https://www.curseforge.com/minecraft/mc-mods/tinkers-construct')";
        db.execSQL(strSQL);
        strSQL = "insert into mod values ('2', 'Thirst Was Taken', 'https://www.curseforge.com/minecraft/mc-mods/thirst-was-taken')";
        db.execSQL(strSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(newVersion == 2){
            db.execSQL("DROP TABLE IF EXISTS mod");
            onCreate(db);
        } else if (newVersion == 3) {
            db.execSQL("update mod set name = 'ars nouveau' where id = '1'");
        }

    }
}
