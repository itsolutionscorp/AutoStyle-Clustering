

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
        if(myRoot != null){//Checks for an empty tree
            myRoot.makeNamesLowercase();//makes current Amoeba name lowercase
        }
    }
 
    // Replaces the name of an amoeba named currentName with the name newName.
    // Precondition: the amoeba family contains exactly one amoeba named currentName.
    public void replaceName(String currentName, String newName) {
        // Your goal is to make this as similar as possible to addChild
        if(myRoot != null){//Checks for an empty tree
            myRoot.replaceName(currentName, newName); //Call the corresponding method in the Amoeba class
        }
    }
 
    // Print the names of all amoebas in the family, one on each line.
    // later you will write print() that has more interesting formatting
    public void printFlat() {
        // Your goal is to make this as similar as possible to addChild
        if(myRoot != null){
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
        if(myRoot != null){
            myRoot.print("");
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
    public int longestName() {
        // your goal is to make this look as similar as possible to
        // longestNameLength
        if(myRoot != null){
            return myRoot.longestName();
        }
        return 0;
    }
     
    public int size() {
        if(myRoot != null){
            return myRoot.size();
        }
        return 0; 
    }
     
    public String busiest() {
        if(myRoot != null){
            return myRoot.busiest();
        }
        return null;
    }
     
    public int height(){
        if(myRoot != null){
            return myRoot.height();
        }
        return -1;
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
        //family.print();
        AmoebaIterator familyIter = family.new AmoebaIterator();
        while(familyIter.hasNext()){
            System.out.println(familyIter.next());
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
        @SuppressWarnings("rawtypes")
        private LinkedList<Amoeba> fringe = new LinkedList<Amoeba>();
        public AmoebaIterator() {
             if (myRoot != null) {
                    fringe.add(myRoot);
                }
        }
 
        public boolean hasNext() {
            return !fringe.isEmpty();
        }
 
        public Amoeba next() {
             Amoeba node= fringe.remove();
              
             for(Amoeba a:node.myChildren){
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
        public int indent;
 
        public Amoeba(String name, Amoeba parent) {
            myName = name;
            myParent = parent;
            myChildren = new ArrayList<Amoeba>();
            indent = 1;
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
            myName = myName.toLowerCase();
            for(Amoeba a : myChildren){
                a.makeNamesLowercase();
            }
        }
         
        public void replaceName(String currName, String newName){
            if(myName.equals(currName)){ //If the name of the root is currName, just change that and you are done
                myName = newName;
            }else{
                for(Amoeba a : myChildren){
                    a.replaceName(currName,newName);
                }
            }
        }
         
        public void printFlat(){
            System.out.println(myName); //First print out name of the root
            for(Amoeba a : myChildren){ //Then call the method on each of the children
                a.printFlat();
            }
        }
         
        public void print(String str){
            System.out.println(str + myName); //First print out name of the root
            for(Amoeba a : myChildren){ //Then call the method on each of the children
                a.print(str + "    ");
                 
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
         
        public int longestName(){
            int currLength = myName.length();
            for(Amoeba a : myChildren){
                currLength = Math.max(currLength, a.longestName());
            }
            return currLength;
        }
         
        public int size(){
            int currSize = 1;
            for(Amoeba a : myChildren){
                currSize += a.size();
            }
            return currSize;
        }
         
        public String busiest(){
            int num_Children = myChildren.size();
            String busiest_name = myName;
            for(Amoeba a : myChildren){
                if(a.myChildren.size() > num_Children){
                    num_Children = a.myChildren.size();
                    busiest_name = a.myName;
                }
            }
            return busiest_name;
             
        }
         
        private int height() {
            if (myChildren.isEmpty()) {
                return 1;
            } else {
                int maxHeight = 0;
                for (Amoeba a : myChildren) {
                    maxHeight = Math.max(a.height(), maxHeight);
                }
                return maxHeight + 1;
                 
            }
        }
 
    }
} 