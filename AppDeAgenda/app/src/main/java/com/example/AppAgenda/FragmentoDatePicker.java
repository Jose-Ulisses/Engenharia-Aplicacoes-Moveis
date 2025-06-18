package com.example.AppAgenda;

import android.app.Dialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class FragmentoDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH);
        int ano = c.get(Calendar.YEAR);

        return new DatePickerDialog(requireContext(), this, ano, mes, dia);
    }

    public void onDateSet(DatePicker view, int year, int month, int day){

        Bundle date = new Bundle();
        date.putInt("dia", day);
        date.putInt("mes", month);
        date.putInt("ano", year);

        getParentFragmentManager().setFragmentResult("dataSelecionada", date);
    }
}