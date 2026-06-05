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