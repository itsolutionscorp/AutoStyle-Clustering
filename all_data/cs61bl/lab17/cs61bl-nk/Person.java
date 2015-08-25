public class Person implements Comparable<Person> {

	private String myName;
	private boolean nameHasChanged;

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
		this.nameHasChanged = true;
	}
	
	// TODO add additional methods
	public boolean nameChanged() {
		return this.nameHasChanged;
	}

	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return this.myName.compareTo(((Person)o).myName);
	}
}
