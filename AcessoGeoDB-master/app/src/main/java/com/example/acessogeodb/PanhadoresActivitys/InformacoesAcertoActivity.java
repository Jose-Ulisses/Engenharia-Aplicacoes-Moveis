package com.example.acessogeodb.PanhadoresActivitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.acessogeodb.ColheitaDB.ColheitaDB;
import com.example.acessogeodb.R;
import com.example.acessogeodb.TalhaoDB.TalhaoDB;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;

public class InformacoesAcertoActivity extends AppCompatActivity {
    ColheitaDB mColheitaDb;
    TalhaoDB mTalhaoDb;
    double preco_talhao;
    double totalAcerto;

    @SuppressLint("Range")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_acerto);

        Map<String, Double> totaisPorTalhao = new HashMap<>();
        Map<String, String> lavouraPorTalhao = new HashMap<>();

        String nome = getIntent().getStringExtra("nome_panhador");

        mColheitaDb = new ColheitaDB(getBaseContext());
        mTalhaoDb = new TalhaoDB(getBaseContext());

        Cursor cursor = mColheitaDb.queryColheita((String) null, (String[]) null);
        Cursor cursorTalhao = mTalhaoDb.queryTalhao((String) null, (String[]) null);
        if(cursor != null){
            try{
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){

                     String nome_panhador = cursor.getString(cursor.getColumnIndex("panhador"));
                    if(Objects.equals(nome_panhador, nome)){
                        String lavoura = cursor.getString(cursor.getColumnIndex("lavoura"));
                        String talhao = cursor.getString(cursor.getColumnIndex("talhao"));
                        double quantidade = cursor.getDouble(cursor.getColumnIndex("quantidade"));

                        if(cursorTalhao != null){
                            cursorTalhao.moveToFirst();
                            while(!cursorTalhao.isAfterLast()){
                                String nome_talhao = cursorTalhao.getString(cursorTalhao.getColumnIndex("nomeTalhao"));
                                if(Objects.equals(talhao, nome_talhao)){
                                    preco_talhao = cursorTalhao.getDouble(cursorTalhao.getColumnIndex("preco"));
                                    break;
                                }
                                cursorTalhao.moveToNext();
                            }
                        }

                        double valorTotal = quantidade * preco_talhao;
                        String chave = lavoura + "##" + talhao;

                        totaisPorTalhao.put(chave, totaisPorTalhao.getOrDefault(chave, 0.0) + valorTotal);
                        lavouraPorTalhao.put(chave, lavoura);

                        totalAcerto += valorTotal;
                    }
                    cursor.moveToNext();
                }
            } finally {
                cursor.close();
                cursorTalhao.close();

                LinearLayout linearLayout = findViewById(R.id.linearLayoutInformacoesAcerto);

                for(String chave : totaisPorTalhao.keySet()){
                    String[] partes = chave.split("##");
                    String lavoura = partes[0];
                    String talhao = partes[1];
                    double total = totaisPorTalhao.get(chave);

                    TextView textView = new TextView(this);
                    textView.setText("Lavoura: " + lavoura + "\nTalhao: " + talhao + "\nTotal: " + total);
                    textView.setTextSize(26);
                    textView.setPadding(30, 5, 0, 100);
                    linearLayout.addView(textView);
                }

                TextView totalFinal = new TextView(this);
                totalFinal.setText("Total = " + totalAcerto);
                totalFinal.setTextSize(30);
                totalFinal.setPadding(30, 50, 0, 100);
                linearLayout.addView(totalFinal);
            }
        }
    }

    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, InformacoesAcertoActivity.class);
        return intent;
    }
}