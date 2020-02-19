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
public class Transicion {
    
    private int IDTerminal;
    private Estado Destino;
    
    Transicion(){
        
    }
    Transicion(int ID ){
        this.IDTerminal=ID;
    }
    Transicion(int ID , Estado Dest){
        this.IDTerminal=ID;
        this.Destino=Dest;
    }
    
    public void setDestino(Estado arg1){
        this.Destino = arg1;
    }
    
    public void setID(int arg1){
        this.IDTerminal=arg1;
    }
    
    
    
}
