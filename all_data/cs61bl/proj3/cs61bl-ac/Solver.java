import java.io.BufferedReader;
import java.io.File;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Solver {

	String initial; // the initial board setup (ex. 5 4 0 0 1 0 0 3 1 3 2 0 3 0
					// 2 3 3 3 1 1 2 2 3 1 3 2 4 0 4 0)
	ArrayList<int[]> destination; // the final board setup at completion
	String current; // the current board state returned at each execution of the
					// search algorithm. This is just the last element of path.
	int x_dim; //the x dimension of the board
	int y_dim; //the y dimension of the board

	public Solver(String destination, String current) {
		String[] container = current.split(" ");
		int x_dim = Integer.parseInt(container[0]);
		int y_dim = Integer.parseInt(container[1]);
		
		this.destination = stringToArray(x_dim + " " + y_dim + " " + destination);
		this.current = current; 
	}

	public void findSolution() {
		StateGraph myGraph = new StateGraph(destination, current);
		while (!isSolution(current)) {
			current = myGraph.visitNode(current);
		}
		myGraph.printPath(myGraph.getPath());
	}
	
	public boolean isSolution(String state) {
		ArrayList<int[]> stateArr = stringToArray(state);
	//	ArrayList<int[]> destArr = stringToArray(destination);
		for (int[] item : destination) {
			if (!arrayContains(stateArr, item)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean arrayContains(ArrayList<int[]> test, int[] against) {
		for (int i = 0; i < test.size(); i++) {
			if (Arrays.equals(test.get(i), against)) {
				return true;
			}
		} 
		return false;
	}

	public ArrayList<int[]> stringToArray(String s) {
		ArrayList<int[]> converted = new ArrayList<int[]>();
		String[] container = s.split(" ");
		for (int i = 2; i < container.length - 2; i+= 4) {
			int[] vertex = new int[4];
			vertex[0] = Integer.parseInt(container[i]);
			vertex[1] = Integer.parseInt(container[i+1]);;
			vertex[2] = Integer.parseInt(container[i+2]);;
			vertex[3] = Integer.parseInt(container[i+3]);;
			converted.add(vertex);
		}
		return converted;
	}
	
	public String arrayToString(int x_dim, int y_dim, ArrayList<int[]> vertices) {
		String newState = "";
		newState += x_dim + " " + y_dim + " ";
		for (int[] vertex : vertices) {
			for (int i = 0; i < vertex.length; i++) {
				newState += vertex[i] + " ";
			}
		}
		newState= newState.substring(0, newState.length()-1);
		return newState;
	}

	public static boolean checkFormate(String s, int a){
		String[] container = s.split(" ");
		int numElement = container.length;
		if(numElement % 4 == a){
			return true;
		}
		return false;
	}
	
	

public static String readFile(String fileName) throws IOException {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			File newFile = new File(fileName);
			if (!newFile.exists()) {
				System.out.println("Invalid init and/or goal file.");
				System.exit(0);
			}
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				while (line != null) {
					sb.append(line);
					String newLine = br.readLine();
					if (newLine != null) {
						sb.append(" ");
					}
					line = newLine;
				}
				return sb.toString();
			} finally {
				br.close();
			}
		}

public static boolean readGoal(String goalName) throws IOException{
	File newFile = new File(goalName);
	if (!newFile.exists()) {
		System.out.println("Invalid init and/or goal file.");
		System.exit(0);
	}
	String content = new String(readAllBytes(get(goalName)));
	String lines[] = content.split("[\r\n]+");
	//check the format
	// evey one should be length
	//String returnString = "";
	for (String line: lines){
		String[] container = line.split(" ");
		if(container.length != 4){
			System.out.println("Invalid init and/or goal file.");
			System.exit(0);
		}
	}
	return true;
}

public static boolean readInit(String initName) throws IOException{
	File newFile = new File(initName);
	if (!newFile.exists()) {
		System.out.println("Invalid init and/or goal file.");
		System.exit(0);
	}
	String content = new String(readAllBytes(get(initName)));
	String lines[] = content.split("[\r\n]+");
	
	//check the format
	// evey one should be length
	String[] firstLine = lines[0].split(" ");
	if(firstLine.length != 2){
		System.out.println("Invalid init and/or goal file.");
		System.exit(0);
	}

	for (int i =1; i < lines.length; i++){
		String line = lines[i];
		String[] container = line.split(" ");
		if(container.length != 4){
			System.out.println("Invalid init and/or goal file.");
			System.exit(0);
			}
	}

	return true;
}
	public static void main(String[] args) {
		String init;
		String destination;
		try {
			if (args.length != 2) {
				System.out.println("Invalid init and/or goal file.");
				System.exit(0);
			}else{
				if(readInit(args[0]) && readGoal(args[1])){
					init = readFile(args[0]);
					destination = readFile(args[1]);
			if (checkFormate(init, 2) && checkFormate(destination, 0)) {
				Solver mySolution = new Solver(destination, init);
				mySolution.findSolution();
			} else {
				System.out.println("Invalid init and/or goal file.");
				System.exit(0);
			}
			}
			}
		} catch (IOException e) {
			System.exit(0);
		} catch (IllegalArgumentException e1) {
			System.exit(0);
		}
	}
}

