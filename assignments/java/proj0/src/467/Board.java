public class Board {
	//some of this is copied from StdDrawDemo.java
	private boolean[][] pieces;
	private Piece[][] p2;
	private boolean fireTurn;
	private boolean hasSelected;
	private Piece selectedPiece;
    private int selectedPieceX;
    private int selectedPieceY;
	private boolean movedThisTurn;  
	private void drawBoard(int N) {
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (p2[i][j] != null) {
                	if (p2[i][j].isKing() == false) {
		                if (p2[i][j].isFire() == true) {
		                	if (p2[i][j].isBomb() == false && p2[i][j].isShield() == false) {
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
		                	}
		                	if (p2[i][j].isShield() == true) {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
		                	}
		                	if (p2[i][j].isBomb() == true) {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
		                	}
		                }
		                if (p2[i][j].isFire() == false) {
		                	if (p2[i][j].isBomb() == false && p2[i][j].isShield() == false) {
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
		                	}
		                	if (p2[i][j].isShield() == true) {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
		                	}
		                	if (p2[i][j].isBomb() == true) {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
		                	}
	                	}
                	}
                	if (p2[i][j].isKing() == true){
                		if (p2[i][j].isFire() == true) {
		                	if (p2[i][j].isShield() == false && p2[i][j].isBomb() == false) {
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
		                	}
		                	if (p2[i][j].isShield() == true) {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
		                	}
		                	if (p2[i][j].isBomb() == true) {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
		                	}
		                }
		                if (p2[i][j].isFire() == false) {
		                	if (p2[i][j].isShield() == false && p2[i][j].isBomb() == false) {
		                    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
		                	}
		                	if (p2[i][j].isShield() == true) {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
		                	}
		                	if (p2[i][j].isBomb() == true) {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
		                	}
	                	}
                	}
                }
        	} 
    	}
    }

	
	public Board(boolean shouldBeEmpty) {
		int n = 8;
		p2 = new Piece[n][n];
		pieces = new boolean[n][n];
		fireTurn = true;
		hasSelected = false;
		if (shouldBeEmpty == false) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (j == 0) {
						if (i % 2 == 0) {
							p2[i][j] = new Piece(true, this, i, j, "pawn");
							pieces[i][j] = true;
						}
					}
					else if (j == 1) {
						if (i % 2 == 1) {
							p2[i][j] = new Piece(true, this, i, j, "shield");
							pieces[i][j] = true;
						}
					}
					else if (j == 2) {
						if (i % 2 == 0) {
							p2[i][j] = new Piece(true, this, i, j, "bomb");
							pieces[i][j] = true;
						}
					}
					else if (j == 5) {
						if (i % 2 == 1) {
							p2[i][j] = new Piece(false, this, i, j, "bomb");
							pieces[i][j] = true;
						}
					}
					else if (j == 6) {
						if (i % 2 == 0) {
							p2[i][j] = new Piece(false, this, i, j, "shield");
							pieces[i][j] = true;
						}
					}
					else if (j == 7) {
						if (i % 2 == 1) {
							p2[i][j] = new Piece(false, this, i, j, "pawn");
							pieces[i][j] = true;
						}
					}
					else {
						p2[i][j] = null;
						pieces[i][j] = false;
					}
				}
			}
		}
	}

	//copied from StdDrawDemo.java 
	public static void main(String[] args) {
        int N = 8;
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(b.winner() == null) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y) == true) {
                    b.select(x, y);
                    StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn() == true) {
                    b.endTurn();
                }
            }            
            StdDrawPlus.show(100);
        }
        b.winner();
    }
    public Piece pieceAt(int x, int y) {
    	int n = p2.length;
    	if (x >= n || x < 0) {
    		return null;
    	}
    	if (y >= n || y < 0) {
    		return null;
    	}
    	else {
    		if (pieces[x][y] == false) {
    			return null;
    		}
    		return p2[x][y];
    	}
    }
    public boolean canSelect(int x, int y) {
    	Piece p = p2[x][y];
    	if (p != null) {
            if (p.isFire() != fireTurn) {
                return false;
            }
            else {
                if (hasSelected == false) {
                    return true;
                }
                else if (hasSelected == true) {
                    if (movedThisTurn == false) {
                        return true;
                    }
                    if (movedThisTurn == true || selectedPiece.hasCaptured() == true) {
                            return false;
                    }
                }
            }
    	}
    	if (p == null) {
    		if (hasSelected == false) {
    			return false;
    		}
    		//if (hasSelected == true) {
            else {
    			if ((int) java.lang.Math.abs(selectedPieceX - x) != (int) java.lang.Math.abs(selectedPieceY - y)) {
    				return false;
    			}
                else if (((int) java.lang.Math.abs(selectedPieceX - x)) > 2 || ((int) java.lang.Math.abs(selectedPieceY - y)) > 2) {
                    return false;
                }
    			else if (selectedPiece.isKing() == false && selectedPiece.isFire() == true) {
    				if (selectedPieceY > y) {
    					return false;
    				}
    			}
    			else if (selectedPiece.isKing() == false && selectedPiece.isFire() == false) {
    				if (selectedPieceY < y) {
    					return false;
    				}
    			} 
    			else if ((int) java.lang.Math.abs(selectedPieceX - x) == 1 && (int) java.lang.Math.abs(selectedPieceY - y) == 1) {
    				if (movedThisTurn == true || selectedPiece.hasCaptured() == true) {
    					return false;
    				}
    				return true;  
    			}
    			if (java.lang.Math.abs(selectedPieceX - x) == 2 && java.lang.Math.abs(selectedPieceY - y) == 2) {
                // else if (selectedPieceX - x == 2 || x - selectedPieceX == 2) {
    				//if spot you're skipping over is empty, return false
                    int intPieceX = (int) ((x - selectedPieceX) / 2 + selectedPieceX);
                    int intPieceY = (int) ((y - selectedPieceY) / 2 + selectedPieceY);
    				Piece intPiece = p2[intPieceX][intPieceY];
    				if (intPiece == null) {
    					return false;
    				}
    				//if spot you're skipping over is same type as your piece, return false
    				else if (intPiece.isFire() == selectedPiece.isFire()) {
    					return false;
    				}
    				else {
    					return true;
    				}
    				//doesn't matter if you've already captured this turn
    			}
    			return true;
    		}
    	}
    	return true;
    }
    public void select(int x, int y) {
		if (selectedPiece == null && p2[x][y] != null) {
			selectedPiece = p2[x][y];
            selectedPieceX = x;
            selectedPieceY = y;
			hasSelected = true;
		}
		else if (selectedPiece != null && p2[x][y] == null) {
            selectedPiece.move(x, y);
		}
        else if (selectedPiece != null && p2[x][y] != null) {
            selectedPiece = p2[x][y];
            selectedPieceX = x;
            selectedPieceY = y;
            hasSelected = true;
            //other case taken care of in a previous else if statement
        }
    }
    public void place(Piece p, int x, int y) {
    	int n = p2.length;
    	if (x < n && x >= 0 && y < n && y >= 0) {
            if (pieces[x][y] == true) {
    	       remove(x, y);
            }   	   
            p2[x][y] = p;
    	    pieces[x][y] = true;
    	    movedThisTurn = true;
        }
        else {
            ;
        }
    }
    public Piece remove(int x, int y) {
    	int n = p2.length;
    	if (x >= n || x < 0) {
    		System.out.println("Off of the board!");
    		return null;
    	}
    	else if (y >= n || y < 0) {
    		System.out.println("Off of the board!");
    		return null;
    	}
    	else {
    		if (p2[x][y] == null) {
    			System.out.println("No piece to remove!");
    			return null;
    		}
    		else {
    			Piece pi = p2[x][y];
    			pieces[x][y] = false;
    			p2[x][y] = null;
    			return pi;
    		}
    	}
    }
    public boolean canEndTurn() {
        //not true in case of bomb explosion!!!
        // if (hasSelected == false || selectedPiece == null) {
        //     System.out.println("selectedPiece" + selectedPieceX);
        //     return false;
        // }

    	if (movedThisTurn == true) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public void endTurn() {
    	//change turns
    	hasSelected = false;
        if (selectedPiece != null) {
    	   selectedPiece.doneCapturing();
        }
    	selectedPiece = null;
    	movedThisTurn = false;
    	if (fireTurn == true) {
    		fireTurn = false;
    	}
    	else {
    		fireTurn = true;
    	}
    }
    public String winner() {
    	boolean remainingFire = false;
    	boolean remainingWater = false;
    	for (int i = 0; i < p2.length; i++) {
    		for (int j = 0; j < p2.length; j++) {
                if (p2[i][j] != null) {
        			if (p2[i][j].isFire() == true) {
        				remainingFire = true;
        			}
        			if (p2[i][j].isFire() == false) {
        				remainingWater = true;
        			}
                }
    		}
    	}
    	if (remainingFire == true && remainingWater == true) {
    		return null;
    	}
    	else if (remainingFire == true) {
            return "Fire";
        }
        else if (remainingWater == true) {
            return "Water";
        }
        else {
            return "No one";
        }
    }
}