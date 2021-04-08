/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author Juan Manuel
 */
public class Reproductor {

    public BasicPlayer player;

    public Reproductor() {
        player = new BasicPlayer();
    }

    public void coge(String y) {

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

    public void reproducemp3() throws Exception {
        try {
            Reproductor mi_reproductor = new Reproductor();
            mi_reproductor.AbrirFichero("C:/Users/Public/Music/Sample Music/Kalimba.mp3");
            mi_reproductor.Play();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void main(String args[]) throws Exception {
        Reproductor y = new Reproductor();
        y.AbrirFichero("C:/Users/Public/Music/Sample Music/Kalimba.mp3");
        y.Play();
    }
}
