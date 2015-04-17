import static org.junit.Assert.assertTrue;
public class Board {
	private Piece[][] pieces;
	//true is for fire. false is for water - according to isFire(). fire makes the first move
	private boolean turn;  
	private int[] selectedCoord; 
	private Piece selectedPiece;  
	private static int N = 8;
	private boolean moved;  
	public Board (boolean shouldBeEmpty) {
		pieces = new Piece[N][N];
		turn = true; 
		moved = false; 
		selectedCoord = new int[2];
		selectedPiece = null; 
		if (shouldBeEmpty == false) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((j == 0) && (i%2 == 0)) {
						pieces[i][j] = new Piece(true, this, i, j, "pawn"); 
					}
					if ((j == 2) && (i%2==0)) {
						pieces[i][j] = new Piece(true, this, i, j, "bomb");
					}
					if ((j==6) && (i%2==0)) {
						pieces[i][j] = new Piece(false, this, i, j, "shield");
					}
					if ((j==1) && (i%2!=0)) {
						pieces[i][j] = new Piece(true, this, i, j, "shield");
					}
					if ((j==5) && (i%2!=0)) {
						pieces[i][j] = new Piece(false, this, i, j, "bomb");
					}
					if ((j==7) && (i%2!=0)) {
						pieces[i][j] = new Piece(false, this, i, j, "pawn");
					}
				}
			}
		}
		
	}
	
	public Piece pieceAt(int x, int y) {
		if ( x < N && x >= 0 && y < N && y >=0) {
			return pieces[x][y]; 
		}
		return null; 
	}

	public boolean canSelect(int x, int y) {
		if ((x < N) && (x >= 0) && (y < N) && (y >= 0)) {
			Piece possiblePiece = pieceAt(x,y);
			if (possiblePiece != null && possiblePiece.isFire() == turn && moved == false) {
				System.out.println("Can switch your selectedPiece to a piece on your side");
				return true; 
			} else if (selectedPiece != null){
				if (moved == false || selectedPiece.hasCaptured()) {
					if (moved == true && selectedPiece.hasCaptured() && selectedPiece.isBomb() == true) {
						return false; 
					}
					System.out.println("You haven't moved or you've captured (in the case of a multicapture");
					if (validMove(selectedCoord[0], selectedCoord[1], x, y)) {
						System.out.println("Check if this is a validMove");
						return true; 
					}
				}
			}
		}
		return false; 
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf) {
		int xDir = xf - xi;
		int yDir = yf - yi; 
		boolean isValid = false; 
		boolean tryCapt = false; 
		
		if (selectedPiece.isKing()) {
			if (Math.abs(xDir) == 1 && Math.abs(yDir) == 1) {
				isValid = true; 
			} else if (Math.abs(xDir) == 2 && Math.abs(yDir) == 2) {
				if (pieceAt(xf, yf) == null) {
					tryCapt = true; 
				}
			}
		} else {
				if (selectedPiece.isFire()) {
					if (Math.abs(xDir) == 1 && yDir == 1) {
						isValid = true; 
					} else if (Math.abs(xDir) == 2 && yDir == 2) {
						if (pieceAt(xf, yf) == null) {
							tryCapt = true; 
						}
							
					}
				} else {
					if (Math.abs(xDir) == 1 && yDir == -1) {
						isValid = true; 
					} else if (Math.abs(xDir) == 2 && yDir == -2) {
						if (pieceAt(xf, yf) == null) {
							tryCapt = true; 
						}
					}
				}
		}
		if (tryCapt) {
			Piece possible = pieceAt(xi + xDir/2, yi + yDir/2);
			if (possible != null && possible.isFire() != turn) {
				return true; 
			}
		}
		if (pieceAt(xf, yf) != null || selectedPiece.hasCaptured()) {
			isValid = false; 
		}
		return isValid; 
	}
	
	public void select(int x, int y) {
		if (selectedPiece != null && pieceAt(x, y) == null) {
			selectedPiece.move(x, y);
			moved = true;
		}
		updateSelect(x, y);	
	}
	private void updateSelect(int x, int y) {
		selectedCoord[0] = x;
		selectedCoord[1] = y;
		selectedPiece = pieces[x][y];
	}
	public void place(Piece p, int x, int y) {
		if ((x < N) && (x >= 0) && (y < N) && (y >= 0)) {
			if (p!= null) {
				pieces[x][y] = p; 
				selectedCoord[0] = x;
				selectedCoord[1] = y; 
			}
		}
	}

	public Piece remove(int x, int y) {
		if ((x >= N) || (y >= N) || (x < 0) || (y < 0)) {
			System.out.println("Location " + Integer.toString(x) + ", " 
					+ Integer.toString(y) + " is out of bounds.");
			return null;
		} 
		if (pieces[x][y] == null) {
			System.out.println("There is no piece to remove at location " 
					+ Integer.toString(x) + ", " + Integer.toString(y) + ".");
			return null; 
		}
		Piece removed = pieces[x][y];
		pieces[x][y] = null;
		System.out.println("Removed " + removed.isBomb()); 
		return removed; 
	}
	
	public boolean canEndTurn() {
		return moved; 
	}
	public void endTurn() {
		turn = !turn; 
		moved = false; 
		if (selectedPiece !=null) {
			selectedPiece.doneCapturing(); 
		}
	}
	public String winner() {
		int numFire = 0;
		int numWater = 0; 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						numFire++; 
					} else {
						numWater++; 
					}
				}
			}
		}
		System.out.print(numWater);
		System.out.print(numFire);
		if (numFire == 0 && numWater == 0) {
			return "No one";
		} else if (numFire == 0) {
			return "Water";
		} else if (numWater == 0) {
			return "Fire";
		}
		return null; 
	}
	private void drawBoard() {
		StdDrawPlus.setXscale(0, N);
	    StdDrawPlus.setYscale(0, N);
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (canSelect(i, j)) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j] != null) {
                	drawPiece(i, j);
        		} // end if
            }
        }
    }
	private void drawPiece (int x, int y) {
		//draw the picture
		String image = "img/";
		if (pieceAt(x, y).isShield()) {
			image = image + "shield-";
		} else if (pieceAt(x, y).isBomb()) {
			image = image + "bomb-";
		} else {
			image = image + "pawn-";
		}
		if (pieceAt(x, y).isFire()) {
			image = image + "fire";
		} else {
			image = image + "water";
		}
		if (pieceAt(x, y).isKing()) {
			image = image + "-crowned";
		}
		image = image + ".png";
    	StdDrawPlus.picture(x + .5, y + .5, image, 1, 1);
	}
	private void onClick() {
		int x = (int) StdDrawPlus.mouseX();
		int y = (int) StdDrawPlus.mouseY(); 
		if (canSelect(x,  y)) {
			select(x, y);	
			System.out.println("select used");	
		} else {
			System.out.println("select failed");
		}
		drawBoard(); 
	}
	private void onSpace() {
		if (canEndTurn()) {
			System.out.println("you canEndTurn");
				endTurn(); 
				System.out.println("Turn ended");
		}

	}
	public static void main(String args[]) {
		//starts a GUI supported version of the game.
		Board board = new Board(false);
		board.drawBoard();
			
		while (true) {
			if (StdDrawPlus.mousePressed()) {
				board.onClick(); 

			}	
			if (StdDrawPlus.isSpacePressed()) {
				board.onSpace(); 
				if (board.winner() != null) {
					break; 
				}
			}
		}
		StdDrawPlus.show(2);  
	}
}
//