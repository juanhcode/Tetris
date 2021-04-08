/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Conexion;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Juan Manuel
 */
public class RankingVistaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Administrador admin;
    @FXML
    private TableView<Usuario> columnaTabla;
    @FXML
    private TableColumn<Usuario, String> columnaNombre;
    @FXML
    private TableColumn<Usuario, Integer> columnaPuntaje;

    ObservableList<Usuario> lista;

    Connection conn = null;

    PreparedStatement pst = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        columnaPuntaje.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("puntaje"));

        lista = Conexion.getDatausers();
        columnaTabla.setItems(lista);
    }

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    @FXML
    private void atras() {
        admin.initRootLayout();
    }

}
