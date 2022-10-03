package sneyderbg.logica3.nodos;

import java.util.Arrays;
import java.util.List;

import sneyderbg.logica3.util.Symbols;

public class NodoSimple extends Nodo {

	private NodoSimple liga;

	public NodoSimple() {
		super();
	}

	public NodoSimple(Object dato) {
		super(dato);
	}

	public NodoSimple(Object dato, NodoSimple liga) {
		super(dato);
		this.liga = liga;
	}

	public NodoSimple getLiga() {
		return liga;
	}

	public void setLiga(NodoSimple X) {
		liga = X;
	}

	public List<StringBuilder> consNodeRepr(boolean includeLiga, int fieldWidth) {

		StringBuilder topLine, line, bottomLine;
		String d, l;

		d = getDato() == null ? " " : getDato().toString();
		l = getLiga() == null ? Symbols.NULL : (includeLiga ? "@" + Integer.toHexString(getLiga().hashCode()) : " ");

		if (fieldWidth == 0) {

			fieldWidth = Math.max(d.length(), l.length());

		}

		// └ ┘ ┌ ┐ ─ │ ┼ ┴ ┬ ┤ ├
		topLine = new StringBuilder((Symbols.TOP_T + Symbols.HORIZONTAL.repeat(fieldWidth)).repeat(2));
		topLine.replace(0, 1, Symbols.TOP_LEFT).append(Symbols.TOP_LEFT);

		line = new StringBuilder((Symbols.VERTICAL + "%" + fieldWidth + "s").repeat(2));
		line.append(Symbols.VERTICAL);
		line = new StringBuilder(String.format(line.toString(), d, l));

		bottomLine = new StringBuilder((Symbols.BOTTOM_T + Symbols.HORIZONTAL.repeat(fieldWidth)).repeat(2));
		bottomLine.replace(0, 1, Symbols.BOTTOM_LEFT).append(Symbols.BOTTOM_RIGHT);

		return Arrays.asList(topLine, line, bottomLine);

	}

	@Override
	public String toString() {

		String r, fields, d, l;
		r = "NS" + "@" + Integer.toHexString(hashCode());

		if (getDato() == null) {
			d = "null";
		} else {
			d = getDato().toString();
		}
		if (getLiga() == null) {
			l = "null";
		} else {
			l = "@" + Integer.toHexString(getLiga().hashCode());
		}

		fields = String.format("(d=%s,l=%s)", d, l);

		return r.concat(fields);

	}

}
