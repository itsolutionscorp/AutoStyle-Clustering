public class Board {
	private Piece[][] pieces;
	private int size;
	private boolean isEmpty;
	private boolean hasMoved = false;
	private String turn;
	private boolean hasSelected = false;
	private Piece selected_piece;
	private int selected_x, selected_y;
	private int numFirePieces;
	private int numWaterPieces;

	// Constructor, fire goes first
	public Board(boolean shouldBeEmpty) {
        int N = 8;
        this.size = N;
        this.pieces = new Piece[N][N];
        if (!shouldBeEmpty) {
        	placeInitialPieces(N);
        	countPieces();
        }
        this.isEmpty = shouldBeEmpty;
        this.turn = "Fire";
	}

	// return piece at a location, null if no piece
	public Piece pieceAt(int x, int y) {
		if (outOfBounds(x,y)) {
			return null; // Out of bounds 
		}
		return pieces[x][y];
	}

	// returns whether a square at (x,y) can be selected
	// true if correct side and piece
	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x,y);
		if (p != null) {
			if (!hasSelected && rightPiece_and_Side(p)) {
				return true;
			}
			if (hasSelected && p.side() != selected_piece.side()) {
				return false;
			}
			if (hasSelected && !hasMoved) {
				return true;
			}
		}
		if (p == null) {
			if (hasSelected && hasMoved && !selected_piece.hasCaptured()) {
				return false;
			}
			if (hasSelected && validMove(selected_x,selected_y,x,y)) {
				return true;
			}
			if (hasSelected && selected_piece.hasCaptured() && validMove(selected_x,selected_y,x,y)) {
				return true;
			}
			if (hasSelected && selected_piece.isKing() && canMultiCapture(selected_x,selected_y)) {
				return true;
			}
		}
		if (p == null && selected_piece != null && selected_piece.isKing()) {
			if (canMultiCapture(x,y)) {
				return true;
			}
			return false;
		}
		return false;
	}

	private boolean rightPiece_and_Side(Piece p) {
		if (turn.equals("Fire") && p.isFire()) {
			return true;
		} else if (turn.equals("Water") && !p.isFire()) {
			return true;	
		} else {
			return false;
		}
	}

	// return whether a piece at (xi,yi) can be moved to (xf,yf)
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (outOfBounds(xf, yf)) {
			return false;
		}
		if (is_Diagonal_1(xi,yi,xf,yf) && pieceAt(xf,yf) == null && !hasMoved) {
			return true;
		}
		if (is_Diagonal_2(xi,yi,xf,yf) && enemyPieceBetween(xi,yi,xf,yf)) {
			return true;
		}
		return false;
	}

	private boolean is_Diagonal_1(int xi, int yi, int xf, int yf) {
		if (selected_piece.isKing()) {
			return ((xf - xi == 1 && yf - yi == 1) || (xf - xi == -1 && yf - yi == 1) || (xf - xi == 1 && yf - yi == -1) || (xf - xi == -1 && yf - yi == -1));
		}
		if (selected_piece.isFire()) {
			return ((xf - xi == 1 && yf - yi == 1) || (xf - xi == -1 && yf - yi == 1));
		} else {
			return ((xf - xi == 1 && yf - yi == -1) || (xf - xi == -1 && yf - yi == -1));
		}
	}

	private boolean is_Diagonal_2(int xi, int yi, int xf, int yf) {
		if (selected_piece.isFire()) {
			return ((xf - xi == 2 && yf - yi == 2) || (xf - xi == -2 && yf - yi == 2));
		} else {
			return ((xf - xi == 2 && yf - yi == -2) || (xf - xi == -2 && yf - yi == -2));
		}
	}


	// should assume points are diagonal
	private boolean enemyPieceBetween(int xi, int yi, int xf, int yf) {
		int between_x = (int)(xi + (xf - xi)/2);
		int between_y = (int)(yi + (yf - yi)/2);
		Piece p = pieceAt(between_x, between_y);
		if (p != null && p.side() != selected_piece.side()) {
			// here lies the one and only problem...REST IN PEACE #rekt #35/36
			// I know there shouldn't be unnecessary comments but please this is important
			return true;
		}
		return false;
	}

	private boolean canMultiCapture(int x, int y) {
		int between_x_1 = x + 2;
		int between_x_2 = x - 2;
		int between_y_1 = y + 2;
		int between_y_2 = y - 2;
		Piece p_1 = pieceAt(between_x_1, between_y_1);
		Piece p_2 = pieceAt(between_x_1, between_y_2);
		Piece p_3 = pieceAt(between_x_2, between_y_1);
		Piece p_4 = pieceAt(between_x_2, between_y_2);
		if (p_1 != null || p_2 != null || p_3 != null || p_4 != null) {
			return true;
		}
		return false;
	}

	// select and highlight a square if it can be highlighted
	public void select(int x, int y) {
		// if different piece
		if (pieceAt(x,y) != null) {
			hasSelected = true;
			selected_x = x;
			selected_y = y;
			selected_piece = pieceAt(x,y);
		} else {
			// update selected piece if able to move
			// check if different square because mouse click too fast
			if (selected_x != x && selected_y != y) {
				selected_piece.move(x,y);
				this.hasMoved = true;
				// in case there is a capture, recount pieces
				countPieces();
				selected_x = x;
				selected_y = y;
			}
		}
	}

	private boolean outOfBounds(int x, int y) {
		return x >= size || y >= size || x < 0 || y < 0;
	}

	// places a piece p at (x,y)
	public void place(Piece p, int x, int y) {
		// checks if p is null and if (x,y) is out of bounds
		if (!outOfBounds(x,y) && p != null) {
			pieces[x][y] = p;
			countPieces(); // update number of pieces
		}
	}

	private void printPieces() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieceAt(j,i) != null) {
            		Piece p = pieceAt(j,i);
            		System.out.println(pieceType(p) + pieceSide(p) + " at " + j + "," + i);
            	}
            }
        }
	}

	// removes a piece from the square and returns the piece
	public Piece remove(int x, int y) {
		Piece temp;
		if (outOfBounds(x,y)) {
			System.out.println("("+x+","+y +") are out of bounds");
		}
		if (pieceAt(x,y) == null) {
			return null;
		} else {
			temp = pieceAt(x,y);
			pieces[x][y] = null;
		}
		return temp;
	}

	// returns whether a piece has been moved or captured during the turn
	public boolean canEndTurn() {
		countPieces();
		if (hasMoved) {
			return true;
		}
		if (selected_piece != null && selected_piece.hasCaptured()) {
			return true;
		}
		return false;
	}

	// calls canEndTurn and switches turns if it's true, sets hasMoved to false and
	// resets selected fields
	public void endTurn() {
		if (canEndTurn()) {
			this.turn = nextTurn(this.turn);
			reset_selected();
			this.hasMoved = false;
		}
	}

	private void reset_selected() {
		hasSelected = false;
		if (selected_piece != null) {
			selected_piece.doneCapturing();
		}
		selected_piece = null;
		selected_x = 0;
		selected_y = 0;
	}

	// switches turns
	private String nextTurn(String currTurn) {
		if (currTurn.equals("Fire")) {
			return "Water";
		} else {
			return "Fire";
		}
	}

	// returns side of piece
	private String pieceSide(Piece p) {
		if (p.side() == 1) {
			return "-water";
		} else {
			return "-fire";
		}
	}

	private void countPieces() {
		numWaterPieces = 0;
		numFirePieces = 0;
		for (int i = 0; i < size ; i += 1) {
			for (int j = 0; j < size; j+= 1) {
				Piece p = pieceAt(i,j);
				if (p != null) {
					if (p.side() == 1) {
						numWaterPieces += 1;
					} else {
						numFirePieces += 1;
					}
				}
			}
		}
	}

	private String pieceType(Piece p) {
		if (p.isBomb()) {
			return "bomb";
		}
		if (p.isShield()) {
			return "shield";
		}
		return "pawn";
	}

	private String add_if_Crown(Piece p) {
		if (p.isKing()) {
			return "-crowned";
		} else {
			return "";
		}
	}

	// returns winner
	public String winner() {
		if (numFirePieces != 0 && numWaterPieces != 0) {
			return null;
		}
		if (numFirePieces == 0 && numWaterPieces == 0) {
			return "No one";	
		}
		if (numWaterPieces == 0) {
			return "Fire";
		} 
		if (numFirePieces == 0) {
			return "Water";
		}
		return null;
	}


	private void drawPieces() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	Piece p = pieceAt(i,j);
            	if (p != null) {
        			if (p.isFire()) {
						StdDrawPlus.picture(i + .5, j + .5, "img/" + pieceType(p) + "-fire" + add_if_Crown(p) + ".png", 1, 1);
					} else {
						StdDrawPlus.picture(i + .5, j + .5, "img/" + pieceType(p) + "-water" + add_if_Crown(p) + ".png", 1, 1);
					}
                }
            }
        }
	}

	// places pieces in the initial configuration 
	private void placeInitialPieces(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j != 3 && j != 4 && (i + j) % 2 == 0) {
                	Piece p;
                	if (j <= 2) {
                		p = new Piece(true, this, i, j, initialBoardTypes(j));
                	} else {
                		p = new Piece(false, this, i, j, initialBoardTypes(j));
                	}
                	pieces[i][j] = p;
                }
            }
        }
	}

	// returns type of piece depending on row
	private String initialBoardTypes(int row) {
		String[] initial_types = new String[]{"pawn", "shield", "bomb", null, null, "bomb", "shield", "pawn"};
		return initial_types[row];
	}

	// draws the empty board
	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                if (hasSelected && selected_x == i && selected_y == j) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
	}

	public static void main(String[] args) {
		// initializes the board
		Board b = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        b.size = N;
        b.pieces = new Piece[N][N];
		b.drawBoard(b.size);

		if(!b.isEmpty) {
        	b.placeInitialPieces(b.size);
        }

        while(b.winner() == null) {
            b.drawBoard(b.size);
            b.drawPieces();
            if (StdDrawPlus.mousePressed()) {
                int x = (int)(StdDrawPlus.mouseX()); // citation: http://stackoverflow.com/questions/2143476/how-to-cast-a-double-to-an-int-in-java-by-rounding-it-down
                int y = (int)(StdDrawPlus.mouseY());
                if (b.canSelect(x,y)) {
                	System.out.println("can select" + x + " " + y);
                	b.select((int)(x), (int)(y)); 
            	}
            }            
            StdDrawPlus.show(15);
            if(StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
            	b.endTurn();
            }
        }
	}
}