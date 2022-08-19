package arboles;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import listasGeneralizadas.Lg;
import nodos.Nodo;
import nodos.NodoLg;

/**
 * Esta clase representa un árbol n-ario cualquiera en forma de lista
 * generalizada.
 */
public class ArbolLg extends Lg implements Arbol {

    /**
     * Raíz de este árbol.
     */
    private NodoLg root;

    /**
     * Constructor, Contruye el árbol y le asigna su nodo raíz entregado como
     * parámetro, siempre que este no sea {@code null}.
     * 
     * @param root Raíz a asignar a este árbol, debe ser diferente de {@code null},
     *             de lo contrario arrojará una excepción.
     * @throws IllegalArgumentException Si {@code root == null}.
     */
    public ArbolLg(NodoLg root) {

        super();

        if (root == null) {
            throw new IllegalArgumentException("root must not be null");
        }

        this.root = root;
        setPrimerNodo(root);
        setUltimoNodo(root);

    }

    public NodoLg getRoot() {
        return root;
    }

    public void setRoot(NodoLg root) {

        this.root = root;
        setPrimerNodo(root);
        setUltimoNodo(root);

    }

    /**
     * Construye un árbol y sus subárboles recursivamente a partir del String
     * entregado como parámetro.
     * 
     * @param arbolStr  String que representa el árbol a construir.
     * @param globalIdx Índice en el String el cual indica donde comienza el arbol
     *                  que se va a construir.
     * @return {@link ArbolLg} que representa el String dado.
     */
    private static ArbolLg consArbolLg(String arbolStr, AtomicInteger globalIdx) {

        if (arbolStr.trim().length() == 0) {

            return null;

        }

        ArbolLg A = null;
        ArbolLg subArbol;
        NodoLg nodoX;
        String atomo;

        int charIdx = globalIdx.get();
        atomo = "";

        // a(b(c, d(e)), f, g(h, i(j, k(l)), m, n))
        // loop para recorrer el string
        do {

            switch (arbolStr.charAt(charIdx)) {

                case '(': // lo anterior debió ser un átomo

                    nodoX = new NodoLg();

                    // si el arbol no se ha creado
                    if (A == null) {

                        // se asigna el átomo leído anteriormente, y se crea el árbol con el nodoX
                        nodoX.setSw(0);
                        nodoX.setDato(atomo.trim());
                        A = new ArbolLg(nodoX);

                    } else { // el arbol ya esta creado, y por tanto su raíz está presente

                        nodoX.setSw(1);

                        // se crea el subArbol a partir de la posición del átomo leído anteriormente
                        globalIdx.set(charIdx - atomo.length());
                        subArbol = consArbolLg(arbolStr, globalIdx);

                        // globalIdx es la posición en la cual se deben seguir leyendo los datos, en
                        // otras palabras, es donde el subArbol anterior termino de leer, por tanto no
                        // hay que leer los mismos datos
                        charIdx = globalIdx.get();

                        // se asigna el campo Dato del nodoX al subArbol creado.
                        nodoX.setDato(subArbol);

                        // se conecta ese subArbol al ultimo nodo
                        A.conectar(nodoX, A.getUltimoNodo());

                    }

                    // se reinicia el átomo
                    atomo = "";
                    break;

                case ',': // lo anterior pudo ser un átomo (sin hijos), o un subArbol (un paréntesis de
                          // cierre)

                    // si lo anterior fue un subArbol, ya se debió haber conectado

                    // si fue un átomo, o sea, el string atomo no está vacío
                    if (atomo.trim().length() == 0) {
                        break;
                    }

                    // se crea un nuevo nodo con ese átomo
                    nodoX = new NodoLg(0, atomo.trim(), null);

                    // se conecta
                    A.conectar(nodoX, A.getUltimoNodo());

                    // se reinicia el átomo
                    atomo = "";
                    break;

                case ')': // lo anterior pudo ser un átomo o un subArbol

                    // si fue un subArbol, ya se debió haber conectado

                    // si fue un átomo
                    if (atomo.trim().length() > 0) {

                        // se crea un nuevo nodo con ese átomo y se conecta a este arbol
                        nodoX = new NodoLg(0, atomo.trim(), null);
                        A.conectar(nodoX, A.getUltimoNodo());

                    }

                    globalIdx.set(charIdx);
                    return A;

                default:

                    // se añade el carácter al átomo
                    atomo = atomo.concat(arbolStr.substring(charIdx, charIdx + 1));
                    break;

            }

            // se avanza al siguiente carácter
            charIdx++;

        } while (charIdx < arbolStr.length());
        // mientras charIdx este dentro del rango de arbolStr

        // globalIdx será la continuación en el string del arbol padre que contiene
        // este arbol
        globalIdx.set(charIdx);

        return A;

    }

    public static ArbolLg consArbolLg(String arbolStr) {

        return consArbolLg(arbolStr, new AtomicInteger(0));

    }

    @Override
    public NodoLg find(Object d) {
        return super.find(d);
    }

    @Override
    public Nodo getParent(Nodo child) {

        if (child == null || child == getRoot()) {
            return null;
        }

        ArbolLg subArbol;
        Nodo subResult;
        NodoLg nodoX, root;

        root = getRoot();
        nodoX = (NodoLg) root.getLiga();

        while (nodoX != null) {

            if (nodoX == child) {

                return root;

            }

            if (nodoX.getSw() == 1) {

                subArbol = (ArbolLg) nodoX.getDato();
                if (subArbol.getRoot() == child) {
                    return root;
                }

                subResult = subArbol.getParent(child);

                if (subResult != null) {
                    return subResult;
                }

            }

            nodoX = (NodoLg) nodoX.getLiga();

        }

        return null;

    }

    @Override
    public List<Object> getAncestors(Nodo nodoX) {

        if (nodoX == null) {
            return null;
        }

        List<Object> ancestors, subAncestors;
        ArbolLg subArbol;
        NodoLg root, child;

        ancestors = new ArrayList<Object>();

        root = getRoot();

        if (root == nodoX) {
            ancestors.add(root.getDato());
        }

        child = (NodoLg) root.getLiga();

        while (child != null) {

            if (child.getSw() == 1) {

                subArbol = (ArbolLg) child.getDato();
                subAncestors = subArbol.getAncestors(nodoX);

                if (subAncestors.size() > 0 && subAncestors.get(subAncestors.size() - 1) == nodoX.getDato()) {

                    subAncestors.add(0, root.getDato());
                    return subAncestors;

                }

            } else {

                if (child == nodoX) {

                    ancestors.add(root.getDato());
                    ancestors.add(child.getDato());
                    return ancestors;

                }

            }

            child = (NodoLg) child.getLiga();

        }

        return ancestors;

    }

    public int getHeight() {

        ArbolLg subArbol;
        int max, subHeight;

        max = 1;

        NodoLg nodoX = (NodoLg) getPrimerNodo();

        if (nodoX.getLiga() != null) {

            max = 2;

        }

        while (nodoX != null) {

            if (nodoX.getSw() == 1) {

                subArbol = (ArbolLg) nodoX.getDato();
                subHeight = subArbol.getHeight();
                max = Math.max(max, subHeight + 1);

            }

            nodoX = (NodoLg) nodoX.getLiga();

        }

        return max;

    }

    public int getDegreeNonRecursive() {

        if (getRoot().getLiga() == null) {
            return 0;
        }

        int maxDegree, count;

        ArbolLg subArbol;
        NodoLg nodoX, last;
        Stack<NodoLg> stack = new Stack<>();

        nodoX = (NodoLg) getPrimerNodo().getLiga();
        last = nodoX;

        maxDegree = 0;
        count = 0;

        while (nodoX != null) {

            count++;
            if (nodoX.getSw() == 1) {

                stack.push(nodoX);

            }

            if (nodoX.getLiga() == null && !stack.isEmpty()) {

                maxDegree = Math.max(maxDegree, count);
                count = 0;

                last = stack.pop();
                subArbol = (ArbolLg) last.getDato();
                nodoX = (NodoLg) subArbol.getPrimerNodo(); // nunca es null

            }

            nodoX = (NodoLg) nodoX.getLiga();

        }

        return maxDegree;

    }

    public int getMaxDegree() {

        int degree, subDegree, maxSubDegree;
        ArbolLg subArbol;

        NodoLg nodoX = (NodoLg) getPrimerNodo().getLiga();

        degree = 0;
        maxSubDegree = 0;

        while (nodoX != null) {

            degree++;
            if (nodoX.getSw() == 1) {

                subArbol = (ArbolLg) nodoX.getDato();
                subDegree = subArbol.getMaxDegree();
                maxSubDegree = Math.max(maxSubDegree, subDegree);

            }

            nodoX = (NodoLg) nodoX.getLiga();

        }

        degree = Math.max(degree, maxSubDegree);

        return degree;

    }

    public int countLeafs() {

        int count, subCount;

        ArbolLg subArbol;
        NodoLg nodoX = (NodoLg) getRoot().getLiga(); // root == primerNodo

        count = 0;

        while (nodoX != null) {

            if (nodoX.getSw() == 1) {

                subArbol = (ArbolLg) nodoX.getDato();
                subCount = subArbol.countLeafs();
                count += subCount;

            } else {

                count++;

            }

            nodoX = (NodoLg) nodoX.getLiga();

        }

        return count;

    }

    public void consTreeRepr(StringBuilder sb, String prefix, int widthFix) {

        ArbolLg subArbol;
        NodoLg nodoX;

        String newPrefix;

        sb.append(getRoot().getDato().toString());
        sb.append("\n");

        nodoX = (NodoLg) getRoot().getLiga();

        newPrefix = prefix.concat("├").concat("─".repeat(widthFix));

        // └ ┘ ┌ ┐ ─ │ ┼ ┴ ┬ ┤ ├
        while (nodoX != null) {

            if (nodoX == getUltimoNodo()) {

                newPrefix = prefix.concat("└").concat("─".repeat(widthFix));

            }

            sb.append(newPrefix);

            if (nodoX.getSw() == 1) {

                newPrefix = prefix.concat((nodoX == getUltimoNodo()) ? " " : "│");
                newPrefix = newPrefix.concat(" ".repeat(widthFix));

                subArbol = (ArbolLg) nodoX.getDato();
                subArbol.consTreeRepr(sb, newPrefix, widthFix);

                newPrefix = prefix.concat("├").concat("─".repeat(widthFix));

            } else {

                sb.append(nodoX.getDato().toString());
                sb.append("\n");

            }

            nodoX = (NodoLg) nodoX.getLiga();

        }

    }

    @Override
    public String toString() {

        String s = "";

        NodoLg nodoX = (NodoLg) getPrimerNodo();
        ArbolLg subArbol;

        while (!finDeRecorrido(nodoX)) {

            if (getRoot().getLiga() == nodoX) {

                s = s.concat("(");

            } else if (nodoX != getRoot()) {

                s = s.concat(", ");

            }

            if (nodoX.getSw() == 0) {

                s = s.concat(nodoX.getDato().toString());

            } else {

                subArbol = (ArbolLg) nodoX.getDato();
                s = s.concat(subArbol.toString());

            }

            if (nodoX == getUltimoNodo() && nodoX != getRoot()) {

                s = s.concat(")");

            }
            nodoX = (NodoLg) nodoX.getLiga();

        }

        return s;

    }

    public String parentToString() {
        return super.toString();
    }

    public void showAsTreeRepr() {

        StringBuilder sb = new StringBuilder();

        consTreeRepr(sb, "", 1);

        System.out.println(sb.toString());

    }

    public static void main(String[] args) {

        ArbolLg A = consArbolLg("a(b(c, d(e)), f, g(h, i(j, k(l(x(z, o(p))))), m, n))", new AtomicInteger());

        A.show();

        A.showAsTreeRepr();

        NodoLg B = A.find("p");

        System.out.println(B);
        
        System.out.println(A.getAncestors(B));
        
    }

}
