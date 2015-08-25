import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.Test;

import junit.framework.TestCase;

public class CommitTest extends TestCase {
	/*
	 * java Gitlet init java Gitlet add Notes.txt java Gitlet Commit firstCommit
	 * 
	 * the following test is for the commands above only
	 */
	// public void testFirstCommit() {
	// Status status1 = (Status) FileOperation.read(".gitlet/myStatus");
	// Branch branch1 = status1.getActiveBranch();
	// assertEquals("master",branch1.getName());
	// assertTrue(branch1.getCommitId()==1);
	// Commit commit1 = (Commit)
	// FileOperation.read(".gitlet/commit"+branch1.getCommitId()+"/commit");
	// assertTrue(commit1.getFileCollection().size()==1);
	// assertTrue(commit1.getFileCommitId("Notes.txt") ==1);
	// System.out.println(commit1.getDate());
	// assertFalse(FileOperation.exists(".gitlet/stage/Merge.class"));
	//
	// }
	/*
	 * java Gitlet add Merge.class java Gitlet rm Notes.txt java Gitlet Commit
	 * commitagain .... commands missing the following test is for the commands
	 * above only
	 */
	// public void testSecondCommit() {
	// Status status1 = (Status) FileOperation.read(".gitlet/myStatus");
	// Branch branch1 = status1.getActiveBranch();
	// assertEquals("newbranch2",branch1.getName());
	// assertTrue(branch1.getCommitId()==2);
	// Commit commit1 = (Commit)
	// FileOperation.read(".gitlet/commit"+branch1.getCommitId()+"/commit");
	// assertTrue(commit1.getFileCollection().size()==1);
	// assertTrue(commit1.getFileCommitId("Merge.class") ==2);
	// System.out.println(commit1.getDate());
	// assertFalse(FileOperation.exists(".gitlet/stage/Merge.class"));
	// assertTrue(status1.getCommitTable().size()==3);
	// }
	public void testDir() {
		File a = new File("testResultsYours");
		System.out.println(a.list().length);
	}
}
