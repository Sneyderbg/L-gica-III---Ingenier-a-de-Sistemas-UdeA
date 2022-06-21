package polinomios;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PoliF2 {

    private ArrayList<Object> V;

    private char variable;

    public PoliF2(int numElementosNoCero, char variable) {

        V = new ArrayList<Object>();
        V.add(numElementosNoCero);
        this.variable = variable;

    }

    public PoliF2(int numElementosNoCero, String poli, char variable) {

        V = new ArrayList<Object>();
        V.add(numElementosNoCero);
        this.variable = variable;
        Termino t;

        ArrayList<String> terms = new ArrayList<String>();

        int i, j;
        i = 0;
        j = 0;
        while (j < poli.length()) {

            if ((poli.charAt(j) != '+' && poli.charAt(j) != '-') || i == j) {
                j++;
                continue;
            }

            terms.add(poli.substring(i, j));
            i = j;
            j++;

        }

        terms.add(poli.substring(i, j));

        assert (terms.size() == numElementosNoCero)
                : "El número de elementos diferentes de cero no coincide con los terminos dados en el String.";

        for (String term : terms) {

            t = stringToTerm(term);
            V.add(t);

        }

    }

    public int getNumElementosNoCero() {
        return (int) V.get(0);
    }

    public void setNumElementosNoCero(int n) {
        V.set(0, n);
    }

    public Termino getTerm(int idx) {
        return (Termino) V.get(idx);
    }

    public void setTerm(Termino term, int idx) {

        assert (idx <= V.size()) : "Debe añadir terminos en las posiciones anteriores a 'idx'.";

        if (idx == V.size()) {
            V.add(term);
            return;
        }

        V.set(idx, term);

    }

    public int getGrade() {

        if (V.size() == 1) {
            return 0;
        }

        Termino t = (Termino) V.get(1);

        return t.getExp();

    }

    public char getVariable() {
        return variable;
    }

    public void insertTerm(Termino term, boolean reemplazar) {

        // int i = 1;

        // while (i < V.size() && term.getExp() < ((Termino) V.get(i)).getExp()) {
        // i++;
        // }

        // if (i == V.size()) {
        // V.add(term);
        // return;
        // }

        // if (term.getExp() == ((Termino) V.get(i)).getExp()) {

        // if (reemplazar) {

        // V.set(i, term);
        // return;

        // } else {
        // return;
        // }

        // }

        // V.add(i, term);

    }

    /**
     * Suma este polinomio con el polinomio dado como parámetro y retorna el
     * resultado como un polinomio de la clase {@link PoliF2}.
     * 
     * @param poliB {PoliF2} sumando.
     * @return {@link PoliF2} resultado de la suma.
     */
    public PoliF2 sum(PoliF2 poliB) {

        assert (getVariable() == poliB.getVariable()) : "No se pueden sumar 2 polinomios con distintas variables.";

        PoliF2 poliC;

        int numElemNoCeroA, numElemNoCeroB, numElemNoCeroC, i, j, k;
        double s;
        Termino termA, termB, termC;

        numElemNoCeroA = getNumElementosNoCero();
        numElemNoCeroB = poliB.getNumElementosNoCero();
        numElemNoCeroC = numElemNoCeroA + numElemNoCeroB;

        poliC = new PoliF2(numElemNoCeroC, variable);

        i = 1;
        j = 1;
        k = 1;

        while (i <= numElemNoCeroA && j <= numElemNoCeroB) {

            termA = getTerm(i);
            termB = poliB.getTerm(j);

            if (termA.getExp() > termB.getExp()) {

                termC = new Termino(termA.getCoef(), termA.getExp());
                poliC.setTerm(termC, k);
                i++;

            } else if (termA.getExp() < termB.getExp()) {

                termC = new Termino(termB.getCoef(), termB.getExp());
                poliC.setTerm(termC, k);
                j++;

            } else {

                s = termA.getCoef() + termB.getCoef();
                if (s != 0) {

                    termC = new Termino(s, termA.getExp());
                    poliC.setTerm(termC, k);

                } else {
                    k--;
                }

                i++;
                j++;

            }
            k++;

        }
        while (i <= numElemNoCeroA) {

            termA = getTerm(i);
            termC = new Termino(termA.getCoef(), termA.getExp());
            poliC.setTerm(termC, k);
            i++;
            k++;

        }
        while (j <= numElemNoCeroB) {

            termB = poliB.getTerm(j);
            termC = new Termino(termB.getCoef(), termB.getExp());
            poliC.setTerm(termC, k);
            j++;
            k++;

        }

        poliC.setNumElementosNoCero(k - 1);
        return poliC;

    }

    /**
     * Reste este polinomio con el polinomio dado como parámetro y retorna el
     * resultado como un polinomio de la clase {@link PoliF2}.
     * 
     * @param poliB {PoliF2} sustraendo.
     * @return {@link PoliF2} resultado de la resta.
     */
    public PoliF2 substract(PoliF2 poliB) {

        assert (getVariable() == poliB.getVariable()) : "No se pueden restar 2 polinomios con distintas variables.";

        PoliF2 poliC;

        int numElemNoCeroA, numElemNoCeroB, numElemNoCeroC, i, j, k;
        double r;
        Termino termA, termB, termC;

        numElemNoCeroA = getNumElementosNoCero();
        numElemNoCeroB = poliB.getNumElementosNoCero();
        numElemNoCeroC = numElemNoCeroA + numElemNoCeroB;

        poliC = new PoliF2(numElemNoCeroC, variable);

        i = 1;
        j = 1;
        k = 1;

        while (i <= numElemNoCeroA && j <= numElemNoCeroB) {

            termA = getTerm(i);
            termB = poliB.getTerm(j);

            if (termA.getExp() > termB.getExp()) {

                termC = new Termino(termA.getCoef(), termA.getExp());
                poliC.setTerm(termC, k);
                i++;

            } else if (termA.getExp() < termB.getExp()) {

                termC = new Termino(-termB.getCoef(), termB.getExp());
                poliC.setTerm(termC, k);
                j++;

            } else {

                r = termA.getCoef() - termB.getCoef();
                if (r != 0) {

                    termC = new Termino(r, termA.getExp());
                    poliC.setTerm(termC, k);

                } else {
                    k--;
                }

                i++;
                j++;

            }
            k++;

        }
        while (i <= numElemNoCeroA) {

            termA = getTerm(i);
            termC = new Termino(termA.getCoef(), termA.getExp());
            poliC.setTerm(termC, k);
            i++;
            k++;

        }
        while (j <= numElemNoCeroB) {

            termB = poliB.getTerm(j);
            termC = new Termino(-termB.getCoef(), termB.getExp());
            poliC.setTerm(termC, k);
            j++;
            k++;

        }

        poliC.setNumElementosNoCero(k - 1);
        return poliC;

    }

    /**
     * Multiplica este polinomio con el polinomio dado como parámetro, usando el
     * método {@link #smult(Termino)} como apoyo, y retorna el resultado como un
     * polinomio de
     * la clase {@link PoliF2}.
     * 
     * @param poliB {@link PoliF2} multiplicador.
     * @return {@link PoliF2} resultado de la multiplicación,
     */
    public PoliF2 multiply(PoliF2 poliB) {

        assert (variable == poliB.getVariable())
                : "No se pueden multiplicar 2 polinomios con distinta variable. (de momento)";

        Termino termB = poliB.getTerm(1);

        PoliF2 poliC = smult(termB);

        for (int j = 2; j <= poliB.getNumElementosNoCero(); j++) {

            termB = poliB.getTerm(j);
            poliC = poliC.sum(smult(termB));

        }

        return poliC;

    }

    /**
     * Multiplica todo este polinomio por el término entregado como parámetro, y
     * devuelde un {@link PoliF2} como resultado.
     * 
     * @param term Multiplicador.
     * @return {@link PoliF2} resultado de la multiplicación.
     */
    public PoliF2 smult(Termino term) {

        PoliF2 poliC = new PoliF2(getNumElementosNoCero(), getVariable());

        Termino termA, termC;

        for (int i = 1; i <= getNumElementosNoCero(); i++) {

            termA = getTerm(i);
            termC = new Termino(termA.getCoef() * term.getCoef(), termA.getExp() + term.getExp());
            poliC.setTerm(termC, i);

        }

        return poliC;

    }

    @Override
    public String toString() {

        String output, term;

        output = "";

        for (int i = 1; i <= getNumElementosNoCero(); i++) {

            term = ((Termino) V.get(i)).toString();

            output = (i == 1) ? output + term.replace("+ ", "") : output + term;
            output = (i == getNumElementosNoCero()) ? output : output + " ";

        }

        return output;

    }

    /**
     * Convierte el String dado a un {@link Termino} siempre que este formado
     * correctamente. Ej: <i>-4x^5</i>
     * 
     * @param term String que describe al termino a retornar.
     * @return {@link Termino} que describía el String dado.
     */
    public Termino stringToTerm(String term) {

        String coefAux, expAux;
        Termino t;
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

        t = new Termino(coefAndExp[0], coefAndExp[1]);
        return t;

    }

    public void show() {
        System.out.println(toString());
    }

    public static void main(String[] args) {

        String polA = "7x^4 + 4x^2 + 7x + 2";
        String polB = "6x^3 + 8x +3";

        PoliF2 A = new PoliF2(4, polA, 'x');
        PoliF2 B = new PoliF2(3, polB, 'x');

        A.show();
        B.show();

        PoliF2 C = A.multiply(B);

        System.out.println();

        C.show();

    }

}

// --------------------------------------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------------------------------------------

class Termino {

    private double coef;

    private int exp;

    Termino(double coef, int exp) {
        this.coef = coef;
        this.exp = exp;
    }

    public double getCoef() {
        return coef;
    }

    public int getExp() {
        return exp;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {

        String output = "%1$s%2$s%3$s%4$s"; // 1 = signo, 2 = coef, 3 = variable, 4 = exp

        if (coef == 0) {

            return "";

        }
        if (exp == 0) {

            output = output.replace("%3$s%4$s", "");

        } else if (exp == 1) {

            output = output.replace("%4$s", "");

        }
        if ((coef == 1 || coef == -1) && exp > 0) {

            output = output.replace("%2$d", "");

        }

        if (coef > 0) {

            output = output.formatted("+ ", Math.abs(coef), 'x', "^" + exp);

        } else {

            output = output.formatted("- ", Math.abs(coef), 'x', "^" + exp);

        }

        return output;

    }

}