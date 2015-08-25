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
		myRoot.makeNamesLowercase();
	}
	
	
	
	// Replaces the name of an amoeba named currentName with the name newName.
	// Precondition: the amoeba family contains exactly one amoeba named currentName.
	public void replaceName(String currentName, String newName) {
			myRoot.replaceName(currentName,newName);
	}
	

	
	public static String genIndents(int indents) {
		String result = "";
		for(int i = 0; i < indents; i++) {
			result += "    ";
		}
		return result;
	}

	// Print the names of all amoebas in the family, one on each line.
	// later you will write print() that has more interesting formatting
	public void printFlat() {
		myRoot.printFlat();
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
		myRoot.print(0);
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
		if (myRoot!=null){
			return myRoot.longestName();
		}
		return "";
	}
	
	public int size() {
		if(myRoot != null) return myRoot.size();
		return 0;
	}
	
	public int height() {
		if (myRoot !=null) return myRoot.height();
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
		System.out.println("Here's the family:");
		String maxName=family.longestName();
		Iterator fam=family.iterator();
		while (fam.hasNext()){
			System.out.println(fam.next());
		}
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


		private Queue<Amoeba> fringe = new LinkedList<Amoeba>();


		public AmoebaIterator() {
		    if (myRoot != null) {
		        fringe.add (myRoot);
		    }
		}

		public boolean hasNext() {
		    return !fringe.isEmpty();
		}

		public Amoeba next() {
		    if (!hasNext()) {
		        throw new NoSuchElementException("tree ran out of elements");
		    }
		    Amoeba a = (Amoeba) fringe.remove();
		    for (Amoeba myChild: a .myChildren){
		        fringe.add(myChild);
		    }

		    return a;
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
        

    	public void print(int indents) {
    		System.out.println(genIndents(indents) + myName);
    		for(Amoeba child : myChildren) {
    			child.print( indents + 1);
    		}
    	}
    	
    	public void printFlat() {
    		System.out.println(myName);
    		for(Amoeba child : myChildren) {
    			child.printFlat();
    		}
    		
    	}
    	
    	public void replaceName(String currentName, String newName) {
    		if (myName.equals(currentName)){
    			myName=newName;
    		} 
    		for (Amoeba child: myChildren){
    			child.replaceName(currentName,newName);
    		}
    	}

    	
    	public void makeNamesLowercase(){
    		myName = myName.toLowerCase();
    		for (Amoeba child: myChildren){
    			child.makeNamesLowercase();
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
        
        public String longestName(){
        	String maxName=myName;
            for (Amoeba a : myChildren) {
            	if (a.longestName().length()>maxName.length()){
            		maxName=a.longestName();
            	} 
            }
            return maxName;
        }
        
        public int size() {
        	int size = 1;
            for (Amoeba a : myChildren) {
                size += a.size();
            }
            return size;
        }
        
        
        public int height(){
        	int bestSoFar = 1;
		    if (myChildren.isEmpty()) {
		    	return bestSoFar;
		    } else {
		        for (Amoeba a : myChildren) {
		            bestSoFar = Math.max(1+a.height(), bestSoFar);
		        }
		        return bestSoFar;
		    }
        }

    }
} 
