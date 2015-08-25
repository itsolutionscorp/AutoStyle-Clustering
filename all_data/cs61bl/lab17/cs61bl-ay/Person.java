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
	
	public int compareTo(Object person){
		return myName.compareTo(((Person)person).myName);
	}
	
	public boolean equals(Object person) {
		return ((Person) person).myName.equals(myName);
	}
	
	public int hashCode(){
		return myName.hashCode();
	}
}
