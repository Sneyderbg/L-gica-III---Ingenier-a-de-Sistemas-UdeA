package sneyderbg.logica3.listasLigadas.listas;

import java.util.Arrays;
import java.util.List;

import sneyderbg.logica3.nodos.NodoSimple;
import sneyderbg.logica3.util.Symbols;

/**
 * Clase Lista Simplemente Ligada
 */
public class LSL {

	/**
	 * Apuntadores hacia el primer y último nodo de esta lista
	 */
	protected NodoSimple primerNodo, ultimoNodo;

	/**
	 * Constructor. Construye una lista simplemente ligada con {@link #primerNodo} y
	 * {@link #ultimoNodo} inicialmente nulos.
	 */
	public LSL() {
		primerNodo = null;
		ultimoNodo = null;
	}

	/**
	 * Retorna el primer nodo de esta lista
	 * 
	 * @return {@link #primerNodo}
	 */
	public NodoSimple getPrimerNodo() {
		return primerNodo;
	}

	protected void setPrimerNodo(NodoSimple primerNodo) {
		this.primerNodo = primerNodo;
	}

	/**
	 * Retorna el último nodo de esta lista
	 * 
	 * @return {@link #ultimoNodo}
	 */
	public NodoSimple getUltimoNodo() {
		return ultimoNodo;
	}

	protected void setUltimoNodo(NodoSimple ultimoNodo) {
		this.ultimoNodo = ultimoNodo;
	}

	/**
	 * Comprueba si esta lista está vacía.
	 * 
	 * @return {@code true} si {@code primerNodo == null}, {@code false} de lo
	 *         contrario.
	 */
	public boolean isEmpty() {
		return primerNodo == null;
	}

	/**
	 * Evalua si el nodo <b>p</b> ya recorrió esta lista por completo.
	 * 
	 * @param p {@link NodoSimple} a evaluar.
	 * @return {@code true} si el nodo <b>p</b> ya recorrió esta lista.
	 */
	public boolean finDeRecorrido(NodoSimple p) {
		return p == null;
	}

	/**
	 * Retorna el número de nodos que contiene esta lista
	 * 
	 * @return Número de nodos de esta lista.
	 */
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

	/**
	 * Busca y retorna el nodo anterior a <b>p</b>.
	 * 
	 * @param p
	 * @return
	 */
	public NodoSimple anterior(NodoSimple p) {
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

	public NodoSimple buscarDondeInsertar(Object d) {
		NodoSimple p = getPrimerNodo();
		while (!finDeRecorrido(p) && d.hashCode() < p.getDato().hashCode()) {
			p = p.getLiga();
		}
		NodoSimple ap = anterior(p);
		return ap;
	}

	public void insertar(Object d, NodoSimple anterior) {
		NodoSimple x = new NodoSimple(d);
		conectar(x, anterior);
	}

	/**
	 * Conecta el {@link NodoSimple} <b>x</b> adelante del {@link NodoSimple}
	 * <b>ax</b>, y actualiza el {@link #primerNodo} y {@link #ultimoNodo} si es
	 * necesario.
	 * 
	 * @param x  {@link NodoSimple} a conectar.
	 * @param ax {@link NodoSimple} anterior a <b>x</b>.
	 */
	public void conectar(NodoSimple x, NodoSimple ax) {
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
	 * Añade el {@link NodoSimple} <b>x</b> al final de la lista.
	 * 
	 * @param x {@link NodoSimple} a aadir.
	 */
	public void add(NodoSimple x) {
		conectar(x, getUltimoNodo());
	}

	/**
	 * Busca y retorna el {@link NodoSimple} que contiene el dato <b>d</b>.
	 * 
	 * @param d Dato a buscar.
	 * @return {@link NodoSimple} con el dato <b>d</b>. Si el dato <b>d</b> no se
	 *         encontró, se retorna {@code null}.
	 */
	public NodoSimple buscarDato(Object d) {

		assert (!isEmpty()) : "La lista está vacia";

		NodoSimple p = getPrimerNodo();
		while (!finDeRecorrido(p) && d != p.getDato()) {
			p = p.getLiga();
		}
		return p;

	}

	public void borrar(NodoSimple x) {
		if (x == null) {
			System.out.println("ERROR = Parmetro invlido");
			return;
		}
		desconectar(x, anterior(x));
	}

	public void desconectar(NodoSimple x, NodoSimple ax) {
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

	public void invertir() {
		NodoSimple p, q, r;
		p = getPrimerNodo();
		q = anterior(p);
		while (!finDeRecorrido(p)) {
			r = q;
			q = p;
			p = p.getLiga();
			q.setLiga(r);
		}
		ultimoNodo = primerNodo;
		primerNodo = q;
	}

	public List<StringBuilder> consLSLRepr() {

		StringBuilder topLineRepr, midLineRepr, bottomLineRepr;
		topLineRepr = new StringBuilder();
		midLineRepr = new StringBuilder();
		bottomLineRepr = new StringBuilder();

		NodoSimple x = getPrimerNodo();
		List<StringBuilder> nodeRepr;

		while (x != null) {

			nodeRepr = x.consNodeRepr(false, 0);

			topLineRepr.append("   ").append(nodeRepr.get(0));

			midLineRepr.append(Symbols.HORIZONTAL).append(Symbols.HORIZONTAL).append('>');
			midLineRepr.append(nodeRepr.get(1));

			bottomLineRepr.append("   ").append(nodeRepr.get(2));

			x = x.getLiga();

		}

		return Arrays.asList(topLineRepr, midLineRepr, bottomLineRepr);

	}

	@Override
	public String toString() {

		String s = "";

		NodoSimple nodoX = getPrimerNodo();

		while (!finDeRecorrido(nodoX)) {

			if (nodoX != getPrimerNodo()) {

				s = s.concat(", ");

			}

			s = s.concat(nodoX.getDato().toString());

		}

		return s;

	}

	public void show() {

		List<StringBuilder> repr = consLSLRepr();

		for (StringBuilder sb : repr) {

			System.out.println(sb);

		}

	}

	public static void main(String[] args) {

		LSL a = new LSL();

		a.add(new NodoSimple(2));
		a.add(new NodoSimple("hola"));
		a.add(new NodoSimple('c'));

		a.show();

	}

}
