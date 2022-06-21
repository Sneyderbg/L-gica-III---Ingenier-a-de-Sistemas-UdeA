package arboles;

import nodos.NodoAVL;
import nodos.NodoDoble;

public class ArbolAVL extends ArbolBinarioDeBusqueda {

	private int maxFb;

	public ArbolAVL(Object d) {
		root = new NodoAVL(d);
	}

	public ArbolAVL(ArbolBinarioLL aB) {
		root = (NodoDoble) copy(aB.getRoot());
	}

	@Override
	public void insert(Object d) {
		if (!(d instanceof Integer || d instanceof String)) {
			throw new IllegalArgumentException("Solo está definido el orden para cadenas de texto y números enteros");
		}
		if (isEmpty()) {
			this.root = new NodoAVL(d);
			return;
		}
		if (!getRoot().getD().getClass().isInstance(d)) {
			throw new IllegalArgumentException("el dato a insertar debe ser del mismo tipo que el dato de la raíz");
		}

		NodoAVL x, p, q, pivote, pp;
		int r;
		x = new NodoAVL(d);
		p = getRoot();
		q = null;
		pivote = getRoot();
		pp = null;
		while (p != null) {
			r = compare(d, p.getD());
			if (r == 0) {
				System.out.println(getClass() + ": ya existe el dato " + d.toString() + ".");
				return;
			}
			if (p.getFb() != 0) {
				pivote = p;
				pp = q;
			}
			q = p;
			if (r == -1) {
				p = (NodoAVL) p.getLi();
			} else {
				p = (NodoAVL) p.getLd();
			}
		}
		r = compare(d, q.getD());
		if (r == -1) {
			q.setLi(x);
		} else {
			q.setLd(x);
		}
		r = compare(d, pivote.getD());
		if (r == -1) {
			pivote.setFb(pivote.getFb() + 1);
			q = (NodoAVL) pivote.getLi();
		} else {
			pivote.setFb(pivote.getFb() - 1);
			q = (NodoAVL) pivote.getLd();
		}
		p = q;
		while (p != x) {
			r = compare(d, p.getD());
			if (r == -1) {
				p.setFb(1);
				p = (NodoAVL) p.getLi();
			} else {
				p.setFb(-1);
				p = (NodoAVL) p.getLd();
			}
		}
		if (Math.abs(pivote.getFb()) < 2) {
			return;
		}
		if (pivote.getFb() == 2) {
			if (q.getFb() == 1) {
				turnRight(pivote, q);
			} else {
				doubleTurnRight(pivote, q);
			}
		} else {
			if (q.getFb() == -1) {
				turnLeft(pivote, q);
			} else {
				doubleTurnLeft(pivote, q);
			}
		}
		if (pivote == getRoot()) {
			this.root = q;
			return;
		}
		if (pp.getLi() == pivote) {
			pp.setLi(q);
		} else {
			pp.setLd(q);
		}
	}

	protected void recalculateFb(NodoAVL r) {
		if (r == null)
			return;

		r.setFb(getHeight(r.getLi()) - getHeight(r.getLd()));
		recalculateFb((NodoAVL) r.getLi());
		recalculateFb((NodoAVL) r.getLd());
	}

	// Fb(p) = +2, Fb(q) = +1
	private NodoAVL turnRight(NodoAVL p, NodoAVL q) {
		p.setLi(q.getLd());
		q.setLd(p);
		p.setFb(0);
		q.setFb(0);
		return q;
//		if(parent != null) {
//			if (parent.getLi() == p) {
//				parent.setLi(q);
//			} else {
//				parent.setLd(q);
//			}
//		} else {
//			this.root = q;
//		}
	}

	// Fb(p) = -2, Fb(q) = -1
	private NodoAVL turnLeft(NodoAVL p, NodoAVL q) {
		p.setLd(q.getLi());
		q.setLi(p);
		p.setFb(0);
		q.setFb(0);
		return q;
//		if(parent != null) {
//			if (parent.getLi() == p) {
//				parent.setLi(q);
//			} else {
//				parent.setLd(q);
//			}
//		} else {
//			this.root = q;
//		}
	}

	// Fb(p) = +2, Fb(q) = -1
	private NodoAVL doubleTurnRight(NodoAVL p, NodoAVL q) {
		NodoAVL r = (NodoAVL) q.getLd();
		q.setLd(r.getLi());
		r.setLi(q);
		p.setLi(r.getLd());
		r.setLd(p);
		switch (r.getFb()) {
		case -1:
			p.setFb(0);
			q.setFb(1);
			break;

		case +1:
			p.setFb(-1);
			q.setFb(0);
			break;

		case 0:
			p.setFb(0);
			q.setFb(0);
			break;

		default:
			break;
		}
		r.setFb(0);
		return r;
	}

	// Fb(p) = -2, Fb(q) = +1
	private NodoAVL doubleTurnLeft(NodoAVL p, NodoAVL q) {
		NodoAVL r = (NodoAVL) q.getLi();
		q.setLi(r.getLd());
		r.setLd(q);
		p.setLd(r.getLi());
		r.setLi(p);
		switch (r.getFb()) {
		case -1:
			p.setFb(1);
			q.setFb(0);
			break;

		case 1:
			p.setFb(0);
			q.setFb(-1);
			break;

		case 0:
			p.setFb(0);
			q.setD(0);
			break;
		default:
			break;
		}
		r.setFb(0);
		return r;
	}

	private NodoAVL copy(NodoDoble r) {

		if (r == null)
			return null;

		int Fb;
		NodoAVL x, childL, childR;
		x = new NodoAVL(r.getD());
		childL = copy(r.getLi());
		childR = copy(r.getLd());
		Fb = getHeight(childL) - getHeight(childR);
		x.setFb(Fb);
		if (Fb > maxFb) {
			maxFb = Fb;
		}
		x.setLi(childL);
		x.setLd(childR);
		return x;
	}

	public boolean isBalanced() {
		return Math.abs(maxFb) < 2;
	}

	@Override
	public NodoAVL getRoot() {
		return (NodoAVL) super.getRoot();
	}

	public static void main(String[] args) {
		ArbolBinarioDeBusqueda a = new ArbolBinarioDeBusqueda("28 24 40 8 26 32 44 4 16 27 36 43 52 12 20 48", ' ');
		ArbolAVL aa = new ArbolAVL(a);
		aa.printIRD();
		System.out.println(aa.isBalanced());
		aa.insert(47);
		aa.printIRD();
		System.out.println(aa.isBalanced());
	}

}
