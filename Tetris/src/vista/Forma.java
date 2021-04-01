/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author cifu1
 */
public class Forma {

    Rectangle a;
    Rectangle b;
    Rectangle c;
    Rectangle d;
    Color color;
    private String nombre;
    public int forma = 1;

    public Forma(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Forma(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String nombre) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.nombre = nombre;

        //cambiar el color
        switch (nombre) {
            case "j":
                color = Color.SLATEGRAY;
                break;
            case "l":
                color = Color.SLATEBLUE;
                break;
            case "o":
                color = Color.DARKBLUE;
                break;
            case "s":
                color = Color.AQUAMARINE;
                break;
            case "t":
                color = Color.BISQUE;
                break;
            case "z":
                color = Color.CORAL;
                break;
            case "i":
                color = Color.DARKGOLDENROD;
                break;
        }
        //ponemos los colores
        this.a.setFill(color);
        this.b.setFill(color);
        this.c.setFill(color);
        this.d.setFill(color);
    }

    public String getNombre() {
        return nombre;
    }

    public void cambiarForma() {
        if (forma != 4) {
            forma++;
        } else {
            forma = 1;
        }
    }

}
