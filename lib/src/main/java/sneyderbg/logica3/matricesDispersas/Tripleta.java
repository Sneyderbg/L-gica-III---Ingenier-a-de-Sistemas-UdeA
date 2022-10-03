package sneyderbg.logica3.matricesDispersas;

/**
 * Clase representativa de la estructura de una Tripleta.
 * 
 * @author sneyd
 *
 */
public class Tripleta implements Cloneable {

	private int fila;
	private int columna;
	private Object valor;

	/**
	 * Contructor. Inicia la clase 'Tripleta' estableciendo el valor de los campos
	 * privados por los valores que se entregan en los parámetros.
	 * 
	 * @param f Valor del campo privado 'fila'.
	 * @param c Valor del campo privado 'columna'.
	 * @param v Valor del campo privado 'valor'.
	 */
	public Tripleta(int f, int c, Object v) {
		this.fila = f;
		this.columna = c;
		this.valor = v;
	}

	/**
	 * Getter del campo {@link #fila}.
	 * 
	 * @return {@link #fila}.
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * Setter del campo {@link #fila}.
	 * 
	 * @param fila Valor a asignar al campo {@link #fila}.
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}

	/**
	 * Getter del campo {@link #columna}.
	 * 
	 * @return {@link #columna}.
	 */
	public int getColumna() {
		return columna;
	}

	/**
	 * Setter del campo {@link #columna}.
	 * 
	 * @param columna Valor a asignar al campo {@link #columna}.
	 */
	public void setColumna(int columna) {
		this.columna = columna;
	}

	/**
	 * Getter del campo {@link #valor}.
	 * 
	 * @return {@link #valor}.
	 */
	public Object getValor() {
		return valor;
	}

	/**
	 * Setter del campo {@link #valor}.
	 * 
	 * @param valor Valor a asignar al campo {@link #valor}.
	 */
	public void setValor(Object valor) {
		this.valor = valor;
	}

	@Override
	public Tripleta clone() {
		try {

			return (Tripleta) super.clone();

		} catch (Exception e) {

			e.printStackTrace();
			return new Tripleta(getFila(), getColumna(), getValor());	

		}
	}

	@Override
	public String toString() {

		return String.format("(%d, %d, %s)", getFila(), getColumna(), getValor().toString());

	}

}