public class Person {

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
	public boolean equals(Object o2) {
		
		if(o2 instanceof Person) {
			Person p2 = (Person) o2;
			if(p2.myName.equals(myName) &&
			   hashCode() == p2.hashCode()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return myName.hashCode();
	}
	
	// TODO add additional methods
	
}
