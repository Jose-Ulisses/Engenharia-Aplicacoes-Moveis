package com.example.acessogeodb.PanhadorDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PanhadorDB {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public PanhadorDB(Context contexto){
        mContext = contexto.getApplicationContext();
        mDatabase = new PanhadoresDbHelper(mContext).getWritableDatabase();
    }

    public void addPanhador(String nome, String cpf, String numero, String chave_pix){
        ContentValues valores_panhador = new ContentValues();

        valores_panhador.put(PanhadoresDbSchema.PanhadoresTbl.Cols.NOME, nome);
        valores_panhador.put(PanhadoresDbSchema.PanhadoresTbl.Cols.CPF, cpf);
        valores_panhador.put(PanhadoresDbSchema.PanhadoresTbl.Cols.NUMERO, numero);
        valores_panhador.put(PanhadoresDbSchema.PanhadoresTbl.Cols.CHAVE_PIX, chave_pix);

        mDatabase.insert(PanhadoresDbSchema.PanhadoresTbl.NOME_TBL, null, valores_panhador);
    }

    public Cursor queryPanhador(String clausulaWhere, String[] argsWhere){
        Cursor cursor = mDatabase.query(PanhadoresDbSchema.PanhadoresTbl.NOME_TBL,
                null,
                clausulaWhere,
                argsWhere,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor queryNomePanhador(String[] columns, String clausulaWhere, String[] argsWhere){
        Cursor cursor = mDatabase.query(PanhadoresDbSchema.PanhadoresTbl.NOME_TBL,
                columns,
                clausulaWhere,
                argsWhere,
                null,
                null,
                null
        );
        return cursor;
    }
}