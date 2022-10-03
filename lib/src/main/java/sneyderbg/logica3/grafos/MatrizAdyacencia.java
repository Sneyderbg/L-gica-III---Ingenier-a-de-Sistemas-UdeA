package sneyderbg.logica3.grafos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import sneyderbg.logica3.listasLigadas.listas.LSL;
import sneyderbg.logica3.nodos.NodoSimple;
import sneyderbg.logica3.util.Matrices;

public class MatrizAdyacencia {

    private int[][] adya;

    public MatrizAdyacencia(int numVer) {
        adya = new int[numVer][numVer];
    }

    public MatrizAdyacencia(int[][] adya) {
        this.adya = adya;
    }

    public int[][] getAdya() {
        return adya;
    }

    public int[][] copyAdya() {

        int[][] copy = new int[adya.length][adya[0].length];

        for (int i = 0; i < adya.length; i++) {

            copy[i] = Arrays.copyOf(adya[i], adya[0].length);

        }

        return copy;

    }

    private int getRows() {
        return adya.length;
    }

    public int getCols() {
        return adya[0].length;
    }

    public void addAll(boolean dirigido, int... vi_vj) {

        for (int i = 0; i < vi_vj.length; i += 2) {

            adya[vi_vj[i] - 1][vi_vj[i + 1] - 1] = 1;

            if (!dirigido) {

                adya[vi_vj[i + 1] - 1][vi_vj[i] - 1] = 1;

            }

        }

    }

    public boolean areAdjacent(int v1, int v2) {

        return adya[v1][v2] == 1;
    }

    public String bfs(int v) {

        v = v - 1; // 1 -> 0

        StringBuilder bfs = new StringBuilder();

        int[] visited = new int[adya.length];

        Queue<Integer> queue = new LinkedList<>();

        visited[v] = 1;
        queue.add(v);

        while (!queue.isEmpty()) {

            v = queue.poll();
            bfs.append(v + 1);
            bfs.append(", ");
            for (int w = 0; w < adya[0].length; w++) {

                if (adya[v][w] == 1) {

                    if (visited[w] == 0) {
                        visited[w] = 1;
                        queue.add(w);
                    }

                }

            }

        }

        bfs.setLength(bfs.length() - 2);

        return bfs.toString();

    }

    private StringBuilder bfsRecursivo(int v, boolean[] visited, Queue<Integer> queue) {

        v = v - 1; // 1 -> 0, normalizar

        StringBuilder bfs = new StringBuilder();

        if (!visited[v]) {
            bfs.append(v + 1);
            visited[v] = true;
        }

        for (int w = 0; w < adya[v].length; w++) {

            if (adya[v][w] == 1) {
                if (!visited[w]) {
                    visited[w] = true;
                    queue.add(w);
                    bfs.append(", ");
                    bfs.append(w + 1);
                }
            }

        }

        while (!queue.isEmpty()) {

            v = queue.poll() + 1;
            bfs.append(bfsRecursivo(v, visited, queue));

        }

        return bfs;

    }

    public StringBuilder bfsRecursivo(int v) {

        return bfsRecursivo(v, new boolean[getRows()], new LinkedList<>());

    }

    public void dijkstra(int v, int[] costoMinimo, int[] ruta) {

        int n = getCols();
        boolean yaCalculado[] = new boolean[getRows()];
        int i, w, j;

        for (i = 0; i < n; i++) {

            costoMinimo[i] = adya[v][i];
            yaCalculado[i] = false;
            ruta[i] = i;

        }

        yaCalculado[v] = true;
        i = 0;

        while (i < n - 1) {

            j = 0;
            while (yaCalculado[j]) {
                j++;
            }

            w = j;

            for (j = w + 1; j < n; j++) {

                if (!yaCalculado[j] && costoMinimo[j] < costoMinimo[w]) {
                    w = j;
                }

            }

            yaCalculado[w] = true;
            i++;

            for (j = 0; j < n; j++) {

                if (yaCalculado[j])
                    continue;

                // else
                if (costoMinimo[w] + adya[w][j] < costoMinimo[j]) {

                    costoMinimo[j] = costoMinimo[w] + adya[w][j];
                    ruta[j] = w;

                }

            }

        }

    }

    public int[][] floyd() {

        int[][] costosMinimos = new int[getCols()][getCols()];

        for (int i = 0; i < costosMinimos.length; i++) {
            for (int j = 0; j < costosMinimos.length; j++) {
                costosMinimos[i][j] = adya[i][j];
            }
        }

        int aux;
        for (int k = 0; k < costosMinimos.length; k++) {

            for (int i = 0; i < costosMinimos.length; i++) {

                for (int j = 0; j < costosMinimos.length; j++) {

                    aux = costosMinimos[i][k] + costosMinimos[k][j];
                    costosMinimos[i][j] = Math.min(aux, costosMinimos[i][j]);

                }

            }

        }

        return costosMinimos;

    }

    public ListasLigadasAdyacencia toLLA() {

        ListasLigadasAdyacencia lla = new ListasLigadasAdyacencia(getCols());

        for (int i = 0; i < adya.length; i++) {

            for (int j = 0; j < adya[i].length; j++) {

                if (adya[i][j] == 0)
                    continue;

                if (lla.getLSL(i) == null) {

                    lla.setLSL(i, new LSL());

                }

                lla.getLSL(i).add(new NodoSimple(j));

            }

        }

        return lla;

    }

    public static void main(String[] args) {

        int[][] a = { { 0, 1, 1, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 1, 0 } };

        MatrizAdyacencia adya = new MatrizAdyacencia(a);

        System.out.println(Matrices.matrixRepr("adya", adya.getAdya(), 2, false, 0));

        adya.toLLA().show();

    }

}