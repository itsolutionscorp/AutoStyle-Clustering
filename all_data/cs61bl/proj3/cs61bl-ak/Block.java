import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class Block {

	Integer myHeight;
	Integer myWidth;
	String myName;
	Integer[] myCoordinates;
	
	public Block(String[] nums) {
		myName = "";
		myCoordinates = new Integer[4];
		for (int i = 0; i < 4; i++) {
			myName += nums[i];
			myCoordinates[i] = Integer.parseInt(nums[i]);
		}
		myHeight = myCoordinates[2] - myCoordinates[0];
		myWidth = myCoordinates[3] - myCoordinates[1];
	}
	
	public Block(Integer height, Integer width, Integer[] coords, String name) {
		myHeight = height;
		myWidth = width;
		myCoordinates = coords;
		myName = name;
	}
	
	public ArrayList<Integer[]> moves(Set<String> empties) {
		ArrayList<Integer[]> moves = new ArrayList<Integer[]>();
		Boolean up = canMove(myCoordinates, myCoordinates[1], myCoordinates[3], "up", empties);
		Boolean down = canMove(myCoordinates, myCoordinates[1], myCoordinates[3], "down", empties);
		Boolean left = canMove(myCoordinates, myCoordinates[0], myCoordinates[2], "left", empties);
		Boolean right = canMove(myCoordinates, myCoordinates[0], myCoordinates[2], "right", empties);
		if (up) {
			moves.add(new Integer[] {myCoordinates[0], myCoordinates[1], myCoordinates[0] - 1, myCoordinates[1]});
		}
		if (down) {
			moves.add(new Integer[] {myCoordinates[0], myCoordinates[1], myCoordinates[0] + 1, myCoordinates[1]});
		}
		if (right) {
			moves.add(new Integer[] {myCoordinates[0], myCoordinates[1], myCoordinates[0], myCoordinates[1] + 1});
		}
		if (left) {
			moves.add(new Integer[] {myCoordinates[0], myCoordinates[1], myCoordinates[0], myCoordinates[1] - 1});
		}
		return moves;
	}

	public boolean canMove(Integer[] block, Integer start, Integer end, String direction, Set<String> empties) {
		for (int i = start; i <= end; i++) {
			Integer[] newPos;
			switch (direction) {
				case "up": 
					newPos = new Integer[] {block[0] - 1, i};
					break;
				case "down": 
					newPos = new Integer[] {block[2] + 1, i};
					break;
				case "left":
					newPos = new Integer[] {i, block[1] - 1};
					break;
				default: newPos = new Integer[] {i, block[3] + 1};
			}
			String pos = newPos[0] + " " + newPos[1];
			if (empties.contains(pos)) {
				continue;
			}
			return false;
		}
		return true;
	}
	
	public Block move(Integer[] mv) {
		Integer[] newCoords = new Integer[] {mv[2], mv[3], mv[2] + myHeight, mv[3] + myWidth};
		String newName = "" + newCoords[0] + newCoords[1] + newCoords[2] + newCoords[3];
		return new Block(myHeight, myWidth, newCoords, newName);
	}
	
	public boolean equals(Object other) {
		if (other instanceof Block) {
			if (myName.equals(((Block) other).myName)) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return myName;
	}
}