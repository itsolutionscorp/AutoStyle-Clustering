import java.util.*;

public class AmoebaFamily implements Iterable<AmoebaFamily.Amoeba> {

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
		nameHelper(myRoot);
	}

	public static void nameHelper(Amoeba a) {
		if (a != null) {
			a.myName = a.myName.toLowerCase();
			for (Amoeba b : a.myChildren)
				nameHelper(b);
		}
	}

	// Replaces the name of an amoeba named currentName with the name newName.
	// Precondition: the amoeba family contains exactly one amoeba named
	// currentName.
	public void replaceName(String currentName, String newName) {
		// Your goal is to make this as similar as possible to addChild
		replaceHelper(myRoot, currentName, newName);
	}

	private static void replaceHelper(Amoeba a, String currentName,
			String newName) {
		if (a.myName.equals(currentName))
			a.myName = newName;
		else
			for (Amoeba b : a.myChildren)
				replaceHelper(b, currentName, newName);
	}

	// Print the names of all amoebas in the family, one on each line.
	// later you will write print() that has more interesting formatting
	public void printFlat() {
		printHelper(myRoot);
	}

	private static void printHelper(Amoeba a) {
		if (a != null) {
			System.out.println(a);
			for (Amoeba b : a.myChildren)
				printHelper(b);
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
		pprintHelper(myRoot, 0);
	}

	private static void pprintHelper(Amoeba a, int level) {
		if (a != null) {
			String cheese = "";
			for (int i = 0; i < level; i++) {
				cheese += "\t";
			}
			cheese += a.myName;
			System.out.println(cheese);
			for (Amoeba b : a.myChildren) {
				pprintHelper(b, level + 1);
			}
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
		if (myRoot != null)
			return myRoot.longestName();
		return null;
	}

	public int size() {
		return myRoot.size(myRoot);
	}

	// Return an iterator of the amoeba family.
	public Iterator<Amoeba> iterator() {
		return new AmoebaIterator();
	}

	public int height() {
		if (myRoot != null)
			return myRoot.height();
		else
			return 0;
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
		// family.makeNamesLowercase();
		// System.out.println("\n=====");
		// family.printFlat();
		family.replaceName("Amos McCoy", "WugLord Extraordinaire");
		System.out.println(family.longestName());
		// System.out.println("\n=====");
		// family.printFlat();
		System.out.println(family.size());
		System.out.println(family.height());
		System.out.println("====");
		AmoebaIterator i = (AmoebaIterator) family.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
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

//		private Stack<Amoeba> pancakes;
//
//		public AmoebaIterator() {
//			pancakes = new Stack();
//			pancakes.push(myRoot);
//		}
//
//		public boolean hasNext() {
//			return !pancakes.empty();
//		}
//
//		public Amoeba next() {
//			if (!hasNext()) {
//				throw new NoSuchElementException("tree ran out of elements");
//			}
//			Amoeba amy = (Amoeba) pancakes.pop();
//			if (amy.myChildren != null) {
//				for (int i = amy.myChildren.size() - 1; i > -1; i--)
//					pancakes.push(amy.myChildren.get(i));
//			}
//			return amy;
		
		private LinkedList<Amoeba> stick;
		
		public AmoebaIterator() {
			stick = new LinkedList<Amoeba>();
			stick.add(myRoot);
		}
		
		public boolean hasNext() {
			return !stick.isEmpty();
		}

		public Amoeba next() {
			if (!hasNext()) {
				throw new NoSuchElementException("tree ran out of elements");
			}
			Amoeba amy = (Amoeba) stick.remove();
			if (amy.myChildren != null) {
				for (int i =0; i < amy.myChildren.size(); i++)
					stick.add(amy.myChildren.get(i));
			}
			return amy;
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

		public int size(Amoeba amy) {
			int x = 1;
			if (!amy.myChildren.isEmpty()) {
				for (Amoeba a : amy.myChildren) {
					x += size(a);
				}
			}
			return x;
		}

		public String toString() {
			return myName;
		}

		public Amoeba parent() {
			return myParent;
		}

		// Add a child if parent name matches an amoeba's name,
		// or if parentName matches any of the descendents
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

		// Add more void recursive functions below

		// Returns the length of the longest name of this Amoeba's children
		public int longestNameLength() {
			int maxLengthSeen = myName.length();
			for (Amoeba a : myChildren) {
				maxLengthSeen = Math.max(maxLengthSeen, a.longestNameLength());
			}
			return maxLengthSeen;
		}

		public String longestName() {
			Amoeba amy = this;
			int maxLengthSeen = myName.length();
			for (Amoeba a : myChildren) {
				maxLengthSeen = Math.max(maxLengthSeen, a.longestNameLength());
			}
			for (Amoeba a : myChildren) {
				if (a.myName.length() == maxLengthSeen) {
					amy = a;
				}
			}
			return amy.myName;
		}

		private int height() {
			if (myChildren.isEmpty())
				return 1;
			else {
				int bestSoFar = 0;
				for (Amoeba a : myChildren) {
					bestSoFar = Math.max(a.height(), bestSoFar);
				}
				return 1 + bestSoFar;
			}

		}
	}
}
