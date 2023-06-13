package org.example.Repositorios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.Modelos.Producto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ProductoRepo implements IRepository<Producto> {
    private final File pathJson = new File("src/main/java/org/example/Archivos/productos.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private ArrayList<Producto> productos;

    public ProductoRepo() {
    }

    @Override
    public void cargar(){
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Producto.class);
            this.productos = mapper.readValue(pathJson,collectionType);
        }catch (IOException e){
            this.productos = new ArrayList<>();
        }
    }
    @Override
    public void guardar(){
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(pathJson,productos);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public ArrayList<Producto> listar(){
        cargar();
        return this.productos;
    }
    @Override
    public void agregar(Producto...producto){
        cargar();
        this.productos.addAll(Arrays.asList(producto));
        guardar();
    }
    @Override
    public void eliminar(Producto producto){
        cargar();
        this.productos.remove(producto);
        guardar();
    }
    @Override
    public int getUltimoID(){
        cargar();
        if(productos.isEmpty()){
            return 0;
        }else {
            return productos.get(productos.size() - 1).getId();
        }
    }
    @Override
    public boolean existe(Producto producto){
        cargar();
        if(this.productos.contains(producto)){
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public void modificar(Producto producto){
        cargar();
        for (Producto p : this.productos){
            if(p.equals(producto)){
                producto.setStock(p.getStock());
                producto.setCategoria(p.getCategoria());
                int index = productos.indexOf(p);
                productos.set(index,producto);
                break;
            }
        }
        guardar();
    }
}
