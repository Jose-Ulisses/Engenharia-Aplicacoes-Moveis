package com.example.acessogeodb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TalhoesDbHelper extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String NOME_DATABASE = "talhoesDB";

    public TalhoesDbHelper(Context context){
        super(context, NOME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TalhoesDbSchema.TalhoesTbl.NOME_TBL + "(" +
                "_id integer PRIMARY KEY autoincrement," +
                TalhoesDbSchema.TalhoesTbl.Cols.NOME_LAVOURA + "," +
                TalhoesDbSchema.TalhoesTbl.Cols.NOME_TALHAO + "," +
                TalhoesDbSchema.TalhoesTbl.Cols.PRECO + "," +
                TalhoesDbSchema.TalhoesTbl.Cols.TOTAL + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        db.execSQL("DROP TABLE IF EXISTS " + TalhoesDbSchema.TalhoesTbl.NOME_TBL);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TalhoesDbSchema.TalhoesTbl.NOME_TBL);
        onCreate(db);
    }
}