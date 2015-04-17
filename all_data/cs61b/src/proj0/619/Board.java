public class Board {

    private Piece [][] pieceArray;
    private Piece pieceSelected;
    private int counter;
    private boolean existsFire;
    private boolean existsWater;
    private boolean hasMovedFire;
    private boolean hasMovedWater;
    private boolean hasPlaced;
    private boolean hasMoved = false;
    
    private void drawBoard(int N) {
        
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
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        int N = 8;
        
        Board board = new Board(false);
        board.drawBoard(N);
        
        board.counter = 0;
        

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
       
       while (true) {
            // call to winner
            board.winner();
            //System.out.println(board.winner());
            //System.out.println(board.hasMoved);

            
            board.drawBoard(8);
            board.drawPieces();
            
            if (StdDrawPlus.isSpacePressed()) {
                //System.out.println(board.hasMovedFire);
                //&& !board.pieceSelected.hasCaptured()) || (board.pieceSelected.hasCaptured() && (board.returnX(board.pieceSelected) == 20)))
                        if (board.canEndTurn() == true) {
                            // System.out.println(board.pieceSelected.hasCaptured());
                            board.endTurn();
                           
                            } else {
                                // System.out.println("yy");
                    }
                }


            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                //System.out.println(board.isFireTurn());

                 // if statement for has captured
                if (board.pieceSelected != null && board.pieceSelected.hasCaptured() && !board.pieceSelected.isBomb()) {
                    // System.out.println("gg");
                    if (board.isFireTurn()) {
                        
                        if (board.canSelect((int)x, (int)y)) {
                        
                        board.select((int)x, (int)y);
                        // xi, yi, xf, yf created to see if piece has moved -> see if counter needs to be updated
                        int xi = board.returnX(board.pieceSelected);
                        int yi = board.returnY(board.pieceSelected);
                        
                        

                        int xf = board.returnX(board.pieceSelected);
                        int yf = board.returnY(board.pieceSelected);

                       
                        if (xi != xf && yi != yf) {
                           
                            //board.hasMovedFire = true;
                        }
                        
                    }
                    board.drawPiece(board.pieceAt((int)x, (int)y));
                    } else {
                        // water capture move
                        if (board.canSelect((int)x, (int)y)) {
                        
                        board.select((int)x, (int)y);
                        // xi, yi, xf, yf created to see if piece has moved -> see if counter needs to be updated
                        int xi = board.returnX(board.pieceSelected);
                        int yi = board.returnY(board.pieceSelected);
                        System.out.println((int)x);
                        board.pieceSelected.move((int)x, (int)y);

                        int xf = board.returnX(board.pieceSelected);
                        int yf = board.returnY(board.pieceSelected);
                        
                        if (xi != xf && yi != yf) {
                           
                            //board.hasMovedWater = true;
                        }
                        
                    }
                    board.drawPiece(board.pieceAt((int)x, (int)y));
                    }
                } 

                if (board.isFireTurn() == true) {
                    //if (board.pieceSelected != null && board.pieceSelected.isFire() == true) {
                    //System.out.println("dshj");
                    if (board.canSelect((int)x, (int)y)) {
                        
                        board.select((int)x, (int)y);
                        // xi, yi, xf, yf created to see if piece has moved -> see if counter needs to be updated
                        // int xi = board.returnX(board.pieceSelected);
                        // int yi = board.returnY(board.pieceSelected);
                        // // board.pieceSelected.move((int)x, (int)y);

                        // int xf = board.returnX(board.pieceSelected);
                        // int yf = board.returnY(board.pieceSelected);
                        
                        // if (xi != xf && yi != yf) {
                           
                        //     board.hasMovedFire = true;
                        // }
                        
                    //}
                    board.drawPiece(board.pieceAt((int)x, (int)y));
                } else {
                    // pieceSelected is null
                    if (board.canSelect((int)x, (int)y)) {
                        board.select((int)x, (int)y);
                    }
                    
                } 
                
                } else {



                // water pieces
                //if (board.pieceSelected != null && board.pieceSelected.isFire() == false) {
                    
                    if (board.canSelect((int)x, (int)y)) {
                        board.select((int)x, (int)y);
                        // xi, yi, xf, yf created to see if piece has moved -> see if counter needs to be updated
                        // int xi = board.returnX(board.pieceSelected);
                        // int yi = board.returnY(board.pieceSelected);
                        // board.pieceSelected.move((int)x, (int)y);
                        

                        // board.pieceSelected.hasCaptured();
                        
                        // int xf = board.returnX(board.pieceSelected);
                        // int yf = board.returnY(board.pieceSelected);
                        
                        

                        // if (xi != xf && yi != yf) {
                        //    // board.counter = board.counter + 1; 
                        //     // board.endTurn();
                        //     board.hasMovedWater = true;
                        // }

                        board.drawPiece(board.pieceAt((int)x, (int)y));
                    }
                    

                else {
                    // pieceSelected is null
                    if (board.canSelect((int)x, (int)y)) {
                        board.select((int)x, (int)y);
                    }
                    
                }
                    
            } 
                
            } 
            
            
            StdDrawPlus.show(200);

       }    

    }
    
            
    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty == true) {
            pieceArray = new Piece [8][8];
        } else {
            this.pieceArray = new Piece [8][8];
            // Fire Pieces
            pieceArray[1][1] = new Piece (true, this, 1, 1, "shield");
            pieceArray[3][1] = new Piece (true, this, 3, 1, "shield");
            pieceArray[5][1] = new Piece (true, this, 5, 1, "shield");
            pieceArray[7][1] = new Piece (true, this, 7, 1, "shield");

            pieceArray[0][2] = new Piece (true, this, 0, 2, "bomb");
            pieceArray[2][2] = new Piece (true, this, 2, 2, "bomb");
            pieceArray[4][2] = new Piece (true, this, 4, 2, "bomb");
            pieceArray[6][2] = new Piece (true, this, 6, 2, "bomb");

            pieceArray[0][0] = new Piece (true, this, 0, 0, "pawn");
            pieceArray[2][0] = new Piece (true, this, 2, 0, "pawn");
            pieceArray[4][0] = new Piece (true, this, 4, 0, "pawn");
            pieceArray[6][0] = new Piece (true, this, 6, 0, "pawn");

            // Water Pieces
            pieceArray[1][7] = new Piece (false, this, 1, 7, "pawn");
            pieceArray[3][7] = new Piece (false, this, 3, 7, "pawn");
            pieceArray[5][7] = new Piece (false, this, 5, 7, "pawn");
            pieceArray[7][7] = new Piece (false, this, 7, 7, "pawn");

            pieceArray[0][6] = new Piece (false, this, 0, 6, "shield");
            pieceArray[2][6] = new Piece (false, this, 2, 6, "shield");
            pieceArray[4][6] = new Piece (false, this, 4, 6, "shield");
            pieceArray[6][6] = new Piece (false, this, 6, 6, "shield");

            pieceArray[1][5] = new Piece (false, this, 1, 5, "bomb");
            pieceArray[3][5] = new Piece (false, this, 3, 5, "bomb");
            pieceArray[5][5] = new Piece (false, this, 5, 5, "bomb");
            pieceArray[7][5] = new Piece (false, this, 7, 5, "bomb");
    }
}

    private void drawPieces() {
        for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (pieceArray[i][j] == null) {

                    } else if (pieceArray[i][j].isKing() == false) {
                        if (pieceArray[i][j].isFire() == true  && pieceArray[i][j].isShield() == true) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    } else if (pieceArray[i][j].isFire() == true  && pieceArray[i][j].isBomb() == true) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    } else if (pieceArray[i][j].isFire() == true  && pieceArray[i][j].isBomb() == false && pieceArray[i][j].isShield() == false) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    } else if (pieceArray[i][j].isFire() == false  && pieceArray[i][j].isShield() == true) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    } else if (pieceArray[i][j].isFire() == false  && pieceArray[i][j].isBomb() == true) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    } else if (pieceArray[i][j].isFire() == false  && pieceArray[i][j].isBomb() == false && pieceArray[i][j].isShield() == false) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    }
                    } else {
                        if (pieceArray[i][j].isFire() == true  && pieceArray[i][j].isShield() == true) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                    } else if (pieceArray[i][j].isFire() == true  && pieceArray[i][j].isBomb() == true) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    } else if (pieceArray[i][j].isFire() == true  && pieceArray[i][j].isBomb() == false && pieceArray[i][j].isShield() == false) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    } else if (pieceArray[i][j].isFire() == false  && pieceArray[i][j].isShield() == true) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    } else if (pieceArray[i][j].isFire() == false  && pieceArray[i][j].isBomb() == true) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    } else if (pieceArray[i][j].isFire() == false  && pieceArray[i][j].isBomb() == false && pieceArray[i][j].isShield() == false) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                    }
                    }       
            }
        }
    }

    private void drawPiece(Piece p) {
        int a = returnX(p);
        int b = returnY(p);
        if ( p == null) {
            } else {
                if (p.isFire() == true  && p.isShield() == true) {
                StdDrawPlus.picture(a + .5, b+ .5, "img/shield-fire.png", 1, 1);
            } else if (p.isFire() == true  && p.isBomb() == true) {
                StdDrawPlus.picture(a + .5, b + .5, "img/bomb-fire.png", 1, 1);
            } else if (p.isFire() == true  && p.isBomb() == false && p.isShield() == false) {
                StdDrawPlus.picture(a + .5, b + .5, "img/pawn-fire.png", 1, 1);
            } else if (p.isFire() == false  && p.isShield() == true) {
                StdDrawPlus.picture(a + .5, b + .5, "img/shield-water.png", 1, 1);
            } else if (p.isFire() == false  && p.isBomb() == true) {
                StdDrawPlus.picture(a + .5, b + .5, "img/bomb-water.png", 1, 1);
            } else if (p.isFire() == false  && p.isBomb() == false && p.isShield() == false) {
                StdDrawPlus.picture(a + .5, b + .5, "img/pawn-water.png", 1, 1);
            }
            }
    }
    
    private boolean isFireTurn() {
        if (counter % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    

    // private void removeCapturedPiece() {
    //     if ()
    // }

    public Piece pieceAt(int x, int y) {
        if (x > 7 | x < 0 | y > 7 | y < 0) {
            return null;
        } else if (pieceArray[x][y] == null) {
            return null;
        } else {
            return pieceArray [x][y];
        }
    }

    public boolean canSelect(int x, int y) {
        // int a = returnX(pieceSelected);
        // int b = returnY(pieceSelected);

//         if (pieceAt(x, y) == null) {
//             if (pieceSelected != null && pieceSelected.isFire() != isFireTurn()) {
//                 return false;
//             } else {
//                 if (validMove(a, b, x, y)) {
//                     return true;
//                 }
//             }
//         } else if (pieceAt(x, y) != null) {
//             if (pieceAt(x, y).isFire() == isFireTurn()) {
//                 if (pieceSelected == null || (pieceSelected != null && pieceSelected.isFire() != isFireTurn() )) {
//                     return true;
//                 } else {
//                     return false;
//             } 
//     }
//     } else {
//         return false;
//     } return false;
// }
        // picking piece to move
    //     if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == isFireTurn() && pieceSelected != null && pieceSelected.isFire() != isFireTurn()) {
    //         return true;
    //     }else if (pieceSelected != null && pieceAt(x, y) == null && validMove(a, b, x, y)) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }   



        //if (pieceSelected != null && !pieceSelected.hasCaptured() )
        // moving piece
        //if (pieceSelected != null) {
            int a = returnX(pieceSelected);
            int b = returnY(pieceSelected);
        //}
        

        if (isFireTurn() == true) {
            

            if (pieceSelected !=null && pieceSelected.hasCaptured() && !pieceSelected.isBomb()) {
                return true;
            }
                if (hasMoved) {
                return false;
            }
            if (pieceSelected != null) {
                
                if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == true) {
                    return true;
                }
                 else if (validMoveFire(a, b, x, y) == true) {
                    return true;
                } else if (validMoveFire(a, b, x, y) == false) {
                    return false;
                }
            } else if (pieceSelected == null ) {
                
                if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == true) {
                    return true;
                } else {
                    return false;
                } 
            } 
            return false;
        // end of fire, start water canSelect
        } else {
            if (pieceSelected !=null && pieceSelected.hasCaptured() && !pieceSelected.isBomb()) {
                return true;
            }
            if (hasMoved) {
                return false;
            }
            if (pieceSelected != null) {
                
                if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == false) {
                    return true;
                }
                 else if (validMoveWater(a, b, x, y) == true) {
                    return true;
                } else if (validMoveWater(a, b, x, y) == false) {
                    return false;
                }
            } else if (pieceSelected == null) {

                if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == false) {
                    return true;
                } else {
                    return false;
            } 
        } 
        return false;
        }
        
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (validMoveWater(xi, yi, xf, yf) && !isFireTurn()) {
            return true;
        } else if (validMoveFire(xi, yi, xf, yf) && isFireTurn()) {
            return true;
        } else {
            return false;
        }
    }

    private int returnX(Piece p) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (p == pieceArray[i][j]) {
                    return i;
                } 
            }
        } return 20;
    } 

    private int returnY(Piece p) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (p == pieceArray[i][j]) {
                    return j;
                }
            }
        } return 20;
    }

    private boolean validMoveFire(int xi, int yi, int xf, int yf) {
        if (pieceAt(xi, yi) != null && pieceAt(xi, yi).isKing() == false) {
           if (xi > 7 | yi > 7 | xf > 7 | yf > 7 | xi < 0 | yi < 0 | xf < 0 | yf < 0) {
            return false;
            // } else if (isFireTurn() == false) {
            //  return false;
            } else if (pieceArray[xf][yf] !=null) {
                return false;
            } else if (pieceArray[xi][yi] == null || pieceArray[xi][yi].isFire() == false) {
                return false;
            } else if (yf - yi == 1 && (xf - xi == 1 || xf - xi == -1)) {
                return true;
            } else if (yf - yi == 2 && (xf - xi == 2 || xf - xi == -2) && this.pieceAt((xf+xi)/2, (yf+yi)/2) != null  && this.pieceAt((xf+xi)/2, (yf+yi)/2).isFire() == false) {
                return true;
            } else {
                return false;
            } 
        } else {
            if (xi > 7 | yi > 7 | xf > 7 | yf > 7 | xi < 0 | yi < 0 | xf < 0 | yf < 0) {
            return false;
            // } else if (isFireTurn() == false) {
            //  return false;
            } else if (pieceArray[xf][yf] !=null) {
                return false;
            } else if (pieceArray[xi][yi] == null || pieceArray[xi][yi].isFire() == false) {
                return false;
            } else if ((yf - yi == 1 || yf - yi == -1) && (xf - xi == 1 || xf - xi == -1)) {
                return true;
            } else if ((yf - yi == 2 || yf - yi == -2) && (xf - xi == 2 || xf - xi == -2) && this.pieceAt((xf+xi)/2, (yf+yi)/2) != null  && this.pieceAt((xf+xi)/2, (yf+yi)/2).isFire() == false) {
                return true;
            } else {
                return false;
            } 
        }
        
    }

    // private boolean validJumpFire(int xi, int yi, int xf, int yf) {
    //     if (pieceAt(xi, yi) != null && pieceAt(xi, yi).isKing() == true) {
    //         if (xi > 7 | yi > 7 | xf > 7 | yf > 7 | xi < 0 | yi < 0 | xf < 0 | yf < 0) {
    //             System.out.println("g");
    //             return false;      
    //         } else if (((yf - yi == 2 || yf - yi == -2) && (xf - xi == 2 || xf - xi == -2) && this.pieceAt((xf+xi)/2, (yf+yi)/2) != null  && this.pieceAt((xf+xi)/2, (yf+yi)/2).isFire() == false)) {
    //             return true;
    //         } else {
    //             System.out.println("g");
    //             return false;
    //         }
    //     } else {
    //         if (xi > 7 | yi > 7 | xf > 7 | yf > 7 | xi < 0 | yi < 0 | xf < 0 | yf < 0) {
    //             System.out.println("g");
    //             return false;      
    //         } else if (((yf - yi == 2) && (xf - xi == 2 || xf - xi == -2) && this.pieceAt((xf+xi)/2, (yf+yi)/2) != null  && this.pieceAt((xf+xi)/2, (yf+yi)/2).isFire() == false)) {
    //             return true;
    //         } else {
    //             System.out.println("g");
    //             return false;
    //         }
    //     }
    // }

    //     if (pieceAt(xi, yi) != null && pieceAt(xi, yi).isKing() == false) {
    //        if (xi > 7 | yi > 7 | xf > 7 | yf > 7 | xi < 0 | yi < 0 | xf < 0 | yf < 0) {
    //         return false;
    //         // } else if (isFireTurn() == false) {
    //         //  return false;
    //         } else if (pieceArray[xf][yf] !=null) {
    //             return false;
    //         } else if (pieceArray[xi][yi] == null || pieceArray[xi][yi].isFire() == false) {
    //             return false;
    //         // } else if (yf - yi == 1 && (xf - xi == 1 || xf - xi == -1)) {
    //         //     return true;
    //         } else if (yf - yi == 2 && (xf - xi == 2 || xf - xi == -2) && this.pieceAt((xf+xi)/2, (yf+yi)/2) != null  && this.pieceAt((xf+xi)/2, (yf+yi)/2).isFire() == false) {
    //             return true;
    //         } else {
    //             return false;
    //         } 
    //     } else {
    //         if (xi > 7 | yi > 7 | xf > 7 | yf > 7 | xi < 0 | yi < 0 | xf < 0 | yf < 0) {
    //         return false;
    //         // } else if (isFireTurn() == false) {
    //         //  return false;
    //         } else if (pieceArray[xf][yf] !=null) {
    //             return false;
    //         } else if (pieceArray[xi][yi] == null || pieceArray[xi][yi].isFire() == false) {
    //             return false;
    //         // } else if ((yf - yi == 1 || yf - yi == -1) && (xf - xi == 1 || xf - xi == -1)) {
    //         //     return true;
    //         } else if ((yf - yi == 2 || yf - yi == -2) && (xf - xi == 2 || xf - xi == -2) && this.pieceAt((xf+xi)/2, (yf+yi)/2) != null  && this.pieceAt((xf+xi)/2, (yf+yi)/2).isFire() == false) {
    //             return true;
    //         } else {
    //             return false;
    //         } 
    //     }
        
    // }

    private boolean validMoveWater(int xi, int yi, int xf, int yf) {
        if (pieceAt(xi, yi) != null && pieceAt(xi, yi).isKing() == false) {
            if (xi > 7 | yi > 7 | xf > 7 | yf > 7 | xi < 0 | yi < 0 | xf < 0 | yf < 0) {
                return false;
            } else if (isFireTurn() == true) {
                return false; 
            } else if (pieceArray[xf][yf] !=null) {
                return false;
            } else if (pieceArray[xi][yi] == null || pieceArray[xi][yi].isFire() == true) {
                return false;
            } else if (yf - yi == -1 && (xf - xi == 1 || xf - xi == -1)) {
                return true;
            } else if (yf - yi == -2 && (xf - xi == 2 || xf - xi == -2) && this.pieceAt((xf+xi)/2, (yf+yi)/2) != null  && this.pieceAt((xf+xi)/2, (yf+yi)/2).isFire() == true) {
                return true;
            } else {
                return false;
            }
        } else {
            if (xi > 7 | yi > 7 | xf > 7 | yf > 7 | xi < 0 | yi < 0 | xf < 0 | yf < 0) {
            return false;
        } else if (isFireTurn() == true) {
            return false; 
        } else if (pieceArray[xf][yf] !=null) {
            return false;
        } else if (pieceArray[xi][yi] == null || pieceArray[xi][yi].isFire() == true) {
            return false;
        } else if ((yf - yi == -1 || yf - yi == 1) && (xf - xi == 1 || xf - xi == -1)) {
            return true;
        } else if ((yf - yi == -2 || yf - yi == 2) && (xf - xi == 2 || xf - xi == -2) && this.pieceAt((xf+xi)/2, (yf+yi)/2) != null  && this.pieceAt((xf+xi)/2, (yf+yi)/2).isFire() == true) {
            return true;
        } else {
            return false;
        }
        }
        
    }

    private boolean validJumpWater(int xi, int yi, int xf, int yf) {
        if (pieceAt(xi, yi) != null && pieceAt(xi, yi).isKing() == true) {
            if (xi > 7 | yi > 7 | xf > 7 | yf > 7 | xi < 0 | yi < 0 | xf < 0 | yf < 0) {
                return false;      
            } else if (((yf - yi == 2 || yf - yi == -2) && (xf - xi == 2 || xf - xi == -2) && this.pieceAt((xf+xi)/2, (yf+yi)/2) != null  && this.pieceAt((xf+xi)/2, (yf+yi)/2).isFire() == true)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (xi > 7 | yi > 7 | xf > 7 | yf > 7 | xi < 0 | yi < 0 | xf < 0 | yf < 0) {
                return false;      
            } else if (((yf - yi == -2) && (xf - xi == 2 || xf - xi == -2) && this.pieceAt((xf+xi)/2, (yf+yi)/2) != null  && this.pieceAt((xf+xi)/2, (yf+yi)/2).isFire() == true)) {
                return true;
            } else {
                return false;
            }
        }
    }



    public void select(int x, int y) {
         StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        //if (this.canSelect(x, y) == true) {
         // selecting a piece
            if (pieceAt(x, y) != null) {
                pieceSelected = pieceAt(x, y);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            } else {
                if (pieceSelected != null) {
                    StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                    pieceSelected.move(x, y);
                    hasMoved = true;
            }
            }
    }


    public void place(Piece p, int x, int y) {

        if (x > 7 || x < 0 || y > 7 || y < 0) {
            // do nothing
        } else {
            
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (p == this.pieceAt(i,j)) {
                            this.remove(i,j);
                            //this.hasPlaced = true;
                            this.hasMoved = true;
                        }
                    }
                }

                pieceArray[x][y] = p;
                
        }
        


    }

    public Piece remove(int x, int y) {
        if (x > 7 | x < 0 | y > 7 | y < 0) {
            System.out.println("Coordinates Out of Bounds");
            return null;

        } else if (pieceArray[x][y] == null) {
            System.out.println("No Piece at Coordinates");
            return null;
        } else {
            Piece oldPiece = new Piece(pieceArray[x][y].isFire(), this, x, y, type(pieceArray[x][y]));
            pieceArray[x][y]=null;
            return oldPiece;
        }
        
    }

    public boolean canEndTurn () {
        if (hasMoved) {
            return true;
        } else {
            return false;
        }

        


    }

    public void endTurn() {
        this.counter = this.counter + 1;
        
        hasMoved = false;
    }

    public String winner() {
        existsWater = false;
        existsFire = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceAt(i, j) != null && pieceAt(i, j).isFire() == true) {
                    existsFire = true;
                } 
                if (pieceAt(i, j) != null && pieceAt(i, j).isFire() == false) {
                    existsWater = true;
                }
                    
            }
        } 
        if (existsWater == true && existsFire == true) {
            return null;
        } else if (existsWater == true && existsFire == false) {
            return "Water";
        } else if (existsFire == true && existsWater == false) {
            return "Fire";
        } else if (existsFire == false && existsWater == false) {
            return "No one";
        } else {
            return "No one";
        }
    } 

    private String type(Piece P) {
        if (P.isShield() == true) {
            return "shield";
        } else if (P.isBomb() == true) {
            return "bomb";
        } else {
            return "pawn";
    }
    }
}


