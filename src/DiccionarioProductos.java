public class DiccionarioProductos {
    // Tabla hash con direccionamiento abierto (sondeo lineal).
    // Permite localizar un producto por su codigo SIN recorrer todo el arreglo,
    // tal como pide el enunciado ("sin realizar recorridos lineales").
    Producto[] tabla;
    int capacidad;
    int cantidad;

    public DiccionarioProductos(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new Producto[capacidad];
        this.cantidad = 0;
    }

    // Funcion de hash: convierte el codigo en una posicion del arreglo.
    private int hash(String codigo) {
        int h = 0;
        for (int i = 0; i < codigo.length(); i++) {
            h = (h * 31 + codigo.charAt(i)) % capacidad;
        }
        return Math.abs(h);
    }

    public void insertar(Producto p) {
        if (cantidad == capacidad) {
            System.out.println("Diccionario lleno.");
            return;
        }
        int pos = hash(p.codigo);
        // Si la posicion esta ocupada por otro codigo, busca la siguiente libre.
        while (tabla[pos] != null) {
            if (tabla[pos].codigo.equals(p.codigo)) {
                tabla[pos] = p; // mismo codigo: actualiza
                return;
            }
            pos = (pos + 1) % capacidad;
        }
        tabla[pos] = p;
        cantidad++;
    }

    // Busqueda directa por hash (no recorre todo el deposito).
    public Producto buscar(String codigo) {
        int pos = hash(codigo);
        int recorridos = 0;
        while (tabla[pos] != null && recorridos < capacidad) {
            if (tabla[pos].codigo.equals(codigo)) {
                return tabla[pos];
            }
            pos = (pos + 1) % capacidad;
            recorridos++;
        }
        return null;
    }

    public void mostrar() {
        for (int i = 0; i < capacidad; i++) {
            if (tabla[i] != null) {
                System.out.println(tabla[i]);
            }
        }
    }

    // Devuelve todos los productos en un arreglo compacto.
    // Sirve para reconstruir otras estructuras (ej: la cola de stock critico).
    public Producto[] obtenerTodos() {
        Producto[] r = new Producto[cantidad];
        int k = 0;
        for (int i = 0; i < capacidad; i++) {
            if (tabla[i] != null) {
                r[k++] = tabla[i];
            }
        }
        return r;
    }
}
