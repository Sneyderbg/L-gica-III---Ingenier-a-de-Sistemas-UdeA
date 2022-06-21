package nodos;

public class NodoDobleE {

	/**
	 * 0 - <b>Li</b> es una hebra. <br>
	 * 1 - <b>Li</b> apunta hacia el hijo izquierdo.
	 */
	private int Bi;

	/**
	 * 0 - <b>Ld</b> es una hebra. <br>
	 * 1 - <b>Ld</b> apunta hacia el hijo derecho.
	 */
	private int Bd;

	/**
	 * Liga izquierda del nodo doble.
	 */
	private NodoDobleE Li;

	/**
	 * Liga derecha del nodo doble.
	 */
	private NodoDobleE Ld;

	/**
	 * Object del nodo doble.
	 */
	private Object d;

	/**
	 * Constructor. Inicializa el objeto instanciado asignando el campo {@link #d}
	 * con el parámetro entregado.
	 * 
	 * @param d -> Valor a asignar al campo {@link #d}.
	 */
	public NodoDobleE(Object d) {
		this.d = d;
		this.Bi = 1;
		this.Bd = 1;
		this.Li = null;
		this.Ld = null;
	}

	/**
	 * Retorna el {@link NodoDobleE} conectado a la liga izquierda.
	 * 
	 * @return {@link NodoDobleE} conectado por el campo {@link #Li}.
	 */
	public NodoDobleE getLi() {
		return Li;
	}

	/**
	 * Conecta el {@link NodoDobleE} entregado como parámetro a la liga izquierda de
	 * este nodo.
	 * 
	 * @param li -> {@link NodoDobleE} a conectar.
	 */
	public void setLi(NodoDobleE li) {
		Li = li;
	}

	/**
	 * Retorna el {@link NodoDobleE} conectado a la liga derecha.
	 * 
	 * @return {@link NodoDobleE} conectado por el campo {@link #Ld}.
	 */
	public NodoDobleE getLd() {
		return Ld;
	}

	/**
	 * Conecta el {@link NodoDobleE} entregado como parámetro a la liga derecha de
	 * este nodo.
	 * 
	 * @param ld -> {@link NodoDobleE} a conectar.
	 */
	public void setLd(NodoDobleE ld) {
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

	public int getBi() {
		return Bi;
	}

	public int getBd() {
		return Bd;
	}

	public void setBi(int bi) {
		Bi = bi;
	}

	public void setBd(int bd) {
		Bd = bd;
	}

	@Override
	public String toString() {
		String r, fields, dd, li, ld;
		r = "NDE" + "@" + Integer.toHexString(hashCode());

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
		fields = String.format("(d=%s,li=%s,ld=%s,bi=%d,bd=%d)", dd, li, ld, Bi, Bd);

		return r.concat(fields);
	}

}
