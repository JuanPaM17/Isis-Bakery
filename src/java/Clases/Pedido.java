/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author sarit
 */
public class Pedido {

    private int id_pedido;
    private int id_cliente;
    private float total;
    private String fecha_recogida;
    private String hora_recogida;
    private String nombre_recoge;
    private long telefono_contacto;
    private int id_estado_pedido;
    // Atributos para consulta con innerJoin
    private String nombre_cliente;

    public Pedido() {
    }

    public Pedido(int id_pedido, int id_cliente, float total, String fecha_recogida, String hora_recogida, String nombre_recoge, long telefono_contacto, int id_estado_pedido) {
        this.id_pedido = id_pedido;
        this.id_cliente = id_cliente;
        this.total = total;
        this.fecha_recogida = fecha_recogida;
        this.hora_recogida = hora_recogida;
        this.nombre_recoge = nombre_recoge;
        this.telefono_contacto = telefono_contacto;
        this.id_estado_pedido = id_estado_pedido;
    }

    public Pedido(int id_cliente, float total, String fecha_recogida, String hora_recogida, String nombre_recoge, long telefono_contacto, int id_estado_pedido) {
        this.id_cliente = id_cliente;
        this.total = total;
        this.fecha_recogida = fecha_recogida;
        this.hora_recogida = hora_recogida;
        this.nombre_recoge = nombre_recoge;
        this.telefono_contacto = telefono_contacto;
        this.id_estado_pedido = id_estado_pedido;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFecha_recogida() {
        return fecha_recogida;
    }

    public void setFecha_recogida(String fecha_recogida) {
        this.fecha_recogida = fecha_recogida;
    }

    public String getHora_recogida() {
        return hora_recogida;
    }

    public void setHora_recogida(String hora_recogida) {
        this.hora_recogida = hora_recogida;
    }

    public String getNombre_recoge() {
        return nombre_recoge;
    }

    public void setNombre_recoge(String nombre_recoge) {
        this.nombre_recoge = nombre_recoge;
    }

    public long getTelefono_contacto() {
        return telefono_contacto;
    }

    public void setTelefono_contacto(long telefono_contacto) {
        this.telefono_contacto = telefono_contacto;
    }

    public int getId_estado_pedido() {
        return id_estado_pedido;
    }

    public void setId_estado_pedido(int id_estado_pedido) {
        this.id_estado_pedido = id_estado_pedido;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id_pedido=" + id_pedido + "\nid_cliente=" + id_cliente + "\ntotal=" + total + "\nfecha_recogida=" + fecha_recogida + "\nhora_recogida=" + hora_recogida + "\nnombre_recoge=" + nombre_recoge + "\ntelefono_contacto=" + telefono_contacto + "\nid_estado_pedido=" + id_estado_pedido + '}';
    }

}
