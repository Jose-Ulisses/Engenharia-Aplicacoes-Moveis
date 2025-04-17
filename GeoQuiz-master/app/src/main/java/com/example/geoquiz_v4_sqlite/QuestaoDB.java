package com.example.geoquiz_v4_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

public class QuestaoDB {

    private Context mContext;
    private static Context mStaticContext;
    private SQLiteDatabase mDatabase;

    public QuestaoDB(Context contexto){
        mContext = contexto.getApplicationContext();
        mStaticContext = mContext;
        mDatabase = new QuestoesDBHelper(mContext).getWritableDatabase();
    }
    private static ContentValues getValoresConteudo(Questao q){
        ContentValues valores = new ContentValues();

        // pares chave-valor: nomes das colunas - valores
        valores.put(QuestoesDbSchema.QuestoesTbl.Cols.UUID, q.getId().toString());
        valores.put(QuestoesDbSchema.QuestoesTbl.Cols.TEXTO_QUESTAO,
                mStaticContext.getString(q.getTextoRespostaId())); // recupera valor do recurso string pelo id
        valores.put(QuestoesDbSchema.QuestoesTbl.Cols.QUESTAO_CORRETA, q.isRespostaCorreta());
        return valores;
    }
    public void addQuestao(Questao q){
        ContentValues valores = getValoresConteudo(q);
        mDatabase.insert(QuestoesDbSchema.QuestoesTbl.NOME, null, valores);
    }
    public void updateQuestao(Questao q){
        String uuidString = q.getId().toString();
        ContentValues valores = getValoresConteudo(q);
       // mDatabase.update(QuestoesDbSchema.QuestoesTbl.NOME, valores, QuestoesDbSchema.QuestoesTbl.Cols.UUID +" = ?",
        //        new String[] {uuidString});
    }
    public Cursor queryQuestao(String clausulaWhere, String[] argsWhere){
        Cursor cursor = mDatabase.query(QuestoesDbSchema.QuestoesTbl.NOME,
                null,  // todas as colunas
                    clausulaWhere,
                    argsWhere,
                null, // sem group by
                null, // sem having
                null  // sem order by
                );
                return cursor;
    }

    public void addResposta(UUID questaoId, boolean respostaCorreta, String string_resposta, boolean colou) {
        ContentValues valores_respostas = new ContentValues();

        valores_respostas.put(QuestoesDbSchema.RespostasTbl.Cols.UUID_RESPOSTA, questaoId.toString());
        valores_respostas.put(QuestoesDbSchema.RespostasTbl.Cols.RESPOSTA_CORRETA, respostaCorreta ? 1 : 0);
        valores_respostas.put(QuestoesDbSchema.RespostasTbl.Cols.RESPOSTA_OFERECIDA, string_resposta);
        valores_respostas.put(QuestoesDbSchema.RespostasTbl.Cols.COLOU, colou ? 1 : 0);

        mDatabase.insert(QuestoesDbSchema.RespostasTbl.NOME, null, valores_respostas);
    }

    public Cursor queryResposta(String clausulaWhere, String[] argsWhere){
        Cursor cursor = mDatabase.query(QuestoesDbSchema.RespostasTbl.NOME,
                null,  // todas as colunas
                clausulaWhere,
                argsWhere,
                null, // sem group by
                null, // sem having
                null  // sem order by
        );
        return cursor;
    }

    void deleteTbl(){
        int delete;
        delete = mDatabase.delete(QuestoesDbSchema.RespostasTbl.NOME, null, null);
    }

    void removeBanco(){
        int delete;
        delete = mDatabase.delete(
                QuestoesDbSchema.QuestoesTbl.NOME,
                null, null);
    }
}
