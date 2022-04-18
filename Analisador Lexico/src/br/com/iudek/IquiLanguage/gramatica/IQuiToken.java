package br.com.iudek.IquiLanguage.gramatica;

public class IQuiToken {
	
	public static final int TK_IDENTIFICADOR = 0;
	public static final int TK_NUMERO = 1;
	public static final int TK_OPERADOR = 2;
	public static final int TK_PONTUACAO = 3;
	public static final int TK_ATRIBUICAO = 4;
	
	private int tipo;
	private String texto;
	
	public IQuiToken() {
	}

	public IQuiToken(int tipo, String texto) {
		this.tipo = tipo;
		this.texto = texto;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return "IQuiToken [tipo=" + tipo + ", texto=" + texto + "]";
	}
	
	
	
	
}
