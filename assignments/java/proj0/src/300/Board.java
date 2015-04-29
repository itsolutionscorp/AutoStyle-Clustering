
public class Board {

    private int size;
    private int turn;
    private boolean selected;
    private int moved;
    private Piece current;
    private boolean notDone;
    private int thingz;
    private Board b;
    private Piece[][] pieces = new Piece[8][8];

    public Board(boolean shouldBeEmpty) {
        //The constructor for Board. If shouldBeEmpty is true, initializes 
        //an empty Board. Otherwise, initializes a Board with the default 
        //configuration. Note that an empty Board could possibly be useful 
        //for testing purposes.
        size = 8;
        turn = 0;
        moved = 0;
        current = null;
        thingz = 0;
        selected = false;
        notDone = true;
        if (! shouldBeEmpty) {
            Piece fp1 = new Piece(true, this, 0, 0, "pawn");
            Piece fp2 = new Piece(true, this, 2, 0, "pawn");
            Piece fp3 = new Piece(true, this, 4, 0, "pawn");
            Piece fp4 = new Piece(true, this, 6, 0, "pawn");
            Piece fs1 = new Piece(true, this, 1, 1, "shield");
            Piece fs2 = new Piece(true, this, 3, 1, "shield");
            Piece fs3 = new Piece(true, this, 5, 1, "shield");
            Piece fs4 = new Piece(true, this, 7, 1, "shield");
            Piece fb1 = new Piece(true, this, 0, 2, "bomb");
            Piece fb2 = new Piece(true, this, 2, 2, "bomb");
            Piece fb3 = new Piece(true, this, 4, 2, "bomb");
            Piece fb4 = new Piece(true, this, 6, 2, "bomb");
            Piece wb1 = new Piece(false, this, 1, 5, "bomb");
            Piece wb2 = new Piece(false, this, 3, 5, "bomb");
            Piece wb3 = new Piece(false, this, 5, 5, "bomb");
            Piece wb4 = new Piece(false, this, 7, 5, "bomb");
            Piece ws1 = new Piece(false, this, 0, 6, "shield");
            Piece ws2 = new Piece(false, this, 2, 6, "shield");
            Piece ws3 = new Piece(false, this, 4, 6, "shield");
            Piece ws4 = new Piece(false, this, 6, 6, "shield");
            Piece wp1 = new Piece(false, this, 1, 7, "pawn");
            Piece wp2 = new Piece(false, this, 3, 7, "pawn");
            Piece wp3 = new Piece(false, this, 5, 7, "pawn");
            Piece wp4 = new Piece(false, this, 7, 7, "pawn");
            pieces[0][0] = fp1;
            pieces[2][0] = fp2;
            pieces[4][0] = fp3;
            pieces[6][0] = fp4;
            pieces[1][1] = fs1;
            pieces[3][1] = fs2;
            pieces[5][1] = fs3;
            pieces[7][1] = fs4;
            pieces[0][2] = fb1;
            pieces[2][2] = fb2;
            pieces[4][2] = fb3;
            pieces[6][2] = fb4;
            pieces[1][5] = wb1;
            pieces[3][5] = wb2;
            pieces[5][5] = wb3;
            pieces[7][5] = wb4;
            pieces[0][6] = ws1;
            pieces[2][6] = ws2;
            pieces[4][6] = ws3;
            pieces[6][6] = ws4;
            pieces[1][7] = wp1;
            pieces[3][7] = wp2;
            pieces[5][7] = wp3;
            pieces[7][7] = wp4;
        }
    }

    public Piece pieceAt(int x, int y) {
        //Gets the piece at position (x, y) on the board, or null if there 
        //is no piece. If (x, y) are out of bounds, returns null.
        if ((x >= size) | (x < 0) | (y >= size) | (y < 0)) {
            return null;
        } else
        if (pieces[x][y] == null) {
            return null;
        } else {
            return pieces[x][y];
        }
    }

    public boolean canSelect(int x, int y) {
        // Returns true if there is a piece at (x, y) and it can be selected.
        // A piece may be selected if it is the corresponding player’s turn 
        //and one of the following is true:
            //The player has not selected a piece yet.
            //The player has selected a piece, but did not move it.
        //An empty square may be selected if one of the following is true:
            //During this turn, the player has selected a Piece which hasn’t 
                //moved yet and is selecting an empty spot which is a valid 
                //move for the previously selected Piece.
            //During this turn, the player has selected a Piece, captured, 
                //and has selected another valid capture destination. 
                //(You may select as many captures in a row as long as they are 
                //all valid and from the same piece.)
        Piece p = pieceAt(x, y);
        if ((p == null) && (current != null)) {
            int cside = current.side();
            if ((selected == true) && (cside == turn) && (moved == 0) && 
                            (validMove(xPos(current), yPos(current), x, y))) {
                return true;
            } else if ((selected == true) && (cside == turn) && 
                            (current.hasCaptured()) && 
                            (attemptedCapture(xPos(current), yPos(current), x, y))) {
                return true;
            } else {
                return false;
            }
        } else
        if ((p == null) & (current == null)) {
            return false;
        } else
        if (p.side() == turn) {
            if (selected == false) {
                return true;
            } else if ((selected == true) && (moved == 0)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean attemptedCapture(int xi, int yi, int xf, int yf) {
        int dx = Math.abs(xf - xi);
        int dy = Math.abs(yf - yi);
        if ((dx == 2) | (dy == 2)) {
            return validMove(xi, yi, xf, yf);
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        //Returns true if the piece at (xi, yi) can either move to (xf, yf) or 
        //capture to (xf, yf) in a valid fashion compatible with the rules.
        if ((xf < 0) | (xf >= size) | (yf < 0) | (yf >= size)) {
            return false;
        }
        int dx = xf - xi;
        int dy = yf - yi;
        if ((dy < 0) && (current.isFire()) && (! current.isKing())) {
            return false;
            } 
        else 
        if ((dy > 0) && (! current.isFire()) && (! current.isKing())) {
            return false;
        } 
        else {
        int adx = Math.abs(dx);
        int ady = Math.abs(dy);
        if ((adx == 1) && (ady == 1)) {
            if (moved != 0) {
                return false;
            } 
            else {
            return true;}
        } 
        else
        if ((adx == 2) && (ady == 2)) {
            int mdx = (xf + xi) / 2;
            int mdy = (yf + yi) / 2;
            Piece toBeCaptured = pieceAt(mdx, mdy);
            if (toBeCaptured == null) {
                return false;
            } 
            else
            if (toBeCaptured.side() == current.side()) {
                return false;
            }
            else {return true;}
        } else {return false;}
    }
    }

    public void select(int x, int y) {
        //Selects the piece at (x, y) if possible. Optionally, it is recommended 
        //to color the background of the selected square white on the GUI via 
        //the pen color function. For any piece to perform a capture, that piece 
        //must have been selected first.
            Piece thing = pieceAt(x, y);
            if ((thing == null) && (validMove(xPos(current), yPos(current), x, y))) {
                place(current, x, y);
                current.move(x, y);
            } else {
                current = thing;
                selected = true;
            }
    }

    public void place(Piece p, int x, int y) {
        //Places p at (x, y). If (x, y) is out of bounds or if p is null, does 
        //nothing. If p already exists in the current Board, first removes it 
        //from its initial position. If another piece already exists at 
        //(x, y), p will replace that piece. (This method is potentially useful 
        //for creating specific test circumstances.)
        if ((x >= 0) && (x < size) && (y >= 0) && (y < size) && (p != null)) {
            int oldx = xPos(p);
            int oldy = yPos(p);
            if ((oldx != -1) && (oldy != -1)) { // if already on the board
                remove(oldx, oldy);
                moved += 1;
                pieces[x][y] = p;
            }
            if ((oldx == -1) && (oldy == -1)) { // if not on the board
                pieces[x][y] = p;
            }
        }
    }

    private int xPos(Piece p) {
        int i = 0;
        while (i < size) {
            int j = 0;
            while (j < size) {
                if (pieces[i][j] == p) {
                    return i;
                }
                j += 1;
            }
            i += 1;
        }
        return -1; 
    }

    private int yPos(Piece p) {
        int i = 0;
        while (i < size) {
            int j = 0;
            while (j < size) {
                if (pieces[i][j] == p) {
                    return j;
                }
                j += 1;
            }
            i += 1;
        }
        return -1; 
    }


    public Piece remove(int x, int y) {
        //Executes a remove. Returns the piece that was removed. If the input 
        //(x, y) is out of bounds, returns null and prints an appropriate message. 
        //If there is no piece at (x, y), returns null and prints an appropriate 
        //message.
        if ((x >= size) | (x < 0) | (y >= size) | (y < 0)) {
            System.out.println("remove inputs are out of bounds.");
            return null;
        }
        Piece p = pieceAt(x, y);
        if (p == null) {
            System.out.println("remove is trying to remove from an empty square.");
            return null;
        }
        pieces[x][y] = null;
        return p;
    }

    public boolean canEndTurn() {
        //Returns whether or not the the current player can end their turn. To 
        //be able to end a turn, a piece must have moved or performed a capture.
        if (moved > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void endTurn() {
        //Called at the end of each turn. Handles switching of players and 
        //anything else that should happen at the end of a turn.
        selected = false;
        if (turn == 0) {
            turn = 1;
        } else {
            turn = 0;
        }
        moved = 0;
        current.doneCapturing();
        current = null;
    }

    public String winner() {
        //Returns the winner of the game. "Fire", "Water", "No one" (tie / no 
        //pieces on the board), or null if the game is not yet over. Assume 
        //there is no stalemate situation in which the current player has 
        //pieces but cannot legally move any of them (In this event, just 
        //leave it at null). Determine the winner solely by the number of 
        //pieces belonging to each team.
        int fires = numPiecesOfType("fire");
        int waters = numPiecesOfType("water");
        if ((fires == 0) && (waters != 0)) {
            return "Water";
        } else
        if ((waters == 0) && (fires != 0)) {
            return "Fire";
        } else
        if ((waters == 0) && (fires == 0)) {
            return "No one";
        } else {
            return null;
        }
    } 

    private int numPiecesOfType(String type) {
        if (type.equals("fire")) {
            int answer = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (pieces[i][j] != null) {
                        if (pieces[i][j].isFire() == true) {
                            answer += 1;
                    }   }
                }
            }
            return answer;
        } else if (type.equals("water")) {
            int answer = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (pieces[i][j] != null) {
                        if (pieces[i][j].isFire() == false) {
                            answer += 1;
                    }   }
                }
            }
            return answer;
        } else {return 0;}
    }

    //DRAWING THE BOARD

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        if (pieces[i][j].isKing()) {
                            if (pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            } else if (pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }   
                            } else {
                                if (pieces[i][j].isShield()) {
                                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                                } else if (pieces[i][j].isBomb()) {
                                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                                } else {
                                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                                }
                            }
                        } else {
                            if (pieces[i][j].isKing()) {
                            if (pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            } else if (pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }   
                            } else {
                                if (pieces[i][j].isShield()) {
                                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                                } else if (pieces[i][j].isBomb()) {
                                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                                } else {
                                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                                }
                            }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        //starts a GUI supported version of the game.
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
        while(b.notDone) {
            b.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                    b.select(x, y);
                }
                if (b.thingz == 2) {
                    b.notDone = false;
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            if ((b.numPiecesOfType("water") == 0) | (b.numPiecesOfType("fire") == 0)) {
                if (b.thingz != 2) {
                    System.out.println("The winner is " + b.winner());
                }
                b.thingz = 2;
            }
            StdDrawPlus.show(25);   
        }
        return;
    }

}