package com.example.acessogeodb.LavouraDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LavouraDB {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public LavouraDB(Context contexto){
        mContext = contexto.getApplicationContext();
        mDatabase = new LavourasDbHelper(mContext).getWritableDatabase();
    }

    public void addLavoura(String nome){
        ContentValues valores_lavoura = new ContentValues();

        valores_lavoura.put(LavourasDbSchema.LavourasTbl.Cols.NOME, nome);

        mDatabase.insert(LavourasDbSchema.LavourasTbl.NOME_TBL, null, valores_lavoura);
    }

    public Cursor queryLavoura(String clausulaWhere, String[] argsWhere){
        Cursor cursor = mDatabase.query(LavourasDbSchema.LavourasTbl.NOME_TBL,
                null,
                clausulaWhere,
                argsWhere,
                null,
                null,
                null
        );
        return cursor;
    }

    void deleteTbl(){
        int delete;
        delete = mDatabase.delete(LavourasDbSchema.LavourasTbl.NOME_TBL, null, null);
    }
}
