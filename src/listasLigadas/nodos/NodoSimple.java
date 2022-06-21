package listasLigadas.nodos;

public class NodoSimple {
	public NodoSimple() {
		Dato = null;
	}

	public NodoSimple(Object d) {
		Dato = d;
	}

	private Object Dato;
	private NodoSimple Liga;

	public Object getDato() {
		return Dato;
	}

	public NodoSimple getLiga() {
		return Liga;
	}

	public void setDato(Object D) {
		Dato = D;
	}

	public void setLiga(NodoSimple X) {
		Liga = X;
	}

	@Override
	public String toString() {
		return Dato.toString();
	}
	
}
