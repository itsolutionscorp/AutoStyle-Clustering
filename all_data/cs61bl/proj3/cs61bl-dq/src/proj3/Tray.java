//package proj3;
import java.util.*;

public class Tray {
	private static HashSet <String> alreadySeen = new HashSet <String>();
	private boolean[][] tray;
	private ArrayList<Block> blocks;
	private HashSet<Tray> myNextTrays;
	private final int myHeight;
	private final int myWidth;
	private String myPrevMove;
	private String myCode;
	private Tray myPrevTray;

	public Tray(int height, int width, String prevMove, Tray prevTray, ArrayList<Block> blocklist) {
		myHeight = height;
		myWidth = width;
		myCode = "";
		myPrevMove = prevMove; 
		myPrevTray = prevTray;
		tray = new boolean[height][width];
		blocks = new ArrayList <Block>();
		myNextTrays = new HashSet<Tray>();
		for (Block b : blocklist) {
			blocks.add(b);
			for (int x = b.returnTL_X(); x <= b.returnBR_X(); x++) {
				for (int y = b.returnTL_Y(); y <= b.returnBR_Y(); y++) {
					tray[x][y] = true;
				}
			}
		}
	}

	public void setCode() {
		ArrayList <String> tempCodeArray = new ArrayList<String>();
		for (Block b: blocks) {
			String temp = b.returnCode();
			if (tempCodeArray.isEmpty()) {
				tempCodeArray.add(temp);
			} else {
				for (int i = 0; i < tempCodeArray.size(); i++) {
					
					String insideArray = tempCodeArray.get(i);
					
					if (temp.compareTo(insideArray)<0) {
						tempCodeArray.add(i, temp);
						break;
					} else if (i == tempCodeArray.size() - 1) {
						tempCodeArray.add(i+1,temp);
						break;
					}
				}
			}
		}
		
		StringBuilder temp2 = new StringBuilder();
		for (int i = 0; i < tempCodeArray.size(); i++) {
			temp2.append(tempCodeArray.get(i));
			temp2.append("\n");
		}
		myCode = temp2.toString();
	}
	
	public void addCodeToAlreadySeen() {
		alreadySeen.add(myCode);
	}
	
	public HashSet<Tray> returnNextTrays() {
		return myNextTrays;
	}
	
	public Tray returnPrevTray() {
		return myPrevTray;
	}
	
	public String returnPrevMove() {
		return myPrevMove;
	}
	
	public HashSet<String> returnAlreadySeen() {
		return alreadySeen;
	}
	
	public String returnCode() {
		return myCode;
	}
	
	public ArrayList<Block> returnBlocks() {
		return blocks;
	}
	
	public boolean canMoveRight(Block b) {
		int y_next = b.returnBR_Y() + 1;
		
		if (y_next >= myWidth) {
			return false;
		} else {
			for (int i = b.returnTL_X(); i <= b.returnBR_X(); i++) {
				if (tray[i][y_next] == true) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean canMoveLeft(Block b) {
		int y_prev = b.returnTL_Y() - 1;
		
		if (y_prev < 0) {
			return false;
		} else {
			for (int i = b.returnTL_X(); i <= b.returnBR_X(); i++) {
				if (tray[i][y_prev] == true) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean canMoveUp(Block b) {
		int x_prev = b.returnTL_X() - 1;
		
		if (x_prev < 0) {
			return false;
		} else {
			for (int i = b.returnTL_Y(); i <= b.returnBR_Y(); i++) {
				if (tray[x_prev][i] == true) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean canMoveDown(Block b) {
		int x_next = b.returnBR_X() + 1;
		
		if (x_next >= myHeight) {
			return false;
		}
		
		for (int i = b.returnTL_Y(); i <= b.returnBR_Y(); i++) {
			if (tray[x_next][i] == true) {
				return false;
			}
		}
		return true;
	}
	
	public HashSet<Tray> setNextTrays() {
		if (blocks == null) {
			return null;
		} else {
			for (Block b : blocks) {
				if (canMoveRight(b)) {
					StringBuilder s = new StringBuilder();
					s.append (b.returnTL_X() + " ");
					s.append (b.returnTL_Y() + " ");
					b.right();
					s.append (b.returnTL_X() + " ");
					s.append (b.returnTL_Y());
					ArrayList <Block> tempBlocks = new ArrayList<Block>();
					for (Block a : blocks) {
						tempBlocks.add(a.returnCopy());
					}
					b.left();
					Tray tempTray = new Tray (myHeight, myWidth, s.toString(), this, tempBlocks);
					tempTray.setCode();
					if (!alreadySeen.contains(tempTray.returnCode())) {
						myNextTrays.add(tempTray);
					}
				} 
				if (canMoveLeft(b)) {
					StringBuilder s = new StringBuilder();
					s.append (b.returnTL_X() + " ");
					s.append (b.returnTL_Y() + " ");
					b.left();
					s.append (b.returnTL_X() + " ");
					s.append (b.returnTL_Y());
					ArrayList <Block> tempBlocks = new ArrayList<Block>();
					for (Block a : blocks) {
						tempBlocks.add(a.returnCopy());
					}
					b.right();
					Tray tempTray = new Tray (myHeight, myWidth, s.toString(), this, tempBlocks);
					tempTray.setCode();
					if (!alreadySeen.contains(tempTray.returnCode())) {
						myNextTrays.add(tempTray);
					}
				}
				if (canMoveUp(b)) {
					StringBuilder s = new StringBuilder();
					s.append (b.returnTL_X() + " ");
					s.append (b.returnTL_Y() + " ");
					b.up();
					s.append (b.returnTL_X() + " ");
					s.append (b.returnTL_Y());
					ArrayList <Block> tempBlocks = new ArrayList<Block>();
					for (Block a : blocks) {
						tempBlocks.add(a.returnCopy());
					}
					b.down();
					Tray tempTray = new Tray (myHeight, myWidth, s.toString(), this, tempBlocks);
					tempTray.setCode();
					if (!alreadySeen.contains(tempTray.returnCode())) {
						myNextTrays.add(tempTray);
					}
				}
				if (canMoveDown(b)) {
					StringBuilder s = new StringBuilder();
					s.append (b.returnTL_X() + " ");
					s.append (b.returnTL_Y() + " ");
					b.down();
					s.append (b.returnTL_X() + " ");
					s.append (b.returnTL_Y());
					ArrayList <Block> tempBlocks = new ArrayList<Block>();
					for (Block a : blocks) {
						tempBlocks.add(a.returnCopy());
					}
					b.up();
					Tray tempTray = new Tray (myHeight, myWidth, s.toString(), this, tempBlocks);
					tempTray.setCode();
					if (!alreadySeen.contains(tempTray.returnCode())) {
						myNextTrays.add(tempTray);
					}
				}
			}
		}
		return myNextTrays;
	}
	
	@Override
	public int hashCode() {
		return returnCode().hashCode();
	}
}