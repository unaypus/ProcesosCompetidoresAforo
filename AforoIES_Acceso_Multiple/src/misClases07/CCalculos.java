/*
 * 
 */
package misClases07;

import java.text.DecimalFormat;

/**
 *
 * @author juaponabr
 * 
 *   Alumno: Juan Pons Abraham
 *   Para:   Curso 1º DAM Tarde/Noche grupo A
 * 
 * funciones que realizan calculos con los datos introducidos por el usuario
 * 
 */

public class CCalculos {
    
    private String sNombre;
    private DecimalFormat fE = new DecimalFormat("########.##");
    
    /**
     * CCalculos
     * 
     * Constructor de la clase
     * se le asigna un nombre al instanciarlo con la instruccion new
     * 
     * De momento el que tenga un nombre no es relevante, pero se usa
     * para compatibilidad de futuras versiones en las que si fuera
     * necesario su uso
     * 
     * @param cNombre el nombre
     */
    public CCalculos( String cNombre){        
        sNombre = cNombre;
    }
    
    ////////////////////////////////////////////////////
    // Funciones de calculo
    ////////////////////////////////////////////////////
    /**
     * ponerParentesis
     * 
     * en algunas formulas es necesario representar 
     * los números negativos entre parentesis
     * esta función nos ayuda a ello
     * 
     * 
     * @param unnumero double a tratar
     * @return representación en parentesis
     */
    public String ponerParentesis(double unnumero){        
        
        String sNumero;
        
        // convertmos el número a texto
        sNumero = fE.format(unnumero);
        
        // y si es negativo le añadimos los parentesis
        if (unnumero<0) {
            sNumero = "("+sNumero+")";
	}
        
        return sNumero;
    }  
    
    /**
     * esImpar
     * 
     * determina con verdadero si un número dado es impar
     * si devuelve falso es que es par
     * 
     * @param unnumero double a tratar
     * @return true/false
     */
    public boolean esImpar(double unnumero) {      
                
        // por defecto suponemos que es impar
        boolean ssalida = true;
	
        // en caso de ser divisible por 2 es par
        if (unnumero%2==0) {
            ssalida = false;
	}
        
	return ssalida;
    }    
    
    /**
     * hacerCalculos
     * 
     * Aquí se realizan varias operaciones matemáticas que se 
     * pueden practicar sobre uno o dos números dados
     * 
     * 
     * 
     * @param quecalculo    Dichos números se entregan en el Array de 2 posiciones 
     * @param unnumero      y la operación a realizar viene dada por el entero 
     * @return              devuelve el número conseguido
     */
    public String hacerCalculos(int quecalculo, double unnumero[]) {       
        
        // cadena de salida con el resultado 
        String noptenido="";
        
        double elresto;
        String sNumero[];
        sNumero = new String[2];
        
        // poner parentesis a los negativos para su representación
        sNumero [0] = ponerParentesis(unnumero[0]);
        sNumero [1] = ponerParentesis(unnumero[1]);        
        
        // según el valor de "quecalculo"
        // se realiza una u otra operación
        // y se formatea la cadena de salida
	switch (quecalculo) {
        
        // suma
	case 1:
            
                noptenido = sNumero [0]+" + "+ 
                            sNumero [1]+" = "+
		            fE.format(unnumero[0]+unnumero[1]);
		break;
                
	// resta
        case 2:
		noptenido = sNumero [0]+" - "+ 
                            sNumero [1]+" = "+
                            fE.format(unnumero[0]-unnumero[1]);
		break;
                
        // multiplicación
	case 3:
		noptenido = sNumero [0]+" * "+ 
                            sNumero [1]+" = "+
                            fE.format(unnumero[0]*unnumero[1]);
		break;
                
        // división        
	case 4:
                noptenido = sNumero [0]+" / "+ 
                            sNumero [1]+" = ";
		if (unnumero[0]==0 || unnumero[1]==0) {
			noptenido = noptenido+"Error no se puede dividir por 0";
		} else {
			noptenido = noptenido+
                                    fE.format(unnumero[0]/unnumero[1]);
                }
		break;
                
        // potencia de 2
        case 5:  
		noptenido = sNumero [0]+"^2      = "+
                            fE.format(Math.pow(unnumero[0],2));
		break;
                
        // raiz cuadrada
        case 6:
                if (unnumero[1]<0) {
			noptenido = noptenido+"Error el número es menor que 0";
		} else {
			noptenido = "√"+ 
                                    sNumero [1]+"       = "+
                                    fE.format(Math.sqrt(unnumero[1]));
                }		
		break;
        
                
        // hallar el múltiplo
        case 8:
                noptenido = "                           es : ";
                if (unnumero[0]==0 || unnumero[1]==0) {
			noptenido = noptenido+"Error no se puede dividir por 0";
		} else {
                    elresto = unnumero[0]%unnumero[1];
                    //noptenido = noptenido+fE.format(Double.toString(elresto));
                    noptenido = noptenido+fE.format(elresto);
                    noptenido = noptenido+" el primer número ";
                    if (elresto==0) {
                        noptenido = noptenido+"SI";
                    } else {
                        noptenido = noptenido+"NO";
                    }
                    noptenido = noptenido+" es múltiplo del segundo ";
                }
                break;
                
        }
        
        // salida de la cadena ya formateada
	return noptenido;
        
    } 
    
    /**
     * hacerCifras
     * 
     * Función que introduce un espacio detras de cada uno 
     * de los caracteres que componen la cadena de las cifras 
     * individuales del número recibido
     * 
     * @param unnumero matriz de double
     * @return la cadena de salida
     */
    public String hacerCifras(double unnumero[]){
        
        String sSalida;
        String sAux;
        
        sSalida = "";
        
        // convertimos el número a una cadena de texto
        sAux =fE.format(unnumero[0]);
        
        // recorremos la cadena introduciendo un espacio entre cada caracter
        for (int ncontador=0;ncontador<sAux.length();ncontador++) {
            sSalida = sSalida+sAux.charAt(ncontador)+" ";
        }
        
        return sSalida;
        
    }    
    
    /**
     * compCadena
     * 
     * Función que comprueba si contiene errores  en
     * 
     *     scadena
     * 
     *     a - Obligado 5 caracteres
     *     b - solo números y sin signo ni separadores
     * 
     * Consideramos si se encuentran errores de formato como
     *     VERADERO true
     * si no se sale como 
     *     FALSO false
     * 
     * @param scadena   la cadena recibida
     * @return          verdadero falso
     */
    public boolean compCadena(String scadena) {
        
        String sAux =" ";
        
        // solo admitimos cadenas de 5 caracteres
        if ( scadena.length() !=5) {
          return true;
	}
        
        // recorremos la cadena por si hay caracteres no válidos
	for (int naqui=0;naqui<=4;naqui++) {
        
            sAux = scadena.substring(naqui,naqui+1);
            
            // si encontramos un caracter no valido salimos
            if (sAux.equals("-") || 
		sAux.equals(".") || 
		sAux.equals(",") || 
		sAux.equals("+")) {
                return true;
            }
            
	}
        
        // si llegamos hasta aquí
        // salimos sin errores
        return false;
        
    }
    
    /**
     * compruebaNumero
     * 
     * Esta función comprueba el valor del número recibido en
     * 
     *    unnumero
     * 
     * valores posibles
     * 
     *     mayor de 0
     *     menor de 0
     *     igual a  0
     * 
     * y devuelve una cadena con el resultado
     *
     * @param unnumero un double
     * @return cadena positivo negativo nulo
     */
    public String compruebaNumero(double unnumero) {       
       
        
	String sposnegint;
              
	if (unnumero!=0) {
            
            if (unnumero>0) {
		sposnegint = "es POSITIVO";
            } else {
		sposnegint = "es NEGATIVO";
            }
            
	} else {
            
            sposnegint = "es NULO";
            
	}
        
	return sposnegint;
    }  
    
    
    /**
     * hallarArea
     * 
     * Metodo para hallar el area de una circunferencia
     * según el radio recibido en
     * 
     *     nRadio
     * 
     * usamos la constante PI de la clase Math de java
     * 
     * @param nRadio    radio recibido
     * @return el area
     */
    public double hallarArea(double nRadio) {
        
	return (nRadio*nRadio)*Math.PI;
    }
    
    /**
     * hallarLongitud
     * 
     * Metodo para hallar la longitud de una circunferencia
     * según el radio recibido en
     * 
     *     nRadio
     * 
     * usamos la constante PI de la clase Math de java
     * 
     * @param nRadio radio recibido
     * @return la longitud
     */
    public double hallarLongitud(double nRadio) {
        
        return Math.PI*(2*nRadio);
    }
    
    /**
     * numero_mayor
     * 
     * esta función nos devuelve una cadena con la información
     * del número mayor del Array recibido y la posición que 
     * ocupa este en el mismo
     * 
     * @param nmatriz matriz de double
     * @return la salida formateada
     */
    public String numero_mayor(double nmatriz[]) {
        
	double elMayor;
	int ncontador;
	int laPosicion;
	String ssalida;
        
        // iniciamos las variables
        // asumiendo que en la primera posición está el mayor
        // ncontador es la variable que nos da control sobre 
        // en que posición del array nos encontramos
        ncontador = 1;
	elMayor = nmatriz[0];        
	laPosicion = ncontador;
	
        // recorremos todo el array
        for(double elemento:nmatriz){
            
            // si encontramos un número mayor
            // actualizamos las variables
            if (elemento>elMayor) {
		elMayor = elemento;
		laPosicion=ncontador;
            }
            
            // avanzamos el contador de posiciones
            ncontador++;
            
        }
	
        // formateamos la cadena de salida
	ssalida = "El número mayor es : " + fE.format(elMayor) +
                  " y ocupa la " + fE.format(laPosicion) + "ª posición";
	
        // y la devolvemos
        return ssalida;
    }
    
    /**
     * sumaSucesiva
     * 
     * suma el valor de la variable elNumero
     * a la variable nSalida
     * tantas veces como sea el valor
     * de la variable lasVeces
     * 
     * @param elNumero valor a calcular
     * @param lasVeces las n veces
     * @return el resultado
     */
    public double sumaSucesiva(double elNumero, double lasVeces) {
        
        int nContador;
	double nSalida;
        
	nSalida = 0;
        
        // Si el número de veces es negativo invertimos los signos
        if(lasVeces<0){
            
            lasVeces = lasVeces*(-1);
            elNumero = elNumero*(-1);
            
        }
        
	// Sumamos a un número dado su propio valor
	// un determinado número de veces 
        // tantas veces como sea el valor de lasVeces
	for (nContador=1;nContador<=lasVeces;nContador++) {
            
            // suma el valor de elNumero a nSalida
            nSalida = nSalida + elNumero;
            
	}
        
	return nSalida;
    }
    
    // //////////////////////////////////////////////////
    // Fin Funciones de calculo
    // //////////////////////////////////////////////////
    
}
