public class Person {

	private String myName;
	public int hashcode;
	
    
	public Person(String name) {
		this.myName = name;
		hashcode = name.hashCode();
		
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

	public int hashCode(){
		return myName.hashCode();
	}
	
}
