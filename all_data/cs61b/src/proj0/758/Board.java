

public class Board {

	private boolean shouldBeEmpty;
    private Piece[][] pieces;
    private int turn = 0;
    private boolean hasntSelectedYet = true;
    private boolean hasntMovedYet = true;
    private boolean hasCaptured = false;
    private int selectedPiecePositionX;
    private int selectedPiecePositionY;

	public static void main(String args[]) {
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        b.drawBoard(8);
        if(!(b.shouldBeEmpty)) {
            b.drawPieces();
        }
        
        while (b.winner() == null) {
            if (StdDrawPlus.mousePressed()) {
                if (b.canSelect((int)StdDrawPlus.mouseX(), (int)StdDrawPlus.mouseY())) {
                    b.select((int)StdDrawPlus.mouseX(), (int)StdDrawPlus.mouseY());
                    b.drawBoard(8);
                    b.drawPieces();
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            StdDrawPlus.show(5);
        }
    }
    
    private void drawPieces() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.pieces[i][j] != null) {
                    if (this.pieces[i][j].isKing()) {
                        if (this.pieces[i][j].isFire()) 
                        {                                
                            if (this.pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else if (this.pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                        }
                        else {
                            if (this.pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            else if (this.pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                        }
                    }
                    else {
                        if (this.pieces[i][j].isFire()) {
                            if (this.pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                            else if (this.pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                        else {
                            if (this.pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                            else if (this.pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
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
                            
                                
                                
                

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
        
        int N = 8;

        this.pieces = new Piece[N][N];    
        
        if (!(this.shouldBeEmpty)) {   
            // put pieces in array
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    if (j == 0 && i % 2 == 0) {
                        this.pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    }
                    else if (j == 7 && (i + 1) % 2 == 0) {
                        this.pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }

                    else if (j == 1 && (i + 1) % 2 == 0) {
                        this.pieces[i][j] = new Piece(true, this, i, j, "shield");
                    }
                    else if (j == 6 && i % 2 == 0) {
                        this.pieces[i][j] = new Piece(false, this, i, j, "shield");
                    }
                    else if (j == 2 && i % 2 == 0) {
                        this.pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }
                    else if (j == 5 && (i + 1) % 2 == 0) {
                        this.pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    }
                }
            }
        }
	}

    public Piece pieceAt(int x, int y) {
         if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
             return null;
         }
         return this.pieces[x][y];
    }
    
    public boolean canSelect(int x, int y) {
        if (pieceAt(x, y) != null) {
            if (this.turn == this.pieces[x][y].side()) {
                if (this.hasntSelectedYet) {
                    return true;
                }
                else if (this.hasntMovedYet) {
                    return true;
                }
            }
        }
        else if (!(this.hasntSelectedYet)) {
            if (this.hasntMovedYet) {
                if (validMove(this.selectedPiecePositionX, this.selectedPiecePositionY, x, y)) {
                    return true;
                }
            }
            else if (this.hasCaptured) {
                if (validMove(this.selectedPiecePositionX, this.selectedPiecePositionY, x, y)) {
                    return true;
                }
            }
        }
        return false;             
	 }
    
    private boolean validMove(int xi, int yi, int xf, int yf) {
    // if has captured, must be another capture
    // if hasnt captured, can be 1 away or capture
    
        if (pieceAt(xf, yf) == null) {
            //if hasnt captured
            if (!(this.hasCaptured)) {
                // one space away
                if (xf - xi == 1 || xf - xi == -1) {
                    if (this.pieces[xi][yi].isKing()) {
                        if (yf - yi == 1 || yf - yi == -1) {
                            return true;
                        }
                    }
                    else {
                        if (this.pieces[xi][yi].isFire()) {
                            if (yf - yi == 1) {
                                return true;
                            }
                        }
                        else {
                            if (yf - yi == -1) {
                                return true;
                            }
                        }
                    }
                }

                //CAPTURE

                // TO THE RIGHT
                else if (xf - xi == 2) {
                    // if king, can go up or down
                    if (this.pieces[xi][yi].isKing()) {
                        if (yf - yi == 2) {
                            if (pieceAt(xi+1, yi+1) != null) {
                                return true;
                            }
                        }
                        else if (yf - yi == -2) {
                            if (pieceAt(xi+1, yi-1) != null) {
                                return true;
                            }
                        }
                    }
                    // if not a king, only go up/down depending on side
                    else {
                        if (this.pieces[xi][yi].isFire()) {
                            //can only go up
                            if (yf - yi == 2) {
                                if (pieceAt(xi+1, yi+1) != null) {
                                    return true;
                                }
                            }
                        }
                        else {
                            //can only go down
                            if (yf - yi == -2) {
                                if (pieceAt(xi+1, yi-1) != null) {
                                    return true;
                                }
                            }
                        }
                    }
                }

                // TO THE LEFT
                else if (xf - xi == -2) {
                    // if king, can go up or down
                    if (this.pieces[xi][yi].isKing()) {
                        if (yf - yi == 2) {
                            if (pieceAt(xi-1, yi+1) != null) {
                                return true;
                            }
                        }
                        else if (yf - yi == -2) {
                            if (pieceAt(xi-1, yi-1) != null) {
                                return true;
                            }
                        }
                    }
                    // if not a king, only go up/down depending on side
                    else {
                        if (this.pieces[xi][yi].isFire()) {
                            //can only go up
                            if (yf - yi == 2) {
                                if (pieceAt(xi-1, yi+1) != null) {
                                    return true;
                                }
                            }
                        }
                        else {
                            //can only go down
                            if (yf - yi == -2) {
                                if (pieceAt(xi-1, yi-1) != null) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            else {
                // if has captured, must capture
                //CAPTURE

                // TO THE RIGHT
                if (xf - xi == 2) {
                    // if king, can go up or down
                    if (this.pieces[xi][yi].isKing()) {
                        if (yf - yi == 2) {
                            if (pieceAt(xi+1, yi+1) != null) {
                                return true;
                            }
                        }
                        else if (yf - yi == -2) {
                            if (pieceAt(xi+1, yi-1) != null) {
                                return true;
                            }
                        }
                    }
                    // if not a king, only go up/down depending on side
                    else {
                        if (this.pieces[xi][yi].isFire()) {
                            //can only go up
                            if (yf - yi == 2) {
                                if (pieceAt(xi+1, yi+1) != null) {
                                    return true;
                                }
                            }
                        }
                        else {
                            //can only go down
                            if (yf - yi == -2) {
                                if (pieceAt(xi+1, yi-1) != null) {
                                    return true;
                                }
                            }
                        }
                    }
                }

                // TO THE LEFT
                else if (xf - xi == -2) {
                    // if king, can go up or down
                    if (this.pieces[xi][yi].isKing()) {
                        if (yf - yi == 2) {
                            if (pieceAt(xi-1, yi+1) != null) {
                                return true;
                            }
                        }
                        else if (yf - yi == -2) {
                            if (pieceAt(xi-1, yi-1) != null) {
                                return true;
                            }
                        }
                    }
                    // if not a king, only go up/down depending on side
                    else {
                        if (this.pieces[xi][yi].isFire()) {
                            //can only go up
                            if (yf - yi == 2) {
                                if (pieceAt(xi-1, yi+1) != null) {
                                    return true;
                                }
                            }
                        }
                        else {
                            //can only go down
                            if (yf - yi == -2) {
                                if (pieceAt(xi-1, yi-1) != null) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
        
                
    
    public void select(int x, int y) {
        
            if (pieceAt(x, y) == null) {  
                if (!(this.hasntSelectedYet)) {
                    this.hasntMovedYet = false; //bout to move yo
                    
                    place(pieceAt(this.selectedPiecePositionX, this.selectedPiecePositionY), x, y);
                    
                    int a = this.selectedPiecePositionX;
                    int b = this.selectedPiecePositionY;
                
                    
                    //See if it was a capture and if its a bomb
                    if (!(this.pieces[x][y].isBomb())) {
                        if ((x - this.selectedPiecePositionX) == 2 || (x - this.selectedPiecePositionX) == -2) {
                            this.hasCaptured = true;
                            if ((x - this.selectedPiecePositionX) == 2) {
                                if ((y - this.selectedPiecePositionY) == 2) {
                                    remove(x-1, y-1);
                                    this.selectedPiecePositionX = x;
                                    this.selectedPiecePositionY = y;
                                }
                                else {
                                    remove(x-1, y+1);
                                    this.selectedPiecePositionX = x;
                                    this.selectedPiecePositionY = y;
                                }
                            }
                            else {
                                if ((y - this.selectedPiecePositionY) == 2) {
                                    remove(x+1, y-1);
                                    this.selectedPiecePositionX = x;
                                    this.selectedPiecePositionY = y;
                                }
                                else {
                                    remove(x+1, y+1);
                                    this.selectedPiecePositionX = x;
                                    this.selectedPiecePositionY = y;
                                }
                            }
                        }
                    }
                    else {
                        if ((x - this.selectedPiecePositionX) == 2 || (x - this.selectedPiecePositionX) == -2) {
                            remove(x, y);
                            if (pieceAt(x, y+1) != null) {
                                if (this.pieces[x][y+1] != null) {
                                    if (!(this.pieces[x][y+1].isShield())) {
                                        remove(x, y+1);
                                    }
                                }
                            }
                            if (pieceAt(x, y-1) != null) {
                                if (this.pieces[x][y-1] != null) {
                                    if (!(this.pieces[x][y-1].isShield())) {
                                        remove(x, y-1);
                                    }
                                }
                            }
                            if (pieceAt(x+1, y+1) != null) {
                                if (this.pieces[x+1][y+1] != null) {
                                    if (!(this.pieces[x+1][y+1].isShield())) {
                                        remove(x+1, y+1);
                                    }
                                }
                            }
                            if (pieceAt(x-1, y+1) != null) {
                                if (this.pieces[x-1][y+1] != null) {
                                    if (!(this.pieces[x-1][y+1].isShield())) {
                                        remove(x-1, y+1);
                                    }
                                }
                            }
                            if (pieceAt(x+1, y) != null) {
                                if (this.pieces[x+1][y] != null) {
                                    if (!(this.pieces[x+1][y].isShield())) {
                                        remove(x+1, y);
                                    }
                                }
                            }
                            if (pieceAt(x-1, y) != null) {
                                if (this.pieces[x-1][y] != null) {
                                    if (!(this.pieces[x-1][y].isShield())) {
                                        remove(x-1, y);
                                    }
                                }
                            }
                            if (pieceAt(x-1, y-1) != null) {
                                if (this.pieces[x-1][y-1] != null) {
                                    if (!(this.pieces[x-1][y-1].isShield())) {
                                        remove(x-1, y-1);
                                    }
                                }
                            }
                            if (pieceAt(x+1, y-1) != null) {
                                if (this.pieces[x+1][y-1] != null) {
                                    if (!(this.pieces[x+1][y-1].isShield())) {
                                        remove(x+1, y-1);
                                    }
                                }
                            }
                        }
                    }
                    
                    pieceAt(this.selectedPiecePositionX, this.selectedPiecePositionY).move(x, y);

                    remove(a, b);
                }         
            }
            else {
                this.selectedPiecePositionX = x;
                this.selectedPiecePositionY = y;       
                this.hasntSelectedYet = false;
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            }
        
    }
    
    public void place(Piece p, int x, int y) {
        if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
            return;
        }
        else if (p == null) {
            return;
        }
        this.pieces[x][y] = p;     
    }
    
    public Piece remove(int x, int y) {
        
        if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
            System.out.println("Input out of bounds");
            return null;
        }
        else if (pieces[x][y] == null) {
            System.out.println("No piece at given location");
            return null;
        }
        Piece p = this.pieces[x][y];
        this.pieces[x][y] = null;
        return p;
    }
    
    public boolean canEndTurn() {
        if (hasntMovedYet == false || hasCaptured == true) {
            return true;
        }
        return false;
    }
        
    
    public void endTurn() {
        if (this.turn == 0) {
            this.turn = 1;
        }
        else {
            this.turn = 0;
        }
        this.hasntSelectedYet = true;
        this.hasntMovedYet = true;
        this.hasCaptured = false;
    }
    
    public String winner() {
        int firePieces = 0;
        int waterPieces = 0;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.pieces[i][j] != null) {
                    if (this.pieces[i][j].isFire()) {
                        firePieces += 1;
                    }
                    else {
                        waterPieces += 1;
                    }
                }
            }
        }
        
        if (firePieces > 0 && waterPieces == 0) {
            return "Fire";
        }
        else if (firePieces == 0 && waterPieces > 0) {
            return "Water";
        }
        else if (firePieces == 0 && waterPieces == 0) {
            return "No one";
        }
        return null;
    }
    
}











