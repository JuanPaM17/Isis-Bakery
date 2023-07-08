/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesDAO;

import BaseDatos.Conexion;
import Clases.DetallePedido;
import ClasesNecesarias.Carrito;
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
public class DetalleVentaDAO {

    Connection con; //Conectarnos con la bd
    Statement st; //Establce la conexion
    ResultSet rs; //Resultados de las consultas
    PreparedStatement ps; //Enviar consultas
    Conexion cn = new Conexion();
    ProductoDAO pdao = new ProductoDAO();

    public ArrayList<DetallePedido> listar_productos_detalle_venta(int id_pedido) {
        ArrayList<DetallePedido> listaProductosDetalle = new ArrayList<>();
        String sql = "SELECT tp.nombre,sum(tbp.cantidad) FROM tbldetalle_pedido tbp INNER JOIN tblproducto tp ON tp.id_producto=tbp.id_producto WHERE tbp.id_pedido='" + id_pedido + "'GROUP BY tp.nombre";
        try {
            con = cn.Conectar();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                DetallePedido dp = new DetallePedido();
                dp.setNombre_producto(rs.getString(1));
                dp.setCantidad(rs.getInt(2));
                //JOptionPane.showMessageDialog(null, dp.toString());
                listaProductosDetalle.add(dp);
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
        return listaProductosDetalle;
    }

    public void registrar_detallePedido(ArrayList<Carrito> listCarrito, int id_pedido) {
        ArrayList<DetallePedido> listaDetalle = new ArrayList<>();
        for (int i = 0; i < listCarrito.size(); i++) {
            DetallePedido dp = new DetallePedido();
            dp.setId_pedido(id_pedido);
            dp.setId_producto(listCarrito.get(i).getIdproducto());
            dp.setCantidad(listCarrito.get(i).getCantidad());
            dp.setSubTotal(listCarrito.get(i).getSubTotal());
            listaDetalle.add(dp);
        }
        for (int i = 0; i < listaDetalle.size(); i++) {
            registro_detalle(listaDetalle.get(i));
            pdao.actualizar_stock(listaDetalle.get(i).getId_producto(), listaDetalle.get(i).getCantidad());
        }
    }

    private boolean registro_detalle(DetallePedido dp) {
        String consulta = "Insert into tbldetalle_pedido (id_pedido,id_producto,cantidad,subtotal) values (?,?,?,?)";
        try {
            con = cn.Conectar();
            st = con.createStatement(); //Se crea la conexion
            ps = con.prepareStatement(consulta);
            ps.setInt(1, dp.getId_pedido());
            ps.setInt(2, dp.getId_producto());
            ps.setInt(3, dp.getCantidad());
            ps.setFloat(4, dp.getSubTotal());
            int r = ps.executeUpdate();
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

    public void eliminar_Detallepedido(int id_pedido) {
        try {
            con = cn.Conectar();
            String sql = "delete from tbldetalle_pedido where id_pedido=" + id_pedido;
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

    public ArrayList<DetallePedido> listar_detalle_pedido_todos() {
        ArrayList<DetallePedido> listaProductosDetalle = new ArrayList<>();
        String sql = "SELECT tbp.id_pedido,tp.nombre,tbp.cantidad FROM tbldetalle_pedido tbp INNER JOIN tblproducto tp ON tp.id_producto=tbp.id_producto";
        try {
            con = cn.Conectar();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                DetallePedido dp = new DetallePedido();
                dp.setId_pedido(rs.getInt(1));
                dp.setNombre_producto(rs.getString(2));
                dp.setCantidad(rs.getInt(3));
                //JOptionPane.showMessageDialog(null, dp.toString());
                listaProductosDetalle.add(dp);
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
        return listaProductosDetalle;
    }

}
