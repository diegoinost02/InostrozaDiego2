package org.example.Repositorios;

import org.example.Modelos.Producto;

import java.util.ArrayList;
public interface IRepository <T>{
    void cargar();
    void guardar();
    ArrayList<T> listar();
    void agregar(T...objeto);
    void eliminar(T objeto);
    int getUltimoID();
    boolean existe(T objeto);
    void modificar(T objeto);
}