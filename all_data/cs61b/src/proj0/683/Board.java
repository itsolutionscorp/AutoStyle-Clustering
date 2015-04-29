public class Board {
	private Piece[][] pieces;
	private Boolean shouldBeEmpty;
	private boolean selected;
	private boolean end = false;
	private boolean moved = false;
	private Piece pieceSelected;
	private int pieceSelectedX;
	private int pieceSelectedY;
	private boolean captured;
	private String player = "Fire";

	public static void main(String[] args) { 
		int N = 8;
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
		while(b.winner() == null) { 
			b.drawBoard(N);
			StdDrawPlus.show(10);

        	if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
     
                if (b.canSelect((int)x, (int)y) == true) {
                	b.select((int)x, (int)y);
                } else {
                	;
                }

            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn() == true) {
                	b.endTurn();
          
                } else {
                	;
                }

            }
        	}
		}
	}

	public Board(boolean shouldBeEmpty) {
			if (shouldBeEmpty == true) {
				pieces = new Piece[8][8]; 
			}
			else {
				pieces = new Piece[8][8];
				for (int rows = 0; rows < 8; rows++) {
					for (int cols = 0; cols < 8; cols++) {
						if ((rows + cols)%2 == 0) {
							if (cols==0) {
								pieces[rows][cols] = new Piece(true, this, rows, cols, "pawn");
							} else if (cols == 1) {
								pieces[rows][cols] = new Piece(true, this, rows, cols, "shield");
							} else if (cols ==2) {
								pieces[rows][cols] = new Piece(true, this, rows, cols, "bomb");
							} else if (cols==5) {
								pieces[rows][cols] = new Piece(false, this, rows, cols, "bomb");
							} else if (cols==6) {
								pieces[rows][cols] = new Piece(false, this, rows, cols, "shield");
							} else if (cols==7) {
								pieces[rows][cols] = new Piece(false, this, rows, cols, "pawn");
							}
						}
					}
				}
			}
		}

	public void place(Piece p, int x, int y) {
		if ((x<0 || x>7) || (y<0 || y>7) || (p == null)) {
	 		System.out.println("out of bounds");
	 	} else {
	 		this.pieces[x][y] = p;
	
	 	}
	}

	public Piece remove(int x, int y) {
		if ((x<0 || x>7) || (y<0 || y>7)) {
			System.out.println("out of bounds");
	 		return null;
	 	} else {
	 		if (pieces[x][y] != null) {
	 		Piece old = pieces[x][y]; 
	 		pieces[x][y] = null;
	 		return old;
	 	} else {
	 		return null;
	 	}
	 	}
	} 

	public Piece pieceAt(int x, int y) {
		if ((x<0 || x>7) || (y<0 || y>7)) {
			return null; 
		} else {
			if (pieces[x][y] == null) {
				return null;
			} else {
			return pieces[x][y];
			} 
		}
	}

	private boolean canCapture(int xi, int yi, int xf, int yf) {

		int captureX = ((xi + xf)/2);
		int captureY = ((yi + yf)/2);

		if (selected == true) {
		if ((this.pieceAt(xi, yi) != null) && (this.pieceAt(xi, yi).isFire())) {
		if (this.pieceAt(xi, yi).isKing() == false) {
			if (yf > yi) {
			if ((pieceAt(captureX, captureY) != null) && !(pieceAt(captureX, captureY).isFire())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		} else {
			if ((pieceAt(captureX, captureY) != null) && !(pieceAt(captureX, captureY).isFire())) {
				return true;
			} else {
				return false;
			}
		}
		} else {
			if (this.pieceAt(xi, yi) != null) {
			if (this.pieceAt(xi, yi).isKing() == false) {
				if (yf < yi) {
					if ((pieceAt(captureX, captureY) != null) && (pieceAt(captureX, captureY).isFire())) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				if ((pieceAt(captureX, captureY) != null) && (pieceAt(captureX, captureY).isFire())) {
						return true;
					} else {
						return false;
					}
			}
		} else {
			return false;
		}
	}
	} else {
		return false;
	}

	}


	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (this.pieceAt(xi, yi) != null) {
			if (this.pieceAt(xi, yi).isFire()) {
			if (this.pieceAt(xi, yi).isKing() == false) {
				if (((xf - xi) == 1 || (xf - xi) == -1) && ((yf - yi) == 1)) {
					return true;
				} else {
					if (canCapture(xi, yi, xf, yf) == true) {
						if (((xf - xi) == 2 || (xf - xi) == -2) && (((yf - yi) == 2))) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} 
			} else {
				if (((xf - xi) == 1 || (xf - xi) == -1) && (((yf - yi) == 1) || ((yf - yi) == -1))) {
					return true;
				} else {
					if (canCapture(xi, yi, xf, yf) == true) {
						if (((xf - xi) == 2 || (xf - xi) == -2) && (((yf - yi) == 2) || ((yf - yi) == -2))) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		} else {
			if (this.pieceAt(xi, yi).isKing() == false) {
				if (((xf - xi) == 1 || (xf - xi) == -1) && ((yf - yi) == -1)) {
					return true;
				} else {
					if (canCapture(xi, yi, xf, yf) == true) {
						if (((xf - xi) == 2 || (xf - xi) == -2) && (((yf - yi) == -2))) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} 
			} else {
				if (((xf - xi) == 1 || (xf - xi) == -1) && (((yf - yi) == 1) || ((yf - yi) == -1))) {
					return true;
				} else {
					if (canCapture(xi, yi, xf, yf) == true) {
						if (((xf - xi) == 2 || (xf - xi) == -2) && (((yf - yi) == 2) || ((yf - yi) == -2))) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		}
		} else {
			return false;
		}	
	}


	public void select(int x, int y) {
		
		if (pieceAt(x, y) != null) {
			
			pieceSelected = pieceAt(x, y); 
			pieceSelectedX = x;
			pieceSelectedY = y;
			selected = true;
			System.out.println("Piece " + x + ", " + y + " selected.");

		} else {
			if (selected == true) {
					pieceSelected.move(x, y);
					if (pieceSelected.hasCaptured() == true) {
						captured = true;
					}
					pieces[pieceSelectedX][pieceSelectedY] = null;
					pieceSelectedX = x;
					pieceSelectedY = y;
					moved = true;
			}
		}
	}

	public boolean canSelect(int x, int y) {
		if ((pieceAt(x, y) != null) && (moved == false)) {
			if (player == "Fire" && pieceAt(x,y).isFire()) {
				return true;
			} else {
				if (player == "Water" && pieceAt(x,y).isFire() == false) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			if ((selected == true) && (pieceAt(x, y) == null) && (moved == false)) {
				if (validMove(pieceSelectedX, pieceSelectedY, x, y) == true) {
					return true;
				} else {
					return false;
				}

			} else {
				 if ((selected == true) && (pieceAt(x, y) == null) && (moved == true)) {
				 	if (captured == true) {
				 	if (canCapture(pieceSelectedX, pieceSelectedY, x, y) == true) {
				 		return true;
				 	} else {
				 		return false;
				 	}
				 } else {
				 	return false;
				 }
				} else {
					return false;
				}
			}
			}	
		}

	public String winner() {
		int firePieces = 0;
		int waterPieces = 0;
		for (int rows = 0; rows < 8; rows++) {
            for (int cols = 0; cols < 8; cols++) {
            		if (pieceAt(rows, cols) != null) {
            			if (pieceAt(rows, cols).isFire()) {
            				firePieces++;  
            			} else {
            				waterPieces++;
            			}   
	            		
	                }
	            }
	        }
			if (firePieces > 0 && waterPieces ==0) {
				return "Fire";
			} else if (firePieces == 0 && waterPieces > 0) {
				return "Water";
			} else if (firePieces == 0 && waterPieces == 0) {
				return "No one";
			} else {
				return null;
			}

	    }  
 


	public boolean canEndTurn() {
		if ((moved == true) || (captured == true)) { 
			return true;
		} else {
			return false;
		}
	}

	public void endTurn() {
		if (player == "Fire") {
			player = "Water";
		} else {
			player = "Fire";
		}
		moved = false;
		selected = false;
		pieceSelected.doneCapturing();
		pieceSelected = null;

	}

	private void drawBoard(int N) {
		for (int rows = 0; rows < N; rows++) {
            for (int cols = 0; cols < N; cols++) {
                if ((rows + cols) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {                 
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(rows + .5, cols + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
 				
 				if (this.pieceAt(rows, cols) == null) {
                	;
                } else {
                	if (this.pieceAt(rows, cols).isFire() == true) {
                		if (this.pieceAt(rows, cols).isKing()) {
                			if (this.pieceAt(rows, cols).isBomb() == true) {
					 			StdDrawPlus.picture(rows + .5, cols + .5, "img/bomb-fire-crowned.png", 1, 1);
							} 
							else if (this.pieceAt(rows, cols).isShield() == true) {
								StdDrawPlus.picture(rows + .5, cols + .5, "img/shield-fire-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(rows + .5, cols + .5, "img/pawn-fire-crowned.png", 1, 1);
							}
						}

                	if (this.pieceAt(rows, cols).isBomb() == true) {
			 			StdDrawPlus.picture(rows + .5, cols + .5, "img/bomb-fire.png", 1, 1);
					} 
					else if (this.pieceAt(rows, cols).isShield() == true) {
						StdDrawPlus.picture(rows + .5, cols + .5, "img/shield-fire.png", 1, 1);
				}
					else {
						StdDrawPlus.picture(rows + .5, cols + .5, "img/pawn-fire.png", 1, 1);
				}
                }
            	else {
            		if (this.pieceAt(rows, cols).isKing()) {
                			if (this.pieceAt(rows, cols).isBomb() == true) {
					 			StdDrawPlus.picture(rows + .5, cols + .5, "img/bomb-water-crowned.png", 1, 1);
							} 
							else if (this.pieceAt(rows, cols).isShield() == true) {
								StdDrawPlus.picture(rows + .5, cols + .5, "img/shield-water-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(rows + .5, cols + .5, "img/pawn-water-crowned.png", 1, 1);
							}
						}

                	if (this.pieceAt(rows, cols).isBomb() == true) {
			 			StdDrawPlus.picture(rows + .5, cols + .5, "img/bomb-water.png", 1, 1);
					} 
					else if (this.pieceAt(rows, cols).isShield() == true) {
						StdDrawPlus.picture(rows + .5, cols + .5, "img/shield-water.png", 1, 1);
				}
					else {
						StdDrawPlus.picture(rows + .5, cols + .5, "img/pawn-water.png", 1, 1);
				}
            	}
                }
            }
        }
    }
    
	
	}