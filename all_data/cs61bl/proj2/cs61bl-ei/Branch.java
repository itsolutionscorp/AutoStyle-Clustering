import java.io.Serializable;


public class Branch implements Serializable{
	private Commit myRoot;
	private Commit myLeaf;
	private String myName;
	private Gitlet myGit;
	
	/**
	 * Branch constructor with all its associated values
	 * @param name
	 *        Branch's name
	 * @param root
	 *        Branch's root
	 * @param git
	 *        The Gitlet instance the branch is associated with.
	 */
	public Branch(String name, Commit root, Gitlet git){
		myRoot = root;
		myName = name;
		myLeaf = root;
		myGit = git;
		myGit.getMyBranches().put(myName, this);
	}
	
	/**
	 * Gets the name of the Branch
	 * @return
	 *        String of the Branch's name
	 */
	public String getName(){
		return myName;
	}
	
	/**
	 * Returns the Branch's associated Gitlet instance
	 * @return
	 *        A Gitlet instance
	 */
	public Gitlet getMyGit(){
		return myGit;
	}
	
	/**
	 * Gets the Branch's commit root
	 * @return
	 * 	 	  Commit instance
	 */
	public Commit getRoot(){
		return myRoot;
	}
	/**
	 * Gets the Branch's leaf/latest commit.
	 * @return
	 *        Commit instance
	 */
	public Commit getLeaf(){
		return myLeaf;
	}
	
	/**
	 * Sets the Branch's leaf commit
	 * @param c
	 *         A commit
	 */
	public void setLeaf(Commit c) {
		myLeaf = c;
	}
}