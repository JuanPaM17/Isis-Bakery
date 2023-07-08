/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesDAO;

import BaseDatos.Conexion;
import Clases.Pedido;
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
public class PedidoDAO {

    Connection con; //Conectarnos con la bd
    Statement st; //Establce la conexion
    ResultSet rs; //Resultados de las consultas
    PreparedStatement ps; //Enviar consultas
    Conexion cn = new Conexion();

    public int registro_pedido(Pedido p) {
        String consulta = "Insert into tblpedido (id_cliente,total,fecha_recogida,hora_recogida,nombre_recoge,telefono_contacto,id_estado_pedido) values (?,?,?,?,?,?,?)";
        try {
            con = cn.Conectar();
            st = con.createStatement(); //Se crea la conexion
            ps = con.prepareStatement(consulta);
            ps.setInt(1, p.getId_cliente());
            ps.setFloat(2, p.getTotal());
            ps.setString(3, p.getFecha_recogida());
            ps.setString(4, p.getHora_recogida());
            ps.setString(5, p.getNombre_recoge());
            ps.setLong(6, p.getTelefono_contacto());
            ps.setInt(7, p.getId_estado_pedido());
            int r = ps.executeUpdate(); //Ejecutarla o enviarla
            if (r == 1) {
                return ultimoId();
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

    private int ultimoId() {
        String sql = "SELECT LAST_INSERT_ID() FROM tblpedido LIMIT 1";
        try {
            con = cn.Conectar();
            con.createStatement();
            rs = ps.executeQuery(sql);
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

    public int contar_estado_pedidos(int estado) {
        String sql = "select count(id_pedido) from tblpedido where id_estado_pedido=" + estado;
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
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

    public ArrayList<Pedido> listar_pedidos_por_cliente(int id_cliente) {
        ArrayList<Pedido> listaPedidosCliente = new ArrayList<>();
        String sql = "select * from tblpedido where id_cliente=? AND id_estado_pedido=1 OR id_estado_pedido=3 OR id_estado_pedido=2 ORDER BY id_pedido";
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_cliente);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId_pedido(rs.getInt(1));
                p.setId_cliente(rs.getInt(2));
                p.setTotal(rs.getFloat(3));
                p.setFecha_recogida(rs.getString(4));
                p.setHora_recogida(rs.getString(5));
                p.setNombre_recoge(rs.getString(6));
                p.setTelefono_contacto(rs.getLong(7));
                p.setId_estado_pedido(rs.getInt(8));
                listaPedidosCliente.add(p);
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
        return listaPedidosCliente;
    }

    public void eliminar_pedido(int id_pedido) {
        DetalleVentaDAO aO = new DetalleVentaDAO();
        aO.eliminar_Detallepedido(id_pedido);
        try {
            con = cn.Conectar();
            String sql = "delete from tblpedido where id_pedido=" + id_pedido;
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

    public Pedido buscar_producto_por_Id(Pedido p) {
        String sql = "select * from tblpedido where id_pedido=?";
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getId_pedido());
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId_pedido(rs.getInt(1));
                p.setId_cliente(rs.getInt(2));
                p.setTotal(rs.getFloat(3));
                p.setFecha_recogida(rs.getString(4));
                p.setHora_recogida(rs.getString(5));
                p.setNombre_recoge(rs.getString(6));
                p.setTelefono_contacto(rs.getLong(7));
                p.setId_estado_pedido(rs.getInt(8));
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

    public boolean actualizar_pedido(Pedido p) {
        try {
            con = cn.Conectar();
            String sql = "UPDATE tblpedido set nombre_recoge=?, telefono_contacto=? where id_pedido=?";
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre_recoge());
            ps.setLong(2, p.getTelefono_contacto());
            ps.setInt(3, p.getId_pedido());
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

    public ArrayList<Pedido> listar_pedidos_espera() {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        String sql = "SELECT tp.id_pedido,tp.id_cliente,tp.total,tp.fecha_recogida,tp.hora_recogida,tp.nombre_recoge,tp.telefono_contacto,tp.id_estado_pedido,tu.nombre from tblpedido tp INNER JOIN tblusuario tu ON tu.id_usuario=tp.id_cliente where id_estado_pedido=? ORDER BY fecha_recogida";
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId_pedido(rs.getInt(1));
                p.setId_cliente(rs.getInt(2));
                p.setTotal(rs.getFloat(3));
                p.setFecha_recogida(rs.getString(4));
                p.setHora_recogida(rs.getString(5));
                p.setNombre_recoge(rs.getString(6));
                p.setTelefono_contacto(rs.getLong(7));
                p.setId_estado_pedido(rs.getInt(8));
                p.setNombre_cliente(rs.getString(9));
                listaPedidos.add(p);
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
        return listaPedidos;
    }

    public boolean rechazar_pedido(int id_pedido) {
        try {
            con = cn.Conectar();
            String sql = "UPDATE tblpedido set id_estado_pedido=? where id_pedido=?";
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setInt(1, 3);
            ps.setInt(2, id_pedido);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean aceptar_pedido(int id_pedido) {
        try {
            con = cn.Conectar();
            String sql = "UPDATE tblpedido set id_estado_pedido=? where id_pedido=?";
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setInt(1, 2);
            ps.setInt(2, id_pedido);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
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
