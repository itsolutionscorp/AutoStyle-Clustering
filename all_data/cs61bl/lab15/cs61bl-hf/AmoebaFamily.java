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
		if (myRoot != null) {
			myRoot.makeNamesLowercase();
		}
	}

	// Replaces the name of an amoeba named currentName with the name newName.
	// Precondition: the amoeba family contains exactly one amoeba named
	// currentName.
	public void replaceName(String currentName, String newName) {
		if (myRoot != null) {
			myRoot.replaceName(currentName, newName);
		}

		// Your goal is to make this as similar as possible to addChild
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

	int height() {
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
		System.out.println("Here's the family:");
		// family.print();
		Iterator<Amoeba> iter = family.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next().myName);
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
		private LinkedList<Amoeba> fringe = new LinkedList<Amoeba>();
		public void constract(Amoeba in) {
			fringe.add(in);
			if (!in.myChildren.isEmpty()) {
				for (Amoeba a : in.myChildren) {
					constract(a);
				}
			}
		}

		// You will supply the details of this class in a future lab.

		public AmoebaIterator() {
			if (myRoot != null) {
				constract(myRoot);
				 System.out.print(fringe);
			}
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		@SuppressWarnings("null")
		public Amoeba next() {
			Amoeba myAmoeba = fringe.getFirst();
			fringe.remove(0);
			return myAmoeba;
		}

		// private Stack<Amoeba> fringe = new Stack<Amoeba>();
		//
		// public AmoebaIterator() {
		// if (myRoot != null) {
		// fringe.push(myRoot);
		// }
		// }
		//
		// public boolean hasNext() {
		// return !fringe.isEmpty();
		// }
		//
		// @SuppressWarnings("null")
		// public Amoeba next() {
		// if (fringe.isEmpty()) {
		// throw new NoSuchElementException("tree ran out of elements");
		// } else {
		// Amoeba myAmoeba = fringe.pop();
		// ArrayList<Amoeba> flop = new ArrayList<Amoeba>() ;
		// for (Amoeba a : myAmoeba.myChildren) {
		// flop.add(a);
		// }
		// if (!flop.isEmpty()){
		// for (int i= flop.size()-1;i>=0 ; i--) {
		// fringe.push(flop.get(i));
		// }
		// }
		// return myAmoeba;
		//
		// }
		// }

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
			myName = myName.toLowerCase(); // make my name small case
			for (Amoeba a : myChildren) {
				a.makeNamesLowercase(); // make all my childern's names small
										// case
			}
		}

		public void replaceName(String currentName, String newName) {
			if (myName.equals(currentName)) {
				myName = newName; // if it is my name change it
			} else {
				for (Amoeba a : myChildren) {

					a.replaceName(currentName, newName); // if it is not call
															// all
															// my children and
															// ask...
				}
			}
		}

		public void printFlat() {
			System.out.println(myName); // print my name;
			for (Amoeba a : myChildren) {
				a.printFlat(); // print all my kid's name
			}
		}

		public void print() {
			if (myParent == null) {
				System.out.println(myName);
				for (Amoeba a : myChildren) {
					a.print(); // print all my kid's name
				}
			} else {
				for (int i = 0; i < helpPrint_indent(); i++) {
					System.out.print("    ");
				}
				System.out.println(myName);
				for (Amoeba a : myChildren) {
					a.print(); // print all my kid's name
				}
			}
		}

		public int helpPrint_indent() {
			Amoeba ret = myParent;
			int count = 0;
			while (ret != null) {
				count++;
				ret = ret.myParent;
			}
			return count;
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
			String longest = myName;
			for (Amoeba a : myChildren) {
				if (longest.length() < a.longestName().length()) {
					longest = a.longestName();
				}
			}
			return longest;

		}

		public int size() {
			int ret = 1;
			for (Amoeba a : myChildren) {
				ret += a.size();
			}
			return ret;
		}

		private int height() {
			if (myChildren.isEmpty()) {
				return 1;
			} else {
				int bestSoFar = 1;
				for (Amoeba a : myChildren) {
					bestSoFar = Math.max(a.height() + 1, bestSoFar);
				}

				return bestSoFar;

			}
		}
	}
}
