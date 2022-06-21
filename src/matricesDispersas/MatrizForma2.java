package matricesDispersas;

import nodos.NodoDoble;

/**
 * Clase que representa una matriz dispersa implementando listas doblemente
 * ligadas circulares con nodo cabeza.
 * <p>
 * <b>Nota:</b> Esta matriz dispersa tiene un nodo cabeza en el cual se usa la
 * liga derecha para la lista de las filas, y la liga izquierda para la lista de
 * las columnas.
 * 
 * @author sneyd
 *
 */
public class MatrizForma2 {

	/**
	 * {@link NodoDoble} que representa la matriz dispersa.
	 * <p>
	 * <b>Nota:</b> Contiene el tamaño de la matriz dispersa.
	 */
	private NodoDoble mat;

	/**
	 * Objeto que representa el elemento nulo o vacío de la matriz dispersa.
	 * <p>
	 * <b>Nota:</b> Este campo se inicializa en los constructores.
	 */
	private final Object nulo;

	/**
	 * Constructor. Inicializa el objeto instanciado creando un {@link NodoDoble}
	 * conteniendo una {@link Tripleta} que describe el tamaño de la matriz
	 * dispersa.
	 * <p>
	 * Luego construye y conecta un {@link NodoDoble} cabeza para recorrer la lista
	 * de las filas y la lista de las columnas.
	 * 
	 * @param m    -> Número de filas de la matriz dispersa.
	 * @param n    -> Número de columnas de la matriz dispersa.
	 *             <p>
	 * @param nulo -> Establece el objet nulo o vacío de la matriz dispersa.<br>
	 *             Ejs: <br>
	 *             <i>1.</i> Si la matriz es de tipo {@link Boolean}, el parámetro
	 *             <b>nulo</b> es <code>false</code>.<br>
	 *             <i>2.</i> Si es de tipo {@link Integer}, <b>nulo</b> es
	 *             <b>0</b>.<br>
	 *             <i>3.</i> Si es tipo {@link String}, <b>nulo</b> es <b>""</b>.
	 */
	public MatrizForma2(int m, int n, Object nulo) {

		// se inicializa el nodo que representa la matriz
		Tripleta t = new Tripleta(m, n, null);
		mat = new NodoDoble(t);

		// se inicializa y se conecta el nodo cabeza para las filas y columnas
		NodoDoble nodoCabeza = new NodoDoble(null);
		nodoCabeza.setLd(nodoCabeza);
		nodoCabeza.setLi(nodoCabeza);
		mat.setLd(nodoCabeza);

		this.nulo = nulo;
	}

	/**
	 * Constructor. Construye la matriz dispersa a partir de los elementos
	 * diferentes al elemento nulo o vacío de la matriz entregada como parámetro.
	 * 
	 * @param matriz -> Matriz de enteros de la cual se construye esta matriz
	 *               dispersa.
	 *               <p>
	 * @param nulo   -> Establece el objeto nulo o vacío de la matriz dispersa.<br>
	 *               Ejs: <br>
	 *               <i>1.</i> Si la matriz es de tipo {@link Boolean}, el parámetro
	 *               <b>nulo</b> es <code>false</code>.<br>
	 *               <i>2.</i> Si es de tipo {@link Integer}, <b>nulo</b> es
	 *               <b>0</b>.<br>
	 *               <i>3.</i> Si es tipo {@link String}, <b>nulo</b> es <b>""</b>.
	 */
	public MatrizForma2(Object[][] matriz, Object nulo) {

		// tamaño de la matriz que se almacena en el nodo mat
		int m = matriz.length;
		int n = matriz[0].length;

		Tripleta t = new Tripleta(m, n, null);
		this.mat = new NodoDoble(t);

		// se inicializa y se conecta el nodo cabeza para las filas y columnas
		NodoDoble nodoCabeza = new NodoDoble(null);

		nodoCabeza.setLd(nodoCabeza); // liga derecha para lista de filas
		nodoCabeza.setLi(nodoCabeza); // liga izquierda para lista de columnas

		this.mat.setLd(nodoCabeza);

		this.nulo = nulo;

		// por cada elemento diferente del elemento nulo de la matriz se añade un nodo a
		// la matriz
		// dispersa conectandolo como corresponde.
		Object v;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				v = matriz[i][j];
				if (v != this.nulo) {
					t = new Tripleta(i, j, v);
					NodoDoble x = new NodoDoble(t);
					conectar(x, false); // al construir la matriz no hay nodos repetidos
				}
			}
		}
	}

	/**
	 * Invoca las funciones para conectar un {@link NodoDoble} <b>x</b> por filas y
	 * por columnas. <br>
	 * Si ya existe un nodo con la misma <b>fila</b> y <b>columna</b> que el nodo
	 * <b>x</b>, <b>reemplazar</b> determina que operación se hace.
	 * 
	 * @param nodoX      -> {@link NodoDoble} a conectar en la matriz dispersa.
	 * @param reemplazar -> Si es <code>true</code> se reemplaza el valor del nodo
	 *                   en la matriz dispersa con el valor del nodo <b>x</b>. De lo
	 *                   contrario no se reemplaza.
	 */
	public void conectar(NodoDoble nodoX, boolean reemplazar) {
		conectarPorFilas(nodoX, reemplazar);
		conectarPorColumnas(nodoX, reemplazar);
	}

	/**
	 * Conecta un {@link NodoDoble} <b>x</b> en la posición que le corresponde según
	 * su fila y columna. el {@link NodoDoble} es conectado en la lista de las
	 * filas, osea, utilizando su liga derecha.<br>
	 * Si ya existe un nodo con la misma <b>fila</b> y <b>columna</b> que el nodo
	 * <b>x</b>, <b>reemplazar</b> determina que operación se hace.
	 * 
	 * @param nodoX      -> {@link NodoDoble} a conectar.
	 * @param reemplazar -> Si es <code>true</code> se reemplaza el valor del nodo
	 *                   en la matriz dispersa con el valor del nodo <b>x</b>. De lo
	 *                   contrario no se reemplaza.
	 */
	public void conectarPorFilas(NodoDoble nodoX, boolean reemplazar) {
		Tripleta tripletaT = (Tripleta) nodoX.getD();
		int f = tripletaT.getFila();
		int c = tripletaT.getColumna();
		Object v = tripletaT.getValor();

		NodoDoble primerNodo = getPrimerNodo();

		NodoDoble antNodoQ = primerNodo;
		NodoDoble nodoQ = antNodoQ.getLd(); // liga derecha para la lista de las filas
		tripletaT = (Tripleta) nodoQ.getD();

		// se busca la primera fila menor o igual a 'f'
		while (nodoQ != primerNodo && tripletaT.getFila() < f) {
			antNodoQ = nodoQ;
			nodoQ = nodoQ.getLd();
			tripletaT = (Tripleta) nodoQ.getD();
		}

		// se busca la primera columna menor o igual a 'c'
		while (nodoQ != primerNodo && tripletaT.getColumna() < c) {
			antNodoQ = nodoQ;
			nodoQ = nodoQ.getLd();
			tripletaT = (Tripleta) nodoQ.getD();
		}

		// si el nodo no existe en la matriz
		if (nodoQ == primerNodo || f < tripletaT.getFila() || c < tripletaT.getColumna()) {

			// se conecta el nodo entre 'antNodoQ' y 'nodoQ'
			nodoX.setLd(nodoQ);
			antNodoQ.setLd(nodoX);
		} else { // ya existe

			// si 'reemplazar' es 'true'
			if (reemplazar) {
				tripletaT.setValor(v);
			} // else
			return;
		}
	}

	/**
	 * Conecta un {@link NodoDoble} <b>x</b> en la posición que le corresponde según
	 * su fila y columna. el {@link NodoDoble} es conectado en la lista de las
	 * columnas, osea, utilizando su liga izquierda.<br>
	 * Si ya existe un nodo con la misma <b>fila</b> y <b>columna</b> que el nodo
	 * <b>x</b>, <b>reemplazar</b> determina que operación se hace.
	 * 
	 * @param nodoX      -> {@link NodoDoble} a conectar.
	 * @param reemplazar -> Si es <code>true</code> se reemplaza el valor del nodo
	 *                   en la matriz dispersa con el valor del nodo <b>x</b>. De lo
	 *                   contrario no se reemplaza.
	 */
	public void conectarPorColumnas(NodoDoble nodoX, boolean reemplazar) {
		Tripleta tripletaT = (Tripleta) nodoX.getD();
		int f = tripletaT.getFila();
		int c = tripletaT.getColumna();
		Object v = tripletaT.getValor();

		NodoDoble primerNodo = getPrimerNodo();

		NodoDoble antNodoQ = primerNodo;
		NodoDoble nodoQ = antNodoQ.getLi(); // liga izquierda para la lista de las columnas
		tripletaT = (Tripleta) nodoQ.getD();

		// se busca la primera columna menor o igual a 'c'
		while (nodoQ != primerNodo && tripletaT.getColumna() < c) {
			antNodoQ = nodoQ;
			nodoQ = nodoQ.getLi();
			tripletaT = (Tripleta) nodoQ.getD();
		}

		// se busca la primera fila menor o igual a 'f'
		while (nodoQ != primerNodo && tripletaT.getFila() < f) {
			antNodoQ = nodoQ;
			nodoQ = nodoQ.getLi();
			tripletaT = (Tripleta) nodoQ.getD();
		}

		// si el nodo no existe en la matriz
		if (nodoQ == primerNodo || f < tripletaT.getFila() || c < tripletaT.getColumna()) {

			// se conecta el nodo entre 'antNodoQ' y 'nodoQ'
			nodoX.setLi(nodoQ);
			antNodoQ.setLi(nodoX);
		} else { // ya existe

			// si 'reemplazar' es 'true'
			if (reemplazar) {
				tripletaT.setValor(v);
			} // else
			return;
		}
	}

	/**
	 * Retorna el {@link NodoDoble} que representa la matriz dispersa.
	 * 
	 * @return {@link NodoDoble} {@link #mat}
	 */
	public NodoDoble getNodoCabeza() {
		return this.mat;
	}

	/**
	 * Retorna el {@link NodoDoble} cabeza con el cual se puede recorrer la lista de
	 * las filas y la lista de las columnas de la matriz dispersa.
	 * <p>
	 * Este {@link NodoDoble} está contenido en la liga derecha del
	 * {@link NodoDoble} cabeza {@link #mat}.
	 * 
	 * @return {@link NodoDoble} cabeza que representa la lista de las filas y la
	 *         lista de las columnas de la matriz dispersa.
	 */
	public NodoDoble getPrimerNodo() {
		return this.mat.getLd();
	}

	/**
	 * Retorna el elemento nulo o vacío que tiene definido esta matriz dispersa.
	 * 
	 * @return {@link #nulo}.
	 */
	public Object getNulo() {
		return this.nulo;
	}

	/**
	 * Retorna el número de filas que tiene esta matriz dispersa, el cual está en el
	 * campo <b>fila</b> de la {@link Tripleta} contenida en el {@link NodoDoble}
	 * cabeza {@link #mat}.
	 * 
	 * @return Número de filas de la matriz dispersa.
	 */
	public int getNumFilas() {
		Tripleta t = (Tripleta) this.mat.getD();
		int numFilas = t.getFila();
		return numFilas;
	}

	/**
	 * Retorna el número de columnas que tiene esta matriz dispersa, el cual está en
	 * el campo <b>columna</b> de la {@link Tripleta} contenida en el
	 * {@link NodoDoble} cabeza {@link #mat}.
	 * 
	 * @return Número de columnas de la matriz dispersa.
	 */
	public int getNumColumnas() {
		Tripleta t = (Tripleta) this.mat.getD();
		int numColumnas = t.getColumna();
		return numColumnas;
	}

	/**
	 * Busca en la matriz dispersa el dato correspondiente a los indíces (<b>i</b>,
	 * <b>j</b>), si alguno de los indices está fuera del rango de la matriz
	 * dispersa, ocurre una excepción.
	 * 
	 * @param i -> Indíce correspondiente a la fila.
	 * @param j -> Indíce correcpondiente a la columna.
	 * @return El dato en la posición (<b>i</b>, <b>j</b>). Si no se encuentra
	 *         retorna {@link #nulo}.
	 */
	public Object get(int i, int j) {

		int m = getNumFilas();
		int n = getNumColumnas();

		// se comprueban que los indíces estén dentro del rango del tamaño de la matriz
		if ((i < 0 || i >= m) || (j < 0 || j >= m)) {
			String error = "the index 'i' must be 0 <= i < %d, \n and 'j' must be 0 <= j < %d";
			throw new IndexOutOfBoundsException(String.format(error, m, n));
		}

		NodoDoble primerNodo = getPrimerNodo();
		NodoDoble nodoP = primerNodo.getLd();
		Tripleta tripletaT = (Tripleta) nodoP.getD();

		// se busca la fila 'i'
		while (nodoP != primerNodo && tripletaT.getFila() < i) {
			nodoP = nodoP.getLd();
			tripletaT = (Tripleta) nodoP.getD();
		}

		// se busca la columna 'j'
		while (nodoP != primerNodo && tripletaT.getColumna() < j) {
			nodoP = nodoP.getLd();
			tripletaT = (Tripleta) nodoP.getD();
		}

		// si se recorrió toda la lista o no se encontró un nodo con fila 'i' o columna
		// 'j'
		if (nodoP == primerNodo || i != tripletaT.getFila() || j != tripletaT.getColumna()) {
			return this.nulo;
		} else { // 'i' y 'j' se encontraron
			return tripletaT.getValor();
		}
	}

	/**
	 * Crea un nodo con los datos entregados y lo inserta en donde corresponde. Si
	 * ya existe un nodo con la misma <b>fila</b> y <b>columna</b>, se reemplaza el
	 * valor de este. Si alguno de los indices está fuera del rango de la matriz,
	 * ocurre una excepción.
	 * 
	 * @param i -> Fila en donde se asigna el valor <b>v</b>.
	 * @param j -> Columna en donde se asigna el valor <b>v</b>.
	 * @param v -> Valor que se asigna en la posición (<b>i</b>, <b>j</b>).
	 */
	public void set(int i, int j, Object v) {
		int m = getNumFilas();
		int n = getNumColumnas();

		// se comprueban que los indíces estén dentro del rango del tamaño de la matriz
		if ((i < 0 || i >= m) || (j < 0 || j >= m)) {
			String error = "the index 'i' must be 0 <= i < %d, \n and 'j' must be 0 <= j < %d";
			throw new IndexOutOfBoundsException(String.format(error, m, n));
		}

		Tripleta tripletaT = new Tripleta(i, j, v);
		NodoDoble nodoX = new NodoDoble(tripletaT);
		conectar(nodoX, true);
	}

	/**
	 * Muestra los elementos diferentes de {@link #nulo} de la matriz dispersa,
	 * ordenado ascendentemente por filas o columnas, según el valor del parámetro
	 * <b>mode</b>.
	 * 
	 * @param mode -> Define el orden en el que se muestran los elementos
	 *             diferenetes de <b>0</b>. <br>
	 *             Si es <b>0</b>, se muestra ordenado por filas, si es <b>1</b>,
	 *             ordenado por columnas.<br>
	 *             Si es diferente de <b>0</b> o <b>1</b> provocará un
	 *             {@link AssertionError}.
	 */
	public void show(int mode) {

		assert (mode == 0 || mode == 1) : "\\'mode\\' must be 0 or 1";

		NodoDoble primerNodo = getPrimerNodo();

		NodoDoble nodoP = primerNodo; // NodoDoble para recorrer la matriz
		Tripleta tripletaT;
		int f, c;
		Object v;

		System.out.println(String.format("[%d, %d]", getNumFilas(), getNumColumnas()));

		if (mode == 0) {
			nodoP = nodoP.getLd();
		} else {
			nodoP = nodoP.getLi();
		}

		while (nodoP != primerNodo) {

			tripletaT = (Tripleta) nodoP.getD();
			f = tripletaT.getFila();
			c = tripletaT.getColumna();
			v = tripletaT.getValor();
			System.out.println(String.format("(%d, %d, %s)", f, c, v));

			// se pregunta si es por filas (0) o por columnas (1)
			if (mode == 0) {
				nodoP = nodoP.getLd(); // liga derecha para la lista de las filas
			} else {
				nodoP = nodoP.getLi(); // liga izquierda para la lista de las columnas
			}
		}
	}

	public void showLinked(int mode) {

		assert (mode == 0 || mode == 1) : "\\'mode\\' must be 0 or 1";

		NodoDoble primerNodo = getPrimerNodo();

		NodoDoble nodoP = primerNodo; // NodoDoble para recorrer la matriz
		Tripleta tripletaT;
		int f, c;
		Object v;

		System.out.println(String.format("[%d, %d]", getNumFilas(), getNumColumnas()));

		if (mode == 0) {
			nodoP = nodoP.getLd();
		} else {
			nodoP = nodoP.getLi();
		}

		while (nodoP != primerNodo) {

			tripletaT = (Tripleta) nodoP.getD();
			f = tripletaT.getFila();
			c = tripletaT.getColumna();
			v = tripletaT.getValor();
			System.out.print(String.format("(%d, %d, %s) --> ", f, c, v));

			// se pregunta si es por filas (0) o por columnas (1)
			if (mode == 0) {
				nodoP = nodoP.getLd(); // liga derecha para la lista de las filas
			} else {
				nodoP = nodoP.getLi(); // liga izquierda para la lista de las columnas
			}
		}
	}
	
	public void showAsArrayOfArrays() {
		// TODO terminar
	}

	// pruebas
	public static void main(String[] args) {
		Object m[][] = { //
				{ 0, 3, 1, 0 }, //
				{ 6, 0, 0, 8 }, //
				{ 2, 4, 5, 0 }, //
		};
		MatrizForma2 matriz = new MatrizForma2(m, 0);
		matriz.show(0);
		System.out.println(matriz.get(2, 0));
		matriz.set(2, 0, "hola");
		System.out.println(matriz.get(2, 0));
		matriz.showLinked(0);
	}

}
