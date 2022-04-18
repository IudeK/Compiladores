package br.com.iudek.IquiLanguage.main;

import br.com.iudek.IquiLanguage.gramatica.IQuiScanner;
import br.com.iudek.IquiLanguage.gramatica.IQuiToken;

public class main {

	public static void main(String[] args) {
		
		IQuiScanner scanner = new IQuiScanner("teste.iqui");
		IQuiToken token = null;
		
		do {
			token = scanner.nextToken();
			if(token!=null) {
				System.out.println(token);
			}
		}while(token!=null);
	}
}
