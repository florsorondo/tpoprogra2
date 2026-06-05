public class PilaMovimientos {
    Movimiento[] pila;
    int tope;

    public PilaMovimientos(int max) {
        pila = new Movimiento[max];
        tope = -1;
    }

    public void apilar(Movimiento m) {
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
}