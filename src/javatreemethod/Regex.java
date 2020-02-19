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
    private LinkedList<Estado> Estados = new LinkedList();
    private Nodo RaizArbol=null;
    
    public void setID(String arg1){
        this.ID = arg1;
    }   
    
    public void addNodo(Nodo arg1){
        this.Nodos.add(arg1);
    }
    
    public LinkedList<Nodo> getNodos(){
        return this.Nodos;
    }
    
    public Nodo getRaizArbol(){
        return this.RaizArbol;
    }
    
    public void printRegex(){
        System.out.println("--------------------");
        System.out.println("Expresion Regular : "+this.ID);
        for(int i = 0; i<=this.Nodos.size()-1;i++){
            this.Nodos.get(i).printNodo();
        }
        System.out.println("--------------------");
    }
    
    public void generarArbol (){
        this.RaizArbol = Nodos.get(0);
        for(int i=1;i<this.Nodos.size();i++){
            
        }
    }
    
    public void setAnulables(){
        int TreeSize = this.Nodos.size()-1;
        Nodo TempNodo = new Nodo();
        for(int i=TreeSize;i >=0;i--){
            TempNodo = new Nodo();
            TempNodo=this.Nodos.get(i);
           
            if(TempNodo.getTipo()==Nodo.TipoNodo.POSITIVA){
                if(this.Nodos.get(i+1).getAnulable()){
                    TempNodo.setAnulableTrue();
                }
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.ALTERNANCIA){
                if( this.Nodos.get(i+1).getAnulable() || this.Nodos.get(i+2).getAnulable()  ){
                    TempNodo.setAnulableTrue();
                }
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.CONCATENACION){
                if( this.Nodos.get(i+1).getAnulable() && this.Nodos.get(i+2).getAnulable()  ){
                    TempNodo.setAnulableTrue();
                }
            }
        }
    }
    
    public void setPrimeros(){
        int TreeSize = this.Nodos.size()-1;
        Nodo TempNodo = new Nodo();
        LinkedList<Integer> TempPrimeros =  new LinkedList();
        for(int i=TreeSize;i >=0;i--){
            TempNodo = new Nodo();
            TempNodo=this.Nodos.get(i);
           
            if(TempNodo.getTipo()==Nodo.TipoNodo.KLEENE){
                TempPrimeros=this.Nodos.get(i+1).getPrimeros();
                for ( int Primero : TempPrimeros){
                    TempNodo.addPrimeros(Primero);
                }
                this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.POSITIVA){
                TempPrimeros=this.Nodos.get(i+1).getPrimeros();
                for ( int Primero : TempPrimeros){
                    TempNodo.addPrimeros(Primero);
                }
                this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.UNAOCERO){
                TempPrimeros=this.Nodos.get(i+1).getPrimeros();
                for ( int Primero : TempPrimeros){
                    TempNodo.addPrimeros(Primero);
                }
                this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.ALTERNANCIA){
                
                //PRIMERO NODO
                TempPrimeros=this.Nodos.get(i+1).getPrimeros();
                for ( int Primero : TempPrimeros){
                    TempNodo.addPrimeros(Primero);
                }          
                //SEGUNDO NODO
                TempPrimeros=this.Nodos.get(i+2).getPrimeros();
                for ( int Primero : TempPrimeros){
                    TempNodo.addPrimeros(Primero);
                }
               
                this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.CONCATENACION){
                
                //PRIMERO NODO
                TempPrimeros=this.Nodos.get(i+1).getPrimeros();
                for ( int Primero : TempPrimeros){
                    TempNodo.addPrimeros(Primero);
                }
                
                //SI PRIMER NODO ES ANULABLE
                if(this.Nodos.get(i+1).getAnulable()){
                    //SEGUNDO NODO
                    TempPrimeros=this.Nodos.get(i+2).getPrimeros();
                    for ( int Primero : TempPrimeros){
                        TempNodo.addPrimeros(Primero);
                    } 
                }
                
                this.Nodos.set(i, TempNodo);
            }
        }
    }
    
    public void setUltimos(){
        int TreeSize = this.Nodos.size()-1;
        Nodo TempNodo = new Nodo();
        LinkedList<Integer> TempUltimos =  new LinkedList();
        for(int i=TreeSize;i >=0;i--){
            TempNodo = new Nodo();
            TempNodo= this.Nodos.get(i);
           
            if(TempNodo.getTipo()==Nodo.TipoNodo.KLEENE){
                TempUltimos=this.Nodos.get(i+1).getUltimos();
                for ( int Ultimo : TempUltimos){
                    TempNodo.addUltimos(Ultimo);
                }
                this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.POSITIVA){
                TempUltimos=this.Nodos.get(i+1).getUltimos();
                for ( int Ultimo : TempUltimos){
                    TempNodo.addUltimos(Ultimo);
                }
                this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.UNAOCERO){
                TempUltimos=this.Nodos.get(i+1).getUltimos();
                for ( int Ultimo : TempUltimos){
                    TempNodo.addUltimos(Ultimo);
                }
                this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.ALTERNANCIA){
                
                //PRIMERO NODO
                TempUltimos=this.Nodos.get(i+1).getUltimos();
                for ( int Ultimo : TempUltimos){
                    TempNodo.addUltimos(Ultimo);
                }          
                //SEGUNDO NODO
                TempUltimos=this.Nodos.get(i+2).getUltimos();
                for ( int Ultimo : TempUltimos){
                    TempNodo.addUltimos(Ultimo);
                }
               
                this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.CONCATENACION){
                
                if(i==0){
                    TempNodo.addUltimos(this.Nodos.getLast().getID());
                }
                else{
                    //PRIMERO NODO
                    TempUltimos=this.Nodos.get(i+1).getUltimos();
                    for ( int Ultimo : TempUltimos){
                        TempNodo.addUltimos(Ultimo);
                    }
                
                    //SI SEGUNDO NODO ES ANULABLE
                    if(this.Nodos.get(i+2).getAnulable()){
                        //SEGUNDO NODO
                        TempUltimos=this.Nodos.get(i+2).getUltimos();
                        for ( int Ultimo : TempUltimos){
                            TempNodo.addUltimos(Ultimo);
                        } 
                    }
                    
                }
              
                this.Nodos.set(i, TempNodo);
            }
        }
    }
    
    public void setSiguientes(){
        int TreeSize = this.Nodos.size()-1;
        Nodo TempNodo = new Nodo();
        Nodo AuxNodo = new Nodo();
        LinkedList<Integer> TempIDs1 =  new LinkedList();
        LinkedList<Integer> TempIDs2 =  new LinkedList();
        
        //SE RECORREN NODOS
        for(int i=TreeSize;i >=0;i--){
            TempNodo = new Nodo();
            TempNodo=this.Nodos.get(i);
           
            if(TempNodo.getTipo()==Nodo.TipoNodo.CONCATENACION){
                TempIDs1 = this.Nodos.get(i+1).getUltimos();
                if(i==0){
                    TempIDs2 = this.Nodos.getLast().getPrimeros();
                }
                else{
                    TempIDs2 = this.Nodos.get(i+2).getPrimeros();
                }               
                
                //PARA CADA ULTIMO EN C2
                for(int ID1 : TempIDs1){
                    for(int j=0 ; j<this.Nodos.size();j++){
                        AuxNodo=this.Nodos.get(j);
                        if(AuxNodo.getID()==ID1){
                            //CADA PRIMERO DE C2 ES SIGUIENTE
                            for(int ID2 : TempIDs2){
                                AuxNodo.addSiguientes(ID2);
                            }
                            this.Nodos.set(j, AuxNodo);
                            break;
                        }
                    }
                }                
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.KLEENE||TempNodo.getTipo()==Nodo.TipoNodo.POSITIVA){
                TempIDs1 = this.Nodos.get(i+1).getUltimos();
                TempIDs2 = this.Nodos.get(i+1).getPrimeros();
                
                //PARA CADA ULTIMO EN C1
                for(int ID1 : TempIDs1){
                    
                    for(int j=0 ; j<this.Nodos.size();j++){
                        AuxNodo=this.Nodos.get(j);
                        if(AuxNodo.getID()==ID1){
                            //CADA PRIMERO DE C1 ES SIGUIENTE
                            for(int ID2 : TempIDs2){
                                AuxNodo.addSiguientes(ID2);
                            }
                            this.Nodos.set(j, AuxNodo);
                            break;
                        }
                    }
                } 
                
            }                   
        }
        
    }
    
    public void setEstados(Estado TempEstado){
        //EstadoInicial.addTerminal(this.Nodos.getFirst().getPrimeros());
        LinkedList<Integer> TempTerminales = TempEstado.getTerminales(); 
        Estado AuxEstado;
        Transicion NewTransicion;
        
        //PARA CADA ID DEL CONJUNTO
        for(int ID : TempTerminales){
            
            AuxEstado = new Estado();
            NewTransicion = new Transicion();
            
            NewTransicion.setID(ID);
            
            //SE BUSCA NODO CON ID
            for(Nodo TempNodo : this.Nodos){
                
                if(TempNodo.getID()==ID){                  
                    AuxEstado.addTerminal(TempNodo.getSiguientes());
                    int ExisteEstado =this.existeEstado(AuxEstado);
                    if(ExisteEstado==-1){                       
                        AuxEstado.setID(Estado.Contador);
                        Estado.Contador++;
                        this.Estados.add(AuxEstado);
                        NewTransicion.setDestino(this.Estados.getLast());
                    }
                    else{
                        NewTransicion.setDestino(this.Estados.get(ExisteEstado));
                    }
                    //SE AÑADE TRANSICION 
                    TempEstado.addTransicion(NewTransicion);
                    break;
                }
            }
        }
        
    }
    
    //RETORNA -1 SI NO EXISTE DE NO SER ASI RETORNA POSICION i DONDE EXISTE
    public int existeEstado(Estado TempEstado){
        for(int i = 0 ; i<this.Estados.size() ; i++){
            Estado AuxEstado = this.Estados.get(i);
            if(AuxEstado.compareEstado(TempEstado)){
                return i;
            }
        }
        return -1;
    }
    
    
}
