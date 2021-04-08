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
public class destruido {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Reproductor mi_reproductor = new Reproductor();
            mi_reproductor.AbrirFichero("c:/mi_archivo_de_musica.mp3");
            mi_reproductor.Play();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
