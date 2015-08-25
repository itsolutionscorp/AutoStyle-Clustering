import java.util.Calendar;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**   
 *  A CommitNote tree or leaf, stores the data for each commit, including its id, files of each commit, message
 *  ,the parent commit, and current time.
 */ 
public class CommitNode implements Serializable {	
	private static final long serialVersionUID = 1L;

	private int id;  // id of each commit
	private HashMap<String,File> files; // the array to store all the files in each commit note
	private String message; // message of the commit
	private CommitNode next; // the prev commit
	private String Time; // to store the time in String


	// might dont need and change based on Linux mechine
	private String comitfolder;
	private String staging = System.getProperty("user.dir") + File.separator + ".gitlet"+File.separator+"stagging"+File.separator;
	/**
	 * A Constructor for Commit Node
	 * @param id
	 *            id of the commit
	 * @param message
	 *            commit message
	 * @param prev
	 *            prev commitnode, where this commit point to 	 
	 * @parem copyFromStag
	 * 		boolean to check do we need to copy from stag area	  
	 */
	@SuppressWarnings("unchecked") 
	public CommitNode(int id, String message, CommitNode prev, HashMap<String,File> copyFromStag, HashMap<String,File> UnTracked){
		try{
			this.id = id;
			this.message = message;		
			next = prev;

			//Credit:
			//http://stackoverflow.com/questions/11681202/simpledateformat-appears-to-fail-with-yyyy-mm-dd-hhmmss-sss0
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.Time = sdf.format(Calendar.getInstance().getTime()).toString();

			//Commit Folder		
			comitfolder = System.getProperty("user.dir") + File.separator+".gitlet"+File.separator+"commit"+File.separator + id + File.separator;		
			new File(comitfolder).mkdirs(); // create folder

			files = new HashMap<String,File>(); // initialize

			//Saving Current Commit file in files 
			if (next != null){
				files =	(HashMap<String, File>) next.getFile().clone();
			}	

			if (UnTracked != null){
				for (String name: UnTracked.keySet())
					files.remove(name);	
			}	

			if ( copyFromStag == null)
				return;

			for (File file: copyFromStag.values()){
				//Getting the last filename from commit folder
				String filePath = file.getAbsolutePath().substring(file.getAbsolutePath().indexOf(".gitlet")+8);

				if (filePath.indexOf("stagging")!=-1){ // from stagging area
					filePath = filePath.substring(filePath.indexOf(file.separator));

					File dest = new File(comitfolder+filePath);	
					if (!dest.exists())
						dest.mkdirs();

					Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
					files.put(filePath.substring(1), dest);	
				}
				else{ // from reference point in commit folder
					filePath = filePath.substring(filePath.indexOf(file.separator)+1);
					filePath = filePath.substring(filePath.indexOf(file.separator)+1);					
					files.put(filePath, file);	
				}		
			}
		}catch(IOException e){
			return;
		}
	}
	/** get the parent or prev commitnode	 * 
	 * @return
	 * CommitNode next
	 */
	public CommitNode getNext(){
		return next;
	}
	/** get commit message of this commitnode
	 * @return String message
	 */
	public String getMessage(){
		return message;
	}
	/**
	 * get id of this commit
	 * @return int Id
	 */
	public int getId(){
		return id;
	}
	/** taking a commitnode that now believe to be parent for this commitnode.
	 * 
	 * @param x CommitNode to set as prev or parent
	 */
	public void setNext(CommitNode x){
		next = x;
	}
	/**
	 * print method for each commit in this formation
	 * <br>=== <br>Commit id <br>Time in yyyy-mm-dd hh:mm:ss format
	 * <br>commit message <br>(newline)
	 */
	public void print(){		
		System.out.println("===\nCommit " + id +"\n"+ Time + "\n" + message + "\n");
	}

	/**
	 * return the file inside the commit/id folder
	 * @return HashMap<String,File> file
	 */
	public HashMap<String,File> getFile(){
		return files;
	}		
}