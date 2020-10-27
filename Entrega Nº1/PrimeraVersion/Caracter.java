/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Caracter {
    public static final int PACMAN = 1;
    public static final int FANTASMA = 2;
    
    int tipo;
    Posicion posicion;
    char representacion;

    public Caracter(int tipo, Posicion posicion, char representacion) {
        this.tipo = tipo;
        this.posicion = posicion;
        this.representacion = representacion;
    }
    
}
