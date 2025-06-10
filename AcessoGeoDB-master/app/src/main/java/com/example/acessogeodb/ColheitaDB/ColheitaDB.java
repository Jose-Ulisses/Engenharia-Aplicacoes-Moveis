package com.example.acessogeodb.ColheitaDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ColheitaDB{
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public ColheitaDB(Context contexto){
        mContext = contexto.getApplicationContext();
        mDatabase = new ColheitasDbHelper(mContext).getWritableDatabase();
    }

    public void addColheita(String lavoura, String talhao, String panhador, double quantidade, String data){
        ContentValues valores_colheita = new ContentValues();

        valores_colheita.put(ColheitasDbSchema.ColheitasTbl.Cols.LAVOURA, lavoura);
        valores_colheita.put(ColheitasDbSchema.ColheitasTbl.Cols.TALHAO, talhao);
        valores_colheita.put(ColheitasDbSchema.ColheitasTbl.Cols.PANHADOR, panhador);
        valores_colheita.put(ColheitasDbSchema.ColheitasTbl.Cols.QUANTIDADE, quantidade);
        valores_colheita.put(ColheitasDbSchema.ColheitasTbl.Cols.DATA, data);

        mDatabase.insert(ColheitasDbSchema.ColheitasTbl.NOME_TBL, null, valores_colheita);
    }

    public Cursor queryColheita(String clausulaWhere, String[] argsWhere){
        Cursor cursor = mDatabase.query(ColheitasDbSchema.ColheitasTbl.NOME_TBL,
                null,
                clausulaWhere,
                argsWhere,
                null,
                null,
                null
        );
        return cursor;
    }

    public void deleteTbl(){
        int delete;
        delete = mDatabase.delete(ColheitasDbSchema.ColheitasTbl.NOME_TBL, null, null);
    }
}