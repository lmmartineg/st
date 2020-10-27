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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Juego {

    public static final int PUNTOS_VIDA_INICIALES = 10;
    Tablero tablero;
    Pacman pacman;
    ArrayList<Celda> visitadas;
    ArrayList<Celda> ruta;

    public Juego(String archivo) {
        try {
            Scanner in = new Scanner(new File(archivo));
            tablero = new Tablero(in);
            leerVarios(in);
            /*
            Posicion posicion = Pacman.leerPosicion(in);
            pacman = new Pacman(Caracter.PACMAN, posicion, '^', PUNTOS_VIDA_INICIALES);
            tablero.tablero[posicion.fila][posicion.col].caracter = pacman;
             */
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el archivo " + archivo + ex.getMessage());
        }
        visitadas = new ArrayList<>();
        ruta = new ArrayList<>();
    }

    private void leerVarios(Scanner in) {
        while (in.hasNextLine()) {
            String linea = in.nextLine();
            if (linea.length() == 0) {
                continue;
            }
            Scanner lineScan = new Scanner(linea.substring(1));
            if (linea.charAt(0) == 'P') {
                int fila = lineScan.nextInt();
                int col = lineScan.nextInt();
                Posicion posicion = new Posicion(fila, col);
                pacman = new Pacman(Caracter.PACMAN, posicion, '^', PUNTOS_VIDA_INICIALES);
                tablero.tablero[posicion.fila][posicion.col].caracter = pacman;
            } else if (linea.charAt(0) == 'O') {
                int fila = lineScan.nextInt();
                int col = lineScan.nextInt();
                tablero.tablero[fila][col].esSalida = true;
            }
        }
    }

    public void jugar() {
        boolean ganaElJuego = false;
        tablero.dibujarTablero();
        Scanner in = new Scanner(System.in);
        String linea = in.nextLine();
        while (!linea.equals("q") && !ganaElJuego) {
            int fila = pacman.posicion.fila;
            int col = pacman.posicion.col;
            int nuevaFila = fila;
            int nuevaCol = col;
            switch (linea) {
                case "a":
                    nuevaCol = col - 1;
                    break;
                case "d":
                    nuevaCol = col + 1;
                    break;
                case "w":
                    nuevaFila = fila - 1;
                    break;
                case "s":
                    nuevaFila = fila + 1;
                    break;
            }
            if (validarCasilla(nuevaFila, nuevaCol)) {
                Celda anterior = tablero.tablero[fila][col];
                Celda nueva = tablero.tablero[nuevaFila][nuevaCol];
                nueva.caracter = pacman;
                anterior.caracter = null;
                pacman.posicion = new Posicion(nuevaFila, nuevaCol);
                if(tablero.tablero[nuevaFila][nuevaCol].esSalida) {
                    ganaElJuego = true;
                    tablero.dibujarTablero();
                    continue;
                }
            }
            tablero.dibujarTablero();
            linea = in.nextLine();
        }
        if(ganaElJuego) {
            System.out.println("Has ganado el juego, ¡felicitaciones!");
        }
    }

    private boolean validarCasilla(int nuevaFila, int nuevaCol) {
        if (nuevaFila < 0 || nuevaFila >= tablero.numFilas) {
            return false;
        }
        if (nuevaCol < 0 || nuevaCol >= tablero.numCols) {
            return false;
        }
        if (tablero.tablero[nuevaFila][nuevaCol].esMuro) {
            return false;
        }
        if (tablero.tablero[nuevaFila][nuevaCol].caracter != null) {
            return false;
        }
        return true;
    }
    
    public void encontrarRuta() {
        int fila = pacman.posicion.fila;
        int col = pacman.posicion.col;
        boolean exito = encontrarRutaAux(fila, col);
        if(exito) {
            for(int i = ruta.size() - 1; i >= 0 ; i--) {
                ruta.get(i).enLaRuta = true;
                tablero.dibujarTablero();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        tablero.dibujarTablero();
    }
    
    public boolean encontrarRutaAux(int fila, int col) {
        boolean exito;
        // Encontramos la salida!
        if (tablero.tablero[fila][col].esSalida) return true;
        // Verificar si esta celda no se visitó ya
        Celda esta = tablero.tablero[fila][col];
        // No visitar celdas que ya se han visitado para evitar
        // ciclos infinitos
        if(visitadas.contains(esta)) {
            return false;
        } else {
            // Marcar esta celda como visitada
            visitadas.add(esta);
        }
        // izquierda
        if(validarCasilla(fila, col-1)) {
            exito = encontrarRutaAux(fila, col-1);
            if(exito) {
                //tablero.tablero[fila][col-1].enLaRuta = true;
                ruta.add(esta);
                return true;
            }
        }
        // abajo
        if(validarCasilla(fila+1, col)) {
            exito = encontrarRutaAux(fila+1, col);
            if(exito) {
                //tablero.tablero[fila+1][col].enLaRuta = true;
                ruta.add(esta);
                return true;
            }
        }
        
        // derecha
        if(validarCasilla(fila, col+1)) {
            exito = encontrarRutaAux(fila, col+1);
            if(exito) {
                //tablero.tablero[fila][col+1].enLaRuta = true;
                ruta.add(esta);
                return true;
            }
        }
        
        // arriba
        if(validarCasilla(fila-1, col)) {
            exito = encontrarRutaAux(fila-1, col);
            if(exito) {
                //tablero.tablero[fila-1][col].enLaRuta = true;
                ruta.add(esta);
                return true;
            }
        }
        
        // Por esta ruta no se llegó a la meta
        // Desmarcar esta celda
        visitadas.remove(esta);
        return false;
    }
}
