/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.comandas;

import Model.PedidoModel;
import Model.CartaModel;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author diegu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int num;
       
        Comanda co = new Comanda();
        PedidoModel pedido = new PedidoModel();
        
        
        while (!salir) {
            System.out.println("1. Crear pedido.");
            System.out.println("2. Eliminar pedido.");
            System.out.println("3. Marcar pedido.");
            System.out.println("4. Listar pendientes.");
            System.out.println("5. Listar carta.");
            System.out.println("6. Salir.");

            System.out.println("\n");
            num = sc.nextInt();

            try {
                switch (num) {
                    case 1:
                        System.out.println("Carta de productos: ");
                        co.listarCarta().forEach(e -> System.out.println(e));
                        System.out.println("\n");
                       
                        
                        System.out.println("Introduce un nombre para el pedido: ");
                        String nombre = sc.next();
                        
                        System.out.println("Introduce un id de producto: ");
                        int i = sc.nextInt();
                        Integer n = co.crear(i, nombre);
                       
                        if(n > 0){
                            System.out.println("Su pedido se ha realizado con éxito.");
                        }else{
                            System.out.println("Pedido no realizado.");
                        }
                        
                        
                        break;

                    case 2:
                        System.out.println("Selecciona el pedido a borrar: ");
                        pedido.setId(sc.nextInt());
                        co.borrar(pedido.getId());
                        System.out.println("Pedido con id: " + pedido.getId() + " borrado");
                        break;
                    case 3:
                        System.out.println("Selecciona el pedido que quieres marcar: ");
                        pedido.setId(sc.nextInt());
                        
                        System.out.println("Pedido actualizado.");
                        break;
                    case 4:
                        System.out.println("Las comandas pendientes son: ");
                        co.listarPendientes().forEach(e -> System.out.println(e));
                        break;
                    case 5:
                        System.out.println("Carta:");
                        co.listarCarta().forEach(e -> System.out.println(e));
                        break;
                    case 6:
                        salir = true;
                        System.out.println("Saliendo.");
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Introduce una opción correcta.");
                sc.next();
            }

        }

    }
}
