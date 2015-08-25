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
	
	// TODO add additional methods	
	@Override
	public int compareTo(Person p) {
		if (myName.equals(p.myName)) {
			return 0;
		}
		for (int i = 0; i < myName.length(); i++) {
			if (myName.charAt(0) > p.myName.charAt(0)) {
				return 1;
			}
			if (myName.charAt(0) < p.myName.charAt(0)) {
				break;
			}
		}
		return -1;
	}
}
