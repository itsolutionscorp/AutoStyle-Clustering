public class Board {
	private Piece[][] pieces;
    private static int N;
    private boolean fireTurn;
    private Piece selected;
    private int selX;
    private int selY;
    private boolean hasMoved;


	public Board(boolean shouldBeEmpty) {
        this.N = 8;
        fireTurn = true;
        selected = null;
        hasMoved = false;
        pieces = new Piece[N][N];
		if (!shouldBeEmpty) {
			for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {
            		if (i % 2 == 0) {
            			if (j == 0) {
            				pieces[i][j] = new Piece(true, this, i, j, "pawn");
            			}

                        if (j == 2) {
                            pieces[i][j] = new Piece(true, this, i, j, "bomb");
                        }

                        
                        if (j == 6) {
                            pieces[i][j] = new Piece(false, this, i, j, "shield");
                        }

                        
            		}
                    else {
                        if (j == 1) {
                            pieces[i][j] = new Piece(true, this, i, j, "shield");
                        }
                        if (j == 5) {
                            pieces[i][j] = new Piece(false, this, i, j, "bomb");
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
        String name = "";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                name = "";
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (selected != null && i == selX && j == selY)
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	if (pieces[i][j].isFire())
                        name += "fire";
                    else
                        name += "water";

                    if (pieces[i][j].isBomb())
                        name = "bomb-" + name;
                    else if (pieces[i][j].isShield())
                        name = "shield-" + name;
                    else
                        name = "pawn-" + name;

                    if (pieces[i][j].isKing())
                        name = name + "-crowned";

                	StdDrawPlus.picture(i + .5, j + .5, "img/"+ name +".png", 1.0, 1.0);
    
                }
            }
        }
	}

    public static void main(String[] args) {
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

       	while(true) {
           	b.drawBoard(N);

            if (StdDrawPlus.mousePressed()) {
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                int x = (int)(StdDrawPlus.mouseX());
                int y = (int)(StdDrawPlus.mouseY());
                StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
                if (b.canSelect(x, y)) {
                    
                    b.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn())
                    b.endTurn();
            }


        	StdDrawPlus.show(20);
            if (b.winner() != null) {
                System.out.println("The winner is " + b.winner());
                break;
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if ((x < N) && (y < N) && (x >= 0) && (y >= 0))
    	   return pieces[x][y];
        return null;
    }

    public void place(Piece p, int x, int y) {
        if (p != null) {
            if ((x < N) && (y < N) && (x >= 0) && (y >= 0)) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (pieces[i][j] == p)
                            pieces[i][j] = null;
                    }
                }
                pieces[x][y] = p;
            }
        }
    }

    public Piece remove(int x, int y) {
        if ((x >= N) || (y >= N) || (x < 0) || (y < 0)) {
            System.out.println("Position out of bounds");
            return null;
        }
        else if (pieces[x][y] == null) {
            System.out.println("There is no piece at selected position");
            return null;
        }
        else {
            Piece p = pieces[x][y];
            if (selected == p)
                selected = null;
            pieces[x][y] = null;
            return p;
        }
    }

    public boolean canSelect(int x, int y) {
        if ((x >= N) || (y >= N) || (x < 0) || (y < 0)) {
            return false;
        }   
        if (pieces[x][y] != null) {
            return (!hasMoved && fireTurn == (pieces[x][y].isFire()));
        }
        else {
            if (selected != null)
                return validMove(selX, selY, x, y);    
        }
        return false;
    }

    public void select(int x, int y) {
        if (!hasMoved) {
            if (pieces[x][y] != null) {
                selected = pieces[x][y];
                selX = x;
                selY = y;
            }
            else {
                if (selected != null) {
                    selected.move(x, y);
                    selX = x;
                    selY = y;
                    hasMoved = true;
                }
            }
        }
        else {
            if ((selected != null) && selected.hasCaptured()) {
                selected.move(x, y);
                selX = x;
                selY = y;
                hasMoved = true;
                
            }
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (pieces[xi][yi] != null) {
            if (pieces[xi][yi].isKing()) {
                if ((Math.abs(xf-xi) == Math.abs(yf-yi)) && (Math.abs(xf-xi) <= 2) && (xf != xi)) {
                    if (Math.abs(xf-xi) == 2) {
                        if (pieceAt(xi, yi).hasCaptured() || !hasMoved)
                            return canCaptureTo(xi, yi, xf, yf);
                    }
                    else {
                        if (!hasMoved)
                            return (pieceAt(xf, yf) == null);
                    }

                }
            }
            else {
                if ((Math.abs(xf-xi) == Math.abs(yf-yi)) && (Math.abs(xf-xi) <= 2) && (xf != xi)) {
                    if (hasMoved && !pieceAt(xi, yi).hasCaptured())
                        return false;

                    if (pieces[xi][yi].isFire() && (yf-yi) == 2) {
                        return canCaptureTo(xi, yi, xf, yf);
                    }
                    else if (!pieces[xi][yi].isFire() && (yf-yi) == -2) {
                        return canCaptureTo(xi, yi, xf, yf);
                    }
                    if (!pieces[xi][yi].hasCaptured()) {
                        if (pieces[xi][yi].isFire() && (yf-yi) == 1)
                            return (pieces[xf][yf] == null);
                        if (!pieces[xi][yi].isFire() && (yf-yi) == -1)
                            return (pieces[xf][yf] == null);

                    } 

                }
            }
        }
        return false;
    }


    private boolean canCaptureTo(int xi, int yi, int xf, int yf) {
        if (pieceAt(xi, yi) == null)
            return false;
        else {
            return (pieceAt((xi+xf)/2, (yi+yf)/2) != null) && (pieceAt((xi+xf)/2, (yi+yf)/2).isFire() != pieceAt(xi, yi).isFire());
        }
    }

    public boolean canEndTurn() {
        if (selected != null)
            return hasMoved || selected.hasCaptured();
        return hasMoved;
    }
    public void endTurn() {
        if (selected != null)
            selected.doneCapturing();
        fireTurn = !fireTurn;
        hasMoved = false;
        selected = null;
    }

    public String winner() {
        int total = 0;
        int numFire = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] != null) {
                    total += 1;
                    if (pieces[i][j].isFire())
                        numFire += 1;
                }
            }
        }
        if (total == 0)
            return "No One";
        else if (numFire == total)
            return "Fire";
        else if (numFire == 0)
            return "Water";
        else
            return null;
    }
}





