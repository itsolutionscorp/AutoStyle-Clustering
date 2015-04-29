/** 
 *  @author Amy Ji LOGIN: CS61B-afq
 */

public class Board {
	private Piece[][] pieces = new Piece[8][8];
	private boolean hasMoved = false;
	private Piece selected = null;
	private int selectedx;
	private int selectedy;
	// keeps track of the player
	private boolean fireturn = true;
	private boolean bombexploded = false;
	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j ++) {
					pieces[i][j] = null;
 				}
			}
		}
		else {
			//creating pieces
			int i = 0;
			for (int j =0; j <8; j = j+2) {
				pieces[j][i] = new Piece(true,this,j,i,"pawn");
			}
			int k = 1;
			for (int j =1; j < 8; j = j+2) {
				pieces[j][k] = new Piece(true,this,j,k,"shield");
			}
			int l = 2;
			for (int j =0; j <8; j = j+2) {
				pieces[j][l] = new Piece(true,this,j,l,"bomb");
			}
			int m = 5;
			for (int j =1; j <8; j = j+2) {
				pieces[j][m] = new Piece(false,this,j,m,"bomb");
			}
			int n = 6;
			for (int j =0; j <8; j = j+2) {
				pieces[j][n] = new Piece(false,this,j,n,"shield");
			}
			int p = 7;
			for (int j =1; j <8; j = j+2) {
				pieces[j][p] = new Piece(false,this,j,p,"pawn");
			}
		}
	}
	private boolean outBound(int x, int y) {
		return ((x > 7) || (x < 0)) || ((y > 7) || (y < 0));
	}

	public Piece pieceAt(int x, int y) {
		if (outBound(x,y)) {
			return null;
		}
		else if (pieces[x][y] == null) {
			return null;
		}
		else {
			return pieces[x][y];
		}
	}
	public boolean canSelect(int x, int y) {
		if ((!outBound(x,y)) && (pieceAt(x,y) != null) && (pieceAt(x,y).isFire()) && fireturn && (!bombexploded)) {
			if (selected == null) {
				return true;
			}
			else if (!hasMoved) {
				return true;
			}
			else {
				return false;
			}
		}
		else if ((!outBound(x,y)) && (pieceAt(x,y) != null) && (!pieceAt(x,y).isFire()) && (!fireturn) && (!bombexploded)) {
			if (selected == null) {
				return true;
			}
			else if (!hasMoved) {
				return true;
			}
			else {
				return false;
			}
		}		
		else if ((!outBound(x,y)) && (pieces[x][y] == null) && (selected != null)) {
			if ((!hasMoved) && validMove(selectedx,selectedy,x,y)) {
				return true;
			}
			else if ((hasMoved) && (selected.hasCaptured()) && validMove(selectedx,selectedy,x,y)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if ((pieceAt(xi,yi) != null) && (pieceAt(xi,yi).isFire()) && fireturn && (!outBound(xf,yf))) {
			if (pieceAt(xi,yi).isKing()) {
				if ((Math.abs(xi - xf) <= 1) && (Math.abs(yi - yf) <= 1) && (!pieceAt(xi,yi).hasCaptured())) {
					return true;
				}
				else if ((Math.abs(yf - yi) ==2) && (Math.abs(xf - xi) == 2)) {
					if ((pieceAt(xi+1,yi+1) != null) && (!pieceAt(xi+1,yi+1).isFire())) {
						return true;
					}
					else if ((pieceAt(xi-1,yi+1) != null) && (!pieceAt(xi-1,yi+1).isFire())) {
						return true;
					}
					else if ((pieceAt(xi+1,yi-1) != null) && (!pieceAt(xi+1,yi-1).isFire())) {
						return true;
					}
					else if ((pieceAt(xi-1,yi-1) != null) && (!pieceAt(xi-1,yi-1).isFire())) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
			else if ((Math.abs(xi-xf) == 1) && (yi+1 == yf) && (!pieceAt(xi,yi).hasCaptured())) {
				return true;
			}
			else if ((pieceAt(xi+1,yi+1) != null) && (!pieceAt(xi+1,yi+1).isFire())
					&& (yf == yi +2) && (xf == xi +2)) {
				return true;
			}
			else if ((pieceAt(xi-1,yi+1) != null) && (!pieceAt(xi-1,yi+1).isFire())
					&& (yf == yi +2) && (xf == xi -2)) {
						return true;
			}
			else {
				return false;
			}		
		}
		else if ((pieceAt(xi,yi) != null) && (!pieceAt(xi,yi).isFire()) && (!fireturn) && (!outBound(xf,yf))) {
			if (selected.isKing()) {
				if ((Math.abs(xi - xf) <= 1) && (Math.abs(yi - yf) <= 1) && (!pieceAt(xi,yi).hasCaptured())) {
					return true;
				}
				else if ((Math.abs(yf - yi) ==2) && (Math.abs(xf - xi) == 2)) {
					if ((pieceAt(xi+1,yi+1) != null) && (pieceAt(xi+1,yi+1).isFire())) {
						return true;
					}
					else if ((pieceAt(xi-1,yi+1) != null) && (pieceAt(xi-1,yi+1).isFire())) {
						return true;
					}
					else if ((pieceAt(xi+1,yi-1) != null) && (pieceAt(xi+1,yi-1).isFire())) {
						return true;
					}
					else if ((pieceAt(xi-1,yi-1) != null) && (pieceAt(xi-1,yi-1).isFire())) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
			else if ((pieceAt(xf,yf) == null) && (Math.abs(xi-xf) == 1) && (yi-1 == yf) && (!pieceAt(xi,yi).hasCaptured())){
				return true;
			}
			else if ((pieceAt(xi+1,yi-1) != null) && (pieceAt(xi+1,yi-1).isFire())
					&& (yf == yi -2) && (xf == xi +2)) {
				return true;
			}
			else if ((pieceAt(xi-1,yi-1) != null) && (pieceAt(xi-1,yi-1).isFire())
					&& (yf == yi -2) && (xf == xi -2)) {
						return true;
			}
			else {
				return false;
			}		
		}
		else {
			return false;
		}
	}

	public void select(int x, int y) {
		if (pieceAt(x,y) != null) {
			selected = pieceAt(x,y);
			selectedx = x;
			selectedy = y;
			
		}
		else {
			hasMoved = true;
			selected.move(x, y);
			selected = pieceAt(x,y);
			selectedx = x;
			selectedy = y;
		}
	}

	public void place(Piece p, int x, int y) {
		if ((p != null) && (!outBound(x,y))) {
			if (pieceAt(x,y) != null) {
				pieces[x][y] = null;
			}
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (pieceAt(i,j) == p) {
						pieces[i][j] = null;
					}
				}
			}
			pieces[x][y] = p;
			if ((p.isBomb()) && p.hasCaptured()) {
				bombexploded = true;
				pieces[x][y] = null;
			}
		}
	}
	public Piece remove(int x, int y) {
		if (outBound(x,y)) {
			System.out.println("Coordinates out of bound!");
			return null;
		}
		Piece result = pieceAt(x,y);
		pieces[x][y] = null;
		return result;
	}
	public boolean canEndTurn() {
		if ((selected != null) && ((hasMoved) || selected.hasCaptured())) {
			return true;
		}
		else {
			return false;
		}
	}
	public void endTurn() {
			if (fireturn == true) {
				fireturn = false;
			}
			else if (fireturn ==false) {
				fireturn = true;
			}
			if (selected != null) {
				selected.doneCapturing();
				selected = null;
			}
			hasMoved = false;
	}

	public String winner () {
		boolean isEmpty = true;
		boolean allWater = true;
		boolean allFire = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieceAt(i,j) != null) {
					isEmpty = false;
				}
				if ((pieceAt(i,j) != null) && (pieceAt(i,j).isFire())) {
					allWater = false;
				}
				if ((pieceAt(i,j) != null) && (!pieceAt(i,j).isFire())) {
					allFire = false;
				}				
			}
		}
		if (isEmpty) {
			return "No one";
		}
		else if (allFire) {
			return "Fire";
		}
		else if (allWater) {
			return "Water";
		}
		else {
			return null;
		}
	}
	private static boolean[][] p;
    private static void drawBoard(int N) {
    	Board b = new Board(false);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if ((p[i][j]) && ((b.pieceAt(i,j)).isFire())) {
                	if ((b.pieceAt(i,j)).isShield()) {
                	   if ((b.pieceAt(i,j)).isKing()) {
                	   	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crown.png", 1, 1);
                	   }
                	   else {
                	   	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                	   }
            		}
                	else if ((b.pieceAt(i,j)).isBomb()) {
                	   if ((b.pieceAt(i,j)).isKing()) {
                	   	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crown.png", 1, 1);
                	   }
                	   else {
                	   	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                	   }
            		}
            		else {
            			if ((b.pieceAt(i,j)).isKing()) {
                	   		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crown.png", 1, 1);
                	   	}
                	   	else {
                	   		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                	   	}       			
            		}
            	}
            	if ((p[i][j]) && (!(b.pieceAt(i,j)).isFire())) {
                	if ((b.pieceAt(i,j)).isShield()) {
                	   if ((b.pieceAt(i,j)).isKing()) {
                	   	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crown.png", 1, 1);
                	   }
                	   else {
                	   	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                	   }
            		}
                	else if ((b.pieceAt(i,j)).isBomb()) {
                	   if ((b.pieceAt(i,j)).isKing()) {
                	   	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crown.png", 1, 1);
                	   }
                	   else {
                	   	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                	   }
            		}
            		else {
            			if ((b.pieceAt(i,j)).isKing()) {
                	   		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crown.png", 1, 1);
                	   	}
                	   	else {
                	   		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                	   	}       			
            		}
            	}
            }
        }
    }
	public static void main(String[] args) {
		Board board = new Board(false);
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		p = new boolean[8][8];
		for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j ++) {
					if (board.pieceAt(i,j) != null) {
						p[i][j] = true;
					}
				}
			}
		drawBoard(8);
		while (board.winner() == null) {
			if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int)x,(int)y)) {
                	board.select((int)x,(int)y);
                	p[(int) x][(int) y] = true;
                }
            }
            drawBoard(8);
            if (StdDrawPlus.isSpacePressed()) {
            	if (board.canEndTurn()) {
            		board.endTurn();
            	}
            }
           
            StdDrawPlus.show(100);
		}
	} 
}