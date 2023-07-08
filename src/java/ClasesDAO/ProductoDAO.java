/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesDAO;

import BaseDatos.Conexion;
import Clases.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sarit
 */
public class ProductoDAO {

    Connection con; //Conectarnos con la bd
    Statement st; //Establce la conexion
    ResultSet rs; //Resultados de las consultas
    PreparedStatement ps; //Enviar consultas
    Conexion cn = new Conexion();

    public boolean registro_producto(Producto p) {
        String consulta = "Insert into tblproducto (codigo,nombre,descripcion,precio_venta,stock,foto) values (?,?,?,?,?,?)";
        try {
            con = cn.Conectar();
            st = con.createStatement(); //Se crea la conexion
            ps = con.prepareStatement(consulta);
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDescripcion());
            ps.setFloat(4, p.getPrecio_venta());
            ps.setInt(5, p.getStock());
            ps.setString(6, p.getUrlFoto());
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

    public ArrayList<Producto> listar_productos() {
        ArrayList<Producto> listaProducto = new ArrayList<>();
        String sql = "SELECT * FROM tblproducto ORDER BY id_producto";
        try {
            con = cn.Conectar();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Producto p = new Producto();
                p.setId_producto(rs.getInt(1));
                p.setCodigo(rs.getString(2));
                p.setNombre(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio_venta(rs.getFloat(5));
                p.setStock(rs.getInt(6));
                p.setUrlFoto(rs.getString(7));
                //JOptionPane.showMessageDialog(null, p.toString());
                listaProducto.add(p);
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
        return listaProducto;
    }

    public int contar_produtos() {
        String sql = "SELECT count(id_producto) FROM tblproducto";
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

    public Producto buscar_producto_por_Id(Producto p) {
        String sql = "select * from tblproducto where id_producto=?";
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getId_producto());
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId_producto(rs.getInt(1));
                p.setCodigo(rs.getString(2));
                p.setNombre(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio_venta(rs.getFloat(5));
                p.setStock(rs.getInt(6));
                p.setUrlFoto(rs.getString(7));
                return p;
            }
        } catch (SQLException e) {
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public Producto buscar_producto_por_codigo(String codigo_producto) {
        String sql = "select * from tblproducto where codigo=?";
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo_producto);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId_producto(rs.getInt(1));
                p.setCodigo(rs.getString(2));
                p.setNombre(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio_venta(rs.getFloat(5));
                p.setStock(rs.getInt(6));
                p.setUrlFoto(rs.getString(7));
                return p;
            }
        } catch (SQLException e) {
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void eliminar_producto(int id_producto) {
        try {
            con = cn.Conectar();
            String sql = "delete from tblproducto where id_producto=" + id_producto;
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean actualizar_producto(Producto p) {
        try {
            con = cn.Conectar();
            String sql = "UPDATE tblproducto set codigo=?, nombre=?, descripcion=?, precio_venta=?, stock=?, foto=? where id_producto=?";
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDescripcion());
            ps.setFloat(4, p.getPrecio_venta());
            ps.setInt(5, p.getStock());
            ps.setString(6, p.getUrlFoto());
            ps.setInt(7, p.getId_producto());
            int r = ps.executeUpdate();
            if (r == 1) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public int buscar_stock_producto(int id) {
        String sql = "select stock from tblproducto where id_producto=?";
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
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
        return -1;
    }
    
    public boolean actualizar_stock(int id_productp, int cantidad) {
        int stockActual = buscar_stock_producto(id_productp) - cantidad;
        try {
            con = cn.Conectar();
            String sql = "UPDATE tblproducto set stock=? where id_producto=?";
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stockActual);
            ps.setInt(2, id_productp);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

}
