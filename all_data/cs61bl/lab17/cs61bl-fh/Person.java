public class Person implements Comparable {

	private String myName;
	private boolean hasChangedName;

	public Person(String name) {
		this.myName = name;
	}

	// return a String representation of the Person object
	public String toString() {
		return myName;
	}

	// Change the name of the person
	public void changeName(String newName) {
		this.myName = newName;
		this.hasChangedName = true;
	}

	public int compareTo(Object other) {
		if (myName.hashCode() > ((Person) other).myName.hashCode())
			return 1;
		if (myName.hashCode() == ((Person) other).myName.hashCode())
			return 0;
		else
			return -1;

	}

	public boolean hasChangedName() {
		return hasChangedName;
	}
}
