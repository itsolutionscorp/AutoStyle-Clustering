public class Board {
//- starts a GUI supported version of the game.

    private Piece[][] pieces;
    private boolean empty;

    private int currentplayer; //first player should be Fire!
    private Piece selected = null;
    private int selected_x = -1;
    private int selected_y = -1;

//    private boolean canEndTurn = false;
    private boolean canEndTurn;

	public static void main (String args[]) {
        Board b = new Board(false);

        while(true) {
//        	System.out.println("Working " + ((int) (counter/1000)) + " current player: " + b.currentplayer);
			b.drawBoard(8);
	        if (StdDrawPlus.mousePressed()) {
	            int x = (int) StdDrawPlus.mouseX();
	            int y = (int) StdDrawPlus.mouseY();
	            if (b.canSelect(x, y)) {
	            	b.select(x, y);
	            }

//	            if (b.selected != null)
//	            	System.out.println("Selected hasCaptured = " + b.selected.hasCaptured());

	        }
	        else if (StdDrawPlus.isSpacePressed()) {
	        	if (b.canEndTurn()) {
	        		b.endTurn();
	        	}
	        }
        StdDrawPlus.show(20);
        }
    }

	public Board(boolean shouldBeEmpty) {
		empty = shouldBeEmpty;
		pieces = new Piece[8][8];
		canEndTurn = false;
		currentplayer = 0;

		if (!empty) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {					
					if (j == 2) new Piece(true, this, i, j, "bomb");
                	if (j == 5) new Piece(false, this, i, j, "bomb");

                	if (j == 1)	new Piece(true, this, i, j, "shield");
                	if (j == 6) new Piece(false, this, i, j, "shield");

                	if (j == 0)	new Piece(true, this, i, j, "pawn");
                	if (j == 7)	new Piece(false, this, i, j, "pawn");
	               	}
                }
            }
        }			
	}

    private void drawBoard(int N) {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
    	            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
    	            if (pieces[i][j] != null) {
    	            	Piece piece = pieces[i][j];

    	            	//define "type" - shield, bomb, or pawn.
    	            	String type;
    	            	if (piece.isShield())
    	            		type = "shield";
    	            	else if (piece.isBomb())
    	            		type = "bomb";
    	            	else
    	            		type = "pawn";
				    	
    	            	//if selected, draw a white square before drawing the piece.
    	            	if (selected == piece) {
    	            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		    	            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
    	            	}

    	            	//Now draw the piece!
				    	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				    	String filename = "img/" + type + "-";
				    	if (piece.side() == 0)
				    		filename = filename + "fire";
				    	else filename = filename + "water";
				    	if (piece.isKing())
				    		filename = filename + "-crowned";
				    	filename = filename + ".png";
				    	StdDrawPlus.picture(i + .5, j + .5, filename, 1, 1);    	            	
    	            }
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }                  
            }
        }
    }

	public Piece pieceAt(int x, int y) {
		if (inBounds(x, y))
			return pieces[x][y];
		else return null;
	} 

	private boolean inBounds(int x, int y) {
		return (x >= 0 && x <= 7 && y >= 0 && y <= 7);
	}

	public boolean canSelect(int x, int y) {
		Piece tried = pieceAt(x, y);

		if (tried == null) {
			//has something else been selected?
			if (selected == null)
				return false;
			//something has been selected. can it move to (x, y)?
			return validMove(selected_x, selected_y, x, y);
		}
		//If this line is reached, this place is a piece!

//		//if the user is just clicking on the same piece again, nothing will happen.
//		if (tried == selected)
//			return false;

		if (tried.side() != currentplayer)
			return false;
		//If this line is reached, this piece belongs to the current player!

		//Check if you've selected something, and if that something has already moved.
		if (selected != null && canEndTurn() == true)
			return false;

		//If this line is reached, there is either no other selected piece, or the
		//selected piece hasn't done anything. Therefore:
		return true;
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		if (!inBounds(xf, yf)) 
			return false;
		//So, xf and yf are in bounds!
		if (pieceAt(xf, yf) != null)
			return false;
		//So, xf and yf are blank spaces!
		if (xi == xf || yi == yf)
			return false;
		//So, x is not moving only in the x direction or only in the y direction!

		Piece tried = pieceAt(xi, yi);

		if (tried == null)
			return false;

		//if this piece has moved but not captured, it can't move again.
		if (canEndTurn() && !tried.hasCaptured())
				return false;

		//if final position is lower than initial... (water and kings go down)
		if (yi > yf) {
			if (tried.isFire() && !tried.isKing())
				return false;
		}
		else {
			if (!tried.isFire() && !tried.isKing())
				return false;
		}

		//So, the piece is going in the correct y direction for its type.

		//If yf & yi differ by 1, then xf & xi must differ by one.
		if ((yi - yf) == 1 || (yf - yi) == 1) {
			if ((xi - xf) == 1 || (xf - xi) == 1) {
				if (tried.hasCaptured())
					return false;
				else
					return true;
				}
			else 
				return false;
		}

		//If (xf, yf) == (xi + 2, yi + 2) or vice versa, then must check intermediate.
		//if it's anything else, then no dice.
		if (xf != xi + 2 && xf != xi - 2)
			return false;

		if (yf != yi + 2 && yf != yi - 2)
			return false;

/*		else if (xf == xi - 2) {
			if (yf != yi - 2) {
				if (yf != yi + 2)
					return false;
			else
				return true;
			}
*/
		//So, "tried" is trying to jump two spaces diagonally in some allowed direction.
		//What's it jumping over?
		int[] intermediate_position = victim_position(xi, yi, xf, yf);
		Piece intermediate = pieceAt(intermediate_position[0], intermediate_position[1]);
		if (intermediate == null || intermediate.side() == currentplayer)
			return false;
		else
			return true;
	}

	private int[] victim_position(int xi, int yi, int xf, int yf) {
		int[] position = new int[2];
		position[0] = xi + ((xf - xi)/2);
		position[1] = yi + ((yf - yi)/2);
		return position;
	}

	public void select(int x, int y) {
		Piece tried = pieceAt(x, y);

		if (tried == null) {
			//Clicked on a blank space for selected to move to.
			selected.move(x, y);
			canEndTurn = true;
		}
		else {
			//Clicked on a piece to select.
			selected = tried;
			selected_x = x;
			selected_y = y;
		}
	}

	public void place(Piece p, int x, int y) {
		if (inBounds(x, y) && p != null) {
			//Remove p from it's original position on the board if it's there.
			int[] pos = position(p);
			if (pos != null)
				remove(pos[0], pos[1]);
			//If there is a piece at (x, y), remove that piece!
			Piece original = pieceAt(x, y);
			if (original != null)
				remove(x, y);
			//Finally, place the piece!
			pieces[x][y] = p;
			selected_x = x;
			selected_y = y;
		}
	}

	private int[] position(Piece p) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (pieces[i][j] == p)
					return new int[]{i, j};
		return null;
	}

	public Piece remove(int x, int y) {
		if (!inBounds(x, y)) {
			System.out.println("(" + x + ", " + y + ") is out of bounds.");
			return null;
		}

		Piece toreturn = pieces[x][y];
		if (toreturn == null) 
			System.out.println("There is no piece at (" + x + ", " + y + ").");
		else {
			pieces[x][y] = null;
		}

//		System.out.println("The piece at (" + x + ", " + y + ") has been removed.");

		return toreturn;
	}

	public boolean canEndTurn() {
		return canEndTurn;
	}

	public void endTurn() {
//if (canEndTurn) {
		if (this.currentplayer == 0)
			this.currentplayer = 1;
		else
			this.currentplayer = 0;
		canEndTurn = false;
//		drawDeSelect();
		if (selected != null) //added for the autograder, and then for NewTestCheckers.java :)
			selected.doneCapturing();
		selected = null;
		String champion = winner();
		if (champion != null)
			System.out.println(champion);
		//Do I need to do anything else?
	}
//}
	public String winner() {
		int fire_left = 0;
		int water_left = 0;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire())
						fire_left += 1;
					else
						water_left += 1;
				}
			}
		if (fire_left + water_left == 0)
			return "No one"; //is alone. Believe me/Truly...
		else if (water_left == 0)
			return "Fire";
		else if (fire_left == 0)
			return "Water";
		else
			return null;
	}
}