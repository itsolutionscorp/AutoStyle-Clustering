public class Person implements Comparable<Person>{

	private String myName;
	private boolean access = true;

	public Person(String name) {
		this.myName = name;
	}

	// return a String representation of the Person object
	public String toString() {
		return myName;
	}

	// Change the name of the person
	public void changeName(String newName) {
		access = false;
		this.myName = newName;
	}
		
	// TODO add additional methods
	public int compareTo(Person person) {
		// TODO Auto-generated method stub		
		return this.myName.compareTo(person.myName);
	}
	
	public boolean getaccess() {
		return access;
	}
	
}
