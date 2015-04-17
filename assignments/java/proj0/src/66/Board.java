public class Board {
    private Piece[][] pieces;
    private int N = 8;
    private int player = 0;
    private boolean select = false;
    private int selectX = -1;
    private int selectY = -1;
    private boolean hasMoved = false;
    private int firePieces = 0;
    private int waterPieces = 0;
    private Piece selectPiece;

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[N][N];
		if (shouldBeEmpty) {
			for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {
            		pieces[i][j] = null;
            	}
            }
        }
        else {
            firePieces = 12;
            waterPieces = 12;
        	for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {
            		if (i%2 == 0 && j == 0) {
            			pieces[i][j] = new Piece(true, this, i, j, "pawn");
            		}
            		else if (i%2 != 0 && j == 1) {
            			pieces[i][j] = new Piece(true, this, i, j, "shield");
            		}
            		else if (i%2 == 0 && j == 2) {
            			pieces[i][j] = new Piece(true, this, i, j, "bomb");
            		}
            		else if (i%2 != 0 && j == N - 3) {
            			pieces[i][j] = new Piece(false, this, i, j, "bomb");
            		}
            		else if (i%2 == 0 && j == N - 2) {
            			pieces[i][j] = new Piece(false, this, i, j, "shield");
            		}
            		else if (i%2 != 0 && j == N - 1) {
            			pieces[i][j] = new Piece(false, this, i, j, "pawn");
            		}
            		else {
            			pieces[i][j] = null;
            		}
            	}
            }
        }
    }

	private void drawBoard() {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        String img = "null";

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	if (pieces[i][j].isFire()) {
                		if (pieces[i][j].isBomb()) {
                			img = "img/bomb-fire.png";
                            if (pieces[i][j].isKing()) {
                                img = "img/bomb-fire-crowned.png";
                            }
                		}
                		else if (pieces[i][j].isShield()) {
                			img = "img/shield-fire.png";
                            if (pieces[i][j].isKing()) {
                                img = "img/shield-fire-crowned.png";
                            }
                		}
                		else {
                			img = "img/pawn-fire.png";
                            if (pieces[i][j].isKing()) {
                                img = "img/pawn-fire-crowned.png";
                            }
                		}
                	}
                	else {
                		if (pieces[i][j].isBomb()) {
                			img = "img/bomb-water.png";
                            if (pieces[i][j].isKing()) {
                                img = "img/bomb-water-crowned.png";
                            }
                		}
                		else if (pieces[i][j].isShield()) {
                			img = "img/shield-water.png";
                            if (pieces[i][j].isKing()) {
                                img = "img/shield-water-crowned.png";
                            }
                		}
                		else {
                			img = "img/pawn-water.png";
                            if (pieces[i][j].isKing()) {
                                img = "img/pawn-water-crowned.png";
                            }
                		}
                	}
                	StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
    	if (x >= N || x < 0 || y < 0 || y >= N) {
    		return null;
    	}
    	else {
    		return pieces[x][y];
    	}
    }

    public void place(Piece p, int x, int y) {
    	if (x >= N || x < 0 || y < 0 || y >= N || p == null) {
    	}
    	else {
    		for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(p == pieceAt(i, j)) {
                        if (p.isFire()) {
                            firePieces = firePieces - 1;
                        }
                        else {
                            waterPieces = waterPieces - 1;
                        }
                        pieces[i][j] = null;

                    }
                }
            }
            pieces[x][y] = p;
            if (p.isFire()) {
                firePieces = firePieces + 1;
            }
            else {
                waterPieces = waterPieces + 1;
            }
    	}
    }

    public Piece remove(int x, int y) {
        if (x >= N || x < 0 || y < 0 || y >= N) {
            System.out.println("Out of bounds");
            return null;
        }
        else if (pieceAt(x, y) == null) {
            System.out.println("There was no piece in this space");
            return null;
        }
        else {
            Piece p = pieceAt(x, y);
            pieces[x][y] = null;
            if(p.isFire()) {
                firePieces = firePieces - 1;
            }
            else {
                waterPieces = waterPieces - 1;
            }
            return p;
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece p = pieceAt(xi, yi);
        if (xf >= N || xf < 0 || yf < 0 || yf >= N) {
            return false;
        }
        else if (pieceAt(xf, yf) != null) {
            return false;
        }
        else if (p.isKing()) {
            if ((xf == xi + 1 && yf == yi + 1) || (xf == xi - 1 && yf == yi + 1) || (xf == xi + 1 && yf == yi - 1) || (xf == xi - 1 && yf == yi - 1)) {
                return true;
            }
            else if (xf == xi + 2 && yf == yi + 2) {
                if (pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 1, yi + 1).side() != p.side()) {
                    return true;
                }
            }
            else if (xf == xi - 2 && yf == yi + 2) {
                if (pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 1, yi + 1).side() != p.side()) {
                    return true;
                }
            } 
            else if (xf == xi + 2 && yf == yi - 2) {
                if (pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1).side() != p.side()) {
                    return true;
                }
            }
            else if (xf == xi - 2 && yf == yi - 2) {
                if (pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 1, yi - 1).side() != p.side()) {
                    return true;
                }
            }
        }
        else if (p.isFire()) {
            if ((xf == xi + 1 && yf == yi + 1) || (xf == xi - 1 && yf == yi + 1)) {
                return true;
            }
            else if (xf == xi + 2 && yf == yi + 2) {
                if (pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 1, yi + 1).side() != p.side()) {
                    return true;
                }
            }
            else if (xf == xi - 2 && yf == yi + 2) {
                if (pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 1, yi + 1).side() != p.side()) {
                    return true;
                }
            }
        }
        else if (!p.isFire()) {
            if ((xf == xi + 1 && yf == yi - 1) || (xf == xi - 1 && yf == yi - 1)) {
                return true;
            }
            else if (xf == xi + 2 && yf == yi - 2) {
                if (pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1).side() != p.side()) {
                    return true;
                }
            }
            else if (xf == xi - 2 && yf == yi - 2) {
                if (pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 1, yi - 1).side() != p.side()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canSelect(int x, int y) {
        if (x >= N || x < 0 || y < 0 || y >= N) {
            return false;
        }
        else {
            if (pieceAt(x, y) != null) {
                if (!hasMoved && player == pieceAt(x, y).side()) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                if (select && !hasMoved && validMove(selectX, selectY, x, y)) {
                    return true;
                }
                else if (select && pieceAt(selectX, selectY).hasCaptured() && validMove(selectX, selectY, x, y) && ((x == selectX + 2 && y == selectY + 2) || (x == selectX - 2 && y == selectY + 2) || (x == selectX + 2 && y == selectY - 2) || (x == selectX - 2 && y == selectY - 2))) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
    }

    public void select(int x, int y) {
       if (pieceAt(x, y) == null) {
                Piece p = pieceAt(selectX, selectY);
                selectPiece = p;
                p.move(x, y);
                hasMoved = true;
                selectX = x;
                selectY = y;
                if (p.isBomb() && p.hasCaptured()) {
                    select = false;
                }
        }
        else if (pieceAt(x, y) != null) {
                select = true;
                selectX = x;
                selectY = y;
        }
    }

    public boolean canEndTurn() {
        return hasMoved;
    }
   
    public void endTurn() {
        if(player == 0) {
            player = 1;
        }
        else {
            player = 0;
        }
        hasMoved = false;
        select = false;
        selectPiece.doneCapturing();

    }
 
    public String winner() {
        if (waterPieces > 0 && firePieces > 0) {
            return null;
        }
        else if (waterPieces == 0 && firePieces == 0) {
            return "No one";
        }
        else if (waterPieces == 0 && firePieces > 0) {
            return "Fire";
        }
        else {
            return "Water";
        }
    }

	public static void main(String[] args) {
        Board b = new Board(false);
        while(b.winner() == null) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xApprox = (int) x;
                int yApprox = (int) y;
                if(b.canSelect(xApprox, yApprox)) {
                    b.select(xApprox, yApprox);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if(b.canEndTurn()) {
                    b.endTurn();
                }
            }            
            StdDrawPlus.show(90);
        }
        b.drawBoard();
        StdDrawPlus.show(90);
        System.out.println(b.winner());
    }
}
