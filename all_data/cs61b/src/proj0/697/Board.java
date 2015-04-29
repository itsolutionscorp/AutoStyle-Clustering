public class Board {
    private int whose_turn;
    private Piece[][] piece_info;
    private Piece current_p;
    private boolean haveMoved;
    private int curr_x;
    private int curr_y;
    
    public static void main(String[] args) {
        int N = 8;
        int[] intcheck = new int[2];
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board game = new Board(false);
        game.drawBoard(N);
        game.refreshThePiece();
        while(true) {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                intcheck = game.integerCheck(x, y);
                game.select(intcheck[0], intcheck[1]);
                game.drawBoard(N);
                game.refreshThePiece();
            }
            else if (StdDrawPlus.isSpacePressed()) {
                if (game.canEndTurn() == true) {
                    game.winner();
                    game.endTurn();
                } 
            }
        }
    }

    private int[] integerCheck(double x, double y) {
        int modified_x = (int) x;
        int modified_y = (int) y;
        int[] rv = new int[2];
        if (x < 0) {
            modified_x = 0;
        }
        else if (x > 8) {
            modified_x = 7;
        }
        if (y < 0) {
            modified_y = 0;
        }
        else if (y > 8) {
            modified_y = 7;
        }
        rv[0] = modified_x;
        rv[1] = modified_y;
        return rv;
    }
    
    public Board(boolean shouldBeEmpty) {
        haveMoved = false;
        whose_turn = 0;
        current_p = null;
        piece_info = new Piece[8][8];
        if (!shouldBeEmpty) {
            for (int i = 0; i < 4; i++) {
            piece_info[2*i][0] = new Piece(true, this, 2*i, 0, "pawn");
            piece_info[7 - 2*i][7] = new Piece(false, this, 7 - 2*i, 7, "pawn");
            piece_info[7 - 2*i][1] = new Piece(true, this, 7 - 2*i, 1, "shield");
            piece_info[2*i][6] = new Piece(false, this, 2*i, 6, "shield");
            piece_info[2*i][2] = new Piece(true, this, 2*i, 2, "bomb");
            piece_info[7 - 2*i][5] = new Piece(false, this, 7 - 2*i, 5, "bomb");
            }
        }
    }
    private String getType(Piece p) {
        if (p.isShield()) {
            return ("shield");
        }
        else if (p.isBomb()) {
            return ("bomb");
        }
        else {
            return ("pawn");
        }
    }

    private void refreshThePiece() {
        for (int k = 0; k < 8; k++) {
                for (int j = 0; j < 8; j++) {
                    if (piece_info[k][j] != null) {
                        if (piece_info[k][j].isFire()) {
                            if (piece_info[k][j].isKing()) {
                            StdDrawPlus.picture(k + .5, j + .5, "img/"+getType(piece_info[k][j])+"-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(k + .5, j + .5, "img/"+getType(piece_info[k][j])+"-fire.png", 1, 1);
                                }
                        }
                        else {
                            if (piece_info[k][j].isKing()) {
                            StdDrawPlus.picture(k + .5, j + .5, "img/"+getType(piece_info[k][j])+"-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(k + .5, j + .5, "img/"+getType(piece_info[k][j])+"-water.png", 1, 1);
                            }
                        }
                    }
                }
        }  
    }

    private void drawBoard(int N) {
        // StdDrawPlus.setXscale(0, N);
        // StdDrawPlus.setYscale(0, N);
        int x_axis = 0;
        int y_axis = 0;
        while (y_axis < N) {
            while (x_axis < N) {
                if ((x_axis + y_axis) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                  StdDrawPlus.setPenColor(StdDrawPlus.RED);  
                }
                StdDrawPlus.filledSquare((x_axis % 8) + .5, y_axis + .5, .5);
                x_axis += 1;
            }       
            y_axis += 1;
            x_axis = 0;
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x < 0 | x > 8 | y < 0 | y > 8) {
                                                     return null;
            
        }
        else if (piece_info[x][y] != null)           return piece_info[x][y];
        else                                         return null;
    }

    public boolean canSelect(int x, int y) {
        //A piece is selected
        if (pieceAt(x, y) != null) {
            //if you have already moved, you cannot select a new piece
            if (haveMoved) {
                if (pieceAt(x, y) != current_p) {
                    return false;
                }
            }
            //is this the player's piece? if not, cannot enter the statement
            if (pieceAt(x, y).side() == whose_turn) {
                //the player has already captured stuff with this piece
                if (pieceAt(x, y).hasCaptured()) {
                    //is it the current_piece? if not, return false;
                    if (pieceAt(x, y) != current_p) {
                        return false;
                    }
                }
                //at this point, the piece is the player's, and it's the first move.
                return true;
            }
            //return false for all other cases
            return false;
        }
        //space is selected
        else {
            //if we are selecting the square, we better have a piece selected
            if (current_p != null) {
                /*if you are fire, you move up
                 *also, if you are a king, you are like a honeybadger. 
                you don't give a shit */
                if (current_p.side() == 0 & !current_p.isKing()) {
                    /*if where you wanna go (y) is lower(smaller) 
                     *than where you are. you are going down - bad fire! */
                    if (curr_y >= y) {
                        return false;
                    }
                }
                //if you are water, go down.
                if (current_p.side() == 1 & !current_p.isKing()) {
                    /*if where you wanna go (y) is higher(larger) 
                     *than where you are (curr_y). you are 
                     *going down - antigravitational water! */
                    if (curr_y <= y) {
                        return false;
                    }
                }
                /*at this stage, the piece is 
                 * 1. fire going up,
                 * 2. water going down,
                 * 3. or king not giving a shit */
                //check this is a diagonal movement.
                if (Math.abs(curr_x - x) == Math.abs(curr_y - y)) {
                    //this is the first move.
                    if (!haveMoved) {
                        //the piece is moved one square. this is true
                        if (Math.abs(curr_x - x) == 1) {
                            return true;
                        }
                        //the piece is moved 2 squares. check first that there is a foe on the way
                        //and then return true
                        else if (Math.abs(curr_x - x) == 2) {
                            //check that there is a foe on the way
                            if (pieceAt((int) ((curr_x + x) / 2), (int) ((curr_y + y) / 2)).side() != current_p.side()) {
                                return true;
                            }
                        }
                        //all other movements beyond 2 squares are illegal return false
                        return false;
                    }
                    //this is NOT the first move
                    else {
                        //check that the movement is 2 units long.
                        if (Math.abs(curr_x - x) == 2) {
                            //check that there is a foe on the way
                            Piece foe = pieceAt(((curr_x + x) / 2), ((curr_y + y) / 2));
                            if (foe.side() != current_p.side()) {
                                return true;
                            }
                        }
                        else {
                        //all movements that are not of length 2 are illegal return false;
                            return false;
                        }
                    }
                        //all movements that are not diagonal are illegal
                }
                //all space clicking when there's no current_p will be ignored
                return false;
            }
            return false;
        }
    }

    public void select(int x, int y) {
        if (canSelect(x, y)) {
            if (pieceAt(x, y) != null) {
                if (!haveMoved) {
                    current_p = pieceAt(x, y);
                    curr_x = x;
                    curr_y = y;

                }
            }
            else {
                current_p.move(x, y);
                curr_x = x;
                curr_y = y;
                haveMoved = true;
                if (current_p.isBomb() && current_p.hasCaptured()) {
                    for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {    
                        if (pieceAt(curr_x + i, curr_y + j) != null) {
                            if (!pieceAt(curr_x + i, curr_y + j).isShield()) {
                                remove(curr_x + i,curr_y + j);
                            }
                        }
                    }}
                }
            }
        }  
    }

    public void place(Piece p, int x, int y) {
        //out of bound?
        if (x <= 7 | y <= 7) {
        //if the square not empty, empty
            if (piece_info[x][y] != null) {
                remove(x, y);
            }
        //now put the piece
            piece_info[x][y] = p;
        }
    }


    public Piece remove(int x, int y) {
        if (x > 7 && y > 7) {
            System.out.println("illegal selection : out of bound");
        }
        else if (pieceAt(x, y) != null) {
            Piece rv = pieceAt(x, y);
            piece_info[x][y] = null;
            return rv;
        }
        else {
            System.out.println("Please select a piece.");
        }
        return null;
    }    

    public boolean canEndTurn() {
        if (haveMoved) {
            return true;
        }
        return false;
    }
    

    public void endTurn() {
        if (whose_turn == 0) {
            whose_turn++;
        }
        else {
            whose_turn--;
        }
        current_p.doneCapturing();
        current_p = null;
        haveMoved = false;
    }

    public String winner() {
        int remaining_fire = 0;
        int remaining_water = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceAt(i, j) != null) {
                    if (pieceAt(i, j).side() == 0) {
                        remaining_fire += 1;
                    }
                    else {
                        remaining_water += 1;
                    }
                }
            }
        }
        if (remaining_water == 0 && remaining_fire != 0) {
            return ("Fire");
        }
        else if (remaining_fire == 0 && remaining_water != 0) {
            return ("Water");
        }
        else if (remaining_water == 0 && remaining_fire == 0) {
            return ("No one");
        }
        else {
            return null;
        }
    }
}

































