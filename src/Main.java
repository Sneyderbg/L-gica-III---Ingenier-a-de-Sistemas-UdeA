import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

	public static void main(String[] args) {

		Vector<Integer> a = new Vector<Integer>(0, 1);

		a.set(0, 1);
		System.out.println(a.capacity() + ", " + a);

		a.add(2);
		System.out.println(a.capacity() + ", " + a);

		a.setSize(11);
		a.set(5, 5);
		System.out.println(a.capacity() + ", " + a);

	}

	public static void change(AtomicInteger... i) {
		
	}

}
