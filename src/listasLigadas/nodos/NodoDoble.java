package listasLigadas.nodos;

public class NodoDoble {
	public NodoDoble() {
		Dato = null;
	}

	public NodoDoble(Object d) {
		Dato = d;
	}

	private Object Dato;
	private NodoDoble Li, Ld;

	public Object RetornarDato() {
		return Dato;
	}

	public NodoDoble RetornarLi() {
		return Li;
	}

	public NodoDoble RetornarLd() {
		return Ld;
	}

	public void AsignarDato(Object D) {
		Dato = D;
	}

	public void AsignarLi(NodoDoble X) {
		Li = X;
	}

	public void AsignarLd(NodoDoble X) {
		Ld = X;
	}
}
