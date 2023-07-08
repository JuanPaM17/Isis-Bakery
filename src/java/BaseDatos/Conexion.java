/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sarit
 */
public class Conexion {

    // --- el url es la conexion con oracle y el driver jdbc, el puerto por lo general es 1521
    String bd = "blqsxdvgtjm5jmfmdd3s";
    String user = "uwjrwmhas0yilxzw";
    String pass = "o5J2DFKBw2bTyB86BJzH";
    String url = "jdbc:mysql://blqsxdvgtjm5jmfmdd3s-mysql.services.clever-cloud.com:3306/";
    //String user = "isisbakery";
    //String pass = "pjT.sQQFu25WKuZ";
    //String bd = "isisbakery2";
    //String url = "jdbc:mysql://localhost:3306/";
    //String user = "root";
    //String pass = "";
    String driver = "com.mysql.cj.jdbc.Driver";

    // --- Estos objetos son importantes para hacer para trabajar con la base datos
    Connection con; //Conectarnos con la bd
    Statement st; //Establce la conexion
    ResultSet rs; //Resultados de las consultas
    PreparedStatement ps; //Enviar consultas

    public Connection Conectar() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + bd, user, pass);
            //JOptionPane.showMessageDialog(null, "Se conecto");
        } catch (ClassNotFoundException ex) {
            //JOptionPane.showMessageDialog(null, "No se conecto");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "No se conecto");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

}
