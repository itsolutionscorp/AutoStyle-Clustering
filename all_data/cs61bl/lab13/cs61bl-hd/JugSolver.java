import java.util.HashMap;

public class JugSolver {
	private int desiredAmt;
	private int capacity[];
	HashMap<JugContents, Boolean> map;
	HashMap<JugContents, Boolean> doneBefore;
	public JugSolver (int jug0size, int jug1size, int jug2size, int desired) {
		capacity = new int [3];
		capacity[0] = jug0size;
		capacity[1] = jug1size;
		capacity[2] = jug2size;
		desiredAmt = desired;
		map = new HashMap<JugContents, Boolean>();
		doneBefore = new HashMap<JugContents, Boolean>();
		
		
	}
	
	public boolean tryPouring (JugContents jugsObject) {
		debugPrint (jugsObject.toString());
		if (jugsObject.jugs[0] == desiredAmt || jugsObject.jugs[1] == desiredAmt
		        || jugsObject.jugs[2] == desiredAmt) {
			map.put(jugsObject, true);
			return true;
		}
		if (map.get(jugsObject)!=null && map.get(jugsObject)) return true;
		if (map.get(jugsObject)!=null && !map.get(jugsObject)) return false;
		if (doneBefore.get(jugsObject)!= null && doneBefore.get(jugsObject)) {
			return false;
		}
		if (doneBefore.get(jugsObject)==null) {
			doneBefore.put(jugsObject, true);
		}

		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i != j && tryPouring (pour(jugsObject, i, j))) {
					System.out.println ("Pouring from jug " + i + " to jug " + j);
					map.put(jugsObject, true);
					//success.add(jugsObject);
					return true;
				}
			}
		}
		map.put(jugsObject, false);
		return false;
	}
	
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

	private static boolean DEBUGGING = true;

	private static void debugPrint (String s) {
		if (DEBUGGING) {
			System.out.println (s);
		}
	}

}