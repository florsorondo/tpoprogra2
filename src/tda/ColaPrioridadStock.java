package tda;

import model.Producto;

public class ColaPrioridadStock {
    Producto[] cola;
    int cantidad;

    public ColaPrioridadStock(int max) {
        cola = new Producto[max];
        cantidad = 0;
    }

    public void insertar(Producto p) {
        if (cantidad == cola.length) {
            System.out.println("Cola de prioridad llena.");
            return;
        }

        int i = cantidad - 1;

        // Inserta manteniendo el array ordenado de menor a mayor stock,
        // así el producto más crítico siempre queda en la posición 0
        while (i >= 0 && cola[i].stock > p.stock) {
            cola[i + 1] = cola[i];
            i--;
        }

        cola[i + 1] = p;
        cantidad++;
    }

    public Producto eliminarPrioritario() {
        if (cantidad == 0) {
            System.out.println("No hay productos criticos.");
            return null;
        }

        Producto prioritario = cola[0];
        // Desplaza todos los elementos una posición hacia atrás,
        // para llenar el lugar que dejó el elemento eliminado (el de mayor prioridad)

        for (int i = 0; i < cantidad - 1; i++) {
            cola[i] = cola[i + 1];
        }

        cantidad--;
        return prioritario;
    }

    public void mostrar() {
        for (int i = 0; i < cantidad; i++) {
            System.out.println(cola[i]);
        }
    }
}