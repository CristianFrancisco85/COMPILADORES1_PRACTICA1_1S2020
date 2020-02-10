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
public class JavaTreeMethod {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner Scan = new Scanner();
        String Prueba = "{\n" +
        "// Conjuntos\n" +
        "CONJ: letra -> a~z;\n" +
        "CONJ: digito -> 0~9;\n" +
        "<! Expresiones\n" +
        "Regulares\n" +
        "!>\n" +
        "ExpReg1 -> . {letra} * | \"_\" | {letra} {digito};\n" +
        "RegEx2 -> . {digito} * | \"_\" | {letra} {digito};\n" +
        "ExpReg3 -> . {digito} . \".\" + {digito};\n" +
        "%%\n" +
        "%%\n" +
        "ExpReg1 : “este_es_un_lexema_valido”\n" +
        "ExpReg3 : “34.44”\n" +
        "}";
        
        Scan.analizeText(Prueba);
        Scan.viewTokens();
    }
    
}
