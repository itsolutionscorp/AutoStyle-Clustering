/**
* @author Adam Krueger
*/

public class Board {
	private Piece[][] positionArray = new Piece[8][8];
    private boolean isFireTurn = true;
    private Piece selectedPiece = null;

    private void drawBoard() {
        int N = 8;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {                 
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (positionArray[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, this.imageString(positionArray[i][j]), 1, 1);
                }  
            }
        }
    }

    private boolean noMovesAvailable() {
        int newX = selectedX;
        int newY = selectedY;
        for (int q = newX - 2; q < (newX + 3); q++) {
            for (int w = newY - 2; w < (newY + 3); w++) {
                if (this.validMove(newX, newY, q, w)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }

	public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        b.drawBoard();
        while (true) {
            if (StdDrawPlus.mousePressed()) {
                double r = StdDrawPlus.mouseX();
                double e = StdDrawPlus.mouseY();
                int x = (int)r;
                int y = (int)e;
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (b.canSelect(x, y)) {
                        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                        b.select(x, y);
                        b.drawBoard();
                    }
                }
            if (StdDrawPlus.isSpacePressed()) {
                if(b.canEndTurn()) {
                    b.endTurn();
                }
            }
            if (StdDrawPlus.isNPressed()) {
                b = new Board(false);
            }
            if (b.winner() != null) {
                System.out.println(b.winner());
                return;
            }
        }
    }

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
			//drawBoard();
		} else {
            int N = 8;
			for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (y == 0 && x % 2 == 0) {positionArray[x][y] = new Piece(true, this, y, x, "pawn");}
                    if (y == 1 && x % 2 != 0) {positionArray[x][y] = new Piece(true, this, y, x, "shield");}
                    if (y == 2 && x % 2 == 0) {positionArray[x][y] = new Piece(true, this, y, x, "bomb");}
                    if (y == 5 && x % 2 != 0) {positionArray[x][y] = new Piece(false, this, y, x, "bomb");}
                    if (y == 6 && x % 2 == 0) {positionArray[x][y] = new Piece(false, this, y, x, "shield");}
                    if (y == 7 && x % 2 != 0) {positionArray[x][y] = new Piece(false, this, y, x, "pawn");
                    }
                }
            }
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		if (positionArray[x][y] != null) {
			//places piece on the board
			return positionArray[x][y];
		} else {
            return null;
		}
	}

    public void place(Piece p, int x, int y) {
        if (p != null && x < 8 && y < 8 && x >= 0 && y >= 0 && (x + y) % 2 == 0) {
            positionArray[x][y] = p;
            boolean f = p.isFire();
            if (p.isBomb()) {
                p = new Piece(f, this, x, y, "bomb");
            } else if (p.isShield()) {
                p = new Piece(f, this, x, y, "shield");
            } else {
                p = new Piece(f, this, x, y, "pawn");
            }
            this.hasMoved = true;
        }
    }

    public Piece remove(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            System.out.println("Error: out of bounds");
            return null;
        }
        if (positionArray[y][x] == null) {
            System.out.println("Error: no piece to remove");
            return null;
        } else {
            Piece temp = positionArray[y][x];
            positionArray[y][x] = null;
            return temp;
        }
    }

    private void switchTurn() {
        if (isFireTurn) {
            isFireTurn = false;
        } else {
            isFireTurn = true;
        }
    }

    private boolean hasCaptured = false;

    public boolean canSelect(int x, int y) {
        Piece temp = this.pieceAt(x, y);
        Piece cache = this.selectedPiece;

        // Out of bounds check
        if (x > 7 || y > 7 || x < 0 || y < 0 || (y + x) % 2 != 0) {
            return false;
        }

        // if Piece hasn't been selected yet
        else if (cache == null) {
            if (temp != null && temp.isFire() == this.isFireTurn) {
                return true;
            } else {
                return false;
            }
        } 

        // Selected Piece is regular piece moving one diaganol spot
        else if (this.hasMoved == false && this.hasCaptured == false && cache != null && temp == null && Math.abs(x - selectedX) == 1 && y - selectedY == 1) {
            if (cache.isFire() == this.isFireTurn) {
                return true;
            }
        }

        // Selected Piece is King piece moving one diaganol spot
        else if (this.hasMoved == false && this.hasCaptured == false && cache.isKing() == true && cache != null && temp == null && Math.abs(x - selectedX) == 1 && Math.abs(y - selectedY) == 1) {
            if (cache.isFire() == this.isFireTurn) {
                return true;
            }
        }

        // Selected Piece is normal piece moving 2 diaganol spots
        else if (cache != null && temp == null && Math.abs(x - selectedX) == 2 && y - selectedY == 2) {
            int intermediaryX = (x - selectedX)/2 + selectedX;
            int intermediaryY = (y - selectedY)/2 + selectedY;
            Piece temp2 = this.pieceAt(intermediaryX, intermediaryY);
            if (temp2 == null) {
                return false;
            }
            if (temp2.isFire() != this.isFireTurn) {
                return true;
            } else { return false; }
        }

        //Selected Piece is king piece moving 2 diaganol spots
        else if (cache != null && temp == null && Math.abs(x - selectedX) == 2 && Math.abs(y - selectedY) == 2) {
            int intermediaryX = (x - selectedX)/2 + selectedX;
            int intermediaryY = (y - selectedY)/2 + selectedY;
            Piece temp2 = this.pieceAt(intermediaryX, intermediaryY);
            if (temp2 == null) {
                return false;
            }
            if (temp2.isFire() != this.isFireTurn) {
                return true;
            } else { return false; }
        }
        else {
            return false;
        }

    return false;
    }

    private boolean canMove = true;

    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece p = this.pieceAt(xi, yi);
        Piece p2 = this.pieceAt(xf, yf);
        int xChange = xf - xi;
        int yChange = yf - yi;
        int absxChange = Math.abs(xf - xi);
        int absyChange = Math.abs(yf - yi);
        if (xf > 7 || yf > 7 || xf < 0 || yf < 0) {
            return false;
        } else if (absxChange != absyChange) {
            return false;
        } else if (p == null) {
            return false;
        } else if (this.canMove == false) {
            return false;
        } else if ((xf + yf)%2 != 0) {
            return false;
        } else if (absxChange > 2 || absxChange < 1 || p2 != null) {
            return false;
        } else if (p.isKing() == false && yChange < 0) {
            return false;
        } else if (absxChange == 2 && this.pieceAt((xi + xChange), (yi + yChange)).isFire() == this.isFireTurn) {
            return false;
        } else {
            return true;
        }
    }

    private boolean hasMoved = false;
    private int selectedX = 8;
    private int selectedY = 8;

    public void select(int x, int y) {
        if (positionArray[x][y] == null) {
            this.selectedPiece.move(x, y);
            this.hasMoved = true;
            this.hasCaptured = true;
            selectedX = x;
            selectedY = y;
            if (this.selectedPiece.hasCaptured() == false) {
                this.canMove = false;
                this.hasCaptured = false;
            }
        } else {
            this.selectedPiece = positionArray[x][y];
            selectedX = x;
            selectedY = y;
        }
    }

    public boolean canEndTurn() {
        if (selectedPiece == null) {
            return false;
        } else if (this.canMove == false) {
            return true;
        } else {
            int newX = selectedX;
            int newY = selectedY;
            for (int q = newX - 2; q < (newX + 3); q++) {
                for (int w = newY - 2; w < (newY + 3); w++) {
                    if (this.validMove(newX, newY, q, w)) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
        return true;
    }
    public void endTurn() {
        this.switchTurn();
        //this.noMovesAvailable();
        this.canMove = true;
        this.selectedPiece = null;
        this.hasCaptured = false;
        this.hasMoved = false;
    }

    public String winner() {
        int N = 8;
        int fireLeft = 0;
        int waterLeft = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (positionArray[i][j] != null) {
                    if (positionArray[i][j].isFire()) {
                        fireLeft += 1;
                    } else {
                        waterLeft += 1;
                    }
                }
            }
        }
        if (fireLeft == 0  && waterLeft == 0) {
            return "No one";
        }
        if (fireLeft > 0 && waterLeft == 0) {
            return "Fire";
        }
        if (fireLeft == 0 && waterLeft > 0) {
            return "Water";
        } else {
            return null;
        }
    }

    private String firePawn = "img/pawn-fire.png";
    private String fireBomb = "img/bomb-fire.png";
    private String fireShield = "img/shield-fire.png";
    private String kingFirePawn = "img/pawn-fire-crowned.png";
    private String kingFireBomb = "img/bomb-fire-crowned.png";
    private String kingFireShield = "img/shield-fire-crowned.png"; 
    private String waterPawn = "img/pawn-water.png";
    private String waterBomb = "img/bomb-water.png";
    private String waterShield = "img/shield-water.png";
    private String kingWaterPawn = "img/pawn-water-crowned.png";
    private String kingWaterBomb = "img/bomb-water-crowned.png";
    private String kingWaterShield = "img/shield-water-crowned.png";   

    private String imageString(Piece p) {
        if (p.isFire()) {
            if (p.isKing()) {
                if (p.isShield() == false && p.isBomb() == false) {
                    return kingFirePawn;
                }
                if (p.isShield()) {
                    return kingFireShield;
                } else {
                    return kingFireBomb;
                }
            } else {
                if (p.isShield() == false && p.isBomb() == false) {
                    return firePawn;
                }
                if (p.isShield()) {
                    return fireShield;
                } else {
                    return fireBomb;
                }
            }
        } else {
            if (p.isKing()) {
                if (p.isShield() == false && p.isBomb() == false) {
                    return kingWaterPawn;
                }
                if (p.isShield()) {
                    return kingWaterShield;
                } else {
                    return kingWaterBomb;
                }
            } else {
                if (p.isShield() == false && p.isBomb() == false) {
                    return waterPawn;
                }
                if (p.isShield()) {
                    return waterShield;
                } else {
                    return waterBomb;
                }
            }
        }
    }
}