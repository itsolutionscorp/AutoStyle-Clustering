public class Board {
    private Piece[][] pieces;
    private boolean firesturn = true; //Fire moves first
    private boolean moveinprogress = false;
    private boolean hasselected = false;
    private boolean bombhasexploded = false; //An exploded bomb ends the turn
    private int[] currentselection = new int[2];
    private String winner = "No one";
    public static void main(String args[]) {
        int scale = 8;
        StdDrawPlus.setXscale(0, scale);
        StdDrawPlus.setYscale(0, scale);
        boolean gameover = false;
    	Board gameboard = new Board(false);
        while(!gameover) {
            if(gameboard.winner() != null) {
                gameover = true;
            }
            gameboard.drawBoard();
            StdDrawPlus.show(100);
            if(StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if(gameboard.canSelect(x, y)) {
                     gameboard.select(x, y);
                }
            }
            if(StdDrawPlus.isSpacePressed()) {
		        gameboard.endTurn(); //The check of whether this is allowed is done by the method itself. 
            }
        }
        if(gameboard.winner().equals("No one")) {
            System.out.println("Draw! The board is completely empty of pieces.");
        }
        else {
            System.out.println(gameboard.winner() + " has won!");
        }
    }
    public Board(boolean shouldBeEmpty) {
        currentselection[0] = 8; //Out of bounds
        currentselection[1] = 8;
        pieces = new Piece[8][];
        for(int i = 0; i < pieces.length; i++){
            pieces[i] = new Piece[8];
            for(int j = 0; j < pieces[i].length; j++){
                  pieces[i][j] = null;
            }
        }
        if(!shouldBeEmpty) {
            for(int i = 0; i < 8; i++){
                 for(int j = 0; j < 8; j++){
                      switch(i) {
                          case 0:
                              if(j%2 == 0){
                                   pieces[j][0] = new Piece(true, this, j, 0, "pawn");
                              }
                          break;
                          case 1:
                              if(j%2 == 1){
                                   pieces[j][1] = new Piece(true, this, j, 1, "shield");
                              }
                          break;
                          case 2:
                              if(j%2 == 0) {
                                   pieces[j][2] = new Piece(true, this, j, 2, "bomb");
                              }
                          break;
                          case 5:
                              if(j%2 == 1){
                                   pieces[j][5] = new Piece(false, this, j, 5, "bomb");
                              }
                          break;
                          case 6:
                              if(j%2 == 0){
                                   pieces[j][6] = new Piece(false, this, j, 6, "shield");
                              }
                          break;
                          case 7:
                              if(j%2 == 1){
                                   pieces[j][7] = new Piece(false, this, j, 7, "pawn");
                              }
                          break;
                      }
                 }
	    }
        }
    }
    public Piece pieceAt(int x, int y) {
        if(x < 8 && y < 8 && x >= 0 && y >=0) {
            return pieces[x][y];
        }
        return null;
    }
    public boolean canSelect(int x, int y) {
        if(bombhasexploded) {
            return false; //This unconditionally means that the turn is over and the player has no business selecting anything further.
        }
        if(x >= 8 || y >= 8 || x < 0 || y < 0) {
            return false; //Out of bounds
        }
        if(pieces[x][y] == null) {
            if(!hasselected){//Cannot select a blank square until a piece is selected
                return false;
            }
            if(validMove(currentselection[0], currentselection[1], x, y)){
                return true;
            }
            return false; //This move by the selected piece is not valid
        }
        if(firesturn == pieces[x][y].isFire()) {
            if(moveinprogress && hasselected){
                return false; //Player has already committed to a move and is trying to select a different piece.
            }
            return true;
        }
        return false; //The piece does not belong to the player
    }
    private boolean validMove(int xi, int yi, int xf, int yf) { //The method assumes the starting position is already valid
        if((xf >=8 || yf >=8 || xf < 0 || yf < 0)||(pieceAt(xi, yi) == null)||(pieceAt(xf, yf) != null)) { //Cannot move to an occupied or nonexistent square, nor move a nonexistent piece
            return false;
        }
        Piece piecetomove = pieces[xi][yi];
        int movedirection = 1;
        if(!piecetomove.isFire()) {
            movedirection = -1;
        }
        if(!moveinprogress && !piecetomove.hasCaptured() && (xf == xi - 1 || xf == xi + 1) && ((yf == yi + movedirection)||(yf == yi - movedirection && piecetomove.isKing()))) { //Normal moves; if an existing move is in progress, then that's the most it can move and it can move no further. Furthermore, a normal move cannot follow a capture.
            return true;
        }
        if(yf == yi + 2*movedirection) { //Forward captures; captures can be done an unlimited amount of times and so do not need to check moveinprogress.
            if(piecetomove.hasCaptured() && piecetomove.isBomb()) {
                return false; //This edge case probably will never be gotten to, but if for some reason a bomb that has captured has not been removed yet, it should not be movable.
            }
            if(xf == xi + 2) {
                return pieces[xi + 1][yi + movedirection] != null; //Must have a piece to capture
            }
            if(xf == xi - 2) {
                return pieces[xi - 1][yi + movedirection] != null;
            }
        }
        if((yf == yi - 2*movedirection) && piecetomove.isKing()) { //Backwards captures - kings only
            if(xf == xi + 2) {
                return pieces[xi + 1][yi - movedirection] != null; //Must have a piece to capture
            }
            if(xf == xi - 2) {
                return pieces[xi - 1][yi - movedirection] != null;
            }
        }
        return false; //None of the possible moves are legal
    }
    public void select(int x, int y) {
        if(hasselected) {//Meaning this will be a move, unless player is changing selection to another piece
            if(pieceAt(x, y) == null) {
                moveinprogress = true;
                Piece currentpiece = pieces[currentselection[0]][currentselection[1]];
                currentpiece.move(x, y);
                if(currentpiece.hasCaptured()) { //Implement the capture if it has moved;
                    int capturedpiecex = (x + currentselection[0])/2;
                    int capturedpiecey = (y + currentselection[1])/2;
                    remove(capturedpiecex, capturedpiecey);
                    if(currentpiece.isBomb()) {
                        int checkpointx = 0;
                        int checkpointy = 0;
                        for(int bombcheckpoint = 0; bombcheckpoint < 4; bombcheckpoint ++){
                            switch(bombcheckpoint) {
                                case 0:
                                    checkpointx = x + 1;
                                    checkpointy = y + 1;
                                break;
                                case 1:
                                    checkpointx = x - 1;
                                    checkpointy = y + 1;
                                break;
                                case 2:
                                    checkpointx = x - 1;
                                    checkpointy = y - 1;
                                break;
                                case 3:
                                    checkpointx = x + 1;
                                    checkpointy = y - 1;
                                break;
                            }
                            if(pieceAt(checkpointx, checkpointy) !=null && pieceAt(checkpointx, checkpointy).isFire() != firesturn && !pieceAt(checkpointx, checkpointy).isShield()) {
                                remove(checkpointx, checkpointy);
                            }
                        }
                        bombhasexploded = true;
                        currentpiece.doneCapturing();
                    }
                }
                pieces[x][y] = remove(currentselection[0], currentselection[1]);
                if(bombhasexploded) {
                    pieces[x][y] = null;
                    currentselection[0] = 8;
                    currentselection[1] = 8;
                    hasselected = false;
                }
            }
        }
        else {
           hasselected = true;
        }
        if(!bombhasexploded) {
            currentselection[0] = x;
            currentselection[1] = y;
        }
       
    }
    public void place(Piece p, int x, int y) {
        if(p != null && x < 8 && y < 8 && x >= 0 && y >= 0) {
            pieces[x][y] = p;
        }
        else {
            System.out.println("This piece placement is invalid - the piece is null and/or the position is out-of-range.");
        }
    }
    public Piece remove(int x, int y) {
        if(!(x >=8 || y >=8 || x < 0 || y < 0)) {
            Piece piecetoreturn = pieces[x][y];
            pieces[x][y] = null;
            return piecetoreturn;
        }
        return null;
    }
    public boolean canEndTurn() {
        if(!moveinprogress) { //Player has no turn to end, because the player has made no moves.
             return false;
        }
        return true;
    }
    public void endTurn() {
        if(canEndTurn()) {
            if(pieceAt(currentselection[0], currentselection[1]) != null) {
                pieceAt(currentselection[0], currentselection[1]).doneCapturing();
            }
             firesturn = !firesturn; //Switch the turn
             moveinprogress = false;
             currentselection[0] = 8;
             currentselection[1] = 8;
             hasselected = false;
             bombhasexploded = false;
        }
    }
    public String winner() {
        //Does not determine stalemates yet
        boolean firefound = false;
        boolean waterfound = false;
        for(int i = 0; i < pieces.length; i++) {
            for(int j = 0; j < pieces.length; j++) {
                if (pieceAt(i, j) != null){
                    if(pieceAt(i, j).isFire()) {
                        firefound = true;
                    }
                    else {
                        waterfound = true;
                    }
                }
                if(firefound && waterfound) {
                    break;
                }
            }
            if(firefound && waterfound) {
                break;
            }
        }
        if(!waterfound && !firefound) {
            winner = "No one";
        }
        else if(!waterfound){
            winner = "fire";
        }
        else if(!firefound){
            winner = "water";
        }
        /* else if(isstalemated()) {
            if(firesturn) {
                winner = "water";
            }
            else {
                winner = "fire";
            }
        } */
        else {
            winner = null;
        }
        return winner;
    }
    private static String generateimagename(Piece p) {//Returns the image name corresponding to a Piece
        if(p == null) {
             return null; //No piece no image
        }
        String name = "";
        if(p.isBomb()){
             name = "bomb-";
        }
        else if(p.isShield()){
             name = "shield-";
        }
        else {
             name = "pawn-";
        } //Determine type
        if(p.isFire()){ 
             name = name + "fire";
        }
        else {
             name = name + "water";
        }
        if(p.isKing()) {
             name = name + "-crowned";
        }
        return name + ".png";
    }
    private void drawBoard() {
        for(int i = 0; i < pieces.length; i++){
            for(int j = 0; j < pieces.length; j++){
                if(currentselection[0] == i && currentselection[1] == j) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE); //This is the currently selected square
                }
                else if((i + j) % 2 == 0) { //Checks square color
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if(pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + generateimagename(pieces[i][j]), 1, 1);
                }
            }
        }
    }
    private boolean isstalemated() {
        for(int i = 0; i < pieces.length; i++){
            for(int j = 0; j < pieces.length; j++){
                if(pieces[i][j] != null && pieces[i][j].isFire() == firesturn) { //A piece is stalemated when all of its possible moves is illegal. It follows that if no possible piece moves exist, the player as a whole is stalemated. This is a loss in orthodox checkers and that's the convention I'll follow here.
                    if(validMove(i, j, i + 1, j + 1)||validMove(i, j, i + 2, j + 2)||validMove(i, j, i - 1, j + 1)||validMove(i, j, i - 2, j + 2)||validMove(i, j, i - 1, j - 1)||validMove(i, j, i - 2, j - 2)||validMove(i, j, i + 1, j - 1)||validMove(i, j, i + 2, j - 2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
