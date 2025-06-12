package com.example.acessogeodb;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.acessogeodb.ColheitaActivitys.ColheitaActivity;
import com.example.acessogeodb.ColheitaActivitys.Lavouras.LavouraActivity;
import com.example.acessogeodb.PanhadoresActivitys.PanhadoresActivity;

public class MainActivity extends AppCompatActivity {
    private Button mBotaoColheita;
    private Button mBotaoPanhadores;
    private Button mBotaoLavouras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mBotaoColheita = (Button) findViewById(R.id.botao_colheita);
        this.mBotaoColheita.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = ColheitaActivity.novoIntent(MainActivity.this);
                MainActivity.this.startActivityForResult(intent, 0);
            }
        });

        mBotaoLavouras = (Button) findViewById(R.id.botao_lavouras);
        mBotaoLavouras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = LavouraActivity.novoIntent(MainActivity.this);
                MainActivity.this.startActivityForResult(intent, 0);
            }
        });

        this.mBotaoPanhadores = (Button) findViewById(R.id.botao_panhadores);
        this.mBotaoPanhadores.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = PanhadoresActivity.novoIntent(MainActivity.this);
                MainActivity.this.startActivityForResult(intent, 0);
            }
        });
    }
}