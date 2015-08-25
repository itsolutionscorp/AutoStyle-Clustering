import java.util.ArrayList;

public class Tray {

	boolean[][] myTray;
	ArrayList<int[]> allBlocks;
	static int myLength;
	static int myWidth;
	ArrayList<Tray> possibleMoves;
	int[] move;
	Tray previousTray;
	int distance;
	int key;

	public Tray(int y, int x) {
		myTray = new boolean[y][x];
		allBlocks = new ArrayList<int[]>();
		myLength = y;
		myWidth = x;
		possibleMoves = new ArrayList<Tray>();
		move = new int[]{0,0,0,0};
		previousTray = null;
		key = 0;
	}

	public void setPossibleMoves() {
		possibleMoves = allMoves();
	}

	public void put(int[] blockObj) {
		for (int i = blockObj[0]; i <= blockObj[2]; i++) {
			for (int j = blockObj[1]; j <= blockObj[3]; j++) {
				myTray[i][j] = true;
			}
		}
		allBlocks.add(blockObj);
	}

	public static boolean sameBlock(int[] b, int[] l) {
		for (int i = 0; i < 4; i++) {
			if (b[i] != l[i]) {
				return false;
			}
		}
		return true;
	}

	public void remove(int[] blockObj) {
		for (int[] temp : allBlocks){
			int x = 0;
			for (int i = 0; i < 4; i++){
				if (temp[i] == blockObj[i]){
					x++;
				}
			}
			if (x == 4){
				allBlocks.remove(temp);
				break;
			}
		}
		for (int i = blockObj[0]; i <= blockObj[2]; i++) {
			for (int j = blockObj[1]; j <= blockObj[3]; j++) {
				myTray[i][j] = false;
			}
		}
	}

	public boolean get(int y, int x) {
		return myTray[y][x];
	}

	public boolean[][] getTray() {
		return myTray;
	}

	public int getLength() {
		return myLength;
	}

	public int getWidth() {
		return myWidth;
	}

	public static Tray cloneTray(Tray currentTray) {
		Tray clone = new Tray(currentTray.getLength(), currentTray.getWidth());
		for (int[] block : currentTray.allBlocks) {
			int[] cloneBlock = new int[5];
			for (int index = 0; index < 4; index++) {
				cloneBlock[index] = block[index];
			}
			cloneBlock[4] = 0;
			clone.put(cloneBlock);
		}
		return clone;
	}

	public ArrayList<Tray> upMove() {
		for (int[] block : allBlocks) {
			block[4] = 0;
			outerloop: 
			for (int yPos = block[0]; yPos > 0; yPos--) {
				for (int xPos = block[1]; xPos <= block[3]; xPos++) {
					if (this.get(yPos - 1, xPos) == true) {
						block[4] = Math.abs(block[0] - yPos);
						break outerloop;

					}
				}
				block[4]++;
			}
		}
		ArrayList<Tray> adjTray = new ArrayList<Tray>();
		ArrayList<int[]> listOfBlocks = new ArrayList<int[]>();
		for (int[] block : this.allBlocks) {
			listOfBlocks.add(block);
		}
		for (int[] block : listOfBlocks) {
			int s = 1;
			while (s <= block[4]) {
				Tray newAdjTray = cloneTray(this);
				int prevY = block[0];
				int prevX = block[1];
				newAdjTray.remove(block);
				int[] newBlock = { block[0] - s, block[1], block[2] - s, block[3], 0 };
				newAdjTray.put(newBlock);
				newAdjTray.move[0] = prevY;
				newAdjTray.move[1] = prevX;
				newAdjTray.move[2] = newBlock[0];
				newAdjTray.move[3] = newBlock[1];
				adjTray.add(newAdjTray);
				s++;
			}
		}
		/*System.out.println("ups: ");
		for(Tray t: adjTray){
			System.out.println(t);
			System.out.println("---------");

		}*/
		return adjTray;
	}

	public ArrayList<Tray> downMove() {
		for (int[] block : allBlocks) {
			block[4] = 0;
			outerloop: for (int yPos = block[2]; yPos < this.getLength() - 1; yPos++) {
				for (int xPos = block[1]; xPos <= block[3]; xPos++) {
					if (this.get(yPos + 1, xPos) == true) {
						block[4] = Math.abs(yPos - block[2]);
						break outerloop;
					}
				}
				block[4]++;
			}
		}
		ArrayList<Tray> adjTray = new ArrayList<Tray>();
		ArrayList<int[]> listOfBlocks = new ArrayList<int[]>();
		for (int[] block : this.allBlocks) {
			listOfBlocks.add(block);
		}
		for (int[] block : listOfBlocks) {
			int s = 1;
			while (s <= block[4]) {
				Tray newAdjTray = cloneTray(this);
				int prevY = block[0];
				int prevX = block[1];
				newAdjTray.remove(block);
				int[] newBlock = {block[0] + s, block[1], block[2] + s, block[3], 0 };
				newAdjTray.put(newBlock);
				newAdjTray.move[0] = prevY;
				newAdjTray.move[1] = prevX;
				newAdjTray.move[2] = newBlock[0];
				newAdjTray.move[3] = newBlock[1];
				adjTray.add(newAdjTray);
				s++;
			}
		}
		/*System.out.println("downs: ");
		for(Tray t: adjTray){
			System.out.println(t);
			System.out.println("---------");
		}*/
		return adjTray;
	}

	public ArrayList<Tray> leftMove() {
		for (int[] block : allBlocks) {
			block[4] = 0;
			outerloop: for (int xPos = block[1]; xPos > 0; xPos--) {
				for (int yPos = block[2]; yPos >= block[0]; yPos--) {
					if (this.get(yPos, xPos - 1) == true) {
						block[4] = Math.abs(xPos - block[1]);
						break outerloop;
					}
				}
				block[4]++;
			}
		}
		ArrayList<Tray> adjTray = new ArrayList<Tray>();
		ArrayList<int[]> listOfBlocks = new ArrayList<int[]>();
		for (int[] block : this.allBlocks) {
			listOfBlocks.add(block);
		}
		for (int[] block : listOfBlocks) {
			int s = 1;
			while (s <= block[4]) {
				Tray newAdjTray = cloneTray(this);
				int prevY = block[0];
				int prevX = block[1];
				newAdjTray.remove(block);
				int[] newBlock = { block[0], block[1] - s, block[2], block[3] - s, 0 };
				newAdjTray.put(newBlock);
				newAdjTray.move[0] = prevY;
				newAdjTray.move[1] = prevX;
				newAdjTray.move[2] = newBlock[0];
				newAdjTray.move[3] = newBlock[1];
				adjTray.add(newAdjTray);
				s++;
			}
		}
		/*System.out.println("lefts: ");
		for(Tray t: adjTray){
			System.out.println(t);
			System.out.println("---------");

		}*/
		return adjTray;
	}

	public ArrayList<Tray> rightMove() {
		for (int[] block : allBlocks) {
			block[4] = 0;
			outerloop: for (int xPos = block[3]; xPos < this.getWidth() - 1; xPos++) {
				for (int yPos = block[2]; yPos >= block[0]; yPos--) {
					if (this.get(yPos, xPos + 1) == true) {
						block[4] = Math.abs(block[3] - xPos);
						break outerloop;
					}
				}
				block[4]++;
			}
		}

		ArrayList<Tray> adjTray = new ArrayList<Tray>();
		ArrayList<int[]> listOfBlocks = new ArrayList<int[]>();
		for (int[] block : this.allBlocks) {
			listOfBlocks.add(block);
		}
		for (int[] block : listOfBlocks) {
			int s = 1;
			while (s <= block[4]) {
				Tray newAdjTray = cloneTray(this);
				int prevY = block[0];
				int prevX = block[1];
				newAdjTray.remove(block);
				int[] newBlock = { block[0], block[1] + s, block[2], block[3] + s, 0 };
				newAdjTray.put(newBlock);
				newAdjTray.move[0] = prevY;
				newAdjTray.move[1] = prevX;
				newAdjTray.move[2] = newBlock[0];
				newAdjTray.move[3] = newBlock[1];
				adjTray.add(newAdjTray);
				s++;
			}
		}
		/*System.out.println("rights: ");
		for(Tray t: adjTray){
			System.out.println(t);
			System.out.println("---------");

		}*/
		return adjTray;
	}

	public ArrayList<Tray> allMoves() {
		ArrayList<Tray> allAdjTray = upMove();
		allAdjTray.addAll(downMove());
		allAdjTray.addAll(leftMove());
		allAdjTray.addAll(rightMove());
		return allAdjTray;
	}


	public int keyGen() {
		int key = 0;
		for (int[] b : allBlocks) {
			for (int i : b) {
				key += i;
				if (key % 2 == 0)
					key = key * key / myLength;
				else
					key = 2 * key + myWidth;
			}
		}
		return key;
	}

	public String toString(){
		String rtn = "";
		for(int[] block: allBlocks){
			for(int i: block){
				rtn += i + " ";
			}
			rtn += "\n";
		}
		return rtn;
	}
}
