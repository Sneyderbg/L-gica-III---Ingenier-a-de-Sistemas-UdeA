package arboles;

import java.util.Stack;

import nodos.NodoDoble;
import nodos.NodoDobleE;

public class ArbolBinarioEnhebrado {

	/**
	 * <b>Bd</b> siempre 1. <br>
	 * <b>Ld</b> siempre apunta a sí mismo.
	 * <p>
	 * Bi:<br>
	 * 0 - Árbol vacío y <b>Li</b> apunta a sí mismo.<br>
	 * 1 - <b>Li</b> apunta hacia la raíz del árbol.
	 */
	private NodoDobleE cabeza;
	private NodoDobleE root;

	public ArbolBinarioEnhebrado() {
		cabeza = new NodoDobleE(null);
		cabeza.setBi(0);
		cabeza.setLi(cabeza);
		cabeza.setBd(1);
		cabeza.setLd(cabeza);
	}

	// a(b(j(k),h(l,g)),c(d(e,f)))
	// TODO: terminar
	public ArbolBinarioEnhebrado(String aBString, boolean removeSpaces) {
		Stack<NodoDobleE> stack = new Stack<>();
		Character c;
		String entry = "";
		NodoDobleE lastParent = null;
		NodoDobleE lastNode = null;

		if (removeSpaces) {
			aBString = aBString.replaceAll(" ", "");
		}

		for (int i = 0; i < aBString.length(); i++) {
			c = aBString.charAt(i);
			if (c == '(') {

				if (lastParent == null) {
					this.root = lastNode;
					lastParent = this.root;
					this.cabeza.setBi(1);
					this.cabeza.setLi(this.root);

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

				if (lastNode == null) {
					lastParent.setBi(0);
				} else {
					lastParent.setLi(lastNode);
				}
				lastNode = null;

			} else {
				entry = c.toString();
				c = aBString.charAt(i + 1);
				while (c != '(' && c != ')' && c != ',') {
					entry = entry.concat(c.toString());
					i++;
					c = aBString.charAt(i + 1);
				}
				lastNode = new NodoDobleE(entry);
			}
		}
	}

	public ArbolBinarioEnhebrado(ArbolBinarioLL arbolB) {

		if (arbolB.isEmpty()) {
			return;
		}

		cabeza = new NodoDobleE(null);
		cabeza.setBi(0);
		cabeza.setLi(cabeza);
		cabeza.setBd(1);
		cabeza.setLd(cabeza);

		enhebrarInorden(arbolB.getRoot(), null, null, null, null);
	}

	private void enhebrarInorden(NodoDoble x, NodoDoble parent, NodoDobleE parentE, Stack<NodoDobleE> antIRD,
			Stack<NodoDobleE> sigIRD) {

		assert (x != null) : "como vas a enhebrar algo nulo pdjo";

		if (antIRD == null) {
			antIRD = new Stack<NodoDobleE>();
		}
		if (sigIRD == null) {
			sigIRD = new Stack<NodoDobleE>();
		}

		NodoDobleE xE, antX, sigX;

		xE = new NodoDobleE(x.getD());
		if (x.getLi() != null) {
			sigIRD.push(xE);
			enhebrarInorden(x.getLi(), x, xE, antIRD, sigIRD);
		} else {
			if (antIRD.isEmpty()) {
				xE.setLi(cabeza);
			} else {
				antX = antIRD.pop();
				xE.setLi(antX);
			}
			xE.setBi(0);
		}
		if (x.getLd() != null) {
			antIRD.push(xE);
			enhebrarInorden(x.getLd(), x, xE, antIRD, sigIRD);
		} else {
			if (sigIRD.isEmpty()) {
				xE.setLd(cabeza);
			} else {
				sigX = sigIRD.pop();
				xE.setLd(sigX);
			}
			xE.setBd(0);
		}
		if (parent != null) {
			if (parent.getLi() == x) {
				parentE.setLi(xE);
			} else {
				parentE.setLd(xE);
			}
		} else {
			cabeza.setLi(xE);
			cabeza.setBi(1);
		}
	}

	public String inorden(NodoDobleE r) {
		NodoDobleE x = nextIRD(r);
		String IRD = "";
		while(x != getCabeza()) {
			IRD += x.getD().toString();
			x = nextIRD(x);
		}
		return IRD;
	}
	
	public NodoDobleE nextIRD(NodoDobleE x) {
		NodoDobleE sig = x.getLd();
		if (x.getBd() == 1) {
			while (sig.getBi() == 1) {
				sig = sig.getLi();
			}
		}
		return sig;
	}

	public boolean isEmpty() {
		return cabeza.getBi() == 0;
	}
	
	public NodoDobleE getCabeza() {
		return cabeza;
	}
	
	public void printIRD() {
		System.out.println(inorden(getCabeza()));
	}

	public static void main(String[] args) {
		ArbolBinarioLL a = new ArbolBinarioLL("a(b(k(m(,o(p,r)),),),c(e(f,g(h(s,),)),d))"
				+ "", false);
		a.printIRD();
		a.printIDR();
		ArbolBinarioEnhebrado ae = new ArbolBinarioEnhebrado(a);
		ae.printIRD();
	}
	
}
