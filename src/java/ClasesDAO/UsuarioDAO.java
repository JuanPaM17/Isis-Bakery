/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesDAO;

import BaseDatos.Conexion;
import Clases.Usuario;
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
public class UsuarioDAO {

    Connection con; //Conectarnos con la bd
    Statement st; //Establce la conexion
    ResultSet rs; //Resultados de las consultas
    PreparedStatement ps; //Enviar consultas
    Conexion cn = new Conexion();

    public Usuario inicio_sesion_usuario(Usuario u) {
        String sql = "select * from tblusuario where correo=? and contrasena=?";
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getCorreo());
            ps.setString(2, u.getContrasena());
            rs = ps.executeQuery();
            while (rs.next()) {
                u.setId_usuario(rs.getInt(1));
                u.setCedula(rs.getLong(2));
                u.setNombre(rs.getString(3));
                u.setApellido(rs.getString(4));
                u.setFecha_nacimiento(rs.getString(5));
                u.setCelular(rs.getLong(6));
                u.setCorreo(rs.getString(7));
                u.setDireccion(rs.getString(8));
                u.setColor(rs.getString(9));
                u.setContrasena(rs.getString(10));
                u.setId_estado_usuario(rs.getInt(11));
                u.setId_tipo_usuario(rs.getInt(12));
                return u;
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
        return null;
    }

    public boolean registro_usuario(Usuario u) {
        String consulta = "Insert into tblusuario (cedula,nombre,apellido,fecha_nacimiento,celular,correo,direccion,color,contrasena,id_estado_usuario,id_tipo_usuario) values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.Conectar();
            st = con.createStatement(); //Se crea la conexion
            ps = con.prepareStatement(consulta); //Prepara la consulta
            ps.setLong(1, u.getCedula());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getFecha_nacimiento());
            ps.setLong(5, u.getCelular());
            ps.setString(6, u.getCorreo());
            ps.setString(7, u.getDireccion());
            ps.setString(8, u.getColor());
            ps.setString(9, u.getContrasena());
            ps.setInt(10, u.getId_estado_usuario());
            ps.setInt(11, u.getId_tipo_usuario());
            int r = ps.executeUpdate();
            if (r == 1) {
                return true;
            }
            return true;
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

    public void eliminar_usuario(int id_usuario) {
        try {
            con = cn.Conectar();
            String sql = "delete from tblusuario where id_usuario=" + id_usuario;
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

    public Usuario olvido_contra_usuario(String correo) {
        String sql = "select nombre, correo, color, contrasena from tblusuario where correo=?";
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setNombre(rs.getString(1));
                u.setCorreo(rs.getString(2));
                u.setColor(rs.getString(3));
                u.setContrasena(rs.getString(4));
                return u;
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
        return null;
    }

    public boolean verificar_correo_usuario(Usuario u) {
        String sql = "select nombre from tblusuario where correo=?";
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getCorreo());
            rs = ps.executeQuery();
            if (rs.next()) {
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

    public boolean verificar_cedula_usuario(Usuario u) {
        String sql = "select nombre from tblusuario where cedula=?";
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setLong(1, u.getCedula());
            rs = ps.executeQuery();
            if (rs.next()) {
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

    public int contar_usuarios_clientes() {
        String sql = "SELECT count(id_usuario) FROM tblusuario WHERE id_tipo_usuario=2";
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

    public int contar_usuarios_vendedores() {
        String sql = "SELECT count(id_usuario) FROM tblusuario WHERE id_tipo_usuario=3";
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

    public ArrayList<Usuario> listar_usuarios_por_tipo(int id_tipo_usuario) {
        ArrayList<Usuario> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM tblusuario WHERE id_tipo_usuario=" + id_tipo_usuario + " ORDER BY id_usuario";
        try {
            con = cn.Conectar();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId_usuario(rs.getInt(1));
                u.setCedula(rs.getLong(2));
                u.setNombre(rs.getString(3));
                u.setApellido(rs.getString(4));
                u.setFecha_nacimiento(rs.getString(5));
                u.setCelular(rs.getLong(6));
                u.setCorreo(rs.getString(7));
                u.setDireccion(rs.getString(8));
                u.setColor(rs.getString(9));
                u.setContrasena(rs.getString(10));
                u.setId_estado_usuario(rs.getInt(11));
                u.setId_tipo_usuario(rs.getInt(12));
                //JOptionPane.showMessageDialog(null, u.toString());
                listaClientes.add(u);
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
        return listaClientes;
    }

    public Usuario buscar_usuario_por_Id(Usuario u) {
        String sql = "select * from tblusuario where id_usuario=?";
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setInt(1, u.getId_usuario());
            rs = ps.executeQuery();
            if (rs.next()) {
                u.setId_usuario(rs.getInt(1));
                u.setCedula(rs.getLong(2));
                u.setNombre(rs.getString(3));
                u.setApellido(rs.getString(4));
                u.setFecha_nacimiento(rs.getString(5));
                u.setCelular(rs.getLong(6));
                u.setCorreo(rs.getString(7));
                u.setDireccion(rs.getString(8));
                u.setColor(rs.getString(9));
                u.setContrasena(rs.getString(10));
                u.setId_estado_usuario(rs.getInt(11));
                u.setId_tipo_usuario(rs.getInt(12));
                return u;
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

    public Usuario buscar_cliente_por_cedula(long cedula_usuario) {
        String sql = "select * from tblusuario where cedula=? AND id_tipo_usuario=2";
        try {
            con = cn.Conectar();
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setLong(1, cedula_usuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId_usuario(rs.getInt(1));
                u.setCedula(rs.getLong(2));
                u.setNombre(rs.getString(3));
                u.setApellido(rs.getString(4));
                u.setFecha_nacimiento(rs.getString(5));
                u.setCelular(rs.getLong(6));
                u.setCorreo(rs.getString(7));
                u.setDireccion(rs.getString(8));
                u.setColor(rs.getString(9));
                u.setContrasena(rs.getString(10));
                u.setId_estado_usuario(rs.getInt(11));
                u.setId_tipo_usuario(rs.getInt(12));
                return u;
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

    public boolean actualizar_usuario(Usuario u) {
        try {
            con = cn.Conectar();
            String sql = "UPDATE tblusuario set cedula=?, nombre=?, apellido=?, fecha_nacimiento=?, celular=?, correo=?, direccion=?, color=?, contrasena=?, id_estado_usuario=? where id_usuario=?";
            con.createStatement();
            ps = con.prepareStatement(sql);
            ps.setLong(1, u.getCedula());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getFecha_nacimiento());
            ps.setLong(5, u.getCelular());
            ps.setString(6, u.getCorreo());
            ps.setString(7, u.getDireccion());
            ps.setString(8, u.getColor());
            ps.setString(9, u.getContrasena());
            ps.setInt(10, u.getId_estado_usuario());
            ps.setInt(11, u.getId_usuario());
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

    public ArrayList<Usuario> top_3_vendedores_con_mas_ventas() {
        ArrayList<Usuario> listaVendedoresTOP = new ArrayList<>();
        String sql = "SELECT tu.nombre, COUNT(tvt.id_vendedor) AS total_ventas FROM tblusuario tu INNER JOIN tblventa tvt ON tu.id_usuario = tvt.id_vendedor GROUP BY tu.nombre ORDER BY total_ventas DESC LIMIT 3";
        try {
            con = cn.Conectar();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setNombre(rs.getString(1));
                u.setNumero_venta(rs.getInt(2));
                listaVendedoresTOP.add(u);
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
        return listaVendedoresTOP;
    }

}
