public class Board { 

    private Piece[][] total_pieces;
    private Piece selected;
    private boolean piece_moved;
    private boolean piece_selected;
    private int x_selected, y_selected;
    private boolean fire_wins = false;
    private boolean water_wins = false;
    private boolean no_winner = false;
    private int curr_player = 0; //game starts off with fire

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        Piece piece = this.total_pieces[x][y];
                        String picFile;
                        if (piece != null) {
                            if (piece.isShield()) {
                                picFile = "/shield";
                            } else if (piece.isBomb()) {
                                picFile = "/bomb";
                            } else {
                                picFile = "/pawn";
                            }
                            if (piece != null && piece.isFire()) {
                                if (piece.isKing()) {
                                    StdDrawPlus.picture(x + .5, y + .5, "img" + picFile + "-fire-crowned.png", 1, 1);
                                } else {
                                    StdDrawPlus.picture(x + .5, y + .5, "img" + picFile + "-fire.png", 1, 1);
                                }
                            } else if (piece != null) {
                                if (piece.isKing()) {
                                    StdDrawPlus.picture(x + .5, y + .5, "img" + picFile + "-water-crowned.png", 1, 1);
                                } else {
                                    StdDrawPlus.picture(x + 0.5, y + 0.5, "img" + picFile + "-water.png", 1, 1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Board board = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while (board.notEmpty()) {
            board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int) x, (int) y)) {
                    board.select((int) x, (int) y);
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
                }
            }

            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    System.out.println("The turn has ended");
                    board.endTurn();
                }
            }
            StdDrawPlus.show(100);
        }
        // board.winner();
    }

    public Board(boolean shouldBeEmpty) {
        total_pieces = new Piece[8][8];
        if (shouldBeEmpty) {
            // initializes an empty Board
        } else {
            // initializes Board with default configuration
            // Water Pieces
            placePieces(false, 1, 5, "bomb", this); // water bombs
            placePieces(false, 0, 6, "shield", this); // water shields
            placePieces(false, 1, 7, "pawn", this); // water pawns  

            // Fire Pieces        
            placePieces(true, 0, 2, "bomb", this); // fire bombs
            placePieces(true, 1, 1, "shield", this); // fire shields
            placePieces(true, 0, 0, "pawn", this); // fire pawns
        }
    }

    // Determines location to place pieces on Board
    private void placePieces(boolean isFire, int start_index, int start_row, String type, Board b) {
        for (int x = 0; x <= 7; x++) {
            if (start_index == 0) {
                if (x % 2 == 0) {
                    b.total_pieces[x][start_row] = new Piece(isFire, this, x, start_row, type);
                }
            }
            if (start_index == 1) {
                if (x % 2 == 1) {
                    b.total_pieces[x][start_row] = new Piece(isFire, this, x, start_row, type);
                }
            }
        }
    }

    // Gets the piece at position (x, y) on the board, or null if there is 
    // no piece. If (x, y) are out of bounds, returns null.
    public Piece pieceAt(int x, int y) {
        if (x > 7 || y > 7) {
            return null;
        } else {
            return total_pieces[x][y];
        }
    }

    public boolean canSelect(int x, int y) {
        if (total_pieces[x][y] != null) {
            if (!piece_selected || (piece_selected && !piece_moved)) {
                System.out.println(curr_player == total_pieces[x][y].side());
                return (curr_player == total_pieces[x][y].side());
            } else {
                return false;
            }
        } else {
            if (piece_selected && !piece_moved && validMove(x_selected, y_selected, x, y)) {
                return (curr_player == total_pieces[x_selected][y_selected].side());
            } else if (piece_selected && selected.hasCaptured() && validMove(x_selected, y_selected, x, y)) {
                return (curr_player == total_pieces[x_selected][y_selected].side());
            } else {
                return false;
            }
        } 
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece piece = total_pieces[xi][yi];
        if (piece_moved && piece.hasCaptured()) {
            if (piece.isKing()) {
                if ((Math.abs(yf - yi) == 2) && (Math.abs(xf - xi) == 2)) {
                    return (total_pieces[xf][yf] == null) && canCapture(piece, xi, yi, xf, yf);
                }
                return false;
            } else if (piece.side() == 0) {  // if fire piece
                if ((yf - yi == 2) && (Math.abs(xf - xi) == 2)) {
                    return (total_pieces[xf][yf] == null) && canCapture(piece, xi, yi, xf, yf);
                }
                return false;
            } else {
                if ((yi - yf == 2) && (Math.abs(xf - xi) == 2)) {
                    return (total_pieces[xf][yf] == null) && canCapture(piece, xi, yi, xf, yf);
                }
                return false;
            }
        }
        if (piece.isKing()) { 
            if ((Math.abs(yf - yi) == 1) && (Math.abs(xf - xi) == 1)) {
                return (total_pieces[xf][yf] == null) && !piece_moved;
            } else if ((Math.abs(yf - yi) == 2) && (Math.abs(xf - xi) == 2)) {
                return (total_pieces[xf][yf] == null) && canCapture(piece, xi, yi, xf, yf);
            } else {
                return false;
            }
        }
        if (piece.side() == 0) { //if piece is a fire piece
            if ((yf - yi == 1) && (Math.abs(xf - xi) == 1)) {
                System.out.println(piece_moved);
                return (total_pieces[xf][yf] == null) && !piece_moved;
            } else if ((yf - yi == 2) && (Math.abs(xf - xi) == 2)) {
                return (total_pieces[xf][yf] == null) && canCapture(piece, xi, yi, xf, yf);
            } else {
                return false;
            }
        } else { // if piece is water
            if ((yi - yf == 1) && (Math.abs(xf - xi) == 1)) {
                return (total_pieces[xf][yf] == null) && !piece_moved;
            } else if ((yi - yf == 2) && (Math.abs(xf - xi) == 2)) {
                return (total_pieces[xf][yf] == null) && canCapture(piece, xi, yi, xf, yf);
            } else {
                return false;
            }
        }
    }

    private boolean canCapture(Piece piece, int xi, int yi, int xf, int yf) {
        int x_captured_piece = ((xi + xf) / 2);
        int y_captured_piece = ((yi + yf) / 2);
        Piece captured_piece = total_pieces[x_captured_piece][y_captured_piece];
        if (captured_piece != null) {
            return (!piece_moved || (piece.hasCaptured()) && (piece.side() != captured_piece.side()));
        } else {
            return false;
        }
    }

    public void select(int x, int y) {
        if (total_pieces[x][y] == null) {
            selected.move(x,y);
        }
        if (total_pieces[x][y] != null) {
            x_selected = x;
            y_selected = y;
            selected = total_pieces[x][y];
            piece_selected = true;
        }
    }

    public void place(Piece p, int x, int y) {
        if (x <= 7 && y <= 7 && p != null) {
            total_pieces[x][y] = p;
            x_selected = x;
            y_selected = y;
        }  
        selected = null;
    }

    public Piece remove(int x, int y) {
        if (x <= 7 && y <= 7) {
            Piece removed_piece = total_pieces[x][y];
            total_pieces[x][y] = null;
            piece_moved = true;
            return removed_piece;
        } 
        return null;
    }


    public boolean canEndTurn() {
        if ((selected != null && selected.hasCaptured()) || piece_moved) {
            return true;
        } else {
            return false;
        }
    }

    public void endTurn() {
        piece_moved = false;
        piece_selected = false;
        selected.doneCapturing();
        selected = null;
        x_selected = 8;
        y_selected = 8;
        curr_player = 1 - curr_player;

    }

     private boolean notEmpty() {
        boolean fire_left = false;
        boolean water_left = false;
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                if (total_pieces[x][y] != null) {
                    Piece piece = total_pieces[x][y];
                    if (total_pieces[x][y] != null) {
                        if (piece.side() == 0) {
                            fire_left = true;
                        }
                        if (piece.side() != 0) {
                            water_left = true;
                        }
                    }
                }
            }
        }
        // if no more water pieces left, fire wins.
        
        if (!fire_left && !water_left) {
            no_winner = true;
        } else if (!water_left) {
            fire_wins = true;
        } else if (!fire_left) {
            water_wins = true;
        }
        // returns true if the board is not empty
        return true;
    }

    public String winner() {

        boolean fire_left = false;
        boolean water_left = false;
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                if (total_pieces[x][y] != null) {
                    Piece piece = total_pieces[x][y];
                    if (total_pieces[x][y] != null) {
                        if (piece.side() == 0) {
                            fire_left = true;
                        }
                        if (piece.side() != 0) {
                            water_left = true;
                        }
                    }
                }
            }
        }
        // if no more water pieces left, fire wins.
        
        if (!fire_left && !water_left) {
            no_winner = true;
        } else if (!water_left) {
            fire_wins = true;
        } else if (!fire_left) {
            water_wins = true;
        }


        
        if (no_winner) {
            return "No one";
        } else if (fire_wins) {
            return "Fire";
        } else if (water_wins) {
            return "Water";
        } else {
            return null;
        }
    }
}