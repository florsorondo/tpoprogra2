public class GrafoDeposito {
    String[] sectores;
    boolean[][] conexiones;
    int cantidad;

    public GrafoDeposito(int max) {
        sectores = new String[max];
        conexiones = new boolean[max][max];
        cantidad = 0;
    }

    public boolean agregarSector(String nombre) {
        if (cantidad == sectores.length) return false;
        for (int i = 0; i < cantidad; i++) {
            if (sectores[i].equals(nombre)) return false;
        }
        sectores[cantidad++] = nombre;
        return true;
    }

    public boolean agregarConexion(String s1, String s2) {
        int i = indexOf(s1);
        int j = indexOf(s2);
        if (i == -1 || j == -1) return false;
        conexiones[i][j] = true;
        conexiones[j][i] = true;
        return true;
    }

    private int indexOf(String sector) {
        for (int i = 0; i < cantidad; i++) {
            if (sectores[i].equals(sector)) return i;
        }
        return -1;
    }

    // BFS para encontrar la ruta mas corta entre dos sectores
    public String calcularRuta(String origen, String destino) {
        int inicio = indexOf(origen);
        int fin = indexOf(destino);
        if (inicio == -1) return "Sector de origen no encontrado.";
        if (fin == -1) return "Sector de destino no encontrado.";
        if (inicio == fin) return "Ya esta en el sector: " + origen;

        boolean[] visitado = new boolean[cantidad];
        int[] padre = new int[cantidad];
        int[] queue = new int[cantidad];
        for (int i = 0; i < cantidad; i++) padre[i] = -1;

        int frente = 0, fondo = 0;
        queue[fondo++] = inicio;
        visitado[inicio] = true;
        boolean encontrado = false;

        while (frente < fondo && !encontrado) {
            int actual = queue[frente++];
            for (int i = 0; i < cantidad; i++) {
                if (conexiones[actual][i] && !visitado[i]) {
                    visitado[i] = true;
                    padre[i] = actual;
                    if (i == fin) {
                        encontrado = true;
                        break;
                    }
                    queue[fondo++] = i;
                }
            }
        }

        if (!encontrado) return "No existe ruta entre " + origen + " y " + destino + ".";

        // Reconstruir camino desde destino hacia origen y luego invertir
        String[] camino = new String[cantidad];
        int largo = 0;
        int nodo = fin;
        while (nodo != -1) {
            camino[largo++] = sectores[nodo];
            nodo = padre[nodo];
        }

        String ruta = "";
        for (int i = largo - 1; i >= 0; i--) {
            ruta += camino[i];
            if (i > 0) ruta += " -> ";
        }
        return ruta;
    }

    public void mostrar() {
        if (cantidad == 0) {
            System.out.println("No hay sectores registrados.");
            return;
        }
        for (int i = 0; i < cantidad; i++) {
            String linea = "  " + sectores[i] + " -> ";
            boolean hay = false;
            for (int j = 0; j < cantidad; j++) {
                if (conexiones[i][j]) {
                    linea += sectores[j] + "  ";
                    hay = true;
                }
            }
            if (!hay) linea += "(sin conexiones)";
            System.out.println(linea);
        }
    }
}
