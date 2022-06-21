package nodos;

/**
 * Clase que representa la estrutura de un Nodo Doble.
 * 
 * @author sneyd
 *
 */
public class NodoDoble {

	/**
	 * Liga izquierda del nodo doble.
	 */
	private NodoDoble Li;

	/**
	 * Liga derecha del nodo doble.
	 */
	private NodoDoble Ld;

	/**
	 * Dato del nodo doble.
	 */
	private Object d;

	/**
	 * Constructor. Inicializa el objeto instanciado asignando el campo {@link #d}
	 * con el parámetro entregado.
	 * 
	 * @param d -> Valor a asignar al campo {@link #d}.
	 */
	public NodoDoble(Object d) {
		this.d = d;
	}

	/**
	 * Retorna el {@link NodoDoble} conectado a la liga izquierda.
	 * 
	 * @return {@link NodoDoble} conectado por el campo {@link #Li}.
	 */
	public NodoDoble getLi() {
		return Li;
	}

	/**
	 * Conecta el {@link NodoDoble} entregado como parámetro a la liga izquierda de
	 * este nodo.
	 * 
	 * @param li -> {@link NodoDoble} a conectar.
	 */
	public void setLi(NodoDoble li) {
		Li = li;
	}

	/**
	 * Retorna el {@link NodoDoble} conectado a la liga derecha.
	 * 
	 * @return {@link NodoDoble} conectado por el campo {@link #Ld}.
	 */
	public NodoDoble getLd() {
		return Ld;
	}

	/**
	 * Conecta el {@link NodoDoble} entregado como parámetro a la liga derecha de
	 * este nodo.
	 * 
	 * @param ld -> {@link NodoDoble} a conectar.
	 */
	public void setLd(NodoDoble ld) {
		Ld = ld;
	}

	/**
	 * Retorna el objeto {@link #d}.
	 * 
	 * @return El objeto del campo {@link #d}, sea nulo o no.
	 */
	public Object getD() {
		return d;
	}

	/**
	 * Asigna al campo {@link #d} el objeto entregado en el parámetro.
	 * 
	 * @param d -> Objeto a asignar al campo {@link #d}.
	 */
	public void setD(Object d) {
		this.d = d;
	}
	
	@Override
	public String toString() {
		String r, fields, dd, li, ld;
		r = "ND" + "@" + Integer.toHexString(hashCode());

		if (d == null) {
			dd = "null";
		} else {
			dd = d.toString();
		}
		if (Li == null) {
			li = "null";
		} else {
			li = "@" + Integer.toHexString(Li.hashCode());
		}
		if (Ld == null) {
			ld = "null";
		} else {
			ld = "@" + Integer.toHexString(Ld.hashCode());
		}
		fields = String.format("(d=%s,li=%s,ld=%s)", dd, li, ld);

		return r.concat(fields);
	}

}
