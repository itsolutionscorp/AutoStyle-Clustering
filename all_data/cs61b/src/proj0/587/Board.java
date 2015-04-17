public class Board {


	private Piece[][] board;
	private int player;

	private boolean pieceMoved;
	private Piece selectedPiece;
	private int selectedPieceX;
	private int selectedPieceY;

	public Board(boolean shouldBeEmpty) {
		board = new Piece[8][8];
		player = 0;
		pieceMoved = false;
		selectedPiece = null;
		selectedPieceX = -1;
		selectedPieceY = -1;
		if(!shouldBeEmpty) {
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					if(j == 0 && j%2 == 0) board[i][j] = new Piece(true, this, i, j, "pawn");
					else if(j == 1 && i%2 == 1) board[i][j] = new Piece(true, this, i, j, "shield");
					else if(j == 2 && i%2 == 0) board[i][j] = new Piece(true, this, i, j, "bomb");
					else if(j == 5 && i%2 == 1) board[i][j] = new Piece(false, this, i, j, "bomb");
					else if(j == 6 && i%2 == 0) board[i][j] = new Piece(false, this, i, j, "shield");
					else if(j == 7 && i%2 == 1) board[i][j] = new Piece(false, this, i, j, "pawn");	
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if(x >= 0 && x < 8 && y >= 0 && y < 8) return board[x][y];
		else return null;
	}

	public void place(Piece p, int x, int y) {
		if(x >= 0 && x < 8 && y >= 0 && y < 8 && p != null) {
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					if(pieceAt(i, j) == p) remove(i, j);
				}
			}
			if(pieceAt(x, y) != null) remove(x, y);
			board[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if(x >= 0 && x < 8 && y >= 0 && y < 8) {
			if(pieceAt(x, y) != null) {
				Piece pieceToRemove = board[x][y];
				board[x][y] = null;
				return pieceToRemove;
			}
			else System.out.println("There is no piece there!");
		}
		else System.out.println("That's out of bounds!");
		return null;
	}

	public boolean canSelect(int x, int y) {
		if(pieceAt(x, y) != null) {
			if(pieceAt(x, y).side() == player && (selectedPiece == null || !pieceMoved)) return true;
		}
		else if(selectedPiece != null){
			if(!pieceMoved && validMove(selectedPieceX, selectedPieceY, x, y)) {
				return true;
			}
			else if(selectedPiece.hasCaptured() && validCaptureMove(selectedPieceX, selectedPieceY, x, y)) {
				return true;
			}
		}
		return false;
	}

	private boolean validMove(int x0, int y0, int x, int y) {
		if(validCaptureMove(x0, y0, x, y)) return true;
		if(pieceAt(x, y) == null && Math.abs(x-x0) == 1){
			if(pieceAt(x0, y0).isKing()) {
				if(Math.abs(y-y0) == 1) return true;
			}
			else if(pieceAt(x0, y0).isFire()) {
				if(y-y0 == 1) return true;
			} 
			else {
				if(y-y0 == -1) return true;
			}
		}
		return false;
	}

	private boolean validCaptureMove(int x0, int y0, int x, int y) {
		if(pieceAt(x, y) == null) {
			if(Math.abs(x-x0) == 2 && Math.abs(y-y0) == 2) {
				if(pieceAt((x+x0)/2, (y+y0)/2) != null && pieceAt((x+x0)/2, (y+y0)/2).side() != pieceAt(x0, y0).side()) {
					return true;
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if(pieceAt(x, y) != null) {
			selectedPiece = pieceAt(x, y);
		}
		else {
			selectedPiece.move(x, y);
			pieceMoved = true;
		} 
		selectedPieceX = x;
		selectedPieceY = y;
	}

	public boolean canEndTurn() {
		return pieceMoved;
	}

	public void endTurn() {
		pieceMoved = false;
		selectedPiece.doneCapturing();
		selectedPiece = null;
		selectedPieceX = -1;
		selectedPieceY = -1;
        player = switchPlayers();
	}

	private int switchPlayers() {
		return 1 - player;
	}

	public String winner() {
		if(stillKicking(0)) {
			if(!stillKicking(1)) return "Fire";
		}
		else {
			if(stillKicking(1)) return "Water";
			else return "No one";
		}
		return null;
	}

	private boolean stillKicking(int player) {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if(pieceAt(i, j) != null && pieceAt(i, j).side() == player) return true;
            }
        }
        return false;
	}

	private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(selectedPiece != null && i == selectedPieceX && j == selectedPieceY) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if(board[i][j] != null) {
                	String fileName = "";
                	if(board[i][j].isBomb()) fileName += "bomb";
                	else if(board[i][j].isShield()) fileName += "shield";
                	else fileName += "pawn";

                	if(board[i][j].isFire()) fileName += "-fire";
                	else fileName += "-water";

                    if(board[i][j].isKing()) fileName += "-crowned";

                    StdDrawPlus.picture(i + .5, j + .5, "img/" + fileName + ".png", 1, 1);
                }
            }
        }
    }

	public static void main(String[] args) {
		Board gameBoard = new Board(false);
		StdDrawPlus.setScale(0, 8);
		while(true) {
			if(StdDrawPlus.mousePressed()) {
				int x = (int)StdDrawPlus.mouseX();
				int y = (int)StdDrawPlus.mouseY();
				if(gameBoard.canSelect(x, y)) gameBoard.select(x, y);
			}
			if(StdDrawPlus.isSpacePressed()) {
				if(gameBoard.canEndTurn()) gameBoard.endTurn();
			} 

			gameBoard.drawBoard();

			StdDrawPlus.show(20);

			if(gameBoard.winner() != null) break;
		}
		System.out.println("The winner is " + gameBoard.winner());
	}

}