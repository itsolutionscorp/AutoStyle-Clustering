

public class Board {

    private static boolean[][] pieces;
    private Piece[][] allPieces = new Piece[8][8];
    private int x, y;
    private int iX, iY, cX, cY;
    private Piece p;
    private int player = 0;
    private boolean store = false;
    private boolean move = false;
    //private String bomb, shield, pawn, king;
    //private static Board b;


    public static void main(String[] args) { 
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new boolean[N][N];
        boolean shouldBeEmpty = false;
        Board b = new Board(shouldBeEmpty); // true = empty; false = normal config
        //b.endturn();
        //Piece p = new Piece(true, b, 0, 1, null);
        //b.allPieces[0][1] = p;
        //Piece curr = b.allPieces[0][1];
        //System.out.println(b.noPiece(0,1));
        //System.out.println(curr.type);
        //while(true) { // make pieces visible on the board
        if (shouldBeEmpty == true) {
            b.drawBoard(N);
        } else {
            b.drawBoard(N);
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++) {
                        //pieces[x][y] = true;
                    if ((j == 0) && (i % 2 == 0)) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    }
                    if ((j == 1) && (i % 2 == 1)) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    }
                    if ((j == 2) && (i % 2 == 0)) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    }
                    if ((j == 7) && (i % 2 == 1)) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    }
                    if ((j == 6) && (i % 2 == 0)) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    }
                    if ((j == 5) && (i % 2 == 1)) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    }
                }
            }     
            StdDrawPlus.show(100);            
        }
        
        while (true) {
            b.drawBoard(N);
            b.winner();
            if(StdDrawPlus.mousePressed()) {
                int i =(int) StdDrawPlus.mouseX();
                int j =(int) StdDrawPlus.mouseY();
                if (b.canSelect(i, j)) {
                    pieces[i][j] = true;
                    b.select(i, j);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
        }
        //}
        //starts a GUI supported version of the game.
    }

	private void drawBoard(int N) { // good

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    } //from the demo
    // TASK 1

    public Board(boolean shouldBeEmpty) { // good
        //int N = 8;

    	if (shouldBeEmpty == true) {   //initializes an empty Board.        
            // do nothing

    	} else { // initializes a normal config

            p = new Piece(true, this, 0, 0, "pawn");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/pawn-fire.png", 1, 1);
            allPieces[0][0] = p; // 1 fire p
            p = new Piece(true, this, 2, 0, "pawn");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/pawn-fire.png", 1, 1);
            allPieces[2][0] = p; // 2 fire p
            p = new Piece(true, this, 4, 0, "pawn");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/pawn-fire.png", 1, 1);
            allPieces[4][0] = p; // 3 fire p
            p = new Piece(true, this, 6, 0, "pawn");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/pawn-fire.png", 1, 1);
            allPieces[6][0] = p; // 4 fire p
            p = new Piece(true, this, 1, 1, "shield");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/shield-fire.png", 1, 1);
            allPieces[1][1] = p; // 1 fire s
            p = new Piece(true, this, 3, 1, "shield");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/shield-fire.png", 1, 1);
            allPieces[3][1] = p; // 2 fire s
            p = new Piece(true, this, 5, 1, "shield");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/shield-fire.png", 1, 1);
            allPieces[5][1] = p; // 3 fire s
            p = new Piece(true, this, 7, 1, "shield");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/shield-fire.png", 1, 1);
            allPieces[7][1] = p; // 4 fire s
            p = new Piece(true, this, 0, 2, "bomb");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/bomb-fire.png", 1, 1);
            allPieces[0][2] = p; // 1 fire b
            p = new Piece(true, this, 2, 2, "bomb");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/bomb-fire.png", 1, 1);
            allPieces[2][2] = p; // 2 fire b
            p = new Piece(true, this, 4, 2, "bomb");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/bomb-fire.png", 1, 1);
            allPieces[4][2] = p; // 3 fire b
            p = new Piece(true, this, 6, 2, "bomb");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/bomb-fire.png", 1, 1);
            allPieces[6][2] = p; // 4 fire b

            p = new Piece(false, this, 1, 7, "pawn");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/pawn-water.png", 1, 1);
            allPieces[1][7] = p; // 1 water p
            p = new Piece(false, this, 3, 7, "pawn");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/pawn-water.png", 1, 1);
            allPieces[3][7] = p; // 2 water p
            p = new Piece(false, this, 5, 7, "pawn");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/pawn-water.png", 1, 1);
            allPieces[5][7] = p; // 3 water p
            p = new Piece(false, this, 7, 7, "pawn");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/pawn-water.png", 1, 1);
            allPieces[7][7] = p; // 4 water p
            p = new Piece(false, this, 0, 6, "shield");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/shield-water.png", 1, 1);
            allPieces[0][6] = p; // 1 water s
            p = new Piece(false, this, 2, 6, "shield");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/shield-water.png", 1, 1);
            allPieces[2][6] = p; // 2 water s
            p = new Piece(false, this, 4, 6, "shield");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/shield-water.png", 1, 1);
            allPieces[4][6] = p; // 3 water s
            p = new Piece(false, this, 6, 6, "shield");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/shield-water.png", 1, 1);
            allPieces[6][6] = p; // 4 water s
            p = new Piece(false, this, 1, 5, "bomb");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/bomb-water.png", 1, 1);
            allPieces[1][5] = p; // 1 water b
            p = new Piece(false, this, 3, 5, "bomb");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/bomb-water.png", 1, 1);
            allPieces[3][5] = p; // 2 water b
            p = new Piece(false, this, 5, 5, "bomb");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/bomb-water.png", 1, 1);
            allPieces[5][5] = p; // 3 water b
            p = new Piece(false, this, 7, 5, "bomb");
            //StdDrawPlus.picture(p.x + .5, p.y + .5, "img/bomb-water.png", 1, 1);
            allPieces[7][5] = p; // 4 water b 
        }
    	// Note: An empty Board could possibly be useful for
    	// testing purposes.
    }

    // TASK 2
    public Piece pieceAt(int x, int y) { // done
    	// gets the piece at a position (x,y) on the board,
    	// or null if there is no piece.
    	if (outOfBound(x, y) || noPiece(x, y)) {
    		return null; // good
    	} else {
            return allPieces[x][y];
            //return getP; // place holders
        }

    	// Note: if (x,y) are out of bounds, return null.
    }

    private boolean noPiece(int x, int y) { // good
        if (allPieces[x][y] == null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean outOfBound(int x, int y) { // good
        if ((x >= 8) || (x < 0)) {
            return true;
        }
        if ((y >= 8) || (y < 0)) {
            return true;
        }
        return false;
    }


    private boolean validMove(int xi, int yi, int xf, int yf) {

        int dx = xf - xi;
        int dy = yf - yi;
        if (!outOfBound(xi, yi) && !outOfBound(xf, yf)) {
            if ((allPieces[xi][yi] != null) && allPieces[xi][yi].isKing()) { // if the selected piece a king
                if ((store == true) && (Math.abs(dx) == 1) && (Math.abs(dy) == 1)) { // moving normally
                    return true;
                } // moving as king pieces fire/water
                //!outOfBound(xi-1, yi+1) !outOfBound(xi+1, yi+1)
                if (!outOfBound(xi+1,yi+1) && !outOfBound(xi+1,yi-1) && !outOfBound(xi-1,yi-1) && !outOfBound(x-1,y+1)) { // case 1
                    if (allPieces[xi][yi].isFire() && (store == true) && isFireTurn() && (allPieces[xf][yf] == null) && ((!(allPieces[xi+1][yi+1].isFire()) && (dx == 2)) || (!(allPieces[xi-1][yi+1].isFire()) && (dx == -2)) )) {
                        if (((Math.abs(dy) == 2)) && ((!(allPieces[xi+1][yi-1].isFire()) && (dx == 2)) || (!(allPieces[xi-1][yi-1].isFire()) && (dx == -2)) )) {
                            return true;
                        } // capturing as king pieces fire
                        return false;
                    }
                    if (!allPieces[xi][yi].isFire() && (store == true) && !isFireTurn() && (allPieces[xf][yf] == null) && (((allPieces[xi+1][yi-1].isFire()) && (dx == 2)) || ((allPieces[xi-1][yi-1].isFire()) && (dx == -2)))) {
                        if (((Math.abs(dy) == 2)) && (((allPieces[xi+1][yi+1].isFire()) && (dx == 2)) || ((allPieces[xi-1][yi+1].isFire()) && (dx == -2)) )) {
                            return true;
                        }
                        return false;
                    } // capturing as king pieces water                    
                }

                if (outOfBound(xi+1,yi+1) && outOfBound(xi+1,yi-1) && outOfBound(xi-1,yi+1) && !outOfBound(xi-1,yi-1)) { // case 2
                    if (allPieces[xi][yi].isFire() && (store == true) && isFireTurn() && (allPieces[xf][yf] == null)) {
                        if (((Math.abs(dy) == 2)) && (!(allPieces[xi-1][yi-1].isFire()) && (dx == -2))) {
                            return true;
                        } // capturing as king pieces fire
                        return false;
                    }
                    if (!allPieces[xi][yi].isFire() && (store == true) && !isFireTurn() && (allPieces[xf][yf] == null)) {
                        if (((Math.abs(dy) == 2)) && ((allPieces[xi-1][yi-1].isFire()) && (dx == -2))) {
                            return true;
                        }
                        return false;
                    } // capturing as king pieces water
                }

                if (outOfBound(xi+1,yi-1) && outOfBound(xi-1,yi-1) && outOfBound(xi-1,yi+1) && !outOfBound(xi+1,yi+1)) { // case 3
                    if (allPieces[xi][yi].isFire() && (store == true) && isFireTurn() && (allPieces[xf][yf] == null) && ((!(allPieces[xi+1][yi+1].isFire()) && (dx == 2)))) {
                        if (((Math.abs(dy) == 2))) {
                            return true;
                        } // capturing as king pieces fire
                        return false;
                    }
                    if (!allPieces[xi][yi].isFire() && (store == true) && !isFireTurn() && (allPieces[xf][yf] == null) && ((allPieces[xi+1][yi+1].isFire()) && (dx == -2))) {
                        if (((Math.abs(dy) == 2))) {
                            return true;
                        }
                        return false;
                    } // capturing as king pieces water
                }

                if (outOfBound(xi+1,yi-1) && outOfBound(xi-1,yi-1) && !outOfBound(xi-1,yi+1) && !outOfBound(xi+1,yi+1)) { // case 4
                    if (allPieces[xi][yi].isFire() && (store == true) && isFireTurn() && (allPieces[xf][yf] == null) && ((!(allPieces[xi+1][yi+1].isFire()) && (dx == 2)) || (!(allPieces[xi-1][yi+1].isFire()) && (dx == -2)) )) {
                        if (((Math.abs(dy) == 2))) {
                            return true;
                        } // capturing as king pieces fire
                        return false;
                    }
                    if (!allPieces[xi][yi].isFire() && (store == true) && !isFireTurn() && (allPieces[xf][yf] == null) && (((allPieces[xi+1][yi+1].isFire()) && (dx == 2)) || ((allPieces[xi-1][yi+1].isFire()) && (dx == -2)))) {
                        if (((Math.abs(dy) == 2))) {
                            return true;
                        }
                        return false;
                    } // capturing as king pieces water
                }

                if (outOfBound(xi+1,yi+1) && outOfBound(xi-1,yi+1) && !outOfBound(xi-1,yi-1) && !outOfBound(xi+1,yi-1)) { // case 5
                    if (allPieces[xi][yi].isFire() && (store == true) && isFireTurn() && (allPieces[xf][yf] == null)) {
                        if (((Math.abs(dy) == 2)) && ((!(allPieces[xi+1][yi-1].isFire()) && (dx == 2)) || (!(allPieces[xi-1][yi-1].isFire()) && (dx == -2)) )) {
                            return true;
                        } // capturing as king pieces fire
                        return false;
                    }
                    if (!allPieces[xi][yi].isFire() && (store == true) && !isFireTurn() && (allPieces[xf][yf] == null) && (((allPieces[xi+1][yi-1].isFire()) && (dx == 2)) || ((allPieces[xi-1][yi-1].isFire()) && (dx == -2)))) {
                        if (((Math.abs(dy) == 2))) {
                            return true;
                        }
                        return false;
                    } // capturing as king pieces water
                }

                if (outOfBound(xi+1,yi+1) && outOfBound(xi+1,yi-1) && !outOfBound(xi-1,yi-1) && !outOfBound(xi-1,yi+1)) { // c6
                    if (allPieces[xi][yi].isFire() && (store == true) && isFireTurn() && (allPieces[xf][yf] == null) && ((!(allPieces[xi-1][yi+1].isFire()) && (dx == -2)))) {
                        if (((Math.abs(dy) == 2)) && ((!(allPieces[xi-1][yi-1].isFire()) && (dx == -2)))) {
                            return true;
                        } // capturing as king pieces fire
                        return false;
                    }
                    if (!allPieces[xi][yi].isFire() && (store == true) && !isFireTurn() && (allPieces[xf][yf] == null) && ((allPieces[xi-1][yi-1].isFire()) && (dx == -2))) {
                        if (((Math.abs(dy) == 2)) && ((allPieces[xi-1][yi+1].isFire()) && (dx == -2))) {
                            return true;
                        }
                        return false;
                    } // capturing as king pieces water 
                }

                if (outOfBound(xi-1,yi-1) && outOfBound(xi-1,yi+1) && !outOfBound(xi+1,yi+1) && !outOfBound(xi+1,yi-1)) { // c7
                    if (allPieces[xi][yi].isFire() && (store == true) && isFireTurn() && (allPieces[xf][yf] == null) && ((!(allPieces[xi+1][yi+1].isFire()) && (dx == 2)))) {
                        if (((Math.abs(dy) == 2)) && ((!(allPieces[xi+1][yi-1].isFire()) && (dx == 2)))) {
                            return true;
                        } // capturing as king pieces fire
                        return false;
                    }
                    if (!allPieces[xi][yi].isFire() && (store == true) && !isFireTurn() && (allPieces[xf][yf] == null) && (((allPieces[xi+1][yi-1].isFire()) && (dx == 2)))) {
                        if (((Math.abs(dy) == 2)) && (((allPieces[xi+1][yi+1].isFire()) && (dx == 2)))) {
                            return true;
                        }
                        return false;
                    } // capturing as king pieces water 
                }

                if (allPieces[xi+1][yi+1] != null) {
                    if (allPieces[xi][yi].isFire() && (store == true) && isFireTurn() && (allPieces[xf][yf] == null) && ((!(allPieces[xi+1][yi+1].isFire()) && (dx == 2)))) {
                        if (((Math.abs(dy) == 2))) {
                            return true;
                            } // capturing as king pieces fire
                            return false;
                        }
                        if (!allPieces[xi][yi].isFire() && (store == true) && !isFireTurn() && (allPieces[xf][yf] == null)) {
                            if (((Math.abs(dy) == 2)) && (((allPieces[xi+1][yi+1].isFire())))) {
                                return true;
                            }
                            return false;
                    } // capturing as king pieces water 
                }

            } else if ((allPieces[xi][yi] != null) && !(allPieces[xi][yi].isKing())) { // if the selected piece is not a king
                if ((store == true) && isFireTurn() && allPieces[xi][yi].isFire() && (Math.abs(dx) == 1) && (dy == 1)) {
                    return true;
                } // piece moving normally for fire

                if ((store == true )&& !(isFireTurn()) && !(allPieces[xi][yi].isFire()) && (Math.abs(dy) == 1) && (dy == -1)) {
                    return true;
                } // moving water normally
                if (!outOfBound(xi-1,yi+1) && !outOfBound(xi+1,yi+1)) {
                    if (validXY(xi,yi) && validXY(xf,yf) && (store == true) && isFireTurn() && (allPieces[xf][yf] == null)) {
                        //((!(allPieces[xi+1][yi+1].isFire()) && (dx == 2)) || (!(allPieces[xi-1][yi+1].isFire()) && (dx == -2)) )) {
                        if (allPieces[xi+1][yi+1] != null) {
                            if ((dy == 2) && ((!(allPieces[xi+1][yi+1].isFire()) && (dx == 2)))) {
                                return true;
                                } // capturing as normal fire
                                return false;
                            } else if (allPieces[xi-1][yi+1] != null) {
                                if ((dy == 2) && (!(allPieces[xi-1][yi+1].isFire()) && (dx == -2))) {
                                    return true;
                                } // capturing as normal fire
                                return false;
                            } else if ((allPieces[xi][yi] != null) && (allPieces[xi][yi] != null)) {
                                if ((dy == 2) && ((!(allPieces[xi+1][yi+1].isFire()) && (dx == 2)) || (!(allPieces[xi-1][yi+1].isFire()) && (dx == -2)))) {
                                    return true;
                                } // capturing as normal fire
                                return false;
                            }
                        } else if (outOfBound(xi-1,yi+1)){
                    // NW otb check NE
                            if (validXY(xi,yi) && validXY(xf,yf) && (store == true) && isFireTurn() && (allPieces[xf][yf] == null) && ((!(allPieces[xi+1][yi+1].isFire()) && (dx == 2)))) {
                                if ((dy == 2)) {
                                    return true;
                        } // capturing as normal fire
                        return false;
                    }
                }
            }else if (outOfBound(xi+1,yi+1)){
                    // NE otb check NW
                if (validXY(xi,yi) && validXY(xf,yf) && (store == true) && isFireTurn() && (allPieces[xf][yf] == null) && ((!(allPieces[xi-1][yi+1].isFire()) && (dx == -2)))) {
                    if ((dy == 2)) {
                        return true;
                        } // capturing as normal fire
                        return false;
                    }
                }
                
                if (!outOfBound(xi-1,yi-1) && !outOfBound(xi+1,yi-1)) {
                    if ((allPieces[xi+1][yi-1] != null) && (allPieces[xi-1][yi-1] != null)) {
                        if (validXY(xi,yi) && validXY(xf,yf) && (store == true) && !isFireTurn() && (allPieces[xf][yf] == null) && (((allPieces[xi+1][yi-1].isFire()) && (dx == 2)) || ((allPieces[xi-1][yi-1].isFire()) && (dx == -2)) )) {
                            if ((dy == -2)) {
                                return true;
                            }
                            return false;
                        } else if ((allPieces[xi+1][yi-1] == null) && (allPieces[xi-1][yi-1] != null)) {
                            if (validXY(xi,yi) && validXY(xf,yf) && (store == true) && !isFireTurn() && (allPieces[xf][yf] == null) && (((allPieces[xi-1][yi-1].isFire()) && (dx == -2)))) {
                                if ((dy == -2)) {
                                    return true;
                                }
                                return false;
                            } else if ((allPieces[xi+1][yi-1] != null) && (allPieces[xi-1][yi-1] == null)) {
                                if (validXY(xi,yi) && validXY(xf,yf) && (store == true) && !isFireTurn() && (allPieces[xf][yf] == null) && (((allPieces[xi+1][yi-1].isFire()) && (dx == 2)))) {
                                    if ((dy == -2)) {
                                        return true;
                                    }
                                    return false;
                                }
                            }
                    // capturing as normal water
                        }
                    } 

                } else if (outOfBound(xi-1,yi-1) && !outOfBound(xi+1,yi-1)) {
                    // SW otb check SE
                    if (allPieces[xi+1][yi-1] != null) {
                        if (validXY(xi,yi) && validXY(xf,yf) && (store == true) && !isFireTurn() && (allPieces[xf][yf] == null) && (((allPieces[xi+1][yi-1].isFire()) && (dx == 2)))) {
                            if ((dy == -2)) {
                                return true;
                            }
                            return false;
                        } // capturing as normal water
                    }
                } else if (!outOfBound(xi-1,yi-1) && outOfBound(xi+1,yi-1)) {
                    // SE otb check SW
                    if (validXY(xi,yi) && validXY(xf,yf) && (store == true) && !isFireTurn() && (allPieces[xf][yf] == null) && (((allPieces[xi-1][yi-1].isFire()) && (dx == 2)))) {
                        if ((dy == -2)) {
                            return true;
                        }
                        return false;
                    } // capturing as normal water
                }
            }
        }

        return false;  
    }

    public boolean canSelect(int x, int y) { // not done
    	// return true if there is a piece at (x,y) and it can
    	// selected.
        //if (!allPieces[iX][iY].hasCaptured()) {
            if ((this.pieceAt(x,y) != null)) { // clicking on piece
                // if there is a piece. if the piece is same piece as
                // the player
                if ((store == false) && (isFireTurn()) && (this.pieceAt(x, y).isFire())) {
                    return true;
                } // case for fire
                if ((store == false) && (!(isFireTurn())) && (this.pieceAt(x, y).isFire() == false)) {
                    return true;
                } // case for water
                /*if ((store == true) && (this.pieceAt(x, y) == null)) {
                    return true;
                }*/
                //if ((store == true) && (allPieces[x][y].hasCaptured())) { // can select if captured is true
                //    return true;
                //}
                if ((store == true) && (isFireTurn()) && (this.pieceAt(x, y).isFire())) {
                    if (move == false) {
                        return true;
                    }
                    return false;
                } // switching piece in the case of fire while no move has been done
                if ((store == true) && (!isFireTurn()) && (!(this.pieceAt(x, y).isFire()))) {
                    if (move == false) {
                        return true;
                    }
                    return false;
                } // switching piece in the case of water while no move has been done

            } else { // clicking on null
                if ((store == true) && (validMove(cX, cY, x, y)) && allPieces[cX][cY].hasCaptured()) {
                    return true;
                } // return true if selected piece's captured is true

                if ((store == true) && (validMove(iX, iY, x, y))) {
                    return true;
                }
                // move
            }
        //} else if (allPieces[iX][iY].hasCaptured() == true) {
        //    if ((this.pieceAt(x,y) == null) && validMove(iX, iY, x, y)) {
        //        return true;
        //    }
        //}
            return false;
        }

    // TASK 4

    public void select(int x, int y) { // good
            //this.select(x,y);
        if (this.pieceAt(x, y) != null) {
            //this.pieceAt(x, y).move(x, y);
            store = true;
            iX = x;
            iY = y; 
        } else {
            if (store == true) {
                if (allPieces[iX][iY] != null) {
                    this.allPieces[iX][iY].move(x, y);
                    move = true;
                    cX = x;
                    cY = y;
                } else {
                    this.allPieces[cX][cY].move(x, y);
                    move = true;
                    cX = x;
                    cY = y;
                }
            }
        }
            //this.select(x, y);
    } // good if canSelect is correct

    public void place(Piece p, int x, int y) {
        if ((p == null) || outOfBound(x, y)){
            // do nothing
        } else {
            if (noPiece(x, y) == false) {
                // if there is already a piece at (x, y),
                // replace it
                allPieces[x][y] = p; // replacing existing piece
            }
            // if there is no piece at (x, y), put p
            allPieces[x][y] = p; // how do I update p.x and p.y...
            //p.x = x; // doesn't work since p.x and p.y are private
            //p.y = y;
        }
    }

    public Piece remove(int x, int y) { // done
        // check if (x,y) is out of bound or has no piece
        if ((outOfBound(x, y) == true) || (noPiece(x, y) == true)) {
            System.out.println("Either (x, y) is out of bound or no piece available to remove.");
            return null;
        }
        Piece rP = allPieces[x][y];
        allPieces[x][y] = null; // removing the piece by making it null
        return rP; // returning the removed piece
    } // good

    public boolean canEndTurn() { // not done
        /*if ((this.hasCaptured() == true) && (this.move(x, y))) {
            return true;
        } */
        if (move == true) {
            return true;
        }
        return false; // testing purposes
    }

    private boolean isFireTurn() {
        if (player == 0) {
            return true;
        }
        return false;
    }

    public void endTurn() { // done but need to work on canEndTurn()
        if ((canEndTurn())) {
            if (player == 0) {
                player = 1;
                move = false;
                store = false;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (allPieces[i][j] == null) {

                        } else if (allPieces[i][j].hasCaptured()) {
                            allPieces[i][j].doneCapturing();
                        }
                    }
                }
                System.out.println("It is WATER'S turn.");
            } else if (player == 1) {
                player = 0;
                move = false;
                store = false;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (allPieces[i][j] == null) {
                            // do nothing    
                        } else if (allPieces[i][j].hasCaptured()) {
                            allPieces[i][j].doneCapturing();
                        }
                    }
                }
                System.out.println("It is FIRE'S turn");
            }           
        }
    } // good

    private boolean onlyFire() { // good
        boolean allFire = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.allPieces[i][j] == null) {
                    // do nothing
                } else if (!(this.allPieces[i][j].isFire())) {
                    allFire = false;
                }
            }
        }
        return allFire;
    }

    private boolean onlyWater() { // good
        boolean allWater = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.allPieces[i][j] == null) {
                    // do nothing
                } else if ((this.allPieces[i][j].isFire())) {
                    allWater = false;
                }
            }
        }
        return allWater;
    }
    private boolean isFireWin() { // done
        if (onlyFire()) {
            return true;
        }
        return false;
    } 

    private boolean isWaterWin() { // done
        if (onlyWater()) {
            return true;
        }
        return false;
    }

    private boolean noMorePieces() { // good
        boolean allNull = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.allPieces[i][j] != null) {
                    allNull = false;
                }
            }
        }
        return allNull;
    }

    private boolean isDraw() { // done
        if (noMorePieces()) {
            return true;
        }
        return false;
    }

    public String winner() { // done

        if (this.isDraw()) {
            return "No one";
        } else if (this.isFireWin()) {
            return "Fire";
        } else if (this.isWaterWin()) {
            return "Water";
        } else {
            return null; // cont. the game
        }

    }

    private boolean validXY(int x, int y) {
        if ((x >= 8) || (x < 0)) {
            return false;
        } // out of bound x check
        if ((y >= 8) || (y < 0)) {
            return false;
        } // out of bound y check
        if ((y == 0) && (x % 2 == 1)) {
            return false;
        } // row 0 check 
        if ((y == 1) && (x % 2 == 0)) {
            return false;
        } // row 1 check
        if ((y == 2) && (x % 2 == 1)) {
            return false;
        } // row 2 check
        if ((y == 3) && (x % 2 == 0)) {
            return false;
        } // row 3 check
        if ((y == 4) && (x % 2 == 1)) {
            return false;
        } // row 4 check
        if ((y == 5) && (x % 2 == 0)) {
            return false;
        } // row 5 check
        if ((y == 6) && (x % 2 == 1)) {
            return false;
        } // row 6 check
        if ((y == 7) && (x % 2 == 0)) {
            return false;
        } // row 7 check
        return true;
    }

}