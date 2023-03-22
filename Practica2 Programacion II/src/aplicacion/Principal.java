package aplicacion;
import dominio.Tablero;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;
import java.io.*;

public class Principal{
    public static void main(String[] args){
        try{
            Tablero tablero = new Tablero();
            System.out.println("SIUMLACION CON TABLERO LEÍDO");
            tablero.leerEstadoActual();
            System.out.println(tablero);
            tablero.estadoSiguiente = tablero.estadoActual;
            for(int i = 0; i <= 5; i++){
                TimeUnit.SECONDS.sleep(1);
                tablero.transitarAlEstadoSiguiente();
                System.out.println(tablero);
            }
            System.out.println("SIMULACION CON TABLERO GENERADO MEDIANTE MONTECARLO");
            tablero.generarEstadoActualPorMonteCarlo();
            System.out.println(tablero);
            tablero.estadoSiguiente = tablero.estadoActual;
            for(int i = 0; i <= 15; i++){
                TimeUnit.SECONDS.sleep(1);
                tablero.transitarAlEstadoSiguiente();
                System.out.println(tablero);

            }
        } catch(InterruptedException e){
            System.out.println(e);
        }

    }
}
