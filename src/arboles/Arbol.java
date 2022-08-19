package arboles;

import java.util.List;

import nodos.Nodo;

public interface Arbol {
    
    public Nodo find(Object d);
    
    public Nodo getParent(Nodo child);

    public List<Object> getAncestors(Nodo arbol);
    
    public int getHeight();

    public int getMaxDegree();

    public int countLeafs();
    
}
