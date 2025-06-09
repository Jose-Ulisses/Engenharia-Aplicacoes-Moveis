package com.example.acessogeodb.TalhaoActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.acessogeodb.LavourasDbHelper;
import com.example.acessogeodb.R;
import com.example.acessogeodb.TalhaoDB;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdicionarTalhaoActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    LavourasDbHelper dbhelper;
    EditText inputNomeTalhao;
    EditText inputPrecoTalhao;
    private Button mBotaoAdiciona;
    String nomeLavoura;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_talhao);

        autoCompleteTextView = findViewById(R.id.auto_complete_textview);
        dbhelper = new LavourasDbHelper(this);
        List<String> item = dbhelper.getNomesDasLavouras();

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, item);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nomeLavoura = adapterView.getItemAtPosition(i).toString();
            }
        });

        inputPrecoTalhao = (EditText) findViewById(R.id.input_preco_talhao);
        inputPrecoTalhao.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals(current)){
                    inputPrecoTalhao.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[^\\d]", "");
                    try {
                        double parsed = Double.parseDouble(cleanString) / 100;
                        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                        nf.setMaximumFractionDigits(2);
                        nf.setMinimumFractionDigits(2);
                        String formatted = nf.format(parsed).replace("R$", "").trim();

                        current = formatted;
                        inputPrecoTalhao.setText(formatted);
                        inputPrecoTalhao.setSelection(formatted.length());
                    } catch (NumberFormatException e) {
                        current = "";
                        inputPrecoTalhao.setText("");
                    }
                    inputPrecoTalhao.addTextChangedListener(this);
                }
            }
        });

        mBotaoAdiciona = (Button) findViewById(R.id.botao_adiciona_talhao);
        mBotaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputNomeTalhao = (EditText) findViewById(R.id.input_nome_talhao);
                String valorTextoOriginal = inputPrecoTalhao.getText().toString();
                double preco = 0.0;

                try {
                    String cleanedString = valorTextoOriginal.replaceAll("\\s", "").replace(",", ".").trim();
                    if(!cleanedString.isEmpty()){
                        preco = Double.parseDouble(cleanedString);
                    } else {
                        Toast.makeText(AdicionarTalhaoActivity.this, "valor invalido", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String nomeDoTalhao = inputNomeTalhao.getText().toString();
                    if (nomeLavoura == null || nomeLavoura.trim().isEmpty()) {
                        Toast.makeText(AdicionarTalhaoActivity.this, "Selecione uma lavoura", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (nomeDoTalhao.trim().isEmpty()) {
                        Toast.makeText(AdicionarTalhaoActivity.this, "Digite o nome do talhão", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    adicionaTalhao(nomeLavoura, nomeDoTalhao, preco);

                    Toast.makeText(AdicionarTalhaoActivity.this, "Talhão adicionado!", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(AdicionarTalhaoActivity.this, "Formato de preço inválido: " + valorTextoOriginal, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void adicionaTalhao(String nomeLavoura, String nomeTalhao, double preco){
        TalhaoDB talhaoDB = new TalhaoDB(this);
        talhaoDB.addTalhao(nomeLavoura, nomeTalhao, preco);
    }

    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, AdicionarTalhaoActivity.class);
        return intent;
    }
}
