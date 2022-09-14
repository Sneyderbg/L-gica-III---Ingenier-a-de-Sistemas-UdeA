package grafos;

import java.util.ArrayList;
import java.util.List;

import listasLigadas.listas.LSL;
import nodos.NodoSimple;
import utils.LinesChars;

public class ListasLigadasAdyacencia {

    private LSL[] vec;

    public ListasLigadasAdyacencia(int numVertices) {

        vec = new LSL[numVertices];

    }

    public void addAll(boolean esDirigido, int... vi_vj) {

        LSL lsl;

        for (int i = 0; i < vi_vj.length; i += 2) {

            lsl = vec[vi_vj[i]];

            if (lsl == null) {
                lsl = new LSL();
            }

            lsl.add(new NodoSimple(vi_vj[i + 1]));
            vec[vi_vj[i]] = lsl;

            if (!esDirigido) {

                lsl = vec[vi_vj[i + 1]];

                if (lsl == null) {
                    lsl = new LSL();
                }

                lsl.add(new NodoSimple(vi_vj[i]));
                vec[vi_vj[i + 1]] = lsl;

            }
            

        }

    }

    private List<StringBuilder> consVerticalVec() {

        List<StringBuilder> lines = new ArrayList<StringBuilder>();

        StringBuilder top, mid, bottom;

        lines.add(new StringBuilder("   vec"));

        for (int i = 0; i < vec.length; i++) {

            top = new StringBuilder(String.format("   %s%s%s",
                    i == 0 ? LinesChars.TOP_LEFT : LinesChars.LEFT_T,
                    Character.toString(LinesChars.HORIZONTAL).repeat(4),
                    i == 0 ? LinesChars.TOP_RIGHT : LinesChars.RIGHT_T));

            mid = new StringBuilder(String.format("%2s %s%s%s",
                    i,
                    LinesChars.VERTICAL,
                    "    ",
                    LinesChars.VERTICAL));

            bottom = new StringBuilder(mid.toString());
            bottom.replace(1, 2, " ");

            lines.add(top);
            lines.add(mid);
            lines.add(bottom);

        }

        lines.add(new StringBuilder(String.format("   %s%s%s",
                LinesChars.BOTTOM_LEFT,
                Character.toString(LinesChars.HORIZONTAL).repeat(4),
                LinesChars.BOTTOM_RIGHT)));

        return lines;

    }

    public List<StringBuilder> consLLARepr() {

        List<StringBuilder> lines = consVerticalVec();

        List<StringBuilder> lslRepr;

        int linesIdx = 2;

        for (LSL lsl : vec) {

            if (lsl != null) {

                lslRepr = lsl.consLSLRepr();

                lines.get(linesIdx - 1).append(lslRepr.get(0));
                lines.get(linesIdx).append(lslRepr.get(1));
                lines.get(linesIdx + 1).append(lslRepr.get(2));

            }
            linesIdx += 3;

        }

        return lines;

    }

    public void show() {

        List<StringBuilder> lines = consLLARepr();

        for (StringBuilder sb : lines) {

            System.out.println(sb);

        }

    }

    public static void main(String[] args) {

        ListasLigadasAdyacencia A = new ListasLigadasAdyacencia(5);
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
