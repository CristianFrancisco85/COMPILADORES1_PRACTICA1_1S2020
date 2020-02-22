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
    
    public LinkedList<Transicion> getTransiciones(){
        return this.ListaTransiciones;
    }
    
    public LinkedList<Integer> getTerminales(){
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
    
    
    //REGRESA -100 SI NO HAY TRANSICION , DE NO SER ASI REGRESA ID DEL ESTADO DESTINO
    public int testChar(String arg1,LinkedList<Nodo> ListaNodos,LinkedList<Estado> Estados){
        
        Nodo TempNodo;
        Estado TempEstado;
        int EstadoID=0;
        int Largo;
        int TransicionesSize=this.ListaTransiciones.size();
        String Lexema;       
        boolean Validacion;
        Transicion TempTransicion;
        
        //BUSCA SI EXISTE UNA TRANSICION PARA EL LEXEMA
        for(int i=0 ; i<TransicionesSize; i++ ){
            
            //SE OBTIENE LA TRANSICION i DEL ESTADO ACTUAL
            TempEstado=Estados.get(EstadoID);
            TempTransicion = TempEstado.getTransiciones().get(i);
            //OBTIENE NODO TERMINAL DE LA TRANSICION
            TempNodo = TempEstado.getNodoTerminal(ListaNodos,TempTransicion.getID());
            //VERIFICAR SI VA VALIDAR UN TERMINAL O CONJUNTO
            if(TempNodo.getConjunto()==null){
                //SI SE VALIDA UN TERMINAL VALIDAR CUANTOS CARACTERES NECESITA                
                Largo = TempNodo.getTerminal().length();
                if(Largo>1){
                    //SE VALIDA QUE HAYA LA CANTIDAD DE CARACTERES REQUERIDOS
                    if(Largo<=arg1.length()){
                       Lexema = arg1.substring(0,Largo); 
                    }
                    else{
                        //SI NO RETORNAR ESTADOID -1
                        return -1;
                    }                   
                }
                else{
                    Lexema = Character.toString(arg1.charAt(0));
                }               
            }
            else{
                //SI NO VALIDAR UN SOLO CARACTER
                Lexema = Character.toString(arg1.charAt(0));
            }
                       
            //SE VALIDA EN EL NODO TERMINAL DE LA TRANSICION
            Validacion = TempNodo.testChar(Lexema);
            
            //SI LA TRANSICION LO ACEPTA , ACTUALIZAR NODO,TRANSICIONES Y Lexema
            if(Validacion){
                i=-1;
                EstadoID=TempTransicion.getDestino().getID();
                TransicionesSize=Estados.get(EstadoID).getTransiciones().size();
                
                //SE VERIFICA SI LA CADENA QUEDARA VACIA
                if(Lexema.length()==arg1.length()){
                    return EstadoID;
                }
                //SI SE ACEPTO UNA CADENA 
                else if(Lexema.length()>1){
                    arg1=arg1.substring(Lexema.length(),arg1.length());
                }
                //SI SE VALIDO UN CARACTER
                else{
                    arg1=arg1.substring(1,arg1.length());
                }
                
            }
            //SI NO SEGUIR ITERANDO EN LA TRANSICIONES            
   
        }
        //NO EXISTE TRANSICION EN EL ESTADO PARA EL LEXEMA
        if(arg1.length()!=0){
            return -1;
        }
        return EstadoID;
    }
    
    //REGRESA NODO CON EL ID INDICADO
    public Nodo getNodoTerminal(LinkedList<Nodo> ListaNodos,int ID){
        for(int i = 0; i<ListaNodos.size();i++){
            if(ListaNodos.get(i).getID()==ID){
                return ListaNodos.get(i);
            }
        }
        return null;
    }
    
}

