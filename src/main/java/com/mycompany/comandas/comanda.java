/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comandas;

import Model.PedidoModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diegu
 */
public class comanda {

    private static Connection con;

    static {
        String url = "jdbc:mysql://localhost:3308/comandas?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String password = "";

        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión realizada con éxito.");
        } catch (SQLException ex) {
            Logger.getLogger(comanda.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Queries
    static final String CREAR_PEDIDO = "INSERT INTO pedidos(producto_id, nombre_pedido, fecha, recogido) values (?, ?, ?, ?)";
    static final String BORRAR = "DELETE FROM pedidos where id=?";
    static final String RECOGIDO = "UPDATE FROM pedidos set recogido=1 where recogido = 0";
    static final String COMANDA_HOY = "SELECT FROM pedidos where fecha = ? and recogido = 0";
    static final String LISTAR = "SELECT * FROM carta";

    public Integer crear(PedidoModel p) {
        try ( PreparedStatement ps = con.prepareStatement(CREAR_PEDIDO, RETURN_GENERATED_KEYS)) {
            ps.setInt(1, p.getProducto_id());
            ps.setString(2, p.getNombre_pedido());
            ps.setDate(3, p.getFecha());
            ps.setBoolean(4, false);
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(comanda.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public boolean borrar(Integer id) {
        try ( PreparedStatement ps = con.prepareStatement(BORRAR)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(comanda.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ResultSet listarPendiente(String RECOGIDO) {

        Statement st;

        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(RECOGIDO);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(comanda.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
