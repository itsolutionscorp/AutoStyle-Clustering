import java.util.*;

public class AmoebaFamily implements Iterable<AmoebaFamily.Amoeba>{

	public Amoeba myRoot = null;
	private int size = 1;

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
		for (Amoeba child : myRoot.myChildren) {
			child.myName.toLowerCase();
		}
	}

	// Replaces the name of an amoeba named currentName with the name newName.
	// Precondition: the amoeba family contains exactly one amoeba named currentName.
	public void replaceName(String currentName, String newName) {
		// Your goal is to make this as similar as possible to addChild
		for (Amoeba child : myRoot.myChildren) {
			if (child.myName.equals(currentName)) {
				child.myName = newName;
			}
		}
	}

	// Print the names of all amoebas in the family, one on each line.
	// later you will write print() that has more interesting formatting
	public void printFlat() {
		// Your goal is to make this as similar as possible to addChild
		System.out.println(myRoot.myName);
		for (Amoeba child : myRoot.myChildren) {
			AmoebaFamily fam = new AmoebaFamily(child.myName);
			fam.myRoot = child;
			fam.printFlat();
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
		//Pre order is PRINT THIS NODe, then DO ALL THE CHILDREN ON LEFT, ALL THE WAY DOWN, THEN RIGHT.
		if (level(myRoot) == 1) {
			System.out.println(myRoot.myName);
		}
		for (int i = 0; i < myRoot.myChildren.size(); i++) {
			for (int j = 1; j < level(myRoot.myChildren.get(i)); j++) {
				System.out.print("   ");
			}
			System.out.println(myRoot.myChildren.get(i));
			AmoebaFamily fam = new AmoebaFamily(myRoot.myChildren.get(i).myName);
			fam.myRoot = myRoot.myChildren.get(i);
			fam.print();
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
		int temp = myRoot.myName.length();
		int max = myRoot.longestNameLength();
		if (temp >= max) {
			return myRoot.myName;
		} else {
			for (Amoeba child : myRoot.myChildren) {
				if (child.myName.length() == max) {
					return child.myName;
				}
				AmoebaFamily t = new AmoebaFamily(child.myName);
				t.myRoot = child;
				return t.longestName();
			}
			return null;
		}
	}
	
	public int level(Amoeba g) {
		if (g.parent() == null) {
			return 1;
		} else {
			return level(g.parent()) + 1;
		}
	}
	
	
	public int height() {
		if (myRoot.myChildren == null) {
			return 1;
		}
		int h = 0;
		for (Amoeba child : myRoot.myChildren) {
			AmoebaFamily fam = new AmoebaFamily(child.myName);
			fam.myRoot = child;
			h = Math.max(h, fam.height());
		}
		return h + 1;	
	}
	
	// Return the number of Amoebas.
	public int size() {
		if (myRoot.myChildren == null) {
			return 1;
		} else {
			for (Amoeba child : myRoot.myChildren) {
				AmoebaFamily fam = new AmoebaFamily(child.myName);
				fam.myRoot = child;
				size = size + fam.size();
			}
			return size;
		}
		
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
		public int level = 1;

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
