import java.io.Serializable;

import java.nio.file.*;
import java.util.HashMap;
import java.util.ArrayList;

public class FileTree implements Serializable {
	private static final long serialVersionUID = 42059025135571391L;
	private HashMap<String, Integer> fileMap;
	
	/**
	 * Constructs the filetree as a HashMap with the filename as the key and the fileID as the value.
	 */
	public FileTree() {
		fileMap = new HashMap<String, Integer>();
	}
	/**
	 * Copies the fileTree
	 * @return
	 * 		a fileTree
	 */
	public FileTree Copy(){
		FileTree temp = new FileTree();
		temp.fileMap = new HashMap<String, Integer>(this.fileMap);
		return temp;
	}
	
	/**
	 * Adds a path and id to fileMap
	 * @param path
	 * 		a path
	 * @param id
	 * 		a file id
	 */
	public void add(String path, int id) {
		fileMap.put(path, new Integer(id));
	}
	
	/**
	 * Adds a path as a String and id to fileMap
	 * @param path
	 * 		a path
	 * @param id
	 * 		a file id
	 */
	public void add(Path path, int id) {
		fileMap.put(path.toString(), new Integer(id));
	}
	
	/**
	 * Removes a path from fileMap
	 * @param path
	 * 		a string
	 */
	public void remove(String path) {
		fileMap.remove(path);
	}
	
	/**
	 * Same as above, but takes in a path rather than a string
	 * @param path
	 * 		a path
	 */
	public void remove(Path path) {
		fileMap.remove(path.toString());
	}
	
	/**
	 * Finds the file Id of a path
	 * @param path
	 * 		a string
	 * @return the fileId
	 */
	public Integer getID(String path) {
		Integer id = fileMap.get(path);
		if (id != null)
			return id;
		else 
			return -1;
	}
	
	/**
	 * Gets the full path of the file
	 * @param path
	 * 		a string
	 * @return a path
	 */
	public Path getRealPath(String path) {
		int id = fileMap.get(path);
		Path p = FileSystems.getDefault().getPath(".gitlet", "AllFiles", Integer.toString(id));
		return p;
	}
	
	
	
	/**
	 * Returns an Arraylist of all the files
	 * @return an ArrayList
	 */
	public ArrayList<String> returnallfiles(){
		ArrayList<String> temp = new ArrayList<String>(); 
		for(String x: fileMap.keySet()){
			temp.add(x);
		}
		return temp;
	}
	
}
