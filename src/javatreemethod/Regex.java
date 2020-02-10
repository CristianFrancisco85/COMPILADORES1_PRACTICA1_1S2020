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
public class Regex {
    
    private String ID;
    private LinkedList<Nodo> Nodos = new LinkedList();
    
    public void setID(String arg1){
        this.ID = arg1;
    }   
    
    public void addNodo(Nodo arg1){
        this.Nodos.add(arg1);
    }
    
    public void printRegex(){
        System.out.println("--------------------");
        System.out.println("Expresion Regular : "+this.ID);
        for(int i = 0; i<=this.Nodos.size()-1;i++){
            this.Nodos.get(i).printNodo();
        }
        System.out.println("--------------------");
    }
    
}
