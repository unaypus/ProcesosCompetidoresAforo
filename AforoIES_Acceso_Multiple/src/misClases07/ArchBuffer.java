/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misClases07;

import java.io.*;
import java.nio.channels.FileLock;
import java.text.DecimalFormat;

/**
 *
 * @author japonabr
 * 
 *   Alumno: Juan Pons Abraham
 *   Para:   Curso 1º DAM Tarde/Noche grupo A
 * 
 * funciones que realizan acciones sobre ficheros de binarios con acceso directo
 * 
 */
public class ArchBuffer {
    
    static CInteractiva pInteraccion = new CInteractiva("Peticiones");
    // Constantes para mostrar
    private final String NO_ENCONTRADO      = "No sa ha encontrado ningún registro que corresponda con la busqueda : ";
    private final String ERROR_DESC         = "Error desconocido al acceder al archivo" ;
    private final String REGISTROS_ENC      = "Registros encontrados : ";
    private final String FIN_ARCHIVO        = "Fin del archivo" ;
    private final String REGISTROS_LIB      = "Registros libres: ";
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
    private long lTamanyoArchivo ;
    private long nRegLibres = 0 ;
    private int nMaxREG = 0 ;
    private int posActualREG = 0 ;
    private File ficheroDIR ;
    private RandomAccessFile flujoLectura;
    private RandomAccessFile fEscrituraLectura;
    private FileLock bloqueado = null;
    
    DecimalFormat fE5 = new DecimalFormat("##,###,###.##");
    
    
    /**
     * 
     * Constructor de la clase
     * 
     * @param cNombre       le asigna un nombre                                     a sNombre
     * @param cArchivo      le asigna ruta/nombre del archivo                       a sArchivo
     * @param iCampos       le asigna el número de campos que contine un registro   a nCampos
     * @param losCampos     le asigna los nombres de los campos                     a sCampos
     * @param tamRegistro   
     * @param nMaxRegitros   
     * 
     */
    public ArchBuffer(    String cNombre, 
                            String cArchivo, 
                            int iCampos, 
                            String losCampos[], 
                            int tamRegistro,
                            int nMaxRegitros){
        
        
        
        sNombre = cNombre;
        sArchivo = cArchivo;
        nCampos = iCampos ;
        sCampos = new String [ nCampos ] ;
        this.tamRegistro = tamRegistro ;
        nMaxREG = nMaxRegitros;
        
        System.arraycopy( losCampos, 0, sCampos, 0, nCampos ) ;
        
        ficheroDIR = new File( sArchivo ) ;
        
        
    }    
    
    /**
     * 
     * @param sCampoCCC
     * @param sCampoENT
     * @param sCampoOF
     * @param sCampoDC
     * @param sCampoTIT
     * @param nCampoSALD
     * @return 
     */
    public boolean guardarRegistro2( int nCampoCOD,
                                    String sCampoCCC,
                                    String sCampoENT,
                                    String sCampoOF,
                                    String sCampoDC,
                                    String sCampoTIT,
                                    double nCampoSALD){
                      
        try{
            // CCC
            // CÓDIGO
            fEscrituraLectura.writeInt(nCampoCOD);
            // CUENTA
            fEscrituraLectura.writeUTF(sCampoCCC); 
            // ENTIDAD
            fEscrituraLectura.writeUTF(sCampoENT); 
            // OFICINA
            fEscrituraLectura.writeUTF(sCampoOF); 
            // DC
            fEscrituraLectura.writeUTF(sCampoDC); 
            // TITULAR
            fEscrituraLectura.writeUTF(sCampoTIT); 
            // SALDO
            fEscrituraLectura.writeDouble(nCampoSALD);
            
            return true;
            
        } catch (IOException e) {
            
            return false;
            
        }
                      
    }
    
    public boolean bloquealo(){
        
        //if( bloqueado != null ){ return false ; }
        
        try{
            
            //Crea un flujo de E/S para un fichero de acceso directo, en modo rw para grabar
            fEscrituraLectura  = new RandomAccessFile(ficheroDIR,"rwd");
            bloqueado         = fEscrituraLectura.getChannel().lock();
            return true;
            
        } catch (IOException e) {
            
            bloqueado = null ;
            return false;
            
        }
            
    }
    
    public boolean desbloquealo(){
        
        try{
            
            fEscrituraLectura.close() ;
            bloqueado.release();            
            bloqueado = null;
            
            return true;
            
        } catch (IOException e) {
            
            return false;
            
        }
        
    }
    
    public boolean guardarRegistro( int posArchivo, int[] campoINT){
                      
        try{
            
            fEscrituraLectura.seek( posArchivo ) ;
            
            for( int posMat = 0 ; posMat < campoINT.length ; posMat++ ){
                
                escribirCampo( campoINT[ posMat ] ) ;
                
            }        
            
            return true;
            
        } catch (IOException e) {
            
            return false;
            
        }
                      
    }   
    
    /**
     * 
     * Método crearArchivo
     * 
     * crea en disco el archivo
     * si lo consigue devuelve 0
     * si no devuelve -1
     * 
     * 
     * @return 
     */
    public boolean crearArchivo(){
        
        // Crea un fichero vacio con los códigos con valor cero
        boolean bSalida = true;
        
        try{
            
            if(ficheroDIR.exists()){
                ficheroDIR.delete();
            }
            
            ficheroDIR.createNewFile();
            
            if( bloquealo() ){
                if( guardarRegistro( 0, Constantes.REGISTRO_INI ) ){ 
                    if( desbloquealo() ){ nError = 0 ; }
                }
                
            }
            
        } catch (IOException ioe ){
            
            bSalida = false;
            nError = -1;
            
        }
        
        return bSalida;
        
    }    
    
    /**
     *
     * Método comprobarArchivo
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
        
            
        if(ficheroDIR.exists()){
            if(ficheroDIR.canRead()){
                if(ficheroDIR.canWrite()){
                    nError = 0;
                }else{
                    nError = 4;
                }
            }else{
                nError = 3;
            }
            /*
            if(!tamanyoYnumRegistros()){
                nError = 1;
            }
            */
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
     * @return 
     */
    public boolean tamanyoYnumRegistros(){
        
        nRegLibres = 0 ;
        
        try {
            flujoLectura =new RandomAccessFile(ficheroDIR,"r");
        
            for(int nContador=0;nContador<nMaxREG;nContador++){
                        
                flujoLectura.seek((nContador)*tamRegistro);
            
                if(flujoLectura.readInt()==0){
                    
                    nRegLibres++;
                    
                }
            }
            
            flujoLectura.close();
            
        } catch (IOException ex) {
            return false;
        }
        
        
        
        return true;
        
    }
    
    /**
     * 
     * @return 
     */
    public long numRegistros(){
        tamanyoYnumRegistros();
        return nRegLibres ;
    }
    
    /**
     * 
     * @return 
     */
    public int posicionActualRegistro(){
        return posActualREG;
    }
    
    /**
     * 
     * @param aCampos
     * @param posREG
     * @return 
     */
    public boolean modifRgistro(String aCampos[], int posREG){
        
        boolean sSalida = true ;
        
        try {            
            
            fEscrituraLectura =new RandomAccessFile(ficheroDIR,"rw");
            
            fEscrituraLectura.seek((posREG-1)*tamRegistro);
            
            if(fEscrituraLectura.readInt()==posREG){
                
                fEscrituraLectura.seek((posREG-1)*tamRegistro);
                if(guardarRegistro2( posREG,
                                    aCampos[1],
                                    aCampos[2],
                                    aCampos[3],
                                    aCampos[4],
                                    aCampos[5],
                                    Double.parseDouble(aCampos[6].trim()))){
                    sSalida = true ;
                    
                    posActualREG = posREG;
                }else{
                    sSalida = false;
                }
                 
               
            }
              fEscrituraLectura.close();
            
        } catch (IOException e) {
            
            sSalida = false;
            
        }
        
        return sSalida;
        
    }
    
    /**
     * 
     * Método altaRegistro
     * 
     * guarda en un registro los datos recibidos en aCampos[]
     * y separa los campos con el caracter de sSeparador
     * 
     * true si lo cosigue false si no ha podido hacerlo
     * 
     * @param aCampos
     * @return 
     */
    public boolean altaRegistro(String aCampos[]){
        
        boolean sSalida = true ;
        String sCadenaRegistro = "" ;
        String unaCad = "" ;
        int nLongCad ;
        int nNuevoReg ;
        
        try {            
            
            fEscrituraLectura =new RandomAccessFile(ficheroDIR,"rw");
            
            // hay que buscar el registro
            // donde colocarse
            // el primero que su código sea 0
            
            for(int nContador = 0 ; nContador < nMaxREG; nContador++){
                
                fEscrituraLectura.seek(nContador*tamRegistro);
                
                if(fEscrituraLectura.readInt()==0){
                    
                    // añadimos el nuevo registro");
                    fEscrituraLectura.seek(nContador*tamRegistro);
                    
                    if(guardarRegistro2( nContador+1,
                                        aCampos[1],
                                        aCampos[2],
                                        aCampos[3],
                                        aCampos[4],
                                        aCampos[5],
                                        Double.parseDouble(aCampos[6].trim()))){
                        sSalida = true ;
                        nRegLibres--;
                        posActualREG = nContador + 1;
                    }else{
                        sSalida = false;
                    }
                    
                    break;
                }
                
            }
            
            fEscrituraLectura.close();
            
            
        } catch (IOException e) {
            
            sSalida = false;
            
        }
        
        return sSalida;
    }     
    
    /**
     * 
     * @param cadBusca
     * @return 
     */
    public boolean bajaRegistro(String cadBusca){
        
        String sSalida ="" ;
        int codigo ;
        int nCodBusqueda = Integer.parseInt(cadBusca.trim()) ;
       
        try {
            
            fEscrituraLectura =new RandomAccessFile(ficheroDIR,"rw");
            
            fEscrituraLectura.seek((nCodBusqueda-1)*tamRegistro);
            
            codigo=fEscrituraLectura.readInt();
            
            
            if(codigo==nCodBusqueda){
                
                //System.out.println("Llegamos hasta aquí  "+ codigo);
                fEscrituraLectura.seek((nCodBusqueda-1)*tamRegistro);
                guardarRegistro2(    0,
                                    "1234567891",
                                    "1234",
                                    "1234",
                                    "12",
                                    "1234567891123456789212345678931234567894",
                                    0);
                nRegLibres++;

            }
             
            fEscrituraLectura.close();
            
        }catch(EOFException eofe){
            sSalida = NO_ENCONTRADO +cadBusca;
        }catch (IOException e) {             
            sSalida = ERROR_DESC;            
        }
        
        return true;
    }
    
    /**
     * 
     * Metodo listadoCompleto
     * 
     * accedemos al arhivo para ver todos sus registros
     * devolviendo una cadena con todos ellos 
     * o en caso de error el código correspondiente
     * 
     * @return 
     */
    public String listadoCompleto(){
        
        String sLista = " " ;
        int nRegistros ;
        int nCod;
        char letra = 32 ; //num antiguo 124;       
               
        try {
           
            nRegistros = 0 ;
            sLista = "" ;
            
            flujoLectura =new RandomAccessFile(ficheroDIR,"r");    
            
            // recorremos todo el archivo
            for(long nContador = 0;nContador<nMaxREG; nContador++){
                
                // al hallar un registro formateamos la cadena de salida
                // y al llegar al final
                // añadimos el número de registros procesados
                
                flujoLectura.seek((nContador)*tamRegistro);
                nCod = flujoLectura.readInt() ;
                
                if(nCod>0){
                    sLista = sLista+letra+" ";
                    sLista = sLista+pInteraccion.ajustarCadenaNUM(String.valueOf(nCod), 4 , false)+" "+letra+" ";
                    sLista = sLista+flujoLectura.readUTF()+" "+letra+" ";
                    sLista = sLista+flujoLectura.readUTF()+" "+letra+" ";
                    sLista = sLista+flujoLectura.readUTF()+" "+letra+" ";
                    sLista = sLista+flujoLectura.readUTF()+" "+letra+" ";
                    sLista = sLista+flujoLectura.readUTF()+" "+letra+" ";
                    sLista = sLista+pInteraccion.ajustarCadenaNUM(fE5.format(flujoLectura.readDouble()), 13 , false) +" "+letra+" ";
                            //String.valueOf(flujoLectura.readDouble()), 13 , false) +" "+letra+" ";
                    sLista = sLista + "\n";
                    nRegistros++;
                }
                
            }
            sLista =   sLista  + "\n"+ REGISTROS_ENC + nRegistros+" - " +
                                 REGISTROS_LIB + (nMaxREG-nRegistros) + "\n";
                       
            flujoLectura.close();
            
        } catch (IOException e) {
            
            sLista = ERROR_DESC;
            
        }
        
        return sLista;
        
    }
    
    /**
     * 
     * Método buscaRegistro
     * 
     * buscamos un registro que su primer campo contenga cadBusca
     * devolviendo una cadena con el regsitro coincidente 
     * o en caso de error el código correspondiente
     * 
     * @param cadBusca
     * @param logCadCampo
     * @return 
     */
    public String buscaRegistro(String cadBusca, int logCadCampo){
        
        String sSalida ="" ;
        int codigo ;
        int nCodBusqueda = Integer.parseInt(cadBusca.trim()) ;
       
        try {
            
            flujoLectura =new RandomAccessFile(ficheroDIR,"r");
            
            flujoLectura.seek((nCodBusqueda-1)*tamRegistro);
            
            codigo=flujoLectura.readInt();
            
            // al hallar el registro formateamos la cadena de salida
            if(codigo==nCodBusqueda){
                
                sSalida =   "\n";
                sSalida =   sSalida +pInteraccion.ajustarCadena(sCampos[0], logCadCampo , "*")
                                +"  :  "+codigo+"\n";
                
                
                sSalida = sSalida+pInteraccion.ajustarCadena(sCampos[2], logCadCampo , "*")
                                +"  :  "+flujoLectura.readUTF()+"\n";
                sSalida = sSalida+pInteraccion.ajustarCadena(sCampos[3], logCadCampo , "*")
                                +"  :  "+flujoLectura.readUTF()+"\n";
                sSalida = sSalida+pInteraccion.ajustarCadena(sCampos[4], logCadCampo , "*")
                                +"  :  "+flujoLectura.readUTF()+"\n";
                sSalida = sSalida+pInteraccion.ajustarCadena(sCampos[1], logCadCampo , "*")
                                +"  :  "+flujoLectura.readUTF()+"\n";
                
                sSalida = sSalida+pInteraccion.ajustarCadena(sCampos[5], logCadCampo , "*")
                                +"  :  "+flujoLectura.readUTF()+"\n";              
                
                
                
                sSalida = sSalida+pInteraccion.ajustarCadena(sCampos[6], logCadCampo , "*")
                                +"  :  "+fE5.format(flujoLectura.readDouble())+"\n";
                
                
                
            }else{
                sSalida = "ERROR - "+NO_ENCONTRADO +cadBusca; 
            }
             
            flujoLectura.close();
            
        }catch(EOFException eofe){
            sSalida = "ERROR - "+NO_ENCONTRADO +cadBusca; 
        }catch (IOException e) {             
            sSalida = ERROR_DESC;            
        }
        
        return sSalida;
        
    }   
    
    public String buscaRegistro2(String cadBusca, int logCadCampo,String[] unReg ){
        
        String sSalida ="" ;
        int codigo ;
        int nCodBusqueda = Integer.parseInt(cadBusca.trim()) ;
       
        try {
            
            flujoLectura =new RandomAccessFile(ficheroDIR,"r");
            
            flujoLectura.seek((nCodBusqueda-1)*tamRegistro);
            
            codigo=flujoLectura.readInt();
            
            // al hallar el registro formateamos la cadena de salida
            if(codigo==nCodBusqueda){
                
                
                unReg[0] =  ""+codigo;
                
                unReg[1] = flujoLectura.readUTF();
                unReg[2] = flujoLectura.readUTF();
                unReg[3] = flujoLectura.readUTF();
                unReg[4] = flujoLectura.readUTF();
                unReg[5] = flujoLectura.readUTF();              
                
                
                
                unReg[6] = fE5.format(flujoLectura.readDouble());
                
                
                
            }else{
                sSalida = "ERROR - "+NO_ENCONTRADO +cadBusca; 
            }
             
            flujoLectura.close();
            
        }catch(EOFException eofe){
            sSalida = "ERROR - "+NO_ENCONTRADO +cadBusca; 
        }catch (IOException e) {             
            sSalida = ERROR_DESC;            
        }
        
        return sSalida;
        
    } 
    
    public int leerCampo(){
        
        try {
            
            return fEscrituraLectura.readInt() ;
            
        }catch ( IOException e ) {             
            
            return -1 ;            
            
        }
                
    }
    public int leerCampo( int queCampo ){
        
        try {
            
            fEscrituraLectura.seek( queCampo ) ;
            return fEscrituraLectura.readInt() ;
            
        }catch ( IOException e ) {             
            
            return -1 ;            
            
        }
                
    }

    public boolean escribirCampo( int campINT ){
        
        try {
            
            fEscrituraLectura.writeInt( campINT ) ;
            return true ;
            
            
        }catch ( IOException e ) {             
            
            return false ;            
            
        }
                
    }
    
    public boolean actualizaCampo( int queCampo, int campINT ){
        
        try {
            
            fEscrituraLectura.seek( queCampo ) ;
            fEscrituraLectura.writeInt( campINT ) ;
            return true ;
            
            
        }catch ( IOException e ) {             
            
            return false ;            
            
        }
                
    }
    
    
}

    











 