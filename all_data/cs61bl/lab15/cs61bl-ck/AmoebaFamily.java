import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
		myRoot.myName = myRoot.myName.toLowerCase();
		for (Amoeba a : myRoot.myChildren) {
			AmoebaFamily sub = new AmoebaFamily("sub"); 
			sub.myRoot = a;
			sub.makeNamesLowercase();
		}
		
	}

	// Replaces the name of an amoeba named currentName with the name newName.
	// Precondition: the amoeba family contains exactly one amoeba named currentName.
	public void replaceName(String currentName, String newName) {
		// Your goal is to make this as similar as possible to addChild
		if (myRoot.myName.equals(currentName)) {
			myRoot.myName = newName;
		}
		for (Amoeba a : myRoot.myChildren) {
			AmoebaFamily sub = new AmoebaFamily("sub"); 
			sub.myRoot = a;
			sub.replaceName(currentName, newName);
		}
	}

	// Print the names of all amoebas in the family, one on each line.
	// later you will write e() that has more interesting formatting
	public void printFlat() {
		// Your goal is to make this as similar as possible to addChild
		System.out.println("amoeba_" + myRoot);
		for (Amoeba a : myRoot.myChildren) {
			a.printFlatHelper(1);
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
		System.out.println(myRoot);
		for (Amoeba a : myRoot.myChildren) {
			a.printHelper();
		}
	}

	// returns the length of the longest name in the Amoeba Family
	public int longestNameLength() {
		int longest = myRoot.myName.length();
		for (Amoeba a : myRoot.myChildren) {
			if (a.longestNameLength() > longest) {
				longest = a.longestNameLength();
			}
		}
		return longest;
	}
	
	public int height() {
		if (myRoot == null) {
			return 0;
		}
		if (myRoot.myChildren.isEmpty()) {
			return 1;
		}
		int max = 1;
		for (Amoeba a : myRoot.myChildren) {
			max = Math.max(a.height(), max);
		}
		return max + 1;
	}
    
	// instead of returning the length of the longest name, this method should
	// return the name that is longest.
	public String longestName() {
		// your goal is to make this look as similar as possible to
		// longestNameLength
//		String longest = myRoot.myName;
//		for (Amoeba a : myRoot.myChildren) {
//			if (a.longestName().length() > longest.length()) {
//				longest = a.longestName();
//			}
//		}
//		return longest;
		if (myRoot != null) {
			return myRoot.longestName();
		}
		return "";
	}
	
	public int size() {
		if (myRoot == null) return 0;
		if (myRoot.myChildren.isEmpty()) {
			return 1;
		}
		int size = 1;
		for (Amoeba a : myRoot.myChildren) {
			size = size + a.sizeHelper();
		}
		return size;
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
		family.addChild("me", "MARGE");
		family.addChild("Mike", "Bart");
		family.addChild("Mike", "Lisa");
		family.addChild("MARGE", "Bill");
		family.addChild("MARGE", "Hilary");
		System.out.println("Here's the family:");
		
		Iterator f = family.iterator();
		System.out.println(f.next());
		System.out.println(f.hasNext());
		System.out.println(f.next());
		System.out.println(f.next());
		System.out.println(f.hasNext());
		System.out.println(f.next());
		System.out.println(f.next());
		System.out.println(f.next());
		System.out.println(f.hasNext());
		System.out.println(f.next());
		System.out.println(f.next());
		System.out.println(f.next());
		System.out.println(f.next());
		System.out.println(f.hasNext());
		System.out.println(f.next());
		System.out.println(f.next());
		System.out.println(f.next());
		System.out.println(f.hasNext());
		family.makeNamesLowercase();
	}
	

//	public class AmoebaIterator implements Iterator<Amoeba> {
//		// Amoebas in the family are enumerated in preorder,
//		// with children enumerated oldest first.
//		// Members of the family constructed with the main program above
//		// should be enumerated in the following sequence:
//		// Amos McCoy, mom/dad, me, Mike, Bart, Lisa, Homer, Marge,
//		// Bill, Hilary, Fred, Wilma
//		// Complete enumeration of a family of N amoebas should take
//		// O(N) operations.
//		private Stack stack = new Stack();
//		// You will supply the details of this class in a future lab.
//		public AmoebaIterator() {
//			if (myRoot != null) {
//				stack.push(myRoot);
//			}
//			
//		}
//
//		public boolean hasNext() {
//			return !stack.isEmpty();
//		}
//
//		public Amoeba next() {
//			if (!hasNext())
//				throw new NoSuchElementException("tree ran out of elements");
//			Amoeba node = (Amoeba) stack.pop();
//			if (!node.myChildren.isEmpty()) {
//				for (int k = node.myChildren.size() - 1; k >= 0; k--) {
//					stack.push(node.myChildren.get(k));
//				}
//			}
//			return node;
//		}
//
//		public void remove() {
//			// Not used for now -- removal from a tree can be difficult.
//			// Once you've learned about different ways to remove from
//			// trees, it might be a good exercise to come back and 
//			// try to implement this.
//		}
//
//	} // end of AmoebaIterator nested class
	
	public class AmoebaIterator implements Iterator<Amoeba> {
		private ArrayList<Amoeba> a = new ArrayList<Amoeba>();

		public AmoebaIterator() {
			if (myRoot != null) {
				a.add(myRoot);
			}
		}

		public boolean hasNext() {
			return !a.isEmpty();
		}

		public Amoeba next() {
			if (!hasNext()) {
				throw new NoSuchElementException("tree ran out of elements");
			}
			Amoeba node = a.remove(0);
			if (!node.myChildren.isEmpty()) {
				for (int k = 0; k < node.myChildren.size(); k++) {
					a.add(node.myChildren.get(k));
				}
			}
			return node;
		}
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

        //Add more void recursive functions below
        
        private int height() {
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
        
        private int sizeHelper() {
        	if (myChildren.isEmpty()) {
        		return 1;
        	} else {
        		int size = 1;
        		for (Amoeba a : myChildren) {
        			size = size + a.sizeHelper();
        		}
        		return size;
        	}
        }
        
//        private Amoeba nextHelper(int nextIdx) {
//        	if (nextIdx == 2) {
//        		return this;
//        	} else {
//        		int count = 2;
//        		for 
//        	}
//        	
//        }

        //Returns the length of the longest name of this Amoeba's children
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
        		if (a.longestName().length() > longest.length()) {
        			longest = a.longestName();
        		}
        	}
        	return longest;
        }
        
        
    	public void makeNamesLowercase() { 
    		myName = myName.toLowerCase();
    		for (Amoeba a : myChildren) {
    			a.makeNamesLowercase();
    		}
    	}

        public void printHelper() {
        	System.out.println(this);
        	for (Amoeba a : myChildren) {
        		a.printHelper();
        	}
        }
        
        
        public void replacenameHelper(String currentName, String newName) {
        	if (myName.equals(currentName)) {
        		myName = newName;
        	}
        	for (Amoeba a : myChildren) {
        		a.replacenameHelper(currentName, newName);
        	}
        }
        
        
    	public void printFlatHelper(int count) {
    		if (count == 1) {
    			System.out.println("	child_" + this);
    			for (Amoeba a : myChildren) {
        			a.printFlatHelper(2);
        		}
    		} else if (count == 2) {
    			System.out.println("		grandchild_" + this);
    			for (Amoeba a : myChildren) {
        			a.printFlatHelper(3);
        		}
    		} else {
    			int i = count;
    			String s  = "grandchild_" + this;
    			while (i > 2) {
    				s = "grand_" + s;
    				i --;
    			}
    			int c = count;
    			while (c >0) {
    				s  = "	" + s;
    				c--;
    			}
    			System.out.println(s);
				count += 1;
    			for (Amoeba a : myChildren) {
        			a.printFlatHelper(count);
        		}
    		}
    	}
    }


	

} 
