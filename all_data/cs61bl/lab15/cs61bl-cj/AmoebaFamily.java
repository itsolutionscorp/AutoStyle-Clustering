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
		if (myRoot != null) {
			myRoot.makeNamesLowercase();
		}
	}

	// Replaces the name of an amoeba named currentName with the name newName.
	// Precondition: the amoeba family contains exactly one amoeba named currentName.
	public void replaceName(String currentName, String newName) {
		if (myRoot != null) {
			myRoot.replaceName(currentName, newName);
		}
	}

	// Print the names of all amoebas in the family, one on each line.
	// later you will write print() that has more interesting formatting
	public void printFlat() {
		if (myRoot != null) {
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
		if (myRoot != null) {
			myRoot.print();
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
		if (myRoot != null) {
			return myRoot.longestName();
		} 
		return "";
	}
	
	public int size() {
		if (myRoot != null) {
			return myRoot.size();
		} 
		return 0;
	}

	public int height() {
		if (myRoot != null) {
			return myRoot.height();
		}
		return 0;
	}
	
	public String busiest() {
		if (myRoot != null) {
			return myRoot.busiest().myName;
		}
		return null;
	}
	// Return an iterator of the amoeba family.
	public Iterator<Amoeba> iterator() {
		return new AmoebaIterator();
	}

	public static void main(String[] args) {
		AmoebaFamily family = new AmoebaFamily("Amos McCoy");
		System.out.println(family.height());
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
		family.makeNamesLowercase();
		Iterator iter = family.iterator();
		while (iter.hasNext()) {
			System.out.println(((Amoeba) iter.next()).myName);
		}
	
		System.out.println("should throw exception"+((Amoeba) iter.next()).myName);
		System.out.println("");

	}
	
	/**
	public class AmoebaIterator implements Iterator<Amoeba> {
		// A Depth-First Amoeba Iterator

		private Stack fringe = new Stack();
		public AmoebaIterator() {
			if (myRoot != null) {
				fringe.push(myRoot);
			}
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Amoeba next() {
			if (!hasNext()) {
		        throw new NoSuchElementException("tree ran out of elements");
		    }
			Amoeba result = (Amoeba) fringe.pop();
			Stack temp = new Stack();
			for (Amoeba a : result.myChildren) {
				temp.push(a);
			}
			while(!temp.isEmpty()) {
				fringe.push(temp.pop());
			}
			return result;
		}

		public void remove() {
			// Not used for now -- removal from a tree can be difficult.
			// Once you've learned about different ways to remove from
			// trees, it might be a good exercise to come back and 
			// try to implement this.
		}

	} // end of AmoebaIterator nested class
	**/
	

	public class AmoebaIterator implements Iterator<Amoeba> {
		// A Breadth-First Amoeba Iterator

		private Queue fringe = new LinkedList();
		public AmoebaIterator() {
			if (myRoot != null) {
				fringe.add(myRoot);
			}
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Amoeba next() {
			if (!hasNext()) {
		        throw new NoSuchElementException("tree ran out of elements");
		    }
			Amoeba result = (Amoeba) fringe.peek();
			fringe.remove();
			
			for (Amoeba a : result.myChildren) {
				fringe.add(a);
			}
			
			return result;
		}

		public void remove() {
			// Not used for now -- removal from a tree can be difficult.
			// Once you've learned about different ways to remove from
			// trees, it might be a good exercise to come back and 
			// try to implement this.
		}

	} // end of AmoebaIterator nested class
	
	private boolean hasMoreElements() {
		Iterator iter = this.iterator();
		return iter.hasNext();
	}
	
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

        public void makeNamesLowercase() {
        	myName = myName.toLowerCase();
        	for (Amoeba a : myChildren) {
        		a.makeNamesLowercase();
        	}
        }
        
        
        //Add more void recursive functions below
    	public void replaceName(String currentName, String newName) {
    		if (myName.equals(currentName)) {
    			myName = newName;
    		} else {
    			for (Amoeba a : myChildren) {
    				a.replaceName(currentName, newName);
    			}
    		}
    	}
    	
    	public void printFlat() {
    		System.out.println(myName);
    		for (Amoeba a : myChildren) {
    			a.printFlat();
    		}
    	}

    	public void print() { 		
    		printHelper(0,this);   		
    	}
    	
    	public void printHelper(int i, Amoeba amoeba) {    		
    		String s = "";
    		while (i > 0) {
    			s += "  ";
    			i--;
    		}
    		
    		System.out.println(s + amoeba.myName);
    		for (Amoeba a : amoeba.myChildren) {
    			printHelper(a.indetingLevel(a), a); 		 			
    		}
    	}
        
    	public int indetingLevel(Amoeba a) {
    		int i = 0;
    		while(a.myParent != null) {
    			i++;
    			a = a.myParent;
    		}
    		return i;
    	}
        
        //Returns the length of the longest name of this Amoeba's children
        public int longestNameLength() {
            int maxLengthSeen = myName.length();
            for (Amoeba a : myChildren) {
                maxLengthSeen = Math.max(maxLengthSeen, a.longestNameLength());
            }
            return maxLengthSeen;
        }
        
        public String longestName() {
        	String s = myName;
            for (Amoeba a : myChildren) {
            	s = longestString(s, a.longestName());
            }
            return s;
        }
        
        public String longestString(String s1, String s2) {
        	if (s1.length() > s2.length()) {
        		return s1;
        	} else {
        		return s2;
        	}
        }
        
        public int size() {
        	int size = 1;
        	for (Amoeba a : myChildren) {
        		size += a.size();
        	}
        	return size;
        }
        
        public int height() {
        	if (myChildren.isEmpty()) {
        		return 1;
        	} else {
        		int bestSoFar = 1;
        		for (Amoeba a : myChildren) {
        			bestSoFar = Math.max(a.height(), bestSoFar);
        		}
        		return bestSoFar + 1;
        	}
        }
        
        public Amoeba busiest() {
        	Amoeba childrenMax = this;
        	for (Amoeba a : myChildren) {
        		childrenMax = busier(childrenMax, a.busiest());
        	}
        	return childrenMax;
        }
        
        public Amoeba busier(Amoeba a, Amoeba b) {
        	if (b.myChildren.size() > a.myChildren.size())
        		return b;
        	else
        		return a;
        }      
        
    }
} 
