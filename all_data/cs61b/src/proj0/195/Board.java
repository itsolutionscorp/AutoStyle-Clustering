public class Board {

    private Piece[][] pieces = new Piece[8][8];
    private int selectX = -1;
    private int selectY = -1;
    private boolean selected = false;
    private boolean moved = false;
    private int turn = 0; // 0 for fire and 1 for water
    private int numFire = 0;
    private int numWater = 0;

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) initPieces();
	}


    private void initPieces() {
		for (int i = 0; i < 8; i++) {
        	for (int j = 0; j < 8; j++) {
        		if ((j == 0) && (i % 2 == 0)) {
        			pieces[i][j] = new Piece(true, this, i, j, "pawn");
        		} 
        		if ((j == 1) && (i % 2 != 0)) {
        			pieces[i][j] = new Piece(true, this, i, j, "shield");
        		}
        		if ((j == 2) && (i % 2 == 0)) {
        			pieces[i][j] = new Piece(true, this, i, j, "bomb");
        		}
        		if ((j == 7) && (i % 2 != 0)) {
        			pieces[i][j] = new Piece(false, this, i, j, "pawn");
        		} 
        		if ((j == 6) && (i % 2 == 0)) {
        			pieces[i][j] = new Piece(false, this, i, j, "shield");
        		}
        		if ((j == 5) && (i % 2 != 0)) {
        			pieces[i][j] = new Piece(false, this, i, j, "bomb");
        		}
        	}
        }
	}

	private static void drawBoard(Board b) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                
                if (b.selected && i == b.selectX && j == b.selectY) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE); 
                } else if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                if (b.pieces[i][j] != null) {
                    drawPiece(b.pieces[i][j], i, j);
                }
            }
        }
    }

    private static void drawPiece(Piece pieceToDraw, int i, int j) {
    	String side;
  		String type;
  		String kinged = "";
  		
  		if (pieceToDraw.isFire()) side = "fire";
  		else side = "water";
  		
  		if (pieceToDraw.isShield()) type = "shield";
  		else if (pieceToDraw.isBomb()) type = "bomb";
  		else type = "pawn";

  		if (pieceToDraw.isKing()) kinged = "-crowned";

    	StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-" + side + kinged + ".png", 1, 1);
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {

        int direction; 

    	if (pieceAt(xi, yi) == null) return false;
    	Piece p = pieceAt(xi, yi);

    	if (p.isFire()) direction = 1;
        else direction = -1;

        if (((xf - xi) == 1) || ((xf - xi) == -1)) {
            if ((yf - yi) == direction) {
                return true;
            }
            if ((yf - yi) == -direction) {
                if (p.isKing()) {
                    return true;
                }
            }
        }
       
		if (validCapture(xi, yi, xf, yf)) return true;
		return false;
    }

    private boolean validCapture(int xi, int yi, int xf, int yf) {
        int direction; 

        if (pieceAt(xi, yi) == null) return false;
        Piece p = pieceAt(xi, yi);

        if (p.isFire()) direction = 1;
        else direction = -1;

        if (((xf - xi) == 2) || ((xf - xi) == -2)) {
            if ((yf - yi) == 2*direction) {
                if (pieceAt(((xi + xf)/2), ((yi + yf)/2)) != null) {
                    return true;
                }
            }
            if ((yf - yi) == -2*direction) {
                if (p.isKing()) {
                    if (pieceAt(((xi + xf)/2), ((yi + yf)/2)) != null) {
                        if (pieceAt(((xi + xf)/2), ((yi + yf)/2)).side() != p.side()) {
                            return true;
                        }
                    }
                }
            }
        } 
        return false;
    }

    public Piece pieceAt(int x, int y) {
    	if (x > -1 && x < 8 && y > -1 && y < 8) {
            return pieces[x][y];
        } else {
            return null;
        }
    }

    public void place(Piece p, int x, int y) {
    	if (p != null) {
    		if (x > -1 && x < 8 && y > -1 && y < 8) {
		  		pieces[x][y] = p;
  			}
  		}
    }

    public Piece remove(int x, int y) {
    	if (x > -1 && x < 8 && y > -1 && y < 8) {
    		if (pieceAt(x, y) == null) {
    			//System.out.println("no piece to remove");
    			return null;
    		} 
	        Piece removedPiece = pieceAt(x, y);
            pieces[x][y] = null;
            //System.out.println("removing" + x);
            //System.out.println("removing" + y);
            //System.out.println("place set to null");
	    	return removedPiece;
	    }
	    return null;
    }

    public boolean canSelect(int x, int y) {
    	if (pieceAt(x, y) != null) {
            if (!selected || !moved) {
                //System.out.println(pieceAt(x, y).side());
                //System.out.println(turn);
                if (pieceAt(x, y).side() == turn) {
                    //System.out.println("first true");
                    return true;
                }
            }
    	} else {
            if (selected) {
                if (!moved) {
                    if (validMove(selectX, selectY, x, y)) {
                        //System.out.println("second true");
                        return true;
                    }
                }
                if (pieceAt(selectX, selectY)!= null) {
                    if (pieceAt(selectX, selectY).hasCaptured()) {
                        if (validCapture(selectX, selectY, x, y)) {
                            //System.out.println("third true");
                            return true;
                        }
                    }
                }
            }
        }    	
        return false;
    }

    public void select(int x, int y) {
        //System.out.println(x + ",,," + y);

        if (pieceAt(x, y) == null) {
            //System.out.println("remove" + selectX + "," + selectY);
            pieceAt(selectX, selectY).move(x, y);
            moved = true;
        } 
        selected = true;
        selectX = x;
        selectY = y;
    }


    public boolean canEndTurn() {
        //System.out.println("checking canEndTurn");
        if(moved) {
            //System.out.println("has moved or captured");
            return true;
        }

        if(pieceAt(selectX, selectY)!= null && pieceAt(selectX, selectY).hasCaptured()) {
            return true;
        }
        //System.out.println("cannot end turn");
        return false;
    }

    public void endTurn() {
        turn = 1 - turn;
        // if (turn == 0) {
        //     System.out.println("Fire's turn");
        // } else {
        //     System.out.println("Water's turn");
        // }
        selected = false;
        moved = false;
        //bombed = false;
        if (pieceAt(selectX, selectY) != null) {
            pieceAt(selectX, selectY).doneCapturing();
        }
        //System.out.println("End Turn");
    }

    public String winner() {
        numFire = 0;
        numWater = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    drawPiece(pieces[i][j], i, j);
                    if (pieces[i][j].isFire()) {
                        numFire += 1;
                    } else {
                        numWater += 1;
                    }
                }
            }
        }

        if (numFire == 0) {
            if (numWater == 0) {
                return "No one";
            } else {
                return "Water";
            }
        } else if (numWater == 0) {
            return "Fire";
        }
        return null;
    }

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
       	Board b = new Board(false);
        boolean endGame = false;
        
        while(!endGame) {
            drawBoard(b);           
            StdDrawPlus.show(100);
        
            if (StdDrawPlus.mousePressed()) {
                StdDrawPlus.show(15);
                if (b.canSelect((int)StdDrawPlus.mouseX(), (int)StdDrawPlus.mouseY())) {
                    b.select((int)StdDrawPlus.mouseX(), (int)StdDrawPlus.mouseY());
                }
            }

            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }

            if (b.winner() != null) {
                endGame = true;
            }
        }
    }
}   
