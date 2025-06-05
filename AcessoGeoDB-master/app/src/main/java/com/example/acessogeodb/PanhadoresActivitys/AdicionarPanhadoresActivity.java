package com.example.acessogeodb.PanhadoresActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.acessogeodb.PanhadorDB;
import com.example.acessogeodb.R;

public class AdicionarPanhadoresActivity extends AppCompatActivity {

    private Button mBotaoAdiciona;
    EditText inputNome, inputCpf, inputNumero, inputChavePix;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_adicionar_panhadores);

        mBotaoAdiciona = (Button) findViewById(R.id.botao_adiciona);
        mBotaoAdiciona.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                inputNome = (EditText) findViewById(R.id.input_nome);
                inputCpf = (EditText) findViewById(R.id.input_cpf);
                inputNumero = (EditText) findViewById(R.id.input_numero);
                inputChavePix = (EditText) findViewById(R.id.input_chave_pix);

                adicionaPanhador(inputNome.getText().toString(), inputCpf.getText().toString(),
                        inputNumero.getText().toString(), inputChavePix.getText().toString());

                finish();
            }
        });
    }

    private void adicionaPanhador(String nome, String CPF, String numero, String chavePix){
        PanhadorDB panhadorDB = new PanhadorDB(this);
        panhadorDB.addPanhador(nome, CPF, numero, chavePix);
    }

    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, AdicionarPanhadoresActivity.class);
        return intent;
    }
}
