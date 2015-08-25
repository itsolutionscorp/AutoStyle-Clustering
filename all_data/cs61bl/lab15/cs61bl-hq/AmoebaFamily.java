import java.util.*;
import java.util.Queue;

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
			myRoot.lowerCaseHelper();
		}
	}

	// Replaces the name of an amoeba named currentName with the name newName.
	// Precondition: the amoeba family contains exactly one amoeba named currentName.
	public void replaceName(String currentName, String newName) {
		if (myRoot != null) {
			myRoot.replaceHelper(currentName, newName);
		}
	}

	// Print the names of all amoebas in the family, one on each line.
	// later you will write print() that has more interesting formatting
	public void printFlat() {
		if (myRoot != null) {
			myRoot.flatHelper();
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
			myRoot.printHelper(0);
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
			return myRoot.longestNameHelper(longestNameLength(), myRoot.myName);
		}
		return null;
	}

	public int size() {
		if (myRoot != null) {
			return 1 + myRoot.sizeHelper(0);
		}
		return 0;
	}
	
	public int height() {
		if (myRoot != null) {
			return myRoot.height();
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
		Iterator<Amoeba> forFamily = family.iterator();
		System.out.println(forFamily.next());
		System.out.println(forFamily.next());
		System.out.println(forFamily.next());
		System.out.println(forFamily.next());
		System.out.println(forFamily.next());
		System.out.println(forFamily.next());
		System.out.println(forFamily.next());
		System.out.println(forFamily.next());
		System.out.println(forFamily.next());
		System.out.println(forFamily.next());
		System.out.println(forFamily.next());
		System.out.println(forFamily.next());
		System.out.println(forFamily.next());
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
		Queue<Amoeba> fringe = new LinkedList<Amoeba>();
		
		public AmoebaIterator() {
			if (myRoot != null) {
				fringe.add(myRoot);
			}
		}

		public boolean hasNext() {
			return (fringe != null);
		}

		public Amoeba next() {
			if (!hasNext()) {
				throw new NoSuchElementException("no more children");
			}
			Amoeba n = (Amoeba) fringe.remove();
			if (n.myChildren != null) {	
				for (Amoeba a : n.myChildren) {
					fringe.add(a);
				}
			}
			return n;
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
        public void lowerCaseHelper() {
        	myName = myName.toLowerCase();
        	for (Amoeba a : myChildren) {
        		a.lowerCaseHelper();
        	}
        }
        
        public void replaceHelper(String currentName, String newName) {
        	if (myName == currentName) {
        		myName = newName;
        	}
        	for (Amoeba a : myChildren) {
        		a.replaceHelper(currentName, newName);
        	}
        }
        
        public void flatHelper() {
        	System.out.println(myName);
        	for (Amoeba a : myChildren) {
        		a.flatHelper();
        	}
        }
        
        public void printHelper(int indent) {
        	System.out.println(repeat("    ", indent) + myName);
        	for (Amoeba a : myChildren) {
        		a.printHelper(indent + 1);
        	}
        }
         
        public String repeat(String string, int indent) {
        	String newString = "";
        	while (indent > 0) {
        		newString = newString + string;
        		indent--;
        	}
        	return newString;
    	}
        
        public String longestNameHelper(int longestLength, String longestName) {
        	if (myName.length() == longestLength) {
        		return myName;
        	}
        	for (Amoeba a : myChildren) {
        		longestName = a.longestNameHelper(longestLength, longestName);
        	}
        	return longestName;
        }
        
        //Returns the length of the longest name of this Amoeba's children
        public int longestNameLength() {
            int maxLengthSeen = myName.length();
            for (Amoeba a : myChildren) {
                maxLengthSeen = Math.max(maxLengthSeen, a.longestNameLength());
            }
            return maxLengthSeen;
        }
        
        public int sizeHelper(int number) {
        	for (Amoeba a : myChildren) {
        		number = 1 + a.sizeHelper(number);
        	}
        	return number;
        }
        
        private int height() {
            if (myChildren.isEmpty()) {
            	return 1;
            } else {
                int bestSoFar = 1;
            	int heightSoFar = 1; 
                for (Amoeba a : myChildren) {
                	heightSoFar = 1 + a.height();
                    bestSoFar = Math.max(heightSoFar, bestSoFar);
                }
                return bestSoFar;
            }
        }
    }

} 
