package com.example.acessogeodb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PanhadorDB {
    private Context mContext;
    private static Context mStaticContext;
    private SQLiteDatabase mDatabase;

    public PanhadorDB(Context contexto){
        mContext = contexto.getApplicationContext();
        mStaticContext = mContext;
        mDatabase = new DbHelper(mContext).getWritableDatabase();
    }

    public void addQuestao(String nome, String cpf, String numero, String chave_pix){
        ContentValues valores_panhador = new ContentValues();

        valores_panhador.put(DbSchema.PanhadoresTbl.Cols.NOME, nome);
        valores_panhador.put(DbSchema.PanhadoresTbl.Cols.CPF, cpf);
        valores_panhador.put(DbSchema.PanhadoresTbl.Cols.NUMERO, numero);
        valores_panhador.put(DbSchema.PanhadoresTbl.Cols.CHAVE_PIX, chave_pix);
    }

    public Cursor queryPanhador(String clausulaWhere, String[] argsWhere){
        Cursor cursor = mDatabase.query(DbSchema.PanhadoresTbl.NOME_TBL,
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
        delete = mDatabase.delete(DbSchema.PanhadoresTbl.NOME_TBL, null, null);
    }
}
