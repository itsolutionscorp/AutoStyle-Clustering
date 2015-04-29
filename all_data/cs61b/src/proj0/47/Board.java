public class Board {
    private Piece[][] pieces;
    private boolean empty;
    private Piece selected;
    private int team; 
    private Piece moved_piece;
    private int bound;
    private int x;
    private int y;


	public Board(boolean shouldBeEmpty) {
        empty = shouldBeEmpty;
        selected = null;
        moved_piece = null; 
        team = 0;
        pieces = new Piece[8][8];
        bound = 8;
        if (!empty) {
            this.default_config(8);
        }

	}

	public Piece pieceAt(int x, int y) {
        if ((x >= bound || y >= bound || x < 0 || y < 0) || pieces[x][y] == null) {
            return null;
        }
        return pieces[x][y];
	}


    public void place(Piece p, int x, int y) {
        if (p == null || (x >= bound || y >= bound || x < 0 || y < 0)) {
            return;
        }
        else {
            int[] old_location = location(p);
            if (old_location[0] >= 0 && old_location[0] < bound && old_location[1] >= 0 && old_location[1] < bound) {
                remove(old_location[0], old_location[1]);
            }
            if (pieceAt(x, y) != null) {
                remove(x, y);
            }
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if (pieceAt(x, y) == null) {
            System.out.println("No piece at (" + x + ", " + y + ")");
            return null;
        }
        else if (x < 0 || x >= bound || y < 0 || y >= bound) {
            System.out.println("(" + x + ", " + y + ") out of bound");
            return null;
        }
        Piece removed_piece = pieces[x][y];
        pieces[x][y] = null;
        return removed_piece;
    } 

    public boolean canSelect(int x, int y) {
        if (x < 0 || x >= bound || y < 0 || y >= bound) {
            return false;
        }
        if (pieceAt(x, y) != null && pieceAt(x, y).side() == team) {
            if (selected == null && moved_piece == null) {
                return true;
            }
            if (selected != null && moved_piece == null) {
                return true;
            }
        }
        else if (pieceAt(x, y) == null && selected != null) {
            int[] piece_location = location(selected);
            if (moved_piece == null && validMove(piece_location[0], piece_location[1], x, y)) {
                return true;
            }
            else if (selected.hasCaptured() && validMove(piece_location[0], piece_location[1], x, y)) {
                return true;
            }
            
        }
        return false;
    }
    
    
    public void select(int x, int y) {
        if (pieceAt(x, y) == null && selected != null) {
            if (selected.hasCaptured()) {
                selected.move(x, y);
            }
            else {
                moved_piece = selected;
                selected.move(x, y);
                selected = pieceAt(x, y);
            }
        }
        else {
            selected = pieceAt(x,y);
        }
    }
    


    public boolean canEndTurn() {
        if (selected != null) {
            return (moved_piece != null || selected.hasCaptured());
        }
        return (moved_piece != null); 
    }

    public void endTurn() {
        if (moved_piece != null) {
            moved_piece.doneCapturing();
        }
        selected = null; 
        moved_piece = null;
        team = 1 - team;
    }


	private boolean validMove(int xi, int yi, int xf, int yf) {
        if (xi < 0 || xi >= bound || yi < 0 || yi >= bound || xf < 0 || xf >= bound || yf < 0 || yf >= bound) {
            return false;
        }
        if (pieceAt(xf, yf) != null) {
            return false;
        }

        if (pieceAt(xi, yi).isKing()) {
            if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1 && !pieceAt(xi, yi).hasCaptured()) {
                return true;
            }
            // if ((Math.abs(xf - xi) != 2) || Math.abs(yf - yi) != 2) {
            //     return false; 
            // }
            else if (xf == xi + 2 && yf == yi + 2) {
                if (pieceAt(xi + 1, yi + 1) != null && (pieceAt(xi, yi).side() - pieceAt(xi + 1, yi + 1).side() != 0)) {
                    return true;
                }
            }
            else if (xf == xi + 2 && yf == yi - 2) {
                if (pieceAt(xi + 1, yi - 1) != null && (pieceAt(xi, yi).side() - pieceAt(xi + 1, yi - 1).side() != 0)) {
                    return true;
                }
            }
            else if (xf == xi - 2 && yf == yi - 2) {
                if (pieceAt(xi - 1, yi - 1) != null && (pieceAt(xi, yi).side() - pieceAt(xi - 1, yi - 1).side() != 0)) {
                    return true;
                }
            }
            else if (xf == xi - 2 && yf == yi + 2) {
                if (pieceAt(xi - 1, yi + 1) != null && (pieceAt(xi, yi).side() - pieceAt(xi - 1, yi + 1).side() != 0)) {
                    return true;
                }
            }
        }

        else if (pieceAt(xi, yi).side() == 0) {
            if ((yf - yi) < 0) {
                return false;
            }
            if (Math.abs(xf - xi) == 1 && (yf - yi == 1) && !pieceAt(xi, yi).hasCaptured()) {
                return true;
            } 
            if ((yf - yi) == 2 && (xf - xi) == 2) {
                if ((pieceAt(xi + 1, yi + 1) != null) && (pieceAt(xi, yi).side() - pieceAt(xi + 1, yi + 1).side() != 0)) {
                    return true;
                }
            }
            if ((yf - yi) == 2 && (xf - xi) == -2) {
                if (pieceAt(xi - 1, yi + 1) != null && (pieceAt(xi, yi).side() - pieceAt(xi - 1, yi + 1).side() != 0)) {
                    return true;
                }
            }
        }

    
        else if (pieceAt(xi, yi).side() == 1) {
            if ((yf - yi) > 0) {
                return false;
            }
            if (Math.abs(xf - xi) == 1 && (yi - yf == 1) && !pieceAt(xi, yi).hasCaptured()) {
                return true;
            }
            if ((yf - yi) == -2 && (xf - xi) == 2) {
                if (pieceAt(xi + 1, yi - 1) != null && (pieceAt(xi, yi).side() - pieceAt(xi + 1, yi - 1).side() != 0)) {
                    return true;
                }
            }
            if ((yf - yi) == -2 && (xf - xi) == -2) {
                if (pieceAt(xi - 1, yi - 1) != null && (pieceAt(xi, yi).side() - pieceAt(xi - 1, yi - 1).side() != 0)) {
                    return true;
                }
            }
        }
                
        return false; 
	}

    public String winner() {
        int fire_count = 0;
        int water_count = 0;
        for (int i = 0; i < bound; i += 1) {
            for (int j = 0; j < bound; j += 1) {
                if (pieceAt(i, j) != null) {
                    if (pieceAt(i, j).side() == 0) {
                        fire_count += 1;
                    }
                    else {
                        water_count += 1;
                    }
                    
                }
            }
        }
        if (fire_count == 0 && water_count == 0) {
            return "No one";
        }
        else if (fire_count == 0) {
            return "Water";
        }
        else if (water_count == 0) {
            return "Fire";
        }
        return null;
    }

    private int[] location(Piece p) {
        int a = -1;
        int b = -1;
        for (int i = 0; i < bound; i += 1) {
            for (int j = 0; j < bound; j += 1) {
                if (pieceAt(i, j) == p) {
                    a = i;
                    b = j;
                }
            }
        }
        return new int[]{a, b};
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (selected != null && i == location(selected)[0] && j == location(selected)[1]) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE); 
                }
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) { 
                    if (pieces[i][j].side() == 0) {
                        if (pieces[i][j].isBomb()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                        }
                        else if (pieces[i][j].isShield()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                        }
                        else {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1); 
                            }
                        }
                    }

                    else {
                        if (pieces[i][j].isBomb()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                        }
                        else if (pieces[i][j].isShield()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                        }
                        else {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1); 
                            }
                        }
                    }
                }
            }
        }
    }	

    private void default_config(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i % 2 == 0 && j == 0) {
                    pieces[i][j] = new Piece(true, this, i, j, "pawn");
                }
                else if (i % 2 != 0 && j == 1) {
                    pieces[i][j] = new Piece(true, this, i, j, "shield");
                }
                else if (i % 2 == 0 && j == 2) {
                    pieces[i][j] = new Piece(true, this, i, j, "bomb");
                }
                else if (i % 2 != 0 && j == 5) {
                    pieces[i][j] = new Piece(false, this, i, j, "bomb");
                }
                else if (i % 2 == 0 && j == 6) {
                    pieces[i][j] = new Piece(false, this, i, j, "shield");
                }
                else if (i % 2 != 0 && j == 7) {
                    pieces[i][j] = new Piece(false, this, i, j, "pawn");
                }           
            }

        }   
    }

    private void empty_config(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }
	
	public static void main(String[] args) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board game_board = new Board(false); 
        game_board.bound = N;
        game_board.pieces = new Piece[N][N];
        
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            game_board.x = -1;
            game_board.y = -1; 
            game_board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                game_board.x = (int) StdDrawPlus.mouseX();
                game_board.y = (int) StdDrawPlus.mouseY();

            }
            if (game_board.canSelect(game_board.x, game_board.y)) {
                game_board.select(game_board.x, game_board.y);
            }

            if (StdDrawPlus.isSpacePressed()) {
                if (game_board.canEndTurn()) {
                    game_board.endTurn();
                }
            }
            StdDrawPlus.show(25);
		}
	}

}