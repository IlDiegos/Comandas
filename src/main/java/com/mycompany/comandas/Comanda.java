/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comandas;

import Model.CartaModel;
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

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author diegu
 */
public class Comanda {

    private static Connection con;

    static {
        String url = "jdbc:mysql://localhost:3308/comandas?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String password = "";

        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión realizada con éxito.");
        } catch (SQLException ex) {
            Logger.getLogger(Comanda.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Queries
    static final String CREAR_PEDIDO = "INSERT INTO pedidos(producto_id, nombre_pedido, fecha, recogido) values (?, ?, ?, ?)";
    static final String BORRAR = "DELETE FROM pedidos where id=?";
    static final String RECOGIDO = "UPDATE pedidos set recogido=1 where id=?";
    static final String COMANDA_HOY = "SELECT * FROM pedidos where fecha = CURDATE() and recogido = 0";
    static final String LISTAR = "SELECT * FROM carta";

    java.util.Date utilDate = new java.util.Date();
    long lnMilisegundos = utilDate.getTime();
    java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);

    public boolean marcar(int i) {

        try ( PreparedStatement ps = con.prepareStatement(RECOGIDO)) {
            ps.setInt(1, i);
            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            Logger.getLogger(Comanda.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ArrayList<CartaModel> listarCarta() {

        var salida = new ArrayList<CartaModel>();

        try ( Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(LISTAR);

            while (rs.next()) {
                CartaModel c = new CartaModel();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setPrecio(rs.getDouble("precio"));
                salida.add(c);

            }
            return salida;
        } catch (SQLException ex) {
            Logger.getLogger(Comanda.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ArrayList<PedidoModel> listarPendientes() {

        var salida = new ArrayList<PedidoModel>();

        try ( Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(COMANDA_HOY);

            while (rs.next()) {
                PedidoModel p = new PedidoModel();
                p.setId(rs.getInt("id"));
                p.setProducto_id(rs.getInt("producto_id"));
                p.setNombre_pedido(rs.getString("nombre_pedido"));
                p.setFecha(rs.getDate("fecha"));
                p.setRecogido(rs.getBoolean("recogido"));
                salida.add(p);

            }
            return salida;
        } catch (SQLException ex) {
            Logger.getLogger(Comanda.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public Integer crear(int i, String nombre) {
        try ( PreparedStatement ps = con.prepareStatement(CREAR_PEDIDO, RETURN_GENERATED_KEYS)) {
            ps.setInt(1, i);
            ps.setString(2, nombre);
            ps.setDate(3, sqlDate);
            ps.setBoolean(4, false);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            } else {
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Comanda.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public boolean borrar(Integer id) {
        try ( PreparedStatement ps = con.prepareStatement(BORRAR)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(Comanda.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
