
        

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Principal {
    public static void main(String [] args) {
        Juego juego = new Juego("laberinto.txt");
        //juego.jugar();
        juego.encontrarRuta();
    }
}
