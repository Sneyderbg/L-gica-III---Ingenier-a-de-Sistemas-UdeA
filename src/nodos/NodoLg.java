package nodos;

/**
 * Clase que representa un nodo con 3 campos para las listas generalizadas.
 * 
 * @author sneyd
 *
 */
public class NodoLg {

	/**
	 * Indica si el campo {@link #dato} es un átomo o es un apuntador hacia una
	 * sublista, <b>0</b> y <b>1</b> respectivamente.
	 */
	private int sw;

	/**
	 * Dato del nodo.
	 */
	private Object dato;

	/**
	 * Liga derecha del nodo.
	 */
	private NodoLg liga;

	/**
	 * Inicializa el nodo solo con el campo de liga.
	 */
	public NodoLg(NodoLg l) {
		this.liga = l;
		this.sw = 0;
		this.dato = null;
	}

	/**
	 * Contructor. Inicializa el objeto {@link NodoLg} con los argumentos
	 * entregados.
	 * 
	 * @param sw Switch. Determina si este nodo es un átomo o un apuntador hacia una
	 *           sublista.
	 * @param d  Dato a almacenar en el nodo.
	 * @param l  {@link NodoLg} al cual apunta la liga de este nodo.
	 */
	public NodoLg(int sw, Object d, NodoLg l) {
		assert sw == 0 || sw == 1 : "sw must be 0 or 1";
		this.sw = sw;
		this.dato = d;
		this.liga = l;
	}

	public int getSw() {
		return sw;
	}

	public void setSw(int sw) {
		this.sw = sw;
	}

	public Object getDato() {
		return dato;
	}

	public void setDato(Object dato) {
		this.dato = dato;
	}

	public NodoLg getLiga() {
		return liga;
	}

	public void setLiga(NodoLg liga) {
		this.liga = liga;
	}

	@Override
	public String toString() {
		String r, fields, d, l;
		r = "NLg" + "@" + Integer.toHexString(hashCode());

		if (dato == null) {
			d = "null";
		} else {
			d = dato.toString();
		}
		if (liga == null) {
			l = "null";
		} else {
			l = "@" + Integer.toHexString(liga.hashCode());
		}
		fields = String.format("(sw=%d,d=%s,l=%s)", sw, d, l);

		return r.concat(fields);
	}

}
