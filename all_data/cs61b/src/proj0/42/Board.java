/** 
 *  @author Matthew Kozak
 */

public class Board {

	private int N = 8;
    private boolean[][] pieceExist = new boolean[N][N];
    private Piece[][] p = new Piece[N][N];
	private boolean shouldBeEmpty = false;
	private boolean isFireTurn = true;
	private boolean moveMade = false;
	private boolean breakValue = false;
	private boolean[][] pieceSelected = new boolean[N][N];
	private int selectedx = -1;
	private int selectedy = -1;
	private boolean hasJumped = false;



	public Board(boolean shouldBeEmpty) {
	this.shouldBeEmpty = shouldBeEmpty;
	for (int a = 0; a < N; a++) {
		for (int b = 0; b < N; b++) {
			pieceExist[a][b] = false;
			p[a][b] = null;
			pieceSelected[a][b] = false;
		}
	}
		if (this.shouldBeEmpty == false) {
			for (int a = 0; a < N; a++) {
				for (int b = 0; b < 3; b++) {
            		if ((a + b) % 2 == 0) {
            			pieceExist[a][b] = true;
            		}
            		else {
            			pieceExist[a][b] = false;
            		}
            	}
            }
			for (int c = 0; c < N; c++) {
				for (int d = N-3; d < N; d++) {
            		if ((c + d) % 2 == 0) {
            			pieceExist[c][d] = true;
            		}
            		else {
            			pieceExist[c][d] = false;
            		}
            	}
            }
			for (int i = 0; i < N; i++) {
		        for (int j = 0; j < N; j++) {
				    if (pieceExist[i][j] && (j == 0)) {
				    	p[i][j] = new Piece(true, this, i, j, "pawn");
				    	}
					if (pieceExist[i][j] && (j == 1)) {
				    	p[i][j] = new Piece(true, this, i, j, "shield");
				    	}
				    if (pieceExist[i][j] && (j == 2)) {
				    	p[i][j] = new Piece(true, this, i, j, "bomb");
				    	}
				    if (pieceExist[i][j] && (j == 5)) {
				    	p[i][j] = new Piece(false, this, i, j, "bomb");
				    	}
				    if (pieceExist[i][j] && (j == 6)) {
				    	p[i][j] = new Piece(false, this, i, j, "shield");
				    	}
				    if (pieceExist[i][j] && (j == 7)) {
				    	p[i][j] = new Piece(false, this, i, j, "pawn");
				    }
				}
			}
		}
	}


	private boolean outOfBounds(int x, int y) {
		if (x >= N || y >= N) {
			System.out.println("Out of bounds!");
			return true;
		}
		else {
			return false;
		}
	}


	public void place(Piece piece, int x, int y) {
		if (outOfBounds(x, y) == true) {
			return;
		}
		else if (p[x][y] == null) {
			return;
		}
		p[x][y] = piece;
	}


	public Piece pieceAt(int x, int y) {
		if (outOfBounds(x, y) == true) {
			return null;
		}
		else if (pieceExist[x][y] == true) {
			return p[x][y];
		}
		else {
			return null;
		}
		
	}


	public Piece remove(int x, int y) {
		if (outOfBounds(x, y) == true) {
			return null;
		}
		else if (p[x][y] == null) {
			System.out.printf("There is no piece at location: %d, %d.", x, y);
			return null;
		}
		else {
			pieceExist[x][y] = false;
			return p[x][y];
		}
	}


    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }


    private void drawPieces(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
			    if (p[i][j] != null) {
			    	if (p[i][j].isKing() == false) {
				    	if (p[i][j].isFire() == true) {
				    		if (p[i][j].isBomb() == true) {
				    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
				    		}
				    		else if (p[i][j].isShield() == true) {
				    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
				    		}
				    		else {
				    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
				    		}
				    	}
				    	else {
				    		if (p[i][j].isBomb() == true) {
				    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
				    		}
				    		else if (p[i][j].isShield() == true) {
				    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
				    		}
				    		else {
				    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
				    		}
				    	}
			    	}
			    	else {
				    	if (p[i][j].isFire() == true) {
				    		if (p[i][j].isBomb() == true) {
				    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
				    		}
				    		else if (p[i][j].isShield() == true) {
				    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
				    		}
				    		else {
				    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
				    		}
				    	}
				    	else {
				    		if (p[i][j].isBomb() == true) {
				    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
				    		}
				    		else if (p[i][j].isShield() == true) {
				    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
				    		}
				    		else {
				    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
				    		}
				    	}
			    	}			    		
			    }
			}
		}
	}


	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xi < 0 || yi < 0 || xf < 0 || yf < 0 || xi >= N || xf >= N || yi >= N || yf >= N) {
			return false;
		}
		if (p[xi][yi] == null) {
			return false;
		}

		Piece myPiece;
		myPiece = pieceAt(xi, yi);

		if (p[xf][yf] != null) {
			return false;
		}
		if (p[xi][yi] != null) {
			if (isFireTurn == true && myPiece.isFire()) {
				if (myPiece.isKing() == false || myPiece.isKing() == true) {
					if (xi+1 < N && yi+1 < N) {
						if ((p[xi+1][yi+1] != null) && (xf == xi+2) && (yf == yi+2)) {
							if (pieceAt(xi+1, yi+1).isFire() == false) {
								if (myPiece.isBomb() == true) {
									if (xf+1 < N && yf+1 < N) {
										if (p[xf+1][yf+1] != null) {
											if (pieceAt(xf+1, yf+1).isFire() == false && pieceAt(xf+1, yf+1).isShield() == false) {
												remove(xf+1, yf+1);
												p[xf+1][yf+1] = null;
											}
										}
									}
									if (xf-1 >= 0 && yf+1 < N) {
										if (p[xf-1][yf+1] != null) {
											if (pieceAt(xf-1, yf+1).isFire() == false && pieceAt(xf-1, yf+1).isShield() == false) {
												remove(xf-1, yf+1);
												p[xf-1][yf+1] = null;
											}
										}
									}
									if (xf+1 < N && yf-1 >= 0) {
										if (p[xf+1][yf-1] != null) {
											if (pieceAt(xf+1, yf-1).isFire() == false && pieceAt(xf+1, yf-1).isShield() == false) {
												remove(xf+1, yf-1);
												p[xf+1][yf-1] = null;
											}
										}
									}
								}
								hasJumped = true;
								myPiece.hasCaptured();
								remove(xi+1, yi+1);
								p[xi+1][yi+1] = null;
								return true;
							}
							else {
								return false;
							}
						}
						else if ((xf == xi+1) && (yf == yi+1)) {
							return true;
						}
					}
					if (xi-1 >= 0 && yi+1 < N) {
						if ((p[xi-1][yi+1] != null) && (xf == xi-2) && (yf == yi+2)) {
							if (pieceAt(xi-1, yi+1).isFire() == false) {
								if (myPiece.isBomb() == true) {
									if (xf+1 < N && yf+1 < N) {
										if (p[xf+1][yf+1] != null) {
											if (pieceAt(xf+1, yf+1).isFire() == false && pieceAt(xf+1, yf+1).isShield() == false) {
												remove(xf+1, yf+1);
												p[xf+1][yf+1] = null;
											}
										}
									}
									if (xf-1 >= 0 && yf+1 < N) {
										if (p[xf-1][yf+1] != null) {
											if (pieceAt(xf-1, yf+1).isFire() == false && pieceAt(xf-1, yf+1).isShield() == false) {
												remove(xf-1, yf+1);
												p[xf-1][yf+1] = null;
											}
										}
									}
									if (xf-1 >= 0 && yf-1 >= 0) {
										if (p[xf-1][yf-1] != null) {
											if (pieceAt(xf-1, yf-1).isFire() == false && pieceAt(xf-1, yf-1).isShield() == false) {
												remove(xf-1, yf-1);
												p[xf-1][yf-1] = null;
											}
										}
									}
								}
								hasJumped = true;
								myPiece.hasCaptured();
								remove(xi-1, yi+1);
								p[xi-1][yi+1] = null;
								return true;
							}
							else {
								return false;
							}
						}
						else if ((xf == xi-1) && (yf == yi+1)) {
							return true;
						}
					}
				}
				if (myPiece.isKing() == true) {
					if (xi+1 < N && yi-1 >= 0) {
						if ((p[xi+1][yi-1] != null) && (xf == xi+2) && (yf == yi-2)) {
							if (pieceAt(xi+1, yi-1).isFire() == false) {
								if (myPiece.isBomb() == true) {
									if (xf+1 < N && yf+1 < N) {
										if (p[xf+1][yf+1] != null) {
											if (pieceAt(xf+1, yf+1).isFire() == false && pieceAt(xf+1, yf+1).isShield() == false) {
												remove(xf+1, yf+1);
												p[xf+1][yf+1] = null;
											}
										}
									}
									if (xf+1 < N && yf-1 >= 0) {
										if (p[xf+1][yf-1] != null) {
											if (pieceAt(xf+1, yf-1).isFire() == false && pieceAt(xf+1, yf-1).isShield() == false) {
												remove(xf+1, yf-1);
												p[xf+1][yf-1] = null;
											}
										}
									}
									if (xf-1 >= 0 && yf-1 >= 0) {
										if (p[xf-1][yf-1] != null) {
											if (pieceAt(xf-1, yf-1).isFire() == false && pieceAt(xf-1, yf-1).isShield() == false) {
												remove(xf-1, yf-1);
												p[xf-1][yf-1] = null;
											}
										}
									}
								}
								hasJumped = true;
								myPiece.hasCaptured();
								remove(xi+1, yi-1);
								p[xi+1][yi-1] = null;
								return true;
							}
							else {
								return false;
							}
						}
						else if ((xf == xi+1) && (yf == yi-1)) {
							return true;
						}
					}
					if (xi-1 >= 0 && yi-1 >= 0) {
						if ((p[xi-1][yi-1] != null) && (xf == xi-2) && (yf == yi-2)) {
							if (pieceAt(xi-1, yi-1).isFire() == false) {
								if (myPiece.isBomb() == true) {
									if (xf-1 >= 0 && yf-1 >= 0) {
										if (p[xf+1][yf+1] != null) {
											if (pieceAt(xf-1, yf-1).isFire() == false && pieceAt(xf-1, yf-1).isShield() == false) {
												remove(xf-1, yf-1);
												p[xf-1][yf-1] = null;
											}
										}
									}
									if (xf-1 >= 0 && yf+1 < N) {
										if (p[xf-1][yf+1] != null) {
											if (pieceAt(xf-1, yf+1).isFire() == false && pieceAt(xf-1, yf+1).isShield() == false) {
												remove(xf-1, yf+1);
												p[xf-1][yf+1] = null;
											}
										}
									}
									if (xf+1 < N && yf-1 >= 0) {
										if (p[xf+1][yf-1] != null) {
											if (pieceAt(xf+1, yf-1).isFire() == false && pieceAt(xf+1, yf-1).isShield() == false) {
												remove(xf+1, yf-1);
												p[xf+1][yf-1] = null;
											}
										}
									}
								}
								hasJumped = true;
								myPiece.hasCaptured();
								remove(xi-1, yi-1);
								p[xi-1][yi-1] = null;
								return true;
							}
							else {
								return false;
							}
						}
						else if ((xf == xi-1) && (yf == yi-1)) {
							return true;
						}
					}
				}
			}
			else if (isFireTurn == false && myPiece.isFire() == false) {
				if (myPiece.isKing() == false || myPiece.isKing() == true) {
					if (xi+1 < N && yi-1 >= 0) {
						if ((p[xi+1][yi-1] != null) && (xf == xi+2) && (yf == yi-2)) {
							if (pieceAt(xi+1, yi-1).isFire() == true) {
								if (myPiece.isBomb() == true) {
									if (xf+1 < N && yf+1 < N) {
										if (p[xf+1][yf+1] != null) {
											if (pieceAt(xf+1, yf+1).isFire() == true && pieceAt(xf+1, yf+1).isShield() == false) {
												remove(xf+1, yf+1);
												p[xf+1][yf+1] = null;
											}
										}
									}
									if (xf+1 < N && yf-1 >= 0) {
										if (p[xf+1][yf-1] != null) {
											if (pieceAt(xf+1, yf-1).isFire() == true && pieceAt(xf+1, yf-1).isShield() == false) {
												remove(xf+1, yf-1);
												p[xf+1][yf-1] = null;
											}
										}
									}
									if (xf-1 >= 0 && yf-1 >= 0) {
										if (p[xf-1][yf-1] != null) {
											if (pieceAt(xf-1, yf-1).isFire() == true && pieceAt(xf-1, yf-1).isShield() == false) {
												remove(xf-1, yf-1);
												p[xf-1][yf-1] = null;
											}
										}
									}
								}
								hasJumped = true;
								myPiece.hasCaptured();
								remove(xi+1, yi-1);
								p[xi+1][yi-1] = null;
								return true;
							}
							else {
								return false;
							}
						}
						else if ((xf == xi+1) && (yf == yi-1)) {
							return true;
						}
					}
					if (xi-1 >= 0 && yi-1 >= 0) {
						if ((p[xi-1][yi-1] != null) && (xf == xi-2) && (yf == yi-2)) {
							if (pieceAt(xi-1, yi-1).isFire() == true) {
								if (myPiece.isBomb() == true) {
									if (xf-1 >= 0 && yf-1 >= 0) {
										if (p[xf-1][yf-1] != null) {
											if (pieceAt(xf-1, yf-1).isFire() == true && pieceAt(xf-1, yf-1).isShield() == false) {
												remove(xf-1, yf-1);
												p[xf-1][yf-1] = null;
											}
										}
									}
									if (xf-1 >= 0 && yf+1 < N) {
										if (p[xf-1][yf+1] != null) {
											if (pieceAt(xf-1, yf+1).isFire() == true && pieceAt(xf-1, yf+1).isShield() == false) {
												remove(xf-1, yf+1);
												p[xf-1][yf+1] = null;
											}
										}
									}
									if (xf+1 < N && yf-1 >= 0) {
										if (p[xf+1][yf-1] != null) {
											if (pieceAt(xf+1, yf-1).isFire() == true && pieceAt(xf+1, yf-1).isShield() == false) {
												remove(xf+1, yf-1);
												p[xf+1][yf-1] = null;
											}
										}
									}
								}
								hasJumped = true;
								myPiece.hasCaptured();
								remove(xi-1, yi-1);
								p[xi-1][yi-1] = null;
								return true;
							}
							else {
								return false;
							}
						}
						else if ((xf == xi-1) && (yf == yi-1)) {
							return true;
						}
					}
				}
				if (myPiece.isKing() == true) {
					if (xi+1 < N && yi+1 < N) {
						if ((p[xi+1][yi+1] != null) && (xf == xi+2) && (yf == yi+2)) {
							if (pieceAt(xi+1, yi+1).isFire() == true) {
								if (myPiece.isBomb() == true) {
									if (xf+1 < N && yf+1 < N) {
										if (p[xf+1][yf+1] != null) {
											if (pieceAt(xf+1, yf+1).isFire() == true && pieceAt(xf+1, yf+1).isShield() == false) {
												remove(xf+1, yf+1);
												p[xf+1][yf+1] = null;
											}
										}
									}
									if (xf+1 < N && yf-1 >= 0) {
										if (p[xf+1][yf-1] != null) {
											if (pieceAt(xf+1, yf-1).isFire() == true && pieceAt(xf+1, yf-1).isShield() == false) {
												remove(xf+1, yf-1);
												p[xf+1][yf-1] = null;
											}
										}
									}
									if (xf-1 >= 0 && yf+1 < N) {
										if (p[xf-1][yf+1] != null) {
											if (pieceAt(xf-1, yf+1).isFire() == true && pieceAt(xf-1, yf+1).isShield() == false) {
												remove(xf-1, yf+1);
												p[xf-1][yf+1] = null;
											}
										}
									}
								}
								hasJumped = true;
								myPiece.hasCaptured();
								remove(xi+1, yi+1);
								p[xi+1][yi+1] = null;
								return true;
							}
							else {
								return false;
							}
						}
						else if ((xf == xi+1) && (yf == yi+1)) {
							return true;
						}
					}
					if (xi-1 >= 0 && yi+1 < N) {
						if ((p[xi-1][yi+1] != null) && (xf == xi-2) && (yf == yi+2)) {
							if (pieceAt(xi-1, yi+1).isFire() == true) {
								if (myPiece.isBomb() == true) {
									if (xf+1 < N && yf+1 < N) {
										if (p[xf+1][yf+1] != null) {
											if (pieceAt(xf+1, yf+1).isFire() == true && pieceAt(xf+1, yf+1).isShield() == false) {
												remove(xf+1, yf+1);
												p[xf+1][yf+1] = null;
											}
										}
									}
									if (xf-1 >= 0 && yf+1 < N) {
										if (p[xf-1][yf+1] != null) {
											if (pieceAt(xf-1, yf+1).isFire() == true && pieceAt(xf-1, yf+1).isShield() == false) {
												remove(xf-1, yf+1);
												p[xf-1][yf+1] = null;
											}
										}
									}
									if (xf-1 >= 0 && yf-1 >= 0) {
										if (p[xf-1][yf-1] != null) {
											if (pieceAt(xf-1, yf-1).isFire() == true && pieceAt(xf-1, yf-1).isShield() == false) {
												remove(xf-1, yf-1);
												p[xf-1][yf-1] = null;
											}
										}
									}
								}
								hasJumped = true;
								myPiece.hasCaptured();
								remove(xi-1, yi+1);
								p[xi-1][yi+1] = null;
								return true;
							}
							else {
								return false;
							}
						}
						else if ((xf == xi-1) && (yf == yi+1)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}


	public void endTurn() {
		if (isFireTurn == true) {
			isFireTurn = false;
		}
		else if (isFireTurn == false) {
			isFireTurn = true;
		}
		moveMade = false;
	}


	public void select(int x, int y) {
		drawPieces(N);
		//StdDrawPlus.show(100);

		while (moveMade == false) {
			if (StdDrawPlus.mousePressed()) {
				if (p[(int) StdDrawPlus.mouseX()][(int) StdDrawPlus.mouseY()] != null) {
					if (canSelect((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY())) {
						pieceSelected[(int) StdDrawPlus.mouseX()][(int) StdDrawPlus.mouseY()] = true;
	 					//StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
						//StdDrawPlus.filledSquare(x + .5, y + .5, .5);
						//StdDrawPlus.show(100);
					}
				}
				else if (canSelect((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY()) == true) {
 					//StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					//StdDrawPlus.filledSquare(x + .5, y + .5, .5);
					//StdDrawPlus.show(100);
				}
				drawBoard(N);
				drawPieces(N);
				StdDrawPlus.show(100);
				select((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY());
			}
			if (p[x][y] != null) {
	            	pieceSelected[x][y] = true;
	            	selectedx = x;
	            	selectedy = y;
            }
 				//StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				//StdDrawPlus.filledSquare(x + .5, y + .5, .5);
				drawPieces(N);
				StdDrawPlus.show(100);
            	if (validMove(selectedx, selectedy, x, y) == true) {
            		p[x][y] = pieceAt(selectedx, selectedy);
            		pieceAt(selectedx, selectedy).move(x, y);
            		pieceSelected[selectedx][selectedy] = false;
            		pieceExist[selectedx][selectedy] = false;
            		pieceExist[x][y] = true;
            		p[selectedx][selectedy] = null;

            		if (hasJumped == true && p[x][y].isBomb()) {
            			p[x][y] = null;
            			pieceExist[x][y] = false;
            			hasJumped = false;
            		}

/** My attempt at allowing the user to perform a double jump if desired

            		else if (hasJumped == true && p[x][y].isBomb() == false) {
            			if (p[x][y].isFire()) {
            				if (x+1 < N && y+1 < N) {
            					if (p[x+1][y+1] != null) {
            						if (pieceAt(x+1, y+1).isFire() == false) {
            							if (StdDrawPlus.isSpacePressed() == true) {
            								hasJumped = false;
            							}
            							else if (StdDrawPlus.mousePressed()) {
								           	select((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY());
						                }
            						}
            					}
            				}
            				if (x-1 >= 0 && y+1 < N) {
            					if (p[x-1][y+1] != null) {
            						if (pieceAt(x-1, y+1).isFire() == false) {
            							if (StdDrawPlus.isSpacePressed() == true) {
            								hasJumped = false;
            							}
            							else if (StdDrawPlus.mousePressed()) {
								           	select((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY());
						                }
						            }
            					}
            				}
            				if (p[x][y].isKing()) {
	            				if (x+1 < N && y-1 >= 0) {
	            					if (p[x+1][y-1] != null) {
	            						if (pieceAt(x+1, y-1).isFire() == false) {
	            							if (StdDrawPlus.isSpacePressed() == true) {
	            								hasJumped = false;
	            							}
	            							else if (StdDrawPlus.mousePressed()) {
									           	select((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY());
							                }
	            						}
	            					}
	            				}
	            				if (x-1 >= 0 && y-1 >= 0) {
	            					if (p[x-1][y-1] != null) {
	            						if (pieceAt(x-1, y-1).isFire() == false) {
	            							if (StdDrawPlus.isSpacePressed() == true) {
	            								hasJumped = false;
	            							}
	            							else if (StdDrawPlus.mousePressed()) {
									           	select((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY());
							                }
							            }
	            					}
	            				}
            				}
            			}
						else if (p[x][y].isFire() == false) {
            				if (x+1 < N && y-1 >= 0) {
            					if (p[x+1][y-1] != null) {
            						if (pieceAt(x+1, y-1).isFire() == false) {
            							if (StdDrawPlus.isSpacePressed() == true) {
            								hasJumped = false;
            							}
            							else if (StdDrawPlus.mousePressed()) {
								           	select((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY());
						                }
            						}
            					}
            				}
            				if (x-1 >= 0 && y-1 >= 0) {
            					if (p[x-1][y-1] != null) {
            						if (pieceAt(x-1, y-1).isFire() == false) {
            							if (StdDrawPlus.isSpacePressed() == true) {
            								hasJumped = false;
            							}
            							else if (StdDrawPlus.mousePressed()) {
								           	select((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY());
						                }
						            }
            					}
            				}
            				if (p[x][y].isKing()) {
            					if (p[x+1][y+1] != null) {
            						if (pieceAt(x+1, y+1).isFire() == false) {
            							if (StdDrawPlus.isSpacePressed() == true) {
            								hasJumped = false;
            							}
            							else if (StdDrawPlus.mousePressed()) {
								           	select((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY());
						                }
            						}
            					}
            					if (x-1 >= 0 && y+1 < N) {
            						if (p[x-1][y+1] != null) {
            							if (pieceAt(x-1, y+1).isFire() == false) {
            								if (StdDrawPlus.isSpacePressed() == true) {
            									hasJumped = false;
            								}
            								else if (StdDrawPlus.mousePressed()) {
								           		select((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY());
						                	}
						            	}
            						}
            					}
            				}
            			}
            		}*/


 					//StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					//StdDrawPlus.filledSquare(selectedx + .5, selectedy + .5, .5);
					//StdDrawPlus.show(1);
	            	drawBoard(N);
					drawPieces(N);
            		while (StdDrawPlus.isSpacePressed() == false) {
 						//StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
						//StdDrawPlus.filledSquare(x + .5, y + .5, .5);
						drawPieces(N);
						StdDrawPlus.show(1);
            		}
						drawBoard(N);
						drawPieces(N);
						StdDrawPlus.show(1);
						moveMade = true;

            	}
	            		drawBoard(N);
						drawPieces(N);
						StdDrawPlus.show(1);
            }
            return;
		}


	public boolean canEndTurn() {
		if (moveMade == true) {
			return true;
		}
		else {
			return false;
		}
	}


	public boolean canSelect(int x, int y) {
		if (p[x][y] != null) {
			Piece mypiece;
			mypiece = pieceAt(x, y);
			if (isFireTurn == true) {
				if (mypiece.isFire() == true) {
					return true;
				}
			}
			else if (isFireTurn == false) {
				if (mypiece.isFire() == false) {
					return true;
				}
			}
		}
		return false;
	}


	public String winner() {
		int firecount = 0;
		int watercount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (p[i][j] != null && p[i][j].isFire() == true) {
					firecount += 1;
				}
				else if (p[i][j] != null && p[i][j].isFire() == false) {
					watercount += 1;
				}
			}
		}
		if (firecount == 0 && watercount == 0) {
			return "No one";
		}
		else if (watercount == 0) {
			return "Fire";
		}
		else if (firecount == 0) {
			return "Water";
		}
		else {
			return null;
		}
	}

    public static void main (String args[]) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board b = new Board(false);
        double valx = 0;
        double valy = 0;
        String result = "";

        while(b.winner() == null) {
            b.drawBoard(N);
			b.drawPieces(N);
			while (b.moveMade == false) {
	            if (StdDrawPlus.mousePressed()) {
	    			valx = StdDrawPlus.mouseX();
	        		valy = StdDrawPlus.mouseY();
		           	b.select((int) valx, (int) valy);
                }
            }

            StdDrawPlus.show(100);
            b.endTurn();
            result = b.winner();
    	}
    	System.out.printf("The Winner Is %s", result);
    	StdDrawPlus.show(1000);
    	System.exit(0);
	}
}