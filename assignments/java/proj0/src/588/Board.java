public class Board {
	private int N;
	private Piece[][] pieces;
	private int currentTurn;
	private Piece selectedPiece;
	private boolean didMove;


	public Board(boolean shouldBeEmpty) {
		N = 8;
		pieces = new Piece[N][N];

		if (!shouldBeEmpty) {
			for (int i = 0; i < N; i+=2) {
				pieces[i][0] = new Piece(true, this, i, 0, "pawn");
				pieces[i+1][1] = new Piece(true, this, i+1, 1, "shield");
				pieces[i][2] = new Piece(true, this, i, 2, "bomb");

				pieces[i+1][N-1] = new Piece(false, this, i+1, N-1, "pawn");
				pieces[i][N-2] = new Piece(false, this, i, N-2, "shield");
				pieces[i+1][N-3] = new Piece(false, this, i+1, N-3, "bomb");
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (inBounds(x, y)) {
			return pieces[x][y];
		}
		return null;
		
	}

	public boolean canSelect(int x, int y) {
		if (!inBounds(x, y)) {
			return false;
		}
		if (pieceAt(x, y) != null) {
			return (pieceAt(x, y).side() == currentTurn)
						&& (selectedPiece == null || !didMove);
		} else {

			return (selectedPiece != null && !didMove && validMove(selectedPiece, x, y))
				|| (selectedPiece != null && selectedPiece.hasCaptured() &&
					validCaptureMove(selectedPiece, x, y));
		}
	}

	private int[] extractCoordinates(Piece p) {
		int x = 0;
		int y = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (p == pieces[i][j]) {
					x = i;
					y = j;
				}
			}
		}
		int[] coords = {x, y};
		return coords;
	}

	private boolean inBounds(int x, int y) {
		return x >=0 && x < N && y >= 0 && y < N;
	}


	private boolean existsEnemyPieceInBetween(Piece p, int xf, int yf) {
		int[] coords = extractCoordinates(p);
		int xi = coords[0];
		int yi = coords[1];
		int xtarget = Math.abs(xf-xi) + Math.min(xi, xf) - 1;
		int ytarget = Math.abs(yf-yi) + Math.min(yi, yf) - 1;

		Piece inBetween = pieceAt(xtarget, ytarget);

		return inBetween != null && inBetween.side() != p.side();
	}	

	private boolean validMove(Piece p, int xf, int yf) {
		return validSimpleMove(p, xf, yf) || validCaptureMove(p, xf, yf);
	}

	private boolean validCaptureMove(Piece p, int xf, int yf) {
		int[] coords = extractCoordinates(p);
		int xi = coords[0];
		int yi = coords[1];
		boolean value = true;
		int xdistance = xf-xi;
		int ydistance = yf-yi;
		int necessaryDistance = 0;

		if (Math.abs(xdistance) == 2 && Math.abs(ydistance) == 2) {
			necessaryDistance = 2;
			return existsEnemyPieceInBetween(p, xf, yf) &&
				validMoveHelp(p, xdistance, ydistance, necessaryDistance);
		} else {
			return false;
		}

	}

	private boolean validMoveHelp(Piece p, int xdistance, int ydistance, int necessaryDistance) {
		boolean value = true; //can make true

		if (p.isKing()) {
			value = Math.abs(ydistance) == necessaryDistance && value;
		} else if (p.side() == 0) {
			value = ydistance == necessaryDistance && value;
		} else {
			value = ydistance == -necessaryDistance && value;
		}
		return value;
	}

	private boolean validSimpleMove(Piece p, int xf, int yf) {
		int[] coords = extractCoordinates(p);
		int xi = coords[0];
		int yi = coords[1];
		boolean value = true;
		int xdistance = xf-xi;
		int ydistance = yf-yi;
		int necessaryDistance = 0;

		if (Math.abs(xdistance) == 1 && Math.abs(ydistance) == 1) {
			necessaryDistance = 1;
			return validMoveHelp(p, xdistance, ydistance, necessaryDistance);
		} else {
			return false;
		}

	}

	public void select(int x, int y) {
		
		if (pieceAt(x, y) == null) {
			selectedPiece.move(x, y);
			didMove = true;
		} else {
			selectedPiece = pieceAt(x, y);
		}
		

	}

	public void place(Piece p, int x, int y) {
		if (p != null && inBounds(x, y)) {
			if (pieceAt(x,y) != null) {
				remove(x, y);
			}
			pieces[x][y] = p;

		}

	}

	public Piece remove(int x, int y) {
		if (inBounds(x,y)) {
			Piece hold = pieces[x][y];
			pieces[x][y] = null;
			return hold;
		} else {
			if (!inBounds(x, y)) {
				System.out.println("Removing piece out of bounds.");
			} else {
				System.out.println("Tried to remove piece in out of bounds square.");
			}
			return null;
		}
		
	}

	public boolean canEndTurn() {
		return didMove || (selectedPiece != null && selectedPiece.hasCaptured());
	}

	public void endTurn() {
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
		}
		
		if (currentTurn == 1) {
            currentTurn = 0;
        } else {
            currentTurn = 1;
        }
        selectedPiece = null;
        didMove = false;

	}

	public String winner() {
		int countFire = 0;
		int countWater = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Piece hold = pieceAt(i, j);
				if (hold != null) {
					if (hold.isFire()) {
						countFire += 1;
					} else {
						countWater += 1;
					}
				}
				
			}
		}

		if (countFire == 0 && countWater == 0) {
			return "No one";
		} else if (countFire == 0) {
			return "Water";
		} else if (countWater == 0) {
			return "Fire";
		} else {
			return null;
		}

	}

	private void drawBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (selectedPiece != null && selectedPiece == pieces[i][j]) {
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	} else if ((i + j) % 2 == 0) {
            		StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
            	} else {
            		StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
            	}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getImage(pieces[i][j]), 1, 1);
                }
            }
        }
    }

    private String getImage(Piece p) {
    	String img = "img/";
    	if (p.isBomb()) {
    		img = img + "bomb";
    	} else if (p.isShield()) {
    		img = img + "shield";
    	} else {
    		img = img + "pawn";
    	}

    	if (p.isFire()) {
    		img = img + "-fire";
    	} else {
    		img = img + "-water";
    	}

    	if (p.isKing()) {
    		img = img + "-crowned";
    	}
    	img = img + ".png";

    	return img;
    }
	public static void main(String[] args) {
		Board board = new Board(false);
		StdDrawPlus.setXscale(0, board.N);
        StdDrawPlus.setYscale(0, board.N);

        while(true) {
            board.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (board.canSelect(x, y)) {
                	board.select(x,y);
                }
                

            }        

            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                	board.endTurn();
                	if (board.winner() != null) {
                		break;
                	}
                	
                }
                
                	
            }    
            StdDrawPlus.show(100);
        }
        board.drawBoard();
        StdDrawPlus.show(100);
	}


}