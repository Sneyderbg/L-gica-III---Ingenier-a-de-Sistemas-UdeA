package listasLigadas.nodos;

public class NodoSimple {

	private Object dato;
	private NodoSimple liga;

	public NodoSimple() {
		dato = null;
	}

	public NodoSimple(Object d) {
		dato = d;
	}

	public NodoSimple(Object d, NodoSimple liga){
		this.dato = d;
		this.liga = liga;
	}
	
	public Object getDato() {
		return dato;
	}

	public NodoSimple getLiga() {
		return liga;
	}

	public void setDato(Object D) {
		dato = D;
	}

	public void setLiga(NodoSimple X) {
		liga = X;
	}

	@Override
	public String toString() {
		return dato.toString();
	}

}
