package org.example;

import org.example.Servicios.GestionCliente;
import org.example.Servicios.GestionProducto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menuPrincipal(scanner);
        scanner.close();
    }

    public static void menuPrincipal(Scanner scanner) {

        String seguir = "s";
        boolean entradaValida = false;
        int opcion = 0;

        GestionCliente gestionCliente = new GestionCliente();
        GestionProducto gestionProducto = new GestionProducto();

        while (seguir.equalsIgnoreCase("s")) {

            System.out.println("Ingrese lo que desea hacer:\n" +
                    "1: Ver clientes\n" +
                    "2: Eliminar clientes\n" +
                    "3: Agregar clientes\n" +
                    "4: Ver productoss\n" +
                    "5: Eliminar productoss\n" +
                    "6: Agregar productos\n" +
                    "7: Modificar productos\n" +
                    "8: Vender\n" +
                    "9: Salir");

            while (!entradaValida){
                try {
                    opcion = scanner.nextInt();
                    entradaValida = true;
                } catch (RuntimeException e) {
                    System.out.println("Caracter invalido, debe ingresar un numero");
                    scanner.nextLine();
                }
            }

            switch (opcion){
                case 1:
                    gestionCliente.listar();
                    break;
                case 2:
                    gestionCliente.eliminar(scanner);
                    break;
                case 3:
                    gestionCliente.agregar(scanner);
                    break;
                case 4:
                    gestionProducto.listar();
                    break;
                case 5:
                    gestionProducto.eliminar(scanner);
                    break;
                case 6:
                    gestionProducto.agregar(scanner);
                    break;
                case 7:
                    gestionProducto.modificarProfucto(scanner);
                    break;
                case 8:
                    gestionProducto.vender(scanner);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Numero invalido, ingrese nuevamente");
            }
            entradaValida = false;
        }
    }
}