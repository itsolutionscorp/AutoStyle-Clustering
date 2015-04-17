/*Chengyu Si(Cynthia)
 *Februay 6, 2015 
*/

public class Board{ 
    
    private int N = 8, selectedX, selectedY;
    private Piece [][] pieces = new Piece[N][N];
    private boolean moved = false, selecting = false;
    private Piece selected = null;
    private boolean fireTurn = true;
    

    private void drawBoard(int N) {

	String pic; 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		
                if (pieces[i][j] != null) {
		   if (pieces[i][j].isBomb())
		       pic = "bomb";
		   else
		   if (pieces[i][j].isShield())
		       pic = "shield";
		   else
		       pic = "pawn";
		   if (pieces[i][j].isFire())
		       pic = pic+"-fire";
		   else
		       pic = pic+"-water";
		   if (pieces[i][j].isKing())
		       pic = pic+"-crowned";
		   StdDrawPlus.picture(i + .5, j + .5, "img/"+pic+".png", 1, 1);
                }
		 
            }
        }
    }

    public Board(boolean shouldBeEmpty){
	
	for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++)
		pieces[i][j] = null;

	if (!shouldBeEmpty){
	    for (int i = 0; i < N; i++)
		if (i%2 == 0){
		    pieces[i][0] = new Piece(true, this, i, 0, "pawn");
		    pieces[i][N-2] = new Piece(false, this, i, N-2, "shield");
		    pieces[i][2] = new Piece(true, this, i, 2, "bomb");
		}
		else{
		    pieces[i][1] = new Piece(true, this, i, 1, "shield");
		    pieces[i][N-3] = new Piece(false, this, i, N-3, "bomb");
		    pieces[i][N-1] = new Piece(false, this, i, N-1, "pawn");
		}
	}
    }

    private boolean ifInBounds(int x, int y){
	return (x>=0 && x<N && y>=0 && y<N);
    }
    
    public Piece pieceAt(int x, int y){
	if (ifInBounds(x, y) && pieces[x][y]!=null)
	    return pieces[x][y];
	return null;
    }

    private boolean validChoice(int x, int y){
	if (Math.abs(x-selectedX) != 1 || Math.abs(y-selectedY) != 1) 
	    return false;
	if (selected.isKing()) 
	    return true;
	if ((selected.isFire() && y-selectedY > 0) || (!selected.isFire() && y-selectedY < 0) )
	    return true;
	return false;
    }

    private boolean canCapture(int x, int y){
	if (Math.abs(selectedX-x) != 2 || Math.abs(selectedY-y) != 2)
	    return false;
	int Ax = (int)(selectedX+x)/2;
	int Ay = (int)(selectedY+y)/2;
	if (!ifInBounds(Ax, Ay)) return false;
	if (pieces[Ax][Ay] == null) return false; 
	if (pieces[Ax][Ay].isFire() == fireTurn) return false;
	if (selected.isKing()) return true;
	if ((selected.isFire() && y-selectedY > 0) || (!selected.isFire() && y-selectedY < 0) )
	    return true;
	return false;
    }

    public boolean canSelect(int x, int y){
	if (! ifInBounds(x, y))
	    return false;

	if (pieces[x][y] != null){
	    Piece p = pieces[x][y];
	    if (p.isFire() != fireTurn)
		return false;
	    if ((selecting && !moved) || (!selecting && !moved))
		return true;
	    return false;
	}
	else{
	    if (selected != null && !moved && (validChoice(x, y)||canCapture(x, y)))
		return true;
	    if (selected != null && moved)
		if (selected.hasCaptured() && canCapture(x, y))
		    return true;
	    return false;
	}
       
    }
    
    public void place(Piece p, int x, int y){
	if (ifInBounds(x, y) && p!=null){
	    pieces[x][y] = p;
	    for(int i=0; i<8; i++)
		for(int j=0; j<8; j++)
		    if(pieces[i][j]!=null && !(i == x && j == y) && pieces[i][j] == p)
	              remove(i, j);
	}
    }

    public void select(int x, int y){
	if (x>=N || x<0 || y>=N || y<0) return;
	if (pieces[x][y] != null){
	    selected = pieces[x][y];
	    selectedX = x;
	    selectedY = y;
	}
	
	if (selecting == false)
	    selecting = true;
	else{
	    if(pieces[x][y] == null){
		selected.move(x, y);
		selectedX = x;
		selectedY = y;
		if (selected.hasCaptured())
		    if (selected.isBomb()){
			selecting = false;
			selected = null;
			for (int i=x-1; i<= x+1; i++)
			    for (int j = y-1; j <= y+1; j++)
				if (ifInBounds(i, j))
				    if(pieces[i][j] != null)
					if(!pieces[i][j].isShield())
					    remove(i, j);
		    }
	    moved = true;
	    }	
	
	   
	    //StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	}
   }

    public Piece remove(int x, int y){
	if(!ifInBounds(x, y)){
	    System.out.println("(x, y) is out of bounds.");
	    return null;
	}
	if(pieces[x][y] == null){
	    System.out.println("There is no piece at (x, y).");
	    return null;   
	}
	Piece p = pieces[x][y];
	pieces[x][y] = null;
	return p;
    }
    
    public boolean canEndTurn(){
	return moved;
    }
    
    public void endTurn(){
	//fireTurn = !fireTurn;
	if (fireTurn == true) fireTurn = false;
	else                  fireTurn = true;
	selecting = false;
	selected = null;
	moved = false;

	for (int i=0; i<8; i++)
	    for (int j=0; j<8; j++)
		if(pieces[i][j] != null)
		    pieces[i][j].doneCapturing();
    }
    
    public String winner(){
	int water = 0, fire = 0;
	for (int i=0; i<8; i++)
	    for (int j=0; j<8; j++)
		if(pieces[i][j] != null)
		    if(pieces[i][j].isFire()) 
			fire ++;
		    else
			water ++;
	if(water+fire == 0) return "No one";
	if(water*fire != 0) return null;
	if(fire == 0) return "Water";
	return "Fire";
    }
   
    public static void main(String[] args){
	
	Board boards = new Board(false);

	StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        boards.drawBoard(8);
        while(true) {
             if (StdDrawPlus.mousePressed()) {
                double x1 = StdDrawPlus.mouseX();
                double y1 = StdDrawPlus.mouseY();
                int x=(int) x1;
                int y=(int) y1;
                if(boards.canSelect(x, y))
		    if(boards.selecting==false){
			boards.select(x, y); 
			boards.selecting=true;
		    }
		    else
                    if(boards.selecting==true){
			if(boards.pieces[x][y]==null)
			    if(boards.validChoice(x, y)){
				boards.selected.move(x, y);
				boards.moved=true; 
				boards.drawBoard(8);
			    }
                    if(boards.pieces[x][y]!=null&&boards.moved==false){
                         boards.drawBoard(8); 
                         boards.select(x, y);
                    }
                    if(boards.pieces[x][y]!=null)
                    if(boards.pieces[x][y].hasCaptured()&&boards.moved==true){
                           	boards.drawBoard(8);
                         boards.select(x, y);
                    }

                 	}
	
	     } 
	    if(StdDrawPlus.isSpacePressed()){
		if(boards.canEndTurn()){
		    if(boards.winner()!=null){
			System.out.printf("%s", boards.winner());
		    }
		    else
		    boards.endTurn();
		}
	    }
            StdDrawPlus.show(100);
	}
    }    

}
