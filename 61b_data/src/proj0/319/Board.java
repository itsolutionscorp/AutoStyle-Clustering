public class Board {
	
    private boolean shouldBeEmpty;
    private Piece isSelected;
    private boolean hasSelected;
    private int hasMoved;
    private int isSelectedX;
    private int isSelectedY;
    private int turnTracker;
    private int fireLives;
    private int waterLives;
	private Piece[][] pieces;
    // 
    
    public Board (boolean shouldBeEmpty) {
        this.shouldBeEmpty = shouldBeEmpty;
        this.hasSelected = false;
        this.hasMoved = 0;
        this.turnTracker = 0;
        this.fireLives = 0;
        this.waterLives = 0;
        pieces = new Piece[8][8];
        if (!this.shouldBeEmpty) {
            this.fireLives = 12;
            this.waterLives = 12;
            assignPieces();
        }
    }
	
	private void assignPieces () {
        String[] types = {"pawn", "shield", "bomb", null, null, "bomb", "shield", "pawn"};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j < 3) { //fire pieces
                    if (j % 2 == 0) {
                        if (i % 2 == 0) { //start at 0
                            pieces[i][j] = new Piece(true, this, i, j, types[j]);
                        }
                    } else {
                        if (i % 2 != 0) {
                            pieces[i][j] = new Piece(true, this, i, j, types[j]);
                        }
                    }
                }
                else if (j > 4) { //water pieces
                    if (j % 2 == 0) {
                        if (i % 2 == 0) { //start at 0
                            pieces[i][j] = new Piece(false, this, i, j, types[j]);
                        }
                    } 
                    else {
                        if (i % 2 != 0) {
                            pieces[i][j] = new Piece(false, this, i, j, types[j]);
                        }
                    }
                }
            }
        }
    }
        
    private void drawBoard (int N) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {             
                        if (pieces[i][j].isShield()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                        }
                        else if (pieces[i][j].isBomb()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                        }
                        else {
                            if (pieces[i][j].isKing()) {
                                 StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    } else {
                        if (pieces[i][j].isShield()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                        }
                        else if (pieces[i][j].isBomb()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                        }
                        else {
                            if (pieces[i][j].isKing()) {
                                 StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public Piece pieceAt (int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return null;
        }
        else {
            return pieces[x][y];
        }
    }

    public void place (Piece p, int x, int y) {
        if (x>7 || y > 7 || x < 0 || y < 0) {
        }
        else {
            pieces[x][y] = p;
        }
    }

    public Piece remove (int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            System.out.println("There location (" + x + ", " + y + ") does not exist.");
            return null;
        }
        else if (pieces[x][y] == null) {
            System.out.println("There is no piece to remove at (" + x + ", " + y + ").");
            return null;
        } else {
            if (pieces[x][y].hasCaptured()) {
                if (pieces[x][y].isFire()) {
                    fireLives -= 1;
                }
                else {
                    waterLives -= 1;
                }
            }
            Piece removed = pieces[x][y];
            pieces[x][y] = null;
            return removed;
        }

    }
    private boolean validMove (int xi, int yi, int xf, int yf) {
        int differenceX = (xf - xi);
        int differenceY = (yf - yi);
        if (pieces[xf][yf] != null || pieces[xi][yi] == null) {
            return false;
        }
        else if (Math.abs(differenceX) > 2 || Math.abs(differenceY) > 2 || Math.abs(differenceX) != Math.abs(differenceY)) {
                return false;
        }
        else if ((Math.abs(differenceX) == 1) || (Math.abs(differenceY) == 1)) {
            if (pieces[xi][yi].isKing()) {
                return true;
            }
            else if (pieces[xi][yi].isFire()) {
                if (differenceY > 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                if (differenceY < 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
        } 
        else if (differenceX == 2 && differenceY == 2 && (this.isSelected.side() != pieces[xf-1][yf-1].side())) {
            if (pieces[xf-1][yf-1] != null) {
                if (pieces[xi][yi].isKing()) {
                    return true;
                }
                else if (pieces[xi][yi].isFire()) {
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
        else if (differenceX == -2 && differenceY == 2 && (this.isSelected.side() != pieces[xf+1][yf-1].side())) {
            if (pieces[xf+1][yf-1] != null) {
                if (pieces[xi][yi].isKing()) {
                    return true;
                }
                else if (pieces[xi][yi].isFire()) {
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
        else if (differenceX == 2 && differenceY == -2 && (this.isSelected.side() != pieces[xf-1][yf+1].side())) {
            if (pieces[xf-1][yf+1] != null) {
                if (pieces[xi][yi].isKing()) {
                    return true;
                }
                else if (pieces[xi][yi].isFire()) {
                    return false;
                }
                else {
                    return true;
                }
            }
            else {
                return false;
            }
        }
        else if ((differenceX == -2 && differenceY == -2) && (this.isSelected.side() != this.pieceAt(xf+1, yf+1).side())) {
            if (pieces[xf+1][yf+1] != null) {
                if (pieces[xi][yi].isKing()) {
                    return true;
                }
                else if (pieces[xi][yi].isFire()) {
                    return false;
                }
                else {
                    return true;
                }
            }
            else {
                return false;
            }
        } 
        else {
            return false;
        }
    }    


    public boolean canSelect (int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return false;
        }
        else if (!this.hasSelected) {
            if (pieces[x][y] != null) {
                if (this.turnTracker == 0) {
                    if (pieces[x][y].isFire()) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }   
                else {
                    if (!pieces[x][y].isFire()) {
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
        else if (this.hasMoved == 0) {
            if (pieces[x][y] == null) {
                return true;
            }
            else {
                if (this.turnTracker == 0) {
                    if (pieces[x][y].isFire()) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }   
                else {
                    if (!pieces[x][y].isFire()) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        else {
            if (pieces[x][y] == null) {
                if (this.isSelected.hasCaptured()) {
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
    }

//should not use can select - assumes x, y is fine
// - in main - mouse click --> if (canSelect) --> select
    public void select (int x, int y) {
        if (this.hasSelected == false && this.hasMoved == 0) {
            this.isSelected = pieces[x][y];
            this.isSelectedX = x;
            this.isSelectedY = y;
            this.hasSelected = true; 
        } 
        else if ((this.hasSelected == true) && this.hasMoved == 0) {
            if (this.pieceAt(x, y) != null) {
                this.isSelected = pieces[x][y];
                this.isSelectedX = x;
                this.isSelectedY = y;
                this.hasSelected = true;
            }
            else {
                if (validMove(isSelectedX, isSelectedY, x, y)) {
                    this.pieceAt(isSelectedX, isSelectedY).move(x,y);
                    this.isSelected = pieces[x][y];
                    this.isSelectedX = x;
                    this.isSelectedY = y;
                    this.hasMoved += 1;
                    this.hasSelected = true;
                }
            }
        }
        
        else if (this.hasMoved > 0) {
            if (validMove(isSelectedX, isSelectedY, x, y)) {
                this.pieceAt(isSelectedX, isSelectedY).move(x,y);
                this.isSelectedX = x;
                this.isSelectedY = y;
                this.hasSelected = true;
                this.hasMoved += 1;
            }
        }
    }
    
    public boolean canEndTurn() {
        if (hasMoved > 0) {
            return true;
        }
        else {
            return false;
        }
    }
    public void endTurn () {
        this.hasSelected = false;
        this.isSelected = null;
        this.hasMoved = 0;
        if (this.isSelected != null) {
            this.isSelected.doneCapturing();
        }
        if (this.turnTracker == 0) {
            this.turnTracker = 1;
        } else {
            this.turnTracker = 0;
        }
    }
    public String winner () {
        if (this.fireLives > 0 || this.waterLives > 0) {
            return null;
        }
        else if (this.fireLives == 0 && this.waterLives == 0) {
            return "No one";
        }
        else if (this.fireLives == 0) {
            return "Water";
        }
        else if (this.waterLives == 0) {
            return "Fire";
        }
        else {
            return null;
        }

    }

// 
// - in main - mouse click --> if (canSelect) --> select
    public static void main(String[] args) {
    	int N = 8;
        Board b = new Board(false);
    	StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while(true) {
            b.drawBoard(N);       
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);
                }
                else {
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()){
                    b.endTurn();
                }
            }
            if(b.fireLives == 0 || b.waterLives == 0) {
                b.winner();
            }     
            StdDrawPlus.show(80);
        }
    }
}