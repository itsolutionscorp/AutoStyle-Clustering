import java.util.*;

public class JugSolver {
	private int desiredAmt;
	private int capacity[];
	private HashSet<JugContents> alreadyTried;
	
	public JugSolver (int jug0size, int jug1size, int jug2size, int desired) {
		capacity = new int [3];
		capacity[0] = jug0size;
		capacity[1] = jug1size;
		capacity[2] = jug2size;
		desiredAmt = desired;
		alreadyTried = new HashSet<JugContents>(20);
	}
	
	// Try to solve the puzzle, starting at configuration b.
	public boolean tryPouring (JugContents jugsObject) {
		if (jugsObject == null)
			return false;
		debugPrint (jugsObject.toString());
		if (jugsObject.jugs[0] == desiredAmt || jugsObject.jugs[1] == desiredAmt
		        || jugsObject.jugs[2] == desiredAmt) {
			return true;
		}
		// Your code at this line, and possibly below
		alreadyTried.add(jugsObject);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i != j && tryPouring (pour(jugsObject, i, j))) {
					System.out.println ("Pouring from jug " + i + " to jug " + j);
					return true;
				}
			}
		}
		return false;
	}
	
	// Return the result of pouring as much as possible from jug from to jug to.
	public JugContents pour (JugContents current, int from, int to) {
		JugContents afterPour = new JugContents (current);
		int amtToCanGet = capacity[to] - current.jugs[to];
		int amtFromCanSupply = current.jugs[from];
		int amtPoured = min (amtToCanGet, amtFromCanSupply);
		debugPrint ("Pouring " + amtPoured 
			+ " from jug " + from + " to jug " + to);
		afterPour.jugs[from] -= amtPoured;
		afterPour.jugs[to] += amtPoured;
		if (alreadyTried.contains(afterPour))
			return null;
		return afterPour;
	}
		
	static int min (int x, int y) {
		if (x < y){
			return x;
		}
		else {
			return y;
		}
	}

	private static boolean DEBUGGING = false;

	private static void debugPrint (String s) {
		if (DEBUGGING) {
			System.out.println (s);
		}
	}

}