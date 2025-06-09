package com.example.acessogeodb.ColheitaActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.acessogeodb.R;

public class AdicionarColheitaActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_colheita);


    }


    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, AdicionarColheitaActivity.class);
        return intent;
    }
}
