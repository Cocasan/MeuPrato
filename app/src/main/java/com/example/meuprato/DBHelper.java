package com.example.meuprato;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static String NOME = "prato.db";
    private static int VERSAO = 1;

    public DBHelper(Context context) {
        super(context, NOME, null, VERSAO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE prato(" +
                "codigo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "nome VARCHAR(60)NOT NULL,\n" +
                "ingredientes VARCHAR(60) NOT NULL,\n" +
                "precocusto DOUBLE NOT NULL,\n" +
                "precoprod DOUBLE NOT NULL) ");

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
