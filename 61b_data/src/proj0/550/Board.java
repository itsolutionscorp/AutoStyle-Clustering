import static java.lang.Math.*;
import java.util.*;

public class Board {
	private boolean empty;
	private static int N = 8;
    private Piece[][] pieces;
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[N][N];
		empty = shouldBeEmpty;
		String[] type = new String[] {"pawn", "shield", "bomb", "", "", "bomb", "shield", "pawn"};
		if (!empty) {
        	for (int i = 0; i < N; i++) {
        		boolean isFire = true;
            	for (int j = 0; j < N; j++) {
            		if ((i + j) % 2 == 0) {
            			if (j >= 4) isFire = false;
						if (j <= 2 || j >= 5) {
		        			pieces[i][j] = new Piece(isFire, this, i, j, type[j]);
		        		}
		        	}
		        }
		    }
		}
		move = false;
		firesTurn = true;
		selectpiece = null;
	}

	public Piece pieceAt(int x, int y) {
		if (x < N & x >= 0 & y < N & y >= 0) return pieces[x][y];
		else return null;
	}

	private int[] positionPiece(Piece p) {
		int x = 0; int y = 0;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieces[i][j] == p) {
            		x = i;
            		y = j;
            	}
            }
        }
        int [] pos = new int[] {x, y};
        return pos;
	}

	public boolean canSelect(int x, int y) {

		if (!move) {
			if (pieceAt(x,y) != null) {
			if (pieceAt(x, y).isFire() & firesTurn) return true;
			else if (!(pieceAt(x, y).isFire()) & !firesTurn) return true;
			}
			if (selectpiece != null) {
				if (validMove(positionPiece(selectpiece)[0], positionPiece(selectpiece)[1], x, y)) return true;
			}
		}
		//else if (pieceAt(x, y) == selectpiece) return true;

		else if (selectpiece != null & selectpiece.hasCaptured() & pieceAt(x, y) == null) {
				if (validCapture(positionPiece(selectpiece)[0], positionPiece(selectpiece)[1], x, y)) return true;
			}
		return false;
	}

	private boolean validCapture(int xi, int yi, int xf, int yf) {
			if (selectpiece.isKing()) {

				if (xf > 0 & yf > 0) {
					if ((xf - xi) == 2 & (yf - yi) == 2 & pieceAt(xf - 1, yf - 1) != null) {
						if (pieceAt(xf - 1, yf - 1).isFire() != selectpiece.isFire()) return true;
					}
				}
				if (xf < (N-1) & yf > 0) {
					if ((xf - xi) == -2 & (yf - yi) == 2 & pieceAt(xf + 1, yf - 1) != null) {
						if (pieceAt(xf + 1, yf - 1).isFire() != selectpiece.isFire()) return true;
					}
				}
				if (xf > 0 & yf < (N-1)) {
					if ((xf - xi) == 2 & (yf - yi) == -2 & pieceAt(xf - 1, yf + 1) != null) {
						if (pieceAt(xf - 1, yf + 1).isFire() != selectpiece.isFire()) return true;
					}
				}			
				if (xf < (N-1) & yf < (N-1)) {
					if ((xf - xi) == -2 & (yf - yi) == -2 & pieceAt(xf + 1, yf + 1) != null) {
						if (pieceAt(xf + 1, yf + 1).isFire() != selectpiece.isFire()) return true;
					}
				}
			}
			else if (selectpiece.isFire()) {
				if (xf > 0 & yf > 0) {
					if ((xf - xi) == 2 & (yf - yi) == 2 & pieceAt(xf - 1, yf - 1) != null) {
						if (pieceAt(xf - 1, yf - 1).isFire() != selectpiece.isFire()) return true;
					}
				}
				if (xf < (N-1) & yf > 0) {
					if ((xf - xi) == -2 & (yf - yi) == 2 & pieceAt(xf + 1, yf - 1) != null) {
						if (pieceAt(xf + 1, yf - 1).isFire() != selectpiece.isFire()) return true;
					}
				}
			}
			else if (!(selectpiece.isFire())) {
				if (xf > 0 & yf < (N-1)) {
					if ((xf - xi) == 2 & (yf - yi) == -2 & pieceAt(xf - 1, yf + 1) != null) {
						if (pieceAt(xf - 1, yf + 1).isFire() != selectpiece.isFire()) return true;
					}
				}			
				if (xf < (N-1) & yf < (N-1)) {
					if ((xf - xi) == -2 & (yf - yi) == -2 & pieceAt(xf + 1, yf + 1) != null) {
						if (pieceAt(xf + 1, yf + 1).isFire() != selectpiece.isFire()) return true;
					}
				}
			}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
			if (validCapture(xi, yi, xf, yf)) return true;
			if (pieceAt(xi, yi).isKing()) {
				if (abs(xf - xi) == 1 & abs(yf - yi) == 1) return true;
			}
			else if (pieceAt(xi, yi).isFire()) {
				if (abs(xf - xi) == 1 & (yf - yi) == 1) return true;
			}
			else if (!(pieceAt(xi, yi).isFire())){
				if (abs(xf - xi) == 1 & (yf - yi) == -1) return true;
			}
		return false;
	}

	private Piece oldselect = null;
	private Piece selectpiece;
	private boolean move = false;
	public void select(int x, int y) {
			if (pieceAt(x,y) != null) {		
				selectpiece = pieceAt(x, y);
        		oldselect = selectpiece; 

			}
			else {
				if (oldselect != null) {
					oldselect.move(x, y);
					move = true;
				}
			}

	}

	private void deselect(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		if (pieceAt(x, y) != null) StdDrawPlus.picture(x + .5, y + .5, imgPath(pieceAt(x, y)), 1, 1);
	}

	private void deselect(int[] x, int[] y) {
		for (int k=1; k<79; k++) {
			StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
			StdDrawPlus.filledSquare(x[k] + .5, y[k] + .5, .5);
			if (pieceAt(x[k], y[k]) != null) StdDrawPlus.picture(x[k] + .5, y[k] + .5, imgPath(pieceAt(x[k], y[k])), 1, 1);
		}
	}

	private String imgpath;
	private String imgPath(Piece p) {
		if (p.isFire()) {
			if (p.isKing()) {
				if (p.isBomb()) imgpath = "img/bomb-fire-crowned.png";
				else if (p.isShield()) imgpath = "img/shield-fire-crowned.png";
				else imgpath ="img/pawn-fire-crowned.png";
			}
			else {
				if (p.isBomb()) imgpath = "img/bomb-fire.png";
				else if (p.isShield()) imgpath = "img/shield-fire.png";
				else imgpath = "img/pawn-fire.png";
			}
		}
		else { 
			if (p.isKing()) {
				if (p.isBomb()) imgpath = "img/bomb-water-crowned.png";
				else if (p.isShield()) imgpath = "img/shield-water-crowned.png";
				else imgpath = "img/pawn-water-crowned.png";
			}
			else {
				if (p.isBomb()) imgpath = "img/bomb-water.png";
				else if (p.isShield()) imgpath = "img/shield-water.png";
				else imgpath = "img/pawn-water.png";
			}
		} 
		return imgpath;
	}

	public void place(Piece p, int x, int y) {
		if (x < N & x >= 0 & y < N & y >= 0 & p != null) {
			pieces[x][y] = p;
		}
	}

	private boolean containsInts(int x, int y, int[] x_a, int[] y_a) {
		for (int k = 0; k < 80; k++) {
				if (x_a[k] == x & y_a[k] == y) return true;
		}
		
		return false;
	}

	private int i = 1;
	private int[] xplacesToPaint = new int[80]; 
	private int[] yplacesToPaint = new int[80];
	public Piece remove(int x, int y) {
		if (x < N & x >= 0 & y < N & y >= 0 & pieceAt(x,y) != null) {
			Piece p = pieceAt(x, y);
			pieces[x][y] = null;
 
			if (!containsInts(x, y, xplacesToPaint, yplacesToPaint)) {
				xplacesToPaint[i] = x;
				yplacesToPaint[i] = y;
				i += 1;

			}
			return p;
		}
		else if (pieceAt(x,y) == null) {
			System.out.println("Missed Pieces!");
			return null;
		}
		else {
			System.out.println("Out of bounds");
			return null;
		}
		
	}

	public boolean canEndTurn() {
		if (move) return true;
		else return false;
	}

	public void endTurn() {
		if (firesTurn) firesTurn = false;
		else firesTurn = true;
		move = false;
		selectpiece.doneCapturing();
		selectpiece = null;
		xplacesToPaint = new int[80];
		yplacesToPaint = new int[80];
		i = 1;
	}

	public String winner() {
		int water = 0;
		int fire = 0;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieces[i][j] != null) {
            		if (pieces[i][j].isFire()) fire += 1;
            		else water += 1;
            	}
            }
    	}

        if (water == 0 & fire == 0) return "No one";
		else if (water == 0) return "Fire";
		else if (fire == 0) return "Water";
		else return null;
	}

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
               	 	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	if (!empty) {
		        		if (j <= 2 || j >= 5) {
		        		StdDrawPlus.picture(i + .5, j + .5, imgPath(pieces[i][j]), 1, 1); 
                		}	
                	}
            	}
            	else {
            		StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
               		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
                
            	}
            }
        }		
    }
    private Piece oldselect2 = null; 
    private int oldsecx2; 
    private int oldsecy2;
    private boolean firesTurn;
 	public static void main(String[] args) {
 		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        boolean shouldBeEmpty = false;
        Board b = new Board(shouldBeEmpty);
		b.drawBoard(N);
        while (b.winner() == null) {	
        	if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (x >= 0 & x <= 8 & y >= 0 & y <= 8 & b.canSelect((int) x,(int) y)) {
                	b.select((int) x,(int) y);
                	if (b.pieceAt((int) x,(int) y) != null) {
                		if (b.oldselect2 != null) b.deselect(b.oldsecx2, b.oldsecy2);					
                		StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
        				StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5); 
        				StdDrawPlus.picture((int) x + .5, (int) y + .5, b.imgPath(b.pieceAt((int) x, (int) y)), 1, 1);
                	}     
                	else {
                		
                		if (b.oldselect2 != null) b.deselect(b.oldsecx2, b.oldsecy2);
					
                	}	
                	b.deselect(b.xplacesToPaint, b.yplacesToPaint);
        			StdDrawPlus.show(1);
        			b.oldselect2 = b.pieceAt((int) x, (int) y);
        			b.oldsecx2 = (int) x;
        			b.oldsecy2 = (int) y;
                }
            }
            if (StdDrawPlus.isSpacePressed() & b.canEndTurn()) b.endTurn();
        }
        System.out.println(b.winner());
    }
}