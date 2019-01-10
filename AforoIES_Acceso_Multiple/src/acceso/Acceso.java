/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import registro.CtrlRegistro;

/**
 * <pre>
 *
 *         archivo Acceso.java
 * 
 *               - Es una clase extends Thread de la que Inicio crea tantos como puertas
 *                 o puntos de acceso tenga el instituto simulado
 *               - Los objetos de esta clase crean los objetos Detector. Según se indique
 *                 crearan 2 hilos uno detectaEntrada y otro detectaSalida, o si así se
 *                 ha especificado uno solo de ellos
 *  
 * 
 * @author juaponabr
 *
 * </pre>
 */ 
public class Acceso{

    private Detector        detectaEntrada  ;
    private Detector        detectaSalida   ;
    private CtrlRegistro    controlador     ;
    
    private String          nombre          ;    
    private int             tAcceso         ;
    private int             nPersonas       ;
    
    private final int COMPLETO  = 0 ;
    private final int ENTRADA   = 1 ;
    private final int SALIDA    = 2 ;
    
    private final String S_COMPLETO  = " Doble - E/S     . " ;
    private final String S_ENTRADA   = " Solo  - Entrada . " ;
    private final String S_SALIDA    = " Solo  - Salida  . " ;
    
    /**
     * <pre>
     * 
     * @param nombre      cadena con el nombre identificador del Acceso
     * @param controlador objeto CtrlRegistro para acceder a los métodos sincronizados
     * @param tAcceso     entero tipo de Acceso segun las constantes: S_COMPLETO,
     *                    S_ENTRADA y S_SALIDA
     * @param nPersonas   entero número máximo de personas que entra
     * 
     * </pre>
     */
    public Acceso( String nombre, CtrlRegistro controlador, int tAcceso , int nPersonas) {
        
        this.controlador    = controlador   ;
        this.nombre         = nombre        ;
        this.tAcceso        = tAcceso       ;
        this.nPersonas      = nPersonas     ;
        iniciate();
        
    }
    
    /**
     * <pre>
     * 
     * El método run se sobre escribe con un switch
     * Según indique tAcceso, se crearan 2 hilos uno detectaEntrada y otro detectaSalida, 
     * o si así se ha especificado uno solo de ellos
     * 
     * </pre> 
     */
    
    public void iniciate() {
        
        switch ( this.tAcceso ){
            
            case COMPLETO   :
                
                this.detectaEntrada = new Detector( nombre + S_COMPLETO, controlador, nPersonas, true   ) ;
                this.detectaSalida  = new Detector( nombre + S_COMPLETO, controlador, nPersonas, false  ) ;
                
                this.detectaEntrada.start() ;
                this.detectaSalida.start()  ;                
                
                break ;
                
            case ENTRADA    :
                
                this.detectaEntrada = new Detector( nombre + S_ENTRADA, controlador, nPersonas, true ) ;                
                this.detectaEntrada.start() ;
                
                break ;
                
            case SALIDA     :
                
                this.detectaSalida  = new Detector( nombre + S_SALIDA, controlador, nPersonas, false ) ;                
                this.detectaSalida.start()  ;
                
                break ;
                
        }
        
    }
    
}
