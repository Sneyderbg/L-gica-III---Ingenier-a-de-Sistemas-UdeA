package grafos;

import matricesDispersas.Matrices;

public class MatrizIncidencia {

    private int[][] inci;

    public MatrizIncidencia(int m, int n) {

        inci = new int[m][n];

    }

    public int[][] getMatrix() {
        return inci;
    }

    public int getRows() {
        return inci.length;
    }

    public int getCols() {
        return inci[0].length;
    }

    public void addAll(boolean dirigido, int... vi_lj) {

        for (int i = 0; i < vi_lj.length; i += 2) {

            inci[vi_lj[i] - 1][vi_lj[i + 1] - 1] = 1;

        }

    }

    public static void main(String[] args) {

        MatrizIncidencia inci = new MatrizIncidencia(7, 6);

        inci.addAll(false,
                1, 4,
                1, 2,
                2, 4,
                2, 5,
                2, 1,
                4, 5,
                5, 1,
                3, 2,
                3, 3,
                3, 6,
                6, 3,
                7, 6);

        System.out.println();
        System.out.println(Matrices.matrixRepr("inci", inci.getMatrix(), 3, false, 0));

        System.out.println(inci.getRows());
        
    }

}
