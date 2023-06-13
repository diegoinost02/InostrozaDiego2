package org.example.Servicios;

import org.example.Modelos.Cliente;
import org.example.Modelos.Producto;
import org.example.Repositorios.ClienteRepo;
import org.example.Repositorios.ProductoRepo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GestionProducto {
    ProductoRepo productoRepo = new ProductoRepo();

    public GestionProducto() {
    }
    public void listar() {
        productoRepo.listar().forEach(System.out::println);
    }
    public void agregar(Scanner scanner){

        String seguir = "s";
        boolean entradaValida = false;

        while (seguir.equalsIgnoreCase("s")) {

            Producto.ultimoId = productoRepo.getUltimoID();
            Producto producto = new Producto();

            System.out.println("Ingrese la descripcion");
            producto.setDescripcion(scanner.next());

            System.out.println("Ingrese el precio");
            while (!entradaValida) {
                try {
                    producto.setPrecio(scanner.nextDouble());
                    entradaValida = true;
                } catch (RuntimeException e) {
                    System.out.println("Error, usted debe ingresar un double");
                    scanner.nextLine();
                }
            }
            entradaValida = false;

            System.out.println("Ingrese el stock");
            while (!entradaValida) {
                try {
                    producto.setStock(scanner.nextInt());
                    entradaValida = true;
                } catch (RuntimeException e) {
                    System.out.println("Error, usted debe ingresar un entero");
                    scanner.nextLine();
                }
            }
            entradaValida = false;

            int opcion = 0;
            System.out.println("Ingrese la categoria\n" +
                    "1: Frutas\n" +
                    "2: Verduras\n" +
                    "3: Panaderia\n" +
                    "4: Congelados");

            while (!entradaValida){
                try {
                    opcion = scanner.nextInt();
                    entradaValida = true;
                } catch (RuntimeException e) {
                    System.out.println("Caracter invalido, debe ingresar un numero");
                    scanner.nextLine();
                }
            }
            entradaValida = false;
            while (!entradaValida) {
                switch (opcion) {
                    case 1:
                        producto.setCategoria(Producto.Categoria.FRUTAS);
                        entradaValida = true;
                        break;
                    case 2:
                        producto.setCategoria(Producto.Categoria.VERDURAS);
                        entradaValida = true;
                        break;
                    case 3:
                        producto.setCategoria(Producto.Categoria.PANADERÍA);
                        entradaValida = true;
                        break;
                    case 4:
                        producto.setCategoria(Producto.Categoria.CONGELADOS);
                        entradaValida = true;
                        break;
                    default:
                        System.out.println("Ingreso un numero invalido, vuelva a intentarlo");
                        while (!entradaValida){
                            try {
                                opcion = scanner.nextInt();
                                entradaValida = true;
                            } catch (RuntimeException e) {
                                System.out.println("Caracter invalido, debe ingresar un numero");
                                scanner.nextLine();
                            }
                        }
                        entradaValida = false;
                        break;
                }
            }
            entradaValida = false;

            if (productoRepo.existe(producto)) {
                System.out.println("Error, el producto ya existe");
            } else {
                productoRepo.agregar(producto);
                System.out.println("El producto se agrego correctamente");
            }

            System.out.println("¿Desea agregar otro producto? s/n");
            seguir = scanner.next();
        }
    }

    public void eliminar(Scanner scanner) {

        Producto producto = new Producto();
        String seguir = "s";
        boolean entradaValida = false;

        while (seguir.equalsIgnoreCase("s")) {

            System.out.println("Ingrese el ID del producto que desea eliminar");
            while (!entradaValida) {
                try {
                    producto.setId(scanner.nextInt());
                    entradaValida = true;
                } catch (RuntimeException e) {
                    System.out.println("Error, usted debe ingresar un numero entero");
                    scanner.nextLine();
                }
            }
            if (!productoRepo.existe(producto)) {
                System.out.println("Error, el ID no pertenece a nigun producto");
            } else {
                productoRepo.eliminar(producto);
                System.out.println("El producto se elimino correctamente");
            }

            System.out.println("¿Desea eliminar otro producto? s/n");
            seguir = scanner.next();
        }
    }
    public void modificarProfucto(Scanner scanner){

        Producto producto = new Producto();
        String seguir = "s";
        boolean entradaValida = false;

        while (seguir.equalsIgnoreCase("s")) {

            System.out.println("Ingrese el ID del producto que desea eliminar");
            while (!entradaValida) {
                try {
                    producto.setId(scanner.nextInt());
                    entradaValida = true;
                }catch (RuntimeException e){
                    System.out.println("Error, usted debe ingresar un numero entero");
                    scanner.nextLine();
                }
            }

            if(!productoRepo.existe(producto)){
                System.out.println("Error, el ID no pertenece a nigun producto");
            }else{
                System.out.println("Ingrese la nueva descripcion: ");
                producto.setDescripcion(scanner.next());

                System.out.println("Ingrese el nuevo precio");
                producto.setPrecio(scanner.nextInt());

                productoRepo.modificar(producto);
                System.out.println("El producto se modifico correctamente");
            }
            System.out.println("¿Desea modificar otro producto? s/n");
            seguir = scanner.next();
        }
    }
    public void vender(Scanner scanner){

        ClienteRepo clienteRepo = new ClienteRepo();
        Random random = new Random();

        ArrayList<Cliente> clientes = clienteRepo.listar();
        Cliente cliente = clientes.get(random.nextInt(0,clientes.size()));

        ArrayList<Producto> productos = productoRepo.listar();
        Producto producto = productos.get(random.nextInt(0, productos.size()));

        String compra = "";
        int cantidad = random.nextInt(1, producto.getStock());

        System.out.println("El cliente: " + cliente + " desea comprar " + cantidad + " " + producto.getDescripcion() +
                "\n desea efectuar la compra? s/n");
        compra = scanner.next();

        if(compra.equalsIgnoreCase("s")){
            producto.setStock(producto.getStock()-cantidad);
            productoRepo.modificar(producto);
            System.out.println("Se efectuo la compra");
        }else{
            System.out.println("No se efectuo la compra");
        }
    }
}
