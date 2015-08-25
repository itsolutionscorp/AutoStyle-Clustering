import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;

/**
 * stage area class, implements interface serializable this is a stage area for
 * Gitlet add method, while user are adding a file we copy this file to this
 * stage area that's cool, ha
 * 
 * @author Zihao Jing
 * @author Pingao Liu
 * @author Yuchao Gao
 */
public class Stage implements Serializable {

	/**
	 * UID = 1L serialVersionUID definition, for Stage
	 */
	private static final long serialVersionUID = 1L;

	private static final String STAGE_FILE = ".gitlet/.stage/";
	private static final String STAGED_FILES = ".gitlet/.staged.ser";
	private static final String UNTRACKED_FILE = ".gitlet/.untracked.ser";
	private static final String HAS_UNTRACK = ".gitlet/.has.untrack";

	/**
	 * null constructor
	 */
	Stage() {
	}

	/**
	 * serializable method, for staging
	 * 
	 * @param fileName
	 *            staged file name
	 * @param filePath
	 *            the path of ser file
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void setStaged(String fileName) throws IOException,
			ClassNotFoundException {
		HashSet<String> filesList = getStaged();
		if (filesList == null)
			filesList = new HashSet<String>();
		filesList.add(fileName);
		FileOutputStream fileOut = new FileOutputStream(STAGED_FILES);
		ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
		outStream.writeObject(filesList);
		outStream.close();
		fileOut.close();
	}

	/**
	 * deserializable method, for staging
	 * 
	 * @return the file name
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static HashSet<String> getStaged() throws IOException,
			ClassNotFoundException {
		if (Files.exists(Paths.get(STAGED_FILES))) {
			FileInputStream fileIn = new FileInputStream(STAGED_FILES);
			ObjectInputStream inStream = new ObjectInputStream(fileIn);
			@SuppressWarnings("unchecked")
			HashSet<String> filesName = (HashSet<String>) inStream.readObject();
			fileIn.close();
			inStream.close();
			return filesName;
		} else
			return null;
	}

	/**
	 * remove one file from the serializable file, for staging
	 * 
	 * @param fileName
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void rmStaged(String fileName) throws IOException,
			ClassNotFoundException {
		Files.deleteIfExists(Paths.get(STAGE_FILE + fileName));
		HashSet<String> filesList = getStaged();
		if (filesList == null)
			filesList = new HashSet<String>();
		else
			filesList.remove(fileName);
		FileOutputStream fileOut = new FileOutputStream(STAGED_FILES);
		ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
		outStream.writeObject(filesList);
		outStream.close();
		fileOut.close();
	}

	/**
	 * serializable method, for untracking
	 * 
	 * @param fileName
	 *            staged file name
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void setUntracked(String fileName) throws IOException,
			ClassNotFoundException {
		HashSet<String> filesList = getUntracked();
		if (filesList == null)
			filesList = new HashSet<String>();
		filesList.add(fileName);
		FileOutputStream fileOut = new FileOutputStream(UNTRACKED_FILE);
		ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
		outStream.writeObject(filesList);
		outStream.close();
		fileOut.close();
		setCmtChange("true");
	}

	/**
	 * deserializable method, for untracking
	 * 
	 * @return the file name
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static HashSet<String> getUntracked() throws IOException,
			ClassNotFoundException {
		if (Files.exists(Paths.get(UNTRACKED_FILE))) {
			FileInputStream fileIn = new FileInputStream(UNTRACKED_FILE);
			ObjectInputStream inStream = new ObjectInputStream(fileIn);
			@SuppressWarnings("unchecked")
			HashSet<String> filesName = (HashSet<String>) inStream.readObject();
			fileIn.close();
			inStream.close();
			return filesName;
		} else
			return null;
	}

	/**
	 * remove one file from the serializable file, for untracking
	 * 
	 * @param fileName
	 *            file name
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void rmUntrack(String fileName) throws IOException,
			ClassNotFoundException {
		HashSet<String> filesList = getUntracked();
		if (filesList == null)
			filesList = new HashSet<String>();
		else
			filesList.remove(fileName);
		FileOutputStream fileOut = new FileOutputStream(UNTRACKED_FILE);
		ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
		outStream.writeObject(filesList);
		outStream.close();
		fileOut.close();
	}

	/**
	 * get all staged files name
	 * 
	 * @return file name array but not a list
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static String[] getAllStaged() throws IOException,
			ClassNotFoundException {
		if (getStaged() == null)
			return null;
		else {
			String[] fileList = new String[getStaged().size()];
			int i = 0;
			Iterator<String> iter = getStaged().iterator();
			while (iter.hasNext()) {
				fileList[i] = iter.next();
				i++;
			}
			return fileList;
		}
	}

	/**
	 * get all untracking file names
	 * 
	 * @return file names array
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static String[] getAllUntrack() throws IOException,
			ClassNotFoundException {
		if (getUntracked() == null)
			return null;
		else {
			String[] fileList = new String[getUntracked().size()];
			int i = 0;
			Iterator<String> iter = getUntracked().iterator();
			while (iter.hasNext()) {
				fileList[i] = iter.next();
				i++;
			}
			return fileList;
		}
	}

	/**
	 * clear the stage area after every commit
	 * 
	 * @param path
	 *            this path is for .tracked.ser
	 * @throws IOException
	 */
	public static void clear(String path) throws IOException {
		Files.deleteIfExists(Paths.get(STAGED_FILES));
		File f = new File(path);
		if (f.isDirectory()) {
			File[] fileList = f.listFiles();
			for (File s : fileList)
				if (s.isDirectory())
					clear(s.getPath());
				else
					s.delete();
		} else
			f.delete();
	}

	/**
	 * Returns the text from a standard text file.
	 * 
	 * @param fileName
	 *            a given file name
	 * @return the content of that file in String type
	 * @throws IOException
	 * @author Joseph Moghadam (edited by Yuchao Gao)
	 */
	public static String getText(String fileName) throws IOException {
		if (Files.exists(Paths.get(fileName))) {
			byte[] encoded = Files.readAllBytes(Paths.get(fileName));
			return new String(encoded, StandardCharsets.UTF_8);
		} else
			return "";
	}

	/**
	 * Replaces all text in the existing file with the given text.
	 * 
	 * @param fileName
	 *            file name whose content is going to be replaced
	 * @param fileText
	 *            given file text to replace
	 * @throws IOException
	 * @author Joseph Moghadam (edited by Yuchao Gao)
	 */
	public static void writeFile(String fileName, String fileText)
			throws IOException {
		FileWriter fw = new FileWriter(new File(fileName), false);
		fw.write(fileText);
		fw.close();
	}

	/**
	 * to judge if the commit has been changed
	 * 
	 * @return true if changed else false if not changed
	 * @throws IOException
	 */
	public static boolean hasCmtChange() throws IOException {
		if (getText(HAS_UNTRACK).equals("true"))
			return true;
		else if (getText(HAS_UNTRACK).equals("false"))
			return false;
		else
			return true;
	}

	/**
	 * write true if has made any change else false if made no change
	 * 
	 * @param changed
	 *            if has made any changes
	 * @throws IOException
	 */
	public static void setCmtChange(String changed) throws IOException {
		writeFile(HAS_UNTRACK, changed);
	}

}