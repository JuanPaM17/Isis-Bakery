/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesNecesarias;

import java.util.ArrayList;

/**
 *
 * @author sarit
 */
public class Carrito {

    private int idproducto;
    private String nombreProducto;
    private float precio;
    private int cantidad;
    private float subTotal;
    private int stock;

    public Carrito() {
    }

    public Carrito(int idproducto, String nombreProducto, float precio, int cantidad, float subTotal) {
        this.idproducto = idproducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombre_producto) {
        this.nombreProducto = nombre_producto;
    }

    public float getPrecioCompra() {
        return precio;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precio = precioCompra;
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

    @Override
    public String toString() {
        return "Carrito{" + ", idProducto=" + idproducto + ", nombre_producto=" + nombreProducto + ", precioCompra=" + precio + ", cantidad=" + cantidad + ", subTotal=" + subTotal + '}';
    }

    public ArrayList<Carrito> TransformarVentaTabla(ArrayList<VentaTabla> listaVentaTabla) {
        ArrayList<Carrito> listaCarrito = new ArrayList<>();
        for (int i = 0; i < listaVentaTabla.size(); i++) {
            Carrito carrito = new Carrito();
            carrito.setIdproducto(listaVentaTabla.get(i).getId_producto());
            carrito.setCantidad(listaVentaTabla.get(i).getCantidad());
            carrito.setSubTotal(listaVentaTabla.get(i).getSubtotal());
            listaCarrito.add(carrito);
        }

        return listaCarrito;
    }

}
