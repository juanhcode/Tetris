/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.File;
import javazoom.jlgui.basicplayer.BasicPlayer;

/**
 *
 * @author Juan Manuel
 */
public class Reproductor {

    public BasicPlayer player;

    public Reproductor() {
        player = new BasicPlayer();
    }

    public void Play() throws Exception {
        player.play();
    }

    public void AbrirFichero(String ruta) throws Exception {
        player.open(new File(ruta));
    }

    public void Pausa() throws Exception {
        player.pause();
    }

    public void Continuar() throws Exception {
        player.resume();
    }

    public void Stop() throws Exception {
        player.stop();
    }

    public static void main(String args[]) throws Exception {
        Reproductor y = new Reproductor();
        y.AbrirFichero("C:/Users/Juan Manuel/OneDrive - correounivalle.edu.co/Escritorio/Juan/Personal/UNIVERSIDAD/UNIVALLE/SEMESTRE 4/Programacion Interactiva/ProyectoTetris/Tetris/Tetris/src/recursos/SmoothCriminal.mp3");
        y.Play();
    }
}
