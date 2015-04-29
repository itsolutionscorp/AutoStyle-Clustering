public class Board {
	private Piece[][] checkers = new Piece[8][8];
	private int turn = 0;
	private Piece selected;
	private boolean moved = false;
	private boolean winner = false;
	private boolean captured = false;
	private boolean cangrab = false;

	private void drawBoard(int N) {
		for (int i = 0; i < N; i = i + 1) {
			for (int j = 0; j < N; j = j + 1) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					if ((selected == checkers[i][j]) && (checkers[i][j] != null)) {
						StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					}
				} else {				  
					StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				if (checkers[i][j] != null) {
					if (checkers[i][j].isFire()) {
						if (checkers[i][j].isBomb()) {
							if (checkers[i][j].isKing()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
							}
						} else if (checkers[i][j].isShield()) {
							if (checkers[i][j].isKing()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
							}
						} else {
							if (checkers[i][j].isKing()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
							}
						}
					} else {
						if (checkers[i][j].isBomb()) {
							if (checkers[i][j].isKing()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1);
							} else {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
							}
						} else if (checkers[i][j].isShield()) {
							if (checkers[i][j].isKing()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1);
							} else {
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
							}
						} else {
							if (checkers[i][j].isKing()) {
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1);
							} else {
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
							}
						}
					}
				}
			}
		}
	}

	public static void main (String args[]) {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board checkerboard = new Board(false);

        while (checkerboard.winner != true) {
        	int x = (int)StdDrawPlus.mouseX();
            int y = (int)StdDrawPlus.mouseY();
            if (StdDrawPlus.mousePressed()) { 
	            if (checkerboard.canSelect(x, y) == true) {
	            	System.out.println("select piece/spot");
	            	checkerboard.select(x, y);
	            }
            }
        	// if (StdDrawPlus.isSpacePressed() == true) {
         //    	System.out.println("space pressed");
            	if (checkerboard.canEndTurn() == true) {
            		System.out.println("endturn");
            		checkerboard.endTurn();
            	}
            // }
            checkerboard.drawBoard(8);
            StdDrawPlus.show(10);
        }
 	}

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			for (int x = 0; x < 8; x = x + 1) {
				if (x % 2 == 0) {
					checkers[x][0] = new Piece(true, this, x, 0, "pawn");
				} else {
					checkers[x][7] = new Piece(false, this, x, 7, "pawn");
				}
			}
			for (int x = 0; x < 8; x = x + 1) {
				if (x % 2 == 0) {
					checkers[x][6] = new Piece(false, this, x, 6, "shield");
				} else {
					checkers[x][1] = new Piece(true, this, x, 1, "shield");
				}
			}
			for (int x = 0; x < 8; x = x + 1) {
				if (x % 2 == 0) {
					checkers[x][2] = new Piece(true, this, x, 2, "bomb");
				} else {
					checkers[x][5] = new Piece(false, this, x, 5, "bomb");
				}
			}
		}
		drawBoard(8);
	}

	public Piece pieceAt(int x, int y) {
		if ((x >= 8) && (x < 0) && (y >= 8) && (y < 0)) {
			return null;
		}
		return checkers[x][y];
	}

	public boolean canSelect(int x, int y) {
		int ex = getxandy(selected)[0];
		int why = getxandy(selected)[1];
		if (pieceAt(x, y) != null) {
			if (checkers[x][y].side() == turn) {
				if ((moved == false) && (selected != null)) {
					System.out.println("piece selected");
					return true;
				} else if (selected == null) {
					System.out.println("piece has not been selected");
					return true;
				}
			} else {
				return false;
			}
		} else {
			System.out.println("select space");
			if ((selected != null) && (selected.hasCaptured() == true) && (validMove(ex, why, x, y) == true)) {
				System.out.println("multiple move");
				return true;
			} else if (selected != null) {
				System.out.println("selected");
				if (validMove(ex, why, x, y) == true) {
					System.out.println("validMove");
					if (moved == false) {
						System.out.println("moving");
						selected.move(x, y);
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean validMove(int xi, int yi, int xf, int yf) {
		int absx = (xf - xi);
		int absy = (yf - yf);
			if ((xf < 8) && (xf >= 0) && (yf < 8) && (yf >= 0) && (xi < 8) && (xi >= 0) && (yi >= 0) && (yi < 8)) {
			System.out.println("in bounds");
			if (pieceAt(xf, yf) != null || pieceAt(xi, yi) == null) {
				System.out.println("nulls");
				return false;
			}
			if (checkers[xi][yi].isKing() == true) {
				System.out.println("king");
				if (checkers[xi][yi].isFire()) {
					System.out.println("fire king");
					if (turn == checkers[xi][yi].side() && (pieceAt(xi + 2, yi + 2) == null) && (pieceAt(xi + 1, yi + 1) != null)) {
						if (yi + 2 < 8) {
							if (xi + 2 < 8) {
								if ((yf == yi + 2) && (xf == xi + 2) && (checkers[xi + 1][yi + 1].isFire() == false)) {
									System.out.println("fire king right capture");
									System.out.println(cangrab);
									cangrab = true;
								}
							}
						} else if (turn == checkers[xi][yi].side() && (pieceAt(xi - 2, yi + 2) == null) && (pieceAt(xi - 1, yi + 1) != null)) {
							if (yi + 2 < 8) {
								if (xi - 2 >= 0) {
									if ((yf == yi + 2) && (xf == xi - 2) && (checkers[xi - 1][yi + 1].isFire() == false)) {
										System.out.println("fire king left capture");
										cangrab = true;
									}
								}
							}
						}
					} else if (turn == checkers[xi][yi].side() && (pieceAt(xi + 1, yi + 1) == null)) {
						if (yi + 1 < 8) {
							if (xi + 1 < 8) {
								if ((yf == yi + 1) && (xf == xi + 1)) {
									System.out.println(cangrab);
									System.out.println("fire king right move");
									cangrab = true;
								}
							}
						}	
					} else if (turn == checkers[xi][yi].side() && (pieceAt(xi - 1, yi + 1) == null)) {
						if (yi + 1 < 8) {
							if (xi - 1 >= 0) {
								if ((yf == yi + 1) && (xf == xi - 1)) {
									System.out.println("fire king left move");
									cangrab = true;
								}
							}
						}
					}
				} else {
					if (turn == checkers[xi][yi].side() && (pieceAt(xi + 2, yi - 2) == null) && (pieceAt(xi + 1, yi - 1) != null)) {
						if (yi - 2 >= 0) {
							if (xi + 2 < 8) {
								if ((yf == yi - 2) && (xf == xi + 2) && (checkers[xi + 1][yi - 1].isFire() == false)) {
									System.out.println("fire king capture right back");
									System.out.println(cangrab);
									cangrab = true;
								}
							}
						} else if (turn == checkers[xi][yi].side() && (pieceAt(xi - 2, yi - 2) == null) && (pieceAt(xi - 1, yi - 1) != null)) {
							if (yi - 2 >= 0) {
								if (xi - 2 >= 0) {
									if ((yf == yi - 2) && (xf == xi - 2) && (checkers[xi - 1][yi - 1].isFire() == false)) {
										System.out.println("fire king capture left back");
										cangrab = true;
									}
								}
							}
						}
					} else if (turn == checkers[xi][yi].side() && (pieceAt(xi - 1, yi - 1) == null)) {
						if (yi - 1 >= 0) {
							if (xi - 1 >= 0) {
								if ((yf == yi - 1) && (xf == xi - 1)) {
									System.out.println("fire king move left back");
									cangrab = true;
								}
							}
						}	
					} else if (turn == checkers[xi][yi].side() && (pieceAt(xi + 1, yi - 1) == null)) {
						if (yi - 1 >= 0) {
							if (xi + 1 < 8) {
								if ((yf == yi - 1) && (xf == xi + 1)) {
									System.out.println("fire king move right back");
									cangrab = true;
								}
							}
						}
					}
				}
			} else {
				System.out.println("water king");
				if (turn == checkers[xi][yi].side() && (pieceAt(xi + 2, yi + 2) == null) && (pieceAt(xi + 1, yi + 1) != null)) {
					if (yi + 2 < 8) {
						if (xi + 2 < 8) {
							if ((yf == yi + 2) && (xf == xi + 2) && (checkers[xi + 1][yi + 1].isFire() == true)) {
								System.out.println("water king capture left back");
								System.out.println(cangrab);
								cangrab = true;
							}
						}
					} else if (turn == checkers[xi][yi].side() && (pieceAt(xi - 2, yi + 2) == null) && (pieceAt(xi - 1, yi + 1) != null)) {
						if (yi + 2 < 8) {
							if (xi - 2 >= 0) {
								if ((yf == yi + 2) && (xf == xi - 2) && (checkers[xi - 1][yi + 1].isFire() == true)) {
									System.out.println("water king capture right back");
									cangrab = true;
								}
							}
						}
					} else if (turn == checkers[xi][yi].side() && (pieceAt(xi + 1, yi + 1) == null)) {
						if (yi + 1 < 8) {
							if (xi + 1 < 8) {
								if ((yf == yi + 1) && (xf == xi + 1)) {
									System.out.println("water king move back left");
									System.out.println(cangrab);
									cangrab = true;
								}
							}
						}	
					} else if (turn == checkers[xi][yi].side() && (pieceAt(xi - 1, yi + 1) == null)) {
						if (yi + 1 < 8) {
							if (xi - 1 >= 0) {
								if ((yf == yi + 1) && (xf == xi - 1)) {
									System.out.println("water king move right back");
									cangrab = true;
								}
							}
						}
					} else if (turn == checkers[xi][yi].side() && (pieceAt(xi + 2, yi - 2) == null) && (pieceAt(xi + 1, yi - 1) != null)) {
						if (yi - 2 >= 0) {
							if (xi + 2 < 8) {
								if ((yf == yi - 2) && (xf == xi + 2) && (checkers[xi + 1][yi - 1].isFire() == true)) {
									System.out.println("water king capture right");
									System.out.println(cangrab);
									cangrab = true;
								}
							}
						} else if (turn == checkers[xi][yi].side() && (pieceAt(xi - 2, yi - 2) == null) && (pieceAt(xi - 1, yi - 1) != null)) {
							if (yi - 2 >= 0) {
								if (xi - 2 >= 0) {
									if ((yf == yi - 2) && (xf == xi - 2) && (checkers[xi - 1][yi - 1].isFire() == true)) {
										System.out.println("water king capture left");
										cangrab = true;
									}
								}
							}
						}
					} else if (turn == checkers[xi][yi].side() && (pieceAt(xi - 1, yi - 1) == null)) {
						if (yi - 1 >= 0) {
							if (xi - 1 >= 0) {
								if ((yf == yi - 1) && (xf == xi - 1)) {
									System.out.println("water king move right");
									cangrab = true;
								}
							}
						}	
					} else if (turn == checkers[xi][yi].side() && (pieceAt(xi + 1, yi - 1) == null)) {
						if (yi - 1 >= 0) {
							if (xi + 1 < 8) {
								if ((yf == yi - 1) && (xf == xi + 1)) {
									System.out.println("water king move left");
									cangrab = true;
								}
							}
						}
					}
				}
			}
			System.out.println("regular piece");
			if (checkers[xi][yi].isFire()) {
				if (turn == checkers[xi][yi].side() && (pieceAt(xi + 2, yi + 2) == null) && (pieceAt(xi + 1, yi + 1) != null)) {
					if (yi + 2 < 8) {
						if (xi + 2 < 8) {
							if ((yf == yi + 2) && (xf == xi + 2) && (checkers[xi + 1][yi + 1].isFire() == false)) {
								System.out.println("fire capture right");
								System.out.println(cangrab);
								cangrab = true;
							}
						}
					} else if (turn == checkers[xi][yi].side() && (pieceAt(xi - 2, yi + 2) == null) && (pieceAt(xi - 1, yi + 1) != null)) {
						if (yi + 2 < 8) {
							if (xi - 2 >= 0) {
								if ((yf == yi + 2) && (xf == xi - 2) && (checkers[xi - 1][yi + 1].isFire() == false)) {
									System.out.println("fire capture left");
									cangrab = true;
								}
							}
						}
					}
				} else if (turn == checkers[xi][yi].side() && (pieceAt(xi + 1, yi + 1) == null)) {
					if (yi + 1 < 8) {
						if (xi + 1 < 8) {
							if ((yf == yi + 1) && (xf == xi + 1)) {
								System.out.println("fire move right");
								System.out.println(cangrab);
								cangrab = true;
							}
						}
					}	
				} else if (turn == checkers[xi][yi].side() && (pieceAt(xi - 1, yi + 1) == null)) {
					if (yi + 1 < 8) {
						if (xi - 1 >= 0) {
							if ((yf == yi + 1) && (xf == xi - 1)) {
								System.out.println("fire move left");
								cangrab = true;
							}
						}
					}
				}
			} else {
				System.out.println("water piece");
				if (turn == checkers[xi][yi].side() && (pieceAt(xi + 2, yi - 2) == null) && (pieceAt(xi + 1, yi - 1) != null)) {
						if (yi - 2 >= 0) {
							if (xi + 2 < 8) {
								if ((yf == yi - 2) && (xf == xi + 2) && (checkers[xi + 1][yi - 1].isFire() == true)) {
									System.out.println("water capture left");
									System.out.println(cangrab);
									cangrab = true;
								}
							}
						} else if (turn == checkers[xi][yi].side() && (pieceAt(xi - 2, yi - 2) == null) && (pieceAt(xi - 1, yi - 1) != null)) {
							if (yi - 2 >= 0) {
								if (xi - 2 >= 0) {
									if ((yf == yi - 2) && (xf == xi - 2) && (checkers[xi - 1][yi - 1].isFire() == true)) {
										System.out.println("water capture right");
										cangrab = true;
									}
								}
							}
						}
				} else if (turn == checkers[xi][yi].side() && (pieceAt(xi - 1, yi - 1) == null)) {
					if (yi - 1 >= 0) {
						if (xi - 1 >= 0) {
							if ((yf == yi - 1) && (xf == xi - 1)) {
								System.out.println("water move right");
								cangrab = true;
							}
						}
					}	
				} else if (turn == checkers[xi][yi].side() && (pieceAt(xi + 1, yi - 1) == null)) {
					if (yi - 1 >= 0) {
						if (xi + 1 < 8) {
							if ((yf == yi - 1) && (xf == xi + 1)) {
								System.out.println("water move left");
								cangrab = true;
							}
						}
					}
				}
			}
		}
		System.out.println(cangrab);
		return cangrab;
	}

	public void select(int x, int y) {
		if ((checkers[x][y] == null) && (selected != null)) { //piece selected and valid space is there
			moved = true;
			selected.move(x, y);
			System.out.print(x + ", ");
			System.out.println(y);
		} else {
			selected = checkers[x][y];
			System.out.print(x + ", ");
			System.out.println(y);
		}
	}

	public void place(Piece p, int x, int y) {
		if ((x < 8) && (x >= 0) && (y < 8) && (y >= 0) && (p != null)) {
			for (int i = 0; i < 8; i = i + 1) {
				for (int j = 0; j < 8; j = j + 1) {
					if (checkers[i][j] == p) {
						System.out.println("remove from last space");
						checkers[i][j] = null;
					}
				}
			}
			checkers[x][y] = p;
		}
	}
	public Piece remove(int x, int y) {
		Piece removed = checkers[x][y];
		if ((x < 0) && (x > 7) && (y < 0) && (y > 7)) {
			return null;
		}
		else if (checkers[x][y] == null) {
			return null;
		} else {
			checkers[x][y] = null;
			if (selected != null) {
				selected.hasCaptured();
				captured = true;
			}
		}
		return removed;
	}

	public boolean canEndTurn() {
		if (moved == true) {
			return true;
		} else {
			return false;
		}
	}

	public void endTurn() {
		if (canEndTurn() == true) {
			if (turn == 0) {
				turn = 1;
			} else {
				turn = 0;
			}
			System.out.println("switch instance variables to false");
			moved = false;
			selected.doneCapturing();
			selected = null;
		}
	}

	public String winner() {
		int fire = 0;
		int water = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (checkers[i][j] != null) {
					if (checkers[i][j].side() == 0) {
						fire += 1;
					} else {
						water += 1;
					}
				}
			}
		}
		if ((fire == 0) && (water == 0)) {
			winner = true;
			return "No one";
		} else if (fire == 0) {
			winner = true;
			return "Water";
		} else if (water == 0) {
			winner = true;
			return "Fire";
		} else {
			return null;
		}
	}

	private int[] getxandy(Piece p) {
		int[] yay = {0, 0};
		for (int i = 0; i < 8; i = i + 1) {
			for (int j = 0; j < 8; j = j + 1) {
				if (checkers[i][j] == p) {
					yay[0] = i;
					yay[1] = j;
				}
			}
		}
		return yay;
	}

	// private void movePiece(int x, int y) {
	// 	if (selected != null) {
	// 		checkers[getxandy(selected)[0]][getxandy(selected)[1]].move(x, y);
	// 		checkers[x][y] = checkers[getxandy(selected)[0]][getxandy(selected)[1]];
	// 		checkers[getxandy(selected)[0]][getxandy(selected)[1]] = null;
	// 	}
	// }
}