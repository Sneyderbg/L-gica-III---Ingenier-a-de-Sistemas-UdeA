package listasGeneralizadas;

import java.util.Stack;

import nodos.NodoLg;

public class Lg {

	protected NodoLg primerNodo;

	/**
	 * Crea un lista generalizada vacía.
	 */
	public Lg() {
		primerNodo = null;
	}

	/**
	 * Contruye una lista generalizada a partir de una hilera formada por:
	 * <p>
	 * - <b>Caracteres de inicio y fin de una lista o sublista,</b> por ejemplo,
	 * <b>()</b>, <b>[]</b>, <b>{}</b>.<br>
	 * - <b>Separador,</b> el cual separa átomos y listas o sublistas entre sí, por
	 * ejemplo, <b>,</b>, <b>-</b>, <b>.</b>, <b>/</b>.<br>
	 * - <b>Ýtomos, </b> los cuales son todos aquellos carácteres diferentes a los
	 * mencionados anteriormente.
	 * <p>
	 * Este es un ejemplo:
	 * <p>
	 * <b>LgString</b> = (a, (a, b, c, d), f, (a, b, c, d), g).
	 * 
	 * @param LgString Lista generalizada en forma de hilera.
	 * @param LgStart  Carácter que representa el inicio de una lista o sublista en
	 *                 la hilera <b>LgString</b>.
	 * @param LgEnd    Carácter que representa el fin de una lista o sublista en la
	 *                 hilera <b>LgString</b>.
	 * @param splitter Carácter que separa listas o sublistas y átomos entre sí.
	 */
	public Lg(String LgString, char LgStart, char LgEnd, char splitter) {

		int n = LgString.length();

		assert n >= 3;
		assert LgString.charAt(0) == LgStart : "Lista mal formada";
		assert LgString.charAt(n - 1) == LgEnd : "Lista mal formada";

		Stack<NodoLg> stack = new Stack<>();

		NodoLg x, ultimoNodo;
		x = new NodoLg(null);
		primerNodo = x;
		ultimoNodo = x;
		Character c;

		for (int i = 1; i < n - 1; i++) {
			c = LgString.charAt(i);
			if (c == LgStart) { // "("

				stack.push(ultimoNodo);
				x = new NodoLg(null);
				ultimoNodo.setSw(1);
				ultimoNodo.setDato(x);
				ultimoNodo = x;

			} else if (c == LgEnd) { // ")"

				ultimoNodo = stack.pop();

			} else if (c == splitter) { // ","
				
				x = new NodoLg(null);
				ultimoNodo.setLiga(x);
				ultimoNodo = x;

			} else { // átomo

				String atomo = c.toString();
				c = LgString.charAt(i + 1);
				
				while(c != LgStart && c != LgEnd && c != splitter) {
					atomo = atomo.concat(c.toString());
					i++;
					c = LgString.charAt(i + 1);					
				}
				
				ultimoNodo.setSw(0);
				ultimoNodo.setDato(atomo);

			}
		}
		
		if(!stack.isEmpty()) {
			this.primerNodo = null;
			throw new AssertionError("Lista mal formada.");			
		}
		
	}

	public boolean isEmpty() {
		return this.primerNodo == null;
	}

	public NodoLg getPrimerNodo() {
		return this.primerNodo;
	}
	
	public void print() {

		String output = "(";
		if (isEmpty()) {
			output = output.concat(")");
			return;
		}

		Stack<NodoLg> stack = new Stack<NodoLg>();
		NodoLg x = getPrimerNodo();

		while (!stack.isEmpty() || x != null) {
			if (x == null) {
				x = stack.pop();
				x = x.getLiga();
				output = output.concat(")");
				if (x != null && x.getSw() == 0) {
					output = output.concat(", ");
				}
			} else {
				if (x.getSw() == 0) {

					output = output.concat(x.getDato().toString());
					if (x.getLiga() != null) {
						output = output.concat(", ");
					}
					x = x.getLiga();

				} else {

					stack.push(x);
					x = (NodoLg) x.getDato();
					output = output.concat("(");

				}
			}
		}

		output = output.concat(")");
		System.out.println(output);
	}

	public static void main(String[] args) {
		Lg l = new Lg("(a, (b, e, f), c, (d, g, h, (i, k), j))", '(', ')', ',');
		System.out.println(l.toString());
	}
}