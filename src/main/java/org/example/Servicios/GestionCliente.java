package org.example.Servicios;

import org.example.Modelos.Cliente;
import org.example.Repositorios.ClienteRepo;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionCliente {
    ClienteRepo clienteRepo = new ClienteRepo();

    public GestionCliente() {
    }
    public void listar(){
        clienteRepo.listar().forEach(System.out::println);
    }
    public void agregar(Scanner scanner){

        String seguir = "s";

        while (seguir.equalsIgnoreCase("s")) {

            Cliente.ultimoId = clienteRepo.getUltimoID();
            Cliente cliente = new Cliente();

            System.out.println("Ingrese el nombre:");
            cliente.setNombre(scanner.next());

            System.out.println("Ingrese el apellido");
            cliente.setApellido(scanner.next());

            System.out.println("Ingrese el dni");
            cliente.setDni(scanner.next());

            System.out.println("Ingrese el telefono");
            cliente.setTelefono(scanner.next());

            if (clienteRepo.existe(cliente)){
                System.out.println("Error, el cliente ya existe");
            }else{
                clienteRepo.agregar(cliente);
                System.out.println("El cliente se agrego correctamente");
            }

            System.out.println("¿Desea agregar otro cliente? s/n");
            seguir = scanner.next();
        }
    }
    public void eliminar(Scanner scanner){

        Cliente cliente = new Cliente();
        String seguir = "s";
        boolean entradaValida = false;

        while (seguir.equalsIgnoreCase("s")) {

            System.out.println("Ingrese el ID del cliente que desea eliminar");
            while (!entradaValida) {
                try {
                    cliente.setId(scanner.nextInt());
                    entradaValida = true;
                }catch (RuntimeException e){
                    System.out.println("Error, usted debe ingresar un numero entero");
                    scanner.nextLine();
                }
            }

            if(!clienteRepo.existe(cliente)){
                System.out.println("Error, el id no pertenece a ningun cliente");
            }else{
                clienteRepo.eliminar(cliente);
                System.out.println("El cliente se elimino correctamente");
            }
            System.out.println("¿Desea eliminar otro cliente? s/n");
            seguir = scanner.next();
        }
    }
}
