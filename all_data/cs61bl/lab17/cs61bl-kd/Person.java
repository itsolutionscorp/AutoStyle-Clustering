public class Person implements Comparable{

	private String myName;
	private boolean change;

	public Person(String name) {
		this.myName = name;
	}

	// return a String representation of the Person object
	public String toString() {
		return myName;
	}

	// Change the name of the person
	public void changeName(String newName) {
		change = true;
		this.myName = newName;
	}
	
	// TODO add additional methods
	public boolean isChanged() {
		return change;
	}
	
	public int compareTo(Object p) {
		return myName.compareTo(((Person) p).myName);
	}

	
}
