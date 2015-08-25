import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * Tests that our log records in the correct format and evyerhtnig.
 * 
 * @author Kevin Wu, Cynthia Diaz, Jaehyun Sim, Evan Patel
 *
 */
public class LogTest {

	public String dateConstructor() {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ft.format(date);
	}

	// Tests that the log works correctly with the correct commits, timestamps
	// and ID
	@Test
	public void test() {
		Log initialCommit = new Log(0, "initial commit", dateConstructor());
		System.out.println(initialCommit.toString());
		Log firstCommit = new Log(1, "first", dateConstructor());
		System.out.println(firstCommit);
	}

}
