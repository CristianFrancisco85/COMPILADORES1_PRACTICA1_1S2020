/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatreemethod;

import java.util.LinkedList;

/**
 *
 * @author Cristian Meoño
 */
public class Nodo {
    
    enum TipoNodo{
        TERMINAL,
        KLEENE,
        POSITIVA,
        ALTERNANCIA,
        CONCATENACION,
        UNAOCERO
    }
    
    private int ID;
    private String Terminal;
    private Conjunto Conjunto;
    private boolean Anulable;
    private TipoNodo Tipo;
    private LinkedList<Integer> Primeros;
    private LinkedList<Integer> Ultimos;
    private Nodo Padre;
    private Nodo Izq;
    private Nodo Derecho;    
    
    public static int Contador = 1;
    
    Nodo(){
        Primeros = new LinkedList();
        Ultimos = new LinkedList();
        this.Anulable = false;
    }
    
    public void setConjunto(String ID, LinkedList<Conjunto> arg2){
        
        for(int i = 0 ; i <= arg2.size()-1 ; i++){
            
            if(arg2.get(i).getID().equals(ID)){
                this.Conjunto = arg2.get(i);
                break;
            }
        }
               
    }
    
    public void addPrimero(int arg1){
        
    }
    
    public void addUltimos(int arg2){
        
    }
    
    public void setTipo (TipoNodo arg1){
        Tipo = arg1;
    }
    
    public void setAnulableTrue(){
        this.Anulable = true;
    }
    
    public void setID(int arg1){
        this.ID = arg1;
        Nodo.Contador++;
    }
    
    public void setTerminal(String arg1){
        this.Terminal=arg1;
    }
    
    public void printNodo(){
        if(this.Tipo==TipoNodo.TERMINAL){
            System.out.println("Tipo : Terminal - ID : "+this.Terminal);
        }
        else if(this.Tipo==TipoNodo.KLEENE){
            System.out.println("Tipo : KLEENE ");
        }
        else if(this.Tipo==TipoNodo.POSITIVA){
            System.out.println("Tipo : POSITIVA ");
        }
        else if(this.Tipo==TipoNodo.ALTERNANCIA){
            System.out.println("Tipo : ALTERNANCIA ");
        }
        else if(this.Tipo==TipoNodo.CONCATENACION){
            System.out.println("Tipo : CONCATENACION ");
        }
        else if(this.Tipo==TipoNodo.UNAOCERO){
            System.out.println("Tipo : UNAOCERO ");
        }
    }
    

    
    
}
