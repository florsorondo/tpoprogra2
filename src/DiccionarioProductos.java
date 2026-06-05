public class DiccionarioProductos {
    Producto[] productos;
    int cantidad;

    public DiccionarioProductos(int max) {
        productos = new Producto[max];
        cantidad = 0;
    }

    public void insertar(Producto p) {
        if (cantidad < productos.length) {
            productos[cantidad] = p;
            cantidad++;
        } else {
            System.out.println("Diccionario lleno.");
        }
    }

    public Producto buscar(String codigo) {
        for (int i = 0; i < cantidad; i++) {
            if (productos[i].codigo.equals(codigo)) {
                return productos[i];
            }
        }
        return null;
    }

    public void mostrar() {
        for (int i = 0; i < cantidad; i++) {
            System.out.println(productos[i]);
        }
    }
}