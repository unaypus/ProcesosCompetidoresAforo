/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misClases07;

/**
 * clase codigosPruebas
 * 
 * zona de descarga de códigos de ejemplo desde
 * internet, enlaces del curso etc...
 * también se realizarán pruebas para métodosque se 
 * llevarán a otras clases
 * @author juaponabr
 */
public class codigosPruebas {
    
    
    
    
    
    /**
     * <pre>
     * ejmplo_de_String_a_Int
     * 
     * por si alguna vez me olvido de como
     * convertir un dato String a int
     * </pre>
     */
    public void ejmplo_de_String_a_Int(){
        
       
        int unnumero;
        String untextonumerico;
        untextonumerico = "123";
        unnumero = Integer.parseInt(untextonumerico);
        System.out.println(unnumero);
        System.out.println(untextonumerico);          
                
    }
    
    
    
    /**
     * <pre>
     * Descargada de internet para
     * hacer pruebas con colores y fuentes
     * </pre>
     */
    
    public void verASCII(){
        char letra=32 ; // num antiguo 124;
        String  sBarraSep = "\u001B[0;44;30m"+letra+"\u001B[0m";
        String sLista =    "\u001B[0;44;37m"   +
                                " " + "Un teto" + "Otro texto"+"   " +
                                 "Y otro mas" +"\u001B[0m"+"\n";
        System.out.println("\u001B[36mHola Mundo\u001B[0m");
        for(int i=0; i<=255 ; i++ ){
            System.out.println(
                    "El codigo ASCII de la letra " + 
                    letra +
                    " es " +
                    "\u001B[0;44;32m"+ i+"\u001B[0m");
            letra++;
        } 
        System.out.println("\u001b[2J");
    }
}
