package com.example.acessogeodb.PanhadoresActivitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.acessogeodb.PanhadorDB.PanhadorDB;
import com.example.acessogeodb.PanhadorDB.PanhadoresDbHelper;
import com.example.acessogeodb.R;
import java.util.Objects;

public class InformacoesPanhadorActivity extends AppCompatActivity {
    PanhadorDB mPanhadorDb;
    PanhadoresDbHelper dbhlper = new PanhadoresDbHelper(this);
    TextView mInformacoesPanhador;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_panhador);

        String nome = getIntent().getStringExtra("nome_panhador");

        InformacoesPanhadorActivity.this.mPanhadorDb = new PanhadorDB(InformacoesPanhadorActivity.this.getBaseContext());
        //Cursor cursor = InformacoesPanhadorActivity.this.mPanhadorDb.queryNomePanhador((String[])nome, (String)null , (String[])null);
        Cursor cursor = dbhlper.getPanhador(nome);

        if(cursor != null){
            try{
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    String nomePanhador = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
                    String cpf = cursor.getString(cursor.getColumnIndexOrThrow("cpf"));
                    String numero = cursor.getString(cursor.getColumnIndexOrThrow("numero"));
                    String chavePix = cursor.getString(cursor.getColumnIndexOrThrow("chavePix"));

                    mInformacoesPanhador = (TextView) findViewById(R.id.textView_info_panhador);
                    mInformacoesPanhador.append(
                            "Nome: " + nomePanhador + "\n" +
                            "CPF: " + cpf + "\n" +
                            "Numero: " + numero + "\n" +
                            "ChavePix: " + chavePix
                    );

                    cursor.moveToNext();
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