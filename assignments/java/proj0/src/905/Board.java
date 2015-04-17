
public class Board {
	private Piece[][] pieces = new Piece[8][8];
	private boolean hasSelected = false, movedPiece = false, 
					firePlaying = true;
	private Piece pieceSelected;
	private int selectedX, selectedY;

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			boolean fire = true;
			String type;
			for (int j = 0; j < 8; j += 1) {
				int adjust = 0;
				if (j%2 != 0)
					adjust = 1;
				if (j > 4) {
					fire = false;
				}
				if (j == 1 || j == 6) {
					type = "shield";
				}
				else if (j == 2 || j == 5) {
					type = "bomb";
				}
				else {
					type = "pawn";
				}
				for (int i = 0; (i + adjust) < 8; i += 2) {
					if (j < 3 || j > 4) {
						pieces[i + adjust][j] = new Piece(fire, this, i + adjust, j, type);
					}
				}
			}	
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null) {
			if ((pieceAt(x,y).isFire() && !firePlaying) || 
				((!pieceAt(x,y).isFire() && firePlaying)))
				return false;
			if (!movedPiece) 
				return true;
			return false;
		}
		else {
			if (hasSelected && validMove(selectedX, selectedY, x, y)) {
				return true;
			}
			return false;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xf, yf) != null) {
			return false;
		}

		else if ((((xi - xf) == 1) || ((xi - xf) == -1)) &&
			 !pieceAt(xi,yi).hasCaptured() && !movedPiece) {
			if (pieceAt(xi, yi).isKing()) {
				if (((yi - yf) == 1) || ((yi - yf) == -1)) {
					return true;
				}
			}
			int check = -1;
			if (pieceAt(xi, yi).isFire()) 
				check = 1;
			if ((yf - yi) == check)
				return true;
		}
		else if (((xi - xf) == 2 || (xi - xf) == -2))  {
			if ((yf - yi) == 2) {
				if (pieceAt((xi + xf)/2, (yi + yf)/2) != null) {
					if (firePlaying && !pieceAt((xi + xf)/2, (yi + yf)/2).isFire())
						return true;
					else if ((pieceAt(xi, yi).isKing()) &&
								!firePlaying && pieceAt((xi + xf)/2, (yi + yf)/2).isFire())
						return true;
				}
			}
			else if ((yf - yi) == -2) {
				if (pieceAt((xi + xf)/2, (yi + yf)/2) != null) { 
					if (!firePlaying && pieceAt((xi + xf)/2, (yi + yf)/2).isFire())
						return true;
					else if ((pieceAt(xi, yi).isKing()) &&
								firePlaying && !pieceAt((xi + xf)/2, (yi + yf)/2).isFire())
						return true;
				}
			}
			
		}
		return false;	
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			pieceSelected = pieceAt(x, y);
			hasSelected = true;
			selectedX = x;
			selectedY = y;
		}

		else {
			pieceSelected.move(x, y);
			if (pieceSelected.isBomb()) {
				pieceSelected = null;
				hasSelected = false;
			}
			movedPiece = true;
		}
	}

	public void place(Piece p, int x, int y) {
		if (x < 0 || y < 0 || x > 7 || y > 7 || p == null) {
			return;
		}
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (x < 0 || y < 0 || x > 7 || y > 7) {
			System.out.println("Place out of bounds");
			return null;
		}

		else if (pieceAt(x, y) == null) {
			System.out.println("No piece at location (" + x + ", " + y + ")");
			return null;
		}

		else {
			Piece temp = pieceAt(x, y); 
			pieces[x][y] = null;
			return temp;
		}
	}

	public boolean canEndTurn() {
		if (movedPiece)
			return true;
		return false;

	}

	public void endTurn() {
		firePlaying = !firePlaying;
		movedPiece = false;
		hasSelected = false;
		if (pieceSelected != null) {
			pieceSelected.doneCapturing();
			pieceSelected = null;
		}

	}

	public String winner() {
		int countfire = 0, countwater = 0;
		for (int i = 0; i < 8; i += 1) {
			for (int j = 0; j < 8; j += 1) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire() == true)
						countfire += 1;
					else {
						countwater += 1;
					}
				}
			}
		}
		if (countwater == 0 && countfire == 0) 
			return "No One";
		else if (countwater == 0) 
			return "Fire";
		else if (countfire == 0) 
			return "Water";
		else {
			return null;
		}
	}
	

	public static void main(String[] args) {
		Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        b.place(new Piece(false, b, 3, 3, "pawn"), 3, 3);
        b.remove(3, 3);




		while (b.winner() == null) {
			for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

	                if (b.pieceAt(i,j) != null) {
	                	String name = "img/"; 
	                	if (b.pieceAt(i,j).isBomb())
	                		name += "bomb";
	                	else if (b.pieceAt(i,j).isShield())
	                		name += "shield";
	                	else name += "pawn";
	                	if (b.pieceAt(i,j).isFire())
	                		name += "-fire";
	                	else name += "-water";
	                	if (b.pieceAt(i,j).isKing())
	                		name += "-crowned";
	                	name += ".png";

	                	if (b.pieceAt(i,j) == b.pieceSelected) {
	                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	                		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
		                }
                    	StdDrawPlus.picture(i + .5, j + .5, name, 1, 1);
                	}
                }
	      
	        }

            if (StdDrawPlus.mousePressed()) {
                double mousex = StdDrawPlus.mouseX();
                double mousey = StdDrawPlus.mouseY();
                int x = (int) mousex;
                int y = (int) mousey;

                if (b.canSelect(x,y)) 
                	b.select(x,y);
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) 
            		b.endTurn();
            }

            StdDrawPlus.show(15);
   
		}
	}

	
}