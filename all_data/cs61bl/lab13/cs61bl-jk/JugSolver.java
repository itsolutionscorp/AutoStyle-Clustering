import java.util.*;

public class JugSolver {
	private int desiredAmt;
	private int capacity[];
	private HashSet<JugContents> memory;
	
	// Takes three capacities and desired amount
	public JugSolver (int jug0size, int jug1size, int jug2size, int desired) {
		capacity = new int [3];
		capacity[0] = jug0size;
		capacity[1] = jug1size;
		capacity[2] = jug2size;
		desiredAmt = desired;
		memory = new HashSet<JugContents>();
	}
	
	// Try to solve the puzzle, starting at configuration b.
	public boolean tryPouring (JugContents jugsObject) {
		debugPrint (jugsObject.toString());
		if (jugsObject.jugs[0] == desiredAmt || jugsObject.jugs[1] == desiredAmt
		        || jugsObject.jugs[2] == desiredAmt) {
			return true;
		}

		if (memory.contains(jugsObject)) {
			return false;
		}
		memory.add(jugsObject);
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i != j && tryPouring (pour(jugsObject, i, j))) {
					System.out.println ("Pouring from jug " + i + " to jug " + j);
					System.out.println(jugsObject);
					return true;
				}
			}
		}
		return false;
	}
	
	// Return the result of pouring as much as possible from jug from to jug to.
	public JugContents pour (JugContents current, int from, int to) {
		JugContents afterPour = new JugContents (current);
		int amtToCanGet = capacity[to] - current.jugs[to]; //To's capacity - To's content
		int amtFromCanSupply = current.jugs[from]; //From's content
		int amtPoured = min (amtToCanGet, amtFromCanSupply);
		debugPrint ("Pouring " + amtPoured 
			+ " from jug " + from + " to jug " + to);
		afterPour.jugs[from] -= amtPoured; //From -
		afterPour.jugs[to] += amtPoured; //To +
		return afterPour;
	}
	
	
	//Helper methods
	static int min (int x, int y) {
		return x < y ? x : y;
	}
	
	//Open debugging print
	private static boolean DEBUGGING = false;

	private static void debugPrint (String s) {
		if (DEBUGGING) {
			System.out.println (s);
		}
	}

}