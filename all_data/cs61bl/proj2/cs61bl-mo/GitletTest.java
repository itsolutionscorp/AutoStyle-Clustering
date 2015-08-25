import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GitletTest {

	/**
	 * Created by Joseph Moghadam. Deletes current gitlet system before each
	 * test.
	 * 
	 */
	@Before
	public void setUp() {
		File f = new File(".gitlet");
		if (f.exists()) {
			recursiveDelete(f);
		}
	}

	/**
	 * Created by Joseph Moghadam. Deletes the file and all files inside it, if
	 * it is a directory.
	 * 
	 */
	private static void recursiveDelete(File d) {
		if (d.isDirectory()) {
			for (File f : d.listFiles()) {
				recursiveDelete(f);
			}
		}
		d.delete();
	}

	/**
	 * setUpStream and cleanUpStream were found on site below. Used to test
	 * output of certain methods. *
	 * 
	 * http://stackoverflow.com/questions/1119385/junit-test-for-system-out-
	 * println *
	 * 
	 */
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	/**
	 * Test that init can only be called once, and that a nonexistent file
	 * cannot be added.
	 */
	@Test
	public void testInitAdd() {
		this.setUp();
		this.cleanUpStreams();
		this.setUpStreams();
		Gitlet.init();
		
		Gitlet.init();
		assertTrue(outContent.toString().equals("A gitlet version control system already "
							+ "exists in the current directory." + "\r\n"));

		File staging = new File(".gitlet/staging");
		assertTrue(staging.exists());

		Gitlet.add("This.txt");

		File thisFile = new File(".gitlet/staging/This.txt");
		assertTrue(thisFile.exists());

		Gitlet.commit("added this");
		assertTrue(staging.exists());
	}
	
	/**
	 * Test all failure cases for add.
	 */
	@Test
	public void testAdd() {
		this.setUp();
		Gitlet.init();
		Gitlet.add("nothing.txt");

		assertEquals(outContent.toString(), "File does not exist." + "\r\n");
		
		File noFile = new File(".gitlet/staging/nothing.txt");
		assertFalse(noFile.exists());
	}
	
	
	/**
	 * Test all failure cases for commit.
	 */
	@Test
	public void testCommit() {
		this.setUp();
		Gitlet.init();
		
		Gitlet.add("Hi.txt");
		Gitlet.add("Bye.txt");

		Gitlet.commit("added hi and bye");
		
		Gitlet.commit("try again");
		
		assertTrue(outContent.toString().equals(
					"No changes have been added to the commit." + "\r\n"));
	}
	
	@Test
	public void testCommit2() {
		Gitlet.init();
		Gitlet.add("This.txt");
		Gitlet.commit("");
		assertTrue(outContent.toString().equals("Please enter a commit message." + "\r\n"));
		
	}

	/*
	 * Test failure case for remove.
	 */
	@Test
	public void testRM() {
		this.setUp();
		Gitlet.init();
		Gitlet.add("Hi.txt");
		Gitlet.add("Bye.txt");
		Gitlet.commit("added hi and bye");
		Gitlet.remove("Wug.txt");
		assertEquals("No reason to remove the file." + "\r\n",
				outContent.toString());

	}

	/**
	 * Test find failure case.
	 */
	@Test
	public void testFind() {
		this.setUp();
		Gitlet.init();
		Gitlet.add("Hi.txt");
		Gitlet.add("Bye.txt");
		Gitlet.commit("added hi and bye");
		Gitlet.add("Wug.txt");
		Gitlet.add("This.txt");
		Gitlet.commit("added this and wug");
		Gitlet.find("this is not a commit message");
		assertEquals("Found no commit with that message." + "\r\n",
				outContent.toString());
	}


	/**
	 * Test failure cases for status.
	 */
	@Test
	public void testStatus() {
		this.setUp();
		Gitlet.init();
		Gitlet.add("Hi.txt");
		Gitlet.add("Bye.txt");

		Gitlet.status();
		assertEquals("=== Branches ===" + "\r\n" + "*master" + "\r\n" + "\r\n"
				+ "=== Staged Files ===" + "\r\n"  + "Bye.txt" + "\r\n" + "Hi.txt" + "\r\n"
				+ "\r\n" + "=== Files Marked for Untracking ===" + "\r\n" + "\r\n",
				outContent.toString());
	}
	
	@Test
	public void testStatus2() {
		this.setUp();
		Gitlet.init();
		Gitlet.add("Hi.txt");
		Gitlet.add("Bye.txt");
		
		Gitlet.commit("added hi and bye");
		Gitlet.remove("Bye.txt");
		Gitlet.status();
		assertEquals("=== Branches ===" + "\r\n" + "*master" + "\r\n" + "\r\n"
				+ "=== Staged Files ===" + "\r\n" + "\r\n"
				+ "=== Files Marked for Untracking ===" + "\r\n" + "Bye.txt"
				+ "\r\n" + "\r\n", outContent.toString());
	}
	

	/**
	 * test failure cases for checkout.
	 */
	@Test
	public void testCheckout() {
		this.setUp();
		Gitlet.init();
		Gitlet.add("Hi.txt");
		Gitlet.add("Bye.txt");
		Gitlet.commit("added hi and bye");
	
		Gitlet.checkout("Hello.txt");
		assertEquals(outContent.toString(), "File does not exist in that commit, or no such branch exists."+ "\r\n");
	}
	
	@Test
	public void testCheckout2() {
		this.setUp();
		Gitlet.init();
		Gitlet.add("Hi.txt");
		Gitlet.add("Bye.txt");
		Gitlet.commit("added hi and bye");
		Gitlet.checkout("1", "GoodBye.txt");
		assertEquals(outContent.toString(), "File does not exist in that commit."+ "\r\n");	
	}
	
	@Test
	public void testCheckout3() {
		this.setUp();
		Gitlet.init();
		Gitlet.add("Hi.txt");
		Gitlet.add("Bye.txt");
		Gitlet.commit("added hi and bye");
		Gitlet.checkout("5", "Bye.txt");
		assertEquals(outContent.toString(), "No commit with that id exists."+ "\r\n");
	}
	
	@Test
	public void testCheckout4() {
		this.setUp();
		Gitlet.init();
		Gitlet.checkout("branch");
		assertEquals(outContent.toString(), "File does not exist in that commit, or no such branch exists."+ "\r\n");
	}
	
	@Test
	public void testCheckout5() {
		Gitlet.init();
		Gitlet.makebranch("other");		
		Gitlet.checkout("other");
		assertEquals(outContent.toString(), "No need to check out current branch."+ "\r\n");
		
	}

	/**
	 * Test failure cases for branch.
	 */
	@Test
	public void testBranch() {
		this.setUp();
		Gitlet.init();
		Gitlet.add("Hi.txt");
		Gitlet.add("Bye.txt");
		Gitlet.commit("added hi and bye");
		Gitlet.makebranch("branch");		
		Gitlet.makebranch("branch");
		assertEquals("A branch with that name already exists." + "\r\n", outContent.toString());			

	}

	/**
	 * Test failure cases for removing a branch.
	 */
	@Test
	public void testRemoveBranch() {
		this.setUp();	
		Gitlet.init();
		Gitlet.add("Hi.txt");
		Gitlet.add("Bye.txt");
		Gitlet.commit("added hi and bye");
		Gitlet.removeBranch("notabranch");
		assertEquals("A branch with that name does not exist." + "\r\n", outContent.toString());
	}
		
	@Test
	public void testRemoveBranch2() {
		this.setUp();	
		Gitlet.init();
		Gitlet.removeBranch("master");
		assertEquals("Cannot remove current branch." + "\r\n", outContent.toString());
	}

//	/**
//	 * Test failure case for reset.
//	 */
//	@Test
//	public void testReset() {
//		this.setUp();	
//		Gitlet.init();
//		Gitlet.add("Hi.txt");
//		Gitlet.add("Bye.txt");
//		Gitlet.commit("added hi and bye");
//		Gitlet.reset("3");
//		assertEquals("No commit with that id exists." + "\r\n", outContent,toString());
//	}
//
//	/**
//	 * Test merge failure cases.
//	 */
//	@Test
//	public void testMerge() {
//		this.setUp();	
//		Gitlet.init();
//		Gitlet.add("Hi.txt");
//		Gitlet.add("Bye.txt");
//		Gitlet.commit("added hi and bye");
//		Gitlet.merge("notabranch");
//		assertEquals("A branch with that name does not exist." + "\r\n", outContent.toString());
//	}
//	@Test
//	public void testMerge2() {
//		this.setUp();	
//		Gitlet.init();
//		Gitlet.merge("master");
//		assertEquals("Cannot merge a branch with itself." + "\r\n", outContent.toString());
//	}
//
	/**
	 * Test rebase failure cases.
	 */
	@Test
	public void testRebase() {
		this.setUp();	
		Gitlet.init();
		Gitlet.add("Hi.txt");
		Gitlet.add("Bye.txt");
		Gitlet.commit("added hi and bye");
		Gitlet.makebranch("other");
		Gitlet.checkout("other");
		Gitlet.rebase("notabranch");
		assertEquals("A branch with that name does not exist." + "\r\n", outContent.toString());
	}
	
	@Test
	public void testRebase2() {
		this.setUp();	
		Gitlet.init();
		Gitlet.add("Hi.txt");
		Gitlet.add("Bye.txt");
		Gitlet.commit("added hi and bye");
		Gitlet.makebranch("other");
		Gitlet.checkout("other");
		Gitlet.add("This.txt");
		Gitlet.commit("added this");
		Gitlet.rebase("other");
		assertEquals("Cannot rebase a branch onto itself." + "\r\n", outContent.toString());
	}
	
	@Test
	public void testRebase3(){
		this.setUp();	
		Gitlet.init();
		Gitlet.add("Hi.txt");
		Gitlet.add("Bye.txt");
		Gitlet.commit("added hi and bye");
		Gitlet.makebranch("other");
		Gitlet.checkout("other");
		Gitlet.add("This.txt");
		Gitlet.commit("added this");
		Gitlet.rebase("master");
		assertEquals("Already up-to-date." + "\r\n", outContent.toString());
	}
}
