import java.util.*;

public class AmoebaFamily implements Iterable<AmoebaFamily.Amoeba>{

	public Amoeba myRoot = null;

	// A constructor that starts an Amoeba family with an amoeba
	// with the given name.
	public AmoebaFamily(String name) {
		myRoot = new Amoeba(name, null);
	}

	// Add a new amoeba named childName as the youngest child
	// of the amoeba named parentName.
	// Precondition: the amoeba family contains an amoeba named parentName.
	public void addChild(String parentName, String childName) {
		if (myRoot != null) {
            myRoot.addChild(parentName, childName);
		}
	}

	// Makes all Amoeba names only lower case letters.
	public void makeNamesLowercase() {
		// Your goal is to make this as similar as possible to addChild
		if(myRoot != null) {
			
			myRoot.makeNamesLowercase();
		}
		
	}

	// Replaces the name of an amoeba named currentName with the name newName.
	// Precondition: the amoeba family contains exactly one amoeba named currentName.
	public void replaceName(String currentName, String newName) {
		// Your goal is to make this as similar as possible to addChild
		if(myRoot != null){
			
			myRoot.replaceName(currentName, newName);
		}
		
	}

	// Print the names of all amoebas in the family, one on each line.
	// later you will write print() that has more interesting formatting
	public void printFlat() {
		// Your goal is to make this as similar as possible to addChild
		if(myRoot != null){
			
			myRoot.printFlat();
		}
	}

	// Print the names of all amoebas in the family.
	// Names should appear in preorder, with children's names
	// printed oldest first.
	// Members of the family constructed with the main program above
	// should be printed in the following sequence:
	// Amos McCoy, mom/dad, me, Mike, Bart, Lisa, Homer, Marge,
	// Bill, Hilary, Fred, Wilma, auntie
    // This is the pretty print exercise.
	public void print() {
		if(myRoot != null){
			
			myRoot.print("");
		}
	}

	// returns the length of the longest name in the Amoeba Family
	public int longestNameLength() {
		if (myRoot != null) {
			return myRoot.longestNameLength();
		}
		return 0;
	}
    
	// instead of returning the length of the longest name, this method should
	// return the name that is longest.
	public String longestName() {
		// your goal is to make this look as similar as possible to
		// longestNameLength
		if(myRoot != null){
			return myRoot.longestName();
		}
		
		return "";
	}
	
	public String busiest(){
		if(myRoot != null){
			return myRoot.busiest().myName;
		}
		
		return null;
		
		
		
	}
	
	public int height() {
		if(myRoot != null){
			return myRoot.height();
		}
		return 0;
		
	}
	
	private int node(){
		if(myRoot != null){
			return myRoot.node();
		}
		return 0;
	}

	private int twoDescendents(){
		if(myRoot != null){
			return myRoot.twoDescendents();
			
		}
		return 0;
		
	}
	// Return an iterator of the amoeba family.
	public Iterator<Amoeba> iterator() {
		return new AmoebaIterator();
	}

	public static void main(String[] args) {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		family.addChild("Amos McCoy", "mom/dad");
		family.addChild("Amos McCoy", "auntie");
		family.addChild("mom/dad", "me");
		family.addChild("mom/dad", "Fred");
		family.addChild("mom/dad", "Wilma");
		family.addChild("me", "Mike");
		family.addChild("me", "Homer");
		family.addChild("me", "Marge");
		family.addChild("Mike", "Bart");
		family.addChild("Mike", "Lisa");
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");
//		System.out.println("Here's the family:");
//		family.print();
//		System.out.println(family.longestName());
//		System.out.println(family.busiest());
		System.out.println(family.height());
		System.out.println(family.node());
	
	}


	public class AmoebaIterator implements Iterator<Amoeba> {
		// Amoebas in the family are enumerated in preorder,
		// with children enumerated oldest first.
		// Members of the family constructed with the main program above
		// should be enumerated in the following sequence:
		// Amos McCoy, mom/dad, me, Mike, Bart, Lisa, Homer, Marge,
		// Bill, Hilary, Fred, Wilma
		// Complete enumeration of a family of N amoebas should take
		// O(N) operations.

		// You will supply the details of this class in a future lab.
		
//		private Stack tempStack = new Stack();
		LinkedList<Amoeba> tempList = new LinkedList<Amoeba>();
		
		public AmoebaIterator() {
//			if (myRoot != null) {
//		        tempStack.push (myRoot);
//		    }
//		
			if(myRoot != null){
				tempList.add(myRoot);
			}
		}

		public boolean hasNext() {
//		    return !tempStack.empty();
			return !tempList.isEmpty();
		}

		public Amoeba next() {
//			
//			if (!hasNext()) {
//		        throw new NoSuchElementException("tree ran out of elements");
//		    }
//		    Amoeba amoeba = (Amoeba) tempStack.pop();
//		    if (amoeba.myChildren != null) {
//		    	for(int i = 1; i <= amoeba.myChildren.size(); i++){
//		    	
//		        	tempStack.push(amoeba.myChildren.get((amoeba.myChildren.size() - i)));
//			}
//		    }
//		    return amoeba;
			
			if(!hasNext()) {
				throw new NoSuchElementException("tree ran out of elements");
			}
			Amoeba amoeba = tempList.remove(0);
			for(int i = 0; i < amoeba.myChildren.size(); i++){
				tempList.add(amoeba.myChildren.remove(i));
			}
			return amoeba;
		}

		public void remove() {
			// Not used for now -- removal from a tree can be difficult.
			// Once you've learned about different ways to remove from
			// trees, it might be a good exercise to come back and 
			// try to implement this.
		}

	} // end of AmoebaIterator nested class

	public static class Amoeba {

		public String myName; // amoeba's name
		public Amoeba myParent; // amoeba's parent
		public ArrayList<Amoeba> myChildren; // amoeba's children

		public Amoeba(String name, Amoeba parent) {
			myName = name;
			myParent = parent;
			myChildren = new ArrayList<Amoeba>();
		}

		public String toString() {
			return myName;
		}

		public Amoeba parent() {
			return myParent;
        }

        //Add a child if parent name matches an amoeba's name,
        //or if parentName matches any of the descendents
        public void addChild(String parentName, String childName) {
            if (myName.equals(parentName)) {
                Amoeba child = new Amoeba(childName, this);
                myChildren.add(child);
            } else {
                for (Amoeba a : myChildren) {
                    a.addChild(parentName, childName);
                }
            }
        }

        //Add more void recursive functions below

        //Returns the length of the longest name of this Amoeba's children
        public int longestNameLength() {
            int maxLengthSeen = myName.length();
            for (Amoeba a : myChildren) {
                maxLengthSeen = Math.max(maxLengthSeen, a.longestNameLength());
            }
            return maxLengthSeen;
        }
        
        public void makeNamesLowercase() {
    		// Your goal is to make this as similar as possible to addChild
   			myName.toLowerCase();
    		for(Amoeba each: myChildren){
    			each.makeNamesLowercase();
    			
    		}
    		
    	}
        
        public void replaceName(String currentName, String newName) {
    		// Your goal is to make this as similar as possible to addChild
        	if(myName.equals(currentName)) {
        		myName = newName;
        	}
        	for(Amoeba each: myChildren){
        			
        			each.replaceName(currentName, newName);
        	
        		
        	}
    		
    	
        }
        
     // Print the names of all amoebas in the family, one on each line.
    	// later you will write print() that has more interesting formatting
    	public void printFlat() {
    		// Your goal is to make this as similar as possible to addChild
    		System.out.println(myName);
    		for(Amoeba each: myChildren){
        		each.printFlat();

    		}
    	}
    	
    	// Print the names of all amoebas in the family.
    	// Names should appear in preorder, with children's names
    	// printed oldest first.
    	// Members of the family constructed with the main program above
    	// should be printed in the following sequence:
    	// Amos McCoy, mom/dad, me, Mike, Bart, Lisa, Homer, Marge,
    	// Bill, Hilary, Fred, Wilma, auntie
        // This is the pretty print exercise.
    	public void print(String indentation) {
    		System.out.println(indentation + myName);
    		for(Amoeba each: myChildren){
        		each.print(indentation + "    ");
        		

    		}    		
    		
    	}
    	
    	
    	
    	// instead of returning the length of the longest name, this method should
    	// return the name that is longest.
    	public String longestName() {
    		// your goal is to make this look as similar as possible to
    		// longestNameLength
    		String name = myName;
    		for(Amoeba each: myChildren){
    			String longestOfSubtree = each.longestName();
    			if(name.length() < longestOfSubtree.length()){
    				name = longestOfSubtree;
    			}
    			
    			
    		}
    		return name;
    		
    	}
    	
    	public Amoeba busiest(){
    	
    		Amoeba busiestAmoeba = this;
    		for(Amoeba each: myChildren){
    			Amoeba subAmoeba = each.busiest();
    			if(busiestAmoeba.myChildren.size() < subAmoeba.myChildren.size()){
    				busiestAmoeba = subAmoeba;
    			}
    			
    		}
    		return busiestAmoeba;
    		
    		
    		
    	}
    	
    	public int height(){
    		int maxHeight = 0;
    		for(Amoeba each: myChildren){
    			maxHeight = Math.max(maxHeight, each.height());
    		}
			return maxHeight + 1;
    		
    		
    	}
    	
    	private int node(){
    		int node = 0;
    		for(Amoeba each: myChildren){
    			node += each.node();
    		}
    		return node + 1;
    	}
    	
    	private int twoDescendents(){
    		int rightAmoeba = 0;
    			if(myChildren.size() == 2){
    				rightAmoeba += 1;
    			}
    		for(Amoeba each: myChildren){
    			rightAmoeba += each.twoDescendents();

    		}
    		return rightAmoeba;
    		
    	}

    }
} 
