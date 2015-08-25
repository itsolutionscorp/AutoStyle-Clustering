import java.io.File;
import java.nio.file.Paths;
import java.util.Stack;

import junit.framework.TestCase;

public class FileOperationTest extends TestCase {
	public void testCreateAndDelete() {
		FileOperation.makeNewFolder("testFolder1");
		assertTrue(FileOperation.exists("testFolder1"));
		FileOperation.delete("testFolder1");
		assertFalse(FileOperation.exists("testFolder1"));
	}

	// public void testCopyFile(){
	// FileOperation.makeNewFolder("testFolder1");
	// FileOperation.copy("1.pdf","testFolder1/1.pdf");
	// assertTrue(FileOperation.exists("testFolder1/1.pdf"));
	// FileOperation.delete("1.pdf");
	// FileOperation.rename("2.pdf","1.pdf");
	// FileOperation.copy("testFolder1/1.pdf","1.pdf");
	// FileOperation.delete("testFolder1");
	// assertFalse(FileOperation.exists("testFolder1"));
	// FileOperation.makeNewFolder("test2");
	// FileOperation.copy("test1/1.pdf","test2/test1/1.pdf");
	// assertTrue(FileOperation.exists("test2/test1/1.pdf"));
	// FileOperation.rename("test2/test1/1.pdf","test2/test1/2.pdf");
	// FileOperation.copy("test2/test1/2.pdf","test1/2.pdf");
	// }
	public void testWriteAndRead() {
		FileOperation.makeNewFolder("testFolder2");
		String a = "abc";
		FileOperation.write(a, "testFolder2/testA");
		String b = (String) FileOperation.read("testFolder2/testA");
		assertEquals(b, a);
		FileOperation.delete("testFolder2");
	}

	public void testRename() {
		FileOperation.makeNewFolder("testFolder1");
		FileOperation.rename("testFolder1", "testFolder2");
		assertFalse(FileOperation.exists("testFolder1"));
		assertTrue(FileOperation.exists("testFolder2"));
		FileOperation.delete("testFolder2");
	}

}
