/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javafx.scene.shape.Rectangle;
import vista.TetrisJuegoVistaController;

/**
 *
 * @author cifu1
 */
public class Controlador {

    //obteniendo los numeros y la tabla
    public static final int MOVER = TetrisJuegoVistaController.MOVER;
    public static final int TAMANIO = TetrisJuegoVistaController.TAMANIO;
    public static int XMAX = TetrisJuegoVistaController.XMAX;
    public static int YMAX = TetrisJuegoVistaController.YMAX;
    public static int[][] TABLERO = TetrisJuegoVistaController.TABLERO;

    //mover las piezas
    public static void MovimientoDerecha(Forma forma) {
        if (forma.a.getX() + MOVER <= XMAX - TAMANIO && forma.b.getX() + MOVER <= XMAX - TAMANIO
                && forma.c.getX() + MOVER <= XMAX - TAMANIO && forma.d.getX() + MOVER <= XMAX - TAMANIO) {
            int moverA = TABLERO[((int) forma.a.getX() / TAMANIO) + 1][((int) forma.a.getY() / TAMANIO)];
            int moverB = TABLERO[((int) forma.b.getX() / TAMANIO) + 1][((int) forma.b.getY() / TAMANIO)];
            int moverC = TABLERO[((int) forma.c.getX() / TAMANIO) + 1][((int) forma.c.getY() / TAMANIO)];
            int moverD = TABLERO[((int) forma.d.getX() / TAMANIO) + 1][((int) forma.d.getY() / TAMANIO)];

            if (moverA == 0 && moverA == moverB && moverB == moverC && moverC == moverD) {
                forma.a.setX(forma.a.getX() + MOVER);
                forma.b.setX(forma.b.getX() + MOVER);
                forma.c.setX(forma.c.getX() + MOVER);
                forma.d.setX(forma.d.getX() + MOVER);
            }
        }
    }

    public static void MovimientoIzquierda(Forma forma) {
        if (forma.a.getX() - MOVER >= 0 && forma.b.getX() - MOVER >= 0
                && forma.c.getX() - MOVER >= 0 && forma.d.getX() - MOVER >= 0) {
            int moverA = TABLERO[((int) forma.a.getX() / TAMANIO) - 1][((int) forma.a.getY() / TAMANIO)];
            int moverB = TABLERO[((int) forma.b.getX() / TAMANIO) - 1][((int) forma.b.getY() / TAMANIO)];
            int moverC = TABLERO[((int) forma.c.getX() / TAMANIO) - 1][((int) forma.c.getY() / TAMANIO)];
            int moverD = TABLERO[((int) forma.d.getX() / TAMANIO) - 1][((int) forma.d.getY() / TAMANIO)];

            if (moverA == 0 && moverA == moverB && moverB == moverC && moverC == moverD) {
                forma.a.setX(forma.a.getX() - MOVER);
                forma.b.setX(forma.b.getX() - MOVER);
                forma.c.setX(forma.c.getX() - MOVER);
                forma.d.setX(forma.d.getX() - MOVER);
            }
        }

    }

    //creamos los bloques y sus colores
    public static Forma Creacion() {
        //colores para los bloques
        int bloque = (int) (Math.random() * 100);
        String nombre;

        //se agrega delgadas lineas a las piezas
        Rectangle a = new Rectangle(TAMANIO - 1, TAMANIO - 1),
                b = new Rectangle(TAMANIO - 1, TAMANIO - 1),
                c = new Rectangle(TAMANIO - 1, TAMANIO - 1),
                d = new Rectangle(TAMANIO - 1, TAMANIO - 1);

        //creacion de las figuras dependiendo del random
        if (bloque < 15) {
            a.setX(XMAX / 2 - TAMANIO);
            b.setX(XMAX / 2 - TAMANIO);
            b.setY(TAMANIO);
            c.setX(XMAX / 2);
            c.setY(TAMANIO);
            d.setX(XMAX / 2 + TAMANIO);
            d.setY(TAMANIO);
            nombre = "j";

        } else if (bloque < 30) {
            a.setX(XMAX / 2 + TAMANIO);
            b.setX(XMAX / 2 - TAMANIO);
            b.setY(TAMANIO);
            c.setX(XMAX / 2);
            c.setY(TAMANIO);
            d.setX(XMAX / 2 + TAMANIO);
            d.setY(TAMANIO);
            nombre = "l";

        } else if (bloque < 45) {
            a.setX(XMAX / 2 - TAMANIO);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2 - TAMANIO);
            c.setY(TAMANIO);
            d.setX(XMAX / 2);
            d.setY(TAMANIO);
            nombre = "o";

        } else if (bloque < 60) {
            a.setX(XMAX / 2 + TAMANIO);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2);
            c.setY(TAMANIO);
            d.setX(XMAX / 2 - TAMANIO);
            d.setY(TAMANIO);
            nombre = "s";
        } else if (bloque < 75) {
            a.setX(XMAX / 2 - TAMANIO);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2);
            c.setY(TAMANIO);
            d.setX(XMAX / 2 + TAMANIO);
            nombre = "t";
        } else if (bloque < 90) {
            a.setX(XMAX / 2 + TAMANIO);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2 + TAMANIO);
            c.setY(TAMANIO);
            d.setX(XMAX / 2 + TAMANIO + TAMANIO);
            d.setY(TAMANIO);
            nombre = "z";

        } else {
            a.setX(XMAX / 2 - TAMANIO - TAMANIO);
            b.setX(XMAX / 2 - TAMANIO);
            c.setX(XMAX / 2);
            d.setX(XMAX / 2 + TAMANIO);
            nombre = "i";
        }
        return new Forma(a, b, c, d, nombre);

    }

}
