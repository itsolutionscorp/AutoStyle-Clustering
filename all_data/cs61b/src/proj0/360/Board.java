public class Board {
	private Piece[][] pieces;
	private String player;
	private boolean hasSelected;
	private boolean hasMoved;
	private Piece selectedPiece;
	private int selectedX;
	private int selectedY;
	private int waterWinnings;
	private int fireWinnings;
	private boolean gameOver;

	public Board(boolean shouldBeEmpty) {
		hasSelected = false;
		hasMoved = false;
		gameOver = false;
		pieces = new Piece[8][8];
		player = "Fire";
		if (!shouldBeEmpty)
			initPieces();
	}

	private void initPieces() {
		Piece p = null;
		for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
			if (i % 2 == 0) {
				if (j == 0) 
					place(new Piece(true, this, i, j, "pawn"), i, j);
				if (j == 2)
					place(new Piece(true, this, i, j, "bomb"), i, j);
				if (j == 6) 
					place(new Piece(false, this, i, j, "shield"), i, j);
			}
			else if (i % 2 != 0) {
				if (j == 1)
					place(new Piece(true, this, i, j, "shield"), i, j);
				if (j == 5)
					place(new Piece(false, this, i, j, "bomb"), i, j);
				if (j == 7)
					place(new Piece(false, this, i, j, "pawn"), i, j);
			}
		}
		}
	}

	public Piece pieceAt(int x, int y) {
		if ((x > 7) || (y > 7))
			return null;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((x == i) && (y == j))
					return pieces[i][j];
			}
		}
		return null;
	}


	public void place(Piece p, int x, int y) {
		if ((p == null) || (x > 7) || (y > 7))
			return;

		if (pieceAt(x, y) != null)
			remove(x, y);

		pieces[x][y] = p;
	}


	public Piece remove(int x, int y) {
		if ((x > 7) || (y > 7)) {
			return null;
		}

		Piece p = pieceAt(x, y);

		if (p == null) {
			return null;
		}

		pieces[x][y] = null;

		return p;
	}


	public boolean canSelect(int x, int y) {
		Boolean squareHasPiece = false;
		Piece piece = pieceAt(x, y);

		if (piece != null)
			squareHasPiece = true;

		// moving to capture
		if (hasSelected && !squareHasPiece && captureAvailable(x, y))
			return true;

		// attempting to make an invalid move when you've already moved
		if (hasMoved)
			return false;

		// clicking blank squares without having selected a piece
		if (!squareHasPiece && !hasSelected)
			return false;

		// selecting a piece to move, must be your own piece
		if (squareHasPiece) {
			if ((piece.isFire() && (player == "Fire")) || 
				(!piece.isFire() && (player == "Water")))
				return true;
		}

		// selecting a square to place the piece
		if (!squareHasPiece && hasSelected) {
			if (!selectedPiece.isKing() && 
				((selectedPiece.isFire() && (y < selectedY)) ||
				 (!selectedPiece.isFire() && (y > selectedY))))
				return false;
			else if ((Math.abs(x - selectedX) == 1) && (Math.abs(y - selectedY) == 1))
				return true;
		}

		return false;
	}

	private boolean captureAvailable(int x, int y) {
		Piece capturePiece = null;

		if (!selectedPiece.isKing() && 
			((selectedPiece.isFire() && (y < selectedY)) ||
			(!selectedPiece.isFire() && (y > selectedY)))) 
			return false;

		if (!(Math.abs(x - selectedX) == 2) || !(Math.abs(y - selectedY) == 2))
			return false;

		if (y > selectedY) {
			if (x > selectedX) 
				capturePiece = pieceAt(selectedX + 1, selectedY + 1);
			else if (x < selectedX)
				capturePiece = pieceAt(selectedX - 1, selectedY + 1);
		}
		else if (y < selectedY) {
			if (x > selectedX) 
				capturePiece = pieceAt(selectedX + 1, selectedY - 1);
			else if (x < selectedX)
				capturePiece = pieceAt(selectedX - 1, selectedY - 1);
		}

		if ((capturePiece != null) &&
			((selectedPiece.isFire() && !capturePiece.isFire()) ||
			(!selectedPiece.isFire() && capturePiece.isFire())))
			return true;

		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			selectedPiece = pieceAt(x, y);
			selectedX = x;
			selectedY = y;
			hasSelected = true;
		}	
		else if (hasSelected) {
			addExplosionWinnings(x, y);

			selectedPiece.move(x, y);

			if (!selectedPiece.isBomb() && selectedPiece.hasCaptured()) {
				selectedX = x;
				selectedY = y;

				if (player == "Fire")
					fireWinnings += 1;
				else
					waterWinnings += 1;

				selectedPiece.doneCapturing();
			}

			if (selectedPiece.isBomb()) {
				hasSelected = false;
				selectedPiece = null; 
			}
			hasMoved = true;
		}		
	}

	private void addExplosionWinnings(int x, int y) {
		if (!selectedPiece.isBomb())
			return;

		if (player == "Fire")
			waterWinnings += 1;
		else
			fireWinnings += 1;

		Piece[] surroundingPieces = new Piece[8];
		surroundingPieces[0] = pieceAt(x-1, y+1);
		surroundingPieces[1] = pieceAt(x, y+1);
		surroundingPieces[2] = pieceAt(x+1, y+1);
		surroundingPieces[3] = pieceAt(x+1, y);
		surroundingPieces[4] = pieceAt(x+1, y-1);
		surroundingPieces[5] = pieceAt(x, y-1);
		surroundingPieces[6] = pieceAt(x-1, y-1);
		surroundingPieces[7] = pieceAt(x-1, y);

		for (Piece piece : surroundingPieces) {
			if ((piece != null) && !piece.isShield()) {
				if ((player == "Fire") && !piece.isFire())
					fireWinnings += 1;
				else if ((player == "Water") && piece.isFire())
					waterWinnings += 1;
			}
		}
	}

	public boolean canEndTurn() {
		return hasMoved;
	}

	private String other(String player) {
		if (player.equals("Fire"))
			return "Water";
		return "Fire";
	}

	public void endTurn() {
		if (winner() != null) {
			gameOver = true;
			return;
		}
		hasSelected = false;
		hasMoved = false;
		selectedPiece = null;
		player = other(player);
	}

	public String winner() {
		if ((fireWinnings >= 12) && (waterWinnings >= 12))
			return "No one";

		if (fireWinnings >= 12)
			return "Fire";

		if (waterWinnings >= 12)
			return "Water";

		// still pieces on the board
		for (Piece[] pieceList : pieces) {
		for (Piece piece : pieceList) {
			if (piece != null) {
				return null;
			}
		}
		}

		return "No one";
	}

	private void drawPiece(Piece p, int x, int y) {
		if (p == null)
			return;
		String img = "";
		if (p.isFire()) {
			if (p.isShield())
				img = "img/shield-fire";
			else if (p.isBomb())
				img = "img/bomb-fire";
			else 
				img = "img/pawn-fire";
		}
		else if (!p.isFire()) {
			if (p.isShield())
				img = "img/shield-water";
			else if (p.isBomb())
				img = "img/bomb-water";
			else 
				img = "img/pawn-water";
		}
		if (p.isKing())
			img += "-crowned";
		StdDrawPlus.picture(x + .5, y + .5, img + ".png", 1, 1);
	}

    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) 
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else                  
                	StdDrawPlus.setPenColor(StdDrawPlus.BOOK_LIGHT_BLUE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                drawPiece(pieces[i][j], i, j);
            }
        }
    }

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        
       	Board b = new Board(false);

        while (!b.gameOver) {
        	b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int x = ((int) StdDrawPlus.mouseX());
                int y = ((int) StdDrawPlus.mouseY());
                if (b.canSelect(x, y)){
                	b.select(x, y);
					StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
		  			StdDrawPlus.filledSquare(x + .5, y  + .5, .5);
		  			b.drawPiece(b.selectedPiece, x, y);
                }
          	}  
          	if (StdDrawPlus.isSpacePressed()) {
          		if (b.canEndTurn())
          			b.endTurn();
        	}
            StdDrawPlus.show(100);
        }
    }

 }

