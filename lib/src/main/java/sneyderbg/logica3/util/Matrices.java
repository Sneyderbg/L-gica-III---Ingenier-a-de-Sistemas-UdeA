package sneyderbg.logica3.util;

public class Matrices {

    public static String matrixRepr(String name, int[][] matrix, int fieldWidth, boolean showZeros, int firstIdx) {

        StringBuilder repr = new StringBuilder(name);

        // └ ┘ ┌ ┐ ─ │ ┼ ┴ ┬ ┤ ├

        for (int j = 0; j < matrix[0].length; j++) {

            repr.append(String.format("%" + (fieldWidth + 1) + "d", j + firstIdx));

        }

        repr.append("\n");

        repr.append(" ".repeat(name.length()) + Symbols.TOP_LEFT);
        repr.append((Symbols.HORIZONTAL.repeat(fieldWidth) + Symbols.TOP_T).repeat(matrix[0].length));
        repr.replace(repr.length() - 1, repr.length(), Symbols.TOP_LEFT);
        repr.append("\n");

        int d;
        String s;

        for (int i = 0; i < matrix.length; i++) {

            if (i > 0) {

                repr.append(" ".repeat(name.length()) + Symbols.LEFT_T);
                repr.append((Symbols.HORIZONTAL.repeat(fieldWidth) + Symbols.CROSS).repeat(matrix[0].length));
                repr.replace(repr.length() - 1, repr.length(), Symbols.RIGHT_T);
                repr.append("\n");

            }

            repr.append(String.format("%" + name.length() + "d", i + firstIdx));
            repr.append(Symbols.VERTICAL);
            for (int j = 0; j < matrix[0].length; j++) {

                d = matrix[i][j];
                s = (d == 0 ? (showZeros ? "0" : "") : Integer.toString(d));

                repr.append(String.format("%" + fieldWidth + "s│", s));

            }

            repr.append("\n");

        }

        repr.append(" ".repeat(name.length()) + Symbols.BOTTOM_LEFT);
        repr.append((Symbols.HORIZONTAL.repeat(fieldWidth) + Symbols.BOTTOM_T).repeat(matrix[0].length));
        repr.replace(repr.length() - 1, repr.length(), Symbols.BOTTOM_RIGHT);

        return repr.toString();
    }

}
