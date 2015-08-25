

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;



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
		if(myRoot!=null){
			myRoot.makeNamesLowercase();
		}
		
	}

	// Replaces the name of an amoeba named currentName with the name newName.
	// Precondition: the amoeba family contains exactly one amoeba named currentName.
	public void replaceName(String currentName, String newName) {
		// Your goal is to make this as similar as possible to addChild
		if(myRoot!=null){
			myRoot.replaceName(currentName,newName);
		}
	}

	// Print the names of all amoebas in the family, one on each line.
	// later you will write print() that has more interesting formatting
	public void printFlat() {
		// Your goal is to make this as similar as possible to addChild
		if(myRoot!=null){
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
		if(myRoot!=null){
			myRoot.setIndex(0);
			myRoot.prettyPrint();
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
		if(myRoot!=null){
			return myRoot.longestName();
	}
		return "";
		}
	public int size(){
		if(myRoot!=null){
			return myRoot.size();
		}return 0;
	}
	public int height(){
		if(myRoot!=null){
			return myRoot.height();
		}
		return 0;
	}
	public String busiest(){
		if(myRoot!=null){
			return myRoot.busiest().myName;
		}
		return null;
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
		for(Amoeba i: family){
			System.out.println(i);
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
		private Stack fringe;
		private MyQueue<Object> box;
		public AmoebaIterator() {
			//fringe = new Stack();
			//fringe.push(myRoot);
			box=new MyQueue();
			box .add(myRoot);
			
			
		}

		public boolean hasNext() {
			//return  !fringe.isEmpty();
			return !box.isEmpty();
		}

		public Amoeba next() {
			if (!hasNext()) {
		        throw new NoSuchElementException("tree ran out of elements");
		    }
		   // Amoeba node = (Amoeba) fringe.pop();
			Amoeba node=(Amoeba)box.element();
		   //for(int i=node.myChildren.size()-1;i>=0;i--){
			//   fringe.push(node.myChildren.get(i));
		  // }
			for(int i=0;i<node.myChildren.size();i++){
				   box.add(node.myChildren.get(i));
			   }
		    box.remove();
		    return node;
		}
		public class MyQueue<Amoeba>implements Queue<Amoeba>{
            LinkedList Q;
            public MyQueue(){
            	Q= new LinkedList();
            }
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return Q.size();
			}

			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return Q.isEmpty();
			}

			@Override
			public boolean contains(Object o) {
				// TODO Auto-generated method stub
				return Q.contains(o);
			}

			@Override
			public Iterator<Amoeba> iterator() {
				// TODO Auto-generated method stub
				return Q.iterator();
			}

			@Override
			public Object[] toArray() {
				// TODO Auto-generated method stub
				return Q.toArray();
			}

			@Override
			public <T> T[] toArray(T[] a) {
				// TODO Auto-generated method stub
				return (T[]) Q.toArray(a);
			}

			@Override
			public boolean remove(Object o) {
				// TODO Auto-generated method stub
				return Q.remove(o);
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return Q.containsAll(c);
			}

			@Override
			public boolean addAll(Collection<? extends Amoeba> c) {
				// TODO Auto-generated method stub
				return Q.addAll(c);
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return Q.removeAll(c);
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return Q.retainAll(c);
			}

			@Override
			public void clear() {
				// TODO Auto-generated method stub
				Q.clear();
			}

			@Override
			public boolean add(Amoeba e) {
				// TODO Auto-generated method stub
				return Q.add(e);
			}

			@Override
			public boolean offer(Amoeba e) {
				// TODO Auto-generated method stub
				return Q.offer(e);
			}

			@Override
			public Amoeba remove() {
				// TODO Auto-generated method stub
				return (Amoeba) Q.removeFirst();
			}

			@Override
			public Amoeba poll() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Amoeba element() {
				// TODO Auto-generated method stub
				return (Amoeba) Q.element();
			}

			@Override
			public Amoeba peek() {
				// TODO Auto-generated method stub
				return (Amoeba) Q.peek();
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
		public int index;
		public void setIndex(int n){
			index=n;
		}
		public int index(){
			return index;
		}

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
        public void makeNamesLowercase(){
        	myName=myName.toLowerCase();
        	for (Amoeba a : myChildren) {
                a.makeNamesLowercase();
            }
        }
        public void replaceName(String current, String changed){
        	if(myName.equals(current)){
        		myName = changed;
        	}
        		for(Amoeba a: myChildren){
        			a.replaceName(current, changed);
        		
        	}
        }
        public void printFlat(){
        	System.out.println(myName);
        	for(Amoeba a: myChildren){
        		a.printFlat();
        	}
        }
        public void prettyPrint(){
        	String start="";
        	for(int n=0;n<index;n++){
        		start+="    ";
        		
        	}
        	System.out.println(start+myName);
        	for(Amoeba a : myChildren){
        		a.setIndex(this.index()+1);
        		a.prettyPrint();
        	}
        	
        }
        public String longestName(){
        	String max = myName;
        	if(myChildren.isEmpty()){
        		return max;
        	}
        	for(Amoeba a:myChildren){
        		if(a.longestName().length()>max.length()){
        			max= a.longestName();
        		}
        	}return max;
        }
        public int size(){
        	if(myChildren.isEmpty()){
        		return 1;
        	}else{
        		int sofar=1;
        		for(Amoeba a: myChildren){
        			sofar+=a.size();
        		}
        		return sofar;
        	}
        }
        //Returns the length of the longest name of this Amoeba's children
        public int longestNameLength() {
            int maxLengthSeen = myName.length();
            for (Amoeba a : myChildren) {
                maxLengthSeen = Math.max(maxLengthSeen, a.longestNameLength());
            }
            return maxLengthSeen;
        }
        public Amoeba busiest(){
        	if(myChildren.isEmpty()){
        		return this;
        	}
        	Amoeba sofar=this;
        
        	for(Amoeba a: myChildren){
        		if(this.myChildren.size()<a.busiest().myChildren.size()){
        			sofar=a.busiest();
        			
        			}
        	}
        	return sofar;
        }
        private int height() {
            if (myChildren.isEmpty()) {
            	return 1;
            } else {
                int bestSoFar = 0;
                for (Amoeba a : myChildren) {
                    bestSoFar =  Math.max(a.height(), bestSoFar);
                }
                return 1+ bestSoFar;
            }
        }
        
    }
} 
