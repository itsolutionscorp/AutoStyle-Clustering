public class Board {
    private boolean empty = false;
    private Piece[][] pieceList = new Piece[8][8];
    private Piece selectedPiece = null;
    private int turn = 0;
    private boolean moved = false;
    private int selectedX = 0;
    private int selectedY = 0;

    public Board(boolean shouldBeEmpty) {
        empty = shouldBeEmpty;

        if (!empty) {
            initPieces();
        }
    }

    private void initPieces() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if((i + j) % 2 == 0) {
                    if(j == 0) {
                        pieceList[i][j] = new Piece(true, this, i, j, "pawn");
                    } else if(j == 1) {
                        pieceList[i][j] = new Piece(true, this, i, j, "shield");
                    } else if(j == 2) {
                        pieceList[i][j] = new Piece(true, this, i, j, "bomb");
                    } else if(j == 7) {
                        pieceList[i][j] = new Piece(false, this, i, j, "pawn");
                    } else if(j == 6) {
                        pieceList[i][j] = new Piece(false, this, i, j, "shield");
                    } else if(j == 5) {
                        pieceList[i][j] = new Piece(false, this, i, j, "bomb");
                    }
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        return validPosition(x, y) && pieceList[x][y] != null ? pieceList[x][y] : null;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if(xf >= 0 && yf >= 0 && xf < 8 && yf < 8 && pieceList[xi][yi] != null) {
            int tempX = xf;
            int tempY = yf;
            if(pieceList[xi][yi].side() == 0 && !pieceList[xi][yi].isKing() && pieceList[xf][yf] == null) {
                tempY--;
                if(xf < xi) {
                    tempX++;
                } else {
                    tempX--;
                }

                if(validPosition(tempX, tempY) && tempY != yi && tempX != xi && pieceList[tempX][tempY] != null && pieceList[tempX][tempY].side() != 0) {
                    tempY--;
                    if(xf < xi) {
                        tempX++;
                    } else {
                        tempX--;
                    }

                    if(tempX == xi && tempY == yi) {
                        return true;
                    }

                    return false;
                }

                if(tempX == xi && tempY == yi && !moved) {
                    return true;
                }

                return false;
            }

            if(pieceList[xi][yi].side() == 0 && pieceList[xi][yi].isKing() && pieceList[xf][yf] == null) {
                if(xf < xi) {
                    tempX++;
                } else {
                    tempX--;
                }

                if(yf < yi) {
                    tempY++;
                } else {
                    tempY--;
                }

                if(validPosition(tempX, tempY) && tempY != yi && tempX != xi && pieceList[tempX][tempY] != null && pieceList[tempX][tempY].side() != 0) {
                    if(yf < yi) {
                        tempY++;
                    } else {
                        tempY--;
                    }

                    if(xf < xi) {
                        tempX++;
                    } else {
                        tempX--;
                    }

                    if(tempX == xi && tempY == yi) {
                        return true;
                    }

                    return false;
                }

                if(tempX == xi && tempY == yi && !moved) {
                    return true;
                }

                return false;
            }

            if(pieceList[xi][yi].side() == 1 && !pieceList[xi][yi].isKing() && pieceList[xf][yf] == null) {
                tempY++;
                if(xf < xi) {
                    tempX++;
                } else {
                    tempX--;
                }

                if(validPosition(tempX, tempY) && tempY != yi && tempX != xi && pieceList[tempX][tempY] != null && pieceList[tempX][tempY].side() != 1) {
                    tempY++;
                    if(xf < xi) {
                        tempX++;
                    } else {
                        tempX--;
                    }

                    if(tempX == xi && tempY == yi) {
                        return true;
                    }

                    return false;
                }

                if(tempX == xi && tempY == yi && !moved) {
                    return true;
                }

                return false;
            }

            if(pieceList[xi][yi].side() == 1 && pieceList[xi][yi].isKing() && pieceList[xf][yf] == null) {
                if(xf < xi) {
                    tempX++;
                } else {
                    tempX--;
                }

                if(yf < yi) {
                    tempY++;
                } else {
                    tempY--;
                }

                if(validPosition(tempX, tempY) && tempY != yi && tempX != xi && pieceList[tempX][tempY] != null && pieceList[tempX][tempY].side() != 1) {
                    if(yf < yi) {
                        tempY++;
                    } else {
                        tempY--;
                    }

                    if(xf < xi) {
                        tempX++;
                    } else {
                        tempX--;
                    }

                    if(tempX == xi && tempY == yi) {
                        return true;
                    }

                    return false;
                }

                if(tempX == xi && tempY == yi && !moved) {
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    public void select(int x, int y) {
        if (pieceList[x][y] == null) {
            if (selectedPiece != null) {
                if (selectedPiece.hasCaptured()) {
                    selectedPiece.move(x, y);
                } else {
                    selectedPiece.move(x, y);
                    moved = true;
                }
            }
        } else {
            selectedPiece = pieceList[x][y];
            selectedX = x;
            selectedY = y;
        }
    }

    public void place(Piece p, int x, int y) {
        if(x < 8 && y < 8 || p != null) {
            if (pieceList[x][y] == null) {
                pieceList[x][y] = p;
                if(selectedPiece == p) {
                    selectedX = x;
                    selectedY = y;
                }
            } else {
                Outer:
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (pieceList[i][j] == p) {
                            pieceList[i][j] = null;
                            break Outer;
                        }
                    }
                }

                pieceList[x][y] = p;
                if (selectedPiece == p) {
                    selectedX = x;
                    selectedY = y;
                }
            }
        }
    }

    public boolean canSelect(int x, int y) {
        if (validPosition(x, y)) {
            if (pieceList[x][y] != null && selectedPiece == null && pieceList[x][y].side() == turn) {
                return true;
            } else if (pieceList[x][y] != null && selectedPiece != null && !moved && selectedPiece.side() == turn && pieceList[x][y].side() == selectedPiece.side()) {
                return true;
            } else if (selectedPiece != null && !moved && validMove(selectedX, selectedY, x, y) && selectedPiece.side() == turn) {
                return true;
            } else if (selectedPiece != null && moved && validMove(selectedX, selectedY, x, y) && selectedPiece.hasCaptured() && selectedPiece.side() == turn) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean canEndTurn() {
        return moved || selectedPiece != null && selectedPiece.hasCaptured();
    }

    public void endTurn() {
        selectedPiece.doneCapturing();
        moved = false;
        turn = Math.abs(turn - 1);
        selectedPiece = null;
    }

    public Piece remove(int x, int y) {
        if(x <= 7 && y <= 7 && x >= 0 && y >= 0) {
            if(pieceList[x][y] == null) {
                return null;
            } else {
                Piece tempPiece = pieceList[x][y];
                pieceList[x][y] = null;
                return tempPiece;
            }
        } else {
            return null;
        }
    }

    public String winner() {
        int firePieces = 0, waterPieces = 0;

        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                if(pieceList[i][j] != null) {
                    if(pieceList[i][j].isFire()) {
                        firePieces++;
                    } else {
                        waterPieces++;
                    }
                }
            }
        }

        if (firePieces > waterPieces && waterPieces == 0) {
            return "Fire";
        } else if (waterPieces > firePieces && firePieces == 0) {
            return "Water";
        } else if (waterPieces == 0 && firePieces == 0) {
            return "No one";
        } else {
            return null;
        }
    }

    private void removeAllPieces() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceList[i][j] != null) {
                    remove(i, j);
                }
            }
        }
    }

    private boolean validPosition(int x, int y) {
        return x >= 0 && y >= 0 && x < 8 && y < 8;
    }

    public static void main(String[] args) {
        Board b = new Board(false);
        if(!b.empty) {
            b.initPieces();
            StdDrawPlus.setXscale(0, 8);
            StdDrawPlus.setYscale(0, 8);
            boolean clicked = false;
            int clickedX = 0;
            int clickedY = 0;
            int incrementFire = 0;
            int incrementWater = 0;
            int firePieces = -12, waterPieces = -12;

            while (true) {
                for(int i = 0; i < 8; i++) {
                    for(int j = 0; j < 8; j++) {
                        if((i + j) % 2 == 0) {
                            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                            StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);

                            if(b.pieceList[i][j] != null) {
                                b.place(b.pieceList[i][j], i, j);
                                if(b.pieceList[i][j].isBomb() && b.pieceList[i][j].isFire()) {
                                    if(clicked && clickedX == i && j == clickedY) {
                                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                                        StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                                    }

                                    if(b.pieceList[i][j].isKing()) {
                                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1);
                                    } else {
                                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
                                    }
                                } else if(b.pieceList[i][j].isShield() && b.pieceList[i][j].isFire()) {
                                    if(clicked && clickedX == i && j == clickedY) {
                                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                                        StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                                    }

                                    if(b.pieceList[i][j].isKing()) {
                                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
                                    } else {
                                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
                                    }
                                } else if(b.pieceList[i][j].isFire()) {
                                    if(clicked && clickedX == i && j == clickedY) {
                                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                                        StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                                    }

                                    if(b.pieceList[i][j].isKing()) {
                                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1);
                                    } else {
                                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
                                    }
                                } else if(b.pieceList[i][j].isBomb() && !b.pieceList[i][j].isFire()) {
                                    if(clicked && clickedX == i && j == clickedY) {
                                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                                        StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                                    }

                                    if(b.pieceList[i][j].isKing()) {
                                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1);
                                    } else {
                                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
                                    }
                                } else if(b.pieceList[i][j].isShield() && !b.pieceList[i][j].isFire()) {
                                    if(clicked && clickedX == i && j == clickedY) {
                                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                                        StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                                    }

                                    if(b.pieceList[i][j].isKing()) {
                                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1);
                                    } else {
                                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
                                    }
                                } else if(!b.pieceList[i][j].isFire()) {
                                    if(clicked && clickedX == i && j == clickedY) {
                                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                                        StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                                    }

                                    if(b.pieceList[i][j].isKing()) {
                                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1);
                                    } else {
                                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
                                    }
                                }
                            }
                        } else {
                            StdDrawPlus.setPenColor(StdDrawPlus.RED);
                            StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                        }

                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    }
                }

                for(int i = 0; i < 8; ++i) {
                    for(int j = 0; j < 8; ++j) {
                        if(b.pieceList[i][j] != null) {
                            if(b.pieceList[i][j].isFire()) {
                                incrementFire++;
                            } else {
                                incrementWater++;
                            }
                        }
                    }
                }

                firePieces = incrementFire;
                waterPieces = incrementWater;
                incrementFire = 0;
                incrementWater = 0;

                if(StdDrawPlus.mousePressed()) {
                    int x = (int) StdDrawPlus.mouseX();
                    int y = (int) StdDrawPlus.mouseY();

                    if(b.canSelect(x, y)) {
                        clickedX = x;
                        clickedY = y;
                        clicked = true;
                        b.select(clickedX, clickedY);
                    }
                } else if(StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
                    b.endTurn();
                    clicked = false;
                    clickedX = 0;
                    clickedY = 0;
                } else if (StdDrawPlus.isNPressed()) {
                    b.removeAllPieces();
                    b.initPieces();
                    clicked = false;
                    clickedX = 0;
                    clickedY = 0;
                    b.turn = 0;
                    b.selectedPiece = null;
                    b.moved = false;
                    firePieces = -12;
                    waterPieces = -12;
                }

                StdDrawPlus.show(100);

                if (firePieces == 0 || waterPieces == 0) {
                    break;
                }
            }

            System.out.println(b.winner());
        }
    }
}
