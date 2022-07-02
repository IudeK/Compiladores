package com.br.iudek.analisadorLexico;

import java.util.ArrayList;
import java.util.List;
 

public class Gramatica {
    
    private String nome;
    private static final List<Gramatica> T_SIMBOLOS = new ArrayList<Gramatica>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static void addSimbolo(Gramatica simbolo) {
        if (!Gramatica.T_SIMBOLOS.contains(simbolo))
            Gramatica.T_SIMBOLOS.add(simbolo);
    }

    public static List<Gramatica> getTabelaDeSimbolos() {
        return T_SIMBOLOS;
    }
    
    @Override
    public String toString(){
        return this.getNome();
    }
    
    @Override
    public boolean equals(Object obj){
        Gramatica s = (Gramatica) obj;
        return s.getNome().equals(this.nome);
    }
   
    
}
