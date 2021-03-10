/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MenuPrincipalVistaController implements Initializable {

    @FXML
    private AnchorPane AnchorPaneFondo;
    @FXML
    private Button botonModoOscuro;
    @FXML
    private Button play;
    @FXML
    private Button botonModoOscuro1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modoOscuro(ActionEvent event) {
        
    }
    
}
