package com.example.acessogeodb.ColheitaActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.acessogeodb.ColheitaDB.ColheitaDB;
import com.example.acessogeodb.PanhadorDB.PanhadoresDbHelper;
import com.example.acessogeodb.R;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.acessogeodb.LavouraDB.LavourasDbHelper;
import com.example.acessogeodb.TalhaoDB.TalhoesDbHelper;

import java.util.Calendar;
import java.util.List;

public class AdicionarColheitaActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteLavoura;
    AutoCompleteTextView autoCompleteTalhao;
    AutoCompleteTextView autoCompletePanhador;
    LavourasDbHelper lavouradbHelper;
    TalhoesDbHelper talhaoDbHelper;
    PanhadoresDbHelper panhadorDbHelper;
    ArrayAdapter<String> adapterItems;

    private Button mBotaoAdiciona;

    Double quantidade;
    String nomeLavoura = "temp";
    String nomeTalhao;
    String nomePanhador;
    String current = "";
    String ddmmyyyy = "DDMMYYYY";
    String aux;
    private Calendar cal = Calendar.getInstance();
    EditText editTextData;
    EditText editTextQuantidade;

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

        editTextData = (EditText) findViewById(R.id.input_data);
        editTextData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d]", "");

                    String cleanC = current.replaceAll("[^\\d]", "");
                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }

                    if (clean.equals(cleanC))
                        sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    } else {
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon - 1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);

                        day = (day > cal.getActualMaximum(Calendar.DATE)) ? cal.getActualMaximum(Calendar.DATE) : day;
                        clean = String.format("%02d%02d%04d", day, mon, year);
                    }

                    clean = String.format("%s/%s/%s",
                            clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    editTextData.setText(current);
                    editTextData.setSelection(sel < current.length() ? sel : current.length());
                }
            }
        });

        mBotaoAdiciona = (Button) findViewById(R.id.botao_adiciona_colheita);
        mBotaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editTextQuantidade = (EditText) findViewById(R.id.input_quantidade);
                aux = editTextQuantidade.getText().toString().trim();
                quantidade = Double.parseDouble(aux);

                double totalAtual = talhaoDbHelper.getTotalTalhao(nomeTalhao);
                double novoTotal = totalAtual + quantidade;
                talhaoDbHelper.atualizarTotalTalhao(nomeTalhao, novoTotal);

                double totalAtualLavoura = lavouradbHelper.getTotalLavoura(nomeLavoura);
                double novoTotalLavoura = totalAtualLavoura + quantidade;
                lavouradbHelper.atualizarTotalLavoura(nomeLavoura, novoTotalLavoura);

                adicionaColheita(nomeLavoura, nomeTalhao, nomePanhador, quantidade, editTextData.getText().toString());
                finish();
            }
        });
    }

    private void adicionaColheita(String lavoura, String talhao, String panhador, double quant, String data){
        ColheitaDB colheitaDb = new ColheitaDB(this);
        colheitaDb.addColheita(lavoura, talhao, panhador, quant, data);
    }


    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, AdicionarColheitaActivity.class);
        return intent;
    }
}
