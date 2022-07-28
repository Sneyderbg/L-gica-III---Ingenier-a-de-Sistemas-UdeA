package listasGeneralizadas;

import listasLigadas.listas.LSL;
import nodos.NodoLg;

public class Lg extends LSL {

    public Lg() {
        super();
    }

    // ((a, b, c, d), (a, (a, b, c, d), f, (a, b, c, d), g), x, (a, b, c, d))
    public static int consLg(Lg A, String lgStr, int startIdx) {

        if (lgStr.charAt(0) != '(') {

            throw new IllegalArgumentException("Malformed lgStr");

        }

        A.setPrimerNodo(null);
        A.setUltimoNodo(null);

        NodoLg nodoX;
        Lg subLg;
        String s;
        int charIdx;

        charIdx = startIdx + 1;
        s = "";

        while (charIdx < lgStr.length() && lgStr.charAt(charIdx - 1) != ')') {

            switch (lgStr.charAt(charIdx)) {

                case '(':

                    subLg = new Lg();
                    charIdx = consLg(subLg, lgStr, charIdx);
                    nodoX = new NodoLg(1, subLg, null);
                    A.conectar(nodoX, A.getUltimoNodo());
                    s = "";
                    break;

                case ',':

                    if (s.trim().length() == 0) {
                        break;
                    }
                    nodoX = new NodoLg(0, s.trim(), null);
                    A.conectar(nodoX, A.getUltimoNodo());
                    s = "";
                    break;

                case ')':

                    if (s.trim().length() == 0) {
                        break;
                    }
                    nodoX = new NodoLg(0, s.trim(), null);
                    A.conectar(nodoX, A.getUltimoNodo());
                    s = "";
                    break;

                default:

                    s = s.concat(lgStr.substring(charIdx, charIdx + 1));
                    break;

            }

            charIdx++;

        }

        return charIdx;

    }

    @Override
    public String toString() {

        String s = "(";

        NodoLg nodoX = (NodoLg) getPrimerNodo();
        Lg subLg;

        while (!finDeRecorrido(nodoX)) {

            if (nodoX != getPrimerNodo()) {

                s = s.concat(", ");

            }

            if (nodoX.getSw() == 0) {

                s = s.concat(nodoX.getDato().toString());

            } else {

                subLg = (Lg) nodoX.getDato();
                s = s.concat(subLg.toString());

            }

            nodoX = (NodoLg) nodoX.getLiga();

        }

        s = s.concat(")");

        return s;

    }

    public static void main(String[] args) {

        Lg A = new Lg();
        consLg(A, "((a, b, c, d), (a, (a, b, c, d), f, (a, b, c, d), g), x, (a, b, c, d))", 0);

        A.show();

    }

}
