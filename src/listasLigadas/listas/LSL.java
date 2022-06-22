package listasLigadas.listas;

import javax.swing.JOptionPane;

import listasLigadas.nodos.NodoSimple;

public class LSL {

	public LSL() {
		primerNodo = null;
		ultimoNodo = null;
	}

	protected NodoSimple primerNodo, ultimoNodo;

	public NodoSimple getPrimerNodo() {
		return primerNodo;
	}

	public NodoSimple getUltimoNodo() {
		return ultimoNodo;
	}

	public boolean isEmpty() {
		return primerNodo == null;
	}

	public boolean FinDeRecorrido(NodoSimple p) {
		return p == null;
	}

	public int getSize() {

		if (isEmpty()) {
			return 0;
		}

		int i = 0;
		NodoSimple nodoX = getPrimerNodo();

		while (nodoX != null) {
			i++;
			nodoX = nodoX.getLiga();
		}

		return i;

	}

	public NodoSimple Anterior(NodoSimple p) {
		if (p == primerNodo) {
			return null;
		} else {
			NodoSimple x = getPrimerNodo();
			while (x.getLiga() != p) {
				x = x.getLiga();
			}
			return x;
		}
	}

	public void RecorrerLista() {
		NodoSimple p = getPrimerNodo();
		while (!FinDeRecorrido(p)) {
			System.out.println(p.getDato());
			p = p.getLiga();
		}
	}

	public NodoSimple BuscarDondeInsertar(Object d) {
		NodoSimple p = getPrimerNodo();
		while (!FinDeRecorrido(p) && d.hashCode() < p.getDato().hashCode()) {
			p = p.getLiga();
		}
		NodoSimple ap = Anterior(p);
		return ap;
	}

	public void Insertar(Object d, NodoSimple anterior) {
		NodoSimple x = new NodoSimple(d);
		Conectar(x, anterior);
	}

	/**
	 * Conecta el {@link NodoSimple} <b>x</b> adelante del {@link NodoSimple}
	 * <b>ax</b>, y actualiza el {@link #primerNodo} y {@link #ultimoNodo} si es
	 * necesario.
	 * 
	 * @param x  {@link NodoSimple} a conectar.
	 * @param ax {@link NodoSimple} anterior a <b>x</b>.
	 */
	public void Conectar(NodoSimple x, NodoSimple ax) {
		if (ax == null) {
			x.setLiga(primerNodo);
			primerNodo = x;
			if (x.getLiga() == null) {
				ultimoNodo = x;
			}
			return;
		}
		if (ax == ultimoNodo) {
			ultimoNodo = x;
			ax.setLiga(x);
			return;
		}
		x.setLiga(ax.getLiga());
		ax.setLiga(x);
	}

	/**
	 * A?ade el {@link NodoSimple} <b>x</b> al final de la lista.
	 * 
	 * @param x {@link NodoSimple} a aùadir.
	 */
	public void add(NodoSimple x) {
		Conectar(x, getUltimoNodo());
	}

	public NodoSimple BuscarDato(Object d) {
		if (isEmpty()) {
			JOptionPane.showMessageDialog(null, "La lista estù vacia");
			return null;
		}
		NodoSimple p = getPrimerNodo();
		while (!FinDeRecorrido(p) && d.hashCode() != p.getDato().hashCode()) {
			p = p.getLiga();
		}
		if (p == null) {
			System.out.println("Dato no encontrado");
		}
		return p;
	}

	public void Borrar(NodoSimple x) {
		if (x == null) {
			System.out.println("ERROR = Parùmetro invùlido");
			return;
		}
		Desconectar(x, Anterior(x));
	}

	public void Desconectar(NodoSimple x, NodoSimple ax) {
		if (ax == null) {
			primerNodo = x.getLiga();
			return;
		}
		if (x == ultimoNodo) {
			ax.setLiga(null);
			ultimoNodo = ax;
			return;
		}
		ax.setLiga(x.getLiga());
	}

	public void Invertir() {
		NodoSimple p, q, r;
		p = getPrimerNodo();
		q = Anterior(p);
		while (!FinDeRecorrido(p)) {
			r = q;
			q = p;
			p = p.getLiga();
			q.setLiga(r);
		}
		ultimoNodo = primerNodo;
		primerNodo = q;
	}
}
