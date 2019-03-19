package com.example.djmlib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SdkDb extends SQLiteOpenHelper {
    private final static int DB_VERSION = 1;
    private final static String DB_NAME = "sdkdb";
    //Definition of the Card Table
    public final static String CARD_TB_NAME = "TB_CARDS";
    public final static String COL_CARD_ID = "_id";
    public final static String COL_CARD_NUMBER = "_num";
    public final static String COL_CARD_MONTH = "_month";
    public final static String COL_CARD_YEAR = "_year";
    public final static String COL_CARD_TYPE = "_type";

    private final static String CREATE_TB_CARDS =
            "CREATE TABLE " + CARD_TB_NAME + " (" +
                    COL_CARD_ID + " INTEGER PRIMARY KEY, " +
                    COL_CARD_NUMBER + " BIGINT, " +
                    COL_CARD_MONTH + " INT CHECK ("+COL_CARD_MONTH+" BETWEEN 1 AND 12)," +
                    COL_CARD_YEAR + " INT ," +
                    COL_CARD_TYPE + " INT CHECK ("+COL_CARD_TYPE+" BETWEEN 0 AND 1))";



    public SdkDb(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_CARDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CARD_TB_NAME);
        onCreate(db);
    }
}
