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
		if (myRoot != null) {
			myRoot.makeNamesLowercase();
		}

	}

	// Replaces the name of an amoeba named currentName with the name newName.
	// Precondition: the amoeba family contains exactly one amoeba named currentName.
	public void replaceName(String currentName, String newName) {
		// Your goal is to make this as similar as possible to addChild
		if (myRoot != null) {
			myRoot.replaceName(currentName, newName);
		}
	}

	// Print the names of all amoebas in the family, one on each line.
	// later you will write print() that has more interesting formatting
	public void printFlat() {
		// Your goal is to make this as similar as possible to addChild
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
			myRoot.print(0);
		}


	}

	// returns the length of the longest name in the Amoeba Family
	public int longestNameLength() {
		if (myRoot != null) {
			return myRoot.longestNameLength();
		}
		return 0; // empty tree
	}

	// instead of returning the length of the longest name, this method should
	// return the name that is longest.
	public String longestName() {
		// your goal is to make this look as similar as possible to
		// longestNameLength
		if (myRoot != null) {
			return myRoot.longestName();
		}
		return "";
	}
	
	
	// returns the number of amoebas in this family.
	public int size() {
		if (myRoot != null) {
			return myRoot.size(0);
		}
		return 0;
	}
	
	public int height(Amoeba node) {
		if (node != null) {
			return node.height();
		}
		return 0; // the height of a null tree as 0
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
		family.addChild("Lisa", "AAAA");
		family.addChild("Marge", "Bill");
		family.addChild("Marge", "Hilary");
		System.out.println("Here's the family:");
		family.print();
		System.out.println("family.longestName() " + family.longestName());
		System.out.println("family.size() " + family.size());
		System.out.println("family.height(family.myRoot) " + family.height(family.myRoot));
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

		//Add a child if parent name matches an amoeba's name,
		//or if parentName matches any of the descendents
		public void addChild(String parentName, String childName) {
			if (myName.equals(parentName)) {
				// base case: have found the parent, so give it the child
				Amoeba child = new Amoeba(childName, this);
				myChildren.add(child);
			} else {
				for (Amoeba a : myChildren) {
					// go through every child
					a.addChild(parentName, childName);
				}
			}
		}

		//Add more void recursive functions below

		// Makes all Amoeba names only lower case letters.
		public void makeNamesLowercase() {
			// first, change itself
			myName = myName.toLowerCase();
			// then, change all its children
			for (Amoeba a : myChildren) {
				// go through every child
				a.makeNamesLowercase();
			}

		}


		// Replaces the name of an amoeba named currentName with the name newName.
		// Precondition: the amoeba family contains exactly one amoeba named currentName. 
		public void replaceName(String currentName, String newName) {
			if (myName.equals(currentName)) {
				// base case: have found the guy, so change its name
				myName = newName;
				return;
			} else {
				for (Amoeba a : myChildren) {
					// go through every child to find that guy
					a.replaceName(currentName, newName);
				}
			}
		}

		// Print the names of all the amoebas in the family, one on each line
		public void printFlat() {
			// first, print itself
			System.out.println(this);
			// then, print all its children
			for (Amoeba a : myChildren) {
				// go through every child
				a.printFlat();
			}
		}

		
		public void print(int level) {
			// first, print itself
			String spaces = "";
			for (int i = 0; i < level; i++) {
				spaces += " ";
			}
			System.out.println(spaces + this);
			// then, print all its children
			for (Amoeba a : myChildren) {
				// go through every child
				a.print(level + 4);
			}
		}


		//Returns the length of the longest name of this Amoeba's children
		public int longestNameLength() {
			int maxLengthSeen = myName.length();
			// assume it is me!
			for (Amoeba a : myChildren) {
				// hey child, the longest name is either you or myself, so take max()
				maxLengthSeen = Math.max(maxLengthSeen, a.longestNameLength());
			}
			return maxLengthSeen;
		}
		
		
		// this method should return the name that is longest of all names in the tree
		public String longestName() {
			String longestName = myName;
			// assume it is me!
			for (Amoeba a : myChildren) {
				// hey child, the longest name is either you or myself, so update longestName if needed
				int myLength = longestName.length();
				if (a.longestName().length() > myLength) {
					longestName = a.longestName();
				}
			}
			return longestName;
		}
		
		
		public int size(int s) {
			// first, count you as one guy
			++s;
			// then, ask your children
			for (Amoeba a : myChildren) {
				s = a.size(s);
			}
			return s;
		}
		
		private int height() {
		    if (myChildren.isEmpty()) {
		    	// if you have no child, your bestSoFar is 1 (just you)
		    	return 1;
		    } else {
		    	int bestSoFar = 1; // you're guaranteed to have at least one child, so your max depth must be at least 1
		    	// if you have child, each of your children will return his/her bestSoFar
		    	// and you just update bestSoFar and return it when you are done asking all your children
		        for (Amoeba a : myChildren) {
		            bestSoFar =  Math.max(a.height(), bestSoFar);
		        }
		        return 1 + bestSoFar;
		    }
		}
		
		
		
	}
} 
