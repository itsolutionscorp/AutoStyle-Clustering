import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;


public class FileTools {
	
	/**
	 * Helper method for creation of a commit.
	 * This does all the heavy file copying and deleting while also
	 * tracking all the files into a HashMap.
	 * @param source
	 *        source File
	 * @param destination
	 *        destination File
	 * @param subdir
	 *        String of the sub-directory of a file
	 *        Built recursively as you go through the stage folder
	 *        Needed to accurately store the files in the tracked HashMap with
	 *        the their given pathway.
	 * @param tracked
	 *        HashMap of the tracked files
	 */
	public static void commitCopyTool (File source, File destination, String subdir, HashMap<String, File> tracked) {
		try { //How to copy all files in a folder
		    if (source.isDirectory()) {
		    	for (File file : source.listFiles()) { //Recursively adds all files in the folder and subfolder
		    		if (file.isDirectory()) {
		    			File check = new File(destination.getAbsolutePath() + "/" + file.getName());
		    			if (!check.exists()) {
		    				check.mkdir();
		    			}
		    			String subdir2 = subdir + file.getName() + "/";
		    			File file_dest = new File(destination.getAbsolutePath() + "/" + file.getName());
		    			commitCopyTool(file, file_dest, subdir2, tracked);
		    			file.delete();
		    		} else {
		    			File file_dest = new File(destination.getAbsolutePath() + "/" + file.getName());
			    		commitCopyTool(file, file_dest, subdir, tracked);
			    		file.delete();
		    		}
		    	}
		    } else {
				Files.copy(source.toPath(), destination.toPath()); //Straight up copies file to file
				tracked.put(subdir + source.getName(), destination);
				source.delete();
		    	}
			} catch (IOException e) {
				return;
			}
	}
	
	/**
	 * Helper method for general file copying.
	 * @param source
	 * 		  source File
	 * @param destination
	 *        destination File
	 * @param file_name
	 *        path of the file + name
	 */
	public static void copyFile(File source, File destination, String file_name) {
		if (source.getName() != file_name) {
			File directory = new File(file_name);
			if (!directory.exists()) {
				directory.mkdirs();
			}
		}
		try {
			Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			return;
		}
	}
	
	public static boolean compareFiles(File one, File two) {
		try {
			List<String> one_string = Files.readAllLines(one.toPath());
			List<String> two_string = Files.readAllLines(two.toPath());
			return one_string.equals(two_string);
		} catch (IOException e) {
			return false;
		}
	}	
}
