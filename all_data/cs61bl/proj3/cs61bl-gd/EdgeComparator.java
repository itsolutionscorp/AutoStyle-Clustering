import java.util.Comparator;

public class EdgeComparator implements Comparator<Edge> {

	@Override
	public int compare(Edge o1, Edge o2) {
		if (o1.cost < o2.cost) {
			return -1;
		} else if (o1.cost > o2.cost) {
			return 1;
		} else {
			return 0;
		}
	}

}