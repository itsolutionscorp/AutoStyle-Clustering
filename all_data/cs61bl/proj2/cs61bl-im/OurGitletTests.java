import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class OurGitletTests {

	private static final File stageFolder = new File("./.gitlet/stage/");
	private static final String LINE_SEPARATOR = "\r\n|[\r\n]";

	/**
	 * Constructs the gitlet folder
	 * 
	 * @throws Exception
	 *             throws exceptions from main if there are some
	 */
	@Before
	public void constructor() throws Exception {
		if (Gitlet.hasBeenStarted()) { // delete gitlet folder if it has been
										// there
			File gitletFolder = new File("./.gitlet");
			recursiveDelete(gitletFolder);
		}
	}

	@Before
	public void filePlacer() {
		createFile("1.txt", "1");
		createFile("a.txt", "a");
		createFile("wug.txt", "I wish I was a wug.");
		createFile("testing/test.txt", "Test me");

	}

	@Test
	public void testAdd() {
		gitlet("init");
		gitlet("add", "1.txt");
		gitlet("add", "wug.txt");
		ArrayList<String> names = new ArrayList<String>(
				Arrays.asList(stageFolder.list()));
		assertTrue(names.contains("1.txt"));
		assertTrue(names.contains("wug.txt"));
		assertFalse(names.contains("test.txt"));
	}

	@Test
	public void testCheckout() {
		gitlet("init");
		gitlet("add", "wug.txt");
		gitlet("commit", "first wug");
		createFile("wug.txt", "My wish came true!");
		assertEquals("My wish came true!", getText("wug.txt"));
		gitlet("checkout", "wug.txt");
		assertEquals("I wish I was a wug.", getText("wug.txt"));

		gitlet("add", "testing/test.txt");
		gitlet("commit", "no wug");
		createFile("wug.txt", "My wish came true!");
		assertEquals("My wish came true!", getText("wug.txt"));
		gitlet("checkout", "wug.txt");
		assertEquals("I wish I was a wug.", getText("wug.txt"));

		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		createFile("wug.txt", "Sad, I didn't become a wug");
		gitlet("add", "wug.txt");
		gitlet("commit", "sadness");
		assertEquals("Sad, I didn't become a wug", getText("wug.txt"));
		gitlet("checkout", "master");
		assertEquals("I wish I was a wug.", getText("wug.txt"));
		createFile("wug.txt", "I don't care");
		gitlet("add", "wug.txt");
		gitlet("commit", "rebel");
		assertEquals("I don't care", getText("wug.txt"));
		gitlet("checkout", "3", "wug.txt");
		assertEquals("Sad, I didn't become a wug", getText("wug.txt"));
	}

	/**
	 * Test that changes propagate through the branch after rebase
	 * 
	 * 
	 * 
	 * 
	 * 2("2", "B") 3("3") [6("4") 7("C")] master 0 1 ("1" "A") 4("4") 5("C")
	 * other
	 * 
	 * 
	 */
	@Test
	public void testRebasePropagation() {
		writeFile("a.txt", "A");
		writeFile("1.txt", "1");
		gitlet("init");
		gitlet("add", "1.txt");
		gitlet("add", "a.txt");
		gitlet("commit", "1A");
		gitlet("branch", "other");
		writeFile("1.txt", "2");
		writeFile("a.txt", "B");
		gitlet("add", "1.txt");
		gitlet("add", "a.txt");
		gitlet("commit", "2B");
		writeFile("1.txt", "3");
		gitlet("add", "1.txt");
		gitlet("commit", "3B");
		gitlet("checkout", "other");
		writeFile("1.txt", "4");
		gitlet("add", "1.txt");
		gitlet("commit", "4A");
		writeFile("a.txt", "C");
		gitlet("add", "a.txt");
		gitlet("commit", "4C");
		gitlet("rebase", "master");
		gitlet("reset", "1");
		assertTrue(getText("1.txt").equals("1"));
		assertTrue(getText("a.txt").equals("A"));
		gitlet("reset", "2");
		assertTrue(getText("1.txt").equals("2"));
		assertTrue(getText("a.txt").equals("B"));
		gitlet("reset", "3");
		assertTrue(getText("1.txt").equals("3"));
		assertTrue(getText("a.txt").equals("B"));
		gitlet("reset", "4");
		assertTrue(getText("1.txt").equals("4"));
		assertTrue(getText("a.txt").equals("A"));
		gitlet("reset", "5");
		assertTrue(getText("1.txt").equals("4"));
		assertTrue(getText("a.txt").equals("C"));
		gitlet("reset", "6");
		assertTrue(getText("1.txt").equals("4"));
		assertTrue(getText("a.txt").equals("B"));
		gitlet("reset", "7");
		assertTrue(getText("1.txt").equals("4"));
		assertTrue(getText("a.txt").equals("C"));
	}

	/**
	 * Test that rebase produces an appropriate log
	 * 
	 * 
	 * 
	 * 
	 * 2("2", "B") 3("3") [6("4") 7("C")] master 0 1 ("1" "A") 4("4") 5("C")
	 * other
	 * 
	 * 
	 */
	@Test
	public void testRebaseLog() {
		writeFile("a.txt", "A");
		writeFile("1.txt", "1");
		gitlet("init");
		gitlet("add", "1.txt");
		gitlet("add", "a.txt");
		gitlet("commit", "1A");
		gitlet("branch", "other");
		writeFile("1.txt", "2");
		writeFile("a.txt", "B");
		gitlet("add", "1.txt");
		gitlet("add", "a.txt");
		gitlet("commit", "2B");
		writeFile("1.txt", "3");
		gitlet("add", "1.txt");
		gitlet("commit", "3B");
		gitlet("checkout", "other");
		writeFile("1.txt", "4");
		gitlet("add", "1.txt");
		gitlet("commit", "4A");
		writeFile("a.txt", "C");
		gitlet("add", "a.txt");
		gitlet("commit", "4C");
		gitlet("rebase", "master");
		String log = gitlet("log");
		String gLog = gitlet("global-log");
		assertTrue(log.contains("Commit 7")); // I didn't know how to test this
		assertTrue(log.contains("Commit 6"));
		assertFalse(log.contains("Commit 5"));
		assertFalse(log.contains("Commit 4"));
		assertTrue(log.contains("Commit 3"));
		assertTrue(log.contains("Commit 2"));
		assertTrue(log.contains("Commit 1"));
		assertTrue(log.contains("Commit 0"));
		assertTrue(gLog.contains("Commit 0"));
		assertTrue(gLog.contains("Commit 1"));
		assertTrue(gLog.contains("Commit 2"));
		assertTrue(gLog.contains("Commit 3"));
		assertTrue(gLog.contains("Commit 4"));
		assertTrue(gLog.contains("Commit 5"));
		assertTrue(gLog.contains("Commit 6"));
		assertTrue(gLog.contains("Commit 7"));
	}

	@Test
	public void testReset() {
		gitlet("init");
		gitlet("add", "testing/test.txt");
		gitlet("commit", "my first test");
		String commit1 = "my first test";
		createFile("testing/test.txt", "I hate tests");
		gitlet("add", "testing/test.txt");
		gitlet("commit", "second test");
		createFile("testing/test.txt", "last one");
		gitlet("add", "testing/test.txt");
		gitlet("commit", "third test");
		gitlet("reset", "1");
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commit1, "initial commit" },
				extractCommitMessages(logContent));

		gitlet("reset", "2");
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		gitlet("add", "1.txt");
		gitlet("commit", "I am number 1");
		gitlet("checkout", "master");
		gitlet("reset", "3");
		logContent = gitlet("log");
		assertArrayEquals(new String[] { "third test", "second test", commit1,
				"initial commit" }, extractCommitMessages(logContent));

		gitlet("reset", "4");
		logContent = gitlet("log");
		assertArrayEquals(new String[] { "I am number 1", "second test",
				commit1, "initial commit" }, extractCommitMessages(logContent));
	}

	@Test
	public void testMerge() {
		gitlet("init");
		createFile("testing/wug.txt", "This is a wug");
		gitlet("add", "testing/wug.txt");
		gitlet("commit", "added testing wug");
		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		createFile("testing/wug.txt", "This is a cool wug.");
		gitlet("add", "testing/wug.txt");
		gitlet("commit", "made wug the new cool thing");
		gitlet("checkout", "master");
		createFile("testing/wug.txt", "This is a master wug.");
		gitlet("add", "testing/wug.txt");
		gitlet("commit", "mastered the art of the wug");
		gitlet("rm", "testing/wug.txt");
		gitlet("commit", "dethroned the wug");
		gitlet("merge", "cool-beans");
		assertEquals("This is a cool wug.", getText("testing/wug.txt"));
		gitlet("checkout", "master");
		assertEquals("This is a cool wug.", getText("testing/wug.txt"));
	}

	@Test
	public void testConflict() {
		gitlet("init");
		gitlet("add", "a.txt");
		gitlet("add", "1.txt");
		gitlet("commit", "1A");
		gitlet("branch", "branch1");
		createFile("a.txt", "b");
		gitlet("add", "wug.txt");
		gitlet("add", "a.txt");
		gitlet("commit", "a changed");
		gitlet("checkout", "branch1");
		createFile("a.txt", "c");
		gitlet("add", "a.txt");
		gitlet("commit", "a changed again");
		gitlet("merge", "master");
		File conflicted = new File("a.txt.conflicted");
		assertTrue(conflicted.exists());
		assertEquals("b", getText("a.txt.conflicted"));

	}

	/**
	 * Creates a new file with the given fileName and gives it the text
	 * fileText.
	 */
	private static void createFile(String fileName, String fileText) {
		File f = new File(fileName);
		try {
			if (f.exists()) {
				f.delete();
			}
			if (f.toPath().getParent() != null)
				f.toPath().getParent().toFile().mkdirs();
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
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

	private static String gitlet(String... args) {

		String[] commandLineArgs = new String[args.length + 2];
		commandLineArgs[0] = "java";
		commandLineArgs[1] = "Gitlet";
		for (int i = 0; i < args.length; i++) {
			commandLineArgs[i + 2] = args[i];
		}
		String results = command(commandLineArgs);
		System.out.println(results);
		return results.trim();
	}

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

	private static String getText(String fileName) {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(fileName));
			return new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			return "";
		}
	}

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
}
