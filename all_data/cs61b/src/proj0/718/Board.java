

public class Board {
	
	private static int N = 8;
	private Piece[][] boardPieces = new Piece[N][N];
	private String player = "fire";
	private boolean selectedPiece = false; //selected a piece to move
	private boolean selectedMove = false; //selected a square and executed move
	private int selectedX;
	private int selectedY;
	private Piece pSelected;

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if(pSelected != null && i == selectedX && j == selectedY){StdDrawPlus.setPenColor(StdDrawPlus.WHITE);}

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                Piece p = boardPieces[i][j];
                if(p != null)
                {
					String name = ".png";
					if (p.isKing()){
						name = "-crowned" + name;
					}
					if (p.isFire()){
						name = "-fire" + name;
					} else {
						name = "-water" + name;
					}
					if (p.isBomb()){
						name = "bomb" + name;
					} else if (p.isShield()){
						name = "shield" + name;
					} else {
						name = "pawn" + name;
					}
					name = "img/" + name;
					
					StdDrawPlus.picture(i + .5, j + .5, name, 1, 1);
		        }
		    }
        }
    }

	public static void main(String[] args) {

            	
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board gameBoard = new Board(false);

        while(gameBoard.winner() == null) {
			gameBoard.drawBoard(N);	
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();

                if(gameBoard.canSelect((int)x,(int)y))
                {
                	gameBoard.select((int)x,(int)y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {

                if(gameBoard.canEndTurn())
                {
                	gameBoard.endTurn();
                }
            }

        	StdDrawPlus.show(100);
        }
		gameBoard.drawBoard(N);	
		StdDrawPlus.setFont();
		StdDrawPlus.text(0,4,gameBoard.winner() + " wins!");
	}
	
	/* The constructor for Board. If shouldBeEmpty is true, 
	initializes an empty Board. Otherwise, initializes a Board 
	with the default configuration. Note that an empty Board 
	could possibly be useful for testing purposes.*/
	public Board (boolean shouldBeEmpty){
		if(!shouldBeEmpty){
			for (int i = 0; i < 4; i += 1){
				new Piece(true, this, 2 * i, 0, "pawn");
				new Piece(true, this, 1 + 2 * i, 1, "shield");
				new Piece(true, this, 2 * i, 2, "bomb");
				new Piece(false, this, 1 + 2 * i, 5, "bomb");
				new Piece(false, this, 2 * i, 6, "shield");
				new Piece(false, this, 1 + 2 * i, 7, "pawn");
			}
		}
	}

	/* Gets the piece at position (x, y) on the board, or null 
	if there is no piece. If (x, y) are out of bounds, returns null.*/
	public Piece pieceAt(int x, int y){
		if ((x >= 0 && x <= 7) && (y >= 0 && y <= 7)){
			if (boardPieces[x][y] != null){
				return boardPieces[x][y];
			}
		}
		return null;

	}

	/* Returns true if the square at (x, y) can be selected.*/
	public boolean canSelect(int x, int y){
		if (!((x >= 0 && x <= 7) && (y >= 0 && y <= 7))){
			return false;
		}
		if (pieceAt(x, y) != null){
			if ((player == "fire" && pieceAt(x, y).isFire()) || 
				(player == "water" && !pieceAt(x, y).isFire())){
				if (!selectedMove) {
					return true;
				}
			}
		} else {
			if (selectedPiece) {
				if (!selectedMove){
					if ((pSelected.isFire() || (!pSelected.isFire() && pSelected.isKing())) && 
						((x == selectedX + 1 && y == selectedY + 1) || 
						(x == selectedX - 1 && y == selectedY + 1))) {
							return true;
					} 
					else if ((!pSelected.isFire() || (pSelected.isFire() && pSelected.isKing())) && 
							((x == selectedX + 1 && y == selectedY - 1) || 
							(x == selectedX - 1 && y == selectedY - 1))) {
								return true;
					}
				}
				if (!selectedMove || (selectedMove && pSelected.hasCaptured())){
					if ((pSelected.isFire() || (!pSelected.isFire() && pSelected.isKing())) &&
							((x == selectedX + 2 && y == selectedY + 2 && pieceAt(selectedX + 1, selectedY + 1) != null && pSelected.isFire()!= pieceAt(selectedX + 1, selectedY + 1).isFire()) || 
							(x == selectedX - 2 && y == selectedY + 2 && pieceAt(selectedX - 1, selectedY + 1) != null && pSelected.isFire()!= pieceAt(selectedX - 1, selectedY + 1).isFire()))) {
								return true;
					}
					else if ((!pSelected.isFire() || (pSelected.isFire() && pSelected.isKing())) &&
							((x == selectedX + 2 && y == selectedY - 2 && pieceAt(selectedX + 1, selectedY - 1) != null && pSelected.isFire()!= pieceAt(selectedX + 1, selectedY - 1).isFire()) || 
							(x == selectedX - 2 && y == selectedY - 2 && pieceAt(selectedX - 1, selectedY - 1) != null && pSelected.isFire()!= pieceAt(selectedX - 1, selectedY - 1).isFire()))) {
								return true;
					}
				}
			}
		}
		return false; 
	}

	/*Selects the square at (x, y). This method assumes 
	canSelect(x,y) returns true.*/
	public void select(int x, int y){
		selectedX = x;
		selectedY = y;

		if (pieceAt(x, y) != null){
			pSelected = pieceAt(x, y);
			selectedPiece = true;
		} else {
			pSelected.move(x, y);
			//if capture is a bomb
			if(pieceAt(x,y)==null){
				pSelected = null;
				selectedPiece = false;
			}
			selectedMove = true;
		}
	}

	/* Places p at (x, y). If (x, y) is out of bounds or if p 
	is null, does nothing. If another piece already exists at (x, y), 
	p will replace that piece. */
	public void place(Piece p, int x, int y){
		if ((x >= 0 && x <= 7) && (y >= 0 && y <= 7) && (p != null)){
			if (pieceAt(x, y) != null) {
				remove(x, y);
			}
			boardPieces[x][y] = p;
		}
	}

	/* Executes a remove. Returns the piece that was removed. If the 
	input (x, y) is out of bounds, returns null and prints an appropriate 
	message. If there is no piece at (x, y), returns null and prints an 
	appropriate message.*/
	public Piece remove(int x, int y){
		if ((x >= 0 && x <= 7) && (y >= 0 && y <= 7)){
			Piece toRemove = pieceAt(x, y);
			if (toRemove == null){
				System.out.println("There is no piece at this place.");
			}
			boardPieces[x][y] = null;
			return toRemove;
		}
		System.out.println("You are trying to remove a piece out of bounds.");
		return null;
	}

	/* Returns whether or not the the current player can end their turn. 
	To be able to end a turn, a piece must have moved or performed a capture.*/
	public boolean canEndTurn(){
		return selectedMove;
	}

	/*Called at the end of each turn. Handles switching of players and 
	anything else that should happen at the end of a turn.*/
	public void endTurn(){
		selectedPiece = false;
		selectedMove = false;
		pSelected = null;
		if (player == "fire"){
			player = "water";
		} else {
			player = "fire";
		}
	}

	/*Returns the winner of the game. "Fire", "Water", "No one" 
	(tie/no pieces on the board), or null if the game is not yet over.*/
	public String winner() {
		boolean noOne = true;
		boolean fire = true;
		boolean water = true;
		for (int i = 0; i < 8; i += 1){
			for (int j = 0; j < 8; j += 1){
				if (boardPieces[i][j] != null) {
					if (boardPieces[i][j].isFire()){
						noOne = false;
						water = false;
					} else {
						noOne = false;
						fire = false;
					}
				}
			}
		}
		if (noOne) {
			return "No one";
		} else if (fire) {
			return "Fire";
		} else if (water) {
			return "Water";
		} else {
			return null;
		}
	}
}



