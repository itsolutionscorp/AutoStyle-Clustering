
public class Board {
    /** Location and identity of pieces. */
    private boolean[][] pieces = new boolean[8][8];
    private Piece[][] pieceID = new Piece[8][8];
    
    // 0 for fire, 1 for water
    private int whos_turn = 0;
    
    // Piece Selection
    private Piece selected;
    private boolean piece_selection_made = false;
    private boolean new_selection_made = false;
    private int selected_x;
    private int selected_y;
    private boolean has_moved = false;
    private boolean was_bomb = false;
    private boolean just_kinged = false;

    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty) {
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 0 && j != 3 && j != 4) {
                        this.pieces[i][j] = true;
                        if (j == 0) {
                            this.pieceID[i][j] = new Piece(true, this, i, j, "pawn");
                        }
                        if (j == 1) {
                            this.pieceID[i][j] = new Piece(true, this, i, j, "shield");
                        }
                        if (j == 2) {
                            this.pieceID[i][j] = new Piece(true, this, i, j, "bomb");
                        }
                        if (j == 5) {
                            this.pieceID[i][j] = new Piece(false, this, i, j, "bomb");
                        }
                        if (j == 6) {
                            this.pieceID[i][j] = new Piece(false, this, i, j, "shield");
                        }
                        if (j == 7) {
                            this.pieceID[i][j] = new Piece(false, this, i, j, "pawn");
                        }
                    }
                }
            }           
        }
    }

    private void drawBoard() {
        String team;
        String kinged;
        String type;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) { 
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (piece_selection_made) {
                    StdDrawPlus.filledSquare(selected_x + .5, selected_y + .5, .5);
                }
                if (pieces[i][j]) {
                    Piece p = pieceID[i][j];
                    if (p.isBomb()) {
                        type = "bomb";
                    } else {
                        if (p.isShield()) {
                           type = "shield";
                        } else {
                           type = "pawn";
                        }
                    }    
                    if (p.isFire()) {
                        team = "fire";
                    } else {
                        team = "water";
                    }
                    if (p.isKing()) {
                        kinged = "-crowned";
                    } else {
                        kinged = "";
                    }
                    String image_string = "img/" + type + "-" + team + kinged + ".png";   
                    StdDrawPlus.picture(i + 0.5, j + 0.5, image_string, 1, 1);                    
                }
            } 
        }
    }    

    public Piece pieceAt(int x, int y) {
        if (0 <= x && x <= 7 && 0 <= y && y <= 7) {
            if (pieces[x][y]) {
                return pieceID[x][y];
            } return null;
        } return null;
    }

    public void place(Piece p, int x, int y) {
        if (p != null && x >= 0 && x <= 7 && y >= 0 && y <= 7) {
            pieces[x][y] = true;
            pieceID[x][y] = p;
        }
    }

    public boolean canSelect(int x, int y) {
        if (0 <= x && x <= 7 && 0 <= y && y <= 7 && !was_bomb) {
            if (piece_selection_made) {                       
                if (pieces[x][y] && !selected.hasCaptured() && !has_moved) {
                    Piece p = pieceID[x][y];
                    if (p.side() == selected.side()) {
                        if (validMove(p,x,y,x-2,y+2) || validMove(p,x,y,x-2,y-2) 
                            || validMove(p,x,y,x+2,y+2) || validMove(p,x,y,x+2,y-2)
                            || validMove(p,x,y,x+1,y+1) || validMove(p,x,y,x+1,y-1)
                            || validMove(p,x,y,x-1,y+1) || validMove(p,x,y,x-1,y-1)) {
                            new_selection_made = true;
                            return true;
                        }  
                    } return false;  
                } else {
                    new_selection_made = false;   
                    return validMove(selected, selected_x, selected_y, x, y);
                }      
            } else {
                if (pieces[x][y]) {
                    Piece p = pieceID[x][y];
                    if (p.side() == whos_turn) {
                        return (validMove(p,x,y,x-2,y+2) || validMove(p,x,y,x-2,y-2) 
                            || validMove(p,x,y,x+2,y+2) || validMove(p,x,y,x+2,y-2)
                            || validMove(p,x,y,x+1,y+1) || validMove(p,x,y,x+1,y-1)
                            || validMove(p,x,y,x-1,y+1) || validMove(p,x,y,x-1,y-1));                   
                    } 
                }
            } return false;
        } return false;
    }                


    private boolean validMove(Piece p, int xi, int yi, int xf, int yf) {
        if (xf >= 0 && xf <= 7 && yf >= 0 && yf <= 7) {
        if (!pieces[xf][yf]) {
            if (p.isKing()) {
                if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1 && !p.hasCaptured()) {
                    if (!just_kinged) {
                        if (!pieces[xf][yf]) {
                            return true;
                        } else {
                            just_kinged = false;
                            return false;
                        }
                    }   
                }    
                if (Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2) {
                    if (pieces[Math.min(xi, xf) + 1][Math.min(yi, yf) + 1]) {
                        if (pieceID[Math.min(xi, xf) + 1][Math.min(yi, yf) + 1].side() != p.side()) {
                            return true;
                        } 
                    } 
                }
            } else {    
            if (p.isFire()) {
                if (Math.abs(xf - xi) == 1 && yf - yi == 1 && !p.hasCaptured()) {
                    return true;
                    }               
                if (xi - xf == 2 && yf - yi == 2) {
                    if (pieces[xf + 1][yi + 1]) {
                        if (pieceID[xf + 1][yi + 1].side() != p.side()) {
                            return true;
                        }
                    }
                }                    
                if (xi - xf == -2 && yf - yi == 2) {
                    if (pieces[xf - 1][yi + 1]) {
                        if (pieceID[xf - 1][yi + 1].side() != p.side()) {
                            return true;
                        }
                    }
                }
            }    
            if (!(p.isFire())) {
                if (Math.abs(xi - xf) == 1 && yf - yi == -1 && !p.hasCaptured()) {
                    return true;
                }
                if (xi - xf == 2 && yf - yi == -2) {
                    if (pieces[xf + 1][yi - 1]) {
                        if (pieceID[xf + 1][yi - 1].side() != p.side()) {
                            return true;
                        }
                    }
                }    
                if (xi - xf == -2 && yf - yi == -2) {
                    if (pieces[xf - 1][yi - 1]) {
                        if (pieceID[xf - 1][yi - 1].side() != p.side()) {
                            return true;
                        }
                    }
                }              
            }
        } return false;
        } return false;      
        } return false;
    }

    public void select(int x, int y) {
        if (!(piece_selection_made) || new_selection_made) {
            selected = pieceID[x][y];
            selected_x = x;
            selected_y = y;
            piece_selection_made = true;
            new_selection_made = false;
        } else {
            selected.move(x,y);    
            if (Math.abs(x - selected_x) == 1) {
                if (selected.isFire() && y == 7 || !selected.isFire() && y == 0) {
                    just_kinged = true;
                }
                has_moved = true;
                selected_x = x;
                selected_y = y;
            }         
            if (selected.isBomb() && (Math.abs(x - selected_x) == 2)) {
                was_bomb = true;
                selected_x = x;
                selected_y = y;
                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
                            if (pieces[i][j]) {
                                if (!(pieceID[i][j].isShield())) {
                                    remove(i, j);                                
                                }
                            }
                        }
                    }
                } 
            }
            if (Math.abs(x - selected_x) == 2) {
                if (selected.isFire() && y == 7 || !selected.isFire() && y == 0) {
                    just_kinged = true;
                }
                selected_x = x;
                selected_y = y;
            }
        }   
    }        

    public Piece remove(int x, int y) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
            if (pieces[x][y]) {
                pieces[x][y] = false;
                Piece return_piece = pieceID[x][y];
                pieceID[x][y] = null;
                return return_piece;
            } else {
                System.out.println("no piece at coordinates " + x + " " + y);
                return null;
            }    
        } else {
            System.out.println("index out of bounds");
            return null;
        }
    }

    public boolean canEndTurn() {
        if (was_bomb) {
            return true;
        } else {   
            if (selected.hasCaptured() || has_moved) {
                return true;
            } 
        } return false;   
    }    
    

    public void endTurn() {
        new_selection_made = false;
        piece_selection_made = false;
        was_bomb = false;
        just_kinged = false;
        if (winner() != null) {
            System.out.println(winner() + " Wins!");
        }
        selected.doneCapturing();
        has_moved = false;
        if (whos_turn == 0) {
            whos_turn = 1;
        } else {
            whos_turn = 0;
        }
    }

    public String winner() {
        boolean fire_has_pieces = false;
        boolean water_has_pieces = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j]) {
                    if ((pieceID[i][j]).side() == 0) {
                        fire_has_pieces = true;
                    } else {
                        water_has_pieces = true;
                    }
                }
            }
        }
        if (fire_has_pieces && !water_has_pieces) {
            return "Fire";
        }        
        if (water_has_pieces && !fire_has_pieces) {
            return "Water";
        }
        if (water_has_pieces && fire_has_pieces) {
            return null;
        } else {
            return "No one";
        }
    }

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int r = (int) Math.round(x);
                int s = (int) Math.round(y);
                if (b.canSelect(r, s)) {
                    b.select(r, s);
                }
            }            
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                    if (b.winner() != null) {
                        break;
                    }
                }
            }
            StdDrawPlus.show(100);
        }
    }
}
        