/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registro;

import misClases07.CEcho;
import misClases07.ArchSecuenciales;
import java.util.Date;
import misClases07.ArchBuffer;
import misClases07.Constantes;

/**
 * <pre>
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
 * @author juaponabr
 * </pre>
 */
public class CtrlRegistro {
    
    /**
     * <pre>
     * 
     * Declaración de Objetos de caracter general para toda la aplicación
     * 
     * Paquete misClases06
     * 
     * CEcho            -   muestra resultados e informes por pantalla sin
     *                      necesariamente esperar contestación por parte 
     *                      del usuario
     *  
     * </pre>
     */
    private CEcho mPantalla = new CEcho( "laPantalla" ) ;
    
    private final String LINEA_R = "_______________________________________________________________________________________________________________\n" ;
    private byte impreso = 0 ;
    private boolean entra = true ;
    private String informeLog   ;
    private String  sRegistro[] ;
    private String elresultado[];    
   
    private String  sAperCierre[]   = {
        LINEA_R ,
        "                              Apertura Registro del Aforo del I.E.S. El Rincón                                 \n" ,
        "" ,
        LINEA_R ,
        LINEA_R ,
        "                              Aforo 0 el I.E.S. El Rincón está vacio                                           \n" ,
        "",
        LINEA_R ,
        
    } ; 
    
    /**
     * <pre>
     * 
     * Declaración de Objetos de caracter general para toda la aplicación
     * 
     * Paquete misClases06
     * 
     * ArchSecuenciales -   Métodos que realizan acciones sobre ficheros 
     *                      de texto y/o secuenciales
     *  
     * </pre>
     */
    
    private ArchSecuenciales elFichero = new ArchSecuenciales(  Constantes.S_ARCHIVO_NF ,
                                                                Constantes.S_ARCHIVO_F  ,
                                                                Constantes.I_N_CAMPOS   ,
                                                                Constantes.NOM_CAMPOS   ,
                                                                Constantes.LONG_CAMPOS  ) ;
    
    private ArchBuffer archBuffer = new ArchBuffer( Constantes.S_FICHERO_NF     ,
                                                    Constantes.S_FICHERO_F      ,
                                                    Constantes.I_F_N_CAMPOS     ,
                                                    Constantes.LOS_CAMPOS       ,
                                                    Constantes.TAM_REGISTRO     ,
                                                    Constantes.N_MAX_REGISTROS  ) ;

    private int     aforo           ;
    private int     entradas        ;
    private int     salidas         ;
    private int     acciones        ;
    private int     accesosEoS      ;
    private int     maxPersonas     ;
    private Date    hora            ;
    
    /**
     * <pre>
     * 
     * Recibe al ser creado la variable maxPersonas que contiene la 
     * cantidad máxima de personas que pueden entrar al recinto
     * Abre el archivo del registro y rellena la cabecera del informe
     * 
     * @param maxPersonas entero número máximo de personas que entra
     * 
     * </pre> 
     */
    public CtrlRegistro( int maxPersonas ) { 
        
        aforo       = 0 ;
        entradas    = 0 ;
        salidas     = 0 ;
        acciones    = 0 ;
        
        this.maxPersonas = maxPersonas ;
        sRegistro = new String [ 1 ] ;
        //abrirArchivo()          ;          
        
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
    public void abrirArchivo(){
        
        sRegistro = new String [ 1 ] ;
        
        // Comprobar si existen los archivos necesarios
        if( elFichero.comprobarArchivo() == 1 ){
            
            if( !elFichero.crearArchivo() ){
                
                mPantalla.mError( 1 ) ;
                System.out.println( elFichero.estado() ) ;  
                
            }
        }
        
        
        
    }
    
    /**
     * <pre>
     * 
     * Escribe la cabecera y/o el pié del informe
     * lo hace leyendo una matriz de cadenas
     * 
     * @param ini   entero para saber desdee donde empezar a leer la matriz
     * @param medio entero que indica en que lugar cargar la 
     *              fecha y hora del informe
     * @param fin   entero para saber hasta donde leer de la matriz
     * 
     * </pre>
     */
    private synchronized void llenarCabPie( int ini, int medio, int fin ){
        
        Date hora = new Date();
        
        sAperCierre[ medio ] = "                     Hora   :   " + hora.toString() ;
        
        for( int l = ini ; l < fin ; l++ ){
            
            sRegistro[ 0 ] = sAperCierre [ l ] ;
            actualizarArchivo() ;            
            
        } 
        
        System.out.print( "\nTotal entradas      : " + getEntradas()        ) ;
        System.out.print( "\nTotal salidas       : " + getSalidas()         ) ;
        System.out.print( "\nTotal aforo         : " + getAforo()           ) ;
        System.out.print( "\nAcciones a realizar : " + ( maxPersonas * 2 )  ) ;
        System.out.print( "\nAcciones realizadas : " + acciones             ) ;
        System.out.print( "\n" + sAperCierre [ 0 ]                          ) ;
        
    }
    
    /**
     * <pre>
     *  
     * Cuando han salido todas las personas el centro está vacio,
     * por lo que se terminan los detectores y se escribe el pie del
     * informe
     * 
     * </pre> 
     */
    private synchronized void centroVacio(){
        
        // cerrarArchivo();
        llenarCabPie( 4, 6, 8 ) ;
        impreso = 2 ;
        
    }
    
    private synchronized void centroINI(){
        
        //if( ( aforo == 0 && entradas == 0 ) && impreso < 1 ){
        llenarCabPie( 0, 2, 4 ) ;
        impreso = 1 ;
        //}
        
    }
    
    /**
     * <pre>
     * Método sincronizado llamado por los objetos Detector 
     * cuando se produce una entrada 
     * 
     * @param acceso cadena con el nombre identificador del Acceso
     * 
     * </pre>  
     */
    private synchronized void creceAforo( String acceso ){
        
        if( entradas < maxPersonas ){
            
            if( acciones == 0 ){ centroINI() ; }
            
            aforo++;
            entradas++;
            acciones++ ;
            
            actualizaAforo( acceso ) ;
            
        } 
        
    }
    
    /**
     * <pre>
     * Método sincronizado llamado por los objetos Detector 
     * cuando se produce una salida 
     * 
     * @param acceso cadena con el nombre identificador del Acceso
     * 
     * </pre>  
     */
    private synchronized void menguaAforo( String acceso ){       
        
        if( aforo > 0 && salidas < entradas ){
                
            aforo--;
            salidas++;
            acciones++ ;
            
            actualizaAforo( acceso ) ;
            
            if( salidas == maxPersonas ){ centroVacio() ; }
            
        }
        
    }
    
    /**
     * <pre>
     * El método es llamado por menguaAforo() y creceAforo(),
     * recibiendo de ellos el acceso que ha realizado la detección
     * y monta la linea que será insertada en el registro, atraves 
     * del método actualizarArchivo() 
     * 
     * @param acceso cadena con el nombre identificador del Acceso
     * 
     * </pre>  
     */
    private synchronized void actualizaAforo( String acceso ) {
        
        String sAforo = "" ; 
        
        hora = new Date() ;          
        
        if( aforo < 100 ){ sAforo = " "     ; }
        if( aforo < 10  ){ sAforo = "  "    ; }
            
        sAforo          =   sAforo              +
                            aforo               ;
        
        sRegistro[ 0 ]  =   acceso              +
                            "     Hora    :   " +
                            hora.toString()     +
                            "     Aforo   :   " +
                            sAforo              ;
        
        informeLog      =   " aforo : "  + aforo     +
                            " entradas : "  + entradas  +
                            " salidas : "   + salidas   +
                            " Acciones : "  + acciones    + "\n" ;
        
        int registro[] = { aforo, entradas, salidas, acciones, accesosEoS } ;
        
        archBuffer.guardarRegistro( 0 , registro ) ;
        actualizarArchivo() ;        

        System.out.print( "\n      ESCRITO\n      " + acceso + informeLog ) ;      
        
    }
    
    /**
     * <pre>
     * Se conecta la archivo de disco y guarda la nueva linea
     * </pre>
     */
    private synchronized void actualizarArchivo(){   
        
        if( elFichero.altaRegistro( sRegistro, Constantes.S_SEPARADOR ) ){ 
            
            System.out.print( "\n      " + sRegistro[ 0 ] ) ;
           
            
        } else {
            
            mPantalla.mError( 1 ) ; 
        
        }
        
    }
    
    private synchronized void actualizaCampAccesos( int nDato ){
        
        if( archBuffer.bloquealo() ){
            nDato += archBuffer.leerCampo( 16 ) ;
            archBuffer.actualizaCampo( 16, nDato ) ;
        }else{
            System.out.println("\nNo se ha guardado el campoTotalAccesos\n");
        }
        archBuffer.desbloquealo() ;
        
    }
    public synchronized void actualizaBuffer( boolean mengCrece, String acceso ){
        
        informeLog =    "\n" + LINEA_R + 
                        "\n      INICIA actualizaBuffer()\n      " + acceso +
                        "\n\n      INTENTO\n      " + acceso + " espera acceder al BUFER\n";        
        System.out.print( informeLog ) ;
        
        /*if( archBuffer.bloquealo() ){
        
            el código que hemos puesto fuera
        
        } else {
            
            informeLog = "\nFRACASA\n" + acceso + " no accede al BUFER\n";        
            System.out.print( informeLog ) ;
            
        }*/    
        
        //el código que antes estaba en el if
        archBuffer.bloquealo();
        
        informeLog = "\n      CONSEGUIDO\n      " + acceso + " Accede al BUFER\n";        
        System.out.print( informeLog ) ;
           
        aforo       =   archBuffer.leerCampo() ;
        entradas    =   archBuffer.leerCampo() ;
        salidas     =   archBuffer.leerCampo() ;   
        acciones    =   archBuffer.leerCampo() ;
        accesosEoS  =   archBuffer.leerCampo() ;  
        
        informeLog  =   " aforo : "  + aforo     +
                        " entradas : "  + entradas  +
                        " salidas : "   + salidas   +
                        " Acciones : "  + acciones    + "\n" ;
            
        System.out.print( "\n      LEIDO\n      " + acceso + informeLog ) ;
            
        if( mengCrece ){ creceAforo( acceso ) ; } else { menguaAforo( acceso ) ; 
        }   
        //if (archBuffer.desbloquealo() ) { entra = true ; }
        archBuffer.desbloquealo();
                           
        informeLog = "\n      FINALIZADO\n      " + acceso + " Libera al BUFER\n" + LINEA_R;        
        System.out.print( informeLog ) ;
        
    }
    
    public synchronized void iniciaAcceso( String acceso){
        //actualizaCampAccesos( int nDato );
        System.out.print( "\n <  > ABIERTO\n      " + acceso  ) ; 
    }
    
    public synchronized void cierraAcceso( String acceso){
        actualizaCampAccesos( -1 ) ;
        System.out.print( "\n  <>  CERRADO\n      " + acceso  ) ; 
    }
    
    /**
     * 
     * @return entero con el número de entradas hasta el momento 
     */
    public synchronized int     getEntradas()   { return this.entradas        ; }
    /**
     * 
     * @return entro del número de personas que hay en un momento dado
     */
    public synchronized int     getAforo()      { return this.aforo           ; }
    /**
     * 
     * @return entero número de personas que han salido
     */
    public synchronized int     getSalidas()    { return this.salidas         ; }
    
    public synchronized boolean puedoEntrar(){ return entra ; }
    
}
