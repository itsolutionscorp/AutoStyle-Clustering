
public class Board {
	private boolean[][] pieces;
	//Private variable to hold all the actual pieces on the game board.
	private Piece[][] boardPieces;
	//Private variable to check whether a piece has moved.
	private boolean hasMoved = false;
	private int N = 8;
	//Coordinates of selected piece, makes background WHITE
	private int selectedX = -1;
	private int selectedY = -1;
	//Coordinates of where the selected piece would move
	private int moveToX = -1;
	private int moveToY = -1;
	//Player: fire --> 1, water --> -1 (to be used as multipliers
	private int player = 1;
	//Number of pieces for each player. When one of them hits 0, the other player wins.
	private int firePieces = 0;
	private int waterPieces = 0;
	//Determines whether the player has committed a move involving just one place movement (no jump/capture).
	private boolean oneMove = false;

	public Board(boolean shouldBeEmpty) {
        pieces = new boolean[N][N];
        boardPieces = new Piece[N][N];
		if (!shouldBeEmpty) {
        	for (int i = 0; i < N; i ++) {
        		for (int j = 0; j < N; j ++) {
        			if ((i + j) % 2 == 0) {
        				if (j == 0) {
        					place(new Piece(true, this, i, j, "pawn"), i, j);
        				} else if (j == 1) {
        					place(new Piece(true, this, i, j, "shield"), i, j);
        				} else if (j == 2) {
        					place(new Piece(true, this, i, j, "bomb"), i, j);
        				} else if (j == N - 1) {
        					place(new Piece(false, this, i, j, "pawn"), i, j);
        				} else if (j == N - 2) {
        					place(new Piece(false, this, i, j, "shield"), i, j);
        				} else if (j == N - 3) {
        					place(new Piece(false, this, i, j, "bomb"), i, j);
        				}
        			}
        		}
        	}
        }
	}
	
	public Piece pieceAt(int x, int y) {
		//Checks out of bounds
		if (x < 0 || x > (N - 1) || y < 0 || y > (N - 1)) {
			return null;
		} else if (!pieces[x][y]) {
			return null;
		} else {
			return boardPieces[x][y];
		}
	}
	
	public boolean canSelect(int x, int y) {
		if (pieces[x][y] && ((selectedX == -1 && selectedY == -1) || (!hasMoved))) {
			if ((player == 1 && boardPieces[x][y].isFire()) || (player == -1 && !boardPieces[x][y].isFire())) {
				return true;
			}
		} else {
			if (selectedX > -1 && !oneMove) {
				if (pieceAt(selectedX, selectedY) != null) {
					if (boardPieces[selectedX][selectedY].hasCaptured()) {
						if ((x - selectedX == 2 || selectedX - x == 2) && (y - selectedY == 2 || selectedY - y == 2)) {
							return validMove(selectedX, selectedY, x, y);
						}
					} else {
						return validMove(selectedX, selectedY, x, y);
					}
				}
			}
		}
		return false;
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (!boardPieces[xi][yi].isKing()) { //piece is not king
			if ((xf - xi == 1 || xf - xi == -1) && yf - yi == (player * 1)) { //move of 1 spot
				if (!pieces[xf][yf]) { //if no piece is at that spot
					return true;
				}
			}
			if ((xf - xi == 2 || xf - xi == -2) && yf - yi == (player * 2)) { //move of 2 spots
				if (!pieces[xf][yf] && pieces[(xf - xi) / 2 + xi][(yf - yi) / 2 + yi]) {
					if ((player == 1 && !boardPieces[(xf - xi) / 2 + xi][(yf - yi) / 2 + yi].isFire()) || (player == -1 && boardPieces[(xf - xi) / 2 + xi][(yf - yi) / 2 + yi].isFire())) {
						return true;
					}
				}
			}
		} else {
			if ((xf - xi == 1 || xf - xi == -1) && (yf - yi == 1 || yf - yi == -1)) {
				if (!pieces[xf][yf]) { //if no piece is at that spot
					return true;
				}
			}
			if ((xf - xi == 2 || xf - xi == -2) && (yf - yi == 2 || yf - yi == -2)) {
				if (!pieces[xf][yf] && pieces[(xf - xi) / 2 + xi][(yf - yi) / 2 + yi]) {
					if ((player == 1 && !boardPieces[(xf - xi) / 2 + xi][(yf - yi) / 2 + yi].isFire()) || (player == -1 && boardPieces[(xf - xi) / 2 + xi][(yf - yi) / 2 + yi].isFire())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void select(int x, int y) {
		if ((selectedX == -1) || (pieces[x][y] && !hasMoved)) {
			selectedX = x;
			selectedY = y;
		} else {
			moveToX = x;
			moveToY = y;
			if (!(selectedX == x && selectedY == y)) {
	        	if (selectedX - x == 1 || x - selectedX == 1) {
	        		oneMove = true;
	        	}
	        	boardPieces[selectedX][selectedY].move(moveToX, moveToY);
	        	//Re-Initialization
	        	selectedX = moveToX;
	        	selectedY = moveToY;
	        	moveToX = -1;
	        	moveToY = -1;
			}
		}
	}

	public void place(Piece p, int x, int y) {
		pieces[x][y] = true;
		boardPieces[x][y] = p;
		if (p.isFire()) {
			firePieces += 1;
		} else {
			waterPieces += 1;
		}
	}
	
	public Piece remove(int x, int y) {
		if (x < 0 || x > (N - 1) || y < 0 || y > (N - 1)) {
			System.out.println("Out of bounds.");
			return null;
		} else if (pieceAt(x, y) == null) {
			System.out.println("No Piece here.");
			return null;
		} else {
			hasMoved = true;
			Piece p = pieceAt(x, y);
			if (p.isFire()) {
				firePieces -= 1;
			} else {
				waterPieces -= 1;
			}
			pieces[x][y] = false;
			boardPieces[x][y] = null;
			return p;
		}
	}
	
	public boolean canEndTurn() {
		if (hasMoved) {
			return true;
		} else {
			return false;
		}
	}
	
	//Don't know how to switch players
	public void endTurn() {
    	selectedX = -1;
    	selectedY = -1;
    	moveToX = -1;
    	moveToY = -1;
    	oneMove = false;
    	hasMoved = false;
		player = 0 - player;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieces[i][j]) {
            		boardPieces[i][j].doneCapturing();
            	}
            }
		}
	}
	
	//Uses Piece methods to determine identity of Piece p
	private String determinePieceType(Piece p) {
		if (p.isShield()) {
			return "shield";
		} else if (p.isBomb()) {
			return "bomb";
		} else {
			return "pawn";
		}
	}
	
    private void drawBoard(int N) {
    	//Updating Boolean Pieces[][]
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			if (pieceAt(i, j) != null) {
    				pieces[i][j] = true;
    			} else {
    				pieces[i][j] = false;
    			}
    		}
    	}
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
                if (i == selectedX && j == selectedY && pieces[i][j]) {
        			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        			StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j]) {
                	if ((boardPieces[i][j].isFire()) && (determinePieceType(boardPieces[i][j]).equals("pawn"))) {
                		if (boardPieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                		} else {
                    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                		}
                	} else if ((boardPieces[i][j].isFire()) && (boardPieces[i][j].isShield())) {
                		if (boardPieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                		} else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                		}	
                	} else if ((boardPieces[i][j].isFire()) && (boardPieces[i][j].isBomb())) {
                		if (boardPieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                		} else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                		}
                	} else if ((!boardPieces[i][j].isFire()) && (determinePieceType(boardPieces[i][j]).equals("pawn"))) {
                		if (boardPieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                		} else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                		}
                	} else if ((!boardPieces[i][j].isFire()) && (boardPieces[i][j].isShield())) {
                		if (boardPieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                		} else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                		}
                	} else if ((!boardPieces[i][j].isFire()) && (boardPieces[i][j].isBomb())) {
                		if (boardPieces[i][j].isKing()) {
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                		} else {
                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                		}
                	}
                }
            }
        }
    }
    
    public String winner() {
    	if (waterPieces == 0 && firePieces == 0) {
    		return "No one";
    	} else if (firePieces == 0) {
    		return "Water";
    	} else if (waterPieces == 0) {
    		return "Fire";
    	} else {
    		return null;
    	}
    }
    
    public static void main(String[] args) {
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
    	Board board = new Board(false);
    	
        while(board.winner() == null) {
            StdDrawPlus.setXscale(0, board.N);
            StdDrawPlus.setYscale(0, board.N);
            board.drawBoard(board.N);
            //Mouse Pressed
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int) x, (int) y)) {
                    board.select((int) x, (int) y);
                }
            }
            //Space Pressed
            if (StdDrawPlus.isSpacePressed()) {
            	if (board.canEndTurn()) {
            		board.endTurn();
            	}
            }
            StdDrawPlus.show(100);
        }
        System.out.println(board.winner());
    }
}
