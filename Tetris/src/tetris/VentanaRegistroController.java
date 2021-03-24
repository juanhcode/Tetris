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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class VentanaRegistroController implements Initializable {

    @FXML
    private Button botonRegistrar;
    @FXML
    private TextField IngresarUsuario;
    @FXML
    private TextField agergarContraseña;
    @FXML
    private TextField repetirContraseña;
    @FXML
    private ImageView imagenRegistro;
    @FXML
    private ImageView imagentetris;
    @FXML
    private Button botonAtras;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //imagenes de la interfaz
        Image ima = new Image("/recursos/imagenregistro.jpg");
        Image titulo = new Image("/recursos/Titulo.png");
        imagenRegistro.setImage(ima);
        imagentetris.setImage(titulo);
        
        
    }
// esto cierra la ventana 
    @FXML
    private void btatras(ActionEvent event) {
        Stage stage = (Stage) this.botonAtras.getScene().getWindow();
        stage.close();
    }


}
