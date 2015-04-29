public class Board {

    private int N;
    private Piece[][] pieces;
    private String[] imgFiles;
    private boolean[][] highlight;
    private int[] pieceSelected;
    private int turn;
    private boolean selected;
    private boolean moved;
    private String result;

    public static void main(String[] args) {
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, b.N);
        StdDrawPlus.setYscale(0, b.N);

        while(true) {
            b.drawBoard(b.N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);
                }
                if (b.result != null) {
                    b.drawBoard(b.N);
                    System.out.println(b.result + " wins!");
                    StdDrawPlus.show(1000);
                    System.exit(0);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            if (StdDrawPlus.isNPressed()) {
                b = new Board(false);
            }
            StdDrawPlus.show(100);
        }
    }

	public Board(boolean shouldBeEmpty) {
        N = 8;
        turn = 0;
        selected = false;
        moved = false;
        pieceSelected = new int[]{-N, -N};
        pieces = new Piece[N][N];
        highlight = new boolean[N][N];
        if (!shouldBeEmpty) {
            makeFullBoard(N);
        }
        imgFiles = new String[]{"img/bomb-water.png", "img/shield-water.png", "img/pawn-water.png",
                            "img/bomb-fire.png", "img/shield-fire.png", "img/pawn-fire.png",
                            "img/bomb-water-crowned.png", "img/shield-water-crowned.png",
                            "img/pawn-water-crowned.png", "img/bomb-fire-crowned.png",
                            "img/shield-fire-crowned.png", "img/pawn-fire-crowned.png"};
	}

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.MAGENTA);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (highlight[i][j]) {
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                Piece p = pieces[i][j];
                if (p != null) {
                    StdDrawPlus.picture(i + .5, j + .5, imgFiles[imgIndex(p)], 1, 1);
                }
            }
        }
    }

	private void makeFullBoard(int N) {
        String type;
        boolean isFire;
        for (int j = 0; j < N; j++) {
            if (j == 0 || j == N - 1) {
                type = "pawn";
            } else if (j == 1 || j == N - 2) {
                type = "shield";
            } else if (j == 2 || j == N - 3) {
                type = "bomb";
            } else {
                continue;
            }
            if (j < N / 2) {
                isFire = true;
            } else {
                isFire = false;
            }
            for (int i = 0; i < N; i++) {
                if ((i + j) % 2 == 0) {
                    new Piece(isFire, this, i, j, type);
                }
            }
        }
	}

    private int imgIndex(Piece p) {
        int index;
        if (p.isBomb()) {
            index = 0;
        } else if (p.isShield()) {
            index = 1;
        } else {
            index = 2;
        }
        if (p.isFire()) {
            index += 3;
        }
        if (p.isKing()) {
            index += 6;
        }
        return index;
    }

    public Piece pieceAt(int x, int y) {
        if (outOfBounds(x, y)) {
            return null;
        }
        return pieces[x][y];
    }

    public void place(Piece p, int x, int y) {
        if (outOfBounds(x, y)) {
            return;
        }
        if (pieceAt(x, y) != null) {
            remove(x, y);
        }
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y){
        Piece p = pieceAt(x, y);
        if (p == null) {
            if (outOfBounds(x, y)) {
                System.out.println("location is out of bounds");
            } else {
                System.out.println("there is no piece at that location");
            }
        } else {
            pieces[x][y] = null;
            highlight[x][y] = false;
        }
        return p;
    }

    private boolean outOfBounds(int x, int y) {
        return x < 0 || x > (N - 1) || y < 0 || y > (N - 1);
    }

    public boolean canSelect(int x, int y) {
        if ((x + y) % 2 != 0) {
            return false;
        }
        Piece p = pieceAt(x, y);
        if (p != null) {
            if (!moved && p.side() == turn) {
                return true;
            }
        } else {
            Piece old = pieceAt(pieceSelected[0], pieceSelected[1]);
            if (old != null) {
                int dy = y - pieceSelected[1];
                if (old.isKing() || (turn == 0 && dy > 0) || (turn == 1 && dy < 0)) {
                    if ((selected && validMove(old, x, y)) && 
                        (!moved || (moved && old.hasCaptured()))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean validMove(Piece p, int x, int y) {
        int dx = x - pieceSelected[0];
        int dy = y - pieceSelected[1];
        if (pieceAt(x, y) == null) {
            if (Math.abs(dx) == 1 && Math.abs(dy) == 1 && !p.hasCaptured()) {
                return true;
            } else if (Math.abs(dx) == 2 && Math.abs(dy) == 2) {
                Piece c = pieceAt(x - (dx / 2), y - (dy / 2));
                if (c != null) {
                    if (c.side() != p.side()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void select(int x, int y) {
        int xi = pieceSelected[0];
        int yi = pieceSelected[1];
        int[] current = new int[]{x, y};
        if (pieceSelected != current) {
            if (!outOfBounds(xi, yi)) {
                highlight[xi][yi] = false;
            }
            if (pieceAt(x, y) != null) {
                pieceSelected = current;
            }
            selected = true;
            highlight[x][y] = true;
        }
        Piece p = pieceAt(xi, yi);
        if (p != null) {
            if (validMove(p, x, y)) {
                p.move(x, y);
                moved = true;
                remove(xi, yi);
                pieceSelected = current;
                result = winner();
            }
        }    
    }

    public boolean canEndTurn() {
        return moved;
    }

    public void endTurn() {
        turn = 1 - turn;
        selected = false;
        moved = false;
        int x = pieceSelected[0];
        int y = pieceSelected[1];
        if (!outOfBounds(x, y)) {
            highlight[x][y] = false;
            Piece p = pieceAt(x, y);
            if (p != null) {
                p.doneCapturing();
            }
        }
        pieceSelected = new int[]{-N, -N};
    }

    public String winner() {
        int fireSum = 0;
        int waterSum = 0;
        for (Piece[] row : pieces) {
            for (Piece p : row) {
                if (p != null) {
                    if (p.isFire()) {
                        fireSum += 1;
                    } else {
                        waterSum += 1;
                    }
                }
            }
        }
        if (fireSum == 0 && waterSum == 0){
            return "No one";
        } else if (fireSum == 0) {
            return "Water";
        } else if (waterSum == 0) {
            return "Fire";
        }
        return null;
    }
}