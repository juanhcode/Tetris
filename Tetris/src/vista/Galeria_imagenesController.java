package vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Galeria_imagenesController implements Initializable {

    private int contador = 0;
    private Label labelimagen;
    private final Image imagenes[] = new Image[6];
    @FXML
    private ImageView campoImagen;
    @FXML
    private Button botonAnterior;
    @FXML
    private Button botonSiguiente;
    @FXML
    private Button botonVolverPrimera;
    @FXML
    private Button botonUltimaImagen;
    
    private Administrador admin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < 3; i++) {
            imagenes[i] = new Image("/recursos/a" + i + ".jpg");
        }
        campoImagen.setImage(imagenes[0]);
        //   campoLabel.setText("a" + 0 + ".jpg");
    }

    @FXML
    private void Anterior() {
        if (contador == 0) {
            contador = 1;
            botonAnterior.setDisable(true);
        } else {
            botonSiguiente.setDisable(false);
            contador--;
            campoImagen.setImage(imagenes[contador]);
            // campoLabel.setText("a" + contador + ".jpg");
        }
    }

    @FXML
    private void siguiente() {
        if (contador == 2) {
            contador = 3;
            botonSiguiente.setDisable(true);
        } else {
            botonAnterior.setDisable(false);
            contador++;
            campoImagen.setImage(imagenes[contador]);
            //campoLabel.setText("a" + contador + ".jpg");
        }
    }

    @FXML
    private void volverPrimeraImagen() {
        contador = 0;
        int primerImagen = 0;
        botonAnterior.setDisable(true);
        botonSiguiente.setDisable(false);
        campoImagen.setImage(imagenes[primerImagen]);
        // campoLabel.setText("a" + 0 + ".jpg");
    }

    @FXML
    private void irUltimaImagen() {
        contador = 2;
        int ultimaImagen = 2;
        botonSiguiente.setDisable(true);
        botonAnterior.setDisable(false);
        campoImagen.setImage(imagenes[ultimaImagen]);
        //  campoLabel.setText("a" + ultimaImagen + ".jpg");
    }


    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }
    
    

}
