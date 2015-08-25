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

	@Override
	public int compareTo(Object otherPerson) {
		// TODO Auto-generated method stub
		if (this.toString().equals(otherPerson.toString())) {
			return 0;
		}
		else if (this.toString().compareTo(otherPerson.toString()) < 0) {
			return -1;
		} else {
			return 1;
		}
	}
	
	// TODO add additional methods
	
}
