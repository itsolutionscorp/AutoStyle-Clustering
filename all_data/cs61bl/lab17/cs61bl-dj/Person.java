public class Person implements Comparable {

	private String myName;

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
	}

	// TODO add additional methods
	public int compareTo(Object o) {
		if (o == null) {
			throw new NullPointerException();
		} else if (myName.charAt(0) < ((Person) o).myName.charAt(0)) {
			return -1;
		} else if (myName.charAt(0) > ((Person) o).myName.charAt(0)) {
			return 1;
		} else {
			return 0;

		}

	}
}
