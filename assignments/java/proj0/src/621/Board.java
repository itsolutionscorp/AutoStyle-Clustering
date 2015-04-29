
public class Board{

	private static Piece [][] pieces;
	private boolean moved;
	private boolean playerFire;
	private boolean selected;
	private int xprev;
	private int yprev;
	private boolean captured;

	public Board(boolean shouldBeEmpty){
        pieces = new Piece[8][8];
		if (shouldBeEmpty){
			for (int i =0; i< 8; i++){
				for (int j = 0; j<8; j++){
					pieces[i][j]=null;
				}
			}
			//numberoffire=0;
			//numberofwater=0;
		 }
		else{
			initializeBoard();
			//numberoffire = 12;
			//numberofwater = 12;
		}
		playerFire = true;
        xprev = -1;
        yprev = -1;

	}
	private void initializeBoard(){
		for (int x = 0; x<8; x++){
			for (int y = 0; y<8; y++){
				if ( (x + y) %2 == 0 && y != 3 && y !=4){
					Piece p = choosePiece(x,y);
					pieces[x][y]= p;
				}
				else
					pieces[x][y]=null;
			}
		}

	}

	private Piece choosePiece(int x, int y){
		boolean isFire;
		String type;
		if (y <4)
			isFire = true;
		else
			isFire = false;
		if (y == 0 || y == 7)
			type = "pawn";
		else if (y == 1 || y == 6)
			type = "shield";
		else
			type = "bomb";
		Piece p = new Piece(isFire, this, x,y, type);
		return p;
	}

	/** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
	private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                //else if (i == xprev && j == yprev) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j]!=null){
                	StdDrawPlus.picture(i + .5, j + .5, getImageName(pieces[i][j]), 1, 1);
                }
            }
        }
    }

    private static String getImageName(Piece p){
    	String name = "img/";
    	if (p.isBomb())
    		name= name + "bomb-";
    	else if (p.isShield())
    		name = name + "shield-";
    	else
    		name = name + "pawn-";
    	if (p.isFire())
    		name = name + "fire";
    	else
    		name = name + "water";
    	if (p.isKing())
    		name = name + "-crowned";
    	name = name + ".png";
    	return name;
    }


    /**
    *from Josh Hug's StdDrawDemo
    */
    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        //initializeBoard();
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(b.winner()==null) {//b.winner().equals("No one")
            drawBoard(N);
            // if (b.xprev !=-1){
            //     StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            //     StdDrawPlus.filledSquare(b.xprev + .5, b.yprev + .5, .5);
            //     if (pieces[b.x][y]!=null)
            //         StdDrawPlus.picture(b.xprev + .5, b.yprev + .5, getImageName(pieces[b.xprev][b.yprev]), 1, 1);
            // }
            while (!StdDrawPlus.isSpacePressed()) {
                if (StdDrawPlus.mousePressed()){
                    int x = (int) StdDrawPlus.mouseX();
                     int y = (int) StdDrawPlus.mouseY();
                    if (b.canSelect(x,y)){
                        b.select(x,y);
                    }
                    StdDrawPlus.show(10);
                }
                drawBoard(N);
             }
             if (b.canEndTurn())
                b.endTurn();            
            StdDrawPlus.show(10);
        }
    }
    /**
    * Gets the piece at position (x, y) on the board, 
    * or null if there is no piece. 
    * If (x, y) are out of bounds, returns null.
    */
    public Piece pieceAt(int x, int y){
        if (x<0 || x >7 || y<0 || y >7)
            return null;
    	return pieces[x][y];
    } 

    public boolean canSelect(int x, int y){
    	if (x <0 || x >8 || y >8 || y<0)
    		return false;
    	if (pieceAt(x,y)!=null){ //there is a piece at x y
    		if (!selected && sameType(x, y))
    			return true;
    		if (selected && !moved && sameType(x,y))
    			return true;
    		return false;
    	}
    	else{ //the square at x y is empty
    		if (selected && !moved)
    			return validMove(x,y, false);
            else if (selected && pieceAt(xprev,yprev)!=null&& pieceAt(xprev,yprev).hasCaptured())//
                return validMove(x,y,true);
    		return false;
    	}
    }

    private boolean validMove(int x, int y, boolean twospace){
    	int xbetween = (x + xprev)/2;
    	int ybetween = (y + yprev)/2;
    	if (pieceAt(xprev,yprev).isKing()){ //for king, any 4 corners can work
    		if ((oneSpaceAwayUp(x,y)||oneSpaceAwayDown(x,y))&& !twospace)
    			return true;
    		if (twoSpaceAwayUp(x,y)||twoSpaceAwayDown(x,y)){
    			if (canJumpOver(xbetween, ybetween))
    				return true;
    		}
    		return false;
    	}
    	else if (playerFire){ //not a king and moving upwards
    		if (oneSpaceAwayUp(x,y) && !twospace)
    			return true;
    		if (twoSpaceAwayUp(x,y)&& canJumpOver(xbetween, ybetween))
    			return true;
            return false;
    	}
    	else{ //not a king and moving down
    		if (oneSpaceAwayDown(x,y)&&!twospace)
    			return true;
    		if (twoSpaceAwayDown(x,y)&& canJumpOver(xbetween, ybetween))
    			return true;
            return false;
    	}
    }

    private boolean twoSpaceAwayUp(int x, int y){
    	if (x==xprev+2 || x == xprev - 2){
    		if (y==yprev + 2){
    			return true;
    		}
    	}
    	return false;
    }

    private boolean twoSpaceAwayDown(int x, int y){
    	if (x==xprev+2 || x == xprev - 2){
    		if (y==yprev - 2){
    			return true;
    		}
    	}
    	return false;
    }

    private boolean oneSpaceAwayUp(int x, int y){
    	if (x == xprev + 1 || x == xprev -1){
    		if (y == yprev + 1)
    			return true;
    	}
    	return false;
    }

    private boolean oneSpaceAwayDown(int x, int y){
    	if (x == xprev + 1 || x == xprev -1){
    		if (y == yprev - 1)
    			return true;
    	}
    	return false;
    }

    private boolean sameType(int x, int y){
    	if (playerFire){
    		if (pieceAt(x,y).isFire()){
    			return true;
    		}
    		return false;
    	}
    	if (pieceAt(x,y).isFire()){
    		return false;
    	}
    	return true;
    }

    private boolean canJumpOver(int x, int y){
        if (pieceAt(x,y)==null)
            return false;
    	return !sameType(x,y);
    }

    public void select(int x, int y){
    	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        if (pieces[x][y]!=null)
            StdDrawPlus.picture(x + .5, y + .5, getImageName(pieces[x][y]), 1, 1);
    	if (pieceAt(x,y)==null){
            //takeCareOfCount(x,y); //keeps track of what is eaten
    		// if (twoSpaceAwayDown(x,y) || twoSpaceAwayUp (x,y)){
      //           captured= true;
      //       }
            pieceAt(xprev, yprev).move(x,y);
            place(pieceAt(xprev, yprev), x, y);
            if (pieceAt(xprev,yprev).isBomb() && pieceAt(xprev, yprev).hasCaptured()){
                //remove(xprev, yprev);
                remove(x,y);
            }
            //pieces[xprev][yprev]=null;
            remove(xprev, yprev);
    		moved = true;
    	}
    	xprev = x;
    	yprev = y;
        selected = true;
    	//selected = true;
    }

    // private void takeCareOfCount(int x, int y){
    //     int xbetween = (x + xprev)/2;
    //     int ybetween = (y + yprev)/2;
    //     if (pieceAt(xbetween, ybetween).isFire())
    //         numberoffire--;
    //     else
    //         numberofwater--;
    // }
    /**
    * Places p at (x, y). If (x, y) is out of bounds or if p is null, 
    * does nothing.
    * If p already exists in the current Board, 
    * first removes it from its initial position
    * If another piece already exists at (x, y),
    * p will replace that piece.
    */
    public void place(Piece p, int x, int y){
    	if (x <0 || x>7 || y < 0 || y >7 || p == null){
    		return; 
    	}
    	pieces[x][y]= p;
    }

    public Piece remove(int x, int y){
    	if (x>7 || y>7 || x<0 || y<0){
    		System.out.println("Coordinates are not within boundary.");
    		return null;
    	}
        if (pieceAt(x,y)!=null){
    	   Piece temp = pieceAt(x,y);
    	   pieces[x][y]=null;
    	   return temp;
        }
        return null;
    }

    public boolean canEndTurn(){
        if (moved) //|| pieceAt(xprev, yprev).hasCaptured()
    	   return true;
        return false;
    }

    public void endTurn(){
    	playerFire= !playerFire;
    	selected=false;
    	moved = false;
        if (pieceAt(xprev,yprev)!=null)
            pieceAt(xprev,yprev).doneCapturing();
        xprev = -1;
        yprev = -1;
    }

    public String winner(){
        int numberofwater=0;
        int numberoffire=0;
        for (int i =0; i<8 ; i ++){
            for (int j=0; j<8; j++){
                if (pieceAt(i,j)!=null){
                    if (pieceAt(i,j).isFire())
                        numberoffire++;
                    else
                        numberofwater++;
                }
            }
        }
        if (numberofwater==0 && numberoffire==0){
         //board has not been initiated yet
         return "No one";
        }
        if (numberofwater==0){
         return "Fire";
        }
        if (numberoffire==0){
         return "Water";
        }
        return null;
    }

}