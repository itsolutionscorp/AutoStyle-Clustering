public class Board {

private Piece[][] pieces;
private int selx;
private int sely;
private boolean fireTurn;
private boolean hasMoved;



	public Board(boolean shouldBeEmpty) {
		this.pieces = new Piece[8][8];
		if (shouldBeEmpty) {
			this.pieces = new Piece[8][8];
			this.fireTurn = true;
			this.hasMoved = false;
			this.selx = 0;
			this.sely = 1;
		} else {
			this.fireTurn = true;
			this.hasMoved = false;
			this.selx = 0;
			this.sely = 1;
//			Piece[][] fireTeam = new Piece[8][8];
//			Piece[][] waterTeam = new Piece[8][8];
			int j = 0;
//			int countfi = 0;
			for (int i = 0; i < 8; i = i + 2) {
				pieces[i][j] = new Piece(true, this, i, j, "pawn");
//				fireTeam[i][j] = new Piece(true, this, i, j, "pawn");
			} j++;
			for (int i = 1; i < 8; i = i + 2) {
				pieces[i][j] = new Piece(true, this, i, j, "shield");
//				fireTeam[i][j] = new Piece(true, this, i, j, "shield");
			} j++;
			for (int i = 0; i < 8; i = i + 2) {
				pieces[i][j] = new Piece(true, this, i, j, "bomb");
//				fireTeam[i][j] = new Piece(true, this, i, j, "bomb");
			}
			j = 7;
//			int countwa = 0;
			for (int i = 1; i < 8; i = i + 2) {
				pieces[i][j] = new Piece(false, this, i, j, "pawn");
//				waterTeam[i][j] = new Piece(false, this, i, j, "pawn");
			} j = j - 1;
			for (int i = 0; i < 8; i = i + 2) {
				pieces[i][j] = new Piece(false, this, i, j, "shield");
//				waterTeam[i][j] = new Piece(false, this, i, j, "shield");
			} j = j - 1;
			for (int i = 1; i < 8; i = i + 2) {
				pieces[i][j] = new Piece(false, this, i, j, "bomb"); 
//				waterTeam[i][j] = new Piece(false, this, i, j, "bomb");
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if ((x > 7)||(y > 7)) {
			return null;
		}
		if ((x < 0)||(y < 0)) {
			return null;
		}
		if (pieces[x][y] == null) {
			return null;
		} else {
			return pieces[x][y];
		}
	}

	public void place(Piece p, int x, int y) {
		if ((x > 7)||(y > 7)) return;
		if ((x < 0)||(y < 0)) return;
		this.pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if ((x > 7)||(y > 7)) {
			System.out.println("chosen location is outside of the board!");
			return null;
		}
		if ((x < 0)||(y < 0)) {
			System.out.println("chosen location is outside of the board!");
			return null;			
		}
		if (this.pieces[x][y] == null) {
			System.out.println("No piece to remove at specified location!");
			return null;
		}
		Piece temp = this.pieces[x][y];
//		if (temp.isFire()) {
//			fireDed = fireDed + 1;
//		} else {
//			waterDed = waterDed + 1;
//		}
		this.pieces[x][y] = null;
		return temp;
	}

	public boolean canSelect(int x, int y) {
		if (((x > 7)||(y > 7))||((x < 0)||(y < 0))) {
			return false;
		}
		Piece selPiece = this.pieces[this.selx][this.sely];
		if (this.pieces[x][y] == null) {
			if (selPiece == null) {
				return false;
			}
			if (!selPiece.hasCaptured()) {
				if (!this.hasMoved) {
					if (this.validMove(this.selx, this.sely, x, y)) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				if (this.validMove(this.selx, this.sely, x, y)) {
					if (Math.abs(selx - x) == 2) {
						return true;
					} else {
						return false;						
					}
				} else {
					return false;
				}
			}
		} else {
			if (this.fireTurn != this.pieces[x][y].isFire()) {
				return false;
			} else if (selPiece == null) {
				return true;
			} else if (!this.hasMoved) {
				return true;
			} else {
				return false;
			}
		} 
	}

	public void select(int x, int y) {
			if (this.pieces[selx][sely] == null) {
				selx = x;
				sely = y;
				return;
			} else if (this.pieces[x][y] == null) {
				this.pieces[selx][sely].move(x, y);
				selx = x;
				sely = y;
				this.hasMoved = true;
				return;
			} else {
				selx = x;
				sely = y;
			}
		}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		int xmid = (xi + xf)/2;
		int ymid = (yi + yf)/2;
		if ((xf < 0)||(xf > 7)||(yf < 0)||(yf > 7)) {
			return false;
		}
		if ((xi == xf)||(yi == yf)) {
			return false;
		} else if ((Math.abs(xi - xf) == 1)&&(Math.abs(yi - yf)) == 1) {
			if (pieces[xi][yi].isKing()) {
				if (pieces[xf][yf] != null) {
					return false;
				} else {
					return true;
				}				
			} else if (pieces[xi][yi].isFire()) {
				if (yf > yi) {
					if (pieces[xf][yf] != null) {
						return false;
					} else {
						return true;
					}
				} else {
					return false;
				}
			} else if (!pieces[xi][yi].isFire()) {
				if (yf < yi) {
					if (pieces[xf][yf] != null) {
						return false;
					} else {
						return true;
					} 				
				} else {
					return false;
				}
			}
		} else if ((Math.abs(xi - xf) == 2)&&(Math.abs(yi - yf)) == 2) {
			if (pieces[xf][yf] != null) {
				return false;
			}
			if (pieces[xmid][ymid] == null) {
				return false;
			} else if (pieces[xi][yi].isKing()) {
				if (pieces[xi][yi].isFire() != pieces[xmid][ymid].isFire()) {
					return true;
				} else {
					return false;
				}
			} else if (pieces[xi][yi].isFire()) {
				if (yf > yi) {
					if (!pieces[xmid][ymid].isFire()) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else if (!pieces[xi][yi].isFire()) {
				if (yf < yi) {
					if (pieces[xmid][ymid].isFire()) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}				
			}
		} else {
			return false;
		}
		return false;
	}

	public boolean canEndTurn() {
		if (this.hasMoved) {
			return true;
		} else {
			return false;
		}
	}

	public void endTurn() {
		this.hasMoved = false;
		if (this.fireTurn) {
			this.fireTurn = false;
		} else {
			this.fireTurn = true;
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.pieces[i][j] != null) {
					this.pieces[i][j].doneCapturing();
				}
			}
		}
		this.selx = 0;
		this.sely = 1;
	}

	public String winner() {
		boolean fireLeft = false;
		boolean waterLeft = false;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (this.pieces[i][j] != null) {
            		if (this.pieces[i][j].isFire()) {
            			fireLeft = true;
            		}
            		if (!this.pieces[i][j].isFire()) {
            			waterLeft = true;
            		}
            	}
        	}
        }
        if (!fireLeft&&!waterLeft) {
        	return "No one";
        } else if (!fireLeft) {
        	return "Water";
        } else if (!waterLeft) {
        	return "Fire";
        } else {
        	return null;
        }

	}


	private static String getImage(Piece piece) {
		if (!piece.isFire()) {
			if (piece.isShield() && piece.isKing()) {
                return "img/shield-water-crowned.png";
            } else if (piece.isShield()) {
                return "img/shield-water.png";
            } else if (piece.isBomb() && piece.isKing()) {
                return "img/bomb-water-crowned.png";
            } else if (piece.isBomb()) {
            	return "img/bomb-water.png";
            } else if (piece.isKing()) {
            	return "img/pawn-water-crowned.png";
            } else {
            	return "img/pawn-water.png";
            }
		} else {
			if (piece.isShield() && piece.isKing()) {
                return "img/shield-fire-crowned.png";
            } else if (piece.isShield()) {
                return "img/shield-fire.png";
            } else if (piece.isBomb() && piece.isKing()) {
                return "img/bomb-fire-crowned.png";
            } else if (piece.isBomb()) {
            	return "img/bomb-fire.png";
            } else if (piece.isKing()) {
            	return "img/pawn-fire-crowned.png";
            } else {
            	return "img/pawn-fire.png";
            }
		}
	}

	private void drawBoard(int N) {
			for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {
                	if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	if (pieces[i][j] != null) {
                		StdDrawPlus.picture(i + .5, j + .5, getImage(pieces[i][j]), 1, 1);			
                		}
                	}
            	}
        	}


	public static void main(String[] args) {
        Board b = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        b.fireTurn = true;
//        b.selx = 0;
//        b.sely = 0;
//        pieces = new boolean[N][N];


        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x,(int) y)) {
                	b.select((int) x,(int) y);
 //               	System.out.println(x + y);
 //               	b.drawBoard(N);
                }
            }

            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
//            		b.drawBoard(N);
            	} 

            }
            if (b.winner() != null) {
            	System.out.println(b.winner());
            	b.drawBoard(N);
            	StdDrawPlus.show(20);
            	return;
            } 
            StdDrawPlus.show(100);
        }
    }
}