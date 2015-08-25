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
	
	// TODO add additional methods
//    @Override
//    public boolean equals(Object obj) {
//    	return ((this.myName.equals(((Person)obj).myName)));
//    }
    
    @Override
    public int hashCode() {
    	return myName.hashCode();
    	// Note: if you change the name of the person, hashCode also changes. 
    	//       if you do not override hashCode function, hashCode function returns memory location 
    	//       which stays the same.
    }
	
}
