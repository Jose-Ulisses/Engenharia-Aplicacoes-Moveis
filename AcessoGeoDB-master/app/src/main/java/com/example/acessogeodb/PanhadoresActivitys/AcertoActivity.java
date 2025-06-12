package com.example.acessogeodb.PanhadoresActivitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.acessogeodb.PanhadorDB.PanhadorDB;
import com.example.acessogeodb.R;

public class AcertoActivity extends AppCompatActivity {

    PanhadorDB mPanhadorDb;
    private TextView mTextViewTodosPanhadores;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerto);

        mPanhadorDb = new PanhadorDB(getBaseContext());
        Cursor cursor = mPanhadorDb.queryPanhador((String) null, (String[]) null);
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    @SuppressLint("Range") String nome_panhador = cursor.getString(cursor.getColumnIndex("nome"));
                    mTextViewTodosPanhadores = new TextView(this);
                    mTextViewTodosPanhadores.setText(nome_panhador);
                    mTextViewTodosPanhadores.setTextSize(26);
                    mTextViewTodosPanhadores.setPadding(30, 5, 0, 100);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);

                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutAcerto);
                    linearLayout.addView(mTextViewTodosPanhadores);

                    mTextViewTodosPanhadores.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = InformacoesAcertoActivity.novoIntent(AcertoActivity.this);
                            intent.putExtra("nome_panhador", nome_panhador);
                            AcertoActivity.this.startActivityForResult(intent, 0);
                        }
                    });
                    cursor.moveToNext();
                }
            } finally {
                cursor.close();
            }
        }
    }
    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, AcertoActivity.class);
        return intent;
    }
}