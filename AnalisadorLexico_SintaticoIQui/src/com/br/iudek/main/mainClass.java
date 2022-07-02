package com.br.iudek.main;

import com.br.iudek.analisadorLexico.*;
import java.io.IOException;
import com.br.iudek.analisadorSintatico.*;

public class mainClass {

    public static void main(String[] args) throws Exception{
        AnalisadorLexico analisadorL = new AnalisadorLexico("teste.iqui");
        AnalisadorSintatico analisadorS = new AnalisadorSintatico();
        try {
            analisadorL.analisar();
            System.out.println("Pares <token,lexema> \n"+ analisadorL.getTokens());
            System.out.println("\n\nLista de erros lexicos");
            int i;
            for (i = 0; i < ErroLexico.getErros().size();i++){
                ErroLexico erro = ErroLexico.getErros().get(i);      
                System.out.println(erro.showErrors());
            }
            System.out.println("\n\nTabela de variaveis\n" + Gramatica.getTabelaDeSimbolos());

            if(ErroLexico.getErros().size()==0)
                analisadorS.analisar(analisadorL.getTokens());
            else
                System.out.println("\n\nTRATAR ERROS LÉXICOS");    

        } catch (IOException ex) {
            System.out.println("\n\n*** Erro na leitura. ***\n\n");
        }
    }
}
