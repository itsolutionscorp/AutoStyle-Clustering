//Final Submission Version
//Author: Jared Sun

public class Board {

	private Piece[][] pieces = new Piece[8][8];
	private boolean shouldBeEmpty = true;
	private boolean p1Turn = true;
	private boolean p2Turn = false;
	private boolean hasMoved = false;
	private boolean selectedPiece = false;
	private Piece theSelectedPiece = null;

	public static void main(String[] args) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board b = new Board(false);

		while (b.winner() == null) {
			b.drawBoard(N);

			if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int x1 = (int) x;
                int y1 = (int) y;
                if (b.canSelect(x1, y1)) {
                	b.select(x1, y1);
                	b.highlight(x1, y1);
                }
			}
        

            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }            
            StdDrawPlus.show(80);
        }
		
		b.drawBoard(N);
		System.out.println("The winner is: " + b.winner());
	}

	private void highlight(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		if (pieceAt(x, y) != null) {
			StdDrawPlus.picture(x+ .5, y + .5, getPath(pieceAt(x, y)), 1, 1);
		}
	}

	/* taken from StdDrawDemo */
	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (!(shouldBeEmpty)) {
                	if (pieceAt(i, j) != null) {
                    	StdDrawPlus.picture(i + .5, j + .5, getPath(pieceAt(i, j)), 1, 1);
                    }
                }
            }
        }
    }

    private void placePieces(int N, Board b) {
    	pieces = new Piece[N][N];
		for (int i = 0; i < (N-1); i += 2) {
			pieces[i][0] = new Piece(true, b, i, 0, "pawn");
			pieces[i][2] = new Piece(true, b, i, 2, "bomb");
			pieces[i][6] = new Piece(false, b, i, 6, "shield");
		}

		for (int i = 1; i < N; i += 2) {
			pieces[i][1] = new Piece(true, b, i, 1, "shield");
			pieces[i][5] = new Piece(false, b, i, 5, "bomb");
			pieces[i][7] = new Piece(false, b, i, 7, "pawn");
		}
    }

    private String getPath(Piece p) {
    	String bleh;
    	if (p.isShield())
    		bleh = "shield";
    	else if (p.isBomb())
    		bleh = "bomb";
    	else bleh = "pawn";
    	String path = "img/" + bleh + "-";
    	if (p.isFire()) {
    		path += "fire";
    	} else {
    		path += "water";
    	}
    	if (p.isKing())
    		path += "-crowned";
    	path += ".png";

    	return path;
    }

	public Board(boolean shouldBeEmpty) {

		this.shouldBeEmpty = shouldBeEmpty;
		if (!(shouldBeEmpty)) {
			System.out.printf("board initialized, should not be empty%n");
			placePieces(8, this);
		} else 
			System.out.printf("board initialized, should be Empty%n");
	}

	public Piece pieceAt(int x, int y) {
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0))
			return null;
		if (pieces[x][y] != null) {
			// System.out.printf("identified a piece at (%d, %d).%n", x, y);
			return pieces[x][y];
		}
		// System.out.printf("did not find a piece at (%d, %d).%n", x, y);
		return null;
	}

	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);

		System.out.println("canSelect called.");


		if (p != null) {
			System.out.printf("Piece at (%d, %d) is not null.%n", x, y);
			if (!this.selectedPiece || (this.selectedPiece && !this.hasMoved)) {
				if ((this.p1Turn && p.isFire()) || this.p2Turn && !p.isFire()) {
					System.out.printf("Piece at (%d, %d) can be selected.%n", x, y);
					return true;
				}
			}
		} else {
			System.out.printf("Square at (%d, %d) is null.%n", x, y);
			if ((this.selectedPiece) && (!this.hasMoved)) {
				if (validMove(this.theSelectedPiece, x, y)) {
					System.out.printf("Square at (%d, %d) can be selected.%n", x, y);
					return true;
				}	
			} else if ((this.selectedPiece) && (this.theSelectedPiece.hasCaptured())) {
				if (validMove(this.theSelectedPiece, x, y)) {
					System.out.printf("Square at (%d, %d) can be selected.%n", x, y);
					return true;
				}
			}
		}

		System.out.println("Cannot select, returning false");
		return false;
	}

	private boolean validMove(Piece p, int x, int y) {

		System.out.println("validMove has been called.");
		int px = 0;
		int py = 0;
		boolean pieceFound = false;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieceAt(i,j) == p) {
					pieceFound = true;
					px = i;
					py = j;
				}
			}
		}

		if (!pieceFound) {
			return false;
		}

		if ((x > -1) && (y > -1) && (x < 8) && (y < 8)
			 && (this.pieceAt(x, y) == null)) 
		{

			//Regular Movement

			if (this.theSelectedPiece.hasCaptured() == false) {
				if ((p.isFire()) && (!p.isKing())) {
					if ((y == py + 1) && (Math.abs(x - px) == 1)) {
						System.out.println("this is a valid move.");
						return true;
					}
				} else if ((!p.isFire()) && (!p.isKing())) {
					if ((y == py - 1) && (Math.abs(x - px) == 1)) {
						System.out.println("this is a valid move.");
						return true;
					}
				} else if (p.isKing()) {
					if ((Math.abs(y - py) == 1) && (Math.abs(x - px) == 1)) {
						System.out.println("this is a valid move.");
						return true;
					}
				}
			}

			//Capturing
			Piece captured = this.pieceAt(((x + px) / 2), ((y + py) / 2));
			if ((captured != null) && (p.side() != captured.side())) {
				return true;
			}
		}


		System.out.println("this is not a valid move.");
		return false;
	}

	public void select(int x, int y) {
		
		Piece p = pieceAt(x, y);
		
		if (p != null) {
			this.selectedPiece = true;
			this.theSelectedPiece = p;
			System.out.printf("piece selected at (%d %d)%n", x, y);
		} else {
			System.out.printf("square selected at (%d %d)%n", x, y);
			this.theSelectedPiece.move(x, y);
			this.hasMoved = true;
		}
	}

	public void place(Piece p, int x, int y) {
		if (!((x > 7) || (y > 7) || (x < 0) || (y < 0) || (p == null))) {
			if ((pieces[x][y]) != null)
				remove(x, y);
			this.pieces[x][y] = p;
			this.shouldBeEmpty = false;
			System.out.printf("Piece placed at: (%d, %d) %n", x, y);
		}

	}

	public Piece remove(int x, int y) {
		System.out.printf("remove called at (%d, %d)%n", x, y);
		if (pieceAt(x, y) != null) {
			Piece temp = pieceAt(x, y);
			this.pieces[x][y] = null;
			return temp;
		} else if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
			System.out.println("index out of bounds");
			return null;
		} else 
			System.out.println("there is no piece there");
			return null;
	}

	public boolean canEndTurn() {

		System.out.println("canEndTurn called");
		return (this.hasMoved);
	}

	public void endTurn() {
		System.out.println("endTurn called");
		if (this.p1Turn) {
			this.p1Turn = false;
			this.p2Turn = true;
			System.out.println("It is now p2's Turn.");
		} else {
			this.p2Turn = false;
			this.p1Turn = true;
			System.out.println("It is now p1's Turn.");
		}
		this.hasMoved = false;
		this.selectedPiece = false;

		this.theSelectedPiece.doneCapturing();
		this.theSelectedPiece = null;
		
	}

	public String winner() {
		int fireCount = 0;
		int waterCount = 0;
		Piece p;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				p = pieceAt(i, j);
				if (p != null) {
					if (p.isFire()) {
						fireCount += 1;
					} else {
						waterCount += 1;
					}
				}
			}
		}

		if ((fireCount > 0) && (waterCount == 0)) {
			System.out.println("Fire wins.");
			return "Fire";
		} else if ((waterCount > 0) && (fireCount == 0)) {
			System.out.println("Water wins.");
			return "Water";
		} else if ((waterCount == 0) && (fireCount == 0)) {
			System.out.println("No one wins.");
			return "No one";
		} else {
			return null;
		}

	}


}