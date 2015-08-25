import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.junit.Test;


public class CommitNodeTest {

	@Test
	public void testGetNext() {
		int id1 = 123;
		int id2 = 456;
		String message1 = "Here is a message";
		String message2 = "Oh wow another message";
		HashMap<String, File> stag1 = new HashMap<String, File>();
		HashMap<String, File> stag2 = new HashMap<String, File>();
		HashMap<String, File> untrack1 = new HashMap<String, File>();
		HashMap<String, File> untrack2 = new HashMap<String, File>();
		
		CommitNode test1 = new CommitNode(id1, message1, null, stag1, untrack1);
		CommitNode test2 = new CommitNode(id2, message2, test1, stag2, untrack2);
		
		assertEquals(test2.getNext(), test1);
	}

	@Test
	public void testGetMessage() {
		int id1 = 123;
		int id2 = 456;
		String message1 = "Here is a message";
		String message2 = "Oh wow another message";
		HashMap<String, File> stag1 = new HashMap<String, File>();
		HashMap<String, File> stag2 = new HashMap<String, File>();
		HashMap<String, File> untrack1 = new HashMap<String, File>();
		HashMap<String, File> untrack2 = new HashMap<String, File>();
		
		CommitNode test1 = new CommitNode(id1, message1, null, stag1, untrack1);
		CommitNode test2 = new CommitNode(id2, message2, test1, stag2, untrack2);
		
		assertEquals(test1.getMessage(), message1);
		assertEquals(test2.getMessage(), message2);
	}

	@Test
	public void testGetId() {
		int id1 = 123;
		int id2 = 456;
		String message1 = "Here is a message";
		String message2 = "Oh wow another message";
		HashMap<String, File> stag1 = new HashMap<String, File>();
		HashMap<String, File> stag2 = new HashMap<String, File>();
		HashMap<String, File> untrack1 = new HashMap<String, File>();
		HashMap<String, File> untrack2 = new HashMap<String, File>();
		
		CommitNode test1 = new CommitNode(id1, message1, null, stag1, untrack1);
		CommitNode test2 = new CommitNode(id2, message2, test1, stag2, untrack2);

		assertEquals(test1.getId(), id1);
		assertEquals(test2.getId(), id2);
	}

	@Test
	public void testSetNext() {
		int id1 = 123;
		int id2 = 456;
		int id3 = 789;
		String message1 = "Here is a message";
		String message2 = "Oh wow another message";
		String message3 = "wowowow";
		HashMap<String, File> stag1 = new HashMap<String, File>();
		HashMap<String, File> stag2 = new HashMap<String, File>();
		HashMap<String, File> stag3 = new HashMap<String, File>();
		HashMap<String, File> untrack1 = new HashMap<String, File>();
		HashMap<String, File> untrack2 = new HashMap<String, File>();
		HashMap<String, File> untrack3 = new HashMap<String, File>();
		
		CommitNode test1 = new CommitNode(id1, message1, null, stag1, untrack1);
		CommitNode test2 = new CommitNode(id2, message2, test1, stag2, untrack2);
		CommitNode test3 = new CommitNode(id3, message3, test1, stag3, untrack3);
		
		test3.setNext(test2);
		assertEquals(test3.getNext(), test2);
	}

	@Test
	public void testPrint() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(Calendar.getInstance().getTime()).toString();
		//Cannot hard code time
		
		int id1 = 123;
		int id2 = 456;
		int id3 = 789;
		String message1 = "Here is a message";
		String message2 = "Oh wow another message";
		String message3 = "wowowow";
		HashMap<String, File> stag1 = new HashMap<String, File>();
		HashMap<String, File> stag2 = new HashMap<String, File>();
		HashMap<String, File> stag3 = new HashMap<String, File>();
		HashMap<String, File> untrack1 = new HashMap<String, File>();
		HashMap<String, File> untrack2 = new HashMap<String, File>();
		HashMap<String, File> untrack3 = new HashMap<String, File>();

		CommitNode test1 = new CommitNode(id1, message1, null, stag1, untrack1);
		CommitNode test2 = new CommitNode(id2, message2, test1, stag2, untrack2);
		CommitNode test3 = new CommitNode(id3, message3, test1, stag3, untrack3);
		
		String print1 = "===\nCommit " + id1 +"\n"+ time + "\n" + message1 + "\n";
		String print2 = "===\nCommit " + id2 +"\n"+ time + "\n" + message2 + "\n";
		String print3 = "===\nCommit " + id3 +"\n"+ time + "\n" + message3 + "\n";
		
		test1.print();
		System.out.println("Should be equal to: \n" + print1);
		test2.print();
		System.out.println("Should be equal to: \n" + print2);
		test3.print();
		System.out.println("Should be equal to: \n" + print3);
	}
}
