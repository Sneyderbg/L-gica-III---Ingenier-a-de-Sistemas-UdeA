package arboles;

import java.util.concurrent.atomic.AtomicInteger;

import listasGeneralizadas.Lg;
import nodos.NodoLg;

/**
 * Esta clase representa un árbol n-ario cualquiera en forma de lista
 * generalizada.
 */
public class arbolLg extends Lg {

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
    public arbolLg(NodoLg root) {

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
     * @param arbolStr String que representa el árbol a construir.
     * @param startIdx Índice en el String el cual indica donde comienza el arbol
     *                 que se va a construir.
     * @return {@link arbolLg} que representa el String dado.
     */
    private static arbolLg consArbolLg(String arbolStr, AtomicInteger startIdx) {

        arbolLg A = null;
        arbolLg subArbol;
        NodoLg nodoX;
        String atomo;

        int charIdx = startIdx.get();
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
                        A = new arbolLg(nodoX);

                    } else { // el arbol ya esta creado, y por tanto su raíz está presente

                        nodoX.setSw(1);

                        // se crea el subArbol a partir de la posición del átomo leído anteriormente
                        startIdx.set(charIdx - atomo.length());
                        subArbol = consArbolLg(arbolStr, startIdx);

                        // startIdx es la posición en la cual se deben seguir leyendo los datos, en
                        // otras palabras, es donde el subArbol anterior termino de leer, por tanto no
                        // hay que leer los mismos datos
                        charIdx = startIdx.get();

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
                    if (atomo.trim().length() == 0) {
                        break;
                    }

                    // se crea un nuevo nodo con ese átomo y se conecta a este arbol
                    nodoX = new NodoLg(0, atomo.trim(), null);
                    A.conectar(nodoX, A.getUltimoNodo());

                    // se reinicia el átomo
                    atomo = "";
                    break;

                default:

                    // se añade el carácter al átomo
                    atomo = atomo.concat(arbolStr.substring(charIdx, charIdx + 1));
                    break;

            }

            // se avanza al siguiente carácter
            charIdx++;

        } while (charIdx < arbolStr.length() && arbolStr.charAt(charIdx - 1) != ')');
        // mientras charIdx este dentro del rango de arbolStr, y el último character
        // leído no sea un cierre de paréntesis

        // startIdx será el la continuación en el string del arbol padre que contiene
        // este arbol
        startIdx.set(charIdx);

        return A;

    }

    public static arbolLg consArbolLg(String arbolStr) {

        return consArbolLg(arbolStr, new AtomicInteger(0));

    }

    @Override
    public String toString() {

        String s = "";

        NodoLg nodoX = (NodoLg) getPrimerNodo();
        arbolLg subArbol;

        while (!finDeRecorrido(nodoX)) {

            if (getRoot().getLiga() == nodoX) {

                s = s.concat("(");

            } else if (nodoX != getRoot()) {

                s = s.concat(", ");

            }

            if (nodoX.getSw() == 0) {

                s = s.concat(nodoX.getDato().toString());

            } else {

                subArbol = (arbolLg) nodoX.getDato();
                s = s.concat(subArbol.toString());

            }

            nodoX = (NodoLg) nodoX.getLiga();

        }

        s = s.concat(")");

        return s;

    }

    public String parentToString() {
        return super.toString();
    }

    public String toStr() {

        StringBuilder sb = new StringBuilder("");

        NodoLg nodoX = (NodoLg) getPrimerNodo();
        arbolLg subArbol;

        while (!finDeRecorrido(nodoX)) {

            if (getRoot().getLiga() == nodoX) {

                sb = sb.append("(");

            } else if (nodoX != getRoot()) {

                sb = sb.append(", ");

            }

            if (nodoX.getSw() == 0) {

                sb = sb.append(nodoX.getDato().toString());

            } else {

                subArbol = (arbolLg) nodoX.getDato();
                sb = sb.append(subArbol.toString());

            }

            nodoX = (NodoLg) nodoX.getLiga();

        }

        sb = sb.append(")");

        return sb.toString();

    }

    public static void main(String[] args) {

        arbolLg A = consArbolLg("a(b(c, d(e), x, y, zero(k, ss)), f, g(h, i(j, k(l)), m, n))", new AtomicInteger());

        A.showAsLgRepr(4);

        A.show();

    }

}
