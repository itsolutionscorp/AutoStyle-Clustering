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
		myName = newName;
	}
	
	// TODO add additional methods
	public boolean equals(Person other){
		return myName.equals(other.myName);
			
	}
	
	public int hashCode(){
		return myName.hashCode()
				;
	}
	
	public int compareTo(Object other){
		if(myName.equals(((Person)other).myName))
			return 0;
		else if(myName.compareTo(((Person)other).myName) < 0)
			return -1;
		return 1;
	}
}
