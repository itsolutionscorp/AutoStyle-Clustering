import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestExceptions {

	public static void main (String [ ] args) {
        System.out.println("Desired output");
        try {
			int[] points = null;
			points[0] = 2;
            System.out.println("Undesired output");
        } catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			int[] ints = new int[5];
            double[] doubles = new double[] { 1, 0.5, 0.25, 0.125, 0.0625 };
            System.arraycopy(doubles, 0, ints, 0, 5);
            System.out.println("Undesired output");
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
            List<Integer> l = new ArrayList<>();
            l.add(1);
            ((LinkedList<Integer>) l).pop();
            System.out.println("Undesired output");
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}
