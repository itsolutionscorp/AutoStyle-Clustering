import java.util.*;

public class Tray implements Cloneable{
	private int myHeight;
	private int myWidth;
	private int[][] myTray;
	private ArrayList<Block> myBlocks; 
	private Block myTarget;
	private LinkedList<int[]> myRoute;
	
	
	public Tray(int height, int width) {
		myHeight = height;
		myWidth = width;
		myTray = new int[myWidth][myHeight];
		myBlocks = new ArrayList<Block>();
		myTarget = null;
	}
	
	public Tray(int height, int width, int[][] tray, ArrayList<Block> blocks, Block target) {
		myHeight = height;
		myWidth = width;
		myTray = tray;
		myBlocks = blocks;
		myTarget = target;
	}
	
	public Tray clone() {
		int cloneHeight = myHeight;
		int cloneWidth = myWidth;
		int[][] cloneTray = new int[myWidth][myHeight];
		for (int i = 0; i < myWidth; i++) {
			cloneTray[i] = myTray[i].clone();
		}
		ArrayList<Block> cloneBlocks = (ArrayList<Block>) myBlocks.clone();
		Block cloneTarget = myTarget == null ? null : myTarget.clone();
		return new Tray(cloneHeight, cloneWidth, cloneTray, cloneBlocks, cloneTarget);
	}
		
	public void add(Block block) {
		myBlocks.add(block);
		flip(block);
	}
	
	public ArrayList<Block> getBlocks() {
		return myBlocks;
	}
	
	public int[][] getTray() {
		return myTray;
	}
	
	public void setTarget(Block target) {
		myTarget = target;
	}
	
	public void addRoute(int[] move) {
		myRoute.add(move);
	}
	
	public void setRoute(LinkedList<int[]> route) {
		myRoute = (LinkedList<int[]>) route.clone();
	}
	
	public LinkedList<int[]> getRoute() {
		return myRoute;
	}
	
	public boolean isSlidable(Block block, int dx, int dy) {
		setTarget(block);
		Tray dest = clone();
		dest.flip(block);
		dest.myBlocks.remove(block);
		block = block.slide(dx, dy);
		dest.myBlocks.add(block);
		int tlX = block.topLeftX;
		int tlY = block.topLeftY;
		int brX = block.bottomRightX;
		int brY = block.bottomRightY;
		if (tlX < 0 || tlY < 0|| brX >= myWidth ||brY >= myHeight) {
			return false; 
		}
		for (int x = tlX; x <= brX; x++) {
			for (int y = tlY; y <= brY; y++) {
				if (dest.myTray[x][y] == 1) {
					return false; 
				}
			}
		}
		dest.flip(block); 
		HashSet<String> alreadySeen = new HashSet<String>(); 
		LinkedList<Tray> fringe = new LinkedList<Tray>(); 
		alreadySeen.add(toString());
		fringe.add(this);
		while (!fringe.isEmpty()) {
			Tray curr = fringe.pop();
			if (curr.equals(dest)){
				return true;
			}
			else {
				for (int x : new int[] {-1, 0, 1}) {
					for (int y : new int[] {-1, 0, 1}) {
						if (x*x + y*y == 1 && curr.isSlidableHelper(curr.myTarget, x, y)) {
							Tray child = curr.slide(curr.myTarget, x, y);
							if (!alreadySeen.contains(child.toString())) {
								child.setTarget(curr.myTarget.slide(x, y));
								alreadySeen.add(child.toString());
								fringe.add(child);
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean isSlidableHelper(Block block, int dx, int dy) { 
		if (dx == 1) {
			if (block.bottomRightX  == myWidth - 1) {return false;}
			for (int y = block.topLeftY; y <= block.bottomRightY; y++) {
				if (myTray[block.bottomRightX  + 1][y] == 1) {return false;}
			}
		}
		if (dx == -1) {
			if (block.topLeftX == 0) {return false;}
			for (int y = block.topLeftY; y <= block.bottomRightY; y++) {
				if (myTray[block.topLeftX - 1][y] == 1) {return false;}
			}
		}
		if (dy == 1) {
			if (block.bottomRightY == myHeight - 1) {return false;}
			for (int x = block.topLeftX; x <= block.bottomRightX; x++) {
				if (myTray[x][block.bottomRightY + 1] == 1) {return false;}
			}
		}
		if (dy == -1) {
			if (block.topLeftY == 0) {return false;}
			for (int x = block.topLeftX; x <= block.bottomRightX; x++) {
				if (myTray[x][block.topLeftY - 1] == 1) {return false;}
			}
		}
		return true;	
	}
	
	public Tray slide( Block before, int dx, int dy) {
		Tray result = clone();
		result.myBlocks.remove(before);
		result.flip(before);
		Block after = before.slide(dx, dy);
		result.myBlocks.add(after);
		result.flip(after);
		return result;
	}
	
	public void flip(Block block) {
		for (int x = (int) block.topLeftX; x <= (int) block.bottomRightX; x++) {
			for (int y = (int) block.topLeftY; y <= (int) block.bottomRightY; y++) {
				myTray[x][y] = 1 - myTray[x][y]; 
			}
		}
	}
	
	public boolean check(Tray goal) {
		return myBlocks.containsAll(goal.myBlocks);
	}
	
	@Override
	public int hashCode() {
		int result = 0;
		for (int y = 0; y < myHeight; y++) {
			for (int x = 0; x < myWidth; x++) {
				result += myTray[x][y]==1 ? 0 : x + y * myWidth;
			}
		}
		return result * 31;
	}
	
	@Override
	public boolean equals(Object arg0) {
		Tray comp = (Tray) arg0;
		return myBlocks.size() == comp.myBlocks.size() && check(comp);
	}
}
