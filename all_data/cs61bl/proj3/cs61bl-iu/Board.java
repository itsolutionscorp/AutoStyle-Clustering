import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Board {
	
	private short height;
	private short width;
	private HashMap<ShortPoint, Block> tray;
	private HashSet<Block> allBlocks;
	private ArrayList<String> history;
	private HashSet<ShortPoint> emptySet;
	private HashSet<Block> adjacentEmpty;
	
	//root Board
	public Board(int h, int w) {
		height = (short) h;
		width = (short) w;
		tray = new HashMap<ShortPoint, Block>();
		history = new ArrayList<String>();
		allBlocks = new HashSet<Block>();
		emptySet = new HashSet<ShortPoint>();
		adjacentEmpty = new HashSet<Block>();
	}
	
	//assumes creating board is valid move. Creates an identical board, 
	public Board(Board b) {
		height = b.getHeight();
		width = b.getWidth();
		tray = new HashMap<ShortPoint, Block>();
		allBlocks = new HashSet<Block>();
		history = new ArrayList<String>();
		Block block = null;
		for (String s: b.getHistory()) {
			history.add(new String(s));
		}
		for (ShortPoint p: b.getTray().keySet()) {
			block = b.getTray().get(p).clone();
			allBlocks.add(block);
		}
		for (Block bl: allBlocks) {
			insertBlock(bl);
		}
		emptySet = new HashSet<ShortPoint>();
		for (ShortPoint p: b.emptySet){
			emptySet.add(p.clone());
		}
		adjacentEmpty = new HashSet<Block>();
		for (Block bl: b.adjacentEmpty) {
			adjacentEmpty.add(bl.clone());
		}
	}
	
	public HashSet<Block> getAdjacentEmpty() {
		return adjacentEmpty;
	}
	public ArrayList<String> getHistory() {
		return history;
	}
	
	public HashSet<ShortPoint> getEmptySet() {
		return emptySet;
	}
	
	public void insertBlock(Block b) {
		short tlX = b.getTopLeft().getX();
		short brX = b.getBotRight().getX();
		short tlY = b.getTopLeft().getY();
		short brY = b.getBotRight().getY();
		if (validInsert(b)) {
			for (short i = tlX; i <= brX; i++) {
				for (short j = tlY; j <= brY; j++) {
					ShortPoint p = new ShortPoint(i, j);
					tray.put(p, b);
				}
			}
			allBlocks.add(b);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public short getHeight() {
		return height;
	}
	
	public short getWidth() {
		return width;
	}
	
	public boolean validInsert(Block b) {
		ShortPoint TL = b.getTopLeft();
		ShortPoint BR = b.getBotRight();
		short tlX = TL.getX();
		short tlY = TL.getY();
		short brX = BR.getX();
		short brY = BR.getY();
		
		for (short i = tlX; i <= brX; i++) {
			for (short j = tlY; j <= brY; j++) {
				ShortPoint temp = new ShortPoint(i, j);
				if (tray.containsKey(temp)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public HashMap<ShortPoint, Block> getTray() {
		return tray;
	}
	
	public HashSet<Block> getAllBlocks() {
		return allBlocks;
	}
	
//	public int hashCode() {
//		int total = 0;
//		for (Block b: allBlocks) {
//			total += b.hashCode();
//		}
//		return total;
//	}
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (! (obj instanceof Board)) {
			return false;
		} else {
			Board other = (Board) obj;
			return this.tray.equals(other.getTray());
		}
	}
	
	public String toString() {
		String all = "";
		for (Block b: getAllBlocks()) {
			all += b.toString() + " : ";
		}
		return all;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allBlocks == null) ? 0 : allBlocks.hashCode());
		return result;
	}

	public void move(Block b, String s) {
		int tlX = (int) b.getTopLeft().getX();
		int tlY = (int) b.getTopLeft().getY();
		allBlocks.remove(b);
		//changeBlock(b, s);
		b.move(s);
		allBlocks.add(b);
		ShortPoint p = b.getTopLeft();
		String oldx = "" + tlX;
		String oldy = "" + tlY;
		String newx = "" + p.getX();
		String newy = "" + p.getY();
		String combined = "" + oldy + " " + oldx + " " + newy + " " + newx;
		history.add(combined);
	}
	
	public boolean validAndChange(ShortPoint p, String s) {
		Block b = tray.get(p);
		ShortPoint topLeft = b.getTopLeft();
		short topLeftX = topLeft.getX();
		short topLeftY = topLeft.getY();
		ShortPoint botRight = b.getBotRight();
		short botRightX = botRight.getX();
		short botRightY = botRight.getY();
		ShortPoint temp = null;
		ShortPoint temp2 = null;
		if (s.equals("left")) {
			if (topLeftX == 0) {
				return false;
			}
			for (int j = topLeftY; j <= botRightY; j++) {
				temp = new ShortPoint((topLeftX - 1), j);
				if (tray.containsKey(temp)) {
					return false;
				}
				tray.put(temp, b);
				temp2 = new ShortPoint(botRightX, j);
				tray.remove(temp2);	
				emptySet.remove(temp);
				emptySet.add(temp2);
			}
		} else if (s.equals("right")) {
			if (botRightX == width - 1) {
				return false;
			}
			for (int j = topLeftY; j <= botRightY; j++) {
				temp = new ShortPoint((int) (botRightX + 1), j);
				if (tray.containsKey(temp)) {
					return false;
				}
				tray.put(temp, b);
				temp2 = new ShortPoint(topLeftX, j);
				tray.remove(temp2);
				emptySet.remove(temp);
				emptySet.add(temp2);
			}
		} else if (s.equals("up")) {
			if (topLeftY == 0) {
				return false;
			}
			for (int j = topLeftX; j <= botRightX; j++) {
				temp = new ShortPoint(j, topLeftY - 1);
				if (tray.containsKey(temp)) {
					return false;
				}
				tray.put(temp, b);
				temp2 = new ShortPoint(j, botRightY);
				tray.remove(temp2);
				emptySet.remove(temp);
				emptySet.add(temp2);
			}
		} else if (s.equals("down")) {
			if (botRightY == height - 1) {
				return false;
			}
			for (int j = topLeftX; j <= botRightX; j++) {
				temp = new ShortPoint(j, botRightY + 1);
				if (tray.containsKey(temp)) {
					return false;
				}
				tray.put(temp, b);
				temp2 = new ShortPoint(j, topLeftY);
				tray.remove(temp2);
				emptySet.remove(temp);
				emptySet.add(temp2);
			}
		}
		this.move(b, s);
		return true;
	}
	
	public void findAdjacent() {
		ShortPoint p_top;
		ShortPoint p_left;
		ShortPoint p_right;
		ShortPoint p_bot;
		adjacentEmpty = new HashSet<Block>();
		short pX;
		short pY;
		for (ShortPoint p : emptySet) {
			pX = p.getX();
			pY = p.getY();
			if (pY != 0) {
				p_top = new ShortPoint(pX, pY - 1);
			} else {
				p_top = null;
			}
			if (pY != height - 1) {
				p_bot = new ShortPoint(pX, pY + 1);
			} else {
				p_bot = null;
			}
			if (pX != 0) {
				p_left = new ShortPoint(pX - 1, pY);
			} else {
				p_left = null;
			}
			if (pX != width - 1) {
				p_right = new ShortPoint(pX + 1, pY);
			} else {
				p_right = null;
			}
			if (tray.containsKey(p_bot)) {
				adjacentEmpty.add(tray.get(p_bot));
			}
			if (tray.containsKey(p_top)) {
				adjacentEmpty.add(tray.get(p_top));
			}
			if (tray.containsKey(p_left)) {
				adjacentEmpty.add(tray.get(p_left));
			}
			if (tray.containsKey(p_right)) {
				adjacentEmpty.add(tray.get(p_right));
			}
		}
	}
	
	
	
	
	
	
	
	
}
