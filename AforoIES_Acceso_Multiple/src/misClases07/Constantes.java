/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misClases07;

/**
 *
 * @author juaponabr
 */
public class Constantes {
    
    // Archivo de texto con el registro secuencial de entradas y salidas
    public static final int         LONG_LINEA      = 111                   ;    
    public static final String      S_ARCHIVO_F     = "RegistroAforo.txt"   ;
    public static final String      S_ARCHIVO_NF    = "RegistroAforo"       ;
    public static final String[]    NOM_CAMPOS      = { "linea"     }       ;
    public static final int[]       LONG_CAMPOS     = { LONG_LINEA  }       ;    
    public static final int         I_N_CAMPOS      = 1                     ;
    public static final String      S_SEPARADOR     = ";"                   ;
    //
    public static final String S_FICHERO_F      = "numPersonas.txt"     ;
    public static final String S_FICHERO_NF     = "numPersonas"         ;
    public static final int     I_F_N_CAMPOS    = 5                     ;
    public static final int     N_MAX_REGISTROS = 1                     ;
    public static final int     REGISTRO_INI[]  = { 0, 0, 0, 0, 0   }   ;
    public static final String  LOS_CAMPOS[]    = { "Aforo"     ,
                                                    "Entradas"  ,
                                                    "Salidas"   ,
                                                    "Estado"    ,
                                                    "Accesos"       }   ;
    
    /**
     * 
     * tamaño en disco de cada registro 
     * 4+10+4+4+2+40+8 = 72
     * + 2 saltos de fila por campo String (10)= 82
     * 
     * int = 4 bytes * 3 campos = 12
     */
    public static final int TAM_REGISTRO = 16 ;
    
}
