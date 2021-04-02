package vista;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Juan Manuel
 */
public class TetrisJuegoVistaController implements Initializable {

    public static final int MOVER = 25; //los pixeles que se va a mover la pieza
    public static final int TAMANIO = 25; //tamanio de los pixeles en la pieza 
    public static int XMAX = TAMANIO * 12; // ancho de la tabla
    public static int YMAX = TAMANIO * 24; // altura de la tabla
    public static int[][] TABLERO = new int[XMAX / TAMANIO][YMAX / TAMANIO]; //divide todos los pixeles individualmente del tablero
    private static Forma objeto;
    public static int puntaje = 0;
    public static int arriba = 0;
    private static boolean juego = true;
    private static Forma siguienteObj = Controlador.Creacion();
    private static int noLineas = 0;

    @FXML
    private Text nivel;
    @FXML
    private Text lineas;
    @FXML
    private Pane nextBloque;

    @FXML
    private Pane paneles;
    @FXML
    private Text puntajeTexto;
    @FXML
    private Label vida;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void iniciar() {
        for (int[] a : TABLERO) {
            Arrays.fill(a, 0);
        }

        Forma a = siguienteObj;
        paneles.getChildren().addAll(a.a, a.b, a.c, a.d);
        moveOnKeyPress(a);
        objeto = a;
        siguienteObj = Controlador.Creacion();

        //Temporizador
        Timer caida = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if (objeto.a.getY() == 0 || objeto.b.getY() == 0 || objeto.c.getY() == 0 || objeto.d.getY() == 0) {
                            arriba++;
                        } else {
                            arriba = 0;
                        }

                        if (arriba == 2) {
                            // se termina el juego
                            int vidas = Integer.parseInt(vida.getText());
                            vidas--;
                            if (vidas == 2) {
                                vida.setText(vidas + "");
                                paneles.getChildren().clear();
                                iniciar();
                            } else if (vidas == 1) {
                                vida.setText(vidas + "");
                                paneles.getChildren().clear();
                                iniciar();
                            }
                            if (vidas == 0) {
                                System.out.println("Hola");
                            }
                            //juego = false;
                        }
                        // Exit
                        if (juego) {
                            Abajo(objeto);
                            puntajeTexto.setText(Integer.toString(puntaje));
                            lineas.setText(Integer.toString(noLineas));
                        }
                    }
                });
            }
        };
        caida.schedule(task, 0, 300);
    }

    private void moveOnKeyPress(Forma forma) {
        Prueba.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case D:
                        Controlador.MovimientoDerecha(forma);
                        break;
                    case S:
                        Abajo(forma);
                        puntaje++;
                        break;
                    case A:
                        Controlador.MovimientoIzquierda(forma);
                        break;
                    case W:
                        vuelta(forma);
                        break;
                }
            }
        });
    }

    private boolean comprobando(Rectangle rect, int x, int y) {
        boolean xb = false;
        boolean yb = false;
        if (x >= 0) {
            xb = rect.getX() + x * MOVER <= XMAX - TAMANIO;
        }
        if (x < 0) {
            xb = rect.getX() + x * MOVER >= 0;
        }
        if (y >= 0) {
            yb = rect.getY() - y * MOVER > 0;
        }
        if (y < 0) {
            yb = rect.getY() + y * MOVER < YMAX;
        }
        return xb && yb && TABLERO[((int) rect.getX() / TAMANIO) + x][((int) rect.getY() / TAMANIO) - y] == 0;

    }

    private void Abajo(Rectangle rect) {
        if (rect.getY() + MOVER < YMAX) {
            rect.setY(rect.getY() + MOVER);
        }
    }

    private void Arriba(Rectangle rect) {
        if (rect.getY() - MOVER > 0) {
            rect.setY(rect.getY() - MOVER);
        }
    }

    private void Izquierda(Rectangle rect) {
        if (rect.getX() - MOVER >= 0) {
            rect.setX(rect.getX() - MOVER);
        }
    }

    private void Derecha(Rectangle rect) {
        if (rect.getX() + MOVER <= XMAX - TAMANIO) {
            rect.setX(rect.getX() + MOVER);
        }
    }

    private boolean moverA(Forma forma) {
        return (TABLERO[(int) forma.a.getX() / TAMANIO][((int) forma.a.getY() / TAMANIO) + 1] == 1);
    }

    private boolean moverB(Forma forma) {
        return (TABLERO[(int) forma.b.getX() / TAMANIO][((int) forma.b.getY() / TAMANIO) + 1] == 1);
    }

    private boolean moverC(Forma forma) {
        return (TABLERO[(int) forma.c.getX() / TAMANIO][((int) forma.c.getY() / TAMANIO) + 1] == 1);
    }

    private boolean moverD(Forma forma) {
        return (TABLERO[(int) forma.d.getX() / TAMANIO][((int) forma.d.getY() / TAMANIO) + 1] == 1);
    }

    private void Abajo(Forma forma) {
        if (forma.a.getY() == YMAX - TAMANIO || forma.b.getY() == YMAX - TAMANIO || forma.c.getY() == YMAX - TAMANIO
                || forma.d.getY() == YMAX - TAMANIO || moverA(forma) || moverB(forma) || moverC(forma) || moverD(forma)) {
            TABLERO[(int) forma.a.getX() / TAMANIO][(int) forma.a.getY() / TAMANIO] = 1;
            TABLERO[(int) forma.b.getX() / TAMANIO][(int) forma.b.getY() / TAMANIO] = 1;
            TABLERO[(int) forma.c.getX() / TAMANIO][(int) forma.c.getY() / TAMANIO] = 1;
            TABLERO[(int) forma.d.getX() / TAMANIO][(int) forma.d.getY() / TAMANIO] = 1;
            RemoverLineas(paneles);

            Forma a = siguienteObj;
            siguienteObj = Controlador.Creacion();
            objeto = a;
            paneles.getChildren().addAll(a.a, a.b, a.c, a.d);
            moveOnKeyPress(a);
        }

        if (forma.a.getY() + MOVER < YMAX && forma.b.getY() + MOVER < YMAX && forma.c.getY() + MOVER < YMAX
                && forma.d.getY() + MOVER < YMAX) {
            int movera = TABLERO[(int) forma.a.getX() / TAMANIO][((int) forma.a.getY() / TAMANIO) + 1];
            int moverb = TABLERO[(int) forma.b.getX() / TAMANIO][((int) forma.b.getY() / TAMANIO) + 1];
            int moverc = TABLERO[(int) forma.c.getX() / TAMANIO][((int) forma.c.getY() / TAMANIO) + 1];
            int moverd = TABLERO[(int) forma.d.getX() / TAMANIO][((int) forma.d.getY() / TAMANIO) + 1];
            if (movera == 0 && movera == moverb && moverb == moverc && moverc == moverd) {
                forma.a.setY(forma.a.getY() + MOVER);
                forma.b.setY(forma.b.getY() + MOVER);
                forma.c.setY(forma.c.getY() + MOVER);
                forma.d.setY(forma.d.getY() + MOVER);
            }
        }
    }

    private void vuelta(Forma forma) {
        int f = forma.forma;
        Rectangle a = forma.a;
        Rectangle b = forma.b;
        Rectangle c = forma.c;
        Rectangle d = forma.d;

        switch (forma.getNombre()) {
            case "j":
                if (f == 1 && comprobando(a, 1, -1) && comprobando(c, -1, -1) && comprobando(d, -2, -2)) {
                    Derecha(forma.a);
                    Abajo(forma.a);
                    Abajo(forma.c);
                    Izquierda(forma.c);
                    Abajo(forma.d);
                    Abajo(forma.d);
                    Izquierda(forma.d);
                    Izquierda(forma.d);
                    forma.cambiarForma();
                    break;
                }
                if (f == 2 && comprobando(a, -1, -1) && comprobando(c, -1, 1) && comprobando(d, -2, 2)) {
                    Abajo(forma.a);
                    Izquierda(forma.a);
                    Izquierda(forma.c);
                    Arriba(forma.c);
                    Izquierda(forma.d);
                    Izquierda(forma.d);
                    Arriba(forma.d);
                    Arriba(forma.d);
                    forma.cambiarForma();
                    break;
                }
                if (f == 3 && comprobando(a, -1, 1) && comprobando(c, 1, 1) && comprobando(d, 2, 2)) {
                    Izquierda(forma.a);
                    Arriba(forma.a);
                    Arriba(forma.c);
                    Derecha(forma.c);
                    Arriba(forma.d);
                    Arriba(forma.d);
                    Derecha(forma.d);
                    Derecha(forma.d);
                    forma.cambiarForma();
                    break;
                }
                if (f == 4 && comprobando(a, 1, 1) && comprobando(c, 1, -1) && comprobando(d, 2, -2)) {
                    Arriba(forma.a);
                    Derecha(forma.a);
                    Derecha(forma.c);
                    Abajo(forma.c);
                    Derecha(forma.d);
                    Derecha(forma.d);
                    Abajo(forma.d);
                    Abajo(forma.d);
                    forma.cambiarForma();
                    break;
                }
                break;
            case "l":
                if (f == 1 && comprobando(a, 1, -1) && comprobando(c, 1, 1) && comprobando(b, 2, 2)) {
                    Derecha(forma.a);
                    Abajo(forma.a);
                    Arriba(forma.c);
                    Derecha(forma.c);
                    Arriba(forma.b);
                    Arriba(forma.b);
                    Derecha(forma.b);
                    Derecha(forma.b);
                    forma.cambiarForma();
                    break;
                }
                if (f == 2 && comprobando(a, -1, -1) && comprobando(b, 2, -2) && comprobando(c, 1, -1)) {
                    Abajo(forma.a);
                    Izquierda(forma.a);
                    Derecha(forma.b);
                    Derecha(forma.b);
                    Abajo(forma.b);
                    Abajo(forma.b);
                    Derecha(forma.c);
                    Abajo(forma.c);
                    forma.cambiarForma();
                    break;
                }
                if (f == 3 && comprobando(a, -1, 1) && comprobando(c, -1, -1) && comprobando(b, -2, -2)) {
                    Izquierda(forma.a);
                    Arriba(forma.a);
                    Abajo(forma.c);
                    Izquierda(forma.c);
                    Abajo(forma.b);
                    Abajo(forma.b);
                    Izquierda(forma.b);
                    Izquierda(forma.b);
                    forma.cambiarForma();
                    break;
                }
                if (f == 4 && comprobando(a, 1, 1) && comprobando(b, -2, 2) && comprobando(c, -1, 1)) {
                    Arriba(forma.a);
                    Derecha(forma.a);
                    Izquierda(forma.b);
                    Izquierda(forma.b);
                    Arriba(forma.b);
                    Arriba(forma.b);
                    Izquierda(forma.c);
                    Arriba(forma.c);
                    forma.cambiarForma();
                    break;
                }
                break;
            case "o":
                break;
            case "s":
                if (f == 1 && comprobando(a, -1, -1) && comprobando(c, -1, 1) && comprobando(d, 0, 2)) {
                    Abajo(forma.a);
                    Izquierda(forma.a);
                    Izquierda(forma.c);
                    Arriba(forma.c);
                    Arriba(forma.d);
                    Arriba(forma.d);
                    forma.cambiarForma();
                    break;
                }
                if (f == 2 && comprobando(a, 1, 1) && comprobando(c, 1, -1) && comprobando(d, 0, -2)) {
                    Arriba(forma.a);
                    Derecha(forma.a);
                    Derecha(forma.c);
                    Abajo(forma.c);
                    Abajo(forma.d);
                    Abajo(forma.d);
                    forma.cambiarForma();
                    break;
                }
                if (f == 3 && comprobando(a, -1, -1) && comprobando(c, -1, 1) && comprobando(d, 0, 2)) {
                    Abajo(forma.a);
                    Izquierda(forma.a);
                    Izquierda(forma.c);
                    Arriba(forma.c);
                    Arriba(forma.d);
                    Arriba(forma.d);
                    forma.cambiarForma();
                    break;
                }
                if (f == 4 && comprobando(a, 1, 1) && comprobando(c, 1, -1) && comprobando(d, 0, -2)) {
                    Arriba(forma.a);
                    Derecha(forma.a);
                    Derecha(forma.c);
                    Abajo(forma.c);
                    Abajo(forma.d);
                    Abajo(forma.d);
                    forma.cambiarForma();
                    break;
                }
                break;
            case "t":
                if (f == 1 && comprobando(a, 1, 1) && comprobando(d, -1, -1) && comprobando(c, -1, 1)) {
                    Arriba(forma.a);
                    Derecha(forma.a);
                    Abajo(forma.d);
                    Izquierda(forma.d);
                    Izquierda(forma.c);
                    Arriba(forma.c);
                    forma.cambiarForma();
                    break;
                }
                if (f == 2 && comprobando(a, 1, -1) && comprobando(d, -1, 1) && comprobando(c, 1, 1)) {
                    Derecha(forma.a);
                    Abajo(forma.a);
                    Izquierda(forma.d);
                    Arriba(forma.d);
                    Arriba(forma.c);
                    Derecha(forma.c);
                    forma.cambiarForma();
                    break;
                }
                if (f == 3 && comprobando(a, -1, -1) && comprobando(d, 1, 1) && comprobando(c, 1, -1)) {
                    Abajo(forma.a);
                    Izquierda(forma.a);
                    Arriba(forma.d);
                    Derecha(forma.d);
                    Derecha(forma.c);
                    Abajo(forma.c);
                    forma.cambiarForma();
                    break;
                }
                if (f == 4 && comprobando(a, -1, 1) && comprobando(d, 1, -1) && comprobando(c, -1, -1)) {
                    Izquierda(forma.a);
                    Arriba(forma.a);
                    Derecha(forma.d);
                    Abajo(forma.d);
                    Abajo(forma.c);
                    Izquierda(forma.c);
                    forma.cambiarForma();
                    break;
                }
                break;
            case "z":
                if (f == 1 && comprobando(b, 1, 1) && comprobando(c, -1, 1) && comprobando(d, -2, 0)) {
                    Arriba(forma.b);
                    Derecha(forma.b);
                    Izquierda(forma.c);
                    Arriba(forma.c);
                    Izquierda(forma.d);
                    Izquierda(forma.d);
                    forma.cambiarForma();
                    break;
                }
                if (f == 2 && comprobando(b, -1, -1) && comprobando(c, 1, -1) && comprobando(d, 2, 0)) {
                    Abajo(forma.b);
                    Izquierda(forma.b);
                    Derecha(forma.c);
                    Abajo(forma.c);
                    Derecha(forma.d);
                    Derecha(forma.d);
                    forma.cambiarForma();
                    break;
                }
                if (f == 3 && comprobando(b, 1, 1) && comprobando(c, -1, 1) && comprobando(d, -2, 0)) {
                    Arriba(forma.b);
                    Derecha(forma.b);
                    Izquierda(forma.c);
                    Arriba(forma.c);
                    Izquierda(forma.d);
                    Izquierda(forma.d);
                    forma.cambiarForma();
                    break;
                }
                if (f == 4 && comprobando(b, -1, -1) && comprobando(c, 1, -1) && comprobando(d, 2, 0)) {
                    Abajo(forma.b);
                    Izquierda(forma.b);
                    Derecha(forma.c);
                    Abajo(forma.c);
                    Derecha(forma.d);
                    Derecha(forma.d);
                    forma.cambiarForma();
                    break;
                }
                break;
            case "i":
                if (f == 1 && comprobando(a, 2, 2) && comprobando(b, 1, 1) && comprobando(d, -1, -1)) {
                    Arriba(forma.a);
                    Arriba(forma.a);
                    Derecha(forma.a);
                    Derecha(forma.a);
                    Arriba(forma.b);
                    Derecha(forma.b);
                    Abajo(forma.d);
                    Izquierda(forma.d);
                    forma.cambiarForma();
                    break;
                }
                if (f == 2 && comprobando(a, -2, -2) && comprobando(b, -1, -1) && comprobando(d, 1, 1)) {
                    Abajo(forma.a);
                    Abajo(forma.a);
                    Izquierda(forma.a);
                    Izquierda(forma.a);
                    Abajo(forma.b);
                    Izquierda(forma.b);
                    Arriba(forma.d);
                    Derecha(forma.d);
                    forma.cambiarForma();
                    break;
                }
                if (f == 3 && comprobando(a, 2, 2) && comprobando(b, 1, 1) && comprobando(d, -1, -1)) {
                    Arriba(forma.a);
                    Arriba(forma.a);
                    Derecha(forma.a);
                    Derecha(forma.a);
                    Arriba(forma.b);
                    Derecha(forma.b);
                    Abajo(forma.d);
                    Izquierda(forma.d);
                    forma.cambiarForma();
                    break;
                }
                if (f == 4 && comprobando(a, -2, -2) && comprobando(b, -1, -1) && comprobando(d, 1, 1)) {
                    Abajo(forma.a);
                    Abajo(forma.a);
                    Izquierda(forma.a);
                    Izquierda(forma.a);
                    Abajo(forma.b);
                    Izquierda(forma.b);
                    Arriba(forma.d);
                    Derecha(forma.d);
                    forma.cambiarForma();
                    break;
                }
                break;
        }
    }

    public void RemoverLineas(Pane pane) {
        ArrayList<Node> rectas = new ArrayList<>();
        ArrayList<Integer> lineas = new ArrayList<>();
        ArrayList<Node> nuevasRectas = new ArrayList<>();
        int full = 0;
        //comprobar que linea esta completa
        for (int i = 0; i < TABLERO[0].length; i++) {
            for (int j = 0; j < TABLERO.length; j++) {
                if (TABLERO[j][i] == 1) {
                    full++;
                }
            }
            if (full == TABLERO.length) {
                lineas.add(i);
            }
            //lines.add(i + lines.size());
            full = 0;
        }
        if (lineas.size() > 0) {
            do {
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle) {
                        rectas.add(node);
                    }
                }
                puntaje += 50;
                noLineas++;

                for (Node node : rectas) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() == lineas.get(0) * TAMANIO) {
                        TABLERO[(int) a.getX() / TAMANIO][(int) a.getY() / TAMANIO] = 0;
                        pane.getChildren().remove(node);
                    } else {
                        nuevasRectas.add(node);
                    }
                }

                for (Node node : nuevasRectas) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() < lineas.get(0) * TAMANIO) {
                        TABLERO[(int) a.getX() / TAMANIO][(int) a.getY() / TAMANIO] = 0;
                        a.setY(a.getY() + TAMANIO);
                    }
                }
                lineas.remove(0);
                rectas.clear();
                nuevasRectas.clear();
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle) {
                        rectas.add(node);
                    }
                }
                for (Node node : rectas) {
                    Rectangle a = (Rectangle) node;
                    try {
                        TABLERO[(int) a.getX() / TAMANIO][(int) a.getY() / TAMANIO] = 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                rectas.clear();
            } while (lineas.size() > 0);
        }
    }

    @FXML
    private void pausar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/pausaVista.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(root1));
        stage.show();
    }

}
