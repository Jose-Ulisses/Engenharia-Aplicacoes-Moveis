package com.example.acessogeodb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.acessogeodb.PanhadoresActivitys.PanhadoresActivity;

public class DbSchema {
    public static final class PanhadoresTbl{
        public static final String NOME_TBL = "Panhadores";
        public static final class Cols{
            public static final String NOME = "nome";
            public static final String CPF = "cpf";
            public static final String NUMERO = "numero";
            public static final String CHAVE_PIX = "chavePix";
        }
    }

    public static class MainActivity extends AppCompatActivity {
        private static final String TAG = "MainActivity";
        private Button mBotaoColheita;
        private Button mBotaoPanhadores;

        public MainActivity(){

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            this.mBotaoColheita = (Button) findViewById(R.id.botao_colheita);
            this.mBotaoColheita.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view) {
                    setContentView(R.layout.activity_colheita);
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
}
