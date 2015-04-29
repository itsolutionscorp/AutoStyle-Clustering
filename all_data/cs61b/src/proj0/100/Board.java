public class Board {
// This is for updating the ag	
    private Piece[][] pieces;

    private int turnHolder; // Init as fire first
    private int playerStatus; 
    	// 0 = have not selected yet
    	// 1 = have selected one piece but have not moved it
    	// 2 = have selected one piece and have moved it
    private int selecteX;
    private int selecteY;// because we may select a null piece which cannot identify where itself is
    // private int flag;

	public static void main(String[] args) {
		// starts a GUI supported version of the game.

		// ------------------------------------ Initiate gameBoard ------------------------------------
		Board gameBoard = new Board(false);

		// ------------------------------------ Main loop ------------------------------------
		while(true) {

        StdDrawPlus.show(100);

		// ------------------------------------ Win condition ------------------------------------

		if (gameBoard.winner() != null) {
			return;
		}

		// ------------------------------------ Draw Board ------------------------------------
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
        }

        // ------------------------------------ Endturn condition ------------------------------------
        if (StdDrawPlus.isSpacePressed() && gameBoard.canEndTurn()) {
        	// System.out.println("End of turn!");
        	gameBoard.endTurn();
        } 

        // if (StdDrawPlus.isSpacePressed()) System.out.println("Pressed!!!!");

        // ------------------------------------ Highlight the selected piece ------------------------------------
    	if ((!(gameBoard.selecteX < 0 || gameBoard.selecteX >= 8 || gameBoard.selecteY < 0 || gameBoard.selecteY >= 8)) && (gameBoard.pieces[gameBoard.selecteX][gameBoard.selecteY] != null)) {
        	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        	StdDrawPlus.filledSquare(gameBoard.selecteX + .5, gameBoard.selecteY + .5, .5);
			StdDrawPlus.picture(gameBoard.selecteX+0.5, gameBoard.selecteY+0.5, gameBoard.imgName(gameBoard.pieces[gameBoard.selecteX][gameBoard.selecteY]), 1, 1);
        }


        // ------------------------------------ Draw all pieces and update if anyone is crowned------------------------------------
    	for (int i = 0; i < 8; i++) {
    		for (int j = 0; j < 8; j++) {
    			Piece p = gameBoard.pieces[i][j];
    			// System.out.println(p);
    			if (p != null) StdDrawPlus.picture(i+0.5, j+0.5, gameBoard.imgName(p), 1, 1); 
    		}
    	}


        // ------------------------------------ Game conditions ------------------------------------
            
    		if (StdDrawPlus.mousePressed()) {

    			gameBoard.infoSheet();

                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int preX = (int) x;
                int preY = (int) y;

                if (gameBoard.canSelect(preX, preY)) {
                	gameBoard.select(preX, preY);
                }
            }
		}
	}

	public Board(boolean shouldBeEmpty) {
		// The constructor for Board. If shouldBeEmpty is true, initializes an empty Board. 
		// Otherwise, initializes a Board with the default configuration. 
		// Note that an empty Board could possibly be useful for testing purposes.

		if (shouldBeEmpty) {
			initEmptyBoard();
		}
		else {
			initDefaultBoard();
		}
	}

	private void initEmptyBoard() {
		// initializes a empty board.
		pieces = new Piece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				pieces[i][j] = null;
			}
		}
		turnHolder = 0; // Indicate this is an inactive game;
		playerStatus = 0; // As stated above;
		selecteX = -1;
		selecteY = -1;
		// flag = 0;
	}

	private void initDefaultBoard() {
		// initializes a default board.
		pieces = new Piece[8][8];
		initPieces(8, 8, 1);
		turnHolder = 0; // Fire = 0 & water = 1;
		playerStatus = 0; // The start of one's turn;
		selecteX = -1;
		selecteY = -1;
		// flag = 1;
	}

	public Piece pieceAt(int x, int y) {
		// Gets the piece at position (x, y) on the board, or null if there is no piece. 
		// If (x, y) are out of bounds, returns null.
		if (boundExceed(x, y)) {
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		// Returns true if there is a piece the piece at (x, y) and it can be selected.
		// A piece may be selected if it is the corresponding player’s turn and one of the 
		// of the following is true:

		// The player has not selected a piece yet.
		// The player has selected a piece, but did not move it.

		// An empty square may be selected if one of the following is true:

		// During this turn, the player has selected a Piece which hasn’t moved yet 
		// and is selecting an empty spot which is a 
		// valid move for the previously selected Piece.

		// During this turn, the player has selected a Piece, captured, 
		// and has selected another valid capture destination. 
		// (You may select as many captures in a row as long as they 
		// are all valid and from the same piece.)

		// System.out.print("Can select "+"("+x+","+y+") when ");
		// System.out.println("selected pair is "+"("+selecteX+","+selecteY+") ?");
		// System.out.println("----------------------------------------\n");

		// if (selecteX == x && selecteY == y) return true;

		if (playerStatus == 2 && selecteX == -1 && selecteY == 1) return false;

		if (!boundExceed(x,y) && pieces[x][y] != null) {
			Piece p = pieces[x][y];
			if (p.side() == turnHolder && (playerStatus == 0 || playerStatus == 1))
				return true;
			else 
				return false;	
		} else if (!boundExceed(x, y)) {
			if (playerStatus == 1 && validMove(selecteX, selecteY, x, y))
				return true;
			else if (playerStatus == 2 && validMove(selecteX, selecteY, x, y))
				return true;
			else
				return false; 
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		// Returns true if the piece at (xi, yi) can either move to (xf, yf) 
		// or capture a piece thus jump to (xf, yf) in a valid fashion compatible with the rules.
		
		// infoSheet();
		// System.out.println(xi+"-"+yi+"-"+xf+"-"+yf+"\n");

		if (boundExceed(xi, yi) || boundExceed(xf, yf)) {
			return false;
		}

		if (pieces[xi][yi] == null) return false;

		if (pieces[xi][yi].isKing()) {
			if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1 && playerStatus == 1) {
				if (pieces[xf][yf] != null) 
					return false;
				else
					return true;
			} else if (Math.abs(xi - xf) == 2 && Math.abs(yf - yi) == 2 && (playerStatus == 1 || playerStatus == 2)) {
				int crossX = (xi + xf)/2;
				int crossY = (yi + yf)/2;
				Piece crossP = pieces[crossX][crossY];
				if (crossP == null) 
					return false;
				else if (crossP.side() != pieces[xi][yi].side())
					return true;
				else
					return false;
			}
			return false;
		} else {
			int dy;
			if (pieces[xi][yi].side() == 0) dy = 1;
			else dy = -1;

			if ((Math.abs(xi-xf) == 1) && (yf - yi == dy) && playerStatus == 1) {
				if (pieces[xf][yf] != null) 
					return false;
				else
					return true;
			} else if ((Math.abs(xi - xf) == 2) && (yf - yi == 2*dy) && (playerStatus == 1 || playerStatus == 2)) {
				int crossX = (xi + xf)/2;
				int crossY = (yi + yf)/2;
				Piece crossP = pieces[crossX][crossY];
				if (crossP == null) 
					return false;
				else if (crossP.side() != pieces[xi][yi].side())
					return true;
				else
					return false;
			}
			return false;
		}
	}

	private void choose(int x, int y) {
		if (!boundExceed(x, y)) {
			selecteX = x;
			selecteY = y;
		}
	}

	public void select(int x, int y) {
		// Selects the piece at (x, y) if possible. 
		// Optionally, it is recommended to color the background of the selected 
		// square white on the GUI via the pen color function. 
		// For any piece to perform a capture, that piece must have been selected first.

		// System.out.println("Select ("+x+","+y+")");

		    if (playerStatus == 0) {
                	choose(x, y);
                	playerStatus = 1;
			}

                	// ------------------------------------ status 1 selected but have not moved ------------------------------------

            else if (playerStatus == 1) {
                if (validMove(selecteX, selecteY, x, y)) {
               		Piece save_P = pieces[selecteX][selecteX];
              		pieces[selecteX][selecteY].move(x, y);
               		playerStatus = 2;
               		if (save_P != null && save_P.isBomb() && save_P.hasCaptured()) {
               			selecteX = -1;
               			selecteY = -1;               		}
               	} else {
              		choose(x, y);
                }
            }

					// ------------------------------------ status 2 selected & moved ------------------------------------

            else if (playerStatus == 2) {
                	if (validMove(selecteX, selecteY, x, y)) {
                		pieces[selecteX][selecteY].move(x, y);
               		}
               		choose(x, y);
            }
	}


	public void place(Piece p, int x, int y) {
	
	// Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. 
	// If p already exists in the current Board, first removes it from its initial position. 
	// If another piece already exists at (x, y), p will replace that piece. 
	// (This method is potentially useful for creating specific test circumstances.)
		
		// System.out.println("Put "+p+" in ("+x+","+y+")");

		if (!boundExceed(x, y) && p != null) {
			if (pieces[x][y] != null) {
				kickOutFromBoard(x, y);
			}
			pieces[x][y] = p;
			selecteX = x;
			selecteY = y;
		}
	}

	public Piece remove(int x, int y) {
		// Executes a remove. Returns the piece that was removed. 
		// If the input (x, y) is out of bounds, 
		// returns null and prints an appropriate message. 
		// If there is no piece at (x, y), 
		// returns null and prints an appropriate message.

		// System.out.println("Remove "+pieces[x][y]+" in ("+x+","+y+")");


		if (boundExceed(x, y)) {
			System.out.println("("+x+","+y+") out of bounds");
			return null;
		} else if (pieces[x][y] == null) {
			System.out.println("There's no piece at ("+x+","+y+");");
			return null;
		} else {
			Piece p = pieces[x][y];
			kickOutFromBoard(x, y);
			return p;
		}
	}

	public boolean canEndTurn() {
		// Returns whether or not the the current player can end their turn. 
		// To be able to end a turn, a piece must have moved or performed a capture.
		
		if (playerStatus == 2) return true;
		else return false;
	}

	public void endTurn() {
		// Called at the end of each turn. 
		// Handles switching of players and anything else that should happen at the end of a turn.

		switchHolder();
		playerStatus = 0;
		if (selecteX == -1 && selecteY == -1) return;
		if (pieces[selecteX][selecteY] != null)
			pieces[selecteX][selecteY].doneCapturing();
		selecteX = -1;
		selecteY = -1;
	}

	private void switchHolder() {
		if (turnHolder == 1) turnHolder = 0;
		else turnHolder = 1;
	}

	public String winner() {
		// Returns the winner of the game. 
		// "Fire", "Water", "No one" (tie / no pieces on the board), 
		// or null if the game is not yet over. 
		// Assume there is no stalemate situation in which the current player has 
		// pieces but cannot legally move any of them 
		// (In this event, just leave it at null). 
		// Determine the winner solely by the number of pieces belonging to each team.

		int fireNum = 0;
		int waterNum = 0;

		// if (flag == 0) {
		// 	flag = 1;	
		// 	return null;
		// }

		for (int i = 0; i<8; i++)
			for (int j = 0; j<8; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].side() == 0) fireNum+=1;
					else waterNum += 1;
				}
			}
		if (fireNum > waterNum && waterNum == 0) return "Fire";
			else if (waterNum > fireNum && fireNum == 0) return "Water";
			else if (waterNum == 0 && fireNum == 0) return "No one";
		return null;
	}

    private static String imgName(Piece p) {
    	// System.out.println(p);
    	String img = "img/";
    	if (p.isBomb()) {
    		img += "bomb";
    	} else if (p.isShield()) {
    		img += "shield";
    	} else {
    		img += "pawn";
    	}
    	
    	img += "-";
    	if (p.isFire()) img += "fire";
    		else img += "water";
    	if (p.isKing()) img += "-crowned";
    	img += ".png";
    	return img;
    }

    private void initPieces(int x, int y, int mode) {
    	if (mode == 1) {
    		for(int i = 0; i < x; i+=2) initSinglePiece(true, this, i, 0, "pawn");
    		for(int i = 1; i < x; i+=2) initSinglePiece(true, this, i, 1, "shield"); 
    		for(int i = 0; i < x; i+=2) initSinglePiece(true, this, i, 2, "bomb");
    		for(int i = 1; i < x; i+=2) initSinglePiece(false, this, i, y-1, "pawn");
    		for(int i = 0; i < x; i+=2) initSinglePiece(false, this, i, y-2, "shield"); 
    		for(int i = 1; i < x; i+=2) initSinglePiece(false, this, i, y-3, "bomb");    														    															   		
    	}
    }

    private void initSinglePiece(boolean isFire, Board b, int x, int y, String type) {
    	pieces[x][y] = new Piece(isFire, b, x, y, type);
    }

    private boolean boundExceed(int x, int y) {
    	return (x >= 8 || y >= 8 || x < 0 || y < 0);
    }

    private void kickOutFromBoard(int x, int y) {
    	pieces[x][y] = null;
    }

    private void infoSheet() {

		System.out.println("playerStatus = "+playerStatus);
		System.out.println("selecteX = "+selecteX);
		System.out.println("selecteY = "+selecteY);
		System.out.println("turnHolder = "+turnHolder);
		System.out.println("\n");

    }

}
