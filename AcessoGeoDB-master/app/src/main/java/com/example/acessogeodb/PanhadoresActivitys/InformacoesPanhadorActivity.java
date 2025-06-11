package com.example.acessogeodb.PanhadoresActivitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.acessogeodb.PanhadorDB.PanhadorDB;
import com.example.acessogeodb.R;

import java.util.Objects;

public class InformacoesPanhadorActivity extends AppCompatActivity {
    PanhadorDB mPanhadorDb;
    TextView mInformacoesPanhador;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_panhador);

        String nome = getIntent().getStringExtra("nome_panhador");

        InformacoesPanhadorActivity.this.mPanhadorDb = new PanhadorDB(InformacoesPanhadorActivity.this.getBaseContext());
        Cursor cursor = InformacoesPanhadorActivity.this.mPanhadorDb.queryPanhador((String)null, (String[])null);

        if(cursor != null){
            try{
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    @SuppressLint("Range") String nome_panhador = cursor.getString(cursor.getColumnIndex("nome"));

                    if (Objects.equals(nome_panhador, nome)) {
                        @SuppressLint("Range") String cpf_panhador = cursor.getString(cursor.getColumnIndex("cpf"));
                        @SuppressLint("Range") String numero_panhador = cursor.getString(cursor.getColumnIndex("numero"));
                        @SuppressLint("Range") String chavePix_panhador = cursor.getString(cursor.getColumnIndex("chavePix"));


                        mInformacoesPanhador = (TextView) findViewById(R.id.textView_info_panhador);
                        mInformacoesPanhador.append(
                                "Nome: " + nome_panhador + "\n" +
                                        "CPF: " + cpf_panhador + "\n" +
                                        "Número: " + numero_panhador + "\n" +
                                        "Chave Pix: " + chavePix_panhador);
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
        Intent intent = new Intent(packageContext, InformacoesPanhadorActivity.class);
        return intent;
    }
}
