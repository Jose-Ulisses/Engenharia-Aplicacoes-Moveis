package com.example.acessogeodb.TalhaoDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class TalhoesDbHelper extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String NOME_DATABASE = "talhoesDB";

    public TalhoesDbHelper(Context context){
        super(context, NOME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TalhoesDbSchema.TalhoesTbl.NOME_TBL + "(" +
                "_id integer PRIMARY KEY autoincrement," +
                TalhoesDbSchema.TalhoesTbl.Cols.NOME_LAVOURA + "," +
                TalhoesDbSchema.TalhoesTbl.Cols.NOME_TALHAO + "," +
                TalhoesDbSchema.TalhoesTbl.Cols.PRECO + "," +
                TalhoesDbSchema.TalhoesTbl.Cols.TOTAL + ")"
        );
    }

    public List<String> getNomeDosTalhoes(String lavoura) {
        List<String> nomes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nomeTalhao FROM Talhoes WHERE nomeLavoura = ?", new String[]{lavoura});
        if(cursor.moveToFirst()){
            do{
                nomes.add(cursor.getString(0));
            } while(cursor.moveToNext());
        }
        cursor.close();
        return nomes;
    }

    public double getTotalTalhao(String nomeTalhao){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT total FROM Talhoes WHERE nomeTalhao = ?", new String[]{nomeTalhao});
        double total = 0.0;

        if(cursor.moveToFirst()){
            total = cursor.getDouble(0);
        }
        cursor.close();
        return total;
    }

    public boolean atualizarTotalTalhao(String nomeTalhao, double novoTotal){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("total", novoTotal);

        int linhasAfetadas = db.update("Talhoes", values, "nomeTalhao = ?", new String[]{nomeTalhao});
        return linhasAfetadas > 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        db.execSQL("DROP TABLE IF EXISTS " + TalhoesDbSchema.TalhoesTbl.NOME_TBL);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TalhoesDbSchema.TalhoesTbl.NOME_TBL);
        onCreate(db);
    }
}