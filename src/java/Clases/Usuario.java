/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author sarit
 */
public class Usuario {

    private int id_usuario;
    private long cedula;
    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    private long celular;
    private String correo;
    private String direccion;
    private String color;
    private String contrasena;
    private int id_estado_usuario;
    private int id_tipo_usuario;
    // Atributos para consulta con INNERJOIN
    private int numero_venta;

    public Usuario() {
    }

    public Usuario(long cedula, String nombre, String apellido, String fecha_nacimiento, long celular, String correo, String direccion, String color, String contrasena, int id_estado_usuario, int id_tipo_usuario) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.celular = celular;
        this.correo = correo;
        this.direccion = direccion;
        this.color = color;
        this.contrasena = contrasena;
        this.id_estado_usuario = id_estado_usuario;
        this.id_tipo_usuario = id_tipo_usuario;
    }

    public Usuario(int id_usuario, long cedula, String nombre, String apellido, String fecha_nacimiento, long celular, String correo, String direccion, String color, String contrasena, int id_estado_usuario, int id_tipo_usuario) {
        this.id_usuario = id_usuario;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.celular = celular;
        this.correo = correo;
        this.direccion = direccion;
        this.color = color;
        this.contrasena = contrasena;
        this.id_estado_usuario = id_estado_usuario;
        this.id_tipo_usuario = id_tipo_usuario;
    }

    public int getNumero_venta() {
        return numero_venta;
    }

    public void setNumero_venta(int numero_venta) {
        this.numero_venta = numero_venta;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getId_estado_usuario() {
        return id_estado_usuario;
    }

    public void setId_estado_usuario(int id_estado_usuario) {
        this.id_estado_usuario = id_estado_usuario;
    }

    public int getId_tipo_usuario() {
        return id_tipo_usuario;
    }

    public void setId_tipo_usuario(int id_tipo_usuario) {
        this.id_tipo_usuario = id_tipo_usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + "\ncedula=" + cedula + "\nnombre=" + nombre + "\napellido=" + apellido + "\nfecha_nacimiento=" + fecha_nacimiento + "\ncelular=" + celular + "\ncorreo=" + correo + "\ndireccion=" + direccion + "\ncolor=" + color + "\ncontrasena=" + contrasena + "\nid_estado_usuario=" + id_estado_usuario + "\nid_tipo_usuario=" + id_tipo_usuario + '}';
    }
}
