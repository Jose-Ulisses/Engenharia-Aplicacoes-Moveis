package com.example.acessogeodb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button mBotaoColheita;
    private Button mBotaoPanhadores;
    private Button mTodosPanhadores;
    private Button mAdicionar;
    private Button mRemover;
    private Button mBotaoAdiciona;
    EditText inputText;

    Panhador funcionario;

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
                setContentView(R.layout.activity_panhadores);
                mAdicionar = (Button) findViewById(R.id.botao_adicionar_panhador);
                mAdicionar.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view) {
                        setContentView(R.layout.adicionar_panhadores);

                        mBotaoAdiciona = (Button) findViewById(R.id.botao_adiciona);
                        mBotaoAdiciona.setOnClickListener(new View.OnClickListener(){
                            public void onClick(View view){

                                inputText = (EditText) findViewById(R.id.input_nome);
                                String nomeInput = inputText.getText().toString();

                                inputText = (EditText) findViewById(R.id.input_cpf);
                                String cpfInput = inputText.getText().toString();

                                inputText = (EditText) findViewById(R.id.input_numero);
                                String numeroInput = inputText.getText().toString();

                                inputText = (EditText) findViewById(R.id.input_chave_pix);
                                String chavePixInput = inputText.getText().toString();

                                adicionaPanhador(nomeInput, cpfInput, numeroInput, chavePixInput);
                            }
                        });
                    }
                });
            }
        });

    }
    private void adicionaPanhador(String nome, String CPF, String numero, String chavePix){
        PanhadorDB panhadorDB = new PanhadorDB(this);

        panhadorDB.addPanhador(nome, CPF, numero, chavePix);
    }

}
