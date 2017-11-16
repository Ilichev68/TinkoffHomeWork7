package com.example.user.tinkoffhomework7.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by User on 12.11.2017.
 */

public class BaseHelper extends SQLiteOpenHelper {


    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "nodesBase.db";
    public static final String NODES_ID_TABLE_NAME = "nodes";
    public static final String NODES_TABLE_NAME = "entries";
    public static final String VALUE = "value";
    public static final String PARENT_ID = "pID";
    public static final String CHILD_ID = "cID";

    public BaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + NODES_TABLE_NAME + "(" +
                "id integer primary key autoincrement, " +
                VALUE + ")");

        sqLiteDatabase.execSQL("create table " + NODES_ID_TABLE_NAME + "(" +
                PARENT_ID + ", " +
                CHILD_ID + ", " +
                "PRIMARY KEY (" + PARENT_ID + ", " + CHILD_ID + ")" + ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }


}
