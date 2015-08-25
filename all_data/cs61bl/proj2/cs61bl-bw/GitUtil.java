import java.io.*;
import java.nio.channels.FileChannel;

public class GitUtil {
	public static final String gitDir = ".gitlet/";

	/**
	 * Copy the file from the source file to the destination file
	 * 
	 * @param sourceFile
	 *            a string represents the name of source file
	 * @param destFile
	 *            a string represents the name of source file
	 * @throws IOException
	 *             if an I/O exception of some sort has occurred
	 */
	public static void copyFile(File sourceFile, File destFile)
			throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	/**
	 * Delete the files in the folder
	 * 
	 * @param d
	 *            a File to be deleted
	 */
	public static void recursiveDelete(File d) {
		if (d.isDirectory() && d.getName() != "staged") {
			for (File f : d.listFiles()) {
				recursiveDelete(f);
			}
		}
		d.delete();
	}
}
