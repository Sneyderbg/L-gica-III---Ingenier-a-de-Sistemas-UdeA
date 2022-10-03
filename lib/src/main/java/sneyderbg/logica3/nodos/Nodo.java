package sneyderbg.logica3.nodos;

public abstract class Nodo {

    protected Object dato;

    public Nodo() {
        this.dato = null;
    }

    public Nodo(Object dato) {
        this.dato = dato;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

}
