public class Person implements Comparable {

	private String myName;
	private boolean changedName;

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
		changedName = true;
	}

	// TODO add additional methods

	boolean hasChangedName() {
		return changedName;
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if (this.equals(o)) {
			return 0;
		}
		else if (this.hashCode() < o.hashCode()) {
			return -1;
		}
		else {
			return 1;
		}
	}
		
}
