public class Board {
	private Piece[][] _gameboard;
	private final int SIZE = 8;
	private boolean empty;
	private boolean captured;
	private boolean selectedPiece;
	private Piece savedPiece;
	private int xOld;
	private int yOld;
	private boolean fireTurn = true;
	private boolean hasMoved;
    private boolean beginGame;

	public static void main(String [] args) {
		Board gameboard = new Board(false);
		gameboard.createBoardGUI();
		while (gameboard.winner() == null) {
			if (StdDrawPlus.mousePressed() == true) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				int xVal = (int) x;
				int yVal = (int) y;
				if (gameboard.canSelect(xVal, yVal) == true) {
					gameboard.select(xVal, yVal);
				}
			}
			gameboard.createBoardGUI();
			if (StdDrawPlus.isSpacePressed() == true && gameboard.canEndTurn() == true) {
				gameboard.endTurn();
			}
			StdDrawPlus.show(100);
		}	
		
	}

	public Board(boolean shouldBeEmpty) {
		empty = shouldBeEmpty;
		_gameboard = new Piece[SIZE][SIZE];
		if (empty == false) {
			this.addPieces();
		}
        beginGame = true;
	}

	private void createBoardGUI() { 
		StdDrawPlus.setXscale(0, SIZE);
        StdDrawPlus.setYscale(0, SIZE);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if ((i + j) % 2 == 0) {
                StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            	} else {
            	StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        if (empty == false) {
            if (beginGame == true) {
                this.addPiecesGUI();
            }
			this.updateGUI();
		}
    }

    private void addPieces() {
    	for (int j = 0; j < SIZE; j++) {
    		for (int i = 0; i < SIZE; i++) {
    			if (i % 2 == 0 && j == 0) {
    				Piece firePawn = new Piece(true, this, i, j, "pawn");
    				_gameboard[i][j] = firePawn;
    			}
    			if (i % 2 == 1 && j == 7) {
    				Piece waterPawn = new Piece(false, this, i, j, "pawn");
    				_gameboard[i][j] = waterPawn;
    			}
    			if (i % 2 == 1 && j == 1) {
    				Piece fireShield = new Piece(true, this, i, j, "shield");
    				_gameboard[i][j] = fireShield;
    			}
    			if (i % 2 == 0 && j == 6) {
    				Piece waterShield = new Piece(false, this, i, j, "shield");
    				_gameboard[i][j] = waterShield;
    			}
    			if (i % 2 == 0 && j == 2) {
    				Piece fireBomb = new Piece(true, this, i, j, "bomb");
    				_gameboard[i][j] = fireBomb;
    			}
    			if (i % 2 == 1 && j == 5) {
    				Piece waterBomb = new Piece(false, this, i, j, "bomb");
    				_gameboard[i][j] = waterBomb;
    			}
    		}
    	}
    }

    private void addPiecesGUI() {
    	for (int j = 0; j < SIZE; j++) {
    		for (int i = 0; i < SIZE; i++) {
    			if (i % 2 == 0 && j == 0) {
    				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
    			}
    			if (i % 2 == 1 && j == 7) {
    				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
    			}
    			if (i % 2 == 1 && j == 1) {
    				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
    			}
    			if (i % 2 == 0 && j == 6) {
    				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
    			}
    			if (i % 2 == 0 && j == 2) {
    				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
    			}
    			if (i % 2 == 1 && j == 5) {
    				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
    			}
    		}
    	}
    }

    private void updateGUI() {
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                String fileName = "";
                String kinged = "";
                if (_gameboard[i][j] == null) {
                    // do nothing
                } else {
                    if (_gameboard[i][j].isKing() == true) {
                        kinged = "-crowned";
                    } 
                    if ((_gameboard[i][j].isFire() && _gameboard[i][j].isBomb()) == true) {
                        fileName = "img/bomb-fire";
                    } else if ((_gameboard[i][j].isFire() && _gameboard[i][j].isShield()) == true) {
                        fileName = "img/shield-fire";
                    } else if (_gameboard[i][j].isFire() == true) {
                        fileName = "img/pawn-fire";
                    } else if ((!_gameboard[i][j].isFire() && _gameboard[i][j].isBomb()) == true) {
                        fileName = "img/bomb-water";
                    } else if ((!_gameboard[i][j].isFire() && _gameboard[i][j].isShield()) == true) {
                        fileName = "img/shield-water";
                    } else if ((_gameboard[i][j].isFire() == false)) {
                        fileName = "img/pawn-water";
                    }
                    StdDrawPlus.picture(i + .5, j + .5, fileName + kinged + ".png", 1, 1);

                }    

            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x > SIZE || y > SIZE || x < 0 || y < 0) {
            return null;
        }
    	if (_gameboard[x][y] == null) {
    		return null;
        }
    	return _gameboard[x][y];
    }

    public void place(Piece p, int x, int y) {
    	if (p == null || x > SIZE || y > SIZE || x < 0 || y < 0) {
    		
    	} else {
    		if (_gameboard[x][y] != null) {
    			_gameboard[x][y] = null;
    			_gameboard[x][y] = p;
    			// should i remove previous piece or is this ok?
    		} else {
    			_gameboard[x][y] = p;
    		}
    	}	 	
    }

//Returns true if the square at (x, y) can be selected.

//A square with a piece may be selected if it is the corresponding player’s turn and one of the following is true:

//The player has not selected a piece yet.
//The player has selected a piece, but did not move it.
//An empty square may be selected if one of the following is true:

//During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an empty spot which is a valid move for the previously selected Piece.
//During this turn, the player has selected a Piece, captured, and has selected another valid capture destination.
//When performing multi-captures, you should only select the active piece once; all other selections should be valid destination points.
    public boolean canSelect(int x, int y) {
    	if (x < SIZE && y < SIZE && x >= 0 && y >=0) {
    		if (_gameboard[x][y] != null && _gameboard[x][y].isFire() == fireTurn) {
    			if (selectedPiece == false || (selectedPiece == true && hasMoved == false)) {
    				return true;
    			}
    		} else {
    			if (_gameboard[x][y] == null) {
    				if (selectedPiece == true && hasMoved == false) {
    					return this.validMove(xOld, yOld, x, y);
    				}
    				if (selectedPiece == true && captured == true) {
    					return this.validMove(xOld, yOld, x, y);
    				}
    			} 
    		}
    	}
    	return false;	
    }


    private boolean validMove(int xi, int yi, int xf, int yf) {
    	// check everything is in bounds
    	// make sure there doesnt exist a piece in the final spot
    	// if thats the case return false
    	// 3 main ifs one is if xi yi is fire == true (first case) or if it == false and 3rd one is isKing == true
    	// within king you can have a fire or water
    	// so theres actually 4 if cases... write king first and tehn an else case for if fire )(write how it moves)
    	// and else water (opposite of water) for king just copy both of them and itll work
    	if (xi < SIZE && xi >= 0 && yi < SIZE && yi >= 0 && xf < SIZE && xf >= 0 && yf < SIZE && yf >=0) {
    		if (selectedPiece == true) {
    			if (_gameboard[xf][yf] != null) {
    				return false;
    			} else {
    				if (_gameboard[xi][yi] != null) {
    					if (_gameboard[xi][yi].isKing() == true) {
	    					if ((xf == xi + 1 || xf == xi - 1) && (yf == yi + 1 || yf == yi - 1)) {
	    						return true;
	    					}
	    					if (this.fireCanCapture(xi, yi, xf, yf) == true || this.waterCanCapture(xi, yi, xf, yf) == true) {
	    						return true;
	    					}
	    				} else {
	    					if (_gameboard[xi][yi].isFire() == true) {
                                if (captured == false) {
	    						     if ((xf == xi + 1 || xf == xi - 1) && yf == yi + 1) {
	    							    return true;
	    						     }
                                }     
	    						if (this.fireCanCapture(xi, yi, xf, yf) == true) {
	    							return true;
	    						}
	    					} else {
                                if (captured == false) {
    	    						 if (((xf == xi + 1 || xf == xi  -1) && yf == yi - 1)) {
    	    							return true;
    	    						}
                                }
	    						if (this.waterCanCapture(xi, yi, xf, yf) == true) {
	    							return true;
	    						}
	    					}
	    				} 
    				}
    			}
    		}
    	}
    	return false;
    }

    private boolean waterCanCapture(int xi, int yi, int xf, int yf) {
    	if ((xf == xi + 2  && yf == yi - 2) && (_gameboard[xi + 1][yi - 1] != null)) {
    		if (_gameboard[xi + 1][yi - 1].isFire() == (!_gameboard[xi][yi].isFire())) {
    			return true;
    		}
    	} else if ((xf == xi - 2 && yf == yi - 2) && (_gameboard[xi - 1][yi - 1] != null)) {
    		if (_gameboard[xi - 1][yi - 1].isFire() == (!_gameboard[xi][yi].isFire())) {
    			return true;
    		}
    	}
    	return false;
    }

    private boolean fireCanCapture(int xi, int yi, int xf, int yf) {
    	if ((xf == xi + 2 && yf == yi + 2) && (_gameboard[xi + 1][yi + 1] != null)) {
    		if (_gameboard[xi + 1][yi + 1].isFire() == (!_gameboard[xi][yi].isFire())) {
    			return true;
    		}
    	} else if ((xf == xi - 2 && yf == yi + 2) && (_gameboard[xi - 1][yi + 1] != null)) {
    		if (_gameboard[xi - 1][yi + 1].isFire() == (!_gameboard[xi][yi].isFire())) {
				return true;
			}
		}
		return false; 
	}
    
    /* Selects the square at (x, y). This method assumes canSelect (x,y) returns
    true. Optionally, it is recommended to color the background of the selected square white
    on the GUI via the pen color function. For any piece to perform a capture, that piece must have been selected
    first. If you select a square with a piece, you are prepping that piece for movement.
    If you select an empty square (assuming canSelect returns true),
    you should move your most recently selected piece to that square. */
    public void select(int x, int y) {
    	// if (_gameboard[x][y] == null && firstClick.equals(false)) {
    	// 	_gameboard[x][y] // move method  
    	// }
    	if (_gameboard[x][y] != null) {
    		savedPiece = _gameboard[x][y];
    		xOld = x;
    		yOld = y;
    		selectedPiece = true;
    	} else {
    		savedPiece.move(x, y);// move method;
            beginGame = false;
    		xOld = x;
    		yOld = y;
    		hasMoved = true;
    		captured = savedPiece.hasCaptured();
    	}	
    	
    }

    public Piece remove(int x, int y) {
    	if (x > SIZE && y > SIZE && x < 0 && y < 0) {
    		System.out.println("x and/ or y are out of bounds");
    		return null;
    	}
    	if (_gameboard[x][y] == null) {
    		System.out.println("a piece doesn't exist here");
    		return null;
    	}
    	Piece temp = _gameboard[x][y];
    	_gameboard[x][y] = null;
    	return temp;

    }
    public boolean canEndTurn() {
    	if (captured == true || hasMoved == true) {
    		return true;
    	} 
    	return false;
    }

    public void endTurn() {
    	if (this.canEndTurn() == true) {
    		fireTurn = !fireTurn;
    		captured = false;
    		hasMoved = false;
            savedPiece.doneCapturing();
    		savedPiece = null;
    		xOld = 0;
    		yOld = 0;
    		selectedPiece = false;
    		empty = false;
    	} 
    }

    public String winner() {
    	int counterFire = 0;
    	int counterWater = 0;
    	for (int i = 0; i < SIZE; i++) {
    		for (int j = 0; j < SIZE; j++) {
    			if (_gameboard[i][j] != null) {
    				if (_gameboard[i][j].isFire() == true) {
	    				counterFire++;
	    			} else {
	    				if (_gameboard[i][j].isFire() == false) {
	    				counterWater++;	
	    				}
	    			}
    			}
    		}
    	}
    	if (counterFire == 0 && counterWater == 0) {
    			return "No One";
		} else {
			if (counterFire == 0) {
    			return "Water";
    		}
    		if (counterWater == 0) {
    			return "Fire";
    		}
		}
    	return null;	
    }



}



