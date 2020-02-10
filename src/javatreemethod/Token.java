/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatreemethod;

/**
 *
 * @author Cristian Meoño
 */
public class Token {
    
    Token(){
        
    }
    
    private String Tipo;
    private String Lexema;
    
    public void setTipo(String arg1){
        this.Tipo=arg1;
    }
    
    public void setLexema(String arg1){
        this.Lexema=arg1;
    }
    
    public void printToken(){
        System.out.println(this.getTipo()+"--"+this.Lexema);
    }
    
    public String getTipo(){
        return this.Tipo;
    }
    
    public String getLexema(){
        return this.Lexema;
    }
}
