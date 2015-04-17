import java.awt.Color; 

public class Board {

	private Piece[][] pieces; 
    private boolean isFireTurn = true; 

    private boolean hasMoved = false; 
    private Piece selectedPiece = null; 
    private int selectedX = -1; 
    private int selectedY = -1; 

	private static final Color CUSTOM_LIGHT = new Color(245, 245, 245); 
	private static final Color CUSTOM_DARK = new Color(230, 230, 230);
    private static final Color CUSTOM_SELECT = new Color(230, 255, 255);
	
    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false); 
        while(true) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y); 
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn(); 
                }
            }
            StdDrawPlus.show(10);
        }
    }

	public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8]; 
        if (!shouldBeEmpty) {
            setInitialPieces(); 
        }
	}

    private void setInitialPieces() {
        for (int x = 0; x < 8; x += 2) {
            pieces[x][0] = new Piece(true, this, x, 0, "pawn"); 
            pieces[x][2] = new Piece(true, this, x, 2, "bomb"); 
            pieces[x][6] = new Piece(false, this, x, 6, "shield"); 
        }

        for (int x = 1; x < 8; x += 2) {
            pieces[x][1] = new Piece(true, this, x, 1, "shield"); 
            pieces[x][5] = new Piece(false, this, x, 5, "bomb");
            pieces[x][7] = new Piece(false, this, x, 7, "pawn");
        }
    }

    private void drawBoard() {
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {

                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(CUSTOM_LIGHT);
                } else {
                	StdDrawPlus.setPenColor(CUSTOM_DARK);
                }

                StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                // Select highlighting 
                if (selectedX == i && selectedY == j) {
                    StdDrawPlus.setPenColor(CUSTOM_SELECT); 
                    StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                }

                // Piece rendering 
                if (pieces[i][j] != null) {

                    String type = "pawn"; 
                    if (pieces[i][j].isBomb()) {
                        type = "bomb"; 
                    } else if (pieces[i][j].isShield()) {
                        type = "shield"; 
                    }

                    String element = "water"; 
                    if (pieces[i][j].isFire()) {
                        element = "fire"; 
                    } 

                    String crowned = ""; 
                    if (pieces[i][j].isKing()) {
                        crowned = "-crowned"; 
                    } 

                    StdDrawPlus.picture(i + 0.5, j + 0.5, 
                        "img/" + type + "-" + element + crowned + ".png", 1, 1);
                }
            }
        }
    }


	public Piece pieceAt(int x, int y) {
        if (x >= 8 || y >= 8 || x < 0 || y < 0) {
            return null; 
        }
		return pieces[x][y]; 
	}

	public boolean canSelect(int x, int y) {
		if (x >= 8 || y >= 8 || x < 0 || y < 0) {
            return false; 
        }

        Piece p = pieces[x][y];

        if (p != null && p.isFire() == isFireTurn) { 
            // Selecting piece to move 
            if (selectedPiece == null || !hasMoved) {
                return true; 
            } 
        } else if (p == null && selectedPiece != null) { 
            // Selecting target location 
            if ((!hasMoved) &&
                (validMove(selectedPiece, selectedX, selectedY, x, y) ||
                 validCapture(selectedPiece, selectedX, selectedY, x, y)) ) {
                    return true; 
            }
            if (selectedPiece.hasCaptured() && 
                validCapture(selectedPiece, selectedX, selectedY, x, y)) {
                    return true; 
            }
        }
        return false; 
	}

    private boolean validMove(Piece p, int xi, int yi, int xf, int yf) {
        /* Assumes canSelect has checked that (xf, yf) is in bounds */ 

        if (pieces[xf][yf] != null) {
            return false;
        }

        // Upward movement 
        if (p.isFire() || p.isKing()) {
            if ((xf == xi + 1 || xf == xi - 1) && yf == yi + 1) {
                return true; 
            }
        }

        // Downward movement 
        if (!p.isFire() || p.isKing()) {
            if ((xf == xi + 1 || xf == xi - 1) && yf == yi - 1) {
                return true; 
            }
        }
        return false; 
    }

    private boolean validCapture(Piece p, int xi, int yi, int xf, int yf) {
        if (pieces[xf][yf] != null) {
            return false;
        }

        // Upward capturing 
        if (p.isFire() || p.isKing()) {
            if ((xf == xi + 2 && yf == yi + 2) &&
                (pieces[xi+1][yi+1] != null) &&
                (pieces[xi+1][yi+1].side() != p.side())) {
                    return true; 
            }
            if ((xf == xi - 2 && yf == yi + 2) &&
                (pieces[xi-1][yi+1] != null) &&
                (pieces[xi-1][yi+1].side() != p.side())) {
                    return true; 
            }
        }

        // Downward capturing 
        if (!p.isFire() || p.isKing()) {
            if ((xf == xi + 2 && yf == yi - 2) &&
                (pieces[xi+1][yi-1] != null) &&
                (pieces[xi+1][yi-1].side() != p.side())) {
                    return true; 
            }
            if ((xf == xi - 2 && yf == yi - 2) &&
                (pieces[xi-1][yi-1] != null) &&
                (pieces[xi-1][yi-1].side() != p.side())) {
                    return true; 
            }
        }
        return false; 
    }

	public void select(int x, int y) {
        if (pieces[x][y] != null) {
            selectedPiece = pieces[x][y]; 
            selectedX = x; 
            selectedY = y; 
        } else {
            selectedPiece.move(x, y); 
            hasMoved = true; 
            if (pieceAt(x, y) == null) { // i.e. bomb exploded 
                selectedX = -1;
                selectedY = -1; 
            } else {
                selectedX = x; 
                selectedY = y;
            }
        }
	}

	public void place(Piece p, int x, int y) {
        if (x < 8 && y < 8 && x >= 0 && y >= 0) {
            pieces[x][y] = p; 
        }
	}

	public Piece remove(int x, int y) {
		if (x >= 8 || y >= 8 || x < 0 || y < 0) {
            System.out.println("Coordinate out of bounds.");
            return null; 
        }
        if (pieces[x][y] == null) {
            System.out.println("No piece to remove at selected coordinate.");
            return null; 
        } 
        Piece removedPiece = pieces[x][y]; 
        pieces[x][y] = null; 
        return removedPiece; 
	}

	public boolean canEndTurn() {
		return hasMoved; 
	}

	public void endTurn() {
        isFireTurn = !isFireTurn; 
        hasMoved = false; 
        selectedPiece.doneCapturing(); 
        selectedPiece = null; 
        selectedX = -1; 
        selectedY = -1; 
	}

	public String winner() {
        int fireCount = 0; 
        int waterCount = 0; 
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if (pieces[i][j] != null && pieces[i][j].isFire()) {
                    fireCount += 1; 
                } else if (pieces[i][j] != null && !pieces[i][j].isFire()) {
                    waterCount += 1; 
                }
            }
        }

        if (waterCount == 0 && fireCount == 0) {
            return "No one"; 
        } else if (waterCount == 0) {
            return "Fire"; 
        } else if (fireCount == 0) {
            return "Water"; 
        } else {
		    return null; 
        }
	} 

}