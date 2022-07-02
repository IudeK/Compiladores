package com.br.iudek.analisadorLexico;

public class ReturnErro extends ErroLexico {
    
   @Override
   public String showErrors(){
       return "Erro lexico " + getCodigo() + " - " + getDescricao() + " em " + getNomeArquivo() + " | linha: " + getNumLinha();
   }
}
