/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatreemethod;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

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
    
    public String getID(){
        return this.ID;
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
        for(Nodo TempNodo : this.Nodos){
            TempNodo.printNodo();
        }
        System.out.println("--------------------");
        System.out.println("Estados de : "+this.ID +"  Cantidad: "+this.Estados.size());        
        for(Estado TempEstado : this.Estados) {
            System.out.print("Estado ["+TempEstado.getID()+"] TERMINALES:[");
            for(int num : TempEstado.getTerminales()){
                System.out.print(num+",");
            }            
            System.out.print("]");
            if(TempEstado.getAceptacion()){
                System.out.println("--ES ESTADO DE ACEPTACION");
            }
            else{
                System.out.println();
            }
            for(Transicion TempTransicion : TempEstado.getTransiciones()){
                System.out.println("Transicion -- IDTerminal: "+TempTransicion.getID()+" HACIA ESTADO: "+TempTransicion.getDestino().getID());
            }
        }
        System.out.println("--------------------");
        System.out.println("Arbol de : "+this.ID);
        for(Nodo TempNodo : this.Nodos){
            
        }
        System.out.println("--------------------");
        
    }
    
    public void generarArbol (){
        this.RaizArbol = this.Nodos.getFirst();
        //DESDE EL SEGUNDO NODO HASTA EL PENULTIMO
        for(int i =1; i<this.Nodos.size()-1;i++){
          this.insertarArbol(this.Nodos.get(i));
        } 
        this.RaizArbol.setDerecho(Nodos.getLast());        
    }
    
    public void insertarArbol(Nodo NuevoNodo){
        Nodo TempNodo = new Nodo();
        TempNodo = this.RaizArbol;
        Nodo Anterior=null;
        
        while(TempNodo!=null){           
            Anterior=TempNodo;
            Nodo Aux = new Nodo();
            if(Anterior.getIzquierdo()==null){
                TempNodo=TempNodo.getIzquierdo();
            }
            else if(Anterior.getIzquierdo().getTipo()!=Nodo.TipoNodo.TERMINAL){
                TempNodo=TempNodo.getIzquierdo();
            }
                       
            else if(Anterior.getDerecho()==null ) {
                
                if(Anterior.getTipo() == Nodo.TipoNodo.KLEENE || Anterior.getTipo() == Nodo.TipoNodo.POSITIVA || Anterior.getTipo() == Nodo.TipoNodo.UNAOCERO ){
                    while(TempNodo!=null){
                        Aux=Anterior;
                        Anterior = Anterior.getPadre();
                        if(Anterior.getDerecho()==null ) {
                            if(Anterior.getTipo() != Nodo.TipoNodo.KLEENE && Anterior.getTipo() != Nodo.TipoNodo.POSITIVA && Anterior.getTipo() != Nodo.TipoNodo.UNAOCERO ){
                                TempNodo=Anterior.getDerecho();
                            }                         
                        }
                        else if(Anterior.getDerecho().getTipo()!=Nodo.TipoNodo.TERMINAL && Anterior.getDerecho()!=Aux){
                            TempNodo=Anterior.getDerecho();
                            break;
                        }
                    }
                }
                else{
                  TempNodo=TempNodo.getDerecho();  
                }
                
            }
            else if(Anterior.getDerecho().getTipo()!=Nodo.TipoNodo.TERMINAL){
                TempNodo=TempNodo.getDerecho();
            }
            else{              
                while(TempNodo!=null){
                    Aux=Anterior;
                    Anterior = Anterior.getPadre();
                    if(Anterior.getDerecho()==null) {
                        if(Anterior.getTipo() != Nodo.TipoNodo.KLEENE && Anterior.getTipo() != Nodo.TipoNodo.POSITIVA && Anterior.getTipo() != Nodo.TipoNodo.UNAOCERO ){
                            TempNodo=Anterior.getDerecho();
                        }                         
                    }
                    else if(Anterior.getDerecho().getTipo()!=Nodo.TipoNodo.TERMINAL && Anterior.getDerecho()!=Aux ){
                        TempNodo=Anterior.getDerecho();
                        break;
                    }
                }
            }
            
        }
        
        NuevoNodo.setPadre(Anterior);
        
        if(Anterior.getTipo() == Nodo.TipoNodo.KLEENE || Anterior.getTipo() == Nodo.TipoNodo.POSITIVA || Anterior.getTipo() == Nodo.TipoNodo.UNAOCERO ){
            Anterior.setIzquierdo(NuevoNodo);
        } 
        else{
            if(Anterior.getIzquierdo()==null){              
                Anterior.setIzquierdo(NuevoNodo);  
            }
            else if(Anterior.getDerecho()==null){
                Anterior.setDerecho(NuevoNodo); 
            }
            
        }
       
    }
    
    public void setAnulables(){
        int TreeSize = this.Nodos.size()-1;
        Nodo TempNodo = new Nodo();
        for(int i=TreeSize;i >=0;i--){
            TempNodo = new Nodo();
            TempNodo=this.Nodos.get(i);
           
            if(TempNodo.getTipo()==Nodo.TipoNodo.POSITIVA){
                if(this.Nodos.get(i).getIzquierdo().getAnulable()){
                    TempNodo.setAnulableTrue();
                }
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.ALTERNANCIA){  
                if( this.Nodos.get(i).getIzquierdo().getAnulable() || this.Nodos.get(i).getDerecho().getAnulable() ){
                    TempNodo.setAnulableTrue();
                }
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.CONCATENACION){
              
                if( this.Nodos.get(i).getIzquierdo().getAnulable() && this.Nodos.get(i).getDerecho().getAnulable()  ){
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
                TempPrimeros=this.Nodos.get(i).getIzquierdo().getPrimeros();
                for ( int Primero : TempPrimeros){
                    TempNodo.addPrimeros(Primero);
                }
                this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.POSITIVA){
                TempPrimeros=this.Nodos.get(i).getIzquierdo().getPrimeros();
                for ( int Primero : TempPrimeros){
                    TempNodo.addPrimeros(Primero);
                }
                this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.UNAOCERO){
                TempPrimeros=this.Nodos.get(i).getIzquierdo().getPrimeros();
                for ( int Primero : TempPrimeros){
                    TempNodo.addPrimeros(Primero);
                }
                this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.ALTERNANCIA){
                        
                //AGREGAR PRIMEROS DE PRIMERO NODO
                TempPrimeros=this.Nodos.get(i).getIzquierdo().getPrimeros();
                for ( int Primero : TempPrimeros){
                    TempNodo.addPrimeros(Primero);
                }

                //AGREGAR PRIMEROS DE SEGUNDO NODO
                TempPrimeros=this.Nodos.get(i).getDerecho().getPrimeros();
                for ( int Primero : TempPrimeros){
                    TempNodo.addPrimeros(Primero);
                } 
                
                this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.CONCATENACION){
                        
                //AGREGAR PRIMEROS DE PRIMERO NODO
                TempPrimeros=this.Nodos.get(i).getIzquierdo().getPrimeros();
                for ( int Primero : TempPrimeros){
                    TempNodo.addPrimeros(Primero);
                }
                
                //SI PRIMER NODO ES ANULABLE
                if(this.Nodos.get(i).getIzquierdo().getAnulable()){
                    //AGREGAR PRIMEROS DE SEGUNDO NODO
                    TempPrimeros=this.Nodos.get(i).getDerecho().getPrimeros();
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
                TempUltimos=this.Nodos.get(i).getIzquierdo().getUltimos();
                for ( int Ultimo : TempUltimos){
                    TempNodo.addUltimos(Ultimo);
                }
                this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.POSITIVA){
                TempUltimos=this.Nodos.get(i).getIzquierdo().getUltimos();
                for ( int Ultimo : TempUltimos){
                    TempNodo.addUltimos(Ultimo);
                }
                this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.UNAOCERO){
                TempUltimos=this.Nodos.get(i).getIzquierdo().getUltimos();
                for ( int Ultimo : TempUltimos){
                    TempNodo.addUltimos(Ultimo);
                }
                this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.ALTERNANCIA){

                    //AGREGAR ULTIMOS SEGUNDO NODO
                    TempUltimos=this.Nodos.get(i).getDerecho().getUltimos();
                    for ( int Ultimo : TempUltimos){
                        TempNodo.addUltimos(Ultimo);
                    }

                    //AGREGAR ULTIMOS DE PRIMER NODO
                    TempUltimos=this.Nodos.get(i).getIzquierdo().getUltimos();
                    for ( int Ultimo : TempUltimos){
                        TempNodo.addUltimos(Ultimo);
                    }   
                    
                    this.Nodos.set(i, TempNodo);
            }
            else if(TempNodo.getTipo()==Nodo.TipoNodo.CONCATENACION){

                    //AGREGAR ULTIMOS SEGUNDO NODO
                    TempUltimos=this.Nodos.get(i).getDerecho().getUltimos();
                    for ( int Ultimo : TempUltimos){
                        TempNodo.addUltimos(Ultimo);
                    }

                    //SI SEGUNDO NODO ES ANULABLE
                    if(this.Nodos.get(i).getDerecho().getAnulable()){
                        //AGREGAR ULTIMOS DE PRIMER NODO
                        TempUltimos=this.Nodos.get(i).getIzquierdo().getUltimos();
                        for ( int Ultimo : TempUltimos){
                            TempNodo.addUltimos(Ultimo);
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
                
                
                TempIDs1 = TempNodo.getIzquierdo().getUltimos();
                if(i==0){
                    TempIDs2 = this.Nodos.getLast().getPrimeros();
                }
                else{
                    TempIDs2 = TempNodo.getDerecho().getPrimeros();
                }               
                
                //PARA CADA ULTIMO EN C1
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
                TempIDs1 = TempNodo.getIzquierdo().getUltimos();
                TempIDs2 = TempNodo.getIzquierdo().getPrimeros();
                
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
    
    public void setEstados(){
        //SE DEFINE ESTADO INICIAL
        Estado EstadoInicial = new Estado();
        EstadoInicial.addTerminal(this.Nodos.getFirst().getPrimeros());
        EstadoInicial.setID(Estado.Contador);
        Estado.Contador++;
        //SE AGREGA ESTADO INICIAL
        this.Estados.add(EstadoInicial);
        
        int ListEstadoSize = this.Estados.size();
        Estado TempEstado;
        for(int i = 0;i<ListEstadoSize;i++){
            TempEstado = this.Estados.get(i);
            setEstado(TempEstado);
            ListEstadoSize = this.Estados.size();
        }
        Estado.Contador=0;
        
    }
    
    public void setEstado(Estado TempEstado){       
        LinkedList<Integer> TempTerminales = TempEstado.getTerminales(); 
        Estado AuxEstado;
        Transicion NewTransicion;       
        
        //PARA CADA ID DEL CONJUNTO DE TERMINALES
        for(int ID : TempTerminales){
            
            AuxEstado = new Estado();
            NewTransicion = new Transicion();
            
            NewTransicion.setID(ID);
            
            //SI ES UNA TRANSICION CON #
            if(ID==this.Nodos.getLast().getID()){
                TempEstado.setAceptacion();
            }
            else{
            //SI NO SE BUSCA NODO CON ID    
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
    
    public void PreOrden(Nodo Raiz){
        if(Raiz!=null){
            System.out.println(Raiz.getTipo());
            PreOrden(Raiz.getIzquierdo());
            PreOrden(Raiz.getDerecho());
        }
    }
    
    
    public boolean TestLexema(String arg1){
        int EstadoID=0;    
        EstadoID=this.Estados.get(EstadoID).testChar(arg1,this.Nodos,this.Estados);
        if(EstadoID==-1){
            return false;
        }        
        else if(this.Estados.get(EstadoID).getAceptacion()){
           return true; 
        }
        else{
            return false;
        }
         
    }
    
    public void graficarArbol(){
        
        File f;
        f = new File("/Users/cristianmeono/Desktop/COMPI/Arbol/Arbol"+this.ID+".dot");   
        String Ruta="/Users/cristianmeono/Desktop/COMPI/Arbol/Arbol"+this.ID;
        String Path = "/Users/cristianmeono/Desktop/";

        //Escritura
        try{
            
        if (!f.exists()) {
                f.createNewFile();
        }              
        FileWriter w = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);	
        wr.write("graph{\n");
        for(int i = 0 ; i<this.Nodos.size();i++){
            Nodo TempNodo = this.Nodos.get(i);
            if(TempNodo.getTipo()==Nodo.TipoNodo.KLEENE||TempNodo.getTipo()==Nodo.TipoNodo.POSITIVA||TempNodo.getTipo()==Nodo.TipoNodo.UNAOCERO){
                wr.append("\""+TempNodo.getID2()+"_"+this.NodeFormatter(TempNodo)+"\""+"--"+"\""+TempNodo.getIzquierdo().getID2()+"_"+this.NodeFormatter(TempNodo.getIzquierdo())+"\""+";\n");
            }
            else if(TempNodo.getTipo()!=Nodo.TipoNodo.TERMINAL&&TempNodo.getTipo()!=Nodo.TipoNodo.FINCADENA){
            wr.append("\""+TempNodo.getID2()+"_"+this.NodeFormatter(TempNodo)+"\""+"--"+"\""+TempNodo.getIzquierdo().getID2()+"_"+this.NodeFormatter(TempNodo.getIzquierdo())+"\""+";\n");
            wr.append("\""+TempNodo.getID2()+"_"+this.NodeFormatter(TempNodo)+"\""+"--"+"\""+TempNodo.getDerecho().getID2()+"_"+this.NodeFormatter(TempNodo.getDerecho())+"\""+";\n");
            }
        }
            
        wr.append("}"); 

        wr.close();
        bw.close();
        ProcessBuilder pbuilder = new ProcessBuilder();
        //pbuilder.command(" dot -Tpng -o "+Ruta+".png "+Ruta+".dot");
        String Comando = " dot -Tpng -o "+Ruta+".png "+Ruta+".dot";
        String direccionpng = Ruta+".png";
        String direcciondot = Ruta+".dot";
        pbuilder = new ProcessBuilder("/bin/bash","dot","-Tpng","-o",direccionpng,direcciondot);
        pbuilder.redirectErrorStream(true);
        pbuilder.start();
        }
        catch(IOException e){
             e.printStackTrace();
        };
           
        }
    
    public String NodeFormatter(Nodo arg1){
            if(arg1.getTipo()==Nodo.TipoNodo.ALTERNANCIA){
                return "|";
            }
            else if(arg1.getTipo()==Nodo.TipoNodo.CONCATENACION){
                return ".";
            }
            else if(arg1.getTipo()==Nodo.TipoNodo.FINCADENA){
                return "#";
            }
            else if(arg1.getTipo()==Nodo.TipoNodo.KLEENE){
                return "*";
            }
            else if(arg1.getTipo()==Nodo.TipoNodo.POSITIVA){
                return "+";
            }
            else if(arg1.getTipo()==Nodo.TipoNodo.TERMINAL){
                return arg1.getTerminal();
            }
            else if(arg1.getTipo()==Nodo.TipoNodo.UNAOCERO){
                return ".";
            }
            return "";
        }
    
    public String ListString(LinkedList<Integer> Lista){
        String Aux = "";
        for(int aux : Lista){
            Aux= Aux+Integer.toString(aux)+",";
        }
        return Aux;
    }
    
    public void graficarSiguientes(){
        File f;
        f = new File("/Users/cristianmeono/Desktop/COMPI/Siguientes/Siguientes"+this.ID+".dot");   
        String Ruta="/Users/cristianmeono/Desktop/COMPI/Siguientes/Siguientes"+this.ID;
        String Path = "/Users/cristianmeono/Desktop/";

        //Escritura
        try{
            
        if (!f.exists()) {
                f.createNewFile();
        }              
        FileWriter w = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);	
        wr.write("graph{\n");
        for(int i = 0 ; i<this.Nodos.size();i++){
            Nodo TempNodo = this.Nodos.get(i);
            if(TempNodo.getID()>=1){
                wr.append("\" Hoja: "+TempNodo.getTerminal()+" -- ID: "+this.NodeFormatter(TempNodo)+" -- Siguientes: "+this.ListString(TempNodo.getSiguientes())+"\"");
            }
            
        }
            
        wr.append("}"); 

        wr.close();
        bw.close();
        ProcessBuilder pbuilder = new ProcessBuilder();        
        String direccionpng = Ruta+".png";
        String direcciondot = Ruta+".dot";
        //ProcessBuilder pbuilder = new ProcessBuilder("/bin/bash","dot","-Tpng","-o",direccionpng,direcciondot);
//        pbuilder.command(" dot -Tpng -o "+Ruta+".png "+Ruta+".dot");
//        pbuilder.redirectErrorStream(true);
//        pbuilder.start();
        }
        catch(IOException e){
             e.printStackTrace();
        };
    }
    
    public void graficarTransiciones(){
        File f;
        f = new File("/Users/cristianmeono/Desktop/COMPI/Transiciones/Transiciones"+this.ID+".dot");   
        String Ruta="/Users/cristianmeono/Desktop/COMPI/Transiciones/Transiciones"+this.ID;
        String Path = "/Users/cristianmeono/Desktop/";

        //Escritura
        try{
            
        if (!f.exists()) {
                f.createNewFile();
        }              
        FileWriter w = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);	
        wr.write("graph{\n");
        for(int i = 0 ; i<this.Estados.size();i++){
            Estado TempNodo = this.Estados.get(i);
            wr.append("\" Estado: S"+TempNodo.getID()+" Transiciones : \n");
            for(Transicion TempTransicion : TempNodo.getTransiciones()){
                wr.append("Terminal : "+TempTransicion.getID()+ " Hacia Estado S"+TempTransicion.getDestino().getID()+"\n");
            }
            wr.append("\"\n");
        
        }
            
        wr.append("}"); 

        wr.close();
        bw.close();
        ProcessBuilder pbuilder = new ProcessBuilder();        
        String direccionpng = Ruta+".png";
        String direcciondot = Ruta+".dot";
        //ProcessBuilder pbuilder = new ProcessBuilder("/bin/bash","dot","-Tpng","-o",direccionpng,direcciondot);
//        pbuilder.command(" dot -Tpng -o "+Ruta+".png "+Ruta+".dot");
//        pbuilder.redirectErrorStream(true);
//        pbuilder.start();
        }
        catch(IOException e){
             e.printStackTrace();
        };
    }
    
    public void graficarAFD(){
        File f;
        f = new File("/Users/cristianmeono/Desktop/AFD"+this.ID+".dot");   
        String Ruta="/Users/cristianmeono/Desktop/AFD"+this.ID;
        String Path = "/Users/cristianmeono/Desktop/";

        //Escritura
        try{
            
        if (!f.exists()) {
                f.createNewFile();
        }              
        FileWriter w = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);	
        wr.write("graph{\n");
        for(int i = 0 ; i<this.Estados.size();i++){
            Estado TempNodo = this.Estados.get(i);
            wr.append("\" Estado: S"+TempNodo.getID()+" Transiciones : \n");
            for(Transicion TempTransicion : TempNodo.getTransiciones()){
                wr.append("Terminal : "+TempTransicion.getID()+ " Hacia Estado S"+TempTransicion.getDestino().getID()+"\n");
            }
            wr.append("\"\n");
        
        }
            
        wr.append("}"); 

        wr.close();
        bw.close();
        ProcessBuilder pbuilder = new ProcessBuilder();        
        String direccionpng = Ruta+".png";
        String direcciondot = Ruta+".dot";
        //ProcessBuilder pbuilder = new ProcessBuilder("/bin/bash","dot","-Tpng","-o",direccionpng,direcciondot);
//        pbuilder.command(" dot -Tpng -o "+Ruta+".png "+Ruta+".dot");
//        pbuilder.redirectErrorStream(true);
//        pbuilder.start();
        }
        catch(IOException e){
             e.printStackTrace();
        };
    }
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
 

