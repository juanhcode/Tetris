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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Juan Manuel
 */
public class TetrisJuegoVistaController implements Initializable {

    @FXML
    private Label tablero;
    @FXML
    private AnchorPane puntuacion;
    private Administrador menuTetris;
    @FXML
    private Button pausar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void setProgramaTetris (Administrador ProgramaPrincipal) {
        this.menuTetris = ProgramaPrincipal;
    }
    
}
