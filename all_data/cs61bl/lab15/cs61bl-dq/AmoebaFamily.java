import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class AmoebaFamily implements Iterable<AmoebaFamily.Amoeba> {

	public Amoeba myRoot = null;
	public static int myDepth = 0;
	public static int mySize = 0;

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
		if (myRoot != null) {
			AmoebaFamily.makeNamesLowercaseHelper(myRoot);
		}
	}

	public static void makeNamesLowercaseHelper(Amoeba current) {
		current.myName = current.myName.toLowerCase();
		Iterator<Amoeba> iter = current.myChildren.iterator();
		while (iter.hasNext()) {
			AmoebaFamily.makeNamesLowercaseHelper(iter.next());
		}
	}

	// Replaces the name of an amoeba named currentName with the name newName.
	// Precondition: the amoeba family contains exactly one amoeba named
	// currentName.
	public void replaceName(String currentName, String newName) {
		// Your goal is to make this as similar as possible to addChild
		if (myRoot != null) {
			AmoebaFamily.replaceNameHelper(currentName, newName, myRoot);
		}
	}

	public static void replaceNameHelper(String currentName, String newName,
			Amoeba currentAmoeba) {
		if (currentAmoeba.myName.equals(currentName)) {
			currentAmoeba.myName = newName;
		} else {
			Iterator<Amoeba> iter = currentAmoeba.myChildren.iterator();
			while (iter.hasNext()) {
				if (currentAmoeba.myName.equals(currentName)) {
					currentAmoeba.myName = newName;
				} else {
					replaceNameHelper(currentName, newName, iter.next());
				}
			}
		}
	}

	// Print the names of all amoebas in the family, one on each line.
	// later you will write print() that has more interesting formatting
	public void printFlat() {
		// Your goal is to make this as similar as possible to addChild
		if (myRoot != null) {
			AmoebaFamily.printFlatHelper(myRoot);
		}
	}

	public static void printFlatHelper(Amoeba currentAmoeba) {
		if (currentAmoeba.myChildren == null) {
			System.out.println(currentAmoeba.myName);
		} else {
			System.out.println(currentAmoeba.myName);
			Iterator<Amoeba> iter = currentAmoeba.myChildren.iterator();
			while (iter.hasNext()) {
				AmoebaFamily.printFlatHelper(iter.next());
			}
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
			AmoebaFamily.printHelper(myRoot);
		}
	}

	public static void printHelper(Amoeba currentAmoeba) {
		if (currentAmoeba.myChildren == null) {
			depthIndenter(myDepth);
			System.out.println(currentAmoeba.myName);
		} else {
			depthIndenter(myDepth);
			System.out.println(currentAmoeba.myName);
			myDepth++;
			Iterator<Amoeba> iter = currentAmoeba.myChildren.iterator();
			while (iter.hasNext()) {
				depthIndenter(myDepth);
				AmoebaFamily.printHelper(iter.next());
			}
			myDepth--;
		}
	}

	public static void depthIndenter(int myDepth) {
		while (myDepth > 0) {
			System.out.print("    ");
			myDepth--;
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
			return AmoebaFamily.longestName(myRoot);
		}
		return myRoot.myName;
	}

	public static String longestName(Amoeba currentAmoeba) {
		String maxLengthName = currentAmoeba.myName;
		Iterator<Amoeba> iter = currentAmoeba.myChildren.iterator();
		while (iter.hasNext()) {
			String childMaxLengthName = AmoebaFamily.longestName(iter.next());
			if (childMaxLengthName.length() > maxLengthName.length()) {
				maxLengthName = childMaxLengthName;
			}
		}
		return maxLengthName;
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
		family.print();
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
		
//		private Stack<Amoeba> fringe = new Stack<Amoeba>();
		private Queue<Amoeba> fringe = new LinkedList<Amoeba>();

		public AmoebaIterator() {
			if (myRoot != null) {
		        fringe.add(myRoot);
		    }
		}

		public boolean hasNext() {
			return (fringe.size() > 0);
		}

		public Amoeba next() {
			 	if (!hasNext()) {
			        throw new NoSuchElementException("family ran out of descendants");
			    }
			    Amoeba node = (Amoeba) ((LinkedList<Amoeba>) fringe).pollFirst();
			    if (node.myChildren == null) {
			    	return node;
			    }
//			    
//			    int childsize = node.myChildren.size();
//			    while (childsize > 0) {
//			    	fringe.add(node.myChildren.get(childsize - 1));
//			    	childsize--;
//			    }
			    for (Amoeba a : node.myChildren) {
                    fringe.add(a);
                }
			    return node;
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
        
        public void makeNamesLowercase() {
    		// Your goal is to make this as similar as possible to addChild
    		myName = toString().toLowerCase();
    		for (Amoeba a : myChildren) {
    			a.makeNamesLowercase();
    		}
    	}
        
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
    		System.out.println(toString());
    		for (Amoeba a : myChildren) {
    			a.printFlat();
    		}
    	}
    	
    	public void print() {
    		for (Amoeba a : myChildren) {
    			printHelper(a, "");
    		}
    	}
    	
    	private void printHelper(Amoeba child, String predent) {
    		String tbi = predent + child;
    		tbi = "    " + tbi;
    		System.out.println(tbi);
    		if (child.myChildren != null) {
    			tbi = "    " + predent;
    			for (Amoeba a : child.myChildren) {
    				printHelper(a, tbi);
    			}
    		}
    		
}
		// Returns the length of the longest name of this Amoeba's children
		public int longestNameLength() {
			int maxLengthSeen = myName.length();
			for (Amoeba a : myChildren) {
				maxLengthSeen = Math.max(maxLengthSeen, a.longestNameLength());
			}
			return maxLengthSeen;
		}
	}

	public int size() {
		if (myRoot != null) {
			AmoebaFamily.sizeHelper(myRoot);
		}
		return mySize;
	}

	public static void sizeHelper(Amoeba currentAmoeba) {
		if (currentAmoeba.myChildren == null)
			mySize++;
		else {
			mySize++;
			Iterator<Amoeba> iter = currentAmoeba.myChildren.iterator();
			while (iter.hasNext()) {
				AmoebaFamily.sizeHelper(iter.next());
			}
		}
	}

	public String busiestAmoeba() {
		if (myRoot != null) {
			return AmoebaFamily.longestName(myRoot);
		}
		return myRoot.myName;
	}

	public static String busiestAmoebaHelper(Amoeba currentAmoeba) {
		String maxLengthName = currentAmoeba.myName;
		Iterator<Amoeba> iter = currentAmoeba.myChildren.iterator();
		while (iter.hasNext()) {

			String childMaxLengthName = AmoebaFamily.longestName(iter.next());
			if (childMaxLengthName.length() > maxLengthName.length()) {
				maxLengthName = childMaxLengthName;
			}
		}
		return maxLengthName;
	}

	public int height() {
		if (myRoot != null) {
			return heightHelper(myRoot);
		}
		return 0;
	}

	private static int heightHelper(Amoeba x) {
		if (x.myChildren.isEmpty()) {
			return 1;
		} else {
			int bestSoFar = 1;
			Iterator<Amoeba> iter = x.myChildren.iterator();
			while (iter.hasNext()) {
				Amoeba child = iter.next();
				bestSoFar = Math.max(heightHelper(child), bestSoFar);
			}
			bestSoFar++;
			return bestSoFar;
		}
	}
}
