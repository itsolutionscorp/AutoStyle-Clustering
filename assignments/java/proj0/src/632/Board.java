//  @author Judiel Salandanan

public class Board {

    /** Location of pieces. */
    private Piece[][] pieces = new Piece[8][8];

    // Active player variables.
    private int turn = 0;
    private Piece selectedPiece;
    private int selectedPieceX;
    private int selectedPieceY;
    private int oldX;
    private int oldY;
    private boolean hasMoved = false;
    private int waterSide;
    private int fireSide;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty == true) {
            waterSide = 0;
            fireSide = 0;
        }
    	if (shouldBeEmpty == false) {
            waterSide = 12;
            fireSide = 12;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                        if (j == 0) {
                        pieces[i][j] = new Piece(true, this, i, j, "pawn");
                        }
                        if (j == 1) {
                        pieces[i][j] = new Piece(true, this, i, j, "shield");
                        }
                        if (j == 2) {
                        pieces[i][j] = new Piece(true, this, i, j, "bomb");
                        }
                        if (j == 5) {
                        pieces[i][j] = new Piece(false, this, i, j, "bomb");
                        }
                        if (j == 6) {
                        pieces[i][j] = new Piece(false, this, i, j, "shield");
                        }
                        if (j == 7) {
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");
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
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (selectedPiece != null) {
                    highlightSquare(selectedPieceX, selectedPieceY);
                }
                        if (pieces[i][j] != null) {
                            if (pieces[i][j].isFire() && pieces[i][j].isBomb() == false
                                && pieces[i][j].isShield() == false && pieces[i][j].isKing() == false) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }                            
                            if (pieces[i][j].isFire() && pieces[i][j].isBomb()
                                && pieces[i][j].isKing() == false) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                            if (pieces[i][j].isFire() && pieces[i][j].isShield()
                                && pieces[i][j].isKing() == false) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }        
                            if (pieces[i][j].isFire() == false && pieces[i][j].isBomb() == false
                                && pieces[i][j].isShield() == false && pieces[i][j].isKing() == false) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }                            
                            if (pieces[i][j].isFire() == false && pieces[i][j].isBomb()
                                && pieces[i][j].isKing() == false) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                            if (pieces[i][j].isFire() == false && pieces[i][j].isShield()
                                && pieces[i][j].isKing() == false) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }                             
                            // handling isKing for all pieces in GUI
                            if (pieces[i][j].isFire() && pieces[i][j].isBomb() == false
                                && pieces[i][j].isShield() == false && pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }                            
                            if (pieces[i][j].isFire() && pieces[i][j].isBomb()
                                && pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            if (pieces[i][j].isFire() && pieces[i][j].isShield()
                                && pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }        
                            if (pieces[i][j].isFire() == false && pieces[i][j].isBomb() == false
                                && pieces[i][j].isShield() == false && pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }                            
                            if (pieces[i][j].isFire() == false && pieces[i][j].isBomb()
                                && pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            if (pieces[i][j].isFire() == false && pieces[i][j].isShield()
                                && pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                        }   
            }
        }

				}

    public Piece pieceAt(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0) {
            return null;
        }
        else if (pieces[x][y] == null) {
            return null;
        }
        else {
            return pieces[x][y];
        }
    }

    // Select functionality.
    public boolean canSelect(int x, int y) {
        // this allows clicking on white space
        if (x > 7 || y > 7) {
            return false;
        }
        if (pieces[x][y] != null) {
            if (turn == pieceAt(x, y).side()) {
                if (hasMoved == false) {
                    return true;
                }
            }
        }
        else {
            if (selectedPiece != null) {
                if (hasMoved == false || selectedPiece.hasCaptured()) {
                    if (validMove(selectedPieceX, selectedPieceY, x, y)) {
                        return true;    
                    }
                }
            }
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        // king suite
        if (pieceAt(xi, yi).isKing()) {
            // king moves
            if (xi - xf == 1 || xi - xf == -1) {    
                    if (yi - yf == -1 || yi - yf == 1) {
                        if (hasMoved == false) {
                            return true;
                    }
                }
            }
            // king captures
            if (xi - xf == 2) {
                if (pieceAt(xi, yi).side() == 0) {
                    if (yi - yf == -2) {
                        if (pieceAt(xi - 1, yi + 1) != null
                            && pieceAt(xi - 1, yi + 1).side() == 1) {
                            return true;
                        }
                    }
                }
                if (pieceAt(xi, yi).side() == 0) {
                    if (yi - yf == 2) {
                        if (pieceAt(xi - 1, yi - 1) != null
                            && pieceAt(xi - 1, yi - 1).side() == 1) {
                            return true;
                        }
                    }
                }                
                if (pieceAt(xi, yi).side() == 1) {            
                    if (yi - yf == 2) {
                        if (pieceAt(xi - 1, yi - 1) != null
                            && pieceAt(xi - 1, yi - 1).side() == 0) {
                            return true;
                        }
                    }
                }
                if (pieceAt(xi, yi).side() == 1) {            
                    if (yi - yf == -2) {
                        if (pieceAt(xi - 1, yi + 1) != null
                            && pieceAt(xi - 1, yi + 1).side() == 0) {
                            return true;
                        }
                    }
                }                
            }
            if (xi - xf == -2) {
                if (pieceAt(xi, yi).side() == 0) {
                    if (yi - yf == -2) {
                        if (pieceAt(xi + 1, yi + 1) != null
                            && pieceAt(xi + 1, yi + 1).side() == 1) {
                            return true;
                        }
                    }
                }
                if (pieceAt(xi, yi).side() == 0) {
                    if (yi - yf == 2) {
                        if (pieceAt(xi + 1, yi - 1) != null
                            && pieceAt(xi + 1, yi - 1).side() == 1) {
                            return true;
                        }
                    }
                }                
                if (pieceAt(xi, yi).side() == 1) {            
                    if (yi - yf == 2) {
                        if (pieceAt(xi + 1, yi - 1) != null
                            && pieceAt(xi + 1, yi - 1).side() == 0) {
                            return true;
                        }
                    }
                }
                if (pieceAt(xi, yi).side() == 1) {            
                    if (yi - yf == -2) {
                        if (pieceAt(xi + 1, yi + 1) != null
                            && pieceAt(xi + 1, yi + 1).side() == 0) {
                            return true;
                        }
                    }
                }                
            }                 
        }
        // Normal suite
        else {
            // Normal moves
            if (xi - xf == 1 || xi - xf == -1) {    
                if (pieceAt(xi, yi).side() == 0) {
                    if (yi - yf == -1) {
                        if (hasMoved == false) {
                            return true;
                        }
                    }
                }
                if (pieceAt(xi, yi).side() == 1) {
                    if (yi - yf == 1) {
                        if (hasMoved == false) {
                            return true;    
                        }
                    }
                }
            }
            // Capturing moves
            if (xi - xf == 2) {
                if (pieceAt(xi, yi).side() == 0) {
                    if (yi - yf == -2) {
                        if (pieceAt(xi - 1, yi + 1) != null
                            && pieceAt(xi - 1, yi + 1).side() == 1) {
                            return true;
                        }
                    }
                }
                if (pieceAt(xi, yi).side() == 1) {            
                    if (yi - yf == 2) {
                        if (pieceAt(xi - 1, yi - 1) != null
                            && pieceAt(xi - 1, yi - 1).side() == 0) {
                            return true;
                        }
                    }
                }
            }
            if (xi - xf == -2) {
                if (pieceAt(xi, yi).side() == 0) {
                    if (yi - yf == -2) {
                        if (pieceAt(xi + 1, yi + 1) != null
                            && pieceAt(xi + 1, yi + 1).side() == 1) {
                            return true;
                        }
                    }
                }
                if (pieceAt(xi, yi).side() == 1) {            
                    if (yi - yf == 2) {
                        if (pieceAt(xi + 1, yi - 1) != null
                            && pieceAt(xi + 1, yi - 1).side() == 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void select(int x, int y) {
        if (pieces[x][y] == null) {
            oldX = selectedPieceX;
            oldY = selectedPieceY;
            place(selectedPiece, x, y);
            selectedPiece.move(x, y);
            selectedPieceX = x;
            selectedPieceY = y;
            remove(oldX, oldY);
            // handle bombs and shields case
            if (selectedPiece.isBomb() && selectedPiece.hasCaptured()) {
                selectedPiece = null;
            }
                hasMoved = true;
        }
        else {
            selectedPiece = pieces[x][y];
            selectedPieceX = x;
            selectedPieceY = y;
        }
        return;
    }

    // Colors the tile at (x, y) white. 
    private void highlightSquare(int x, int y) {
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        if (pieceAt(x, y) != null) {
            if (pieces[x][y].isFire() && pieces[x][y].isBomb() == false
                && pieces[x][y].isShield() == false && pieces[x][y].isKing() == false) {
                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
            }                            
            if (pieces[x][y].isFire() && pieces[x][y].isBomb()
                && pieces[x][y].isKing() == false) {
                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
            }
            if (pieces[x][y].isFire() && pieces[x][y].isShield()
                && pieces[x][y].isKing() == false) {
                StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
            }        
            if (pieces[x][y].isFire() == false && pieces[x][y].isBomb() == false
                && pieces[x][y].isShield() == false && pieces[x][y].isKing() == false) {
                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
            }                            
            if (pieces[x][y].isFire() == false && pieces[x][y].isBomb()
                && pieces[x][y].isKing() == false) {
                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
            }
            if (pieces[x][y].isFire() == false && pieces[x][y].isShield()
                && pieces[x][y].isKing() == false) {
                StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
            }
            // handle king image cases
            if (pieces[x][y].isFire() && pieces[x][y].isBomb() == false
                && pieces[x][y].isShield() == false && pieces[x][y].isKing()) {
                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
            }                            
            if (pieces[x][y].isFire() && pieces[x][y].isBomb()
                && pieces[x][y].isKing()) {
                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
            }
            if (pieces[x][y].isFire() && pieces[x][y].isShield()
                && pieces[x][y].isKing()) {
                StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
            }        
            if (pieces[x][y].isFire() == false && pieces[x][y].isBomb() == false
                && pieces[x][y].isShield() == false && pieces[x][y].isKing()) {
                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
            }                            
            if (pieces[x][y].isFire() == false && pieces[x][y].isBomb()
                && pieces[x][y].isKing()) {
                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
            }
            if (pieces[x][y].isFire() == false && pieces[x][y].isShield()
                && pieces[x][y].isKing()) {
                StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
            }            
        }
    }

    public void place(Piece p, int x, int y) {
        if (p == null) {
            return;
        }
        else if (x > 7 || x < 0 || y > 7 || y < 0) {
            return;
        }
        if (p.side() == 0) {
            fireSide = fireSide + 1;
        }
        if (p.side() == 1) {
            waterSide = waterSide + 1;
        }
        pieces[x][y] = p;    
    }

    public Piece remove(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0) {
            System.out.println("Cannot remove from a position not on the game board.");
            return null;        
        }
        else if (pieces[x][y] == null) {
            System.out.println("No piece to remove at " + "(" + x + ", " + y + ").");
            return null;              
        }
        Piece removed = pieces[x][y];
        if (removed.side() == 0) {
            fireSide = fireSide - 1;
        }
        if (removed.side() == 1) {
            waterSide = waterSide - 1;
        }        
        pieces[x][y] = null;
        return removed;
    }

    // Switching turn methods.
    public boolean canEndTurn() {
        if (hasMoved == true) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        if (turn == 1) {
            turn = 0;
        }
        else {
            turn = 1;
        }
        if (selectedPiece != null) {
            selectedPiece.doneCapturing();
        }
        selectedPiece = null;
        hasMoved = false;
    }

    public String winner() {
        if (fireSide == 0 && waterSide == 0) {
            return "No one";
        }
        if (fireSide == 0 && waterSide != 0) {
            return "Water";
        }
        if (fireSide != 0 && waterSide == 0) {
            return "Fire";
        }
        return null;
    }

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board board = new Board(false);

        while (true) {
        	board.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int) x, (int) y) == true) {
                    board.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                }
            }
        	StdDrawPlus.show(15);
        }

    }
}