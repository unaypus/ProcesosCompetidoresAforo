/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misClases07;

import java.io.*;

/**
 *
 * @author japonabr
 * 
 *   Alumno: Juan Pons Abraham
 *   Para:   Curso 1º DAM Tarde/Noche grupo A
 * 
 * Métodos que realizan acciones sobre ficheros de texto y/o secuenciales
 * 
 */
public class ArchSecuenciales {
    
    static CInteractiva pInteraccion = new CInteractiva("Peticiones");
    // Constantes para mostrar
    private final String NO_ENCONTRADO      = "No sa ha encontrado ningún registro que corresponda con la busqueda : ";
    private final String ERROR_DESC         = "Error desconocido al acceder al archivo" ;
    private final String REGISTROS_ENC      = "Registros encontrados : ";
    private final String FIN_ARCHIVO        = "Fin del archivo" ;
    private final String EL_ARCHIVO         = "El archivo : " ;
      
    private final String ESTADO_ARCHIVO [] = {  " SI se puede leer y escribir.",
                                                " NO existe",
                                                " NO se ha podido CREAR.",
                                                " NO se puede acceder.",
                                                " SI se puede leer pero NO escribir"};
    
    
    private String sNombre;
    private String sArchivo ;
    private int nError;   
    private int nCampos;
    private int tamRegistro;
    private String sCampos [];
    private int lCampos [];
    private long lTamanyoArchivo ;
    private long nRegPreVis = 0 ;
    private  File ficheroSeq ;
    
    
    /**
     * 
     * AccesoFicheros
     * 
     * Constructor de la clase
     * 
     * @param cNombre       le asigna un nombre                                     a sNombre
     * @param cArchivo      le asigna ruta/nombre del archivo                       a sArchivo
     * @param iCampos       le asigna el número de campos que contine un registro   a nCampos
     * @param losCampos     le asigna los nombres de los campos                     a sCampos
     * @param longCampos    le asigna las longitudes de los campos                  a lCampos
     */
    public ArchSecuenciales(   String cNombre, 
                                    String cArchivo, 
                                    int iCampos, 
                                    String losCampos[],
                                    int longCampos[]){
        
        
        
        sNombre     = cNombre;
        sArchivo    = cArchivo;
        nCampos     = iCampos ;
        sCampos     = new String [nCampos];
        lCampos     = new int [nCampos];
        tamRegistro = iCampos ;
        
        
        System.arraycopy(losCampos, 0, sCampos, 0, nCampos);
        System.arraycopy(longCampos, 0, lCampos, 0, nCampos);
        
        for(int nContador= 0;nContador < nCampos; nContador++){
            tamRegistro = tamRegistro+lCampos[nContador];
        }
        
        ficheroSeq = new File (sArchivo);
        
    }
    /**
     * 
     * @return 
     */
    public String nombreFichero(){
        return sNombre;
    }
    
    /**
     * 
     * crearArchivo
     * 
     * crea en disco el archivo
     * si lo consigue devuelve 0
     * si no devuelve -1
     * 
     * 
     * @return 
     */
    public boolean crearArchivo(){
        
        
        boolean bSalida = true;
       
        File ficheroSeq = new File (sArchivo);
        
        try{
            ficheroSeq.createNewFile();
            nError = 0;
        }
        
        catch (IOException ioe){
            bSalida = false;
            nError = -1;
            //
        }
        
        return bSalida;
        
    }    
    
    /**
     * 
     * comprobarArchivo
     * 
     * intenta acceder al archivo devolviendo un código
     * 
     * 0 existe, se puede leer y escribir
     * 1 no existe
     * 3 existe pero no se puede leer
     * 4 existe , se puede leer pero no escribir
     * 
     * @return 
     */
    public int comprobarArchivo(){        
                
        if(ficheroSeq.exists()){
            if(ficheroSeq.canRead()){
                tamanyoYnumRegistros();
                if(ficheroSeq.canWrite()){
                     nError = 0;
                }else{
                    nError = 4;
                }
            }else{
                nError = 3;
            }
        }else{
            nError = 1;           
        }
        
        return nError;
        
    }  
    
    /**
     * 
     * estado
     * 
     * según el código de error hallado por comprobarArchivo
     * generamos una cadena paa informar al usuario
     * 
     * @return 
     */
    public String estado(){
        
        String sSalida ;
        
        if(nError < 0 ){
            sSalida = ERROR_DESC ;
        }else{
            sSalida = EL_ARCHIVO + sArchivo + ESTADO_ARCHIVO[nError] ;
        }
        
        return sSalida;
        
    }
    
    /**
     * 
     */
    public void tamanyoYnumRegistros(){
        lTamanyoArchivo =  ficheroSeq.length();
        nRegPreVis = lTamanyoArchivo / tamRegistro;
    }
    
    /**
     * 
     * @return 
     */
    public long numRegistros(){
        tamanyoYnumRegistros();
        return nRegPreVis ;
    }
    
    /**
     * 
     * altaRegistro
     * 
     * guarda en un registro los datos recibidos en aCampos[]
     * y separa los campos con el caracter de sSeparador
     * 
     * true si lo cosigue false si no ha podido hacerlo
     * 
     * @param aCampos
     * @param sSeparador
     * @return 
     */
    public boolean altaRegistro(String aCampos[], String sSeparador){
        
        boolean sSalida ;
        String sCadenaRegistro = "" ;
        int nLongCad ;
        
        // generar el registro con los campos separados por sSeparador
        for(int nContador = 0; nContador < nCampos; nContador++){
            sCadenaRegistro = sCadenaRegistro + aCampos[nContador] + sSeparador ;
        }
        // quitamos el último sSeparador
        nLongCad = sCadenaRegistro.length()-1;
        sCadenaRegistro = sCadenaRegistro.substring(0, nLongCad);
        
        try {
            // añadimos el nuevo registro
            FileWriter archivoEscritor = new FileWriter(sArchivo, true);
            BufferedWriter archivoFlujo = new BufferedWriter(archivoEscritor);
            archivoFlujo.write(sCadenaRegistro);
            archivoFlujo.newLine();
            archivoFlujo.close();
            sSalida = true;
            
        } catch (IOException e) {
            
            sSalida = false;
            
        }
        
        return sSalida;
    }     
    
    
    /**
     * método listadoCompleto 
     * 
     * accedemos al arhivo para ver todos sus registros
     * devolviendo una cadena con todos ellos 
     * o en caso de error el código correspondiente
     * 
     * @param sSeparador
     * @return 
     */
    public String listadoCompleto(String sSeparador){
       
        
        String sLista = "" ;
        String sRegistro = "" ;
        String sCAD [] ;
        sCAD = new String[nCampos];
        int nRegistros ;        
        char letra = 32 ; //num antiguo 124;
        
        try {
            // Actualizar nº de registros
            tamanyoYnumRegistros();
            nRegistros = 0 ;
            FileReader archivoLector = new FileReader(sArchivo);
            
            BufferedReader archivoFlujo = new BufferedReader(archivoLector);
        
            // recorremos todo el archivo
            while(sRegistro != null){
                
                sRegistro = archivoFlujo.readLine();
                //
                // al hallar un registro formateamos la cadena de salida
                // y al llegar al final
                // añadimos el número de registros procesados
                //
                if(sRegistro != null){                    
                    
                    sCAD = sRegistro.split(sSeparador);
                    sLista = sLista+letra+" ";
                    for(int nContador = 0; nContador < nCampos; nContador++){
                        sLista =    sLista+sCAD[nContador]+" "+letra+" ";
                    }
                    
                    sLista = sLista + "\n";
                    nRegistros++;
                        
                }else{
                    
                     sLista =   sLista  + "\n"+ REGISTROS_ENC + nRegistros+"   " +
                                 FIN_ARCHIVO +"\n";
                     
                }
            }
            
            archivoFlujo.close();
            
        } catch (IOException e) {
            
            sLista = ERROR_DESC;
            
        }
        
        return sLista;
        
    }
    
    /**
     * 
     * buscaRegistro
     * 
     * buscamos un registro que su primer campo contenga cadBusca
     * devolviendo una cadena con el regsitro coincidente 
     * o en caso de error el código correspondiente
     *
     * @param cadBusca
     * @param sSeparador
     * @param logCadCampo
     * @return 
     */
    public String buscaRegistro(String cadBusca, String sSeparador, int logCadCampo){       
        
        String sSalida ;
        String sRegistro = "" ;
        String sCAD [] ;
        sCAD = new String[nCampos];
        int nRegistros ;
       
        try {
            
            sSalida = NO_ENCONTRADO +cadBusca;
            FileReader archivoLector = new FileReader(sArchivo);
            BufferedReader archivoFlujo = new BufferedReader(archivoLector);
            
            // recorremos todo el archivo
            while(sRegistro != null){
                
                sRegistro = archivoFlujo.readLine();
                
                if(sRegistro != null){                    
                    
                    sCAD = sRegistro.split(sSeparador);
                    
                    // al hallar el registro formateamos la cadena de salida
                    if(sCAD[0].equals(cadBusca)){
                        sSalida =   "\n";
                        for(int nContador = 0; nContador < nCampos; nContador++){
                            sSalida = sSalida+pInteraccion.ajustarCadena(sCampos[nContador], logCadCampo , "*")
                                +"  :  "+sCAD[nContador]+"\n";
                        }
                        
                        break;
                    }
                }
                
            }
        } catch (IOException e) {
            
            sSalida = ERROR_DESC;
            
        }
        
        return sSalida;
        
    }    
    
}
