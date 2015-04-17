

public class Board {
	private boolean[][] havePiece;
	private Piece[][] piece;
	private boolean player;
	private int selectedX;
	private int selectedY;
	private Piece removed;
	private boolean move;
	private String picture;
	private boolean hasCaptured;
	private int fireCount;
	private int waterCount;
	private boolean over;
	private static double xSelected, ySelected;


	public Board(boolean shouldBeEmpty) {
		/* The constructor for Board. If shouldBeEmpty is true, 
		  initializes an empty Board. Otherwise, 
		 initializes a Board with the default configuration. 
		  Note that an empty Board could possibly be useful for 
		testing purposes.
		*/
		selectedX = -1;
		selectedY = -1;
		move = true;
		piece = new Piece[8][8];
		havePiece = new boolean[8][8];
		player = true;
		removed = null;
		hasCaptured = false;
		waterCount = 0;
		fireCount = 0;
		over = false;
		int N = 8;

		if(!shouldBeEmpty) {	
			for (int i = 0; i < N; i++) {
				if(i % 2 == 0) {
					havePiece[i][0] = true;
					havePiece[i][2] = true;
					havePiece[i][6] = true;
					place(new Piece(true, this, i, 0, "pawn"), i, 0);
					place(new Piece(true, this, i, 2, "bomb"), i, 2);
					place(new Piece(false, this, i, 6, "shield"), i, 6);
				}
				else {
					havePiece[i][1] = true;
					havePiece[i][5] = true;
					havePiece[i][7] = true;
					place(new Piece(true, this, i, 1, "shield"), i, 1);
					place(new Piece(false, this, i, 5, "bomb"), i, 5);
					place(new Piece(false, this, i, 7, "pawn"), i, 7);
				}
			}
}
	}

	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            	else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	Piece p = pieceAt(i, j);
            	if(p != null) {
            		StdDrawPlus.picture(i + .5, j + .5, getPicture(p), 1, 1);
            		if((p == pieceAt((int) selectedX, (int) selectedY))) {
                        StdDrawPlus.filledSquare((int) selectedX + .5, (int) selectedY + .5, .5);
            			StdDrawPlus.picture(i + .5, j + .5, getPicture(p), 1, 1);
            		}
            	} 
            }
        }
	}

	private String getPicture(Piece p) {
		if(p.isBomb()) {
			if(p.isFire()) {
				if(p.isKing()) picture = "img/bomb-fire-crowned.png";
				else picture = "img/bomb-fire.png";
			} 
			else {
				if(p.isKing()) picture = "img/bomb-water-crowned.png";
				else picture = "img/bomb-water.png";
			}
		}
		else if (p.isShield()) {
			if(p.isFire()) {
				if(p.isKing()) picture = "img/shield-fire-crowned.png";
				else picture = "img/shield-fire.png";
			} 
			else {
				if(p.isKing()) picture = "img/shield-water-crowned.png";
				else picture = "img/shield-water.png";
			}
		}
		else {
			if(p.isFire()) {
				if(p.isKing()) picture = "img/pawn-fire-crowned.png";
				else picture = "img/pawn-fire.png";
			} 
			else {
				if(p.isKing()) picture = "img/pawn-water-crowned.png";
				else picture = "img/pawn-water.png";
			}
		}
		return picture;
	}

	public Piece pieceAt(int x, int y) {
		/* Gets the piece at position (x, y) on the board, 
		or null if there is no piece. If (x, y) are out of bounds, 
		returns null.
		*/
		if(x >= 0 && x <= 7 && y >= 0 && y <= 7 && havePiece[x][y]) {
			return piece[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null) {
			if (pieceAt(x, y).isFire() == player) {
				return true;
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		/* Returns true if the piece at (xi, yi) 
		can e ither move to (xf, yf) or capture to (xf, yf), 
		strictly from a geometry/piece-race point of view. 
		validMove does not need to take into consideration 
		whose turn it is or if a move has already been made, 
		but rather can.
		*/

		if(xf >= 0 && xf <= 7 && yf >= 0 && yf <= 7) {
			if(!piece[xi][yi].isKing()) {
					if ((((Math.abs(xf - xi) == 1) && ((yf - yi) == 1) && player) || 
						((Math.abs(xf - xi) == 1) && ((yi - yf) == 1) && !player)) && !hasCaptured) {
						move = false;
						return true;
					} 
					else if (((Math.abs(xf - xi) == 2) && ((yf - yi) == 2) 
						&& havePiece[xi + (xf - xi)/2][yi + (yf - yi)/2] 
						&& !piece[xi + (xf - xi)/2][yi + (yf - yi)/2].isFire() && player) ||
						((Math.abs(xf - xi) == 2) && ((yi - yf) == 2) 
						&& havePiece[xi + (xf - xi)/2][yi + (yf - yi)/2] 
						&& piece[xi + (xf - xi)/2][yi + (yf - yi)/2].isFire() && !player) && move) {
						return true;
					}		
			}
			else {
					if (((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)) && !hasCaptured) {
						move = false;
						return true;
					}
				
					else if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2) 
						&& havePiece[xi + (xf - xi)/2][yi + (yf - yi)/2] 
						&& (!piece[xi + (xf - xi)/2][yi + (yf - yi)/2].isFire() && player) ||
						(piece[xi + (xf - xi)/2][yi + (yf - yi)/2].isFire() && !player) && move) {
						return true;
					}
			}
		}
		return false;
	}


	public void select(int x, int y) {
		/* Selects the piece at (x, y) if possible. 
		Optionally, it is recommended to color 
		the background of the selected square white on the 
		GUI via the pen color function. For any piece to 
		perform a capture, that piece must have been selected first.
		*/
		if(canSelect(x, y) && move) {
			selectedX = x;
			selectedY = y;
		}
		else {
			if(pieceAt(selectedX, selectedY) != null 
				&& validMove(selectedX, selectedY, x, y) && pieceAt(x, y) == null) {
					piece[selectedX][selectedY].move(x, y);
					hasCaptured = true;
					selectedX = x;
					selectedY = y;
			}
		}
	}


	public void place(Piece p, int x, int y) {
		/* Places p at (x, y). If (x, y) is out of bounds, 
		does nothing. If another piece already exists at 
		(x, y), p will replace that piece. (This method is 
		potentially useful for creating specific test circumstances.)
		*/
		if(x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			piece[x][y] = p;
			havePiece[x][y] = true;
			if(p.isFire()) {
				fireCount += 1;
			}
			else {
				waterCount += 1;
			}
		}
	}


	public Piece remove(int x, int y) {
		/* Executes a remove. Returns the piece that 
		was removed. If the input (x, y) is out of bounds, 
		returns null and prints an appropriate message. 
		If there is no piece at (x, y), returns null and 
		prints an appropriate message.
		*/
		if(x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("This is out of bound!");
		}
		else if (pieceAt(x, y) == null) {
			System.out.println("There is no piece at (" + Integer.toString(x) + ", " + Integer.toString(y) + ").");
			return null;
		}
		else if(pieceAt(x, y) != null || piece[x][y].hasCaptured()) {
			move = piece[x][y].hasCaptured();
			removed = piece[x][y];
			if(pieceAt(x, y).isBomb()) move = false;
			havePiece[x][y] = false;
			piece[x][y] = null;

			if(removed.isFire()) fireCount -= 1;
			else waterCount -= 1;
			//System.out.println(fireCount);
			//System.out.println(waterCount);
			return removed;
		}
		return null;
	}

	public boolean canEndTurn() {
		/* Returns whether or not the the current player can 
		end their turn. To be able to end a turn, 
		a piece must have moved or performed a capture.
		*/
		if(removed != null) {
			if(move = false || removed.hasCaptured()) {
				selectedX = (int) xSelected;
				selectedY = (int) ySelected;
				return true;
			}
		}
		return false;
	}

	public void endTurn() {
		/*  Called at the end of each turn. 
		Handles switching of players and anything 
		else that should happen at the end of a turn.
		*/
		player = !player;
		removed = null;
		move = true;
		hasCaptured = false;
		selectedX = -1;
        selectedY = -1;   
	}

	public String winner() {
		/* Returns the winner of the game. "Fire", "Water", "No one" 
		(tie / no pieces on the board), or null if the game is not yet over. 
		Assume there is no stalemate situation in which the current 
		player has pieces but cannot legally move any of them 
		(In this event, just leave it at null). 
		Determine the winner solely by the number of pieces belonging 
		to each team.
		*/
		if(fireCount == 0 || waterCount == 0) {
	        over = true;
		}
		if(over) {
			if(fireCount > waterCount) {
				return "Fire";
			}
			else if(fireCount < waterCount) {
				return "Water";
			}
			else if (fireCount == waterCount) {
				return "No one";
			}
		}
		return null;
	}

	public static void main(String[] args) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false);

        while(board.winner() == null) {
        	board.drawBoard(N);
        	if(StdDrawPlus.mousePressed()) {
        		xSelected = StdDrawPlus.mouseX();
        		ySelected = StdDrawPlus.mouseY();
        		board.select((int) xSelected, (int) ySelected);
        	}
        	if(StdDrawPlus.isSpacePressed()) {
        		if(board.canEndTurn()) {
        			board.endTurn();
        		}
        	}
        	StdDrawPlus.show(100);
        }
        board.drawBoard(N);
        StdDrawPlus.show(100);
        System.out.println(board.winner());
    }
}
