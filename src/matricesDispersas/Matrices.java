package matricesDispersas;

public class Matrices {

    public static String matrixRepr(String name, int[][] matrix, int fieldWidth, boolean showZeros, int firstIdx) {

        StringBuilder repr = new StringBuilder(name);

        // └ ┘ ┌ ┐ ─ │ ┼ ┴ ┬ ┤ ├

        for (int j = 0; j < matrix[0].length; j++) {

            repr.append(String.format("%" + (fieldWidth + 1) + "d", j + firstIdx));

        }

        repr.append("\n");

        repr.append(" ".repeat(name.length()) + "┌");
        repr.append(("─".repeat(fieldWidth) + "┬").repeat(matrix[0].length));
        repr.replace(repr.length() - 1, repr.length(), "┐");
        repr.append("\n");

        int d;
        String s;

        for (int i = 0; i < matrix.length; i++) {

            if (i > 0) {

                repr.append(" ".repeat(name.length()) + "├");
                repr.append(("─".repeat(fieldWidth) + "┼").repeat(matrix[0].length));
                repr.replace(repr.length() - 1, repr.length(), "┤");
                repr.append("\n");

            }

            repr.append(String.format("%4d", i + firstIdx));
            repr.append("│");
            for (int j = 0; j < matrix[0].length; j++) {

                d = matrix[i][j];
                s = (d == 0 ? (showZeros ? "0" : "") : "1");

                repr.append(String.format("%" + fieldWidth + "s│", s));

            }

            repr.append("\n");

        }

        repr.append(" ".repeat(name.length()) + "└");
        repr.append(("─".repeat(fieldWidth) + "┴").repeat(matrix[0].length));
        repr.replace(repr.length() - 1, repr.length(), "┘");

        return repr.toString();
    }

}
