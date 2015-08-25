import static org.junit.Assert.*;

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

import org.junit.Test;
import org.junit.Before;

public class GitletTest {
	
	private static final String GITLET_DIR = ".gitlet" + File.separator;
	private static final String TESTING_DIR = "test_files" + File.separator;
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

	@Test
	public void testBasicBranch(){
		gitlet("init");
		String wugBranchName = "wug";
		gitlet("branch", wugBranchName);
		String statusContent = gitlet("status");
		assertArrayEquals(new String[] {"wug", "*master"}, extractBranchNames(statusContent));
	}
	
	@Test public void testBasicBranchCheckout(){
		gitlet("init");
		String wugBranchName = "wug";
		gitlet("branch", wugBranchName);
		gitlet("checkout", wugBranchName);
		String statusContent = gitlet("status");
		assertArrayEquals(new String[] {"*wug", "master"}, extractBranchNames(statusContent));
	}
	
	@Test public void testCollidedCheckout(){
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		String wugBranch = wugFileName;
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", wugBranch);
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "updated wug");
		gitlet("checkout", wugBranch);
		assertEquals(wugText, getText(wugFileName));
	}
	
//	@Test public void testCheckoutById(){
//		fail("not yet implemented");
//	}
//	
	@Test public void testFind(){
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "updated wug");
		writeFile(wugFileName, "This is a super wug");
		gitlet("add", wugFileName);
		gitlet("commit", "updated wug");
		String output = gitlet("find", "updated wug");
		String[] ids = output.split(LINE_SEPARATOR);
		assertArrayEquals(new String[] {"2","3"}, ids);
	}
	
	@Test public void testBasicMerge(){
		String wugFileName = TESTING_DIR + "wug.txt";
		String jugFileName = TESTING_DIR + "jug.txt";
		String wugText = "This is a wug.";
		String wugBranch = "wug";
		String jugText = "jug";
		createFile(wugFileName, wugText);
		createFile(jugFileName, jugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", wugBranch);
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "updated wug");
		writeFile(wugFileName, "This is a super wug");
		gitlet("add", wugFileName);
		gitlet("commit", "updated wug");
		gitlet("checkout", wugBranch);
		gitlet("add", jugFileName);
		gitlet("commit", "jug commit");
		gitlet("checkout", "master");
		gitlet("merge", wugBranch);
		assertEquals("This is a super wug", getText(wugFileName));
		assertEquals("jug", getText(jugFileName));
	}
	
	@Test public void testConflictedMerge(){
		String wugFileName = TESTING_DIR + "wug.txt";
		String jugFileName = TESTING_DIR + "jug.txt";
		String wugText = "This is a wug.";
		String wugBranch = "wug";
		String jugText = "jug";
		createFile(wugFileName, wugText);
		createFile(jugFileName, jugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", wugBranch);
		writeFile(wugFileName, "This is a super wug");
		gitlet("add", wugFileName);
		gitlet("commit", "updated wug");
		gitlet("add", jugFileName);
		gitlet("commit", "jug commit1");
		gitlet("checkout", wugBranch);
		gitlet("add", jugFileName);
		gitlet("commit", "jug commit");
		gitlet("checkout", "master");
		gitlet("merge", wugBranch);
		String conflictedFileName = TESTING_DIR + "jug.txt.conflicted";
		assertTrue(Files.exists(Paths.get(conflictedFileName)));
		String complaint= gitlet("checkout", wugBranch);
		assertEquals(complaint, "Cannot do this command until the merge conflict has been resolved.");
	}
	
	@Test public void testBasicRebase(){
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("branch", "wug");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("checkout", "wug");
		gitlet("rebase", "master");
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { "added wug", "initial commit" },
				extractCommitMessages(logContent));
	}
	
	@Test public void testFailCheckoutBranch(){
		
	}
	
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
	
	private static String[] extractBranchNames(String logOutput) {
		String[] logChunks = logOutput.split("===");
		String[] branchNames = logChunks[2].split(LINE_SEPARATOR);
		String [] proccessed = new String[branchNames.length-1];
		for (int i=0; i<proccessed.length; i++){
			proccessed[i] = branchNames[i+1];
		}
		return proccessed;
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
}
	
