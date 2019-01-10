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
 * 
 *          archivo Detector.java
 * 
 *                - Otra clase de tipo extends Thread
 *                - El método run se sobre escribe con un if que discrimina en función
 *                  de si será un detectaEntrada o un detectaSalida
 *                - Se llamará al método lanzaEntradas() o al método lanzaSalidas()
 *                - Estos entran en un bucle que hace llamadas a creceAforo( acceso )
 *                  o a menguaAforo( acceso ) simulando entradas y salidas
 *                - Se sale de estos bucles cuando las variables entradas y salidas son
 *                  iguales a maxPersonas
 *                - Como las variables pertenecen a CtrlRegistro se consultan por medio
 *                  de los métodos sincronizados, getEntradas() y getSalidas()
 * 
 * 
 * @author juaponabr
 * 
 * </pre>
 */
public class Detector extends Thread{
    
    private CtrlRegistro    controlador ; 
    
    private String          nombre      ;       
    private boolean         e_s         ;
    private int             nPersonas   ;
    
    private final String S_ENTRADA  = "Entrada <<== <:   " ;
    private final String S_SALIDA   = "Salida  ==>> >:   " ;
    
    /**
     * <pre>
     * 
     * @param nombre      cadena con el nombre identificador del Acceso
     * @param controlador objeto CtrlRegistro para acceder a los métodos sincronizados
     * @param nPersonas   entero número máximo de personas que entra
     * @param e_s         logico verdadero es una entrada, falso es una salida
     * 
     * </pre> 
     */
    public Detector( String nombre, CtrlRegistro controlador, int nPersonas, boolean e_s ) {
        
        this.controlador    = controlador   ;
        this.nombre         = nombre        ;
        this.nPersonas      = nPersonas     ;
        this.e_s            = e_s           ;
        
    }

    /**
     * <pre>
     * 
     * El método run se sobre escribe con un if que discrimina en función
     * de si será un detectaEntrada o un detectaSalida
     * Se llamará al método lanzaEntradas() o al método lanzaSalidas()
     * 
     * </pre>
     */
    @Override
    public void run() {
        
        if( e_s ){
            
            lanzaEntradas();            
        
        }else{
            
            lanzaSalidas();
            
        }
        
    } 

    /**
     * <pre>
     * 
     * entra en un bucle que hace llamadas a creceAforo( acceso )
     * simulando entradas
     * Se sale cuando la variable entradas es igual a nPersonas
     * 
     * </pre> 
     */
    private void lanzaEntradas(){
        
        controlador.iniciaAcceso( S_ENTRADA + nombre ) ;
        
        while ( true ){
            
            if( controlador.getEntradas() < nPersonas ){
                
                controlador.actualizaBuffer( true, S_ENTRADA + nombre ) ;
                yield() ;
            
            } else {
                
                break;
                
            }
            
        }
        
        controlador.cierraAcceso( S_ENTRADA + nombre ) ;
        
    }
    
    /**
     * <pre>
     *
     * entra en un bucle que hace llamadas a menguaAforo( acceso )
     * simulando entradas
     * Se sale cuando la variable salidas es igual a nPersonas
     * 
     * </pre>
     */
    private void lanzaSalidas(){
        
        controlador.iniciaAcceso( S_SALIDA + nombre ) ;
        
        while (  true ){            
            
            if( controlador.getSalidas() <  nPersonas ){
                
                controlador.actualizaBuffer( false, S_SALIDA + nombre ) ;
                yield() ;
                
            } else {
                
                break;
                
            }
            
        }
        
        controlador.cierraAcceso( S_SALIDA + nombre ) ;
        
    }    
    
}
