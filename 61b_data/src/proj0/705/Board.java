public class Board{
	// location of pieces//

    private Piece[][] pieces;
    private int numoffire;
    private int numofwater;
    private static Board b;
    private int N;
    private boolean turn;
    private boolean move; 
    private Piece selected;
    private int sx;
    private int sy; 
    private boolean bombexploded;
    /** Got significant debugging help from Daniel Choi and Jennifer Wu */


    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    public Board(boolean shouldBeEmpty) {
        /* true occurs when its fire's turn */
            turn = true;
        /* move is true when player moved a piece */
            move = false;
            bombexploded = false;
            selected= null;
            sx = 8;
            sy = 8;
            N = 8;
            pieces = new Piece[N][N];
            if (shouldBeEmpty) {
                numoffire = 0;
                numofwater = 0;
            }
            else {
                numoffire = 12;
        	   numofwater = 12;
			for (int i = 0; i<N; i++) {
            	for (int j=0; j<N; j++) {
                if (j==0 && i %2==0) {
                    pieces[i][j] = new Piece(true, this, i, j, "pawn");


                }else if (j==1 && i%2!=0){
                    pieces[i][j] = new Piece(true, this, i, j, "shield");

                }else if (j==2 && i%2==0){
                    pieces[i][j] = new Piece(true, this, i, j, "bomb");

                }else if (j==5 && i%2!=0){
                    pieces[i][j] = new Piece(false, this, i, j, "bomb");


                }else if (j==6 && i%2==0){
                    	pieces[i][j] = new Piece(false, this, i, j, "shield");


                }else if (j==7 && i%2!=0){
                   pieces[i][j]= new Piece(false, this, i, j, "pawn");

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
                if (b.pieces[i][j] != null) {
                	Piece one = b.pieces[i][j];
                	if (b.selected!= null && one == b.selected) {
                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	}
                	if (one.isFire() && one.isKing() && one.isBomb() ) {
                    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                	}else if (one.isFire() && one.isKing() && one.isShield()) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                	}else if (one.isFire() && one.isKing()) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                	}else if (one.isFire()  && one.isShield()) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                	}else if (one.isFire()  && one.isBomb()) {
                	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                	}else if (one.isFire()) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
					}else if (one.isKing() && one.isBomb()) {
                	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                	}else if (one.isKing() && one.isShield()) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                	}else if (one.isKing()) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                	}else if (one.isShield()) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                	}else if (one.isBomb()) {
                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                	}else  {
                		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                	}

                }
            }
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		b = new Board(false);


        while(b.winner()==null) {
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int)x, (int) y)) {
                	b.select((int) x, (int) y);
                }
            }
         	
                

                        
            StdDrawPlus.show(100);
            drawBoard(N);
        }

    }

    public Piece pieceAt(int x, int y) {
    	if (x>N-1 || y>N-1 || x<0 || y<0) {
    		return null;
    	}
    	else {
    		return pieces[x][y];
    	}
    }
    public void place(Piece p, int x, int y) {
    	if (x<0 || y < 0 || x> N-1 || y> N-1 || p==null) {
    	}
        else {
            // replacing a piece with a new piece p //
            if (pieces[x][y]!=null) {
                remove(x, y);
                pieces[x][y] = p;
                counter(p);
            }
            // placing a new piece //
            else if (selected == null) {
                pieces[x][y] = p;
                counter(p);
            }
            // moving a piece//
            else  {
                move = true;
                remove(sx, sy);
                pieces[x][y] = p; 
                counter(p);
                p.move(x, y);
                if (p.isBomb() && p.hasCaptured()) {
                    bombexploded = true;
                }
        }
    }

    }

    private void counter(Piece p ) {
        if (p==null) {
        }
        else if (p.isFire()) {
            numoffire +=1;
        }
        else if (p.side()==1) {
            numofwater +=1;
        }

    }


    public boolean canSelect(int x, int y) {
        if (bombexploded) {
            return false;
        }
    	if (pieces[x][y] != null &&pieces[x][y].side() == 0 && turn == true) {
            if (selected == null) {
                return true;
            }
            else if (move == false) {
                return true;
            }
        }
        if (pieces[x][y] != null && (pieces[x][y].side() == 1) && turn == false) {
            if (selected == null) {
                return true;
            }
            if (move == false) {
                return true;
            }
        }
        else if (pieces[x][y] == null ) {
            if (selected!=null && sx <8 && validMove(sx, sy, x, y)) {
                return true;
            }
        }
        return false;
    
    }

    public void select(int x, int y) {

    	if (pieces[x][y] == null) {
            place(selected, x, y);
        }
        
            selected = pieces[x][y];
            sx = x;
            sy = y;
        
    }


    public Piece remove(int x, int y) {
    	if ((x+y)%2 !=0 || x<0 || y<0 || x>N-1 || y>N-1) {
    		System.out.println("The coordinates are out of bounds");
 			return null;
    	}
    	else if (pieces[x][y] == null){
    		System.out.println("There is no piece at (" + x + ", " + y + ")" );
    		return null;
    	}
    	else {
            boolean save = pieces[x][y].isFire();
    		Piece removed = pieces[x][y];
    		pieces[x][y] = null;
            if (save) {
                numoffire -= 1;
            }
            else {
                numofwater -= 1;
            }
    		return removed;

    	}
    }

    public boolean canEndTurn() {
    	if (move ||( selected != null && selected.hasCaptured()) || bombexploded) {
    		return true;
    	}
    	else {

    		return false;
    	}
    }

    public void endTurn() {
    	if (turn){
    		turn = false;
    	}
    	else { 
            turn = true;
    	}
        if (selected != null) {
        selected.doneCapturing();
        }
        bombexploded = false;
    	move = false;
    	selected = null;
        sx = 8;
        sy = 8;
    }

 public String winner() {
        if (numoffire==0 && numofwater==0 ) {
            return "No one";
        }               
        else if (numofwater == 0) {         
            return "Fire";               
        }               
        else if (numoffire == 0) {              
            return "Water";
        }

        else return null;


    }
    

    private boolean validMove(int xi, int yi, int xf, int yf) {
    	int dx = Math.abs(xf-xi);
    	int dy = Math.abs(yf-yi);

    	if (dx != dy) {
    		return false;
    	}
    	else if (xf > N-1 || xf <0 || yf>N-1 || yf <0) {
    		return false;
    	}
    	else if ((xf+yf) %2 !=0) {
    		return false; 
    	}
        else if (pieces[xi][yi].side()==0 && pieces[xi][yi].isKing() ==false && yf<yi) {
            return false;
                }
        else if (pieces[xi][yi].side()==1 && pieces[xi][yi].isKing() ==false && yf>yi) {
            return false;
                }
        else if (move && dx == 1) { 

            return false;}
        else if (pieces[xi][yi] == null) {
            return false;
        }
    	else if (dx >0 && dx <=2) {
            if (dx == 1) {
                if ( pieces[xf][yf] == null) {
    			     return true;
    		    } else { 
                    return false;
                }
            }
            else if (dx ==2) {
                Piece midpoint = pieces[(int) (xi+xf)/2][(int) (yi+yf)/2];
     			if (midpoint==null) {
    				return false;
    			} 
                else if (midpoint.isFire() && turn==true)  {
                    return false;
                }
                else if (midpoint.side()==1 && turn == false) {
                    return false;
                }
                else {
                    return true;
                }   				
    		}
        }
        return false;  	
    }

}