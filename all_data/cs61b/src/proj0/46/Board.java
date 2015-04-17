public class Board {

    private Piece[][] pieces = new Piece[8][8];
    private Piece selected;
    private int sX, sY;
    private boolean moved;
    private int turn;

    public Board(boolean shouldBeEmpty) {
        if (!shouldBeEmpty) {
	    for (int i = 0; i < 8; i += 2) {
		Piece b1 = new Piece(true, this, i, 0, "pawn");
		Piece b2 = new Piece(true, this, i, 2, "bomb");
		Piece b3 = new Piece(false, this, i, 6, "shield");
		Piece b4 = new Piece(true, this, i+1, 1, "shield");
		Piece b5 = new Piece(false, this, i+1, 5, "bomb");
		Piece b6 = new Piece(false, this, i+1, 7, "pawn");
		place(b1, i, 0);
		place(b2, i, 2);
		place(b3, i, 6);
		place(b4, i+1, 1);
		place(b5, i+1, 5);
		place(b6, i+1, 7);
	    }
	}
	selected = null;
	sX = 0; sY = 0;
	moved = false;
	turn = 0;
    }

    private void drawEmptyBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	    }
	}
    }

    private void drawPieces(Board b) {
	String[] imageList = new String[]{"img/pawn-fire.png",
		   "img/shield-fire.png","img/bomb-fire.png",
		   "img/pawn-water.png","img/shield-water.png",
		   "img/bomb-water.png","img/pawn-fire-crowned.png",
		   "img/shield-fire-crowned.png","img/bomb-fire-crowned.png",
		   "img/pawn-water-crowned.png","img/shield-water-crowned.png",
					  "img/bomb-water-crowned.png"};
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		Piece p = b.pieces[i][j];
		if (p != null) {
		    if (p.isFire() == true && p.isKing() == false) {
			if (p.isShield()) StdDrawPlus.picture(i+0.5, j+0.5, imageList[1], 1, 1);
			else if (p.isBomb()) StdDrawPlus.picture(i+0.5, j+0.5, imageList[2], 1, 1);
			else StdDrawPlus.picture(i+0.5, j+0.5, imageList[0], 1, 1);
		    } else if (p.isFire() == false && p.isKing() == false) {
			if (p.isShield()) StdDrawPlus.picture(i+0.5, j+0.5, imageList[4], 1, 1);
			else if (p.isBomb()) StdDrawPlus.picture(i+0.5, j+0.5, imageList[5], 1, 1);
			else StdDrawPlus.picture(i+0.5, j+0.5, imageList[3], 1, 1);
		    } else if (p.isFire() == true && p.isKing() == true) {
			if (p.isShield()) StdDrawPlus.picture(i+0.5, j+0.5, imageList[7], 1, 1);
			else if (p.isBomb()) StdDrawPlus.picture(i+0.5, j+0.5, imageList[8], 1, 1);
			else StdDrawPlus.picture(i+0.5, j+0.5, imageList[6], 1, 1);
		    }else if (p.isFire() == false && p.isKing() == true) {
			if (p.isShield()) StdDrawPlus.picture(i+0.5, j+0.5, imageList[10], 1, 1);
			else if (p.isBomb()) StdDrawPlus.picture(i+0.5, j+0.5, imageList[11], 1, 1);
			else StdDrawPlus.picture(i+0.5, j+0.5, imageList[9], 1, 1);
		    }
		}
	    }
	}
    }
    
    /*
    private void testPieces(Board b) {
	Piece b1 = new Piece(true, b, 2, 2, "pawn");
	Piece b2 = new Piece(false, b, 3, 3, "pawn");
	Piece b3 = new Piece(false, b, 5, 5, "pawn");
	Piece b4 = new Piece(false, b, 3, 5, "bomb");
	Piece b5 = new Piece(false, b, 5, 3, "shield");
	place(b1, 2, 2);
	place(b2, 3, 3);
	place(b3, 5, 5);
	place(b4, 3, 5);
	place(b5, 5, 3);
    }
    */
	

    public static void main(String[] args) {
	Board b;
	double x, y;
	int xi = 8, yi = 8;
	String winner;
	
	//uncomment to create standard board
	b = new Board(false);
	
	//uncomment to create test board
	//if (args == null) b = new Board(false);
	//else b = new Board(Boolean.valueOf(args[0]));

        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
	b.drawEmptyBoard();
	b.drawPieces(b);

        while(true) {
	    //draw empty board
	    b.drawEmptyBoard();
	    //draw all pieces on the board
	    b.drawPieces(b);
	    //if player can still play
	    if (StdDrawPlus.mousePressed()) {
		//record position of the lick
		x = StdDrawPlus.mouseX();
		y = StdDrawPlus.mouseY();
		xi = (int) x;
		yi = (int) y;
	    }
	    //if can select, select and do all consequent works
	    if (b.canSelect(xi, yi)) {
		//set the square selected white
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(xi+0.5,yi+0.5,0.5);
		b.drawPieces(b);
		//select the piece and do all consequent stuffs
		b.select(xi, yi);
	    }
	    //end the turn by press the space
	    if (b.canEndTurn() && StdDrawPlus.isSpacePressed()) b.endTurn();
	    //return if there's a winner
	    winner = b.winner();
	    if (winner != null) {
		System.out.println(winner + " is the winner");
		return;
	    }
	    StdDrawPlus.show(100);
	}
    }


    private boolean inRange(int x) {
	return (x >= 0) && (x <= 7);
    }

    private boolean inRange(int x, int min, int max) {
	return (x >= 0) && (x <= 7) && ((x >= min) && (x <= max));
    }

    public Piece pieceAt(int x, int y) {
	if ((inRange(x) && inRange(y)) == false) return null;
	else {
	    Piece p = pieces[x][y];
	    if (p == null) return null;
	    else return p;
	}
    }

    public boolean canSelect(int x, int y) {
	Piece p = pieceAt(x, y);
	//if select an empty square
	if (p == null) {
	    //if selected a piece before, haven't moved or has captured
	    if (selected != null) {
		if (moved == false) {
		    //check if it's a valid move or can apture target piece
		    return validMove(sX, sY, x, y) || canCapture(sX, sY, x, y);
		} else if (selected.hasCaptured()) {
		    return canCapture(sX, sY, x, y);
		}
	    }
	//if select a square with his own piece
	} else if (p.side() == turn) {
	    //player should not have selected or moved yet
	    return selected == null || moved == false;
	}
	//otherwise can't select
	return false;
    }

    private boolean canCapture(int xi, int yi, int xf, int yf) {
	Piece target = pieceAt((int) (xi+(xf-xi)/2), (int) (yi+(yf-yi)/2));
	Piece landing = pieceAt(xf, yf);
	if ((target != null)  && (target != selected) && (landing == null) && target.side()!=turn) {
	    if (selected.isKing())
		return (inRange(xf, xi-2, xi+2) && inRange(yf, yi-2, yi+2));
	    else if (selected.isFire())
		return (inRange(xf, xi-2, xi+2) && inRange(yf, yi, yi+2));
	    else
		return (inRange(xf, xi-2, xi+2) && inRange(yf, yi-2, yi));
	} else return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
	//check if it's king, can go four directions
	if (selected.isKing()) {
	    return inRange(xf, xi-1, xi+1) && 
		inRange(yf, yi-1, yi+1) && ((xf-yf) % 2 == 0);
	}
	//check if it's fire, can go wpwards
	if (selected.isFire()) {
	    return inRange(xf, xi-1, xi+1) && 
		inRange(yf, yi, yi+1) && ((xf-yf) % 2 == 0);
	}
	//otherwise it's water, can go downwards
	return inRange(xf, xi-1, xi+1) && inRange(yf, yi-1, yi) && ((xf-yf) % 2 == 0);
    }

    public void select(int x, int y) {
	Piece p = pieceAt(x, y);
	//if current select is a piece
	if (p != null) {
	    //change the current selected piece to this one
	    selected = p;
	    sX = x; sY = y;
	    //if current select is an empty square
	} else {
	    //if have selected a piece
	    if (selected != null) {
		//do all the consequent actions
		selected.move(x, y);
		//the player have moved, updare infomation
		moved = true;
		if (pieceAt(x,y) != null) {
		    selected = pieceAt(x, y);
		    sX = x; 
		    sY = y;
		}
	    }
	}	
    }

    public void place(Piece p, int x, int y) {
	if (inRange(x) && inRange(y) && p != null) {
	    pieces[x][y] = p;
	}
    }

    public Piece remove(int x, int y) {
	Piece temp = pieceAt(x, y);
	if ((inRange(x) && inRange(y)) == false) {
	    System.out.println("Input coordinate out of bound!");
	    return null;
	}
	else if (temp == null) {
	    System.out.println("no piece at input coordinate to remove!");
	    return null;
	} else {
	    pieces[x][y] = null;
	    return temp;
	}
    }

    public boolean canEndTurn() {
	return moved;
    }

    public void endTurn() {
	if (selected != null) {
	    selected.doneCapturing();
	    selected = null;
	}
	moved = false;
	turn = (turn + 1) % 2;
	System.out.println("change turn!");
    }

    public String winner() {
	int fireN = 0;
	int waterN = 0;
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		Piece p = pieces[i][j];
		if (p != null) {
		    if (p.isFire()) fireN += 1;
		    else waterN += 1;
		}
	    }
	}
	if (fireN > 0 && waterN == 0) return "Fire";
	else if (waterN > 0 && fireN == 0) return "Water";
	else if (fireN == 0 && waterN == 0) return "No one";
	else return null;
    }

}
