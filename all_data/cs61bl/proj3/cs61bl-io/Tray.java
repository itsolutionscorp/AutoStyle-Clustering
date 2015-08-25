import java.util.HashMap;
/**
 * The Tray class represent the tray configuration, contains the parent and children trays, represent where it come from
 * and what does it produces, has boolean 2d array represent the occupied blocks, x, y size of the tray, and hashmap
 * all blocks in this tray.
 */
public class Tray {
	private Tray parent;	
	private boolean[][] tray;
	private int x;
	private int y;
	private HashMap<String, Block> myBlocks;
	private String move;

	/**
	 * Constructor of Tray, making new Tray involving create 2-d booleans to represent the Tray configuration.
	 * Also initilizing myBlock to store all blocks that this Tray contains.
	 * @param x int length vertical of your Tray
	 * @param y int length horizontal of your Tray
	 * @param p Tray parent Tray that make this Tray.
	 * @param myMove String represenation of move that result this Tray
	 */
	public Tray(int x, int y, Tray p, String myMove){
		this.x = x;
		this.y = y;
		move = myMove;
		tray = new boolean[x][y];
		myBlocks = new HashMap<String, Block>();
		parent = p;
	}	
	/**
	 * clear the memory usage of this Tray, HashMap myBlock and Boolean[][].
	 * use this method when we are sure this Tray isn't goal Tray. 
	 * Help memory efficiency.
	 */
	public void setNull(){
		tray = null;
		myBlocks = null;
	}
	/**
	 * Sets the blocks according to the argument p.
	 * @param p HashMap of blocks to set
	 */
	public void setMyBlocks(HashMap<String, Block> p){
		myBlocks = p;		
	}
	/**
	 * Updates the boolean array of trays. if a block's area intersect with other block's, exit the program.
	 * Block is suppose to be individual.
	 */
	public void updateBoard(){
		tray = new boolean[x][y];
		for (Block p: myBlocks.values()){
			for (int i = p.getX1(); i < p.getX2() +1; i++){
				for (int j= p.getY1(); j < p.getY2()+1; j++){
					if (tray[i][j])
						System.exit(0);					
					else
						tray[i][j] = true;
				}
			}			
		}
	}

	/**
	 * Returns true if the Tray contains all the blocks inside the
	 * Block HashMap.
	 * @param p HashMap<String, Block> Block to compare
	 * @return true if this Tray contains all the blocks from p
	 */
	public boolean contains(HashMap<String, Block> p){		
		for (String str: p.keySet()){
			if (!myBlocks.containsKey(str))
				return false;			
		}
		return true;
	}

	/**
	 * The hashCode for a Tray is the String int of the Tray by sum up all block coordinate and multiple it prime number
	 * plus with the prime number * block hashcode, making it really unique.
	 */
	public int hashCode() {		
		int value = 0;		
		for (Block b: myBlocks.values()){			
			value = 719*value+ 719*b.getX1() + 719*b.getX2() + 719*b.getY2()+ 718*b.getY2()+719*b.hashCode();			
		}
		return value;
	}
	/**
	 * Helper method to return the tray boolean representation. 
	 * @return Tray boolean[][]
	 */
	public boolean[][] getTrayConfiguration(){
		return tray;
	}

	/**
	 * Helper method to return HashMap of Blocks in this Tray. 
	 * @return myBlocks HashMap<String, Block>
	 */
	public HashMap<String, Block> getAllBlock(){
		return myBlocks;
	}
	/**
	 * Helper method to get the parent Tray that create this Tray, help with the printing move String.
	 * @return Tray parent
	 */
	public Tray getParent(){
		return parent;
	}
	/**
	 * Helper method to get the String representation of the move from parent Tray that result create this new Tray.
	 * @return String move
	 */
	public String getMoveString(){
		return move;
	}	
}
