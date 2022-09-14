package grafos;

// TODO no c
public abstract class Grafo {

    private int numVer, numLados;

    public Grafo(int numVer, int numLados) {

        this.numVer = numVer;
        this.numLados = numLados;

    }

    public abstract boolean areAdjacent(int v1, int v2);

    public int getNumVer() {
        return numVer;
    }

    public int getNumLados() {
        return numLados;
    }

}
