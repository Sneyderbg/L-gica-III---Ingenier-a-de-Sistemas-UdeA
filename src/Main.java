import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {

		String term = "-.2x^2";

		Pattern coefPat, expPat;
		Matcher coefMatc, expMatc;

		coefPat = Pattern.compile("(\\-|\\+?)([0-9]*)(.[0-9])*%c?".formatted('x'));
		expPat = Pattern.compile("%c((\\^[0-9]+)?)".formatted('x'));

		coefMatc = coefPat.matcher(term);
		expMatc = expPat.matcher(term);

		coefMatc.find();

		System.out.println(coefMatc.group());
		System.out.println(Double.parseDouble("2"));

	}

}
