public class Board {
	private boolean[][] pieces;
	private int max = 8;

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {

		} else {
			
		}
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                // if (pieces[i][j] != null) {
                //     StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                }
            }
        }
    }


	public static void main(String[] args) {
		Board board = new Board(false);

		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new boolean[N][N];

        while(true) {
            drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                pieces[(int) x][(int) y] = true;
            }            
            StdDrawPlus.show(100);
        }
        board.drawBoard(N);
    	

	}
}