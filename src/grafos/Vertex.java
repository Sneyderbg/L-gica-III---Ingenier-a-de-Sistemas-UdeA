package grafos;

// TODO tampoco c
public class Vertex<T> {

    private int id;

    private T dato;

    public Vertex(int id) {
        this.id = id;
    }

    public Vertex(int id, T dato) {

        this.id = id;
        this.dato = dato;

    }

    public int getId() {
        return id;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

}
