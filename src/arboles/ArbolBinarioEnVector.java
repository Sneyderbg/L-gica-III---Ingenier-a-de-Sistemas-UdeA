package arboles;

public class ArbolBinarioEnVector {

	/**
	 * V[0] indica la posición del último valor del árbol binario
	 */
	private Object[] V;

	public ArbolBinarioEnVector(int altura) {
		V = new Object[2 * altura];
	}

	public ArbolBinarioEnVector(Object[] v) {
		this.V = v;
	}

	public String inorden(int idx) {
		String IRD = "";
		if (idx <= (int) V[0]) {
			IRD = inorden(2 * idx) + V[idx].toString() + inorden(2 * idx + 1);
		}
		return IRD;
	}

	public String posorden(int idx) {
		String IDR = "";
		if (idx <= (int) V[0]) {
			IDR = posorden(2 * idx) + posorden(2 * idx + 1) + V[idx].toString();
		}
		return IDR;
	}

	public String preorden(int idx) {
		String RID = "";
		if (idx <= (int) V[0]) {
			RID = V[idx].toString() + preorden(2 * idx) + preorden(2 * idx + 1);
		}
		return RID;
	}

	public void crearHeap(int i, int n) {
		try {
			int j, d;
			d = (int) V[i];
			j = 2 * i;
			while (j <= n) {
				if (j < n && (int) V[j] < (int) V[j + 1]) {
					j = j + 1;
				}
				if (d <= (int) V[j]) {
					V[i] = V[j];
					V[j] = d;
				} else {
					return;
				}
				i = j;
				j = 2 * i;
			}
			V[i] = d;
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}

	public void heapSort() {
		int n, i;
		n = getSize();
		for (i = n / 2; i > 0; i--) {
			crearHeap(i, n);
		}
		print();
		System.out.println(esHeap(1));
		for (i = n - 1; i > 0; i--) {
			V[i + 1] = (int) V[i + 1] + (int) V[1];
			V[1] = (int) V[i + 1] - (int) V[1];
			V[i + 1] = (int) V[i + 1] - (int) V[1];
			crearHeap(1, i);
		}
	}

	public int getSize() {
		return (int) V[0];
	}

	public void print() {
		String output = "[";
		if ((int) V[0] == 0) {
			output += "]";
		}
		for (int i = 1; i < (int) V[0]; i++) {
			output += (int) V[i] + ",";
		}
		output += (int) V[(int) V[0]] + "]";
		System.out.println(output);
	}

	public boolean esHeap(int idx) {
		boolean izq, der;
		izq = true;
		der = true;
		if (2 * idx <= (int) V[0]) {
			if ((int) V[2 * idx] > (int) V[idx]) {
				return false;
			}
			izq = esHeap(2 * idx);
		}
		if (2 * idx + 1 <= (int) V[0]) {
			if ((int) V[2 * idx + 1] > (int) V[idx]) {
				return false;
			}
			der = esHeap(2 * idx + 1);
		}
		if (izq && der) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Object[] v = { 8, 5, 9, 4, 1, 6, 2, 8, 3 };
		ArbolBinarioEnVector A = new ArbolBinarioEnVector(v);
		A.print();
		A.heapSort();
		A.print();
		System.out.println(A.esHeap(1));
	}

}
