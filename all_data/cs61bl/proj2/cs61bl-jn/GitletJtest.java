import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class GitletJtest {

	private static final String GITLET_DIR = ".gitlet/";
	private static final String TESTING_DIR = "test_files/";

	/* matches either unix/mac or windows line separators */
	private static final String LINE_SEPARATOR = "\r\n|[\r\n]";

	@Before
	public void setUp() {
		File f = new File(GITLET_DIR);
		if (f.exists()) {
			try {
				recursiveDelete(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		f = new File(TESTING_DIR);
		if (f.exists()) {
			try {
				recursiveDelete(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		f.mkdirs();
	}
	
	private static String getText(String fileName) {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(fileName));
			return new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			return "";
		}
	}

	/**
	 * Creates a new file with the given fileName and gives it the text
	 * fileText.
	 */
	private static void createFile(String fileName, String fileText) {
		File f = new File(fileName);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		writeFile(fileName, fileText);
	}

	/**
	 * Replaces all text in the existing file with the given text.
	 */
	private static void writeFile(String fileName, String fileText) {
		FileWriter fw = null;
		try {
			File f = new File(fileName);
			fw = new FileWriter(f, false);
			fw.write(fileText);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Deletes the file and all files inside it, if it is a directory.
	 * 
	 * @throws IOException
	 */
	private static void recursiveDelete(File d) throws IOException {
		if (d.isDirectory()) {
			for (File f : d.listFiles()) {
				recursiveDelete(f);
			}
		}
		if (!d.delete()) {
			throw new IOException("Failed to delete file " + d.getPath());
		}
	}

	/**
	 * Returns an array of commit messages associated with what log has printed
	 * out.
	 */
	private static String[] extractCommitMessages(String logOutput) {
		String[] logChunks = logOutput.split("===");
		int numMessages = logChunks.length - 1;
		String[] messages = new String[numMessages];
		for (int i = 0; i < numMessages; i++) {
			String[] logLines = logChunks[i + 1].split(LINE_SEPARATOR);
			messages[i] = logLines[3];
		}
		return messages;
	}

	/**
	 * Executes the given command on the terminal, and return what it prints out
	 * as a string.
	 * 
	 * Example: If you want to call the terminal command `java Gitlet add
	 * wug.txt` you would call the method like so: `command("java", "Gitlet",
	 * "add", "wug.txt");` The `...` syntax allows you to pass in however many
	 * strings you want.
	 */
	private static String command(String... args) {
		try {
			StringBuilder results = new StringBuilder();
			Process p = Runtime.getRuntime().exec(args);
			p.waitFor();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));) {
				String line = null;
				while ((line = br.readLine()) != null) {
					results.append(line).append(System.lineSeparator());
				}
				return results.toString();
			}
		} catch (IOException e) {
			return e.getMessage();
		} catch (InterruptedException e) {
			return e.getMessage();
		}
	}
	
	@Test
	public void test() {
		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");
		Gitlet git = new Gitlet();
		git.init();
		git.add("test_files/wug.txt");
		git.commit("hi");
		git.branch("cool-beans");
		git.checkoutC("cool-beans");
		String coolMessage = "cool branch!";
		writeFile(wugFileName, "This is a cool wug.");
		git.add(wugFileName);
		git.commit(coolMessage);

		git.checkoutC("master");
		assertEquals("This is a wug.", getText(wugFileName));

		writeFile(wugFileName, "This is the master of all wugs.");
		git.add(wugFileName);
		git.commit("mastered wugs");
		writeFile(wugFileName, "This is the master of all wugs!");
		git.add(wugFileName);
		git.commit("mastered wugs!");
		writeFile(wugFileName, "This is the master of all wugs!?");
		git.add(wugFileName);
		git.commit("mastered wugs?!");
		git.checkoutC("cool-beans");

		writeFile(wugFileName, "Wug is mastered.");
		git.add(wugFileName);
		git.commit(wugFileName);
		git.branch("cool");
		git.checkoutC("cool");
		
		writeFile(wugFileName, "cool");
		git.add(wugFileName);
		git.commit("cool");
		git.branch("cruel");
		git.checkoutC("cruel");
		
		writeFile(wugFileName, "cruel");
		git.add(wugFileName);
		git.commit("cruel");
		git.branch("cruelty");
		git.checkoutC("cruelty");
		
		writeFile(wugFileName, "cruelty");
		git.add(wugFileName);
		git.commit("cruelty");
		git.checkoutC("cool-beans");
		git.branch("tentacruelty");
		git.checkoutC("tentacruelty");
		
		writeFile(wugFileName, "tentacruelty");
		git.add(wugFileName);
		git.commit("tentacruelty");
		git.globalLog();
		git.status();
		
		git.checkoutB(2, wugFileName);
		assertEquals("This is a cool wug.", getText(wugFileName));
		git.checkoutB(7, wugFileName);
		assertEquals("cool", getText(wugFileName));
		git.checkoutB(8, wugFileName);
		assertEquals("cruel", getText(wugFileName));
		git.checkoutC("cruelty");
		System.out.println("printing log of cruelty:");
		git.log();
		System.out.println("GETTING ID "+git.splitFinder("cruelty", "master").getID());
		git.rebase("master");
		System.out.println("printing log of rebased master:");
		git.log();
		git.checkoutC("cruelty");
		System.out.println("printing log of cruelty:");
	}
	
	@Test
	public void testMergeConflicted() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String commitMessage1 = "added wug";
		String commitMessage2 = "made wug cool";
		String commitMessage3 = "mastered wugs";
		createFile(wugFileName, "This is a wug.");
		Gitlet git=new Gitlet();
		git.init();
		git.add(wugFileName);
		git.commit(commitMessage1);
		
		git.branch("cool-beans");
		git.checkoutC("cool-beans");
		
		writeFile(wugFileName, "This is an awesome wug.");
		git.add(wugFileName);
		git.commit("awesome wug");
		
		writeFile(wugFileName, "This is a cool wug.");
		git.add(wugFileName);
		git.commit(commitMessage2);

		git.checkoutC("master");
		
		writeFile(wugFileName, "This is a wug?");
		git.add(wugFileName);
		git.commit("mastered wugs?");	
		
		writeFile(wugFileName, "This is definitely a wug!");
		git.add(wugFileName);
		git.commit(commitMessage3);
		git.checkoutC("cool-beans");

		git.merge("master");

		System.out.println(getText(TESTING_DIR + "wug.txt.conflicted"));

		assertTrue(getText(TESTING_DIR + "wug.txt.conflicted").equals("This is definitely a wug!"));
	}
	
	@Test
	public void testRebaseSpecialCase() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String commitMessage1 = "added wug";
		String commitMessage2 = "made wug cool";
		String commitMessage3 = "mastered wugs";
		createFile(wugFileName, "This is a wug.");
		Gitlet git=new Gitlet();
		git.init();
		git.add(wugFileName);
		git.commit(commitMessage1);
		
		git.branch("cool-beans");
		
		writeFile(wugFileName, "This is an awesome wug.");
		git.add(wugFileName);
		git.commit("awesome wug");
		
		writeFile(wugFileName, "This is a cool wug.");
		git.add(wugFileName);
		git.commit(commitMessage2);
		
		writeFile(wugFileName, "This is a wug?");
		git.add(wugFileName);
		git.commit("mastered wugs?");	
		
		writeFile(wugFileName, "This is definitely a wug!");
		git.add(wugFileName);
		git.commit(commitMessage3);
		git.log();
		git.checkoutC("cool-beans");
		git.log();
		git.rebase("master");
		git.log();

	}

}
