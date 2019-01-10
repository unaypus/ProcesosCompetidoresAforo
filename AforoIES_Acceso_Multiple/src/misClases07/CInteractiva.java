/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misClases07;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Conjunto de métodos  para interactuar con el usuario.
 * 
 * @author juaponabr
 * <pre>
 * 
 *   Alumno: Juan Pons Abraham
 *   Para:   Curso 1º DAM Tarde/Noche grupo A
 * 
 * </pre>
 */
public class CInteractiva {
    
    private String sNombre;
    private final String S_ERROR_NUMERO_MAX_CARACTERES  = "Número excesivo de caracteres. No puede ser mayor de : ";
    private final String S_ERROR_NUMERO_MIN_CARACTERES  = "Número insuficiente de caracteres. No puede ser memor de : ";
    
    private final String S_CARCTERES                = "caracteres";
    private final String S_ERROR_DIGITOS            = "Los " + S_CARCTERES + " deben ser todos números";
    private final String S_ERROR_NUM_CCC            = "El múmero de cuenta NO es válido";
    private final String S_CAMPO_OBLIGATORIO        = "Este campo es obligatorio debe introducir un dato" ;
    
    private static final String S_PRE_ABORTAR           = "¿Abortar? s/n: " ;
     
    /**
     * <pre> Método CInteractiva
     * 
     * Constructor de la clase
     * se le asigna un nombre al instanciarlo con la instruccion new
     * 
     * De momento el que tenga un nombre no es relevante, pero se usa
     * para compatibilidad de futuras versiones en las que si fuera
     * necesario su uso
     * 
     * @param cNombre 
     * </pre>
     */    
    public CInteractiva( String cNombre){
        
        sNombre = cNombre;
    }
    
    // //////////////////////////////////////////////////
    // Funciones interactivas
    // //////////////////////////////////////////////////
    
    /**
     * <pre> Método intNumero
     * 
     * Función para pedir al usuario que introduzca un número
     * sea este positivo, negativo o con decimales
     * 
     * @param sPedirNumero
     * @return 
     * </pre>
     */
    public double intNumero(String sPedirNumero){
	
             
        Scanner unDnumero = new Scanner (System.in) ;
        
        double x=0;
        
        System.out.println("-------------------------------------------------------------------------");
        System.out.print("Introducir el "+sPedirNumero+": ");
             
        x = unDnumero.nextDouble();
        
        return x;
        
    }
    
    /**
     * <pre> Método intNumero
     * 
     * Función para pedir al usuario que introduzca un número
     * entero
     * 
     * @param sPedirNumero
     * @return 
     * </pre>
     */
    public int intNumeroINT(String sPedirNumero){
	
      
        Scanner unDnumero = new Scanner (System.in) ;
        
        int x=0;
        
        System.out.println("-------------------------------------------------------------------------");
        System.out.print("Introducir el "+sPedirNumero+": ");
        
        x = unDnumero.nextInt();
        
        return x;
        
    }
    
    /**
     * <pre> Método intCadena
     * 
     * Función para pedir al usuario que introduzca una cadena de caracteres
     * 
     * 
     * @param unTitulo
     * @param sPedirCadena
     * @param nLongCadena
     * 
     * @return la cadena introducida
     * </pre>
     */
    public String intCadena(String unTitulo, String sPedirCadena, int nLongCadena){
        
        boolean error;
        
        Scanner unaCadena = new Scanner (System.in) ;
        
        String sCadena = "";
        
        
        do{
            try{
                
                
                System.out.println("-------------------------------------------------------------------------");
                System.out.println(unTitulo);
                System.out.print("Introducir "+sPedirCadena+": ");
                sCadena = unaCadena.nextLine();
                
                comprobarStringVacio(sCadena);
                comprobarStringDemasiadoLarga(sCadena,nLongCadena);
                sCadena = ajustarCadena(sCadena,nLongCadena,"*");
                error=false;
                
            }
            catch(Exception e){
                
                System.out.println(e.getMessage());
                error=true;
                
            }
        } while(error);
       
        return sCadena;
        
    }  
    
    /**
     * <pre>Método intCadenaObligatorio
     * 
     * Función para pedir al usuario que introduzca una cadena de caracteres
     * recordando que es un campo obligatorio, no debe estar vacia la cadena
     * 
     * @param unTitulo
     * @param sPedirCadena
     * 
     * @return la cadena introducida
     * </pre>
     */
    public String intCadenaObligatorio(String unTitulo, String sPedirCadena){
        
        boolean error;
        
        Scanner unaCadena = new Scanner (System.in) ;
        
        String sCadena = "";
        
        
        do{
            try{
                
                
                System.out.println("-------------------------------------------------------------------------");
                System.out.println(unTitulo);
                System.out.print("Introducir "+sPedirCadena+": ");
                sCadena = unaCadena.nextLine();
                
                comprobarStringVacio(sCadena);
                //sCadena = comprobarAbortar(sCadena);
                
                error=false;
                
            }
            catch(Exception e){
                
                System.out.println(e.getMessage());
                error=true;
                
            }
        } while(error);
       
        return sCadena;
        
    } 
    
    /**
     * <pre> Método ajustarCadena
     * 
     * comprobamos que sCadena tiene la longitud deseada en nLongCadena
     * si excede recortamos , si no llega añadimos espacios
     * y si el primer caracter coincide con carAbort devolvemos dicho
     * caracter
     * 
     * @param sCadena
     * @param nLongCadena
     * @param carAbort
     * @return 
     * </pre>
     */
    public String ajustarCadena(String sCadena, int nLongCadena, String carAbort){
        
        
        String sSalida = sCadena;
        int nLCad = sCadena.length();
        
        if(carAbort.equals( sCadena.substring(0, 1))){
            sSalida = carAbort ;
        }else{
            if(nLCad>nLongCadena){
                sSalida = sCadena.substring(0, nLongCadena);
            }else{
                for(int nContador = nLCad; nLCad < nLongCadena; nLCad++){
                    sSalida = sSalida + " ";
                }
            }
        }
        
        return sSalida ;
        
    }
    
    
    /**
     * <pre> Método ajustarCadena
     * 
     * comprobamos que sCadena tiene la longitud deseada en nLongCadena
     * si excede recortamos , si no llega añadimos espacios
     * 
     * @param sCadena
     * @param nLongCadena
     * @param bDetras
     * @return 
     * </pre>
     */
    public String ajustarCadenaNUM(String sCadena, int nLongCadena, boolean bDetras){
        
        /**
         * ajustarCadena
         * 
         * comprobamos que sCadena tiene la longitud deseada en nLongCadena
         * si excede recortamos , si no llega añadimos espacios
         * 
         */
        
        String sSalida = sCadena;
        String  sDetras ;
        String sDelante;
        int nLCad = sCadena.length();
        
        if(nLCad>nLongCadena){
            sSalida = sCadena.substring(0, nLongCadena);
        }else{
            if(bDetras){
                sDetras= " " ;
                sDelante = "" ;
            }else{
                sDetras= "" ;
                sDelante = " " ;
            }
            for(int nContador = nLCad; nLCad < nLongCadena; nLCad++){
                sSalida = sDelante + sSalida + sDetras;
            }
        }
       
        
        return sSalida ;
        
    }
    
    /**
     * <pre> Método repPrograma
     * 
     * Esta función le pide al usuario si quiere realizar una tarea
     * ademas se puede usar como espera hasta que se pulse INTRO
     * 
     * @param sPregunta
     * @return 
     * </pre>
     */
    public String repPrograma(String sPregunta ){
              
        Scanner unTexto = new Scanner (System.in) ;
        String sotravez ;
                
        System.out.println("-------------------------------------------------------------------------");
	System.out.println(" ");
	System.out.print(  sPregunta );
        
        // Esperando respuesta introducida por teclado	
        sotravez = unTexto.nextLine();
      
        // forzamos la respuesta a minúsculas
        // en caso de pulsar N tendremos n
        sotravez = sotravez.toLowerCase();
        
        // comprobación de la opción elegida  
        if (!sotravez.equals("n")) {            
            // convertimos en s cualquier respuesta diferente a n
            sotravez = "s";
	}
        
        // nos aseguramos que solo devolvemos una letra s/n
        return sotravez.substring(0, 1);        
        
    }
    
    /**
     * <pre> Método comprobarStringVacio
     * 
     * si un campo es obligatorio
     * comprobamos que la cadena no está vacia
     * y en caso de estarlo avisamos al usuario
     * 
     * @param cadena Texto a investigar
     * @throws Exception el aviso
     * </pre>
     */
    public void comprobarStringVacio(String cadena)throws Exception{
        
        if(cadena.equals("")){
            throw new Exception("\n" + S_CAMPO_OBLIGATORIO);
        }
    }
   
    /**
     * <pre> Método comprobarStringDemasiadoLarga
     * 
     * comprobamos que la cadena no exceda cierta 
     * longitud y en caso de exceder avisamos al usuario
     * 
     * @param cadena Texto a investigar
     * @param maximo máxima longitud de cadena permitido
     * @throws Exception el aviso
     * </pre>
     */
    public void comprobarStringDemasiadoLarga(String cadena, int maximo)throws Exception{
        
        if(cadena.length()>maximo){
            throw new Exception("\n" + S_ERROR_NUMERO_MAX_CARACTERES + maximo + " "+ S_CARCTERES);
        }
    }
    
    /**
     * <pre> Método comprobarStringDemasiadoCorta
     * 
     * comprobamos que la cadena tenga una longitud 
     * mínima y en caso de no alcanzarla avisamos al usuario
     * 
     * @param cadena Texto a investigar
     * @param minimo máxima longitud de cadena permitido
     * @throws Exception el aviso
     * </pre>
     */
    public void comprobarStringDemasiadoCorta(String cadena, int minimo)throws Exception{
        
        if(cadena.length()<minimo){
            throw new Exception("\n" + S_ERROR_NUMERO_MIN_CARACTERES + minimo + " "+ S_CARCTERES);
        }
    }
    
    /**
     * <pre> Método comprobarStringSinEspacios
     * 
     * comprobamos que la cadena no contenga espacios
     * en blanco y en caso de tenerlos avisamos al usuario
     * 
     * @param cadena Texto a investigar
     * @throws Exception el aviso
     * </pre>
     */
    public void comprobarStringSinEspacios(String cadena)throws Exception{
        
        // recorremos la cadena en busca de espacios en blanco
        // al primero que se halla lanzamos la excepción
        
        for(int nContador=0;nContador<cadena.length();nContador++){
            if(cadena.substring(nContador, nContador+1).equals(" ")){
                throw new Exception("\n" + "La cadena contiene espacios en blanco");
            }
        }
        
    }
    
    /**
     * <pre>Método comprobarFormato
     * 
     * comprobamos que la cadena se ajuste al
     * formato definido en la expresión regular
     * recibida, de no ser así provocamos la 
     * excepción
     * 
     * @param cadena Texto a investigar
     * @param formato expresión regular a encontrar
     * @throws Exception el aviso para el método que lo ha llamado
     * </pre>
     */
    public void comprobarFormato(String cadena, String formato)throws Exception{
        
        Pattern p=Pattern.compile(formato);
        Matcher m=p.matcher(cadena);
        
        if (!m.matches()){
            throw new Exception("\n Formato incorrecto");
        }
        
       
    }
 
    // //////////////////////////////////////////////////
    // Fin Funciones interactivas
    // //////////////////////////////////////////////////
    
}
