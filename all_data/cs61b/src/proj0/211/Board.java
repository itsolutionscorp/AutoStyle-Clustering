/** Board.
 *  @author Mitchell Huang
 */

public class Board {

    private int n = 8;
    private Piece[][] pieces = new Piece[n][n];
    private int player = 0;
    private Piece selectedPiece = null;
    private boolean hasMoved = false;
    private int selectedX;
    private int selectedY;

    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty == false) {
            defaultBoard();
        }

        // StdDrawPlus.setXscale(0, n);
        // StdDrawPlus.setYscale(0, n);
        // if (!shouldBeEmpty) {
        //     defaultBoard();
        // }
    }

    private void drawBoard() {
        StdDrawPlus.setXscale(0, n);
        StdDrawPlus.setYscale(0, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece p = pieceAt(i, j);
                if (p != null) {
                    if (p.isFire()) {
                        if (p.isBomb()) {
                            if (p.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                        } else if (pieceAt(i, j).isShield()) {
                            if (p.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }                            
                        } else {
                            if (p.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }                              
                        }
                    } else {
                        if (p.isBomb()) {
                            if (p.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                        } else if (p.isShield()) {
                            if (p.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }                            
                        } else {
                            if (p.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }                              
                        }
                    }
                }
            }
        }
        if (selectedPiece != null) {
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.square(selectedX + .5, selectedY + .5, .5);
        }
    }

    private void defaultBoard() {
        for (int x = 0; x < n; x +=2) {
            place(new Piece(true, this, x, 0, "pawn"), x, 0);
        }
        for (int x = 1; x < n; x +=2) {
            place(new Piece(true, this, x, 1, "shield"), x, 1);
        }
        for (int x = 0; x < n; x +=2) {
            place(new Piece(true, this, x, 2, "bomb"), x, 2);
        }
        for (int x = 1; x < n; x +=2) {
            place(new Piece(false, this, x, n-3, "bomb"), x, n-3);
        }
        for (int x = 0; x < n; x +=2) {
            place(new Piece(false, this, x, n-2, "shield"), x, n-2);
        }
        for (int x = 1; x < n; x +=2) {
            place(new Piece(false, this, x, n-1, "pawn"), x, n-1);
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x >= 0 && y >= 0 && x < n && y < n) {
            if (pieces[x][y] != null) {
                return pieces[x][y];
            }
        }
        return null;
    }

    public boolean canSelect(int x, int y) {

        if (x < 0 || y < 0 || x > n || y > n) {
            return false;
        }
        Piece p = pieceAt(x, y);
        
        if (p == null) {
            if (selectedPiece != null) {
                int inBetweenX = (selectedX + x) / 2;
                int inBetweenY = (selectedY + y) / 2;
                Piece b = pieceAt(inBetweenX, inBetweenY);
                if (selectedPiece.isKing()) {
                    //System.out.println("in3");
                    if (Math.abs(selectedX - x) == 1 && Math.abs(selectedY - y) == 1 && !selectedPiece.hasCaptured() && !hasMoved) {
                        return true;
                        }
                    if (Math.abs(selectedX - x) == 2 && Math.abs(selectedY - y) == 2) {
                        if (b != null) {
                            if (player == 0 && !b.isFire() || player == 1 && b.isFire()) {
                                return true;
                                }
                            }
                        }
                    }
                 else {
                    if (Math.abs(selectedX - x) == 1 && Math.abs(selectedY - y) == 1){
                        if (!selectedPiece.hasCaptured() && !hasMoved) {
                            if (player == 0 && y == (selectedY + 1)) {
                                    return true;
                                }
                            if (player == 1 && y == (selectedY - 1)) {
                                    return true;
                                }
                            }
                        }
                    if (Math.abs(selectedX - x) == 2 && Math.abs(selectedY - y) == 2) {
                        if (hasMoved && !selectedPiece.hasCaptured()) {
                            return false;
                            }
                        if (b != null) {
                            if ((player == 0 && y == selectedY + 2 && !b.isFire()) || (player == 1 && y == selectedY - 2 && b.isFire())) {
                                return true;                        
                                }
                            }
                        }
                    }
                }
            }
            if (p != null) {
                    if (!hasMoved) {
                        if ((player == 0 && p.isFire()) || (player == 1 && !p.isFire())) {
                            return true;
                        }
                    }
                }
        // default returns is false
        return false;
    }

    public void select(int x, int y) {
        Piece p = pieceAt(x, y);
        if (p != null) {
            selectedPiece = pieceAt(x, y);
            selectedX = x;
            selectedY = y;
        } else {
            selectedPiece.move(x, y);
            this.hasMoved = true;
            if (pieceAt(x, y) != null) {
                selectedPiece = pieceAt(x, y);
                selectedX = x;
                selectedY = y;
            } else {
                selectedPiece = null;
                selectedX = 0;
                selectedY = 0;
            }
        }
    }

    public void place(Piece p, int x, int y) {
        if (x >= 0 && y >= 0 && x < n && y < n) {
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if (x >= 0 && y >= 0 && x < n && y < n) {
            if (pieceAt(x, y) != null) {
                Piece p = pieceAt(x, y);
                place(null, x, y);
                return p;
            }
            System.out.println("Error: No such piece exists.");
            return null;
        }
        System.out.println("Error: Index out of bounds.");
        return null;
    }

    public boolean canEndTurn() {
        return this.hasMoved;
    }

    public void endTurn() {
        if (player == 0) {
            player = 1;
        } else if (player == 1) {
            player = 0;
        }
        this.hasMoved = false;
        if (this.selectedPiece != null) {
            this.selectedPiece.doneCapturing();
            this.selectedPiece = null;
        }
    }

    public String winner() {
        boolean f = false;
        boolean w = false;
        for (int y=0; y<this.n; y++) {
            for (int x=0; x<this.n; x++) {
                Piece p = this.pieceAt(x, y);
                if (p != null) {
                    if (p.isFire()) {
                        f = true;
                    } else {
                        w = true;
                    }
                }
            }
        }
        if (f == true && w == true) {
            return null;
        }
        if (w == true) {
            return "Water";
        }
        if (f == true) {
            return "Fire";
        }
        return "No one";
    }

    public static void main(String[] args) {
        Board b = new Board(false);
        // Piece fireShield = new Piece(true, b, 1, 6, "shield");
        // b.place(fireShield, 1, 6);
        // Piece fireShield = new Piece(true, b, 1, 5, "shield");
        // b.place(fireShield, 1, 5);

        // Piece waterBomb = new Piece(false, b, 2, 6, "bomb");
        // b.place(waterBomb, 2, 6);

        // Piece waterShield = new Piece(false, b, 4, 6, "shield");
        // b.place(waterShield, 4, 6);

        while (true) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y) == true) {
                    //System.out.println("true");
                    b.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            // check if the board can currently end turn.
                if (b.canEndTurn() == true) {
                    b.endTurn();
                }
            }
            StdDrawPlus.show(15);
        }
    }
}