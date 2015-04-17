public class Board {
    
    private boolean empty;
    private Piece[][] pieces = new Piece[8][8];
    
    private int player = 0;
    private Piece selectedPiece;
    private boolean hasMoved = false;
    
    public Board(boolean shouldBeEmpty) {
    	empty = shouldBeEmpty;
    	if (shouldBeEmpty == false) {
    		for (int i = 0; i < 8; i = i + 2) {
        		pieces[i][0] = new Piece(true, this, i, 0, "pawn");
        		pieces[i][2] = new Piece(true, this, i, 2, "bomb");
        	}
        	for (int i = 1; i < 8; i = i + 2) {
        		pieces[i][1] = new Piece(true, this, i, 1, "shield");
        	}
        	for (int i = 1; i < 8; i = i + 2) {
        		pieces[i][7] = new Piece(false, this, i, 7, "pawn");
        		pieces[i][5] = new Piece(false, this, i, 5, "bomb");
        	}
        	for (int i = 0; i < 8; i = i + 2) {
        		pieces[i][6] = new Piece(false, this, i, 6, "shield");
        	}
    	}
    }
    
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] != null) {
                	Piece piece = pieces[i][j];
                	String img = null;
                	if (piece.side() == 0) {
                		if (piece.isBomb() == true) {
                			img = "img/bomb-fire.png";
                			if (piece.isKing() == true) {
                				img = "img/bomb-fire-crowned.png";
                			}
                		} else if (piece.isShield() == true) {
                			img = "img/shield-fire.png";
                			if (piece.isKing() == true) {
                				img = "img/shield-fire-crowned.png";
                			}
                		} else {
                			img = "img/pawn-fire.png";
                			if (piece.isKing() == true) {
                				img = "img/pawn-fire-crowned.png";
                			}
                		}
                	}
                	if (piece.side() == 1) {
                		if (piece.isBomb() == true) {
                			img = "img/bomb-water.png";
                			if (piece.isKing() == true) {
                				img = "img/bomb-water-crowned.png";
                			}
                		} else if (piece.isShield() == true) {
                			img = "img/shield-water.png";
                			if (piece.isKing() == true) {
                				img = "img/shield-water-crowned.png";
                			}
                		} else {
                			img = "img/pawn-water.png";
                			if (piece.isKing() == true) {
                				img = "img/pawn-water-crowned.png";
                			}
                		}
                	}
                	StdDrawPlus.picture(i+.5, j+0.5, img, 1, 1);
                }
            }
        }
    }
    
    private int getX(Piece piece) {
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] == piece) {
                	return i;
                }
            }
        }
        return 0;
    }
    
    private int getY(Piece piece) {
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] == piece) {
                	return j;
                }
            }
        }
        return 0;
    }
    
    public Piece pieceAt(int x, int y) {
    	if (x > 7 || y > 7) {
    		return null;
    	} else if (pieces[x][y] == null) {
    		return null;
    	} else {
    		return pieces[x][y];
    	}
    }
    
    public boolean canSelect(int x, int y) {
    	if (pieceAt(x, y) != null) {
    		Piece piece = pieceAt(x, y);
    		if (piece.side() == player) {
    			if (selectedPiece == null) {
    				return true;
    			} else if (hasMoved == false) {
    				return true;
    			} else {
    				return false;
    			}
    		} else {
    			return false;
    		}
    	} else {
    		if (selectedPiece != null && pieceAt(getX(selectedPiece), getY(selectedPiece)) == null) {
    			return false;
    		} else if (selectedPiece != null && hasMoved == false && validMove(getX(selectedPiece), getY(selectedPiece), x, y) == true) {
    			return true;
    		} else if (selectedPiece != null && selectedPiece.hasCaptured() == true && validMove(getX(selectedPiece), getY(selectedPiece), x, y) == true) {
    			return true;
    		} else {
    			return false;
    		}
    	}
    }
    
    private boolean validMove(int xi, int yi, int xf, int yf) {
    	Piece piece = pieceAt(xi, yi);
    	if (piece.hasCaptured() == true) {
    		if (xf - 1 == xi && yf - 1 == yi) {
    			return false;
    		} else if (xf + 1 == xi && yf - 1 == yi) {
    			return false;
    		} else if (xf - 1 == xi && yf + 1 == yi) {
    			return false;
    		} else if (xf + 1 == xi && yf + 1 == yi) {
    			return false;
    		}
    	}
    	if (piece.isKing() == true) {
    		if (xf - 1 == xi && yf - 1 == yi) {
    			return true;
    		} else if (xf + 1 == xi && yf - 1 == yi) {
    			return true;
    		} else if (xf - 1 == xi && yf + 1 == yi) {
    			return true;
    		} else if (xf + 1 == xi && yf + 1 == yi) {
    			return true;
    		} else if (xf - 2 == xi && yf - 2 == yi) {
    		 	Piece piece1 = pieceAt(xf-1, yf-1);
    		 	if (piece1 == null) {
    		 		return false;
    		 	} else if (piece1.side() == player) {
    		 		return false;
    		 	} else {
    		 		return true;
    		 	}
    		} else if (xf + 2 == xi && yf - 2 == yi) {
    		 	Piece piece1 = pieceAt(xf+1, yf-1);
    		 	if (piece1 == null) {
    		 		return false;
    		 	} else if (piece1.side() == player) {
    		 		return false;
    		 	} else {
    		 		return true;
    		 	}
    		 } else if (xf - 2 == xi && yf + 2 == yi) {
    		 	Piece piece1 = pieceAt(xf-1, yf+1);
    		 	if (piece1 == null) {
    		 		return false;
    		 	} else if (piece1.side() == player) {
    		 		return false;
    		 	} else {
    		 		return true;
    		 	}
    		 } else if (xf + 2 == xi && yf + 2 == yi) {
    		 	Piece piece1 = pieceAt(xf+1, yf+1);
    		 	if (piece1 == null) {
    		 		return false;
    		 	} else if (piece1.side() == player) {
    		 		return false;
    		 	} else {
    		 		return true;
    		 	}
    		 } else {
    		 	return false;
    		 }
    	} else {
    		if (piece.side() == 0) {
    			if (xf - 1 == xi && yf - 1 == yi) {
    				return true;
    			} else if (xf + 1 == xi && yf - 1 == yi) {
    				return true;
    			} else if (xf - 2 == xi && yf - 2 == yi) {
    		 		Piece piece1 = pieceAt(xf-1, yf-1);
    		 		if (piece1 == null) {
    		 			return false;
    		 		} else if (piece1.side() == player) {
    		 			return false;
    		 		} else {
    		 			return true;
    		 		}
    			} else if (xf + 2 == xi && yf - 2 == yi) {
    		 		Piece piece1 = pieceAt(xf+1, yf-1);
    		 		if (piece1 == null) {
    		 			return false;
    		 		} else if (piece1.side() == player) {
    		 			return false;
    		 		} else {
    		 			return true;
    		 		}
    		 	} else {
    		 		return false;
    		 	}
    		 } else {
    		 	if (xf + 1 == xi && yf + 1 == yi) {
    				return true;
    			} else if (xf - 1 == xi && yf + 1 == yi) {
    				return true;
    			} else if (xf - 2 == xi && yf + 2 == yi) {
    		 		Piece piece1 = pieceAt(xf-1, yf+1);
    		 		if (piece1 == null) {
    		 			return false;
    		 		} else if (piece1.side() == player) {
    		 			return false;
    		 		} else {
    		 			return true;
    		 		}
    			} else if (xf + 2 == xi && yf + 2 == yi) {
    		 		Piece piece1 = pieceAt(xf+1, yf+1);
    		 		if (piece1 == null) {
    		 			return false;
    		 		} else if (piece1.side() == player) {
    		 			return false;
    		 		} else {
    		 			return true;
    		 		}
    		 	} else {
    		 		return false;
    		 	}
    		 }
    	}
    }
    
    public void select(int x, int y) {
    	if (pieceAt(x, y) != null) {
    		selectedPiece = pieceAt(x, y);
    	} else {
    		selectedPiece.move(x, y);
    		hasMoved = true;
    	}
    }
    
    public void place(Piece p, int x, int y) {
    	if (x > 7 || y > 7) {
    		return;
    	} else if (p == null) {
    		return;
    	} else {
    		pieces[x][y] = p;
    	}
    }
    
    public Piece remove(int x, int y) {
    	if (x > 7 || y > 7) {
    		System.out.println("Out of bounds.");
    		return null;
    	} else if (pieces[x][y] == null) {
    		System.out.println("No piece at this point in the board.");
    		return null;
    	} else {
    		Piece piece = pieces[x][y];
    		pieces[x][y] = null;
    		return piece;
    	}
    }
    
    public boolean canEndTurn() {
    	if (hasMoved == true) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void endTurn() {
    	if (selectedPiece != null) {
    		selectedPiece.doneCapturing();
    	}
    	selectedPiece = null;
    	hasMoved = false;
    	if (player == 0) {
    		player = 1;
    	} else {
    		player = 0;
    	}
    }
    
    public String winner() {
    	boolean fireLeft = false;
    	boolean waterLeft = false;
    	for (int i = 0; i < 8; i++) {
    		for (int j = 0; j < 8; j++) {
    			Piece piece = pieces[i][j];
    			if (piece != null && piece.side() == 0) {
    				fireLeft = true;
    			}
    			if (piece != null && piece.side() == 1) {
    				waterLeft = true;
    			}
    		}
    	}
		if (fireLeft == true && waterLeft == false) {
    		return "Fire";
    	} else if (waterLeft == true && fireLeft == false) {
    		return "Water";
    	} else if (waterLeft == false && fireLeft == false) {
    		return "No one";
    	} else {
    		return null;
    	}
    }
    
    public static void main(String[] args) {
        Board board = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        while(true) {
        	board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double doubleX = StdDrawPlus.mouseX();
                double doubleY = StdDrawPlus.mouseY();
                int x = (int) doubleX;
                int y = (int) doubleY;
                if (board.canSelect(x, y) == true) {
            		board.select(x, y);
            	}
            }
            if (StdDrawPlus.isSpacePressed() == true && board.canEndTurn() == true) {
            	board.endTurn();
            }
            StdDrawPlus.show(100);
        }
    }
    
}