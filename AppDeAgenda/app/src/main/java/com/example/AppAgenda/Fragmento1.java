package com.example.AppAgenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class Fragmento1 extends Fragment {

    public Fragmento1(){}

    private Button mBotaoData;
    private Button mBotaohora;
    private Button mBotaoOk;
    private View v;
    EditText inputDescricao;
    CompromissosDB mCompromissosDb;

    int dia, mes, ano;
    int hora, minuto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragmento1, container, false);

        mBotaoData = (Button) v.findViewById(R.id.botao_data);
        mBotaoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment fragmentoData = new FragmentoDatePicker();
                fragmentoData.show(getParentFragmentManager(), "datePicker");
                recebeDadosFragmentoDatePicker();
            }
        });

        mBotaohora = (Button) v.findViewById(R.id.botao_hora);
        mBotaohora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment fragmentoHora = new FragmentoTimePicker();
                fragmentoHora.show(getParentFragmentManager(), "timePicker");
                recebeDadosFragmentoTimePicker();
            }
        });

        mBotaoOk = (Button) v.findViewById(R.id.botao_ok);
        mBotaoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDescricao = (EditText) v.findViewById(R.id.editTextDescricao);
                adicionaCompromissos(dia, mes, ano, hora, minuto, inputDescricao.getText().toString());
            }
        });

        return v;
    }

    private void recebeDadosFragmentoTimePicker(){
        getParentFragmentManager().setFragmentResultListener("horaSelecionada", Fragmento1.this, (key, bundle) -> {
            hora = bundle.getInt("hora");
            minuto = bundle.getInt("minuto");
            String horaFormatada = String.format("%02d:%02d", hora, minuto);
            mBotaohora.setText(horaFormatada);
        });
    }

    private void recebeDadosFragmentoDatePicker(){
        getParentFragmentManager().setFragmentResultListener("dataSelecionada", Fragmento1.this, (key, bundle) -> {
            dia = bundle.getInt("dia");
            mes = bundle.getInt("mes") + 1;
            ano = bundle.getInt("ano");
            String dataFormatada = String.format("%02d/%02d/%04d", dia, mes, ano);
            mBotaoData.setText(dataFormatada);
        });
    }

    private void adicionaCompromissos(int dia, int mes, int ano, int hora, int minuto, String desc){
        CompromissosDB compromissosDB = new CompromissosDB(getContext());
        compromissosDB.addCompromisso(dia, mes, ano, hora, minuto, desc);
    }
}