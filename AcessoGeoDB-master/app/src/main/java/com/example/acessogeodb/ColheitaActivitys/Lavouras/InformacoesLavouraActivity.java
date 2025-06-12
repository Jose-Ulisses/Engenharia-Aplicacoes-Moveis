package com.example.acessogeodb.ColheitaActivitys.Lavouras;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.acessogeodb.LavouraDB.LavouraDB;
import com.example.acessogeodb.R;
import com.example.acessogeodb.TalhaoDB.TalhaoDB;
import java.util.Objects;

public class InformacoesLavouraActivity extends AppCompatActivity {
    LavouraDB mLavouraDb;
    TalhaoDB mTalhaoDb;
    private TextView mTextViewInformacoesTalhao;
    private TextView mTextViewInformacoesLavoura;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_lavouras);

        String nomeLavoura = getIntent().getStringExtra("nome_lavoura");

        mLavouraDb = new LavouraDB(getBaseContext());
        Cursor cursorLavoura = mLavouraDb.queryLavoura((String)null, (String[])null);

        mTalhaoDb = new TalhaoDB(getBaseContext());
        Cursor cursorTalhao = mTalhaoDb.queryTalhao((String) null, (String[]) null);

        if(cursorTalhao != null){
            try{
                cursorTalhao.moveToFirst();
                while(!cursorTalhao.isAfterLast()){
                    @SuppressLint("Range") String nomeLavouraTalhao = cursorTalhao.getString(cursorTalhao.getColumnIndex("nomeLavoura"));
                    if(Objects.equals(nomeLavoura, nomeLavouraTalhao)){
                        @SuppressLint("Range") String nomeTalhao = cursorTalhao.getString(cursorTalhao.getColumnIndex("nomeTalhao"));
                        @SuppressLint("Range") double totalTalhao = cursorTalhao.getDouble(cursorTalhao.getColumnIndex("total"));

                        mTextViewInformacoesTalhao = new TextView(this);
                        mTextViewInformacoesTalhao.setText("");
                        mTextViewInformacoesTalhao.setTextSize(26);
                        mTextViewInformacoesTalhao.setPadding(30, 5, 0, 100);

                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);

                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutInformacoesLavouras);
                        linearLayout.addView(mTextViewInformacoesTalhao);
                        mTextViewInformacoesTalhao.append(nomeTalhao + " = " + totalTalhao);

                        cursorTalhao.moveToNext();
                    } else
                        cursorTalhao.moveToNext();
                }
            } finally {
                cursorTalhao.close();
            }
        }

        if(cursorLavoura != null){
            try{
                cursorLavoura.moveToFirst();
                while(!cursorLavoura.isAfterLast()){
                    @SuppressLint("Range") String nome = cursorLavoura.getString(cursorLavoura.getColumnIndex("nome"));
                    if(Objects.equals(nomeLavoura, nome)){
                        @SuppressLint("Range") double totalLavoura = cursorLavoura.getDouble(cursorLavoura.getColumnIndex("total"));

                        mTextViewInformacoesLavoura = new TextView(this);
                        mTextViewInformacoesLavoura.setText("");
                        mTextViewInformacoesLavoura.setTextSize(26);
                        mTextViewInformacoesLavoura.setPadding(30, 5, 0, 100);

                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);

                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutInformacoesLavouras);
                        linearLayout.addView(mTextViewInformacoesLavoura);
                        mTextViewInformacoesLavoura.append("TOTAL DA LAVOURA = " + totalLavoura);

                        cursorLavoura.moveToNext();
                    } else
                        cursorLavoura.moveToNext();
                }
            } finally {
                cursorLavoura.close();
            }
        }
    }

    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, InformacoesLavouraActivity.class);
        return intent;
    }
}