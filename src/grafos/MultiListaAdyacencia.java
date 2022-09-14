package grafos;

import java.util.ArrayList;
import java.util.List;

import utils.Lines;

public class MultiListaAdyacencia {

    private NodoAdya[] vec;

    public MultiListaAdyacencia(int numVertices) {

        vec = new NodoAdya[numVertices];

    }

    public void add(int vi, int vj) {

        NodoAdya x = vec[vi];

        if (x == null) {
            vec[vi] = new NodoAdya(vi, vj);
            return;
        }

        NodoAdya aX = null;
        while (x != null) {

            aX = x;
            x = x.getVi() == vi ? x.getLvi() : x.getLvj();

        }

        x = new NodoAdya(vi, vj);
        if (aX.getVi() == vi) {
            aX.setLvi(x);
        } else {
            aX.setLvj(x);
        }

    }

    public void addAll(boolean esDirigido, int... vi_vj) {

        for (int i = 0; i < vi_vj.length; i += 2) {

            add(vi_vj[i], vi_vj[i + 1]);

            if (!esDirigido) {
                add(vi_vj[i + 1], vi_vj[i]);
            }

        }

    }

    public List<StringBuilder> consMLARepr() {

        List<StringBuilder> lines = new ArrayList<>();

        lines.add(new StringBuilder((Lines.TOP_T + Lines.HORIZONTAL.repeat(8)).repeat(vec.length))
                .append(Lines.TOP_RIGHT).replace(0, 1, Lines.TOP_LEFT));

        StringBuilder sb = new StringBuilder(Lines.VERTICAL);
        for (NodoAdya x : vec) {

            sb.append(String.format("%8s%s", x == null ? "" : Integer.toHexString(x.hashCode()), Lines.VERTICAL));

        }
        lines.add(sb);

        lines.add(new StringBuilder((Lines.BOTTOM_T + Lines.HORIZONTAL.repeat(8)).repeat(vec.length))
                .append(Lines.BOTTOM_RIGHT).replace(0, 1, Lines.BOTTOM_LEFT));

        List<NodoAdya> allNodes = getAllNodes();
        List<StringBuilder> nodeRepr;

        for (NodoAdya x : allNodes) {

            nodeRepr = x.consNodeRepr(8);

            for (StringBuilder sbX : nodeRepr) {
                lines.add(sbX);
            }

        }

        return lines;

    }

    public List<NodoAdya> getAllNodes() {

        List<NodoAdya> nodes = new ArrayList<NodoAdya>();

        for (NodoAdya x : vec) {

            while (x != null) {

                if (nodes.contains(x))
                    continue;

                nodes.add(x);
                x = x.getLvi();

            }

        }

        return nodes;

    }

    public void show() {

        List<StringBuilder> repr = consMLARepr();

        for (StringBuilder sb : repr) {

            System.out.println(sb);

        }

    }

    public static void main(String[] args) {

        MultiListaAdyacencia A = new MultiListaAdyacencia(5);
        A.addAll(false,
                1, 2,
                1, 3,
                1, 4,
                2, 3,
                2, 4,
                3, 4);

        A.show();

    }

}
