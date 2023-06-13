package org.example.Repositorios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.Modelos.Cliente;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ClienteRepo implements IRepository<Cliente> {
    private final File pathJson = new File("src/main/java/org/example/Archivos/clientes.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private ArrayList<Cliente> clientes;

    public ClienteRepo() {
    }

    @Override
    public void cargar(){
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class,Cliente.class);
            this.clientes = mapper.readValue(pathJson,collectionType);
        }catch (IOException e){
            this.clientes = new ArrayList<>();
        }
    }
    @Override
    public void guardar(){
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(pathJson,clientes);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public ArrayList<Cliente> listar(){
        cargar();
        return this.clientes;
    }
    @Override
    public void agregar(Cliente...cliente){
        cargar();
        this.clientes.addAll(Arrays.asList(cliente));
        guardar();
    }
    @Override
    public void eliminar(Cliente cliente){
        cargar();
        this.clientes.remove(cliente);
        guardar();
    }
    @Override
    public int getUltimoID(){
        cargar();
        if(clientes.isEmpty()){
            return 0;
        }else {
            return clientes.get(clientes.size() - 1).getId();
        }
    }
    @Override
    public boolean existe(Cliente cliente){
        cargar();
        if(this.clientes.contains(cliente)){
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public void modificar(Cliente cliente){
        cargar();
        for (Cliente c : this.clientes){
            if(c.equals(cliente)){
                int index = clientes.indexOf(c);
                clientes.set(index,cliente);
                break;
            }
        }
        guardar();
    }
}
