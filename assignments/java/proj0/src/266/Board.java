public class Board {

	private static Piece[][] pieces;
	private boolean[][] selected;
	private boolean empty;
	private String turns = "fire";
	private int moves = 0;
    private boolean[][] multi;
    private Piece called;
	public Board (boolean shouldBeEmpty) {
		empty = shouldBeEmpty;
		pieces = new Piece[8][8];
		selected = new boolean[8][8];
        multi = new boolean[8][8];
		if (empty == false) {
	        for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	                if (j == 2 && (i== 0 || i== 2|| i== 4|| i==6)) {
	                	place(new Piece(true, this, i, j, "bomb"), i, j);
	                }
	                if (j == 5 && (i== 1 || i== 3|| i== 5|| i==7)) {
	                	place(new Piece(false, this, i, j, "bomb"), i, j);
	                }
	                if (j == 0 && (i== 0 || i== 2|| i== 4|| i==6)) {
	                	place(new Piece(true, this, i, j, "pawn"), i, j);
	                }
	                if (j == 7 && (i== 1 || i== 3|| i== 5|| i==7)) {
	                	place(new Piece(false, this, i, j, "pawn"), i, j);
	                }
	                if (j == 1 && (i== 1 || i== 3|| i== 5|| i==7)) {
	                	place(new Piece(true, this, i, j, "shield"), i, j);
	                }
	                if (j == 6 && (i== 0 || i== 2|| i== 4|| i==6)) {
	                	place(new Piece(false, this, i, j, "shield"), i, j);
	                } 
	            }           	
            }  
        }		
	}
	public void place(Piece p, int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return; 
		}
		pieces[x][y] = p;
	}
	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		return pieces[x][y];
	}
    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece current = pieces[i][j];
                if (current != null) {
                	if (current.isBomb()) {
                		if (current.isFire()) {
                			if (current.isKing()) {
                				StdDrawPlus.picture(i +.5, j+.5, "img/bomb-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i +.5, j + .5, "img/bomb-fire.png", 1, 1);
                			}
                		}
                		else {
                			if (current.isKing()) {
                			StdDrawPlus.picture(i +.5, j+.5, "img/bomb-water-crowned.png", 1, 1);
	                		}
	                		else {
	                			StdDrawPlus.picture(i +.5, j + .5, "img/bomb-water.png", 1, 1);
	                		}        
	                	}        		

                	}
                	else if (current.isShield()) {
                		if (current.isFire()) {
                			if (current.isKing()) {
                				StdDrawPlus.picture(i +.5, j+.5, "img/shield-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i +.5, j + .5, "img/shield-fire.png", 1, 1);
                			}
                		}
                		else {
                			if (current.isKing()) {
                			StdDrawPlus.picture(i +.5, j+.5, "img/shield-water-crowned.png", 1, 1);
	                		}
	                		else {
	                			StdDrawPlus.picture(i +.5, j + .5, "img/shield-water.png", 1, 1);
	                		}
                		}                		
                	}
                	else if (current.isFire()) {
                		if (current.isKing()) {
                			StdDrawPlus.picture(i +.5, j+.5, "img/pawn-fire-crowned.png", 1, 1);
                		}
                		else {
                			StdDrawPlus.picture(i +.5, j + .5, "img/pawn-fire.png", 1, 1);
                		}
                	}
                	else {
                		if (current.isKing()) {
                			StdDrawPlus.picture(i +.5, j+.5, "img/pawn-water-crowned.png", 1, 1);
                		}
                		else {
                			StdDrawPlus.picture(i +.5, j + .5, "img/pawn-water.png", 1, 1);
                		}
                	}

                }
            }
        }
    }
    public boolean canSelect(int x, int y) {
    	int xpos = 0;
    	int ypos = 0;
    	Piece current = pieces[x][y];
        if (moves == 2) {
            return false;
        }
        else if (moves == 3) {
            for (int a = 0; a < 8; a++) {
                for (int b = 0; b < 8; b++) {
                    if ((multi[a][b]) == true) {
                        if (a == x && b == y) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    	else if (current != null) {
    		if (current.isFire() && turns == "fire" || current.isFire() == false && turns =="water") {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	else {
    		for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
            		if (selected[i][j] == true) {
            			xpos = i;
            			ypos = j;
            			break;
            		}
            	}
            }
            if (validMove(xpos, ypos, x, y)) {
            	return true;
            }
    		else {
    			return false;
    		}
	    }
	}
    public void select (int x, int y) {
    	if (pieces[x][y] != null) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (selected[i][j] == true) {
                        selected[i][j] = false; 
                    }
                }
            }           
    		selected[x][y] = true;
    		moves = 1;
    	}
    	else {
    		for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
            		if (selected[i][j] == true) {
            			selected[i][j] = false;
            			pieces[i][j].move(x, y);
                        if (pieceAt(x, y) != null) {
                            called = pieceAt(x,y);
                        }
                        if (pieces[x][y] != null && called.hasCaptured()) {
                            if (pieces[x][y].isKing()) {
                                if (validCapture(x, y, x+1, y+1) && validMove(x, y, x+2, y+2)) {
                                    moves = 3;
                                    selected[x][y] = true;
                                    multi[x+2][y+2] = true;                                    
                                }
                                else if (validCapture(x,y,x-1,y+1) && validMove(x, y, x-2,y+2)) {
                                    moves = 3;
                                    selected[x][y] = true;
                                    multi[x-2][y+2] = true;                                    
                                }
                                else if (validCapture(x,y,x+1,y-1) && validMove(x, y, x+2, y-2)) {
                                    moves = 3;
                                    selected[x][y] = true;
                                    multi[x+2][y-2] = true;                                    
                                }
                                else if (validCapture(x,y,x-1,y-1) && validMove(x, y, x-2, y-2)) {
                                    moves = 3;
                                    selected[x][y] = true;
                                    multi[x-2][y-2] = true;                                    
                                }
                                else {
                                    moves = 2;
                                }
                            }
                            else if (pieces[x][y].isFire()) {
                                if (validCapture(x, y, x+1, y+1) && validMove(x, y, x+2, y+2)) {
                                    moves = 3;
                                    selected[x][y] = true;
                                    multi[x+2][y+2] = true;                                    
                                }
                                else if (validCapture(x,y,x-1,y+1) && validMove(x, y, x-2,y+2)) {
                                    moves = 3;
                                    selected[x][y] = true;
                                    multi[x-2][y+2] = true;                                    
                                }
                                else {
                                    moves = 2;
                                }
                            }
                            else if (pieces[x][y].isFire() == false) {
                                if (validCapture(x,y,x+1,y-1) && validMove(x, y, x+2, y-2)) {
                                    moves = 3;
                                    selected[x][y] = true;
                                    multi[x+2][y-2] = true;                                    
                                }
                                else if (validCapture(x,y,x-1,y-1) && validMove(x, y, x-2, y-2)) {
                                    moves = 3;
                                    selected[x][y] = true;
                                    multi[x-2][y-2] = true;                                    
                                }
                                else {
                                    moves = 2;
                                }
                            }
                            else {
                                moves = 2;
                            }
                        }    
                        else {
                            moves = 2;
                        }                
            		}
            	}
            }
    	}
    }

    private boolean validCapture(int xi, int yi, int xf, int yf) {
        if (xf > 7 || xf < 0 || yf > 7 || yf < 0) {
            return false;
        }
        if (pieceAt(xf, yf) != null) {
            if (turns == "fire" && pieceAt(xf, yf).isFire() == false) {
                return true;
        }
            else if (turns == "water" && pieceAt(xf, yf).isFire() == true) {
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
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (xf > 7 || xf < 0 || yf > 7 || yf < 0) {
            return false;
        }
    	if (Math.abs(xf - xi) == 2) {
            if (pieceAt(xi, yi) != null) {
                if (pieceAt(xi, yi).isKing()) {
                    if (pieceAt(xi+1, yi+1) != null && (xi+2<=7 && yi+2 <=7) && (pieceAt(xi + 2, yi+ 2) == null)) {
                        return true;
                    }
                    else if (pieceAt(xi-1, yi +1) != null && (xi-2>=0 &&yi+2<=7) &&(pieceAt(xi -2, yi+2) == null)) {
                        return true;
                    }
                    else if (pieceAt(xi+1, yi-1) != null && (xi+2 <= 7 && yi-2 >=0) && pieceAt(xi + 2, yi - 2) == null) {
                        return true;
                    }
                    else if (pieceAt(xi-1, yi-1) != null && (xi -2 >=0 && yi-2 >= 0) && pieceAt(xi - 2, yi-2) == null) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
        		else if (pieceAt(xi,yi).isFire() == true) {
        			if (pieceAt(xi+1, yi+1) != null && (xi+2<=7 && yi+2 <=7) && (pieceAt(xi + 2, yi+ 2) == null)) {
        				return true;
        			}
                    else if (pieceAt(xi-1, yi +1) != null && (xi-2>=0 &&yi+2<=7) &&(pieceAt(xi -2, yi+2) == null)) {
                        return true;
                    }
        			else {
        				return false;
        			}
        		}
        		else{
        			if (pieceAt(xi+1, yi-1) != null && (xi+2 <= 7 && yi-2 >=0) && pieceAt(xi + 2, yi - 2) == null) {
        				return true;
        			}
                    else if (pieceAt(xi-1, yi-1) != null && (xi -2 >=0 && yi-2 >= 0) && pieceAt(xi - 2, yi-2) == null) {
                        return true;
                    }
        			else {
        				return false;
        			}
        		}
            }
            else {
                return false;
            }
    	}
    	else if (Math.abs(xf-xi) == 1) {
            if (pieceAt(xi, yi).isKing()) {
                if ((Math.abs(xf - xi) == 1 && yf - yi == 1) || (Math.abs(xf - xi) == 1 &&  yi - yf == 1)) {
                    return true;
                }
                else {
                    return false;
                }
            }
	    	else if (pieceAt(xi,yi).isFire() == true) {
	    		if (Math.abs(xf - xi) == 1 && yf - yi == 1) {
	    			return true;
	    		}
	    		else {
	    			return false;
	    		}
	    	}
	    	else {
	    		if (Math.abs(xf - xi) == 1 &&  yi - yf == 1) {
	    			return true;
	    		}
	    		else {
	    			return false;
	    		}
	    	}
	    }
	    else {
	    	return false;
	    }
    }
    public boolean canEndTurn() {
    	if (moves == 2 || moves == 3) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public String winner() {
    	int fire = 0;
    	int water = 0;
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	        	if (pieceAt(i, j) != null) {
		    		if (pieceAt(i, j).side() == 0) {
		    			fire += 1;
		    		}
		    		else {
		    			water += 1;
		    		}
		    	}
	    	}
    	}
    	if (fire > water && water == 0) {
    		return "Fire";
    	}
    	if (water > fire && fire == 0) {
    		return "Water";
    	}
    	if (water == fire && fire == 0) {
    		return "No one";
    	}
    	else {
    		return null;
    	}
    }
    public Piece remove(int x, int y) {
    	if (x > 7 || y > 7) {
    		System.out.println("Out of bounds");
    		return null;
    	}
    	Piece removed = pieceAt(x, y);
    	if (removed == null) {
    		System.out.println("No piece to remove");
    		return null;
    	}
    	pieces[x][y] = null;
    	return removed;
    }
    public void endTurn() {
        called.doneCapturing();
    	if (turns == "fire") {
    		turns = "water";
    		moves = 0;

    	}
    	else {
    		turns = "fire";
    		moves = 0;
    	}
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);    
        Board b = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);
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