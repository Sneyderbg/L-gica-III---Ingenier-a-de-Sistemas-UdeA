package arboles;

import java.util.Stack;

import nodos.NodoDoble;

/**
 * Árboles binarios como listas ligadas.
 * 
 * @author sneyd
 *
 */
public class ArbolBinarioLL {

	protected NodoDoble root;

	public ArbolBinarioLL() {
		root = null;
	}

	public ArbolBinarioLL(Object d) {
		root = new NodoDoble(d);
	}

	// a(c(b(h, i(P(, z),)),e(j ,k)), f(g(l(, q(u, t(v,))), m), d(n, o(, r(s,)))))
	// a-*-*-*- *-*-- *-----*-* -*--- *-*-*-- *-*- *-*----- *-- *-*- *-- *-*------
	public ArbolBinarioLL(String aBString, boolean removeSpaces) {
		Stack<NodoDoble> stack = new Stack<>();
		Character c;
		String entry = "";
		NodoDoble lastParent = null;
		NodoDoble lastNode = null;

		if (removeSpaces) {
			aBString = aBString.replaceAll(" ", "");
		}

		for (int i = 0; i < aBString.length(); i++) {
			c = aBString.charAt(i);
			if (c == '(') {

				if (lastParent == null) {
					this.root = lastNode;
					lastParent = this.root;
				} else {
					stack.push(lastParent);
					lastParent = lastNode;
				}
				lastNode = null;

			} else if (c == ')') {

				lastParent.setLd(lastNode);
				lastNode = lastParent;
				if (lastParent != this.root) {
					lastParent = stack.pop();
				}

			} else if (c == ',') {

				lastParent.setLi(lastNode);
				lastNode = null;

			} else {
				entry = c.toString();
				c = aBString.charAt(i + 1);
				while (c != '(' && c != ')' && c != ',') {
					entry = entry.concat(c.toString());
					i++;
					c = aBString.charAt(i + 1);
				}
				lastNode = new NodoDoble(entry);
			}
		}

	}

	public String inorden(NodoDoble r, char splitter) {
		String IRD = "";
		if (r != null) {
			IRD += inorden(r.getLi(), splitter) + r.getD().toString() + splitter + inorden(r.getLd(), splitter);
		}
		return IRD;
	}

	public String posorden(NodoDoble r, char splitter) {
		String IDR = "";
		if (r != null) {
			IDR += posorden(r.getLi(), splitter) + posorden(r.getLd(), splitter) + r.getD().toString() + splitter;
		}
		return IDR;
	}

	public String preorden(NodoDoble r, char splitter) {
		String RID = "";
		if (r != null) {
			RID += r.getD().toString() + splitter + preorden(r.getLi(), splitter) + preorden(r.getLd(), splitter);
		}
		return RID;
	}

	public int getHeight(NodoDoble x) {
		if (x == null)
			return 0;

		return 1 + Math.max(getHeight(x.getLi()), getHeight(x.getLd()));
	}

	public NodoDoble getRoot() {
		return root;
	}

	public boolean isEmpty() {
		return getRoot() == null;
	}

	public void printIRD() {
		System.out.println(inorden(getRoot(), ','));
	}

	public void printIDR() {
		System.out.println(posorden(getRoot(), ','));
	}

	public void printRID() {
		System.out.println(preorden(getRoot(), ','));
	}

	public static void main(String[] args) {
		ArbolBinarioLL A = new ArbolBinarioLL("a(b(k(m(,o(p,r)),),),c(e(f,g(h(s,),)),d))", true);
		A.printIDR();
	}

}
