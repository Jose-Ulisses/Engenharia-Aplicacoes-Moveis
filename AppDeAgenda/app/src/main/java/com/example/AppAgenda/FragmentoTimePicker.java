package com.example.AppAgenda;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;
import android.text.format.DateFormat;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class FragmentoTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR);
        int minuto = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hora, minuto, DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute){

        Bundle time = new Bundle();
        time.putInt("hora", hourOfDay);
        time.putInt("minuto", minute);

        getParentFragmentManager().setFragmentResult("horaSelecionada", time);
    }
}