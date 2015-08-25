import java.util.*;

public class JugSolver {
	private static HashSet<JugContents> memorizeTrue = new HashSet<JugContents>();
	private static HashSet<JugContents> memorizeFalse = new HashSet<JugContents>();
	private int desiredAmt;
	private int capacity[];
	
	public JugSolver (int jug0size, int jug1size, int jug2size, int desired) {
		capacity = new int [3];
		capacity[0] = jug0size;
		capacity[1] = jug1size;
		capacity[2] = jug2size;
		desiredAmt = desired;
	}
	
	// Try to solve the puzzle, starting at configuration b.
	public boolean tryPouring (JugContents jugsObject) {
		debugPrint (jugsObject.toString());
		if (jugsObject.jugs[0] == desiredAmt || jugsObject.jugs[1] == desiredAmt
		        || jugsObject.jugs[2] == desiredAmt) {
			memorizeTrue.add(jugsObject);
			return true;
		}
		if (memorizeTrue.contains(jugsObject)){
			return true;
		}
		if (memorizeFalse.contains(jugsObject)){
			return false;
		} 
		memorizeFalse.add(jugsObject);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i != j && tryPouring(pour(jugsObject, i, j))) {
					System.out.println ("Pouring from jug " + i + " to jug " + j);
					memorizeTrue.add(jugsObject);
					return true;
				} else {
				memorizeFalse.add(jugsObject);
				}
			}
		}
		memorizeFalse.add(jugsObject);
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