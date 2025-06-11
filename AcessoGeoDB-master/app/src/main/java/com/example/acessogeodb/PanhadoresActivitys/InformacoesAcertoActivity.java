package com.example.acessogeodb.PanhadoresActivitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.acessogeodb.ColheitaDB.ColheitaDB;
import com.example.acessogeodb.R;
import com.example.acessogeodb.TalhaoDB.TalhaoDB;

import java.util.Objects;

public class InformacoesAcertoActivity extends AppCompatActivity {

    ColheitaDB mColheitaDb;
    TalhaoDB mTalhaoDb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_acerto);

        String nome = getIntent().getStringExtra("nome_panhador");

        mColheitaDb = new ColheitaDB(getBaseContext());
        Cursor cursor = mColheitaDb.queryColheita((String) null, (String[]) null);
        if(cursor != null){
            try{
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    @SuppressLint("Range") String nome_panhador = cursor.getString(cursor.getColumnIndex("panhador"));
                    if(Objects.equals(nome_panhador, nome)){
                        @SuppressLint("Range") String lavoura = cursor.getString(cursor.getColumnIndex("lavoura"));
                        @SuppressLint("Range") String talhao = cursor.getString(cursor.getColumnIndex("talhao"));
                        @SuppressLint("Range") double quantidade = cursor.getDouble(cursor.getColumnIndex("quantidade"));

                        System.out.println(nome_panhador);
                        System.out.println(lavoura);
                        System.out.println(talhao);
                        System.out.println(quantidade);

                        cursor.moveToNext();
                    } else {
                        cursor.moveToNext();
                    }
                }
            } finally {
                cursor.close();
            }
        }
    }

    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, InformacoesAcertoActivity.class);
        return intent;
    }
}
