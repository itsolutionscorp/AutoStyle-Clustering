public class Board {
	
	private Piece[][] pieces;
    private static boolean shouldBeEmpty = false;
	private int player = 0;
	private boolean hasMoved = false;
	private Piece selected = null; 
	private boolean phase1;
	private boolean phase2;
	private boolean phase3;
	private boolean phase4;
	
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
            }
        }
    }
    
    private void drawPieces(int N){
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieces[i][j] == null){
            		
            	} else if (pieces[i][j].isFire()) {
                    if(pieces[i][j].isBomb() && pieces[i][j].isKing()) {
                    	StdDrawPlus.picture(i +.5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    } else if(pieces[i][j].isBomb()){
                    	StdDrawPlus.picture(i +.5, j + .5, "img/bomb-fire.png", 1, 1);
                    } else if(pieces[i][j].isShield() && pieces[i][j].isKing()){
                    	StdDrawPlus.picture(i +.5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                    } else if(pieces[i][j].isShield()){
                    	StdDrawPlus.picture(i +.5, j + .5, "img/shield-fire.png", 1, 1);
                    } else if(pieces[i][j].isKing()){
                    	StdDrawPlus.picture(i +.5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    } else {
                    	StdDrawPlus.picture(i +.5, j + .5, "img/pawn-fire.png", 1, 1);
                    }
                } else if (pieces[i][j].isFire() == false) {
                	if(pieces[i][j].isBomb() && pieces[i][j].isKing()) {
                    	StdDrawPlus.picture(i +.5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    } else if(pieces[i][j].isBomb()){
                    	StdDrawPlus.picture(i +.5, j + .5, "img/bomb-water.png", 1, 1);
                    } else if(pieces[i][j].isShield() && pieces[i][j].isKing()){
                    	StdDrawPlus.picture(i +.5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    } else if(pieces[i][j].isShield()){
                    	StdDrawPlus.picture(i +.5, j + .5, "img/shield-water.png", 1, 1);
                    } else if(pieces[i][j].isKing()){
                    	StdDrawPlus.picture(i +.5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                    } else {
                    	StdDrawPlus.picture(i +.5, j + .5, "img/pawn-water.png", 1, 1);
                    }	           
                }
                
            }
        }
    	
    }
    
    public Board(boolean shouldBeEmpty){
    	int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new Piece[N][N];
    	if (shouldBeEmpty){

    	} else {
//          red pieces
            pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
            pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
            pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
            pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
            pieces[1][1] = new Piece(true, this, 1, 1, "shield");
            pieces[3][1] = new Piece(true, this, 3, 1, "shield");
            pieces[5][1] = new Piece(true, this, 5, 1, "shield");
            pieces[7][1] = new Piece(true, this, 7, 1, "shield");
            pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
            pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
            pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
            pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
//    		blue pieces
            pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
            pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
            pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
            pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
            pieces[0][6] = new Piece(false, this, 0, 6, "shield");
            pieces[2][6] = new Piece(false, this, 2, 6, "shield");
            pieces[4][6] = new Piece(false, this, 4, 6, "shield");
            pieces[6][6] = new Piece(false, this, 6, 6, "shield");
            pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
            pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
            pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
            pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
            
            /*pieces[3][3] = new Piece(true,this, 3, 3, "shield");
            pieces[4][4] = new Piece(false, this, 4, 4, "pawn");
            pieces[2][6] = new Piece(false, this, 2, 6, "shield");
            pieces[4][6] = new Piece(false, this, 4, 6, "bomb");*/
    	}
    
    }
    
    public static void main(String[] args) {
    	Board b = new Board(shouldBeEmpty); 
        while(true) {
        	b.drawBoard(8);
        	if (b.selected != null){
        	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        	StdDrawPlus.filledSquare(b.xVal(b.selected) + .5, b.yVal(b.selected) + .5, .5);}
        	b.drawPieces(8);
        	if (StdDrawPlus.mousePressed()) {
                double x1 = StdDrawPlus.mouseX();
                double y1 = StdDrawPlus.mouseY();
                int x = (int) x1;
                int y = (int) y1;
	            if (x > 7 || y > 7){}
	            else {
	            	if(b.canSelect(x, y)){
	                	b.select(x, y);
	                }
	            }
        	}
        	if (StdDrawPlus.isSpacePressed()){
            	if (b.canEndTurn()){
                	b.endTurn();
                }	
            }
        	b.winner();
        	StdDrawPlus.show(100);
        }
    }
	    
    public Piece pieceAt(int x, int y){
    	if (x > 7 || x < 0 || y < 0 || y > 7){
    		return null;
    	} else {
    		return pieces[x][y];
    
    	}
    }
    
    private int xVal(Piece P){
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] == P){
            		return i;
            	}
            }
    	}
    	return 0;
    }
    
    private int yVal(Piece P){
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] == P){
            		return j;
            	}
            }
    	}
    	return 0;
    }
    
    public boolean canSelect(int x, int y){
    	phase1 = selected == null && hasMoved == false;
		phase2 = selected != null && hasMoved == false;
		phase3 = selected != null && hasMoved == true;
		phase4 = selected != null && hasMoved == true && selected.hasCaptured() && 
				validMove(player, selected.isKing(), xVal(selected), yVal(selected), x, y) == true;
    	if (phase1) {
	    	if (pieceAt(x,y) != null) {
    			if (pieceAt(x,y).side() == player){
	    			return true;
	    		}
	    	}	
    	} else if (phase2) {
    		if (pieceAt(x, y) == null){
    			return validMove(player, selected.isKing(), xVal(selected), yVal(selected), x, y);
    		} else if (pieceAt(x, y) != null){
    			if (pieceAt(x, y).side() == player){
    				return true;
    			}
    		}
    	} else if (phase3) {
    		if (pieceAt(x, y) == null){
    			return validMove(player, selected.isKing(), xVal(selected), yVal(selected), x, y);
    		}
    	} else if (phase4) {
    		return true;
    	}
    	return false;
    }
              
	private boolean validMove(int player, boolean kingz, int xi, int yi, int xf, int yf){
    	Piece midpoint = pieceAt(((xi+xf)/2), ((yi+yf)/2));
		if (hasMoved == true) {
	    	if (kingz && pieces[xf][yf] == null){
	    		if (Math.abs(xi - xf)==2 && Math.abs(yi - yf)==2) {
	    			if (midpoint != null){
	        	    	if (player == 0 && midpoint.side() == 1){
		        		    return true;
	        	    	}
		        		else if (player == 1 && midpoint.side() == 0){
		        			return true;	
		        		}
	        		}
	    		}
	    	} else if (selected.side() == player && pieces[xf][yf] == null) {
	    		if (player == 0 && yi - yf == -2 && midpoint.side() == 1) {
	    			return true;
	    		} else if (player == 1 && yi - yf == 2 && midpoint.side() == 0) {
	    			return true;
	    		} 	  	
	    	}
    	} else {
    		if (kingz && pieces[xi][yi].side()==player && pieces[xf][yf]==null){
        		if (Math.abs(xi-xf)==1) {
            		return true;
            	    }            	    
        	    else if (Math.abs(xi - xf)==2 && Math.abs(yi - yf)==2) {
        	    	if (midpoint != null){
	        	    	if (player == 0 && midpoint.side() == 1){
		        		    return true;
	        	    	}
		        		else if (player == 1 && midpoint.side() == 0){
		        			return true;	
		        		}
	        		}
        	    }
        	}
        	else if (pieces[xi][yi].side()==player && pieces[xf][yf]==null) {    	    
        	    if (Math.abs(xi-xf)==1) {
        		if (player == 0 && (yi - yf == -1))
        		    return true;
        		else if (player == 1 && (yi - yf == 1))
        		    return true;
        	    }
        	    
        	    else if (Math.abs(xi - xf)==2) {
	        		if (midpoint != null){
	        	    	if (player == 0 && (yi - yf==-2) && midpoint.side() == 1){
		        		    return true;
	        	    	}
		        		else if (player == 1 && (yi - yf == 2) && midpoint.side() == 0){
		        			return true;	
		        		}
	        		}
        	    }
        	}
    	}    	  	
    	return false;		
    }
              
	public void select(int x, int y) {
		phase1 = selected == null && hasMoved == false;
		phase2 = selected != null && hasMoved == false;
		phase3 = selected != null && hasMoved == true;
		if (phase1) {
			selected = pieceAt(x, y);
		} else if (phase2) {
			if (pieceAt(x, y)!=null) {
				if (pieceAt(x, y).side() == player){
					selected = pieceAt(x, y);
				}
			}
			if (pieceAt(x, y) == null) {
					selected.move(x, y);
					if (selected.isBomb()) {
						if (selected.hasCaptured()){
							selected = null;
						}
					}
					hasMoved = true;
			}
		} else if (phase3) {
			selected.move(x, y);
		}
	}
	
	public void place(Piece p, int x, int y){
		if (x <= 7 && y <= 7 && x > -1 && y > -1) {
			pieces[x][y] = p;  
		}
	}
		
	public Piece remove(int x, int y){
		if (x <= 7 && y <= 7 && x > -1 && y > -1){	
			Piece a = pieces[x][y];
			pieces[x][y] = null;
			return a;
		}
		return null;
	}
		
	public boolean canEndTurn(){
		return hasMoved;
		/*if (hasMoved == true){
			if (selected != null){
				if(selected.hasCaptured()== true || hasMoved == true){
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			} 
		}
		return false;*/
	}
	
	public void endTurn(){
		if (player == 0){
			player = 1;
			hasMoved = false;
			if (selected != null){
				selected.doneCapturing();
			}
			selected = null; 
		} else if (player == 1){
			player = 0;
			hasMoved = false;
			if (selected != null){
				selected.doneCapturing();
			}
			selected = null; 
			
		}
	}
    		
    public String winner(){
    	int firecounter = 0;
    	int watercounter = 0;
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] == null){
            		
            	} else if (pieces[i][j].isFire()) {
                    firecounter += 1;
                } else if (pieces[i][j].isFire() == false) {
                	watercounter += 1;
                }       
            }
        }
    	
    	if (firecounter == 0 && watercounter == 0){
    		return "No one";
    	} else if (watercounter == 0){
    		return "Fire";
    	} else if (firecounter == 0){
    		return "Water";
    	} else {
    		return null;
    	}
    	
    }
    
    
}