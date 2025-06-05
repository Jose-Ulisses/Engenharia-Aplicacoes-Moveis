package com.example.acessogeodb.ColheitaActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.acessogeodb.R;

public class ColheitaActivity extends AppCompatActivity {

    private Button mBotaoLavouras;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colheita);

        this.mBotaoLavouras = (Button) findViewById(R.id.botao_lavouras);
        this.mBotaoLavouras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = LavouraActivity.novoIntent(ColheitaActivity.this);
                ColheitaActivity.this.startActivityForResult(intent, 0);
                System.out.println("teste");

            }
        });
    }

    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, ColheitaActivity.class);
        return intent;
    }
}
