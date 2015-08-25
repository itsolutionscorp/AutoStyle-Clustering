import java.io.Serializable;

public class Pointer implements Serializable {

	/**
	 * myName is a String representing the name of a Pointer object. A Pointer
	 * object is one which "points" to the head of a Commit branch.
	 * 
	 * myCommit is the Commit to which this Pointer object points.
	 */
	private String myName;
	private Commit myCommit;

	/**
	 * This is the constructor of the Pointer class.
	 * 
	 * @param name
	 *            The name of given to this Pointer, when created in the branch
	 *            method of the Gitlet class.
	 * @param commit
	 *            The Commit object to which this Pointer points.
	 */
	public Pointer(String name, Commit commit) {
		myName = name;
		myCommit = commit;
	}

	/**
	 * This method sets this Pointer to point to the given Commit.
	 * 
	 * @param newCommit
	 *            The Commit object to which this Pointer will now point.
	 */
	public void move(Commit newCommit) {
		myCommit = newCommit;
	}

	/**
	 * This is a getter method for this Pointer's name.
	 * 
	 * @return the name of this Pointer, represented as a String.
	 */

	public String getName() {
		return myName;
	}

	/**
	 * This is a getter method for the Commit object to which this Pointer
	 * points.
	 * 
	 * @return the Commit object to which this Pointer points.
	 */
	public Commit getMyCommit() {
		return myCommit;
	}

	/**
	 * This is a setter method for this Pointer to point to a different Commit
	 * object.
	 * 
	 * @param newCommit
	 *            The Commit object to which this Pointer will point.
	 */
	public void setMyCommit(Commit newCommit) {
		myCommit = newCommit;
	}

	/**
	 * This method overrides the hashcode method of the Object class. The hash
	 * code is used when hashing Pointers into the pointers HashMap in the
	 * Gitlet class.
	 * 
	 * @return the hash value of this Pointer's Commit.
	 */
	@Override
	public int hashCode() {
		return myCommit.hashCode();
	}

	/**
	 * This method overrides the equals method of the Object class. The equals
	 * method is used when determining whether or not this Pointer and another
	 * Pointer are pointing to the same Commit object.
	 * 
	 * @return TRUE if the two Pointers point to the same Commit.
	 */
	@Override
	public boolean equals(Object obj) {
		Pointer check = (Pointer) obj;
		return myCommit.getID() == check.myCommit.getID();
	}
}
