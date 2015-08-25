import java.util.ArrayList;

public class Block {

	private int x1, x2, y1, y2;
	private String myMove;

	public Block(ArrayList<Integer> coords) {
  	  y1 = coords.get(0);
  	  x1 = coords.get(1);
  	  y2 = coords.get(2);
  	  x2 = coords.get(3);
  	  myMove = null;
	}

	public Block(int left, int right, int up, int down) {
  	  x1 = left;
  	  y1 = up;
  	  x2 = right;
  	  y2 = down;
  	  myMove = null;
	}

	public Block moveUp() {
  	  myMove = "" + y1 + " " + x1 + " " + (y1-1) + " " + x1;
  	  return new Block(x1, x2, y1 - 1, y2 - 1);
	}

	public Block moveDown() {
  	  myMove = "" + y1 + " " + x1 + " " + (y1+1) + " " + x1;
  	  return new Block(x1, x2, y1 + 1, y2 + 1);
	}

	public Block moveLeft() {
  	  myMove = "" + y1 + " " + x1 + " " + y1 + " " + (x1-1);
  	  return new Block(x1 - 1, x2 - 1, y1, y2);
	}

	public Block moveRight() {
  	  myMove = "" + y1 + " " + x1 + " " + y1 + " " + (x1+1);
  	  return new Block(x1 + 1, x2 + 1, y1, y2);
	}
	
	public boolean sameShape(Block b){
		if ((x2-x1)==(b.x2()-b.x1()) && (y1-y2)==(b.y1()-b.y2())){
			return true;
		} else {
			return false;
		}
	}

	public int x1() {
  	  return x1;
	}

	public int x2() {
  	  return x2;
	}

	public int y1() {
  	  return y1;
	}

	public int y2() {
  	  return y2;
	}

	// don't call before moving!!!!!
	public String move() {
  	  return myMove;
	}
    
	/**
 	* @param Block b
 	* @return difference with the coordinates between two blocks.
 	*/
	public int distance(Block b) {
  	  return (int) Math.sqrt((b.x1()-x1)*(b.x1()-x1) + (b.y1()-y1)*(b.y1()-y1));
	}

	@Override
	public String toString() {
  	  return "((" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + "))";
	}
    
    public Block move(ArrayList<Integer> move) {
   	 myMove = "" + y1 + " " + x1 + " " + move.get(0) + " " + move.get(1);
   	 return new Block(move);
    }

    
	/**
 	* Returns hashCode of Block object. Same if they have same position.
 	*/
	@Override
	public int hashCode() {
  	  int hash = 1 + (((x1 * 257) + x2) * 257 + y1) * 257 + y2;
  	  return hash;
	}

	@Override
	public boolean equals(Object O) {
  	  if (O instanceof Block) {
  		  if (((Block) O).x1() != x1)
  			  return false;
  		  if (((Block) O).y1() != y1)
  			  return false;
  		  if (((Block) O).x2() != x2)
  			  return false;
  		  if (((Block) O).y2() != y2)
  			  return false;
  		  return true;
  	  }
  	  return false;
	}

}









