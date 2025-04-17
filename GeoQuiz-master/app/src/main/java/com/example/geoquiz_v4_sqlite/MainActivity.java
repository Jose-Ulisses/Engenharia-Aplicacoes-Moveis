package com.example.geoquiz_v4_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

/*
  Modelo de projeto para a Atividade 1.
  Será preciso adicionar o cadastro das respostas do usuário ao Quiz, conforme
  definido no Canvas.

  GitHub: https://github.com/udofritzke/GeoQuiz
 */

public class MainActivity extends AppCompatActivity {
    private Button mBotaoVerdadeiro;
    private Button mBotaoFalso;
    private Button mBotaoProximo;
    private Button mBotaoCadastra;
    private Button mBotaoMostra;
    private Button mBotaoDeleta;

    private Button mBotaoCola;

    private TextView mTextViewQuestao;
    private TextView mTextViewQuestoesArmazenadas;

    private static final String TAG = "QuizActivity";
    private static final String CHAVE_INDICE = "INDICE";
    private static final int CODIGO_REQUISICAO_COLA = 0;

    private Questao[] mBancoDeQuestoes = new Questao[]{
            new Questao(R.string.questao_suez, true),
            new Questao(R.string.questao_alemanha, false)
    };

    QuestaoDB mQuestoesDb;

    private int mIndiceAtual = 0;

    private boolean mEhColador;

    @Override
    protected void onCreate(Bundle instanciaSalva) {
        super.onCreate(instanciaSalva);
        setContentView(R.layout.activity_main);
        //Log.d(TAG, "onCreate()");
        if (instanciaSalva != null) {
            mIndiceAtual = instanciaSalva.getInt(CHAVE_INDICE, 0);
        }

        mTextViewQuestao = (TextView) findViewById(R.id.view_texto_da_questao);
        atualizaQuestao();

        mBotaoVerdadeiro = (Button) findViewById(R.id.botao_verdadeiro);
        // utilização de classe anônima interna
        mBotaoVerdadeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {verificaResposta(true, "verdadeiro");}
        });

        mBotaoFalso = (Button) findViewById(R.id.botao_falso);
        mBotaoFalso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {verificaResposta(false, "falso");
            }
        });
        mBotaoProximo = (Button) findViewById(R.id.botao_proximo);
        mBotaoProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIndiceAtual = (mIndiceAtual + 1) % mBancoDeQuestoes.length;
                mEhColador = false;
                atualizaQuestao();
            }
        });

        mBotaoCola = (Button) findViewById(R.id.botao_cola);
        mBotaoCola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inicia ColaActivity
                // Intent intent = new Intent(MainActivity.this, ColaActivity.class);
                boolean respostaEVerdadeira = mBancoDeQuestoes[mIndiceAtual].isRespostaCorreta();
                Intent intent = ColaActivity.novoIntent(MainActivity.this, respostaEVerdadeira);
                //startActivity(intent);
                startActivityForResult(intent, CODIGO_REQUISICAO_COLA);
            }
        });

        mBotaoMostra = (Button) findViewById(R.id.botao_mostra_questoes);
        mBotaoMostra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Acesso ao SQLite

                if (mTextViewQuestoesArmazenadas == null)
                    mTextViewQuestoesArmazenadas = (TextView) findViewById(R.id.texto_questoes_a_apresentar);
                else
                    mTextViewQuestoesArmazenadas.setText("");

                mQuestoesDb = new QuestaoDB(getBaseContext());

                Cursor cursor = mQuestoesDb.queryResposta(null, null);

                if (cursor != null) {
                    try {
                        cursor.moveToFirst();
                        while (!cursor.isAfterLast()) {

                            String texto_UUID = cursor.getString(cursor.getColumnIndex(QuestoesDbSchema.RespostasTbl.Cols.UUID_RESPOSTA));
                            String texto_respostaCorreta = cursor.getString(cursor.getColumnIndex(QuestoesDbSchema.RespostasTbl.Cols.RESPOSTA_CORRETA));
                            String texto_respostaOferecida = cursor.getString(cursor.getColumnIndex(QuestoesDbSchema.RespostasTbl.Cols.RESPOSTA_OFERECIDA));
                            String texto_Colou = cursor.getString(cursor.getColumnIndex(QuestoesDbSchema.RespostasTbl.Cols.COLOU));

                            mTextViewQuestoesArmazenadas.append("\nUUID: " + texto_UUID + "\n" +
                                                                "RESPOSTA_CORRETA: " + texto_respostaCorreta + "\n" +
                                                                "RESPOSTA_OFERECIDA: " + texto_respostaOferecida + "\n" +
                                                                "COLOU: " + texto_Colou + "\n");

                            cursor.moveToNext();
                        }

                    } finally {
                        cursor.close();
                    }
                }

            } //fechamento onClick
        }); //fechamento listener

        mBotaoDeleta = (Button) findViewById(R.id.botao_deleta);
        mBotaoDeleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                  Acesso ao SQLite
                */
                if (mQuestoesDb != null) {
                    mQuestoesDb.deleteTbl();
                    if (mTextViewQuestoesArmazenadas == null) {
                        mTextViewQuestoesArmazenadas = (TextView) findViewById(R.id.texto_questoes_a_apresentar);
                    }
                    mTextViewQuestoesArmazenadas.setText("");
                }
            }
        });
    }

    private void atualizaQuestao() {
        int questao = mBancoDeQuestoes[mIndiceAtual].getTextoRespostaId();
        mTextViewQuestao.setText(questao);
    }

    private void verificaResposta(boolean respostaPressionada, String string_resposta) {
        UUID uuid = mBancoDeQuestoes[mIndiceAtual].getId();
        boolean respostaCorreta = mBancoDeQuestoes[mIndiceAtual].isRespostaCorreta();

        QuestaoDB respostasDB = new QuestaoDB(this);
        respostasDB.addResposta(uuid, respostaCorreta, string_resposta , mEhColador);

        int idMensagemResposta = 0;

        if (mEhColador) {
            idMensagemResposta = R.string.toast_julgamento;
        } else {
            if (respostaPressionada == respostaCorreta) {
                idMensagemResposta = R.string.toast_correto;
            } else
                idMensagemResposta = R.string.toast_incorreto;
        }
        Toast.makeText(this, idMensagemResposta, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle instanciaSalva) {
        super.onSaveInstanceState(instanciaSalva);
        Log.i(TAG, "onSaveInstanceState()");
        instanciaSalva.putInt(CHAVE_INDICE, mIndiceAtual);
    }

    @Override
    protected void onActivityResult(int codigoRequisicao, int codigoResultado, Intent dados) {
        if (codigoResultado != Activity.RESULT_OK) {
            return;
        }
        if (codigoRequisicao == CODIGO_REQUISICAO_COLA) {
            if (dados == null) {
                return;
            }
            mEhColador = ColaActivity.foiMostradaResposta(dados);
        }
    }
}