import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
public class Solver {
	private HashSet<Block> initConfig;
	private ArrayList<Block> goalConfig;
	private int width;
	private int height;
	private Tray initTray;
	private boolean[][] myBoard;
	private BufferedReader bufferedReader;
	public Solver(String initFileName, String goalFileName) throws IOException {
		initConfig = new HashSet<Block>();
		goalConfig = new ArrayList<Block>();
		
		File init = new File("./" + initFileName);
		File goal = new File("./" + goalFileName);
		//Read from init
		FileReader initReader = new FileReader(init);
		bufferedReader = new BufferedReader(initReader);
		String line = bufferedReader.readLine();
		String[] splited = line.split(" ");
		if (splited.length != 2) throw new IOException();
		height = Integer.parseInt(splited[0]);
		width = Integer.parseInt(splited[1]);
		myBoard = new boolean[height][width];
		
		
		ArrayList<Integer> coords;
		while ((line = bufferedReader.readLine()) != null) {
			splited = line.split(" ");
			if (splited.length != 4) throw new IOException();
			coords = new ArrayList<Integer>();
			for (String s : splited) {
				coords.add(Integer.parseInt(s));
			}
			//[y1, x1, y2, x2]
			for (int i = coords.get(0); i <= coords.get(2); i++) {
				for (int j = coords.get(1); j <= coords.get(3); j++) {
					if (myBoard[i][j] == true) throw new IOException();
					myBoard[i][j] = true;
				}
			}
			
			
			initConfig.add(new Block(coords));
			
			
		}
		initReader.close();
		//Read from goal
		FileReader goalReader = new FileReader(goal);
		bufferedReader = new BufferedReader(goalReader);
		ArrayList<Integer> goalcoords;
		while ((line = bufferedReader.readLine()) != null) {
			splited = line.split(" ");
			goalcoords = new ArrayList<Integer>();
			for (String s : splited) {
				goalcoords.add(Integer.parseInt(s));
			}
			goalConfig.add(new Block(goalcoords));
		}
		goalReader.close();
		bufferedReader.close();
		//add first tray into myTrays
		if (goalConfig.size() > initConfig.size()) throw new IllegalStateException();
		initTray = new Tray(initConfig, goalConfig, width, height);
	}
	
	public void solve() {
		if (initTray.isGoal()) {
            initTray.printMoves();
            Tray.problemSolved = true;
        }
		initTray.solve();
        
		 while(!Tray.fringe.isEmpty() && !Tray.problemSolved){
	            Tray t = Tray.fringe.poll();
	            t.solve();
	       }
	}
	
	public static void main(String[] args) {
		try {
			Solver solver = new Solver(args[0], args[1]);
//			System.out.print(solver.initConfig);
//			System.out.print(solver.goalConfig);
			solver.solve();
		} catch (IllegalStateException e) {
			System.out.println("Invalid init and/or goal file.");
		} catch (Exception e) {
			System.out.println("Invalid init and/or goal file.");
		}
		
	}
}




