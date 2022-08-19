package arboles;

import nodos.Nodo;

public interface Arbol {
    
    public Nodo find(Object d);
    
    public Nodo getParent(Nodo x);

    public int getHeight();

    public int getDegree();

    public int countLeafs();

}
