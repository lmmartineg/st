/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Celda {
    boolean esMuro;
    boolean esSalida;
    boolean tieneArepita;
    Caracter caracter;

    public Celda(boolean esMuro, boolean esSalida, boolean tieneArepita, Caracter caracter) {
        this.esMuro = esMuro;
        this.esSalida = esSalida;
        this.tieneArepita = tieneArepita;
        this.caracter = caracter;
    }
    
    public char caracterCelda() {
        if(esMuro) {
            return '*';
        } else if(tieneArepita) {
            return '.';
        } else if(caracter != null) {
            return caracter.representacion;
        } else if (esSalida) {
            return 'O';
        } else {
            return ' ';
        }
    }
    
}
