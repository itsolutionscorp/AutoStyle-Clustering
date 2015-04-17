import static org.junit.Assert.*;
import org.junit.Test;

public class Board{
	
	private static boolean empty_board;
	private Piece array_pieces[][];
	private Piece selected_piece = null;
  
	
	private boolean fire_captured = false; 
  private boolean water_captured = false;
	private boolean fire_moved = false; 
  private boolean water_moved = false;
	private boolean gameover = false;
	private int current_player = 0;
  private boolean turnchange = false;

	public Board (boolean shouldBeEmpty){
		empty_board = shouldBeEmpty;
		array_pieces = new Piece[8][8];
		int N = 8;
    if (empty_board == false) {
		for (int n = 0; n < N; n += 2){
              		array_pieces[n][0] = new Piece(true, this, n, 0, "pawn");
              		array_pieces[n + 1][1] = new Piece(true, this, n + 1, 1, "shield");
              		array_pieces[n][2] = new Piece(true, this, n, 2, "bomb");
              		array_pieces[n + 1][5] = new Piece(false, this, n + 1, 5, "bomb");
              		array_pieces[n][6] = new Piece(false, this, n, 6, "shield");
              		array_pieces[n + 1][7] = new Piece(false, this, n + 1, 7, "pawn"); }
	}
}
	
	private void drawBoard(int N) {
			for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {
                	if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	else    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	if (array_pieces[i][j] != null){ 
                		if (array_pieces[i][j].isFire()){
               				if (array_pieces[i][j].isShield()){
               					if (array_pieces[i][j].isKing()){
               						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1 ,1); 
               					} else {
               						StdDrawPlus.picture(i + 0.5, j + 0.5,"img/shield-fire.png", 1 ,1); 
               					}
               				} else if ((array_pieces[i][j].isBomb())){
               					if (array_pieces[i][j].isKing()){
               						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1 ,1); 
               					} else {
               						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1 ,1);
               					}
               					 }
               		 		else{ 
               		 			if (array_pieces[i][j].isKing()){
               		 				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1 ,1); 
               		 			} else {
               		 				StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1 ,1);
               		 			}
                			 }
               			} else {
               				if (array_pieces[i][j].isShield()){
               					if (array_pieces[i][j].isKing()){
               						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1 ,1); }
               					else {
               						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1 ,1); }
               					}
               				else if (array_pieces[i][j].isBomb()){
               					if (array_pieces[i][j].isKing()){
               						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1 ,1); }
               					else {
               						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1 ,1); }
               					}
    					 	else {
    					 		if (array_pieces[i][j].isKing()){
               						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1 ,1); }
               					else {
               						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1 ,1); }
               					} 
                		} 
                    }
                }
            }
        }
	public static void main(String[] args){
		
		System.out.println("after runTests!");
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(empty_board);
        
        while(board.gameover == false) {
          //System.out.println(board.captured);
        	board.drawBoard(N);
                
                if (StdDrawPlus.mousePressed()){
                	int x_coordiante = (int)StdDrawPlus.mouseX();
                	int y_coordinate = (int)StdDrawPlus.mouseY();
                	
                	if ((board.canSelect(x_coordiante,y_coordinate) == true)){
                		board.select(x_coordiante,y_coordinate);
                  }
                	} else if (StdDrawPlus.isSpacePressed()){
                		if (board.canEndTurn() == true){
                			board.endTurn();
                		}
                	}
                  board.drawBoard(N);
                
					StdDrawPlus.show(100); 
                	} board.winner();
                }        

    private int getXposition (Piece p){
      //got significant help from a friend (Paige Plander) in order to create getter methods for X and Y.
      //Help in terms of the idea. 
    	int ans = 0;
    	for (int i = 0; i < 8; i += 1){
    		for (int j = 0; j < 8; j += 1){
    			if (array_pieces[i][j] == p) {
    				ans = i;
    			} 
    		}
    	} return ans;
    }

    private int getYposition(Piece p){
      //got significant help from a friend (Paige Plander) in order to create getter methods for X and Y. 
      //Help in terms of the idea. 
    	int answer = 0;
    	for (int a = 0; a < 8; a += 1){
    		for (int b = 0; b < 8; b += 1){
    		    if (array_pieces[a][b] == p){
    		    	answer = b;
    		    }
    		}
    	} return answer;
    }
    
	private boolean outofbounds (int a){
		if ((a < 0) || (a > 7)){
			return true; 
		} else {
			return false;
		}
	}

	public Piece pieceAt(int x, int y){
		
  		if ((outofbounds(x) == true) || (outofbounds(y) == true)){
 			return null; 
    	} else if (array_pieces[x][y] == null){
 			return null;
 		} else {
 			return array_pieces[x][y];
 		}
 	}

 	public boolean canSelect (int x, int y){
    if (current_player == 0) {
      System.out.println("going into canSelect current_player = 0");
      if ((pieceAt(x,y) != null) && (pieceAt(x,y).isFire())){ 
      System.out.println(selected_piece); 
          if ((selected_piece == null) || ((selected_piece != null) && (fire_moved == false))){
            System.out.println("Fire piece cselected");
            //selected_piece = pieceAt(x,y);
            return true; 
          }
        } 
      else if ((selected_piece != null) && (selected_piece.isFire()) && (pieceAt(x,y) == null)){
          boolean validity = validMove(getXposition(selected_piece),getYposition(selected_piece),x,y);
          if ((fire_moved == false) && (validity)){
            System.out.println("Fire piece cselected. goes into fire_moved == false) && (validity)");
              return true; 
          }
          else if ((fire_captured == true) && (selected_piece != null) && (validity) && (validcapturemove(selected_piece,x,y))){
            System.out.println("Fire piece cselected. goes into (fire_captured == true) && (selected_piece != null) && (validity)");
              return true; 
         }
      } 
    System.out.println("Did not select fire piece");
    return false; 
    } 
    else if (current_player == 1){
      System.out.println("Entered water loop");
      if ((pieceAt(x,y) != null) && (pieceAt(x,y).isFire() == false)){  
        System.out.println(selected_piece);
        System.out.println(water_moved);
          if ((selected_piece == null) || ((selected_piece != null) && (water_moved == false))){
            System.out.println("passes if statement");
            //selected_piece = pieceAt(x,y);
            return true; 
          }
        } 
      else if ((selected_piece != null) && (selected_piece.isFire() == false) && (pieceAt(x,y) == null)){
          boolean validity = validMove(getXposition(selected_piece),getYposition(selected_piece),x,y);
          if ((water_moved == false) && (validity)){
            System.out.println("Water piece cselected");
              return true; 
          }
          else if ((water_captured == true) && (selected_piece != null) && (validity) && (validcapturemove(selected_piece,x,y))){
            System.out.println("Fire piece cselected");
              return true; 
         }
      } 
    } System.out.println("Did not select Water piece");
      return false;
     
    } 
 		
 	private boolean validcapturemove (Piece p,int x, int y){
    int p_x = getXposition(p);
    int p_y = getYposition(p);
    int x_diff = x - p_x; 
    int y_diff = y - p_y;
    if ((Math.abs(x_diff) == 2) && (Math.abs(y_diff) == 2)){
      return true; 
    } else {
      return false; 
    }
  }

 	private boolean validMove(int xi, int yi, int xf, int yf){
 		int x_diff = (xf - xi);
 		int y_diff = (yf - yi);
 		if ((outofbounds(xf)) || (outofbounds(yf))){
 		 	return false; }
 		else if ((pieceAt(xf,yf) != null) || (pieceAt(xi,yi) == null)){
 			return false;
 		}
 		else if ((Math.abs(x_diff) > 2) || (Math.abs(y_diff) > 2)){
 			return false; }
 		else if ((x_diff == 0) || (y_diff == 0)){
 			return false;
 		} else if (pieceAt(xi,yi).isFire()){
 			if (pieceAt(xi,yi).isKing()){
 				return fireking(xi,yi,xf,yf);
 			}
 			else if ((x_diff == 2) && (y_diff == 2)){
 				if ((pieceAt(xi + 1, yi + 1) != null) && (pieceAt(xi + 1, yi + 1)).isFire() == false){
 					return true; }
 				return false; }
	        else if ((x_diff == -2) && (y_diff == 2)){
					if ((pieceAt(xi - 1, yi + 1) != null) && (pieceAt(xi - 1, yi + 1)).isFire() == false){
 						return true; }
 					return false; }
	        else {
				if ((Math.abs(x_diff) == 1) && (y_diff == 1)){
					return true;
				} return false; }
		} else if (!pieceAt(xi,yi).isFire()){
			if (pieceAt(xi,yi).isKing()){
 				return waterking(xi,yi,xf,yf);}
 			else if ((x_diff == 2) && (y_diff == -2)){
 				if ((pieceAt(xi + 1, yi - 1) != null) && (pieceAt(xi + 1, yi - 1)).isFire()){
 					return true; }
 				return false; }
		    else if ((x_diff == -2) && (y_diff == -2)){
					if ((pieceAt(xi - 1, yi - 1) != null) && (pieceAt(xi - 1, yi - 1)).isFire()){
 					return true; }
 				return false; }
			else {
				if ((Math.abs(x_diff) == 1) && (y_diff == -1)){
					return true;
				} return false; } 
			} return false;
 		}

 	private boolean fireking (int xi, int yi, int xf, int yf){
 		int x_diff_king = (xf - xi);
 		int y_diff_king = (yf - yi);
 		if ((x_diff_king == 2) && (y_diff_king == 2)){
 				if ((pieceAt(xi + 1, yi + 1) != null) && (pieceAt(xi + 1, yi + 1)).isFire() == false){
 					return true; }
 				return false; }
	    else if ((x_diff_king == -2) && (y_diff_king == 2)){
				if ((pieceAt(xi - 1, yi + 1) != null) && (pieceAt(xi - 1, yi + 1)).isFire() == false){
 					return true; }
 				return false; }
	    else if ((x_diff_king == 2) && (y_diff_king == -2)){
				if ((pieceAt(xi + 1, yi - 1) != null) && (pieceAt(xi + 1, yi - 1)).isFire() == false){
 					return true; }
 				return false; }
		else if ((x_diff_king == -2) && (y_diff_king == -2)){
				if ((pieceAt(xi - 1, yi - 1) != null) && (pieceAt(xi - 1, yi - 1)).isFire() == false){
 					return true; }
 				return false; }
 		else if ((Math.abs(x_diff_king) == 1) && (Math.abs(y_diff_king) == 1)){
				return true; }
		else {
			return false;
		}
	}

	private boolean waterking (int xi, int yi, int xf, int yf){
 		int x_diff_water = (xf - xi);
 		int y_diff_water = (yf - yi);
 		if ((x_diff_water == 2) && (y_diff_water == 2)){
 				if ((pieceAt(xi + 1, yi + 1) != null) && (pieceAt(xi + 1, yi + 1)).isFire()){
 					return true; }
 				return false; }
	    else if ((x_diff_water == -2) && (y_diff_water == 2)){
				if ((pieceAt(xi - 1, yi + 1) != null) && (pieceAt(xi - 1, yi + 1)).isFire()){
 					return true; }
 				return false; }
	    else if ((x_diff_water == 2) && (y_diff_water == -2)){
				if ((pieceAt(xi + 1, yi - 1) != null) && (pieceAt(xi + 1, yi - 1)).isFire()){
 					return true; }
 				return false; }
		else if ((x_diff_water == -2) && (y_diff_water == -2)){
				if ((pieceAt(xi - 1, yi - 1) != null) && (pieceAt(xi - 1, yi - 1)).isFire()){
 					return true; }
 				return false; }
 		else if ((Math.abs(x_diff_water) == 1) && (Math.abs(y_diff_water) == 1)){
				return true; }
		else {
			return false;
		}
	}

 	// public void select(int x, int y){
 	// 	Piece another = pieceAt(x,y);
  //   System.out.println(another);
 	// 	if (another == null){
  //     System.out.println("goes into anothe")
 	// 		if ((current_player == 0) && (selected_piece != null) && (selected_piece.isFire())){
  //       System.out.println("Its moving the fire piece.");
  //       fire_moved = true;
  //       selected_piece.move(x,y);
  //     }
 	// 	 else if ((current_player == 1) && (selected_piece != null) && (selected_piece.isFire() == false)){
  //       System.out.println("Its moving the water piece.");
  //       water_moved = true;
  //       selected_piece.move(x,y);
  //     }
  //   }
  //    else {
  //     if (current_player == 0){
  //       System.out.println("goes into the right place");
  //       selected_piece = another;
  //     } 
  //     else if (current_player == 1){
  //       selected_piece = another; 
  //     }
 			
 	// 	}
 	// }

  public void select(int x, int y){
    Piece another = pieceAt(x,y);
    if (another == null){
      if (selected_piece != null){
        System.out.println("Goes into the loop");
        selected_piece.move(x,y);
      }
    } else {
      selected_piece = another;
    }
  }

 	public void place(Piece p, int x, int y){

    //Recieved help from a friend (Nicki Peternal)in setting up the loop. 
    if((outofbounds(x)) || (outofbounds(y)) || (p == null)){
      System.out.println("place invalid");
      return ; }
 		//validMove(getXposition(p),getYposition(p),x,y) == false)
    boolean place_exists = false; 
    for (int i = 0; i < 8; i ++){
      for (int j = 0; j < 8; j ++){
        if (array_pieces[i][j] == p) {
          place_exists = true;
          array_pieces[i][j] = null;
        }
      }
    }
    
 		 if(place_exists == false) {
        array_pieces[x][y] = p;  
      } else {
        System.out.println("goes inside place_exists = true");
        array_pieces[x][y] = p; 
        System.out.println(array_pieces[x][y]);
        if (p.isFire()){
          fire_moved = true;
        } else {
          water_moved = true;
        }
        System.out.println(p);
        System.out.println(selected_piece);
        
      }
 			
    }
	

 	public Piece remove(int x, int y){
 		if ((outofbounds(x)) || (outofbounds(y))){
 			System.out.println("One or both of the inputs are out of bounds.");
 			return null; }
 		else if (array_pieces[x][y] == null){
 			System.out.println("There is no piece at position (x,y).");
 			return null; }
 		else{
 			Piece removed_piece = array_pieces[x][y];
 			array_pieces[x][y] = null; 
 			if ((selected_piece != null) && (selected_piece.isFire())){
 				selected_piece.hasCaptured();
 				fire_captured = true;
 			} else if ((selected_piece != null) && (!selected_piece.isFire())){
        selected_piece.hasCaptured();
        water_captured = true;
      }
 			return removed_piece;
 		}
 	  }

 	  public boolean canEndTurn(){
      if (current_player == 0){
        if ((fire_moved == true) || (fire_captured == true)){
          turnchange = true;
          System.out.println("ends turn");
            return true;
        } else{
            return false;
      }
      } else {
        if ((water_moved == true) || (water_captured == true)){
            turnchange = true;
            System.out.println("end turn");
            return true;
        } else{
            return false;
      }
      }
 	  	
 	  }

 	 public void endTurn(){
 	 	if (canEndTurn() == true){
      if (current_player == 0){
        fire_moved = false;
        if (selected_piece != null){
            selected_piece.doneCapturing(); 
        }
        selected_piece = null;
        fire_captured = false;
        System.out.println("testing current_player");
        current_player = 1;
        //turnchange = false;
        System.out.println("changed to water");
        System.out.println(current_player);
        System.out.println(selected_piece);
        System.out.println(turnchange);

      } else {
         water_moved = false; 

         if (selected_piece != null) {
          selected_piece.doneCapturing(); 
        }
         selected_piece = null; 
         water_captured = false;
         current_player = 0;
         //turnchange = false;
         System.out.println("changed to fire");
         System.out.println(current_player);
         System.out.println(selected_piece);
         System.out.println(turnchange);
      }
 	 		if (winner() != null){
 	 			gameover = true;
 	 		}
 	 	}
 	 }

 	 private int[] count_pieces (){
 	 	int[] side_numbers = new int[2];
 	 	int default_N = 8;
 	 	int fire_pieces = 0; 
 	 	int water_pieces = 0;
 	 	for (int row_count = 0; row_count < default_N; row_count += 1){
 	 		for (int col_count = 0; col_count < default_N; col_count += 1){
 	 			if (array_pieces [row_count][col_count] != null){
 	 				if (array_pieces[row_count][col_count].isFire() == true){
 	 					fire_pieces += 1;
 	 				} else {
 	 					water_pieces += 1; }
 	 				}
 				}
 			} side_numbers[0] = fire_pieces;
 			  side_numbers[1] = water_pieces;
        System.out.println(side_numbers[0]);
        System.out.println(side_numbers[1]);
 			  return side_numbers;
 		}

 	 public String winner(){
 	 	int[] copy_side_numbers = count_pieces();
 	 	int fire_pieces = copy_side_numbers[0];
 	 	int water_pieces = copy_side_numbers[1];
    System.out.println(fire_pieces);
    System.out.println(water_pieces);
 	 	if ((fire_pieces != 0) && (water_pieces != 0)){
 	 		gameover = false;
 	 		return null;
 	 	}
 	 	else if ((fire_pieces != 0) && (water_pieces == 0)){
      System.out.println("condition working");
 	 		gameover = true;
 	 		return ("Fire"); }
 	 	 else if ((water_pieces == 0) && (fire_pieces == 0)){
      System.out.println("why would it go in there?!?!");
 	 	 	gameover = true;
 	 	 	return ("No one"); }
 	 	 else {
 	 	 	gameover = true;
 	 	 	return ("Water"); }
 		 }
} 
