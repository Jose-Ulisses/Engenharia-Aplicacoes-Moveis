package com.example.AppAgenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CompromissosDB {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public CompromissosDB(Context contexto){
        mContext = contexto.getApplicationContext();
        mDatabase = new CompromissosDBHelper(mContext).getWritableDatabase();
    }

    public void addCompromisso(int dia, int mes, int ano, int hora, int minuto, String descricao){
        ContentValues valores_compromisso = new ContentValues();

        valores_compromisso.put(CompromissosDBSchema.CompromissosTbl.Cols.DIA, dia);
        valores_compromisso.put(CompromissosDBSchema.CompromissosTbl.Cols.MES, mes);
        valores_compromisso.put(CompromissosDBSchema.CompromissosTbl.Cols.ANO, ano);
        valores_compromisso.put(CompromissosDBSchema.CompromissosTbl.Cols.HORA, hora);
        valores_compromisso.put(CompromissosDBSchema.CompromissosTbl.Cols.MINUTO, minuto);
        valores_compromisso.put(CompromissosDBSchema.CompromissosTbl.Cols.DESCRICAO, descricao);

        mDatabase.insert(CompromissosDBSchema.CompromissosTbl.NOME_TBL, null, valores_compromisso);
    }

    public Cursor queryCompromissos(String clausulaWhere, String[] argsWhere){
        Cursor cursor = mDatabase.query(CompromissosDBSchema.CompromissosTbl.NOME_TBL,
                null,
                null,
                null,
                null,
                null,
                "ano ASC, mes ASC, dia ASC, hora ASC, minuto ASC"
        );
        return cursor;
    }

    void deleteTbl() {
        int delete;
        delete = mDatabase.delete(CompromissosDBSchema.CompromissosTbl.NOME_TBL, null, null);
    }
}