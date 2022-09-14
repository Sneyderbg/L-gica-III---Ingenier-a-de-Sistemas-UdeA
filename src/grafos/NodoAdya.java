package grafos;

import java.util.Arrays;
import java.util.List;

import nodos.NodoDoble;
import utils.Lines;

public class NodoAdya extends NodoDoble {

    public NodoAdya(int vi, int vj) {

        this.dato = new Lado(vi, vj);

    }

    public void setVi(int vi) {

        ((Lado) this.dato).vi = vi;

    }

    public void setVj(int vj) {

        ((Lado) this.dato).vj = vj;

    }

    public int getVi() {
        return ((Lado) this.dato).vi;
    }

    public int getVj() {
        return ((Lado) this.dato).vj;
    }

    public NodoAdya getLvi() {
        return (NodoAdya) getLi();
    }

    public NodoAdya getLvj() {
        return (NodoAdya) getLd();
    }

    public void setLvi(NodoAdya nodoVi) {
        setLi(nodoVi);
    }

    public void setLvj(NodoAdya nodoVj) {
        setLd(nodoVj);
    }

    public List<StringBuilder> consNodeRepr(int fieldWidth) {

        StringBuilder top, mid, bottom, id;

        String nodeId, LviId, LvjId;

        nodeId = Integer.toHexString(this.hashCode());
        LviId = getLvi() == null ? "¬" : Integer.toHexString(getLvi().hashCode());
        LvjId = getLvj() == null ? "¬" : Integer.toHexString(getLvj().hashCode());

        if (fieldWidth == 0) {
            fieldWidth = Math.max(
                    Math.max(LviId.length(), LvjId.length()),
                    Math.max(Integer.toString(getVi()).length(), Integer.toString(getVj()).length()));
        }

        top = new StringBuilder((Lines.TOP_T + Lines.HORIZONTAL.repeat(fieldWidth)).repeat(4));
        top.append(Lines.TOP_RIGHT).replace(0, 1, Lines.TOP_LEFT);

        mid = new StringBuilder(
                String.format((Lines.VERTICAL + "%" + fieldWidth + "s").repeat(4).concat(Lines.VERTICAL),
                        getVi(), getVj(), LviId, LvjId));

        bottom = new StringBuilder((Lines.BOTTOM_T + Lines.HORIZONTAL.repeat(fieldWidth)).repeat(4));
        bottom.append(Lines.BOTTOM_RIGHT).replace(0, 1, Lines.BOTTOM_LEFT);

        int idSpacing = (bottom.length() - nodeId.length()) / 2;

        id = new StringBuilder(" ".repeat(idSpacing));
        id.append(nodeId);

        return Arrays.asList(top, mid, bottom, id);

    }

    class Lado {

        int vi;
        int vj;

        public Lado(int vi, int vj) {
            this.vi = vi;
            this.vj = vj;
        }

    }

    public static void main(String[] args) {
        NodoAdya x = new NodoAdya(4, 5);
        x.setLd(new NodoAdya(2, 1));

        for (StringBuilder sb : x.consNodeRepr(0)) {

            System.out.println(sb);

        }

    }

}
