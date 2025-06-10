package com.example.acessogeodb.ColheitaActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.acessogeodb.PanhadoresDbHelper;
import com.example.acessogeodb.R;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.example.acessogeodb.LavourasDbHelper;
import com.example.acessogeodb.TalhoesDbHelper;
import java.util.List;
import java.util.Objects;

public class AdicionarColheitaActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteLavoura;
    AutoCompleteTextView autoCompleteTalhao;
    AutoCompleteTextView autoCompletePanhador;
    LavourasDbHelper lavouradbHelper;
    TalhoesDbHelper talhaoDbHelper;
    PanhadoresDbHelper panhadorDbHelper;
    ArrayAdapter<String> adapterItems;

    String nomeLavoura = "temp";
    String nomeTalhao;
    String nomePanhador;
    String aux;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_colheita);

        autoCompleteLavoura = findViewById(R.id.auto_complete_lavoura);
        lavouradbHelper = new LavourasDbHelper(this);
        List<String> lavoura = lavouradbHelper.getNomesDasLavouras();

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item_lavoura, lavoura);
        autoCompleteLavoura.setAdapter(adapterItems);
        autoCompleteLavoura.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nomeLavoura = adapterView.getItemAtPosition(i).toString();

                autoCompleteTalhao = findViewById(R.id.auto_complete_talhao);
                autoCompleteTalhao.setText("");
                talhaoDbHelper = new TalhoesDbHelper(AdicionarColheitaActivity.this);
                List<String> talhao = talhaoDbHelper.getNomeDosTalhoes(nomeLavoura);

                adapterItems = new ArrayAdapter<>(AdicionarColheitaActivity.this, R.layout.list_item_lavoura, talhao);
                autoCompleteTalhao.setAdapter(adapterItems);
                autoCompleteTalhao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        nomeTalhao = adapterView.getItemAtPosition(i).toString();
                    }
                });

            }
        });

        autoCompletePanhador = findViewById(R.id.auto_complete_panhador);
        panhadorDbHelper = new PanhadoresDbHelper(this);
        List<String> panhador = panhadorDbHelper.getNomesDosPanhadores();

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item_panhador, panhador);
        autoCompletePanhador.setAdapter(adapterItems);
        autoCompletePanhador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nomePanhador = adapterView.getItemAtPosition(i).toString();
            }
        });
    }



    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, AdicionarColheitaActivity.class);
        return intent;
    }
}
