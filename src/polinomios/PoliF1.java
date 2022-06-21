package polinomios;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Polinomios en vector, términos ordenados descendentemente por exponente
 */
public class PoliF1 {

    private int[] V;
    private char variable;

    /**
     * Constructor for the Polinomios
     * 
     * @param grade grado del polinomio.
     */
    public PoliF1(int grade) {

        assert (grade >= -1) : "grade must be >=-1";

        V = new int[grade + 2];
        V[0] = grade;
        variable = 'x';

    }

    public PoliF1(int grade, char var) {

        assert (grade >= -1) : "grade must be >=-1";

        V = new int[grade + 2];
        V[0] = grade;
        variable = var;

    }

    /**
     * Contructor. Construye un polinomio a partir del String dado.
     * <p>
     * Si el String que representa el polinomio contiene un terminocon un patron
     * inválido, se arrojará una excepción.
     * <p>
     * Si el String que representa el polinomio contiene un termino cuyo exponente
     * es mayor al grado del polinomio, se
     * omitirá.
     * 
     * @param grade Grado del polinomio.
     * @param Poli  String que representa el polinomio.
     * @param var   Variable del polinomio.
     */
    public PoliF1(int grade, String Poli, char var) {

        assert (grade >= -1) : "grade must be >=-1";

        V = new int[grade + 2];
        V[0] = grade;
        variable = var;

        ArrayList<String> terms = new ArrayList<String>();

        int i, j;
        i = 0;
        j = 0;
        while (j < Poli.length()) {

            if ((Poli.charAt(j) != '+' && Poli.charAt(j) != '-') || i == j) {
                j++;
                continue;
            }

            terms.add(Poli.substring(i, j));
            i = j;
            j++;

        }

        terms.add(Poli.substring(i, j));

        int coefAndExp[];

        for (String term : terms) {

            coefAndExp = StringToCoefAndExp(term);

            if (coefAndExp[1] > grade) {
                continue;
            }

            V[getPos(coefAndExp[1])] += coefAndExp[0];

        }

        normalize();

    }

    public int getPos(int exp) {
        return getGrade() - exp + 1;
    }

    public int getExp(int pos) {
        return getGrade() - pos + 1;
    }

    public void setCoef(int coef, int idx) {

        assert (idx != 0) : "idx cannot be zero!";

        V[idx] = coef;

    }

    public int getCoef(int idx) {

        assert (idx != 0) : "idx cannot be zero!";

        return V[idx];

    }

    public int getGrade() {

        return V[0];

    }

    public char getVariable() {
        return variable;
    }

    /**
     * Realiza una suma entre este polinomio y el polinomio <b>PoliB</b> dado como
     * parámetro.
     * 
     * @param PoliB Segundo sumando de la suma a realizar.
     * @return {@link PoliF1}, resultado de la suma.
     */
    public PoliF1 sum(PoliF1 PoliB) {

        assert (variable == PoliB.getVariable()) : "No quiero sumar dos polinomios con diferentes variables.";

        int gradeA, gradeB, gradeC, i, j, k, s;
        PoliF1 PoliC;

        gradeA = this.getGrade();
        gradeB = PoliB.getGrade();
        gradeC = Math.max(gradeA, gradeB);

        PoliC = new PoliF1(gradeC, variable);

        i = gradeA + 1;
        j = gradeB + 1;
        k = gradeC + 1;

        while (i > 0 && j > 0) {

            s = this.getCoef(i) + PoliB.getCoef(j);
            PoliC.setCoef(s, k);

            i--;
            j--;
            k--;

        }
        while (i > 0) {

            PoliC.setCoef(this.getCoef(i), k);
            i--;
            k--;

        }
        while (j > 0) {

            PoliC.setCoef(PoliB.getCoef(j), k);
            j--;
            k--;

        }

        PoliC.normalize();
        return PoliC;

    }

    public PoliF1 substract(PoliF1 poliB) {

        assert (variable == poliB.getVariable()) : "No se pueden restar 2 polinomios con distintas variables.";

        PoliF1 poliC;
        int gradeA, gradeB, gradeC, i, j, k, r;

        gradeA = getGrade();
        gradeB = poliB.getGrade();
        gradeC = Math.max(gradeA, gradeB);

        poliC = new PoliF1(gradeC, variable);

        i = gradeA + 1;
        j = gradeB + 1;
        k = gradeC + 1;

        while (i >= 1 && j >= 1){

            r = getCoef(i) - poliB.getCoef(j);
            poliC.setCoef(r, k);
            
            i--;
            j--;
            k--;

        }
        while (i >= 1) {
        
            poliC.setCoef(getCoef(i), k);
            
            i--;
            k--;

        }
        while (j >= 1) {
            
            poliC.setCoef(poliB.getCoef(j), k);

            j--;
            k--;
            
        }
        
        normalize();
        return poliC;

    }

    /**
     * Multiplica dos polinomios y el resultado lo retorna como {@link PoliF1}
     * 
     * @param poliB Polinomio multiplicador.
     * @return {@link PoliF1} resultado de multiplicar este polinomio con el
     *         polinomio <b>poliB</b>.
     */
    public PoliF1 multiply(PoliF1 poliB) {

        assert (variable == poliB.getVariable())
                : "No se pueden multiplicar 2 polinomios con distinta variable. (de momento)";

        PoliF1 poliC;
        int gradeA, gradeB, gradeC, i, j, k, coefA, coefB, expA, expB, coefC, expC;

        gradeA = getGrade();
        gradeB = poliB.getGrade();
        gradeC = gradeA * gradeB;

        poliC = new PoliF1(gradeC, variable);

        for (i = 1; i <= gradeA + 1; i++) {

            coefA = getCoef(i);
            expA = getExp(i);

            for (j = 0; j < gradeB + 1; j++) {

                coefB = poliB.getCoef(j);
                expB = poliB.getExp(j);

                expC = expA + expB;

                k = poliC.getPos(expC);
                coefC = poliC.getCoef(k) + coefA * coefB;

                poliC.setCoef(coefC, k);

            }

        }

        return poliC;

    }

    /**
     * Normaliza el grado del polinomio según los terminos iguales a cero
     * consecutivos que tiene el polinomio en sus exponentes mayores, posteriormente
     * actualiza el grado del polinomio si es necesario.
     */
    protected void normalize() {

        int grade, i, j;

        grade = this.getGrade();

        i = 1;
        while (i <= grade + 1 && this.getCoef(i) == 0) {
            i++;
        }

        if (i == 1) {
            return;
        }

        V[0] = grade - i + 1;

        j = 1;
        while (i <= grade + 1) {

            this.setCoef(this.getCoef(i), j);
            i++;
            j++;

        }

    }

    /**
     * Convierte todo este polinomio a un String usando el método
     * {@link #termToString(int, int)} como apoyo.
     * 
     * @return String que representa este polinomio.
     */
    @Override
    public String toString() {

        String output, term;
        output = "";
        int grade, coef, exp, i;
        grade = getGrade();

        for (i = 1; i <= grade + 1; i++) {

            coef = getCoef(i);
            exp = getExp(i);

            if (coef == 0) {
                continue;
            }

            term = termToString(coef, exp);

            if (i == 1) {
                output = output + term.replace("+ ", "");
            } else {
                output = output + term;
            }

            output = (i == grade + 1) ? output : output + " ";

        }

        return output;

    }

    /**
     * Convierte el término definido por los parámetros dados a un String de la
     * forma
     * <b><i>(+/-)cx^e</i></b> donde c es el coeficiente y e el exponente.
     * 
     * @param coef Coeficiente del término a convertir.
     * @param exp  Exponente del término a convertir.
     * @return String de la forma <b><i>(+/-)cx^e</i></b.
     */
    public String termToString(int coef, int exp) {

        String term = "%1$s%2$d%3$s%4$s"; // 1 = signo, 2 = coef, 3 = variable, 4 = exp

        if (coef == 0) {

            return "";

        }
        if (exp == 0) {

            term = term.replace("%3$s%4$s", "");

        } else if (exp == 1) {

            term = term.replace("%4$s", "");

        }
        if ((coef == 1 || coef == -1) && exp > 0) {

            term = term.replace("%2$d", "");

        }

        if (coef > 0) {

            term = term.formatted("+ ", Math.abs(coef), variable, "^" + exp);

        } else {

            term = term.formatted("- ", Math.abs(coef), variable, "^" + exp);

        }

        return term;

    }

    /**
     * Toma un termino de tipo String y lo convierte en un par
     * coeficiente/exponente. El termino debe coincidir con este patron:
     * <p>
     * <b>(+/-)cx^e</b>
     * <br>
     * </br>
     * donde c es el coeficiente (un entero) y e el exponente(un entero no
     * negativo).
     * 
     * @param term El string a convertir.
     * @return Vector de 2 elementos, coeficiente y exponente.
     */
    public int[] StringToCoefAndExp(String term) {

        String coefAux, expAux;
        int coefAndExp[] = new int[2];
        term = term.replaceAll(" ", "");

        if (term.length() == 0) {
            return null;
        }

        Pattern coefPat, expPat;
        Matcher coefMatc, expMatc;

        coefPat = Pattern.compile("(\\-|\\+?)[0-9]*%c?".formatted(this.variable));
        expPat = Pattern.compile("%c((\\^[0-9]+)?)".formatted(this.variable));

        coefMatc = coefPat.matcher(term);
        expMatc = expPat.matcher(term);

        if (coefMatc.find()) {

            coefAux = coefMatc.group().replaceAll("[^\\d\\-]", "");
            if (Pattern.matches("\\-?%c?".formatted(this.variable), coefAux)) {

                coefAndExp[0] = 1;

            } else {

                try {

                    coefAndExp[0] = Integer.parseInt(coefAux);

                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }

            }

        } else {

            throw new IllegalArgumentException("Misformed term expression: " + term);

        }

        if (expMatc.find()) {

            expAux = expMatc.group().replaceAll("[^\\d\\-]", "");
            if (expAux.equalsIgnoreCase("")) {

                coefAndExp[1] = 1;

            } else {

                try {

                    coefAndExp[1] = Integer.parseInt(expAux);

                } catch (NumberFormatException e) {

                    e.printStackTrace();

                }

            }

        } else {

            coefAndExp[1] = 0;

        }

        return coefAndExp;

    }

    public void show() {
        System.out.println(toString());
    }

    public static void main(String[] args) {

        String polA = "7x^4 + 4x^2 + 7x + 2";
        String polB = "6x^3 + 8x +3";

        PoliF1 A = new PoliF1(4, polA, 'x');
        PoliF1 B = new PoliF1(3, polB, 'x');

        A.show();
        B.show();

        System.out.println();

        PoliF1 C = A.substract(B);

        C.show();

    }

}