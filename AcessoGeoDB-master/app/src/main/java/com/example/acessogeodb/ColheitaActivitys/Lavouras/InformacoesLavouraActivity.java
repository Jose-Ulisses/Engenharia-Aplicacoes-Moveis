package com.example.acessogeodb.ColheitaActivitys.Lavouras;

import android.content.Context;
import android.content.Intent;

public class InformacoesLavouraActivity {

    public static Intent novoIntent(Context packageContext){
        Intent intent = new Intent(packageContext, InformacoesLavouraActivity.class);
        return intent;
    }
}
