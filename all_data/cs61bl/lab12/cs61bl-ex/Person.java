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
	public boolean equals(Object obj){
		return this.toString().equals(obj.toString());
	}
	
	public int hashCode(){
		return this.toString().hashCode();
	}
	
}
