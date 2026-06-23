package tda;

import model.Producto;

public class DiccionarioProductos {

    String[] claves;
    Producto[] valores;
    int capacidad;
    int cantidad;

    public DiccionarioProductos(int capacidad) {
        this.capacidad = capacidad;
        this.claves = new String[capacidad];
        this.valores = new Producto[capacidad];
        this.cantidad = 0;
    }

    private int buscarPosicion(String codigo) {
        for (int i = 0; i < cantidad; i++) {
            if (claves[i].equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    public void insertar(Producto p) {
        int pos = buscarPosicion(p.codigo);
        if (pos != -1) {
            valores[pos] = p;
            return;
        }
        if (cantidad == capacidad) {
            System.out.println("Diccionario lleno.");
            return;
        }
        claves[cantidad] = p.codigo;
        valores[cantidad] = p;
        cantidad++;
    }

    public Producto buscar(String codigo) {
        int pos = buscarPosicion(codigo);
        if (pos == -1) return null;
        return valores[pos];
    }

    public void mostrar() {
        for (int i = 0; i < cantidad; i++) {
            System.out.println(valores[i]);
        }
    }

    public Producto[] obtenerTodos() {
        Producto[] r = new Producto[cantidad];
        for (int i = 0; i < cantidad; i++) {
            r[i] = valores[i];
        }
        return r;
    }
}
