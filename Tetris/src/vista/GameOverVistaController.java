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
public class GameOverVistaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Administrador admin;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void home() {
        admin.initRootLayout();
    }

    @FXML
    private void cargarDeNuevo() {
        admin.llamarJuegoTetris();
    }

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }
    
    
    
    
}
