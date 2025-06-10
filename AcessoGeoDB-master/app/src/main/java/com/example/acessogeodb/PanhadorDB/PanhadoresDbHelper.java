package com.example.acessogeodb.PanhadorDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class PanhadoresDbHelper extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String NOME_DATABASE = "panhadoresDB";

    public PanhadoresDbHelper(Context context){
        super(context, NOME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + PanhadoresDbSchema.PanhadoresTbl.NOME_TBL + "(" +
                "_id integer PRIMARY KEY autoincrement," +
                PanhadoresDbSchema.PanhadoresTbl.Cols.NOME + "," +
                PanhadoresDbSchema.PanhadoresTbl.Cols.CPF + "," +
                PanhadoresDbSchema.PanhadoresTbl.Cols.NUMERO + "," +
                PanhadoresDbSchema.PanhadoresTbl.Cols.CHAVE_PIX + ")"
        );
    }

    public List<String> getNomesDosPanhadores() {
        List<String> nomes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nome FROM Panhadores", null);

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
        db.execSQL("DROP TABLE IF EXISTS " + PanhadoresDbSchema.PanhadoresTbl.NOME_TBL);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + PanhadoresDbSchema.PanhadoresTbl.NOME_TBL);
        onCreate(db);
    }
}