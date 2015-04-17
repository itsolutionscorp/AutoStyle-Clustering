
public class Board {

    private boolean empty;
    private int side;
    private boolean moved;
    private Piece selected;
    private int selectedx;
    private int selectedy;
    private Piece[][] pieces;
    private int highlightx;
    private int highlighty;
	
    public Board(boolean shouldBeEmpty) {
	empty = shouldBeEmpty;
	pieces = new Piece[8][8];

	if (empty == false) {
	    for (int i = 0; i < 4; i++) {
		pieces[(i*2)][0] = new Piece(true, this, 2*i, 0, "pawn-fire");
	    }
	    for (int j = 0; j < 4; j++) {     
		pieces[(j*2 + 1)][1] = new Piece(true, this, 2*j+1, 1, "shield-fire");
	    }
	    for (int k = 0; k < 4; k++) {
		pieces[(k*2)][2] = new Piece(true, this, 2*k, 2, "bomb-fire");
	    }
	    
	    for (int i = 0; i < 4; i++) {
		pieces[(i*2+1)][7] = new Piece(false, this, 2*i+1, 7, "pawn-water");
	    }
	    for (int j = 0; j < 4; j++) {     
		pieces[(j*2)][6] = new Piece(false, this, 2*j, 6, "shield-water");
	    }
	    for (int k = 0; k < 4; k++) {
		pieces[(k*2+1)][5] = new Piece(false, this, 2*k+1, 5, "bomb-water");
	    }
	}
	highlightx = -1;
	highlighty = -1;
	selectedx = -1;
	selectedy = -1;
	side = 0;
	selected = null;
	moved = false;
        //drawBoard(); 
	//a la de da comment
    }


    private void drawBoard() {
	StdDrawPlus.setXscale(0,8);
	StdDrawPlus.setYscale(0,8);

	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		if ((i+j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		else		    StdDrawPlus.setPenColor(StdDrawPlus.RED);
		StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		//if (highlightx != -1 && highlighty != -1) {
		  //  StdDrawPlus.filledSquare( i + 0.5, j + 0.5, 0.5);
               // }
	    }
	}

	if (highlightx != -1 && highlighty != -1) {
	     StdDrawPlus.filledSquare(highlightx + 0.5, highlighty + 0.5, 0.5);
        }


	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		//System.out.println(pieces[i][j].ptype);
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire() == true) {
                        if (pieces[i][j].isKing() == true) {
                            if (pieces[i][j].isBomb() == true) {
                                StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-fire-crowned.png",1,1);
                            } else if (pieces[i][j].isShield() == true) {
                                StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-fire-crowned.png",1,1);
                            } else {
                                StdDrawPlus.picture(i+0.5,j+0.5,"img/pawn-fire-crowned.png",1,1);
                            }
                        } else {
                            if (pieces[i][j].isBomb() == true) {
                                StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-fire.png",1,1);
                            } else if (pieces[i][j].isShield() == true) {
                                StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-fire.png",1,1);
                            } else {
                                StdDrawPlus.picture(i+0.5,j+0.5,"img/pawn-fire.png",1,1);
                            }
                        }
                    } else {
                        if (pieces[i][j].isKing() == true) {
                            if (pieces[i][j].isBomb() == true) {
                                StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-water-crowned.png",1,1);
                            } else if (pieces[i][j].isShield() == true) {
                                StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-water-crowned.png",1,1);
                            } else {
                                StdDrawPlus.picture(i+0.5,j+0.5,"img/pawn-water-crowned.png",1,1);
                            }
                        } else {
                            if (pieces[i][j].isBomb() == true) {
                                StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-water.png",1,1);
                            } else if (pieces[i][j].isShield() == true) {
                                StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-water.png",1,1);
                            } else {
                                StdDrawPlus.picture(i+0.5,j+0.5,"img/pawn-water.png",1,1);
                            }
                        }
                    }		
                }
	    }
	}
    }    
    /*private void printBoard() {
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		if (pieces[i][j] == null) {
		    System.out.print("   ");
		}else {
		    System.out.print(pieces[i][j].xpos);
		    System.out.print(pieces[i][j].ypos);
		    System.out.print(" ");
		}
	    }
	    System.out.println();
	}
    }*/
    
    public Piece pieceAt( int x, int y) {
        if ((x >= 0 && x <=7) && (y >= 0 && y <= 7)) {
	    if (pieces[x][y] != null) {
		//System.out.println("Piece!");
	    	return pieces[x][y];
	    } else {
		return null;
	    }
	} else {
	    //System.out.println("No piece...");
	    return null;
	}
    }
	
    public boolean canSelect(int x, int y)
    {
        if ((x >= 0 && x <=7) && (y >= 0 && y <= 7))
	{
	    if (moved == true && pieceAt(selectedx,selectedy) == null)
	    { // This means a bomb went off, so you can't select anything anymore
	        return false;
	    }
	    if (pieceAt(x,y) != null) {    
		if (pieceAt(x,y).side() == side && selected == null)
	        {
		    return true;
		} else if (pieceAt(x,y).side() == side && moved == false)
	        {
		    return true;
		}
		else
		{
		    return false;
		}
  	    } else if (pieces[x][y] == null)
	        {
		if (selected == null) {
		    return false;
		}
		else if (moved == false && validMove(selectedx,selectedy,x,y) == true)
	        {
		    return true;
		} else if (selected.hasCaptured() == true && validMove1(selectedx,selectedy,x,y))
	        {
		    return true;
		} else
	        {
		    return false;
		}
	    } else
	        {
		    return false;
	        }
	} else {
	    return false;
	}	    
		     
    }
 
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if ((xf >= 0 && xf <=7) && (yf >= 0 && yf <= 7))
	{ // First major check is whether the attempted move is outside the boundary

	    if (selected.isKing() == true)
	    {
	    // Do one thing for a king peice
		if (((xf - xi) == 1) || ((xf - xi) == -1) && (((yf - yi) == 1) || ((yf - yi) == -1)))
   	        { // kinged can move with coordinates (1,1),(1,-1),(-1,1),(-1,-1)
	            if (pieceAt((xi + (xf - xi)),(yi + (yf - yi))) == null)
		    { // if there is no piece blocking the coordinates
			return true;
		    }
		}
		if (((xf - xi) == 2) || ((xf - xi) == -2) && (((yf - yi) == 2) || ((yf - yi) == -2)))
		{ // king could also move with coordinates (2,2),(2,-2),(-2,2),(-2,-2)
		    if (pieceAt((xi + (xf - xi)/2),(yi + (yf - yi)/2)) == null)
		    { // not a valid move if there is no piece in between the +-2 offset jump
			return false;
		    } else {
		        if (pieceAt((xi + (xf - xi)/2),(yi + (yf - yi)/2)).side() != pieceAt(xi,yi).side())
		        { // If piece at the +-1 offset has a different side than the xi,yi piece
			    return true;
		        }
		    }
		} else
		{ // Two types of moves any give piece can make, so if we make it to the end w/o returning true it must be an illegal move
		    return false;
		} 
		return false;		
	    }
	    else {
	    //Do something else for non-king piec
	        int direction = 0;
		if (pieces[xi][yi].side() == 0) { // matters which side you are for going up or down
		    direction = 1;
		} else if (pieces[xi][yi].side() == 1) {
		    direction = -1;
		}
		if ((((xf - xi) == 1) || ((xf-xi) == -1))  && (((yf - yi) == direction)))
   	        { // kinged can move with coordinates (1,1)(-1,1)
	            if (pieceAt((xi + (xf - xi)),(yi + (yf - yi))) == null)
		    { // if there is no piece blocking the coordinates
			return true;
		    }
		}
		if (((xf - xi) == 2) || ((xf - xi) == -2) && (((yf - yi) == 2*direction)))
		{ // king could also move with coordinates (2,2),(2,-2),(-2,2),(-2,-2)
		    if (pieceAt((xi + (xf - xi)/2),(yi + (yf - yi)/2)) == null) 
		    { // if the piece in between the attempted two space move is empty, not possible
			return false;
		    }
		    else {
			if (pieceAt((xi + (xf - xi)/2),(yi + (yf - yi)/2)).side() != pieceAt(xi,yi).side())
		        { // If piece at the +-1 offset has a different side than the xi,yi piece
			    return true;
		        }
		    }
		} else
		{ // Two types of moves any give piece can make, so if we make it to the end w/o returning true it must be an illegal move
		    return false;
		} 
		return false;
	    }
	}
	else {
	    return false; //Clearly return false if you end up outside the boundary
   	}
    }

    private boolean validMove1(int xi, int yi, int xf, int yf) {
        if ((xf >= 0 && xf <=7) && (yf >= 0 && yf <= 7))
	{ // First major check is whether the attempted move is outside the boundary

	    if (selected.isKing() == true)
	    {// for this check king can only move if he's overtaking another piece
		if (((xf - xi) == 2) || ((xf - xi) == -2) && (((yf - yi) == 2) || ((yf - yi) == -2)))
		{ // king could also move with coordinates (2,2),(2,-2),(-2,2),(-2,-2)
		    if (pieceAt((xi + (xf - xi)/2),(yi + (yf - yi)/2)) == null)
		    { // not a valid move if there is no piece in between the +-2 offset jump
			return false;
		    } else {
		        if (pieceAt((xi + (xf - xi)/2),(yi + (yf - yi)/2)).side() != pieceAt(xi,yi).side())
		        { // If piece at the +-1 offset has a different side than the xi,yi piece
			    return true;
		        }
		    }
		} else
		{ // Two types of moves any give piece can make, so if we make it to the end w/o returning true it must be an illegal move
		    return false;
		} 
		return false;		
	    }
	    else {
	    //Do something else for non-king piec
	        int direction = 0;
		if (pieces[xi][yi].side() == 0) { // matters which side you are for going up or down
		    direction = 1;
		} else if (pieces[xi][yi].side() == 1) {
		    direction = -1;
		}
		if (((xf - xi) == 2) || ((xf - xi) == -2) && (((yf - yi) == 2*direction)))
		{ // non-king  could also move with coordinates (2,2),(2,-2),(-2,2),(-2,-2)
		    if (pieceAt((xi + (xf - xi)/2),(yi + (yf - yi)/2)) == null) 
		    { // if the piece in between the attempted two space move is empty, not possible
			return false;
		    }
		    else {
			if (pieceAt((xi + (xf - xi)/2),(yi + (yf - yi)/2)).side() != pieceAt(xi,yi).side())
		        { // If piece at the +-1 offset has a different side than the xi,yi piece
			    return true;
		        }
		    }
		} else
		{ // Two types of moves any give piece can make, so if we make it to the end w/o returning true it must be an illegal move
		    return false;
		} 
		return false;
	    }
	}
	else {
	    return false; //Clearly return false if you end up outside the boundary
   	}
    }

    public void select(int x, int y) {
	if (canSelect(x, y)) {
	    highlightx = x;
	    highlighty = y;
	    if (pieces[x][y] == null) {
		selected.move(x,y);
	 	moved = true;
		selectedx = x;
		selectedy = y;
	    } else {
		selected = pieceAt(x,y);
		selectedx = x;
		selectedy = y;
	    }
	    //printBoard();
	}
    }

    public void place(Piece P, int x, int y) {
        if ((x >= 0 && x <=7) && (y >= 0 && y <= 7)) {
	    if (P != null) {
		pieces[x][y] = P;
	        //drawBoard();
	    } else {
		System.out.println("Original was null!");
	    }
	}
        
    }

    public Piece remove(int x, int y) {
        //if (pieceAt(x,y) != null) && (x >= 0);
        if ((x >= 0 && x <= 7) && (y >= 0 && y <= 7)) {
	    if (pieceAt(x,y) != null) {
	        Piece temporary = pieces[x][y];
	        pieces[x][y] = null;
	        //drawBoard();
	        return temporary;
	    } else {
	        System.out.println("No piece to remove!");
	        //System.out.println(pieces[x][y].xpos);
	        //System.out.println(x);
	        //System.out.println(y);
                return null;
	    }
	} else {
	    System.out.println("Can't remove a piece that was off the board");
	    return null;
   	}
     }

    public boolean canEndTurn() {
        if (moved != false) {
	    return true;
	} else {
	    return false;
	}
    }

    public void endTurn() {
	if (canEndTurn() == true) {
	    side = (side + 1) % 2;
	    selected.doneCapturing();
	    selected = null;
	    moved = false;
	    highlightx = -1;
	    highlighty = -1;
	    selectedx = -1;
	    selectedy = -1;
	    //drawBoard();
	} else {
	}
    }

    public String winner() {

	int firecount = 0;
	int watercount = 0;

        for (int i = 0; i < 8; i++) {
	    for (int j = 0; j <8; j++) {
		if (pieces[i][j] != null) {
		    if (pieces[i][j].isFire() == true) {
			firecount += 1;
		    } else {
			watercount += 1;
		    }
		}
	    }
	}
	
	if (firecount == 0 || watercount == 0) {
	    if (firecount > watercount) {
		return "Fire";
	    } else if ( watercount > firecount) {
		return "Water";
	    } else {
		return "No one";
	    }
	} else {
	    return null;
	}
    }

    public static void main(String args[]) {
	Board myboard = new Board(false);
	//fire starts
	//side = 0;

	// Player has not yet moved or selected
	//moved = null;
	//selected = null;

	myboard.drawBoard();
        while(true) {
	    //drawWhite(click_x, click_y);
	    if (StdDrawPlus.mousePressed()) {
		int x = (int)Math.floor(StdDrawPlus.mouseX());
		int y = (int)Math.floor(StdDrawPlus.mouseY());
		//drawBoard();
		//selected = remove(x,y);
		//System.out.println("Before select board was:");
		//printBoard();
		myboard.select(x,y);
		//System.out.println("After select board is:");
		//printBoard();
		//System.out.print(selected.xpos);
		//System.out.println(selected.ypos);
	    }

	    if (StdDrawPlus.isSpacePressed()) {
		myboard.endTurn();
		//System.out.println(side);
	    }
	    myboard.drawBoard();
	    //if (StdDrawPlus.mousePressed()) {
	//	int a = (int)Math.floor(StdDrawPlus.mouseX());
	//	int b = (int)Math.floor(StdDrawPlus.mouseY());
	  //  }
	    //if (StdDrawPlus.mousePressed()
	    //drawWhite(x,y);
	    StdDrawPlus.show(100);
        }
    }
}
