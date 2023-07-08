/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author sarit
 */
public class Producto {
    
    private int id_producto;
    private String codigo;
    private String nombre;
    private String descripcion;
    private float precio_venta;
    private int stock;
    private String urlFoto;

    public Producto() {
    }

    public Producto(int id_producto, String codigo, String nombre, String descripcion, float precio_venta, int stock, String urlFoto) {
        this.id_producto = id_producto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio_venta = precio_venta;
        this.stock = stock;
        this.urlFoto = urlFoto;
    }

    public Producto(String codigo, String nombre, String descripcion, float precio_venta, int stock, String urlFoto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio_venta = precio_venta;
        this.stock = stock;
        this.urlFoto = urlFoto;
    }
    
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String foto) {
        this.urlFoto = foto;
    }

    @Override
    public String toString() {
        return "Producto{" + "\nid_producto=" + id_producto + "\ncodigo=" + codigo + "\nnombre=" + nombre + "\ndescripcion=" + descripcion + "\n precio_venta=" + precio_venta +  "\nStock=" + stock +"\n foto=" + urlFoto + '}';
    }
}
