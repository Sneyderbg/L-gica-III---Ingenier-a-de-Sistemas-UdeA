package listasLigadas.nodos;

public class NodoDoble {
	public NodoDoble() {
		Dato = null;
	}

	public NodoDoble(Object d) {
		Dato = d;
		this.Li = null;
		this.Ld = null;
	}

	private Object Dato;
	private NodoDoble Li, Ld;

	public Object getDato() {
		return Dato;
	}

	public NodoDoble getLi() {
		return Li;
	}

	public NodoDoble getLd() {
		return Ld;
	}

	public void setDato(Object D) {
		Dato = D;
	}

	public void setLi(NodoDoble X) {
		Li = X;
	}

	public void setLd(NodoDoble X) {
		Ld = X;
	}
}
