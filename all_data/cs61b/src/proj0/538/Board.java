public class Board {
    /** Location of pieces. */
   // private static boolean[][] pieces;
    private Piece[][] pieces = new Piece[80][80];
    private Piece selected_piece = null;
    private boolean selected;
    private int gameState;
    private boolean canEndTurn = false;
    private boolean[][] possible_move = new boolean[8][8];
    //private boolean[][] possible_move_capture = new boolean[8][8];
    private int firePieces = 12;
    private int waterPieces = 12;
    private int totalPieces = 0;
    private boolean isEmpty = false;
    private int selected_x, selected_y;
    private boolean can_select_team = true;
    //private boolean canEndTurn = true;


    public Board(boolean shouldBeEmpty) {
        if (!shouldBeEmpty) {
            firePieces = 0;
            waterPieces = 0;
            resetPossibleMoves();
            Piece p;
                for (int i = 0; i < 8; i += 2) { // draw each piece
                p = new Piece(true, this, i, 0, "pawn");
                place(p, i, 0);
                p = new Piece(true, this, i, 1, "shield");
                place(p, i + 1, 1);
                p = new Piece(true, this, i, 2, "bomb");
                place(p, i, 2);
            }
        
            for (int i = 0; i < 8; i += 2) { // draw each piece
                p = new Piece(false, this, i, 7, "pawn");
                place(p, i+1, 7);
                p = new Piece(false, this, i, 6, "shield");
                place(p, i, 6);
                p = new Piece(false, this, i, 5, "bomb");
                place(p, i+1, 5);       
            }
        }
        
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.MAGENTA);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                // handle piece placement
                if (pieces[i][j] != null) {
                    totalPieces += 1;
                    drawPiece(pieces[i][j], i, j);
                    if (pieces[i][j].isFire()) {
                        firePieces += 1;
                    } else {
                        waterPieces += 1;
                    }
                } else {
                    fillInSquare(i, j);
                }
                // handle highlight box
                if ((selected_piece == pieces[i][j]) && (selected_piece != null)) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(i+.5, j+.5, .5);
                    drawPiece(pieces[i][j], i, j);
                }

            }
        }
    }

    private void drawPiece(Piece p, int x, int y) {
        if (p.isBomb()) {
            if (p.isFire()) {
                if (p.isKing()) {
                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
                } else {
                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                }
            } else {
                if (p.isKing()) {
                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
                } else {
                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
                }
            }
        } else if (!p.isShield() && !p.isBomb()) { // is pawn
            if (p.isFire()) {
                if (p.isKing()) {
                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
                } else {
                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
                }
            } else {
                if (p.isKing()) {
                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
                } else {
                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
                }
            }
        } else if (p.isShield()) {
            if (p.isFire()) {
                if (p.isKing()) {
                StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
                } else {
                StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
                }
            } else {
                if (p.isKing()) {
                StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
                } else {
                StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
                }
            }
        }
    }

    private void resetPossibleMoves() {
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                possible_move[i][j] = false; // they're all false 
            }
        }
    }

    private void drawBoardDefault(boolean isFire, Board b) {
        Piece p;
        // set all possible moves to false 
        firePieces = 0;
        waterPieces = 0;
        resetPossibleMoves();
        if (isFire) {
            for (int i = 0; i < 8; i += 2) { // draw each piece
                p = new Piece(true, b, i, 0, "pawn");
                place(p, i, 0);
                p = new Piece(true, b, i, 1, "shield");
                place(p, i + 1, 1);
                p = new Piece(true, b, i, 2, "bomb");
                place(p, i, 2);
            }
        }
        if (!isFire) {
            for (int i = 0; i < 8; i += 2) { // draw each piece
                p = new Piece(false, b, i, 7, "pawn");
                place(p, i+1, 7);
                p = new Piece(false, b, i, 6, "shield");
                place(p, i, 6);
                p = new Piece(false, b, i, 5, "bomb");
                place(p, i+1, 5);       
            }
        }
    }

    private void fillInSquare(int x, int y) {
        boolean x_even = false; 
        boolean y_even = false;
        if (x % 2 == 0) {
            x_even = true;
        }
        if (y % 2 == 0) {
            y_even = true;
        }
        if (x_even && y_even || !x_even && !y_even) {
            StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        } else {
            StdDrawPlus.setPenColor(StdDrawPlus.MAGENTA);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return null;
        } else {
            Piece object_in_box = pieces[x][y];
            if (object_in_box != null) {
                return object_in_box;
            } else {
                return null;
            }
        }
    }

    private boolean sameTeam(Piece a, Piece b) {
        if ((a.isFire() && b.isFire()) || (!a.isFire() && !b.isFire())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canSelect(int x, int y) {
        if (selected_piece != null) { // trying to move piece
            if (pieceAt(x, y) == null) {
                // handling captures
                if (x == selected_x + 2 && y == selected_y + 2) { // for fire, hop to the right
                    if (pieceAt(x-1, y-1) != null && (!pieceAt(x-1, y-1).isFire() && selected_piece.isFire() || pieceAt(x-1, y-1).isFire() && selected_piece.isKing() && !selected_piece.isFire()) && (selected_piece.isFire() || (!selected_piece.isFire() && selected_piece.isKing()))) {
                        System.out.println("Can SElect 1");
                        //selected_piece.hasCaptured = true;
                        return true;
                    }
                } else if (x == selected_x - 2 && y == selected_y + 2) { // fire, hopping to the left
                    if (pieceAt(x+1, y-1) != null && (!pieceAt(x+1, y-1).isFire() && selected_piece.isFire() || pieceAt(x+1, y-1).isFire() && selected_piece.isKing() && !selected_piece.isFire()) && (selected_piece.isFire() || (!selected_piece.isFire() && selected_piece.isKing()))) {
                        System.out.println("Can SElect 2");
                        return true;
                    }
                } else if (x == selected_x - 2 && y == selected_y - 2) { // fire, hopping to the left
                    if (pieceAt(x+1, y+1) != null && (pieceAt(x+1, y+1).isFire() && !selected_piece.isFire() || !pieceAt(x+1, y+1).isFire() && selected_piece.isKing() && selected_piece.isFire()) && (!selected_piece.isFire() || (selected_piece.isFire() && selected_piece.isKing()))) {
                        System.out.println("Can SElect 3");
                        //selected_piece.hasCaptured = true;
                        return true;
                    }
                } else if (x == selected_x + 2 && y == selected_y - 2) { // fire, hopping to the left
                    if (pieceAt(x-1, y+1) != null && (pieceAt(x-1, y+1).isFire() && !selected_piece.isFire() || !pieceAt(x-1, y+1).isFire() && selected_piece.isKing() && selected_piece.isFire()) && (!selected_piece.isFire() || (selected_piece.isFire() && selected_piece.isKing()))) {
                       System.out.println("Can SElect 4");
                        //selected_piece.hasCaptured = true;
                        return true;
                    }
                }       
                System.out.println("Can Select 5");
                System.out.println("SHIELD POS1: " + selected_x + selected_y + " SHIELD POS2: " + x + y + possible_move[x][y]);
                //System.out.println("YY " + selected_piece.isFire() + possible_move[x][y] + x + y);
                return possible_move[x][y];
            } else if (pieceAt(x, y).side() == selected_piece.side() && can_select_team) {
                System.out.println("Can SElect 6");
                return true;
            } else if (pieceAt(x, y).side() != selected_piece.side()) {
                System.out.println("Can't move here, that's another team piece");
                return false;
            } else {
                return false;
            }
        } else {
            if (pieceAt(x, y) == null) {
                System.out.println("Please select a square");
                return false;
            }
            if (gameState % 2 == pieceAt(x, y).side()) {
                System.out.println("Can SElect 191293");
                return true;
            } else {
                System.out.println("You can't move this piece, wait for your turn");
                return false;
            }
        }
     } 

    private void isThere(int x, int y) {
        Piece z = this.pieceAt((int) x, (int) y);
        if (z != null) {
            System.out.println("There is a piece here");
        } else {
            System.out.println("there is not a piece ehre");
        }
    }

    public void select(int x, int y) {
        //this.isThere(x, y);
        if (pieceAt(x, y) == null) {
            if (selected_piece != null && pieceAt(x, y) == null) { // handle movement
                resetPossibleMoves();   
                canEndTurn = true; // will be true no matter what
                place(selected_piece, x, y);
                this.remove(selected_x, selected_y);
                selected_piece.move(x, y);
                //selected_piece = pieceAt(x, y);
                can_select_team = false;
                //System.out.println("selected_piece: " + selected_piece.x + selected_piece.y);
                if (selected_piece.hasCaptured()) {
                selected_x = x;
                selected_y = y;
                }

            }
        } else {
            if (selected_piece == null || (selected_piece.side() == pieceAt(x,y).side())) {
            selected_piece = pieceAt(x, y);
            selected_x = x;
            selected_y = y;
            resetPossibleMoves();
            fetchPossibleMoves(selected_x, selected_y);
          } else {
            //selected_piece = null;
          }
        }
    }

    private void fetchPossibleMoves(int x, int y) {
        if (selected_piece.isFire()) { // handle fire possible moves
                if ((x + 1 < 8) && (y + 1 < 8)) {
                    possible_move[x+1][y+1] = true;
                } 
                if ((y + 1 < 8) && (x - 1 > -1)) {
                    possible_move[x-1][y+1] = true;
                }
                
                // add king moves!
                if (selected_piece.isKing()) {
                    if ((x - 1 > -1) && (y - 1 > -1)) {
                        possible_move[x-1][y-1] = true;
                    } 
                if ((y - 1 > -1) && (x + 1 < 8)) {
                    possible_move[x+1][y-1] = true;
                    }
                }
            } else { // handle water possible moves
                if ((x - 1 > -1) && (y - 1 > -1)) {
                possible_move[x-1][y-1] = true;
                } 
                if ((y - 1 > -1) && (x + 1 < 8)) {
                possible_move[x+1][y-1] = true;
                }

                if (selected_piece.isKing()) {
                    if ((x + 1 < 8) && (y + 1 < 8)) {
                    possible_move[x+1][y+1] = true;
                    } 
                if ((y + 1 < 8) && (x - 1 > -1)) {
                    possible_move[x-1][y+1] = true;
                    }
                 }
             }
    }

    public void place(Piece p, int x, int y) {
        //board.remove(this.x, this.y);
        //drawPiece(p,x,y);
        if (pieces[x][y] != null) { // there is a piece in this x,y coordinate spot
            System.out.println("Not null"); // checkmate stuff
        }
        if (x > 7 || y > 7 || x < 0 || y < 0 || p == null) {
            System.out.println("Out of bounds");
            return;
        }
        // check if p is already on the board, if so, remove p from board then re-add
        pieces[x][y] = p;
        if (this.gameState == 0) {
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        //System.out.println("Removing...");
        Piece piece = pieces[x][y];
        if (piece == null) {
            return null;
        } else {
            //System.out.println("Removing a piece...");
            //piece = null;
            pieces[x][y] = null;
            return piece;
        }
    }

    public String winner() {
        this.firePieces = 0;
        this.waterPieces = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = pieces[i][j];
                if (p != null) {
                    if (p.isFire()) {
                        this.firePieces += 1;
                    } else {
                        this.waterPieces += 1;
                    }
                }
            }
         }
        if (this.waterPieces == 0 && this.firePieces == 0) {
            return "No one";
        } else if (this.firePieces == 0) {
            return "Water";
         } else if (this.waterPieces == 0) {
            return "Fire"; 
         } else {
            return null;
         }
    }

    public boolean canEndTurn() {
        return this.canEndTurn;
    }

    public void endTurn() {
        selected_piece.doneCapturing();
        this.canEndTurn = false;
        this.can_select_team = true;
        this.selected_piece = null;
        this.selected_x = 0;
        this.selected_y = 0;
        this.resetPossibleMoves();
        this.gameState += 1;   
        //this.calculateTotals();
    }

    private boolean canMoveAgain() {
         for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (canSelect(i,j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void calculateTotals() {
        this.firePieces = 0;
        this.waterPieces = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = pieces[i][j];
                if (p != null) {
                    if (p.isFire()) {
                        this.firePieces += 1;
                    } else {
                        this.waterPieces += 1;
                    }
                }
            }
         }
      }



    public static void main(String[] args) {
        Board newBoard = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
           // newBoard.drawBoardDefault(true, newBoard);
            //newBoard.drawBoardDefault(false, newBoard);
            newBoard.drawBoard(N);
            newBoard.gameState = 0; // begin game 
            //newBoard.calculateTotals();
            while(newBoard.winner() == null) { // this refreshes a bunch of times
               // System.out.println("GameState: " + newBoard.gameState);  
                if (StdDrawPlus.mousePressed()) { // game state has changed
                    double x = StdDrawPlus.mouseX();
                    double y = StdDrawPlus.mouseY();
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    if (newBoard.canSelect((int) x, (int) y)) {
                    newBoard.select((int) x, (int) y);
                    } else {
                        System.out.println("Can't select!");
                    } 
                } else if (StdDrawPlus.isSpacePressed()) {
                    if (newBoard.canEndTurn()) {
                        newBoard.endTurn();
                    } else {
                        System.out.println("Make a move!");
                    }
                }
                newBoard.drawBoard(N);
                StdDrawPlus.show(100);
            }
            System.out.println("Winner: " + newBoard.winner());
            return;
            //System.out.println(newBoard.winner);
    }
}

