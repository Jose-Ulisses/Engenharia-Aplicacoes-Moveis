package com.example.acessogeodb.ColheitaActivitys;

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

public class ColheitasAnterioresActivity extends AppCompatActivity {

    ColheitaDB mColheitaDb;
    private TextView mTextViewDatasColheitas;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colheitas_anteriores);

        mColheitaDb = new ColheitaDB(getBaseContext());
        Cursor cursor = mColheitaDb.queryColheita((String) null, (String[]) null);
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    @SuppressLint("Range") String lavoura = cursor.getString(cursor.getColumnIndex("lavoura"));
                    @SuppressLint("Range") String talhao = cursor.getString(cursor.getColumnIndex("talhao"));
                    @SuppressLint("Range") String panhador = cursor.getString(cursor.getColumnIndex("panhador"));
                    @SuppressLint("Range") String quantidade = cursor.getString(cursor.getColumnIndex("quantidade"));
                    @SuppressLint("Range") String data = cursor.getString(cursor.getColumnIndex("data"));

                    mTextViewDatasColheitas = new TextView(this);
                    mTextViewDatasColheitas.setText("");
                    mTextViewDatasColheitas.append("Lavoura: " + lavoura + "\n" +
                            "Talhão: " + talhao + "\n" +
                            "Panhador: " + panhador + "\n" +
                            "Quantidade: " + quantidade + "\n" +
                            "Data: " + data + "\n");

                    mTextViewDatasColheitas.setTextSize(20);
                    mTextViewDatasColheitas.setPadding(30, 5, 0, 100);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);

                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutColheitasAnteriores);
                    linearLayout.addView(mTextViewDatasColheitas);

                    cursor.moveToNext();
                }
            } finally {
                cursor.close();
            }
        }
    }

    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, ColheitasAnterioresActivity.class);
        return intent;
    }
}