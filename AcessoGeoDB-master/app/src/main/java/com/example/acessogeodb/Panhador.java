package com.example.acessogeodb;

public class Panhador {
    private String mNome;
    private String mCpf;
    private String mNumero;
    private String mChavePix;

    public Panhador(String nome, String cpf, String numero, String chave_pix){
        this.mNome = nome;
        this.mCpf = cpf;
        this.mNumero = numero;
        this.mChavePix = chave_pix;
    }

    public String getmNome(){
        return mNome;
    }

    public String getmCpf(){
        return mCpf;
    }

    public String getmNumero(){
        return mNumero;
    }

    public String getmChavePix(){
        return mChavePix;
    }
}