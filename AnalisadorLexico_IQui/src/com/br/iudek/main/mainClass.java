package com.br.iudek.main;

import com.br.iudek.analisadorLexico.*;
import java.io.IOException;

public class mainClass {

    public static void main(String[] args){
        AnalisadorLexico analisador = new AnalisadorLexico("teste.iqui");
        try {
            analisador.analisar();
            System.out.println("Pares <token,lexema> \n"+ analisador.getTokens());
            System.out.println("\n\nLista de erros lexicos");
            int i;
            for (i = 0; i < ErroLexico.getErros().size();i++){
                ErroLexico erro = ErroLexico.getErros().get(i);      
                System.out.println(erro.showErrors());
            }
            System.out.println("\n\nTabela de variaveis\n" + Gramatica.getTabelaDeSimbolos());

        } catch (IOException ex) {
            System.out.println("\n\n*** Erro na leitura. ***\n\n");
        }
    }
}
