package com.br.iudek.analisadorLexico;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AnalisadorLexico {
    private BufferedReader reader;
    private String linha;
    private String caractere;
    private String palavra = "";
    private boolean comentado = false;
    private String numerico;
    private String identificador;
    private String literais;

    private final List<String> delimitadores = new ArrayList<String>();
    private final List<String> operAritmetico = new ArrayList<String>();
    private final List<String> operRelacionais = new ArrayList<String>();
    private final List<String> reservadas = new ArrayList<String>();
    private final List<String> operAtribuicao = new ArrayList<String>();
    private final List<String> tipo = new ArrayList<String>();
    private final List<IQuiToken> tokens = new ArrayList<IQuiToken>();
    private String nomeArquivo;
   
    public AnalisadorLexico(String Arquivo) {
        try {
            
            this.nomeArquivo = Arquivo;

            numerico = ("^[0-9]+|[0-9]+.[0-9]+");
            identificador = ("^([a-z]|[A-Z])+[0-9]*");
            literais = ("^\"(\\w|\\d|[_])*\"");

            
            delimitadores.add(";");
            delimitadores.add(":");
            delimitadores.add("(");
            delimitadores.add(")");
            delimitadores.add(",");
            delimitadores.add(" ");

            operAritmetico.add("+");
            operAritmetico.add("-");
            operAritmetico.add("*");
            operAritmetico.add("/");
            operAritmetico.add("%");

            operRelacionais.add("==");
            operRelacionais.add("!=");
            operRelacionais.add(">=");
            operRelacionais.add("<=");
            operRelacionais.add(">");
            operRelacionais.add("<");

            operAtribuicao.add("=");

            tipo.add("int");
            tipo.add("char");
            tipo.add("double");
            tipo.add("boolean");
            tipo.add("string");

            reservadas.add("++");
            reservadas.add("--");
            reservadas.add("if");
            reservadas.add("else");
            reservadas.add("false");
            reservadas.add("true");
            reservadas.add("begin");
            reservadas.add("end");
            reservadas.add("sprintf");
            reservadas.add("scanner.in.ReadNextLine");
            
            reader = new BufferedReader(new FileReader(Arquivo));
        } catch (FileNotFoundException ex) {
            System.out.println("\n\n*** Verificar o nome do arquivo de entrada. Ele deve estar na pasta do Analisador ***\n\n");
        }
    }

    public void analisar() throws IOException {
        int nLine = 0;
        while (true) {
            nLine++;

            linha = reader.readLine();
            if (linha == null)
                break;

            if(linha.equals("begin") || linha.equals("end")){
                String aux = linha;
                this.addToken(aux, nLine);
            }
            else{
            int size = linha.length();

            linha = linha.split("\r")[0];
            caractere = "";

            for (int i = 0; i < size; i++) {
                caractere = linha.substring(i, i + 1);
                if (delimitadores.contains(caractere)) {
                    if ((!comentado) && (palavra.length() >= 2) && (palavra.substring(0, 2).equals(
                            "//"))) {
                        palavra = "";
                        break;
                    }
                    if ((!comentado) && (palavra.length() >= 2) && (palavra.substring(0, 2).equals(
                            "*/"))) {
                        palavra = "";
                        comentado = true;
                    }
                    if ((comentado) && (palavra.length() >= 2) && (palavra.substring(0, 2).equals(
                            "/*"))) {
                        palavra = "";
                        comentado = false;
                    }

                    if (!comentado) {
                        if ((!palavra.equals("")) && (!palavra.contains("/*")))
                            this.addToken(palavra, nLine);

                    }
                    palavra = "";
                    if(!caractere.equals(" "))
                        this.addToken(caractere, nLine);

                } else
                    palavra = palavra + caractere;
            }

        }
    }
    }

    private void addToken(String palavra, int nLine) {

        if (palavra.matches(numerico)) {
            IQuiToken elemento = new IQuiToken();
            elemento.setToken("NUMERICO");
            elemento.setLexema(palavra);
            tokens.add(elemento);
            return;
        }

        if (palavra.matches(literais)) {
            IQuiToken elemento = new IQuiToken();
            elemento.setToken("LITERAL");
            elemento.setLexema(palavra);
            tokens.add(elemento);
            return;
        }

        if (operRelacionais.contains(palavra)) {
            IQuiToken elemento = new IQuiToken();
            elemento.setToken("OPER RELACIONAL");
            elemento.setLexema(palavra);
            tokens.add(elemento);
            return;
        }

        if (tipo.contains(palavra)) {
            IQuiToken elemento = new IQuiToken();
            elemento.setToken("TIPO");
            elemento.setLexema(palavra);
            tokens.add(elemento);
            return;
        }

        if (delimitadores.contains(palavra)) {
            IQuiToken elemento = new IQuiToken();
            elemento.setToken("DELIMITADOR");
            elemento.setLexema(palavra);
            tokens.add(elemento);
            return;
        }

        if (reservadas.contains(palavra)) {
            IQuiToken elemento = new IQuiToken();
            elemento.setToken(palavra);
            tokens.add(elemento);
            return;
        }
        if (operAtribuicao.contains(palavra)) {
            IQuiToken elemento = new IQuiToken();
            elemento.setToken("OPERADOR ATRIBUICAO");
            elemento.setLexema(palavra);
            tokens.add(elemento);
            return;
        }
        if (operAritmetico.contains(palavra)) {
            IQuiToken elemento = new IQuiToken();
            elemento.setToken("OPERADOR ARITMETICO");
            elemento.setLexema(palavra);
            tokens.add(elemento);
            return;
        }

        if (!palavra.equals(reservadas)) {
            if (palavra.matches(identificador)) {
                IQuiToken elemento = new IQuiToken();
                elemento.setToken("IDENTIFICADOR");
                elemento.setLexema(palavra);
                tokens.add(elemento);
                Gramatica simbolo = new Gramatica();
                simbolo.setNome(palavra);
                Gramatica.addSimbolo(simbolo);
                return;
            }

        }

        ErroLexico erro = new ReturnErro();
        erro.setCodigo(404);
        erro.setDescricao("Identificador desconhecido: " + palavra);
        erro.setNomeArquivo(this.nomeArquivo);
        erro.setNumLinha(nLine);
        ErroLexico.addErro(erro);

    }

    public List<IQuiToken> getTokens() {
        return tokens;
    }
}
