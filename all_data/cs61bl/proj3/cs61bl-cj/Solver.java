import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

public class Solver {

    static HashSet<Piece> goalPieces = new HashSet<Piece>(100);

    static HashMap<Tray, Tray> predecessor = new HashMap<Tray, Tray>(100);

    private static void myActualMainYouAreNotTheMain(Scanner init, Scanner goal) {
        LinkedList<Integer[]> pieces = new LinkedList<Integer[]>();

        if(!init.hasNext() || !goal.hasNext()){
            System.out.println("Invalid init and/or goal file.");
            return;
        }
        String dimensionsString = init.nextLine();
        String[] dimensionsStringSplit = dimensionsString.split(" ");
        int[] dimensions = new int[2];
        if(dimensionsStringSplit.length!=2){
            System.out.println("Invalid init and/or goal file.");
            return;
        } else{
        	dimensions[0] = Integer.parseInt(dimensionsStringSplit[0]);
        	dimensions[1] = Integer.parseInt(dimensionsStringSplit[1]);
        }
        while (init.hasNext()) {
            Integer[] newPiece = new Integer[4];
            String stringPiece = init.nextLine();
            String [] stringPieceSplit = stringPiece.split(" ");
            if(stringPieceSplit.length!=4){
                System.out.println("Invalid init and/or goal file.");
                return;
            }
            for (int i = 0; i < 4; i++) {
                newPiece[i] = Integer.parseInt(stringPieceSplit[i]);
                if (i == 0 && newPiece[i] < 0 ||
                        i == 1 && newPiece[i] < 0 ||
                        i == 2 && newPiece[i] >= dimensions[0] ||
                        i == 3 && newPiece[i] >= dimensions[1] ||
                        newPiece[i] == null) {
                    System.out.println("Invalid init and/or goal file.");
                    return;
                }
            }
            // System.out.println(Arrays.toString(newPiece));
            pieces.add(newPiece);
        }

        LinkedList<Integer[]> goals = new LinkedList<Integer[]>();
        while (goal.hasNext()) {
            Integer[] newGoal = new Integer[4];
            String stringGoal = goal.nextLine();
            String [] stringGoalSplit = stringGoal.split(" ");
            if(stringGoalSplit.length!=4){
                System.out.println("Invalid init and/or goal file.");
                return;
            }
            for (int i = 0; i < 4; i++) {
                newGoal[i] = Integer.parseInt(stringGoalSplit[i]);
                if (i == 0 && newGoal[i] < 0 ||
                        i == 1 && newGoal[i] < 0 ||
                        i == 2 && newGoal[i] >= dimensions[0] ||
                        i == 3 && newGoal[i] >= dimensions[1] ||
                        newGoal[i] == null) {
                    System.out.println("Invalid init and/or goal file.");
                    return;
                }
            }
            goals.add(newGoal);
        }

        // At this point there are three variables you care about:
        // Dimension is an int[], with the dimensions of the tray
        // pieces is a linked list of int[]. Each int[] has the coordinates of a
        // piece's starting position
        // goal is also a linked list of int[]. Each int[] has the coordinates
        // of a pieces goal position

        // Create Pieces
        HashSet<Piece> myPieces = new HashSet<Piece>();
        for (int i = 0; i < pieces.size(); i++) {
            Piece toAdd = new Piece(pieces.get(i)[0], pieces.get(i)[1], pieces.get(i)[2], pieces.get(i)[3]);
            myPieces.add(toAdd);
        }

        for (int i = 0; i < goals.size(); i++) {
            Piece newPiece = new Piece(goals.get(i)[0], goals.get(i)[1], goals.get(i)[2], goals.get(i)[3]);
            goalPieces.add(newPiece);
        }

        Tray initialBoard = new Tray(dimensions[0], dimensions[1], myPieces, goalPieces);
        Tray winningTray = null;
        if(myPieces.size() > dimensions[0]*dimensions[1]*0.7) 
            winningTray = searchDense(initialBoard);
        else
            winningTray = search(initialBoard);
        
        
        if (winningTray != null) {
            Tray currentTray = winningTray;
            Stack<Tray> winningMoves = new Stack<Tray>();
            while (currentTray != null) {
                winningMoves.add(currentTray);
                currentTray = predecessor.get(currentTray);
            }

            
            Tray nextTray;
            while (!winningMoves.isEmpty()) {
                nextTray = winningMoves.pop();
                if (nextTray.getMoveString() != null) {
                     System.out.println(nextTray.getMoveString());  //TODO
                }
            }
        }
    }

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Invalid init and/or goal file.");
            return;
        }
        Path initPath = Paths.get(args[0]);
        Path goalPath = Paths.get(args[1]);

        try {
            Scanner init = new Scanner(initPath);
            Scanner goal = new Scanner(goalPath);
            myActualMainYouAreNotTheMain(init, goal);
            init.close();
            goal.close();
        } catch (IOException e) {
            System.out.println("Invalid init and/or goal file.");
            return;
        }
    }
    
    static public Tray search(Tray initialBoard) {
        HashSet<Tray> visited = new HashSet<Tray>(100);
        LinkedList<Tray> fringe = new LinkedList<Tray>();
        fringe.push(initialBoard);
        Tray currentTray = null;
        
        if (initialBoard.checkFinal(goalPieces))
            return initialBoard;
        
        while (!fringe.isEmpty()) {
            currentTray = fringe.pop();
            visited.add(currentTray);
            HashSet<Tray> nextTrays = checkValidMoves(currentTray);
            
            for (Tray t : nextTrays) {
                if (!visited.contains(t)) {
                    fringe.push(t);
                    predecessor.put(t, currentTray);
                    visited.add(t);
                    if (t.checkFinal(goalPieces)) {
                      return t;
                    }
                } 
            }
        }

        return null;
    }
    static public Tray searchDense(Tray initialBoard) {
	
    	boolean depth = true;
    	int thresholdDepth = 30;
    	int thresholdBreadth = 10;
    	int count = 0; 

    	HashSet<Tray> visited = new HashSet<Tray>(300);
        LinkedList<Tray> fringe = new LinkedList<Tray>();
        fringe.add(initialBoard);
        Tray currentTray = null;
            
        if (initialBoard.checkFinal(goalPieces)) {
            return initialBoard;
        }
            
        while (!fringe.isEmpty()) {
        	
            if( (depth && count>=thresholdDepth) || (!depth && count>=thresholdBreadth)){
            	depth = !depth;
            }
        	
        	if(depth){
        		currentTray = fringe.pollLast();
        	} else{
        		currentTray = fringe.pollFirst();
        	}
            visited.add(currentTray);
                
            HashSet<Tray> nextTrays = checkValidMoves(currentTray);
   
            for (Tray t : nextTrays) {
                if (!visited.contains(t)) {
                    fringe.add(t);
                    count++;
                    predecessor.put(t, currentTray);
                    visited.add(t);
                    if (t.checkFinal(goalPieces)) {
                        return t;
                    }
                } 
            }
        }
        return null;
    }

    static public HashSet<Tray> checkValidMoves(Tray t){

        HashSet<Tray> nextStates = new HashSet<Tray>(100);
        for (Piece p : t.myPieces()) {
            nextStates.addAll(t.move(p));
        }

        return nextStates;

    }
}