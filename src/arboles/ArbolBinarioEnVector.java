package arboles;

import java.util.ArrayList;

public class ArbolBinarioEnVector extends ArrayList<Object> {

    public ArbolBinarioEnVector() {

        super();

    }

    public ArbolBinarioEnVector(int[] array) {

        super();

        for (int i = 0; i < array.length; i++) {

            this.add(array[i]);

        }

    }

    public static int consArbolBinario(ArbolBinarioEnVector aB, String aBStr, int startIdx) {

        return 0; //TODO

    }

    @Override
    public Object set(int index, Object element) {

        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index >= size()) {
            for (int i = size(); i <= index; i++) {

                add(null);

            }
        }

        return super.set(index, element);

    }

    public int leftChild(int parent) {

        return 2 * parent + 1;

    }

    public int rightChild(int parent) {

        return 2 * parent + 2;

    }

    public int parent(int child) {

        return (child - 1) / 2;

    }

    public String strRepr(int parent) {

        if (parent >= size()) {

            return "";

        }

        String left, right, s;

        s = get(parent).toString();

        left = strRepr(leftChild(parent));
        right = strRepr(rightChild(parent));

        if (left.length() > 0 && right.length() > 0) {

            s = s.concat(String.format("(%s, %s)", left, right));

        } else if (left.length() > 0) {

            s = s.concat(String.format("(%s)", left));

        } else if (right.length() > 0) {

            s = s.concat(String.format("(%s)", right));

        }

        return s;

    }

    public static void main(String[] args) {

        int[] A = { 1, 2, 3, 4, 5, 6, 7 };

        ArbolBinarioEnVector aV = new ArbolBinarioEnVector(A);

        System.out.println(aV.strRepr(0));

    }

}