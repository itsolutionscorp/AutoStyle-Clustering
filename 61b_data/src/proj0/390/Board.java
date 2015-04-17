public class Board {
    /*Array of arrays. Things are classified by their x-coord, then
     *into their y-coord. */
    private int turn = 0;
    private boolean selected = false;
    private int selected_x;
    private int selected_y;
    private Piece temp;
    private int temp_x;
    private int temp_y;
    private int fire_number;
    private int water_number;
    private boolean moved = false;
    private boolean captured = false;
    private Piece[][] pieces;
    private Board start;
    private boolean shouldBeEmpty;
    private boolean playing = true;

	public static void main(String[] args) {
        Board start = new Board(false);
        start.drawBoard();
        start.drawPieces();
        while (start.playing) {
            if (StdDrawPlus.isSpacePressed()) {
                if (start.canEndTurn()) {
                    if (start.pieceAt(start.selected_x, start.selected_y) != null) {
                        start.pieceAt(start.selected_x, start.selected_y).doneCapturing();
                    } 
                    start.endTurn();
                }
            } else {
                int selectedx = (int) StdDrawPlus.mouseX();
                int selectedy = (int) StdDrawPlus.mouseY();
            if (StdDrawPlus.mousePressed() && start.canSelect(selectedx, selectedy)) {
                start.select((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY());
                if (start.pieceAt((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY()) != null) {
                if (start.pieceAt((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY()).hasCaptured()) {
                    start.pieceAt((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY()).doneCapturing();
                } 
            }
        }
        }
            if (start.winner() != null) {
                start.playing = false;
            }
            start.drawPieces();
            StdDrawPlus.show(25);
        }
    }

    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8];
        if (!shouldBeEmpty) {
            initPieces();
        }
    }

    public Piece pieceAt(int x, int y) {
        if (!onBoard(x, y)) {
            return null;
        }
        if (this.pieces[x][y] == null) {
            return null;
        }
        return this.pieces[x][y];
    }

    public void place(Piece p, int x, int y) {
        if (this.onBoard(x, y)) {
        if ((x >= 0) || (x <= 7) || (y >= 0) || (y <= 7)) {
            this.pieces[x][y] = p;
        }
    }
}

    public Piece remove(int x, int y) {
        if (!this.onBoard(x, y)) {
            System.out.println("Piece coordinates out of bounds!");
            return null;
        }
        if (this.pieces[x][y] == null) {
            System.out.println("There is no piece at those coordinates!");
            return null;
        }
        Piece out;
        out = this.pieces[x][y];
        this.pieces[x][y] = null;
        return out;
    }

    public boolean canEndTurn() {
        if (this.moved) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        this.turn = (1 - this.turn);
        this.changeSelected();
        this.selected = false;
        this.moved = false;
        this.captured = false;
        if (this.pieceAt(selected_x, selected_y) != null) {
            this.pieceAt(selected_x, selected_y).doneCapturing();
        }
    }

	private void drawBoard() {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
            }
        StdDrawPlus.show();
    }

    private void initPieces() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0) {
                    if (i % 2 == 0) {
                        this.pieces[i][j] = new Piece(true, this, i, j, "pawn");
                    }
                }
                if (j == 1) {
                    if (i % 2 != 0) {
                        this.pieces[i][j] = new Piece(true, this, i, j, "shield");
                    }
                }
                if (j == 2) {
                    if (i % 2 == 0) {
                        this.pieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }
                }
                if (j == 5) {
                    if (i % 2 != 0) {
                        this.pieces[i][j] = new Piece(false, this, i, j, "bomb");
                    }
                }
                if (j == 6) {
                    if (i % 2 == 0) {
                        this.pieces[i][j] = new Piece(false, this, i, j, "shield");
                    }
                }
                if (j == 7) {
                    if (i % 2 != 0) {
                        this.pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }
                }
            }
        }
    } 

    // private void drawPieces(Board b) {
    //     for (int i = 0; i < 8; i++) {
    //         for (int j = 0; j < 8; j++) {
    //             if (j == 0) {
    //                 if (i % 2 == 0) {
    //                     StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
    //                     pieces[i][j] = new Piece(true, b, i, j, "pawn");
    //                 }
    //             }
    //             if (j == 1) {
    //                 if (i % 2 != 0) {
    //                     StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
    //                     pieces[i][j] = new Piece(true, b, i, j, "shield");
    //                 }
    //             }
    //             if (j == 2) {
    //                 if (i % 2 == 0) {
    //                     StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
    //                     pieces[i][j] = new Piece(true, b, i, j, "bomb");
    //                 }
    //             }
    //             if (j == 5) {
    //                 if (i % 2 != 0) {
    //                     StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
    //                     pieces[i][j] = new Piece(false, b, i, j, "bomb");
    //                 }
    //             }
    //             if (j == 6) {
    //                 if (i % 2 == 0) {
    //                     StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
    //                     pieces[i][j] = new Piece(false, b, i, j, "shield");
    //                 }
    //             }
    //             if (j == 7) {
    //                 if (i % 2 != 0) {
    //                     StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
    //                     pieces[i][j] = new Piece(false, b, i, j, "pawn");
    //                 }
    //             }
    //         }
    //     }
    // }

    private void drawPieces() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.temp = this.pieceAt(i, j);
                if (this.temp != null) {
                    if (this.temp.isFire()) {
                        if (this.temp.isBomb()) {
                            if (this.temp.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        }
                        }
                        else if (this.temp.isShield()) {
                            if (this.temp.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        }
                        }
                        else {
                            if (this.temp.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                            else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                    }
                }
                    else {
                        if (this.temp.isBomb()) {
                            if (this.temp.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        }
                    }
                        else if (this.temp.isShield()) {
                            if (this.temp.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        }
                        }
                        else {
                            if (this.temp.isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                        }
                    }                        
                }
                if (this.temp == null) {
                    if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5); 
                }
                }
                }   
                } 

    public boolean canSelect(int x, int y) {
        if (!this.onBoard(x, y)) {
            return false;
        }
        this.temp = this.pieceAt(x, y);
        if (this.temp != null) {
            if (turn == 0) {
                if (!this.temp.isFire()) {
                    return false;
                }
            } else if (this.temp.isFire()) {
                return false;
            }
            if (this.pieceAt(this.selected_x, this.selected_y) != null) {
                if ((this.selected && !this.moved) && this.temp.isFire() != (this.pieceAt(this.selected_x, this.selected_y)).isFire()) {
                    return false;
                }
            }
            if ((!this.selected) || (this.selected && (!this.moved))) {
                return true;
            }
        }
        else if ((((this.selected && !this.moved)) && ((this.isValidMove(x, y)) || (this.isValidCapture(x, y))))) {
                return true;
            }
        
        else if ((this.selected && this.captured) && (this.isValidCapture(x, y))) {
            return true;
        }
        return false;
    }

    public void select(int x, int y) {
        temp_x = selected_x;
        temp_y = selected_y;
        if (this.onBoard(x, y)) {
        if (this.pieceAt(x, y) != null) {
            if (this.selected) {
                this.changeSelected();
            }
            if (this.turn == 0) {
                if (this.pieceAt(x, y).isFire()) {
                    this.selected = true;
                    this.selected_x = x;
                    this.selected_y = y;
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                } 
            }
            if (this.turn == 1) {
                if (!this.pieceAt(x, y).isFire()) {
                    this.selected = true;
                    this.selected_x = x;
                    this.selected_y = y;
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                } 
        }
        } else if (this.selected) {
                    this.selected_x = x;
                    this.selected_y = y;
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    }
    if (this.selected) {
        this.captured = this.isValidCapture(this.selected_x, this.selected_y);
        if (this.captured || this.isValidMove(this.selected_x, this.selected_y)) {
            this.pieceAt(this.temp_x, this.temp_y).move(this.selected_x, this.selected_y);
            this.temp_x = this.selected_x;
            this.temp_y = this.selected_y;
            this.moved = true;
}
}
}
}

    private void changeSelected() {
        if (this.selected) {
                if ((this.selected_x + this.selected_y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(selected_x + .5, selected_y + .5, .5);      
        }
    }

    private boolean isValidMove(double x, double y) {
        if (this.onBoard((int) x, (int) y)) {
        this.temp = this.pieceAt(this.temp_x, this.temp_y);
        if (this.temp != null) {
        if (this.temp.isKing() || this.temp.isFire()) {
            if (this.temp_y == (int) y - 1) {
                if (this.temp_x == (int) x - 1) {
                    if (this.pieceAt(this.temp_x + 1, this.temp_y + 1) == null) {
                        return true;
                }
            } else if (this.temp_x == (int) x + 1) {
                if (this.pieceAt(this.temp_x - 1, this.temp_y + 1) == null){
                    return true;
                }
            }
        }
    } if (this.temp.isKing() || (!this.temp.isFire())) {
        if (this.temp_y == (int) y + 1) {
            if (this.temp_x == (int) x - 1) {
                if (this.pieceAt(this.temp_x + 1, this.temp_y - 1) == null) {
                    return true;
                }
            } else if (this.temp_x == (int) x + 1) {
                if (this.pieceAt(this.temp_x - 1, this.temp_y - 1) == null) {
                    return true;
                }
            }
        }
    }
}
}
        return false;
    }

    private boolean isValidCapture(double x, double y) {
        if (this.onBoard((int) x, (int) y)) {
            Piece temp = this.pieceAt(this.temp_x, this.temp_y);
            if (this.pieceAt((int)x, (int)y) == null) {
                if (temp != null) {
                    if (temp.isFire() && !temp.isKing()) {
                        if (this.temp_y == (int) y - 2) {
                            if (this.temp_x == (int) x - 2) {
                                if (((this.pieceAt(this.temp_x + 1, this.temp_y + 1) != null)) && (!this.pieceAt(this.temp_x + 1, this.temp_y + 1).isFire())) {
                                    return true;
                }
            } else if (this.temp_x == (int) x + 2) {
                if (((this.pieceAt(this.temp_x - 1, this.temp_y + 1) != null)) && (!this.pieceAt(this.temp_x - 1, this.temp_y + 1).isFire())) {
                    return true;
                }
            }
        } 
} if (!temp.isFire() && !temp.isKing()) {
    if (this.temp_y == (int) y + 2) {
            if (this.temp_x == (int) x - 2) {
                if (((this.pieceAt(this.temp_x + 1, this.temp_y - 1) != null)) && (this.pieceAt(this.temp_x + 1, this.temp_y - 1).isFire())) {
                    return true;
                }
            } else if (this.temp_x == (int) x + 2) {

                //System.out.println(this.pieceAt(this.temp_x - 1, this.temp_y - 1) != null);
                if (((this.pieceAt(this.temp_x - 1, this.temp_y - 1) != null)) && (this.pieceAt(this.temp_x - 1, this.temp_y - 1).isFire())) {
                    return true;
                }
            }
        }
    }
    if (temp.isKing()) {
        if (this.temp_y == (int) y - 2) {
            if (this.temp_x == (int) x - 2) {
                if (((this.pieceAt(this.temp_x + 1, this.temp_y + 1) != null)) && ((this.pieceAt(this.temp_x + 1, this.temp_y + 1).isFire() != temp.isFire()))) {
                    return true;
                }
            } else if (this.temp_x == (int) x + 2) {
                if (((this.pieceAt(this.temp_x - 1, this.temp_y + 1) != null)) && ((this.pieceAt(this.temp_x - 1, this.temp_y + 1).isFire() != temp.isFire())))  {
                    return true;
            }
        }
    }
            if (this.temp_y == (int) y + 2) {
            if (this.temp_x == (int) x - 2) {
                if (((this.pieceAt(this.temp_x + 1, this.temp_y - 1) != null)) && ((this.pieceAt(this.temp_x + 1, this.temp_y - 1).isFire() != temp.isFire())))  {
                    return true;
                }
            } else if (this.temp_x == (int) x + 2) {
                if (((this.pieceAt(this.temp_x - 1, this.temp_y - 1) != null)) && ((this.pieceAt(this.temp_x - 1, this.temp_y - 1).isFire() != temp.isFire()))) {
                    return true;
    }
}
}
}
}
}
}
        return false;
    }      

    private int checkVictory() {
        this.fire_number = 0;
        this.water_number = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.temp = this.pieceAt(i, j);
                if (this.temp != null) {
                    if (this.temp.isFire()) {
                        this.fire_number += 1;
                    } 
                    else {
                        this.water_number += 1;
                    }
            }
        }
    }
        if ((this.water_number == 0) && (this.fire_number > 0)) {
            return 0;
        } else if ((this.fire_number == 0) && (this.water_number > 0)) {
            return 1;
        } else if ((this.fire_number == 0) && (this.water_number == 0)) {
            return 2;
        }
        else if (this.playing || this.fire_number == this.water_number) {
            return 3;
        }
    else {
        return 3;
    }
}

    public String winner() {
        int result = this.checkVictory();
        if (result == 0) {
            this.changeSelected();
            return "Fire";
        } else if (result == 1) {
            this.changeSelected();
            return "Water";
        } else if (result == 2) {
            this.changeSelected();
            return "No one";
        } else {
            return null;
        }
    }

    private static boolean onBoard(int x, int y) {
        if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
            return false;
        }
        return true;
    }
}