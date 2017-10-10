package com.example.jin.newandroidlabs;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jin on 10/8/2017.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Chat.db";
    public static final int VERSION_NUM = 1;
    public static final String TABLE_NAME = "CHAT";
    public static final String KEY_ID = "_ID";
    public static final String KEY_MESSAGE = "_MESSAGE";
    private static final String Create_Database = "create table "
            + TABLE_NAME
            + "( "
            + KEY_ID
            + " integer primary key autoincrement, "
            + KEY_MESSAGE
            + " text);";

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( Create_Database );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVer + "newVersion=" + newVer);

    }


}
