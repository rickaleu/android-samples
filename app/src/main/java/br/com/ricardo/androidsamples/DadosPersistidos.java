package br.com.ricardo.androidsamples;

import android.content.SharedPreferences;

public class DadosPersistidos {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public DadosPersistidos(SharedPreferences prefs) {
        this.prefs = prefs;
        this.editor = prefs.edit();
    }

    public void salvarDadosString(String chave, String valor){
        editor.putString(chave, valor);
        editor.apply();
    }

    public void salvarDadosInt(String chave, int valor){
        editor.putInt(chave, valor);
        editor.apply();
    }

    public String recuperarDadosString(String chave){

        String caractere = prefs.getString(chave, "não encontrado");

        return caractere;
    }

    public int recuperaDadosInt(String chave){

        int idade = prefs.getInt("idade", 0);

        return idade;
    }
}
