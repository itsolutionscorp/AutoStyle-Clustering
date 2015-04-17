public class Board {

    private Piece[][] game_board;
    private boolean[][] pieces;
    private boolean fire_player = true;
    private boolean water_player = false;
    private boolean prev_selected = false;
    private boolean moved = false;
    private Piece savedpieces;
    private int[] locationsp;

    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty) {
            game_board = new Piece[8][8];
        }
        else {
            game_board = new Piece[8][8];
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                         if (j == 0) {
                            if (i % 2 == 0) {
                                game_board[i][j] = new Piece(true, this, i, j, "pawn");
                            }
                        }
                        else if (j == 1) {
                            if (i % 2 != 0) {
                                game_board[i][j] = new Piece(true, this, i, j, "shield");
                            }
                        }
                        else if (j == 2) {
                            if (i % 2 == 0) {
                                game_board[i][j] = new Piece(true, this, i, j, "bomb");
                            }
                        }
                        else if (j == 5) {
                            if (i % 2 != 0) {
                                game_board[i][j] = new Piece(false, this, i, j, "bomb");
                            }
                        }
                        else if (j == 6) {
                            if (i % 2 == 0) {
                                game_board[i][j] = new Piece(false, this, i, j, "shield");
                            }
                        }
                        else if (j == 7) {
                            if (i % 2 != 0) {
                                game_board[i][j] = new Piece(false, this, i, j, "pawn");    
                        }
                }
              }
            }
        }
    }
            

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if(prev_selected) {
                	if (i == locationsp[0] && j == locationsp[1]) {
                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	}
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (game_board[i][j] != null) {
                    if (game_board[i][j].isFire()) {
                        if (!game_board[i][j].isBomb() && !game_board[i][j].isShield()) {
                            if (game_board[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                        else if (game_board[i][j].isShield()) {
                            if (game_board[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                                                }
                        else if (game_board[i][j].isBomb()) {
                            if (game_board[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                        }
                    else {
                        if (!game_board[i][j].isBomb() && !game_board[i][j].isShield()) {
                            if (game_board[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                        else if (game_board[i][j].isShield()) {
                            if (game_board[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        }
                        else if (game_board[i][j].isBomb()) {
                            if (game_board[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                        }
                }

                }
            }
        }

    public Piece pieceAt(int x, int y) {
      if (x > game_board.length -1 || x < 0 || y > game_board.length - 1 || y < 0) {
        return null;
        }
      if (game_board[x][y] != null) {
        return game_board[x][y];
        }
        return null;
    }

    private void previousSelection(int x, int y) {
        if (pieceAt(x, y) != null) {
            savedpieces = pieceAt(x, y);
        }
            locationsp = new int[2];
            locationsp[0] = x;
            locationsp[1] = y;
        }
   
    public boolean canSelect(int x, int y) {
        if (pieceAt(x, y) != null) {
            if (fire_player == true && game_board[x][y].isFire()) {
                if ((prev_selected == true && moved == false) || (prev_selected == false)) {
                    return true;
                }
                return false;
                }
            if (water_player == true && !game_board[x][y].isFire()) {
                if ((prev_selected == true && moved == false) || (prev_selected == false)) {
                    return true;
                }
        }

            return false;
        }
        else {
            if (prev_selected == true && validMove(locationsp[0], locationsp[1], x, y) && moved == false) {
                return true;
            }
            if (savedpieces != null) {
                if (prev_selected == true && savedpieces.hasCaptured() && validMove(locationsp[0], locationsp[1], x, y)) {
                    return true;
                }
            return false;
        }
        return false;
        }
    }


    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (xf >= 0 && xf < 8 && yf >= 0 && yf < 8) {
            if (game_board[xf][yf] == null) {
                if (savedpieces != null) {
                    if (!savedpieces.isKing()) {
                        if (savedpieces.isFire()) {
                        	if (((xf == xi - 1) && (yf == yi + 1)) || ((xf == xi + 1) && (yf == yi + 1))) {
                        		return true;
                        	}
                        	if ((xf == xi - 2) && (yf == yi + 2)) {
                        		if (game_board[xi - 1][yi + 1] != null) {
                        			if (!game_board[xi - 1][yi + 1].isFire()) {
                        				return true;
                        			}
                        			return false;
                        		}
                        	}
                        	if ((xf == xi + 2 ) && (yf == yi + 2)) {
                        		if (game_board[xi + 1][yi + 1] != null) {
                        			if (!game_board[xi + 1][yi + 1].isFire()) {
                        				return true;
                        			}
                        			return false;
                        		}
                        	}
                        }
                        else {
                        	if (((xf == xi - 1) && (yf == yi - 1)) || ((xf == xi + 1) && (yf == yi - 1))) {
                        		return true;
                        	}
                        	if ((xf == xi - 2) && (yf == yi - 2)) {
                        		if (game_board[xi - 1][yi - 1] != null) {
                        			if (game_board[xi - 1][yi - 1].isFire()) {
                        				return true;
                        			}
                        			return false;
                        		}
                        	}
                        	if ((xf == xi + 2 ) && (yf == yi - 2)) {
                        		if (game_board[xi + 1][yi - 1] != null) {
                        			if (game_board[xi + 1][yi - 1].isFire()) {
                        				return true;
                        			}
                        			return false;
                        		}
                        	}
                        }
                    }
                    else {
                    		if (((xf == xi - 1) && ((yf == yi - 1) || (yf == yi + 1))) 
                    				|| ((xf == xi + 1) && ((yf == yi - 1) || (yf == yi + 1)))) {
                    			return true;
                    		}
                    		if ((xf == xi - 2) && (yf == yi - 2)) {
                    			if (game_board[xi][yi].isFire() != game_board[xi - 1][yi - 1].isFire()) {
                    				return true;
                    			}
                    		}
                    		if ((xf == xi + 2) && (yf == yi - 2)) {
                    			if (game_board[xi][yi].isFire() != game_board[xi + 1][yi - 1].isFire()) {
                    				return true;
                    			}
                    		}
                    		if ((xf == xi - 2) && (yf == yi + 2)) {
                    			if (game_board[xi][yi].isFire() != game_board[xi - 1][yi + 1].isFire()) {
                    				return true;
                    			}
                    		}
                    		if ((xf == xi + 2) && (yf == yi + 2)) {
                    			if (game_board[xi][yi].isFire() != game_board[xi + 1][yi + 1].isFire()) {
                    				return true;
                    			}
                    		}
                            return false;
                        }
                    if (game_board[xf][yf] != null) {
                         return false;
                    }
                }
            }
        }
        return false;
    }
                       

    public void select(int x, int y) {
        if (game_board[x][y] == null) {
        	savedpieces.move(x, y);
            moved = true;
            locationsp[0] = x;
            locationsp[1] = y;
        }
        else {
            previousSelection(x, y);
            prev_selected = true;
        }
    }

    public void place (Piece p, int x, int y) {
        if (x > game_board.length -1 || x < 0 || y > game_board.length - 1 || y < 0 || p == null) {
            return;
        }
        game_board[x][y] = p;
    }

    public Piece remove(int x, int y) {
        if (x > game_board.length -1 || x < 0 || y > game_board.length - 1 || y < 0) {
            System.out.println("Out of Bounds.");
            return null;
        }
        if (game_board[x][y] == null) {
            System.out.println("There's no Piece!");
            return null;
        }
        Piece p = game_board[x][y];
        game_board[x][y] = null;
        return p;
    }

    public boolean canEndTurn() {
        if (moved == true || 
            (savedpieces != null && savedpieces.hasCaptured())) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        if (savedpieces != null) {
            savedpieces.doneCapturing();
        }
        moved = false;
        fire_player = !fire_player;
        water_player = !water_player;
        prev_selected = false;
    }

    public String winner() {
        int fire_pieces = 0;
        int water_pieces = 0;
        for (Piece[] counter: game_board) {
            for (Piece piece: counter) {
                if (piece != null) {
                    if (piece.isFire()) {
                        fire_pieces = fire_pieces + 1;
                    } 
                    else if (!piece.isFire()) {
                        water_pieces = water_pieces + 1;
                    }
                }
            }
        }
        if (fire_pieces == 0 && water_pieces == 0) {
            return "No one";
        }
        if (water_pieces == 0) {
            return "Fire";
        }
        if (fire_pieces == 0) {
            return "Water";
        }
        else {
            return null;
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board game_board = new Board(false);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(game_board.winner() == null) {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (game_board.canSelect((int) x, (int) y)) {
                    game_board.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (game_board.canEndTurn()) {
                    game_board.endTurn();
                }
            }         
            game_board.drawBoard(N);
            game_board.winner();
            StdDrawPlus.show(100);
        }
        if (game_board.winner() != null) {
            System.out.println(game_board.winner());
        }
    }
}