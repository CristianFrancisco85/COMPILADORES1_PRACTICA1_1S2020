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
public class Conjunto {
    
    private String ID;
    private int BeginInterval;
    private int EndInterval;
    private LinkedList<Integer> CharList;
    
    Conjunto(){
        this.BeginInterval = -1;
        this.EndInterval = -1;
        this.CharList = new LinkedList();
    }
    
    public void setID(String arg1){
        this.ID = arg1;
    }
    
    public String getID(){
        return this.ID;
    }
    
    public void setBeginInterval(String arg1){
        this.BeginInterval = (int)arg1.charAt(0);
    }
    
    public int getBeginInterval(){
        return this.BeginInterval;
    }
    
    public void setEndInterval(String arg1){
        this.EndInterval = (int)arg1.charAt(0);
    }
    
    public int getEndInterval(){
        return this.EndInterval;
    }
    
    public void addCharToList(char arg1){
        this.CharList.add((int)arg1);
    }
    
    public LinkedList<Integer> getCharList(){
        return this.CharList;
    }
    
    public boolean testChar(char arg1){
        //PROBAR EN INTERVALO
        if(this.BeginInterval!=-1){
            if((int)arg1>=this.BeginInterval && (int)arg1<=this.EndInterval){
                return true;
            }
            else{
                return false;
            }
        }
        //PROBAR EN LISTA DE CARACTERES
        else{
            if(this.CharList.contains((int)arg1)){
                return true;
            }
            else{
                return false;
            }
            
        }
    }
    
}
