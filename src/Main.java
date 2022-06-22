import java.text.DecimalFormat;

public class Main {

	public static void main(String[] args) {

		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(0);
		
		double d = -1.0;
		String s = "%f";
		if(d % 1 == 0){
			s = s.replace("%s", "%.0f");
		}

		System.out.println(Double.parseDouble("8,5"));

	}

}
