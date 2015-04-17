public class Board{

    private static boolean[][] piecesExist = new boolean[8][8];

	private static Piece[][] pieces = new Piece[8][8];

    private boolean isFireTurn = true;
    private boolean hasSelected = false;
    private Piece selectedPiece;
    private int selectedPiecex;
    private int selectedPiecey;
    private boolean hasMoved = false;
	//most of these things shouldn't be static
	//main method, draw the board there and stuff



	public Board(boolean shouldBeEmpty) {
			for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
            		if (shouldBeEmpty == true) {
            			piecesExist[i][j] = false;
                        pieces[i][j] = null;
            		// pieces[i][j] = new Piece(); 
            		}
            		else if (shouldBeEmpty == false) {
            			if(((j == 0 || j == 2 || j == 6) && (i % 2 == 0)) || ((j == 1 || j == 5 || j == 7) && (i % 2 == 1)))
            			 {
            			piecesExist[i][j] = true;
                        if (j == 0) {
                        pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    }
                        if (j == 1) {
                        pieces[i][j] = new Piece(true, this, i, j, "shield");
                    }
                        if (j == 2) {
                        pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }
                        if (j == 5) {
                        pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    }
                        if (j == 6) {
                        pieces[i][j] = new Piece(false, this, i, j, "shield");
                    }
                        if (j == 7) {
                        pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }

            		}
                    else {
                        piecesExist[i][j] = false;
                        pieces[i][j] = null;
                    }
            	}
            }
        }
}

    private static void drawBoard() {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (piecesExist[i][j]) {
                	if (j == 0) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                	}
                	if (j == 1) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                	}
                	if (j == 2) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                	}
                	if (j == 5) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                	}
                	if (j == 6) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                	}
                	if (j == 7) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                	}
                }
            }
        }
                StdDrawPlus.show(100);
    }

	public Piece pieceAt(int x, int y) {
        if ((piecesExist[x][y] == false) || (x < 0 || x > 7) || (y < 0 || y > 7)) {
            return null;
	}
        else {
            return pieces[x][y];
    }
}

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if ((Math.abs(xi - xf) == 2) && (Math.abs(yi - yf) == 2)) {
            return true;
        }
        if ((Math.abs(xi - xf) == 1) && (Math.abs(yi - yf) == 1)) {
            return true;
        }
    //     if ((pieceAt(xi + 1, yi + 1) != null) && (xf - xi == 2 && yf - yi = 2)) {
    //         return true;
    //     }
    //     if ((pieceAt(xi - 1, yi + 1) != null) && xf - xi == -2 && yf - yi = 2) {
    //         return true;
    // }
        else {
            return false;
        }
}

    // private Piece selectedPiece(int x, int y) {
    //     if (pieceAt(x, y) != null && canSelect(x, y)) {

    //     }
    // }


//also, account for how if the piece inbetween is same team, cannot jump over
	public boolean canSelect(int x, int y) {
        if (isFireTurn) {
            if (pieceAt(x, y) != null) {
                if (pieceAt(x, y).isFire() == true) {
                // hasSelected = true;
                return true;
            }
            else if (pieceAt(x, y).isFire() == false) {
                return false;
            }
        }
            if(pieceAt(x, y) == null) {
                if (Math.abs(x-selectedPiecex) == 1 && (y - selectedPiecey == 1)) {
                    return true;
                }
                if ((x-selectedPiecex == 2) && (y - selectedPiecey == 2) && (pieceAt(x - 1, y - 1) != null) && (pieceAt(x - 1, y - 1).isFire() == false)) {
                    return true;
                }
                if ((x-selectedPiecex == -2) && (y - selectedPiecey == 2) && (pieceAt(x + 1, y - 1) != null) && (pieceAt(x + 1, y - 1).isFire() == false)) {
                    return true;
                }
                }
            }

        if (!isFireTurn) {
            if (pieceAt(x, y) != null) {
                if (pieceAt(x, y).isFire() == false) {
                // hasSelected = true;
                return true;
            }
            else if (pieceAt(x, y).isFire() == true) {
                return false;
            }
        }
            if(pieceAt(x, y) == null) {
                if (Math.abs(x-selectedPiecex) == 1 && (y - selectedPiecey == -1)) {
                    return true;
                }
                if ((x-selectedPiecex == 2) && (y - selectedPiecey == -2) && (pieceAt(x + 1, y - 1) != null) && (pieceAt(x + 1, y - 1).isFire() == true)) {
                    return true;
                }
                if ((x-selectedPiecex == -2) && (y - selectedPiecey == 2) && (pieceAt(x - 1, y - 1) != null) && (pieceAt(x - 1, y - 1).isFire() == true)) {
                    return true;
                }
                }
            }
            return false;
    }




	public void select(int x, int y) {
        if (pieceAt(x, y) != null) {
        selectedPiece = pieceAt(x, y);
        selectedPiecex = x;
        selectedPiecey = y;
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);

	}
        else {
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            selectedPiece.move(x, y);
            piecesExist[x][y] = true;
            pieces[x][y] = selectedPiece;
            piecesExist[selectedPiecex][selectedPiecey] = false;
            pieces[selectedPiecex][selectedPiecey] = null;
            selectedPiecex = x;
            selectedPiecey = y;
            hasMoved = true;
        }
    }




	public void place(Piece p, int x, int y) {
		if ((p == null) || (x < 0 || x > 7) || (y < 0 || y > 7)) {
		}
		else {
            piecesExist[x][y] = true;
            pieces[x][y] = p;

		}
	}




	public Piece remove(int x, int y) {
        Piece p;
		if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
            System.out.println("Out of board bounds!");
            return null;
        }
        else if (pieceAt(x, y) == null) {
            System.out.println("No piece here!");
            return null;
        }
        else {
            p = pieceAt(x, y);
            piecesExist[x][y] = false;
            pieces[x][y] = null;
            return p;

        }
	}




	public boolean canEndTurn() {
        return hasMoved;
    	}




	public void endTurn() {
        isFireTurn = !isFireTurn;
        hasMoved = false;

	}




	public String winner() {
		return null;
	}

	


	public static void main(String[] args) {
		// int N = 8;
		Board board = new Board(false);
		drawBoard();
			// for (int i = 0; i < 8; i++) {
   //          	for (int j = 0; j < 8; j++) {
   //          			if(((i == 0 || i == 2 || i == 6) && (j % 2 == 0)) || ((i == 1 || i == 5 || i == 7) && (j % 2 == 1)))
   //          			 {
   //          			piecesExist[i][j] = true;
   //          		}}}
	    // StdDrawPlus.setXscale(0, 8);
     //    StdDrawPlus.setYscale(0, 8);
     //    for (int i = 0; i < 8; i++) {
     //        for (int j = 0; j < 8; j++) {
     //            if ((i + j) % 2 == 0)  {
     //            	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
     //            } else {
     //                StdDrawPlus.setPenColor(StdDrawPlus.RED);
     //            }
     //            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
     //            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
     //            if (piecesExist[i][j]) {
     //            	if (i == 0) {
     //                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
     //            	}
     //            	if (i == 1) {
     //                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
     //            	}
     //            	if (i == 2) {
     //                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
     //            	}
     //            	if (i == 5) {
     //                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
     //            	}
     //            	if (i == 6) {
     //                    StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
     //            	}
     //            	if (i == 7) {
     //                    StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
     //            	}

     //            }
        //     }
        // }

	}

}