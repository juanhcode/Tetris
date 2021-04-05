package vista;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Administrador extends Application {

    private Stage primaryStage;
    private Stage primaryStagePausa;
    public  static Scene scenePausa;
    private BorderPane rootLaoyut; //Es el borderPane
    public  static Scene sceneT;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStagePausa = new Stage();
        primaryStage.setTitle("Tetris"); //Titulo
        initRootLayout();
    }

    public void initRootLayout() {

        try {

            //Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Administrador.class.getResource("/vista/MenuPrincipalVista.fxml"));
            rootLaoyut = (BorderPane) loader.load();

            //Crear la escena
            Scene scene = new Scene(rootLaoyut);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            //creamos el controlador de la ventana
            //enviamos el administrador Principal a la ventana abierta
            MenuPrincipalVistaController ventanaAbierta = loader.getController();
            ventanaAbierta.setProgramaPrincipal(this);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Error al cargar archivo externo");

        }
    }

    public void llamarSegundaVentana() {

        try {

            //Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Administrador.class.getResource("/vista/VentanaRegistro.fxml"));
            rootLaoyut = (BorderPane) loader.load();

            //Crear la escena
            Scene scene = new Scene(rootLaoyut);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            //creamos el controlador de la ventana
            VentanaRegistroController ventanaAbierta = loader.getController();
            ventanaAbierta.setProgramaRegistro(this);

            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Error al cargar archivo externo");
        }
    }

    public void llamarJuegoTetris() {

        try {

            //Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Administrador.class.getResource("/vista/TetrisJuegoVista.fxml"));
            rootLaoyut = (BorderPane) loader.load();

            //Crear la escena
            sceneT = new Scene(rootLaoyut);
            primaryStage.setScene(sceneT);
            primaryStage.setResizable(false);

            //creamos el controlador de la ventana
            //Tetris ventanaAbierta = loader.getController();
            //ventanaAbierta.setProgramaTetris(this);
            TetrisJuegoVistaController controlador = loader.getController();
            controlador.setAdmin(this);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Error al cargar archivo externo");
        }
    }
    
    public void abrirVentanaPausa() {

        try {

            //Para cargar el archivo fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Administrador.class.getResource("/vista/PausaVista.fxml"));
            rootLaoyut = (BorderPane) loader.load();

            //Crear la escena
            scenePausa = new Scene(rootLaoyut);
            primaryStagePausa.setScene(scenePausa);
            primaryStagePausa.setResizable(false);

            PausaVistaController controlador = loader.getController();
            controlador.setAdmin(this);
            primaryStagePausa.show();

        } catch (IOException ex) {
            System.out.println("Error al cargar archivo externo");
        }
    }
    

    public Scene getSceneT() {
        return sceneT;
    }

    public void setSceneT(Scene sceneT) {
        this.sceneT = sceneT;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStagePausa() {
        return primaryStagePausa;
    }
    
    

}
