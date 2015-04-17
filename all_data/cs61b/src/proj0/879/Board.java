public class Board {
	private boolean shouldBeEmpty = false;
	private Piece[][] checkers = new Piece[8][8];
	private boolean fire = true;
	private boolean has_piece = false;
	private boolean has_moved = false;
	private int selectedX;
	private int selectedY;
	private Piece selected;
	private static int N = 8;
	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
		if (shouldBeEmpty == false) {
			for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
			    	if (i % 2 == 0 && j == 0) {
			    		checkers[i][j] = new Piece(true, this, i, j, "pawn");
				    }
				    if ((i + 1) % 2 == 0 && j == 1) {
			    		checkers[i][j] = new Piece(true, this, i, j, "shield");
				    }
				    if (i % 2 == 0 && j == 2) {
			    		checkers[i][j] = new Piece(true, this, i, j, "bomb");
				    }
				    if ((i + 1) % 2 == 0 && j == 7) {
			    		checkers[i][j] = new Piece(false, this, i, j, "pawn");
				    }
				    if (i % 2 == 0 && j == 6) {
			    		checkers[i][j] = new Piece(false, this, i, j, "shield");
				    }
				    if ((i + 1) % 2 == 0 && j == 5) {
			    		checkers[i][j] = new Piece(false, this, i, j, "bomb");
				    }
				}
			}
		}
	}

	private void drawPieces(int N) {
    	for (int i = 0; i < N; i++) {
		    for (int j = 0; j < N; j++) {
		    	if (pieceAt(i, j) != null) {
		    		if (pieceAt(i, j).isKing() == false) {
				    	if (pieceAt(i, j).isFire() == true && pieceAt(i, j).isShield() == false && pieceAt(i, j).isBomb() == false) {
					        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
				    	}
				    	if (pieceAt(i, j).isFire() == true && pieceAt(i, j).isShield() == true) {
					        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
				    	}
				    	if (pieceAt(i, j).isFire() == true && pieceAt(i, j).isBomb() == true) {
					        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
				    	}
				    	if (pieceAt(i, j).isFire() == false && pieceAt(i, j).isShield() != true && pieceAt(i, j).isBomb() != true) {
					        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
				    	}
				    	if (pieceAt(i, j).isFire() == false && pieceAt(i, j).isShield() == true) {
					        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
				    	}
				    	if (pieceAt(i, j).isFire() == false && pieceAt(i, j).isBomb() == true) {
					        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
				    	}
				    }
				    if (pieceAt(i, j).isKing() == true) {
				    	if (pieceAt(i, j).isFire() == true && pieceAt(i, j).isShield() == false && pieceAt(i, j).isBomb() == false) {
					        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
				    	}
				    	if (pieceAt(i, j).isFire() == true && pieceAt(i, j).isShield() == true) {
					        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
				    	}
				    	if (pieceAt(i, j).isFire() == true && pieceAt(i, j).isBomb() == true) {
					        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
				    	}
				    	if (pieceAt(i, j).isFire() == false && pieceAt(i, j).isShield() != true && pieceAt(i, j).isBomb() != true) {
					        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
				    	}
				    	if (pieceAt(i, j).isFire() == false && pieceAt(i, j).isShield() == true) {
					        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
				    	}
				    	if (pieceAt(i, j).isFire() == false && pieceAt(i, j).isBomb() == true) {
					        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
					    }
			    	}
			    }
		    }
		}
	} 

	private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	            }
            }
        }
    
    private static void drawInitialPieces(int N) { 
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
		    	if (i % 2 == 0 && j == 0) {
			        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
			    }
			    if ((i + 1) % 2 == 0 && j == 1) {
			        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
			    }
			    if (i % 2 == 0 && j == 2) {
			        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
			    }
			    if ((i + 1) % 2 == 0 && j == 7) {
			        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
			    }
			    if (i % 2 == 0 && j == 6) {
			        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
			    }
			    if ((i + 1) % 2 == 0 && j == 5) {
			        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
			    }
			}
		}
	}
	
	public Piece pieceAt(int x, int y) {
		if (x < N && y < N && x >= 0 && y >= 0) { 
			return checkers[x][y];
		}
		else {
			return null;
		}
	}

	public boolean canSelect(int x, int y) {
		if (x < N && y < N && x >= 0 && y >= 0) { 
			if (pieceAt(x, y) != null) {
				if (corresponding_piece(x, y) == true) {		
					if (has_piece == false || has_moved == false) {
						return true;
					}
					else if (has_piece == true && has_moved == false) {
						return true;
					}
				}
			}
			else {   // pieceAt(x, y) is empty
				if (has_piece == true && validMove(selectedX, selectedY, x, y) == true) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xf < N && yf < N && xf >= 0 && yf >= 0) {
			if (pieceAt(xf, yf) == null) {
				if (pieceAt(xi, yi) != null) {
					if (has_moved == true && selected.isBomb() == true && selected.hasCaptured() == true) {
						return false;
					}
					if (pieceAt(xi, yi).isKing() == true) {
						if (has_moved == true && pieceAt(xi, yi).isFire() == true && yi == 7) {
							return false;
						}
						else if (has_moved == true && pieceAt(xi, yi).isFire() == false && yi == 0) {
							return false;
						}
						else if (((yi - 2) == yf || (yi + 2 == yf)) && ((xi + 2) == xf || (xi - 2) == xf)) {
							if (pieceAt((xi + xf)/2, (yi + yf)/2) != null) {
								if (corresponding_piece((xi + xf)/2, (yi + yf)/2) == false) {
									return true;
								}
							}
						}
						else if (((yi - 1) == yf || (yi + 1 == yf)) && ((xi + 1) == xf || (xi - 1) == xf)) {
							return true;
						}
					}
					else {
						if (pieceAt(xi, yi).isFire() == true) {					
							if ((yi + 1) == yf && ((xi + 1) == xf || (xi - 1) == xf)) {					
								return true;
							}
							if (pieceAt((xi + xf)/2, yi + 1) != null) {		
								if (corresponding_piece((xi + xf)/2, yi + 1) == false) { 		
									if ((yi + 2) == yf && ((xi + 2) == xf || (xi - 2) == xf)) {				
										return true;
									}
								}
							}
						}
						else if (pieceAt(xi, yi).isFire() == false) {
							if ((yi - 1) == yf && ((xi + 1) == xf || (xi - 1) == xf)) {
								return true;
							}
							if (pieceAt((xi + xf)/2, yi - 1) != null) {								
								if (corresponding_piece((xi + xf)/2, yi - 1) == false) {
									if ((yi - 2) == yf && ((xi + 2) == xf || (xi - 2) == xf)) {
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean corresponding_piece(int x, int y) {
		if (x < N && y < N && x >= 0 && y >= 0) { 
			if (pieceAt(x, y) != null) {
				if (pieceAt(x, y).isFire() == true && fire == true) {
					return true;
				}
				else if (pieceAt(x, y).isFire() == false && fire == false) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (has_piece == false) {
			has_piece = true;
			selected = pieceAt(x, y);
			selectedX = x;
			selectedY = y;
		}
		else if (has_piece == true) {
			if (corresponding_piece(x, y) == true && has_moved == false) {
				selected = pieceAt(x, y);
				selectedX = x;
				selectedY = y;
			}
			else {
				selected.move(x, y);
				place(selected, x, y);
		        remove(selectedX, selectedY);
		        selectedX = x;
		        selectedY = y; 
		        has_moved = true;
				if (selected.isBomb() == true && selected.hasCaptured() == true) {
		          	remove(x, y);
		        }
	        }

      	}
	}
	

	public void place(Piece p, int x, int y) {
		if (x < N && y < N && x >= 0 && y >= 0 && p != null) { 
			checkers[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		Piece temp = pieceAt(x, y);
		if (x < N && y < N && x >= 0 && y >= 0) {
			if (pieceAt(x, y) == null) {
				System.out.println("Not a piece");
				return null;
			}  
			else {
				checkers[x][y] = null;
				return temp;
			}	
		}
		else {
			System.out.println("Out of bounds");
		}
	return null;
	}

	public boolean canEndTurn() {
		if (has_moved == true){
			return true;
		}
		else {
			return false;
		}
	}

	public void endTurn() {
		if (canEndTurn() == true) { 
			if (fire == true) {
				fire = false;
				has_moved = false;
				has_piece = false;
				selected.doneCapturing();
			}
			else {
				has_piece = false;
				fire = true;
				selected.doneCapturing();
				has_moved = false;
			}
		}
	}

	public String winner() {
		int fireCounter = 0;
	    int waterCounter = 0;
		for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	        	if (checkers[i][j] != null) {
		        	if (checkers[i][j].isFire() == true) {
		        		fireCounter = fireCounter + 1; 
		        	} 
		        	else if (checkers[i][j].isFire() == false) {
		        		waterCounter = waterCounter + 1; 
		        	}
		        }
		    }
		}
	    if (waterCounter == 0 && fireCounter > 0) {
	    	return "Fire";
	    }
	    else if (waterCounter == 0 && fireCounter == 0) {		
	    	return "No one";
	    }
	    else if (waterCounter > 0 && fireCounter == 0){
	    	return "Water";
	    }
	    else {
	    	return null;
	    }
	}

    public static void main(String[] args) {
    	Board game_board = new Board(false);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        // while(true) {
        // drawBoard(N);
            // if (StdDrawPlus.mousePressed()) {
            //     double x = StdDrawPlus.mouseX();
            //     double y = StdDrawPlus.mouseY();
            //     pieces[(int) x][(int) y] = true;
            // }         
            // StdDrawPlus.show(100);
        // }
        game_board.drawBoard(N);
        if (game_board.shouldBeEmpty == false) {
        	game_board.drawInitialPieces(N);
    	}
    	while (game_board.winner() != null) {
    	game_board.drawBoard(N);
		game_board.drawPieces(N);
			if (StdDrawPlus.isSpacePressed() == false && game_board.canEndTurn() == false) {
	    		if (StdDrawPlus.mousePressed() == true) { 
					int clickedX = (int) Math.floor(StdDrawPlus.mouseX()); 
					int clickedY = (int) Math.floor(StdDrawPlus.mouseY());
					while (game_board.canSelect(clickedX, clickedY) == true) {
						game_board.select(clickedX, clickedY);
                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
						StdDrawPlus.filledSquare(clickedX+ .5, clickedY + .5, .5);
						game_board.drawPieces(N);		
						if (StdDrawPlus.mousePressed() == true) { 
							clickedX = (int) Math.floor(StdDrawPlus.mouseX()); 
							clickedY = (int) Math.floor(StdDrawPlus.mouseY());	
						}
					}
				}
			}
			else if (StdDrawPlus.isSpacePressed() == true && game_board.canEndTurn() == true) {
				game_board.endTurn();
			}
			game_board.winner();
			StdDrawPlus.show(10);
    	} 
    	
    }
}


















	