import java.util.ArrayList;

public class Board {

	// Simple wrapper class for keeping track of multicapture points
	private class CoordinatePoint {
		public int x;
		public int y;

		CoordinatePoint(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private int N = 8;
	private int turn = 0;
	private int selectedX = -1;
	private int selectedY = -1;
	private ArrayList<CoordinatePoint> multiCapturePositions = new ArrayList<CoordinatePoint>();
	private Piece[][] pieces = new Piece[N][N];
	private Piece selectedPiece = null;
	private boolean hasGone = false;
	private boolean isMultiCapture = false;
	private boolean hasSelected = false;

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			for (int i = 0; i < N / 2; i++) {
				pieces[2 * i][0] = new Piece(true, this, 2 * i, 0, "pawn");
				pieces[2 * i + 1][1] = new Piece(true, this, 2 * i + 1, 1, "shield");
				pieces[2 * i][2] = new Piece(true, this, 2 * i, 2, "bomb");
				pieces[2 * i + 1][N - 1] = new Piece(false, this, 2 * i + 1, N - 1, "pawn");
				pieces[2 * i][N - 2] = new Piece(false, this, 2 * i, N - 2, "shield");
				pieces[2 * i + 1][N - 3] = new Piece(false, this, 2 * i + 1, N - 3, "bomb");
			}	
		}
		drawBoard();
	}

   private void drawBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	Piece tmp = pieceAt(i, j);
            	if (selectedPiece != null && selectedPiece == tmp) 
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) 
               		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (tmp != null) {
                	StdDrawPlus.picture(i + .5, j + .5, pieceToImg(tmp), 1, 1);
            	}
            }
        }
    }

    private String pieceToImg(Piece p) {
   		String str = "img/";
   		if (p.isBomb()) {
   			str += "bomb";
   		} else if (p.isShield()) {
   			str += "shield";
   		} else {
   			str += "pawn";
   		}
   		if (p.isFire()) {
   			str += "-fire";
   		} else {
   			str += "-water";
   		}
   		 if (p.isKing()) {
   			str += "-crowned";
   		}
   		return str + ".png";
    }

    public Piece pieceAt(int x, int y) {
    	if (x < N && y < N && x >= 0 && y >= 0) {
    		return pieces[x][y];
    	}
    	return null;
    }

    public void place(Piece p, int x, int y) {
    	if (x < N && y < N)
    		pieces[x][y] = p;
    }

    public boolean canSelect(int x, int y) {
    	Piece p = pieceAt(x, y);
    	if (isMultiCapture) {
    		for (CoordinatePoint cp : multiCapturePositions) {
    			if (cp.x == x && cp.y == y)
    				return true;
    		}
    	}
    	if (hasGone) {
    		return false;
    	}
    	if (hasSelected && p == null) {
    		return validMove(selectedX, selectedY, x, y);
    	}
    	if (p == null || p.side() != turn)
    		return false;
    	return true;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
    	Piece p1 = pieceAt(xi, yi);
    	if (xf > N || xf < 0 || yf > N || yf < 0 || p1 == null)
    		return false;
    	Piece p2 = pieceAt(xf, yf);
    	if (p2 == null) {
    		int xDiff = xf - xi;
    		int yDiff = yf - yi;
    		int side = p1.side();

    		if (p1.isKing()) {
    			if (Math.abs(xDiff) == 1 && Math.abs(yDiff) == 1)
    				return true;
    			if (Math.abs(xDiff) == 2 && Math.abs(yDiff) == 2) {
    				Piece p3 = pieceAt((xf + xi) / 2, (yf + yi) / 2);
    				return p3 != null && p3.side() != p1.side();
    			}
    		} else {
    			if (side == 0) {
    				if (Math.abs(xDiff) == 1 && yDiff == 1) {
    					return true;
    				}
    				if (Math.abs(xDiff) == 2 && yDiff == 2) {
    					Piece p3 = pieceAt((xf + xi) / 2, (yf + yi) / 2);
    					return p3 != null && p3.side() != p1.side();
    				}
    			} else {
    				if (Math.abs(xDiff) == 1 && yDiff == -1) {
    					return true;
    				}
    				if (Math.abs(xDiff) == 2 && yDiff == -2) {
    					Piece p3 = pieceAt((xf + xi) / 2, (yf + yi) / 2);
    					return p3 != null && p3.side() != p1.side();
    				}
    			}
    		}
    	}
    	return false;
    }

    public Piece remove(int x, int y) {
    	if (x > N || y > N) {
    		System.out.println("This piece does not have an appropriate coordinate.");
    		return null;
    	}
    	Piece p = pieces[x][y];
    	pieces[x][y] = null;
    	return p;
    }

    private void multiCaptureHelper(int x, int y) {
    	if (validMove(selectedX, selectedY, selectedX + x, selectedY + y))
    		multiCapturePositions.add(new CoordinatePoint(selectedX + x, selectedY + y));	
    }

    public void select(int x, int y) {
    	Piece p = pieceAt(x,y);
    	if (selectedPiece != null && p != null && p.side() == selectedPiece.side()) {
    		selectedPiece = p;
    		selectedX = x;
    		selectedY = y;
    	}
    	else if (selectedPiece != null) {
    		selectedPiece.move(x, y);
    		if (selectedPiece.hasCaptured()) {
    			selectedX = x;
    			selectedY = y;
    			isMultiCapture = validMove(selectedX, selectedY, selectedX + 2, selectedY + 2) || validMove(selectedX, selectedY, selectedX + 2, selectedY - 2) || validMove(selectedX, selectedY, selectedX - 2, selectedY + 2) || validMove(selectedX, selectedY, selectedX - 2, selectedY - 2);
    			if (isMultiCapture) {
    				multiCaptureHelper(2, 2);
    				multiCaptureHelper(2, -2);
    				multiCaptureHelper(-2, 2);
    				multiCaptureHelper(-2, -2);
    			}
    		}
    		hasGone = true;
    	}
    	else if (p != null) {
    		hasSelected = true;
    		selectedPiece = p;
    		selectedX = x;
    		selectedY = y;
    	}
    }

    public boolean canEndTurn() {
    	return hasGone;
    }

    public void endTurn() {
    	turn = 1 - turn;
    	selectedPiece.doneCapturing();
    	selectedPiece = null;
    	hasGone = false;
    	hasSelected = false;
    	selectedX = -1;
    	selectedY = -1;
    	isMultiCapture = false;
    	multiCapturePositions.clear();
    }

    public String winner() {
    	int numFire = 0;
    	int numWater = 0;
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			Piece p = pieceAt(i, j);
    			if (p == null) {
    				continue;
    			}
    			else {
    				if (p.isFire())
    					numFire += 1;
    				else
    					numWater += 1;
    			}
    		}
    	}
    	if (numWater > 0 && numFire > 0) {
    		return null;
    	}
    	if (numWater == 0 && numFire == 0) {
    		return "No one";
    	} else if (numWater > 0 && numFire == 0) {
    		return "Water";
    	}
    	return "Fire";
    }

	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);
		boolean switcher = false;
		while (true) {
			b.drawBoard();
			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (b.canSelect(x, y))
					b.select(x,y);
			} else if (StdDrawPlus.isSpacePressed() && b.canEndTurn() && !switcher) {
				b.endTurn();
				switcher = true;
				if (b.winner() != null) {
					b.drawBoard();
					break;
				}
			} else {
				switcher = false;
			}
			StdDrawPlus.show(1);
		}
	}
}