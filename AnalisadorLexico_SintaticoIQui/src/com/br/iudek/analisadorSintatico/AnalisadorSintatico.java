package com.br.iudek.analisadorSintatico;

import java.util.List;
import com.br.iudek.analisadorLexico.IQuiToken;

public class AnalisadorSintatico {

    public void analisar(List<IQuiToken> x) throws Exception {
        System.out.println("\nEntrou no Analisador Sintatico");
        state(x,0,0);
    }

    private int incrementar(int p) {
        return ++p;// função para incrementar o indice do array com a sequencia dos tokens
    }

    private void state(List<IQuiToken> x,int estado, int pos) throws Exception{
        
        if (estado == 0) {
            if (x.get(pos).getToken().equals("begin")) {
                state(x, 1, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'begin' no inicio");
        }
        if (estado == 1) {
            if (x.get(pos).getToken().equals("sprintf")) {
                state(x, 21, incrementar(pos));
            }
            if (x.get(pos).getToken().equals("IDENTIFICADOR")) {
                state(x, 2, incrementar(pos));
                state(x, 3, incrementar(pos));
                state(x, 13, incrementar(pos));
                state(x, 19, incrementar(pos));
                state(x, 51, incrementar(pos));
                state(x, 56, incrementar(pos));
            }
            if (x.get(pos).getToken().equals("if")) {
                state(x, 26, incrementar(pos));
            }
            if (x.get(pos).getToken().equals("end")) {
                state(x, 25, incrementar(pos));
            }
        }
        if (estado == 2) {
            if (x.get(pos).getLexema().equals(",")) {
                state(x, 4, incrementar(pos));
            }
            if (x.get(pos).getLexema().equals(":")) {
                state(x, 5, incrementar(pos));
            }
        }
        if (estado == 3) {
            if (x.get(pos).getToken().equals("OPERADOR ATRIBUICAO")) {
                state(x, 7, incrementar(pos));
            }
        }
        if (estado == 4) {
            if (x.get(pos).getToken().equals("IDENTIFICADOR")) {
                state(x, 2, incrementar(pos));
            }
        }
        if (estado == 5) {
            if (x.get(pos).getToken().equals("TIPO")) {
                state(x, 6, incrementar(pos));
            }
        }
        if (estado == 6) {
            if (x.get(pos).getLexema().equals(";")) {
                state(x, 1, incrementar(pos));
            } 
            else
                throw new Exception("Era esperado um: ';' no lugar de "+ x.get(pos).getLexema());        
        }
        if (estado == 7) {
            if (x.get(pos).getToken().equals("IDENTIFICADOR") || x.get(pos).getToken().equals("NUMERICO") ) {
                state(x, 8, incrementar(pos));
            }
        }
        if (estado == 8) {
            if (x.get(pos).getToken().equals("OPERADOR ARITMETICO")) {
                state(x, 9, incrementar(pos));
            }
        }
        if (estado == 9) {
            if (x.get(pos).getToken().equals("IDENTIFICADOR") || x.get(pos).getToken().equals("NUMERICO") ) {
                state(x, 10, incrementar(pos));
            }
        }
        if (estado == 10) {
            if (x.get(pos).getLexema().equals(":")) {
                state(x, 11, incrementar(pos));
            }
        }
        if (estado == 11) {
            if (x.get(pos).getToken().equals("TIPO")) {
                state(x, 12, incrementar(pos));
            }
        }
        if (estado == 12) {
            if (x.get(pos).getLexema().equals(";")) {
                state(x, 1, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: ';' no lugar de "+ x.get(pos).getLexema());  
        }
        if (estado == 13) {
            if (x.get(pos).getToken().equals("OPERADOR ATRIBUICAO")) {
                state(x, 14, incrementar(pos));
            }
        }
        if (estado == 14) {
            if (x.get(pos).getToken().equals("LITERAL") || x.get(pos).getToken().equals("NUMERICO")) {
                state(x, 15, incrementar(pos));
            }
        }
        if (estado == 15) {
            if (x.get(pos).getLexema().equals(",")) {
                state(x, 16, incrementar(pos));
            }
            if (x.get(pos).getLexema().equals(":")) {
                state(x, 17, incrementar(pos));
            }
        }
        if (estado == 16) {
            if (x.get(pos).getToken().equals("IDENTIFICADOR")) {
                state(x, 13, incrementar(pos));
            }
        }
        if (estado == 17) {
            if (x.get(pos).getToken().equals("TIPO")) {
                state(x, 18, incrementar(pos));
            }
        }
        if (estado == 18) {
            if (x.get(pos).getLexema().equals(";")) {
                state(x, 1, incrementar(pos));
            } 
            else
                throw new Exception("Era esperado um: ';' no lugar de "+ x.get(pos).getLexema());  
        }
        if (estado == 19) {
            if (x.get(pos).getToken().equals("++") || x.get(pos).getToken().equals("--")) {
                state(x, 20, incrementar(pos));
            }
        }
        if (estado == 20) {
            if (x.get(pos).getLexema().equals(";")) {
                state(x, 1, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: ';' no lugar de "+ x.get(pos).getLexema());  
        }
        if (estado == 21) {
            if (x.get(pos).getLexema().equals("(")) {
                state(x, 22, incrementar(pos));
            }
        }
        if (estado == 22) {
            if (x.get(pos).getToken().equals("LITERAL")) {
                state(x, 23, incrementar(pos));
            }
            else    
                throw new Exception("Era esperado um: 'LITERAL' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 23) {
            if (x.get(pos).getLexema().equals(")")) {
                state(x, 24, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: ')' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 24) {
            if (x.get(pos).getLexema().equals(";")) {
                state(x, 1, incrementar(pos));
            }
        }
        if (estado == 25) {
            if(x.size() == pos){
             System.out.println("\n\nCOMPILADO COM SUCESSO!!");
             return;
            }
            else
                throw new Exception("Argumentos depois do: end");
            
        }
        if (estado == 26) {
            if (x.get(pos).getLexema().equals("(")) {
                state(x, 27, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: '(' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 27) {
            if (x.get(pos).getToken().equals("IDENTIFICADOR")) {
                state(x, 28, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'IDENTIFICADOR' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 28) {
            if (x.get(pos).getToken().equals("OPER RELACIONAL")) {
                state(x, 29, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'OPERADOR RELACIONAL' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 29) {
            if (x.get(pos).getToken().equals("IDENTIFICADOR") || x.get(pos).getToken().equals("NUMERICO") ) {
                state(x, 30, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'NUMERICO' ou 'IDENTIFICADOR' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 30) {
            if (x.get(pos).getLexema().equals(")")) {
                state(x, 32, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: ')' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 31) {
            if (x.get(pos).getToken().equals("IDENTIFICADOR")) {
                state(x, 33, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'IDENTIFICADOR' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 32) {
            if (x.get(pos).getToken().equals("begin")) {
                state(x, 31, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'begin' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 33) {
            if (x.get(pos).getToken().equals("OPERADOR ATRIBUICAO")) {
                state(x, 34, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'OPERADOR ATRIBUICAO' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 34) {
            if (x.get(pos).getToken().equals("IDENTIFICADOR") || x.get(pos).getToken().equals("NUMERICO") ) {
                state(x, 35, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'IDENTIFICADOR' ou 'NUMERICO' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 35) {
            if (x.get(pos).getToken().equals("OPERADOR ARITMETICO")) {
                state(x, 36, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'OPERADOR ARITMETICO' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 36) {
            if (x.get(pos).getToken().equals("IDENTIFICADOR") || x.get(pos).getToken().equals("NUMERICO") ) {
                state(x, 37, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'IDENTIFICADOR' ou 'NUMERICO' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 37) {
            if (x.get(pos).getLexema().equals(";")) {
                state(x, 46, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: ';' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 38) {
            if (x.get(pos).getToken().equals("else")) {
                state(x, 39, incrementar(pos));
            }
            else
                state(x, 1, incrementar(pos));
        }
        if (estado == 39) {
            if (x.get(pos).getToken().equals("begin")) {
                state(x, 40, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'begin' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 40) {
            if (x.get(pos).getToken().equals("IDENTIFICADOR")) {
                state(x, 41, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'IDENTIFICADOR' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 41) {
            if (x.get(pos).getToken().equals("OPERADOR ATRIBUICAO")) {
                state(x, 42, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'OPERADOR ATRIBUICAO' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 42) {
            if (x.get(pos).getToken().equals("IDENTIFICADOR") || x.get(pos).getToken().equals("NUMERICO") ) {
                state(x, 43, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'IDENTIFICADOR' ou 'NUMERICO' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 43) {
            if (x.get(pos).getToken().equals("OPERADOR ARITMETICO")) {
                state(x, 44, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'OPERADOR ARITMETICO' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 44) {
            if (x.get(pos).getToken().equals("IDENTIFICADOR") || x.get(pos).getToken().equals("NUMERICO")) {
                state(x, 45, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'IDENTIFICADOR' ou 'NUMERICO' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 45) {
            if (x.get(pos).getLexema().equals(";")) {
                state(x, 47, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: ';' no lugar de "+ x.get(pos).getLexema()); 
        }
        if (estado == 46) {
            if (x.get(pos).getToken().equals("end")) {
                state(x, 38, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'end' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 47) {
            if (x.get(pos).getToken().equals("end")) {
                state(x, 1, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: 'end' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 48) {
            if (x.get(pos).getLexema().equals("(")) {
                state(x, 49, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: '(' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 49) {
            if (x.get(pos).getLexema().equals(")")) {
                state(x, 50, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: ')' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 50) {
            if (x.get(pos).getLexema().equals(";")) {
                state(x, 1, incrementar(pos));
            }
            else
                throw new Exception("Era esperado um: ';' no lugar de "+ x.get(pos).getLexema()); 
        }
        if (estado == 51) {
            if (x.get(pos).getToken().equals("OPERADOR ATRIBUICAO")) {
                state(x, 52, incrementar(pos));
            }
            else if(!x.get(pos).getLexema().equals(":") && !x.get(pos).getLexema().equals(",") && !x.get(pos).getToken().equals("--") && !x.get(pos).getToken().equals("++"))
                throw new Exception("Era esperado um: 'OPERADOR ATRIBUICAO' ou ':' ou ',' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 52) {
            if (x.get(pos).getToken().equals("IDENTIFICADOR") || x.get(pos).getToken().equals("NUMERICO")) {
                state(x, 53, incrementar(pos));
            }
            else if(!(x.get(pos).getToken().equals("LITERAL")) && !(x.get(pos).getToken().equals("scanner.in.ReadNextLine")))
                throw new Exception("Era esperado um: 'IDENTIFICADOR' ou 'NUMERICO' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 53) {
            if (x.get(pos).getLexema().equals(";")) {
                state(x, 1, incrementar(pos));
            }
            if (x.get(pos).getToken().equals("OPERADOR ARITMETICO")) {
                state(x, 54, incrementar(pos));
            } 
            else if(!(x.get(pos).getLexema().equals(":")) && !(x.get(pos).getToken().equals("OPERADOR ARITMETICO")) && !(x.get(pos).getLexema().equals(";")))
                throw new Exception("Era esperado um: ';' ou 'OPERADOR ARITMETICO' ou ':' no lugar de "+ x.get(pos).getLexema());  
        }
        if (estado == 54) {
            if (x.get(pos).getToken().equals("IDENTIFICADOR") || x.get(pos).getToken().equals("NUMERICO")) {
                state(x, 55, incrementar(pos));
            }
            else 
                throw new Exception("Era esperado um: 'IDENTIFICADOR' ou 'NUMERICO' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 55) {
            if (x.get(pos).getLexema().equals(";")) {
                state(x, 1, incrementar(pos));
            }
            else if(!(x.get(pos).getLexema().equals(":")))
                throw new Exception("Era esperado um: ';' ou ':' no lugar de "+ x.get(pos).getLexema());
        }
        if (estado == 56) {
            if (x.get(pos).getToken().equals("OPERADOR ATRIBUICAO")) {
                state(x, 57, incrementar(pos));
            }
        }
        if (estado == 57) {
            if (x.get(pos).getToken().equals("scanner.in.ReadNextLine")) {
                state(x, 48, incrementar(pos));
            }
        }
    }
    
}
