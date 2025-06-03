package com.example.acessogeodb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String NOME_DATABASE = "panhadoresDB";

    public DbHelper(Context context){
        super(context, NOME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + DbSchema.PanhadoresTbl.NOME_TBL + "(" +
                "_id integer PRIMARY KEY autoincrement," +
                DbSchema.PanhadoresTbl.Cols.NOME + "," +
                DbSchema.PanhadoresTbl.Cols.CPF + "," +
                DbSchema.PanhadoresTbl.Cols.NUMERO + "," +
                DbSchema.PanhadoresTbl.Cols.CHAVE_PIX + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        db.execSQL("DROP TABLE IF EXISTS " + DbSchema.PanhadoresTbl.NOME_TBL);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + DbSchema.PanhadoresTbl.NOME_TBL);
        onCreate(db);
    }
}