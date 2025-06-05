package com.example.acessogeodb.ColheitaActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.acessogeodb.R;

public class LavouraActivity extends AppCompatActivity {

    private Button mBotaoAdicionarLavoura;
    private Button mBotaoTodasLavouras;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lavouras);

        mBotaoAdicionarLavoura = (Button) findViewById(R.id.botao_adicionar_lavoura);
        mBotaoAdicionarLavoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AdicionarLavouraActivity.novoIntent(LavouraActivity.this);
                LavouraActivity.this.startActivityForResult(intent, 0);
            }
        });

        mBotaoTodasLavouras = (Button) findViewById(R.id.botao_todas_lavouras);
        mBotaoTodasLavouras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = TodasLavourasActivity.novoIntent(LavouraActivity.this);
                LavouraActivity.this.startActivityForResult(intent, 0);
            }
        });
    }

    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, LavouraActivity.class);
        return intent;
    }
}


