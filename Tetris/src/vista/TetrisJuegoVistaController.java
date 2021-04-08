package vista;

import modelo.Controlador;
import modelo.Forma;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import modelo.Conexion;

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
    private Administrador admin;

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
    @FXML
    private ImageView corazon1;
    @FXML
    private ImageView corazon2;
    @FXML
    private ImageView corazon3;
    @FXML
    private Label vida1;
    @FXML
    private Label tiempo;
    private long inicio = 0;
    private long minutos = 0;
    private boolean estado = false;
    private int nivelActual;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    public static Reproductor repro = new Reproductor();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nivel.setText(NivelVistaController.valor + "");
    }

    @FXML
    public void iniciar() {
        try {
            //repro.AbrirFichero("C:/Users/Juan Manuel/OneDrive - correounivalle.edu.co/Escritorio/Juan/Personal/UNIVERSIDAD/UNIVALLE/SEMESTRE 4/Programacion Interactiva/ProyectoTetris/Tetris/Tetris/src/recursos/SmoothCriminal.mp3");
            repro.Play();
        } catch (Exception ex) {
            Logger.getLogger(TetrisJuegoVistaController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
                        long aa = System.currentTimeMillis();
                        if (!estado) {
                            inicio = (int) (aa / 1000) % 60;
                            estado = true;
                            inicio -= inicio;
                        } else {
                            inicio++;
                        }
                        if (inicio / 60 >= 1) {
                            int aux = (int) inicio / 60;
                            int aux1 = (int) ((inicio % 60));
                            if (aux1 >= 60) {
                                aux1 -= 60;
                            }
                            if (minutos == 0) {
                                minutos = aux;
                            } else {
                                minutos++;
                            }
                            if (minutos >= 10) {
                                tiempo.setText(minutos + ":0" + aux1);
                                inicio = aux1;
                            } else {
                                tiempo.setText(tiempo.getText().substring(0, 1) + minutos + ":0" + aux1);
                                inicio = aux1;
                            }
                        }
                        int nivelDeAvance = NivelVistaController.valor + 1; // nivel 2
                        int tercerNivel = nivelDeAvance + 1; // nivel 3
                        int cuartoNivel = tercerNivel + 1; // nivel 4
                        int quintoNivel = cuartoNivel + 1;
                        int sextoNivel = quintoNivel + 1;
                        int septimoNivel = sextoNivel + 1;
                        int octavoNivel = septimoNivel + 1;
                        int novenoNivel = octavoNivel + 1;
                        int decimoNivel = novenoNivel + 1;

                        if (noLineas == 2) {
                            nivel.setText(nivelDeAvance + "");
                        } else if (noLineas == 4) {
                            nivel.setText(tercerNivel + "");
                        } else if (noLineas == 6) {
                            nivel.setText(cuartoNivel + "");
                        } else if (noLineas == 8) {
                            nivel.setText(quintoNivel + "");
                        } else if (noLineas == 10) {
                            nivel.setText(sextoNivel + "");
                        } else if (noLineas == 12) {
                            nivel.setText(septimoNivel + "");
                        } else if (noLineas == 14) {
                            nivel.setText(octavoNivel + "");
                        } else if (noLineas == 16) {
                            nivel.setText(novenoNivel + "");
                        } else if (noLineas == 18) {
                            nivel.setText(decimoNivel + "");
                        }

                        if (inicio >= 10) {
                            tiempo.setText(tiempo.getText().substring(0, 3) + inicio);
                        } else {
                            tiempo.setText(tiempo.getText().substring(0, 4) + inicio);
                        }
                        if (objeto.a.getY() == 0 || objeto.b.getY() == 0 || objeto.c.getY() == 0 || objeto.d.getY() == 0) {
                            arriba++;

                        } else {
                            arriba = 0;
                        }

                        if (arriba == 2) {
                            int vidas = Integer.parseInt(vida.getText());
                            vidas--;

                            if (vidas == 2) {
                                corazon3.setVisible(false);
                                vida.setText(vidas + "");
                                paneles.getChildren().clear(); //limpia tablero e inicia
                                iniciar();
                            } else if (vidas == 1) {
                                corazon2.setVisible(false);
                                vida.setText(vidas + "");
                                paneles.getChildren().clear(); //limpia tablero e inicia
                                iniciar();
                            } else if (vidas == 0) {
                                corazon1.setVisible(false);
                                paneles.getChildren().clear(); //limpia tablero e inicia
                                try {
                                    repro.Stop();
                                } catch (Exception ex) {
                                    Logger.getLogger(TetrisJuegoVistaController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                //juego = false;
                                //MANDAR PUNTAJE A LA BASE DE DATOS
                                conn = Conexion.connectDb();
                                String sql = "update usuario set puntaje= '" + puntaje + "' where nombre='" + MenuPrincipalVistaController.usuario + "' ";
                                try {
                                    pst = conn.prepareStatement(sql);
                                    pst.execute();
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, e);
                                }
                                admin.abrirVentanaGameOver();
                            }
                        }
                        // Exit
                        if (juego) {
                            Abajo(objeto);
                            puntajeTexto.setText(Integer.toString(puntaje));
                            lineas.setText(Integer.toString(noLineas));
                        }
                    }
                }
                );
            }

        };
        if (NivelVistaController.valor == 1) {
            caida.schedule(task, 0, 1000);
        } else if (NivelVistaController.valor == 5) {
            caida.schedule(task, 0, 500); //CAMBIAR PARA NO HACER EL RIDICULO COMO CIFUENTES
        } else if (NivelVistaController.valor == 10) {
            caida.schedule(task, 0, 100);
        }
    }

    public void funcion(Timer time, TimerTask task) {
        time.schedule(task, 0, 600);
    }

    public void instrucciones() {
        admin.abririntrucciones();
    }

    private void moveOnKeyPress(Forma forma) {
        Administrador.sceneT.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
                //repro.AbrirFichero(ruta); coloca musica
                //repro.play
            } while (lineas.size() > 0);
        }
    }

    @FXML
    private void pausar() {
        try {
            repro.Pausa();
        } catch (Exception ex) {
            Logger.getLogger(TetrisJuegoVistaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        admin.abrirVentanaPausa();
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }
}
