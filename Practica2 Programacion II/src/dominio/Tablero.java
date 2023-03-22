package dominio;
import aplicacion.*;
import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class Tablero{
    private static int DIMENSION = 30;
    public int[][] estadoActual = new int[DIMENSION + 2][DIMENSION + 2]; // esta matriz representa el estado actual.
    public int[][] estadoSiguiente = new int[DIMENSION + 2][DIMENSION + 2]; // esta matriz representa el estado siguente

    public static int numeroRandom(){
        Random random = new Random();
        int numeroRandom = random.nextInt(10);
        if(numeroRandom < 4){
            numeroRandom = 0;
        }
        else{
            numeroRandom = 1;
        }
        return numeroRandom;
    }
    public void leerEstadoActual(){
        String mensajeLeido = "";
        try{
              // El FileReader nos permite leer el archivo.
           FileReader reader = new FileReader("/Users/giuliotizzano/Desktop/Practica2 Programacion II/src/aplicacion/FicheroMatriz.txt");
           // El BufferedReader nos permite generar una variable que nos permita guardar la lectura hecha.
           // Es decir, sirve para el almacenamiento de la lectura.
           BufferedReader lectorBuffer = new BufferedReader(reader);
           for(int i = 1; i <= DIMENSION; i++){
            for(int j = 1; j <= DIMENSION; j++){
                // Operador ternario: resultado = (condicion)? valor1: valor2;
                // Así funciona el operador ternario en Java como se muestra arriba.
                estadoActual[i][j] = lectorBuffer.read() == '0'?0:1;
            }
            lectorBuffer.read();
           }
           lectorBuffer.close();    
        } catch(Exception ex ){
            ex.printStackTrace();
        }
    }

    public void generarEstadoActualPorMonteCarlo(){
        for(int i = 0; i < DIMENSION + 2; i++){
            estadoActual[i][0] = 0;
                estadoActual[i][DIMENSION + 1] = 0;
                estadoActual[0][i] = 0; 
                estadoActual[DIMENSION][0] = 0;
        }
        
        for(int i = 1; i <= DIMENSION; i++){
            for(int j = 1; j <= DIMENSION; j++){
                int n = numeroRandom();
                estadoActual[i][j] = n;
                // System.out.print(estadoActual[i][j] + " ");
            }
        }
        // System.out.println();
    }
    public String toString(){
       String s = "";
       for(int i = 0; i <= DIMENSION; i++){
        for(int j = 0; j <= DIMENSION; j++){
            if(estadoActual[i][j] == 1){
                s += "1";
            } else{
                s += "0";
            }
           }
           s += "\n";
        }

    return s;
    }
    // En este método se tienen que aplicar las diferentes reglas del juego de la vida.
    public void transitarAlEstadoSiguiente(){
        int count = 0;
        for(int i = 1; i <= DIMENSION; i++){
            for(int j = 1; j <= DIMENSION; j++){
                count = 0;
                // estado actual
                int e = estadoActual[i][j];
                // posible # de vecinos
                int v = estadoSiguiente[i][j + 1] + estadoSiguiente[i][j - 1] + estadoSiguiente[i - 1][j] + estadoSiguiente[i + 1][j]
                 + estadoSiguiente[i + 1][j + 1] + estadoSiguiente[i + 1][j - 1] + estadoSiguiente[i - 1][j + 1] + estadoSiguiente[i - 1][j - 1];
                 // nuevo estado
                // regla a)
                if(e == 1 && (v == 2 || v == 3)){
                    estadoSiguiente[i][j] = 1;
                // regla b)
                } else if(e == 0 && v == 3){
                    estadoSiguiente[i][j] = 1;
                // regla c)
                } else{
                    estadoSiguiente[i][j] = 0;
                }

            }
        }
    }
}

