package com.example.acessogeodb.ColheitaActivitys.Lavouras;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.acessogeodb.LavouraDB.LavouraDB;
import com.example.acessogeodb.R;
import androidx.appcompat.app.AppCompatActivity;

public class TodasLavourasActivity extends AppCompatActivity {

    LavouraDB mLavouraDb;
    private TextView mTextViewTodasLavouras;

    protected void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setContentView(R.layout.activity_todas_lavouras);

        TodasLavourasActivity.this.mLavouraDb = new LavouraDB(TodasLavourasActivity.this.getBaseContext());
        Cursor cursor = TodasLavourasActivity.this.mLavouraDb.queryLavoura((String) null, (String[]) null);
        if (cursor != null) {
            try {
                cursor.moveToFirst();

                while (!cursor.isAfterLast()) {
                    @SuppressLint("Range") String nomeLavoura = cursor.getString(cursor.getColumnIndex("nome"));

                    mTextViewTodasLavouras = new TextView(this);
                    mTextViewTodasLavouras.setText(nomeLavoura);
                    mTextViewTodasLavouras.setTextSize(26);
                    mTextViewTodasLavouras.setPadding(30, 5, 0, 100);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);

                    mTextViewTodasLavouras.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = InformacoesLavouraActivity.novoIntent(TodasLavourasActivity.this);
                            intent.putExtra("nome_lavoura", nomeLavoura);
                            TodasLavourasActivity.this.startActivityForResult(intent, 0);
                        }
                    });

                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutLavouras);
                    linearLayout.addView(mTextViewTodasLavouras);

                    cursor.moveToNext();
                }
            } finally {
                cursor.close();
            }
        }
    }

    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, TodasLavourasActivity.class);
        return intent;
    }
}
