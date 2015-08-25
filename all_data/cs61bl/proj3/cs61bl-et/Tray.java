import java.io.*;
import java.util.*;

public class Tray {
	ArrayList<String> trayList = new ArrayList<String>();
	ArrayList<String> moveList = new ArrayList<String>();
	boolean[][] filled;
	private int trayHeight;
	private int trayWidth;
	String pastMove;
	private boolean otherMove;
	private int otherY;
	private int otherX;

	/**
	 * Construct the tree node for the tree
	 * 
	 * @param myFile
	 */
	public Tray(ArrayList<String> myList) {
		pastMove = null;
		trayList = myList;

		// create the array of array of booleans with the specified parameters
		String arraySize = trayList.get(0);

		// convert the character to a string...
		int index = arraySize.indexOf(' ');
		int y = Integer.parseInt(arraySize.substring(0, index));
		int x = Integer.parseInt(arraySize.substring(index + 1,
				arraySize.length()));

		filled = new boolean[y][x];
		trayHeight = y;
		trayWidth = x;

		// fill in the array of array of booleans...
		for (int i = 1; i < trayList.size(); i++) {
			String temp = trayList.get(i);
			index = temp.indexOf(' ');
			int y1 = Integer.parseInt(temp.substring(0, index));
			int secondIndex = temp.indexOf(' ', index + 1);
			int x1 = Integer.parseInt(temp.substring(index + 1, secondIndex));
			index = temp.indexOf(' ', secondIndex + 1);
			int y2 = Integer.parseInt(temp.substring(secondIndex + 1, index));
			int x2 = Integer.parseInt(temp.substring(index + 1, temp.length()));
			filled[y1][x1] = true;
			filled[y2][x2] = true;

			// if 1x3 or longer horizontal block...
			if (Math.abs(x1 - x2) > 1) {
				int topLeft = x1;
				while (topLeft < x2) {
					topLeft++;
					filled[y1][topLeft] = true;
				}
			}

			// if 3x1 or longer vertical block...
			if (Math.abs(y1 - y2) > 1) {
				int topLeft = y1;
				while (topLeft < y2) {
					topLeft++;
					filled[topLeft][x1] = true;
				}
			}

			// if a square block or really large block..
			if (Math.abs(y1 - y2) > 0 && Math.abs(x1 - x2) > 0) {
				int topY = y1;
				int topX = x1;
				int bottomY = y2;
				int bottomX = x2;

				while (topY < bottomY) {
					while (topX < bottomX) {
						filled[topY][topX] = true;
						topX++;
					}
					topX = x1;
					topY++;
				}
			}
		}
	}

	public Tray(File myFile) {
		pastMove = null;
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(myFile));
		} catch (FileNotFoundException e1) {
			System.out.println("Invalid init and/or goal file");
			System.exit(0);
		}
		String line;
		try {
			while ((line = in.readLine()) != null) {
				trayList.add(line);
			}
		} catch (IOException e) {
			System.out.println("Invalid init and/or goal file");
			return ;
		}

		// create the array of array of booleans with the specified parameters
		String arraySize = trayList.get(0);

		// convert the character to a string...
		int index = arraySize.indexOf(' ');
		int y = Integer.parseInt(arraySize.substring(0, index));
		int x = Integer.parseInt(arraySize.substring(index + 1,
				arraySize.length()));

		filled = new boolean[y][x];
		trayHeight = y;
		trayWidth = x;

		// fill in the array of array of booleans...
		for (int i = 1; i < trayList.size(); i++) {
			String temp = trayList.get(i);
			index = temp.indexOf(' ');
			int y1 = Integer.parseInt(temp.substring(0, index));
			int secondIndex = temp.indexOf(' ', index + 1);
			int x1 = Integer.parseInt(temp.substring(index + 1, secondIndex));
			index = temp.indexOf(' ', secondIndex + 1);
			int y2 = Integer.parseInt(temp.substring(secondIndex + 1, index));
			int x2 = Integer.parseInt(temp.substring(index + 1, temp.length()));
			filled[y1][x1] = true;
			filled[y2][x2] = true;

			// if 1x3 or longer horizontal block...
			if (Math.abs(x1 - x2) > 1) {
				int topLeft = x1;
				while (topLeft < x2) {
					topLeft++;
					filled[y1][topLeft] = true;
				}
			}

			// if 3x1 or longer vertical block...
			if (Math.abs(y1 - y2) > 1) {
				int topLeft = y1;
				while (topLeft < y2) {
					topLeft++;
					filled[topLeft][x1] = true;
				}
			}

			// if a square block or really large block..
			if (Math.abs(y1 - y2) > 0 && Math.abs(x1 - x2) > 0) {
				int topY = y1;
				int topX = x1;
				int bottomY = y2;
				int bottomX = x2;
				while (topY < bottomY) {
					while (topX < bottomX) {
						filled[topY][topX] = true;
						topX++;
					}
					topX = x1;
					topY++;
				}
			}
		}

	}

	// private int charToInt(char c) {
	// String temp = "" + c;
	// return Integer.parseInt(temp);
	// }

	public boolean equalsGoal(ArrayList<String> input) {
		for (int i = 0; i < input.size(); i++) {
			if (!trayList.contains(input.get(i))) {
				return false;
			}
		}
		return true;
	}

	public boolean equalsInit(ArrayList<String> input) {
		for (int i = 0; i < input.size(); i++) {
			if (!trayList.get(i).equals(input.get(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Create new trays depending on which spaces are open and what pieces can
	 * move into them
	 * 
	 * @return
	 */
	public ArrayList<Tray> move() {
		ArrayList<Tray> rtnList = new ArrayList<Tray>();

		// for each empty space in the tray
		for (int i = 0; i < filled.length; i++) {
			for (int k = 0; k < filled[i].length; k++) {
				if (!filled[i][k]) {

					// check if the spot below is empty or out of bounds - move
					// up!
					if (i < trayHeight && i >= 0 && k < trayWidth && k >= 0
							&& i + 1 < trayHeight && filled[i + 1][k] != false) {

						// check to see if the piece can move into the spot
						String topLeft = (i + 1) + " " + k + " ";
						int track = -1;
						for (String s : trayList) {
							if (s.startsWith(topLeft)) {
								track = trayList.indexOf(s);
								break;
							}
						}
						if (track != -1) {
							String temp = trayList.get(track);
							int index = temp.indexOf(' ');
							int y1 = Integer.parseInt(temp.substring(0, index));
							int secondIndex = temp.indexOf(' ', index + 1);
							int x1 = Integer.parseInt(temp.substring(index + 1,
									secondIndex));
							index = temp.indexOf(' ', secondIndex + 1);
							int y2 = Integer.parseInt(temp.substring(
									secondIndex + 1, index));
							int x2 = Integer.parseInt(temp.substring(index + 1,
									temp.length()));
							int emptyY = i;
							int emptyX = k;

							// if it can move, make a new tray of this
							// configuration and add it to the return list
							if (canMove(y1, x1, y2, x2, emptyY, emptyX)) {
								ArrayList<String> toBeAdded = new ArrayList<String>();
								for (String s : trayList) {
									toBeAdded.add(s);
								}
								toBeAdded.remove(track);
								String willAdd = new String((y1 - 1) + " " + x1
										+ " " + (y2 - 1) + " " + x2);
								toBeAdded.add(willAdd);
								Tray other = new Tray(toBeAdded);

								String move = (i + 1) + " " + k + " " + i + " "
										+ k;
								other.pastMove(move);
								rtnList.add(other);
							}
						}
					}
					// check if the spot above is empty or out of bounds - move
					// down!
					if (i < trayHeight && i >= 0 && k < trayWidth && k >= 0
							&& i - 1 >= 0 && filled[i - 1][k] != false) {
						otherMove = false;
						//System.out.println("should see this");
						Tray rtnTray = otherMoveHelper(i, k, -1, 0);
					

						//System.out.println("this is your rtnTray " + rtnTray);
						if (rtnTray != null) {
							rtnList.add(rtnTray);
							String move = "";
							if (otherMove) {
								move = otherY + " " + otherX + " "
										+ (otherY + 1) + " " + otherX;

							} else {
								move = (i - 1) + " " + k + " " + i + " " + k;
							}
							//System.out.println("right before move added");

							rtnTray.pastMove(move);
							rtnList.add(rtnTray);
						}
					}
					// check if the spot to the left is empty or out of bounds -
					// move right!
					if (i < trayHeight && i >= 0 && k < trayWidth && k >= 0
							&& k - 1 >= 0 && filled[i][k - 1] != false) {
						otherMove = false;
						Tray rtnTray = otherMoveHelper(i, k, 0, -1);

						if (rtnTray != null) {
							rtnList.add(rtnTray);
							String move = "";
							if (otherMove) {
								// System.out.println("entering other move ");
								// changed from (k-1);
								move = otherY + " " + otherX + " " + otherY
										+ " " + (otherX + 1);
							} else {
								move = i + " " + (k - 1) + " " + i + " " + k;
							}
							rtnTray.pastMove(move);
							rtnList.add(rtnTray);
						}
					}

					// check if the spot to the right is empty or out of bounds
					// - move left!
					if (i < trayHeight && i >= 0 && k < trayWidth && k >= 0
							&& k + 1 < trayWidth && filled[i][k + 1] != false) {
						Tray rtnTray = moveHelper(i, k, 0, 1);
						if (rtnTray != null) {
							rtnList.add(rtnTray);
							String move = i + " " + (k + 1) + " " + i + " " + k;
							rtnTray.pastMove(move);
							rtnList.add(rtnTray);
						}
					}
				}
			}
		}
		return rtnList;
	}

	private Tray moveHelper(int i, int k, int yDir, int xDir) {

		// check to see if the piece can move into the spot
		String topLeft = (i + yDir) + " " + (k + xDir) + " ";
		int index = -1;
		for (String s : trayList) {
			if (s.startsWith(topLeft)) {
				index = trayList.indexOf(s);
				break;
			}
		}
		if (index != -1) {
			String temp = trayList.get(index);
			int track = temp.indexOf(' ');
			int y1 = Integer.parseInt(temp.substring(0, track));
			int secondIndex = temp.indexOf(' ', track + 1);
			int x1 = Integer.parseInt(temp.substring(track + 1, secondIndex));
			track = temp.indexOf(' ', secondIndex + 1);
			int y2 = Integer.parseInt(temp.substring(secondIndex + 1, track));
			int x2 = Integer.parseInt(temp.substring(track + 1, temp.length()));
			int emptyY = i;
			int emptyX = k;

			// if it can move, make a new tray of this
			// configuration and add it to the return list
			if (canMove(y1, x1, y2, x2, emptyY, emptyX)) {
				// to change the board config...
				// (1) remove the string that's moving from the new array list
				// (2) if moving up add 1 to top left y and 1 to bottom right y
				// and add that to the new array list

				ArrayList<String> toBeAdded = new ArrayList<String>();
				for (String s : trayList) {
					toBeAdded.add(s);
				}
				toBeAdded.remove(index);
				String willAdd = "";
				if (xDir == 0) {
					willAdd = new String((y1 - yDir) + " " + x1 + " "
							+ (y2 - yDir) + " " + x2);
				} else if (yDir == 0) {
					willAdd = new String(y1 + " " + (x1 - xDir) + " " + y2
							+ " " + (x2 - xDir));
				}
				toBeAdded.add(willAdd);
				return new Tray(toBeAdded);
			}
		}
		return null;
	}

	private Tray otherMoveHelper(int i, int k, int yDir, int xDir) {
		// check to see if the piece can move into the spot
		String topLeft = (i + yDir) + " " + (k + xDir) + " ";
		int index = -1;
		for (String s : trayList) {
			if (s.startsWith(topLeft)) {
				index = trayList.indexOf(s);
				break;
			}
		}
		if (index != -1) {
			return moveHelper(i, k, yDir, xDir);
		} else {
			String bottomRight = (i + yDir) + " " + (k + xDir);

			for (String s : trayList) {

				if (s.endsWith(bottomRight)) {
					index = trayList.indexOf(s);
					break;
				}
			}

			if (index != -1) {
				String temp = trayList.get(index);
				int track = temp.indexOf(' ');
				int y1 = Integer.parseInt(temp.substring(0, track));
				int secondIndex = temp.indexOf(' ', track + 1);
				int x1 = Integer.parseInt(temp
						.substring(track + 1, secondIndex));
				track = temp.indexOf(' ', secondIndex + 1);
				int y2 = Integer.parseInt(temp
						.substring(secondIndex + 1, track));
				int x2 = Integer.parseInt(temp.substring(track + 1,
						temp.length()));
				int emptyY = i;
				int emptyX = k;
				otherY = y1;
				otherX = x1;
				
//				System.out.println("other move Helper");
//				System.out.println("y1 " + y1);
//				System.out.println("x1 " + x1);
//				System.out.println("can move " + canMove(y1, x1, y2, x2, emptyY, emptyX));

				// if it can move, make a new tray of this
				// configuration and add it to the return list
				if (canMove(y1, x1, y2, x2, emptyY, emptyX)) {

					ArrayList<String> toBeAdded = new ArrayList<String>();
					for (String s : trayList) {
						toBeAdded.add(s);
					}
					toBeAdded.remove(index);
					String willAdd = "";

					if (xDir == 0) {
						willAdd = new String((y1 - yDir) + " " + x1 + " "
								+ (y2 - yDir) + " " + x2);
					} else if (yDir == 0) {
						willAdd = new String(y1 + " " + (x1 - xDir) + " " + y2
								+ " " + (x2 - xDir));
					}

					toBeAdded.add(willAdd);
					otherMove = true;
					return new Tray(toBeAdded);
				}
			}
		}
		return null;
	}

	private boolean canMove(int y1, int x1, int y2, int x2, int emptyY,
			int emptyX) {
		int width = Math.abs(x1 - x2);
		int height = Math.abs(y1 - y2);
		int counter = 0;
		
		
		if (width == 0 && height == 0) {
			return true;
		}
		
		// works...
		if(emptyX > x1 && emptyX == x2) {
			return false;
		}
		if(emptyX > x2) {
			if(filled[y1][x2+1]){
				return false;
			}
		}
		// works...
		if(emptyY > y1 && emptyY == y2) {
			return false;
		}
		if(emptyY > y1) {
			if(filled[y2+1][x1]){
				return false;
			}
		}
		
		

		// check to see if you can move the block downwards into the empty space
		if (y1 > emptyY) {
			
			// check if the width of y permits it to move downwards
			counter = width;
			while (counter > 0) {
				if (emptyX + counter >= trayWidth) {
					return false;
				}
				if (filled[emptyY][emptyX + counter]) {
					return false;
				}
				counter--;
			}
		}
		if (y1 < emptyY) {
			
			counter = width;
			while (counter > 0) {
				if (emptyX + counter >= trayWidth) {
					return false;
				}
				if (filled[emptyY][emptyX + counter]) {
					return false;
				}
				counter--;
			}
			
		}

		if (x1 < emptyX) {
			counter = height;
			while (counter > 0) {

				if ((emptyY + counter) >= trayHeight) {
					return false;
				}
				if (filled[emptyY + counter][emptyX]) {
					return false;
				}
				counter--;
			}
		}
		if (x1 > emptyX) {
			counter = height;
			while (counter > 0) {
				if (emptyY + counter >= trayHeight) {
					return false;
				}
				if (filled[emptyY + counter][emptyX]) {
					return false;
				}
				counter--;
			}
		}
		return true;
	}

	public void trayToString() {
		for (int i = 0; i < trayList.size(); i++) {
			System.out.println(trayList.get(i));
		}
	}

	public void pastMove(String myString) {
		pastMove = myString;
	}

	public boolean equals(ArrayList<String> input) {
		return input.equals(trayList);
	}
}