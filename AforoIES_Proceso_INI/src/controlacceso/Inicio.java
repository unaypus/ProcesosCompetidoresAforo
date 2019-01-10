/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlacceso;

import static java.lang.Thread.sleep;
import misClases07.ArchDirectos;
import misClases07.ArchSecuenciales;
import misClases07.CEcho;
import misClases07.CInteractiva;
import misClases07.Constantes;

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
     * Constantes 
     */
    static final int MAX_N_PERSONAS = 999 ;
    static final int MAX_N_ACCESOS  =  99 ;
    /**
     * Declaración de las Constantes literales que apareceran en pantalla
     */
    static final String S_CONTINUAR = "Pulse INTRO para continuar"          ;
    static final String S_PRE_SALIR = "¿Quiere iniciar el programa? s/n: "  ;

    static String sdescripcion[] = {    "Programa Control Aforo v 1.0.0\n",
                                        "               SIMULACIÓN                  \n",
                                        "   Archivo de control de las entradas y salidas",
                                        "   que se realizan en el I.E.S. El Rincón\n",
                                        "   Puede introducir hasta 4 parámetros por linea de comandos.",
                                        "   Estos afectarán al funcionamiento del programa según el",
                                        "   siguiente esquema.\n",
                                        "          1 parámetro  : número de accesos E/S",
                                        "          2 parámetros : lo anterior mas número de personas que acceden",
                                        "          3 parámetros : lo anterior mas número de accesos solo entrada",
                                        "          4 parámetros : lo anterior mas número de accesos solo salida\n",
                                        "   Si no introduce ningún parámetro el programa efectua",
                                        "   la simulación con 10 E/S y 100 personas\n",
                                        ""};
    
    /**
     * <pre>
     * Declaración Variables de uso general
     * 
     * queInst
     * 
     * es un entero que indica la opción del case
     * que se encuentran en un método de la calse 
     * CEcho el cual controla que Instrucciones 
     * mostrar al usuario
     * </pre>
     */
    static int queInst ;
    /**
     * <pre>otravez
     * 
     * Controla el bucle mientras queramos estar dentro
     * de la aplicación
     * </pre>
     */
    static String otravez ;
    static String elresultado[];
    
    private static Process nuevoProceso ;

    //*****************************************
    //
    //  resolver situaciones de java en 
    //  variable de entorno
    //
    //*****************************************
    //"/usr/lib/java/jdk1.8.0_144/bin/java -jar"
    private static final String JAVA_JAR    = "java -jar" ;
    private static final String ARCHIVO_JAR = "AforoIES_Acceso_Multiple.jar" ;
    
    /**
     * Cantidades por defecto de los diversos tipos de acceso
     */
    private static int nSoloEntrada    =   0 ;
    private static int nSoloSalida     =   0 ;
    private static int nEntradaSalida  =  10 ;
    
    private static ArchSecuenciales registroAforo = new ArchSecuenciales(   Constantes.S_ARCHIVO_NF    ,
                                                                            Constantes.S_ARCHIVO_F     ,
                                                                            Constantes.I_N_CAMPOS      ,
                                                                            Constantes.NOM_CAMPOS       ,
                                                                            Constantes.LONG_CAMPOS       ) ;
    
    private static ArchSecuenciales logJava = new ArchSecuenciales( Constantes.S_ARCH_NF_LOG    ,
                                                                    Constantes.S_ARCH_LOG    ,
                                                                    Constantes.I_N_CAMPOS_LOG      ,
                                                                    Constantes.NOM_CAMPOS_LOG       ,
                                                                    Constantes.LONG_CAMPOS_LOG       ) ;
    
    private static ArchDirectos archBuffer = new ArchDirectos(  Constantes.S_FICHERO_NF     ,
                                                            Constantes.S_FICHERO_F      ,
                                                            Constantes.I_F_N_CAMPOS     ,
                                                            Constantes.LOS_CAMPOS       ,
                                                            Constantes.TAM_REGISTRO     ,
                                                            Constantes.N_MAX_REGISTROS  ) ;
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
        
        otravez = "n"   ;
        queInst = 6     ;
              
	// Inicio del programa 
        // mostrar créditos e instrucciones
        mPantalla.mBienvenida( sdescripcion , queInst ) ;   
        
        int nPersonas       = 100           ;        
        int nAcceso         =   0           ;
        int nArgs           = args.length   ;
        
        try{
            
            switch ( nArgs ){
            
                case 4 :
                    
                    nSoloSalida = Integer.parseInt( args[ 3 ] ) ;
                    if( nSoloSalida < 1 ||
                        nSoloSalida > ( MAX_N_ACCESOS - 2 - nSoloEntrada - nEntradaSalida ) ){ salidaError() ; }
                                
                case 3 :
                                    
                    nSoloEntrada = Integer.parseInt( args[ 2 ] ) ;
                    if( nSoloEntrada < 1 ||
                        nSoloEntrada > ( MAX_N_ACCESOS - 1 - nEntradaSalida - nSoloSalida ) ){ salidaError() ; }
                                
                case 2 :
                    
                    nPersonas = Integer.parseInt( args[ 1 ] ) ;
                    if( nPersonas < 1 || nPersonas > MAX_N_PERSONAS ){ salidaError() ; }
                
                case 1 :
                    
                    nEntradaSalida = Integer.parseInt( args[ 0 ] ) ;
                    if( nEntradaSalida < 1 ||
                        nEntradaSalida > ( MAX_N_ACCESOS - nSoloSalida - nSoloEntrada ) ){ salidaError() ; }
                                
                case 0 :
                
                    nAcceso =  nEntradaSalida + nSoloEntrada + nSoloSalida ;
                    break;
                            
                default:
                
                    salidaError();
                    break;
                
            }   
        }catch( Exception e ){
                    
            salidaError();
                    
        }
        
        // espera la pulsación del usuario
        otravez = pInteracciones.repPrograma( S_PRE_SALIR ) ; 
        
        if(!"s".equals(otravez.substring(0, 1))){ 
            
            salida() ; 
        
        }else{  
            
            int estado = 0 ;
            int previsto = nPersonas * 2 ;
            int nTotalAccesos = nSoloSalida + nSoloEntrada + ( 2 * ( nAcceso - ( nSoloSalida + nSoloEntrada ) ) ) ;
            
            iniciarArchivos( nTotalAccesos ) ;
            
            cargarAcceso( 0                             , nSoloSalida                   , nPersonas , 2 ) ;     
            cargarAcceso( nSoloSalida                   , nSoloSalida + nSoloEntrada    , nPersonas , 1 ) ;            
            cargarAcceso( nSoloSalida + nSoloEntrada    , nAcceso                       , nPersonas , 0 ) ;
            
            /**/
            
            
            System.out.println("\n***********************************************************\n" +
                                "Iniciando Control Aforo ************************************\n" + 
                                "espere unos instantes...\n" + 
                                "Abriendo  " + comprobarEstado( 16 ) + " Accesos E/S\n");
            
            while ( estado < ( previsto ) ){
                
                try {
                    
                    sleep( 50 ) ;
                    estado = comprobarEstado( 12 );
                    
                    if( estado > 0 ){
                        
                        System.out.print("\nRealizadas : " + estado + " acciones de : " + previsto + " Accesos e/s abiertos : " + comprobarEstado( 16 ));
                        
                    } else {
                        System.out.print("*"); 
                        
                    }
                    
                } catch (InterruptedException ex) { salidaError() ; }
                
            }/**/
            
            System.out.println("\n***********************************************************\n" +
                                "Cerrando Accesos E/S\n" );
            
            while ( estado > 0 ){
                
                try {
                    
                    sleep( 10 ) ;                
                    estado = comprobarEstado( 16 );
                    System.out.println("Accesos E/S abiertos : " + estado);
                    
                } catch (InterruptedException ex) { salidaError() ; }
                
            }
            
            salida();
                
        }      
        
    }
    
    private static void iniciarArchivos( int nTotalAccesos){
        
        // iniciar buffer Aforo
        System.out.println( "\n*************************************************" + 
                            "\nPreparando buffer   : " + Constantes.S_FICHERO_F);
        preparaBuffer( nTotalAccesos ) ;
        
        // iniciar archivo aforo
        System.out.println( "\n*************************************************" + 
                            "\nPreparando registro : " + Constantes.S_ARCHIVO_F);
        preparaRegAforo();
        
        // iniciar archivo log
        System.out.println( "\n*************************************************" + 
                            "\nPreparando log      : " + Constantes.S_ARCH_LOG);
        preparaRegLog();
        
    }
    private static void preparaBuffer( int nTotalAccesos ){
        
        elresultado = new String[1];
        
        // Comprobar si existen los archivos necesarios
        if( !archBuffer.crearArchivo() ){
               
            if( archBuffer.comprobarArchivo() != 0 ){
                
                mPantalla.mError( 1 ) ;
                elresultado[ 0 ] = archBuffer.estado() ;
                mPantalla.mResultado( elresultado ) ;
                
            } 
                
        }
        
        iniciaCampAccesos( nTotalAccesos ) ;
        
    }
    /**
     * <pre>
     * 
     * abre el archivo de disco necesario o lo crea si no existe
     * con los métodos de la clase ArchSecuenciales del que se ha
     * creado el objeto elFichero
     * 
     * </pre> 
     */
    private static void preparaRegAforo(){
        
        //sRegistro = new String [ 1 ] ;
        
        // Comprobar si existen los archivos necesarios
        if( !registroAforo.crearArchivo() ){
                
            if( registroAforo.comprobarArchivo() == 1 ){
                mPantalla.mError( 1 ) ;
                System.out.println( registroAforo.estado() ) ;  
            }
                
        }
        
        
    }
    private static void preparaRegLog(){
        
        //sRegistro = new String [ 1 ] ;
        
        // Comprobar si existen los archivos necesarios
        if( !logJava.crearArchivo() ){
                
            if( logJava.comprobarArchivo() == 1 ){
                mPantalla.mError( 1 ) ;
                System.out.println( logJava.estado() ) ;  
            }
                
        }
        
        
    }
        
    private static void iniciaCampAccesos( int nTotalAccesos ){
        
        if( archBuffer.bloqueado() ){
            archBuffer.escribirCampo( 16, nTotalAccesos ) ;
        }else{
            System.out.println("\nNo se ha guardado el nTotalAccesos\n");
        }
        archBuffer.desbloqueado() ;
        
    }
    private static int comprobarEstado( int queCampo){
        
        int laVar;
        
        archBuffer.bloqueado();
        laVar = archBuffer.leerCampo( queCampo ) ;
        archBuffer.desbloqueado() ;
        
        return laVar ;
    }
    /**
     * <pre>
     * 
     * Lanza los procesos Acceso
     *                
     * @param iniBucle  entero para iniciar el bucle y servirá para el id del 
     *                  Acceso con el que se construye el nombre
     * @param finBucle  entero para finalizar el bucle
     * @param nPersonas entero maximo número de personas que entrara
     * @param tAcceso   entero tipo de acceso: entrada, salida o bidireccional
     * 
     * </pre>
     */ 
    private static void cargarAcceso( int iniBucle, int finBucle, int nPersonas, int tAcceso){  
        
        int     nAcceso         = 0     ;
        String  sAcceso         = ""    ;
        String  sArgumentos     = ""    ;
        String  sLineaConsola   = ""    ;
        
        //Creamos un grupo de procesos que accederán al mismo fichero
        try{
            
        
            for( int n = iniBucle ; n < finBucle ; n++ ){ 
                
                nAcceso         =   n           + 1         ;            
                sAcceso         =   ""          + nAcceso   ;
                sArgumentos     =   sAcceso     + " "       +
                                    tAcceso     + " "       +
                                    nPersonas               ;
                sLineaConsola   =   JAVA_JAR    + " "       +
                                    ARCHIVO_JAR + " "       +
                                    sArgumentos             ;
                
                nuevoProceso = Runtime.getRuntime().exec( sLineaConsola ) ;
            
                //Creamos el nuevo proceso y le indicamos el número de orden
                System.out.println( "\n*********************************************\n" +
                                    "Creado el proceso   : " + nAcceso       + "\n"  +
                                    "Con los argumentos  : " + sArgumentos   + "\n"  +
                                    "Linea consola       : " + sLineaConsola         ) ;
                
                /*+
                                    "Ruta java          : " + JAVA_JAR      + "\n"  +
                                    "Archivo Multiple   : " + ARCHIVO_JAR   + "\n"  +
                                    "Linea consola      : " + sLineaConsola         ) ;*/
                
                //Mostramos en consola que hemos creado otro proceso  
                
            }
            
        ////////////////////////////////////////////////////////////////////////
        //Resolver estas salidas de tono
        }catch (SecurityException ex){
            System.err.println("Ha ocurrido un error de Seguridad."+
                    "No se ha podido crear el proceso por falta de permisos.");
        }catch (Exception ex){
            System.err.println("Ha ocurrido un error, descripción: "+
                    ex.toString());
        }
        ////////////////////////////////////////////////////////////////////////
        
    }
        
    /**
     * <pre>
     * 
     * Si se pasan parámetros por consola de forma o 
     * cantidad erronea se informa al usuario
     * 
     * </pre>
     */
    private static void salidaError(){
        
        System.out.println( "\nCompruebe que el número total de Accesos sea un :\n\n"     +
                            "NÚMERO ENTERO\n\nMayor de 0 y Menor de " + ( MAX_N_ACCESOS + 1 ) + "\n\n"         +
                            "\nCompruebe que el número de Personas sea un :\n\n"    +
                            "NÚMERO ENTERO\n\nMayor de 0 y Menor de " + ( MAX_N_PERSONAS + 1 ) + "\n\n"         ) ;
        System.exit(0);
        
    }
    
    /**
     * Se genera cuando el usuario decide no ejecutar la aplicación
     */
    private static void salida(){
        // Finalización del programa
        // Mostrar despedida y cierre        
        mPantalla.mDespedida(sdescripcion);
    }
    
}
