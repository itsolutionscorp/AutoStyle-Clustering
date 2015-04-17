public class Board {
	/** Board class will handle the Board operations of proj0.
	 *  @author David Dominguez Hooper - Finished on 2/14/15 - 3:14AM
	 *  [Do not modify this file.]
	 */

    private Piece gamePieces[][];
    private int currentPlayer;
    private Piece currentPiece;
    private boolean currentPieceSelected;
    private int selectedPieceX;
    private int selectedPieceY; 
    private boolean pieceMoved;
    private boolean gameInProgress;

	public Board(boolean shouldBeEmpty) {
		this.pieceMoved = false;
		this.gameInProgress = true;
		this.gamePieces = new Piece[8][8];
		this.currentPlayer = 0;
		if(!shouldBeEmpty) {
			this.createPieces();
		}
	}
	public Piece pieceAt(int x, int y) {
		if (this.outOfBounds(x, y) || this.gamePieces[x][y] == null) {
			return null;
		}
		else {
			return this.gamePieces[x][y];
		}
	}
	public boolean canSelect(int x, int y) {
		if (this.outOfBounds(x, y)) {
			return false;
		}
		else if (this.gamePieces[x][y] != null) {
			return (this.gamePieces[x][y].side() == this.currentPlayer) && ((this.currentPieceSelected == false) || (this.currentPieceSelected && this.pieceMoved == false));
		}
		else {
			if (this.currentPieceSelected && this.pieceMoved == false  && this.validMove(this.selectedPieceX, this.selectedPieceY, x, y)) {
				return true;
			}
			else {
				return this.currentPieceSelected && this.currentPiece.hasCaptured() && this.validMove(this.selectedPieceX, this.selectedPieceY, x, y);
			}
		}
	}
	public void select(int x, int y) {
		if (this.gamePieces[x][y] == null) {
			this.currentPiece.move(x, y);
			this.pieceMoved = true;
		}
		this.currentPiece = this.pieceAt(x, y);
		this.selectedPieceX = x;
		this.selectedPieceY = y;
		this.currentPieceSelected = true;

		if(this.currentPiece.isBomb() && this.currentPiece.hasCaptured()) {
			this.remove(x, y);
		}

	}
	public void place(Piece p, int x, int y) {
		if (this.outOfBounds(x, y) || p == null) {
			return;
		}
		else {
			this.gamePieces[x][y] = p;
		}
	}
	public Piece remove(int x, int y) {
		if (this.outOfBounds(x, y)) {
			System.out.println("Tried to remove a piece that was out of bounds at (" + x + ", " + y + ").");
			return null;
		}
		else if (this.gamePieces[x][y] == null) {
			System.out.println("Tried to remove a piece that was not there at (" + x + ", " + y + ").");
			return null;
		}
		else {
			Piece pointer = this.gamePieces[x][y];
			this.gamePieces[x][y] = null;
			return pointer;
		}
	}
	public boolean canEndTurn() {
		if (this.currentPiece == null) {
			return false;
		}
		else if (this.pieceMoved || this.currentPiece.hasCaptured()) {
			return true;
		}
		else {
			return false;
		}
	}
	public void endTurn() {
		if (this.currentPlayer == 0) {
			this.currentPlayer = 1;
		}
		else {
			this.currentPlayer = 0;
		}
		this.currentPieceSelected = false;
		this.pieceMoved = false;
		if (this.currentPiece != null) {
			this.currentPiece.doneCapturing();
			this.currentPiece = null;
		}
		if (this.winner() != null) {
			this.gameInProgress = false;
		}
	}
	public String winner() {
		int[] playerPiecesLeft = this.countPlayerPiecesLeft();
		if (playerPiecesLeft[0] == 0 && playerPiecesLeft[1] == 0) {
			return "No one";
		}
		else if (playerPiecesLeft[0] == 0) {
			return "Water";
		}
		else if (playerPiecesLeft[1] == 0) {
			return "Fire";
		}
		else {
			return null;
		}
	}
	private void startBoardGame() {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        while(this.gameInProgress) {
	        this.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (this.canSelect(x, y)) {
                	this.select(x, y);
                }
            }  
            if (StdDrawPlus.isSpacePressed() && this.pieceMoved) {
	            if (this.canEndTurn() && this.pieceMoved) {
	            	this.endTurn();
	            }
			}
	        StdDrawPlus.show(25);
	    }
	}
    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {                  
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                if (this.currentPiece != null && i == selectedPieceX && j == selectedPieceY){
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                } 
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                Piece piecePointer = this.gamePieces[i][j];
                if (piecePointer != null) {
                    StdDrawPlus.picture(i + .5, j + .5, this.getIcon(piecePointer), 1, 1);
                }
            }
        }
    }
    private String getIcon(Piece a){
    	String iconLocation = "img/";

    	if (a.isBomb()){
    		iconLocation += "bomb";
    	}
    	else if (a.isShield()) {
    		iconLocation += "shield";
    	}
    	else {
    		iconLocation += "pawn";
    	}

    	if (a.isFire()) {
    		iconLocation += "-fire";
    	}
    	else {
    		iconLocation += "-water";
    	}

    	if (a.isKing()){
    		iconLocation += "-crowned";
    	}
    	iconLocation += ".png";
    	return iconLocation;
    }
    private void createPieces() {
    	for(int i = 0; i < 8; i += 2) {
    		gamePieces[i][0] = new Piece(true, this, i, 0, "pawn"); //fire pawns
    		gamePieces[i+1][7] = new Piece(false, this, i+1, 7, "pawn"); //water pawns
    		gamePieces[i+1][1] = new Piece(true, this, i+1, 1, "shield"); //fire shields
    		gamePieces[i][6] = new Piece(false, this, i, 6, "shield"); //water shields
    		gamePieces[i][2] = new Piece(true, this, i, 2, "bomb"); //fire bombs
    		gamePieces[i+1][5] = new Piece(false, this, i+1, 5, "bomb"); //water bombs
    	}
    }
	private boolean outOfBounds(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return true;
		}
		else {
			return false;
		}
	}
	private int[] countPlayerPiecesLeft() {
		int[] totalPlayerPiecesForEachPlayer = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (this.gamePieces[i][j] != null && this.gamePieces[i][j].side() == 0) {
            		totalPlayerPiecesForEachPlayer[0] += 1;
            	}
            	else if (this.gamePieces[i][j] != null && this.gamePieces[i][j].side() == 1) {
            		totalPlayerPiecesForEachPlayer[1] += 1;
            	}
            }
        }
        return totalPlayerPiecesForEachPlayer;
	}
	private boolean correctDirectionMove(int verticalMove) {
		if (this.currentPiece.isFire() && verticalMove > 0) {
			return true;
		}
		else if (!this.currentPiece.isFire() && verticalMove < 0) {
			return true;
		}
		else {
			return false;
		}
	}
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (this.pieceAt(xf, yf) != null) {
			return false;
		}
		int horizontalMove = xf - xi;
		int verticalMove = yf - yi;
		int inBetweenPieceX = xi;
		int inBetweenPieceY = yi;
		if (this.pieceMoved == false && (Math.abs(verticalMove) == 1 || Math.abs(horizontalMove) == 1)) {
			if (this.currentPiece.isKing() && ((Math.abs(verticalMove) == 0 && Math.abs(horizontalMove) == 1) || (Math.abs(verticalMove) == 1 && Math.abs(horizontalMove) == 0))) {
				return true;
			}
			else if (Math.abs(verticalMove) == 1 && Math.abs(horizontalMove) == 1) {	
				if (this.currentPiece.isKing()){
					return true;
				}
				else{
					return correctDirectionMove(verticalMove);
				}
			}
			else {
				return false;
			}
		}
		else if (Math.abs(verticalMove) == 2 || Math.abs(horizontalMove) == 2) {
			boolean isKingAndLateralCaptureMove = (this.currentPiece.isKing() && (((Math.abs(verticalMove) == 2) && (Math.abs(horizontalMove) == 0)) || ((Math.abs(verticalMove) == 0) && (Math.abs(horizontalMove) == 2))));
			if (Math.abs(verticalMove) == 2 && Math.abs(horizontalMove) == 2) {
				if (this.currentPiece.isKing()) {
					if (horizontalMove > 0 && verticalMove > 0) {
						inBetweenPieceX = xi + 1;
						inBetweenPieceY = yi + 1;
					}
					else if (horizontalMove > 0 && verticalMove < 0) {
						inBetweenPieceX = xi + 1;						
						inBetweenPieceY = yi - 1;
					}
					if (horizontalMove < 0 && verticalMove > 0) {
						inBetweenPieceX = xi - 1;
						inBetweenPieceY = yi + 1;
					}
					else if (horizontalMove < 0 && verticalMove < 0) {
						inBetweenPieceX = xi - 1;
						inBetweenPieceY = yi - 1;
					}
				}
				else {
					if (this.currentPiece.isFire()) { 
						inBetweenPieceY = yi + 1;
					}
					else {
						inBetweenPieceY = yi - 1;
					}
					if (horizontalMove > 0) {
						inBetweenPieceX = xi + 1;
					}
					else {
						inBetweenPieceX = xi - 1;
					}
				}
			}
			else if (isKingAndLateralCaptureMove) {
				if (xf == xi && verticalMove > 0) {
					inBetweenPieceY = yi + 1;
				}
				else if (xf == xi && verticalMove < 0) {
					inBetweenPieceY = yi - 1;
				}
				if (yf == yi && horizontalMove > 0) {
					inBetweenPieceX = xi + 1;
				}
				else if (yf == yi && horizontalMove < 0) {
					inBetweenPieceX = xi - 1;
				}
			}
			Piece possibleCapture = this.pieceAt(inBetweenPieceX, inBetweenPieceY);
			if (possibleCapture != null) {
				boolean isCaptureOfOppositeSide = ((possibleCapture.isFire() && !this.currentPiece.isFire()) || (!possibleCapture.isFire() && this.currentPiece.isFire()));
				if (Math.abs(verticalMove) == 2 && Math.abs(horizontalMove) == 2 && isCaptureOfOppositeSide) {
					if (this.currentPiece.isKing()){
						return true;
					}
					else {
						return correctDirectionMove(verticalMove);
					}
				}
				else if (isKingAndLateralCaptureMove && isCaptureOfOppositeSide) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	private boolean canMultiCapture(int xi, int yi, int xf, int yf) {
		if (Math.abs(xf - xi) == 2 || Math.abs(yf - yi) == 2) {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (!(i == 0 && j == 0) && this.pieceAt(xi + i, yi + j) != null ) {
						if (this.validMove(xi, yi, xf, yf)) {
							return true;
						}
					}
				}
			}
			return false;
		}
		else
			return false;
	}
	public static void main(String[] args) {
		Board gameBoard = new Board(false);
		gameBoard.startBoardGame();
	    return;
	}
}