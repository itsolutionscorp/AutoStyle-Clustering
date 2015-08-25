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
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

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
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */

	@Test
	public void testBasicInitialize() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IOException {
		gitlet("init");
		File f = new File(GITLET_DIR);
		assertTrue(f.exists());
	}

	/**
	 * Tests that checking out a file name will restore the version of the file
	 * from the previous commit. Involves init, add, commit, and checkout.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */

	@Test
	public void testBasicCheckout() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IOException {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", wugFileName);
		assertEquals(wugText, getText(wugFileName));
		gitlet("branch", "b1");
		
		String Anuraag = TESTING_DIR + "Anuraag.txt"; 
		String anuraagText = "Hello, I am Anuraag the Peasant"; 
		createFile(Anuraag, anuraagText); 
		String Albert = TESTING_DIR + "Albert.txt"; 
		String albertText = "Hello, my name is The Great Albert"; 
		createFile(Albert, albertText);
		gitlet("add", Anuraag); 
		gitlet("commit", "a");
		gitlet("branch", "b1"); 
		gitlet("checkout", "b1"); 
		gitlet("add", Albert);
		gitlet("commit", "b"); 
		gitlet("checkout", "master"); 
		writeFile(Albert, "What?! Albert is evolving... in to a wug!"); 
		gitlet("checkout", "b1");
		assertEquals(albertText, getText(Albert));   
	}

	/**
	 * Tests that log after one commit conforms to the format in the spec.
	 * Involves init, add, commit, and log.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */

	@Test
	public void testBasicLog() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IOException {
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
	
	@Test
	public void testBasicRebase() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IOException {
		gitlet("init");
		String bFile = TESTING_DIR + "bFile.txt";
		createFile(bFile, "b");
		gitlet("add", bFile);
		String aFile = TESTING_DIR + "aFile.txt";
		createFile(aFile, "a");
		gitlet("add", aFile);
		gitlet("commit", "1");
		gitlet("branch", "b1");

		writeFile(aFile, "edit");
		gitlet("add", aFile);
		gitlet("rm", bFile);
		gitlet("commit", "2");
		
		gitlet("checkout", "b1");
		String cFile = TESTING_DIR + "cFile.txt";
		createFile(cFile, "c");
		gitlet("add", cFile);
		gitlet("commit", "3");
		
		gitlet("rebase", "master");
		gitlet("global-log");
		String error = gitlet("checkout", "4", bFile);
		gitlet("checkout", "4", aFile);
		
		String logContent = gitlet("log");
		
		assertArrayEquals(new String[] { "3", "2", "1", "initial commit" },
				extractCommitMessages(logContent));
		assertEquals("edit", getText(aFile));
		assertEquals(error, "File does not exist in the most recent commit, or no such branch exists.");
	}
	
	@Test
	public void testBasicMerge() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IOException {
		gitlet("init");
		String aFile = TESTING_DIR + "aFile.txt";
		createFile(aFile, "a");
		gitlet("add", aFile);
		gitlet("commit", "1");
		gitlet("branch", "b1");
		
		writeFile(aFile, "conflicted");
		gitlet("add", aFile);
		gitlet("commit", "2");
		
		gitlet("checkout", "b1");
		writeFile(aFile, "edit");
		gitlet("add", aFile);
		gitlet("commit", "3");
		
		String error = gitlet("merge", "master");
		
		assertEquals(error, "Encountered a merge conflict.");
		File conflict = new File(aFile + ".conflicted");
		assertTrue(conflict.exists());
		assertEquals("conflicted", getText(conflict.getPath()));
	}
	
	
	@Test 
	public void testMerge() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IOException {
		gitlet("init");
		String aFile = TESTING_DIR + "cFile.txt";
		String bFile = TESTING_DIR + "dFile.txt";
		String cFile = TESTING_DIR + "wug.txt";
		createFile(aFile, "1");
		createFile(bFile, "1");
		createFile(cFile, "1");
		gitlet("add", aFile);
		gitlet("add", bFile);
		gitlet("add", cFile);
		gitlet("commit", "1");
		
		gitlet("branch", "mergeTest");
		gitlet("checkout", "mergeTest");
		writeFile(aFile, "2");
		writeFile(bFile, "2");
		gitlet("add", aFile);
		gitlet("add", bFile);
		gitlet("commit", "2");
		
		gitlet("checkout", "master");
		writeFile(aFile, "3");
		writeFile(cFile, "3");
		gitlet("add", aFile);
		gitlet("add", cFile);
		gitlet("commit", "3");
		
		String error = gitlet("merge", "mergeTest");
		gitlet("commit", "4");
		assertEquals(error, "Encountered a merge conflict.");
		assertEquals("2", getText(aFile + ".conflicted"));
		writeFile(bFile, "abc");
		
		gitlet("checkout", bFile);
		assertEquals("2", getText(bFile));
		writeFile(cFile, "abc");
		gitlet("checkout", cFile);
		assertEquals("3", getText(cFile));
	}
	
	@Test 
	public void testBasicMerge2() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IOException {
	
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText); 
		gitlet("init"); 
		gitlet("add", wugFileName); 
		gitlet("commit", "added wug"); 
		
		writeFile(wugFileName, "This is not a wug."); 
		gitlet("branch", "b1"); 
		gitlet("checkout", "b1"); 
		gitlet("add", wugFileName); 
		gitlet("commit", "Oops it's not a wug"); 
		
		gitlet("checkout", "master"); 
		writeFile(wugFileName, "This is a wug!"); 
		gitlet("add", wugFileName); 
		gitlet("commit", "Super sure of being a wug!"); 
	
		writeFile(wugFileName, "Are you sure this is a wug?"); 
		gitlet("add", wugFileName); 
		gitlet("commit", "skepticism"); 
		
		writeFile(wugFileName, "Yes I'm sure!"); 
		gitlet("add", wugFileName); 
		gitlet("commit", "reassurance"); 
	
		gitlet("merge", "b1"); 
	
		gitlet("log");
		File conflict = new File(wugFileName + ".conflicted");
		assertTrue(conflict.exists());
		System.out.println(getText(conflict.getPath()));
		assertEquals(getText(conflict.getPath()), "This is not a wug."); 
	}
	
	@Test
	public void testRebase() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IOException {
		gitlet("init");
		String cFile = TESTING_DIR + "cFile.txt";
		String dFile = TESTING_DIR + "dFile.txt";
		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(cFile, "c");
		createFile(dFile, "d");
		createFile(wugFileName, "wug");
		gitlet("add", cFile);
		gitlet("add", dFile);
		gitlet("add", wugFileName);
		gitlet("commit", "1");
		
		gitlet("branch", "rebaseTest");
		gitlet("checkout", "rebaseTest");
		writeFile(cFile, "edit");
		writeFile(dFile, "edit");
		writeFile(wugFileName, "edit");
		gitlet("add", cFile);
		gitlet("add", dFile);
		gitlet("add", wugFileName);
		gitlet("commit", "2");
		
		gitlet("checkout", "master");
		writeFile(cFile, "rebaseC");
		gitlet("add", cFile);
		gitlet("commit", "3");
		
		writeFile(dFile, "rebaseD");
		gitlet("add", dFile);
		gitlet("commit", "4"); 
		
		writeFile(wugFileName, "rebaseWug");
		gitlet("add", wugFileName);
		gitlet("commit", "5"); 
		
		gitlet("rebase", "rebaseTest");
		gitlet("checkout", "2", cFile);
		assertEquals("edit", getText(cFile));
		
		gitlet("checkout", "3", cFile);
		assertEquals("rebaseC", getText(cFile));
		gitlet("checkout", "3", dFile);
		assertEquals("edit", getText(dFile));
		gitlet("checkout", "3", wugFileName);
		assertEquals("edit", getText(wugFileName));
		
		gitlet("checkout", "4", cFile);
		assertEquals("rebaseC", getText(cFile));
		gitlet("checkout", "4", dFile);
		assertEquals("rebaseD", getText(dFile));
		gitlet("checkout", "4", wugFileName);
		assertEquals("edit", getText(wugFileName));
		
		gitlet("checkout", "5", cFile);
		assertEquals("rebaseC", getText(cFile));
		gitlet("checkout", "5", dFile);
		assertEquals("rebaseD", getText(dFile));
		gitlet("checkout", "5", wugFileName);
		assertEquals("rebaseWug", getText(wugFileName));
	}
	
	@Test
	public void testEverything() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IOException {
		gitlet("init");
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);//--------------------------------------------------------Commit 1

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
		
		writeFile(wugFileName, "edit");
		gitlet("branch", "b1");
		String aFile = TESTING_DIR + "a.txt";
		writeFile(aFile, "aaa");
		gitlet("add", aFile);
		gitlet("commit", "a");//-------------------------------------------------------------------Commit 2
		
		String noBranchError = gitlet("checkout", "b");
		assertEquals(noBranchError, "File does not exist in the most recent commit, or no such branch exists.");
		gitlet("checkout", "b1");
		assertEquals(wugText, getText(wugFileName));
		writeFile(aFile, "bbb");
		gitlet("add", aFile);
		gitlet("commit", "b");//-------------------------------------------------------------------Commit 3
		
		gitlet("checkout", "master");
		gitlet("status");
		assertEquals("aaa", getText(aFile));
		String bFile = TESTING_DIR + "b.txt";
		writeFile(bFile, "bbb");
		gitlet("add", bFile);
		gitlet("commit", "c");//-------------------------------------------------------------------Commit 4
		
		writeFile(wugFileName, "edit");
		gitlet("reset", "2");
		assertEquals(wugText, getText(wugFileName));
		logContent = gitlet("log");
		assertArrayEquals(new String[] { "a", commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
		
		gitlet("reset", "4");
		logContent = gitlet("log");
		assertArrayEquals(new String[] { "c", "a", commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
		writeFile(aFile, "aaa");
		gitlet("checkout", "3", aFile);
		assertEquals("bbb", getText(aFile));
		String error = gitlet("checkout", "4", aFile);
		assertEquals("aaa", getText(aFile));
		error = gitlet("rm", "date.java");
		assertEquals(error, "No reason to remove the file.");
		gitlet("rm", aFile);
		writeFile(aFile, "abc");
		String cFile = TESTING_DIR + "cFile";
		createFile(cFile, "c");
		gitlet("add", cFile);
		String dFile = TESTING_DIR + "dFile";
		createFile(dFile, "d");
		gitlet("commit", "d");//-------------------------------------------------------------------Commit 5
		
		error = gitlet("checkout", "5", aFile);
		assertEquals(error, "File does not exist in that commit.");
		error = gitlet("rebase", "master");
		assertEquals(error, "Cannot rebase a branch onto itself.");
		error = gitlet("rebase", "asdf");
		assertEquals(error, "A branch with that name does not exist.");
		gitlet("rebase", "b1");
		logContent = gitlet("log");
		assertArrayEquals(new String[] { "d", "c", "a", "b", commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
		gitlet("checkout", "4", aFile);
		assertEquals("aaa", getText(aFile));
		writeFile(aFile, "gt");
		gitlet("checkout", "6", bFile);
		assertEquals("bbb", getText(bFile));
		gitlet("branch", "b2");
		writeFile(cFile, "conflicted");
		gitlet("add", cFile);
		gitlet("rm", cFile);
		error = gitlet("commit", "e");
		assertEquals(error, "No changes added to the commit.");
		gitlet("add", cFile);
		writeFile(bFile, "edit");
		gitlet("add", bFile);
		gitlet("commit", "e");//-------------------------------------------------------------------Commit 6
		
		gitlet("checkout", "b2");
		writeFile(cFile, "abc");
		writeFile(dFile, "edit");
		gitlet("add", cFile);
		gitlet("add", dFile);
		gitlet("rm", aFile);
		gitlet("rm", bFile);
		gitlet("commit", "f");//-------------------------------------------------------------------Commit 10
		
		error = gitlet("merge", "master");
		System.out.println(getText(bFile));
		assertEquals("Encountered a merge conflict.", error);
		File conflicted = new File(cFile + ".conflicted");
		assertTrue(conflicted.exists());
		gitlet("status");
		error = gitlet("branch", "b3");
		gitlet("commit", "not conflicted anymore");
		assertEquals("Cannot do this command until the merge conflict has been resolved.", error);
		error = gitlet("checkout", bFile);
		assertEquals(error, "File does not exist in the most recent commit, or no such branch exists.");
		gitlet("checkout", dFile);
		assertEquals("edit", getText(dFile));
		writeFile(cFile, "cba");
		writeFile(dFile, "d");
		gitlet("add", cFile);
		gitlet("add", dFile);
		gitlet("commit", "g");//-------------------------------------------------------------------Commit 11
		
		gitlet("reset", "8");
		gitlet("branch", "b3");
		gitlet("reset", "10");
		gitlet("log");
		gitlet("merge", "b3");
		gitlet("checkout", cFile);
		gitlet("checkout", dFile);
		assertEquals("edit", getText(dFile));
		assertEquals("abc", getText(cFile));
		gitlet("checkout", "10", cFile);
		gitlet("checkout", "10", dFile);
		assertEquals("conflicted", getText(cFile + ".conflicted"));
		assertEquals("abc", getText(cFile));
		assertEquals("edit", getText(dFile));
		
		gitlet("branch", "rebaseTest");
		gitlet("checkout", "rebaseTest");
		writeFile(cFile, "edit");
		writeFile(dFile, "edit");
		writeFile(wugFileName, "edit");
		gitlet("add", cFile);
		gitlet("add", dFile);
		gitlet("add", wugFileName);
		gitlet("commit", "rebase");//commit 12
		
		gitlet("checkout", "b2");
		writeFile(cFile, "rebaseC");
		gitlet("add", cFile);
		gitlet("commit", "h"); //13
		
		writeFile(dFile, "rebaseD");
		gitlet("add", dFile);
		gitlet("commit", "i"); //14
		
		writeFile(wugFileName, "rebaseWug");
		gitlet("add", wugFileName);
		gitlet("commit", "j"); //15
		
		gitlet("rebase", "rebaseTest");
		gitlet("checkout", "14", cFile);
		assertEquals("edit", getText(cFile));
		
		gitlet("checkout", "18", cFile);
		assertEquals("rebaseC", getText(cFile));
		gitlet("checkout", "18", dFile);
		assertEquals("edit", getText(dFile));
		gitlet("checkout", "18", wugFileName);
		assertEquals("edit", getText(wugFileName));
		
		gitlet("checkout", "19", cFile);
		assertEquals("rebaseC", getText(cFile));
		gitlet("checkout", "19", dFile);
		assertEquals("rebaseD", getText(dFile));
		gitlet("checkout", "19", wugFileName);
		assertEquals("edit", getText(wugFileName));
		
		gitlet("checkout", "20", cFile);
		assertEquals("rebaseC", getText(cFile));
		gitlet("checkout", "20", dFile);
		assertEquals("rebaseD", getText(dFile));
		gitlet("checkout", "20", wugFileName);
		assertEquals("rebaseWug", getText(wugFileName));
	}
	
	@Test
	public void testBasicRm() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IOException {
		gitlet("init");
		File stage = new File(".gitlet/stage/");
		String Albert = TESTING_DIR + "Albert.txt"; 
		String albertText = "Hello, my name is The Great Albert"; 
		createFile(Albert, albertText); 
		gitlet("add", Albert);
		File staged = new File(".gitlet/stage/" + Albert);
		assertTrue(staged.exists());
		gitlet("rm", Albert);
		assertTrue(!staged.exists()); 
		gitlet("rm", Albert); 
		String test_error = gitlet("rm", Albert); 
		assertEquals(test_error, "No reason to remove the file."); 
	}
	
	@Test
	public void testBasicBranch() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IOException {
		gitlet("init"); 
		String Anuraag = TESTING_DIR + "Anuraag.txt"; 
		String anuraagText = "Hello, I am Anuraag the Peasant"; 
		createFile(Anuraag, anuraagText); 
		String Albert = TESTING_DIR + "Albert.txt"; 
		String albertText = "Hello, my name is The Great Albert"; 
		createFile(Albert, albertText);
		gitlet("add", Anuraag); 
		gitlet("commit", "a");
		gitlet("branch", "b1"); 
		gitlet("checkout", "b1"); 
		gitlet("add", Albert);
		gitlet("commit", "b"); 
		String test = gitlet("status"); 
		assertEquals(test, "=== Branches ===" +System.lineSeparator() + "*b1"+System.lineSeparator() +"master" +System.lineSeparator() +System.lineSeparator() +"=== Staged Files ===" 
		 + System.lineSeparator() +System.lineSeparator() + "=== Files Marked for Untracking ===");
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
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	private static String gitletFast(String... args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IOException {
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