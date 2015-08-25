import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/**
 * A utility class for IO operations.
 */
public class FileIO {

	/* Path of .gitlet folder */
	public static final String GITLET_PATH = "./.gitlet";
	// public static final String GITLET_PATH = ".gitlet/";

	/* Location of serialized object */
	public static final String SERIALIZED_LOCATION = GITLET_PATH
			+ "/Gitlet.ser";

	public static final String STAGING_AREA = GITLET_PATH + "/stagingArea";

	/**
	 * Copies a file from source path to destination path. Will create folders
	 * if necessary
	 * 
	 * @param source
	 *            Path of source
	 * @param dest
	 *            Path of destination
	 * @throws IOException
	 *             If there was an error copying the file
	 */
	public static void copy(String source, String dest) throws IOException {
		Path p1 = Paths.get(source);
		Path p2 = Paths.get(dest);
		File file = new File(dest);
		;
		file.mkdirs();
		Files.copy(p1, p2, StandardCopyOption.REPLACE_EXISTING);
	}

	/**
	 * 
	 * @param path
	 *            The path to the file
	 * @return True if the path or file exists
	 */
	public static boolean pathExists(String path) {
		return Files.exists(Paths.get(path));
	}

	/**
	 * Creates the .gitlet folder. Should only be called by init
	 */
	public static void createGitletFolder() {
		File file = new File(GITLET_PATH);
		file.mkdir();
	}

	/**
	 * Deletes the given filefolder. Throws IOException if there was an error
	 * 
	 * @param source
	 *            Source path of file/folder
	 * @throws IOException
	 *             IOException if there was an error deleting.
	 */
	public static void delete(File source) throws IOException {

		if (source.isDirectory()) {
			File[] files = source.listFiles();
			for (File f : files) {
				delete(f);
			}
		}
		source.delete();
	}

	/**
	 * Copies the directory/file source into the present working directory
	 * 
	 * @param source
	 *            The source path
	 * @param fName
	 *            What the name of the file should be when copied to the PWD
	 * @throws IOException
	 *             IOException if there was an error copying file from source to
	 *             PWD
	 */
	public static void placeInPWD(String source, String fName)
			throws IOException {
		copy(source, "./" + fName);
	}

}
