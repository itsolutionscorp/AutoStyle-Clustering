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
	public void makeNamesLowerCase() {
		// Your goal is to make this as similar as possible to addChild
		if(myRoot!=null){
		myRoot.makeNameLowerCase();
		}
	}

	// Replaces the name of an amoeba named currentName with the name newName.
	// Precondition: the amoeba family contains exactly one amoeba named currentName.
	public void replaceName(String currentName, String newName) {
		// Your goal is to make this as similar as possible to addChild
		if(myRoot!=null){
		myRoot.replaceName(currentName,newName);
		}
	}

	// Print the names of all amoebas in the family, one on each line.
	// later you will write print() that has more interesting formatting
	public void printFlat() {
		// Your goal is to make this as similar as possible to addChild
		if(myRoot!=null){
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
		if(myRoot!=null){
			myRoot.print(0);
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
		if (myRoot != null) {
			return myRoot.longestName(longestNameLength());
		}
		return "";
	}
	

	/**
	 * Get the height of the family tree
	 * It equals to the max height of all the leaves in the family tree
	 * @return
	 */
	public int height(){
		if(myRoot!=null){
		return myRoot.height();
		}else{
			return 0;
		}
	}

	// Return an iterator of the amoeba family.
	public Iterator<Amoeba> iterator() {
		return new AmoebaIterator();
	}
	
	public int size(){
		if(myRoot!=null){
		return myRoot.size()+1;
		}else return 0;
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
		System.out.println("Here's the family:");
		family.makeNamesLowerCase();
		System.out.println("====before chaing===");
		family.print();
		family.replaceName("me", "jackie");
		System.out.println("====after chaing===");
		family.printFlat();
		family.print();
		System.out.println("The longeset name is:"+family.longestName()+" should be:amos mccoy");
		System.out.println("The size of the family is:"+family.size()+" should be:13");
		System.out.println("The height of the family is:"+family.height()+" should be:5");
		
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

		public AmoebaIterator() {
		}

		public boolean hasNext() {
			return false;
		}

		public Amoeba next() {
			return null;
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
		
		public void replaceName(String currentName, String newName){
			 if (myName.equals(currentName)) {
	                myName=newName;
	            } else {
	                for (Amoeba a : myChildren) {
	                    a.replaceName(currentName, newName);
	                }
	            }
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
        
        //make the name of this Amoeba lowercase
        public void makeNameLowerCase(){        	
          	this.myName=this.myName.toLowerCase();
          	if(this.myChildren.isEmpty()){
          		return;
          	}else{
          		for(Amoeba a:myChildren){
          			a.makeNameLowerCase();
          		}
          	}
        }
        
        public String longestName(int lengthOfLongest){
			if (myName.length() == lengthOfLongest){
				return myName;
			} else {
				String longest = "";
				for (Amoeba a : myChildren){
					if (a.longestName(lengthOfLongest) != null){
						longest = a.longestName(lengthOfLongest);
					}
				}
				return longest;
			}
		}
        
        //print out the element of this Amoeba
        public void printFlat(){
        	System.out.println(toString());
        	if(this.myChildren.isEmpty()){
          		return;
          	}else{
          		for(Amoeba a:myChildren){
          			a.printFlat();
          		}
          	}
        }
        
        /**
         * pretty print method
         * @return
         */
        public void print(int level){
        	if(level!=0){
        	    String spaces=String.format("%" +(level*4)+ "s", "");
        		System.out.println(spaces+toString());
        	}else{
        		System.out.println(toString());
        	}
        	
        	if(this.myChildren.isEmpty()){
          		return;
          	}else{
          		level++;
          		for(Amoeba a:myChildren){
          			a.print(level);
          		}
          	}
        }
        
        public int size(){
        	if(myChildren.isEmpty()){
        		return 0;
        	}else{
        		int count=0;
        		for(Amoeba a :myChildren){
        			count=count+1+a.size();
        		}
        		return count;
        	}
        }
      //helper height method inside Amoeba
        //fixed version of height()
        private int height() {
            if (myChildren.isEmpty()) {
            	return 1;
            } else {
                int bestSoFar = 1;
                for (Amoeba a : myChildren) {
                    bestSoFar = Math.max(a.height(), bestSoFar);
                }
                return bestSoFar+1;
            }
        }
        
        //buggy version of height() provided by the lab
//        private int height() {
//            if (myChildren.isEmpty()) {
//            	return 1;
//            } else {
//                int bestSoFar = 1;
//                for (Amoeba a : myChildren) {
//                    bestSoFar = 1 + Math.max(a.height(), bestSoFar);
//                }
//                return bestSoFar;
//            }
//        }
        


        //Add more void recursive functions below

        //Returns the length of the longest name of this Amoeba's children
        public int longestNameLength() {
            int maxLengthSeen = myName.length();
            for (Amoeba a : myChildren) {
                maxLengthSeen = Math.max(maxLengthSeen, a.longestNameLength());
            }
            return maxLengthSeen;
        }
    }
} 
