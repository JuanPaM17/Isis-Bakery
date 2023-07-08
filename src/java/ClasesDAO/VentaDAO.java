/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesDAO;

import BaseDatos.Conexion;
import Clases.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sarit
 */
public class VentaDAO {

    Connection con; //Conectarnos con la bd
    Statement st; //Establce la conexion
    ResultSet rs; //Resultados de las consultas
    PreparedStatement ps; //Enviar consultas
    Conexion cn = new Conexion();

    public boolean registro_venta(Venta v) {
        String consulta = "Insert into tblventa (id_pedido,id_vendedor,nit,fecha_generacion) values (?,?,?,?)";
        try {
            con = cn.Conectar();
            st = con.createStatement(); //Se crea la conexion
            ps = con.prepareStatement(consulta);
            ps.setInt(1, v.getId_pedido());
            ps.setInt(2, v.getId_vendedor());
            ps.setLong(3, v.getNit());
            ps.setString(4, v.getFecha_generacion());
            int r = ps.executeUpdate(); //Ejecutarla o enviarla
            if (r == 1) {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public ArrayList<Venta> listar_ventas() {
        ArrayList<Venta> listaVentas = new ArrayList<>();
        String sql = "SELECT tv.id_venta, tv.id_pedido, tv.nit, tu2.nombre, tu.nombre, tp.total, tv.fecha_generacion FROM tblventa tv INNER JOIN tblpedido tp ON tp.id_pedido=tv.id_pedido INNER JOIN tblusuario tu ON tv.id_vendedor= tu.id_usuario INNER JOIN tblusuario tu2 ON tp.id_cliente=tu2.id_usuario ORDER BY tv.id_venta";
        try {
            con = cn.Conectar();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Venta v = new Venta();
                v.setId_venta(rs.getInt(1));
                v.setId_pedido(rs.getInt(2));
                v.setNit(rs.getInt(3));
                v.setNombre_cliente(rs.getString(4));
                v.setNombre_vendedor(rs.getString(5));
                v.setTotal(rs.getFloat(6));
                v.setFecha_generacion(rs.getString(7));
                //JOptionPane.showMessageDialog(null, u.toString());
                listaVentas.add(v);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaVentas;
    }

    public int contar_ventas() {
        String sql = "SELECT count(id_venta) FROM tblventa";
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
    public int ventas_semana_suma_total(int dia) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sql = "select sum(tp.total) from tblventa tv INNER JOIN tblpedido tp ON tp.id_pedido=tv.id_pedido where tv.fecha_generacion=?";
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
            if (dia == 1) {
                ps.setString(1, dtf.format(LocalDateTime.now()));
            } else {
                ps.setString(1, dtf.format(LocalDateTime.now().plusDays(-(dia - 1))));
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return (rs.getInt(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
    public int ventas_semana_ganancias_total(int dia) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sql = "select count(id_venta) from tblventa tv where tv.fecha_generacion=?";
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
            if (dia == 1) {
                ps.setString(1, dtf.format(LocalDateTime.now()));
            } else {
                ps.setString(1, dtf.format(LocalDateTime.now().plusDays(-(dia - 1))));
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return (rs.getInt(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
}
