package proj2;

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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Class that provides JUnit tests for Gitlet, as well as a couple of utility
 * methods.
 * 
 * 
 */
public class GitletTest
{
	private static final String	GITLET_DIR		= ".gitlet/";
	private static final String	TESTING_DIR		= "test_files/";
	private static final String	COMMIT_DIR		= ".gitlet/commits/";
	private static final String	STAGING_DIR		= ".gitlet/staging/";

	/* matches either unix/mac or windows line separators */
	private static final String	LINE_SEPARATOR	= "\r\n|[\r\n]";

	/**
	 * Deletes existing gitlet system, resets the folder that stores files used
	 * in testing.
	 * 
	 * This method runs before every @Test method. This is important to enforce
	 * that all tests are independent and do not interact with one another.
	 */
	@Before
	public void setUp()
	{
		File f = new File(GITLET_DIR);
		if (f.exists())
		{
			try
			{
				recursiveDelete(f);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		f = new File(TESTING_DIR);
		if (f.exists())
		{
			try
			{
				recursiveDelete(f);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		f.mkdirs();
	}

	private final ByteArrayOutputStream	outContent	= new ByteArrayOutputStream();

	@Before
	public void setUpStreams()
	{
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams()
	{
		System.setOut(null);
	}

	/**
	 * Tests that init creates a .gitlet directory. Does NOT test that init
	 * creates an initial commit, which is the other functionality of init.
	 */
	@Test
	public void testBasicInitialize()
	{
		gitlet("init");
		File f = new File(GITLET_DIR);
		assertTrue(f.exists());

		assertTrue(new File(COMMIT_DIR + 0).exists());

		gitlet("init");
		assertEquals("A gitlet version control system already exists in the current directory.", outContent.toString().trim());
	}

	/**
	 * Tests that checking out a file name will restore the version of the file
	 * from the previous commit. Involves init, add, commit, and checkout.
	 */
	@Test
	public void testBasicCheckout()
	{
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
	 * Tests that log after one commit conforms to the format in the spec.
	 * Involves init, add, commit, and log.
	 */
	@Test
	public void testBasicLog()
	{
		gitlet("init");
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 }, extractCommitMessages(logContent));
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
	private static String gitlet(String... args)
	{

		String[] commandLineArgs = new String[args.length + 2];
		commandLineArgs[0] = "java";
		commandLineArgs[1] = "Gitlet";
		for (int i = 0; i < args.length; i++)
		{
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
	 * 
	 * @throws IOException
	 */
	private static String gitletFast(String... args) throws IOException
	{
		PrintStream originalOut = System.out;
		ByteArrayOutputStream printingResults = new ByteArrayOutputStream();
		try
		{
			/*
			 * Below we change System.out, so that when you call
			 * System.out.println(), it won't print to the screen, but will
			 * instead be added to the printingResults object.
			 */
			System.setOut(new PrintStream(printingResults));
			Gitlet.main(args);
		}
		finally
		{
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
	public static String getText(String fileName)
	{
		try
		{
			byte[] encoded = Files.readAllBytes(Paths.get(fileName));
			return new String(encoded, StandardCharsets.UTF_8);
		}
		catch (IOException e)
		{
			return "";
		}
	}

	/**
	 * Creates a new file with the given fileName and gives it the text
	 * fileText.
	 */
	public static void createFile(String fileName, String fileText)
	{
		File f = new File(fileName);
		if (!f.exists())
		{
			try
			{
				f.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		writeFile(fileName, fileText);
	}

	/**
	 * Replaces all text in the existing file with the given text.
	 */
	public static void writeFile(String fileName, String fileText)
	{
		FileWriter fw = null;
		try
		{
			File f = new File(fileName);
			fw = new FileWriter(f, false);
			fw.write(fileText);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fw.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Deletes the file and all files inside it, if it is a directory.
	 * 
	 * @throws IOException
	 */
	public static void recursiveDelete(File d) throws IOException
	{
		if (d.isDirectory())
		{
			for (File f : d.listFiles())
			{
				recursiveDelete(f);
			}
		}
		if (!d.delete())
		{
			throw new IOException("Failed to delete file " + d.getPath());
		}
	}

	/**
	 * Returns an array of commit messages associated with what log has printed
	 * out.
	 */
	private static String[] extractCommitMessages(String logOutput)
	{
		String[] logChunks = logOutput.split("===");
		int numMessages = logChunks.length - 1;
		String[] messages = new String[numMessages];
		for (int i = 0; i < numMessages; i++)
		{
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
	private static String command(String... args)
	{
		try
		{
			StringBuilder results = new StringBuilder();
			Process p = Runtime.getRuntime().exec(args);
			p.waitFor();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));)
			{
				String line = null;
				while ((line = br.readLine()) != null)
				{
					results.append(line).append(System.lineSeparator());
				}
				return results.toString();
			}
		}
		catch (IOException e)
		{
			return e.getMessage();
		}
		catch (InterruptedException e)
		{
			return e.getMessage();
		}
	}

	/**
	 * Tests commit functionalities and error cases. Makes sure the staging area
	 * is empty after a commit.
	 */
	@Test
	public void testCommit(){
		String fileName = TESTING_DIR + "test.txt";
		String testFile = "Hello world, testing Gitlet";
		createFile(fileName, testFile);
		gitlet("init");
		gitlet("add", fileName);
		
		outContent.reset();
		gitlet("commit");
		assertEquals(outContent.toString().trim(), "Please enter a commit message.");
		
		gitlet("commit", "added test.txt");
		File f = new File(GITLET_DIR + "commits/1/");
		assertTrue(f.exists());
		
		File staging = new File(STAGING_DIR);
		assertEquals(staging.list().length, 0);
		
		outContent.reset();
		gitlet("commit", "shouldn't work");
		assertEquals(outContent.toString().trim(), "No changes added to the commit.");
	}
	
	@Test
	public void testFileSystem2()
	{
		Gitlet gitlet = new Gitlet();
		String firstCommit = "commit 1";
		String commitText = "This is the first commit";
		String commitFileName = "first_commit.txt";
		createFile(commitFileName, commitText);
		gitlet.add(commitFileName);
		gitlet.commit(firstCommit);
		String commit2Text = "This is the second commit";
		writeFile("first_commit.txt", commit2Text);
		gitlet.add("first_commit.txt");
		gitlet.commit("commit 2");
		String commit3FileName = "second_commit.txt";
		String commit3Text = "This is the third commit";
		createFile(commit3FileName, commit3Text);
		gitlet.add(commit3FileName);
		gitlet.commit("commit 3");
		assertTrue(gitlet.getBranches().containsKey("master"));
		assertEquals(gitlet.getBranches().get("master").getMessage(), "commit 3");
		//assertEquals(gitlet.getBranches().get("master").getPrevCommit().getMessage(), "commit 2");
		assertEquals(gitlet.getBranches().get("master").getPrevCommit().getPrevCommit().getMessage(), "commit 1");
		assertTrue(gitlet.getBranches().get("master").getFile("second_commit.txt").exists());
		assertTrue(gitlet.getBranches().get("master").getFile("first_commit.txt").exists());
		assertEquals(commit2Text, getText(gitlet.getBranches().get("master").getFile("first_commit.txt").getPath()));
	}

	@Test
	public void testBranch() throws IOException
	{
		Gitlet gitlet = new Gitlet();
		// create files
		createFile("first_commit.txt", "This is the first commit");
		createFile("second_commit.txt", "This is the second commit");
		createFile("third_commit.txt", "This is the third commit");

		gitlet.add("first_commit.txt");
		gitlet.commit("commit 1");
		gitlet.branch("branch");

		assertEquals(gitlet.getCurrentBranch(), "master");
		assertTrue(gitlet.getBranches().containsKey("branch"));
		assertEquals(gitlet.getBranches().get("master"), gitlet.getBranches().get("branch"));

		gitlet.checkout("master");
		assertEquals("No need to checkout the current branch.\n", outContent.toString());
		outContent.reset();

		gitlet.checkout("branch");
		assertEquals(gitlet.getCurrentBranch(), "branch");
		assertTrue(new File("first_commit.txt").exists());

		gitlet.add("second_commit.txt");
		gitlet.commit("commit 2");
		gitlet.checkout("second_commit.txt");
		assertTrue(new File("second_commit.txt").exists());

		gitlet.checkout("master");
		assertEquals(gitlet.getCurrentBranch(), "master");
		gitlet.add("third_commit.txt");
		gitlet.commit("commit 3");
		outContent.reset();
		gitlet.checkout("second_commit.txt");
		assertEquals("File does not exist in the most recent commit, or no such branch exists.\n", outContent.toString());
		outContent.reset();
		gitlet.checkout("third_commit.txt");
		assertEquals("", outContent.toString());
		assertTrue(new File("third_commit.txt").exists());
		outContent.reset();
		gitlet.log();
		assertArrayEquals(extractCommitMessages(outContent.toString()), new String[] { "commit 3", "commit 1", "initial commit" });
	}

	@Test
	public void testCopy()
	{
		createFile(TESTING_DIR + "a.txt", "a");
		File file = new File(TESTING_DIR + "/folder");
		file.mkdir();
		createFile(TESTING_DIR + "/folder/a.txt", "b");
		assertTrue(new File(TESTING_DIR + "a.txt").exists());
		assertTrue(file.exists() && file.isDirectory());
		// assertEquals(new File(TESTING_DIR +
		// "/folder/a.txt").getPath().toString(), TESTING_DIR + "/folder");
		try
		{
			Files.copy(new File(file, "a.txt").toPath(), new File(STAGING_DIR + "/folder/a.txt").toPath());
			Files.copy(new File(TESTING_DIR, "a.txt").toPath(), new File(STAGING_DIR, "a.txt").toPath());
			assertTrue(new File(STAGING_DIR + "/folder/a.txt").exists());
			assertTrue(new File(STAGING_DIR, "a.txt").exists());
			assertEquals("b", getText(STAGING_DIR + "/folder/a.txt"));
			assertEquals("a", getText(STAGING_DIR + "a.txt"));
		}
		catch (IOException e)
		{
			// e.printStackTrace();
		}
	}

	@Test
	public void testFileSystem()
	{
		Gitlet gitlet = new Gitlet();
		gitlet.add("a");
		gitlet.commit("added a");
		gitlet.add("nest1/a");
		gitlet.commit("added nest1");
		gitlet.add("nest1/nest2/nest3/nest4/a");
		gitlet.commit("added nest4");
		try
		{
			gitlet.checkout("2", "a");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test3()
	{
		createFile("a", "1");
		createFile("nest1/a", "2");
		createFile("nest1/nest2/nest3/nest4/a", "3");
		Gitlet gitlet = new Gitlet();
		gitlet.add("a");
		gitlet.commit("first a");
		gitlet.add("nest1/a");
		gitlet.add("nest1/nest2/nest3/nest4/a");
		gitlet.commit("nest1 nest4 a");
		assertTrue(new File(".gitlet/commits/2/nest1/a").exists());
		writeFile("a", "changed no nest");
		writeFile("nest1/a", "changed nest 1");
		writeFile("nest1/nest2/nest3/nest4/a", "changed nest 4");
		gitlet.add("a");
		gitlet.add("nest1/a");
		gitlet.add("nest1/nest2/nest3/nest4/a");
		gitlet.commit("changed everything");
		try
		{
			gitlet.checkout("2", "a");
			gitlet.checkout("2", "nest1/a");
			gitlet.checkout("2", "nest1/nest2/nest3/nest4/a");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		assertEquals("1", getText("a"));
		assertEquals("2", getText("nest1/a"));
		assertEquals("3", getText("nest1/nest2/nest3/nest4/a"));
	}

	@Test
	public void testMerge() throws IOException
	{
		gitletFast("init");
		createFile("a", "1");
		createFile("nest1/a", "2");
		createFile("nest1/nest2/nest3/nest4/a", "3");
		gitletFast("branch", "branch");
		gitletFast("add", "a");
		gitletFast("add", "nest1/a");
		gitletFast("commit", "commit 1");
		gitletFast("checkout", "branch");
		writeFile("a", "changed a");
		gitletFast("add", "a");
		gitletFast("add", "nest1/nest2/nest3/nest4/a");
		gitletFast("commit", "commit 2");
		gitletFast("merge", "master");
		gitletFast("commit", "commit3");
		assertTrue(new File(".gitlet/commits/3/nest1/a").exists());
		assertTrue(new File(".gitlet/commits/3/a.conflicting").exists());
		assertEquals("1", getText(".gitlet/commits/3/a.conflicting"));
	}

	public void testRebase() throws IOException
	{
		gitletFast("init");
		createFile("a", "1");
		createFile("nest1/a", "2");
		createFile("nest1/nest2/nest3/nest4/a", "3");
		createFile("b", "4");
		gitletFast("branch", "branch");
		gitletFast("add", "a");
		gitletFast("add", "b");
		gitletFast("commit", "commit 1 master");
		gitletFast("add", "nest1/a");
		gitletFast("commit", "commit 2 master");
		gitletFast("checkout", "branch");
		writeFile("nest1/a", "changed nest 1");
		gitletFast("add", "nest1/a");
		gitletFast("commit", "commit 1 branch");
		writeFile("a", "changed a");
		gitletFast("add", "nest1/nest2/nest3/nest4/a");
		gitletFast("commit", "commit 2 branch");
		gitletFast("add", "nest1/a");
		gitletFast("commit", "commit 3 branch");
		gitletFast("rebase", "master");
	}
	@Test
	public void testRebase2() throws IOException
	{
		gitletFast("init");
		String firstCommit = "commit 1";
		String commitText = "This is the first commit";
		String commitFileName = "first_commit.txt";
		createFile(commitFileName, commitText);
		String randomFile = "random.txt";
		createFile(randomFile, "this is random");
		gitletFast("add", randomFile);
		gitletFast("add", commitFileName);
		gitletFast("commit", firstCommit);

		assertEquals("", gitletFast("branch", "branch"));

		writeFile(commitFileName, "first commit revised");
		gitletFast("add", commitFileName);

		gitletFast("commit", "commit 2 text modified and random added");

		assertEquals("", gitletFast("checkout", "branch"));

		String thirdCommit = "commit 3";
		String commit3Text = "This is the third commit";
		String commit3FileName = "third_commit.txt";
		createFile(commit3FileName, commit3Text);

		gitletFast("add", commit3FileName);
		gitletFast("commit", thirdCommit);

		String randomFile2 = "random2.txt";
		createFile(randomFile2, "this is random #2");
		gitletFast("add", randomFile2);
		gitletFast("commit", "commit# 4 random2.txt added");

		assertEquals("", gitletFast("rebase", "master"));
		assertTrue(new File(COMMIT_DIR, "5/third_commit.txt").exists());
	}
	@Test
	public void testRemove() throws IOException{
		String fileName = TESTING_DIR + "test.txt";
		String testFile = "Hello world, testing Gitlet";
		createFile(fileName, testFile);
		gitletFast("init");
		
		outContent.reset();
		gitletFast("rm", "test.txt");
		assertEquals(outContent.toString().trim(), "No reason to remove the file.");
		
		File f = new File(GITLET_DIR + "staging/" + fileName);
		gitletFast("add", fileName);
		assertTrue(f.exists());
		gitletFast("rm", fileName);
		assertFalse(f.exists());
		gitletFast("add", fileName);
		gitletFast("commit", "added test.txt");
		f = new File(GITLET_DIR + "commits/1/test_files/test.txt");
		assertTrue(f.exists());
		gitletFast("rm", fileName);
		gitletFast("commit", "removed test.txt");
		File commit2 = new File(GITLET_DIR + "commits/2");
		assertEquals(commit2.listFiles().length, 0);
		assertTrue(f.exists());
	}
	
	/**
	 * Tests add, making sure that the file is added to the staging folder. Also checks if an 
	 * error message is printed if there is no file with the name
	 */
	@Test
	public void testAdd(){
		String fileName = TESTING_DIR + "test.txt";
		String testFile = "Hello world, testing Gitlet";
		createFile(fileName, testFile);
		gitlet("init");
		gitlet("add", fileName);
		File f = new File(GITLET_DIR + "staging/" + fileName);
		assertTrue(f.exists());
		
		outContent.reset();
		gitlet("add", "doesnotexist.txt");
		assertEquals(outContent.toString().trim(), "File does not exist.");
	}
}