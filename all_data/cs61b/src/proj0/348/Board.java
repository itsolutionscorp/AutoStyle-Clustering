public class Board{
	private boolean is_Empty;
	private Piece[][] pieceArray;
  private int my_Turn = 0;
  private int selected = 0;
  private boolean moved= false;
  private int selectedX=-1;/*selected piece's*/
  private int selectedY=-1;
  


	public static void main(String[] args){
		    StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board board = new Board(false);
        while(true){
          if (StdDrawPlus.mousePressed()){
            System.out.println("Mouse Pressed");
            int x = (int) StdDrawPlus.mouseX();
            int y = (int) StdDrawPlus.mouseY();
            if(board.canSelect(x, y)){
              board.select(x, y);
            }
          }
          if (StdDrawPlus.isSpacePressed()){
            System.out.println("Space Pressed");
            if (board.canEndTurn()){
              board.endTurn();
            }
          }
          board.drawBoard();
          StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
          StdDrawPlus.filledSquare(board.selectedX + .5, board.selectedY + .5, .5);
          board.drawPieces();
          StdDrawPlus.show(100);
          if (board.winner()!= null){
            System.out.println(board.winner().toUpperCase() + " WINS!!!!");
            return ;
          }
        }
      }

        


	public Board(boolean shouldbeEmpty){
    pieceArray = new Piece[8][8];
		is_Empty = shouldbeEmpty;
		if (is_Empty == false){
      for (int i = 0; i < 8; i+=2) {        
          pieceArray[i][0]= new Piece(true, this, i, 0, "pawn");
        }
      for (int i = 1; i < 8; i+=2) {
          pieceArray[i][1]= new Piece(true, this, i, 1, "shield");
        }
        for (int i = 0; i < 8; i+=2) {
          pieceArray[i][2]= new Piece(true, this, i, 2, "bomb");
        }
        for (int i = 1; i < 8; i+=2) {
          pieceArray[i][5]= new Piece(false, this, i, 5, "bomb");
        }
        for (int i = 0; i < 8; i+=2) {
          pieceArray[i][6]= new Piece(false, this, i, 6, "shield");
        }
        for (int i = 1; i < 8; i+=2) {
          pieceArray[i][7]= new Piece(false, this, i, 7, "pawn");
        }
      }
    }


  private void drawBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
              }
            }
  private void drawPieces(){
    for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
              if (pieceArray[i][j] != null){
                   if (pieceArray[i][j].isFire()){
                      if (pieceArray[i][j].isBomb()){
                        if (pieceArray[i][j].isKing()){
                          StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);                          
                        }else{
                          StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        }
                      }else if(pieceArray[i][j].isShield()){
                        if (pieceArray[i][j].isKing()){
                          StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                        }else{
                          StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        }
                       }else{
                        if (pieceArray[i][j].isKing()){
                          StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                        }else{
                          StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                      }
                   }else if (pieceArray[i][j].isFire()==false){
                      if (pieceArray[i][j].isBomb()){
                        if (pieceArray[i][j].isKing()){
                          StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                        }else{
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        }
                      }else if(pieceArray[i][j].isShield()){
                        if (pieceArray[i][j].isKing()){
                          StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                        }else{
                          StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        }
                      }else{
                        if (pieceArray[i][j].isKing()){
                          StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                        }else{
                          StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                        }
                      }
                    }
                }
              }
            }
  
    public Piece pieceAt(int x, int y){
    	if(x >7 || x < 0 || y > 7 || y < 0){
    		return null;
    	}else{
      return pieceArray[x][y];
      }
    }

  


public boolean canSelect(int x, int y){
  if (x > 7 || x < 0 || y > 7 || y < 0){
        return false;
      }
    if (selected > 0 && pieceAt(x, y) == null){
      if (moved == false && ((Math.abs(x - selectedX) == 1) && (Math.abs(y - selectedY)) == 1)) {
        if (pieceAt(selectedX, selectedY).isKing()){
          return true;
        }else if(pieceAt(selectedX,selectedY).isFire()){
          return y - selectedY == 1;

        }else if(pieceAt(selectedX,selectedY).isFire()== false){
          return y -selectedY == -1;
        }
      }else if (Math.abs(x - selectedX) == 2 && Math.abs(y - selectedY) == 2){
        int centerX = x - (x - selectedX)/2;
        int centerY = y - (y - selectedY)/2;
        if (pieceAt(centerX, centerY)== null){
          return false;
        }else{
          if (pieceAt(selectedX,selectedY).side() == pieceAt(centerX, centerY).side()){
            return false;
          }else{
            if (pieceAt(selectedX, selectedY).isKing()){
              return true;
            }else if(pieceAt(selectedX,selectedY).isFire()){
              return y - selectedY == 2;
            }else if(pieceAt(selectedX,selectedY).isFire()== false){
              return y -selectedY == -2;
            }
          }
        }
      }
    }else if (pieceAt(x, y) != null){
      if(selected>0 && moved == false && pieceAt(x, y).side()==pieceAt(selectedX,selectedY).side()){
        return true;
      }else if (selected == 0 && my_Turn==pieceAt(x, y).side()){
        return true;
      }
    }
    return false;
}




    public void select(int x, int y){
      if (selected>0 && selectedX != x && selectedY != y && pieceAt(x,y)==null){
        pieceAt(selectedX, selectedY).move(x, y);
        selectedX = x;
        selectedY = y;
        moved = true;
      }else {
        selectedX = x;
        selectedY = y;
        selected += 1;
      }
      System.out.println("Piece " + selectedX + " " + selectedY +" is selected");
      System.out.println("Location " + x + " " + y +" is selected");
    }



    public Piece remove(int x, int y){
      System.out.println("Called remove at " + x + " " + y);
      if (x >7 || x < 0 || y > 7 || y < 0){
        System.out.println("Input is out of bounds.");
        return null;
      }else if (pieceAt(x, y)==null){
        System.out.println("There is no piece at this place.");
        return null;
      
      }else{
        Piece temp = pieceAt(x,y);
        pieceArray[x][y] = null;
        System.out.println("Removed " + x + " " + y);
        return temp;
      }
    }


    public void place(Piece p, int x, int y){
      if (0 <= x && x < 8 && 0 <= y && y < 8 && p != null){
          pieceArray[x][y] = p;
          System.out.println( x + " " + y + " has been placed a piece");
      }
    }

    public boolean canEndTurn(){
      return moved;

    }

    public void endTurn(){
      if (canEndTurn()){
        moved = false;
        if (pieceAt(selectedX,selectedY) != null){
          pieceAt(selectedX, selectedY).doneCapturing();
        }
        selected = 0;
        my_Turn = 1 - my_Turn;
        System.out.println("Current turn ended.");
      }
    }
    


    public String winner(){
      int num_of_fire = 0;
      int num_of_water = 0;
      for (int i = 0; i < 8; i++){
        for (int j = 0; j < 8; j++){
          if (pieceAt(i, j) != null){
            if (pieceAt(i, j).isFire()){
              num_of_fire++;
            }else{
              num_of_water++;
            }
          }
        }
      }
      if(num_of_fire> 0 && num_of_water == 0){
        return "Fire";
      }
      if(num_of_fire== 0 && num_of_water > 0){
        return "Water";
      }
      if(num_of_water==num_of_fire && num_of_water ==0){
        return "No one";
      }
      if(num_of_fire>0 && num_of_water >0){
        return null;
      }
      return null;
    }
}







