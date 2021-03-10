package tetris;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Administrador extends Application {

    private Stage primaryStage;
    private BorderPane rootLaoyut; //Es el borderPane

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setTitle("Tetris"); //Titulo
        initRootLayout();
    }
    
    public void initRootLayout() {

        try {

            //Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Administrador.class.getResource("/tetris/MenuPrincipalVista.fxml"));
            rootLaoyut = (BorderPane) loader.load();
            
            //Crear la escena
            Scene scene = new Scene(rootLaoyut);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            //creamos el controlador de la ventana
            //enviamos el administrador Principal a la ventana abierta
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Error al cargar archivo externo");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
