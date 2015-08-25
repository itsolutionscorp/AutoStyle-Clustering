import java.util.Comparator;

/**
 * An implementation of the Comparator interface.
 * Compares two trays based on their heuristics.
 */

public class TrayHeuristicComparator implements Comparator<Tray> {

	@Override
	public int compare(Tray t1, Tray t2) {
		return t1.heuristic - t2.heuristic;
	}
	
}
