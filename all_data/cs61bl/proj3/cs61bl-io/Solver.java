import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;
/***
 *  A programe to solving the Sliding Block Puzzle
 */
public class Solver {
	private Tray myTray = null;
	private HashMap<String, Block> block;
	private int x;
	private int y;

	/**
	 * A constructor for String. It helps with parsingn the String and create and initialize the data.
	 * @param args String[] a string array contains file location
	 */
	public Solver(String[] args){
		parsingArgs(args);
	}
	/** 
	 * Takes in a String array and parses each value and initializes the Tray.
	 * By the end of the method, the blocks should match the goal.
	 * 
	 * @param args String array of arguments init and goal
	 */
	private void parsingArgs(String[] args){
		// http://www.avajava.com/tutorials/lessons/how-do-i-read-a-string-from-a-file-line-by-line.html
		try {			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
			String strLine = br.readLine();
			if (strLine == null)
				System.exit(0);
			String[] parts = strLine.split(" ");			
			if (parts.length !=2)
				System.exit(0);
			try{
				x = Integer.parseInt(parts[0]);
				y = Integer.parseInt(parts[1]);
			} catch (NumberFormatException e){
				System.exit(0);
			}			
			myTray = new Tray(x,y,null,null);
			block = new HashMap<String, Block>();
			while ((strLine = br.readLine()) != null) {
				Block b = parseBlock(strLine);
				block.put(b.toString(), b);
			}
			br.close();
			myTray.setMyBlocks(block);
			myTray.updateBoard();
		} catch (IOException e){
			System.exit(0);
		}
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(args[1])));
			String strLine;
			block = new HashMap<String, Block>();
			while ((strLine = br.readLine()) != null){				
				Block ablock = parseBlock(strLine);
				block.put(ablock.toString(), ablock);
			}
			br.close();
			if (block.size() == 0) // if contains no goal
				System.exit(0);
		} catch (IOException e){
			System.exit(0);
		}
	}
	/**Taking a String and create a block based on given a String.
	 * make sure this block is valid and parsing able.	 * 
	 * @param strLine String block represenation
	 * @return Block
	 */
	private Block parseBlock(String strLine){		
		String[] parts = strLine.split(" ");				
		if (parts.length !=4)
			System.exit(0);		
		int x1 = -1;
		int x2 = -1;
		int y1 = -1;
		int y2 = -1;		
		try{
			x1 = Integer.parseInt(parts[0]);
			y1 = Integer.parseInt(parts[1]);
			x2 = Integer.parseInt(parts[2]);
			y2 = Integer.parseInt(parts[3]);
		} catch (NumberFormatException e){
			System.exit(0);
		}			

		if (x1<0 || x1 > x-1 || x2<0 || x2 > x-1 || y1<0 || y1 > y-1 || y2<0 || y2 > y-1)
			System.exit(0);
		return new Block(x1,y1,x2,y2);
	}
	/***
	 * Solving a puzzle by calling Next from DFSIterator, a helperclass that generate new Tray. Every call, it will check if this Tray is a solution.
	 * If found solution, terminate the iteration, then print the moves.
	 */
	public void SolvingDFS(){		
		Iterator<Tray> iter = new DFSIterator();
		Tray result = null;
		boolean foundSolution = false;
		if (goalNotFit())
			return;
		while (iter.hasNext()) {
			result = iter.next();
			if (result.contains(block)){
				foundSolution = true;
				break;
			}
			result.setNull();			
		}
		if (foundSolution){			
			printSolution(result);
		}
	}
	/** an private helper iterative class, Return current Tray and attempt to create many Tray
	 * based on the possible move of  each block in 4 directs, then add this new children Tray in the Fringe.
	 * to Make sure we never repeat generate the same Tray, a HashSet<Integer> AllGenerated, double check this hashCode.
	 *  Using Deep for Search algorithms.	 
	 */
	private class DFSIterator implements Iterator<Tray> {		
		private Stack<Tray> fringe;
		private HashSet<Integer> AllGenerated;
		public DFSIterator() {
			fringe = new Stack<Tray>();
			AllGenerated = new HashSet<Integer>();			
			fringe.add(myTray);
			AllGenerated.add(myTray.hashCode());
		}
		public boolean hasNext() {			
			return !fringe.isEmpty();
		}
		public Tray next() {			
			Tray current = fringe.pop();
			HashMap<String, Block> currentBlock = current.getAllBlock();			
			for (String Str: currentBlock.keySet()){				
				Block block = currentBlock.get(Str);
				Block	left = block.moveLeft(current);
				Block	right = block.moveRight(current);									
				Block	up = block.moveUp(current);
				Block	down = block.moveDown(current);
				if (down!=null){
					String myMove = block.getX1()+" "+block.getY1()+" "+down.getX1()+" "+down.getY1();
					Tray myNewTray = newTray(current, myMove, currentBlock, Str, down);					
					if (!AllGenerated.contains(myNewTray.hashCode())){
						fringe.add(myNewTray);	
						AllGenerated.add(myNewTray.hashCode());
					}
				}
				if (right!=null){
					String myMove = block.getX1()+" "+block.getY1()+" "+right.getX1()+" "+right.getY1();
					Tray myNewTray = newTray(current, myMove, currentBlock, Str, right);					
					if (!AllGenerated.contains(myNewTray.hashCode())){
						fringe.add(myNewTray);	
						AllGenerated.add(myNewTray.hashCode());
					}
				}	
				if (left!=null){
					String myMove = block.getX1()+" "+block.getY1()+" "+left.getX1()+" "+left.getY1();
					Tray myNewTray = newTray(current, myMove, currentBlock, Str, left);					
					if (!AllGenerated.contains(myNewTray.hashCode())){
						fringe.add(myNewTray);	
						AllGenerated.add(myNewTray.hashCode());
					}
				}
				if (up!=null){
					String myMove = block.getX1()+" "+block.getY1()+" "+up.getX1()+" "+up.getY1();
					Tray myNewTray = newTray(current, myMove, currentBlock, Str, up);					
					if (!AllGenerated.contains(myNewTray.hashCode())){
						fringe.add(myNewTray);	
						AllGenerated.add(myNewTray.hashCode());
					}
				}
			}
			return current;
		}
		public void remove(){
			return;
		}
	}
	/***
	 * Print the move that result this solution. This methods calling each Tray's move, its parent and so on and store String in Stack.
	 * Then it pop from Stack and print it, so it is in correct formate. 
	 * @param result Tray Tray that has contains all goal blocks.
	 */
	private void printSolution(Tray result){
		Stack<String> print = new Stack<String>();
		while (result != null && result.getMoveString() != null){
			print.add(result.getMoveString());
			result = result.getParent();
		}
		while (!print.empty()){			
			System.out.println(print.pop());
		}
	}
	/** This helper methods would helps optimize the solution by checking the area of the goal blocks and given blocks.
	 * make sure it is possible that is a solution.	 * 
	 * @return True if goal block is possible to reach from initial blocks
	 */
	private boolean goalNotFit(){
		HashMap<String, Block> currentBlock = myTray.getAllBlock();	

		if (block.size() > currentBlock.size())
			return true;

		ArrayList<Integer> areaGoal = new ArrayList<Integer>();		
		for (Block b: block.values()){
			areaGoal.add((b.getX2() - b.getX1() + 1)*(b.getY2() - b.getY1() + 1));				
		}		
		int count = areaGoal.size();

		for (Block b: currentBlock.values()){
			int area = (b.getX2() - b.getX1() + 1)*(b.getY2() - b.getY1() + 1);			
			if (areaGoal.contains(area)){
				areaGoal.remove(areaGoal.indexOf(area));
				count--;
				if (count <= 0)
					break;
			}
		}
		if (count <= 0)
			return false;
		return true;		
	}
	/**
	 * This helper methods would create new Tray and inherit the parent Tray's Block except the block that just move,
	 * then including the newBlock in this new Tray, including the string Move and return this newTray.	 * 
	 * @param current Tray currentTray
	 * @param myMove String the Move that happen
	 * @param currentBlock HashMap<String, Block> currentBlock in this currentTray 
	 * @param Str String presentation of the old coordinate of the block that just move.
	 * @param mynewBlock Block a new block with new coordinate
	 * @return Tray a newTray that have new Block inside
	 */
	private Tray newTray(Tray current, String myMove, HashMap<String, Block >currentBlock,String Str, Block mynewBlock){		
		Tray myNewTray = new Tray(x, y, current, myMove);
		HashMap<String, Block> temp = (HashMap<String, Block>) currentBlock.clone();
		temp.remove(Str); // this hashmap doesnt contains that piece
		myNewTray.setMyBlocks(temp); // add all piece
		myNewTray.getAllBlock().put(mynewBlock.toString(), mynewBlock);
		myNewTray.updateBoard();		
		return myNewTray;
	}
	/** This main methods to solve the sliding block puzzle, attempt to parsing, and Solving by using Deep for Search.
	 * @param args String[] presentation of the file locations
	 */
	public static void main(String[] args){
		if (args.length< 1 || args.length>3)
			System.exit(0);	
		Solver solving = new Solver(args);		
		solving.SolvingDFS();
	}
}
