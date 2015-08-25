import java.util.ArrayList;
import java.util.LinkedList;

public class Block {
		private ArrayList<Integer> coords;
		private int height;
		private int width;
		private int ID;
		private int x1;
		private int x2;
		private int y1;
		private int y2;
		private int myPriority;
		private ArrayList<Integer> myWH;
		private boolean BIG;
		
		/**
		 * This constructor is meant for the creation of blocks for the goal Tray. 
		 * All other Tray objects requires information about goal Tray to 
		 * calculate its Block objects’ distances from goal Tray’s 
		 * Block objects for heuristics.
		 * 
		 * The constructor initiates all the instance variables. 

		 * @param location coordinates of the Block
		 * @param TraySize Size of the current Tray
		 */
		
		public Block(ArrayList<Integer> location, Integer[] TraySize){
			BIG = false;
			for(Integer i: TraySize){
				if(i > 100){
					BIG = true;
				} 
			}
			coords = location;
			x1 = coords.get(0);
			y1 = coords.get(1);
			x2 = coords.get(2);
			y2 = coords.get(3);
			height = coords.get(2) - coords.get(0) + 1;
			width = coords.get(3) - coords.get(1) + 1;
			ID  = makeHash();
			myWH = new ArrayList<Integer>();
			myWH.add(width);
			myWH.add(height);
		}
		
		/**
		 * This constructor is meant for the creation of blocks for 
		 * Tray obejects other than the gaol Tray. The additional 
		 * parameter sameSizedGoal takes in an LinkedList of the 
		 * current goal Tray’s Block objects with the same dimension 
		 * in order to calculate the heuristics. (See minDist).
		 * 
		 * @param sameSizedGoal: a list of goal blocks with the same dimension
		 * @param location coordinates of the Block
		 * @param TraySize Size of the current Tray
		 */
		public Block(ArrayList<Integer> location, LinkedList<Block> sameSizedGoal, Integer[] TraySize){
			BIG = false;
			for(Integer i: TraySize){
				if(i > 100){
					BIG = true;
				} 
			}
			coords = location;
			x1 = coords.get(0);
			y1 = coords.get(1);
			x2 = coords.get(2);
			y2 = coords.get(3);
			height = coords.get(2) - coords.get(0) + 1;
			width = coords.get(3) - coords.get(1) + 1;
			ID  = makeHash();
			myPriority = minDist(sameSizedGoal);
			myWH = new ArrayList<Integer>();
			myWH.add(width);
			myWH.add(height);
		}
		
		/**
		 * This method takes in sameSizedGoal from the second 
		 * Block constructor and returns the smallest distance 
		 * from the current Block to a closest Goal Block 
		 * with the same dimension.
		 * 
		 * @param myGoals a list of goal blocks with the same dimension
		 * @return the smallest distance 
		 * from the current Block to a closest Goal Block 
		 * with the same dimension.
		 */
		public int minDist(LinkedList<Block> myGoals){
			if (myGoals == null){
				return 1000000;
			}
			
			int minDist = Integer.MAX_VALUE;
			for(Block b: myGoals){ 
				int goal0 = b.getX1(); //TopLeft (Row)
				int goal1 = b.getY1(); //TopLeft (Col)
				int dist = (Math.abs(goal0 - x1) + Math.abs(goal1 - y1)); //dist from goal to cur
				
				if (dist < minDist){
					minDist = dist;
				}
			}
			return minDist;
		}		

		/**
		 * This method creates customized hash code, 
		 * which is stored it as ID. Dependent on the 
		 * size of the Tray, we generate different hash code. 
		 * The general logic is to create hash code corresponding 
		 * to the coordinates of the Block. However, when BIG is true, 
		 * using all four coordinates will cause result in a long 
		 * rather than an int. Therefore, we use the first three 
		 * coordinates of the Block and fill in 0’s to maintain 
		 * each coordinates’ place.

		 * @return hash code
		 */
		public int makeHash(){
			if (BIG){
				String s = "";
				for(int i = 0; i < 3; i++){
					if (coords.get(i) < 100 && coords.get(i) > 10){
						s += "0";
					} else if (coords.get(i) < 10){
						s += "00";
					}
					s += coords.get(i);
				}
				
				return Integer.parseInt(s);
			} else{
//			
			String s = "";
			for(int i = 0; i < 4; i++){
				s += coords.get(i);
			}
			
			return Integer.parseInt(s);
			}
		}
		
		/**
		 * We override the default hashCode() method to return 
		 * the ID of this Block object to limit the call 
		 * to the calculation of hash code to once per 
		 * creation of Block object.
		 */
		@Override public int hashCode(){
			return ID;
		}
		
		/**
		 * Two Block objects are equal if the have the same ID, 
		 * which is determined by their coordinates.
		 */
		@Override
		public boolean equals(Object o){
			return ((Block) o).getID() == ID;
		}
		
		/**
		 * We override the toString method to return 
		 * the coordinates of the Block for identification 
		 * purposes for the debugging process.
		 */
		@Override
		public String toString(){
			ArrayList<Integer> rtn = new ArrayList<Integer>();
			rtn.addAll(coords);
			return rtn.toString();
		}
		
		public ArrayList<Integer> getCoords(){
			return coords;
		}
		
		public int getPriority(){
			return myPriority;
		}
		
		public int getX1(){
			return x1;
		}
		
		public int getX2(){
			return x2;
		}
		
		public int getY1(){
			return y1;
		}
		
		public int getY2(){
			return y2;
		}
		
		public int getHeight(){
			return height;
		}
		
		public int getWidth(){
			return width;
		}
		
		public ArrayList<Integer> getWH(){
			return myWH;
		}
		
		public int getID(){
			return ID;
		}
		
		
}
