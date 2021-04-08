/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Juan Manuel
 */
public class PausaVistaController implements Initializable {

    @FXML
    private Button reanudar;
    private Administrador admin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void reanudar() throws Exception {
        admin.getPrimaryStagePausa().close();
        TetrisJuegoVistaController.repro.Continuar();
        //FALTA LA DE EMANUEL 
    }

    @FXML
    private void comoJugar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/GaleriaVista.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Ayuda");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    private void quitar() throws IOException {
        admin.getPrimaryStagePausa().close();
        admin.initRootLayout();
    }

    public void setReanudar(Button reanudar) {
        this.reanudar = reanudar;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

}
