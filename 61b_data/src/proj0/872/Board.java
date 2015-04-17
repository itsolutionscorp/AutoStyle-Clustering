public class Board {
	private Piece[][] boardSpaces = new Piece[8][8];
	private int whoseTurn = 0;
	private boolean selected = false;
	private boolean moved = false;
	private Piece selectedPiece;

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty)
			initializePieces();
	}

	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7)
			return null;
		return boardSpaces[x][y];
	}

	public boolean canSelect(int x, int y) {
		Piece tempPiece = pieceAt(x, y);
		if (tempPiece != null) {
			if (whoseTurn == tempPiece.side() && !moved)
				return true;
		} else {
			if (selected && validMove(getIndex(selectedPiece), new int[] {x, y}))
				return true;
		}
		return false;
	}

	private int[] getIndex(Piece tempPiece) {
		for (int icount = 0; icount < 8; icount += 1){
			for (int icount2 = 0; icount2 < 8; icount2 += 1) {
				if (boardSpaces[icount][icount2] == tempPiece){
					return new int[] {icount, icount2};
				}
			}
		}
		return null;
	}

	private boolean validMove(int[] current, int[] future) {
		int xChange = future[0] - current[0];
		int yChange = future[1] - current[1];
		int xdir, ydir;
		if (xChange > 0)
			xdir = 1;
		else
			xdir = -1;
		if (yChange > 0)
			ydir = 1;
		else
			ydir = -1;
		if (whoseTurn == 0 && ydir<0 && !pieceAt(current[0], current[1]).isKing())
			return false;
		if (whoseTurn == 1 && ydir>0 && !pieceAt(current[0], current[1]).isKing())
			return false;
		if (moved) {
			if (Math.abs(xChange) == 2 && Math.abs(yChange) == 2  && pieceAt(current[0]+xdir, current[1]+ydir) != null)
				return true;
		} else {
			if (Math.abs(xChange) == 1 && Math.abs(yChange) == 1)
				return true;
			else if (Math.abs(xChange) == 2 && Math.abs(yChange) == 2  && pieceAt(current[0]+xdir, current[1]+ydir) != null)
				return true;
		}
		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) == null){
			selectedPiece.move(x, y);
			if (selectedPiece.isBomb() && selectedPiece.hasCaptured()){
				selected = false;
				moved = true;
				return;
			}
			moved = true;
		} else {
			selectedPiece = pieceAt(x, y);
			selected = true;
		}
	}

	private void checkKing() {
		int n;
		if (whoseTurn == 0)
			n = 7;
		else
			n = 0;
		for(int i = 0; i < 8; i+=1) {
			Piece temp = pieceAt(i, n);
			if (temp != null && temp.side() == whoseTurn && !temp.isKing()) {
				String type = "pawn";
				if (temp.isBomb())
					type = "bomb";
				else if (temp.isShield())
					type = "shield";
				place(new Piece(temp.isFire(), this, i, n, type), i, n);
				selectedPiece = pieceAt(i, n);
			}
		}
	}

	public Piece remove(int x, int y) {
		if (x > 7 || y > 7) {
			System.out.println("selected area is not in the board");
			return null;
		}
		Piece temp = pieceAt(x, y);
		if (temp == null) {
			System.out.println("selected square contains no piece");
			return null;
		}
		boardSpaces[x][y] = null;
		return temp;
	}

	public void place(Piece temp, int x, int y) {
		if (temp != null) {
			boardSpaces[x][y] = temp;
		}
		boardSpaces[x][y] = temp;
	}

	public boolean canEndTurn() {
		return moved;
	}

	public void endTurn() {
		whoseTurn = (whoseTurn+1)%2;
		selected = false;
		moved = false;
		selectedPiece.doneCapturing();
		selectedPiece = null;
	}

	public String winner() {
		int firePieces = 0;
		int waterPieces = 0;
		for (int icount = 0; icount < boardSpaces.length; icount+=1) {
    		for (int icount2 = 0; icount2 < boardSpaces[icount].length; icount2+=1) {
        		if (boardSpaces[icount][icount2] != null) {
        			if (boardSpaces[icount][icount2].isFire())
        				firePieces+=1;
        			else
        				waterPieces+=1;
        		}
    		}
		}
		if (firePieces > 0 && waterPieces > 0) {
			return null;
		} else {
			if (firePieces == waterPieces)
				return "No one";
			if (firePieces > waterPieces)
				return "Fire";
			return "Water";
		}
	}

	private void initializePieces() {
		for (int i = 0; i < 8; i+=2) {
			boardSpaces[i][0] = new Piece(true, this, i, 0, "pawn");
		}
		for (int i = 1; i < 8; i+=2) {
			boardSpaces[i][7] = new Piece(false, this, i, 7, "pawn");
		}
		for (int i = 1; i < 8; i+=2) {
			boardSpaces[i][1] = new Piece(true, this, i, 1, "shield");
		}
		for (int i = 0; i < 8; i+=2) {
			boardSpaces[i][6] = new Piece(false, this, i, 6, "shield");
		}
		for (int i = 0; i < 8; i+=2) {
			boardSpaces[i][2] = new Piece(true, this, i, 2, "bomb");
		}
		for (int i = 1; i < 8; i+=2) {
			boardSpaces[i][5] = new Piece(false, this, i, 5, "bomb");
		}
	}

	private void drawBoard() {
		for (int icount = 0; icount < 8; icount+=1) {
    		for (int icount2 = 0; icount2 < 8; icount2+=1) {
        		if ((icount+icount2)%2 == 0)
        			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        		else
        			StdDrawPlus.setPenColor(StdDrawPlus.RED);
        		StdDrawPlus.filledSquare(icount+.5, icount2+.5, .5);
    		}
		}
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		if (selected) {
			int[] temp = getIndex(selectedPiece);
			StdDrawPlus.filledSquare(temp[0]+.5, temp[1]+.5, .5);
		}
	}

	private void drawPieces() {
		for (int icount = 0; icount < boardSpaces.length; icount+=1) {
    		for (int icount2 = 0; icount2 < boardSpaces[icount].length; icount2+=1) {
        		if (boardSpaces[icount][icount2] != null) {
        			String name;
        			if (boardSpaces[icount][icount2].isBomb())
        				name = "bomb";
        			else if (boardSpaces[icount][icount2].isShield())
        				name = "shield";
        			else 
        				name = "pawn";
        			if (boardSpaces[icount][icount2].isFire())
        				name += "-fire";
        			else
        				name += "-water";
        			if (boardSpaces[icount][icount2].isKing())
        				name += "-crowned";
					StdDrawPlus.picture(icount + .5, icount2 + .5, "img/" + name + ".png", 1, 1);
        		}
    		}
		}
	}

	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		boolean gameEnd = false;
		Board gameBoard = new Board(false);
		while (!gameEnd) {
			gameBoard.drawBoard();
			gameBoard.drawPieces();
			if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (gameBoard.canSelect((int) x,(int) y)){
                	gameBoard.select((int) x,(int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()){
            	if (gameBoard.canEndTurn())
            		gameBoard.endTurn();
            }
            if (gameBoard.winner() != null){
            	System.out.println(gameBoard.winner());
            	return;
            }
			StdDrawPlus.show(50);
		}


	}
}