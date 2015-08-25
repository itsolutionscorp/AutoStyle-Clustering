import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class FileReader {

	private FileReader() {}

	public static ArrayList<String> readFile(String filename)
			throws IllegalStateException {
		File toRead = new File(System.getProperty("user.dir") + "/" + filename);
		ArrayList<String> toReturn = new ArrayList<String>();
		try {
			for (String line : Files.readAllLines(toRead.toPath(), Charset.forName("US-ASCII"))) {
				toReturn.add(line);
			}
			return toReturn;
		} catch (IOException e) {
			throw new IllegalStateException();
		}
	}

}
