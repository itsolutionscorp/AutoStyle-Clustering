public class Board {
	private Piece [][] board;
	//the fisrt turn is fireteam.
	private boolean isFireTeam = true;
	private boolean selected = false;
	private boolean moved = false;
	private Piece activePiece = null;

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
			//Initializes an empty Board.
			board = new Piece [8][8];
			
		} else {
			//Initializes a Board with the default configuration.
			board = new Piece [8][8];
			for (int i = 0; i < 8; i = i + 2) {
				board[i][0] = new Piece(true, this, i, 0, "pawn");
				board[i][2] = new Piece(true, this, i, 2, "bomb");
				board[i][6] = new Piece(false, this, i, 6, "shield");
			}
			for (int j = 1; j < 8; j = j + 2) {
				board[j][1] = new Piece(true, this, j, 1, "shield");
				board[j][5] = new Piece(false, this, j, 5, "bomb");
				board[j][7] = new Piece(false, this, j, 7, "pawn");
			}
		}
	}

	private String drawType(Piece p) {
		if (p.isKing() == false) {
			if(p.isFire() == true) {
				if (p.isShield()) {
					return "img/shield-fire.png";	
				} else if (p.isBomb()) {
					return "img/bomb-fire.png";
				} else {
					return "img/pawn-fire.png";
				}
			} else {
				if (p.isShield()) {
					return "img/shield-water.png";	
				} else if (p.isBomb()) {
					return "img/bomb-water.png";
				} else {
					return "img/pawn-water.png";
				}
			}
			
		} else {
			if(p.isFire() == true) {
				if (p.isShield()) {
					return "img/shield-fire-crowned.png";	
				} else if (p.isBomb()) {
					return "img/bomb-fire-crowned.png";
				} else {
					return "img/pawn-fire-crowned.png";
				}
			} else {
				if (p.isShield()) {
					return "img/shield-water-crowned.png";	
				} else if (p.isBomb()) {
					return "img/bomb-water-crowned.png";
				} else {
					return "img/pawn-water-crowned.png";
				}
			}

		}
	}

	public Piece pieceAt(int x, int y) {
		//Get the piece at position(x, y) on the board.
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			if (board[x][y] != null) {
				return board[x][y];	
			} else {
				return null;
			}	
		} else {
			return null;
		}
	}

	private int getX(Piece p) {
		if (p != null) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (p == board[i][j]) {
						return i;	
					}
				
				}
			
			}
			
		}
		return -1;	
	}

	private int getY(Piece p) {
		if (p != null) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (p == board[i][j]) {
						return j;	
					}
				
				}
			
			}
			
		}
		return -1;
	}

	private String getType(Piece p) {
		if (p != null) {
			if (p.isBomb()) {
				return "bomb";
			} else if (p.isShield()) {
				return "shield";
				
			}else {
				return "pawn";
			}
		}

		return null;
	}
	



	private boolean validMove(int xi, int yi, int xf, int yf) {
	// System.out.println(xi + " " + yi + " " + xf + " " + yf);
		if (xi >= 0 && xi <= 7 && 
			yi >= 0 && yi <= 7 && 
			xf >= 0 && xf <= 7 && 
			yf >= 0 && yf <= 7) {
			Piece m = pieceAt(xi, yi);
			Piece n = pieceAt(xf, yf);
			if (n == null && m != null) {
				if (m.isKing() == false) {
					if (m.side() == 0) {
						if (((xf - xi == 1) && (yf - yi == 1)) || ((xf - xi == -1) && (yf - yi == 1))) {
							return true;
						} else if (((xf - xi == 2) && (yf - yi == 2)) || ((xf - xi == -2) && (yf - yi == 2))) {
							Piece internal = pieceAt((xf + xi) / 2, (yf + yi) / 2 );
							if ((internal != null) && (internal.side() != m.side())){
								return true;
							} else {
								return false;
							}	
						} else {
							return false;
						}
						
					} else {
						if (((xf - xi == -1) && (yf - yi == -1)) || ((xf - xi == 1) && (yf - yi == -1))) {
							return true;
						} else if (((xf - xi == -2) && (yf - yi == -2)) || ((xf - xi == 2) && (yf - yi == -2))) {
							Piece internal_w = pieceAt((xf + xi) / 2, (yf + yi) / 2 );
							if ((internal_w != null) && (internal_w.side() != m.side())){
								return true;
							} else {
								return false;
							}	
						} else {
							return false;
						}

					}
					
				} else {
					if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)) {
						return true;	
					} else if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)) {
						Piece internal_k = pieceAt((xf + xi) / 2, (yf + yi) / 2 );
						if ((internal_k != null) && (internal_k.side() != m.side())){
							return true;
						} else {
							return false;
						}	
						
					} else {
						return false;
					}
				}
				
			} else {
				return false;
			}
		} else {
			return false;
		}
	}


	public boolean canSelect(int x, int y) {
		//Return true if the square at (x, y) can be selected.
		Piece select = pieceAt(x, y);
		if (select != null) {
			if (isFireTeam == select.isFire()) {
				if (selected == false) {
					return true;	
				} else if (moved == false) {
					return true;
				} else {
					return false;
				}
			
			}
			return false;
		} else {
			//if (isFireTeam == select.team) {
				if (selected == true && moved == false) {

					if (validMove(getX(activePiece), getY(activePiece), x, y)) {
						return true;	
					} else {
						return false;
					}
					
				} else if (selected == true && moved == true) {
					if (activePiece.hasCaptured() == true) {
						if (validMove(getX(activePiece), getY(activePiece), x, y)) {
							return true;	
						} else {
							return false;
						}
						
					} else {
						return false;
					}
				} else {
					return false;
				}
				
			//}
			//return false;
		}
		
	}

	public void select(int x, int y) {
		//if (canSelect(x, y)) {
			if (pieceAt(x, y) != null) {
				activePiece = pieceAt(x, y);
				selected = true;
				//System.out.println("Selected piece at " + x + " " + y);
			} else {
				place(activePiece, x, y);
				moved = true;
				//System.out.println("Placed piece at " + x + " " + y);
			}	
		//}

	}

	public void place(Piece p, int x, int y) {
		if(x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			if (p != null) {
				if (pieceAt(x, y) != null) {
					remove(x, y);	
				}
				remove(getX(p), getY(p));
				p.move(x, y);
				board[x][y] = p;
				activePiece = board[x][y];
				//moved = true;	
			}
		}

	}

	public Piece remove(int x, int y) {
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			if (pieceAt(x, y) != null) {
				Piece p = board[x][y];
				Piece beRemoved = new Piece(p.isFire(), this, x, y, getType(p));
				board[x][y] = null;
				return beRemoved;
			} else {
				System.out.println("There is no piece at this point.");
				return null;
			}
			
		} else {
			System.out.println("Out of bound!");
			return null;
		}
		
	}

	public boolean canEndTurn() {
		if (activePiece != null) {
			if (moved == true || activePiece.hasCaptured() == true) {
				return true;	
			}
		}
		return false;	
	}

	public void endTurn() {
		if (isFireTeam) {
			isFireTeam = false;	
		} else {
			isFireTeam = true;
		}

		selected = false;
		moved = false;
		activePiece = null;

	}

	public String winner() {
		int fireNumber = 0;
		int waterNumber = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece p = board[i][j];
				if (p != null) {
					if (p.isFire() == true) {
						fireNumber += 1;	
					} else {
						waterNumber += 1;
					}	
				}	
			}
			
		}

		if (fireNumber == 0 && waterNumber == 0) {
			return "No one";	
		} else if (fireNumber != 0 && waterNumber == 0) {
			return "Fire";	
		} else if (fireNumber == 0 && waterNumber != 0) {
			return "Water";	
		} else {
			return null;
		}

	}


	 public static void main(String[] args) {
	 	int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
	 	Board b = new Board(false);


	 	while(true) {
	 		for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {
                	if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	if (b.board[i][j] != null) {
                		Piece toBeDrawed = b.board[i][j];
                		StdDrawPlus.picture(i + .5, j + .5, b.drawType(toBeDrawed), 1, 1);
                	
                	}
            	}
        	}

        	if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                // System.out.println(x);
                // System.out.println(y);
                if (b.canSelect((int)x, (int)y)) {
                	b.select((int)x, (int)y);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare((int)x + .5, (int)y + .5, .5);

                }
                
	 		}

	 		if (StdDrawPlus.isSpacePressed()) {
	 			if (b.canEndTurn()) {
                	b.endTurn();	
                }
	 		}
	 		StdDrawPlus.show(100);
	 	
		
	 }
	}


	
}