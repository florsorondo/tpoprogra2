package tda;

import model.Movimiento;

public class PilaMovimientos {
    Movimiento[] pila;
    int tope;

    public PilaMovimientos(int max) {
        pila = new Movimiento[max];
        tope = -1;
    }

    public void apilar(Movimiento m) {
         // pila.length - 1 es la última posición válida del array; si "tope" ya está ahí, no hay más lugar
        if (tope == pila.length - 1) {
            System.out.println("Pila llena.");
        } else {
            tope++;
            pila[tope] = m;
        }
    }

    public Movimiento desapilar() {
        if (tope == -1) {
            System.out.println("Pila vacia.");
            return null;
        }

        Movimiento eliminado = pila[tope];
        tope--;
        return eliminado;
    }
    public boolean estaVacia() {
        return tope == -1;
    }

    public void mostrar() {
        if (tope == -1) {
            System.out.println("No hay movimientos registrados ");
            return;
        }
        System.out.println("Historial de Movimientos:");
        for (int i = tope; i >= 0; i--) {
            System.out.println(pila[i]);
        }
    }

}