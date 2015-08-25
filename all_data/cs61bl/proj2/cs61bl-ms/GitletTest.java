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

/**
 * Class that provides JUnit tests for Gitlet, as well as a couple of utility
 * methods.
 * 
 * @author Joseph Moghadam
 * 
 *         Some code adapted from StackOverflow:
 * 
 *         http://stackoverflow.com/questions
 *         /779519/delete-files-recursively-in-java
 * 
 *         http://stackoverflow.com/questions/326390/how-to-create-a-java-string
 *         -from-the-contents-of-a-file
 * 
 *         http://stackoverflow.com/questions/1119385/junit-test-for-system-out-
 *         println
 * 
 */
public class GitletTest {
	private static final String GITLET_DIR = ".gitlet/";
	private static final String TESTING_DIR = "test_files/";

	/* matches either unix/mac or windows line separators */
	private static final String LINE_SEPARATOR = "\r\n|[\r\n]";

	/**
	 * Deletes existing gitlet system, resets the folder that stores files used
	 * in testing.
	 * 
	 * This method runs before every @Test method. This is important to enforce
	 * that all tests are independent and do not interact with one another.
	 */
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

	/**
	 * Tests that init creates a .gitlet directory. Does NOT test that init
	 * creates an initial commit, which is the other functionality of init.
	 */
	@Test
	public void testBasicInitialize() {
		gitlet("init");
		File f = new File(GITLET_DIR);
		assertTrue(f.exists());
	}

	/**
	 * Tests that checking out a file name will restore the version of the file
	 * from the previous commit. Involves init, add, commit, and checkout.
	 */
	@Test
	public void testBasicCheckout() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", wugFileName);
		assertEquals(wugText, getText(wugFileName));
	}

	/**
	 * Test that a file that checkout [id] [filename] can work.
	 */
	@Test
	public void testCheckout2() {
		String hiFileName = TESTING_DIR + "hi.txt";
		String hiText = "hi";
		createFile(hiFileName, hiText);
		gitlet("init");
		String commitMessage1 = "initial commit";
		gitlet("add",hiFileName);
		String commitMessage2 = "added hi";
		gitlet("commit", commitMessage2);
		String lolFileName = TESTING_DIR + "lol.txt";
		String lolText = "lol";
		createFile(lolFileName, lolText);
		gitlet("add",lolFileName);
		String commitMessage3 = "added lol";
		gitlet("commit", commitMessage3);
		writeFile(lolFileName, "This is not a wug.");
		gitlet("checkout","2",lolFileName);
		assertEquals(lolText, getText(lolFileName));
	}
	
	/**
	 * Test that a file that has been committed at some point can be restored by checking it out 
	 * from a commit that tracks that version of the file, even if the file wasn't staged prior 
	 * to that commit.
	 */
	@Test
	public void testCheckout3() {
		String hiFileName = TESTING_DIR + "test3.txt";
		String hiText = "Aloha";
		createFile(hiFileName, hiText);
		String helloFileName = TESTING_DIR + "test4.txt";
		String helloText = "Hallo";
		createFile(helloFileName, helloText);
		gitlet("init");
		String commitMessage1 = "initial commit";
		gitlet("add",hiFileName);
		gitlet("add",helloFileName);
		String commitMessage2 = "added test";
		gitlet("commit", commitMessage2);
		writeFile(hiFileName, "Aloha Yifan");
		gitlet("add",hiFileName);
		writeFile(helloFileName,"Hallo meine Freundin");
		gitlet("add",helloFileName);
		String commitMessage3 = "modified hi and hello";
		gitlet("commit", commitMessage3);
		writeFile(hiFileName, "Aloha Yifan!");
		gitlet("add",hiFileName);
		String commitMessage4 = "modified hi again";
		gitlet("commit", commitMessage4);
		gitlet("checkout",helloFileName);
		assertEquals("Hallo meine Freundin", getText(helloFileName));
	}
	
	/**
	 * Test that a file that checkout [id] [filename] can work for a random position in the list even from another branch.
	 */
	@Test
	public void testCheckout4() {
		String hiFileName = TESTING_DIR + "test3.txt";
		String hiText = "Aloha";
		createFile(hiFileName, hiText);
		String helloFileName = TESTING_DIR + "test4.txt";
		String helloText = "Hallo";
		createFile(helloFileName, helloText);
		gitlet("init");
		String commitMessage1 = "initial commit";
		gitlet("add",hiFileName);
		gitlet("add",helloFileName);
		String commitMessage2 = "added test";
		gitlet("commit", commitMessage2);
		writeFile(hiFileName, "Aloha Yifan");
		gitlet("add",hiFileName);
		writeFile(helloFileName,"Hallo meine Freundin");
		gitlet("add",helloFileName);
		String commitMessage3 = "modified hi and hello";
		gitlet("commit", commitMessage3);
		writeFile(hiFileName, "Aloha Yifan!");
		gitlet("add",hiFileName);
		String commitMessage4 = "modified hi again";
		gitlet("commit", commitMessage4);
		gitlet("checkout","1",hiFileName);
		assertEquals("Aloha", getText(hiFileName));
		gitlet("reset","2");
		writeFile(hiFileName, "Aloha Yifan and Danny");
		gitlet("add",hiFileName);
		String commitMessage5 = "modified hi for the third time";
		gitlet("commit", commitMessage5);
		gitlet("checkout","3",hiFileName);
		assertEquals("Aloha Yifan!", getText(hiFileName));
	}
	
	/**
	 * Tests that log after one commit conforms to the format in the spec.
	 * Involves init, add, commit, and log.
	 */
	@Test
	public void testBasicLog() {
		gitlet("init");
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
	}
	
	/**
	 * Test that log works for initial commit.
	 */
	@Test
	public void test1() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		String logContent = gitlet("log");
		assertArrayEquals(new String[] {commitMessage1},extractCommitMessages(logContent));
	}
	
	/**
	 * Test that resetting backward appropriately changes the output of log. 
	 * Also test if resetting to another branch can work properly.
	 */
	@Test
	public void test2() {
		String hiFileName = TESTING_DIR + "hi.txt";
		String hiText = "hi";
		createFile(hiFileName, hiText);
		gitlet("init");
		String commitMessage1 = "initial commit";
		gitlet("add",hiFileName);
		String commitMessage2 = "added hi";
		gitlet("commit", commitMessage2);
		writeFile(hiFileName, "hi.");
		gitlet("add",hiFileName);
		String commitMessage3 = "again";
		gitlet("commit", commitMessage3);
		writeFile(hiFileName, "hi!");
		gitlet("add",hiFileName);
		String commitMessage4 = "again and again";
		gitlet("commit", commitMessage4);
		gitlet("branch","branch1");
		gitlet("reset","2");
		String logcontent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logcontent));
		assertEquals("hi.", getText(hiFileName));
		//create the second branch
		writeFile(hiFileName, "hi!!");
		gitlet("add",hiFileName);
		String commitMessage5 = "the fifth message";
		gitlet("commit", commitMessage5);
		String logcontent2 = gitlet("log");
		assertArrayEquals(new String[] { commitMessage5, commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logcontent2));
		//test if checkout can work when checking out to another branch
		gitlet("checkout","branch1");
		assertEquals("hi!", getText(hiFileName));
		//test if reset can reset the head to another branch
		gitlet("reset","3");
		String logcontent3 = gitlet("log");
		assertArrayEquals(new String[] { commitMessage4,commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logcontent3));
	}
	/**
	 * Test that the output of log after a rebase includes the commit messages from both branches involved in the rebase.
	 * Also test that the replayed commits have new commit ids and the same messages from the original ones.
	 */
	@Test
	public void testRebase1() {
		String hiFileName = TESTING_DIR + "test.txt";
		String hiText = "This is a test";
		createFile(hiFileName, hiText);
		gitlet("init");
		String commitMessage1 = "initial commit";
		gitlet("add",hiFileName);
		String commitMessage2 = "added test";
		gitlet("commit", commitMessage2);
		writeFile(hiFileName, "This is a lame test");
		gitlet("add",hiFileName);
		String commitMessage3 = "again";
		gitlet("commit", commitMessage3);
		writeFile(hiFileName, "This is a lame test!");
		gitlet("add",hiFileName);
		String commitMessage4 = "again and again";
		gitlet("commit", commitMessage4);
		gitlet("branch","branch1");
		gitlet("reset","2");
		writeFile(hiFileName, "This is a very lame test!");
		gitlet("add",hiFileName);
		String commitMessage5 = "the fifth message";
		gitlet("commit", commitMessage5);
		writeFile(hiFileName, "This is a very very lame test!!");
		gitlet("add",hiFileName);
		String commitMessage6 = "the sixth message";
		gitlet("commit", commitMessage6);
		gitlet("rebase","branch1");
		String logcontent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage6, commitMessage5, commitMessage4, commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logcontent));
		gitlet("checkout",hiFileName);
		assertEquals("This is a very very lame test!!", getText(hiFileName));
	}
	/**
	 * Test that another branch that shares the same split point with the current branch will not be replayed.
	 * Also test that log content changes after switching branch.
	 */
	@Test
	public void testRebase2() {
		String hiFileName = TESTING_DIR + "test2.txt";
		String hiText = "Aloha";
		createFile(hiFileName, hiText);
		gitlet("init");
		String commitMessage1 = "initial commit";
		gitlet("add",hiFileName);
		String commitMessage2 = "added test";
		gitlet("commit", commitMessage2);
		writeFile(hiFileName, "Aloha Yifan");
		gitlet("add",hiFileName);
		String commitMessage3 = "again";
		gitlet("commit", commitMessage3);
		writeFile(hiFileName, "Aloha Yifan!");
		gitlet("add",hiFileName);
		String commitMessage4 = "again and again";
		gitlet("commit", commitMessage4);
		gitlet("branch","branch1");
		gitlet("reset","2");
		writeFile(hiFileName, "Aloha Yifan, my name is Leo");
		gitlet("add",hiFileName);
		String commitMessage5 = "the fifth message";
		gitlet("commit", commitMessage5);
		writeFile(hiFileName, "Aloha Yifan, my name is Leo. Aloha Leo!");
		gitlet("add",hiFileName);
		String commitMessage6 = "the sixth message";
		gitlet("commit", commitMessage6);
		gitlet("branch","branch2");
		gitlet("reset","4");
		writeFile(hiFileName, "Aloha Yifan, my name is Leo. What?");
		gitlet("add",hiFileName);
		String commitMessage7 = "the seventh message";
		gitlet("commit", commitMessage7);
		gitlet("rebase","branch1");
		String logcontent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage7, commitMessage5, commitMessage4, commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logcontent));
		gitlet("checkout", "branch2");
		String logcontent2 = gitlet("log");
		assertArrayEquals(new String[] { commitMessage6, commitMessage5, commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logcontent2));
		assertEquals("Aloha Yifan, my name is Leo. Aloha Leo!", getText(hiFileName));
	}
	/**
	 * Test that changes in the base branch propagate through the replayed branch during a rebase.
	 */
	@Test
	public void testRebase3() {
		String hiFileName = TESTING_DIR + "test3.txt";
		String hiText = "Aloha";
		createFile(hiFileName, hiText);
		String helloFileName = TESTING_DIR + "test4.txt";
		String helloText = "Hallo";
		createFile(helloFileName, helloText);
		gitlet("init");
		String commitMessage1 = "initial commit";
		gitlet("add",hiFileName);
		gitlet("add",helloFileName);
		String commitMessage2 = "added test";
		gitlet("commit", commitMessage2);
		writeFile(hiFileName, "Aloha Yifan");
		gitlet("add",hiFileName);
		String commitMessage3 = "changed aloha";
		gitlet("commit", commitMessage3);
		gitlet("branch","branch1");
		gitlet("reset","1");
		writeFile(helloFileName, "Hallo Yifan");
		gitlet("add",helloFileName);
		String commitMessage4 = "changed Hallo";
		gitlet("commit", commitMessage4);
		gitlet("rebase","branch1");
		gitlet("checkout",hiFileName);
		gitlet("checkout", helloFileName);
		assertEquals("Aloha Yifan", getText(hiFileName));
		assertEquals("Hallo Yifan", getText(helloFileName));
	}
	/**
	 * Test that removal of a file in a current file is going to replay properly after the rebase.
	 */
	@Test
	public void testRebase4() {
		String hiFileName = TESTING_DIR + "test3.txt";
		String hiText = "Aloha";
		createFile(hiFileName, hiText);
		String helloFileName = TESTING_DIR + "test4.txt";
		String helloText = "Hallo";
		createFile(helloFileName, helloText);
		gitlet("init");
		String commitMessage1 = "initial commit";
		gitlet("add",hiFileName);
		gitlet("add",helloFileName);
		String commitMessage2 = "added test";
		gitlet("commit", commitMessage2);
		writeFile(hiFileName, "Aloha Yifan");
		gitlet("add",hiFileName);
		String commitMessage3 = "changed aloha";
		gitlet("commit", commitMessage3);
		gitlet("branch","branch1");
		gitlet("reset","1");
		gitlet("rm",helloFileName);
		String commitMessage4 = "removed hello";
		gitlet("commit", commitMessage4);
		gitlet("rebase","branch1");
		gitlet("checkout",helloFileName);
	}
	
	/**
	 * Test that merge will generate a .conflicted file if a file has been modified in both 
	 * branches since the split point. Also tests that the conflicted file has the right contents
	 * (the file contents of given branch), and that the working directory file has the right contents.
	 */
	@Test
	public void testMerge1() {
		String hiFileName = TESTING_DIR + "hi.txt";
		String hiText = "hi";
		createFile(hiFileName, hiText);
		gitlet("init");
		gitlet("add", hiFileName);
		gitlet("commit", "hi");
		gitlet("branch", "cool");
		writeFile(hiFileName, "hi.");
		gitlet("add", hiFileName);
		gitlet("commit", "hi.");
		gitlet("checkout", "cool");
		writeFile(hiFileName, "hi!");
		gitlet("add", hiFileName);
		gitlet("commit", "hi!");
		gitlet("merge", "master");
		assertEquals("hi.", getText(hiFileName + ".conflicted"));
		assertEquals("hi!", getText(hiFileName));
	}
	
	/**
	 * Test that merge will commit with files from the other branch if those files had 
	 * been modified in the other branch but not in the current branch since the split point.
	 */
	@Test
	public void testMerge2() {
		String hiFileName = TESTING_DIR + "hi.txt";
		String hiText = "hi";
		createFile(hiFileName, hiText);
		gitlet("init");
		gitlet("add", hiFileName);
		gitlet("commit", "hi");
		gitlet("branch", "cool");
		writeFile(hiFileName, "hi.");
		gitlet("add", hiFileName);
		gitlet("commit", "hi.");
		gitlet("checkout", "cool");
		gitlet("add", hiFileName);
		gitlet("commit", "same hi");
		gitlet("merge", "master");
		assertEquals("hi.", getText(hiFileName));
	}

	/**
	 * Calls a gitlet command using the terminal.
	 * 
	 * Warning: Gitlet will not print out anything _while_ it runs through this
	 * command, though it will print out things at the end of this command. It
	 * will also return this as a string.
	 * 
	 * The '...' syntax allows you to pass in an arbitrary number of String
	 * arguments, which are packaged into a String[].
	 */
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

	/**
	 * Another convenience method for calling Gitlet's main. This calls Gitlet's
	 * main directly, rather than through the terminal. This is slightly
	 * cheating the concept of end-to-end tests. But, this allows you to
	 * actually use the debugger during the tests, which you might find helpful.
	 * It's also a lot faster.
	 * 
	 * Warning: Like the other version of this method, Gitlet will not print out
	 * anything _while_ it runs through this command, though it will print out
	 * things at the end of this command. It will also return what it prints as
	 * a string.
	 */
	private static String gitletFast(String... args) {
		PrintStream originalOut = System.out;
		ByteArrayOutputStream printingResults = new ByteArrayOutputStream();
		try {
			/*
			 * Below we change System.out, so that when you call
			 * System.out.println(), it won't print to the screen, but will
			 * instead be added to the printingResults object.
			 */
			System.setOut(new PrintStream(printingResults));
			Gitlet.main(args);
		} finally {
			/*
			 * Restores System.out (So you can print normally).
			 */
			System.setOut(originalOut);
		}
		System.out.println(printingResults.toString());
		return printingResults.toString().trim();
	}

	/**
	 * Returns the text from a standard text file.
	 */
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
}