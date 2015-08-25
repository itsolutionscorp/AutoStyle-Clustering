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
			Amoeba.makeNamesLowercase(myRoot);
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
			System.out.println (myRoot.myName);
			for (Amoeba a : myRoot.myChildren) {
				a.printFlat();
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
		int counter = 0;
		if (myRoot != null) {
			System.out.println (myRoot.myName);
			for (Amoeba a : myRoot.myChildren) {
				a.print(counter + 1);
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
		if (myRoot != null) {
			return myRoot.longestName();
		}
		return "";
	}
	
	// returns the size of the amoeba family
	public int size() {
		int numCount = 0;
		if (myRoot != null) {
			numCount += myRoot.size();
		}
		return numCount;
	}
	
	public Amoeba busiest() {
    	Amoeba busy = null;
		if (myRoot != null) {
    		busy = myRoot.busiest();
    	}
		return busy;
    }
	
	public int height() {
		int height = 0;
		if (myRoot != null) {
			return myRoot.height();
		}
		return height;
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
//		System.out.println(family.size());
//		System.out.println(family.longestName());
//		System.out.println(family.busiest());
//		System.out.println(family.height());
		
		Iterator<Amoeba> iterator = family.iterator ();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
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
		private Queue <Amoeba> list = new LinkedList <Amoeba>();
		
		public AmoebaIterator() {
			if (myRoot != null){
				list.add(myRoot);
			}
		}

		public boolean hasNext() {
			return !((LinkedList<Amoeba>)list).isEmpty();
		}

		public Amoeba next() {
			for (Amoeba a: list.element().myChildren) {
				list.add(a);
			}
			return list.remove();
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
        
        public static void makeNamesLowercase(Amoeba a) {
        	a.myName = a.myName.toLowerCase();
        	for (Amoeba b: a.myChildren) {
        		makeNamesLowercase(b);
        	}
        }
        
        public void replaceName(String current, String next) {
        	int count = countNameRepetition(current);
        	if (count == 1) {
        		replaceNameHelper(current, next);
        	} else {
        		return;
        	}
        	
        } 
        
        public int countNameRepetition(String current) {
        	int count = 0;
        	if (myName.equals(current)) {
        		count = 1;
        	}
        	for (Amoeba a: myChildren) {
        		count += a.countNameRepetition(current);
        	}
        	return count;
        }
        
        public void replaceNameHelper(String current, String next) {
        	for (Amoeba a:myChildren) {
        		if (a.myName.equals(current)) {
        			a.myName = next;
        		} else {
        			a.replaceNameHelper (current,next);
        		}
        	}
        }
        
        public void printFlat() {
        	System.out.println (myName);
        	for (Amoeba a: myChildren) {
        		a.printFlat();
        	}
        }
        
        public void print(int count){
        	System.out.println(printHelper(count) + myName);
        	for (Amoeba a: myChildren) {
        		a.print (count+1);
        	}
        }
        
        public String printHelper (int num){
        	String s = "";
        	for (int i = 0; i < num; i++) {
        		s += "    ";
        	}
        	return s;
        }

        //Returns the length of the longest name of this Amoeba's children
        public int longestNameLength() {
            int maxLengthSeen = myName.length();
            for (Amoeba a : myChildren) {
                maxLengthSeen = Math.max(maxLengthSeen, a.longestNameLength());
            }
            return maxLengthSeen;
        }
        
        //Returns the longest name of this Amoeba's children
        public String longestName() {
        	String name = myName;
        	for (Amoeba a: myChildren) {
        		if (a.longestName().length() > name.length()) {
        			name = a.longestName();
        		}
        	}
        	return name;
        }
        
        public int size() {
        	int count = 1;
        	for (Amoeba a: myChildren) {
        		count += a.size();
        	}
        	return count;
        } 
        
        public Amoeba busiest() {
        	Amoeba temp = this;
        	for (Amoeba a: myChildren) {
        		if (a.busiest().childrenCount() > temp.childrenCount()) {
        			temp = a.busiest();
        		}
        	}
        	return temp;
        }
        
        private int height() {
        	if (myChildren.isEmpty()) {
            	return 1;
            } else {
                int bestSoFar = 1;
                int count = 1;
            	for (Amoeba a : myChildren) {
                    count = 1 + a.height();
                    bestSoFar = Math.max(count,bestSoFar);
                }
                return bestSoFar;
            }
        }
        
        // Helper method to find number of children an amoeba has
        public int childrenCount() {
        	return myChildren.size();
        }
    }
}
