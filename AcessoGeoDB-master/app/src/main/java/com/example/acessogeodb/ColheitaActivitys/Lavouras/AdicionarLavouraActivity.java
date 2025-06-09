package com.example.acessogeodb.ColheitaActivitys.Lavouras;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.acessogeodb.LavouraDB;
import com.example.acessogeodb.R;
import androidx.appcompat.app.AppCompatActivity;

public class AdicionarLavouraActivity extends AppCompatActivity {

    private Button mBotaoAdiciona;
    EditText inputNome;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_adicionar_lavoura);

        mBotaoAdiciona = (Button) findViewById(R.id.botao_adiciona_lavoura);
        mBotaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputNome = (EditText) findViewById(R.id.input_nome_lavoura);
                adicionaLavoura(inputNome.getText().toString());
                finish();
            }
        });
    }

    private void adicionaLavoura(String nome){
        LavouraDB lavouraDB = new LavouraDB(this);
        lavouraDB.addLavoura(nome);
    }

    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, AdicionarLavouraActivity.class);
        return intent;
    }

}
