package listasGeneralizadas;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import listasLigadas.listas.LSL;
import nodos.NodoLg;

public class Lg extends LSL {

    public Lg() {
        super();
    }

    private static Lg consLg(String strLg, AtomicInteger globalIdx) {

        if (strLg.charAt(globalIdx.get()) != '(') {

            throw new IllegalArgumentException("Malformed lgStr");

        }

        Lg A = new Lg();
        Lg subLg;
        NodoLg nodoX;
        String atomo;

        int charIdx = globalIdx.get() + 1;
        atomo = "";

        while (charIdx < strLg.length() && strLg.charAt(charIdx - 1) != ')') {

            switch (strLg.charAt(charIdx)) {

                case '(':

                    globalIdx.set(charIdx);
                    subLg = consLg(strLg, globalIdx);
                    charIdx = globalIdx.get();
                    nodoX = new NodoLg(1, subLg, null);
                    A.conectar(nodoX, A.getUltimoNodo());
                    atomo = "";
                    break;

                case ',':

                    if (atomo.trim().length() == 0) {
                        break;
                    }
                    nodoX = new NodoLg(0, atomo.trim(), null);
                    A.conectar(nodoX, A.getUltimoNodo());
                    atomo = "";
                    break;

                case ')':

                    if (atomo.trim().length() == 0) {
                        break;
                    }
                    nodoX = new NodoLg(0, atomo.trim(), null);
                    A.conectar(nodoX, A.getUltimoNodo());
                    atomo = "";
                    break;

                default:

                    atomo = atomo.concat(strLg.substring(charIdx, charIdx + 1));
                    break;

            }

            charIdx++;

        }

        globalIdx.set(charIdx);
        return A;

    }

    public static Lg consLg(String strLg) {

        return consLg(strLg, new AtomicInteger(0));

    }

    public int countNumSubLg() {

        int count = 0;

        NodoLg nodoX = (NodoLg) getPrimerNodo();
        Lg subLg;

        while (nodoX != null) {

            if (nodoX.getSw() == 1) {

                subLg = (Lg) nodoX.getDato();
                count = count + 1 + subLg.countNumSubLg();

            }

            nodoX = (NodoLg) nodoX.getLiga();

        }

        return count;

    }

    public void consLgRepr(ArrayList<StringBuilder> lines, int spacing, int parentLine, int fieldWidth) {

        StringBuilder topLine, line, bottomLine;
        topLine = new StringBuilder(" ".repeat(spacing));
        line = new StringBuilder(" ".repeat(spacing));
        bottomLine = new StringBuilder(" ".repeat(spacing));

        Stack<NodoLg> nodosStack = new Stack<NodoLg>();
        Stack<Integer> numNodos = new Stack<Integer>();
        NodoLg nodoX = (NodoLg) getPrimerNodo();
        int numNodo = 0;

        String topNodeRepr = ("┬" + "─".repeat(fieldWidth)).repeat(3);
        topNodeRepr = topNodeRepr.replaceFirst("┬", "┌") + "┐";

        String bottomNodeRepr = ("┴" + "─".repeat(fieldWidth)).repeat(3);
        bottomNodeRepr = bottomNodeRepr.replaceFirst("┴", "└") + "┘";

        Lg subLg;

        // └ ┘ ┌ ┐ ─ │ ┼ ┴ ┬ ┤ ├
        while (nodoX != null) {

            if (nodoX.getSw() == 1) {

                nodosStack.push(nodoX);
                numNodos.push(numNodo);

            }

            topLine.append("  ").append(topNodeRepr);
            line.append(String.format("%s>│" + ("%" + fieldWidth + "s│").repeat(3),
                    nodoX == getPrimerNodo() ? "└" : "─",
                    nodoX.getSw(),
                    nodoX.getSw() == 0 ? nodoX.getDato() : " ",
                    nodoX == getUltimoNodo() ? "¬" : " "));
            bottomLine.append("  ").append(bottomNodeRepr);

            nodoX = (NodoLg) nodoX.getLiga();
            numNodo++;

        }

        lines.add(topLine);
        lines.add(line);
        lines.add(bottomLine);

        int currentLine = lines.size() - 2;
        int arrowIdx = spacing;
        int arrowLineIdx = parentLine + 2;

        StringBuilder sbTemp;

        while (arrowLineIdx < currentLine) {

            sbTemp = lines.get(arrowLineIdx);
            sbTemp.replace(arrowIdx, arrowIdx + 1, "│");

            arrowLineIdx++;

        }

        while (!nodosStack.isEmpty()) {

            nodoX = nodosStack.pop();
            numNodo = numNodos.pop();

            subLg = (Lg) nodoX.getDato();
            subLg.consLgRepr(lines, spacing + (6 + 3 * fieldWidth) * numNodo + fieldWidth + 4, currentLine,
                    fieldWidth);

        }

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

    public void showAsLgRepr(int fieldWidth) {

        ArrayList<StringBuilder> lines = new ArrayList<StringBuilder>();
        consLgRepr(lines, 0, 0, fieldWidth);

        for (StringBuilder sb : lines) {

            System.out.println(sb.toString());

        }

    }

    public static void main(String[] args) {

        Lg A = consLg("((a, b, c, d), (a, (a, b, c, d), f, (a, b, c, d)), x, (a, b, c, d))");

        Lg B = new Lg();
        
        A.show();

        System.out.println();

        A.showAsLgRepr(2);

    }

}
