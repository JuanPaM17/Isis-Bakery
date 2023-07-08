/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author sarit
 */
public class DetallePedido {

    private int id_detalle_pedido;
    private int id_pedido;
    private int id_producto;
    private int cantidad;
    private float subTotal;
    // Atributos para la consulta con INNERJOIN
    private String nombre_producto;

    public DetallePedido() {
    }

    public DetallePedido(int id_detalle_pedido, int id_pedido, int id_producto, int cantidad, float subTotal) {
        this.id_detalle_pedido = id_detalle_pedido;
        this.id_pedido = id_pedido;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public DetallePedido(int id_pedido, int id_producto, int cantidad, float subTotal) {
        this.id_pedido = id_pedido;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public int getId_detalle_pedido() {
        return id_detalle_pedido;
    }

    public void setId_detalle_pedido(int id_detalle_pedido) {
        this.id_detalle_pedido = id_detalle_pedido;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    @Override
    public String toString() {
        return "DetallePedido{" + "id_detalle_pedido=" + id_detalle_pedido + "\nid_pedido=" + id_pedido + "\nid_producto=" + id_producto + "\ncantidad=" + cantidad + "\nsubTotal=" + subTotal + "\nnombre_producto=" + nombre_producto + '}';
    }

}
