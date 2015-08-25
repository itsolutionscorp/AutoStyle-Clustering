import java.awt.Point;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class Solver {
	// array list that contains string with each index a line from Init file
	ArrayList<String> lineInit;
	// array list that contains string with each index a line from Goal file
	ArrayList<String> lineGoal;
	// String used to change from Linux to Windows format
	static String BS = "/";
	// String used to get the current directory
	static String currDir = System.getProperty("user.dir") + BS;
	// a HashSet of "Srtring" that keeps track of visited Tray objects 
	HashSet<String> visited;
	// the initial Tray Objects
	Tray initTray; // update in isValidInput
	// comparator used to create a compare method of the PriorityQueue
	Comparator<Tray> comparator = new trayComparator();
	// used to store the tray in the faring according to closeness to solution
	PriorityQueue<Tray> queue = new PriorityQueue<Tray>(50, comparator);
	/**
	 * Initialize null to all instance variables
	 */
	public Solver() {
		lineInit = new ArrayList<String>();
		lineGoal = new ArrayList<String>();
		visited = new HashSet<String>();
	}

	/**
	 * Solves the problem using implicit Graph
	 */
	public void solveIt() {
		queue.add(initTray);
		// fringe.push(initTray);

		while (!queue.isEmpty()) {
			Tray X = queue.poll();
			if (isGoal(X)) {
				moves(X);
				break;
			} else {
				for (Tray XChild : X.children()) {
					if (!queue.contains(XChild)
							&& !visited.contains(XChild.toString())) {

						queue.add(XChild);
					}
				}
			}
			visited.add(X.toString());
		}
	}

	/**
	 * returns true if the argument is equal to goal
	 * 
	 * @param currTray
	 *            the current Tray under examination
	 * @return true if tray is goal else false
	 */

	public boolean isGoal(Tray currTray) {
		// lineGoal
		if (currTray.myPriority == 0) {
			return true;
		}
		return false;
	}

	/**
	 * prints out all the moves from the start to Goal(X)
	 * 
	 * @param X
	 *            the Goal Tray
	 */

	public void moves(Tray X) {
		Stack<String> S = new Stack<String>();
		// avoid visiting the first tray since it has no moves
		while (X.parentTray != null) {
			S.push(X.moveFromParent);
			X = X.parentTray;
		}
		while (!S.isEmpty()) {
			System.out.println(S.pop());
		}
	}

	/**
	 * checks if the input files init and goal are valid
	 * 
	 * @param args
	 *            a String Array that contains the file names for init and goal
	 *            files - must initialize instant Variables args[0] is init file
	 *            nae and args[1] is goal file name
	 * 
	 * @return true if the input files are valid else false - must be
	 *         bullet-proof
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public boolean isValidInput(String[] args) throws FileNotFoundException,
			IOException {
		fileReader(lineInit, args[0]);
		fileReader(lineGoal, args[1]);
		if (lineInit.isEmpty() || lineGoal.isEmpty()) {
			System.out.println("AA");
			return false;
		}
		int xtop;
		int ytop;
		int xbottom = 0;
		int ybottom = 0;
		int xsize;
		int ysize;
		int count = 0;
		int Xlenght = 0;
		int Ylenght = 0;
		String[] S;
		for (String line : lineInit) {
		
			S = line.split("\\s+");
			for(String s:S){
				if(s.substring(0).matches("[^0-9]")){ // makes sure it is an positive integer... 
					System.out.println("BB");

					return false;
				}
			}
			
			if (count == 0 && !(S.length == 2)) {   // make sure there are that file line are specific size
				System.out.println("CC");

				return false;
			} else if (count > 0 && !(S.length == 4)) {
				System.out.println("DD");

				return false;
			}
			xtop = Integer.parseInt(S[1]); // catch parse exception 
			ytop = Integer.parseInt(S[0]);
			if (count==0){
				Xlenght=xtop ;
				Ylenght=ytop ;
			}
			if (count > 0) {
				xbottom = Integer.parseInt(S[3]);
				ybottom = Integer.parseInt(S[2]);
				xsize = xbottom - xtop + 1;
				ysize = ybottom - ytop + 1;
				if ( xsize<=0 || ysize<=0){ // positive int for size...
					System.out.println("EE");

					return false;
				}
			}
			count++;
		}
		if ( xbottom>=Xlenght ||ybottom>=Ylenght){ // Boundary condition 
			System.out.println("FF");

			return false; 
		}
		initTray = new Tray(lineInit, lineGoal);

		return true;
	}

	/**
	 * Reads given file line by line and adds it to the given ArrayList as a
	 * String
	 * 
	 * @param lineArr
	 *            pointer to the ArrayList to add the read lines
	 * @param fileName
	 *            name of the file to be read (lineInit or lineGoal)
	 * @throws IOException
	 *             not handled (assume that isValidinput was true)
	 * @throws FileNotFoundException
	 *             not handled (assume that isValidinput was true)
	 */

	public void fileReader(ArrayList<String> lineArr, String fileName)
			throws FileNotFoundException, IOException {
		String file = currDir + fileName;
		try {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String linePotato;
				while ((linePotato = br.readLine()) != null) {
					// process the line.
					lineArr.add(linePotato);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("1Invalid init and/or goal file.");
		} catch (IOException i) {
			System.out.println("2Invalid init and/or goal file.");
		}
	}

	/**
	 * operates the Game!
	 * 
	 * @param args
	 *            a String Array that contains the file names for init and goal
	 *            files args[0] is init file nae and args[1] is goal file name
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		Solver s = new Solver();
		if (args.length == 2) {
			// check if args[0] and args[1] files exit
			if (s.isValidInput(args)) {

				s.solveIt();
			} else {
				System.out.println("3Invalid init and/or goal file.");
			}
			//
		} else {
			System.out.println("4Invalid init and/or goal file.");

		}

		//

	}
	
	/**
	 * a sub class used to create priority in priority queue
	 * 
	 * @author cs61bl-ch && cs61bl-hf
	 * 
	 */

	public class trayComparator implements Comparator<Tray> {
		/**
		 * this method is used by the priority queue to set a min priority
		 *      - if the tray has more blocks in the goal position it is given more priority
		 *      - else if both are equal use the distance from the goals to set priority 
		 * @param x,y
		 *        Tray Object to be compared by priority... 
		 *        
		 */
		@Override
		public int compare(Tray x, Tray y) {
			if (x.myPriority < y.myPriority) {
				return -1;
			}
			if (x.myPriority > y.myPriority) {
				return 1;
			}
//
//			if (x.myDistance < y.myDistance) {
//				return -1;
//			}
//			if (x.myDistance > y.myDistance) {
//				return 1;
//			}

			return 0;
		}
	}

}
