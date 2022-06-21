package listasLigadas.listas;

import listasLigadas.nodos.NodoSimple;

public class LSLC extends LSL {
	public LSLC() {
		super();
	}

	@Override
	public boolean FinDeRecorrido(NodoSimple p) {
		return p == ultimoNodo;
	}

	@Override
	public NodoSimple Anterior(NodoSimple p) {
		if (isEmpty()) {
			return null;
		}
		if (p == primerNodo) {
			return ultimoNodo;
		} else {
			NodoSimple x = primerNodo;
			while (x.getLiga() != p) {
				x = x.getLiga();
			}
			return x;
		}
	}

	@Override
	public void RecorrerLista() {
		NodoSimple p = primerNodo;
		do {
			System.out.println(p.getDato());
			p = p.getLiga();
		} while (!FinDeRecorrido(p));
	}
	/* Falta */
}
