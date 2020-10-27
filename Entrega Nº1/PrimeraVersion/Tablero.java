/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
import java.util.Scanner;

public class Tablero {

    Celda[][] tablero;
    int numFilas;
    int numCols;

    public Tablero(Scanner in) {
        String linea = in.nextLine();
        Scanner lineScan = new Scanner(linea);
        numFilas = lineScan.nextInt();
        numCols = lineScan.nextInt();
        tablero = new Celda[numFilas][numCols];
        for (int fila = 0; fila < numFilas; fila++) {
            linea = in.nextLine();
            for (int col = 0; col < numCols; col++) {
                char c = linea.charAt(col);
                // esMuro, esSalida, tienearepita, caracter
                if (c == '*') {
                    tablero[fila][col] = new Celda(true, false, false, null);
                }
                if (c == ' ') {
                    tablero[fila][col] = new Celda(false, false, false, null);
                }
            }
        }
    }
    
    public void dibujarTablero() {
        String s = "";
        for(int fila = 0; fila < numFilas; fila++) {
            for(int col = 0; col < numCols; col++) {
                s += tablero[fila][col].caracterCelda();
            }
            s += "\n";
        }
        System.out.println(s);
    }

}
