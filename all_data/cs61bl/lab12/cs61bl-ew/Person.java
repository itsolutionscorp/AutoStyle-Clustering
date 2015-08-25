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
		this.myName.hashCode();
		
	}
	
	// TODO add additional methods
	public int hashCode(){
		return (int) this.myName.hashCode();
	}
}
