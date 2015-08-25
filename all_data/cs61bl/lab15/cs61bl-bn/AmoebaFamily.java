import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

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
		if (myRoot != null) {
			myRoot.makeNamesLowercase();
		}
		// Your goal is to make this as similar as possible to addChild
	}

	// Replaces the name of an amoeba named currentName with the name newName.
	// Precondition: the amoeba family contains exactly one amoeba named
	// currentName.
	public void replaceName(String currentName, String newName) {
		// Your goal is to make this as similar as possible to addChild
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

	// Your goal is to make this as similar as possible to addChild

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
		// your goal is to make this look as similar as possible to
		// longestNameLength
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

	public String busiest() {
		if (myRoot != null) {
			return myRoot.busiest().myName;
		}
		return "";
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
		// AmoebaFamily family = new AmoebaFamily("A");
		// family.addChild("A", "B");
		// family.addChild("B", "C");
		// family.addChild("B", "D");
		// family.addChild("B", "E");
		// family.addChild("A", "F");
		// family.addChild("F", "G");
		// family.addChild("G", "H");
		// family.addChild("H", "I");
		// family.addChild("I", "J");

		Iterator test = family.iterator();
		System.out.println(test.next());
		System.out.println(test.next());
		System.out.println(test.next());
		System.out.println(test.next());
		System.out.println(test.next());
		System.out.println(test.next());
		System.out.println(test.next());
		System.out.println(test.next());
		System.out.println(test.next());
		System.out.println(test.next());
		System.out.println(test.next());
		System.out.println(test.next());
		System.out.println(test.next());
		// System.out.println(test.next());

		// System.out.println(family.height());
		// family.makeNamesLowercase();
		// family.replaceName("amos mccoy", "12345");
		// family.printFlat();
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
		// private Stack<Amoeba> amoebaStack;
		private Queue<Amoeba> amoebaQueue;

		public AmoebaIterator() {
			// amoebaStack = new Stack<Amoeba>();
			amoebaQueue = new LinkedList<Amoeba>();
			if (myRoot == null) {
				return;
			}
			// amoebaStack.push(myRoot);
			amoebaQueue.add(myRoot);

		}

		public boolean hasNext() {
			// return !amoebaStack.empty();
			return !amoebaQueue.isEmpty();
		}

		public Amoeba next() throws IllegalStateException {
			if (hasNext()) {
				// Amoeba temp = amoebaStack.pop();
				Amoeba temp = amoebaQueue.remove();
				if (temp.myChildren.size() != 0) {

					// for (int k = temp.myChildren.size() - 1; k >= 0; k--) {
					// amoebaStack.push(temp.myChildren.get(k));
					//
					// }
					for (Amoeba a : temp.myChildren) {
						amoebaQueue.add(a);
					}
				}
				return temp;
			} else {
				throw new IllegalStateException();
			}
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

		/**
		 * Recursive functions below
		 */
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

		public void makeNamesLowercase() {
			if (myChildren.size() != 0) {
				for (Amoeba a : myChildren) {
					a.makeNamesLowercase();
				}
				myName = myName.toLowerCase();
			} else {
				myName = myName.toLowerCase();
			}
		}

		public void replaceName(String currentName, String newName) {
			if (!myName.equals(currentName)) {
				for (Amoeba a : myChildren) {
					a.replaceName(currentName, newName);
				}
			} else {
				myName = newName;
				return;
			}
		}

		public void printFlat() {
			if (myChildren.size() != 0) {
				for (Amoeba a : myChildren) {
					a.printFlat();
				}
				System.out.println(myName);

			} else {
				System.out.println(myName);
			}
		}

		public void print() {
			if (myChildren.size() != 0) {
				printHelper();

				for (Amoeba a : myChildren) {
					a.print();
				}

			} else {
				printHelper();
			}
		}

		public void printHelper() {
			int numOfP = 0;
			Amoeba placeHolder = this;
			while (placeHolder.myParent != null) {
				numOfP++;
				placeHolder = placeHolder.myParent;
			}
			for (int k = 0; k < numOfP; k++) {
				System.out.print("    ");
			}
			System.out.println(myName);

		}

		// Returns the length of the longest name of this Amoeba's children
		public int longestNameLength() {
			int maxLengthSeen = myName.length();
			for (Amoeba a : myChildren) {
				maxLengthSeen = Math.max(maxLengthSeen, a.longestNameLength());
			}
			return maxLengthSeen;
		}

		public String longestName() {
			String maxLengthSeen = myName;
			for (Amoeba a : myChildren) {
				if (a.longestName().length() > maxLengthSeen.length()) {
					maxLengthSeen = a.longestName();
				}
			}
			return maxLengthSeen;
		}

		public int size() {
			int size = 1;
			if (myChildren.size() != 0) {
				for (Amoeba a : myChildren) {
					size += a.size();
				}
			}

			return size;
		}

		public Amoeba busiest() {
			Amoeba busySoFar = this;
			for (Amoeba a : myChildren) {
				if (busySoFar.myChildren.size() < a.busiest().myChildren.size()) {
					busySoFar = a;
				}
			}
			return busySoFar;
		}

		// In Amoeba
		private int height() {
			if (myChildren.isEmpty()) {
				return 1;
			} else {
				int HighestSoFar = 0;
				for (Amoeba a : myChildren) {
					int d = 1;
					d = d + a.height();
					HighestSoFar = Math.max(HighestSoFar, d);
				}

				return HighestSoFar;
			}
		}
	}
}
