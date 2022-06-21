package listasLigadas.listas;

import listasLigadas.nodos.NodoDoble;

public class LDL {
	public LDL() {
		Primero = null;
		ultimo = null;
	}

	protected NodoDoble Primero, ultimo;

	public NodoDoble PrimerNodo() {
		return Primero;
	}

	public NodoDoble ultimoNodo() {
		return ultimo;
	}

	public NodoDoble UltimoNodo() {
		return ultimo;
	}

	public Boolean esVacia() {
		return Primero == null;
	}

	public Boolean FinDeRecorrido(NodoDoble p) {
		return p == null;
	}

	public NodoDoble Anterior(NodoDoble p) {
		return p.RetornarLi();
	}

	public void RecorrerLista() {
		NodoDoble p = Primero;
		while (!FinDeRecorrido(p)) {
			System.out.println(p.RetornarDato());
			p = p.RetornarLd();
		}
	}

	public NodoDoble BuscarDondeInsertar(Object d) {
		NodoDoble p = Primero;
		while (!FinDeRecorrido(p) && d.hashCode() > p.RetornarDato().hashCode()) {
			p = p.RetornarLd();
		}
		return p.RetornarLi();
	}

	public void Insertar(Object d, NodoDoble anterior) {
		NodoDoble x = new NodoDoble(d);
		Conectar(x, anterior);
	}

	public void Conectar(NodoDoble x, NodoDoble ax) {
		if (ax == null) {
			if (Primero == null) {
				ultimo = x;
			}
			x.AsignarLd(Primero);
			Primero = x;
			return;
		}
		x.AsignarLd(ax.RetornarLd());
		x.AsignarLi(ax);
		ax.AsignarLd(x);
		if (ax == ultimo) {
			ultimo = x;
			return;
		} else {
			x.RetornarLd().AsignarLi(x);
		}
	}

	public NodoDoble BuscarDato(Object d) {
		NodoDoble p = Primero;
		while (!FinDeRecorrido(p) && p.RetornarDato() != d) {
			p = p.RetornarLd();
		}
		if (p == null) {
			System.out.println("ERROR: Dato no encontrado");
		}
		return p;
	}

	public void Borrar(NodoDoble x) {
		if (x == null) {
			System.out.println("ERROR: Parámetro inválido");
			return;
		}
		Desconectar(x);
	}

	public void Desconectar(NodoDoble x) {
		if (x == Primero) {
			Primero = x.RetornarLd();
			if (x == ultimo) {
				ultimo = Primero;
				return;
			}
			Primero.AsignarLi(null);
		}
		x.RetornarLi().AsignarLd(x.RetornarLd());
		if (x == ultimo) {
			ultimo = x.RetornarLi();
		} else {
			x.RetornarLd().AsignarLi(x.RetornarLi());
		}
	}
}
