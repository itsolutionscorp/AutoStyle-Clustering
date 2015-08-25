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

	@Test
	public void testInitialize(){
		gitlet("init");
		File f = new File(GITLET_DIR + "/Commit_0");
		assertTrue(f.exists());
		//gitlet("init");
	}
	
	
	
	@Test
	public void testAddRemoveSimple(){
		//Start testing Add
		
		gitlet("init");		
		gitlet("add", "fake.txt");
		File test = new File("test.txt");
		try{
			test.createNewFile();
		} catch (IOException e){
			System.out.println("IOException");
		}
		assertTrue(test.exists());
		gitlet("add", "test.txt");
		File f = new File(GITLET_DIR + "/Staging/test.txt");
		File fake = new File(GITLET_DIR + "/Staging/fake.txt");
		assertTrue(f.exists()); 								//File added to Staging directory
		assertTrue(!fake.exists());								//File that doesn't exist is not added
		
		gitlet("add", "test.txt");
		assertTrue(f.exists()); 								//Still in staging directory
		
		
		//Start testing Remove
		
		gitlet("rm", "test.txt");
		assertTrue(!f.exists());								//File no longer in Staging dirctory
	}
	
	@Test
	public void testCommit(){
		gitlet("init");		
		File test = new File("test.txt");
		File hi = new File("hi.txt");
		File bye = new File("bye.txt");
		try{
			test.createNewFile();
			hi.createNewFile();
			bye.createNewFile();
		} catch (IOException e){
			System.out.println("IOException");
		}
		gitlet("add", "test.txt");
		gitlet("commit", "aaa");
		File c = new File(GITLET_DIR + "/Commit_1");
		File cf = new File(GITLET_DIR + "/Commit_1/test.txt");
		assertTrue(c.exists());									//Create Commit_1 folder
		assertTrue(cf.exists());								//Create file in that folder
		
		gitlet("add", "hi.txt"); 
		gitlet("rm", "hi.txt");									//add to staging area then remove
		gitlet("commit", "bbb");
		File c2 = new File(GITLET_DIR + "/Commit_2");
		File cf2 = new File(GITLET_DIR + "/Commit_2/hi.txt");
		assertTrue(!c2.exists());								//hi.txt shouldn't have been in staging, so no commit should have me made 
		assertTrue(!cf2.exists());
		
		gitlet("rm", "test.txt"); 								
		gitlet("add", "test.txt");								//untrack then track test, so it should never go into staging area
		gitlet("commit", "ccc");
		File c3 = new File(GITLET_DIR + "/Commit_2");
		File cf3 = new File(GITLET_DIR + "/Commit_2/test.txt");
		assertTrue(!c3.exists());								//hi.txt shouldn't have been in staging, so no commit should have me made 
		assertTrue(!cf3.exists());
		
		gitlet("add", "bye.txt");
		gitlet("rm", "test.txt");
		gitlet("commit", "ddd");
		File c4 = new File(GITLET_DIR + "/Commit_2");
		File cf4 = new File(GITLET_DIR + "/Commit_2/bye.txt");
		assertTrue(c4.exists());
		assertTrue(cf4.exists());								//Finally create Commit_2
		//make sure test.txt is not being tracked!
		
		
		gitlet("add", "test.txt");								//This should actually be coppied into staging area since the untrack hashMap gets emptied after the next commit
		gitlet("commit", "eee");
		File c5 = new File(GITLET_DIR + "/Commit_3");
		File cf5 = new File(GITLET_DIR + "/Commit_3/test.txt");
		assertTrue(c5.exists());
		assertTrue(cf5.exists());								//test should be added to this commit
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
		
		//additional checks
		//Check that committing the edited file and checking it out again will update the file in the working directory
		
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "editted ");
		System.out.println("before checkout");
		gitlet("checkout", wugFileName);
		System.out.println("after checkout");
		String newWugText = "This is not a wug.";
		assertEquals(newWugText, getText(wugFileName));
		
		
		//test checkoutID
		writeFile(wugFileName, wugText);
		
		gitlet("checkout", "2", wugFileName);
		assertEquals(newWugText, getText(wugFileName));
		
		gitlet("checkout", "1", wugFileName);
		assertEquals(wugText, getText(wugFileName));			//ISSUE
		
		//delete wugFileName from the testing directory, then call checkout

	}
	
	
	@Test
	public void testRebase(){
		gitlet("init");
		String wugFileName = "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "commit message");
		gitlet("branch", "branch2");
		
		String hajiFileName = "haji.txt";
		String hajiText = "This is a haji.";
		createFile(hajiFileName, hajiText);
		gitlet("add", hajiFileName);
		gitlet("commit", "haji commit message");
		
		gitlet("checkout", "branch2");
		String newWugText = "This is a haji wug";
		writeFile(wugFileName, newWugText);
		gitlet("add", wugFileName);
		gitlet("commit", "haji wug");
		
		gitlet("rebase", "master");
		assertEquals(getText(".gitlet/Commit_4/" + wugFileName), "This is a haji wug");
		
		File commit4Dir = new File(".gitlet/Commit_4/"+ wugFileName);
		assertTrue(commit4Dir.exists());
		
//		assertEquals()
		
	}
	
	@Test
	public void testRebase2(){
		gitlet("init");
		String wugFileName = "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "commit message");
		gitlet("branch", "branch2");
		
		String hajiFileName = TESTING_DIR + "haji.txt";
		String hajiText = "This is a haji.";
		createFile(hajiFileName, hajiText);
		gitlet("add", hajiFileName);
		gitlet("commit", "haji commit message");
		
		gitlet("checkout", "branch2");
		String newWugText = "This is a haji wug";
		writeFile(wugFileName, newWugText);
		gitlet("add", wugFileName);
		gitlet("commit", "haji wug");
		
		gitlet("checkout", "master");
		gitlet("rebase", "branch2");
		File commit4Dir = new File(".gitlet/Commit_4/"+ wugFileName);
		assertTrue(commit4Dir.exists());
		assertEquals(getText(".gitlet/Commit_4/" + wugFileName), "This is a haji wug");	
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testCheckoutBranch(){
		//checkout branch with one file 
		gitlet("init");
		String testFileName = TESTING_DIR + "test.txt";
		String testText = "hihihihihi";
		createFile(testFileName, testText);
		gitlet("add", testFileName);
		gitlet("commit", "aaa");
		writeFile(testFileName, "byebyebye");
		assertEquals("byebyebye",getText(testFileName));
		gitlet("branch", "newBranch");
		gitlet("checkout", "newBranch");
		
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "im a wug";
		createFile(wugFileName, wugText);
		String cowFileName = TESTING_DIR + "cow.txt";
		String cowText = "im a cow";
		createFile(cowFileName, cowText);
		gitlet("add", cowFileName);
		gitlet("add", wugFileName);
		
	
		writeFile(cowFileName, "im not a cow");
		writeFile(wugFileName, "im not a wug");

		
		//master doesn't have the files cow and wug, so it should not restore the wug and cow files in the working directory
		gitlet("commit", "bbb");
		gitlet("checkout", "master");
		assertEquals("im not a cow",getText(cowFileName));
		assertEquals("im not a wug",getText(wugFileName));
		
		gitlet("checkout", "newBranch");
		
		//restore the wug and cow files in the working directory
		assertEquals("im a cow",getText(cowFileName));
		assertEquals("im a wug",getText(wugFileName));


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
		
		String hajFileName = TESTING_DIR + "haj.txt";
		String hajText = "This is a haj.";
		createFile(hajFileName, hajText);	
		gitlet("add", hajFileName);
		String commitMessage3 = "'added haj'";
		gitlet("commit", commitMessage3);
		logContent = gitlet("log");
		assertArrayEquals(new String[] {commitMessage3, commitMessage2, commitMessage1},
				extractCommitMessages(logContent));		
	}
	
	@Test
	public void testReset(){
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		
		String newWugText = "This is not a wug.";
		writeFile(wugFileName, newWugText);
		gitlet("add", wugFileName);
		gitlet("commit", "editted ");
		gitlet("reset", "2");
		assertEquals(newWugText,getText(wugFileName));
		gitlet("reset", "1");
		assertEquals(wugText,getText(wugFileName));
		
		
		
		
	}
	
	
//	@Test
	public void testMergeSimpleCase() {
	gitlet("init");

	String wugFileName = TESTING_DIR + "wug.txt";
	String wugText = "This is a fat wug.";

	createFile(wugFileName, wugText);

	gitlet("add", wugFileName);

	String commitMessage1 = "added wug";
	gitlet("commit", commitMessage1);

	gitlet("branch", "branch2");

	String elephantFileName= TESTING_DIR + "elephant.txt";
	String elephantText = "This is an elephant.";

	createFile(elephantFileName, elephantText);

	gitlet("add", elephantFileName);

	gitlet("commit", "Elephant and original wug");

	gitlet("checkout", "branch2");

	writeFile(wugFileName, "This is not a skinny wug.");

	gitlet("add", wugFileName);

	gitlet("commit", "new wug");

	gitlet("checkout", "master");

	writeFile(wugFileName, "This is a fat wug.");

	gitlet("merge", "branch2");

	//The file should have been updated in the working directory and in the staging directory
	assertEquals("This is not a skinny wug.", getText(wugFileName));
	assertEquals("This is not a skinny wug.", getText(".gitlet/Staging/" + wugFileName));

	}


	public void testMergeSimpleCase2(){
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a fat wug.";

		createFile(wugFileName, wugText);

		gitlet("add", wugFileName);

		String commitMessage1 = "added wug";
		gitlet("commit", commitMessage1);

		gitlet("branch", "branch2");

		String elephantFileName= TESTING_DIR + "elephant.txt";
		String elephantText = "This is an elephant.";

		writeFile(wugFileName, "This is not a skinny wug.");
		
		createFile(elephantFileName, elephantText);

		gitlet("add", elephantFileName);
		gitlet("add", wugFileName);
		
		gitlet("commit", "Elephant and different wug");

		gitlet("checkout", "branch2");

		writeFile(wugFileName, "This is a fat wug.");

		gitlet("add", wugFileName);

		gitlet("commit", "original wug");

		gitlet("checkout", "master");

		gitlet("merge", "branch2");

		
		
		//The file should have been updated in the working directory and in the staging directory
		assertEquals("This is not a skinny wug.", getText(wugFileName));
		assertEquals("This is not a skinny wug.", getText(".gitlet/Staging/" + wugFileName));

		
	}



	@Test
	public void testMergeConflictCase() {
	gitlet("init");

	String wugFileName = TESTING_DIR + "wug.txt";
	String wugText = "This is a fat wug.";

	createFile(wugFileName, wugText);

	String wug2FileName = TESTING_DIR + "wug.txt";
	String wug2Text = "This is an obese wug.";
	createFile(wug2FileName, wug2Text);

	gitlet("add", wugFileName);
	gitlet("add", wug2FileName);

	String commitMessage1 = "added wug";
	gitlet("commit", commitMessage1);

	gitlet("branch", "branch2");

	gitlet("checkout", "branch2");


	writeFile(wug2FileName, "This is a hella obsese wug."); 

	gitlet("add", wug2FileName);

	writeFile(wugFileName, "this changed");

	gitlet("add", wugFileName);

	gitlet("commit", "changed the wug file");

	gitlet("checkout", "master");

	gitlet("add", wug2FileName);

	writeFile(wugFileName, "this is different also");

	gitlet("add", wugFileName);

	gitlet("commit", "changed the wug file here as well");

	gitlet("merge", "branch2");

	//At this point it should be in a conflicted state. Should find some way to check this. 

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
	 */
	private static String gitletFast(String... args){
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