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
		// Your goal is to make this as similar as possible to addChild
		// if(myRoot!= null){
		myRoot.myName = myRoot.myName.toLowerCase();
		// System.out.println(myRoot.myName);
		if (myRoot.myChildren != null) {
			for (Amoeba a : myRoot.myChildren) {
				a.makeNamesLowercase();
			}
		}

	}

	// public static void helpMakeNamesLowercase(Amoeba a) {
	// a.myName = a.myName.toLowerCase();
	// // System.out.println(a.myName);
	// if (a.myChildren != null) {
	// for (Amoeba b : a.myChildren) {
	// helpMakeNamesLowercase(b);
	// // System.out.println(b.myName);
	// }
	// }
	// }

	// Replaces the name of an amoeba named currentName with the name newName.
	// Precondition: the amoeba family contains exactly one amoeba named
	// currentName.
	public void replaceName(String currentName, String newName) {
		// Your goal is to make this as similar as possible to addChild
		if (myRoot.myName.equals(currentName)) {
			myRoot.myName = newName;
		} else {
			for (Amoeba a : myRoot.myChildren) {
				a.replaceName(currentName, newName);
				// helpReplaceName(a, currentName, newName);
			}

		}
	}

	// public static void helpReplaceName(Amoeba a, String currentName,
	// String newName) {
	// if (a.myName == currentName) {
	// a.myName = newName;
	// }
	// if (a.myChildren != null) {
	// for (Amoeba b : a.myChildren) {
	// helpReplaceName(b, currentName, newName);
	// }
	// }
	// }

	// Print the names of all amoebas in the family, one on each line.
	// later you will write print() that has more interesting formatting
	public void printFlat() {
		if (myRoot != null) {
			System.out.println(myRoot.myName);
			for (Amoeba a : myRoot.myChildren) {
				a.printFlat();
				// helpPrintFlat(a);
				// System.out.println(a.myName);

			}
		}
		// Your goal is to make this as similar as possible to addChild
	}

	// public static void helpPrintFlat(Amoeba a) {
	// System.out.println(a.myName);
	// if (a.myChildren != null) {
	// for (Amoeba b : a.myChildren) {
	// helpPrintFlat(b);
	// }
	// }
	// }

	// Print the names of all amoebas in the family.
	// Names should appear in preorder, with children's names
	// printed oldest first.
	// Members of the family constructed with the main program above
	// should be printed in the following sequence:
	// Amos McCoy, mom/dad, me, Mike, Bart, Lisa, Homer, Marge,
	// Bill, Hilary, Fred, Wilma, auntie
	// This is the pretty print exercise.
	public void print() {
		String howMany = "    ";
		System.out.println(myRoot.myName);
		if (myRoot.myChildren != null) {
			for (Amoeba a : myRoot.myChildren) {
				a.print(howMany);
			}
		}
	}

	// public static void helpPrint(Amoeba a, String howMany) {
	// System.out.println(howMany + a.myName);
	// if (a.myChildren != null) {
	// howMany += "    ";
	// for (Amoeba b : a.myChildren) {
	// helpPrint(b, howMany);
	// }
	// }
	// }

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
			return myRoot.size(1);
		}
		return 0;
	}

	public int height() {
		if (myRoot != null) {
			return myRoot.height(0);
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
		family.makeNamesLowercase();
		family.print();
		System.out.println(family.size());
		System.out.println("---------------");
		Iterator<Amoeba> dog = family.iterator();
		while (dog.hasNext()){
			System.out.println(dog.next());
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

//		Stack penguin = new Stack();
//
//		public AmoebaIterator() {
//
//			if (myRoot != null) {
//				penguin.push(myRoot);
//			}
//		}
//
//		public boolean hasNext() {
//			return !penguin.empty();
//		}
//
//		public Amoeba next() {
//			if(myRoot.myChildren == null){
//				throw new NoSuchElementException("Forever alone");
//			}
//			Amoeba puppy = (Amoeba) penguin.pop();
//			for(int c = puppy.myChildren.size() - 1; c >= 0; c--){

//				penguin.push(puppy.myChildren.get(c));

//			}
//
//			return puppy;
//			
//		}

		Queue snake = new LinkedList();

		public AmoebaIterator() {
			if (myRoot != null) {
				snake.add(myRoot);
			}
		}

		public boolean hasNext() {
			return !snake.isEmpty();
		}
		
		public Amoeba next() {
			if(hasNext() == false){
				throw new NoSuchElementException("Forever alone");
			}
			Amoeba rat = (Amoeba) snake.remove();
			for(int c = 0 ; c < rat.myChildren.size(); c++){
				snake.add(rat.myChildren.get(c));
			}

			return rat;
			
		}
		
		
		
		public void remove() {
			// Not used for now -- removal from a tree can be difficult.
			// Once you've learned about different ways to remove from
			// trees, it might be a good exercise to come back and
			// try to implement this.
		}

	} // end of AmoebaIterator nested class

	public static class Amoeba {

		public Object name;
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

		// Add more void recursive functions below

		public void makeNamesLowercase() {
			myName = myName.toLowerCase();
			// System.out.println(a.myName);
			if (myChildren != null) {
				for (Amoeba b : myChildren) {
					b.makeNamesLowercase();
					// System.out.println(b.myName);
				}
			}
		}

		public void replaceName(String currentName, String newName) {
			if (myName.equals(currentName)) {
				myName = newName;
			}
			if (myChildren != null) {
				for (Amoeba b : myChildren) {
					b.replaceName(currentName, newName);
				}
			}
		}

		public void print(String howMany) {
			System.out.println(howMany + myName);
			if (myChildren != null) {
				howMany += "    ";
				for (Amoeba b : myChildren) {
					b.print(howMany);
				}
			}
		}

		public void printFlat() {
			System.out.println(myName);
			if (myChildren != null) {
				for (Amoeba b : myChildren) {
					b.printFlat();
				}
			}
		}

		public String longestName() {
			String longest = myName;
			for (Amoeba b : myChildren) {
				if (longest.length() < b.longestName().length()) {
					longest = b.longestName();
				}
			}
			return longest;
		}

		public int size(int n) {
			for (Amoeba b : myChildren) {
				n = b.size(n + 1);
			}
			return n;
		}

		private int height(int bestSoFar) {
			if (myChildren.isEmpty()) {
				return 1;
			} else {
				bestSoFar++;
				for (Amoeba a : myChildren) {

					bestSoFar = Math.max(a.height(bestSoFar), bestSoFar);
				}
				return bestSoFar;
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
}
