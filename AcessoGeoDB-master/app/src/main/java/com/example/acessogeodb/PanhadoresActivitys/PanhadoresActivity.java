package com.example.acessogeodb.PanhadoresActivitys;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.acessogeodb.R;


public class PanhadoresActivity extends AppCompatActivity{
    private Button mTodosPanhadores;
    private Button mBotaoAdicionar;
    private Button mBotaoremove;


    public PanhadoresActivity(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_panhadores);

        mBotaoAdicionar = (Button) findViewById(R.id.botao_adicionar_panhador);
        mBotaoAdicionar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = AdicionarPanhadoresActivity.novoIntent(PanhadoresActivity.this);
                PanhadoresActivity.this.startActivityForResult(intent, 0);
            }
        });

        mTodosPanhadores = (Button) findViewById(R.id.botao_visualisar_panhadores);
        mTodosPanhadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = TodosPanhadoresActivity.novoIntent(PanhadoresActivity.this);
                PanhadoresActivity.this.startActivityForResult(intent, 0);
            }
        });

    }

    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, PanhadoresActivity.class);
        return intent;
    }

}
