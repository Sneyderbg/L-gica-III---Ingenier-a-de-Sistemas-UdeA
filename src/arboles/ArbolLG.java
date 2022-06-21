package arboles;

import java.util.Stack;

import listasGeneralizadas.Lg;
import nodos.NodoLg;

public class ArbolLG extends Lg {

	public ArbolLG() {
		super();
	}

	// a(b(e,f),c,d(g,h,i(k),j))
	// *
	public ArbolLG(String aBString, boolean removeSpaces) {
		super();

		Stack<NodoLg> stack = new Stack<>();
		Character c;
		String lastEntry = null;
		NodoLg lastNode = null;
		NodoLg x;

		if (removeSpaces) {
			aBString = aBString.replaceAll(" ", "");
		}

		for (int i = 0; i < aBString.length(); i++) {
			c = aBString.charAt(i);
			if (c == '(') {

				if (lastNode == null) {
					lastNode = new NodoLg(0, lastEntry, null);
					this.primerNodo = lastNode;
					stack.push(lastNode);
				} else {
					x = new NodoLg(1, null, null);
					lastNode.setLiga(x);
					lastNode = x;
					x = new NodoLg(0, lastEntry, null);
					lastNode.setDato(x);
					stack.push(lastNode);
					lastNode = x;
				}
				lastEntry = null;

			} else if (c == ')') {

				if (lastEntry != null) {
					x = new NodoLg(0, lastEntry, null);
					lastNode.setLiga(x);
					lastNode = stack.pop();
					lastEntry = null;
				}

			} else if (c == ',') {

				if (lastEntry != null) {
					x = new NodoLg(0, lastEntry, null);
					lastNode.setLiga(x);
					lastNode = x;
					lastEntry = null;
				}

			} else {

				lastEntry = c.toString();
				c = aBString.charAt(i + 1);
				while (c != '(' && c != ')' && c != ',') {
					lastEntry = lastEntry.concat(c.toString());
					i++;
					c = aBString.charAt(i + 1);
				}

			}
		}
	}

	public int hojas(NodoLg r) {
		if (r == null) {
			return 0;
		}

		NodoLg x = r.getLiga();
		if (x == null) {
			return 1;
		}
		int n = 0;
		while (x != null) {
			if (x.getSw() == 0) {
				n++;
			} else {
				n += hojas((NodoLg) x.getDato());
			}
			x = x.getLiga();
		}
		return n;
	}
	
	private int grado(NodoLg r) {
		if(r == null) {
			return 0;
		}
		NodoLg x = r.getLiga();
		int n, g, mayor;
		n = 0;
		mayor = 0;
		
		while(x != null) {
			n++;
			if(x.getSw() == 1) {
				g = grado((NodoLg) x.getDato());
				if(g > mayor) {
					mayor = g;
				}
			}
			x = x.getLiga();
		}
		if(n > mayor) {
			return n;
		}
		return mayor;
	}

	public NodoLg getRoot() {
		return getPrimerNodo();
	}

	public void print() {
		String output = "";
		if (isEmpty()) {
			output = output.concat("()");
			System.out.println(output);
		}

		Stack<NodoLg> stack = new Stack<NodoLg>();
		NodoLg antX = null;
		NodoLg x = getRoot();

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
					if (antX == null || (antX != null && antX.getSw() == 1)) {
						output = output.concat("(");
					} else if (x.getLiga() != null) {
						output = output.concat(", ");
					}
					antX = x;
					x = x.getLiga();

				} else {

					stack.push(x);
					antX = x;
					x = (NodoLg) x.getDato();

				}
			}
		}

		output = output.concat(")");
		System.out.println(output);
	}

	public static void main(String[] args) {
		ArbolLG A = new ArbolLG("a(b(e,f),c,d(g,h,i(k),j))", false);
		A.print();
		System.out.println(A.hojas(A.getRoot()));
		System.out.println(A.grado(A.getRoot()));
	}

}
