import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

import static java.nio.file.StandardCopyOption.*;

/**
 * - Class that abstracts the operations required to manipulate files on the
 * 		computer, for convenience 
 * - Should not be initialized
 * 
 * @author Johnny Le, Kevin Luong, Sijing Xin, Jia Guo
 *
 */

public final class FileOps {

	private FileOps() {
	}

	/**
	 * - Stores the gitlet instance in a file called Gitlet.ser. 
	 * - Don't really worry about using this... Everything that involves it I already did for
	 * 		you
	 * 
	 * @param gitlet
	 *       - the gitlet instance you want to serialize
	 */
	static void writeToFile(Gitlet gitlet) {
		try {
			ObjectOutputStream toOutput = new ObjectOutputStream(
					new FileOutputStream(System.getProperty("user.dir")
							+ "/.gitlet/Gitlet.ser"));
			toOutput.writeObject(gitlet);
			toOutput.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	/**
	 * - Retrieves the gitlet instance stored in the file Gitlet.ser 
	 * 
	 * @return the gitlet object that is stored in Gitlet.ser, or null if the
	 *         files does not exist
	 * @throws FileNotFoundException
	 *             - if the Gitlet.ser file does not exist
	 */
	static Gitlet readFromFile() throws FileNotFoundException {
		try {
			ObjectInputStream toInput = new ObjectInputStream(
					new FileInputStream(System.getProperty("user.dir")
							+ "/.gitlet/Gitlet.ser"));
			Gitlet toReturn = (Gitlet) toInput.readObject();
			toInput.close();
			return toReturn;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * Checks two files to see if they have equivalent content
	 * 
	 * @param a 
	 * 		Path of the first object
	 * @param b
	 * 		Path of the second object
	 * @return
	 * 		true if equal, false otherwise
	 */
	public static boolean contentEquals(Path a, Path b) {
		try {
			byte[] first = Files.readAllBytes(a);
			byte[] second = Files.readAllBytes(b);
			return Arrays.equals(first, second);
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * - Creates the directory given by the file, if the directory doesn't
	 * already exist
	 * 
	 * @param file
	 *            - A file object referring to the directory you wish to create
	 *            (Note: the file object path must include the directory, ie:
	 *            /User/JonJuno/newDir )
	 * @return true - if the directory was successfully created false - if the
	 *         directory could not be created
	 */
	public static boolean mkdir(File file) {
		try {
			if (file.exists()) {
				return false;
			} else {
				return file.mkdir();
			}
		} catch (SecurityException e) {
			return false;
		}
	}

	/**
	 * Makes a copy of the file a in the location designated by file b
	 * 
	 * @param a
	 *            - File object that you wish to copy
	 * @param b
	 *            - Target directory you want to copy the file to, including the file in the path
	 */
	public static void copy(File a, File b) {
		try {
			FileOps.rCopy(a, b);
		} catch (IOException e) {
		}
	}

	/**
	 * Makes a copy of the file a in the location designated by file b, replacing the file if it exists
	 * @param a
	 * 		- File object that you wish to copy
	 * @param b
	 * 		- Target directory you want to copy the file to, including the file in the path
	 */
	public static void copyReplace(File a, File b) {
		try {
			FileOps.rCopyReplace(a, b);
		} catch (IOException e) {
		}
	}

	/**
	 * Helper Method for the copy function, that recursively creates parent directories for the file if
	 * they don't exist already
	 * 
	 * @param a
	 * 		- File object that you wish to copy
	 * @param b
	 * 		- Target directory you want to copy the file to, including the file in the path
	 * @throws IOException
	 * 		If one of the paths is invalid, or other errors occur
	 */
	private static void rCopy(File a, File b) throws IOException {
		if (b.getParentFile().exists() == false) {
			rCopy(a.getParentFile(), b.getParentFile());
		}
		Path A = Paths.get(a.getPath());
		Path B = Paths.get(b.getPath());
		Files.copy(A, B);
	}

	/**
	 * Helper Method for the copy function, that recursively creates parent directories for the file if
	 * they don't exist already. Also will replace the file
	 * 
	 * @param a
	 * 		- File object that you wish to copy
	 * @param b
	 * 		- Target directory you want to copy the file to, including the file in the path
	 * @throws IOException
	 * 		If one of the paths is invalid, or other errors occur
	 */
	private static void rCopyReplace(File a, File b) throws IOException {
		if (b.getParentFile().exists() == false) {
			rCopy(a.getParentFile(), b.getParentFile());
		}
		Path A = Paths.get(a.getPath());
		Path B = Paths.get(b.getPath());
		if (a.isDirectory() == false)
			Files.copy(A, B, REPLACE_EXISTING);
		else
			Files.copy(A, B);
	}

}
