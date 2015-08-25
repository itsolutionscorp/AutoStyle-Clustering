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
//	public int hashCode() {
//		return this.myName.hashCode();
//	}
//	
	public boolean equals(Person p) {
	if (compareTo(p) == 0){
		return true; 
	} else{
	return false;
	}
	}

	@Override
	public int compareTo(Person p) {
		if (p.toString() == toString()) {
			return 0; 
		} else {
		return 1;
	}
}
}

