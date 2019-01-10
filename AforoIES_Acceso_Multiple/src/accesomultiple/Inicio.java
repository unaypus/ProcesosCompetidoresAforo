/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesomultiple;

import acceso.Acceso;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import misClases07.CEcho;
import misClases07.CInteractiva;
import registro.CtrlRegistro;

//*****************************************************************************
//*****************************************************************************
//
//                      ATENCIÓN nota de revisión
//
//*****************************************************************************
//*****************************************************************************
//
//  paquete misClases07
//
//  Los siguientes archivos necesitan ser igualados tanto en AforoIES_Proceso_INI
//  como en AforoIES_Acceso_Multiple, ninguno tiene prioridad sobre el otro
//
//      - Constantes.java
//      - ArchBuffer.java
//
//  Este solo está en ...INI y tiene que convertirse en ArchBufer cogiendo de el 
//  los métodos necesarios
//
//      - ArchDirectos.java
//
//  Comprobar el resto del paquete
//
//*****************************************************************************
//*****************************************************************************



/**
 *<pre>
 *
 * 
 * Alumno:     Juan Pons Abraham
 * Curso:      Curso 3º DAM Semipresencial
 * Asignatura: PGV
 * Profesor:   Tiburcio
 * 
 * Descripción:
 * 
 * Aplicación que simula una serie de accesos al recinto de un instituto.
 * En dichos accesos existen detectores que controlan la entrada y salida 
 * de pèrsonas, de manera que se sabe el aforo en un momento puntual.
 * 
 * 
 * Estructura 
 * 
 * El código esta dividido en 4 archivos que pertenecen a 3 paquetes
 * 
 * paquete controlacceso
 * 
 *         archvivo Inicio.java
 * 
 *                - Inicia la aplicación cargando los objetos y variables
 *                - Muestra una pantalla de inicio con información de la app
 *                - Comprueba si se han introducido parámetros y si son correctos
 *                - Se queda a la espera de que el usuario pulse una tecla para
 *                  empezar, o cancelar si pulsa n.
 *                - Si se elige comenzar lanza los hilos según los valores por defecto
 *                  o los que el usuario introdujo por consola.
 * 
 * paquete registro
 * 
 *         archivo CtrlRegistro.java
 * 
 *               - Recibe al ser creado la variable maxPersonas que contiene la 
 *                 cantidad máxima de personas que pueden entrar al recinto
 *               - Abre el archivo del registro y rellena la cabecera del informe
 *               - Los métodos sincronizados creceAforo(acceso) y menguaAforo(acceso) 
 *                 son llamados por los objetos Detector cuando se produce una entrada o salida
 *               - El método actualizaAforo(acceso), es llamado por los anteriores, recibiendo
 *                 de ellos el acceso que ha realizado la detección y monta la linea
 *                 que será insertada en el registro, atraves del método actualizarArchivo()
 * 
 * paquete acceso
 * 
 *         archivo Acceso.java
 * 
 *               - Es una clase extends Thread de la que Inicio crea tantos como puertas
 *                 o puntos de acceso tenga el instituto simulado
 *               - Los objetos de esta clase crean los objetos Detector. Según se indique
 *                 crearan 2 hilos uno detectaEntrada y otro detectaSalida, o si así se
 *                 ha especificado uno solo de ellos
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
 *                  iguales a nPersonas
 *                - Como las variables pertenecen a CtrlRegistro se consultan por medio
 *                  de los métodos sincronizados, getEntradas() y getSalidas()
 * 
 * Existen también otros archivos que no han sido creados expresamente para la aplicación.
 * Forman parte de un paquete creado en 1º con métodos para el control de la pantalla y el
 * teclado en modo consola, y también, para el acceso a ficheros de disco.
 * 
 * paquete misClases06
 * 
 *         archivo CEcho.java
 * 
 *               -  Métodos para maquetar la salida por pantalla
 * 
 *         archivo CInteractiva.java
 * 
 *               - Conjunto de métodos  para interactuar con el usuario.
 * 
 *         archivo ArchSecuenciales.java
 * 
 *               - Métodos que realizan acciones sobre ficheros de texto 
 * 
 * 
 * 
 * 
 * ---------------------------------------------------------------------------------------
 *             ACTIVIDAD
 * ---------------------------------------------------------------------------------------
 * 
 * Semana 1 - U.T.1 - AE2: Procesos Competidores - El Edificio del IES El Rincón
 * 
 * Enunciado:
 * 
 * En el IES El Rincón existen múltiples entradas/salidas con detectores de entrada y salida. 
 * 
 * Se quiere saber en todo momento cuantas personas existen en el edificio.
 * Se sugiere que crees un fichero numPersonas.txt que contendrá el número de personas que 
 * hay en todo momento en el edificio.
 * Debes crear tantos procesos como detectores hay en el edificio de forma que habrá:
 * Procesos para los detectores de Entrada. Simplemente aumentará en uno cada vez que entre 
 * una persona. Para simularlo crea un bucle para que entren 10 personas. 
 * Procesos para los detectores de Salida. Simplemente aumentará en uno cada vez que salga una 
 * persona. Para simularlo crea un bucle para que salgan 10 personas. 
 * Proceso Lanzador. Que lanzará todos los procesos anteriores correspondientes a los detectores. 
 * 
 * Criterios de Calificación:
 * 
 * Debes subir sí o sí el/los proyectos creados para el ejercicio. 
 * 60% Si funciona el ejercicio tal y cómo debería. Debes lanzar 10 procesos de entrada y 10
 * procesos de salida. Cada proceso de Entrada ó Salida detectará la entrada ó salida de 10 personas 
 * respectivamente. De esta manera al final el fichero numPersonas.txt contendrá un 0. 
 * 30% Si haces el Programa Lanzador de forma que se le pueda pasar como parámetro el número de 
 * detectores de entrada y el número de detectores de salida. 
 * 10% Si se sube además el/los JavaDoc correspondientes. 
 * 
 * </pre>
 * @author juaponabr
 * 
 */
public class Inicio {
    
    /**
     * <pre>
     * 
     * Declaración de Objetos de caracter general para toda la aplicación
     * 
     * Paquete misClases06
     * 
     * CInteractiva     -   interactua con el usuario mostrandole texto y 
     *                      esperando una respuesta del usuario 
     * 
     * CEcho            -   muestra resultados e informes por pantalla sin
     *                      necesariamente esperar contestación por parte 
     *                      del usuario
     *  
     * </pre>
     */
    static CEcho        mPantalla       = new CEcho(        "laPantalla"    ) ;
    static CInteractiva pInteracciones  = new CInteractiva( "lasPeticiones" ) ;   
    
        
    /**
     * objeto con los métodos sincronizados
     */
    private static CtrlRegistro controlador ;
    /**
     * objeto hilo que simula un acceso al centro
     */
    private static Acceso       accesos[]   ;  
    
    /**
     * 
     * <pre>Método main
     * 
     * procedimento que arranca el programa de gestión
     * mostrando un menú y realizando sus acciones a 
     * perición del usuario.
     * 
     * 
     * @param args the command line arguments
     * </pre>
     */
    public static void main( String[] args ) {
        
        String  sAcceso     = ""    ;
        //
        int     nAcceso     = Integer.parseInt( args[ 0 ] ) ;
        int     tAcceso     = Integer.parseInt( args[ 1 ] ) ;
        int     nPersonas   = Integer.parseInt( args[ 2 ] ) ;
        
        
        
        try{
            //Rediregimos salida y error estándar a un fichero
            PrintStream ps = new PrintStream(
                             new BufferedOutputStream(new FileOutputStream(
                             new File("javalog.txt"),true)), true);
            System.setOut(ps);
            System.setErr(ps);
        }catch(Exception e){
            System.err.println("P"+nAcceso+" No he podido redirigir salidas.");
        }
        
        controlador = new CtrlRegistro( nPersonas   ) ;        
        accesos     = new Acceso[       nAcceso     ] ;
        
        if( nAcceso < 10 ){ sAcceso = " " ; }
            
        sAcceso = sAcceso + nAcceso ;
        System.out.print("\nPreparando  Acceso " + sAcceso + "\n" );
        
        //nuevoProceso = Runtime.getRuntime().exec( sLineaConsola ) ;
        accesos[ 0 ]= new Acceso( "Acceso " + sAcceso , controlador , tAcceso , nPersonas ) ;
        
        System.out.print("\nCreado      Acceso " + sAcceso + "\n" );        
        System.out.print("\nIniciado    Acceso " + sAcceso + "\n" );
        
    }     
    
}
