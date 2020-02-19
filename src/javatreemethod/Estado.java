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
public class Estado {
    
    private int ID;
    private LinkedList<Integer> TerminalesID;
    private boolean EstadoAceptacion;
    private LinkedList<Transicion> ListaTransiciones;
    
    public static int Contador =0;
    
    Estado(){
        TerminalesID = new LinkedList();
        ListaTransiciones= new LinkedList();
        EstadoAceptacion=false;
    }
    
    public int getID(){
        return this.ID;
    }
    public void setID(int arg1){
        this.ID = arg1;
    }
    
    public void setAceptacion(){
        this.EstadoAceptacion=true;
    }
    
    public boolean getAceptacion(){
        return this.EstadoAceptacion;
    }
    
    public void addTerminal(int arg1){
        if(!this.TerminalesID.contains(arg1)){
         this.TerminalesID.add(arg1);   
        }
    }
    
    public void addTerminal(LinkedList arg1){
        LinkedList<Integer> TempList = arg1;
        for(int num : TempList){
            if(!this.TerminalesID.contains(num)){
                this.TerminalesID.add(num);   
            }
        }
      
    }
    
    public void addTransicion(Transicion arg1){
        this.ListaTransiciones.add(arg1);
    }
    
    public LinkedList getTerminales(){
        return this.TerminalesID;
    }
    
    public boolean compareEstado(Estado arg1){
        
        if(this.getTerminales().size()==arg1.getTerminales().size()){
            LinkedList<Integer> TempTerminales = this.getTerminales();
            for (int ID : TempTerminales){
                if(!arg1.getTerminales().contains(ID)){
                    return false;
                }
            }
            return true;
        }
        else{
            return false;
        }
    }
    
}

