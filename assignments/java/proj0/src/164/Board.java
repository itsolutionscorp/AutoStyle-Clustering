 public class Board {
	private Piece FP1, FP2, FP3, FP4, FS1, FS2, FS3, FS4, FB1, FB2, FB3, FB4, 
		WB1, WB2, WB3, WB4, WS1, WS2, WS3, WS4, WP1, WP2, WP3, WP4;

	private Piece[][] gamePieces;

	private String player;
	private int selectX;
	private int selectY;
	private int moves;
	private boolean hasCaptured;
	private boolean pieceSelected;
	private Piece movedPiece;
	private boolean mouseSelect;
	private boolean captureTwo;


	public static void main(String[] args) {
		int N = 8;
	    StdDrawPlus.setXscale(0, N);
       	StdDrawPlus.setYscale(0, N);
       	Board board = new Board(false);  

       	board.drawBoard(N);
        
       	while(true) {
            board.drawBoard(N);            
            StdDrawPlus.show(100);

            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                board.mouseSelect = true;
                if (board.canSelect((int) x, (int) y) == true) {
                	board.select((int) x, (int) y);
                }
            }  

            if (StdDrawPlus.isSpacePressed() == true) {
	        	if( board.canEndTurn() == true ) {
	        		board.endTurn();
        		}
        	}
        	board.winner();	
        }
	}

	public Board(boolean shouldBeEmpty) {
		movedPiece = null;
		selectX = -1;
       	selectY = -1; 
       	moves = 0; 
       	player = "fire";
       	pieceSelected = false;
       	hasCaptured = false;
       	captureTwo = false;

		if (shouldBeEmpty == false) {
			FP1 = new Piece(true, this, 0, 0, "pawn");
	        FP2 = new Piece(true, this, 2, 0, "pawn");
	        FP3 = new Piece(true, this, 4, 0, "pawn");
	        FP4 = new Piece(true, this, 6, 0, "pawn");
	 		//
	        FS1 = new Piece(true, this, 1, 1, "shield");
	        FS2 = new Piece(true, this, 3, 1, "shield");
	        FS3 = new Piece(true, this, 5, 1, "shield");
	        FS4 = new Piece(true, this, 7, 1, "shield");
	        //
	        FB1 = new Piece(true, this, 0, 2, "bomb");
	        FB2 = new Piece(true, this, 2, 2, "bomb");
	        FB3 = new Piece(true, this, 4, 2, "bomb");
	        FB4 = new Piece(true, this, 6, 2, "bomb");
	        //
	        WB1 = new Piece(false, this, 1, 5, "bomb");
	        WB2 = new Piece(false, this, 3, 5, "bomb");
	        WB3 = new Piece(false, this, 5, 5, "bomb");
	        WB4 = new Piece(false, this, 7, 5, "bomb");
	        //
	        WS1 = new Piece(false, this, 0, 6, "shield");
	        WS2 = new Piece(false, this, 2, 6, "shield");
	        WS3 = new Piece(false, this, 4, 6, "shield");
	        WS4 = new Piece(false, this, 6, 6, "shield");
	        //
	        WP1 = new Piece(false, this, 1, 7, "pawn");
	        WP2 = new Piece(false, this, 3, 7, "pawn");
	        WP3 = new Piece(false, this, 5, 7, "pawn");
	        WP4 = new Piece(false, this, 7, 7, "pawn");

	        gamePieces = new Piece[][] {
				{ FP1, null, FB1, null, null, null, WS1, null},
				{ null, FS1, null, null, null, WB1, null, WP1 },
				{ FP2, null, FB2, null, null, null, WS2, null }, 
				{ null, FS2, null, null, null, WB2, null, WP2 },
				{ FP3, null, FB3, null, null, null, WS3, null },
				{ null, FS3, null, null, null, WB3, null, WP3 }, 
				{ FP4, null, FB4, null, null, null, WS4, null }, 
				{ null, FS4, null, null, null, WB4, null, WP4 }
			};
	    }

	    else {
	    	gamePieces = new Piece[8][8];
	    }
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	if (mouseSelect == true) {
                		if ( i == selectX  &&  j == selectY ) {
                			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                		}		
                	}           			
                }
                else { 
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                	if (mouseSelect == true) {
                		if ( i == selectX  &&  j == selectY ) {
                			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                		}		
                	}   
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (gamePieces[i][j] != null) {
                	drawPieces(i, j);
                }
            }
        }
    }

    private void drawPieces(int x, int y) {
    	if (gamePieces[x][y].isFire() == true && gamePieces[x][y].isBomb() == false && gamePieces[x][y].isShield() == false) {
    		if(gamePieces[x][y].isKing() == true) {
    			StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
    		}
    		else {
            	StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
            }
        }
        else if (gamePieces[x][y].isFire() == true && gamePieces[x][y].isBomb() == true) {
        	if(gamePieces[x][y].isKing() == true) {
        		StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
        	}
        	else {
        		StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
        	}
        }
        else if (gamePieces[x][y].isFire() == true && gamePieces[x][y].isShield() == true) {
        	if(gamePieces[x][y].isKing() == true) {
        		StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-fire-crowned.png", 1, 1);
        	}
        	else {
        		StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-fire.png", 1, 1);
        	}
        }
        else if (gamePieces[x][y].isFire() == false && gamePieces[x][y].isBomb() == false && gamePieces[x][y].isShield() == false) {
            if(gamePieces[x][y].isKing() == true) {
            	StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
            }
            else {
            	StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
            }
        }
        else if (gamePieces[x][y].isFire() == false && gamePieces[x][y].isBomb() == true) {
        	if(gamePieces[x][y].isKing() == true) {
        		StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
        	}
        	else {
        		StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
        	}
        }
        else if (gamePieces[x][y].isFire() == false && gamePieces[x][y].isShield() == true) {
        	if(gamePieces[x][y].isKing() == true) {
        		StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-water-crowned.png", 1, 1);
        	}
        	else {
        		StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-water.png", 1, 1);
        	}
        }
    }

	public Piece pieceAt(int x, int y) {
		if ( x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		else if ( gamePieces[x][y] != null ) {
			return gamePieces[x][y];
		}
		else {
			return null;
		}
	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(selectX, selectY) != null && hasCaptured == true && canCapture(selectX,selectY) == true) {
			captureTwo = true; 
		}
		if (pieceSelected == false) {
			if ( gamePieces[x][y] != null) { 	
				
				if (player == "fire") {
					if (gamePieces[x][y].isFire() == true) {
						return true;
					}
					else {
						return false;
					}
				}
				
				if (player == "water") {
					if (gamePieces[x][y].isFire() == false) {
						return true;

					}
					else {
						return false;

					}
				}
			}
		}
		else if (pieceSelected == true && moves == 0 ) {
			if ( gamePieces[x][y] != null) {
				if (player == "fire") {
					if (gamePieces[x][y].isFire() == true) {
						return true;
					}
					else {
						return false;
					}
				}
				//water player
				else {
					if (gamePieces[x][y].isFire() == false) {
						return true;
					}
					else {
						return false;
					}
				}
			}
			else {
				if (validMove(selectX, selectY, x, y) == true) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		else if (moves > 0){
			if (captureTwo == true) {
				if (validMove(selectX, selectY, x, y)  == true) {
					return true;
				}
			}
			return false;
		}
		else {
			if (validMove(selectX, selectY, x, y) == true) {
				return true;
			}
			else {
				return false;
			}
		}

		return false;
	}


	private boolean canCapture(int x, int y) {
		if ((x+2) <= 7 && (y+2) <= 7 ) {
			if (pieceAt(x+1, y+1) != null && pieceAt(x+2, y+2) == null) {
				if (player == "fire" && gamePieces[x+1][y+1].isFire() == false) {
					return true; 
				}
				else if (player == "water" && gamePieces[x+1][y+1].isFire() == true) {
					return true;
				}
			}
		}

		if ( (x+2) <=7 && (y-2) >= 0 ) {
			if (pieceAt(x+1, y-1) != null && pieceAt(x+2, y-2) == null) {
				if (player == "fire" && gamePieces[x+1][y-1].isFire() == false) {
					return true; 
				}
				else if (player == "water" && gamePieces[x+1][y-1].isFire() == true) {
					return true;
				}
			}
		}

		if ( (x-2) >=0 && (y-2) >= 0 ) {
			if (pieceAt(x-1, y-1) != null && pieceAt(x-2, y-2) == null) {
				if (player == "fire" && gamePieces[x-1][y-1].isFire() == false) {
					return true; 
				}
				else if (player == "water" && gamePieces[x-1][y-1].isFire() == true) {
					return true;
				}
			}
		}

		if ( (x-2) >=0 && (y+2) <= 7 ) {
			if (pieceAt(x-1, y+1) != null && pieceAt(x-2, y+2) == null) {
				if (player == "fire" && gamePieces[x-1][y+1].isFire() == false) {
					return true; 
				}
				else if (player == "water" && gamePieces[x-1][y+1].isFire() == true) {
					return true;
				}
			}
		}
		return false;
	}


	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (captureTwo == true && movedPiece.isBomb() == true && Math.abs(xi - xf) == 2 && Math.abs(yf - yi) == 2 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7)
		{
			return false;
		}
		else if (captureTwo == true) {
			if(gamePieces[xi][yi].isKing() == false) {
				if (gamePieces[xi][yi].isFire() == true) {
					if(pieceAt(xf,yf) == null && pieceAt((xi+xf)/2, (yi+yf)/2) != null && Math.abs(xi - xf) == 2 && (yf - yi) == 2 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7) {
						if( pieceAt(xi,yi).isFire() != pieceAt((xi+xf)/2, (yi+yf)/2).isFire()) {
							captureTwo = false;
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
					if( pieceAt(xf,yf) == null && pieceAt((xi+xf)/2, (yi+yf)/2) != null && Math.abs(xi - xf) == 2 && (yi - yf) == 2 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7) {
						if( pieceAt(xi,yi).isFire() != pieceAt((xi+xf)/2, (yi+yf)/2).isFire()) {
							captureTwo = false;
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
			}
			else {
			 	if( pieceAt(xf,yf) == null && pieceAt((xi+xf)/2, (yi+yf)/2) != null && Math.abs(xi - xf) == 2 && Math.abs(yf - yi) == 2 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7) {
			 		if( pieceAt(xi,yi).isFire() != pieceAt((xi+xf)/2, (yi+yf)/2).isFire()) {
						captureTwo = false;
						return true;
					}
					else {
						return false;
					}
			 	}
			}
		}
		else if (gamePieces[xi][yi].isKing() == false) {
			if (canCapture(xi, yi) == false) {
				if (gamePieces[xi][yi].isFire() == true) {
					if ( Math.abs(xi - xf) == 1 && (yf - yi) == 1 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					if ( Math.abs(xi - xf) == 1 && (yi - yf) == 1 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7) {
						return true;
					}
					else {
						return false;
					}
				}
			}
			else  {
				if (gamePieces[xi][yi].isFire() == true) {
					if ( Math.abs(xi - xf) == 1 && (yf - yi) == 1 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7 ) {
						return true;
					} 
					else if( pieceAt(xf,yf) == null && Math.abs(xi - xf) == 2 && (yf - yi) == 2 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					if ( Math.abs(xi - xf) == 1 && (yi - yf) == 1 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7 ) {
						return true;
					} 
					else if( pieceAt(xf,yf) == null && Math.abs(xi - xf) == 2 && (yi - yf) == 2 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7) {
						return true;
					}
					else {
						return false;
					}
				}
			}
		}
		else {
			if (canCapture(xi, yi) == false) {
				if (gamePieces[xi][yi].isFire() == true) {
					if ( Math.abs(xi - xf) == 1 && Math.abs(yf - yi) == 1 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					if ( Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7) {
						return true;
					}
					else {
						return false;
					}
				}
			}
			else  {
				if (gamePieces[xi][yi].isFire() == true) {
					if ( Math.abs(xi - xf) == 1 && Math.abs(yf - yi) == 1 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7 ) {
						return true;
					} 
					else if( pieceAt(xf,yf) == null && Math.abs(xi - xf) == 2 && Math.abs(yf - yi) == 2 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					if ( Math.abs(xi - xf) == 1 && (yi - yf) == 1 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7 ) {
						return true;
					} 
					else if( pieceAt(xf,yf) == null && Math.abs(xi - xf) == 2 && (yi - yf) == 2 && yf >=0 && yf <= 7 && xf >= 0 && xf <= 7) {
						return true;
					}
					else {
						return false;
					}
				}
			}
		}
		return false;	
	}
	

	public void select(int x, int y) {
		if  (gamePieces[x][y] != null) {
			selectX = x;
			selectY = y;
			pieceSelected = true;
			mouseSelect = true;
			movedPiece = gamePieces[x][y];
		}
		else {
			movedPiece.move(x,y);
			moves = moves + 1;
			selectX = x;
			selectY = y;
			hasCaptured = movedPiece.hasCaptured();

			}
	}


	public void place(Piece p, int x, int y) {
		if ( x > 7 || y > 7 || x < 0 || y < 0) {
			return;
		}
		else {
			gamePieces[x][y] = p;
		}
	}


	public Piece remove(int x, int y) {
		Piece p = null;
		if ( x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}

		else {
			if (gamePieces[x][y] != null) {
				p = gamePieces[x][y];
				gamePieces[x][y] = null;
				place(null, x, y);
			}
			else {
				return p;
			}
		}	
		return p;
	}

	public boolean canEndTurn() {
		if (moves > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void endTurn() {
		//swtiches players
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieceAt(i,j) != null) {
					pieceAt(i,j).doneCapturing();
				}
			}
		}

		if (player != "water") {
			player = "water";
		}
		else {
			player = "fire";
		}
		selectX = -1;
		selectY = -1;
		pieceSelected = false;
		moves = 0;
		hasCaptured = false;
		captureTwo = false;
	}

	public String winner() {
		int fire = 0;
		int water = 0;
		String string = null;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(gamePieces[i][j] != null ) {
					if (gamePieces[i][j].isFire() == true) {
						fire = fire + 1;
					}
					else {
						water = water + 1;
					}
				}
			}
		}

		if (water == 0 && water < fire) {
			string = "Fire";
		}
		else if ( fire == 0 && water > fire ) {
			string = "Water";
		}
		else if ( fire == 0 && water == 0 ) {
			string = "No one";
		}
		return string;

	}
}
