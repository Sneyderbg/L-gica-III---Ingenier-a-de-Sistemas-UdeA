package matricesDispersas;

public class FormulasDeDireccionamiento {

	public static void main(String[] args) {

		int mtsi[][] = { //
				{ 3, 1, 6, 2, 1, 7 }, //
				{ 1, 2, 4, 3, 6, 0 }, //
				{ 6, 5, 3, 5, 0, 0 }, //
				{ 2, 7, 6, 0, 0, 0 }, //
				{ 8, 1, 0, 0, 0, 0 }, //
				{ 3, 0, 0, 0, 0, 0 }, //
		};
		
		int mtsd[][] = { //
				{ 3, 2, 5, 7, 5, 4 }, //
				{ 0, 2, 3, 1, 6, 4 }, //
				{ 0, 0, 3, 2, 8, 1 }, //
				{ 0, 0, 0, 4, 4, 9 }, //
				{ 0, 0, 0, 0, 1, 3 }, //
				{ 0, 0, 0, 0, 0, 8 }, //
		};
		
		int mtii[][] = { //
				{ 3, 0, 0, 0, 0, 0 }, //
				{ 1, 2, 0, 0, 0, 0 }, //
				{ 6, 5, 3, 0, 0, 0 }, //
				{ 2, 7, 6, 4, 0, 0 }, //
				{ 8, 1, 2, 9, 1, 0 }, //
				{ 3, 5, 4, 2, 1, 8 }, //
		};
		
		int mtid[][] = { //
				{ 0, 0, 0, 0, 0, 7 }, //
				{ 0, 0, 0, 0, 4, 2 }, //
				{ 0, 0, 0, 8, 6, 3 }, //
				{ 0, 0, 6, 4, 5, 7 }, //
				{ 0, 1, 2, 9, 1, 3 }, //
				{ 3, 5, 4, 2, 1, 8 }, //
		};
		
		int[] v = MTIDEnVector(mtid);
		show(v);
		
		for (int i = 0; i < 6; i++) {
			for (int j = 6 - i - 1; i + j + 2 >= 6 + 1 && j < 6; j++) {
				System.out.print(String.format("%d, ", posMTID(6, i + 1, j + 1)));
			}
		}
		
	}
	
	/**
	 * Transfiere los datos una matriz triangular superior izquierda a un vector
	 * utilizando la fórmula de direccionamiento {@link #posMTSI(int, int, int)}.
	 * 
	 * @param matriz -> Matriz triangular superior izquierda.<br>
	 *               Si existen datos diferentes de <b>0</b> por fuera del triangulo
	 *               superior izquierdo, se desecharán.
	 * @return Un vector que representa este tipo de matriz.
	 */
	public static int[] MTSIEnVector(int[][] matriz) {
		// Vector usado desde la posición 1 hasta n
				int v[];
				int n = matriz.length;
			
				v = new int[(n * (n + 1) / 2) + 1]; // el '+1' es para empezar desde la posición 1
			
				for (int i = 0; i < n; i++) {
					for (int j = 0; i + j + 2 <= n + 1; j++) {
						v[posMTSI(n, i + 1, j + 1)] = matriz[i][j];
					}
				}
				return v;
	}

	/**
	 * Transfiere los datos una matriz triangular superior derecha a un vector
	 * utilizando la fórmula de direccionamiento {@link #posMTSD(int, int, int)}.
	 * 
	 * @param matriz -> Matriz triangular superior derecha.<br>
	 *               Si existen datos diferentes de <b>0</b> por fuera del triangulo
	 *               superior derecho, se desecharán.
	 * @return Un vector que representa este tipo de matriz.
	 */
	public static int[] MTSDEnVector(int[][] matriz) {
	
		// Vector usado desde la posición 1 hasta n
		int v[];
		int n = matriz.length;
	
		v = new int[(n * (n + 1) / 2) + 1]; // el '+1' es para empezar desde la posición 1
	
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				v[posMTSD(n, i + 1, j + 1)] = matriz[i][j]; // '+1' para utilizar el vector desde 1
			}
		}
	
		return v;
	}

	/**
	 * Transfiere los datos una matriz triangular inferior izquierda a un vector
	 * utilizando la fórmula de direccionamiento {@link #posMTII(int, int, int)}.
	 * 
	 * @param matriz -> Matriz triangular inferior izquierda.<br>
	 *               Si existen datos diferentes de <b>0</b> por fuera del triangulo
	 *               inferior izquierdo, se desecharán.
	 * @return Un vector que representa este tipo de matriz.
	 */
	public static int[] MTIIEnVector(int[][] matriz) {
	
		// Vector usado desde la posición 1 hasta n
		int v[];
		int n = matriz.length;
	
		v = new int[(n * (n + 1) / 2) + 1]; // el '+1' es para empezar desde la posición 1
	
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				v[posMTII(n, i + 1, j + 1)] = matriz[i][j];
			}
		}
		return v;
	}

	/**
	 * Transfiere los datos una matriz triangular inferior derecha a un vector
	 * utilizando la fórmula de direccionamiento {@link #posMTID(int, int, int)}.
	 * 
	 * @param matriz -> Matriz triangular inferior derecha.<br>
	 *               Si existen datos diferentes de <b>0</b> por fuera del triangulo
	 *               inferior derecho, se desecharán.
	 * @return Un vector que representa este tipo de matriz.
	 */
	public static int[] MTIDEnVector(int[][] matriz) {
		// Vector usado desde la posición 1 hasta n
				int v[];
				int n = matriz.length;
			
				v = new int[(n * (n + 1) / 2) + 1]; // el '+1' es para empezar desde la posición 1
			
				for (int i = 0; i < n; i++) {
					for (int j = n - i - 1; i + j + 2 >= n + 1 && j < n; j++) {
						v[posMTID(n, i + 1, j + 1)] = matriz[i][j];
					}
				}
				return v;
	}
	
	/**
	 * Fórmula de direccionamiento para una matriz triangular superior izquierda.
	 * 
	 * @param i -> Fila de la matriz.
	 * @param j -> Columna de la matriz.
	 * @param n -> Tamaño de la matriz cuadrada.
	 */
	public static int posMTSI(int n, int i, int j) {
		return (i - 1)*(2*n + 2 - i)/2 + j;
	}

	/**
	 * Fórmula de direccionamiento para una matriz triangular superior derecha.
	 * 
	 * @param i -> Fila de la matriz.
	 * @param j -> Columna de la matriz.
	 * @param n -> Tamaño de la matriz cuadrada.
	 */
	public static int posMTSD(int n, int i, int j) {
		return (i * (2 * n + 1 - i)) / 2 - n + j;
	}

	/**
	 * Fórmula de direccionamiento para una matriz triangular inferior izquierda.
	 * 
	 * @param i -> Fila de la matriz.
	 * @param j -> Columna de la matriz.
	 * @param n -> Tamaño de la matriz cuadrada.
	 */
	public static int posMTII(int n, int i, int j) {
		return ((i * (i - 1)) / 2) + j;
	}

	/**
	 * Fórmula de direccionamiento para una matriz triangular inferior derecha.
	 * 
	 * @param i -> Fila de la matriz.
	 * @param j -> Columna de la matriz.
	 * @param n -> Tamaño de la matriz cuadrada.
	 */
	public static int posMTID(int n, int i, int j) {
		return i*(i + 1)/2 - n + j;
	}
	
	/**
	 * Muestra los datos del vector desde la posición 1 hasta
	 * <code>v.length - 1</code>.
	 * 
	 * @param v -> Vector a mostrar.
	 */
	public static void show(int[] v) {
		System.out.println(v.length);
		for (int i = 1; i < v.length; i++) {
			if (i == 1) {
				System.out.print(String.format("[%d, ", v[i]));
			} else if (i == v.length - 1) {
				System.out.println(String.format("%d]", v[i]));
			} else {
				System.out.print(String.format("%d, ", v[i]));
			}
		}
	}
}
