package matricesDispersas;

import java.util.function.BiFunction;

/**
 * Matriz Dispersa representada en un vector de tripletas.
 * 
 * @author sneyd
 * @see Tripleta
 */
public class MatrizEnTripletas {

	// TODO incluir el elemento nulo

	/**
	 * Vector de la Matriz Dispersa
	 * <p>
	 * <b>Nota:</b> La primera {@link Tripleta} contiene el tamaño de la matriz
	 * dispersa, y el número de elementos diferentes de cero que hay en el vector
	 * desde la posición 1.
	 */
	private Tripleta V[];

	/**
	 * Objeto que representa el elemento nulo o vacío de la matriz dispersa.
	 * 
	 * <b>Nota:</b> Este campo se inicializa en los constructores.
	 */
	private final Object nulo;

	/**
	 * Constructor. Inicializa el objeto instanciado a partir de la {@link Tripleta}
	 * entregada como parámetro.
	 * <p>
	 * El vector {@link #V} se inicializa con un tamaño <b><i>m*n+2</i></b>, donde
	 * <b>m</b> es el número de filas y <b>n</b> es el número de columnas, extraídos
	 * de la {@link Tripleta} <b>t</b>. Luego esta {@link Tripleta} se guarda en la
	 * primera posición del vector.
	 * 
	 * @param tripletaT -> {@link Tripleta} que contiene el número de filas y
	 *                  columnas en los campos <b>fila</b> y <b>columna</b>
	 *                  respectivamente.
	 * @param nulo      -> Establece el objeto nulo o vacío de la matriz
	 *                  dispersa.<br>
	 *                  Ejs: <br>
	 *                  <i>1.</i> Si la matriz es de tipo {@link Boolean}, el
	 *                  parámetro <b>nulo</b> es <code>false</code>.<br>
	 *                  <i>2.</i> Si es de tipo {@link Integer}, <b>nulo</b> es
	 *                  <b>0</b>.<br>
	 *                  <i>3.</i> Si es tipo {@link String}, <b>nulo</b> es
	 *                  <b>""</b>.
	 */
	public MatrizEnTripletas(Tripleta tripletaT, Object nulo) {
		int m = tripletaT.getFila();
		int n = tripletaT.getColumna();
		V = new Tripleta[m * n + 2];
		V[0] = tripletaT;
		this.nulo = nulo;
	}

	/**
	 * Contructor a partir de una matriz. Construye esta matriz dispersa a partir de
	 * la matriz entregada como parámetro.
	 * <p>
	 * El vector {@link #V} se inicializa con el tamaño de la matriz entregada como
	 * parámetro, y se añaden los datos diferentes de {@link #nulo} de la matriz.
	 * 
	 * @param matriz -> Matriz de la cual se construye la matriz dispersa.
	 * @param nulo   -> Establece el objeto nulo o vacío de la matriz dispersa.<br>
	 *               Ejs: <br>
	 *               <i>1.</i> Si la matriz es de tipo {@link Boolean}, el parámetro
	 *               <b>nulo</b> es <code>false</code>.<br>
	 *               <i>2.</i> Si es de tipo {@link Integer}, <b>nulo</b> es
	 *               <b>0</b>.<br>
	 *               <i>3.</i> Si es tipo {@link String}, <b>nulo</b> es <b>""</b>.
	 */
	public MatrizEnTripletas(Object[][] matriz, Object nulo) {

		// tamaño de la matriz que se asigna a la primera tripleta del vector
		int m = matriz.length;
		int n = matriz[0].length;
		V = new Tripleta[m * n + 2];
		Tripleta tripletaT = new Tripleta(m, n, 0); // incialmente tiene 0 elementos
		V[0] = tripletaT;
		this.nulo = nulo;

		// se añaden los elementos diferentes de cero de la matriz

		int k = 1; // se inicia en 1 ya que en la posición 0 se encuentra la tripleta que describe
					// la matriz dispersa
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matriz[i][j] != this.nulo) {
					tripletaT = new Tripleta(i, j, matriz[i][j]);
					setTripleta(tripletaT, k);
					k++; // se aumenta por cada elemento diferente de 0 que se añade
				}
			}
		}
		V[0].setValor(k - 1); // finalmente se actualiza el valor de la primera tripleta con el número de
								// elementos diferentes de cero que hay en el vector.
	}

	/**
	 * Inserta la {@link Tripleta} <b>t</b> en orden ascendente respecto a la fila y
	 * columna.
	 * 
	 * @param tripletaT  -> {@link Tripleta} a insertar.
	 * @param reemplazar -> Valor que establece si se reemplaza o se rechaza la
	 *                   {@link Tripleta} <b>t</b> cuando ya existe una
	 *                   {@link Tripleta} en el vector {@link #V} con la misma fila
	 *                   y columna que <b>t</b>.<br>
	 *                   Si es <code>true</code> se reemplaza, de lo contrario se
	 *                   rechaza.
	 * @return <code>true</code> si existía una {@link Tripleta} con la misma fila y
	 *         columna que <b>t</b> y por tanto se reemplazó o rechazó según el
	 *         parámetro <b>reemplazar</b>. <code>false</code> de lo contrario.
	 */
	public boolean insertar(Tripleta tripletaT, boolean reemplazar) {
		int f = tripletaT.getFila();
		int c = tripletaT.getColumna();
		int numElementos = getNumElementos(); // numero de elementos diferentes de cero
		int i = 1;

		// se busca la primera fila mayor a f desde 1 hasta p
		while (i <= numElementos && f < V[i].getFila()) {
			i++;
		}

		// se busca la primera columna mayor a c si la fila encontrada es igual a f
		while (i <= numElementos && f == V[i].getFila() && c < V[i].getColumna()) {
			i++;
		}

		// si las dos filas y las dos columnas son iguales, se utiliza el parámetro
		// reemplazar
		if (f == V[i].getFila() && c == V[i].getColumna()) {
			if (reemplazar) {
				V[i] = tripletaT;
			} // else -> rechazada
			return true;
		} // else -> no esta duplicada

		// se mueven los datos desde p hasta i+1 una posición hacia adelante
		int j = numElementos;
		while (j >= i) {
			V[j + 1] = V[j];
			j--;
		}
		// se inserta la tripleta
		V[i] = tripletaT;
		// se suma 1 al numero de elementos diferentes de 0
		numElementos++;
		V[0].setValor(numElementos);

		return false;
	}

	/**
	 * Asigna la tripleta <b>t</b> a la posición <b>k</b> del vector {@link #V}.
	 * 
	 * @param t -> {@link Tripleta} a asignar.
	 * @param k -> Posición en la cual se va a asignar en el vector.
	 */
	public void setTripleta(Tripleta t, int k) {
		V[k] = t;
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
	 * Retorna el número de filas de la matriz dispersa.
	 * 
	 * @return Número de filas contenido en el campo <b>fila</b> de la primera
	 *         {@link Tripleta} del vector {@link #V}.
	 */
	public int getNumFilas() {
		return V[0].getFila();
	}

	/**
	 * Retorna el número de columnas de la matriz dispersa.
	 * 
	 * @return Número de columnas contenido en el campo <b>columna</b> de la primera
	 *         {@link Tripleta} del vector {@link #V}.
	 */
	public int getNumColumnas() {
		return V[0].getColumna();
	}

	/**
	 * Retorna el número de elementos diferentes del elemento nulo de la matriz
	 * dispersa, contando desde la posición <b>1</b> del vector {@link #V}.
	 * 
	 * @return Número de columnas contenido en el campo <b>valor</b> de la primera
	 *         {@link Tripleta} del vector {@link #V}.
	 */
	public int getNumElementos() {
		return (int) this.V[0].getValor();
	}

	/**
	 * Asigna el número de elementos diferentes de {@link #nulo} que hay en el
	 * vector {@link #V} la matriz dispersa.
	 * 
	 * @param n -> Número de elementos diferentes de {@link #nulo} a asignar.
	 */
	public void setNumElementos(int n) {
		this.V[0].setValor(n);
	}

	/**
	 * Retorna la tripleta <b>i</b> del vector {@link #V}.
	 * 
	 * @param i -> Indíce de la {@link Tripleta}, el cual está en el rango <i>1 <=
	 *          <b>i</b> <= n </i>, donde <i>n</i> es el número de elementos
	 *          diferentes de {@link #nulo} del vector. <br>
	 *          Si <b>i</b> está fuera del rango, ocurrirá una excepción.
	 * @return {@link Tripleta} en la posición <b>i</b>.
	 */
	public Tripleta getTripleta(int i) {
		assert (i >= 1 && i <= getNumElementos()) : "el indíce i debe estar dentro del rango [1, n]";

		return this.V[i];
	}

	/**
	 * Suma dos matrices dispersas, recorriendo sus vectores y añadiendo una nueva
	 * {@link Tripleta} por cada suma diferente de {@link #nulo}. <br>
	 * Además, para que la suma se dé, se tienen que cumplir ciertas condiciones, de
	 * lo contrario ocurrirá una excepción.
	 * <p>
	 * Las condiciones son: <br>
	 * - Las dimensiones de las dos matrices deben ser iguales. <br>
	 * - Los datos de cada posición de las matrices deben ser del mismo tipo de
	 * dato. <br>
	 * - El elemento <b>nulo</b> de las dos matrices debe ser el mismo.
	 * <p>
	 * <b>Nota:</b> El tipo de dato debe ser inmutable, de lo contrario es posible
	 * que los objetos de las matrices a sumar queden duplicados en la matriz
	 * resultante, y por tanto puedan ser cambiados desde 2 o más matrices.
	 * <p>
	 * Veáse {@link Tripleta#copiar()}.
	 * 
	 * @param matrizB -> Matriz dispersa a sumar
	 * @param sumador -> Función de tipo {@link BiFunction} para sumar los elementos
	 *                de las matrices dispersas. Este método se utiliza para sumar
	 *                diferentes tipos de datos según sea el caso, debe ser de la
	 *                forma <br>
	 *                <i><b>({@link Object} a, {@link Object} b) ->
	 *                {@link Object}</b></i>.
	 * @return Una nueva {@link MatrizForma1} la cual es la suma de esta matriz
	 *         dispersa con la matriz dispersa <b>matrizB</b>.
	 */
	public MatrizEnTripletas sumar(MatrizEnTripletas matrizB, BiFunction<Object, Object, Object> sumador) {

		MatrizEnTripletas matrizA = this;

		// se comprueba que las dimensiones sean iguales
		assert (matrizA.getNumFilas() == matrizB.getNumFilas() && matrizA.getNumColumnas() == matrizB.getNumColumnas())
				: "las dimensiones de las matrices deben ser las mismas";

		// se comprueba que los elementos nulos sean iguales
		assert (matrizA.getNulo() == matrizB.getNulo()) : "los elementos 'nulo' de las matrices deben ser los mismos";

		Tripleta tripletaA, tripletaB, tripletaC;
		
		// se crea la matriz a retornar
		tripletaC = new Tripleta(matrizA.getNumFilas(), matrizA.getNumColumnas(), 0);

		MatrizEnTripletas matrizC = new MatrizEnTripletas(tripletaC, matrizA.getNulo());

		int i, j, k, fA, fB, cA, cB;
		Object suma;

		i = 1; // para avanzar en el vector de A
		j = 1; // para avanzar en el vector de B
		k = 0; // para avanzar y añadir en el vector de C

		while (i <= matrizA.getNumElementos() && j <= matrizB.getNumElementos()) {
			tripletaA = matrizA.getTripleta(i);
			tripletaB = matrizB.getTripleta(j);
			fA = tripletaA.getFila();
			fB = tripletaB.getFila();

			// si hay elementos por sumar se aumenta k;
			k++;

			// se comparan las filas de las dos tripletas
			if (fA < fB) {

				// se asigna la tripleta con menor fila a la posición 'k' de la matriz C
				matrizC.setTripleta(tripletaA.copiar(), k);
				i++; // se avanza en la matriz A

			} else if (fB < fA) {

				// se asigna la tripleta con menor fila a la posición 'k' de la matriz C
				matrizC.setTripleta(tripletaB.copiar(), k);
				j++; // se avanza en la matriz B

			} else { // las filas son iguales

				cA = tripletaA.getColumna();
				cB = tripletaB.getColumna();

				// se comparan las columnas de las dos tripletas
				if (cA < cB) {

					// se asigna la tripleta con menor columna a la posición 'k' de la matriz C
					matrizC.setTripleta(tripletaA.copiar(), k);
					i++; // se avanza en la matriz A

				} else if (cB < cA) {

					// se asigna la tripleta con menor columna a la posición 'k' de la matriz C
					matrizC.setTripleta(tripletaB.copiar(), k);
					j++; // se avanza en la matriz B

				} else { // las columnas son iguales

					// si los valores son de diferente tipo salta una excepción
					if (tripletaA.getValor().getClass() != tripletaB.getValor().getClass()) {
						String error = "la suma no se puede efectuar, los tipos de dato en la posición (%d, %d) son diferentes";
						throw new AssertionError(String.format(error, tripletaA.getFila(), tripletaA.getColumna()));
					}

					suma = sumador.apply(tripletaA.getValor(), tripletaB.getValor());

					// si la suma es diferente del elemento nulo o vacío, se añade a la matriz C
					if (suma != matrizA.nulo) {
						tripletaC = new Tripleta(fA, cA, suma);
						matrizC.setTripleta(tripletaC, k);
					} else { // si es nulo

						// se invierte el aumento de k ya que no se va a sumar el elemento nulo
						k--;
					}

					// se avanza en los dos vectores de las matrices
					i++;
					j++;
				}

			}
		}

		// si quedan tripletas en el vector de A
		while (i <= matrizA.getNumElementos()) {
			tripletaA = matrizA.getTripleta(i);
			k++;
			matrizC.setTripleta(tripletaA.copiar(), k);
			i++;
		}

		// si quedan tripletas en el vector de B
		while (j <= matrizB.getNumElementos()) {
			tripletaB = matrizB.getTripleta(j);
			k++;
			matrizC.setTripleta(tripletaB.copiar(), k);
			j++;
		}

		// se asigna el número de elementos diferentes de nulo, definido por 'k'
		matrizC.setNumElementos(k);
		return matrizC;
	}

	/**
	 * Crea una nueva matriz dispersa, la cual es la traspuesta a esta matriz
	 * dispersa, utilizando el método con orden de magnitud <b>O(m*n)</b>.
	 * 
	 * @return {@link MatrizEnTripletas}, la cual es la traspuesta de esta matriz
	 *         dispersa.
	 */
	public MatrizEnTripletas traspuesta() {
		int S[] = new int[getNumColumnas()]; // contiene el número de elementos diferentes de 'nulo' de cada columna
		int T[] = new int[getNumColumnas()]; // contiene la posición a la cual hay que trasladar las tripletas de la
												// matriz original a la traspuesta
		int numElementos = getNumElementos();

		// se construye la matriz traspuesta invirtiendo sus dimensiones
		Tripleta tripletaT = new Tripleta(getNumColumnas(), getNumFilas(), numElementos);
		MatrizEnTripletas matrizT = new MatrizEnTripletas(tripletaT, getNulo());

		int f, c, k;
		Object v;

		// se cuentan los elementos de cada columna
		for (int i = 1; i <= numElementos; i++) {
			c = V[i].getColumna();
			S[c]++;
		}

		T[0] = 1; // posición en la cual se empiezan a insertar tripletas en la traspuesta

		// se construye el vector 'T'
		for (int i = 1; i < getNumColumnas(); i++) {
			T[i] = T[i - 1] + S[i - 1];
		}

		// Se añaden las tripletas a la traspuesta usando el vector 'T'
		for (int i = 1; i <= numElementos; i++) {
			f = V[i].getFila();
			c = V[i].getColumna();
			v = V[i].getValor();
			tripletaT = new Tripleta(c, f, v); // aquí se efectúa la trasposición intercambiando 'f' y 'c'

			// se traslada a la posición correspondiente
			k = T[c];
			matrizT.setTripleta(tripletaT, k);
			T[c]++;
		}
		return matrizT;
	}

	/**
	 * Muestra en la consola los elementos diferentes de {@link #nulo} del vector
	 * {@link #V}.
	 */
	public void show() {
		int f = V[0].getFila();
		int c = V[0].getColumna();
		int n = (int) V[0].getValor();

		System.out.println(String.format("[%d, %d, %d]", f, c, n)); // primera tripleta del vector

		for (int i = 1; i < n + 1; i++) { // tripletas desde la posición 1 hasta n + 1
			f = V[i].getFila();
			c = V[i].getColumna();
			Object v = V[i].getValor();
			System.out.println(String.format("(%d, %d, %s)", f, c, v));
		}
	}

	public void showAsArrayOfArrays() {
		// TODO terminar
	}

	// pruebas
	public static void main(String[] args) {
		Object matriz[][] = { //
				{ 0, 1, 0, 0, 0, 8 }, //
				{ 4, 5, 0, 7, 3, 0 }, //
				{ 0, 2, 0, 0, 0, 0 }, //
				{ 6, 0, 0, 8, 0, 0 } //
		};
		MatrizEnTripletas m = new MatrizEnTripletas(matriz, 0);
		m.showAsArrayOfArrays();
		System.out.println(" traspuesta:");
		m.traspuesta().showAsArrayOfArrays();
	}

}
