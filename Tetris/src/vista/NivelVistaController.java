/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Juan Manuel
 */
public class NivelVistaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Prueba prueba;
    private Administrador admin;
    private Nivel nivel = new Nivel();
    public static int valor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void intermedio() {
        this.valor = 5;
        nivel.setNivel(5);
        if (nivel.getNivel() == 5) {
            admin.llamarJuegoTetris();
        }
    }

    @FXML
    public void dificil() {
        this.valor = 10;
        nivel.setNivel(10);
        if (nivel.getNivel() == 10) {
            admin.llamarJuegoTetris();
        }

    }

    @FXML
    public void basico() {
        this.valor = 1;
        nivel.setNivel(1);
        if (nivel.getNivel() == 1) {
            admin.llamarJuegoTetris();
        }
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

}
