package arboles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import nodos.Nodo;
import nodos.NodoDoble;

public class ArbolBinarioLL extends NodoDoble implements Arbol {

    public ArbolBinarioLL(Object dato) {

        super(dato);

    }

    // a(b(c, d(e)), g(h, i(j, k(l))))
    public static ArbolBinarioLL consArbolBinario(String arbolStr, AtomicInteger globalIdx) {

        ArbolBinarioLL A, subArbol;

        A = null;

        String atomo;
        int charIdx = globalIdx.get();

        atomo = "";

        while (charIdx < arbolStr.length()) {

            switch (arbolStr.charAt(charIdx)) {

                case '(':

                    A = new ArbolBinarioLL(atomo.trim());

                    globalIdx.set(charIdx + 1);
                    subArbol = consArbolBinario(arbolStr, globalIdx);
                    charIdx = globalIdx.get();

                    A.setLi(subArbol);

                    if (arbolStr.charAt(charIdx) == ',') {

                        globalIdx.set(charIdx + 1);
                        subArbol = consArbolBinario(arbolStr, globalIdx);
                        charIdx = globalIdx.get();

                        A.setLd(subArbol);

                    }

                    atomo = "";
                    break;

                case ',':

                    if (atomo.trim().length() > 0) {

                        A = new ArbolBinarioLL(atomo.trim());

                    }

                    globalIdx.set(charIdx);
                    return A;

                case ')':

                    if (atomo.trim().length() > 0) {

                        A = new ArbolBinarioLL(atomo.trim());

                    }

                    globalIdx.set(charIdx);
                    return A;

                default:

                    atomo = atomo.concat(arbolStr.substring(charIdx, charIdx + 1));
                    break;

            }

            charIdx++;

        }

        return A;

    }

    public ArbolBinarioLL find(Object d) {

        if (d.equals(getDato())) {
            return this;
        }

        ArbolBinarioLL leftChild, rightChild, leftResult, rightResult;

        leftChild = (ArbolBinarioLL) getLi();
        rightChild = (ArbolBinarioLL) getLd();

        leftResult = (leftChild == null) ? null : leftChild.find(d);
        if (leftResult != null) {
            return leftResult;
        }

        rightResult = (rightChild == null) ? null : rightChild.find(d);
        if (rightResult != null) {
            return rightResult;
        }

        return null;

    }

    @Override
    public ArbolBinarioLL getParent(Nodo children) {

        if (children == null) {
            return null;
        }

        ArbolBinarioLL leftChild, rightChild, leftResult, rightResult;

        leftChild = (ArbolBinarioLL) getLi();
        rightChild = (ArbolBinarioLL) getLd();

        if (leftChild == children || rightChild == children) {
            return this;
        }

        leftResult = (leftChild == null) ? null : leftChild.getParent(children);
        if (leftResult != null) {

            return leftResult;

        }

        rightResult = (rightChild == null) ? null : rightChild.getParent(children);
        if (rightResult != null) {

            return rightResult;

        }

        return null;

    }

    public List<Object> getAncestors(ArbolBinarioLL arbolX) {

        if (arbolX == null) {
            return null;
        }

        List<Object> ancestors, leftAncestors, rightAncestors;
        ArbolBinarioLL leftChild, rightChild;

        ancestors = new ArrayList<>();

        if (arbolX == this) {

            ancestors.add(this.getDato());
            return ancestors;

        } // else

        leftChild = (ArbolBinarioLL) getLi();
        rightChild = (ArbolBinarioLL) getLd();

        if (leftChild != null) {

            leftAncestors = leftChild.getAncestors(arbolX);
            if (leftAncestors.size() > 0) {

                if (leftAncestors.get(leftAncestors.size() - 1) == arbolX.getDato()) {

                    leftAncestors.add(0, this.getDato());
                    ancestors = leftAncestors;

                }

            }

        }

        if (rightChild != null) {

            rightAncestors = rightChild.getAncestors(arbolX);

            if (rightAncestors.size() > 0) {

                if (rightAncestors.get(rightAncestors.size() - 1) == arbolX.getDato()) {

                    rightAncestors.add(0, this.getDato());
                    ancestors = rightAncestors;

                }

            }

        }

        return ancestors;

    }

    public int getHeight() {

        int maxHeight, leftHeight, rightHeight;
        ArbolBinarioLL leftChild, rightChild;

        leftChild = (ArbolBinarioLL) getLi();
        rightChild = (ArbolBinarioLL) getLd();

        leftHeight = (leftChild == null) ? 0 : leftChild.getHeight();
        rightHeight = (rightChild == null) ? 0 : rightChild.getHeight();
        maxHeight = 1 + Math.max(leftHeight, rightHeight);

        return maxHeight;

    }

    public int getDegree() {

        int maxDegree, leftDegree, rightDegree;
        ArbolBinarioLL leftChild, rightChild;

        leftChild = (ArbolBinarioLL) getLi();
        rightChild = (ArbolBinarioLL) getLd();

        maxDegree = 0;
        leftDegree = 0;
        rightDegree = 0;

        if (leftChild != null) {

            leftDegree = leftChild.getDegree();
            maxDegree++;

        }

        if (rightChild != null) {

            rightDegree = rightChild.getDegree();
            maxDegree++;

        }

        maxDegree = Math.max(maxDegree, Math.max(leftDegree, rightDegree));

        return maxDegree;

    }

    public int countLeafs() {

        int leftCount, rightCount;
        ArbolBinarioLL leftChild, rightChild;

        leftChild = (ArbolBinarioLL) getLi();
        rightChild = (ArbolBinarioLL) getLd();

        if (leftChild == null && rightChild == null) {

            return 1;

        }

        leftCount = (leftChild == null) ? 0 : leftChild.countLeafs();
        rightCount = (rightChild == null) ? 0 : rightChild.countLeafs();

        return leftCount + rightCount;

    }

    public void consTreeRepr(StringBuilder sb, String prefix, int widthFix) {

        ArbolBinarioLL leftChild, rightChild;
        leftChild = (ArbolBinarioLL) getLi();
        rightChild = (ArbolBinarioLL) getLd();

        sb.append(getDato().toString());
        sb.append("\n");

        String leftPrefix, rightPrefix;

        leftPrefix = prefix.concat((rightChild == null) ? "└" : "├");
        leftPrefix = leftPrefix.concat("─".repeat(widthFix));

        rightPrefix = prefix.concat("└".concat("─".repeat(widthFix)));

        if (leftChild != null) {

            sb.append(leftPrefix);
            leftPrefix = prefix.concat("│").concat(" ".repeat(widthFix));

            leftChild.consTreeRepr(sb, leftPrefix, widthFix);

        }

        if (rightChild != null) {

            sb.append(rightPrefix);
            rightPrefix = prefix.concat(" ").concat(" ".repeat(widthFix));

            rightChild.consTreeRepr(sb, rightPrefix, widthFix);

        }

    }

    @Override
    public String toString() {

        String root, left, right;
        ArbolBinarioLL leftChild, rightChild;

        leftChild = (ArbolBinarioLL) getLi();
        rightChild = (ArbolBinarioLL) getLd();

        root = getDato().toString();

        if (leftChild == null && rightChild == null) {

            return root;

        }

        left = (leftChild == null) ? "" : leftChild.toString();
        right = (rightChild == null) ? "" : rightChild.toString();

        if (rightChild == null) {

            return String.format("%s(%s)", root, left);

        }

        return String.format("%s(%s, %s)", root, left, right);

    }

    public void show() {

        System.out.println(toString());

    }

    public void showAsTreeRepr(int widthFix) {

        StringBuilder sb = new StringBuilder();

        consTreeRepr(sb, "", widthFix);

        System.out.println(sb.toString());

    }

    public static void main(String[] args) {

        ArbolBinarioLL A = consArbolBinario("a(b(c, d(e)), g(h, i(j, k(l))))", new AtomicInteger(0));

        A.show();

        A.showAsTreeRepr(2);

        List<Object> ancestors = A.getAncestors(A.find("l"));

        System.out.println(A.getParent(A.find("p")));

    }

}
