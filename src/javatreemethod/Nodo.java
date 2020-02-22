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
        UNAOCERO,
        FINCADENA
    }
    
    private int ID;
    private int ID2;
    private String Terminal;
    private Conjunto Conjunto;
    private boolean Anulable;
    private TipoNodo Tipo;
    private LinkedList<Integer> Primeros;
    private LinkedList<Integer> Ultimos;
    private LinkedList<Integer> Siguientes;
    private Nodo Padre;
    private Nodo Izq;
    private Nodo Derecho;    
    
    public static int Contador = 1;
    public static int Contador2 = 1;
    
    Nodo(){
        Primeros = new LinkedList();
        Ultimos = new LinkedList();
        Siguientes = new LinkedList();
        Padre = null;
        Izq = null;
        Derecho = null;
        this.Anulable = false;
        Conjunto = null;
    }
    
    public void setConjunto(String ID, LinkedList<Conjunto> arg2){
        
        for(int i = 0 ; i <= arg2.size()-1 ; i++){
            
            if(arg2.get(i).getID().equals(ID)){
                this.Conjunto = arg2.get(i);
                break;
            }
        }
               
    }
    
    public Conjunto getConjunto(){
        return this.Conjunto;
    }
    
    public void addPrimeros(int arg1){
        if(!this.Primeros.contains(arg1)){
         this.Primeros.add(arg1);   
        }
    }
    
    public LinkedList getPrimeros(){
        return this.Primeros;
    }
    
    public void addUltimos(int arg1){
        if(!this.Ultimos.contains(arg1)){
         this.Ultimos.add(arg1);   
        }
    }
    
    public LinkedList getSiguientes(){
        return this.Siguientes;
    }
    
    public void addSiguientes(int arg1){
        if(!this.Siguientes.contains(arg1)){
         this.Siguientes.add(arg1);   
        }
    }
    
    public LinkedList getUltimos(){
        return this.Ultimos;
    }
    
    public void setTipo (TipoNodo arg1){
        Tipo = arg1;
    }
    
    public TipoNodo getTipo (){
        return this.Tipo;
    }
    
    public void setAnulableTrue(){
        this.Anulable = true;
    }
    
    public boolean getAnulable(){
        return this.Anulable;
    }
    
    public void setID(int arg1){
        this.ID = arg1;
        Nodo.Contador++;
    }
    
    public int getID(){
        return this.ID;
    }  
    
    public void setID2(int arg1){
        this.ID2 = arg1;
        Nodo.Contador2++;
    }
    
    public int getID2(){
        return this.ID2;
    }  
    
    public void setTerminal(String arg1){
        this.Terminal=arg1;
    }
    
    public String getTerminal (){
        return this.Terminal;
    }
    
    public void printNodo(){
        if(this.Tipo==TipoNodo.TERMINAL){
            System.out.print("Tipo : Terminal - ID : ["+this.ID+"--"+this.Terminal+"]");
            System.out.print(" SIGUIENTES : ");
            for(int num : this.Siguientes){
                System.out.print(num+",");
            }
            
        }
        else if(this.Tipo==TipoNodo.KLEENE){
            System.out.print("Tipo : KLEENE ");        
            
        }
        else if(this.Tipo==TipoNodo.POSITIVA){
            System.out.print("Tipo : POSITIVA ");
        }
        else if(this.Tipo==TipoNodo.ALTERNANCIA){
            System.out.print("Tipo : ALTERNANCIA ");
        }
        else if(this.Tipo==TipoNodo.CONCATENACION){
            System.out.print("Tipo : CONCATENACION ");
        }
        else if(this.Tipo==TipoNodo.UNAOCERO){
            System.out.print("Tipo : UNAOCERO ");
        }
        else if(this.Tipo==TipoNodo.FINCADENA){
            System.out.print("Tipo : FIN DE CADENA # - ID : ["+this.ID+"]");
            System.out.print(" SIGUIENTES : ");
            for(int num : this.Siguientes){
                System.out.print(num+",");
            }
        }
        
        System.out.print(" PRIMEROS : ");
        for(int num : this.Primeros){
            System.out.print(num+",");
        }
        System.out.print(" ULTIMOS : ");
        for(int num : this.Ultimos){
            System.out.print(num+",");
        }
        System.out.println();
        
    }
 
    public Nodo getPadre(){
        return this.Padre;
    }
    
    public Nodo getIzquierdo(){
        return this.Izq;
    }
    
    public Nodo getDerecho(){
        return this.Derecho;
    }
    
    public void setPadre(Nodo arg1){
        this.Padre=arg1;
    }
    
    public void setIzquierdo(Nodo arg1){
        this.Izq=arg1;
    }
    
    public void setDerecho(Nodo arg1){
        this.Derecho=arg1;
    }
    
    //Testea el caracter en el Nodo
    public boolean testChar(String arg1){
        
        
        //SI ES UN TERMINAL NORMAL
        if(this.getConjunto()==null){
            //VALIDAR UN STRING
            if(this.Terminal.length()>1){
                if(this.Terminal.equals(arg1)){
                    return true;
                }
                else{
                    return false;
                }
            }
           
            //SI NO VALIDAR UN CARACTER
            else if (this.Terminal.charAt(0)==arg1.charAt(0)){
                return true;
            }
            else{
                return false;
            }
        }
        //PROBAR EN CONJUNTO
        else{
            if(this.getConjunto().testChar(arg1.charAt(0))){
                return true;
            }
            else{
                return false;
            }
            
        }
        
    }
}
