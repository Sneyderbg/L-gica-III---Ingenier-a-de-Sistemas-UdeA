import javax.swing.JFrame;
import javax.swing.JTextArea;

import arboles.ArbolLg;
import arboles.Arboles;

public class Main extends JFrame{

	public Main() {

		super("test");

		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextArea ta = new JTextArea();

		this.add(ta);
		
		try {

			ArbolLg A = Arboles.consArbolLg("a(b(c, d))");
			ta.setText(A.lgRepr(2));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {

		Main m = new Main();
		m.setVisible(true);

	}

}
