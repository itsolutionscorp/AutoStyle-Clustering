public class Board {

	// private Board tester;
	private int boardSize = 8;
	private Piece[][] piecesList;
	private int turnCount= 0;
	// private boolean thingSelected = false;
	private boolean hasMoved = false;
    // private Piece scratch;
    private Piece selectedPiece = null;
    private int selectX;
    private int selectY;

	public static void main(String[] args) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);


        Board b = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(b.winner() != "No one") {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                    b.selectX = x;
                    b.selectY = y;
                    b.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                // System.out.println("I got here");
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            } 
            StdDrawPlus.show(10);
        }


	}


	public Board(boolean shouldBeEmpty) {
		selectX = 0;
        selectY = 0;
        if (shouldBeEmpty) {
			piecesList = new Piece[8][8];
		} else {
			piecesList = new Piece[8][8];
			for (int i = 0; i < 8; i ++) {
	            for (int j = 0; j < 8; j++) {
	          		
	                
	                if ((i % 2 != 0) && (j == 7)) {
                    	piecesList[i][j] = new Piece(false, this, i, j, "pawn");
	                }
	                if ((i % 2 == 0) && (j == 6)) {
                    	piecesList[i][j] = new Piece(false, this, i, j, "shield");
	                }
	                if ((i % 2 != 0) && (j == 5)) {
	                    piecesList[i][j] = new Piece(false, this, i, j, "bomb");
	                }

	                if ((i % 2 == 0) && (j == 0)) {
	                    piecesList[i][j] = new Piece(true, this, i, j, "pawn");
	                }
	                if ((i % 2 != 0) && (j == 1)) {
	                    piecesList[i][j] = new Piece(true, this, i, j, "shield");
	                }
	               	if ((i % 2 == 0) && (j == 2)) {
	                    piecesList[i][j] = new Piece(true, this, i, j, "bomb");
	                }
	            }
        	}
		}
	}

	//empty board
	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    if (canSelect(selectX, selectY) && i == selectX && j == selectY) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    }
                } else                  
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    if (canSelect(selectX, selectY) && i == selectX && j == selectY) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    }

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                // if (selectX && selectY) {
                //     StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                //     StdDrawPlus.filledSquare(selectX + .5, select + .5, .5);
                // }

                if (piecesList[i][j] != null) {
                	Piece holder = pieceAt(i, j);
                    if (holder.isKing() == false) {
                    	if (holder.isShield() == true && holder.isFire() == true) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                    } else if (holder.isShield() == true && holder.isFire() == false) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                    } else if (holder.isBomb() == true && holder.isFire() == true) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                    } else if (holder.isBomb() == true && holder.isFire() == false) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                    } else if (holder.isFire() == true) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                    } else if (holder.isFire() == false) {
	                    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                    }
                    } else if (holder.isKing() == true) {
                        if (holder.isShield() == true && holder.isFire() == true) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                        } else if (holder.isShield() == true && holder.isFire() == false) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                        } else if (holder.isBomb() == true && holder.isFire() == true) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                        } else if (holder.isBomb() == true && holder.isFire() == false) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                        } else if (holder.isFire() == true) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                        } else if (holder.isFire() == false) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                        }
                    }

                    // StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                }
            }
        }
    }


    public Piece pieceAt(int x, int y) {
    	if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
    		return null;
    	}

    	if (piecesList[x][y] != null) {
    		return piecesList[x][y];
    	} else {
    		return null;
    	}
    }

    public Piece remove(int x, int y) {
       	if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
       		// System.out.println("(x, y) out of bounds");
    		return null;
    	}

    	if (piecesList[x][y] != null) {
    		Piece temp = piecesList[x][y];
    		piecesList[x][y] = null;
    		return temp;
    	} else {
    		// System.out.println("no piece at (x, y)");
    		return null;
    	}

    }

    public void place(Piece p, int x, int y) {
       	if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
       		// System.out.println("(x, y) out of bounds");
    		return;
    	}
    	if (p == null) {
    		// System.out.println("p is null");
    		return;
    	}

    	piecesList[x][y] = p;

    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        System.out.println("xi: " + xi + "yi: " + yi + "xf: " + xf + "yf: " + yf);

        int x = Math.abs(xf - xi);
        int y = Math.abs(yf - yi);

        if (selectedPiece.isKing()) {
            if (x == 1 && y == 1 && pieceAt(xf, yf) == null && selectedPiece.hasCaptured() == false) {
                return true;
            } else if (x == 2 && y == 2 && pieceAt(xf, yf) == null) {
                if (pieceAt(xi+((xf-xi)/2), yi+((yf-yi)/2)) != null) {
                    if (pieceAt(xi+((xf-xi)/2), yi+((yf-yi)/2)).side() != turnCount) {
                       return true;
                    } else {
                        return false;
                    }                    
                } else {
                    return false;
                }

            }
        } else if (selectedPiece.side() == 0) {

            if (x == 1 && y == 1 && pieceAt(xf, yf) == null && (yf > yi) && selectedPiece.hasCaptured() == false) {
                return true;
            } 
            if (x == 2 && y == 2 && pieceAt(xf, yf) == null && (yf > yi)) {
                if (pieceAt(xi+((xf-xi)/2), yi+((yf-yi)/2)) != null) {
                    if (pieceAt(xi+((xf-xi)/2), yi+((yf-yi)/2)).side() != turnCount) {
                        return true;
                    }
                } 
            return false;
            }
        } else if (selectedPiece.side() == 1) {
            if (x == 1 && y == 1 && pieceAt(xf, yf) == null && (yf < yi) && selectedPiece.hasCaptured() == false) {
                return true;
            } else if (x == 2 && y == 2 && pieceAt(xf, yf) == null && yf < yi) {
                if (pieceAt(xi+((xf-xi)/2), yi+((yf-yi)/2)) != null) {
                    if (pieceAt(xi+((xf-xi)/2), yi+((yf-yi)/2)).side() != turnCount) {
                        return true;
                    }
                } 
            return false;
            }
        }
        return false;
    }


    public boolean canSelect(int x, int y) {
        

    	Piece tempAt = pieceAt(x, y);
     //    if (tempAt != null) {
     //        selectedPiece = piecesList[x][y];
     //        selectX = x;
     //        selectY = y;
     //    }

    	//can select your own piece

        if (tempAt != null && tempAt.side() == turnCount) {
            if (selectedPiece != null && hasMoved == false) {
                // System.out.println("I got here");
                return true;
            } else if( selectedPiece == null) {
                // System.out.println("I got here");
                return true;
            }
        } else if (tempAt == null) {
            // System.out.println("I got here");
            if (selectedPiece != null && hasMoved == false && validMove(selectX, selectY, x, y)) {
                // System.out.println("I got here");
                return true;
            } else if (selectedPiece != null && selectedPiece.hasCaptured() && hasMoved && validMove(selectX, selectY, x, y)) {
                return true;
            }
        }

        return false;

    }

    public void select(int x, int y) {
        // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        // StdDrawPlus.filledSquare(x + .5, y + .5, .5);

        if (pieceAt(x, y) != null) {
            // System.out.println("x is: " + x + "  y is: " + y);
            selectedPiece = pieceAt(x, y);
        } else if (pieceAt(x, y) == null) {
            // System.out.println("I got here");
            selectedPiece.move(x, y);
            // System.out.println("x is: " + x + "  y is: " + y);
            hasMoved = true;
        }
        
    }

    public boolean canEndTurn() {
        // System.out.println("i got here");
        if (hasMoved) {
            return true;
        }
        if (selectedPiece!= null) {
            if (selectedPiece.hasCaptured()) {
                return true;
            }
        }
        return false;
    }

    public void endTurn() {
        if (turnCount == 0) {
            turnCount = 1;
            selectedPiece.doneCapturing();
            selectedPiece = null;
            hasMoved = false;
            // selectX = 0;
            // selectY = 0;
        } else if (turnCount == 1) {
            turnCount = 0;
            selectedPiece.doneCapturing();
            selectedPiece = null;
            hasMoved = false;
            // selectX = 0;
            // selectY = 0;
        }
    }

    public String winner() {
        boolean fire = false;
        boolean water = false;
        boolean empty= false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece temp = pieceAt(i, j);
                if (temp != null) {
                    if (temp.isFire() == false) {
                        water = true;
                    }
                    if (temp.isFire()) {
                        fire = true;
                    }
                }
            }
        }

        if (fire && water) {
            return null;
        }
        if (fire && !water) {
            return "Fire";
        }
        if (water && !fire) {
            return "Water";
        }
        if (!fire && !water) {
            return "No one";
        }

        return "Not an option";
    }

}