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
public class Scanner {
    
    public LinkedList<Token> TablaToken;
    public LinkedList<Conjunto> Conjuntos;
    public LinkedList<Palabra> Palabras;
    public LinkedList<Regex> Expresiones;
    
    Scanner(){
       TablaToken = new LinkedList();
       Conjuntos = new LinkedList();
       Palabras = new LinkedList();
       Expresiones = new LinkedList();
    }
    
    public void analizeText(String Code){
        int MyChar;
        String TempLexema;
        for(int i=0;i<=Code.length()-1;i++){
            
            MyChar = Code.charAt(i);
            Token TempToken = new Token();
            TempLexema="";
            
            // PARA SIMBOLOS INDIVIDUALES
            
            switch (MyChar){
                case '{':
                    TempToken.setTipo("ABRE_LLAVE");
                    TempToken.setLexema("{");
                    this.TablaToken.add(TempToken);
                    break;
                case '}':
                    TempToken.setTipo("CIERRA_LLAVE");
                    TempToken.setLexema("}");
                    this.TablaToken.add(TempToken);
                    break;
                case '%':
                    TempToken.setTipo("PORCENTAJE");
                    TempToken.setLexema("%");
                    this.TablaToken.add(TempToken);
                    break;
                case '~':
                    TempToken.setTipo("GUION_CURVO");
                    TempToken.setLexema("~");
                    this.TablaToken.add(TempToken);
                    break;
                case '-':
                    TempToken.setTipo("GUION");
                    TempToken.setLexema("-");
                    this.TablaToken.add(TempToken);
                    break;
                case '>':
                    TempToken.setTipo("SIGNO_MAYOR");
                    TempToken.setLexema(">");
                    this.TablaToken.add(TempToken);
                    break;
                case '.':
                    TempToken.setTipo("PUNTO");
                    TempToken.setLexema(".");
                    this.TablaToken.add(TempToken);
                    break;
                case '*':
                    TempToken.setTipo("SIGNO_ASTERISCO");
                    TempToken.setLexema("*");
                    this.TablaToken.add(TempToken);
                    break;
                case '+':
                    TempToken.setTipo("SIGNO_MAS");
                    TempToken.setLexema("+");
                    this.TablaToken.add(TempToken);
                    break;
                case ';':
                    TempToken.setTipo("PUNTO_COMA");
                    TempToken.setLexema(";");
                    this.TablaToken.add(TempToken);
                    break;
                case ':':
                    TempToken.setTipo("DOS_PUNTOS");
                    TempToken.setLexema(":");
                    this.TablaToken.add(TempToken);
                    break;
                case '|':
                    TempToken.setTipo("SIGNO_ALTERNANCIA");
                    TempToken.setLexema("|");
                    this.TablaToken.add(TempToken);
                    break;
                case '?':
                    TempToken.setTipo("SIGNO_UNAOCERO");
                    TempToken.setLexema("?");
                    this.TablaToken.add(TempToken);
                    break;
                case ',':
                    TempToken.setTipo("COMA");
                    TempToken.setLexema(",");
                    this.TablaToken.add(TempToken);
                    break;
            }
            
            //PARA IDENTIFICADORES
            
            if(((int)MyChar>=65 && (int)MyChar<=90)||((int)MyChar>=97 && (int)MyChar<=122)||((int)MyChar>=48 && (int)MyChar<=57)||(MyChar=='_')){
                for(int j=i;j<=Code.length()-1;j++){
                    MyChar=Code.charAt(j);
                    if(((int)MyChar>=65 && (int)MyChar<=90)||((int)MyChar>=97 && (int)MyChar<=122)||((int)MyChar>=48 && (int)MyChar<=57)||MyChar=='_'){
                        TempLexema = TempLexema+Code.charAt(j);
                    }
                    else{
                        i=j-1;
                        break;
                    }
                }
                TempToken.setTipo("ID");
                TempToken.setLexema(TempLexema);
                this.TablaToken.add(TempToken);
            }
            
            //PARA CADENAS
            
            else if((int)MyChar ==34 ){
                i++;
                MyChar=Code.charAt(i);
                for(int j=i;j<=Code.length()-1;j++){
                    MyChar=Code.charAt(j);
                    if((int)MyChar ==34){
                        i=j;
                        TempToken.setTipo("CADENA");
                        TempToken.setLexema(TempLexema);
                        this.TablaToken.add(TempToken);
                        break;
                    }
                    else {
                        TempLexema = TempLexema + Code.charAt(j);
                    }
                }
            }
            
            //PARA COMENTARIOS MULTILINEA
            
            else if(MyChar=='<'){
                i++;
                MyChar=Code.charAt(i);
                if(MyChar=='!'){
                    i++;
                    MyChar=Code.charAt(i);
                    for(int j=i;j<=Code.length()-1;j++){
                        MyChar=Code.charAt(j);
                        if(MyChar=='!'){
                            j++;
                            MyChar=Code.charAt(j);
                            if(MyChar=='>'){
                                i=j;
                                TempToken.setTipo("COMENTARIO");
                                TempToken.setLexema(TempLexema);
                                this.TablaToken.add(TempToken);
                                break;  
                            }
                            else{
                                //CONTROLAR EXCEPCION 
                            }
                        }
                        else {
                            TempLexema = TempLexema + Code.charAt(j);
                        }
                    }
                }
                else{
                    //CONTROLAR EXCEPCION 
                } 
            }
            
            //PARA COMENTARIOS DE UNA LINEA
            else if(MyChar == '/'){
                i++;
                MyChar=Code.charAt(i);
                if(MyChar=='/'){
                    i++;
                    MyChar=Code.charAt(i);
                    for(int j=i;j<=Code.length()-1;j++){
                        MyChar=Code.charAt(j);
                        
                        if((int)MyChar==10){
                        i=j;
                        TempToken.setTipo("COMENTARIO");
                        TempToken.setLexema(TempLexema);
                        this.TablaToken.add(TempToken);
                        break;
                        }
                        else {
                            TempLexema = TempLexema + Code.charAt(j);
                        }
                    }
                }
                else {
                    //CONTROLAR EXCEPCION
                }
            }
            
        }
    }
    
    public void analizeTokens(){
        for(int i =0 ; i<=this.TablaToken.size()-1;i++){
            
            //DETERMINA CONJUNTOS, EXPRESIONES REGULARES Y PALABRAS A EVALUAR
            if(this.TablaToken.get(i).getTipo().equals("ID")){
                
                i++;
                
                //PARA CONJUNTOS Y PALABRAS A EVALUAR
                if(this.TablaToken.get(i).getTipo().equals("DOS_PUNTOS")){
                    
                    i++;
                    
                    // PARA PALABRAS A EVALUAR 
                    if(this.TablaToken.get(i).getTipo().equals("CADENA")){
                        Palabra TempPalabra = new Palabra();
                        TempPalabra.setID(this.TablaToken.get(i-2).getLexema());
                        TempPalabra.setLexema(this.TablaToken.get(i).getLexema()); 
                        Palabras.add(TempPalabra);
                    }
                    
                    // PARA CONJUNTOS TIPO RANGO
                    else if(this.TablaToken.get(i+4).getTipo().equals("GUION_CURVO")){
                        Conjunto TempConjunto = new Conjunto();
                        TempConjunto.setID(this.TablaToken.get(i).getLexema());
                        i+=3;                      
                        TempConjunto.setBeginInterval(this.TablaToken.get(i).getLexema());
                        TempConjunto.setEndInterval(this.TablaToken.get(i+2).getLexema());
                        Conjuntos.add(TempConjunto);
                    }
                                 
                    //PARA CONJUNTOS TIPO LISTA
                    else if(this.TablaToken.get(i+4).getTipo().equals("COMA")){
                        Conjunto TempConjunto = new Conjunto();
                        TempConjunto.setID(this.TablaToken.get(i).getLexema());
                        i+=3;
                        // ADVERTENCIA -- CICLO CON NECESIDAD DE BREAK
                        for( int j = i ;j>0;j+=2 ){
                            TempConjunto.addCharToList(this.TablaToken.get(j).getLexema().charAt(0));
                            if(this.TablaToken.get(j+1).getTipo().equals("PUNTO_COMA")){
                                i=j+1;
                                break;
                            }
                        }
                        Conjuntos.add(TempConjunto);
                    }                         
                }
                
                //PARA EXPRESIONES REGULARES
                else if(this.TablaToken.get(i).getTipo().equals("GUION")){                   
                    Regex TempRegex = new Regex();
                    Nodo TempNodo= new Nodo();
                    TempRegex.setID(this.TablaToken.get(i-1).getLexema());
                    //ADELANTA POSICION
                    i+=2;
                    //AGREGA CONCATENACION AL PRINCIPIO
                    TempNodo.setTipo(Nodo.TipoNodo.CONCATENACION);
                    TempNodo.setID2(Nodo.Contador2);
                    TempRegex.addNodo(TempNodo);
                    for( int j = i ;j>0;j++ ){
                        TempNodo = new Nodo();
                        
                        if(this.TablaToken.get(j).getTipo().equals("PUNTO")){
                            TempNodo.setTipo(Nodo.TipoNodo.CONCATENACION);
                            TempNodo.setID2(Nodo.Contador2);
                            TempRegex.addNodo(TempNodo);
                        }                        
                        
                        else if(this.TablaToken.get(j).getTipo().equals("CADENA")){
                            TempNodo.setTipo(Nodo.TipoNodo.TERMINAL);
                            TempNodo.setID(Nodo.Contador);
                            TempNodo.addPrimeros(TempNodo.getID());
                            TempNodo.addUltimos(TempNodo.getID());
                            TempNodo.setTerminal(this.TablaToken.get(j).getLexema());
                            TempNodo.setID2(Nodo.Contador2);
                            TempRegex.addNodo(TempNodo);
                        }
                        
                        else if(this.TablaToken.get(j).getTipo().equals("SIGNO_ASTERISCO")){
                            TempNodo.setTipo(Nodo.TipoNodo.KLEENE);
                            TempNodo.setAnulableTrue();
                            TempNodo.setID2(Nodo.Contador2);
                            TempRegex.addNodo(TempNodo);
                        }
                        
                        else if(this.TablaToken.get(j).getTipo().equals("SIGNO_MAS")){
                            TempNodo.setTipo(Nodo.TipoNodo.POSITIVA);
                            TempNodo.setID2(Nodo.Contador2);
                            TempRegex.addNodo(TempNodo);
                        }
                        
                        else if(this.TablaToken.get(j).getTipo().equals("SIGNO_ALTERNANCIA")){
                            TempNodo.setTipo(Nodo.TipoNodo.ALTERNANCIA);
                            TempNodo.setID2(Nodo.Contador2);
                            TempRegex.addNodo(TempNodo);
                        }
                        
                        else if(this.TablaToken.get(j).getTipo().equals("SIGNO_UNAOCERO")){
                            TempNodo.setTipo(Nodo.TipoNodo.UNAOCERO);
                            TempNodo.setAnulableTrue();
                            TempNodo.setID2(Nodo.Contador2);
                            TempRegex.addNodo(TempNodo);
                        }
                        
                        else if(this.TablaToken.get(j).getTipo().equals("ABRE_LLAVE")){
                            j++;
                            TempNodo.setTipo(Nodo.TipoNodo.TERMINAL);
                            TempNodo.setID(Nodo.Contador);
                            TempNodo.addPrimeros(TempNodo.getID());
                            TempNodo.addUltimos(TempNodo.getID());
                            TempNodo.setTerminal(this.TablaToken.get(j).getLexema());
                            TempNodo.setConjunto(this.TablaToken.get(j).getLexema(),this.Conjuntos);
                            j++;
                            TempNodo.setID2(Nodo.Contador2);
                            TempRegex.addNodo(TempNodo);                         
                        }
                        
                        else if(this.TablaToken.get(j).getTipo().equals("PUNTO_COMA")){
                            this.Expresiones.add(TempRegex);
                            i=j;                           
                            break;
                        }                                      
                    }
                    //AGREGA FIN DE CADENA AL FINAL
                    TempNodo.setTipo(Nodo.TipoNodo.FINCADENA);
                    TempNodo.setID(Nodo.Contador);
                    TempNodo.setID2(Nodo.Contador2);
                    Nodo.Contador=1;
                    Nodo.Contador2=1;
                    TempNodo.addPrimeros(TempNodo.getID());
                    TempNodo.addUltimos(TempNodo.getID());
                    TempRegex.addNodo(TempNodo);
                }
            }
        }
    }
    
    public void viewTokens(){
        for(int i =0 ; i<=this.TablaToken.size()-1;i++){
            this.TablaToken.get(i).printToken();
        }
    }
    
    public void viewExpresiones(){
        for(int i=0 ; i<this.Expresiones.size() ;i ++){
            this.Expresiones.get(i).generarArbol();
            this.Expresiones.get(i).PreOrden(this.Expresiones.get(i).getRaizArbol());
            System.out.println();
            this.Expresiones.get(i).setAnulables();
            this.Expresiones.get(i).setPrimeros();
            this.Expresiones.get(i).setUltimos();
            this.Expresiones.get(i).setSiguientes();
            this.Expresiones.get(i).setEstados();          
            this.Expresiones.get(i).printRegex(); 
            this.Expresiones.get(i).graficarArbol();
            this.Expresiones.get(i).graficarSiguientes();
            this.Expresiones.get(i).graficarTransiciones();
        }       
        
    }
    
    public String testPalabras(){
        boolean validacion;
        String Salida="";
        for (Regex TempRegex : this.Expresiones){
            for(Palabra TempPalabra : this.Palabras){
                
                validacion =TempRegex.TestLexema(TempPalabra.getLexema());
                if(validacion){
                    System.out.println("Palabra "+TempPalabra.getID()+" aceptada por "+TempRegex.getID());
                    Salida=Salida+"Palabra "+TempPalabra.getID()+" aceptada por "+TempRegex.getID()+"\n";
                }
                else{
                    //System.out.println("Palabra "+TempPalabra.getID()+" rechazada por "+TempRegex.getID());
                }
                
            }           
        }
        return Salida;
    }
    
    public void analizeExpresiones(){
        for(int i=0 ; i<this.Expresiones.size() ;i ++){
            
        }
    }
}
