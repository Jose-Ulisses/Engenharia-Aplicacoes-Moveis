package com.example.acessogeodb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TalhaoDB {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public TalhaoDB(Context contexto){
        mContext = contexto.getApplicationContext();
        mDatabase = new TalhoesDbHelper(mContext).getWritableDatabase();
    }

    public void addTalhao(String nomeLavoura, String nomeTalhao, double preco){
        ContentValues valores_talhao = new ContentValues();

        valores_talhao.put(TalhoesDbSchema.TalhoesTbl.Cols.NOME_LAVOURA, nomeLavoura);
        valores_talhao.put(TalhoesDbSchema.TalhoesTbl.Cols.NOME_TALHAO, nomeTalhao);
        valores_talhao.put(TalhoesDbSchema.TalhoesTbl.Cols.PRECO, preco);

        mDatabase.insert(TalhoesDbSchema.TalhoesTbl.NOME_TBL, null, valores_talhao);
    }

    public Cursor queryTalhao(String clausulaWhere, String[] argsWhere){
        Cursor cursor = mDatabase.query(TalhoesDbSchema.TalhoesTbl.NOME_TBL,
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
        delete = mDatabase.delete(TalhoesDbSchema.TalhoesTbl.NOME_TBL, null, null);
    }
}