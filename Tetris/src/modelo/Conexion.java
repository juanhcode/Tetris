package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class Conexion {

    Connection conn = null;

    public static Connection connectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/tetris", "root", "11111111");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public static ObservableList<Usuario> getDatausers(){
        Connection conn = connectDb();
        ObservableList<Usuario> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from usuario ORDER BY `puntaje` DESC");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Usuario(rs.getString("nombre"),rs.getString("password"),(Integer.parseInt(rs.getString("puntaje")))));    
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return list;
        
        
    }
}
