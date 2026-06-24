package tda;

import model.Pedido;

public class ColaPedidos {
    Pedido[] cola;
    int frente;
    int fin;
    int cantidad;

    public ColaPedidos(int max) {
        cola = new Pedido[max];
        frente = 0;
        fin = -1;
        cantidad = 0;
    }

    public void encolar(Pedido p) {
        if (cantidad == cola.length) {
            System.out.println("Cola llena.");
        } else {
             // Avanza el índice "fin" de forma circular: si llega al final del array, vuelve a la posición 0
            fin = (fin + 1) % cola.length;
            cola[fin] = p;
            cantidad++;
        }
    }

    public Pedido desencolar() {
        if (cantidad == 0) {
            System.out.println("Cola vacia.");
            return null;
        }

        Pedido eliminado = cola[frente];
         // Avanza el índice "frente" de forma circular: si llega al final del array, vuelve a la posición 0
        frente = (frente + 1) % cola.length;
        cantidad--;
        return eliminado;
    }

    public void mostrar() {
        for (int i = 0; i < cantidad; i++) {
            int pos = (frente + i) % cola.length;
            System.out.println(cola[pos]);
        }
    }
}