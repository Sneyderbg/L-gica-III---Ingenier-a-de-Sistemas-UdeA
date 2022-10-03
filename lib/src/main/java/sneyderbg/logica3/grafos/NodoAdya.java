package sneyderbg.logica3.grafos;

import java.util.Arrays;
import java.util.List;

import sneyderbg.logica3.nodos.NodoDoble;
import sneyderbg.logica3.util.Symbols;

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

        top = new StringBuilder((Symbols.TOP_T + Symbols.HORIZONTAL.repeat(fieldWidth)).repeat(4));
        top.append(Symbols.TOP_RIGHT).replace(0, 1, Symbols.TOP_LEFT);

        mid = new StringBuilder(
                String.format((Symbols.VERTICAL + "%" + fieldWidth + "s").repeat(4).concat(Symbols.VERTICAL),
                        getVi(), getVj(), LviId, LvjId));

        bottom = new StringBuilder((Symbols.BOTTOM_T + Symbols.HORIZONTAL.repeat(fieldWidth)).repeat(4));
        bottom.append(Symbols.BOTTOM_RIGHT).replace(0, 1, Symbols.BOTTOM_LEFT);

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
