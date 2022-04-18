package br.com.iudek.IquiLanguage.gramatica;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IQuiScanner {

	private char[] c;
	private int estado;
	private int pos;
	
	public IQuiScanner(String arquivo) {
		try {
			String TxtConteudo;
			TxtConteudo = new String(Files.readAllBytes(Paths.get(arquivo)),StandardCharsets.UTF_8);
			System.out.println(TxtConteudo);
			c = TxtConteudo.toCharArray(); 
			
			pos=0;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}


private boolean IsDigit(char c) {
	return c >= '0' && c <= '9';
}

private boolean IsChar(char c) {
	return (c>='a' && c<='z') || (c>='A' && c<='Z');
}

private boolean IsOperator(char c) {
	return c=='>' || c=='<' || c=='=' || c=='!';
}

private boolean IsSpace(char c) {
	return c==' ' || c=='\t' || c=='\n' || c=='\r';
}


private char nextChar() {
	return c[pos++];
	
}

private void back() {
	pos--;
}

private boolean IsEOF() {
	return pos==c.length;
}

public IQuiToken nextToken() {
	char CharAtual;
	IQuiToken token;
	String term = "";
	if(IsEOF())
		return null;
	estado=0;
	while(true) {
		CharAtual = nextChar();
		
		switch(estado) {
		case 0:
			if(IsChar(CharAtual)) {
				term+=CharAtual;
				estado = 1;
			}
			else if(IsDigit(CharAtual)) {
				estado = 3;	
				term+=CharAtual;
			}
			else if(IsOperator(CharAtual)) {
				estado = 5;
			}
			else if(IsSpace(CharAtual)){
				estado = 0;
			}
			else {
				throw new RuntimeException("Simbolo nao reconhecido");
			}break;
		case 1:
			if(IsChar(CharAtual)||IsDigit(CharAtual)) {
				estado = 1;
				term+=CharAtual;
			}
			else {
				estado = 2;
			}
			break;
		
		case 2:
			back();
			token = new IQuiToken();
			token.setTipo(IQuiToken.TK_IDENTIFICADOR);
			token.setTexto(term);
			return token;
		case 3:
			if(IsDigit(CharAtual)) {
				estado = 3;
				term+=CharAtual;
			}
			else if(!IsChar(CharAtual)) {
				estado = 4;
			}
			else {
				throw new RuntimeException("Numero nao reconhecido");
			}
			break;
		case 4:	
			token = new IQuiToken();
			token.setTipo(IQuiToken.TK_NUMERO);
			token.setTexto(term);
			back();
			return token;
		case 5:
			term+=CharAtual;
			token = new IQuiToken();
			token.setTipo(IQuiToken.TK_OPERADOR);
		}
	}
}

}