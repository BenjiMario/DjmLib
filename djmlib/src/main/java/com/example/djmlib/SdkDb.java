package com.example.djmlib;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 *                       Copyright (c) Benjinn
 *
 *                            (C) Benjinn 2019
 * All rights are reserved. Reproduction in whole or in part is
 * prohibited without the written consent of the copyright owner.
 * Benjinn reserves the right to make changes without notice at any time.
 * Benjinn makes no warranty, expressed, implied or statutory, including but
 * not limited to any implied warranty of merchantability or fitness for any
 * particular purpose, or that the use will not infringe any third party patent,
 * copyright or trademark. Benjinn must not be liable for any loss or damage
 * arising from its use.
 */

class SdkDb extends SQLiteOpenHelper {
    private final static int DB_VERSION = 1;
    private final static String DB_NAME = "sdkdb";
    //Definition of the Card Table
    final static String CARD_TB_NAME = "TB_CARDS";
    final static String COL_CARD_ID = "_id";
    final static String COL_CARD_NUMBER = "_num";
    final static String COL_CARD_MONTH = "_month";
    final static String COL_CARD_YEAR = "_year";
    final static String COL_CARD_TYPE = "_type";

    private final static String CREATE_TB_CARDS =
            "CREATE TABLE " + CARD_TB_NAME + " (" +
                    COL_CARD_ID + " INTEGER PRIMARY KEY, " +
                    COL_CARD_NUMBER + " BIGINT, " +
                    COL_CARD_MONTH + " INT CHECK ("+COL_CARD_MONTH+" BETWEEN 1 AND 12)," +
                    COL_CARD_YEAR + " INT ," +
                    COL_CARD_TYPE + " INT CHECK ("+COL_CARD_TYPE+" BETWEEN 0 AND 1))";



    SdkDb(Context ctx) {
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
