import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Tray implements Comparable<Tray>{
	private boolean[][] blocks;
	private ArrayList<Block> blockSet;
	private HashSet<Integer> blockIDSet;
	private HashMap<Integer, Block> hashByBlockID;
	private HashMap<ArrayList<Integer>, LinkedList<Block>> hashBySize;
	
	private ArrayList<Integer> moveToGetHere;
	
	private HashSet<Tray> myChildren;
	private Tray myParent;
	
	private int ID;
	private int myPriority;
	
	public Tray(Integer[] dimension, ArrayList<ArrayList<Integer>> myCoords, Tray goalTray){
		ArrayList<Block> newBlocks = new ArrayList<Block>();
		for(ArrayList<Integer> i: myCoords){
			if (goalTray != null){
				ArrayList<Integer> coords = i;
				int height = coords.get(2) - coords.get(0) + 1;
				int width = coords.get(3) - coords.get(1) + 1;
				ArrayList<Integer> WH = new ArrayList<Integer>();
				WH.add(width);
				WH.add(height);
				newBlocks.add(new Block(i, goalTray.hashBySize.get(WH), dimension));
			} else{
				newBlocks.add(new Block(i, dimension));
			}
		}
		init(null, null, dimension, newBlocks);
	}
	
	public Tray(Tray parent, ArrayList<Integer> prevMove, Integer[] dimension, ArrayList<Block> newBlocks){
		init(parent, prevMove, dimension, newBlocks);
	}

	public void init(Tray parent, ArrayList<Integer> prevMove, Integer[] dimension, ArrayList<Block> newBlocks){
		moveToGetHere = prevMove;
		myParent = parent;
		blockIDSet = new HashSet<Integer>();
		myChildren = new HashSet<Tray>();
		blocks = new boolean[dimension[0]][dimension[1]];
		hashByBlockID = new HashMap<Integer, Block>(); 
		hashBySize = new HashMap<ArrayList<Integer>, LinkedList<Block>>();
		blockSet = newBlocks;
		
		for(Block b: newBlocks){
			ArrayList<Integer> tempPos = b.getCoords();
			for(int row = tempPos.get(0); row <= tempPos.get(2); row ++){
				for(int col = tempPos.get(1); col <= tempPos.get(3); col ++){
					blocks[row][col] = true;
				}
			}
			hashByBlockID.put(b.getID(), b);
			blockIDSet.add(b.getID());
			if (hashBySize.containsKey(b.getWH())){
				hashBySize.get(b.getWH()).add(b);
			} else{
				LinkedList<Block> temp =new LinkedList<Block>();
				temp.add(b);
				hashBySize.put(b.getWH(), temp);
				myPriority += b.getPriority();
			}
		}
		ID = this.makeHashCode();
	}
	
	public void reset(){
		blocks = null;
		hashByBlockID = null;
		blockSet = null;
		blockIDSet = null;
		hashBySize = null;
		myChildren = null;
	}
	
	public void forceUP(){
		myPriority = 0;
	}

	
	public void addChildren(HashSet<Tray> child){
		myChildren.addAll(child);
	}
	

	public boolean isEmpty(HashSet<ArrayList<Integer>> spaces) {
		if (spaces.isEmpty()){
			return false;
		}
		for (ArrayList<Integer> i: spaces) {
			if(blocks[i.get(0)][i.get(1)]) {
				return false;
			}
		}
		return true;
	}
	
	public int compareTo(Tray myTray) {
		return (int) myPriority - (int) myTray.getPriority();
	}
	
	/**
	 * The block objects are "equal" if they have the same area (h * d) 
	 * and the same location. Therefore, our hashCode is dependent on the
	 * height, depth and the location of the block.
	 */
	@Override
	public int hashCode(){
		return ID;
	}
	
	public int makeHashCode(){
		int hashCode = 1;
		for (HashMap.Entry<Integer, Block> b : hashByBlockID.entrySet()){
			hashCode = 19 * hashCode + b.getKey();
		}
		return hashCode;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (getHashBySize().keySet().size() == 1){
			for(Block b: ((Tray) o).getBlockSet()){
				Block ourB = getHashByBlockID().get(b.getCoords());
				if(ourB == null || !b.equals(ourB)){
					return false;
				}
			}
			return true;
		}
		
		return ((Tray) o).getID() == ID;
	}
	
	public boolean GoalEquals(Tray goal){
		for(Integer i: goal.getBlockIDSet()){
			if(!blockIDSet.contains(i)){
				return false;
			}
		}
		return true;
	}
	
	

	public int getID(){
		return ID;
	}
	
	public int getPriority(){
		return myPriority;
	}
	
	public Tray getParent(){
		return myParent;
	}
	
	public boolean[][] getBlocks() {
		return blocks;
	}
	
	public ArrayList<Block> getBlockSet(){
		return blockSet;
	}
	
	public HashSet<Integer> getBlockIDSet(){
		return blockIDSet;
	}
	
	public HashMap<ArrayList<Integer>, LinkedList<Block>> getHashBySize(){
		return hashBySize;
	}
	
	public HashMap<Integer, Block> getHashByBlockID(){
		return hashByBlockID;
	}
	
	public HashSet<Tray> getChildren(){
		return myChildren;
	}
	
	public ArrayList<Integer> getMoveToGetHere(){
		return moveToGetHere;
	}
	
}
