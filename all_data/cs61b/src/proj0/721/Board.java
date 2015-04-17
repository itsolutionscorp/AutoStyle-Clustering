public class Board {
	private boolean empty;
	private Piece[][] pieces;
	private boolean player;
	private boolean hasMoved = false;
	private boolean selected;
	private Piece prevPiece;
	private int prevXCord;
	private int prevYCord;
	private Piece thePiece; 
	private int firePiecesLeft;
	private int waterPiecesLeft;
	
	private static void drawBoard(int maxLength) {
	 	for (int xCord = 0; xCord < maxLength; xCord += 1) {
	 		for (int yCord = 0; yCord < maxLength; yCord += 1) {
	 			if ((xCord + yCord) % 2 == 0) {
	 				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	 			}
	 			else {
	 				StdDrawPlus.setPenColor(StdDrawPlus.RED);
	 			}
	 			StdDrawPlus.filledSquare(xCord + .5, yCord + .5, .5);
	 			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	 		}
	 	}
	}

	private void drawPieces(int maxLength) {
		for (int xCord = 0; xCord < maxLength; xCord += 1) {
	 		for (int yCord = 0; yCord < maxLength; yCord += 1) {
	 			Piece p = pieces[xCord][yCord];
	 			if (p != null) {
		 			String str = "";
		 			if (!p.isKing()) {
			            if (p.isShield()) {
			                if (p.isFire()) {
			                    str = "img/shield-fire.png";
			                }
			                else {
			                    str = "img/shield-water.png";
			                }
			            }
			            else if (p.isBomb()) {
			                if (p.isFire()) {
			                    str = "img/bomb-fire.png";
			                }
			                else {
			                    str = "img/bomb-water.png";
			                }
			            }

			            else {
			                if (p.isFire()) {
			                    str = "img/pawn-fire.png";
			                }
			                else {
			                    str = "img/pawn-water.png";
			                }
			            }
			        }
			        else if (p.isKing()) {
			            if (p.isShield()) {
			                if (p.isFire()) {
			                    str = "img/shield-fire-crowned.png";
			                }
			                else {
			                    str = "img/shield-water-crowned.png";
			                }
			            }
			            else if (p.isBomb()) {
			                if (p.isFire()) {
			                    str = "img/bomb-fire-crowned.png";
			                }
			                else {
			                    str = "img/bomb-water-crowned.png";
			                }
			            }
			            else {
			                if (p.isFire()) {
			                    str = "img/pawn-fire-crowned.png";
			                }
			                else {
			                    str = "img/pawn-water-crowned.png";
			                }
			            }
			        }
			        StdDrawPlus.picture(xCord + .5, yCord + .5, str, 1, 1);
			    }
			}
		}
	}

	public static void main(String args[]) {
		int size = 8;
		StdDrawPlus.setXscale(0, size);
		StdDrawPlus.setYscale(0, size);	
		Board b = new Board(false);
		b.drawBoard(size);
		b.drawPieces(size);
		while (b.winner() == null) {
			if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int)x, (int)y)) {
                	b.drawBoard(size);
                	StdDrawPlus.filledSquare((int)x + .5, (int)y + .5, .5);
                	b.select((int)x, (int)y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }
            b.drawPieces(size);
            StdDrawPlus.show(100);
		}
	}

	public Board(boolean shouldBeEmpty) {
		empty = shouldBeEmpty;
		player = true;
		selected = false;
		pieces = new Piece[8][8];
		if (!empty) {
			for (int xCord = 0; xCord < 8; xCord += 2) {
				pieces[xCord][0] = new Piece(true, this, xCord, 0, "pawn");
			}
			for (int xCord = 0; xCord < 8; xCord += 2) {
				pieces[xCord][2] = new Piece(true, this, xCord, 2, "bomb");
			}
			for (int xCord = 0; xCord < 8; xCord += 2) {
				pieces[xCord][6] = new Piece(false, this, xCord, 6, "shield");
			}
			for (int xCord = 1; xCord < 8; xCord += 2) {
				pieces[xCord][1] = new Piece(true, this, xCord, 1, "shield");
			}
			for (int xCord = 1; xCord < 8; xCord += 2) {
				pieces[xCord][5] = new Piece(false, this, xCord, 5, "bomb");
			}
			for (int xCord = 1; xCord < 8; xCord += 2) {
				pieces[xCord][7] = new Piece(false, this, xCord, 7, "pawn");
			}
			firePiecesLeft = 12;
			waterPiecesLeft = 12;
		}
		else {
			firePiecesLeft = 0;
			waterPiecesLeft = 0;
		}	
	}

	public Piece pieceAt(int x, int y) {
		if ((x < 8) && (y < 8) && (x >= 0) && (y >= 0)) {
			if (pieces[x][y] != null) {
				return pieces[x][y];
			}
			else {
				return null;
			}
		}
		return null;
	}
			
	public void place(Piece p, int x, int y) {
		if ((x < 8) && (y < 8) && (x >= 0) && (y >= 0) && (p != null)) {
			pieces[x][y] = p;
		}
		if (p != null && p.isFire()) {
			firePiecesLeft += 1;
		}
		else {
			waterPiecesLeft += 1;
		}
	}

	public Piece remove(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			System.out.println("Input is out of bounds!");
			return null;
		}
		else if (pieces[x][y] == null) {
			System.out.println("There is no piece to remove!");
			return null;
		}
		Piece removed = pieces[x][y];
		pieces[x][y] = null;
		if (removed != null && removed.isFire()) {
			firePiecesLeft -= 1;
		}
		else {
			waterPiecesLeft -= 1;
		}
		return removed;
	}
	private boolean validMove(int prevX, int prevY, int x, int y) {
		Piece p = pieces[prevX][prevY];
		int xAvg = (x + prevX) / 2;
		int yAvg = (y + prevY) / 2;

		Piece opponentPiece = pieceAt(xAvg, yAvg);
		if (p != null && p.isFire() == true) {
			if (x >= 0 && y >= 0 && x < 8 && y < 8) {
				if ((x == prevX + 1) && (y == prevY + 1) && (pieces[x][y] == null) && (!p.isKing()) && (!p.hasCaptured())) {
					return true;
				}
				else if ((x == prevX - 1) && (y == prevY + 1) && (pieces[x][y] == null) && (!p.isKing()) && (!p.hasCaptured())) {
					return true;
				}
				else if ((x == prevX + 2) && (y == prevY + 2) && (opponentPiece != null) && (opponentPiece.isFire() == false) && (pieces[x][y] == null)) {
					return true;
				}
				else if ((x == prevX - 2) && (y == prevY + 2) && (opponentPiece != null) && (opponentPiece.isFire() == false) && (pieces[x][y] == null)) {
					return true;
				}
				else if (p.isKing() == true) {
					if ((x == prevX - 1) && (y == prevY - 1) && (pieces[x][y] == null) && (!p.hasCaptured())) {
						return true;
					}
					else if ((x == prevX + 1) && (y == prevY - 1) && (pieces[x][y] == null) && (!p.hasCaptured())) {
						return true;
					}
					else if ((x == prevX + 2) && (y == prevY - 2) && (opponentPiece != null) && (opponentPiece.isFire() == false) && (pieces[x][y] == null)) {
						return true;
					}
					else if ((x == prevX - 2) && (y == prevY - 2) && (opponentPiece != null) && (opponentPiece.isFire() == false) && (pieces[x][y] == null)) {
						return true;
					}
				}
			}
		}
		else if (p != null && p.isFire() == false) {
			if (x >= 0 && y >= 0 && x < 8 && y < 8) {
				if ((x == prevX + 1) && (y == prevY - 1) && (pieces[x][y] == null) && (!p.isKing()) && (!p.hasCaptured())) {
					return true;
				}
				else if ((x == prevX - 1) && (y == prevY - 1) && (pieces[x][y] == null) && (!p.isKing()) && (!p.hasCaptured())) {
					return true;
				}
				else if ((x == prevX + 2) && (y == prevY - 2) && (opponentPiece != null) && (opponentPiece.isFire() == true) && (pieces[x][y] == null)) {
					return true;
				}
				else if ((x == prevX - 2) && (y == prevY - 2) && (opponentPiece != null) && (opponentPiece.isFire() == true) && (pieces[x][y] == null)) {
					return true;
				}
				else if (p.isKing() == true) {
					if ((x == prevX - 1) && (y == prevY + 1) && (pieces[x][y] == null) && (!p.hasCaptured())) {
						return true;
					}
					else if ((x == prevX + 1) && (y == prevY + 1) && (pieces[x][y] == null) && (!p.hasCaptured())) {
						return true;
					}
					else if ((x == prevX + 2) && (y == prevY + 2) && (opponentPiece != null) && (opponentPiece.isFire() == true) && (pieces[x][y] == null)) {
						return true;
					}
					else if ((x == prevX - 2) && (y == prevY + 2) && (opponentPiece != null) && (opponentPiece.isFire() == true) && (pieces[x][y] == null)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean canSelect(int x, int y) {
		if (x < 8 && y < 8 && x >= 0 && y >= 0) {
			thePiece = pieces[x][y];
			if (player == true) {
				if (thePiece != null) {
					if (thePiece.isFire() == true) {
						if (selected == false) {
							return true;
						}
						else if (selected == true && hasMoved == false) {
							return true;
						}
					}
				}
				else {
					if (selected && validMove(prevXCord, prevYCord, x, y) == true && hasMoved == false) {
						return true;
					}
					else if (selected && validMove(prevXCord, prevYCord, x, y) == true && hasMoved == true && prevPiece.hasCaptured() == true) {
						return true;
					}
				}
			}
			else {
				if (thePiece != null) {
					if (thePiece.isFire() == false) { 
						if (selected == false) {
							return true;
						}
						else if (selected == true && hasMoved == false) {
							return true;
						}
					}
				}
				else {
					if (selected && validMove(prevXCord, prevYCord, x, y) == true && hasMoved == false) {
						return true;
					}
					else if (selected && validMove(prevXCord, prevYCord, x, y) == true && hasMoved == true && prevPiece.hasCaptured() == true) {
						return true;
					}
				}
			}
		}	
		return false;
	}

	public void select(int x, int y) {
		if (pieces[x][y] != null) {
			selected = true;
			prevPiece = pieces[x][y];
			prevXCord = x;
			prevYCord = y;
		}	
		else if (pieces[x][y] == null && prevPiece != null) {
			prevXCord = x;
			prevYCord = y;
			prevPiece.move(x, y);
			hasMoved = true;
		}
	}

	public String winner() {
		if (firePiecesLeft == 0 && waterPiecesLeft == 0) {
			return "No one";
		}
		else if (firePiecesLeft == 0 && waterPiecesLeft > 0) {
			return "Water";
		}
		else if (firePiecesLeft > 0 && waterPiecesLeft == 0) {
			return "Fire";
		}
		else {
			return null;
		}
	}
	public boolean canEndTurn() {
		return hasMoved;
	}
	public void endTurn() {
		if (prevPiece != null) {
			prevPiece.doneCapturing();
		}
		selected = false;
		hasMoved = false;
		prevPiece = null;
		player = !player;
	}

}


