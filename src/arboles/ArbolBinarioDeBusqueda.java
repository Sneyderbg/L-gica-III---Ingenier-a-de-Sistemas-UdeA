package arboles;

import nodos.NodoDoble;

public class ArbolBinarioDeBusqueda extends ArbolBinarioLL {

	protected final String abcES = "abcdefghijklmnñopqrstuvwxyz0123456789";

	public ArbolBinarioDeBusqueda() {
		super();
	}

	public ArbolBinarioDeBusqueda(Object d) {
		assert (d instanceof Integer || d instanceof String)
				: "Solo está definido el orden para cadenas de texto y números enteros";
		this.root = new NodoDoble(d);
	}

	public ArbolBinarioDeBusqueda(String datos, char splitter) {
		String entry = "";
		char c;
		int d;
		for (int i = 0; i < datos.length(); i++) {
			c = datos.charAt(i);
			while (c != splitter && abcES.contains(String.valueOf(c))) {
				entry += c;
				i++;
				if (i >= datos.length())
					break;
				c = datos.charAt(i);
			}
			if (entry != "") {
				try {
					d = Integer.parseInt(entry);
					insert(d);
				} catch (NumberFormatException e) {
					insert(entry);
				}
				entry = "";
			}
		}
	}

	public void insert(Object d) {
		if (!(d instanceof Integer || d instanceof String)) {
			throw new IllegalArgumentException("Solo está definido el orden para cadenas de texto y números enteros");
		}
		if (isEmpty()) {
			this.root = new NodoDoble(d);
			return;
		}
		if (!getRoot().getD().getClass().isInstance(d)) {
			throw new IllegalArgumentException("el dato a insertar debe ser del mismo tipo que el dato de la raíz");
		}

		NodoDoble x, parent;
		parent = null;
		x = getRoot();
		int r = 0;
		while (x != null) {
			parent = x;
			r = compare(d, x.getD());
			switch (r) {
			case -1:
				x = x.getLi();
				break;

			case 1:
				x = x.getLd();
				break;

			case 0:
				x = new NodoDoble(d);
				x.setLd(parent.getLd());
				parent.setLd(x);
				return;

			default:
				break;
			}
		}
		x = new NodoDoble(d);
		if (r == -1) {
			parent.setLi(x);
		} else {
			parent.setLd(x);
		}
	}

	// -1 a<b, 0 a=b, 1 a>b
	protected int compare(Object a, Object b) {
		if (Integer.class.isInstance(getRoot().getD())) {
			if ((Integer) a < (Integer) b) {
				return -1;
			} else if ((Integer) a > (Integer) b) {
				return 1;
			} else {
				return 0;
			}
		} else {
			String aS = (String) a;
			String bS = (String) b;
			char aC, bC;
			int i, idxA, idxB;

			i = 0;
			while (i < Math.min(aS.length(), bS.length())) {
				aC = aS.charAt(i);
				bC = bS.charAt(i);
				idxA = abcES.indexOf(aC);
				idxB = abcES.indexOf(bC);

				if (idxA < idxB)
					return -1;
				if (idxA > idxB)
					return 1;
				i++;
			}
			if (i <= bS.length())
				return -1;
			if (i <= aS.length())
				return 1;
			return 0;
		}
	}

	public static void main(String[] args) {
		ArbolBinarioDeBusqueda a = new ArbolBinarioDeBusqueda("18 4 5 38", ' ');
		a.printIRD();
		ArbolBinarioEnhebrado ae = new ArbolBinarioEnhebrado((ArbolBinarioLL) a);
		ae.printIRD();
	}

}
