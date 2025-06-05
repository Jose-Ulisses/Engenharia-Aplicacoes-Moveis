package com.example.acessogeodb.PanhadoresActivitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.acessogeodb.PanhadorDB;
import com.example.acessogeodb.R;

public class TodosPanhadoresActivity extends AppCompatActivity {

    PanhadorDB mPanhadorDb;
    private TextView mTextViewTodosPanhadores;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_todos_panhadores);


        TodosPanhadoresActivity.this.mPanhadorDb = new PanhadorDB(TodosPanhadoresActivity.this.getBaseContext());
        Cursor cursor = TodosPanhadoresActivity.this.mPanhadorDb.queryPanhador((String)null, (String[])null);
        if(cursor != null){
            try{
                cursor.moveToFirst();

                while(!cursor.isAfterLast()) {
                 @SuppressLint("Range") String nome_panhador = cursor.getString(cursor.getColumnIndex("nome"));

                    mTextViewTodosPanhadores = new TextView(this);
                    mTextViewTodosPanhadores.setText(nome_panhador);
                    mTextViewTodosPanhadores.setTextSize(26);
                    mTextViewTodosPanhadores.setPadding(30, 5, 0, 100);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);

                    mTextViewTodosPanhadores.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = InformacoesPanhadorActivity.novoIntent(TodosPanhadoresActivity.this);
                            intent.putExtra("nome_panhador", nome_panhador);
                            TodosPanhadoresActivity.this.startActivityForResult(intent, 0);
                        }
                    });

                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
                    linearLayout.addView(mTextViewTodosPanhadores);

                    cursor.moveToNext();
                }
            } finally {
                cursor.close();
            }
        }
    }


    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, TodosPanhadoresActivity.class);
        return intent;
    }
}
