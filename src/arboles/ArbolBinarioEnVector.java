package arboles;

import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class ArbolBinarioEnVector extends Vector<Object> {

    public ArbolBinarioEnVector() {

        super(0, 1);

    }

    public ArbolBinarioEnVector(int[] array) {

        super(array.length, 1);

        Collections.addAll(this, array);

    }

    private static void consArbolBinario(ArbolBinarioEnVector A, String arbolStr, AtomicInteger globalCharIdx,
            int parentIdx) {

        if (arbolStr.trim().length() == 0) {

            A.setSize(0);
            return;

        }

        int charIdx, childIdx;
        childIdx = A.leftChildIdx(parentIdx);

        charIdx = globalCharIdx.get();
        String atomo = "";

        // xx.
        // a(b(c, d(e)), g(h, i(j, k(l))))
        do {

            switch (arbolStr.charAt(charIdx)) {

                case '(':

                    if (atomo.trim().length() == 0) {
                        break;
                    }

                    A.setSize(Math.max(A.rightChildIdx(childIdx) + 1, A.size()));

                    A.set(childIdx, atomo.trim());

                    globalCharIdx.set(charIdx + 1);
                    consArbolBinario(A, arbolStr, globalCharIdx, childIdx);
                    charIdx = globalCharIdx.get();

                    atomo = "";
                    break;

                case ',':

                    if (atomo.trim().length() > 0) {

                        A.set(childIdx, atomo.trim());

                    }

                    childIdx = A.rightChildIdx(parentIdx);
                    atomo = "";
                    break;

                case ')':

                    if (atomo.trim().length() > 0) {

                        A.set(childIdx, atomo.trim());

                    }

                    globalCharIdx.set(charIdx);
                    return;

                default:

                    atomo = atomo.concat(arbolStr.substring(charIdx, charIdx + 1));
                    break;

            }

            charIdx++;

        } while (charIdx < arbolStr.length());

        globalCharIdx.set(charIdx);

    }

    public static ArbolBinarioEnVector consABV(String aBStr) {

        ArbolBinarioEnVector A = new ArbolBinarioEnVector();

        consArbolBinario(A, aBStr, new AtomicInteger(0), -1);

        return A;

    }

    public int leftChildIdx(int parentIdx) {

        if (parentIdx < 0)
            return 0;

        return 2 * parentIdx + 1;

    }

    public int rightChildIdx(int parentIdx) {

        if (parentIdx < 0)
            return 0;

        return 2 * parentIdx + 2;

    }

    public int parentIdx(int childIdx) {

        return (childIdx - 1) / 2;

    }

    public void setLeftChild(int parentIdx, Object value) {

        set(leftChildIdx(parentIdx), value);

    }

    public void setRightChild(int parentIdx, Object value) {

        set(rightChildIdx(parentIdx), value);

    }

    public String toVecRepr(int fieldWidth) {

        StringBuilder topLine, line, bottomLine;

        int size = size();

        // └ ┘ ┌ ┐ ─ │ ┼ ┴ ┬ ┤ ├
        topLine = new StringBuilder(("┬" + "─".repeat(fieldWidth)).repeat(size));
        topLine.replace(0, 1, "┌").append("┐");

        bottomLine = new StringBuilder(("┴" + "─".repeat(fieldWidth)).repeat(size));
        bottomLine.replace(0, 1, "└").append("┘");

        line = new StringBuilder("");

        String v;

        for (int i = 0; i < size; i++) {

            v = get(i) == null ? "" : get(i).toString();
            line.append(String.format("│%" + fieldWidth + "s", v));

        }

        line.append("│");

        return topLine.toString() + "\n" +
                line.toString() + "\n" +
                bottomLine.toString();

    }

    public String strRepr(int parentIdx) {

        if (parentIdx >= size() || get(parentIdx) == null) {

            return "";

        }

        String left, right, s;

        s = get(parentIdx).toString();

        left = strRepr(leftChildIdx(parentIdx));
        right = strRepr(rightChildIdx(parentIdx));

        if (left.length() > 0 && right.length() > 0) {

            s = s.concat(String.format("(%s, %s)", left, right));

        } else if (left.length() > 0) {

            s = s.concat(String.format("(%s)", left));

        } else if (right.length() > 0) {

            s = s.concat(String.format("(%s)", right));

        }

        return s;

    }

    @Override
    public synchronized String toString() {

        return strRepr(0);

    }

    public static void main(String[] args) {

        int[] A = { 1, 2, 3, 4, 5, 6, 7 };

        ArbolBinarioEnVector aV = consABV("a(b(c, d(e)), g(h, i(j, k(l))))");

        System.out.println(aV);
        System.out.println(aV.toVecRepr(2));

    }

}