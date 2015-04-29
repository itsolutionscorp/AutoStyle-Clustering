
public class Board {
    private Piece[][] pieces = new Piece[8][8];
    private int player = 0;
    private Piece selected;
    private int selectedintx;
    private int selectedinty;

    public static void main(String[] args) {
        Board bobo = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        bobo.draw();
        while (bobo.winner() == null); {
            if (StdDrawPlus.mousePressed()) {
                int mousexcoord = (int)StdDrawPlus.mouseX();
                int mouseycoord = (int)StdDrawPlus.mouseY();
                while (bobo.canSelect(mousexcoord, mouseycoord)) {   
                    bobo.select(mousexcoord, mouseycoord);
                    bobo.draw();
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                bobo.endTurn();
            }
        }
    }

    private void draw() {
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        if (pieces[i][j].isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 
                                1, 1);
                        }
                        else if (pieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 
                                1, 1);
                        }
                        else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 
                                1, 1);
                        }
                    }
                    else if (!pieces[i][j].isFire()) {
                        if (pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 
                                    1, 1);
                            }
                            else if (pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 
                                    1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 
                                    1, 1);
                            }
                    }
                }           
               }
        } 

    }



    public Board(boolean shouldBeEmpty) {
        if (!shouldBeEmpty) {
            for (int i = 0; i < 8; i += 1) {
                for (int j = 0; j < 8; j += 1) {                
                        if (i == 0 && j % 2 == 0) {
                        pieces[j][i] = new Piece(true, this, j, i, "pawn");
                        }

                        else if (i == 1 && j % 2 != 0) {
                            pieces[j][i] = new Piece(true, this, j, i, "shield");
                        }

                        else if (i == 2 && j % 2 == 0) {
                            pieces[j][i] = new Piece(true, this, j, i, "bomb");
                        }

                        else if (i == 5 && j % 2 != 0) {
                            pieces[j][i] = new Piece(false, this, j, i, "bomb");
                        }

                        else if (i == 6 && j % 2 == 0) {
                            pieces[j][i] = new Piece(false, this, j, i, "shield");
                        }

                        else if (i == 7 && j % 2 != 0) {
                            pieces[j][i] = new Piece(false, this, j, i, "pawn");
                        }

                    }
                    }
                }
            }

    private boolean validPieceChosen(int x, int y) {
        if (pieceAt(x, y) != null) {
            if (player == 0) {
                if (pieceAt(x, y).isFire()) {
                    return true;
                }
            return false;
            }

            else if (player == 1) {
                if (!pieceAt(x, y).isFire()) {
                    return true;
                }
            return false;
            }
            else {
                return false;
            }
        }
        return false;

    }

    private boolean validMove(int ix, int iy, int fx, int fy) {
        if (player == 0) { 
            if (iy + 1 == fy) { 
                if (ix + 1 == fx || ix - 1 == fx) {
                    return true;
                }
            }
            else if (iy + 2 == fy) {
                if (ix + 2 == fx) {
                    if (!validPieceChosen(ix + 1, 
                    iy + 1)) {
                        return true;        
                    }
                }
                    else if (ix - 2 == fx) {
                        if (!validPieceChosen(ix - 1, 
                            iy + 1)) {
                            return true;
                        }
                    } 
            }
            return false;
            }
        

        else if (player == 1) { 
            if (iy - 1 == fy) {
                if (ix + 1 == fx || ix - 1 == fx) {
                    return true;
                }
            }
            
            if (iy - 2 == fy) {
                if (ix + 2 == fx) {
                    if (!validPieceChosen(ix + 1, 
                        iy - 1)) {
                        return true; 
                    }
                }
                else if (ix - 2 == fx) {
                    if (!validPieceChosen(ix - 1, 
                        iy - 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean canSelect(int x, int y) {
            if (validPieceChosen(x, y) && selected == null) {
                return true;
            } 
            if (selected != null && validMove(selectedintx, selectedinty, x, y)) {
                return true;
            }           
            return false;
    }
     
    public void select(int x, int y) {
        if (selected == null) {
            selected = pieceAt(x, y);
            selectedintx = x;
            selectedinty = y;
        }
        else {      
            selected.move(x, y);
        }    
    }            
    
    private void changePlayer() {
        if (player > 0){
            player += 1;
        }
        player -= 1;
    }
   
    public Piece pieceAt(int x, int y) {
        
        if (x > 7 || y > 7) {
            return null;
        }

        else if (pieces[x][y] != null) {
            return pieces[x][y];
        }
        return null;
    }

    public void place(Piece p, int x, int y) {
        if (p == null) {
            return;
        }   
        if (x < 8 && y < 8) {
            if (pieceAt(x, y) != null) {
                remove(x, y);
            }
            pieces[x][y] = p;

        }

        else {
            return;
        }
    }

    public Piece remove(int x, int y) {
        if (x > 7 || y > 7) {
            System.out.println("there's nothing out there, dummy");
            return null;
        }
        else if (pieceAt(x, y) != null) {
            Piece byebye = pieceAt(x, y);
            pieces[x][y] = null;                
            return byebye;
        }
        System.out.println("sorry, dude. you just missed him.");
        return null;
        }

        public String winner() {
            int fire = 0;
            int water = 0;
            for (int i = 0; i < 8; i += 1) {
                for (int j = 0; j < 8; j += 1) {
                    if (pieces[i][j] != null) {
                        if (pieceAt(i, j).isFire()) {
                            fire += 1;
                        }   
                        else {
                            water += 1;
                        }
                    }
                }
            }
            if (water == 0 && fire == 0) {
                return "No one";
            }

            else if (water == 0 && fire > 0) {
                return "Fire";
            }

            else if (water > 0 && fire == 0) {
                return "Water";
            }

            else {
                return null;
            }
        }

    public boolean canEndTurn() {
        if (pieceAt(selectedintx, selectedinty) == null || 
            selected.hasCaptured()) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        if (canEndTurn()) {
            changePlayer();
            selected.doneCapturing();
            selected = null;
        } 

    }


}