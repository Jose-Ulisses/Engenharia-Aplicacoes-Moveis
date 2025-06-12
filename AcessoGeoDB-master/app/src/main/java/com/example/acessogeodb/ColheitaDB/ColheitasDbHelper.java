package com.example.acessogeodb.ColheitaDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.acessogeodb.PanhadorDB.PanhadoresDbSchema;

public class ColheitasDbHelper extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String NOME_DATABASE = "colheitasDB";

    public ColheitasDbHelper(Context context){
        super(context, NOME_DATABASE, null, VERSAO);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + ColheitasDbSchema.ColheitasTbl.NOME_TBL + "(" +
                "_id integer PRIMARY KEY autoincrement," +
                ColheitasDbSchema.ColheitasTbl.Cols.LAVOURA + "," +
                ColheitasDbSchema.ColheitasTbl.Cols.TALHAO + "," +
                ColheitasDbSchema.ColheitasTbl.Cols.PANHADOR + "," +
                ColheitasDbSchema.ColheitasTbl.Cols.QUANTIDADE + "," +
                ColheitasDbSchema.ColheitasTbl.Cols.DATA + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        db.execSQL("DROP TABLE IF EXISTS " + PanhadoresDbSchema.PanhadoresTbl.NOME_TBL);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PanhadoresDbSchema.PanhadoresTbl.NOME_TBL);
        onCreate(db);
    }
}