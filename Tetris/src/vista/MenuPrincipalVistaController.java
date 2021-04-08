/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private TextField campoContrasenia;
    @FXML
    private TextField campoUsuario;
    private Administrador MenuPrincipal;
    private Nivel nivel;
    public static String usuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        play.setDisable(true);
    }


    //esto abre la ventana regisrar
    @FXML
    private void btregistrar() throws IOException {
        MenuPrincipal.llamarSegundaVentana();
    }

    @FXML
    private void jugar() {
        Connection conn = Conexion.connectDb();
        int resultado = 0;
        usuario = campoUsuario.getText();
        String contrasenia = campoContrasenia.getText();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM USUARIO WHERE nombre='" + usuario + "' and  password='" + contrasenia + "' ");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                resultado = 1;
                if (resultado == 1) {
                    JOptionPane.showMessageDialog(null, campoUsuario.getText() + "  Ya puedes jugar :)");
                    play.setDisable(false);
                }
                campoUsuario.setText("");
                campoContrasenia.setText("");

            } else {
                JOptionPane.showMessageDialog(null, "Error de Acceso , Ususario no registrado");
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    @FXML
    private void empezarJuego() throws InterruptedException {
        MenuPrincipal.llamarNivel();
    }

    public void setProgramaPrincipal(Administrador ProgramaPrincipal) {
        this.MenuPrincipal = ProgramaPrincipal;
    }

    @FXML
    private void ranking() {
        MenuPrincipal.llamarRanking();
    }

    @FXML
    private void salir() {
        System.exit(0);
    }
}
