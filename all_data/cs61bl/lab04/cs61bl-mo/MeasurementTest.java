import junit.framework.TestCase;


public class MeasurementTest extends TestCase {
	public void testConstructor() {
		Measurement lol = new Measurement();
		assertTrue(lol.getFeet() == 0);
		assertTrue(lol.getInches() == 0);
		Measurement pop = new Measurement(3);
		assertTrue(pop.getFeet() == 3);
		assertTrue(pop.getInches() == 0);
		Measurement bob = new Measurement(3,5);
		assertTrue(bob.getFeet() == 3);
		assertTrue(bob.getInches() == 5);
	}
	public void testgetFeet(){
		Measurement ok = new Measurement(3);
		assertTrue(ok.getFeet() == 3);
		Measurement laptop = new Measurement();
		assertTrue(laptop.getFeet() == 0);
		Measurement computer = new Measurement(5,3);
		assertTrue(computer.getFeet() == 5);
	}
	public void testInches(){
		Measurement ok = new Measurement(3,2);
		assertTrue(ok.getInches() == 2);
		Measurement ipad = new Measurement(3);
		assertTrue(ipad.getInches() == 0);
		Measurement ipod = new Measurement();
		assertTrue(ipod.getInches() == 0);
		
	}
	public void testMeasurementplus(){
		Measurement screen = new Measurement();
		Measurement chair = new Measurement();
		Measurement mouse = new Measurement(3,2);
		Measurement keyboard = new Measurement(7,2);
		Measurement cell = new Measurement(5);
		Measurement cable = new Measurement(1);
		Measurement headphones = new Measurement(3,11);
		Measurement spm = new Measurement();
		
		spm = screen.plus(mouse);
		assertTrue(spm.getFeet() == 3);
		assertTrue(spm.getInches() == 2);
		
		Measurement mpk = new Measurement();
		mpk = mouse.plus(keyboard);
		assertTrue(mpk.getFeet() == 10);
		assertTrue(mpk.getInches() == 4);
		
		Measurement spc = new Measurement();
		spc = screen.plus(cell);
		assertTrue(spc.getFeet() == 5);
		assertTrue(spc.getInches() == 0);
		
		Measurement cpm = new Measurement();
		cpm = cell.plus(mouse);
		assertTrue(cpm.getFeet() == 8);
		assertTrue(cpm.getInches() == 2);
		
		Measurement cpc = new Measurement();
		cpc = cable.plus(cell);
		assertTrue(cpc.getFeet() == 6);
		assertTrue(cpc.getInches() == 0);
		
		Measurement cps = new Measurement();
		cps = chair.plus(screen);
		assertTrue(cps.getFeet() == 0);
		assertTrue(cps.getInches() == 0);
		
		Measurement hpk = new Measurement();
		hpk = headphones.plus(keyboard);
		assertTrue(hpk.getFeet() == 11);
		assertTrue(hpk.getInches() == 1);
	} 
	public void testminus(){
		Measurement screen = new Measurement();
		Measurement chair = new Measurement();
		Measurement mouse = new Measurement(3,2);
		Measurement keyboard = new Measurement(7,2);
		Measurement cell = new Measurement(5);
		Measurement cable = new Measurement(1);
		Measurement headphones = new Measurement(3,11);
		
		Measurement mms = new Measurement();
		mms = mouse.minus(screen);
		assertTrue(mms.getFeet() == 3);
		assertTrue(mms.getInches() == 2);
		
		Measurement kmm = new Measurement();
		kmm = keyboard.minus(mouse);
		assertTrue(kmm.getFeet() == 4);
		assertTrue(kmm.getInches() == 0);
		
		Measurement cmc = new Measurement();
		cmc = cell.minus(chair);
		assertTrue(cmc.getFeet() == 5);
		assertTrue(cmc.getInches() == 0);
		
		Measurement cmm = new Measurement();
		cmm = cell.minus(mouse);
		assertTrue(cmm.getFeet() == 1);
		assertTrue(cmm.getInches() == 10);
		
		Measurement cmmc = new Measurement();
		cmmc = cell.minus(cable);
		assertTrue(cmmc.getFeet() == 4);
		assertTrue(cmmc.getInches() == 0);
		
		Measurement cms = new Measurement();
		cms = chair.minus(screen);
		assertTrue(cms.getFeet() == 0);
		assertTrue(cms.getInches() == 0);
		
		Measurement kmh = new Measurement();
		kmh = keyboard.minus(headphones);
		assertTrue(kmh.getFeet() == 3);
		assertTrue(kmh.getInches() == 3);
	}
	public void testmultiple(){
		Measurement screen = new Measurement();
		Measurement mouse = new Measurement(3,2);
		Measurement cell = new Measurement(5);
		
		Measurement mms = new Measurement();
		mms = screen.multiple(3);
		assertTrue(mms.getFeet() == 0);
		assertTrue(mms.getInches() == 0);
		
		Measurement kmm = new Measurement();
		kmm = mouse.multiple(3);
		assertTrue(kmm.getFeet() == 9);
		assertTrue(kmm.getInches() == 6);
		
		Measurement cmc = new Measurement();
		cmc = cell.multiple(3);
		assertTrue(cmc.getFeet() == 15);
		assertTrue(cmc.getInches() == 0);
		
	}
	public void testtoString(){
		Measurement screen = new Measurement();
		Measurement mouse = new Measurement(3,2);
		Measurement cell = new Measurement(5);
		String sts = "0\'0\"";
		String tts = screen.toString();
		assertTrue(sts.equals(tts));
		String mts = "3\'2\"";
		String mms = mouse.toString();
		assertTrue(mms.equals(mts));
		String cts = "5\'0\"";
		String ccs = cell.toString();
		assertTrue(ccs.equals(cts));
		
	}
}
