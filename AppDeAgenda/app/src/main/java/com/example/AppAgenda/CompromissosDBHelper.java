package com.example.AppAgenda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CompromissosDBHelper extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String NOME_DATABASE = "CompromissosDB";

    public CompromissosDBHelper(Context context){super(context, NOME_DATABASE, null, VERSAO);}

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + CompromissosDBSchema.CompromissosTbl.NOME_TBL + "(" +
                "_id integer PRIMARY KEY autoincrement," +
                CompromissosDBSchema.CompromissosTbl.Cols.DIA + "," +
                CompromissosDBSchema.CompromissosTbl.Cols.MES + "," +
                CompromissosDBSchema.CompromissosTbl.Cols.ANO + "," +
                CompromissosDBSchema.CompromissosTbl.Cols.HORA + "," +
                CompromissosDBSchema.CompromissosTbl.Cols.MINUTO + "," +
                CompromissosDBSchema.CompromissosTbl.Cols.DESCRICAO + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        db.execSQL("DROP TABLE IF EXISTS " + CompromissosDBSchema.CompromissosTbl.NOME_TBL);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + CompromissosDBSchema.CompromissosTbl.NOME_TBL);
        onCreate(db);
    }
}