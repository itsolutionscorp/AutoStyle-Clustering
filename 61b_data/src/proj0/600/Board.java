public class Board {
	private Piece[][] pieces;
    private Piece recentSelect;
    private String firepawn = "img/pawn-fire.png";
    private String fireshield = "img/shield-fire.png";
    private String firebomb = "img/bomb-fire.png";
    private String waterbomb = "img/bomb-water.png";
    private String watershield = "img/shield-water.png";
    private String waterpawn = "img/pawn-water.png";
    private String kingfirepawn = "img/pawn-fire-crowned.png";
    private String kingfireshield = "img/shield-fire-crowned.png";
    private String kingfirebomb = "img/bomb-fire-crowned.png";
    private String kingwaterpawn = "img/pawn-water-crowned.png";
    private String kingwatershield = "img/shield-water-crowned.png";
    private String kingwaterbomb = "img/bomb-water-crowned.png";
    private int xSelect;
    private int ySelect;
    private boolean moveCounter;
    private boolean teamFire;
    private boolean teamWater;
    private boolean boardType;

	public Board(boolean shouldBeEmpty) {
    	if (shouldBeEmpty) {
    		int N = 8;
        	pieces = new Piece[N][N];
            boardType = true;
            teamFire = true;
    	}
    	else {
    		int N = 8;
    		pieces = new Piece[N][N];
            teamFire = true;
            boardType = false;
            makeEvenRow(true, N, 0, 0, firepawn, "pawn");
            makeOddRow(true, N, 1, 1, fireshield, "shield");
            makeEvenRow(true, N, 0, 2, firebomb, "bomb");
            makeOddRow(false, N, 1, 5, waterbomb, "bomb");
            makeEvenRow(false, N, 0, 6, watershield, "shield");
            makeOddRow(false, N, 1, 7, waterpawn, "pawn");
    	}
    }

    private void drawMethod(int N) {
        if (boardType == true) {
            drawEmptyBoard(N);
        }
        else if (boardType == false) {
            drawDefaultBoard(N);
        }
    }

    // assistance from StdDrawDemo
	private void drawEmptyBoard(int N) {
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
            }
        }

    private void drawDefaultBoard(int N) {
		drawEmptyBoard(N);
        drawPieces(N);   
    }

    private void makeEvenRow(boolean fire, int N, int xCoord, int yCoord, String image, String type) {
    	for (int x = xCoord; x < N; x++) {
    		if (x % 2 == 0) {
    			pieces[x][yCoord] = new Piece(fire, this, x, yCoord, type);
    		}
    	}
    }

    private void makeOddRow(boolean fire, int N, int xCoord, int yCoord, String image, String type) {
    	for (int x = xCoord; x< N; x++) {
    		if (x % 2 != 0) {
    			pieces[x][yCoord] = new Piece(fire, this, x, yCoord, type);
    		}
    	}
    }

    private void drawPieces(int N) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (pieces[x][y] != null) {
                    drawPiece(x,y,pieces[x][y]);
                }
            }
        }
    }
    
    private void drawPiece(int x, int y, Piece p) {
        String pType;
        if (p == null) ;
        else {
            if (p.isKing()) {
                if (p.isFire()) {
                    if (p.isBomb()) pType = kingfirebomb;
                    else if (p.isShield()) pType = kingfireshield;
                    else pType = kingfirepawn;
                }
                else {
                    if (p.isBomb()) pType = kingwaterbomb;
                    else if (p.isShield()) pType = kingwatershield;
                    else pType = kingwaterpawn;
                }
            }
            else if (p.isFire()) {
                if (p.isBomb()) pType = firebomb;
                else if (p.isShield()) pType = fireshield;
                else pType = firepawn;
            }
            else {
                if (p.isBomb()) pType = waterbomb;
                else if (p.isShield()) pType = watershield;
                else pType = waterpawn;
            }
            StdDrawPlus.picture(x + 0.5, y + 0.5, pType, 1, 1);
        }
        
    }

    public Piece pieceAt(int x, int y) {
    	if ((x > 7) || (y > 7) || pieces[x][y] == null) {
    		return null;
    	}
    	else {
            Piece getPiece = pieces[x][y]; 
            return getPiece;
        }
    }

    public boolean canSelect(int x, int y) {
        // player has not selected a piece yet
        if (recentSelect == null) {
            if ((pieceAt(x,y) != null) && (pieceAt(x,y).isFire() == teamFire)) {
                    return true;
                }
                else return false;
        }
        // after a bomb has gone off/ no multicapture
        else if (recentSelect.isBomb() && recentSelect.hasCaptured()) return false;
        // player has selected but has not moved
        else {
            if (!moveCounter) {
                // selecting a different piece
                if ((pieceAt(x,y) != null) && (pieceAt(x,y).isFire() == teamFire)) {
                    return true;
                }
                // selected a piece that has not moved and selecting an empty square
                else if (validMove(xSelect, ySelect, x, y)) {
                    return true;
                }
                else return false;
            }
            // player has selected a piece that has moved 
            //and is selecting an empty spot which is a valid move - select Bool must be true
            else if (moveCounter && validMove(xSelect, ySelect, x, y)) {
                return true;
            }
            else return false;
        }
    }
    // check not backwards
    // check its an empty space
    // check if 2, then the space is empty, and the space is in between is filled by other character
    // assistance from Arani Bhattacharyay
    private boolean validMove(int xi, int yi, int xf, int yf) {
        // final coordinates out of bounds
        if ((xf > 7) || (xf < 0) || (yf > 7) || (yf < 0)) {
            return false;
        }
        // check if coordinates are diagonal and in right direction
        else if (pieceAt(xi, yi) != null && pieceAt(xi, yi).isFire()) {
            // move has not been made
            if (!moveCounter && !pieceAt(xi,yi).hasCaptured()) {
                // king valid move
                if (pieceAt(xi,yi).isKing()) {
                    return kingNoMoveValidMove(xi,yi,xf,yf);
                }
                // 1 space, empty space
                if ((Math.abs(xf - xi) == 1) && (yf - yi == 1) && (pieceAt(xf, yf) == null)) {
                    return true;
                }
                // 2 spaces, 
                else if ((Math.abs(xf - xi) == 2) && (yf - yi == 2) && (pieceAt(xf, yf) == null)) {
                    // check if middle space has a piece of opposite type
                    // if final piece on the left
                    if (xf - xi == -2) {
                        //check middle space for opposite type piece
                        if ((pieceAt(xf+1, yf-1) != null) && (pieceAt(xf+1, yf-1).side() != pieceAt(xi, yi).side())) {
                            return true;
                        }
                        else return false;
                    }
                    // final piece on right
                    else {
                        if ((pieceAt(xf-1, yf-1) != null) && (pieceAt(xf-1, yf-1).side() != pieceAt(xi, yi).side())) {
                            return true;
                        }
                        else return false;
                    }
                }
                else return false;    
            }
            // move has been made
            else if (moveCounter || pieceAt(xi,yi).hasCaptured()) {
                // 1 space
                if ((Math.abs(xf - xi) == 1) && (yf - yi == 1) && (pieceAt(xf, yf) == null)) {
                    return false;
                }
                if (pieceAt(xi,yi).isKing()) {
                    if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2) && (pieceAt(xf, yf) == null)) {
                        // check if middle space has a piece of opposite type
                        // if final piece on left
                        if (xf - xi == -2) {
                            //check middle space for opposite type piece
                            if ((pieceAt(xf+1, yf-1) != null) && (pieceAt(xf+1, yf-1).side() != pieceAt(xi, yi).side())) {
                                return true;
                            }
                            else if ((pieceAt(xf+1, yf+1) != null) && (pieceAt(xf+1, yf+1).side() != pieceAt(xi, yi).side())) {
                                return true;
                            }
                            else return false;
                        }
                        // final piece on the right
                        else {
                            if ((pieceAt(xf-1, yf-1) != null) && (pieceAt(xf-1, yf-1).side() != pieceAt(xi, yi).side())) {
                                return true;
                            }
                            else if ((pieceAt(xf-1, yf+1) != null) && (pieceAt(xf-1, yf+1).side() != pieceAt(xi, yi).side())) {
                                return true;
                            }
                            else return false;
                        }
                    }
                    else return false;
                }
                else if ((Math.abs(xf - xi) == 2) && (yf - yi == 2) && (pieceAt(xf, yf) == null)) {
                    // check if middle space has a piece of opposite type
                    // if final piece on the left
                    if (xf - xi == -2) {
                        //check middle space for opposite type piece
                        if ((pieceAt(xf+1, yf-1) != null) && (pieceAt(xf+1, yf-1).side() != pieceAt(xi, yi).side())) {
                            return true;
                        }
                        else return false;
                    }
                    // final piece on right
                    else {
                        if ((pieceAt(xf-1, yf-1) != null) && (pieceAt(xf-1, yf-1).side() != pieceAt(xi, yi).side())) {
                            return true;
                        }
                        else return false;
                    }
                }
                else return false;
            }
            else {
                return false;
            }
        }
        else if ((pieceAt(xi,yi) != null) && !pieceAt(xi,yi).isFire()) {
            if (!moveCounter && !pieceAt(xi,yi).hasCaptured()) {
                if (pieceAt(xi,yi).isKing()) {
                    // king valid move
                    return kingNoMoveValidMove(xi,yi,xf,yf);
                }
                // 1 space, empty space
                if ((Math.abs(xf - xi) == 1) && (yf - yi == -1) && (pieceAt(xf, yf) == null)) {
                    return true;
                }
                // 2 spaces, 
                else if ((Math.abs(xf - xi) == 2) && (yf - yi == -2) && (pieceAt(xf,yf) == null)) {
                    // check if middle space has a piece of opposite type
                    // if final piece on the left
                    if (xf - xi == -2) {
                        //check middle space for opposite type piece
                        if ((pieceAt(xf+1, yf+1) != null) && (pieceAt(xf+1, yf+1).side() != pieceAt(xi, yi).side())) {
                            return true;
                        }
                        else return false;
                    }
                    // final piece on right
                    else {
                        if ((pieceAt(xf-1, yf+1) != null) && (pieceAt(xf-1, yf+1).side() != pieceAt(xi, yi).side())) {
                            return true;
                        }
                        else return false;
                    }
                }
                else return false;
            }
            // move has been made
            else if (moveCounter || pieceAt(xi,yi).hasCaptured()) {
                // 1 space
                if ((Math.abs(xf - xi) == 1) && (yf - yi == -1) && (pieceAt(xf, yf) == null)) {
                    return false;
                }
                if (pieceAt(xi,yi).isKing()) {
                    if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2) && (pieceAt(xf, yf) == null)) {
                        // check if middle space has a piece of opposite type
                        // if final piece on left
                        if (xf - xi == -2) {
                            //check middle space for opposite type piece
                            if ((pieceAt(xf+1, yf-1) != null) && (pieceAt(xf+1, yf-1).side() != pieceAt(xi, yi).side())) {
                                return true;
                            }
                            else if ((pieceAt(xf+1, yf+1) != null) && (pieceAt(xf+1, yf+1).side() != pieceAt(xi, yi).side())) {
                                return true;
                            }
                            else return false;
                        }
                        // final piece on the right
                        else {
                            if ((pieceAt(xf-1, yf-1) != null) && (pieceAt(xf-1, yf-1).side() != pieceAt(xi, yi).side())) {
                                return true;
                            }
                            else if ((pieceAt(xf-1, yf+1) != null) && (pieceAt(xf-1, yf+1).side() != pieceAt(xi, yi).side())) {
                                return true;
                            }
                            else return false;
                        }
                    }
                    else return false;
                }
                else if ((Math.abs(xf - xi) == 2) && (yf - yi == -2) && (pieceAt(xf,yf) == null)) {
                    // check if middle space has a piece of opposite type
                    // if final piece on the left
                    if (xf - xi == -2) {
                        //check middle space for opposite type piece
                        if ((pieceAt(xf+1, yf+1) != null) && (pieceAt(xf+1, yf+1).side() != pieceAt(xi, yi).side())) {
                            return true;
                        }
                        else return false;
                    }
                    // final piece on right
                    else {
                        if ((pieceAt(xf-1, yf+1) != null) && (pieceAt(xf-1, yf+1).side() != pieceAt(xi, yi).side())) {
                            return true;
                        }
                        else return false;
                    }
                }
                else return false;
            }
            else return false;
        }
        else return false;
    }

    private boolean kingNoMoveValidMove(int xi, int yi, int xf, int yf) {
        if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1) && (pieceAt(xf, yf) == null)) {
            return true;
        }
        else if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2) && (pieceAt(xf, yf) == null)) {
            // check if middle space has a piece of opposite type
            // if final piece on the left
            if (xf - xi == -2) {
                //check middle space for opposite type piece
                if ((pieceAt(xf+1, yf-1) != null) && (pieceAt(xf+1, yf-1).side() != pieceAt(xi, yi).side())) {
                    return true;
                }
                else if ((pieceAt(xf + 1, yf+1) != null) && (pieceAt(xf+1, yf+1).side() != pieceAt(xi, yi).side())) {
                    return true;
                }
                else return false;
            }
            // final piece on right
            else {
                if ((pieceAt(xf-1, yf-1) != null) && (pieceAt(xf-1, yf-1).side() != pieceAt(xi, yi).side())) {
                    return true;
                }
                else if ((pieceAt(xf-1, yf+1) != null) && (pieceAt(xf-1, yf+1).side() != pieceAt(xi, yi).side())) {
                    return true;
                }
                else return false;
            }
        }
        else return false; 
    }

    public void select(int x, int y) {
        if (pieceAt(x,y) != null) {
            recentSelect = pieceAt(x,y);
            xSelect = x;
            ySelect = y;
        }
        else {
            recentSelect.move(x,y);
            xSelect = x;
            ySelect = y;
            moveCounter = true;
            }
    }

    public void place(Piece p, int x, int y) {
    	if ((x > 7) || (y > 7) || (p == null)) {
            ;
    	}
    	else {
            pieces[x][y] = p;
        }
    }


    public Piece remove(int x, int y) {
        if ((x > 7) || (y > 7)) {
            System.out.println("Cannot remove a piece, input out of bounds.");
            return null;
        }
        else if (pieceAt(x,y) == null) {
            System.out.println("Cannot remove a piece, no piece exists.");
            return null;
        }
        else {
            Piece getPiece = pieces[x][y];
            pieces[x][y] = null;
            return getPiece;
        }
    }

    public boolean canEndTurn() {
        if (moveCounter) {
            return true;
        }
        else return false;
    }

    public void endTurn() {
        if (canEndTurn()) {
            if (teamWater) {
                teamWater = false;
                teamFire = true;
                moveCounter = false;
                xSelect = 0;
                ySelect = 0;
                recentSelect.doneCapturing();
                recentSelect = null;
            }
            else {
                teamWater = true;
                teamFire = false;
                moveCounter = false;
                xSelect = 0;
                ySelect = 0;
                recentSelect.doneCapturing();
                recentSelect = null;
            }
        }
    }
    // assistance from Arani Bhattacharyay
    public String winner() {
        boolean findWater = false;
        boolean findFire = false;
        int N = 8;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (pieces[x][y] == null) ;
                else if (pieces[x][y].isFire()) findFire = true;
                else findWater = true; 
            }
        }
        if ((findWater == true) && (findFire == true)) return null;
        else if ((findWater == false) && (findFire == true)) return "Fire";
        else if ((findWater == true) && (findFire == false)) return "Water";
        else return "No one";
    }


    private void GUI() {
        int N = 8;
        while (true) {
            drawMethod(N);
            if (StdDrawPlus.mousePressed()) {
                double xdouble = StdDrawPlus.mouseX();
                double ydouble = StdDrawPlus.mouseY();
                int x = ((int) xdouble);
                int y = ((int) ydouble);
                if (canSelect(x,y)) {
                    select(x,y);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(x+0.5, y+0.5, 0.5);
                    drawPiece(x,y,pieces[x][y]);
                }
            }
            if (winner() != null) {
                winner();
            }
            StdDrawPlus.show(15);
            if (StdDrawPlus.isSpacePressed()) {
                if (canEndTurn()) {
                    endTurn();
                }
            }
        }
    }

    public static void main(String[] args) {
        // get user click, assistance from StdDrawDemo
        Board b = new Board(false);
        b.GUI();
    }
}