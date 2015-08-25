import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	
	public void testInit (){
		Measurement foot = new Measurement();
		Measurement toe = new Measurement(1);
		Measurement heel = new Measurement(2, 3);
		assertTrue(foot.feet == 0 && foot.inches == 0 &&
				   toe.feet == 1 && toe.inches == 0 &&
				   heel.feet == 2 && heel.inches == 3);
	}
	
	public void testgetFeet () {
		Measurement foot = new Measurement();
		Measurement toe = new Measurement(1);
		Measurement heel = new Measurement(2, 3);
		assertTrue(foot.getFeet() == 0 &&
				   toe.getFeet() == 1 &&
				   heel.getFeet() == 2);
	}
	
	public void testgetInches () {
		Measurement foot = new Measurement();
		Measurement toe = new Measurement(1);
		Measurement heel = new Measurement(2, 3);
		assertTrue(foot.getInches() == 0 &&
				   toe.getInches() == 0 &&
				   heel.getInches() == 3);
	}
	
	public void testPlus () {
		Measurement foot = new Measurement();
		Measurement toe = new Measurement(1);
		Measurement heel = new Measurement(2, 3);
		Measurement footToe = foot.plus(toe);
		Measurement toeHeel = toe.plus(heel);
		Measurement heelFoot = heel.plus(foot);
		assertTrue(foot.feet == 0 && foot.inches == 0 &&
				   toe.feet == 1 && toe.inches == 0 &&
				   heel.feet == 2 && heel.inches == 3);
		assertTrue(footToe.feet == 1 && footToe.inches == 0 &&
				   toeHeel.feet == 3 && toeHeel.inches == 3 &&
				   heelFoot.feet == 2 && heelFoot.inches == 3);
	}
	
	public void testMinus () {
		Measurement foot = new Measurement();
		Measurement toe = new Measurement(1);
		Measurement heel = new Measurement(2, 3);
		Measurement footToe = foot.minus(toe);
		Measurement toeHeel = toe.minus(heel);
		Measurement heelFoot = heel.minus(foot);
		assertTrue(foot.feet == 0 && foot.inches == 0 &&
				   toe.feet == 1 && toe.inches == 0 &&
				   heel.feet == 2 && heel.inches == 3);
		assertTrue(footToe.feet == -1 && footToe.inches == 0 &&
				   toeHeel.feet == -1 && toeHeel.inches == -3 &&
				   heelFoot.feet == 2 && heelFoot.inches == 3);
	}
	
	public void testMultiple () {
		Measurement foot = new Measurement();
		Measurement toe = new Measurement(1);
		Measurement heel = new Measurement(2, 3);
		Measurement bigFoot = foot.multiple(100);
		Measurement bigToe = toe.multiple(2);
		Measurement bigHeel = heel.multiple(4);
		assertTrue(foot.feet == 0 && foot.inches == 0 &&
				   toe.feet == 1 && toe.inches == 0 &&
				   heel.feet == 2 && heel.inches == 3);
		assertTrue(bigFoot.feet == 0 && bigFoot.inches == 0 &&
				   bigToe.feet == 2 && bigToe.inches == 0 &&
				   bigHeel.feet == 9 && bigHeel.inches == 0);
	}
	
	public void testtoString (){
		Measurement foot = new Measurement();
		Measurement toe = new Measurement(1);
		Measurement heel = new Measurement(2, 3);
		System.out.println(foot.toString());
		String a = "0\'0\"";
		System.out.println(a);
		assertTrue(foot.toString().equals("0\'0\"") &&
				   toe.toString().equals("1\'0\"") &&
				   heel.toString().equals("2\'3\""));
	}
	
}
