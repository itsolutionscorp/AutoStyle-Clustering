public class Board {

	private boolean playerturn;
	private boolean playerselected;
	private boolean playermoved;
	private Piece previousPiece;
	private boolean moveAgain;
	private int prevpieceX;
	private int prevpieceY;
	private int selectX;
	private int selectY;
	private static Piece[][] pieces;
	private static boolean gameison;

	public static void main(String[] args) {
		Board b = new Board(false);
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		while(gameison == true) {
            b.drawBoard();
            if (b.winner() != null) {
            	System.out.println(b.winner());
            	return;
            }     
            if (StdDrawPlus.mousePressed()) {
            	double x = StdDrawPlus.mouseX();
	            double y = StdDrawPlus.mouseY();
            	if (b.canSelect((int) x,(int) y) == true) {
	            	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	                b.select((int) x, (int) y);
            	}
            }
            if (StdDrawPlus.isSpacePressed() == true) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }   
            StdDrawPlus.show(10);
        }
	}


	private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (selectX == i && selectY == j) {
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	}
                else if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY); }
                else { 
                	StdDrawPlus.setPenColor(StdDrawPlus.RED); }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	if (pieces[i][j].isKing() == true) {
                		if (pieces[i][j].isShield() == true && pieces[i][j].isFire() == true) {
	                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
		                }
		                if (pieces[i][j].isBomb() == true && pieces[i][j].isFire() == true) {
		                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
		                }
		                if (pieces[i][j].isShield() == false && pieces[i][j].isBomb() == false && pieces[i][j].isFire() == true) {
		                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
		                }
		                if (pieces[i][j].isShield() == true && pieces[i][j].isFire() == false) {
		                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
		                }
		                if (pieces[i][j].isBomb() == true && pieces[i][j].isFire() == false) {
		                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
		                }
		                if (pieces[i][j].isShield() == false && pieces[i][j].isBomb() == false && pieces[i][j].isFire() == false) {
		                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
		                }
                	}
                	else {
		                if (pieces[i][j].isShield() == true && pieces[i][j].isFire() == true) {
		                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
		                }
		                if (pieces[i][j].isBomb() == true && pieces[i][j].isFire() == true) {
		                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
		                }
		                if (pieces[i][j].isShield() == false && pieces[i][j].isBomb() == false && pieces[i][j].isFire() == true) {
		                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
		                }
		                if (pieces[i][j].isShield() == true && pieces[i][j].isFire() == false) {
		                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
		                }
		                if (pieces[i][j].isBomb() == true && pieces[i][j].isFire() == false) {
		                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
		                }
		                if (pieces[i][j].isShield() == false && pieces[i][j].isBomb() == false && pieces[i][j].isFire() == false) {
		                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
		                }
	           		}
	            }
            }
        }
    }

	public Board(boolean shouldBeEmpty) {
		playerturn = true;
		playerselected = false;
		playermoved = false;
		previousPiece = null;
		prevpieceX = 0;
		prevpieceY = 0;
		selectX = 8;
		selectY = 8;
		gameison = true;
		pieces = new Piece[8][8];
		moveAgain = false;
		if (shouldBeEmpty == false) {
			for (int j = 0; j < 8; j++) {
				if (j == 0) {
		            for (int i = 0; i < 8; i+=2) {
		                pieces[i][j] = new Piece(true, this, i, j, "pawn");
		            }
	            }
	            if (j == 1) {
		            for (int i = 1; i < 8; i+=2) {
		                pieces[i][j] = new Piece(true, this, i, j, "shield");
		            }
	            }
	            if (j == 2) {
		            for (int i = 0; i < 8; i+=2) {
		                pieces[i][j] = new Piece(true, this, i, j, "bomb");
		            }
	            }
	            if (j == 5) {
		            for (int i = 1; i < 8; i+=2) {
		                pieces[i][j] = new Piece(false, this, i, j, "bomb");
		            }
	            }
	            if (j == 6) {
		            for (int i = 0; i < 8; i+=2) {
		                pieces[i][j] = new Piece(false, this, i, j, "shield");
		            }
	            }
	            if (j == 7) {
		            for (int i = 1; i < 8; i+=2) {
		                pieces[i][j] = new Piece(false, this, i, j, "pawn");
		            }
	            }

	        }
	    }

	}

	public Piece pieceAt(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return null;
		}
		if (pieces[x][y] != null) {
			return pieces[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y) {
			if (x > 7 || y > 7 || x < 0 || y < 0) {
				return false;
			}
			if (pieces[x][y] != null && moveAgain == false && playermoved == false) {
				if (playerturn == pieces[x][y].isFire()) {
					return true;
				}
			}
			else if (pieces[x][y] == null) {
				if (playerselected == true && validMove(prevpieceX, prevpieceY, x, y) == true) {
					if (playermoved == false || moveAgain == true) {
						return true;
					}
					return false;
				}
			}
			return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) { 
		if (xi > 7 || yi > 7 || xf > 7 || yf > 7 || xi < 0 || yi < 0 || xf < 0 || yf < 0) {
			return false;
		}
		if (playermoved == false || moveAgain == true) {
			if (pieceAt(xi, yi).isKing()) {
				return validKingMove(xi, yi, xf, yf);
			}
			else if (playerturn == true) {
				if ((xf == xi + 1 && yf == yi + 1) || (xf == xi - 1 && yf == yi + 1) && !moveAgain) {
					if (pieces[xf][yf] == null) {
						return true;
					}
					return false;
				}
				if (xf == xi + 2 && yf == yi + 2) {
					if ((pieceAt(xf - 1, yf - 1) != null)) {
						if (pieceAt(xf - 1, yf - 1).isFire() == pieces[xi][yi].isFire()) {
							return false;
							}
						return true;
					} else {
						return false;
					}
				} else if (xf == xi - 2 && yf == yi + 2) {
					if ((pieceAt(xf + 1, yf - 1) != null)) {
						if (pieceAt(xf + 1, yf - 1).isFire() == pieces[xi][yi].isFire()) {
								return false;
								}
							return true;
						} else {
							return false;
						}
					}
			} else if (playerturn == false) {
					if ((xf == xi + 1 && yf == yi - 1) || (xf == xi - 1 && yf == yi - 1) && !moveAgain) {
						if (pieces[xf][yf] == null) {
							return true;
						}
						return false;
					}
					if (xf == xi + 2 && yf == yi - 2) {
						if ((pieceAt(xf - 1, yf + 1) != null)) {
							if (pieceAt(xf - 1, yf + 1).isFire() == pieces[xi][yi].isFire()) {
								return false;
								}
							return true;
						} else {
							return false;
						}
					} else if (xf == xi - 2 && yf == yi - 2) {
						if ((pieceAt(xf + 1, yf + 1) != null)) {
							if (pieceAt(xf + 1, yf + 1).isFire() == pieces[xi][yi].isFire()) {
									return false;
									}
								return true;
							} else {
								return false;
							}
						}
					}
				
		}
		return false;
	}

	private boolean validKingMove(int xi, int yi, int xf, int yf) {
		if (((xf == xi + 1 && yf == yi + 1) || (xf == xi - 1 && yf == yi + 1) ||
			 (xf == xi + 1 && yf == yi - 1) || (xf == xi - 1 && yf == yi - 1)) && !moveAgain) {
			if (pieces[xf][yf] == null) {
				return true;
				}
		}
		if (xf == xi + 2 && yf == yi + 2) {
			if ((pieceAt(xf - 1, yf - 1) != null)) {
				if (pieceAt(xf - 1, yf - 1).isFire() == pieces[xi][yi].isFire()) {
					return false;
					}
				return true;
			} else {
				return false;
			}
		} else if (xf == xi - 2 && yf == yi + 2) {
			if ((pieceAt(xf + 1, yf - 1) != null)) {
				if (pieceAt(xf + 1, yf - 1).isFire() == pieces[xi][yi].isFire()) {
						return false;
						}
					return true;
				} else {
					return false;
				}
			}
		else if (xf == xi + 2 && yf == yi - 2) {
			if ((pieceAt(xf - 1, yf + 1) != null)) {
				if (pieceAt(xf - 1, yf + 1).isFire() == pieces[xi][yi].isFire()) {
					return false;
					}
				return true;
			} else {
				return false;
			}
		} else if (xf == xi - 2 && yf == yi - 2) {
			if ((pieceAt(xf + 1, yf + 1) != null)) {
				if (pieceAt(xf + 1, yf + 1).isFire() == pieces[xi][yi].isFire()) {
						return false;
						}
					return true;
				} else {
					return false;
				}
			}
		return false;
	}

	public void select(int x, int y) {
		selectX = x;
		selectY = y;
		if (pieceAt(x, y) != null && moveAgain == false)  {
			playerselected = true;
			previousPiece = pieces[x][y];
			prevpieceX = x;
			prevpieceY = y;
		}
		if (pieces[x][y] == null) {
			previousPiece.move(x, y);
			playermoved = true;
			prevpieceX = x;
			prevpieceY = y;
			if (pieceAt(x, y) != null) {
				previousPiece = pieceAt(x, y);
			}
			
			System.out.println(previousPiece.hasCaptured());
			if (previousPiece.hasCaptured() && !previousPiece.isBomb()) {
				moveAgain = true;
			}
		}
	}


	public void place(Piece p, int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7 || p == null) {
			return;
			}
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("Board location out of bounds!");
			return null;
		}
		else if (pieces[x][y] == null) {
			System.out.println("This board location is empty!");
			return null;
		}
		previousPiece = pieces[x][y];
		prevpieceX = x;
		prevpieceY = y;
		pieces[x][y] = null;
		return previousPiece;
	}

	public boolean canEndTurn() {
		if (playermoved == true || (previousPiece != null && previousPiece.hasCaptured()) == true) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		if (playerturn == true) {
			previousPiece.doneCapturing();
			playerturn = false;
		}
		else if (playerturn == false) {
			previousPiece.doneCapturing();
			playerturn = true;
		}
		moveAgain = false;
		playermoved = false;
		playerselected = false;
		selectX = 8;
		selectY = 8;
	}

	public String winner() {
		int water = 0;
		int fire = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] != null) {
					if (pieces[i][j].isFire() == true) {
	            		fire += 1;
		            } else {
		            	water +=1;
		            }
            	}
            }
        }
        if (water == 0 && fire != 0) {
        	gameison = false;
        	return "Fire";
        }
        else if (water != 0 && fire == 0) {
        	gameison = false;
        	return "Water";
        }
        else if (water == 0 && fire == 0) {
        	gameison = false;
        	return "No One";
        }
		return null;
	}

}