package com.example.acessogeodb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class LavourasDbHelper extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String NOME_DATABASE = "lavourasDB";

    public LavourasDbHelper(Context context){
        super(context, NOME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + LavourasDbSchema.LavourasTbl.NOME_TBL + "(" +
                "_id integer PRIMARY KEY autoincrement," +
                LavourasDbSchema.LavourasTbl.Cols.NOME + "," +
                LavourasDbSchema.LavourasTbl.Cols.TOTAL + ")"
        );
    }

    public List<String> getNomesDasLavouras() {
        List<String> nomes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nome FROM Lavouras", null);

        if (cursor.moveToFirst()) {
            do {
                nomes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return nomes;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        db.execSQL("DROP TABLE IF EXISTS " + LavourasDbSchema.LavourasTbl.NOME_TBL);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + LavourasDbSchema.LavourasTbl.NOME_TBL);
        onCreate(db);
    }
}