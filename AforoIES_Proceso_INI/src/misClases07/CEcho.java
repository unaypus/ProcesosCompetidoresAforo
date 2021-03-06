/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misClases07;

/**
 *<pre>
 * Métodos para maquetar la salida por pantalla
 * 
 * 
 * @author juaponabr
 * 
 *   Alumno: Juan Pons Abraham
 *   Para:   Curso 1º DAM Tarde/Noche grupo A
 * 
 * </pre>
 * 
 */

public class CEcho {
    
    private String sNombre;
   
    /**
     * <pre> Constructor CEcho
     * 
     * Constructor de la clase
     * se le asigna un nombre al instanciarlo con la instruccion new
     * 
     * De momento el que tenga un nombre no es relevante, pero se usa
     * para compatibilida d de futuras versiones en las que si fuera
     * necesario su uso
     * 
     * @param cNombre nombre
     * </pre>
     */
    public CEcho( String cNombre){
        sNombre = cNombre;
    }
    
    // //////////////////////////////////////////////////
    // Funciones echo
    // //////////////////////////////////////////////////
    
    /**
     * <pre> Método mOrdinal
     * 
     * Recibe un entero y devuelve una cadena
     * con su correspondiente ordinal
     * 
     * ej.:
     *  recibe el entero    1
     *  sale la cadena      1º
     * 
     * @param nPosicion un entero
     * @return el ordinal
     * </pre>
     */
    public String mOrdinal(int nPosicion){
        /**
         * 
        
         * 
         */
        String elOrdinal;
        elOrdinal = Integer.toString(nPosicion)+"º";
        return elOrdinal;
    }
    
    /**
     * <pre> Método mCreditos
     * 
     * Muestra en pantalla 
     * el título del programa
     * una descripción de lo que hace
     * y los créditos de autoría
     * @param sDescripcion descripción de lo que hace
     * </pre>
     */
    public void mCreditos(String sDescripcion[]){
               
        // Título y descripción 
        System.out.println("-------------------------------------------------------------------------");
        
	for (String elemento:sDescripcion) {
            System.out.println(elemento);
        }
        
        // autor
	System.out.println("\n   Autor: Juan Pons Abraham");
	System.out.println("   Para:  Curso 1º DAM Tarde/Noche grupo A\n");
        
    }
    
    /**
     * <pre> Método mInstrucciones
     * 
     * Muestra en pantalla las instrucciones de uso del programa
     * según el valor de la variable queInst
     * 
     * @param queInst entero
     * </pre>
     */
    public void mInstrucciones(int queInst){
        
        /**
         * Cabecera del mensaje
         * igual para todas las instruciones
         * 
         */
        System.out.println("-------------------------------------------------------------------------");
        System.out.println(" ");
	System.out.println(" Instrucciones de uso:");
	System.out.println(" ");
        /**
         * Si la queInst es positiva
         * asumimos que la aplicación pedirá la entrada por teclado
         * de números y que se producirá un error en tiempo de ejecución
         * si se introduce texto o caracteres especiales 
         * 
         */
        if (queInst>0) {
            System.out.println("   Este programa comprueba valores numericos, si introduce ");
            System.out.println("   texto o caracteres especiales dará error y se interrumpirá ");
            System.out.println("   el programa.");
            System.out.println(" ");
            System.out.println("   Si puede usar ");
            System.out.println(" ");
        }
        
        // Comprobación de los números positivos
        switch (queInst) {
            
            // Se usa en espera hasta pulsar INTRO
            case 0:
                System.out.println("Este programa sólo necesita que el usuario \n"+
                                   "pulse INTRO para iniciar el proceso");
                break;
            
            // Para la mayoría de casos
            case 1:
                System.out.println("   Números de varias cifras                       ej: 123");
                System.out.println("   Números con signo                              ej: -12");
                System.out.println("   Números reales usando la coma como separador   ej: 2,1367");
                System.out.println("   (el programa trunca automaticamente a 2 decimales 2.14)");
                break;
            
            // Caso que solo admite positivos y 0
            case 2:
		System.out.println("  Números de varias cifras                        ej: 123");
		System.out.println("  Números reales usando el punto como separador   ej: 2.6");
		System.out.println(" ");
		System.out.println("  No use");
		System.out.println(" ");
		System.out.println("  Números con signo NEGATIVO                      ej: -12");
		break;
            // Caso en que el segundo número tiene otras reglas     
            case 3:
		System.out.println("   Números de varias cifras                       ej: 123");
                System.out.println("   Números con signo                              ej: -12");
                System.out.println("   Números reales usando la coma como separador   ej: 2,1367");
                System.out.println("   (el programa trunca automaticamente a 2 decimales 2.14)");
		System.out.println(" ");
		System.out.println("   El segundo número");  
		System.out.println(" ");
		System.out.println("   Debe ser un entero                              ej: 5");
                System.out.println("   Puede tener signo                               ej: -5 ");
		System.out.println("   Si se introducen decimales estos se obiarán     ej: 2.6 = 2");
		break;
            //
            case 4:
                System.out.println("   Números de 5 cifras                            ej: 12335");
                System.out.println("   Números SIN signo                              ej: -12345");
                System.out.println("   No puede usar comas u otros separadores        ej: 1.234,5");
                break;
            //
            case 5:
                System.out.println("   Debe ser un entero                             ej: 5");
                System.out.println("   Números SIN signo                              ej: -12");
                System.out.println("   No puede usar comas u otros separadores        ej: 1.2,5");
                break;
            //
            case 6:
                System.out.println("\n   No se creó el archivo vehiculos.dbv\n");
                break;    
                
            default:
                System.out.println("   Este ejercicio/programa todavia no se ha realizado");
                System.out.println("   Ejercicios disponibles del   1 al 11");
                System.out.println("                          del  13 al 14");
                System.out.println("                          y el 22");
                System.out.println("   ");
                System.out.println("   Programas disponibles   el   5");
                System.out.println("                        y del   7 al  9");
                System.out.println("   ");
                System.out.println("   Pruebe con otro. Gracias");
               break;
        }
	
        System.out.println(" ");
	 
    }
    
    /**
     * <pre> Método mBienvenida
     * 
     * Muestra en pantalla la bienvenida
     * compuesta por los créditos 
     * y las instrucciones de uso del programa        
     * 
     * @param sDescripcion matriz canenas
     * @param queInst entero
     * </pre>
     */
    public void mBienvenida(String sDescripcion[], int queInst) {
        
	
       //System.out.println("\u001B[2J");
        mCreditos(sDescripcion);
	//mInstrucciones(queInst);
        
    }
    
    /**
     * <pre> Método mMenu
     * 
     * Muestra en pantalla 
     * un  menú de opciones para que el usuario elija 
     * la acción que desea que el programa realice
     * 
     * Recibe
     * 
     * @param sTituloMenu   : Cadena con el título del menú
     * @param sMenu         : Array con los textos de las diferente opciones
     * </pre>
     */
    public void mMenu(String sTituloMenu, String sMenu[]) {

        System.out.println("-------------------------------------------------------------------------");
	System.out.println(" ");
          
        System.out.println(sTituloMenu);
	mMatCadenas(sMenu);     
    	
    }
     
    /**
     * <pre> Método mAyuda
     * 
     * Muestra en pantalla 
     * una descripción de lo que hace
     * el programa a modo de ayuda al usuario
     * 
     * @param sAyuda descripción de lo que hace
     * </pre>
     */
    public void mAyuda(String sAyuda[]) {
        
	mMatCadenas(sAyuda);     
    	
    }
    
    /**
     * <pre> Método mMatCadenas
     * 
     * Muestra en pantalla 
     * el contenido de una matriz de strings
     * previamente formateadas
     * 
     * @param sCadenas matriz de cadenas
     * </pre>
     */
    public void mMatCadenas(String sCadenas[]) {        
	 
        System.out.println("-------------------------------------------------------------------------");
	System.out.println(" ");
        for (String elemento:sCadenas) {
            System.out.println(elemento);
         }
	System.out.println(" ");
    	
    }
    
    /**
     * <pre> Método mDespedida
     * 
     * Muestra en pantalla 
     * el título del programa
     * una descripción de lo que hace
     * los créditos de autoría
     * y la despedida
     * 
     * @param sDescripcion descripción de lo que hace
     * </pre>
     */
    public void mDespedida(String sDescripcion[]) {        
	
        // llamada acréditos
        mCreditos(sDescripcion);
        
        // texto despedida
        System.out.println("-------------------------------------------------------------------------");
	System.out.println(" El programa ha terminado a petición del usuario");
	System.out.println(" Garcias y hasta pronto.");
	System.out.println("-------------------------------------------------------------------------");
		
    }
    
    /**
     * <pre> Método mResultado
     * 
     * Muestra en pantalla 
     * el resultado de las operaciones realizadas
     * a los datos introducidos por el usuario
     * u operaciones con datos internos de la
     * aplicación que deben ser conocidos por el usuario
     * 
     * Para ello recibe una MATRIZ con los datos formateados
     * y estructurados en cadenas de texto
     * 
     * @param elResultado matriz de cadenas
     * </pre>
     */
    public void mResultado(String elResultado[]) {        
	
        mMatCadenas(elResultado); 
	
    }    
    
    /**
     * <pre> Método mError
     * 
     * Asumiendo que se ha producido un error debido
     * a una entrada por teclado no permitida
     * se muestra en pantalla un mensaje de error
     * y las instrucciones de uso del programa
     * 
     * @param queError el numero de error
     * </pre>
     */
    public void mError(int queError){
        
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("¡ Atención ! Se ha producido un ERROR\n");
        // mensaje de error
        switch (queError) {
            case 0:
                // error de tipo de número
                System.out.println("   No ha introducido un número valido.");
                break;
            case 1:
                // error de acceso a archivos
                System.out.println("   No se ha podido realizar la acción sobre el archivo especificado");   
                break;
            case 2:
                 // error de tipo de número
                System.out.println("   No ha introducido un número valido.");
                System.out.println("   El separador decimal debe ser un punto.");
                System.out.println("   Las cifras no puede ser letras ni caracteres especiales.");
                break;
            case 3:
                 // error de tipo de número
                System.out.println("   La cantidad ingresada es negativa.");
                break;
            case 4:
                 // error de tipo de número
                System.out.println("   La cantidad ingresada supera la máxima permitida.");
                break;
            default:
                // error no definido
                System.out.println("   Se ha producido un error inesperado.");   
                break;
        }
                      
        System.out.println("\n   Vuelva a intentarlo");
        
       
        // llamada a las instrucciones del programa
        //mInstrucciones(queInst);
                
    }    
    
    /**
     * <pre> Método ajustarCadena
     * 
     * comprobamos que sCadena tiene la longitud deseada en nLongCadena
     * si excede recortamos , si no llega añadimos espacios
     * 
     * @param sCadena      cadena a comprobar
     * @param nLongCadena longitud deseada
     * @return  la cadena ajustada
     * </pre>
     */    
    public String ajustarLongCadena(String sCadena, int nLongCadena){
        
        
        String sSalida = sCadena;
        int nLCad = sCadena.length();
        
        if(nLCad>nLongCadena){
            sSalida = sCadena.substring(0, nLongCadena);
        }else{
            for(int nContador = nLCad; nLCad < nLongCadena; nLCad++){
                sSalida = sSalida + " ";
            }
        }
       
        
        return sSalida ;
        
    }
    
    
    // //////////////////////////////////////////////////
    // Fin Funciones echo
    // //////////////////////////////////////////////////
    
}
