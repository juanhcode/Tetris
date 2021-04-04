/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import modelo.Conexion;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class VentanaRegistroController implements Initializable {

    @FXML
    private Button botonRegistrar;
    @FXML
    private ImageView imagenRegistro;
    @FXML
    private ImageView imagentetris;
    @FXML
    private Button botonAtras;
    @FXML
    private TextField campoUsuario;
    @FXML
    private TextField campoContraseña;
    @FXML
    private TextField campoVerificacionContraseña;
    private Administrador MenuRegistrar;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

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
    private void btatras() {
        MenuRegistrar.initRootLayout();
    }

    @FXML
    private void registrar() {

        String usuario = campoUsuario.getText();
        String contrasenia = campoContraseña.getText();
        String segundaContrasenia = campoVerificacionContraseña.getText();

        if (campoUsuario.getText().equals("") && campoContraseña.getText().equals("") && campoVerificacionContraseña.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingresar datos validos");
        } else if (contrasenia.equals(segundaContrasenia)) {
            conn = Conexion.connectDb();
            String sql = "insert into usuario (nombre,password)values(?,?)";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, campoUsuario.getText());
                pst.setString(2, campoContraseña.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Usuario añadido");
                campoUsuario.setText("");
                campoContraseña.setText("");
                campoVerificacionContraseña.setText("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    public void setProgramaRegistro(Administrador ProgramaPrincipal) {
        this.MenuRegistrar = ProgramaPrincipal;
    }

}
