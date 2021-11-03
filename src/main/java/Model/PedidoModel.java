/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author diegu
 */
public class PedidoModel implements Serializable {

    private Integer id;
    private Integer producto_id;
    private String nombre_pedido;
    private Date fecha;
    private boolean recogido;

    public PedidoModel() {

    }

    public PedidoModel(Integer id, Integer producto_id, String nombre_pedido, Date fecha, boolean recogido) {
        this.id = id;
        this.producto_id = producto_id;
        this.nombre_pedido = nombre_pedido;
        this.recogido = recogido;
        this.fecha = fecha;

    }

    public Integer getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Integer producto_id) {
        this.producto_id = producto_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isRecogido() {
        return recogido;
    }

    public void setRecogido(boolean recogido) {
        this.recogido = recogido;
    }

    public String getNombre_pedido() {
        return nombre_pedido;
    }

    public void setNombre_pedido(String nombre_pedido) {
        this.nombre_pedido = nombre_pedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Pedido {" + "id=" + id + ", producto_id=" + producto_id + ", nombre_pedido=" + nombre_pedido + ", fecha=" + fecha + ", recogido=" + recogido + '}';
    }

}
