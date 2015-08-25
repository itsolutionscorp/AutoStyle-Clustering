import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 *Gitlet, a program function like git, using serializable and deserilizable object to make the object alive
 * , call GitLetHelper for each command.
 */
public class Gitlet{

	// Credit to http://www.tutorialspoint.com/java/java_serialization.htm	
	private GitLetHelper p = null;
	private String loc = System.getProperty("user.dir") + File.separator+".gitlet" + File.separator + "git.ser";
	/** a Constructor for Gitlet, make new serializable object alive if it never exist.
	 *  and new object for GitLetHelper Object.
	 */
	public Gitlet(){

		p = new GitLetHelper();
		File f = new File(loc);		
		if (!f.exists()){
			new File(System.getProperty("user.dir") + File.separator + ".gitlet" + File.separator).mkdirs();
			Serialize();
		}
	}
	/**a method return GitLetHelper object.
	 * @return GitLetHelper p
	 *  
	 */
	public GitLetHelper getP(){
		return p;
	}
	/**  a method to write the object into git.ser file.
	 *  using File Stream for output, writeObject, then close the both File Stream. 
	 */
	public void Serialize(){
		try
		{
			FileOutputStream fileOut = new FileOutputStream(loc);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(p);
			out.close();
			fileOut.close();			
		}catch(IOException i)
		{
			i.printStackTrace();
		}
	}
	/**  a method to read the object from git.ser file and store it back to GitLetHelper p.
	 *  using File Stream for input, readObject method, casting, then close the both File Stream.
	 */
	public void Deserialize(){
		try
		{
			FileInputStream fileIn = new FileInputStream(loc);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			p = (GitLetHelper) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i)
		{
			i.printStackTrace();
			return;
		}catch(ClassNotFoundException c)
		{
			System.out.println("GitLet class not found");
			c.printStackTrace();
			return;
		}
	}
	/**	 a main program, taking command from user, then create new object, deserialize, 
	 * perform command, then serialize the object.
	 * @param args
	 * 		a command for git to perform
	 *
	 */		
	public static void main(String[] args) {		
		Gitlet b = new Gitlet();	
		b.Deserialize();
		GitLetHelper a = b.getP();		
		a.command(args);		
		b.Serialize();
	}
}
