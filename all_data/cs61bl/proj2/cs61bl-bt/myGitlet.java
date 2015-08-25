import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class myGitlet implements Serializable{
	 HashMap<String, FileObject> staged2;
	 ArrayList<String> untracked;
	 ArrayList<Branch> allBranches;
	 CommitNode hstr;
	 ArrayList<String> allBranchNames;
	 ArrayList<Commit> allCommits;
	 Branch currentB;
	 Boolean conflictedState;
	 int numOfCommits;
	
	 /**
	  * Constructs a myGitlet Object that copies over 
	  * instance variables from class Gitlet that must be serialized
	  */
	public myGitlet(){
		this.staged2 = Gitlet.staged2;
		this.untracked = Gitlet.untracked;
		this.allBranches = Gitlet.allBranches;
		this.hstr = Gitlet.hstr;
		this.allCommits = Gitlet.allCommits;
		this.allBranches = Gitlet.allBranches;
		this.allBranchNames = Gitlet.allBranchNames;
		this.currentB = Gitlet.currentB;
		this.conflictedState = Gitlet.conflictedState;
		this.numOfCommits = Gitlet.numOfCommits;
	}

	/**
	 * Serializes the current state of instance variables from class Gitlet
	 * into GitletSer.ser file in the .gitlet folder.
	 * Also serializes any commits that have been made.
	 */
	public static void Serialize(){
		myGitlet gl = new myGitlet();
		int id = gl.hstr.item().getID();
		try {
			FileOutputStream fos = new FileOutputStream(new File(".gitlet/GitletSer.ser"));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(gl);
			for (int i = gl.numOfCommits-1; i < id + 1; i++){
				File f = new File(".gitlet/commits/"+i);
				if(!f.exists()){
					Files.createDirectory(Paths.get(".gitlet/commits/"+i));
				}
				File mostRecentCommit = new File(".gitlet/commits/"+i+"/"+i+".ser");
				fos = new FileOutputStream(mostRecentCommit);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(gl.allCommits.get(i));
			}
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads the GitletSer.ser file and writes the object back into a myGitlet object
	 * 
	 * @return toRtn myGitlet object containing information from the last run of Gitlet 
	 * 			to be deserialized
	 */
	public static myGitlet Deserialize(){
		if (Gitlet.gitlet.exists()){
		}
		File glFile = new File(".gitlet/GitletSer.ser");
		myGitlet toRtn = null;
		if(glFile.exists()){
			try {
				FileInputStream fis = new FileInputStream(glFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				toRtn = (myGitlet) ois.readObject();
				fis.close();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return toRtn;
	}

}
