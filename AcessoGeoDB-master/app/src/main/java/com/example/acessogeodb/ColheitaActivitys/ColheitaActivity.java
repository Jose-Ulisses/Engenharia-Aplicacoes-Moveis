package com.example.acessogeodb.ColheitaActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.acessogeodb.ColheitaActivitys.Lavouras.LavouraActivity;
import com.example.acessogeodb.R;

public class ColheitaActivity extends AppCompatActivity {

    private Button mBotaoAdicionarColheita;
    private Button mBotaoLavouras;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colheita);

        mBotaoAdicionarColheita = (Button) findViewById(R.id.botao_adicionar_colheita);
        mBotaoAdicionarColheita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AdicionarColheitaActivity.novoIntent(ColheitaActivity.this);
                ColheitaActivity.this.startActivityForResult(intent, 0);

            }
        });

        mBotaoLavouras = (Button) findViewById(R.id.botao_lavouras);
        mBotaoLavouras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = LavouraActivity.novoIntent(ColheitaActivity.this);
                ColheitaActivity.this.startActivityForResult(intent, 0);
            }
        });
    }

    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, ColheitaActivity.class);
        return intent;
    }
}
