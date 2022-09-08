package grafos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import matricesDispersas.Matrices;

public class MatrizAdyacencia {

    private int[][] adya;

    public MatrizAdyacencia(int numVer) {
        adya = new int[numVer][numVer];
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

    public static void main(String[] args) {

        MatrizAdyacencia adya = new MatrizAdyacencia(9);

        adya.addAll(false,
                1, 2,
                1, 3,
                2, 9,
                2, 4,
                2, 5,
                4, 8,
                5, 8,
                8, 6,
                8, 7,
                6, 3,
                3, 7);
        System.out.println();
        System.out.println(Matrices.matrixRepr("adya", adya.getAdya(), 3, false, 1));

        System.out.println();

        System.out.println(adya.bfs(5));

        System.out.println(adya.bfsRecursivo(5));

    }

}