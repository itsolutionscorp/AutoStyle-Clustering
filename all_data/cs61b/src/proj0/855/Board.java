public class Board{
	private static boolean[][] pieces;
	private boolean empty;
	private static Piece[][] checkers;
	private static Board bo = new Board(false);
	private static Piece temp;
	private static boolean fireturn = true;
	private boolean hasSelect = false;
	private static boolean hasMoved = false;
	private boolean hasSelectDest = false;
	private int tempX;
	private int tempY;
	private int destX;
	private int destY;
    private int fire;
    private int water;


	private static void drawBoard(boolean shouldBeEmpty) {
		for (int i = 0; i < checkers.length; i++) {

            for (int j = 0; j < checkers.length; j++) {
            	if (pieces[i][j]){
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                else if ((i + j) % 2 == 0){
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } 
                else{
                	 StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }                
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if ((shouldBeEmpty != true) && (checkers[i][j] != null)){
                	if ((checkers[i][j].isShield() == false) && (checkers[i][j].isBomb() == false) && (checkers[i][j].isFire() == false)){
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                	}
                	if ((checkers[i][j].isShield() == false) && (checkers[i][j].isBomb() == false) && (checkers[i][j].isFire() == true)){
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                	}
                	if ((checkers[i][j].isShield() == true) && (checkers[i][j].isFire() == false)){
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                	}
                	if ((checkers[i][j].isShield() == true) && (checkers[i][j].isFire() == true)){
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                	}
                	if ((checkers[i][j].isBomb() == true) && (checkers[i][j].isFire() == false)){
                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                	}
                	if ((checkers[i][j].isBomb() == true) && (checkers[i][j].isFire() == true)){
                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                	}      
            }
        }
    }
}

	public static void main(String args[]){
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        pieces = new boolean[8][8];
        for (int i = 0; i < checkers.length; i++) {
            		for (int j = 0; j < checkers.length; j++) {
            			pieces[i][j] = false;
            		}
            	}	

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            drawBoard(false);
            bo.winner();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
	                if ((bo.hasSelect == false) && (bo.canSelect((int) x, (int) y))){
	                	bo.select((int) x, (int) y);
	                	pieces[bo.tempX][bo.tempY] = true;
	                }
	                if ((bo.hasSelect == true) && (bo.canSelect((int) x, (int) y))){
	                	bo.select((int) x, (int) y);
	                	pieces[bo.tempX][bo.tempY] = false;
	                	pieces[(int) x][(int) y] = true;
	                }                  	
               
                	
            }
            if (StdDrawPlus.isSpacePressed()){
            	if (bo.canEndTurn()){
            		for (int i = 0; i < checkers.length; i++) {
            			for (int j = 0; j < checkers.length; j++) {
            				pieces[i][j] = false;
            			}
            		}	


      
            		bo.endTurn();
            	}
            }            
            StdDrawPlus.show(100);
        }
	}

	public Board(boolean shouldBeEmpty){
		checkers = new Piece[8][8];
		Piece a = new Piece(true, this, 0, 0, "pawn");
		Piece b = new Piece(true, this, 2, 0, "pawn");
		Piece c = new Piece(true, this, 4, 0, "pawn");
		Piece d = new Piece(true, this, 6, 0, "pawn");
		Piece e = new Piece(true, this, 1, 1, "shield");
		Piece f = new Piece(true, this, 3, 1, "shield");
		Piece g = new Piece(true, this, 5, 1, "shield");
		Piece h = new Piece(true, this, 7, 1, "shield");
		Piece i = new Piece(true, this, 0, 2, "bomb");
		Piece j = new Piece(true, this, 2, 2, "bomb");
		Piece k = new Piece(true, this, 4, 2, "bomb");
		Piece l = new Piece(true, this, 6, 2, "bomb");
		Piece m = new Piece(false, this, 1, 5, "bomb");
		Piece n = new Piece(false, this, 3, 5, "bomb");
		Piece o = new Piece(false, this, 5, 5, "bomb");
		Piece p = new Piece(false, this, 7, 5, "bomb");
		Piece q = new Piece(false, this, 0, 6, "shield");
		Piece r = new Piece(false, this, 2, 6, "shield");
		Piece s = new Piece(false, this, 4, 6, "shield");
		Piece t = new Piece(false, this, 6, 6, "shield");
		Piece u = new Piece(false, this, 1, 7, "pawn");
		Piece v = new Piece(false, this, 3, 7, "pawn");
		Piece w = new Piece(false, this, 5, 7, "pawn");
		Piece x = new Piece(false, this, 7, 7, "pawn");
        if (shouldBeEmpty != true){
                		checkers[0][0] = a;
                		checkers[2][0] = b;
                		checkers[4][0] = c;
                		checkers[6][0] = d;
                		checkers[1][1] = e;
                		checkers[3][1] = f;
                		checkers[5][1] = g;
                		checkers[7][1] = h;
                		checkers[0][2] = i;
                		checkers[2][2] = j;
                		checkers[4][2] = k;
                		checkers[6][2] = l;
                		checkers[1][5] = m;
                		checkers[3][5] = n;
                		checkers[5][5] = o;
                		checkers[7][5] = p;
                		checkers[0][6] = q;
                		checkers[2][6] = r;
                		checkers[4][6] = s;
                		checkers[6][6] = t;
                		checkers[1][7] = u;
                		checkers[3][7] = v;
                		checkers[5][7] = w;
                		checkers[7][7] = x;	 		
        }
    }

    public boolean canSelect(int x, int y){
    	if ((x < 0) || (x > 7) || (y < 0) || (y > 7)){
       		return false;
    	}

    	if ((checkers[x][y] != null) && (checkers[x][y].isFire() == fireturn) && (this.hasSelectDest == false) && ((this.hasSelect == false))){
    		return true;
    	}
    	else if ((checkers[x][y] != null) && (checkers[x][y].isFire() == fireturn) && (this.hasSelectDest == false) && ((this.hasSelect == true))){
    		if (this.hasMoved == false){
    			return true;
    		}
    	}

    	if ((checkers[x][y] == null) && (validMove(tempX, tempY, x, y)) && (this.hasSelectDest == false) && (bo.hasSelect == true)){
    		return true;
    	}
    	return false;

    } 

	private boolean validMove(int xi, int yi, int xf, int yf){
		if ((checkers[xi][yi] != null) && (checkers[xf][yf] == null)){
			if ((checkers[xi][yi].isFire() == true) && (((yi + 1) == yf) && ((xi - 1 == xf) || (xi + 1 == xf)))){
				return true;
			}
			if ((checkers[xi][yi].isFire() == false) && (((yi - 1) == yf) && ((xi - 1 == xf) || (xi + 1 == xf)))){
				return true;
			}
			if ((checkers[xi][yi].isFire() == true) && (checkers[xi][yi].isKing() == true) && ((((yi + 1) == yf) || ((yi - 1) == yf)) && ((xi - 1 == xf) || (xi + 1 == xf)))){
				return true;
			}
			if ((checkers[xi][yi].isFire() == false) && (checkers[xi][yi].isKing() == true) && ((((yi + 1) == yf) || ((yi - 1) == yf)) && ((xi - 1 == xf) || (xi + 1 == xf)))){
				return true;
			}
		
			if ((checkers[xi][yi].isFire() == true) && (pieceAt(xi + 1, yi + 1) != null) && (checkers[xi + 1][yi + 1].isFire() == false)){
				if ((yi + 2 == yf) && (xi + 2 == xf)){
					return true;
				}
			}
			if ((checkers[xi][yi].isFire() == true) && (pieceAt(xi - 1, yi + 1) != null) && (checkers[xi - 1][yi + 1].isFire() == false)){
				if ((yi + 2 == yf) && (xi - 2 == xf)){
					return true;
				}
			}
			if ((checkers[xi][yi].isFire() == false) && (pieceAt(xi + 1, yi - 1) != null) && (checkers[xi + 1][yi - 1].isFire() == true)){
				if ((yi - 2 == yf) && (xi + 2 == xf)){
					return true;
				}
			}
			if ((checkers[xi][yi].isFire() == false) && (pieceAt(xi - 1, yi - 1) != null) && (checkers[xi - 1][yi - 1].isFire() == true)){
				if ((yi - 2 == yf) && (xi - 2 == xf)){
					return true;
				}
			}
			if ((checkers[xi][yi].isFire() == true) && (checkers[xi][yi].isKing() == true) && (pieceAt(xi + 1, yi - 1) != null) && (checkers[xi + 1][yi - 1].isFire() == false)){
				if ((yi - 2 == yf) && (xi + 2 == xf)){
					return true;
				}
			}
			if ((checkers[xi][yi].isFire() == false) && (checkers[xi][yi].isKing() == true) && (pieceAt(xi + 1, yi + 1) != null) && (checkers[xi + 1][yi + 1].isFire() == false)){
				if ((yi + 2 == yf) && (xi + 2 == xf)){
					return true;
				}
			}
			if ((checkers[xi][yi].isFire() == true) && (checkers[xi][yi].isKing() == true) && (pieceAt(xi - 1, yi - 1) != null) && (checkers[xi - 1][yi - 1].isFire() == false)){
				if ((yi - 2 == yf) && (xi - 2 == xf)){
					return true;
				}
			}
			if ((checkers[xi][yi].isFire() == false) && (checkers[xi][yi].isKing() == true) && (pieceAt(xi - 1, yi + 1) != null) && (checkers[xi - 1][yi + 1].isFire() == false)){
				if ((yi + 2 == yf) && (xi - 2 == xf)){
					return true;
				}
			}
		}
		return false;
	}


	public void select(int x, int y){
		if ((x >= 0) && (x <= 7) && (y >= 0) && (y <= 7)){
			if ((checkers[x][y]) != null){
				this.hasSelect = true;
				tempX = x;
				tempY = y;
			}
			else{
				this.hasSelectDest = true;
				destX = x;
				destY = y;
				// pieces[destX][destY] = true;
		        if (checkers[tempX][tempY] != null){
		            checkers[tempX][tempY].move(destX, destY);
			        this.hasMoved = true;	
		         }	
		        
			}
    	}
		
	}

    public Piece pieceAt(int x, int y){
    	if ((x < 0) || (x > 7) || (y < 0) || (y > 7)){
    		return null;
    	}
    	if (checkers[x][y] == null){
    		return null;
    	}
    	return checkers[x][y];
    }

    public void place(Piece p, int x, int y){
    		checkers[x][y] = p;
    }

    public Piece remove(int x, int y){
    	if (pieceAt((int) x, (int) y) != null){
    		temp = checkers[x][y];
    		checkers[x][y] = null;
    		return temp;
    	}
    	if ((x < 0) || (x > 7) || (y < 0) || (y > 7)){
    		System.out.println("out of bounds.");
    		return null;
    	}
    	if (checkers[x][y] == null){
    		System.out.println("no piece there.");
    		return null;
    	}
    	return temp;
    }

    public boolean canEndTurn(){
    	if (this.hasMoved == true){
    		return true;
    	}
    	return false;
   	}

   	public void endTurn(){
   		if (fireturn == true){
   			fireturn = false;
   		}
   		else if (fireturn == false){
   			fireturn = true;
   		}
		hasSelect = false;
		hasMoved = false;
		hasSelectDest = false;
		tempX = 0;
		tempY = 0;
		destX = 0;
		destY = 0;

   	}

    public String winner(){
        fire = 0;
        water = 0;
        for (int i = 0; i < checkers.length; i++){
            for (int j = 0; j < checkers.length; j++){
                if ((checkers[i][j] != null) && (checkers[i][j].isFire() == true)){
                    fire += 1;
                }
                if ((checkers[i][j] != null) && (checkers[i][j].isFire() == false)){
                    water += 1;
                }
            }
         }
         if ((water == 0) && (fire != 0)){
            return "Fire";
         }
         if ((fire == 0) && (water != 0)){
            return "Water";
         }
         if ((water == 0) && (fire == 0)){
            return "No One";
         }
         return null;

    }
}
