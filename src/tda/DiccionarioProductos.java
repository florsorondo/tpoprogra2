package tda;

import model.Producto;

public class DiccionarioProductos {

    Producto[] datos;
    int capacidad;
    int cantidad;

    public DiccionarioProductos(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new Producto[capacidad];
        this.cantidad = 0;
    }

    private int existe(String codigo) {
        for (int i = 0; i < cantidad; i++) {
            if (datos[i].codigo.equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    public void insertar(Producto p) {
        int pos = existe(p.codigo);
        if (pos != -1) {
            datos[pos] = p;
            return;
        }
        if (cantidad == capacidad) {
            System.out.println("Diccionario lleno.");
            return;
        }
        datos[cantidad] = p;
        cantidad++;
    }

    public Producto buscar(String codigo) {
        int pos = existe(codigo);
        if (pos == -1) return null;
        return datos[pos];
    }

    public void mostrar() {
        for (int i = 0; i < cantidad; i++) {
            System.out.println(datos[i]);
        }
    }

    public Producto[] obtenerTodos() {
        Producto[] r = new Producto[cantidad];
        for (int i = 0; i < cantidad; i++) {
            r[i] = datos[i];
        }
        return r;
    }
}