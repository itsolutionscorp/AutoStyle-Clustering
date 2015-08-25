import java.util.*;

public class Tray {
        /**
         * Instance variables
         * 
         * myHeight - height of the tray
         * myWidth - width of the tray
         * myBlocks - 2D Block array, contains Block, and doesn't contain anything if its empty
         * blocks - a HashMap of blocks to their spaces
         * movableBlocks - a list of movableBlocks pieces that can be moved 
         * isDeadEnd - true if this tray leads to an dead end or locked position
         * empty - indicates the coordinates of the empty space(s) on the tray
         * parents - List of this Tray's parents
         * children - List of this Tray's children, Trays that result from moving the movableBLocks
         * myPrev - information about previous move coordinates
         *
         */
        private int myHeight, myWidth;
        private Block[][] myBlocks; 
        private HashMap<Integer,Block> blocks;
        public ArrayList<Block> movableBlocks;
        public boolean isDeadEnd;
        private ArrayList<int[]> empty;
        public Tray parent;
        public ArrayList<Tray> children;
        public int[] myPrev;
        public HashSet<String> config;
        public ArrayList<Block> adjacentBlocks;
        
        public Tray(){

        }
        
        public Tray(int verticalSize, int horizontalSize){
            //Constructor
            myHeight = verticalSize;
            myWidth = horizontalSize;
            myBlocks = new Block[horizontalSize][verticalSize];
            blocks = new HashMap<Integer, Block>();
            movableBlocks = new ArrayList<Block>();
            isDeadEnd = false;
            empty = new ArrayList<int[]>();
            parent = null;
            children = new ArrayList<Tray>();
            config = new HashSet<String>();
            myPrev = null;
            adjacentBlocks = new ArrayList<Block>();
    }

        public int getHeight(){
                return this.myHeight;
        }

        public int getWidth(){
                return this.myWidth;
        }

        public ArrayList<int[]> getEmpty() {
                return empty;
        }
        public boolean getIsDeadEnd() {
                return this.isDeadEnd;
        }
        
        public void makeDeadEnd() {
                this.isDeadEnd = true;
        }
        public void addParent(Tray t) {
                parent = t;
        }

        public void addChild(Tray t) {
                if (! children.contains(t)) {
                        children.add(t);
                }
        }
//    public void setMovableBlocks(ArrayList<Block> inputMovableBlock){
//            this.movableBlocks = inputMovableBlock;
//    }
//        
        public ArrayList<Block> getMovableBlocks() {
                return this.movableBlocks;
        }

        public void setBlock(HashMap<Integer,Block> inputBlock){
                this.blocks = inputBlock;
        }
        public HashMap<Integer,Block> getBlock(){
                return this.blocks;
        }
        public Tray getParent() {
                return this.parent;
        }

        public ArrayList<Tray> getChildren() {
                return this.children;
        }
        
            public int numDeadEndChildren() {
                    int sum = 0;
                    for (Tray t:children) {
                            if (t.isDeadEnd)
                                    sum++;
                    }
                    return sum;
            }
        
        

        public static void main (String[] args){
                Tray testTray = new Tray(4,5);
                    Block newBlock = new Block(1,1,2,2);
                    Block newBlock2 = new Block(0,0,2,0);
                    Block newBlock3 = new Block(0,1,0,2);
                    testTray.addBlock(newBlock);
                    testTray.addBlock(newBlock2);
                    testTray.addBlock(newBlock3);
                    
                    testTray.setMovableBlock();
                    for(Block b : testTray.movableBlocks){
                            System.out.println("movable Blocks are " + b);
                            
                    }
                    Tray placeholder = testTray.moveBlock(0,0, 1, 0);
        } 
    
        
        
        //TestComplete
        public void addBlock(Block b){
                int tly = b.getTopLeftY();
                int tlx= b.getTopLeftX();
                int bry = b.getBottomRightY();
                int brx = b.getBottomRightX();
     
                for (int i = tlx; i <= brx; i++) {
                        for (int j = tly; j <= bry; j++) {
//                System.out.println("added myBlocks java standard [" + i + "][" + (myHeight-1-j) +"]");
                                myBlocks[i][myHeight - 1  - j] = b;
                        }
                }
                blocks.put(tly*1000 + tlx, b);

        }


        //converts the Tray's layout to a HashSet of Strings representing the blocks.
        public HashSet<String> parseBlocks(){
    		for(Block b:this.blocks.values()){
    			String tly = Integer.toString(b.getTopLeftY());
    			String tlx= Integer.toString(b.getTopLeftX());
    			String bry = Integer.toString(b.getBottomRightY());
    			String brx = Integer.toString(b.getBottomRightX());
    			StringBuilder sb = new StringBuilder();
    			sb.append(tly);
    			sb.append(tlx);
    			sb.append(bry);
    			sb.append(brx);
    			//System.out.println("coords: " + coords);
    			this.config.add(sb.toString());
    		}
    		return this.config;
    	}

        /**
         * Adds all blocks adjacent to an empty space to the movableBlocks list
         */
        
        /**
         * This method should be called when all the blocks are added and setEmpty() is called;
         * it sets movableBlock instance variable in a tray.
         */
        
        public void setMovableBlock(){
        	for(Block B : blocks.values()){
//                for(Block B : adjacentBlocks){
                        int locationX = B.getTopLeftX();
                        int locationY = myHeight-B.getTopLeftY()-1;
                        int endLocationX = B.getBottomRightX();
                        int endLocationY = myHeight-B.getBottomRightY()-1;
                        int LeftCount = 0;
                        int RightCount = 0;
                        int UpCount = 0;
                        int DownCount = 0;
                        //System.out.println("this block has coordinate (" + locationX + "," + locationY+")");
                        //System.out.println("this block ends at (" +endLocationX + "," + endLocationY+")");
                        //myLeftCheck, myRIghtCheck;
                        for(int i = locationY; i>=endLocationY; i--){
                                try{
                                    int BsLeft = locationX -1;
                                    if (myBlocks[BsLeft][i] == null){
                                            LeftCount++;
                                    }
                                }
                                catch(ArrayIndexOutOfBoundsException e){
                                        
                                }
                                try{
                                        int BsRight = endLocationX +1;
                                        if (myBlocks[BsRight][i] == null){
                                            RightCount++;
                                    }                                        
                                }
                                catch(ArrayIndexOutOfBoundsException e){
                                        
                                }
                        }
                        if(LeftCount==(locationY-endLocationY+1)){
                                B.isMovable= true;
                                int[] moveoption = new int[2];
                                moveoption[0] = locationX-1;
                                moveoption[1] = locationY;
                                B.addPlaceToMove(moveoption);
                                if (!movableBlocks.contains(B)) {
                                        movableBlocks.add(B);
                                }
                        }
                        if(RightCount==(locationY-endLocationY+1)){
                                B.isMovable= true;
                                int[] moveoption = new int[2];
                                moveoption[0] =locationX+1;
                                moveoption[1] = locationY;
                                B.addPlaceToMove(moveoption);
                                if (!movableBlocks.contains(B)) {
                                        movableBlocks.add(B);
                                }
                        }
                        for(int i = endLocationX; i>=locationX; i--){
                                try{
                                    int BsUp = locationY + 1;
                                    if (myBlocks[i][BsUp] == null){
                                            UpCount++;
                                    }
                                }
                                catch(ArrayIndexOutOfBoundsException e){
                                        
                                }
                                try{
                                        int BsDown = endLocationY - 1;
                                        if (myBlocks[i][BsDown] == null){
                                            DownCount++;
                                    }                                        
                                }
                                catch(ArrayIndexOutOfBoundsException e){
                                        
                                }
                        }
                        if(UpCount==(endLocationX-locationX+1)){
                
                                B.isMovable= true;
                                int[] moveoption = new int[2];
                                moveoption[0] =locationX ;
                                moveoption[1] = locationY+1;
                                B.addPlaceToMove(moveoption);
                                if (!movableBlocks.contains(B)) {
                                        movableBlocks.add(B);
                                }
                        }
                        if(DownCount==(endLocationX-locationX+1)){
                                B.isMovable= true;
                                int[] moveoption = new int[2];
                                moveoption[0] =locationX ;
                                moveoption[1] = locationY-1;
                                B.addPlaceToMove(moveoption);
                                if (!movableBlocks.contains(B)) {
                                        movableBlocks.add(B);
                                }
                        }
                        
                }
        }
        

    public Tray moveBlock(int topLeftY, int topLeftX, int toMoveY, int toMoveX) {
            //System.out.println("Moving from (" + topLeftY + " " + topLeftX + ") to ( " + toMoveY + " " + toMoveX+ ")");
            Tray returnTray = new Tray(this.getHeight(),this.getWidth());
            for(Block K : this.blocks.values()){
                Block placeholder = K.copy();
                    returnTray.addBlock(placeholder);
            }
            Block movingBlock = returnTray.blocks.get(topLeftY*1000+topLeftX);
            // System.out.println("Moving block : " + movingBlock);
            for (int i = topLeftX; i <= movingBlock.getBottomRightX(); i++) {
                        for (int j = topLeftY; j <= movingBlock.getBottomRightY(); j++) {
                
                                returnTray.myBlocks[i][myHeight - 1  - j] = null;
                        }
                }
            returnTray.blocks.remove(movingBlock.hashCode());
            Block toCreate = new Block(toMoveY,toMoveX, movingBlock.getBottomRightY() + (toMoveY-topLeftY),movingBlock.getBottomRightX() + (toMoveX-topLeftX));
            // System.out.println("New block: " + toCreate);
            returnTray.addBlock(toCreate);
//            returnTray.setAdjacentBlocks();
            returnTray.setMovableBlock();
            return returnTray;
        }                

        
        @Override 
        public boolean equals(Object other) {
                if (!(other instanceof Tray)) {
                        return false;
                } else {
                        Tray t = (Tray) other;
                        if (this.config.containsAll(t.config)) {
                                return true;
                        }
                        return false;
                }
        }
        
        @Override
        public int hashCode() {
                int hash = 0;
                for (String s:config) {
                        hash += s.hashCode();
                }
                return hash;
        }

        
}