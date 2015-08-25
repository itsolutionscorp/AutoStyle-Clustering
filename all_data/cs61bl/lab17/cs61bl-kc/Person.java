public class Person implements Comparable{

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
	
	public boolean equals(Object obj2){
		Person other = (Person) obj2;
		return this.myName.equals(other.myName);
	}
	
	public int compareTo(Object obj2){
		Person other = (Person) obj2;
		return this.myName.compareTo(other.myName);
	}	
	
	
	
}
