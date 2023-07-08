/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author sarit
 */
public class Venta {

    private int id_venta;
    private int id_pedido;
    private int id_vendedor;
    private long nit;
    private String fecha_generacion;
    // Atributos para la consulta con INNERJOIN
    private float total;
    private String nombre_cliente;
    private String nombre_vendedor;

    public Venta() {
    }

    public Venta(int id_venta, int id_pedido, int id_vendedor, long nit, String fecha_generacion) {
        this.id_venta = id_venta;
        this.id_pedido = id_pedido;
        this.id_vendedor = id_vendedor;
        this.nit = nit;
        this.fecha_generacion = fecha_generacion;
    }

    public Venta(int id_pedido, int id_vendedor, long nit, String fecha_generacion) {
        this.id_pedido = id_pedido;
        this.id_vendedor = id_vendedor;
        this.nit = nit;
        this.fecha_generacion = fecha_generacion;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getNombre_vendedor() {
        return nombre_vendedor;
    }

    public void setNombre_vendedor(String nombre_vendedor) {
        this.nombre_vendedor = nombre_vendedor;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public long getNit() {
        return nit;
    }

    public void setNit(long nit) {
        this.nit = nit;
    }

    public String getFecha_generacion() {
        return fecha_generacion;
    }

    public void setFecha_generacion(String fecha_generacion) {
        this.fecha_generacion = fecha_generacion;
    }

    @Override
    public String toString() {
        return "Venta{" + "id_venta=" + id_venta + "\nid_pedido=" + id_pedido + "\nid_vendedor=" + id_vendedor + "\nnit=" + nit + "\nfecha_generacion=" + fecha_generacion + '}';
    }

}
