/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modelo.Conexion;

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
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private TextField campoContrasenia;
    @FXML
    private TextField campoUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void modoOscuro() {

    }

    //esto abre la ventana regisrar
    @FXML
    private void btregistrar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tetris/VentanaRegistro.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Ayuda");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    private void jugar() {
        Connection conn = Conexion.connectDb();
        int resultado = 0;
        String usuario = campoUsuario.getText();
        String contrasenia = campoContrasenia.getText(); 
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM USUARIO WHERE nombre='"+ usuario+"' and  password='"+ contrasenia+"' ");
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                resultado = 1;
                if(resultado==1){
                    System.out.println("Hola");
                    JOptionPane.showMessageDialog(null, campoUsuario.getText() +"  Ya puedes jugar :)" );
                    play.setDisable(true);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Error de Acceso , Ususario no registrado");
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    @FXML
    private void empezarJuego() {
        System.out.println("Perra");
    }
}
