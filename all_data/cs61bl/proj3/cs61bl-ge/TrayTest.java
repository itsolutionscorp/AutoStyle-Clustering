
import junit.framework.TestCase;

public class TrayTest extends TestCase {
	public void testInit() {
		Tray tray = new Tray("hard/big.tray.3", "hard/big.tray.3.goal");
		for (Move i : Tray.Path(tray)) {
			System.out.println(i.myMove[0] + " " + i.myMove[1] + " "
					+ i.myMove[2] + " " + i.myMove[3]);
		}
	}
}
