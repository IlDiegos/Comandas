/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.comandas;

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
        var prueba = new comanda();
        boolean salir = false;
        int num;

        while (!salir) {
            System.out.println("1. Crear pedido.");
            System.out.println("2. Eliminar pedido.");
            System.out.println("3. Marcar pedido.");
            System.out.println("4. Listar pendientes.");
            System.out.println("5. Listar disponibles.");
            System.out.println("6. Salir.");

            System.out.println("\n");
            num = sc.nextInt();

            try {
                switch (num) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
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
