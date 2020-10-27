
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class Pacman extends Caracter {

    int puntosVida;

    public Pacman(int tipo, Posicion posicion, char representacion, int puntosVida) {
        super(tipo, posicion, representacion);
        this.puntosVida = puntosVida;
    }

    /*
    public static Posicion leerPosicion(Scanner in) {
        int fila = 0;
        int col = 0;
        if (in.hasNextLine()) {
            String linea = in.nextLine();
            Scanner lineScan = new Scanner(linea);
            if (lineScan.next().equals("P")) {
                fila = lineScan.nextInt();
                col = lineScan.nextInt();
            }
        }
        return new Posicion(fila, col);
    }
    */

}
