package com.example.AppAgenda;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import java.util.Calendar;

public class Fragmento2 extends Fragment {
    static View v;
    private Button botaoHoje;
    private Button botaoOutraData;

    private TextView mTextViewCompromissos;
    CompromissosDB mCompromissosDb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragmento2, container, false);

        botaoHoje = (Button) v.findViewById(R.id.botao_hoje);
        botaoHoje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int dia_hoje = calendar.get(Calendar.DAY_OF_MONTH);
                int mes_hoje = calendar.get(Calendar.MONTH) + 1;
                int ano_hoje = calendar.get(Calendar.YEAR);
                listaCompromissos(dia_hoje, mes_hoje, ano_hoje);
            }
        });

        botaoOutraData = (Button) v.findViewById(R.id.botao_outraData);
        botaoOutraData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment fragmentoData = new FragmentoDatePicker();
                fragmentoData.show(getParentFragmentManager(), "datePicker");

                getParentFragmentManager().setFragmentResultListener("dataSelecionada", Fragmento2.this, (key, bundle) -> {
                    int outro_dia = bundle.getInt("dia");
                    int outro_mes = bundle.getInt("mes") + 1;
                    int outro_ano = bundle.getInt("ano");
                    listaCompromissos(outro_dia, outro_mes, outro_ano);
                });
            }
        });


        return v;
    }

    private void listaCompromissos(int dia, int mes, int ano){
        mCompromissosDb = new CompromissosDB(getActivity().getBaseContext());
        Cursor cursor = mCompromissosDb.queryCompromissos((String) null, (String[]) null);

        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.linearLayoutCompromissos);
        linearLayout.removeAllViews();
        if(cursor != null){

            try{
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){

                    @SuppressLint("Range") int diaCompromisso = cursor.getInt(cursor.getColumnIndex("dia"));
                    @SuppressLint("Range") int mesCompromisso = cursor.getInt(cursor.getColumnIndex("mes"));
                    @SuppressLint("Range") int anoCompromisso = cursor.getInt(cursor.getColumnIndex("ano"));

                    if((dia == diaCompromisso) && (mes == mesCompromisso) && (ano == anoCompromisso)){
                        @SuppressLint("Range") int horaCompromisso = cursor.getInt(cursor.getColumnIndex("hora"));
                        @SuppressLint("Range") int minutoCompromisso = cursor.getInt(cursor.getColumnIndex("minuto"));
                        @SuppressLint("Range") String descCompromisso = cursor.getString(cursor.getColumnIndex("descricao"));

                        String hora = String.format("%02d:%02d", horaCompromisso, minutoCompromisso);

                        mTextViewCompromissos = new TextView(Fragmento2.v.getContext());
                        mTextViewCompromissos.setText(hora + " - " + descCompromisso);
                        mTextViewCompromissos.setTextSize(26);
                        mTextViewCompromissos.setPadding(30, 5, 0, 100);

                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);

                        linearLayout.addView(mTextViewCompromissos);

                        cursor.moveToNext();
                    }
                    else
                        cursor.moveToNext();
                }
            } finally{
                cursor.close();
            }
        }
    }
}