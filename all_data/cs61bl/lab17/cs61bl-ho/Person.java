public class Person implements Comparable<Person> {

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

	public int compareTo(Person p) {
		if (myName.compareTo(p.myName) > 0) {
			return 1;
		} else if (myName.compareTo(p.myName) == 0) {
			return 0;
		}
		return -1;
	}

	// Note: We are supposed not to pass the second test. Because in a BST,
	// sometimes it is okay to modify a key as long as the tree is still a BST,
	// i.e. the order still meets the requirement.
}
