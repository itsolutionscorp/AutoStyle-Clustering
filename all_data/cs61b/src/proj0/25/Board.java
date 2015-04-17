public class Board {
	private boolean board_start;
	private boolean selected;
    private Piece selP;
	private boolean moved;
	private boolean captured;
	private int selPosX;
	private int selPosY;
    /** Location of pieces. */
    private boolean[][] pieces;
    private Piece[][] pieceInd;
    private int count;
    private int waterCollector;
    private int fireCollector;
    private boolean turn;
    private int N;
    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    private void drawBoard() {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);}
                else                  {StdDrawPlus.setPenColor(StdDrawPlus.RED);}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (this.count == 0) {
                    if (i >=0 && i % 2 == 0 && j == 0) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    } else if (i >=0 && i % 2 == 1 && j == 1) {
                    	StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    } else if (i >=0 && i % 2 == 0 && j == 2) {
                    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    } else if (i >=0 && i % 2 == 1 && j == 5) {
                    	StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    } else if (i >=0 && i % 2 == 0 && j == 6) {
                    	StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    } else if (i >=0 && i % 2 == 1 && j == 7) {
                    	StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    }
                    if (i == 7 && j == 7) {
                        this.count++;
                    }
                } else {
                    if (pieces[i][j] && pieceAt(i, j) != null) {
                        StdDrawPlus.picture(i + .5, j + .5, chooseImg(pieceAt(i,j)), 1, 1);
                    }
                }                
            }
        }
    }
    private void drawEmptyBoard() {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
    	for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY);}
                else                  {StdDrawPlus.setPenColor(StdDrawPlus.RED);}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j] && pieceAt(i, j) != null) {
                    StdDrawPlus.picture(i + .5, j + .5, chooseImg(pieceAt(i,j)), 1, 1);
                }
            }
        }
    }
    private void makeBoard(Board b) {
    	if (b.board_start) {
    		drawEmptyBoard();
    	} else {
    		drawBoard();
    	}
    }
    private String chooseImg(Piece pc) {
        if (pc.isKing()) {
            if (pc.isFire()) {
                if (pc.isBomb()) {
                    return "img/bomb-fire-crowned.png";
                } else if (pc.isShield()) {
                    return "img/shield-fire-crowned.png";
                } else {
                    return "img/pawn-fire-crowned.png";
                }
            } else {
                if (pc.isBomb()) {
                    return "img/bomb-water-crowned.png";
                } else if (pc.isShield()) {
                    return "img/shield-water-crowned.png";
                } else {
                    return "img/pawn-water-crowned.png";
                }
            }
        } else {
            if (pc.isFire()) {
                if (pc.isBomb()) {
                    return "img/bomb-fire.png";
                } else if (pc.isShield()) {
                    return "img/shield-fire.png";
                } else {
                    return "img/pawn-fire.png";
                }
            } else {
                if (pc.isBomb()) {
                    return "img/bomb-water.png";
                } else if (pc.isShield()) {
                    return "img/shield-water.png";
                } else {
                    return "img/pawn-water.png";
                }
            }
        }
    }
    private boolean kingCapturable(int xi, int yi, int xf, int yf) {
		if (xf - xi == 2 && yf - yi == 2) {
			if (pieces[xf-1][yf-1]) {
				return true;
			}
		} else if (xf - xi == 2 && yf - yi == -2) {
			if (pieces[xf-1][yf+1]) {
				return true;
			}
		} else if (xf - xi == -2 && yf - yi == 2) {
			if (pieces[xf+1][yf-1]) {
				return true;
			}
		} else if (xf - xi == -2 && yf - yi == -2) {
			if (pieces[xf+1][yf+1]) {
				return true;
			}
		}
		return false;
    }
    private boolean fireCapturable(int xi, int yi, int xf, int yf) {
    	if (xf - xi == 2 && yf - yi == -2) {
			if (pieces[xf-1][yf+1]) {
				return true;
			}
		} else if (xf - xi == -2 && yf - yi == -2) {
			if (pieces[xf+1][yf+1]) {
				return true;
			}
		}
		return false;
    }
    private boolean waterCapturable(int xi, int yi, int xf, int yf) {
    	if (xf - xi == 2 && yf - yi == 2) {
			if (pieces[xf-1][yf-1]) {
				return true;
			}
		} else if (xf - xi == -2 && yf - yi == 2) {
			if (pieces[xf+1][yf-1]) {
				return true;
			}
		}
		return false;
    }
    private boolean bigJump(int xi, int yi, int xf, int yf) {
        if (turn) {
            if (kingCapturable(xi, yi, xf, yf)) {
                return true;
            }
            if (waterCapturable(xi, yi, xf, yf)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (kingCapturable(xi, yi, xf, yf)) {
                return true;
            }
            if (fireCapturable(xi, yi, xf, yf)) {
                return true;
            } else {
                return false;
            }
        }
    }
    public Board(boolean shouldBeEmpty) {
        count = 0;
        waterCollector = 12;
        fireCollector = 12;
        turn = true;
        N = 8;
        pieces = new boolean[N][N];
        pieceInd = new Piece[N][N];
    	board_start = shouldBeEmpty;
    	selected = false;
    	selPosX = -1;
    	selPosY = -1;
        selP = null;
    	moved = false;
    	captured = false;
        if (board_start) {
            drawEmptyBoard();
        } else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (this.count == 0) {
                        if (i >=0 && i % 2 == 0 && j == 0) {
                            pieceInd[i][j] = new Piece(true, this, i, j, "pawn");
                            place(pieceInd[i][j], i, j);
                        } else if (i >=0 && i % 2 == 1 && j == 1) {
                            pieceInd[i][j] = new Piece(true, this, i, j, "shield");
                            place(pieceInd[i][j], i, j);
                        } else if (i >=0 && i % 2 == 0 && j == 2) {
                            pieceInd[i][j] = new Piece(true, this, i, j, "bomb");
                            place(pieceInd[i][j], i, j);
                        } else if (i >=0 && i % 2 == 1 && j == 5) {
                            pieceInd[i][j] = new Piece(false, this, i, j, "bomb");
                            place(pieceInd[i][j], i, j);
                        } else if (i >=0 && i % 2 == 0 && j == 6) {
                            pieceInd[i][j] = new Piece(false, this, i, j, "shield");
                            place(pieceInd[i][j], i, j);
                        } else if (i >=0 && i % 2 == 1 && j == 7) {
                            pieceInd[i][j] = new Piece(false, this, i, j, "pawn");
                            place(pieceInd[i][j], i, j);
                        }
                    }                
                }
            }
        }
    }
    public boolean canEndTurn() {
        if (this.moved) {
            return true;
        }
        return false;
    }
    public void endTurn() {
        if (turn) {
            this.turn = false;
        } else {
            this.turn = true;
        }
        this.selected = false;
        if (pieceAt(selPosX, selPosY) != null) {
            pieceAt(selPosX, selPosY).doneCapturing();
        }
        this.selPosX = -1;
        this.selPosY = -1;
        this.selP = null;
        this.moved = false;
        this.captured = false;
    }
    public String winner() {
        if ((this.waterCollector <= 12 && this.waterCollector > 0) || (this.fireCollector <= 12 && this.fireCollector > 0) || (this.waterCollector == 0 && this.fireCollector == 0)) {
            return "No one";
        } else if (this.waterCollector == 0) {
            return "Fire";
        } else if (this.fireCollector == 0) {
            return "Water";
        } else {
            return null;
        }
    }
    public void select(int x, int y) {
        if (this.selP != null && this.selected && !pieces[x][y]) {
            this.selP.move(x, y);
            if (this.selP.hasCaptured()) {
                this.captured = true;
            }
            pieces[this.selPosX][this.selPosY] = false;
            pieceInd[this.selPosX][this.selPosY] = null;
            this.selPosX = x;
            this.selPosY = y;
            if (pieceAt(x, y) != null) {
                this.selP = pieceAt(x, y);
            }
            this.moved = true;
        } else {
            if (pieceAt(x, y) != null) {
                this.selP = pieceAt(x, y);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            }
            this.selected = true;
            this.selPosX = x;
            this.selPosY = y;
        }
    }
    public boolean canSelect(int x, int y) {
		if (turn) {
            if (((this.selected && this.moved == false) || this.selected == false) && pieceAt(x, y) != null && pieceAt(x, y).isFire()) {
                this.selP = pieceAt(x, y);
                return true;
            } else if (((this.selected && this.moved == false) && validMove(this.selPosX, this.selPosY, x, y)) ||
			  	        (this.selected && this.captured && validMove(this.selPosX, this.selPosY, x, y))) {
				return true;
			}
		} else {
            if (((this.selected && this.moved == false) || this.selected == false) && pieceAt(x, y) != null && !pieceAt(x, y).isFire()) {
                this.selP = pieceAt(x, y);
                return true;
            } else if (((this.selected && this.moved == false) && validMove(this.selPosX, this.selPosY, x, y)) ||
			  	        (this.selected && this.captured && validMove(this.selPosX, this.selPosY, x, y))) {
				return true;
			}
		}
    	return false;
    }
    private boolean validMove(int xi, int yi, int xf, int yf) {
    	if (xi >= 0 && xi < 8 && yi >= 0 && yi < 8 && xf >= 0 && xf < 8 && yf >= 0 && yf < 8) {
    		if (!pieces[xf][yf]) {
	    		if (turn) {
                    if (pieceAt(xi, yi) != null && pieceAt(xi, yi).isFire()) {
                        if (pieceAt(xi, yi).isKing()) {
                            if (kingCapturable(xi, yi, xf, yf)) {
                                return true;
                            } else if (!this.captured && (xf - xi == 1 || xf - xi == -1) && (yf - yi == 1 || yf - yi == -1)) {
                                return true;
                            }
                        } else if (!this.captured && (xf - xi == 1 || xf - xi == -1) && yf - yi == 1) {
    		    			return true;
    		    		} else if (waterCapturable(xi, yi, xf, yf)) {
    		    			return true;
    		    		}
                    }
		    	} else {
                    if (pieceAt(xi, yi) != null && !pieceAt(xi, yi).isFire()) {
                        if (pieceAt(xi, yi).isKing()) {
                            if (kingCapturable(xi, yi, xf, yf)) {
                                return true;
                            } else if (!this.captured && (xf - xi == 1 || xf - xi == -1) && (yf - yi == 1 || yf - yi == -1)) {
                                return true;
                            }
                        } else if (!this.captured && (xf - xi == 1 || xf - xi == -1) && yf - yi == -1) {
    		    			return true;
    		    		} else if (fireCapturable(xi, yi, xf, yf)) {
    		    			return true;
    		    		}
                    }
		    	}
	    	}
    	}
    	return false;
    }
    public Piece pieceAt(int x, int y) {
    	if (x < 0 || x >= 8 || y < 0 || y >= 8 || !pieces[x][y]) {
    		return null;
    	} else {
    		return pieceInd[x][y];
    	}
    }
    public void place(Piece p, int x, int y) {
        if (p != null) {
        	if (x >= 0 && x < 8 && y >= 0 && y < 8) {
        		pieces[x][y] = true;
        		pieceInd[x][y] = p;
        	}
        }
    }
    public Piece remove(int x, int y) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            System.out.println("Input is out of bounds.");
            return null;
        } else if (!pieces[x][y]) {
            System.out.println("There is no piece at the given input.");
            return null;
        } else {
            Piece oldPiece = pieceInd[x][y];
            pieceInd[x][y] = null;
            pieces[x][y] = false;
            if (oldPiece.isFire()) {
                this.fireCollector--;
            } else {
                this.waterCollector--;
            }
            return oldPiece;
        }
    }
    public static void main(String[] args) {
        Board brd = new Board(false);
        /** Monitors for mouse presses. */
        while(true) {
            brd.makeBoard(brd);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (brd.canSelect((int) x, (int) y)) {
                    brd.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (brd.canEndTurn()) {
                    brd.endTurn();
                    if ((brd.waterCollector <= 12 && brd.waterCollector > 0) || (brd.fireCollector <= 12 && brd.fireCollector > 0) || (brd.waterCollector == 0 && brd.fireCollector == 0)) {
                        System.out.println(brd.winner());
                    }
                }
            }
            StdDrawPlus.show(100);
        }
    }
}