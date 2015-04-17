import static java.lang.Math.*;

public class Board {

    private static final int N = 8;
    private Piece[][] pieces;
    private int player = 0;
    private Piece selectedPiece;
    private int selectedPieceX;
    private int selectedPieceY;
    private boolean hasSelected = false;
    private boolean hasMoved = false;

    private void drawBoard(int N) {
        for (int y = 0; y < N; y += 1) {
            for (int x = 0; x < N; x += 1) {
                if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            }
        }
        if (pieces != null) {
            for (int y = 0; y < N; y += 1) {
                for (int x = 0; x < N; x += 1) {
                    if (pieces[x][y] != null) drawPieces(x, y, pieces[x][y]);
                }
            }  
        }
        if (hasSelected) {
            if (selectedPiece != null) {
                for (int y = 0; y < N; y += 1) {
                    for (int x = 0; x < N; x += 1) {
                        if (pieces[x][y] == selectedPiece) {
                            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                            drawPieces(x, y, selectedPiece);  
                        }
                    }
                } 
            }
        }
    }

    private void drawPieces(int x, int y, Piece p) {
    	StdDrawPlus.picture(x + .5, y + .5, img(p), 1, 1);
    }

    private String img(Piece p) {
        String type;
        String fire;
        String img;
        if (p.isBomb()) type = "bomb";
        else if (p.isShield()) type = "shield";
        else type = "pawn";
        if (p.isFire()) fire = "fire";
        else fire = "water";
        if (p.isKing()) img = "img/" + type + "-" + fire + "-crowned.png";
        else img = "img/" + type + "-" + fire + ".png";
        return img;
    }

	public static void main(String[] args){
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        while (true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) b.select(x, y);
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) b.endTurn();
            }
            StdDrawPlus.show(100);
        }
    }

	public Board(boolean shouldBeEmpty) {
        pieces = new Piece[N][N];
		if (!shouldBeEmpty) {
            for (int y = 0; y < N; y += 1) {
            	for (int x = 0; x < N; x += 1) {
            		if ((y == 0) && (x % 2 == 0)) pieces[x][y] = new Piece(true, this, x, y, "pawn");
					if ((y == 1) && (x % 2 != 0)) pieces[x][y] = new Piece(true, this, x, y, "shield");
					if ((y == 2) && (x % 2 == 0)) pieces[x][y] = new Piece(true, this, x, y, "bomb");
					if ((y == 5) && (x % 2 != 0)) pieces[x][y] = new Piece(false, this, x, y, "bomb");
					if ((y == 6) && (x % 2 == 0)) pieces[x][y] = new Piece(false, this, x, y, "shield");
					if ((y == 7) && (x % 2 != 0)) pieces[x][y] = new Piece(false, this, x, y, "pawn");
				}
			}
		}
	}

    public Piece pieceAt(int x, int y) {
        if (!outOfBounds(x, y)) return pieces[x][y];
        else return null;
    }

    private boolean outOfBounds(int x, int y) {
        if ((x < 0) || (x >= N) || (y < 0) || (y >= N)) return true;
        return false;
    }

    public boolean canSelect(int x, int y) {
        if (!outOfBounds(x, y)) { 
            if (pieceAt(x, y) == null) {
                if (hasSelected == true) return validMove(selectedPieceX, selectedPieceY, x, y);
            }
            else if (pieceAt(x, y) != null) {
                if (playerSide(x, y)) {
                    if (hasSelected == false) return true;
                    else if ((hasSelected == true) && (hasMoved == false)) return true;
                }
            }
        }
        return false;
    }

    private boolean playerSide(int x, int y) {
        if (player == 0) return pieceAt(x, y).isFire();
        else if (player == 1) return !(pieceAt(x, y).isFire());    
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        boolean kingOneStep = (abs(xf - xi) == 1) && (abs(yf - yi) == 1);
        boolean kingTwoSteps = (abs(xf - xi) == 2) && (abs(yf - yi) == 2);
        boolean fireOneStep = (abs(xf - xi) == 1) && ((yf - yi) == 1);
        boolean fireTwoSteps = (abs(xf - xi) == 2) && ((yf - yi) == 2);
        boolean waterOneStep = (abs(xi - xf) == 1) && ((yi - yf) == 1);
        boolean waterTwoSteps = (abs(xi - xf) == 2) && ((yi - yf) == 2);
        boolean capture = canCapture(xi, yi, xf, yf);

        if (!hasMoved) {
            if (selectedPiece.isKing()) return (kingOneStep || (kingTwoSteps && capture));
            else if (selectedPiece.isFire()) return (fireOneStep || (fireTwoSteps && capture));
            else return (waterOneStep || (waterTwoSteps && capture));
        }
        if (hasMoved && selectedPiece.hasCaptured()) {
            if (selectedPiece.isKing()) return (kingTwoSteps && capture);
            else if (selectedPiece.isFire()) return (fireTwoSteps && capture);
            else return (waterTwoSteps && capture);
        }   
        return false;
    }

    private boolean canCapture(int xi, int yi, int xf, int yf) {
        int xx = (xi + xf) / 2;
        int yy = (yi + yf) / 2;
        Piece p = pieceAt(xx, yy);
        if (p == null) return false;
        else return (!playerSide(xx, yy));
    }

    public void select(int x, int y) {
        hasSelected = true;
        if (pieceAt(x, y) == null) {
            hasMoved = true;
            selectedPiece.doneCapturing();
            selectedPiece.move(x, y);
            selectedPieceX = x;
            selectedPieceY = y;
        }
        else if (pieceAt(x, y) != null) {
            selectedPiece = pieceAt(x, y);
            selectedPieceX = x;
            selectedPieceY = y;
        }
    }

    public void place(Piece p, int x, int y) {
        if ((!outOfBounds(x, y)) && (p != null)) {
            if (pieceAt(x, y) != null) {
                remove(x, y);
            }
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if (outOfBounds(x, y)) {
            System.out.println("input out of bounds");
            return null;
        }
        if (pieceAt(x, y) == null) {
            System.out.println("no piece at (" + x + ", " + y + ")");
            return null;
        }
        else{
            Piece rm = pieceAt(x, y);
            pieces[x][y] = null;
            return rm;
        }
    }

    public boolean canEndTurn() {
        return hasMoved;
    }

    public void endTurn() {
        player = 1 - player;
        selectedPiece.doneCapturing();
        selectedPiece = null;
        hasSelected = false;
        hasMoved = false;
    }

    public String winner() {
        int f = 0;
        int w = 0;
        for (int y = 0; y < N; y += 1) {
            for (int x = 0; x < N; x += 1) {
                if (pieces[x][y] != null) {
                    if (pieces[x][y].isFire()) f += 1;
                    else if (!(pieces[x][y].isFire())) w += 1;
                }
            }
        }
        if ((f == 0) && (w == 0)) return "No one";
        else if ((f != 0) && (w == 0)) return "Fire";
        else if ((f == 0) && (w != 0)) return "Water";
        return null;
    }
}
