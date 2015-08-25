import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class StateGraph {

	HashSet<String> visitedNodes; // stores the list of all visited
									// nodes as a hash map.
	ArrayList<String> path; // stores the Strings of the nodes you have visited
	ArrayList<String> myNeighbors; // an arrayList of the neighboring boards
									// (boards that can be reached by one move),
									// ordered by distance from the destination
									// from shortest to longest.
	ArrayList<int[]> dest; // the destination board
	ArrayList<Integer> myDistances; // list of distances

	public StateGraph(ArrayList<int[]> destination, String initial) {
		visitedNodes = new HashSet<String>();
		path = new ArrayList<String>();
		myNeighbors = new ArrayList<String>();
		dest = destination;
		path.add(initial);
		myDistances = new ArrayList<Integer>();
	}

	public void findNeighbors(String current) {
		myNeighbors = new ArrayList<String>();
		ArrayList<int[]> vertices = stringToArray(current);
		String[] container = current.split(" ");
		int x_dim = Integer.parseInt(container[0]);
		int y_dim = Integer.parseInt(container[1]);
		int index = 0;
		for (int[] vertex : vertices) {
			int[] moveLeft = new int[4];
			int[] moveRight = new int[4];
			int[] moveDown = new int[4];
			int[] moveUp = new int[4];
			boolean moveL = false;
			boolean moveR = false;
			boolean moveU = false;
			boolean moveD = false;
			if (vertex[0] > 0) {
				moveL = true;
				moveLeft[0] = vertex[0] - 1;
				moveLeft[1] = vertex[1];
				moveLeft[2] = vertex[2] - 1;
				moveLeft[3] = vertex[3];
			}
			if (vertex[1] > 0) {
				moveU = true;
				moveUp[0] = vertex[0];
				moveUp[1] = vertex[1] - 1;
				moveUp[2] = vertex[2];
				moveUp[3] = vertex[3] - 1;
			}
			if (vertex[2] < (x_dim - 1)) {
				moveR = true;
				moveRight[0] = vertex[0] + 1;
				moveRight[1] = vertex[1];
				moveRight[2] = vertex[2] + 1;
				moveRight[3] = vertex[3];
			}
			if (vertex[3] < (y_dim - 1)) {
				moveD = true;
				moveDown[0] = vertex[0];
				moveDown[1] = vertex[1] + 1;
				moveDown[2] = vertex[2];
				moveDown[3] = vertex[3] + 1;
			}
			int[] oldVertex = vertices.get(index);
			if (moveL) {
				vertices.set(index, moveLeft);
				String newStateLeft = arrayToString(x_dim, y_dim, vertices);
				if (noOverlaps(newStateLeft)) {
					myNeighbors.add(newStateLeft);
				}
			}
			if (moveR) {
				vertices.set(index, moveRight);
				String newStateRight = arrayToString(x_dim, y_dim, vertices);
				if (noOverlaps(newStateRight)) {
					myNeighbors.add(newStateRight);
				}
			}
			if (moveU) {
				vertices.set(index, moveUp);
				String newStateUp = arrayToString(x_dim, y_dim, vertices);
				if (noOverlaps(newStateUp)) {
					myNeighbors.add(newStateUp);
				}
			}
			if (moveD) {
				vertices.set(index, moveDown);
				String newStateDown = arrayToString(x_dim, y_dim, vertices);
				if (noOverlaps(newStateDown)) {
					myNeighbors.add(newStateDown);
				}
			}
			vertices.set(index, oldVertex);
			index++;
		}

	}

	public void printNeighbors() {
		for (int i = 0; i < myNeighbors.size(); i++) {
			System.out.println("MY NEIGHBOR " + i + " IS: "
					+ myNeighbors.get(i));
		}
	}

	public ArrayList<int[]> stringToArray(String s) {
		ArrayList<int[]> converted = new ArrayList<int[]>();
		String[] container = s.split(" ");
		for (int i = 2; i < container.length - 2; i += 4) {
			int[] vertex = new int[4];
			vertex[0] = Integer.parseInt(container[i]);
			vertex[1] = Integer.parseInt(container[i + 1]);
			vertex[2] = Integer.parseInt(container[i + 2]);
			vertex[3] = Integer.parseInt(container[i + 3]);
			converted.add(vertex);
		}
		return converted;
	}

	public String arrayToString(int x_dim, int y_dim,
			ArrayList<int[]> vertices) {
		String newState = "";
		newState += x_dim + " " + y_dim + " ";
		for (int[] vertex : vertices) {
			for (int i = 0; i < vertex.length; i++) {
				newState += vertex[i] + " ";
			}
		}
		newState = newState.substring(0, newState.length() - 1);
		return newState;
	}

public boolean noOverlaps(String state) {
		String[] container = state.split(" ");
		int x_dim = Integer.parseInt(container[0]);
		int y_dim = Integer.parseInt(container[1]);
		
		int[][] taken = new int[x_dim][y_dim];
		ArrayList<int[]> check = stringToArray(state);

		for (int[] vertex : check) {
			int x1 = vertex[0];
			int y1 = vertex[1];
			int x2 = vertex[2];
			int y2 = vertex[3];

			for(int i = x1; i <= x2; i++){
				for(int j = y1; j<= y2; j++){
					if(taken[i][j] == 1){
						return false;
					}else{
						taken[i][j]++;
					}
				}
			}			
		}
		return true;
	}

	public String bestNeighbor() {
		for (int i = 0; i < myNeighbors.size(); i++) {
			if (!visitedNodes.contains(myNeighbors.get(i))) {
				return myNeighbors.get(i);
			}
		}
		if (path.size() == 1) {
			throw new IllegalArgumentException();
		} else {
			path.remove(path.size() - 1);
		}
		return path.get(path.size() - 1);
	}

	public String visitNode(String state) {
		findNeighbors(state);
		//distanceToArray();
		//sortDistance();
		String newNode = bestNeighbor();
		visitedNodes.add(newNode);
		if (path.get(path.size() - 1) != newNode) {
			path.add(newNode);
		} else {
			newNode = visitNode(newNode);
		}
		return newNode;
	}

	public  void printPath(ArrayList<String> path) {
		ArrayList<int[]> prev = stringToArray(path.get(0));
		for (int i = 1; i < path.size(); i++) {
			ArrayList<int[]> curr = stringToArray(path.get(i));
			for (int j = 0; j < prev.size(); j++) {
				int[] oldcoord = prev.get(j);
				for (int k = 0; k < curr.size(); k++) {
					int[] newcoord = curr.get(k);
					if ((Arrays.equals(oldcoord, newcoord))) {
						curr.remove(newcoord);
						prev.remove(oldcoord);
						k--;
						j--;
					}
				}
			}
			if (curr.size() == 1 && prev.size() == 1) {
				int[] oldC = prev.get(0);
				int[] newC = curr.get(0);
				System.out.println(oldC[0] + " " + oldC[1] + " " + newC[0]
						+ " " + newC[1]);
			}
			prev = stringToArray(path.get(i));
		}
	}

	public int getDistance(String potential, ArrayList<int[]>  destArr) {
		int totalDistance = 0;
		ArrayList<int[]> potentialArr = stringToArray(potential);
		//ArrayList<int[]> destArr = stringToArray(dest);
		double minDist = Integer.MAX_VALUE;
		for (int[] destVertex : destArr) {
			minDist = Integer.MAX_VALUE;
			for (int[] vertex : potentialArr) {
				if (vertex[2] - vertex[0] == destVertex[2] - destVertex[0]
						&& vertex[3] - vertex[1] == destVertex[3]
								- destVertex[1]) {
					double distCheck = Math.pow((destVertex[0] - vertex[0]), 2)
							+ Math.pow((destVertex[1] - vertex[1]), 2);
					if (distCheck < minDist) {
						minDist = distCheck;
					}
				}
			}
			totalDistance += minDist;
		}
		return totalDistance;
	}

	public int getDistance2(String potential, ArrayList<int[]>  destArr) {
		ArrayList<int[]> potentialArr = stringToArray(potential);
		//ArrayList<int[]> destArr = stringToArray(dest);
		int totalDist = 0;
		for (int[] item : destArr) {
			if (!arrayContains(potentialArr, item)) {
				totalDist++;
			}
		}
		return totalDist;
	}

	public void distanceToArray() {
		for (int i = 0; i < myNeighbors.size(); i++) {
			int distance = getDistance(myNeighbors.get(i), dest);
			myDistances.add(distance);
		}
	}

	public void printDistance() {
		for (int i = 0; i < myDistances.size(); i++) {
			System.out.println("DISTANCE " + i + " IS: " + myDistances.get(i));
		}
	}

	public void sortDistance() {
		ArrayList<String> sortedNeighbors = new ArrayList<String>();
		while (myNeighbors.size() > 0) {
			int smallest = Integer.MAX_VALUE;
			for (int i = 0; i < myDistances.size(); i++) {
				if (myDistances.get(i) < smallest) {
					smallest = myDistances.get(i);
				}
			}
			int index = myDistances.indexOf(smallest);
			sortedNeighbors.add(myNeighbors.get(index));
			myNeighbors.remove(index);
			myDistances.remove(index);
		}
		myNeighbors = sortedNeighbors;
	}

	private static boolean arrayContains(ArrayList<int[]> test, int[] against) {
		for (int i = 0; i < test.size(); i++) {
			if (Arrays.equals(test.get(i), against)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<String> getPath() {
		return path;
	}
}
