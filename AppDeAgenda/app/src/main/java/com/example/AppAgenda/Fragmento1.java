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
    int dia = -1;
    int mes = -1;
    int ano = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragmento1, container, false);

        getParentFragmentManager().setFragmentResultListener("dataSelecionada", this, (key, bundle) -> {
            dia = bundle.getInt("dia");
            mes = bundle.getInt("mes") + 1;
            ano = bundle.getInt("ano");

            System.out.println("Data selecionada2: " + dia + "/" + mes + "/" + ano);
        });

        mBotaoData = (Button) v.findViewById(R.id.botao_data);
        mBotaoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment fragmentoData = new FragmentoDatePicker();
                fragmentoData.show(getParentFragmentManager(), "datePicker");
            }
        });

        mBotaohora = (Button) v.findViewById(R.id.botao_hora);
        mBotaohora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment fragmentoHora = new FragmentoTimePicker();
                fragmentoHora.show(getParentFragmentManager(), "timePicker");

            }
        });

        mBotaoOk = (Button) v.findViewById(R.id.botao_ok);
        mBotaoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("botaoOK");
                inputDescricao = (EditText) v.findViewById(R.id.editTextDescricao);

                System.out.println("Data selecionada: " + dia + "/" + mes + "/" + ano);
            }
        });

        return v;
    }
}