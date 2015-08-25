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
	
	public int compareTo(Object name1){
		Person name = (Person) name1;
		return this.myName.compareTo(name.myName);
	}
	
	public int compareTo(Person name2){
		return this.myName.compareTo(name2.myName);
	}
}
