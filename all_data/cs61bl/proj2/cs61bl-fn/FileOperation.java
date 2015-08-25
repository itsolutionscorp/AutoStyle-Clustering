import java.io.*;
import java.nio.file.*;

public class FileOperation {
	/*
	 * This class contains all the file operation methods that will be used in
	 * the project
	 */
	// methods not implemented yet : isEauql

	public static boolean makeNewFolder(String pathName) {
		if (exists(pathName)) {
			System.out.println("folder already exists");
			return false;
		} else {
			return new File(pathName).mkdir();
		}
	}

	public static boolean exists(String pathName) {
		return new File(pathName).exists();
	}

	public static String getAbsolutePath(String pathName) {
		return new File(pathName).getAbsolutePath();
	}

	/*
	 * delete a path , if it is a directory, will also delete things inside
	 */
	public static boolean delete(String pathName) { // inspired by StackFlow
													// post
		if (!exists(pathName)) {
			System.out.println("target does not exist");
			return false;
		} else {
			File a = new File(pathName);
			if (a.isFile()) {
				return a.delete();
			} else {
				String[] files = a.list();
				for (int i = 0, len = files.length; i < len; i++) {
					delete(new File(a, files[i]).getAbsolutePath());
				}
				return a.delete();
			}
		}
	}

	/*
	 * copy a file, not recursively;
	 */
	public static void copy(String source, String target) {
		Path sourcePath = new File(source).toPath();
		Path targetPath = new File(target).toPath();
		File file = targetPath.toFile();
		if (file.exists()) {
			file.delete();
		}
		try {
			if (file.getParentFile() == null) {
				Files.copy(sourcePath, targetPath);
			} else if (file.getParentFile().exists()
					|| file.getParentFile().mkdirs()) {
				Files.copy(sourcePath, targetPath);
			} else {
				throw new IOException();
			}
		} catch (IOException e) {
			System.out.println("Cannot copy");
			e.printStackTrace();
		}
	}

	public static void write(Object toSave, String path) {
		try {
			FileOutputStream fileOut = new FileOutputStream(path + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(toSave);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object read(String path) {
		try {
			FileInputStream fileIn = new FileInputStream(path + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Object toReturn = in.readObject();
			return toReturn;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		}
	}

	public static void rename(String oldName, String newName) {
		if (FileOperation.exists(newName)) {
			System.out.println("The new file already exists.");
		} else {
			File file1 = new File(oldName);
			File file2 = new File(newName);
			file1.renameTo(file2);
		}
	}

	public static void clearEmptyDirectory(String pathName) {
		File dir = new File(pathName);
		if (dir.isDirectory() && dir.list().length != 0) {
			for (String i : dir.list()) {
				File item = new File(pathName, i);
				if (item.isDirectory()) {
					FileOperation.delete(item.getAbsolutePath());
				}
			}
		}
	}
}
