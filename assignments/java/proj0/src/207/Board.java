public class Board{
	//add board b
	public static Board b = new Board();
	public static int N = 8;

	public static Piece[] pieces = new Piece[]{
	new Piece(true, b, 0, 0, "Pawn"),
	new Piece(true, b, 2, 0, "Pawn"),
	new Piece(true, b, 4, 0, "Pawn"),
	new Piece(true, b, 6, 0, "Pawn"),
	new Piece(true, b, 1, 1, "Shield"),
	new Piece(true, b, 3, 1, "Shield"),
	new Piece(true, b, 5, 1, "Shield"),
	new Piece(true, b, 7, 1, "Shield"),
	new Piece(true, b, 0, 2, "Bomb"),
	new Piece(true, b, 2, 2, "Bomb"),
	new Piece(true, b, 4, 2, "Bomb"),
	new Piece(true, b, 6, 2, "Bomb"),
	new Piece(false, b, 1, 7, "Pawn"),
	new Piece(false, b, 3, 7, "Pawn"),
	new Piece(false, b, 5, 7, "Pawn"),
	new Piece(false, b, 7, 7, "Pawn"),
	new Piece(false, b, 0, 6, "Shield"),
	new Piece(false, b, 2, 6, "Shield"),
	new Piece(false, b, 4, 6, "Shield"),
	new Piece(false, b, 6, 6, "Shield"),
	new Piece(false, b, 1, 5, "Bomb"),
	new Piece(false, b, 3, 5, "Bomb"),
	new Piece(false, b, 5, 5, "Bomb"),
	new Piece(false, b, 7, 5, "Bomb")};
	public static int xplaced;
	public static int yplaced;
	public static int x0;
	public static int y0;

	public static int xorigin;
	public static int yorigin;
	public static Piece currentPiece;
	public static int player = 1; /* 1 for fire, 0 for water*/

	public static void main(String[] args){

		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board(false);

		while(true) {
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int x1 = (int) x;
                int y1 = (int) y;
                x0 = (int) x;
                y0 = (int) y;
                select(x1, y1);
                place(pieceAt(x1,y1), 4, 4);
                remove(x1, y1);
                System.out.println(x1);
                System.out.println(y1);
                /*other select functions*/
            }            
            StdDrawPlus.show(100);

		}
	}

	private static void initializeBoard(){
		for (int i = 0; i < 8; i = i+ 2){
			StdDrawPlus.picture(i + .5, 0 + .5, "img/pawn-fire.png", 1, 1);
			}
		for (int i = 1; i < 8; i = i+ 2){
			StdDrawPlus.picture(i + .5, 1 + .5, "img/shield-fire.png", 1, 1);
			}
		for (int i = 0; i < 8; i = i+ 2){
			StdDrawPlus.picture(i + .5, 2 + .5, "img/bomb-fire.png", 1, 1);
			}
		for (int i = 1; i < 8; i = i+ 2){
			StdDrawPlus.picture(i + .5, 5 + .5, "img/bomb-water.png", 1, 1);
			}
		for (int i = 0; i < 8; i = i+ 2){
			StdDrawPlus.picture(i + .5, 6 + .5, "img/shield-water.png", 1, 1);
			}
		for (int i = 1; i < 8; i = i+ 2){
			StdDrawPlus.picture(i + .5, 7 + .5, "img/pawn-water.png", 1, 1);
			}
}

	public static void Board(boolean shouldBeEmpty){
		/*reimplement Board b after*/

		if (shouldBeEmpty == true){
			for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
			}
		}
		else{
			for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
				}
			/*setting up board row by row*/
			/*do by piece, not generic?*/
			initializeBoard();
		}
	}


	private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

    public static Piece pieceAt(int x, int y){
    	for (int i = 0; i< 24; i++){
    		if (pieces[i].x == x && pieces[i].y == y){
    			System.out.println(pieces[i].type);
    			return pieces[i];
    		}
    	}
    	return null;

    }
    public static boolean canSelect(int x, int y){
//    	need to check for piece moving on top of another piece
    	if (pieceAt(x,y) != null){
    		return true;
    	}
    	if (pieceAt(x,y) == null){
    		if (Math.abs(x-xorigin) == 1 && Math.abs(y-yorigin) == 1){
    			return true;
    		}
    		if (pieceAt((x+xorigin)/2, (y+yorigin/2))!= null){
    			return true;
    			//check caputured
    		}
    		return false;

    	}
    	return false;
    }
    public static void select(int x, int y){
    	if (canSelect(x,y) == true){
    		xorigin = x;
    		yorigin = y;
    		currentPiece = pieceAt(x,y);
    		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
   		}
   		else {
   			System.out.println("Not a valid move");
   		}
	}
	public static void place(Piece p, int x, int y){
		xplaced = x;
		yplaced = y;
		if ((x + y) % 2 == 0){
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		}
		else{
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		}
		p.x = x;
		p.y = y;
		if (p.type == "pawn"){
			if (p.isFire = true){
				StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
			}else{
				StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
			}
		}
		if (p.type == "bomb"){
			if (p.isFire = true){
				StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
			}else{
				StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
			}
		}
		if (p.type == "shield"){
			if (p.isFire = true){
				StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
			}else{
				StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
			}
		}			
	}
	public static Piece remove(int x, int y){
		if (x > N || y >N || x < 0 || y < 0){
			return null;
		}
		if (pieceAt(x,y) == null){
			return null;
		}
		if ((x + y) % 2 == 0){
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		}
		else{
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		}
		Piece p = pieceAt(x,y);
		p.x = 10;
		p.y = 10;
		return p;
		
	}
	public boolean canEndTurn(){
		for (int i = 0; i < 23; i++){
			if (x0 == xplaced && y0 == yplaced){
				return false;
			}
		}
		//check if has captured piece
		//check if movement of pieces. If boards different? may need move function
		return true;
	}

	public void endTurn(){
		if (canEndTurn() == true){
			if (StdDrawPlus.isSpacePressed()){
				if (player == 1){
					player = 0;
				}
				if (player == 0){
					player = 1;
				}
				
			}
		}

	}

	public static String winner(){
		int fireleft = 0;
		int waterleft = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	Piece p = pieceAt(i,j);
            	if (pieceAt(i,j) != null){
            		if (p.isFire == true){
            			fireleft++;
          		  	}
            		if (p.isFire == false){
            			waterleft++;
            		}
            	}
            	if (pieceAt(i,j) == null){
            	}

			}
		}
		if (fireleft == 0 && waterleft == 0){
			return "No one wins";
		}
		if (fireleft == 0){
			return "Water wins";
		}
		if (waterleft == 0){
			return "Fire wins";
		}
		return null;
	}

}